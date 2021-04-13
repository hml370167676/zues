package com.hml.atp.zues.dao;


import com.hml.atp.zues.model.entity.CardBin;
import com.hml.atp.zues.model.ifo.BankCardInfoIFO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardBinDao {


    int deleteByPrimaryKey(Integer id);

    int insert(CardBin record);

    int insertSelective(CardBin record);


    CardBin selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(CardBin record);

    int updateByPrimaryKey(CardBin record);

    List<CardBin> selectByBankCardInfo(BankCardInfoIFO bankCardInfoIFO);
}