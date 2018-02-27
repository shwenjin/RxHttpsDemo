package com.wj.rxhttp.upload;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by wenjin on 2017/7/4.
 */

public interface UploadFileApi {
    @Multipart
    @POST
    Observable<ResponseBody> uploadImg(@Url String uploadUrl,
                                       @Part MultipartBody.Part file);
}
