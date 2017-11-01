package com.example.administrator.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/10/22.
 */


//自定义rc_adapter
public abstract class RCAdapter<T> extends RecyclerView.Adapter<RCViewHolder>
{
    protected Context mContext = null;
    protected int layoutId;
    protected List<T> mDatas = null;//绑定的数据源
    protected OnItemClickListener mOnItemClickListener = null;


    public RCAdapter(Context context,int layoutId,List<T> datas)
    {
        this.mContext = context;
        this.layoutId = layoutId;
        this.mDatas = datas;

    }

    @Override
    public RCViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RCViewHolder mViewHolder = RCViewHolder.get(mContext,parent,layoutId);
        return mViewHolder;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final RCViewHolder holder, int position)
    {

        convert(holder,mDatas.get(position));

        if(mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }



    }

    public abstract void convert(RCViewHolder holder,T t);

    public interface OnItemClickListener
    {
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }
}
