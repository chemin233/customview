package com.example.a00327927.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
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
    private float circleRatio=1;//控制画圆的系数
    private float currentRatio;//控制画圆弧的实际系数
    private Paint mPaint;
    private int radius =100;
    private int translateX;
    private float startX,startY;
    private float currentX;
    private ValueAnimator valueAnimator;
    private Path mPath;
    private float animatedFraction;
    private PointF left,right,top,bottom;
    private float horizontalOffsetValue =6f;
    private float verticalOffsetValue =0.6f;


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

        //初始化4个点
        bottom=new PointF(0,radius);
        right=new PointF(radius,0);
        top=new PointF(0,-radius);
        left=new PointF(-radius,0);

        currentRatio=circleRatio*C;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        translateX= w-3*radius-40;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(currentX,startY);
        mPath.reset();
        mPath.moveTo(bottom.x,bottom.y);
        mPath.cubicTo(currentRatio*radius,bottom.y,right.x,currentRatio*radius,right.x,right.y);
        canvas.drawPath(mPath,mPaint);

        mPath.cubicTo(right.x,-currentRatio*radius,currentRatio*radius,top.y,top.x,top.y);
        canvas.drawPath(mPath,mPaint);

        mPath.cubicTo(-currentRatio*radius,top.y,left.x,-currentRatio*radius,left.x,left.y);
        canvas.drawPath(mPath,mPaint);

        mPath.cubicTo(left.x,currentRatio*radius,-currentRatio*radius,bottom.y,bottom.x,bottom.y);
        canvas.drawPath(mPath,mPaint);

        if (valueAnimator==null){
            valueAnimator = ValueAnimator.ofFloat(0,translateX);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentX=startX+(Float) animation.getAnimatedValue();
                    animatedFraction = animation.getAnimatedFraction();

                    Log.e("cm","value---"+animation.getAnimatedValue());
                    Log.e("cm","animatedFraction---"+animation.getAnimatedFraction());
                    setHorizontalData();


                    invalidate();
                }
            });

            valueAnimator.setDuration(2*1000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.start();
        }





    }

    private void setHorizontalData() {
        if (animatedFraction>0&&animatedFraction<=0.25f){//水平方向左边部分不动,右边增加; 垂直方向不动
            right.x+= horizontalOffsetValue;
        }else if (animatedFraction>0.25f&&animatedFraction<=0.5f){
            left.x-= horizontalOffsetValue;
//            top.y +=verticalOffsetValue;
//            bottom.y-=verticalOffsetValue;
        }else if (animatedFraction>0.5f&&animatedFraction<=0.75f){
            left.x+= horizontalOffsetValue/2;
            right.x-= horizontalOffsetValue/2;
//            currentRatio= (float) (currentRatio*(0.5+animatedFraction));
//            top.y -=verticalOffsetValue/2;
//            bottom.y+=verticalOffsetValue/2;
        }else if (animatedFraction>0.75){
            left.x+= horizontalOffsetValue/2;
            right.x-= horizontalOffsetValue/2;
//            top.y -=verticalOffsetValue/2;
//            bottom.y+=verticalOffsetValue/2;
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
