package com.example.a00327927.animator;

import android.animation.TypeEvaluator;

/**
 * Created by chemin on 2018/3/21 10:01.
 * description：
 */

public class TextEvaluator implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int start=Integer.valueOf(startValue);
        int end=Integer.valueOf(endValue);
        int value= (int) (start+fraction*(end-start));

        return (char)value;
    }
}
