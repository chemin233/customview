package com.example.a00327927.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a00327927.animator.ResilientBallActivity;

public class MainActivity extends AppCompatActivity {

    private Button tv1,tv2,tv3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        tv1= (Button) findViewById(R.id.custom_tv_canvas);
        tv2= (Button) findViewById(R.id.custom_tv_bezier2);
        tv3= (Button) findViewById(R.id.custom_tv_bezier3);
        btn4= (Button) findViewById(R.id.custom_bt_circleview);
        btn5= (Button) findViewById(R.id.custom_bt_circle_by_bezier);
        btn6= (Button) findViewById(R.id.custom_bt_pathview);
        btn7= (Button) findViewById(R.id.custom_bt_pathmeasure);
        btn8= (Button) findViewById(R.id.custom_bt_resilient);
        btn9= (Button) findViewById(R.id.custom_bt_custom_paint);
    }
    private void initData() {

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestViewActivity.class));
            }
        });



        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CustomeRectActivity.class));
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Bezier2Activity.class));

            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Bezier3Activity.class));

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CircleViewActivity.class));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PathActivity.class));
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PathMeasureActivity.class));
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ResilientBallActivity.class));
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CustomPaintActivity.class));
            }
        });
    }


}
