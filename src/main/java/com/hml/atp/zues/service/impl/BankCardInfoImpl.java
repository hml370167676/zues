package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.dao.CardBinDao;
import com.hml.atp.zues.model.entity.CardBin;
import com.hml.atp.zues.model.ifo.BankCardInfoIFO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import com.hml.atp.zues.service.BankCardInfo;

import javax.annotation.Resource;

/**
 * @author hanminglu
 */
public class BankCardInfoImpl implements BankCardInfo {

    @Resource
    private CardBinDao cardBinDao;

    @Override
    public BankCardInfoVO getBankCardInfo(BankCardInfoIFO bankCardInfoIFO) {
        CardBin cardBin = cardBinDao.selectByBankCardInfo(bankCardInfoIFO);
        return null;
    }


    /**
     *     生成n位符合Luhn规则的卡号的算法：
     *
     *     1. 随机生成n-1位字符，称为字符串x。
     *
     *     2. 先假设字符串x有n位（实际上最右边一位缺失是n-1位），将x按照n位长度计算和s，因为最右边第一位是缺失的，忽略跳过，所以计算时最右边一位从2开始。
     *
     *     3. 上一步得到字符串x的校验和s，将s加上一个数字y，使得它正好可以整除10，这个y就是最右边第一位应该放的数字。
     *
     *     4. x+y做字符串拼接运算，得到最终的n位符合Luhn规则的字符串。
     * @param cardBin
     * @param codeLength
     * @return
     */
    public String generateCardNo(Integer cardBin, Integer codeLength) {
        return null;
    }
}
