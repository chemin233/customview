package com.example.a00327927.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.a00327927.bean.MemberGradeRulesBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chemin on 2018/4/12 09:17.
 * description：
 */

public class CustomGradeProgress extends View {

    private int mWidth, mHeight;
    private int windowWidth, windowHeight;
    private List<Integer> mFirstLineData;
    private Paint mPaint;
    private int lineWidth = 10;
    private int padding = 100;//两边的间距
    private int mCurrentFirstValue = 110;
    private int mCurFirstRagion = -1;
    private MemberGradeRulesBean mMemberGradeRulesBean;
    private List<Integer> mSecondLineData;
    private int mCurrentSecondValue;
    private int mCurSecondRagion;
    private int defHeight = 150;
    private boolean isDrawGrade;//是否绘制等级
    private List<String> mGradeNames;

    public CustomGradeProgress(Context context) {
        this(context, null);
    }

    public CustomGradeProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGradeProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//画点的时候是圆形点
        mPaint.setTextSize(40);
        mPaint.setTextAlign(Paint.Align.CENTER);//这么设置就可以让设置的点是文字矩形的中点,做到居中效果

        //获取屏幕框
        DisplayMetrics dm = getResources().getDisplayMetrics();
        windowWidth = dm.heightPixels;
        windowHeight = dm.widthPixels;

        mFirstLineData = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int w = 0;
        int h = 0;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                w = windowWidth;
                break;
            case MeasureSpec.EXACTLY:
                w = width;
                break;
            case MeasureSpec.UNSPECIFIED:
                w = width;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                h = defHeight;
                break;
            case MeasureSpec.EXACTLY:
                h = height;
                break;
            case MeasureSpec.UNSPECIFIED:
                h = height;
                break;
        }
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradeNames != null && mGradeNames.size() != 0) {
            int interver = (mWidth - 2 * padding) / (mGradeNames.size() - 1);
            int pointX = padding;
            //绘制等级名称
            drawGradeName(canvas, mGradeNames, interver, pointX);

            if (mFirstLineData != null && mFirstLineData.size() != 0) {
                canvas.translate(0, mHeight / 2);
                drawProgressLine(canvas, mFirstLineData, mCurFirstRagion, mCurrentFirstValue, interver, pointX);
            }
            if (mSecondLineData != null && mSecondLineData.size() != 0) {
                canvas.translate(0, mHeight / 3);
                drawProgressLine(canvas, mSecondLineData, mCurSecondRagion, mCurrentSecondValue, interver, pointX);
            }
        }
    }

    private void drawGradeName(Canvas canvas, List<String> data, int interver, int pointX) {
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        mPaint.setTextSize(50);
        for (int i = 0; i < data.size(); i++) {
            //4.在最上方绘制等级文字
            String gradeName = mGradeNames.get(i);
            float gradeNameY = metrics.descent - metrics.ascent;
            canvas.drawText(gradeName, pointX, gradeNameY+20, mPaint);
            pointX += interver;
        }
        mPaint.setTextSize(40);
    }

    private void drawProgressLine(Canvas canvas, List<Integer> data, int curRagion, int curValue, int interver, int pointX) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(lineWidth);
        //1.先画一条与父容器同宽的直线
        canvas.drawLine(0, 0, mWidth, 0, mPaint);
        //2.根据节点数画点(由list.size决定) list就是下方数字个数
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        //画出当前进度
        mPaint.setColor(Color.parseColor("#303030"));

        float currentWidth = 0;
        if (curRagion == 0) {
            //使用精确计算 算出当前所在比例
            BigDecimal difDecimal = new BigDecimal(Integer.valueOf(curValue));
            BigDecimal regionDecimal = new BigDecimal(Integer.valueOf(data.get(0)));
            float difPercent = difDecimal.divide(regionDecimal).floatValue();
            currentWidth = difPercent * pointX;
        } else if (curRagion > 0) {
            int curStartValue = data.get(curRagion - 1);
            int ragionMaxValue = data.get(curRagion) - curStartValue;
            int difValue = curValue - curStartValue;
            //使用精确计算 算出当前所在比例
            BigDecimal difDecimal = new BigDecimal(Integer.valueOf(difValue));
            BigDecimal regionDecimal = new BigDecimal(Integer.valueOf(ragionMaxValue));
            float difPercent = difDecimal.divide(regionDecimal).floatValue();

//            Log.e("cm", "currentValue:" + curValue + "ragionMaxValue:" + ragionMaxValue + "startValue:" + curStartValue + ",Percent:" + difPercent);
            currentWidth = difPercent * interver + padding + (curRagion - 1) * interver;
        }

        canvas.drawLine(0, 0, currentWidth, 0, mPaint);
        //画出点和文字
        mPaint.setStrokeWidth(lineWidth * 2);
        for (int i = 0; i < data.size(); i++) {

            //根据当前位置来判断点的颜色
            if (pointX <= currentWidth) {
                mPaint.setColor(Color.parseColor("#303030"));
            } else {
                mPaint.setColor(Color.GRAY);
            }
            canvas.drawPoint(pointX, 0, mPaint);
            mPaint.setColor(Color.parseColor("#303030"));
            //3.在每个节点下方写字
            int content = data.get(i);
            float y = metrics.descent - metrics.ascent;
            canvas.drawText(String.valueOf(content), pointX, y, mPaint);
            pointX += interver;
        }
    }

    public void setContent(List<Integer> data) {
        this.mFirstLineData = data;
        invalidate();
    }

    public void setCurrentPercent(int mCurrentValue) {
        this.mCurrentFirstValue = mCurrentValue;
        invalidate();
    }

    public void setGradeName(List<String> gradeName) {
        this.mGradeNames = gradeName;
        invalidate();
    }

    /**
     * 设置当前的数据
     *
     * @param firstData
     * @param currentFirstValue
     */
    public void setCurrentData(List<Integer> firstData, List<Integer> secondData, List<String> gradeNames, int currentFirstValue, int currentSecondValue) {
        this.mFirstLineData = firstData;
        this.mSecondLineData = secondData;
        this.mCurrentFirstValue = currentFirstValue;
        this.mCurrentSecondValue = currentSecondValue;
        this.mGradeNames = gradeNames;
        //判断当前value在哪一格
        mCurFirstRagion = compareValue(firstData, currentFirstValue);
        mCurSecondRagion = compareValue(secondData, currentSecondValue);
        invalidate();
    }


    private int compareValue(List<Integer> data, int currentValue) {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                if (currentValue <= data.get(i)) {
                    return i;
                }
            }
            return data.size() - 1;
        }
        return -1;
    }
}
