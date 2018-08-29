package com.example.a00327927.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a00327927.animator.AnimatorMenuActivity;
import com.example.a00327927.animator.PropertyValuesHolderActivity;
import com.example.a00327927.animator.ResilientBallActivity;
import com.example.a00327927.animator.Rotate3DAnimationActivity;
import com.example.a00327927.customview.Bezier2Activity;
import com.example.a00327927.customview.Bezier3Activity;
import com.example.a00327927.customview.CircleViewActivity;
import com.example.a00327927.customview.CustomPaintActivity;
import com.example.a00327927.customview.CustomeRectActivity;
import com.example.a00327927.customview.PathActivity;
import com.example.a00327927.customview.PathMeasureActivity;
import com.example.a00327927.customview.R;
import com.example.a00327927.customview.RegionActivity;
import com.example.a00327927.customview.TestViewActivity;
import com.example.a00327927.matrix.MatrixActivity;

public class MainActivity extends AppCompatActivity {

    private Button tv1,tv2,tv3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;

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
        btn10= (Button) findViewById(R.id.custom_bt_rotation);
        btn11= (Button) findViewById(R.id.custom_bt_matrix);
        btn12= (Button) findViewById(R.id.custom_bt_keyfram);
        btn13= (Button) findViewById(R.id.custom_bt_animator_menu);
        btn14= (Button) findViewById(R.id.custom_bt_region);
        btn15= (Button) findViewById(R.id.custom_bt_control_menu);
        btn16= (Button) findViewById(R.id.custom_bt_shopcar);
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

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Rotate3DAnimationActivity.class));
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MatrixActivity.class));
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PropertyValuesHolderActivity.class));
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnimatorMenuActivity.class));
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegionActivity.class));
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PorductionListActivity.class));
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShopCarActvity.class));
            }
        });
    }


}
