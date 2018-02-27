package com.wj.testhttps;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wj.rxhttp.*;
import com.wj.rxhttp.activity.BaseActivity;
import com.wj.rxhttp.dialog.ProgressBarDialog;
import com.wj.rxhttp.dialog.TipDialog;
import com.wj.rxhttp.http.CommonObserver;
import com.wj.rxhttp.interceptor.Transformer;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_onclick)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("tag",BuildConfig.BASE_URL);
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("commandType","le_saveByOa");//  .baseUrl(BuildConfig.BASE_URL)
                        RxHttpUtils.getSingleRxHttp()
                                .createApi(ApiService.class)
                                .SLeave(map)
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

                    }
                });

        findViewById(R.id.btn_global).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }
}
