package com.hml.atp.zues.service;

import com.hml.atp.zues.model.bo.IdentityBO;

import java.util.List;

public interface IdentityInfo {
    /**
     * 获取身份证号
     *
     * @param identityBO
     * @return
     */
    List<String> getIdentityList(IdentityBO identityBO);
}
