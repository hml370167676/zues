package com.hml.atp.zues.service;

import com.hml.atp.zues.dao.AddressDao;
import com.hml.atp.zues.model.entity.Address;
import com.hml.atp.zues.model.ifo.IdentityIFO;
import com.hml.atp.zues.model.vo.IdentityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class IdentityInfoImpl implements IdentityInfo {

    @Resource
    public AddressDao addressDao;

    @Override
    public IdentityVO getIdentityInfo(IdentityIFO identityIFO) {
        Address address = addressDao.selectByPrimaryKey(1);
        return null;
    }
}
