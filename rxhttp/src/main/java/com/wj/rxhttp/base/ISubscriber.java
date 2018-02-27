package com.wj.rxhttp.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by wenjin on 2017/7/3.
 */

public interface ISubscriber<T extends BaseResponse> {

    public void doOnSubscribe(Disposable d);
    public void doOnError(String errorMsg);
    public void doOnNext(T t);
    public void doOnCompleted();
}
