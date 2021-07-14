package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.dao.CardBinDao;
import com.hml.atp.zues.model.bo.BankCardInfoBO;
import com.hml.atp.zues.model.entity.CardBin;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import com.hml.atp.zues.service.BankCardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hanminglu
 */
@Service
@Slf4j
public class BankCardInfoImpl implements BankCardInfo {

    @Resource
    private CardBinDao cardBinDao;

    @Override
    public List<BankCardInfoVO> getBankCardInfo(BankCardInfoBO bankCardInfoBO) {
        List<CardBin> cardBinList = cardBinDao.selectByBankCardInfo(bankCardInfoBO);
        if (cardBinList == null || cardBinList.isEmpty()) {
            return null;
        }
        log.info("获取卡BIN信息结果为：{}", cardBinList);
        List<BankCardInfoVO> bankCardInfoVOList = new ArrayList<>();
        for (CardBin cardBin : cardBinList) {
            BankCardInfoVO bankCardInfoVO = new BankCardInfoVO();
            bankCardInfoVO.setCardNo(generateCardNo(cardBin.getCardBin(), cardBin.getCodeLength()));
            bankCardInfoVO.setBankName(cardBin.getIssuingBankName());
            bankCardInfoVO.setCardName(cardBin.getCardName());
            bankCardInfoVO.setCardType(cardBin.getCardType());

            bankCardInfoVOList.add(bankCardInfoVO);
        }
        return bankCardInfoVOList;
    }


    /**
     * @param cardBin
     * @param codeLength
     * @return cardNo
     * @author hanminglu
     *
     * <p>
     * 生成n位符合Luhn规则的卡号的算法：
     * <p>
     * 1. 随机生成n-1位字符，称为字符串x。
     * <p>
     * 2. 先假设字符串x有n位（实际上最右边一位缺失是n-1位），将x按照n位长度计算和s，因为最右边第一位是缺失的，忽略跳过，所以计算时最右边一位从2开始。
     * <p>
     * 3. 上一步得到字符串x的校验和s，将s加上一个数字y，使得它正好可以整除10，这个y就是最右边第一位应该放的数字。
     * <p>
     * 4. x+y做字符串拼接运算，得到最终的n位符合Luhn规则的字符串。
     */
    private String generateCardNo(String cardBin, Integer codeLength) {
        StringBuilder cardNo = new StringBuilder(cardBin);
        Random random = new Random();
        //随机生成前N-1位
        while (cardNo.length() < codeLength - 1) {
            cardNo.append(random.nextInt(10));
        }
        log.info("生成的前N-1位数值为：{},长度为：{} ,实际卡号长度为:{}", cardNo, cardNo.length(), codeLength);
        //计算前N-1位的校验和
        int sum = 0;
        for (int i = cardNo.length() - 1; i >= 0; --i) {
            char t = cardNo.charAt(i);
            int temp = Integer.parseInt(String.valueOf(t));
            //序号：用于判断奇数位 还是偶数位
            int orderNo = codeLength - i;
            if ((orderNo & 1) == 1) {
                sum += temp;
            } else {
                temp = temp * 2;
                sum += temp < 10 ? temp : (temp % 10 + temp / 10);
            }
        }
        //最后一位当做是校验位，用来补齐到能够整除10
        log.info("sum为{}", sum);
        int lastNum = 10 - sum % 10;
        cardNo.append(lastNum == 10 ? 0 : lastNum);
        log.info("生成的卡号为：{}", cardNo.toString());
        return cardNo.toString();
    }

    /**
     * 功能描述
     * 检验数字算法（Luhn Check Digit Algorithm）
     * 银行卡号码的校验采用Luhn算法，校验过程大致如下：
     * <p>
     * 1. 从右到左给卡号字符串编号，最右边第一位是1，最右边第二位是2，最右边第三位是3….
     * <p>
     * 2. 从右向左遍历，对每一位字符t执行第三个步骤，并将每一位的计算结果相加得到一个数s。
     * <p>
     * 3. 对每一位的计算规则：如果这一位是--奇数位，则返回t本身，如果是--偶数位，则先将t乘以2得到一个数n，如果n是一位数（小于10），
     * 直接返回n，否则将n的个位数和十位数相加返回。
     * <p>
     * 4. 如果s能够整除10，则此号码有效，否则号码无效。
     * <p>
     * 因为最终的结果会对10取余来判断是否能够整除10，所以又叫做模10算法
     *
     * @param cardNo
     * @return java.lang.Boolean
     * @author hanminglu
     * @date 2021/4/13
     */
    private static Boolean luhn(String cardNo) {

        int sum = 0;
        int orderNo = 1;
        for (int i = cardNo.length() - 1; i >= 0; --i) {
            char t = cardNo.charAt(i);
            int temp = Integer.parseInt(String.valueOf(t));
            //序号：用于判断奇数位 还是偶数位
            if ((orderNo & 1) == 1) {
                sum += temp;
            } else {
                temp = temp * 2;
                sum += temp < 10 ? temp : (temp % 10 + temp / 10);
            }
            orderNo++;
        }

        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        //Java中“ /  ”为除法运算符，并且运算结果遵从向下取整
        //Python中 “ /  ”为浮点数除法，返回浮点结果  “//” 为整数除法，运算结果遵从向下取整
        System.out.println(2 / 3);
        System.out.println(BankCardInfoImpl.luhn("6225768744822279"));
    }
}
