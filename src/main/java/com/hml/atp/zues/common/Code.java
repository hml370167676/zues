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
public enum Code {
    RESULT_NULL_REE("1111", "结果为空");


    private String errorCode;
    private String errorMsg;

    public static Code getByErrorCode(String errorCode) {
        for (Code code : values()) {
            if (code.getErrorCode().equals(errorCode)) {
                return code;
            }
        }
        return null;
    }

    public static String getMsgByErrorCode(String errorCode) {
        for (Code code : values()) {
            if (code.getErrorCode().equals(errorCode)) {
                return code.errorMsg;
            }
        }
        return null;
    }

    public static Boolean containsErrorCode(String errorCode) {
        for (Code code : values()) {
            if (code.getErrorCode().equals(errorCode)) {
                return true;
            }
        }
        return false;
    }

}
