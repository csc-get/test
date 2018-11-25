package com.fh.shop.pertal.util;

import com.fh.shop.pertal.common.ServerResponse;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.xml.ws.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpClientUtil {

    public  static  ServerResponse  httpGet(String url, Map<String,String> params, Map<String,String> heads){
        CloseableHttpClient build = HttpClientBuilder.create().build();
        if (null != params && !params.isEmpty()){
            List<NameValuePair> nameValuePairs=new ArrayList<>();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

            iteratorWhile(nameValuePairs, iterator);

            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs,"utf-8");
                String toString = EntityUtils.toString(urlEncodedFormEntity,"utf-8");
                url+="?"+toString;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HttpGet httpGet = new HttpGet(url);
        //头信息
        buildHeads(heads, httpGet);
        CloseableHttpResponse response=null;
        ServerResponse serverResponse=null;
        try {
            response = build.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            Gson gson=new Gson();
            serverResponse = gson.fromJson(result, ServerResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != response) {
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpGet) {
                httpGet.releaseConnection();
                httpGet = null;
            }
            if (null != build) {
                try {
                    build.close();
                    build = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  serverResponse;
    }

    public  static ServerResponse httpPostUserName(String url, Map<String,String> params, Map<String,String> heads){
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        if (null != params && !params.isEmpty()){
            List<NameValuePair> nameValuePairs=new ArrayList<>();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

            iteratorWhile(nameValuePairs, iterator);
            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs,"utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //头信息
        buildHeads(heads, httpPost);
        CloseableHttpResponse response=null;
        ServerResponse serverResponse=null;
        try {
            response = build.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            Gson gson=new Gson();
            serverResponse = gson.fromJson(result, ServerResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != response) {
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpPost) {
                httpPost.releaseConnection();
                httpPost = null;
            }
            if (null != build) {
                try {
                    build.close();
                    build = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return serverResponse;
    }

    private static void iteratorWhile(List<NameValuePair> nameValuePairs, Iterator<Map.Entry<String, String>> iterator) {
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            nameValuePairs.add(new BasicNameValuePair(key,value));
        }
    }

    private static void buildHeads(Map<String, String> heads, HttpUriRequest request ) {
        if (null!= heads && !heads.isEmpty()){
            Iterator<Map.Entry<String, String>> iterator = heads.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                request.addHeader(next.getKey(),next.getValue());
            }
        }
    }

    public static void main(String[] args) {
      /*  Map<String,String> map=new HashMap<>();
        map.put("brandName","bdrfbdf");
        map.put("id","14");
        map.put("logo","bdrtsdsvdgfs");
        map.put("brandDescribe","agbdfvsfgbfdvd");
        httpPut("http://192.168.0.139:8080/brands.jhtml",map,null);*/
        sendDelete("http://192.168.0.124:8088/brands",65,null);
    }


    //delete动作
    public static String sendDelete(String url,Integer id,Map<String,String> headers){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        url += "/"+id+".jhtml";
        System.out.println(url);
        HttpDelete httpDelete = new HttpDelete(url);
        if(headers != null && !headers.isEmpty()){
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                httpDelete.addHeader(next.getKey(),next.getValue());
            }
        }
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != response){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != httpDelete){
                httpDelete.releaseConnection();
            }
            if(null != client){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }

    //put动作
    public static String httpPut(String url,Map<String,String> params,Map<String,String> headers){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut(url);
        Gson gson = new Gson();
        String paramJson = gson.toJson(params);
        StringEntity stringEntity = new StringEntity(paramJson, "utf-8");
        stringEntity.setContentType("application/json");
        httpPut.setEntity(stringEntity);
        if(null != headers && !headers.isEmpty()){
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                httpPut.addHeader(next.getKey(),next.getValue());
            }
        }
        CloseableHttpResponse response=null;
        String result = "";
        try {
            response = client.execute(httpPut);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != httpPut){
                httpPut.releaseConnection();
            }
            if(null != client){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

}
