package com.hml.atp.zues.model.ifo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IdentityIFO {

    @ApiModelProperty(value = "省", example = "北京市")
    private String province;

    @ApiModelProperty(value = "市", example = "市辖区")
    private String city;

    @ApiModelProperty(value = "区", example = "海淀区")
    private String district;

    @ApiModelProperty(value = "性别 1:男 2：女", example = "1")
    private Integer sex;

    @ApiModelProperty(value = "出生日期", example = "19900101")
    private String birthday;
}
