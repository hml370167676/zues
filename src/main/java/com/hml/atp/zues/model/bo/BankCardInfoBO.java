package com.hml.atp.zues.model.bo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hanminglu
 */
@Data
public class BankCardInfoBO implements Serializable {

    /**
     * 发卡行名称
     */
    @ApiModelProperty(value = "发卡行名称", example = "招商银行")
    private String bankName;

    /**
     * 卡名
     */
    @ApiModelProperty(value = "卡名称", example = "招商银行")
    private String cardName;

    /**
     * 卡种
     */
    @ApiModelProperty(value = "卡类型：借记卡、贷记卡、准贷记卡", example = "借记卡")
    @ApiParam(defaultValue = "借记卡")
    private String cardType;
    /**
     * 数量
     */
    @ApiModelProperty(value = "生成数量", example = "5")
    @ApiParam(defaultValue = "5")
    private Integer size;
}
