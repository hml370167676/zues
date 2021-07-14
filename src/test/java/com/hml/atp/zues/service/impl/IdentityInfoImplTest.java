package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.CaseBase;
import com.hml.atp.zues.dao.AddressDao;
import com.hml.atp.zues.model.bo.IdentityBO;
import com.hml.atp.zues.model.entity.Address;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class IdentityInfoImplTest extends CaseBase {
    @Mock
    AddressDao addressDao;
    @InjectMocks
    IdentityInfoImpl identityInfoImpl;


    @Test
    public void testGetIdentityList() {
        when(addressDao.selectByPrimaryKey(anyInt())).thenReturn(new Address());
        when(addressDao.selectByAddressName(any())).thenReturn(new Address());
        when(addressDao.getCount()).thenReturn(0);
        when(addressDao.getFirstId()).thenReturn(0);

        List<String> result = identityInfoImpl.getIdentityList(new IdentityBO());
        Assert.assertEquals(result, Arrays.<String>asList("String"));
    }

}
