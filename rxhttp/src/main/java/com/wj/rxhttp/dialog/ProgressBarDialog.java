package com.wj.rxhttp.dialog;

/**
 * Created by wenjin on 2017/7/3.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wj.rxhttp.R;

public class ProgressBarDialog extends Dialog{
//    private BallScaleView progressBar;
    private TextView text_state=null;
    private Context mcontext;
    public ProgressBarDialog(Context context) {
        super(context,android.R.style.Theme_Translucent_NoTitleBar);
        this.mcontext=context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        RelativeLayout view=(RelativeLayout) LayoutInflater.from(context).inflate(R.layout.progressbar, null);
//        progressBar= (BallScaleView) view.findViewById(R.id.progressBar2);
        text_state= (TextView) view.findViewById(R.id.text_state);
        addContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void show(){
        super.show();
        this.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub

                if (keyCode == KeyEvent.KEYCODE_BACK){
                    return true;
                }
                return true;
            }
        });
//        text_state.startLoading();
    }

    public void show(String text){
        super.show();
        this.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub

                if (keyCode == KeyEvent.KEYCODE_BACK){
                    return true;
                }
                return true;
            }
        });
        text_state.setText(text);
//        text_state.startLoading();
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        text_state.stopLoading();
    }

    @Override
    public void cancel() {
        super.cancel();
//        text_state.stopLoading();
    }


}
