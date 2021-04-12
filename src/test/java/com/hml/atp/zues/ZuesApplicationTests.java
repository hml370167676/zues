package com.hml.atp.zues;

import com.hml.atp.zues.service.IdentityInfo;
import com.hml.atp.zues.service.IdentityInfoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@SpringBootTest
class ZuesApplicationTests {

    @Resource
    public IdentityInfo identityInfo;

    @Test
    void contextLoads() {
    }

    @Test
    void testRandom(){
//        System.out.println(IdentityInfoImpl.getBirthday(""));
        System.out.println("1" + 1 +2);
    }


}
