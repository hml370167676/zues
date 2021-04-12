package com.hml.atp.zues.controller;

import com.hml.atp.zues.common.RespResult;
import com.hml.atp.zues.model.ifo.BankCardInfoIFO;
import com.hml.atp.zues.model.ifo.IdentityIFO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import com.hml.atp.zues.service.IdentityInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "常用工具")
@RestController("/utils")
public class UtilsController {

    @Autowired
    public IdentityInfo identityInfo;

    @ApiOperation(value = "生成身份证号")
    @PostMapping("/getIdentityNO")
    public RespResult<List<String>> GetIdentityNO(@RequestBody @Validated IdentityIFO identityIFO) {
        return RespResult.succeed(identityInfo.getIdentityList(identityIFO));
    }

    @ApiOperation(value = "生成银行卡号")
    @PostMapping("/getBankCard")
    public ResponseEntity<BankCardInfoVO> GetBankCard(@RequestBody @Validated BankCardInfoIFO bankCardInfoIFO) {
        //TODO 生成银行卡号
        BankCardInfoVO bankCardInfo = new BankCardInfoVO();
        return ResponseEntity.ok(bankCardInfo);
    }
}
