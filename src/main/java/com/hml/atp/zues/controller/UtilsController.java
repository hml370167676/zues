package com.hml.atp.zues.controller;

import com.hml.atp.zues.model.ifo.BankCardInfoIFO;
import com.hml.atp.zues.model.ifo.IdentityIFO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import com.hml.atp.zues.model.vo.IdentityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "常用工具")
@RestController("/utils")
public class UtilsController {

    @ApiOperation(value = "生成身份证号")
    @PostMapping("/getIdentityNO")
    public IdentityVO GetIdentityNO(@RequestBody @Validated IdentityIFO identityIFO) {
        //TODO 生成身份证号
        IdentityVO ID = new IdentityVO();
        return ID;
    }

    @ApiOperation(value = "生成银行卡号")
    @PostMapping("/getBankCard")
    public ResponseEntity<BankCardInfoVO> GetBankCard(@RequestBody @Validated BankCardInfoIFO bankCardInfoIFO) {
        //TODO 生成银行卡号
        BankCardInfoVO bankCardInfo = new BankCardInfoVO();
        return ResponseEntity.ok(bankCardInfo);
    }
}
