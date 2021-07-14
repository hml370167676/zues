package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.CaseBase;
import com.hml.atp.zues.dao.CardBinDao;
import com.hml.atp.zues.model.bo.BankCardInfoBO;
import com.hml.atp.zues.model.entity.CardBin;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class BankCardInfoImplTest extends CaseBase {
    @Mock
    CardBinDao cardBinDao;

    @InjectMocks
    BankCardInfoImpl bankCardInfoImpl;


    @DataProvider(name = "cardBin")
    public Object[][] dataProviders() {
        return new Object[][]{{}};
    }

    @Factory(dataProvider = "cardBin")
    public Object[] factoryMethod() {
        return new Object[]{};
    }

    @Test
    public void testGetBankCardInfo() {
        when(cardBinDao.selectByBankCardInfo(any())).thenReturn(Arrays.<CardBin>asList(new CardBin()));

        List<BankCardInfoVO> result = bankCardInfoImpl.getBankCardInfo(new BankCardInfoBO());
        Assert.assertEquals(result, Arrays.<BankCardInfoVO>asList(new BankCardInfoVO()));
    }

}

