package com.hml.atp.zues.service;

import com.hml.atp.zues.model.ifo.IdentityIFO;
import com.hml.atp.zues.model.vo.IdentityVO;

public interface IdentityInfo {
    /**
     * 获取身份证号
     * @param identityIFO
     * @return
     */
    IdentityVO getIdentityInfo(IdentityIFO identityIFO);
}
