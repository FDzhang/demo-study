package com.example.demohttp.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/28 15:44
 */

public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 发送 get 请求
     *
     * @param url 请求地址
     * @return 请求的返回内容
     */
    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {

            // 获取响应体
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("get请求异常");
        }

        return null;
    }

    /**
     * 发送带参数的 get 请求
     *
     * @param url    请求地址
     * @param params 请求的参数
     * @return 请求的返回内容
     */
    public static String get(String url, Map<String, String> params) {
        // 获取带参数的uri
        URI uri = getUri(url, params);

        HttpGet httpGet = new HttpGet(uri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {

            // 获取响应体
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("带参数的get请求异常");
        }

        return null;
    }

    private static URI getUri(String url, Map<String, String> params) {
        List<NameValuePair> sendParams = new ArrayList<>();

        for (Map.Entry<String, String> param : params.entrySet()) {
            sendParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }

        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(sendParams);

            return uriBuilder.build();
        } catch (URISyntaxException e) {
            logger.error("URI解析异常");
        }

        return null;
    }

    /**
     * 发送 post 请求
     *
     * @param url 请求地址
     * @return 请求的返回内容
     */
    public static String post(String url) {
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        setHeader(httpPost);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {

            // 获取响应体
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("post请求异常");
        }

        return null;
    }

    /**
     * 发送带参数的 post 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求的返回内容
     */
    public static String post(String url, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        setHeader(httpPost);

        // 设置请求参数
        setEntity(params, httpPost);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {

            // 获取响应体
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("带参数的post请求异常");
        }

        return null;
    }

    private static void setHeader(HttpPost httpPost) {
    }

    /**
     * 设置 post 的请求参数
     *
     * @param params   请求地址
     * @param httpPost post请求对象
     */
    private static void setEntity(Map<String, String> params, HttpPost httpPost) {
        //把表单包装成一个HttpEntity对象
        HttpEntity entity = getHttpEntity(params);
        //设置请求的内容
        httpPost.setEntity(entity);
    }

    /**
     * 封装 post 的请求参数
     *
     * @param params 请求参数map
     * @return 请求参数的HttpEntity
     */
    private static HttpEntity getHttpEntity(Map<String, String> params) {

        List<NameValuePair> sendParams = new ArrayList<>();

        for (Map.Entry<String, String> param : params.entrySet()) {
            sendParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }

        try {
            //把表单包装成一个HttpEntity对象
            return new UrlEncodedFormEntity(sendParams, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("生成 HttpEntity 异常");
        }

        return null;
    }

}
