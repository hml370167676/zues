package com.hml.atp.zues.service;

import com.hml.atp.zues.model.ifo.BankCardInfoIFO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;

import java.util.List;

/**
 * @author hanminglu
 */
public interface BankCardInfo {
    /**
     * 生成银行卡号
     *
     * @param bankCardInfoIFO
     * @return
     */
    List<BankCardInfoVO> getBankCardInfo(BankCardInfoIFO bankCardInfoIFO);
}
