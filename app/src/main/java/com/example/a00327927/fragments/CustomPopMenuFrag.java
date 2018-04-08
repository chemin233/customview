package com.example.a00327927.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a00327927.customview.R;

/**
 * Created by chemin on 2018/4/8 16:20.
 * description：
 */

public class CustomPopMenuFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pop_menu,container,false);
    }
}
