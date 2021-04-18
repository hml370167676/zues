package com.hml.atp.zues.model.bo;

import com.hml.atp.zues.common.enums.ReqMethod;
import com.hml.atp.zues.common.enums.ReqProtocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 请求参数JSON--parameterType 为path
     */
    private String pathParameter;

    /**
     * 请求参数JSON--parameterType 为query
     */
    private String queryParameter;

    /**
     * 请求体JSON--parameterType 为body
     */
    private String bodyParameter;

    /**
     * 请求参数JSON--parameterType 为formdata--文件请求
     */
    private String filesName;

    /**
     * 请求参数JSON--parameterType 为formdata--表单请求
     */
    private String formParams;

}
