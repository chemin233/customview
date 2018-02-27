package com.example.a00327927.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chemin on 2018/1/10 16:28.
 * description：
 */

public class PathView extends View {

    private Paint mPaint;
    private Path path;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathView(Context context) {
        super(context);
        initPath();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPath();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPath();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initPath(){
        mPaint = new Paint();
        path = new Path();

        // 使用 path 对图形进行描述
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,mPaint);
    }
}
