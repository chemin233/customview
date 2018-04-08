package com.example.a00327927.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a00327927.customview.R;

import java.util.List;

/**
 * Created by chemin on 2018/4/7 23:04.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RcHolder> {


    private List<String> mData;
    private Context mContext;
    private View.OnClickListener mListener;
    private final static String TAG="RecyclerAdapter";

    public RecyclerAdapter(Context context,List<String> data){
        this.mData=data;
        this.mContext=context;
    }

    @Override
    public RcHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false);
        return new RcHolder(view);
    }

    @Override
    public void onBindViewHolder(RcHolder holder, int position) {
        Log.e(TAG,"onBindViewHolder----->"+mData.size());
        holder.mTvShow.setText(mData.get(position));
        holder.mTvShow.setTag(position);
        holder.mTvShow.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    class RcHolder extends RecyclerView.ViewHolder{

        TextView mTvShow;

        public RcHolder(View itemView) {
            super(itemView);
            mTvShow = itemView.findViewById(R.id.tv_rc_show);
        }
    }

    public void setItemListener(View.OnClickListener listener){
        this.mListener=listener;
    }
}
