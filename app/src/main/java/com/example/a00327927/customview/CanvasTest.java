package com.example.a00327927.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by chemin on 2018/1/22 14:07.
 * description：
 */

public class CanvasTest extends View {



    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mWidth;
    private int mHeight;


    public CanvasTest(Context context) {
        this(context,null);
    }

    public CanvasTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.GRAY);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
//        float density = dm.density;
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);

        //位移
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,200,mPaint);
//
//        mPaint.setColor(Color.RED);
//        canvas.translate(400,400);
//        canvas.drawCircle(0,0,200,mPaint);

        //缩放
//        Rect rect=new Rect(0,-800,400,0);
//        canvas.translate(mWidth/2,mHeight/2);
//        canvas.drawRect(rect,mPaint);
//
//        mPaint.setColor(Color.BLUE);
//        canvas.scale(0.5f,0.5f,200,0);
//        canvas.drawRect(rect,mPaint);
////        canvas.drawRect();
//
//        Rect rect1=new Rect(100,-800,500,0);
//
//        mPaint.setColor(Color.BLACK);
//        canvas.scale(0.5f,0.5f,200,0);
//        canvas.drawRect(rect1,mPaint);

        //缩放的应用
//        RectF rectF=new RectF(-400,-400,400,400);
//        canvas.translate(mWidth/2,mHeight/2);
//        canvas.drawRect(rectF,mPaint);
//
//        for (int i = 0; i < 20; i++) {
//            canvas.scale(0.8f,0.8f);
//            canvas.drawRect(rectF,mPaint);
//        }

        //旋转的应用
        canvas.drawCircle(0,0,420,mPaint);
        startRotate(canvas);


        //错切的应用
//        startSkew(canvas);



    }

    private void startRotate(Canvas canvas) {
        canvas.drawCircle(0,0,400,mPaint);
        canvas.drawCircle(0,0,370,mPaint);

        canvas.drawCircle(0,-480,60,mPaint);
        mPaint.setStrokeWidth(30);
        canvas.drawLine(0,-400,0,-420,mPaint);

        mPaint.setStrokeWidth(8);
        int t=0;
        for (int i = 0; i <360; i+=6) {
            mPaint.setStrokeWidth(8);
//            canvas.drawLine(0,400,0,420,mPaint);
//            canvas.drawLine(0,400,0,420,mPaint);
            if (i%5==0){
                canvas.drawLine(0,370,0,330,mPaint);
                mPaint.setTextSize(60);
                mPaint.setStrokeWidth(2);
                if (i==0){
                    canvas.drawText(String.valueOf(12),-15,-280,mPaint);
                }else {
                    canvas.drawText(String.valueOf(t++),-15,-280,mPaint);
                }
                canvas.rotate(6);
                continue;
            }
            canvas.drawLine(0,370,0,350,mPaint);
            canvas.rotate(6);
        }
    }

    private void startSkew(Canvas canvas) {
        Rect rect=new Rect(0,0,200,200);
        canvas.drawRect(rect,mPaint);
        canvas.save();
        canvas.skew(1,0);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect,mPaint);
        canvas.skew(0,1);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect,mPaint);


        Rect rect1=new Rect(0,0,200,200);
        canvas.restore();
        canvas.translate(0,300);
        canvas.drawRect(rect1,mPaint);
        canvas.skew(0,1);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect1,mPaint);
        canvas.skew(1,0);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect1,mPaint);

    }


}
