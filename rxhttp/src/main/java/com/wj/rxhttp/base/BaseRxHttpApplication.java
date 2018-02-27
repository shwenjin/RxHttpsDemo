package com.wj.rxhttp.base;

import android.app.Application;
import android.content.Context;

import com.wj.rxhttp.RxHttpUtils;

/**
 * Created by wenjin on 2017/7/3.
 */

public class BaseRxHttpApplication extends Application{
    private static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    /**
     * 实例
     * @param base_url
     */
    public void initGlobal(String base_url){
        /**
         * 全局请求的统一配置
         */
        RxHttpUtils
                //开启全局配置
                .getGlobalRxHttp()
                //全局的BaseUrl
                .baseUrl(base_url)
                //开启缓存策略
                .setCache(false)
                //全局的请求头信息
                //.setHeaders(headerMaps)
                //全局持久话cookie,保存本地每次都会携带在header中
                .setCookie(false)
                //全局ssl证书认证
                //信任所有证书,不安全有风险
                .setSslSocketFactory()
                //使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.cer"))
                //使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.bks"), "123456", getAssets().open("your.cer"))
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setLog(true);
    }

    /**
     * 实例
     * @param base_url
     */
    public void initSingle(String base_url){
        /**
         * 单个请求的统一配置
         */
        RxHttpUtils.getSingleRxHttp()
                //全局的BaseUrl
                .baseUrl(base_url)
                //开启缓存策略
                .cache(false)
                //全局的请求头信息
                //.setHeaders(headerMaps)
                //全局持久话cookie,保存本地每次都会携带在header中
                .saveCookie(false)
                //全局ssl证书认证
                //信任所有证书,不安全有风险
                .sslSocketFactory()
                //使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.cer"))
                //使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.bks"), "123456", getAssets().open("your.cer"))
                //全局超时配置
                .readTimeout(10)
                //全局超时配置
                .writeTimeout(10)
                //全局超时配置
                .connectTimeout(10)
                //全局是否打开请求log日志
                .log(true);
    }
}
