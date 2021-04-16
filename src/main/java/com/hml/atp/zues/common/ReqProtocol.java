package com.hml.atp.zues.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hanminglu
 */

@Getter
@AllArgsConstructor
public enum ReqProtocol {

    HTTP(0, "HTTP"),
    HTTPS(1, "HTTPS"),
    DUBBO(2, "DUBBO"),
    WEBSOCKET(3, "WEBSOCKET");

    private int key;

    private String desc;

    private static ReqProtocol getByKey(Integer key) {
        for (ReqProtocol e : values()) {
            if (e.getKey() == key) {
                return e;
            }
        }
        return null;
    }

}
