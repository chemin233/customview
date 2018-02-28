package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chemin on 2018/2/28 16:26.
 * description：自定义绘制雷达图
 */

public class RadarView extends View {

    private Paint mPaint;
    private int side=5;
    private float radius=40;//雷达图间隔
    private int centerX;
    private Path mPath;
    private float angle;
    private int centerY;
    private int count=5;//绘制的环数

    public RadarView(Context context) {
        this(context,null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath=new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        radius =Math.min(w,h)/2*0.9f;
        centerX =w/2;
        centerY =h/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRadar(canvas);//绘制多边形
    }

    private void drawRadar(Canvas canvas) {

        angle = (float) (2*Math.PI/side);
        for (int i = 0; i < side; i++) {
            float currentR=i*radius;
            Log.e("cm","currentR--"+currentR+",angle--"+angle+",radius---"+radius);

            mPath.reset();
            for (int j=0;j<count;j++){
                if (j==0){
                    mPath.moveTo(centerX +currentR, centerY);
                    Log.e("cm","centerX--"+centerX+",centerY--"+centerY);
                }else {
                    float x= (float) (centerX+currentR*Math.cos(angle*j));
                    float y= (float) (centerY+currentR*Math.sin(angle*j));
                    Log.e("cm","x--"+x+",y--"+y);

                    mPath.lineTo(x,y);
                }
            }
            mPath.close();
            canvas.drawPath(mPath,mPaint);
        }

    }


}
