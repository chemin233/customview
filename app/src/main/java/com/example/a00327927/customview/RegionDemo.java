package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chemin on 2018/4/4 17:03.
 * description：
 */

public class RegionDemo extends View {

    private Paint mPaint;
    private int width,height;
    private Region mCircleRegion;
    private Path mCirclePath;

    public RegionDemo(Context context) {
        this(context,null);
    }

    public RegionDemo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RegionDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);
        mCircleRegion = new Region();

        mCirclePath = new Path();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w/2;
        height=h/2;
        mCirclePath.addCircle(width,height,200, Path.Direction.CCW);
        Region globalRegion=new Region(-w,-h,w,h);//所有边界设置为视图大小
        mCircleRegion.setPath(mCirclePath,globalRegion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path=new Path(mCirclePath);//这里把path设置成局部变量,方便GC对canvas的回收
        canvas.drawPath(path,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                int x= (int) event.getX();
                int y= (int) event.getY();
                if (mCircleRegion.contains(x,y)){
                    Toast.makeText(this.getContext(),"检测到了点击",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
