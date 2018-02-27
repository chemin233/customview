package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by chemin on 2018/1/12 11:40.
 * description：
 */

public class DrawCircleView extends View {

    private Paint mPaint;
    private int x;
    private int y;
    private int r=200;

    public DrawCircleView(Context context) {
        super(context);
        init();
    }


    public DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF=new RectF(0,0,2*r,2*r);
        canvas.drawArc(rectF,0,50,true,mPaint);

        mPaint.setColor(Color.RED);
        RectF rectF1=new RectF(0,0,2*r,2*r);
        canvas.drawArc(rectF1,50,130,true,mPaint);

        mPaint.setColor(Color.BLUE);

        RectF rectF2=new RectF(0,0,2*r,2*r);
        canvas.drawArc(rectF2,180,60,true,mPaint);

        mPaint.setColor(Color.GREEN);

        RectF rectF3=new RectF(0,0,2*r,2*r);
        canvas.drawArc(rectF3,240,120,true,mPaint);
    }
}
