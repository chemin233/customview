package com.example.a00327927.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a00327927.customview.R;
import com.example.a00327927.fragments.ControlMenuFrag;

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
        }

        FragmentManager fm=getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fm_container,fragment);
        fragmentTransaction.commit();
    }
}
