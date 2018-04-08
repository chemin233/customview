package com.example.a00327927.animator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a00327927.customview.R;

public class AnimatorMenuActivity extends AppCompatActivity {

    private AnimatorSetDemo animatorSetDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_menu);
        animatorSetDemo = (AnimatorSetDemo) findViewById(R.id.animator_set);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        animatorSetDemo.recyclerResource();
    }
}
