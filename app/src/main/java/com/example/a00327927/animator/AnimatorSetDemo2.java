package com.example.a00327927.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Toast;

import com.example.a00327927.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chemin on 2018/3/21 16:27.
 * description：
 *
 * ----------遇到的问题-----------------
 * 在开启硬件加速的时候或者没开启加速
 * viewGroup使用layout_alignParentBottom属性的时候,
 * 使用canvas.getMatrix().invert() 方式得到的逆矩阵
 * 然后转换getRawX坐标后不正确
 * 采用getX()然后自己计算坐标解决了问题或者使用getX(),使用逆矩阵也可以!
 */

public class AnimatorSetDemo2 extends View {

    private int mCenterX, mCenterY;
    private float radius;//伸缩半径
    private int picWidth = 60;//图片宽
    private int picHeight = 60;//图片宽
    private Paint mPaint;
    private Bitmap mBmWeibo;
    private Bitmap mBmWeixin;
    private Bitmap mBmQq;
    private Bitmap mBmZhifubao;
    private Bitmap mBmGit;
    private Bitmap mBmMenu;
    private List<Bitmap> mBitmaps;
    private boolean isKeepAway = false;//是否是远离菜单按钮
    private float mStartLengh;
    private float mEndValue;
    private double degree;
    private final String TAG = AnimatorSetDemo2.class.getSimpleName();
    private boolean isFirstDraw = true;
    private float mFraction;
    private int maxWidth;//自定义view最大宽度
    private ValueAnimator mValueAnimator;
    private List<Region> bitmapRegions;
    private Matrix mMenuMatrix;
    private Context mContext;
    private OnMenuClickListener mListener;

    public AnimatorSetDemo2(Context context) {
        this(context, null);
    }

