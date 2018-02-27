package com.wj.rxhttp;

import com.wj.rxhttp.http.GlobalRxHttp;
import com.wj.rxhttp.http.SingleRxHttp;
import com.wj.rxhttp.upload.UploadRetrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by wenjin on 2017/7/3.
 */

public class RxHttpUtils {
    private static RxHttpUtils instance;

    public static RxHttpUtils getInstance(){
        if(instance ==null){
            synchronized (RxHttpUtils.class){
                if(instance==null){
                    instance=new RxHttpUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 获取单个请求配置实例
     * @return
     */
    public static SingleRxHttp getSingleRxHttp(){
        return SingleRxHttp.getInstance();
    }

    /**
     * 获取全配置的请求实例
     * @return
     */
    public static GlobalRxHttp getGlobalRxHttp(){
        return GlobalRxHttp.getInstance();
    }

    /**
     * 上传单个
     * @param uploadUrl
     * @param filePath
     * @return
     */
    public static Observable<ResponseBody> uploadRxHttp(String uploadUrl, String filePath){
        return UploadRetrofit.getInstance().uploadImg(uploadUrl, filePath);
    }
}
