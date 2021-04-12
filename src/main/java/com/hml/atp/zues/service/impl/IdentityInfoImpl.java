package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.dao.AddressDao;
import com.hml.atp.zues.model.dto.AddressName;
import com.hml.atp.zues.model.entity.Address;
import com.hml.atp.zues.model.ifo.IdentityIFO;
import com.hml.atp.zues.service.IdentityInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述
 * <p>
 * 中国居民身份证号码编码规则
 * 第一、二位表示省（自治区、直辖市、特别行政区）。
 * 第三、四位表示市（地级市、自治州、盟及国家直辖市所属市辖区和县的汇总码）。其中，01-20，51-70表示省直辖市；21-50表示地区（自治州、盟）。
 * 第五、六位表示县（市辖区、县级市、旗）。01-18表示市辖区或地区（自治州、盟）辖县级市；21-80表示县（旗）；81-99表示省直辖县级市。
 * 第七、十四位表示出生年月日（单数字月日左侧用0补齐）。其中年份用四位数字表示，年、月、日之间不用分隔符。例如：1981年05月11日就用19810511表示。
 * 第十五、十七位表示顺序码。对同地区、同年、月、日出生的人员编定的顺序号。其中第十七位奇数分给男性，偶数分给女性。
 * 第十八位表示校验码。作为尾号的校验码，是由号码编制单位按统一的公式计算出来的，校验码如果出现数字10，就用X来代替，详情参考下方计算方法。
 * <p>
 * 其中第一代身份证号码为15位。年份两位数字表示，没有校验码。
 * ------------------------------------------------------------------------------------
 * 中国居民身份证校验码算法
 * 步骤如下：
 * <p>
 * 将身份证号码前面的17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。
 * 将这17位数字和系数相乘的结果相加。
 * 用加出来和除以11，取余数。
 * 余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。其分别对应的最后一位身份证的号码为1－0－X－9－8－7－6－5－4－3－2。
 * 通过上面计算得知如果余数是3，第18位的校验码就是9。如果余数是2那么对应的校验码就是X，X实际是罗马数字10。
 * 例如：某男性的身份证号码为【53010219200508011x】， 我们看看这个身份证是不是合法的身份证。首先我们得出前17位的乘积和【(5*7)+(3*9)+(0*10)+
 * (1*5)+(0*8)+(2*4)+(1*2)+(9*1)+(2*6)+(0*3)+(0*7)+(5*9)+(0*10)+(8*5)+(0*8)+(1*4)+(1*2)】是189，然后用189除以11得出的结果是189/
 * 11=17----2，也就是说其余数是2。最后通过对应规则就可以知道余数2对应的检验码是X。所以，可以判定这是一个正确的身份证号码。
 *
 * @author hanminglu
 * @date 2021/4/12
 */
@Service
@Slf4j
public class IdentityInfoImpl implements IdentityInfo {

    @Resource
    public AddressDao addressDao;

    /**
     * 计算校验码的加权因子
     */
    private static final List<Integer> FACTOR_NUMBER = Arrays.asList(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);

    /**
     * 最后一位校验码
     */
    private static final Map<Integer, String> CHECK_CODE;

    static {
        CHECK_CODE = new HashMap<>();
        CHECK_CODE.put(0, "1");
        CHECK_CODE.put(1, "0");
        CHECK_CODE.put(2, "X");
        CHECK_CODE.put(3, "9");
        CHECK_CODE.put(4, "8");
        CHECK_CODE.put(5, "7");
        CHECK_CODE.put(6, "6");
        CHECK_CODE.put(7, "5");
        CHECK_CODE.put(8, "4");
        CHECK_CODE.put(9, "3");
        CHECK_CODE.put(10, "2");
    }


    @Override
    public List<String> getIdentityList(IdentityIFO identityIFO) {
        List<String> identityList = new ArrayList<>();
        int count = 0;
        while (count < identityIFO.getSize()) {
            String addressCode = getAreaCode(identityIFO);
            String birthday = getBirthday(identityIFO.getBirthday());
            String sequenceCode = getSequenceCode(identityIFO.getSex());
            String identityNo = generateIdentityNo(addressCode, birthday, sequenceCode);
            identityList.add(identityNo);
            count++;
        }
        return identityList;
    }

