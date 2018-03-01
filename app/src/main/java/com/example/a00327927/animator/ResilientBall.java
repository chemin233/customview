package com.example.a00327927.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by chemin on 2018/3/1 14:11.
 * description：自定义弹性小球
 */

public class ResilientBall extends View {

    private final static float C=0.551915024494f;//贝塞尔曲线画圆系数
    private Paint mPaint;
    private int radius =100;
    private int translateX;
    private float startX,startY;
    private float currentX;
    private ValueAnimator valueAnimator;
    private Path mPath;
    private float animatedFraction;


    public ResilientBall(Context context) {
        this(context,null);
    }

    public ResilientBall(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ResilientBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        startX=2*radius;
        startY=2*radius;
        currentX=startX;
        mPath = new Path();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        translateX= w-3*radius-20;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(currentX,startY);
        mPath.reset();
        mPath.moveTo(0,radius);
        mPath.cubicTo(C*radius,radius,radius,C*radius,radius,0);
        canvas.drawPath(mPath,mPaint);

        mPath.cubicTo(radius,-C*radius,C*radius,-radius,0,-radius);
        canvas.drawPath(mPath,mPaint);

        mPath.cubicTo(-C*radius,-radius,-radius,-C*radius,-radius,0);
        canvas.drawPath(mPath,mPaint);

        mPath.cubicTo(-radius,C*radius,-C*radius,radius,0,radius);
        canvas.drawPath(mPath,mPaint);

        if (valueAnimator==null){
            valueAnimator = ValueAnimator.ofFloat(0,translateX);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentX=startX+(Float) animation.getAnimatedValue();
                    animatedFraction = animation.getAnimatedFraction();

                    invalidate();
                }
            });

            valueAnimator.setDuration(3*1000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.start();
        }





    }

    /**
     * 纵坐标渐变的点,圆的上下两个顶点
     */
    class VerticalPoint{
        public float x;
        public float y;

        /**
         * @param percent 动画的进度
         */
        public void adjustY(float percent){
            if (percent<0.6){
                y=y*(1-percent);
            }
        }
    }

    /**
     * 横坐标渐变的点,圆的左右两个点
     */
    class HorizontalPoint{

        public float x,y;
        public void adjustX(float percent){
            if (percent<0.6){
                x=x*(1-percent);
            }
        }

    }



}
