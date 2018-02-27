package com.wj.rxhttp.interceptor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.wj.rxhttp.base.BaseRxHttpApplication;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wenjin on 2017/7/3.
 * 网络缓存
 */

public class CacheInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        if(isNetworkConnected()){
            //没有网络，启用缓存
            request=request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse=chain.proceed(request);
        if(isNetworkConnected()){
            //有网的时候读接口上的@Headers里的配置
            String cacheControl=request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control",cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }else{
            return originalResponse.newBuilder()
                    .header("Cache-Control","public, only-if-cached, max-stale=3600")
                    .removeHeader("Pragma")
                    .build();
        }
    }

    public static boolean isNetworkConnected(){
        Context context= BaseRxHttpApplication.getContext();
        if(context!=null){
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if( networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
