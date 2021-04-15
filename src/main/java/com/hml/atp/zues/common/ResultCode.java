package com.hml.atp.zues.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hanminglu
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode {
    RESULT_NULL_REE("1111", "结果为空");


    private String errorCode;
    private String errorMsg;

    public static ResultCode getByErrorCode(String errorCode) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getErrorCode().equals(errorCode)) {
                return resultCode;
            }
        }
        return null;
    }

    public static String getMsgByErrorCode(String errorCode) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getErrorCode().equals(errorCode)) {
                return resultCode.errorMsg;
            }
        }
        return null;
    }

    public static Boolean containsErrorCode(String errorCode) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getErrorCode().equals(errorCode)) {
                return true;
            }
        }
        return false;
    }

}
