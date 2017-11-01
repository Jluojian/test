package com.example.administrator.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/10/22.
 */


//为RC自定义VH
public class RCViewHolder extends RecyclerView.ViewHolder
{
    //layout中的各个view
    private SparseArray<View> mViews;
    //layout
    private View mConvertView;

    public RCViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static RCViewHolder get(Context context,ViewGroup parent,int layoutId)
    {
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        RCViewHolder holder = new RCViewHolder(context,itemView,parent);
        return holder;
    }

    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if(view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

}