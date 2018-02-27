package com.wj.rxhttp.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wj.rxhttp.R;


/**
 * Created by wenjin on 2017/7/3.
 */
public class TipDialog extends Dialog {
    private TextView dialog_title;
    private TextView dialog_message;
    private Button btn_positive;
    private Button btn_negative;
    private Context mcontext;
    private LinearLayout linear_control=null;
    private View view_xian=null;
    public TipDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mcontext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(context) .inflate(R.layout.view_tips_dialog, null);
        view.setBackgroundColor(Color.argb(100,0,0,0));
        linear_control= (LinearLayout) view.findViewById(R.id.linear_control);
        dialog_title = (TextView) view.findViewById(R.id.dialog_title);
        dialog_message = (TextView) view.findViewById(R.id.dialog_message);
        btn_positive = (Button) view.findViewById(R.id.btn_positive);
        btn_negative = (Button) view.findViewById(R.id.btn_negative);
        view_xian = view.findViewById(R.id.view_xian);
        addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public TipDialog setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            dialog_title.setText(title);
        }
        return this;
    }

    public TipDialog setMessage(String msg){
        if (!TextUtils.isEmpty(msg)){
            dialog_message.setText(msg);
        }
        return this;
    }

    public TipDialog setYes(String yes){
        if (!TextUtils.isEmpty(yes)){
            btn_positive.setText(yes);
        }
        return this;
    }

    public TipDialog setNo(String no){
        if (!TextUtils.isEmpty(no)){
            btn_negative.setText(no);
        }
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void show(){
        view_xian.setVisibility(View.GONE);
        btn_negative.setVisibility(View.GONE);
        super.show();
        this.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return true;
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDismiss();
            }
        });
    }


    /**
     * 单按钮
     * @param positiveBtnListen  yes
     */
    public void show(final View.OnClickListener positiveBtnListen){
        view_xian.setVisibility(View.GONE);
        btn_negative.setVisibility(View.GONE);
        super.show();
        this.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return true;
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positiveBtnListen != null) {
                    positiveBtnListen.onClick(v);
                }
                onDismiss();
            }
        });
    }

    /**
     * 双按钮
     * @param positiveBtnListen  yes
     * @param negativeBtnListener  no
     */
    public void show(final View.OnClickListener positiveBtnListen,
                     final View.OnClickListener negativeBtnListener) {
        super.show();
        this.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return true;
            }
        });
        btn_negative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (negativeBtnListener != null) {
                    negativeBtnListener.onClick(v);
                }
                onDismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positiveBtnListen != null) {
                    positiveBtnListen.onClick(v);
                }
                onDismiss();
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AnimatorSet animatorSet=FlipHorizontal();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                linear_control.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    private void onDismiss(){
        AnimatorSet animatorSet=zoomOutExit();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                superDismiss();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                superDismiss();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public void superDismiss(){
        super.dismiss();
    }

    private AnimatorSet zoomOutExit(){
        //缩小退出
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linear_control, "alpha", 1, 0, 0),//
                ObjectAnimator.ofFloat(linear_control, "scaleX", 1, 0.3f, 0),//
                ObjectAnimator.ofFloat(linear_control, "scaleY", 1, 0.3f, 0));
        return animatorSet;
    }

    private AnimatorSet zoomInEnter(){
        //放大进入
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(linear_control, "scaleX", 0.5f, 1),//
                ObjectAnimator.ofFloat(linear_control, "scaleY", 0.5f, 1),//
                ObjectAnimator.ofFloat(linear_control, "alpha", 0, 1));//
        return animatorSet;
    }

    private AnimatorSet Swing(){
        //抖动
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(linear_control, "alpha", 1, 1, 1, 1, 1, 1, 1, 1),//
                ObjectAnimator.ofFloat(linear_control, "rotation", 0, 10, -10, 6, -6, 3, -3, 0));
        return animatorSet;
    }

    private AnimatorSet FlipHorizontal(){
        //沿Y轴反转90度
        linear_control.setVisibility(View.GONE);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linear_control, "rotationY", 90, 0),//
                ObjectAnimator.ofFloat(linear_control, "alpha", 0, 1));
        return animatorSet;
    }

    private AnimatorSet FlipVerticalExit(){
        //沿X轴反转90度
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linear_control, "rotationX", 0, 90),//
                ObjectAnimator.ofFloat(linear_control, "alpha", 1, 0));
        return animatorSet;
    }

    private AnimatorSet Jelly(){
        //警告进入
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(linear_control, "scaleX", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1),//
                ObjectAnimator.ofFloat(linear_control, "scaleY", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1),//
                ObjectAnimator.ofFloat(linear_control, "alpha", 0.2f, 1));
        return animatorSet;
    }

    private AnimatorSet sss(){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(250);
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(linear_control, "scaleX", 0.1f, 0.5f, 1f), //
                ObjectAnimator.ofFloat(linear_control, "scaleY", 0.1f, 0.5f, 1f),//
                ObjectAnimator.ofFloat(linear_control, "alpha", 0f, 1f),//
                ObjectAnimator.ofFloat(linear_control, "rotation", 1080, 720, 360, 0));
        return animatorSet;
    }
}
