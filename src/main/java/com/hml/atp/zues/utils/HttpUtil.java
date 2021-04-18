package com.hml.atp.zues.utils;

import com.alibaba.fastjson.JSON;
import com.hml.atp.zues.model.bo.RequestBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.text.MessageFormat.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanminglu
 */
@Slf4j
public class HttpUtil {

    private static final CloseableHttpClient HTTP_CLIENT;

    public static final String CHARSET = "UTF-8";

    // 采用静态代码块,初始化超时时间配置,再根据配置生成默认httpClient对象
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8"));
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        HTTP_CLIENT = HttpClients.custom().setDefaultRequestConfig(config).setDefaultHeaders(headers)
                .setConnectionManager(cm).build();

    }


    /**
     * @param requestBO 请求信息
     * @return java.lang.String
     * @Description 功能描述
     * @author hanminglu
     * @date 2021/4/15
     */
    public static <T> String send(RequestBO requestBO) throws URISyntaxException, IOException {
        //重组URL 将protocol、baseURL、path、query组装起来 为URI
        if (requestBO.getQueryParameter() == null || requestBO.getQueryParameter().isEmpty()) {
            URI uri = new URIBuilder()
                    .setScheme(null == requestBO.getReqProtocol() ? "http" : requestBO.getReqProtocol().getDesc())
                    .setHost(requestBO.getBaseUrl())
                    .setPath(requestBO.getPath())
                    .build();
        }
        URI uri = new URIBuilder()
                .setScheme(null == requestBO.getReqProtocol() ? "http" : requestBO.getReqProtocol().getDesc())
                .setHost(requestBO.getBaseUrl())
                .setPath(requestBO.getPath())
                .setParameters(queryParamsConverter(requestBO.getQueryParameter()))
                .build();
        switch (requestBO.getReqMethod().getKey()) {
            case 0:
                return doGet(requestBO, uri);
            case 1:
                return doPost(requestBO, uri);
            case 2:
                return doHead(requestBO, uri);
            case 3:
                return doPut(requestBO, uri);
            case 4:
                return doDelete(requestBO, uri);
            case 5:
                return doTrace(requestBO, uri);
            case 6:
                return doPatch(requestBO, uri);
            case 7:
                return doOptions(requestBO, uri);
            default:
                throw new IllegalStateException("Unexpected value: " + requestBO.getReqMethod().getKey());
        }
    }

    /**
     * @param requestBO 请求信息
     * @return java.lang.String
     * @Description 功能描述
     * @author hanminglu
     * @date 2021/4/15
     */
    private static String doGet(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpGet();
        request.setHeaders(headerConverter("asd"));

        return null;
    }

    private static String doPost(RequestBO requestBO, URI uri) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (requestBO.getHeader() != null || !requestBO.getHeader().isEmpty()) {
            httpPost.setHeaders(headerConverter(requestBO.getHeader()));
        }
        if (requestBO.getBodyParameter() != null || !requestBO.getBodyParameter().isEmpty()) {
            StringEntity reqEntity = new StringEntity(requestBO.getBodyParameter(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(reqEntity);
        }
//        if (requestBO.getFileName() != null || !requestBO.getFileName().isEmpty()) {
//            //TODO 处理file和form表单类型的数据
//            File file = new File(requestBO.getFileName());
//            FileEntity fileEntity = new FileEntity(file, ContentType.MULTIPART_FORM_DATA);
//        }

//        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost)) {
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    long len = entity.getContentLength();
                    if (len != -1 && len < 2048) {
                        System.out.println(EntityUtils.toString(entity));
                        return EntityUtils.toString(entity);
                    } else {
                        // Stream content out
                    }
                }
            } finally {
                response.close();
                HTTP_CLIENT.close();
            }
        }


        return null;
    }


    private static String doHead(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpHead();
        request.setHeaders(headerConverter("asd"));
        return null;
    }


    private static String doPut(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpPut();
        request.setHeaders(headerConverter("asd"));
        return null;
    }


    private static String doDelete(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpDelete();
        request.setHeaders(headerConverter("asd"));
        return null;
    }


    private static String doTrace(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpTrace();
        request.setHeaders(headerConverter("asd"));
        return null;
    }


    private static String doPatch(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpPatch();
        request.setHeaders(headerConverter("asd"));
        return null;
    }


    private static String doOptions(RequestBO requestBO, URI uri) {
        HttpRequestBase request = new HttpOptions();
        request.setHeaders(headerConverter("asd"));
        return null;
    }

    /**
     * @param json json字符串
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
        return headers.toArray(new Header[0]);
    }

    public static List<NameValuePair> queryParamsConverter(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        List<NameValuePair> parameters = new ArrayList<>();
        Map<String, String> kv = JSON.parseObject(json, HashMap.class);
        for (String key : kv.keySet()) {
            parameters.add(new BasicNameValuePair(key, String.valueOf(kv.get(key))));
        }
        return parameters;
    }

    private static void checkRequest(RequestBO requestBO, URI uri) {

    }

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("ContentType", "application/json");
//        String json = JSON.toJSONString(map);
//        JSON.toJSON(map);
//        System.out.println(json);
//        System.out.println(queryParamsConverter(json));
//        System.out.println(JSON.parseArray(test, NameValuePair.class));
//        HttpClients.custom().setDefaultHeaders().setDefaultRequestConfig();
//        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
//                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);


//        HttpPost httpPost = new HttpPost();
//        httpPost.setHeader();
        Map data = new HashMap<>();
        data.put("id","123123");
        System.out.println(StringFormatUtil.format("garage/user/view/filter/{id}",data));



    }
}
