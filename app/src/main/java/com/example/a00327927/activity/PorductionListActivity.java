package com.example.a00327927.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.a00327927.customview.R;

import java.util.ArrayList;
import java.util.List;

public class PorductionListActivity extends AppCompatActivity {

    private RecyclerView mRc;
    private RecyclerAdapter mRcAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porduction_list);
        init();
        initData();
        addListener();
    }

    private void init() {

        mRc = (RecyclerView) findViewById(R.id.rc_show);
        mRc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("自定义菜单按钮");
//        mData.add("进阶");
        mRcAdapter = new RecyclerAdapter(this, mData);
        mRc.setAdapter(mRcAdapter);
    }

    private void addListener() {
        mRcAdapter.setItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
                Intent intent = new Intent(PorductionListActivity.this, ProductDetailActivity.class);
                intent.putExtra("content",mData.get(position));
                PorductionListActivity.this.startActivity(intent);
            }
        });

    }
}
