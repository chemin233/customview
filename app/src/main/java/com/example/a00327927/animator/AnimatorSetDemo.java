package com.example.a00327927.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chemin on 2018/3/21 16:27.
 * description：
 */

public class AnimatorSetDemo extends View{

    private int mCenterX, mCenterY;
    private int radius;//伸缩半径
    private int picWidth=60;//图片宽
    private Paint mPaint;

    public AnimatorSetDemo(Context context) {
        this(context,null);
    }

    public AnimatorSetDemo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimatorSetDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int currWidth=0;
        int currHeight=0;
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode){
            case MeasureSpec.AT_MOST:
                currWidth=width;
                break;
            case MeasureSpec.EXACTLY:
                currWidth=width;
                break;
            case MeasureSpec.UNSPECIFIED:
                currWidth=width;
                break;
        }
        setMeasuredDimension(currWidth,currHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX =w/2;
        mCenterY =h/2;
        radius=w/3;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
