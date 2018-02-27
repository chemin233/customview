package com.example.a00327927.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


/**
 * Created by chemin on 2018/1/31 16:22.
 * description：
 */

public class MotionGroupB extends LinearLayout {

    private final static String TAG="MotionGroupB";
    public MotionGroupB(Context context) {
        super(context);
    }

    public MotionGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MotionGroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"dispatchTouchEvent---B");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onInterceptTouchEvent---B");

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent---B");
        return super.onTouchEvent(event);
    }
}
