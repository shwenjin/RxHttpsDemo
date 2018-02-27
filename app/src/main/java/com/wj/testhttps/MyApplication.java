package com.wj.testhttps;

import com.wj.rxhttp.*;
import com.wj.rxhttp.base.BaseRxHttpApplication;

/**
 * Created by wenjin on 2017/7/3.
 */

public class MyApplication extends BaseRxHttpApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        initSingle(BuildConfig.BASE_URL);
    }
}
