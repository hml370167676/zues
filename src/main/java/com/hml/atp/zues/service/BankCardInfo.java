package com.hml.atp.zues.service;

import com.hml.atp.zues.model.bo.BankCardInfoBO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;

import java.util.List;

/**
 * @author hanminglu
 */
public interface BankCardInfo {
    /**
     * 生成银行卡号
     *
     * @param bankCardInfoBO
     * @return
     */
    List<BankCardInfoVO> getBankCardInfo(BankCardInfoBO bankCardInfoBO);
}
