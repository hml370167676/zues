package com.hml.atp.zues.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hanminglu
 */
@Data
public class BankCardInfoVO implements Serializable {

    /**
     * 发卡行名称
     */
    @ApiModelProperty(value = "发卡行名称")
    private String bankName;

    /**
     * 卡名
     */
    @ApiModelProperty(value = "卡名称")
    private String cardName;

    /**
     * 卡号
     */
    @ApiModelProperty(value = "卡号")
    private Integer cardNo;

    /**
     * 卡种
     */
    @ApiModelProperty(value = "卡类型")
    private String cardType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", issuingBankName=").append(bankName);
        sb.append(", cardName=").append(cardName);
        sb.append(", cardNo").append(cardNo);
        sb.append(", cardType=").append(cardType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
