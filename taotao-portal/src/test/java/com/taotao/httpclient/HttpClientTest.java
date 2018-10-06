package com.taotao.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-10-2 上午 0:25
 */
@Ignore
public class HttpClientTest {

    @Test
    public void doGet()throws Exception{
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET对象
        HttpGet get = new HttpGet("http://www.sogou.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String str = EntityUtils.toString(entity,"utf-8");
        System.out.println(str);
        //关闭httpclient
        response.close();
        httpClient.close();

    }

    @Test
    public void doGetWithParam()throws Exception {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个uri对象
        URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com");
        uriBuilder.addParameter("query","花千骨");
        HttpGet get = new HttpGet(uriBuilder.build());
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String str = EntityUtils.toString(entity,"utf-8");
        System.out.println(str);
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    @Test
    public void doPost() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String string = EntityUtils.toString(response.getEntity());
        System.out.printf(string);
        response.close();
        httpClient.close();
    }

    @Test
    public void doPostWithParam()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
        //创建一个Entity,模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username","zhangshan"));
        kvList.add(new BasicNameValuePair("password","123"));
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList);
        //设置请求内容
        post.setEntity(entity);
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String string = EntityUtils.toString(response.getEntity());
        System.out.printf(string);
        response.close();
        httpClient.close();
    }

}
