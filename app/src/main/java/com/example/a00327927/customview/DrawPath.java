package com.example.a00327927.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Calendar;

/**
 * Created by chemin on 2018/1/15 20:01.
 * description：
 */

public class DrawPath extends View {

    private Paint paint;
    private Path path;
    private int mViewWidth;
    private int mViewHeight;
    private float mDegree;
    private ValueAnimator valueAnimator;


    public DrawPath(Context context) {
        this(context,null);
    }

    public DrawPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path=new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode=MeasureSpec.getMode(widthMeasureSpec);

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int x=0,y=0;
        switch (mode){
            case MeasureSpec.AT_MOST:
                x=400;
                y=400;
                break;
            case MeasureSpec.EXACTLY:
                x=width;
                y=height;
                break;
            case MeasureSpec.UNSPECIFIED:
                x=width;
                y=height;
                break;
        }
        setMeasuredDimension(x,y);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth=w;
        mViewHeight=h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));



//
//        path.addCircle(400,400,200, Path.Direction.CCW);
////        canvas.drawPath(path,paint);
//
//
//        path.addCircle(400,400,400, Path.Direction.CCW);
//
//        path.setFillType(Path.FillType.EVEN_ODD);
//
//        canvas.drawPath(path,paint);


        paint.setStyle(Paint.Style.FILL);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        canvas.rotate(mDegree);

        canvas.rotate(-150);
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.setFillType(Path.FillType.EVEN_ODD);
        path1.addCircle(0,0,200, Path.Direction.CW);
        path2.addRect(0,-200,200,200, Path.Direction.CW);
        path3.addCircle(0,-100,100, Path.Direction.CW);
        path4.addCircle(0,100,100, Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);

        path1.addCircle(0,-100,25, Path.Direction.CW);
        path1.addCircle(0,100,25, Path.Direction.CW);

        canvas.drawPath(path1,paint);

        paint.setStyle(Paint.Style.STROKE);
        path1.addCircle(0,0,200, Path.Direction.CW);
        canvas.drawPath(path1,paint);

        if (valueAnimator==null){
            valueAnimator = ValueAnimator.ofFloat(0,360);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mDegree= (float) animation.getAnimatedValue();
                    invalidate();
                }
            });

            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatCount(-1);
            valueAnimator.setDuration(3*1000);
            valueAnimator.start();
        }




    }
}
