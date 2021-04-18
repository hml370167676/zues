package com.hml.atp.zues.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hanminglu
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ReqParamType {

    BODY(1, "BODY"),
    PATH(2, "PATH"),
    QUERY(3, "QUERY"),
    HEADER(4, "HEADER"),
    FORMDATA(5, "FORMDATA");

    private int key;
    private String desc;

    private static ReqParamType getByKey(int key) {
        for (ReqParamType e : values()) {
            if (e.key == key) {
                return e;
            }

        }
        return null;
    }
}
