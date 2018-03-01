package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chemin on 2018/1/26 09:28.
 * description：
 */

public class TestView extends View {


    private Paint mPaint;
    private final static float C=0.551915024494f;
    private float radius =100;
    private PointF circleLeft,circleRight;
    private float centerX,centerY;

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.GRAY);
        circleLeft=new PointF(-radius,0);
        circleRight=new PointF(radius,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //贝塞尔曲线 画圆
        drawCircle(canvas);



    }

    private void drawCircle(Canvas canvas) {
        Path path=new Path();
        canvas.translate(2*radius,radius);
        path.moveTo(0,radius);
        path.cubicTo(C*radius,radius,radius,C*radius,radius,0);
        canvas.drawPath(path,mPaint);

        path.cubicTo(radius,-C*radius,C*radius,-radius,0,-radius);
        canvas.drawPath(path,mPaint);

        path.cubicTo(-C*radius,-radius,-radius,-C*radius,-radius,0);
        canvas.drawPath(path,mPaint);

        path.cubicTo(-radius,C*radius,-C*radius,radius,0,radius);
        canvas.drawPath(path,mPaint);

        //-------------------
        canvas.translate(0,2*radius);
        path.moveTo(0,radius);
        path.cubicTo(C*radius,radius,radius+60,C*radius,radius+60,0);
        canvas.drawPath(path,mPaint);

        path.cubicTo(radius+60,-C*radius,radius,-radius,0,-radius);
        canvas.drawPath(path,mPaint);
        path.cubicTo(-C*radius,-radius,-radius,-C*radius,-radius,0);
        canvas.drawPath(path,mPaint);

        path.cubicTo(-radius,C*radius,-C*radius,radius,0,radius);
        canvas.drawPath(path,mPaint);

        //-------------------
        canvas.translate(0,2*radius);
        path.moveTo(0,radius);
        path.cubicTo(C*radius,radius,2*radius,C*radius,2*radius,0);
        canvas.drawPath(path,mPaint);

        path.cubicTo(2*radius,-C*radius,C*radius,-radius,0,-radius);
        canvas.drawPath(path,mPaint);
        path.cubicTo(-C*radius,-radius,-radius,-C*radius,-radius,0);
        canvas.drawPath(path,mPaint);

        path.cubicTo(-radius,C*radius,-C*radius,radius,0,radius);
        canvas.drawPath(path,mPaint);
    }
}
