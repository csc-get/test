package com.fh.shop.pertal.util;

import com.fh.shop.pertal.common.SystemConst;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月11日 14:07
 */
public class SendHttpClientUtil {

    /**
     * 测试删除
     */
    public static void main(String[] args) {
        String memberdelete = SendHttpClientUtil.memberdelete("192.168.0.124:8088/goods", 64, null);
        System.out.println(memberdelete);
    }



    /**
     * 删除httpclient
     */
    public static  String  memberdelete(String url,Integer id,Map<String,String> hraders){
        /*获取url*/
        url = "/"+id+".jhtml";
        System.err.println(url);
        /*创建请求*/
        CloseableHttpClient client = HttpClientBuilder.create().build();
        /*delete请求*/
        HttpDelete httpDelete = new HttpDelete(url);
        /*判断头信息至是为空*/
        String result =" ";
        if (hraders !=null && !hraders.isEmpty()){
            /*循环map集合*/
            Iterator<Map.Entry<String, String>> iterator = hraders.entrySet().iterator();
            while (iterator.hasNext()){
                    //取key,value
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpDelete.addHeader(key,value);
            }
            CloseableHttpResponse execute=null;
            /*发送请求*/
            try {
                execute  = client.execute(httpDelete);
                result  = EntityUtils.toString(execute.getEntity(), "utf-8");
                System.err.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (execute !=null){
                    try {
                        execute.close();
                        execute=null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpDelete !=null){
                    httpDelete.releaseConnection();
                }
                if (client !=null){
                    try {
                        client.close();
                        client=null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return result;
    }





    /**
     * smsGet请求
     */
    public static String  smsGet(String url,Map<String,String> params,Map<String,String> hraders) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        /*传参*/
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                list.add(new BasicNameValuePair(key, value));
            }
            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list);
                String result = EntityUtils.toString(urlEncodedFormEntity, "utf-8");
                url += "?" + result;
                System.err.println(url);
                System.err.println(result);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*Get请求*/
        HttpGet get = new HttpGet(url);
        if (hraders != null && !hraders.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = hraders.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                get.addHeader(key, value);
            }
        }
        CloseableHttpResponse response = null;
        String result = "";
        /*执行*/
        try {
            response = client.execute(get);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (get != null) {
                get.releaseConnection();
            }
            if (client != null) {
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

    /**
     * get请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String sendGet(String url, Map<String,String> params, Map<String, String> headers){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;

        if(null!=params && params.size()>0) {
            //设置请求体
            List<NameValuePair> pairs = new ArrayList();
            Iterator<Map.Entry<String, String>> headersIterator = params.entrySet().iterator();
            while (headersIterator.hasNext()) {
                Map.Entry<String, String> map = headersIterator.next();
                String key = map.getKey();
                String value = map.getValue();
                pairs.add(new BasicNameValuePair(key, value));
            }

            try {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, "utf-8"));

                httpGet = new HttpGet(url+"?"+str);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null!=headers && headers.size()>0) {
            //设置请求头
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                String key = map.getKey();
                String value = map.getValue();
                httpGet.addHeader(key, value);
            }
        }

        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != response) {
                    response.close();
                    response=null;
                }
                if(null != httpGet) {
                    httpGet.releaseConnection();
                }
                if(null != httpClient) {
                    httpClient.close();
                    httpClient=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * post请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String sendPost(String url, Map<String,String> params, Map<String, String> headers){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        if(null!=headers && headers.size()>0) {
            //设置请求头
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                String key = map.getKey();
                String value = map.getValue();
                httpPost.addHeader(key, value);
            }
        }

        if(null!=params && params.size()>0) {
            //设置请求体
            List<NameValuePair> pairs = new ArrayList();
            Iterator<Map.Entry<String, String>> headersIterator = params.entrySet().iterator();
            while (headersIterator.hasNext()) {
                Map.Entry<String, String> map = headersIterator.next();
                String key = map.getKey();
                String value = map.getValue();
                pairs.add(new BasicNameValuePair(key, value));
            }
            UrlEncodedFormEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(pairs, "utf-8");
                httpPost.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != response) {
                    response.close();
                    response=null;
                }
                if(null != httpPost) {
                    httpPost.releaseConnection();
                }
                if(null != httpClient) {
                    httpClient.close();
                    httpClient=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
