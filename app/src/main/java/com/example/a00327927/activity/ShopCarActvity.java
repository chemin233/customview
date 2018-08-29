package com.example.a00327927.activity;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.a00327927.animator.AddToShopCarView;
import com.example.a00327927.customview.R;

public class ShopCarActvity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car_actvity);



    }

    public void onClick(View view) {
        final AddToShopCarView mAddToShopCarView = new AddToShopCarView(this);
        final RelativeLayout rlContainer= (RelativeLayout) findViewById(R.id.rl_container);
        rlContainer.addView(mAddToShopCarView);

        mAddToShopCarView.initPoint(new Point(0,0),new Point(600,600));
        mAddToShopCarView.setAddToShopCarListener(new AddToShopCarView.ShopCarListener() {
            @Override
            public void addToShopCarEnd() {
                if (rlContainer!=null) {
                    rlContainer.removeViewInLayout(mAddToShopCarView);
                }
            }
        });
//        mAddToShopCarView.startImgAnimation();
    }
}
