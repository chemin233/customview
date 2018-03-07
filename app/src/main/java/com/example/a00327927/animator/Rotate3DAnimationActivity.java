package com.example.a00327927.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.a00327927.customview.R;

public class Rotate3DAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate3_danimation);
        ImageView imageView= (ImageView) findViewById(R.id.iv_show);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float centerX=v.getWidth()/2;
                float centerY=v.getHeight()/2;

                Rotate3DAnimation rotate3DAnimation=new Rotate3DAnimation(Rotate3DAnimationActivity.this,0,360,centerX,centerY,0,true);
                rotate3DAnimation.setDuration(4000);
                rotate3DAnimation.setFillAfter(true);
                rotate3DAnimation.setRepeatCount(3);
                rotate3DAnimation.setInterpolator(new LinearInterpolator());
                v.startAnimation(rotate3DAnimation);
            }
        });

    }
}
