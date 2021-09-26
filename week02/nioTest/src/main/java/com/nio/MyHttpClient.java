package com.nio;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class MyHttpClient {
    public static void main(String[] args) {

        String uri = "http://localhost:8801";
        //创建httpClient链接
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get请求
        HttpGet httpGet = new HttpGet(uri);
        //httpClient发送请求
        CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                HttpEntity entity = null;
                //我们要拿到页面的返回数据才算请求成功
                if (response != null && response.getStatusLine().getStatusCode() == 200) {
                    //获取返回的数据
                    entity = response.getEntity();
                    String context = EntityUtils.toString(entity, "utf-8");
                    System.out.println(context);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

        
    }
   

}


