package com.hml.atp.zues.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hanminglu
 * @description
 * @date 2021/5/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityCardInfoBO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生年月")
    private String birthday;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "身份证号码")
    private String identityNo;

    @ApiModelProperty(value = "有效期开始时间")
    private String effDate;

    @ApiModelProperty(value = "有效期截止时间")
    private String expDate;

    @ApiModelProperty(value = "签发机关")
    private String issuedName;


}
