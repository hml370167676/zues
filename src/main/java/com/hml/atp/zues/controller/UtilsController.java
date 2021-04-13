package com.hml.atp.zues.controller;

import com.hml.atp.zues.common.RespResult;
import com.hml.atp.zues.model.ifo.BankCardInfoIFO;
import com.hml.atp.zues.model.ifo.IdentityIFO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import com.hml.atp.zues.service.BankCardInfo;
import com.hml.atp.zues.service.IdentityInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanminglu
 */
@Api(tags = "常用工具")
@RestController("/utils")
public class UtilsController {

    @Resource
    public IdentityInfo identityInfo;

    @Resource
    public BankCardInfo bankCardInfo;

    @ApiOperation(value = "生成身份证号")
    @PostMapping("/getIdentityNO")
    public RespResult<List<String>> getIdentityNO(@RequestBody @Validated IdentityIFO identityIFO) {
        return RespResult.succeed(identityInfo.getIdentityList(identityIFO));
    }

    @ApiOperation(value = "生成银行卡号")
    @PostMapping("/getBankCard")
    public RespResult<List<BankCardInfoVO>> getBankCard(@RequestBody @Validated BankCardInfoIFO bankCardInfoIFO) {
        List<BankCardInfoVO> bankCardInfoVOList = bankCardInfo.getBankCardInfo(bankCardInfoIFO);
        if (bankCardInfoVOList.isEmpty() || bankCardInfoVOList == null) {
            return RespResult.fail("1111","结果异常！",bankCardInfoVOList);
        }
        return RespResult.succeed(bankCardInfo.getBankCardInfo(bankCardInfoIFO));
    }
}
