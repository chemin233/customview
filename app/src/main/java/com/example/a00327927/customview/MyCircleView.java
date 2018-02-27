package com.example.a00327927.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by chemin on 2018/1/8 16:27.
 * description：
 */

public class MyCircleView extends View {


    private int defautSize;
    private int defaultColor;

    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircleView);
        defautSize=typedArray.getDimensionPixelSize(R.styleable.MyCircleView_default_size,50);
        defaultColor=typedArray.getColor(R.styleable.MyCircleView_view_color,Color.BLUE);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getSize(defautSize,widthMeasureSpec);
        int heigh=getSize(defautSize,heightMeasureSpec);
        if (width<heigh){
            heigh=width;
        }else {
            width=heigh;
        }
        setMeasuredDimension(width,heigh);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r=getMeasuredHeight()/2;
        //获取圆心
        int centerX=getLeft()+r;
        int centerY=getTop()+r;

        //创建画笔
        Paint paint=new Paint();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.parseColor("#88880000"));
        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.ROUND);
        //开始绘制--------------------------

        //画圆
//        canvas.drawCircle(centerX,centerY,r,paint);
        //绘制颜色
//        canvas.drawColor(Color.parseColor("#88880000"));
        //画矩形
//        canvas.drawRect(100,10,500,50,paint);
        //画点
//        canvas.drawPoint(200,200,paint);

        //画椭圆
        //RectF rect=new RectF(new RectF(20,20,400,200));
//        canvas.drawOval(rect,paint);
        //画直线
//        canvas.drawLine(20,20,400,300,paint);
        //画圆角矩形
//        canvas.drawRoundRect(new RectF(20,50,300,500),50,50,paint);
        //话弧形
        canvas.drawArc(20,100,200,300,45,180,false,paint);
        canvas.drawArc(20,500,200,700,45,90,true,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(20,300,200,500,45,90,true,paint);



    }

    private int getSize(int defSize,int measureSpec){
        int mySize=defSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.AT_MOST://对应wrap_content
                mySize=size;
                break;
            case MeasureSpec.EXACTLY://对应固定尺寸或者match_parent
                mySize=size;
                break;
            case MeasureSpec.UNSPECIFIED://没有指定大小,这种情况就设默认值就行
                mySize=defSize;
                break;
            default:
                mySize=defSize;
        }
        return mySize;
    }
}
