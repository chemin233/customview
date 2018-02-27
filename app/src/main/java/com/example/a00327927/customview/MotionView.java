package com.example.a00327927.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chemin on 2018/1/31 16:25.
 * description：
 */

public class MotionView extends View {
    private final static String TAG="MotionView";

    public MotionView(Context context) {
        super(context);
    }

    public MotionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MotionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG,"dispatchTouchEvent---view");

        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent---view");
        return super.onTouchEvent(event);
    }
}
