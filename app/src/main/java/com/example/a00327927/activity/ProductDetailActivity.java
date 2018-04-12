package com.example.a00327927.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a00327927.customview.R;
import com.example.a00327927.fragments.ControlMenuFrag;
import com.example.a00327927.fragments.CustomPopMenuFrag;
import com.example.a00327927.fragments.GradeProgressFragment;
import com.example.a00327927.fragments.WaveFragment;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        String content=intent.getStringExtra("content");
        Fragment fragment=null;
        if (content.equals("自定义菜单按钮")){
             fragment=new ControlMenuFrag();
        }else if (content.equalsIgnoreCase("自定义弹出菜单")){
            fragment=new CustomPopMenuFrag();
        }else if (content.equalsIgnoreCase("水波纹效果")){
            fragment=new WaveFragment();
        }else if (content.equalsIgnoreCase("自定义等级进度条")){
            fragment=new GradeProgressFragment();
        }

        FragmentManager fm=getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fm_container,fragment);
        fragmentTransaction.commit();
    }
}
