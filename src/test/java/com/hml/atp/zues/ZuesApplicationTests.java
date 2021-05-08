package com.hml.atp.zues;

import com.hml.atp.zues.common.enums.ReqMethod;
import com.hml.atp.zues.common.enums.ReqProtocol;
import com.hml.atp.zues.model.bo.RequestBO;
import com.hml.atp.zues.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
class ZuesApplicationTests {


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


    }
    @Test
    void testHttpUtilDoUpload() throws URISyntaxException, IOException {

    }
}
