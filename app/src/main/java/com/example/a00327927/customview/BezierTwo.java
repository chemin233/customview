package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chemin on 2018/1/25 14:29.
 * description：
 */

public class BezierTwo extends View {


    private Paint mPaint;
    private final static String TAG = "BezierTwo";
    private float centerX, centerY;

    private float controlX, controlY;
    private float startX;
    private float startY;
    private float endX;
    private float endY;


    public BezierTwo(Context context) {
        this(context, null);
    }

    public BezierTwo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.e(TAG, "init------------>");
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged");
        centerX = w / 2;
        centerY = h / 2;
        startX = centerX - 300;
        startY = centerY;
        endX = centerX + 300;
        endY = centerY;
        controlX = centerX;
        controlY = centerY - 300;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure----");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw----");

//        canvas.translate(centerX,centerY);


        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);

        //先绘制2个起始点和1个控制点
        canvas.drawPoint(startX, startY, mPaint);
        canvas.drawPoint(endX, endY, mPaint);
        canvas.drawPoint(controlX, controlY, mPaint);

        //绘制点与控制点之间的线段
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(10);

        canvas.drawLine(startX, startY, controlX, controlY, mPaint);
        canvas.drawLine(controlX, controlY, endX, endY, mPaint);

        //开始绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo(controlX, controlY, endX, endY);
        canvas.drawPath(path, mPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_UP){
            controlX=centerX;
            controlY=centerY;
        }else {
            controlX = event.getX();
            controlY = event.getY();
        }
        invalidate();
        return true;
    }
}
