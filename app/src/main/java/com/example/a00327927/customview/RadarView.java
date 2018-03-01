package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chemin on 2018/2/28 16:26.
 * description：自定义绘制雷达图
 */

public class RadarView extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    private Paint mValuePaint;
    private int side=6;//雷达图边数
    private float radius=360;//雷达图半径
    private int centerX;
    private Path mPath;
    private float angle;
    private int centerY;
    private int count=6;//绘制的环数
    private List<String> mDesList;
    private List<Integer> mDataList;
    private float maxValue=600;

    public RadarView(Context context) {
        this(context,null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(40);

        mValuePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setColor(Color.BLUE);
        mValuePaint.setStyle(Paint.Style.FILL);
        mValuePaint.setAlpha(127);


        mPath=new Path();
        mDesList=new ArrayList<>();
        mDesList.add("Java");
        mDesList.add("Android");
        mDesList.add("Ios");
        mDesList.add("JavaScript");
        mDesList.add("Python");
        mDesList.add("C/C++");

        mDataList=new ArrayList<>();
        mDataList.add(500);
        mDataList.add(400);
        mDataList.add(600);
        mDataList.add(450);
        mDataList.add(600);
        mDataList.add(130);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        radius =Math.min(w,h)/2*0.9f;
        centerX =w/2;
        centerY =h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRadar(canvas);//绘制多边形
        drawLine(canvas);//绘制对角线
        drawText(canvas);//绘制文字
        drawRegion(canvas);//绘制填充区域
    }

    private void drawRegion(Canvas canvas) {
        if (mDataList.size()!=side){
            Log.e("cm","data个数不对");
            return;
        }
        mPath.reset();
        //计算每个节点的坐标
        for (int i = 0; i < side; i++) {
            float degree=i*angle;
            float percent = mDataList.get(i) / maxValue;
            float x= (float) (centerX+radius*Math.cos(degree)* percent);
            float y= (float) (centerY+radius*Math.sin(degree)* percent);
            if (i==0){
                mPath.moveTo(x,y);
            }else {
                mPath.lineTo(x,y);
            }

            mValuePaint.setAlpha(255);
            canvas.drawCircle(x,y,6,mValuePaint);
//            Log.e("cm","regionX---"+x+",regionY--"+y);
        }
        mPath.close();
        mValuePaint.setAlpha(127);
        canvas.drawPath(mPath,mValuePaint);
        //绘制path
    }

    private void drawRadar(Canvas canvas) {

        angle = (float) (2*Math.PI/side);
        float divider=radius/(side-1);
        for (int i = 0; i <side; i++) {
            float currentR=i*divider;
            Log.e("cm","currentR--"+currentR+",angle--"+angle+",radius---"+divider);

            mPath.reset();
            for (int j=0;j<count;j++){
                if (j==0){
                    mPath.moveTo(centerX +currentR, centerY);
                    Log.e("cm","centerX--"+centerX+",centerY--"+centerY);
                }else {
                    float x= (float) (centerX+currentR*Math.cos(angle*j));
                    float y= (float) (centerY+currentR*Math.sin(angle*j));
                    Log.e("cm","x--"+x+",y--"+y);

                    mPath.lineTo(x,y);
                }
            }
            mPath.close();
            canvas.drawPath(mPath,mPaint);
        }

    }

    /**
     * @param canvas
     * draw line from
     */
    private void drawLine(Canvas canvas) {

        for(int i=0;i<side;i++){
            mPath.reset();
            mPath.moveTo(centerX,centerY);
            float x= (float) (centerX+ radius * Math.cos(angle * i));
            float y= (float) (centerY+ radius * Math.sin(angle * i));
            mPath.lineTo(x,y);
            canvas.drawPath(mPath,mPaint);
        }
    }

    /**
     * @param canvas
     * 绘制文字
     */
    private void drawText(Canvas canvas) {
        if (mDesList.size()!=side){//如果数据不能对应,则返回不作处理
            Log.e("cm","数据的数量与角个的个数不对应");
            return;
        }
        double pi = Math.PI;


        //测量文字的高度
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        //算出中心点到文字的半径
        float textRadius =textHeight+radius;
        //算出文字开始绘制位置的x,y
        for (int i = 0; i < side; i++) {
            float degree=i*angle;
            float x= (float) (centerX+textRadius*Math.cos(degree));
            float y=(float)(centerY+textRadius*Math.sin(degree));

            //开始分4个象限逐个角绘制文字
            String content = mDesList.get(i);
            if (degree>=0&&degree<=pi/2){//在数学的第4象限
                canvas.drawText(content,x,y,mTextPaint);
            }else if (degree>pi/2&&degree<pi){//在数学的第3象限,需要测量文字的长度
                float dis = mTextPaint.measureText(content);
                canvas.drawText(content,x-dis,y,mTextPaint);
            }else if (degree>pi&&degree<=3*pi/2){//在数学的第2象限,需要测量文字长度
                float dis=mTextPaint.measureText(content);
                canvas.drawText(content,x-dis,y,mTextPaint);
            }else if (degree>3*pi/2&&degree<2*pi){//在数学的第1象限
                canvas.drawText(content,x,y,mTextPaint);
            }
        }
    }


}
