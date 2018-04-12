package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chemin on 2018/4/12 09:17.
 * description：
 */

public class CustomGradeProgress extends View {

    private int mWidth,mHeight;
    private int windowWidth,windowHeight;
    private List<String> itemConten;
    private Paint mPaint;
    private int lineWidth=10;
    private int padding=200;

    public CustomGradeProgress(Context context) {
        this(context,null);
    }

    public CustomGradeProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomGradeProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //获取屏幕框
        DisplayMetrics dm = getResources().getDisplayMetrics();
        windowWidth = dm.heightPixels;
        windowHeight = dm.widthPixels;

        itemConten=new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int w=0;
        int h=0;
        switch (widthMode){
            case MeasureSpec.AT_MOST:
                w=windowWidth;
                break;
            case MeasureSpec.EXACTLY:
                w=width;
                break;
            case MeasureSpec.UNSPECIFIED:
                w=width;
                break;
        }

        switch (heightMode){
            case MeasureSpec.AT_MOST:
                h=50;
                break;
            case MeasureSpec.EXACTLY:
                h=height;
                break;
            case MeasureSpec.UNSPECIFIED:
                h=height;
                break;
        }
        setMeasuredDimension(w,h);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setColor(Color.parseColor("#303030"));

        mPaint.setStrokeWidth(lineWidth);
        canvas.translate(0,mHeight/3);
        //1.先画一条与父容器同宽的直线
        canvas.drawLine(0,0,mWidth,0,mPaint);
        //2.根据节点数画点(由list.size决定) list就是下方数字个数
        if (itemConten!=null&&itemConten.size()!=0){
            int interver=(mWidth-padding)/(itemConten.size()-1);
            int pointX=padding/2;
            mPaint.setStrokeWidth(lineWidth*2);
            for (int i = 0; i < itemConten.size(); i++) {
                canvas.drawPoint(pointX,0,mPaint);
                pointX+=interver;
            }
            //3.在每个节点下方写字
        }

    }

    public void setContent(List<String> data){
        this.itemConten=data;
        invalidate();
    }
}
