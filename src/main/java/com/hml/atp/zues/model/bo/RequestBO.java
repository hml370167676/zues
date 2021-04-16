package com.hml.atp.zues.model.bo;

import com.hml.atp.zues.common.ReqMethod;
import com.hml.atp.zues.common.ReqProtocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.entity.FileEntity;

import java.io.Serializable;

/**
 * @author hanminglu
 * @description 请求体
 * @date 2021/4/16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestBO implements Serializable {

    /**
     * 请求协议
     */
    private ReqProtocol reqProtocol;

    /**
     * 请求方法
     */
    private ReqMethod reqMethod;

    /**
     * 基础URL
     */
    private String baseUrl;
    /**
     * 请求接口路径
     */
    private String path;

    /**
     * 请求头JSON
     */
    private String header;

    /**
     * 请求体JSON
     */
    private String requestBody;

    /**
     * 请参数JSON
     */
    private String queryArguments;

    /**
     * 文件体
     */
    private FileEntity fileEntity;

}
