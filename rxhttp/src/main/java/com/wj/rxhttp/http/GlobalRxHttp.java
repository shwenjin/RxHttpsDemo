package com.wj.rxhttp.http;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.wj.rxhttp.interceptor.AddCookiesInterceptor;
import com.wj.rxhttp.interceptor.CacheInterceptor;
import com.wj.rxhttp.interceptor.HeaderInterceptor;
import com.wj.rxhttp.interceptor.ReceivedCookiesInterceptor;
import com.wj.rxhttp.utils.SSLUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by wenjin on 2017/7/4.
 * 全配置请求
 */

public class GlobalRxHttp {
    private static GlobalRxHttp instance;
    public static GlobalRxHttp getInstance(){
        if(instance ==null){
            synchronized (SingleRxHttp.class){
                if(instance==null){
                    instance=new GlobalRxHttp();
                }
            }
        }
        return instance;
    }

    public GlobalRxHttp baseUrl(String baseUrl) {
        getGlobalRetrofitBuilder().baseUrl(baseUrl);
        return this;
    }

    public GlobalRxHttp setOkClient(OkHttpClient okClient){
        getGlobalRetrofitBuilder().client(okClient);
        return this;
    }

    public GlobalRxHttp setHeaders(Map<String, Object> headerMaps) {
        getGlobalOkHttpBuilder().addInterceptor(new HeaderInterceptor(headerMaps));
        return this;
    }

    /**
     * 开启请求日志
     * @param showLog
     * @return
     */
    public GlobalRxHttp setLog(boolean showLog) {
        if(showLog){
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){

                @Override
                public void log(String message) {
                    Log.e("RxHttpUtils",message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            getGlobalOkHttpBuilder().addInterceptor(loggingInterceptor);
        }
        return this;
    }

    /**
     * 开启缓存
     * @param isCache
     * @return
     */
    public GlobalRxHttp setCache(boolean isCache) {
        if(isCache){
            CacheInterceptor cacheInterceptor=new CacheInterceptor();
            Cache cache=new Cache(new File(Environment.getExternalStorageDirectory().getPath()+ "/rxHttpCacheData"), 1024 * 1024 * 100);
            getGlobalOkHttpBuilder().addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache);
        }
        return this;
    }

    /**
     * 设置缓存路径及缓存文件大小
     *
     * @param cachePath
     * @param maxSize
     * @return
     */
    public GlobalRxHttp setCache(String cachePath, long maxSize) {
        if (!TextUtils.isEmpty(cachePath) && maxSize > 0) {
            CacheInterceptor cacheInterceptor = new CacheInterceptor();
            Cache cache = new Cache(new File(cachePath), maxSize);
            getGlobalOkHttpBuilder()
                    .addInterceptor(cacheInterceptor)
                    .addNetworkInterceptor(cacheInterceptor)
                    .cache(cache);
        }

        return this;
    }

    public GlobalRxHttp setCookie(boolean saveCookie) {
        if (saveCookie) {
            getGlobalOkHttpBuilder()
                    .addInterceptor(new AddCookiesInterceptor())
                    .addInterceptor(new ReceivedCookiesInterceptor());
        }
        return this;
    }

    /**
     * 设置读取超时时间
     *
     * @param second
     * @return
     */
    public GlobalRxHttp setReadTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置写入超时时间
     *
     * @param second
     * @return
     */
    public GlobalRxHttp setWriteTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置连接超时时间
     *
     * @param second
     * @return
     */
    public GlobalRxHttp setConnectTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 信任所有证书,不安全有风险
     *
     * @return
     */
    public GlobalRxHttp setSslSocketFactory() {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 使用预埋证书，校验服务端证书（自签名证书）
     *
     * @param certificates
     * @return
     */
    public GlobalRxHttp setSslSocketFactory(InputStream... certificates) {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(certificates);
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
     *
     * @param bksFile
     * @param password
     * @param certificates
     * @return
     */
    public GlobalRxHttp setSslSocketFactory(InputStream bksFile, String password, InputStream... certificates) {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(bksFile, password, certificates);
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 全局的 RetrofitBuilder
     *
     * @return
     */
    public Retrofit.Builder getGlobalRetrofitBuilder() {
        return RetrofitClient.getInstance().getRetrofitBuilder();
    }

    public OkHttpClient.Builder getGlobalOkHttpBuilder() {
        return HttpClient.getInstance().getBuilder();
    }

    /**
     * 全局的 retrofit
     *
     * @return
     */
    public static Retrofit getGlobalRetrofit() {
        return RetrofitClient.getInstance().getRetrofit();
    }

    /**
     * 使用全局变量的请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public static <K> K createGApi(final Class<K> cls) {
        return getGlobalRetrofit().create(cls);
    }
}
