package com.example.a00327927.animator;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00327927.customview.MyTextview;
import com.example.a00327927.customview.R;

public class PropertyValuesHolderActivity extends AppCompatActivity {

    private ImageView ivShow;
    private Button button;
    private MyTextview tvShowChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_values_holder);

        button = (Button) findViewById(R.id.bt_start_animator);
        ivShow = (ImageView) findViewById(R.id.iv_phone);
        tvShowChar= (MyTextview) findViewById(R.id.tv_show_char);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doAnimator();
                changeText();
            }
        });
    }

    private void changeText() {
        PropertyValuesHolder propertyValuesHolder=PropertyValuesHolder.ofObject("charContent",new TextEvaluator(),'a','z');
        ObjectAnimator objectAnimator=ObjectAnimator.ofPropertyValuesHolder(tvShowChar,propertyValuesHolder);
        objectAnimator.setDuration(3*1000);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();
    }


    @SuppressLint("ObjectAnimatorBinding")
    private void doAnimator() {

        Keyframe scaleKeyfram=Keyframe.ofFloat(0,1f);
        Keyframe scaleKeyfram1=Keyframe.ofFloat(0.1f,1.1f);
        Keyframe scaleKeyfram2=Keyframe.ofFloat(0.2f,1.1f);
        Keyframe scaleKeyfram3=Keyframe.ofFloat(0.3f,1.2f);
        Keyframe scaleKeyfram4=Keyframe.ofFloat(0.4f,1.2f);
        Keyframe scaleKeyfram5=Keyframe.ofFloat(0.5f,1.3f);
        Keyframe scaleKeyfram6=Keyframe.ofFloat(0.6f,1.2f);
        Keyframe scaleKeyfram7=Keyframe.ofFloat(0.7f,1.2f);
        Keyframe scaleKeyfram8=Keyframe.ofFloat(0.8f,1.1f);
        Keyframe scaleKeyfram9=Keyframe.ofFloat(0.9f,1.1f);
        Keyframe scaleKeyfram0=Keyframe.ofFloat(1,1f);
        PropertyValuesHolder propertyValuesHolder1=PropertyValuesHolder.ofKeyframe("scaleX",scaleKeyfram,scaleKeyfram1,scaleKeyfram2,scaleKeyfram3,scaleKeyfram4,scaleKeyfram5,scaleKeyfram6,scaleKeyfram7,scaleKeyfram8,scaleKeyfram9,scaleKeyfram0);

        Keyframe scfram1=Keyframe.ofFloat(0,1f);
        Keyframe scfram2=Keyframe.ofFloat(0.1f,1.1f);
        Keyframe scfram3=Keyframe.ofFloat(0.2f,1.1f);
        Keyframe scfram4=Keyframe.ofFloat(0.3f,1.2f);
        Keyframe scfram5=Keyframe.ofFloat(0.4f,1.2f);
        Keyframe scfram6=Keyframe.ofFloat(0.5f,1.3f);
        Keyframe scfram7=Keyframe.ofFloat(0.6f,1.2f);
        Keyframe scfram8=Keyframe.ofFloat(0.7f,1.2f);
        Keyframe scfram9=Keyframe.ofFloat(0.8f,1.1f);
        Keyframe scfram0=Keyframe.ofFloat(0.9f,1.1f);
        Keyframe scfram=Keyframe.ofFloat(1,1f);
        PropertyValuesHolder propertyValuesHolder2=PropertyValuesHolder.ofKeyframe("scaleY",scfram1,scfram2,scfram3,scfram4,scfram5,scfram6,scfram7,scfram8,scfram9,scfram0,scfram);

        Keyframe rotate=Keyframe.ofFloat(0,0);
        Keyframe rotate1=Keyframe.ofFloat(0.1f,20);
        Keyframe rotate2=Keyframe.ofFloat(0.2f,-20);
        Keyframe rotate3=Keyframe.ofFloat(0.3f,20);
        Keyframe rotate4=Keyframe.ofFloat(0.4f,-20);
        Keyframe rotate5=Keyframe.ofFloat(0.5f,20);
        Keyframe rotate6=Keyframe.ofFloat(0.6f,-20);
        Keyframe rotate7=Keyframe.ofFloat(0.7f,20);
        Keyframe rotate8=Keyframe.ofFloat(0.8f,-20);
        Keyframe rotate9=Keyframe.ofFloat(0.9f,20);
        Keyframe rotate10=Keyframe.ofFloat(1,0);
        PropertyValuesHolder propertyValuesHolder3=PropertyValuesHolder.ofKeyframe("rotation",rotate,rotate1,rotate2,rotate3,rotate4,rotate5,rotate6,rotate7,rotate8,rotate9,rotate10);

        ObjectAnimator objectAnimator=ObjectAnimator.ofPropertyValuesHolder(ivShow,propertyValuesHolder1,propertyValuesHolder2,propertyValuesHolder3);
        objectAnimator.setDuration(3*1000);
//        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
}