    /**
     * 功能描述
     *
     * @param birthday
     * @return java.lang.String
     * @author hanminglu
     * @date 2021/4/12
     */
    public String getBirthday(String birthday) {
        if (birthday != null | !birthday.isEmpty()) {
            return birthday;
        } else {
            //用Date类处理时间，需要注意，年是从1900开始计算所以要减去1900，月是从0开始计算所以要减去1
            Long startDate = new Date(76, Calendar.JANUARY, 1).getTime();
            Long endDate = System.currentTimeMillis();
            double randomDate = Math.random() * (endDate - startDate) + startDate;
            Date resultDate = new Date(Math.round(randomDate));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            log.info("Birthday为空，开始生成随机出生日期:{}", formatter.format(resultDate));
            return formatter.format(resultDate);
        }
    }

    /**
     * 功能描述 :获取身份证15~17顺序码 男性为取奇数 女性取偶数
     *
     * @param sex
     * @return java.lang.String
     * @author hanminglu
     * @date 2021/4/12
     */
    public static String getSequenceCode(Integer sex) {

        Random random = new Random();
        if (sex == 1) {
            int sequenceCode = random.nextInt(100);
            int thirdNum;
            log.info("性别为男，开始生成奇数顺序码！");
            while (true) {
                int tmp = random.nextInt(10);
                if ((tmp & 1) == 1) {
                    thirdNum = tmp;
                    break;
                }
            }
            if (sequenceCode < 10) {
                return "0" + sequenceCode + thirdNum;
            } else {
                return sequenceCode + String.valueOf(thirdNum);
            }
        } else if (sex == 2) {
            log.info("性别为女，开始生成偶数顺序码!");
            int sequenceCode = random.nextInt(100);
            int thirdNum;
            while (true) {
                int tmp = random.nextInt(10);
                if ((tmp & 1) != 1) {
                    thirdNum = tmp;
                    break;
                }
            }
            if (sequenceCode < 10) {
                return "0" + sequenceCode + thirdNum;
            } else {
                return sequenceCode + String.valueOf(thirdNum);
            }
        } else {
            log.info("性别未设置，随机生成顺序码！");
            int sequenceCode = random.nextInt(1000);
            if (sequenceCode >= 100) {
                return String.valueOf(sequenceCode);
            } else if (10 <= sequenceCode) {
                return "0" + sequenceCode;
            } else {
                return "00" + sequenceCode;
            }
        }
    }

    /**
     * 功能描述 ：
     *
     * @param identityIFO
     * @return java.lang.String
     * @description 获取行政区代码
     * @author hanminglu
     * @date 2021/4/12
     */
    public String getAreaCode(IdentityIFO identityIFO) {

        if (identityIFO == null || identityIFO.getProvince().isEmpty() || identityIFO.getProvince() == null ||
                identityIFO.getCity().isEmpty() || identityIFO.getCity() == null || identityIFO.getDistrict().isEmpty() |
                identityIFO.getDistrict() == null) {
            log.info("没有省市区信息，开始随机获取省市区代码");
            return randomAddress().getDistrictCode().toString();
        } else {
            AddressName addressName = new AddressName();
            addressName.setProvinceName(identityIFO.getProvince().trim());
            addressName.setCityName(identityIFO.getCity().trim());
            addressName.setDistrictName(identityIFO.getDistrict().trim());
            Address address = addressDao.selectByAddressName(addressName);
            return address.getDistrictCode().toString();
        }
    }

    /**
     * 功能描述 获取随机省市区
     *
     * @return com.hml.atp.zues.model.entity.Address
     * @author hanminglu
     * @date 2021/4/12
     */
    public Address randomAddress() {
        int count = addressDao.getCount();
        int first = addressDao.getFirstId();
        Random random = new Random();
        int idengtityID = random.nextInt(count - first) + 1 + first;
        return addressDao.selectByPrimaryKey(idengtityID);
    }


    public String generateIdentityNo(String addressCode, String birthday, String sequenceCode) {
        String temp = addressCode + birthday + sequenceCode;
        int sum = 0;
        for (int i = 0; i < temp.length(); i++) {
            char c = temp.charAt(i);
            sum += Integer.parseInt(String.valueOf(c)) * FACTOR_NUMBER.get(i);
            log.info("********前17位与系数相乘的和为：{}********", sum);
        }
        int remainder = sum % 11;
        log.info("********获取的校验数为：{}********", CHECK_CODE.get(remainder));
        return temp + CHECK_CODE.get(remainder);

    }

    public static void main(String[] args) {
        System.out.println(getSequenceCode(1));
//        System.out.println(randomAddress());
    }
}
