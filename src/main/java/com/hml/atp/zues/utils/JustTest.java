package com.hml.atp.zues.utils;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

/**
 * @author hanminglu
 * @description
 * @date 2021/4/19
 */
public class JustTest {
    public static void main(String[] args) {
        Swagger swagger = new SwaggerParser().read("http://192.168.4.70:2222/garage/v2/api-docs");
//        SwaggerParseResult result = new OpenAPIParser().readLocation("http://192.168.4.70:2222/garage/v2/api-docs", null, null);
//        System.out.println(result);
//        System.out.println(swagger.getPaths().keySet());
        System.out.println(swagger.getBasePath());
        System.out.println(swagger.getDefinitions());
    }
}
