package com.hml.atp.zues.controller;

import com.hml.atp.zues.common.RespResult;
import com.hml.atp.zues.model.bo.BankCardInfoBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanminglu
 * @description 测试数据管理
 * @date 2021/4/22
 */
@Api(tags = "测试数据")
@RestController("/data")
public class TestDataController {

    @ApiOperation(value = "创建询价单（根据模板创建）")
    @PostMapping("/createInquiry")
    public RespResult<List<String>> createInquiry(@RequestBody @Validated BankCardInfoBO bankCardInfoBO) {
        List<String> result = new ArrayList<>();
        return RespResult.succeed(result);
    }
}
