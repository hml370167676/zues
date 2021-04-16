package com.hml.atp.zues;

import com.hml.atp.zues.common.ReqMethod;
import com.hml.atp.zues.common.ReqProtocol;
import com.hml.atp.zues.model.bo.RequestBO;
import com.hml.atp.zues.service.IdentityInfo;
import com.hml.atp.zues.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
class ZuesApplicationTests {

    @Resource
    public IdentityInfo identityInfo;

    @Test
    void contextLoads() {
    }

    @Test
    void testRandom() {
//        System.out.println(IdentityInfoImpl.getBirthday(""));
        System.out.println("1" + 1 + 2);
    }

    @Test
    void testHttpUtil() throws URISyntaxException, IOException {
        HttpUtil httpUtil = new HttpUtil();
        RequestBO request = RequestBO.builder()
                .baseUrl("test1-api.dada365.com")
                .path("garage/user/view/filter")
                .header("{'Content-Type':'application/json','token':'e585a0f41225350390abe19510ee4882bc3c5ba1'}")
                .reqMethod(ReqMethod.POST)
                .reqProtocol(ReqProtocol.HTTP)
                .requestBody("{'companyId':'12891'}")
                .queryArguments("{'pageNum':1,'pageSize':10}")
                .build();
        httpUtil.send(request);


    }


}
