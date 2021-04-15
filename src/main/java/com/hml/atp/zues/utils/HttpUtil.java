package com.hml.atp.zues.utils;

import com.hml.atp.zues.common.ReqMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

import static com.hml.atp.zues.common.ReqMethod.GET;

/**
 * @author hanminglu
 */
@Slf4j
public class HttpUtil {

    private static final CloseableHttpClient HTTP_CLIENT;

    public static final String CHARSET = "UTF-8";

    // 采用静态代码块，初始化超时时间配置，再根据配置生成默认httpClient对象
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8"));
        HTTP_CLIENT = HttpClientBuilder.create().setDefaultRequestConfig(config).setDefaultHeaders(headers).build();
//        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }


    /**
     * @param headers
     * @param url
     * @param reqMethod
     * @param params    请求参数
     * @param ssl
     * @return java.lang.String
     * @Description 功能描述
     * @author hanminglu
     * @date 2021/4/15
     */
    public static <T> String send(List<Header> headers, String url, ReqMethod reqMethod, T params, Boolean ssl) {

        switch (reqMethod.getKey()) {
            case 0:
                return doGet(url, params, ssl);
            case 1:
                return doPost(url, params, ssl);
            default:
                return null;
        }
    }

    /**
     * @param url
     * @param params
     * @param ssl
     * @return java.lang.String
     * @Description 功能描述
     * @author hanminglu
     * @date 2021/4/15
     */
    private static <T> String doGet(String url, T params, Boolean ssl) {
        return null;
    }

    private static <T> String doPost(String url, T params, Boolean ssl) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(GET.getDesc().equals("get".toUpperCase()));
//        Header header =
//        HttpPost httpPost = new HttpPost();
//        httpPost.setHeader();

    }
}
