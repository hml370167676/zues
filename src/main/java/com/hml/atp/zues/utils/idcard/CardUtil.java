package com.hml.atp.zues.utils.idcard;

import com.hml.atp.zues.model.bo.IdentityCardInfoBO;
import com.hml.atp.zues.service.FileSystemService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;
import java.util.*;

/**
 * @author xxx
 */
@Component
public class CardUtil {

    @Resource
    private FileSystemService fileSystemService;

    @Value("${imagePath.baseDir}")
    private String baseDir;

    @Value("${imagePath.targetDir}")
    private String targetDir;

    List<String[]> list = new ArrayList<String[]>();
    List<String> listNian = new ArrayList<String>();
    List<String> listYue = new ArrayList<String>();
    List<String> listRi = new ArrayList<String>();
    List<String> listSan = new ArrayList<String>();

    private static String file1 = "", file2 = "";

    public void initArea() {
        String[] str;
        for (int i = 0; i < IdUtil.area.length; i += 2) {
            str = new String[2];
            if (IdUtil.area[i].length() > 5) {
                str[0] = IdUtil.area[i];
                str[1] = IdUtil.area[i + 1];
                list.add(str);
            }
        }
        for (int i = 1950; i < 1995; i++) {
            listNian.add("" + i);
        }
        for (int i = 1; i < 12; i++) {
            listYue.add(i < 10 ? "0" + i : "" + i);
        }
        for (int i = 1; i < 28; i++) {
            listRi.add(i < 10 ? "0" + i : "" + i);
        }
        for (int i = 100; i < 900; i++) {
            listSan.add("" + i);
        }
    }

    public String[] getAddress() {
        int index = (int) (Math.random() * (list.size() - 1) + 1);
        String[] str = list.get(index);
        String[] addess = new String[3];
        addess[0] = str[0];
        addess[2] = str[1];
        addess[1] = str[1]
                + IdUtil.address3[(int) (Math.random()
                * (IdUtil.address3.length - 1) + 1)];
        return addess;
    }

    public String[] getIdcode(String num) {
        String[] idcode = new String[5];
        idcode[0] = listNian
                .get((int) (Math.random() * (listNian.size() - 1) + 1));
        idcode[1] = listYue
                .get((int) (Math.random() * (listYue.size() - 1) + 1));
        idcode[2] = listRi.get((int) (Math.random() * (listRi.size() - 1) + 1));
        String _pinCode = num + idcode[0] + idcode[1] + idcode[2]
                + listSan.get((int) (Math.random() * (listSan.size() - 1) + 1));
        String sex = _pinCode.substring(14, 17);
        int sexInt = Integer.parseInt(sex);
        sex = sexInt % 2 == 0 ? "女" : "男";

        idcode[3] = sex;
        char[] _chrPinCode = _pinCode.toCharArray();
        // 校验码字符值
        char[] _chrVerify = new char[]{'1', '0', 'X', '9', '8', '7', '6',
                '5', '4', '3', '2'};
        // i----表示号码字符从由至左包括校验码在内的位置序号；
        // ai----表示第i位置上的号码字符值；
        // Wi----示第i位置上的加权因子，其数值依据公式intWeight=2（n-1）(mod 11)计算得出。
        int[] _intWeight = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
                5, 8, 4, 2, 1};
        int _craboWeight = 0;
        // 从1 到 17 位,18为要生成的验证码
        for (int i = 0; i < 17; i++) {
            _craboWeight = _craboWeight
                    + Integer.valueOf(String.valueOf(_chrPinCode[i]), 16)
                    * _intWeight[i];
        }
        _craboWeight = _craboWeight % 11;
        _pinCode += _chrVerify[_craboWeight];

