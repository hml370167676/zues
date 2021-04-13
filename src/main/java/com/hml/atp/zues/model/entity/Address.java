package com.hml.atp.zues.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 省份代码
     */
    private Long provinceCode;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 城市代码
     */
    private Long cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 区县代码
     */
    private Long districtCode;
    /**
     * 区县名称
     */
    private String districtName;

}
