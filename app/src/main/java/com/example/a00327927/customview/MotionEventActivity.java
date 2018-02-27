package com.example.a00327927.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MotionEventActivity extends AppCompatActivity {

    private final static String TAG="MotionEventActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"dispatchTouchEvent---activity");

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent---activity");
        return super.onTouchEvent(event);
    }

    public void montionGroupBClick(View view) {
        Log.e("activity","-------montionGroupBClick-----B");
    }

    public void montionGroupAClick(View view) {
        Log.e("activity","-------montionGroupAClick-----A");

    }
}
