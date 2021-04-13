package com.hml.atp.zues.dao;

import com.hml.atp.zues.model.dto.AddressName;
import com.hml.atp.zues.model.entity.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanminglu
 */
@Mapper
public interface AddressDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    /**
     *功能描述
     * @author hanminglu
     * @date 2021/4/13
     * @param {record}
     * @return com.hml.atp.zues.model.entity.Address
     */
    Address selectByAddressName(AddressName record);

    /**
     *功能描述
     * @author hanminglu
     * @date 2021/4/13
     * @return int
     */
    int getCount();

    /**
     *功能描述
     * @author hanminglu
     * @date 2021/4/13
     * @return int
     */
    int getFirstId();

}