package com.hml.atp.zues;

import com.hml.atp.zues.common.enums.ReqMethod;
import com.hml.atp.zues.common.enums.ReqProtocol;
import com.hml.atp.zues.model.bo.RequestBO;
import com.hml.atp.zues.utils.HttpUtil;
import com.hml.atp.zues.utils.idcard.CanvasViewUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ZuesApplicationTests {

    @Resource
    private CanvasViewUtils testddd;

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

    @Test
    void testPrint() {
        Map info = new HashMap();
        info.put("pPolice", "0");
        info.put("pEffDate", "2010.10.11");
        info.put("pExpDate", "2030.02.15");
        try {
            testddd.loadIDCardBacked(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
