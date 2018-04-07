package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by chemin on 2018/4/6 10:34.
 * 先绘制出菜单按钮
 * 转换点击坐标
 * 响应点击事件
 */

public class RemoteControlMenu extends View {

    private Paint mMPaint;
    private Region upRegion,downRegion,leftRegion,rightRegion;
    private Matrix mMenuMatrix;
    private int mCenterX,mCenterY;

    public RemoteControlMenu(Context context) {
        this(context,null);
    }

    public RemoteControlMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RemoteControlMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mMPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMPaint.setStyle(Paint.Style.FILL);
        mMPaint.setColor(Color.GRAY);

        upRegion=new Region();
        downRegion=new Region();
        leftRegion=new Region();
        rightRegion=new Region();

        mMenuMatrix=new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuMatrix.reset();
        mCenterX=w/2;
        mCenterY=h/2;
        //开始设置path

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动画布
        canvas.translate(mCenterX,mCenterY);

    }
}
