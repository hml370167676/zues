package com.hml.atp.zues;


import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

@SpringBootTest
@Slf4j
public class CaseBase extends AbstractTestNGSpringContextTests {

    public CaseBase() {
    }

    @BeforeClass
    @Step("测试步骤{0}编号{1}")
    public void initTest(){
        MockitoAnnotations.openMocks(this);
    }
}


