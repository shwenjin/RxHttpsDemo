package com.wj.testhttps;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wenjin on 2017/7/3.
 */

public interface ApiService {

    /**
     * 表单方式提交
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("SLeave")
    Observable<BookBean> SLeave(@FieldMap Map<String,String> maps);

    /**
     * Json方式提交
     * @param bookBean
     * @return
     */
    @POST("SLeave")
    Observable<BookBean> getSLeave(@Body BookBean bookBean);

    @GET("GetDataByProductID")
    Observable<BookBean> GetDataByProductID(@Query("NTAccount") String account);
}
