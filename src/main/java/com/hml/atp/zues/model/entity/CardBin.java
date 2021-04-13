package com.hml.atp.zues.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * card_bin
 *
 * @author
 */
@Data
public class CardBin implements Serializable {
    private Integer id;

    /**
     * 发卡行名称
     */
    private String issuingBankName;

    /**
     * Organization code 机构代码
     */
    private Integer orgCode;

    /**
     * 卡名
     */
    private String cardName;

    /**
     * 卡号长度
     */
    private Integer codeLength;

    /**
     * 卡BIN长度
     */
    private Integer cardBinLength;

    /**
     * 卡BIN
     */
    private String cardBin;

    /**
     * 卡种 借记卡、贷记卡、准贷记卡
     */
    private String cardType;

    private static final long serialVersionUID = 1L;

}