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
        RequestBO request = RequestBO.builder()
                .baseUrl("test1-api.dada365.com")
                .path("garage/purchase/batch/all/page/v2")
                .header("{\"Content-Type\":\"application/json;charset=utf-8\",\"token\":\"e585a0f41225350390abe19510ee4882bc3c5ba1\"}")
                .reqMethod(ReqMethod.POST)
                .reqProtocol(ReqProtocol.HTTP)
                .bodyParameter("{\"status\":4}")
                .queryParameter("{\"pageNum\":1,\"pageSize\":100}")
                .build();
        HttpUtil.send(request);


    }

    @Test
    void testHttpUtilDoUpload() throws URISyntaxException, IOException {
        RequestBO request = RequestBO.builder()
                .baseUrl("test1-api.dada365.com")
                .path("garage/dfs/imgs/upload")
                .header("{\"Content-Type\":\"multipart/form-data\",\"token\":\"e585a0f41225350390abe19510ee4882bc3c5ba1\"}")
                .reqMethod(ReqMethod.POST)
                .reqProtocol(ReqProtocol.HTTP)
                .bodyParameter("{}")
                .queryParameter("{}")
                .filesName("{\"img\":\"d:/20210417012401.png\"}")
                .build();
        HttpUtil.send(request);

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
