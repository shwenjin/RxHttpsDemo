package com.wj.rxhttp.base;


import android.util.Log;

import com.wj.rxhttp.dialog.TipDialog;
import com.wj.rxhttp.exception.ApiException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by wenjin on 2017/7/3.
 */

public abstract class BaseObserver<T extends BaseResponse> implements Observer<T>,ISubscriber<T>{
    private TipDialog mTipDialog=null;
    protected void doOnNetError(){

    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull T t) {
        doOnNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d("tag",e.toString());
        if (e instanceof SocketTimeoutException) {
            setError(ApiException.errorMsg_SocketTimeoutException);
        } else if (e instanceof ConnectException) {
            setError(ApiException.errorMsg_ConnectException);
        } else if (e instanceof UnknownHostException) {
            setError(ApiException.errorMsg_UnknownHostException);
        } else if (e instanceof HttpException){
            //获取对应statusCode和Message
            HttpException exception = (HttpException)e;
            String message = exception.response().message();
            int code = exception.response().code();
            setError(ApiException.httpCode(code));
        } else {
            String error = e.getMessage();
//            showDialog(error);
            setError(error);
        }
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }

    private void setError(String errorMsg) {
//        showDialog(errorMsg);
        doOnError(errorMsg);
        doOnNetError();
    }

    /**
     * 提示
     * @param msg
     */
    protected void showDialog(String msg){
        if(mTipDialog==null){
            mTipDialog=new TipDialog(BaseRxHttpApplication.getContext());
        }
        mTipDialog.setMessage(msg)
                .show();
    }

    /**
     * 错误处理
     */
    private String handleError(Throwable e) {
        String error = null;
        try {
            ResponseBody errorBody = ((HttpException) e).response().errorBody();
            error = errorBody.string();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return error;
    }
}
