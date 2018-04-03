package com.example.a00327927.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.a00327927.customview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by chemin on 2018/3/21 16:27.
 * description：
 */

public class AnimatorSetDemo extends View {

    private int mCenterX, mCenterY, mWidth, mHeight;
    private float radius;//伸缩半径
    private int picWidth = 60;//图片宽
    private int picHeight = 60;//图片宽
    private Paint mPaint;
    private Bitmap mBmWeibo;
    private Bitmap mBmWeixin;
    private Bitmap mBmQq;
    private Bitmap mBmZhifubao;
    private Bitmap mBmGit;
    private Bitmap mBmMenu;
    private List<Bitmap> mBitmaps;
    private boolean isKeepAway = false;
    private float mStartLengh;
    private float mStartHeight;
    private float mEndValue;
    private double degree;
    private final String TAG = AnimatorSetDemo.class.getSimpleName();
    private boolean isFirstDraw = true;

    public AnimatorSetDemo(Context context) {
        this(context, null);
    }

    public AnimatorSetDemo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorSetDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        //初始化图片
        mBitmaps = new ArrayList<>();
        mBmWeibo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_weibo);
        mBmWeixin = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_weixin);
        mBmQq = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_qq);
        mBmZhifubao = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_zhifubao);
        mBmGit = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_github);
        mBmMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu);
        mBitmaps.add(mBmWeibo);
        mBitmaps.add(mBmWeixin);
        mBitmaps.add(mBmQq);
        mBitmaps.add(mBmZhifubao);
        mBitmaps.add(mBmGit);
        mBitmaps.add(mBmMenu);

        picWidth = mBmMenu.getWidth();
        picHeight = mBmMenu.getHeight();
        //获取屏幕框
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mCenterX = dm.heightPixels / 2;
        mCenterY = dm.widthPixels / 2;
        Log.e("cm", "x:" + mCenterX + ",y:" + mCenterY);
        radius = ((float) mCenterX * 2.0f) / 4.0f;
        Log.e("cm", "radius:" + radius);
        //计算夹角的弧度
        degree = Math.PI / 2 / (mBitmaps.size()-2);
        Log.i(TAG, "degree---" + degree);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int currWidth = 0;
        int currHeight = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                currWidth = (int) (radius + picWidth);
                break;
            case MeasureSpec.EXACTLY:
                currWidth = width;
                break;
            case MeasureSpec.UNSPECIFIED:
                currWidth = width;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                currHeight = (int) (radius + picWidth);
                break;
            case MeasureSpec.EXACTLY:
                currHeight = height;
                break;
            case MeasureSpec.UNSPECIFIED:
                currHeight = height;
                break;
        }
        setMeasuredDimension(currWidth, currHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mEndValue = mWidth - picWidth;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double rad = 0;
        canvas.translate(radius,radius);
        if (isFirstDraw) {
            isFirstDraw = false;
            for (int i = 0; i < mBitmaps.size() - 1; i++) {
                canvas.drawBitmap(mBitmaps.get(i), 0, 0, mPaint);
            }
        } else {
            for (int i = 0; i < mBitmaps.size() - 1; i++) {
                int x =-(int) (mStartLengh * Math.cos(rad));
                int y =-(int) (mStartLengh * Math.sin(rad));
                canvas.drawBitmap(mBitmaps.get(i), x, y, mPaint);
                rad += degree;
                Log.e(TAG, "rad--" + rad + ",x--" + x + ",y--" + y);
            }
        }
        canvas.drawBitmap(mBitmaps.get(mBitmaps.size() - 1),0, 0, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                isKeepAway = !isKeepAway;
                doAnimator(mStartLengh, mEndValue);
                Log.e("cm", "开始actionDown---->");
                break;
        }
        return true;
    }

    private void doAnimator(float startValue, float endValue) {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startValue, endValue);
        Log.e("cm", "start---" + mStartLengh + ",end--" + radius);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStartLengh = (float) animation.getAnimatedValue();
                invalidate();
                Log.e("cm", "invalidate---->" + mStartLengh);

            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isKeepAway) {
                    mStartLengh = radius;
                    mEndValue = 0;
                } else {
                    mStartLengh = 0;
                    mEndValue = radius;
                }
            }
        });
        valueAnimator.start();
    }

}
