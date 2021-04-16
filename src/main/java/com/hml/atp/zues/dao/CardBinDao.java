package com.hml.atp.zues.dao;


import com.hml.atp.zues.model.bo.BankCardInfoBO;
import com.hml.atp.zues.model.entity.CardBin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hanminglu
 */
@Mapper
public interface CardBinDao {


    int deleteByPrimaryKey(Integer id);

    int insert(CardBin record);

    int insertSelective(CardBin record);

    CardBin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardBin record);

    int updateByPrimaryKey(CardBin record);

    List<CardBin> selectByBankCardInfo(BankCardInfoBO bankCardInfoBO);
}