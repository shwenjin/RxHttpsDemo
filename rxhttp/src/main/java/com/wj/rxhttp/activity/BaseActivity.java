package com.wj.rxhttp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wj.rxhttp.base.BaseRxHttpApplication;
import com.wj.rxhttp.dialog.ProgressBarDialog;
import com.wj.rxhttp.dialog.TipDialog;

/**
 * Created by wenjin on 2017/7/3.
 */

public class BaseActivity extends Activity{
    private ProgressBarDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initDialog(){
        if(mProgressDialog==null){
            mProgressDialog=new ProgressBarDialog(this);
        }
    }

    protected void dismiss(){
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
        }
        mProgressDialog=null;
    }

    protected ProgressBarDialog getProgressDialog(){
        initDialog();
        return mProgressDialog;
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismiss();
    }
}
