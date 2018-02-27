package com.wj.rxhttp.http;

import okhttp3.OkHttpClient;

/**
 * Created by wenjin on 2017/7/3.
 */
public class HttpClient {
    private static HttpClient instance;
    private OkHttpClient.Builder mBuilder;

    private HttpClient(){
        mBuilder=new OkHttpClient.Builder();
    }

    public static HttpClient getInstance(){
        if(instance==null){
            synchronized (HttpClient.class){
                if(instance==null){
                    instance=new HttpClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder(){
        return mBuilder;
    }
}
