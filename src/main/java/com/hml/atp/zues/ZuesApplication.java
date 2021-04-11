package com.hml.atp.zues;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.hml.atp.zues.dao.mapper")
public class ZuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuesApplication.class, args);
    }

}
