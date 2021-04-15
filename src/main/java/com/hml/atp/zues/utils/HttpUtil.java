package com.hml.atp.zues.utils;

import com.alibaba.fastjson.JSON;
import com.hml.atp.zues.common.ReqMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static <T> String send(String headers, String url, ReqMethod reqMethod, T params, Boolean ssl) {

        switch (reqMethod.getKey()) {
            case 0:
                return doGet(headers, url, params, ssl);
            case 1:
                return doPost(headers, url, params, ssl);
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
    private static <T> String doGet(String headers, String url, T params, Boolean ssl) {
        HttpRequestBase request = new HttpGet();
        request.setHeaders(headerConverter(headers));

        return null;
    }

    private static <T> String doPost(String headers, String url, T params, Boolean ssl) {
        HttpRequestBase request = new HttpPost();
        request.setHeaders(headerConverter(headers));
        return null;
    }

    /**
     *
     * @param json  json字符串
     * @return Header[]
     * @Description 将入参中的json转换为 Header[] 以便更新请求中的Header信息
     * @author hanminglu
     * @date 2021/4/15
     */
    public static Header[] headerConverter(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        List<Header> headers = new ArrayList<>();
        Map<String, String> kv = JSON.parseObject(json, HashMap.class);
        for (String key : kv.keySet()) {
            headers.add(new BasicHeader(key, kv.get(key)));
        }
        return headers.toArray(new Header[headers.size()]);
    }

    public static void main(String[] args) {
        System.out.println(GET.getDesc().equals("get".toUpperCase()));
        Map<String,String> map = new HashMap<>();
        map.put("ContentType", "application/json");
        String json = JSON.toJSONString(map);


//        HttpPost httpPost = new HttpPost();
//        httpPost.setHeader();

    }
}
