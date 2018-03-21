package com.example.a00327927.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by chemin on 2018/3/21 09:56.
 * description：
 */

public class MyTextview extends android.support.v7.widget.AppCompatTextView {

    public MyTextview(Context context) {
        this(context,null);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setCharContent(Character charContent){
        setText(String.valueOf(charContent));
    }
}
