package com.example.a00327927.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.a00327927.customview.R;


/**
 * @author chemin
 * @date 2018/8/29 11:12.
 * description：自定义加入购物车特效
 */
public class AddToShopCarAnimView extends View {

    private Path mPath;
    private Paint mPaint;
    private Point starPoint;
    private Point endPoint;
    private Matrix mMatrix;
    private boolean isFirstDraw=true;
    /**
     * 贝塞尔曲线控制点
     */
    private Point mControlPoint;
    private ValueAnimator mAnimator;
    private long ANIMATOR_DURATION=600;
    private Bitmap mBitmap;
    private ShopCarListener mShopCarListener;

    public AddToShopCarAnimView(Context context) {
        this(context,null);
    }

    public AddToShopCarAnimView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddToShopCarAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);

        mMatrix=new Matrix();
    }

    /**
     * @param startPoint
     * @param endPoint
     * 初始化开始点与结束点
     */
    public void initPoint(Point startPoint,Point endPoint){
        this.starPoint=startPoint;
        this.endPoint=endPoint;
        mControlPoint=new Point(endPoint.x,startPoint.y);
    }

    public void setAddToShopCarListener(ShopCarListener shopCarListener){
        this.mShopCarListener=shopCarListener;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        if (isFirstDraw) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wangzai_milk);
            mPath.quadTo(mControlPoint.x,mControlPoint.y,endPoint.x,endPoint.y);
            final PathMeasure pathMeasure=new PathMeasure(mPath,false);
            mAnimator = ValueAnimator.ofFloat(pathMeasure.getLength());
            mAnimator.setDuration(ANIMATOR_DURATION);
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (mBitmap!=null) {
                        mBitmap.recycle();
                    }
                    if (mShopCarListener!=null) {
                        mShopCarListener.addToShopCarEnd();
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });

            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    //获取当前位置的矩阵信息
                    pathMeasure.getMatrix(animatedValue,mMatrix,PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
                    invalidate();
                }
            });
        }

        mMatrix.preTranslate(mBitmap.getWidth()/2, mBitmap.getHeight()/2);

        float animatedFraction = mAnimator.getAnimatedFraction();
        Log.e("cm","animatedFraction------->"+ animatedFraction);
        if ((animatedFraction<0.7)) {
            mMatrix.preScale(1-animatedFraction,1-animatedFraction);
        }else {
            mMatrix.preScale(0.3f,0.3f);
        }
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);
        if (isFirstDraw) {
            isFirstDraw=false;
            if (mAnimator!=null) {
                mAnimator.start();
            }
        }
    }

    public void startImgAnimation(){
        if (mAnimator!=null) {
            mAnimator.start();
        }
    }

    public interface ShopCarListener{
        void addToShopCarEnd();
    }

}
