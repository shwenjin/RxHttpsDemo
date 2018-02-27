package com.wj.rxhttp.activity;

import android.util.Log;

import com.wj.rxhttp.RxHttpUtils;
import com.wj.rxhttp.http.CommonObserver;
import com.wj.rxhttp.interceptor.Transformer;

import io.reactivex.disposables.Disposable;

/**
 * Created by wenjin on 2017/7/4.
 *
 * 调用实例
 */
public class TestActivity extends BaseActivity{


    /**
     *
         public interface ApiService {
            @POST("GetDataByProductID")
            Observable<BookBean> GetDataByProductID(@FieldMap Map<String,String> maps);

            @GET("GetDataByProductID")
            Observable<BookBean> GetDataByProductID(@Query("NTAccount") String account);
        }
     */
    private void test(){
        /**
         * 第一种
            RxHttpUtils.getSingleRxHttp()
                    .baseUrl(BuildConfig.BASE_URL)
                    .createApi(ApiService.class)
                    .GetDataByProductID("1")
                    .compose(Transformer.<BookBean>switchSchedulers(getProgressDialog()))
                    .subscribe(new CommonObserver<BookBean>(getProgressDialog()){

                        @Override
                        protected void getDisposable(Disposable d) {
                            //方法暴露出来使用者根据需求去取消订阅
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            //错误处理
                            Log.e("tag","------->>"+errorMsg);
                        }

                        @Override
                        protected void onSuccess(BookBean bookBean) {
                            Log.d("tag","------->>"+bookBean.toString());
                        }
                    });
         */

        /**
         * 第二种
            RxHttpUtils.getGlobalRxHttp()
                    .baseUrl(BuildConfig.BASE_URL)
                    .createGApi(ApiService.class)
                    .GetDataByProductID("1")
                    .compose(Transformer.<BookBean>switchSchedulers(getProgressDialog()))
                    .subscribe(new CommonObserver<BookBean>(getProgressDialog()) {
                        @Override
                        protected void getDisposable(Disposable d) {

                        }

                        @Override
                        protected void onError(String errorMsg) {

                        }

                        @Override
                        protected void onSuccess(BookBean bookBean) {

                        }
                    });
         */

        /**
         * 第三种
         *
         * 2请求按顺序执行
            RxHttpUtils
                .createApi(ApiService.class)
                .getBook()
                .flatMap(new Function<BookBean, ObservableSource<Top250Bean>>() {
                    @Override
                    public ObservableSource<Top250Bean> apply(@NonNull BookBean bookBean) throws Exception {
                        return RxHttpUtils
                                .createApi(ApiService.class)
                                .getTop250(20);
                    }
                })
                .compose(Transformer.<Top250Bean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<Top250Bean>(loading_dialog) {
                    @Override
                    protected void getDisposable(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Top250Bean top250Bean) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(top250Bean.getTitle() + "\n");

                        for (Top250Bean.SubjectsBean s : top250Bean.getSubjects()) {
                            sb.append(s.getTitle() + "\n");
                        }
                        responseTv.setText(sb.toString());
                        //请求成功
                        showToast(sb.toString());
                    }
                });
         */

        /**
         * 第四种
         *
         * 图片上传

                RxHttpUtils
                .uploadImg("your_upload_url", "your_filePath")
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        Log.e("allen", "上传完毕: " + responseBody.string());
                    }
                });
         */
    }
}
