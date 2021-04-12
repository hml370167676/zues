package com.hml.atp.zues.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hanminglu
 */
@Data
public class BankCardInfoVO implements Serializable {

    private Integer id;

    /**
     * 发卡行名称
     */
    private String issuingBankName;

    /**
     * 卡名
     */
    private String cardName;

    /**
     * 卡号
     */
    private Integer cardNo;

    /**
     * 卡种
     */
    private String cardType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", issuingBankName=").append(issuingBankName);
        sb.append(", cardName=").append(cardName);
        sb.append(", cardNo").append(cardNo);
        sb.append(", cardType=").append(cardType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
