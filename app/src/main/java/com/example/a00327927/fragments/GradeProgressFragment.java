package com.example.a00327927.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a00327927.customview.CustomGradeProgress;
import com.example.a00327927.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chemin on 2018/4/9 23:40.
 */

public class GradeProgressFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_grade_progress_layout, container, false);
        CustomGradeProgress progress=view.findViewById(R.id.grade_progress);

        initData();

        List<Integer> data=new ArrayList<>();
        List<String> gradeNames=new ArrayList<>();
        data.add(10);
        data.add(200);
        data.add(400);
        data.add(500);
//        data.add(1000);
        gradeNames.add("专员");
        gradeNames.add("部长");
        gradeNames.add("经理");
        gradeNames.add("CEO");
        progress.setCurrentData(data,data,gradeNames,300,5);
        return view;
    }

    private void initData() {

    }
}
