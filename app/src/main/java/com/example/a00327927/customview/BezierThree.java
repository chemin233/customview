package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.location.Location;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by chemin on 2018/1/25 15:40.
 * description：
 */

public class BezierThree extends View {

    private PointF control1,control2,start,end;
    private float centerX,centerY;
    private Paint mPaint;
    private final static String TAG="BezierThree";
    private boolean isControl1;
    private boolean isControl2;


    public BezierThree(Context context) {
        this(context,null);
    }

    public BezierThree(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        control1=new PointF(0,0);
        control2=new PointF(0,0);
        start=new PointF(0,0);
        end=new PointF(0,0);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;
        start.x=centerX-400;
        start.y=centerY;
        end.x=centerX+400;
        end.y=centerY;
        control1.x=centerX-300;
        control1.y=centerY-300;
        control2.x=centerX+300;
        control2.y=centerY-400;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化画笔
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);

        //绘制4个点
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);

        mPaint.setStrokeWidth(30);
        mPaint.setColor(Color.YELLOW);
        canvas.drawPoint(control1.x,control1.y,mPaint);
        canvas.drawPoint(control2.x,control2.y,mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GRAY);

        canvas.drawLine(start.x,start.y,control1.x,control1.y,mPaint);
        canvas.drawLine(control1.x,control1.y,control2.x,control2.y,mPaint);
        canvas.drawLine(control2.x,control2.y,end.x,end.y,mPaint);

        //开始绘制贝塞尔曲线
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        Path path=new Path();
        path.moveTo(start.x,start.y);
        path.cubicTo(control1.x,control1.y,control2.x,control2.y,end.x,end.y);
        canvas.drawPath(path,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float tempX = event.getX();
        float tempY = event.getY();


        if (event.getAction()==MotionEvent.ACTION_UP){
            isControl1=false;
            isControl2=false;
        }else {
            if (isControl1||DistanceOfTwoPoints(tempX,tempY,control1.x,control1.y)<30){
                //触摸的是1控制点
                control1.x=tempX;
                control1.y=tempY;
                isControl1=true;
            }else if(isControl2||DistanceOfTwoPoints(tempX,tempY,control2.x,control2.y)<30){
                //触摸的是1控制点
                control2.x=tempX;
                control2.y=tempY;
                isControl2=true;
            }
            invalidate();
        }
        return true;
    }

    public float DistanceOfTwoPoints(float lat1,float lng1,
                                     float lat2,float lng2) {

        return (float) Math.sqrt(Math.pow((lat2-lat1),2)+Math.pow((lng2-lng1),2));
    }
}
