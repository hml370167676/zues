package com.hml.atp.zues;


import lombok.extern.slf4j.Slf4j;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeClass;

@SpringBootTest
@Slf4j
public class CaseBase extends AbstractTestNGSpringContextTests {

    public CaseBase() {
    }

    @BeforeClass
    public void initTest(){
        MockitoAnnotations.openMocks(this);
    }
}


