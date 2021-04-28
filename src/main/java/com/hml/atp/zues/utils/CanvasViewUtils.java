package com.hml.atp.zues.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;


/**
 * 图像处理工具
 * <p>
 * 创建人：爱踢攻城狮
 * 创建时间：2018-8-2
 * 修改人：
 * 修改时间：2018-8-2
 * 修改备注：
 */
@SuppressWarnings("unchecked")
public class CanvasViewUtils {
    /**
     * 图片素材
     */
    private final static Map materials = null;

    //色差范围0~255
    public static int color_range = 210;

    /**
     * 合成身份证背面
     *
     * @param infos
     * @return 合成结果BASE64
     * @throws Exception
     * @author 爱踢攻城狮
     * @date 2018-8-2
     */
    public static String loadIDCardBacked(Map infos) throws Exception {
        //获取素材
        Image img = ImageIO.read(BASE64ToStream(getIDCardBacked()));
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);
        //获取画笔
        BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(img, 0, 0, imgWidth, imgHeight, null);
        //设置文字格式
        Font font = new Font("黑体", Font.PLAIN, 12);
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString(String.valueOf(infos.get("pPolice")), 200, 232);
        g.drawString(String.valueOf(infos.get("pEffDate")) + "-" + String.valueOf(infos.get("pExpDate")), 200, 272);
        g.dispose();
        //输出图片
        ByteArrayOutputStream outImgStream = new ByteArrayOutputStream();
        ImageIO.write(bufImg, "jpg", outImgStream);
        outImgStream.flush();
        outImgStream.close();
        return StreamToBASE64(outImgStream);
    }

    /**
     * 合成身份证正面
     *
     * @param infos
     * @return 合成结果BASE64
     * @throws Exception
     * @author 爱踢攻城狮
     * @date 2018-8-3
     */
    public static String loadIDCardFaced(Map infos) throws Exception {
        String pName = String.valueOf(infos.get("pName"));
        String pSex = String.valueOf(infos.get("pSex"));
        String pNation = String.valueOf(infos.get("pNation"));
        String pBorn = String.valueOf(infos.get("pBorn"));
        String pAddress = String.valueOf(infos.get("pAddress"));
        String pCardNo = String.valueOf(infos.get("pCardNo"));
        String pCardImg = String.valueOf(infos.get("pCardImg"));
        BufferedImage cardImg = ImageIO.read(BASE64ToStream(pCardImg));
        cardImg = clearBackGroud(cardImg);
        //获取素材
        Image img = ImageIO.read(BASE64ToStream(getIDCardFaced()));
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);
        //获取画笔
        BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(img, 0, 0, imgWidth, imgHeight, null);
        //设置文字格式
        Font font = new Font("黑体", Font.PLAIN, 17);
        g.setFont(font);
        g.setColor(Color.BLACK);
        //姓名
        g.drawString(pName, 90, 58);
        //其他
        g.setFont(new Font("黑体", Font.PLAIN, 15));
        g.drawString(pSex, 90, 96);
        g.drawString(pNation, 195, 96);
        g.drawString(Integer.valueOf(pBorn.substring(0, 4)) + "", 90, 134);
        g.drawString(Integer.valueOf(pBorn.substring(4, 6)) + "", 170, 134);
        g.drawString(Integer.valueOf(pBorn.substring(6)) + "", 215, 134);
        g.drawString(pAddress.substring(0, 11), 90, 174);
        if (23 > pAddress.length()) {
            g.drawString(pAddress.substring(11), 90, 194);
        } else {
            g.drawString(pAddress.substring(11, 22), 90, 194);
            g.drawString(pAddress.substring(22), 90, 214);
        }
        //身份证号
        g.setFont(new Font("华文细黑", Font.BOLD, 17));
        //        g.drawString(pCardNo, 168, 271);
        drawString(pCardNo, 168, 271, 1.4, g);
        //头像
        g.drawImage(cardImg, 280, 43, 155, 191, null);
        g.dispose();
        //输出图片
        ByteArrayOutputStream outImgStream = new ByteArrayOutputStream();
        ImageIO.write(bufImg, "jpg", outImgStream);
        outImgStream.flush();
        outImgStream.close();
        return StreamToBASE64(outImgStream);
    }

    /**
     * 自定义文字间距
     *
     * @param str  文字
     * @param x    起始位置x坐标
     * @param y    起始位置y坐标
     * @param rate 间距倍率
     * @param g    画笔
     * @author 爱踢攻城狮
     * @date 2018-8-3
     */
    public static void drawString(String str, int x, int y, double rate, Graphics2D g) {
        String tempStr = new String();
        int orgStringWight = g.getFontMetrics().stringWidth(str);
        int orgStringLength = str.length();
        int tempx = x;
        int tempy = y;
        while (str.length() > 0) {
            tempStr = str.substring(0, 1);
            str = str.substring(1, str.length());
            g.drawString(tempStr, tempx, tempy);
            tempx = (int) (tempx + (double) orgStringWight / (double) orgStringLength * rate);
        }
    }

    /**
     * 去底色
     *
     * @param image
     * @return
     * @author 爱踢攻城狮
     * @date 2018-8-3
     */
    public static BufferedImage clearBackGroud(BufferedImage image) {
        // 高度和宽度
        int height = image.getHeight();
        int width = image.getWidth();

        ImageIcon imageIcon = new ImageIcon(image);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        // 获取画笔
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        // 绘制Image的图片
        g2D.drawImage(imageIcon.getImage(), 0, 0, null);

        // 图片透明度
        int alpha = 0;
        // 外层遍历是Y轴的像素
        for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
            // 内层遍历是X轴的像素
            for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                // 对当前颜色判断是否在指定区间内
                if (colorInRange(rgb)) {
                    alpha = 0;
                } else {
                    // 设置为不透明
                    alpha = 255;
                }
                // #AARRGGBB 最前两位为透明度
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        // 绘制设置了RGB的新图片
        g2D.drawImage(bufferedImage, 0, 0, null);
        g2D.dispose();
        return bufferedImage;
    }

    /**
     * 判断是背景还是内容
     *
     * @param color
     * @return
     * @author 爱踢攻城狮
     * @date 2018-8-3
     */
    public static boolean colorInRange(int color) {
        // 获取color(RGB)中R位
        int red = (color & 0xff0000) >> 16;
        // 获取color(RGB)中G位
        int green = (color & 0x00ff00) >> 8;
        // 获取color(RGB)中B位
        int blue = (color & 0x0000ff);
        // 通过RGB三分量来判断当前颜色是否在指定的颜色区间内
        return red >= color_range && green >= color_range && blue >= color_range;
    }

    /**
     * 获取身份证正面素材
     *
     * @return 素材的base64编码
     * @author 爱踢攻城狮
     * @date 2018-8-2
     */
    public static String getIDCardFaced() {
        try {
            return String.valueOf(((Map) Objects.requireNonNull(materials).get("IDCard")).get("face"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取身份证背面素材
     *
     * @return 素材的base64编码
     * @author 爱踢攻城狮
     * @date 2018-8-2
     */
    public static String getIDCardBacked() {
        try {
            Set<Entry> set = materials.entrySet();
            for (Entry entry : set) {
                entry.getValue();
            }
            return String.valueOf(((Map) Objects.requireNonNull(materials).get("IDCard")).get("back"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * base64转化为输入流
     *
     * @param base64Str
     * @return InputStream
     * @throws IOException
     * @author 爱踢攻城狮
     * @date 2018-6-1
     */
    public static InputStream BASE64ToStream(String base64Str) throws IOException {
        byte[] bytes = new BASE64Decoder().decodeBuffer(base64Str);
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 输出流转为base64
     *
     * @param stream
     * @return string
     * @author 爱踢攻城狮
     * @date 2018-8-2
     */
    public static String StreamToBASE64(ByteArrayOutputStream stream) {
        return new BASE64Encoder().encode(stream.toByteArray());
    }
}