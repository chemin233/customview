package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chemin on 2018/1/15 15:06.
 * description：
 */

public class DrawBitmap extends View {

    private Paint mPaint;

    public DrawBitmap(Context context) {
        super(context);
        init();
    }



    public DrawBitmap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.haerbin);
        canvas.drawBitmap(bitmap,new Rect(20,20,bitmap.getWidth(),bitmap.getHeight()),new Rect(20,20,400,100),mPaint);
    }
}
