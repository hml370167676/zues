package com.hml.atp.zues;

import com.hml.atp.zues.common.enums.ReqMethod;
import com.hml.atp.zues.common.enums.ReqProtocol;
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
    void testHttpUtilDoPost() throws URISyntaxException, IOException {
        HttpUtil httpUtil = new HttpUtil();
        RequestBO request = RequestBO.builder()
                .baseUrl("test1-api.dada365.com")
                .path("garage/user/view/filter")
                .header("{'Content-Type':'application/json;charset=utf-8','token':'e585a0f41225350390abe19510ee4882bc3c5ba1'}")
                .reqMethod(ReqMethod.POST)
                .reqProtocol(ReqProtocol.HTTP)
                .bodyParameter("{}")
                .queryParameter("{'pageNum':1,'pageSize':10,'companyId':12891}")
                .build();
        httpUtil.send(request);


    }
    @Test
    void testHttpUtilDoUpload() throws URISyntaxException, IOException {
        HttpUtil httpUtil = new HttpUtil();
        RequestBO request = RequestBO.builder()
                .baseUrl("test1-api.dada365.com")
                .path("garage/dfs/imgs/upload")
                .header("{'Content-Type':'multipart/form-data','token':'e585a0f41225350390abe19510ee4882bc3c5ba1'}")
                .reqMethod(ReqMethod.POST)
                .reqProtocol(ReqProtocol.HTTP)
                .bodyParameter("{}")
                .queryParameter("{}")
                .filesName("{'img':'d:/20210417012401.png'}")
                .build();
        httpUtil.send(request);


    }


}
