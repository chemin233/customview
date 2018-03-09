package com.example.a00327927.animator;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by chemin on 2018/3/5 15:51.
 * description：
 */

public class Rotate3DAnimation extends Animation {


    private  float mFromDegrees;
    private  float mToDegrees;
    private  float mCenterX;
    private  float mCenterY;
    private  float mDepthZ;
    private  boolean mReverse;
    private Camera mCamera;
    private float scale=1;


    public Rotate3DAnimation(Context context,float fromDegrees, float toDegrees,
                             float centerX, float centerY, float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
        scale=context.getResources().getDisplayMetrics().density;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera=new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float fromDegrees=mFromDegrees;
        float degrees=fromDegrees+(mToDegrees-fromDegrees)*interpolatedTime;
        float centerX=mCenterX;
        float centerY=mCenterY;
        Camera camera=mCamera;
        Matrix matrix=t.getMatrix();
        Log.e("cm","初始 matrix---"+matrix.toShortString());
        camera.save();

        if (mReverse){
            camera.translate(0,0,mDepthZ*interpolatedTime);
        }else {
            camera.translate(0,0,mDepthZ*(1-interpolatedTime));
        }

        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        float[] mValues=new float[9];
        matrix.getValues(mValues);
        mValues[6]=mValues[6]/scale;
        mValues[7]=mValues[7]/scale;
        matrix.setValues(mValues);

        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
        Log.e("cm","结束 matrix---"+matrix.toShortString());


    }
}