    public AnimatorSetDemo2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorSetDemo2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setLayerType(View.LAYER_TYPE_SOFTWARE,null);//关闭硬件加速,否则event.getRowX 的值不正确
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        //初始化图片
        mBitmaps = new ArrayList<>();
        mBmWeibo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_weibo);
        mBmWeixin = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_weixin);
        mBmQq = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_qq);
        mBmZhifubao = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_zhifubao);
        mBmGit = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_github);
        mBmMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu);
        mBitmaps.add(mBmWeibo);
        mBitmaps.add(mBmWeixin);
        mBitmaps.add(mBmQq);
        mBitmaps.add(mBmZhifubao);
        mBitmaps.add(mBmGit);
        mBitmaps.add(mBmMenu);

        picWidth = mBmMenu.getWidth();
        picHeight = mBmMenu.getHeight();
        //获取屏幕框
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mCenterX = dm.heightPixels / 2;
        mCenterY = dm.widthPixels / 2;
        Log.e("cm", "x:" + mCenterX + ",y:" + mCenterY);
        radius = ((float) mCenterX * 2.0f) / 4.0f;
        Log.e("cm", "radius:" + radius);
        //计算夹角的弧度
        degree = Math.PI / 2 / (mBitmaps.size() - 2);
        Log.i(TAG, "degree---" + degree);

        //初始化位移最大距离
        mEndValue = radius;
        //初始化最大边距
        maxWidth = picWidth;
        //初始化点击区域的list
        bitmapRegions = new ArrayList<>();
        mMenuMatrix = new Matrix();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int currWidth = 0;
        int currHeight = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                currWidth = maxWidth;
                break;
            case MeasureSpec.EXACTLY:
                currWidth = width;
                break;
            case MeasureSpec.UNSPECIFIED:
                currWidth = width;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                currHeight = maxWidth;
                break;
            case MeasureSpec.EXACTLY:
                currHeight = height;
                break;
            case MeasureSpec.UNSPECIFIED:
                currHeight = height;
                break;
        }
        setMeasuredDimension(currWidth, currHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e(TAG,"是否硬件加速:"+canvas.isHardwareAccelerated());
        if (mBitmaps != null && mBitmaps.size() > 1) {
            double rad = 0;
            if (isFirstDraw) {//第一次绘制
//            canvas.translate(picWidth,picWidth);
                isFirstDraw = false;
                mPaint.setAlpha(0);//先隐藏除菜单图片外的所有图片
                for (int i = 0; i < mBitmaps.size() - 1; i++) {//绘制每个图片
                    canvas.drawBitmap(mBitmaps.get(i), 0, 0, mPaint);
                }
                mPaint.setAlpha(255);
                //添加菜单按钮的区域
                bitmapRegions.add(new Region(0,0,picHeight,picHeight));
                if (mMenuMatrix.isIdentity()){
                    //获取当前矩阵对应的逆矩阵
                    canvas.getMatrix().invert(mMenuMatrix);
                }
                canvas.drawBitmap(mBitmaps.get(mBitmaps.size() - 1), 0, 0, mPaint);
            } else {
                canvas.translate(maxWidth - picWidth, maxWidth - picWidth);
                if (isKeepAway) {
                    mPaint.setAlpha((int) (255 * mFraction));
                } else {
                    mPaint.setAlpha((int) (255 * (1 - mFraction)));
                }

                for (int i = 0; i < mBitmaps.size() - 1; i++) {
                    int x = -(int) (mStartLengh * Math.cos(rad));
                    int y = -(int) (mStartLengh * Math.sin(rad));
                    canvas.drawBitmap(mBitmaps.get(i), x, y, mPaint);
                    if (maxWidth == radius + picWidth) {//如果图片已经到了指定位置,开始创建对应region
                        if (mMenuMatrix.isIdentity()){
                            //获取当前矩阵对应的逆矩阵
                            canvas.getMatrix().invert(mMenuMatrix);
                        }
//                        Log.e(TAG,"maxWidht-------->");
                        //这里是原始区域
                        Region region = new Region();
                        region.set(x, y, x + picWidth, y + picHeight);
                        bitmapRegions.add(region);
                    }
                    rad += degree;
//                    Log.e(TAG, "rad--" + rad + ",x--" + x + ",y--" + y);
                }
                mPaint.setAlpha(255);
                canvas.drawBitmap(mBitmaps.get(mBitmaps.size() - 1), 0, 0, mPaint);
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断点击区域在哪个位置
                if (bitmapRegions != null && bitmapRegions.size() != 0) {
                    Log.e(TAG,"===============x---->"+event.getX()+",y----->"+event.getY());
                    float[] points = new float[2];
                    points[0] = event.getX();
                    points[1] = event.getY();
                    checkRegion(points);
                }

                break;
        }
        return super.onTouchEvent(event);
    }

    private void checkRegion(float[] points) {
        mMenuMatrix.mapPoints(points);
        Log.e(TAG,"menuMatrix---->"+mMenuMatrix.toShortString());
//        Log.e(TAG, "简单转换后,点击了第X===" +( -maxWidth+(int) points[0] +picWidth)+ "=======>Y===" + (-maxWidth+(int) points[1]+picHeight));
        Log.e(TAG, "简单转换后,点击了第X===" +((int) points[0] )+ "=======>Y===" + ((int) points[1]));
        for (int i = 0; i < bitmapRegions.size(); i++) {
            Rect bounds = bitmapRegions.get(i).getBounds();
            Log.e(TAG, "bounds---->" + bounds.toString());
            if (bitmapRegions.get(i).contains((+(int) points[0]),((int) points[1]))) {
                Toast.makeText(mContext, "点击了第" + (i) + "个图片!", Toast.LENGTH_SHORT).show();
//                Log.i(TAG,"点击了第" + (i) + "个图片!");
                if (mListener!=null){
                    mListener.onClickListener(i);
                }
                bitmapRegions.clear();//清除之前保存的区域
                bitmapRegions.add(new Region(0,0,picHeight,picHeight));//添加菜单按钮
                //开始动画
                if (mValueAnimator == null || !mValueAnimator.isRunning()) {
                    isKeepAway = !isKeepAway;
                    doAnimator(mStartLengh, mEndValue);
                }
            }
        }

    }

    private void doAnimator(float startValue, float endValue) {
        mValueAnimator = ValueAnimator.ofFloat(startValue, endValue);
//        Log.e("cm", "start---" + mStartLengh + ",end--" + radius);
        mValueAnimator.setDuration(800);
        mValueAnimator.setInterpolator(new BounceInterpolator());

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStartLengh = (float) animation.getAnimatedValue();
                mFraction = animation.getAnimatedFraction();
                maxWidth = (int) (mStartLengh + picWidth);
                requestLayout();
            }
        });

        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mMenuMatrix.reset();
                if (isKeepAway) {
                    mStartLengh = radius;
                    mEndValue = 0;
                } else {
                    mStartLengh = 0;
                    mEndValue = radius;
                }
            }
        });
        mValueAnimator.start();
    }

    /**
     * 回收资源
     */
    public void recyclerResource() {
        if (mBitmaps != null && mBitmaps.size() != 0) {
            for (int i = 0; i < mBitmaps.size(); i++) {
                mBitmaps.get(i).recycle();
            }
        }
    }

    public void addClickListener(OnMenuClickListener onMenuClickListener){
        this.mListener=onMenuClickListener;
    }

    interface OnMenuClickListener{
        void onClickListener(int poistion);
    }

}
