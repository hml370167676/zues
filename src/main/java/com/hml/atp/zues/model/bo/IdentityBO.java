package com.hml.atp.zues.model.bo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hanminglu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityBO {

    @ApiModelProperty(value = "省", example = "北京市")
    private String province;

    @ApiModelProperty(value = "市", example = "市辖区")
    private String city;

    @ApiModelProperty(value = "区", example = "海淀区")
    private String district;

    @ApiModelProperty(value = "性别 1:男 2：女", example = "1")
    @ApiParam(defaultValue = "1")
    private Integer sex = 1;

    @ApiModelProperty(value = "出生日期", example = "19900101")
    private String birthday;

    @ApiModelProperty(value = "生成数量", example = "5")
    @ApiParam(defaultValue = "5")
    private Integer size = 5;
}
