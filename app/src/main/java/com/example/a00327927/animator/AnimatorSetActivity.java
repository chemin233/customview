package com.example.a00327927.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.a00327927.customview.R;

/**
 * Created by chemin on 2018/3/21 11:31.
 * description：
 */

public class AnimatorSetActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        Button button= (Button) findViewById(R.id.btn_animatorset);
        AnimatorSet animatorSet=new AnimatorSet();
//        AnimatorSet.Builder builder = animatorSet.play();
//        ObjectAnimator objectAnimator=ObjectAnimator.ofInt();
//        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

    }
}
