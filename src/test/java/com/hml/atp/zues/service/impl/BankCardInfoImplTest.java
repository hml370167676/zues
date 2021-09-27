package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.CaseBase;
import com.hml.atp.zues.dao.CardBinDao;
import com.hml.atp.zues.model.bo.BankCardInfoBO;
import com.hml.atp.zues.model.entity.CardBin;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Epic("手動閥手動閥")
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
    @Story("士大夫但是")
    public void testGetBankCardInfo() {
        when(cardBinDao.selectByBankCardInfo(any())).thenReturn(Arrays.<CardBin>asList(new CardBin()));

        List<BankCardInfoVO> result = bankCardInfoImpl.getBankCardInfo(new BankCardInfoBO());
        Assert.assertEquals(result, Arrays.<BankCardInfoVO>asList(new BankCardInfoVO()));
    }

    @Test
    @Story("生成卡号测试")
    public void generateCardNoTest() throws InvocationTargetException, IllegalAccessException {
        Method generateCardNo = PowerMockito.method(BankCardInfoImpl.class, "generateCardNo", String.class, Integer.class);
        String cardNo = String.valueOf(generateCardNo.invoke(bankCardInfoImpl, "123", 10));
        Assert.assertEquals(10,cardNo.length());
//        verify(cardNo).matches("123");
        Assert.assertTrue(cardNo.startsWith("123"));

    }

}

