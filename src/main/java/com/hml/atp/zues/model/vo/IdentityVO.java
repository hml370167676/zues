package com.hml.atp.zues.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IdentityVO implements Serializable{

    @ApiModelProperty(value = "身份证号")
    private String identityNo;

}