        idcode[4] = _pinCode;
        System.out.println("证件号码  = " + _pinCode + ", 性别：" + sex);
        return idcode;
    }

    public String getExpDate() {
        String exp = "";
        List<String> list1 = new ArrayList<String>();
        for (int i = 2011; i < 2019; i++) {
            list1.add("" + i);
        }
        List<String> list2 = new ArrayList<String>();
        for (int i = 2025; i < 2033; i++) {
            list2.add("" + i);
        }
        exp += list1.get((int) (Math.random() * (list1.size() - 1) + 1));
        exp += "."
                + listYue.get((int) (Math.random() * (listYue.size() - 1) + 1));
        exp += "."
                + listRi.get((int) (Math.random() * (listRi.size() - 1) + 1));
        exp += "-" + list2.get((int) (Math.random() * (list1.size() - 1) + 1));
        exp += "."
                + listYue.get((int) (Math.random() * (listYue.size() - 1) + 1));
        exp += "."
                + listRi.get((int) (Math.random() * (listRi.size() - 1) + 1));
        return exp;
    }

    public String getName() {
        String xing = IdUtil.xing[(int) (Math.random() * (IdUtil.xing.length - 1) + 1)];
        String ming = IdUtil.mingzi[(int) (Math.random()
                * (IdUtil.mingzi.length - 1) + 1)];
        return xing + ming;
    }

    public String getZu() {
        return IdUtil.zu[(int) (Math.random() * (IdUtil.zu.length - 1) + 1)];
    }

    public static String getPic(int type) {
        String name = "idz_nan1.jpg";
        // 男
        if (type == 1) {
            name = IdUtil.idz_nan[(int) (Math.random()
                    * (IdUtil.idz_nan.length - 1) + 1)];
        } else {
            name = IdUtil.idz_nv[(int) (Math.random() * (IdUtil.idz_nv.length - 1) + 1)];
        }
        return name;
    }

    /**
     * 给图片添加水印
     *
     * @param filePath           需要添加水印的图片的路径
     * @param //markContent      水印的文字
     * @param //markContentColor 水印文字的颜色
     * @param //qualNum          图片质量
     * @param //fontType         字体
     * @param //fontsize         字体大小
     * @return
     * @author zhongweihai newwei2001@yahoo.com.cn
     */
    public boolean createMark(String filePath, String outFile,
                              List<Map<String, Object>> list) {
        System.out.println(filePath);
        System.out.println(outFile);
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image theImg = imgIcon.getImage();
        int width = theImg.getWidth(null);
        int height = theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();
        g.drawImage(theImg, 0, 0, null);
        for (int i = 0; i < list.size(); i++) {
            add(g, bimage, list.get(i));
        }
        g.dispose();
        try {
            FileOutputStream out = new FileOutputStream(outFile);


            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);

            param.setQuality(70f, true);
            encoder.encode(bimage, param);
            out.close();
        } catch (Exception e) {
            return false;
        } finally {
            imgIcon = null;
            theImg = null;
        }
        return true;
    }

    public void add(Graphics2D g, BufferedImage bimage, Map<String, Object> map) {
        String markContent = (String) map.get("text");
        g.setColor((Color) map.get("color"));
        g.setBackground(Color.white);

        AttributedString ats = new AttributedString(markContent);
        Font f = new Font((String) map.get("fontType"), Font.BOLD,
                (Integer) map.get("fontSize"));
        ats.addAttribute(TextAttribute.FONT, f, 0, markContent.length());
        AttributedCharacterIterator iter = ats.getIterator();
        // 添加水印的文字和设置水印文字出现的内容
        g.drawString(iter, (Integer) map.get("x"), (Integer) map.get("y"));
    }

    public String generateIdcodeZ(IdentityCardInfoBO identityCardInfoBO) {
        String cert = null;
        boolean bool = false;
        try {
            this.initArea();
            String[] str = this.getAddress();
            String[] idcode = this.getIdcode(str[0]);

            System.out.println(str[0] + "," + str[1]);

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            Color color1 = new Color(30, 30, 30);
            // 姓名
            map.put("text", this.getName());
            map.put("color", color1);
            map.put("x", 90);
            map.put("y", 48);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            map = new HashMap<String, Object>();
            // 性别
            map.put("text", idcode[3]);
            map.put("color", color1);
            map.put("x", 90);
            map.put("y", 94);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            map = new HashMap<String, Object>();
            // 民族
            map.put("text", this.getZu());
            map.put("color", color1);
            map.put("x", 210);
            map.put("y", 94);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            map = new HashMap<String, Object>();
            // 年
            map.put("text", idcode[0]);
            map.put("color", color1);
            map.put("x", 90);
            map.put("y", 136);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            map = new HashMap<String, Object>();
            // 月
            map.put("text", idcode[1]);
            map.put("color", color1);
            map.put("x", 180);
            map.put("y", 136);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            map = new HashMap<String, Object>();
            // 日
            map.put("text", idcode[2]);
            map.put("color", color1);
            map.put("x", 230);
            map.put("y", 136);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            String address = str[1];
            String[] adds = new String[2];
            if (address.length() > 11) {
                adds[0] = address.substring(0, 11);
                adds[1] = address.substring(11);
            } else {
                adds[0] = address;
                adds[1] = "";
            }
            map = new HashMap<String, Object>();
            // 证件地址1
            map.put("text", adds[0]);
            map.put("color", color1);
            map.put("x", 90);
            map.put("y", 184);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);
            if (address.length() > 11) {
                map = new HashMap<String, Object>();
                // 证件地址2
                map.put("text", adds[1]);
                map.put("color", color1);
                map.put("x", 90);
                map.put("y", 210);
                map.put("fontType", "幼圆");
                map.put("fontSize", 18);
                list.add(map);
            }

            map = new HashMap<String, Object>();
            // 证件号码
            map.put("text", idcode[4]);
            map.put("color", color1);
            map.put("x", 167);
            map.put("y", 296);
            map.put("fontType", "微软雅黑");
            map.put("fontSize", 24);
            list.add(map);

            if ("男".equals(idcode[3])) {
                file1 = baseDir + "/" + getPic(1);
            } else {
                file1 = baseDir + "/" + getPic(2);
            }
            this.createMark(file1, baseDir + "/idz/" + idcode[4] + ".jpg", list);
            System.out.println("民族：" + this.getZu() + "，出生日期：" + idcode[0] + idcode[1] + idcode[2]);
            System.out.println("证件正面照【" + targetDir + "idz" + idcode[4] + ".jpg"
                    + "】生成成功");
            Thread.sleep(200);

            File file = new File(baseDir + "/idz/" + idcode[4] + ".jpg");
            InputStream is = new FileInputStream(file);
            String filePathZ = targetDir + list.get(0).get("text") + "_" + idcode[4] + "_正面.jpg";
            String filePathF = targetDir + list.get(0).get("text") + "_" + idcode[4] + "_反面.jpg";

            bool = fileSystemService.uploadFile(filePathZ, is);
            generateIdcodeF(identityCardInfoBO, filePathF);

            if (bool) {
                cert = idcode[4];
            }
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return cert;
    }

    public boolean generateIdcodeF(IdentityCardInfoBO identityCardInfoBO, String targetPath) {
        boolean bool = false;
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            Color color1 = new Color(30, 30, 30);
            map.put("text", identityCardInfoBO.getIssuedName());
            map.put("color", color1);
            map.put("x", 220);
            map.put("y", 263);
            map.put("fontType", "幼圆");
            map.put("fontSize", 18);
            list.add(map);

            map = new HashMap<>();
            map.put("text", this.getExpDate());
            map.put("color", color1);
            map.put("x", 220);
            map.put("y", 310);
            map.put("fontType", "微软雅黑");
            map.put("fontSize", 18);
            list.add(map);
            this.createMark(baseDir + "/template/idf.jpg", baseDir + "tmp/idf/idf.jpg", list);
            bool = true;
            System.out.println("证件反面照【" + targetDir + "】生成成功");


            File file = new File(baseDir + "/idf/idf.jpg");
            InputStream is = new FileInputStream(file);

            fileSystemService.uploadFile(targetPath, is);

        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }

    public static void copyFormJar(CardUtil wm, String fileUrl, String dest) {
        try {
            // 返回读取指定资源的输入流
            InputStream in = wm.getClass().getResourceAsStream(fileUrl);
            int BUFF_SIZE = 100000;
            byte[] buffer = new byte[BUFF_SIZE];
            OutputStream out = null;
            try {
                out = new FileOutputStream(dest);
                while (true) {
                    synchronized (buffer) {
                        int amountRead = in.read(buffer);
                        if (amountRead == -1) {
                            break;
                        }
                        out.write(buffer, 0, amountRead);
                    }
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void byteToString(byte[] byt) {
        String s = new BASE64Encoder().encode(byt);
        System.out.print(s);
    }

    public static String getJarDir(String url) {
        if (url != null && url.startsWith("file")) {
            url = url.substring(6);
            url = url.substring(0, url.indexOf("!"));
            File jar = new File(url);
            url = jar.getParentFile().getPath();
        }
        return url;
    }

    public static void createDir(String dir) {
        if (dir != null) {
            File file = new File(dir);
            if (!file.exists() || file.isFile()) {
                file.mkdirs();
            }
        }
    }

    public static void help() {
        try {
            String str1 = "------身份证自动生成工具 v1.3----------------";
            String str2 = "------作者：隐心--QQ:214175590-----------";
            String str3 = "------此工具只用作开发测试之用，切勿用作非法用途------";
            String str4 = "退出程序指令：exit";
            String[] p1 = str1.split("");
            String[] p2 = str2.split("");
            String[] p3 = str3.split("");
            String[] p4 = str4.split("");
            for (String element : p1) {
                Thread.sleep(5);
                System.out.print(element);
            }
            System.out.println();
            for (String item : p2) {
                Thread.sleep(5);
                System.out.print(item);
            }
            System.out.println();
            for (String value : p3) {
                Thread.sleep(5);
                System.out.print(value);
            }
            System.out.println();
            for (String s : p4) {
                Thread.sleep(5);
                System.out.print(s);
            }
            System.out.println("\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void checkImage(CardUtil wm, String baseDir) {
        String file;
        File fileo1;
        for (int i = 0; i < IdUtil.idz_nan.length; i++) {
            file = baseDir + "\\images\\" + IdUtil.idz_nan[i];
            fileo1 = new File(file);
            if (!fileo1.exists()) {
                copyFormJar(wm, "/" + IdUtil.idz_nan[i], file);
            }
        }

        for (int i = 0; i < IdUtil.idz_nv.length; i++) {
            file = baseDir + "\\images\\" + IdUtil.idz_nv[i];
            fileo1 = new File(file);
            if (!fileo1.exists()) {
                copyFormJar(wm, "/" + IdUtil.idz_nv[i], file);
            }
        }
        file2 = baseDir + "\\images\\" + "idf.jpg";
        File fileo2 = new File(file2);
        if (!fileo2.exists()) {
            copyFormJar(wm, "/idf.jpg", file2);
        }
    }

    public static int parseInt(String param, int dat) {
        if (param != null) {
            try {
                dat = Integer.parseInt(param);
            } catch (Exception e) {
                System.out.println(param + " 是非数字...默认给值 " + dat);
            }
        }
        return dat;
    }

    public void main(String[] args) {
        try {
            help();
            Scanner sc = new Scanner(System.in);
            String arg = "";
            while (!"exit".equals(arg)) {
                System.out.println("请输入需要生成身份证照片的数量（正反面一起算一份）...");
                arg = sc.nextLine();
                if ("-help".equals(arg)) {
                    help();
                    arg = sc.nextLine();
                } else if ("exit".equals(arg)) {
                    System.out.println("\n");
                    int c = 0;
                    String[] wel = {"谢", "谢", "使", "用", "!", "", "", "再", "见", "!"};
                    while (c < 10) {
                        Thread.sleep(50);
                        if (c < 4) {
                            System.out.print(wel[c]);
                        } else if (c > 4 && c < 7) {
                            System.out.println();
                        } else {
                            System.out.print(wel[c]);
                        }
                        c++;
                    }
                    System.exit(-1);
                } else {
                    int num = parseInt(arg, 1);

                    CardUtil wm = new CardUtil();
                    String url = wm.getClass().getResource("/idf.jpg").getPath();
                    url = IdUtil.StringDecode(url);
                    String baseDir = getJarDir(url);
                    createDir(baseDir + "\\images\\");
                    String newDir = baseDir + "\\idcode\\";

                    System.out.println("请输入需要要保存的路径（默认保存当前路径，若要默认直接按回车即可）...");
                    arg = sc.nextLine();

                    if (args.length > 1) {
                        newDir = args[1];
                    } else {
                        createDir(newDir);
                    }
                    checkImage(wm, baseDir);

                    for (int i = 0; i < num; i++) {
                        generateIdcodeZ(new IdentityCardInfoBO());
                        System.out.println("生成第" + (i + 1) + "份成功完成........"
                                + ((float) (i + 1) / (float) num) * 100 + "%");
                        Thread.sleep(100);
                    }
                    System.out.println("\n☆☆☆☆☆☆☆☆ 全部完成 ☆☆☆☆☆☆☆☆\n");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
