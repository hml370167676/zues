package com.hml.atp.zues.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回状态和相关信息的实体类，状态码{@value #SUCCESS_CODE}表示成功，失败状态码请在各模块的service module中定义
 *
 * @param <T> 包装的类型，必须可序列化
 * @author bailu
 * @since 2015年8月10日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "0000";

    public static final String SUCCESS_MSG = "成功";

    /**
     * 状态码
     */
    private String statusCode;

    /**
     * 错误相关信息
     */
    private String msg;

    /**
     * 返回结果对象
     */
    private T model;



    /**
     * 创建成功返回值
     *
     * @param object 需要包装的结果
     * @param <T>    包装的结果类型
     * @return RespResult结果对象
     */
    public static <T> RespResult<T> succeed(T object) {
        return new RespResult<>(SUCCESS_CODE, SUCCESS_MSG, object);
    }

    /**
     * 创建失败返回值
     *
     * @param errCode 此处为错误码(不能为{@value #SUCCESS_CODE})
     * @return RespResult结果对象
     */
    public static <T> RespResult<T> fail(String errCode) {
        return new RespResult<T>(errCode, ResultCode.getMsgByErrorCode(errCode), null);
    }

    /**
     * 创建失败返回值
     *
     * @param errCode 此处为错误码(不能为{@value #SUCCESS_CODE})
     * @param object  需要包装的结果
     * @return RespResult结果对象
     */
    public static <T> RespResult<T> fail(String errCode, T object) {
        return new RespResult<T>(errCode, ResultCode.getMsgByErrorCode(errCode), object);
    }

    /**
     * 创建失败返回值
     *
     * @param errCode 此处为错误码(不能为{@value #SUCCESS_CODE})
     * @param msg     异常信息
     * @param object  需要包装的结果
     * @return RespResult结果对象
     */
    public static <T> RespResult<T> fail(String errCode, String msg, T object) {
        return new RespResult<>(errCode, msg, object);
    }

    /**
     * 结果是否成功，根据状态码是否等于{@value #SUCCESS_CODE}来判断
     *
     * @return 是否成功布尔值
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(statusCode);
    }

    /**
     * 结果是否成功，根据状态码是否等于{@value #SUCCESS_CODE}且model不为空来判断
     *
     * @return 是否成功布尔值
     */
    public boolean isSuccessful() {
        return isSuccess() && model != null;
    }


}