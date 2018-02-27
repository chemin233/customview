package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chemin on 2018/1/23 10:47.
 * description：
 */

public class DrawPicture extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Picture mPicture;


    public DrawPicture(Context context) {
        this(context,null);
    }

    public DrawPicture(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawPicture(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        recording();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPicture = new Picture();

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
//        canvas.drawPicture(mPicture);
//        canvas.drawPicture(mPicture,new Rect(0,0,mPicture.getWidth(),200));
//
//        PictureDrawable pictureDrawable=new PictureDrawable(mPicture);
//        pictureDrawable.setBounds(0,0,200,200);
//        pictureDrawable.draw(canvas);

    }

    private void recording() {
        Canvas canvas = mPicture.beginRecording(500, 500);

        canvas.drawCircle(200,200,200,mPaint);
        mPicture.endRecording();
    }
}
