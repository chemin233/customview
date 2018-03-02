package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chemin on 2018/3/2 16:20.
 * description：自定义绘画板
 */

public class CustomPaint extends View {

    private Paint mPaint;
    private Path mPath;
    private float preX;
    private float preY;
    private float endX;
    private float endY;

    public CustomPaint(Context context) {
        this(context,null);
    }

    public CustomPaint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                preX = event.getX();
                preY = event.getY();
                mPath.moveTo(preX, preY);

                return true;
            case MotionEvent.ACTION_MOVE:
                endX = (preX+ event.getX())/2;
                endY = (preY+ event.getY())/2;
                mPath.quadTo(preX,preY,endX,endY);
                preX = event.getX();
                preY = event.getY();

                invalidate();
                break;
            default:
        }

        return super.onTouchEvent(event);
    }

    public void resetPath(){
        mPath.reset();
        invalidate();
    }
}
