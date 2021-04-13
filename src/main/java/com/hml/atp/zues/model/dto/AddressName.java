package com.hml.atp.zues.model.dto;

import lombok.Data;

/**
 * @author hanminglu
 * @description
 * @date 2021/4/12
 */
@Data
public class AddressName {

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 区县名称
     */
    private String districtName;
}
