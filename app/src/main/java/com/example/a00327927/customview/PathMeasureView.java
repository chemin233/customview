package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chemin on 2018/1/29 16:03.
 * description：
 */

public class PathMeasureView extends View {


    private Paint mPaint;
    private float  mWidth,mHeight;

    private float[] pos=new float[2];
    private float[] tan=new float[2];
    private Matrix mMatrix;
    private Bitmap mBitmap;
    private float currentValue;


    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mMatrix=new Matrix();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jiantou,options);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w/2;
        mHeight=h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth,mHeight);
//        Path path=new Path();
//        path.addRect(-200,-200,200,200, Path.Direction.CW);
////        canvas.drawPath(path,mPaint);
//
//        Path dst =new Path();
//        dst.lineTo(200,0);
//        PathMeasure pathMeasure=new PathMeasure(path,false);
//        pathMeasure.getSegment(200,600,dst,true);
//        canvas.drawPath(dst,mPaint);

        Path path=new Path();
        path.addCircle(0,0,400, Path.Direction.CW);

        PathMeasure pathMeasure=new PathMeasure(path,true);

        currentValue+=0.01;
        if (currentValue>=1){
            currentValue=0;
        }

        pathMeasure.getPosTan(pathMeasure.getLength()*currentValue,pos,tan);

        //=====法一=========
//        mMatrix.reset();
//        float degrees= (float) (Math.atan2(tan[1],tan[0])*(180/Math.PI));
//
//        //设置 旋转图片
//        mMatrix.postRotate(degrees,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
//        // 将图片绘制中心调整到与当前点重合
//        mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);


        //========法二===========
        pathMeasure.getMatrix(pathMeasure.getLength()*currentValue,mMatrix,PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2);

        canvas.drawPath(path,mPaint);
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);

        invalidate();

    }
}
