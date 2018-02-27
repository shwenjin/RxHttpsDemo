package com.wj.rxhttp.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wenjin on 2017/7/3.
 */

public class HeaderInterceptor implements Interceptor{
    private Map<String,Object> headerMaps=new TreeMap<>();

    public HeaderInterceptor(Map<String,Object> headerMaps){
        this.headerMaps=headerMaps;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request=chain.request().newBuilder();
        if(headerMaps!=null&&headerMaps.size()>0){
            for (Map.Entry<String,Object> entry:headerMaps.entrySet()){
                request.addHeader(entry.getKey(),(String) entry.getValue());
            }
        }
        return chain.proceed(request.build());
    }
}
