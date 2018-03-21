package com.example.a00327927.matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by chemin on 2018/3/8 09:53.
 * description：
 */

public class MatrixDemo extends View {

    private Paint mPaint;
    private float[] dst;
    private float[] dst1;

    public MatrixDemo(Context context) {
        this(context,null);
    }

    public MatrixDemo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MatrixDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        dst = new float[9];
        dst1= new float[9];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Matrix matrix=new Matrix();
        Log.e("cm","src---"+ matrix.toString());


        matrix.preScale(2,2);
        matrix.postTranslate(200,200);
        matrix.postScale(2f,2f);

        canvas.setMatrix(matrix);
        Log.e("cm","dst======"+ matrix.toString());
        canvas.drawRect(0,0,100,100,mPaint);

    }

}
