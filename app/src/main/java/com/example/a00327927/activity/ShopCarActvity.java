package com.example.a00327927.activity;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.a00327927.animator.AddToShopCarAnimView;
import com.example.a00327927.customview.R;

public class ShopCarActvity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car_actvity);



    }

    public void onClick(View view) {
        final AddToShopCarAnimView mAddToShopCarAnimView = new AddToShopCarAnimView(this);
        final RelativeLayout rlContainer= (RelativeLayout) findViewById(R.id.rl_container);
        rlContainer.addView(mAddToShopCarAnimView);

        mAddToShopCarAnimView.initPoint(new Point(0,0),new Point(600,600));
        mAddToShopCarAnimView.setAddToShopCarListener(new AddToShopCarAnimView.ShopCarListener() {
            @Override
            public void addToShopCarEnd() {
                if (rlContainer!=null) {
                    rlContainer.removeViewInLayout(mAddToShopCarAnimView);
                }
            }
        });
//        mAddToShopCarView.startImgAnimation();
    }
}
