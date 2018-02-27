package com.example.a00327927.customview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chemin on 2018/1/18 14:09.
 * description：
 */

public class PieData implements Parcelable {

    private float range;
    private String name;
    private float angle;
    private int color;
    private float percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    protected PieData(Parcel in) {
        range = in.readFloat();
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }



    public static final Creator<PieData> CREATOR = new Creator<PieData>() {
        @Override
        public PieData createFromParcel(Parcel in) {
            return new PieData(in);
        }

        @Override
        public PieData[] newArray(int size) {
            return new PieData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
