package com.hml.atp.zues.controller;

import com.hml.atp.zues.common.RespResult;
import com.hml.atp.zues.common.enums.ResultCode;
import com.hml.atp.zues.model.bo.BankCardInfoBO;
import com.hml.atp.zues.model.bo.IdentityBO;
import com.hml.atp.zues.model.bo.IdentityCardInfoBO;
import com.hml.atp.zues.model.vo.BankCardInfoVO;
import com.hml.atp.zues.service.BankCardInfo;
import com.hml.atp.zues.service.IdentityInfo;
import com.hml.atp.zues.utils.idcard.CardUtil;
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

    @Resource
    public CardUtil cardUtil;

    @ApiOperation(value = "生成身份证号")
    @PostMapping("/getIdentityNO")
    public RespResult<List<String>> getIdentityNO(@RequestBody @Validated IdentityBO identityBO) {
        List<String> result = identityInfo.getIdentityList(identityBO);
        if (result == null || result.isEmpty()) {
            return RespResult.fail(ResultCode.RESULT_NULL_REE.getErrorCode());
        }
        return RespResult.succeed(identityInfo.getIdentityList(identityBO));
    }

    @ApiOperation(value = "生成银行卡号")
    @PostMapping("/getBankCard")
    public RespResult<List<BankCardInfoVO>> getBankCard(@RequestBody @Validated BankCardInfoBO bankCardInfoBO) {
        List<BankCardInfoVO> bankCardInfoVOList = bankCardInfo.getBankCardInfo(bankCardInfoBO);
        if (bankCardInfoVOList == null || bankCardInfoVOList.isEmpty()) {
            return RespResult.fail(ResultCode.RESULT_NULL_REE.getErrorCode());
        }
        return RespResult.succeed(bankCardInfo.getBankCardInfo(bankCardInfoBO));
    }

    @ApiOperation(value = "生成身份证图片")
    @PostMapping("/getCardImage")
    public RespResult<String> getCardImage(@RequestBody @Validated IdentityCardInfoBO identityCardInfoBO) {
        cardUtil.generateIdcodeZ(identityCardInfoBO);
        return RespResult.succeed("");
    }

    @ApiOperation(value = "汽修钱包充值（使用大大币提现接口）")
    @PostMapping("/addBalance")
    public RespResult<List<BankCardInfoVO>> addBalance(@RequestBody @Validated BankCardInfoBO bankCardInfoBO) {
        List<BankCardInfoVO> bankCardInfoVOList = bankCardInfo.getBankCardInfo(bankCardInfoBO);
        if (bankCardInfoVOList == null || bankCardInfoVOList.isEmpty()) {
            return RespResult.fail(ResultCode.RESULT_NULL_REE.getErrorCode());
        }
        return RespResult.succeed(bankCardInfo.getBankCardInfo(bankCardInfoBO));
    }

    /**
     * aPOST https://{{baseurl}}/wealth/garage/ddcoin/exchange
     * Content-Type: application/json
     * token:{{token}}
     *
     * {
     *   "exchangeDdcoin": 1000000
     * }
     */
}
