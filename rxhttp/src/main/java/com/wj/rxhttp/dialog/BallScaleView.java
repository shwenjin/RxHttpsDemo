package com.wj.rxhttp.dialog;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wj.rxhttp.R;

/**
 * Created by wenjin on 2017/7/5.
 */

public class BallScaleView extends View{
    private Paint mPaint;
    float[] translateYFloats=new float[3];
    private boolean isFrist=true;

    public BallScaleView(Context context) {
        super(context);
        init(null, 0);
    }

    public BallScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BallScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BallScaleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint=new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    private int measureDimension(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width  = measureDimension(dp2px(65), widthMeasureSpec);
        int height = measureDimension(dp2px(15), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float circleSpacing=5;//间隔
        float radius=(getWidth()-circleSpacing*5)/10;//半径
        if(isFrist){
            isFrist=false;
            for (int index=0;index<3;index++){
                translateYFloats[index]=getHeight()/2+radius;
            }
        }

        float x = getWidth()/ 2-(radius*2+circleSpacing);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX=x+(radius*2)*i+circleSpacing*i;
            canvas.translate(translateX, translateYFloats[i]);
            canvas.drawCircle(0, 0, radius, mPaint);
            canvas.restore();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        float circleSpacing=5;
        final float radius=(getWidth()-circleSpacing*5)/10;
        int[] delays=new int[]{70,140,210};
        for (int i = 0; i < translateYFloats.length; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(getHeight()/2,getHeight()/2-radius*2,getHeight()/2);
            scaleAnim.setDuration(600);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateYFloats[index] = (float) animation.getAnimatedValue()+radius;
                    postInvalidate();
                }
            });
            scaleAnim.start();
        }
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }
}
