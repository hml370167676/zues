package com.hml.atp.zues.dao;

import com.hml.atp.zues.model.dto.AddressName;
import com.hml.atp.zues.model.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    Address selectByAddressName(AddressName record);

    int getCount();

    int getFirstId();
}