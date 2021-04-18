package com.hml.atp.zues.common.enums;

/**
 * @author hanminglu
 * @description
 * @date 2021/4/12
 */
public enum ResultEnum {

    RETURN_SUCCESS("0000", "成功"),
    RETURN_FAIl("9999", "失败");

    private String resultCode;
    private String resultMsg;

    private ResultEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

}
