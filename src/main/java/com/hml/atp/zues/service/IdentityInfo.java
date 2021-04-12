package com.hml.atp.zues.service;

import com.hml.atp.zues.model.ifo.IdentityIFO;

import java.util.List;

public interface IdentityInfo {
    /**
     * 获取身份证号
     *
     * @param identityIFO
     * @return
     */
    List<String> getIdentityList(IdentityIFO identityIFO);
}
