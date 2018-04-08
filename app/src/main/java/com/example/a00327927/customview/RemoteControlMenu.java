package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by chemin on 2018/4/6 10:34.
 * 先绘制出菜单按钮
 *  利用path绘制,并将path设置到region中
 * 转换点击坐标
 * 判断点击位置所在的区域
 * 响应点击事件
 *------遇到的问题-------
 * 在关闭硬件加速的情况下,布局里面使用margin或者padding都是使getRawX坐标计算不准确!
 * 这个时候需要使用getX/getY
 */

public class RemoteControlMenu extends View {

    private Paint mMPaint;
    private Region belowRegion, upRegion, leftRegion, rightRegion, minCircleRegion;
    private Matrix mMenuMatrix;
    private int mCenterX, mCenterY;
    private Path mMinCirclePath;
    private Path mBelowPath;
    private Path mUpPath;
    private Path mLeftPath;
    private Path mRightPath;
    private final static String TAG = "RemoteControlMenu";
    private boolean isPress;
    private int pressId;
    private ControlMenuListener mListener;
    private int mPressColor;
    private int mDefColor;

    public RemoteControlMenu(Context context) {
        this(context, null);
    }

    public RemoteControlMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemoteControlMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);//关闭硬件加速
        init();
    }


    private void init() {
        mPressColor = getResources().getColor(R.color.colorAccent);
        mDefColor =getResources().getColor(R.color.color9206ef);
        mMPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMPaint.setStyle(Paint.Style.FILL);
        mMPaint.setColor(mDefColor);

        belowRegion = new Region();
        upRegion = new Region();
        leftRegion = new Region();
        rightRegion = new Region();
        minCircleRegion = new Region();

        mMenuMatrix = new Matrix();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuMatrix.reset();
        mCenterX = w / 2;
        mCenterY = h / 2;
        //开始设置path
        float bigSweepAngle = 85;
        float smallSweepAngle = 80;
        //1.确定大圆的矩形
        int width = (int) (w * 0.8);
        RectF bigRect = new RectF(-width / 2, -width / 2, width / 2, width / 2);

        //2.确定内圆的矩形
        int minWidth = width / 2;
        RectF minRect = new RectF(-minWidth / 2, -minWidth / 2, minWidth / 2, minWidth / 2);
        //3.确定裁切范围
        Region gloableRegion = new Region();
        gloableRegion.set(-w, -h, w, h);


        mMinCirclePath = new Path();
        mMinCirclePath.addCircle(0, 0, (float) (minWidth / 2.5), Path.Direction.CCW);
        minCircleRegion.setPath(mMinCirclePath, gloableRegion);

        //4.设置上下左右的path,并设置到region中
        mBelowPath = new Path();
        mBelowPath.addArc(bigRect, 50, bigSweepAngle);
        mBelowPath.arcTo(minRect, 130, -smallSweepAngle, false);
        mBelowPath.close();
        belowRegion.setPath(mBelowPath, gloableRegion);

        mUpPath = new Path();
        mUpPath.addArc(bigRect, 230, bigSweepAngle);
        mUpPath.arcTo(minRect, 310, -smallSweepAngle, false);
        mUpPath.close();
        upRegion.setPath(mUpPath, gloableRegion);

        mLeftPath = new Path();
        mLeftPath.addArc(bigRect, 140, bigSweepAngle);
        mLeftPath.arcTo(minRect, 220, -smallSweepAngle, false);
        mLeftPath.close();
        leftRegion.setPath(mLeftPath, gloableRegion);

        mRightPath = new Path();
        mRightPath.addArc(bigRect, 320, bigSweepAngle);
        mRightPath.arcTo(minRect, 40, -smallSweepAngle, false);
        mRightPath.close();
        rightRegion.setPath(mRightPath, gloableRegion);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw====================>"+canvas.isHardwareAccelerated());
        //移动画布
        canvas.translate(mCenterX, mCenterY);
        if (mMenuMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMenuMatrix);
        }

        canvas.drawPath(mUpPath, mMPaint);
        canvas.drawPath(mBelowPath, mMPaint);
        canvas.drawPath(mLeftPath, mMPaint);
        canvas.drawPath(mRightPath, mMPaint);
        canvas.drawPath(mMinCirclePath, mMPaint);

        if (isPress){
            mMPaint.setColor(mPressColor);
//            Log.e(TAG,"press-----changed-------->");
            switch (pressId){
                case 1:
                    canvas.drawPath(mBelowPath,mMPaint);
                    break;
                case 2:
                    canvas.drawPath(mUpPath,mMPaint);
                    break;
                case 3:
                    canvas.drawPath(mLeftPath,mMPaint);
                    break;
                case 4:
                    canvas.drawPath(mRightPath,mMPaint);
                    break;
                case 5:
                    canvas.drawPath(mMinCirclePath,mMPaint);
                    break;
            }
            mMPaint.setColor(mDefColor);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float rawX = event.getX();
        float rawY = event.getY();
        float[] point = new float[2];
        point[0] = rawX;
        point[1] = rawY;
        mMenuMatrix.mapPoints(point);
        //将原始坐标转换成画布坐标
        int mapX = (int) point[0];
        int mapY = (int) point[1];

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
//                Log.e(TAG,"action_down---->");
                isPress = true;
                checkClickArea(mapX, mapY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
//                Log.e(TAG,"action_up---->");
                isPress = false;
                pressId=0;
                invalidate();
                break;
        }
        return true;
    }

    private void checkClickArea(int x, int y) {
        if (belowRegion.contains(x, y)) {
            //设置点击Id及回调
            pressId = 1;
            if (mListener!=null){
                mListener.clickBelow();
            }
        } else if (upRegion.contains(x, y)) {
            pressId = 2;
            if (mListener!=null){
                mListener.clickUp();
            }
        } else if (leftRegion.contains(x, y)) {
            pressId = 3;
            if (mListener!=null){
                mListener.clickLeft();
            }
        } else if (rightRegion.contains(x, y)) {
            pressId = 4;
            if (mListener!=null){
                mListener.clickRight();
            }
        } else if (minCircleRegion.contains(x, y)) {
            pressId = 5;
            if (mListener!=null){
                mListener.clickCenter();
            }
        }

    }

    public void setListener(ControlMenuListener listener){
        this.mListener=listener;
    }

    public void setDefColor(int color){
        this.mDefColor=color;
    }

    public void setPressColor(int color){
        this.mPressColor=color;
    }


    public interface ControlMenuListener{
        void clickCenter();
        void clickUp();
        void clickBelow();
        void clickLeft();
        void clickRight();
    }
}
