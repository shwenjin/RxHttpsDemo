package com.wj.rxhttp.dialog;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

/**
 * Created by wenjin on 2017/7/6.
 */

public class GraduallyTextView extends EditText {
    private CharSequence text;
    private int startY = 0;
    private float progress;
    private Paint mPaint;
    private int textLength;
    private boolean isStop = true;
    private float scaleX;
    private int mDuration = 1000;
    private float sigleDuration;
    private ValueAnimator mValueAnimator=null;
    public GraduallyTextView(Context context) {
        super(context);
        init();
    }

    public GraduallyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraduallyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        setBackgroundColor(Color.argb(0,0,0,0));
        setCursorVisible(false);
        setFocusable(false);
        setEnabled(false);
        setFocusableInTouchMode(false);
        mValueAnimator=ValueAnimator.ofFloat(0,100);
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress= (float) animation.getAnimatedValue();
                GraduallyTextView.this.invalidate();
            }
        });
    }

    public void startLoading() {
        if (!isStop) {
            return;
        }
        textLength = getText().length();
        if (TextUtils.isEmpty(getText().toString())) {
            return;
        }
        isStop = false;
        text = getText();

        scaleX = getTextScaleX() * 10;
        startY = 88;
        mPaint.setColor(getCurrentTextColor());
        mPaint.setTextSize(getTextSize());
        setMinWidth(getWidth());
        setText("");
        setHint("");
        mValueAnimator.start();
        sigleDuration = 100f / textLength;
    }


    public void stopLoading() {
        mValueAnimator.end();
        mValueAnimator.cancel();
        isStop = true;
        setText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isStop) {
            mPaint.setAlpha(255);
            if (progress / sigleDuration >= 1) {
                canvas.drawText(String.valueOf(text), 0,
                        (int) (progress / sigleDuration), scaleX, startY,
                        mPaint);
            }
            mPaint.setAlpha(
                    (int) (255 * ((progress % sigleDuration) / sigleDuration)));
            int lastOne = (int) (progress / sigleDuration);
            if (lastOne < textLength) {
                canvas.drawText(String.valueOf(text.charAt(lastOne)), 0, 1,
                        scaleX + getPaint().measureText(
                                text.subSequence(0, lastOne).toString()),
                        startY, mPaint);
            }
        }
    }
}
