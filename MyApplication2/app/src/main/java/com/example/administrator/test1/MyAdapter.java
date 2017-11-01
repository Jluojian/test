package com.example.administrator.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */

public class MyAdapter extends BaseAdapter
{
    private Context context = null;
    private List<mGoods> list = null;

    public MyAdapter(Context context,List<mGoods> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        if(list == null)return 0;
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        if(list == null)return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        View convertView;
        mViewHolder viewHolder;

        if(view == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.selected_item,null);
            viewHolder = new mViewHolder();
            viewHolder.selected_shopping_circle = (Button) convertView.findViewById(R.id.selected_shopping_circle);
            viewHolder.selected_name = (TextView) convertView.findViewById(R.id.selected_name);
            viewHolder.selected_price = (TextView) convertView.findViewById(R.id.selected_price);
            convertView.setTag(viewHolder);
        }
        else
        {
            convertView = view;
            viewHolder = (mViewHolder) convertView.getTag();
        }

        //设置圆圈中心文字
        char tmp = list.get(position).getBookName().charAt(0);
        if((tmp>='a'&&tmp<='z')||(tmp>='A'&&tmp<='Z'))
        {
            viewHolder.selected_shopping_circle.setText(list.get(position).getBookName().substring(0,1));
        }
        else
        {
            viewHolder.selected_shopping_circle.setText("*");
        }

        //view与数据绑定
        viewHolder.selected_name.setText(list.get(position).getBookName());
        viewHolder.selected_price.setText(list.get(position).getBookPrice());

        return convertView;
    }

    private class mViewHolder
    {
        public Button selected_shopping_circle;
        public TextView selected_name;
        public TextView selected_price;
    }
}
