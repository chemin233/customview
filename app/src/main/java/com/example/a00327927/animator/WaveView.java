package com.example.a00327927.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.a00327927.customview.R;

/**
 * Created by chemin on 2018/4/9 23:11.
 */

public class WaveView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;//view 的宽高
    private int originHeight=200;//水波纹初始高度
    private int mWaveLength=1000;//水波纹波长
    private int mHalfWaveLen;//半波长
    private Path mPath;
    private int mTransX;
    private ValueAnimator mValueAnimator;

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath=new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.color1d7cf7));
        mPaint.setStyle(Paint.Style.FILL);
        mHalfWaveLen= mWaveLength/2;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        originHeight=mHeight/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLength+mTransX,originHeight-mTransX);
        for (int i = -mWaveLength; i <mWidth; i+=mWaveLength) {
            mPath.rQuadTo(mHalfWaveLen/2,-100,mHalfWaveLen,0);
            mPath.rQuadTo(mHalfWaveLen/2,100,mHalfWaveLen,0);
        }

        mPath.lineTo(mWidth,mHeight);
        mPath.lineTo(0,mHeight);
        mPath.close();
        canvas.drawPath(mPath,mPaint);

        if (mValueAnimator==null){
            doAnimator();
        }
    }

    private void doAnimator() {
        mValueAnimator = ValueAnimator.ofInt(0,mWaveLength);
        mValueAnimator.setDuration(3*1000);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTransX = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }


}
