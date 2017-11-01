package com.example.administrator.test1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    protected List<mGoods> selected_goods_list = new ArrayList<>();
    protected boolean page = true;//false为购物车

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //商品数据
        final List<Map<String,String>> goods_list = new ArrayList<Map<String, String>>();
        mGoods b0 = new mGoods("Enchated Forest","¥ 5.00","作者","Johanna Basford");
        goods_list.add(b0.toMap());
        mGoods b1 = new mGoods("Arla Milk","¥ 59.00","产地","德国");
        goods_list.add(b1.toMap());
        mGoods b2 = new mGoods("Devondale Milk","¥ 79.00","产地","澳大利亚");
        goods_list.add(b2.toMap());
        mGoods b3 = new mGoods("Kindle Oasis","¥ 2399.00","版本","8GB");
        goods_list.add(b3.toMap());
        mGoods b4 = new mGoods("waitrose 早餐麦片","¥ 179.00","重量","2Kg");
        goods_list.add(b4.toMap());
        mGoods b5 = new mGoods("Mcvitie's 饼干","¥ 14.90","产地","英国");
        goods_list.add(b5.toMap());
        mGoods b6 = new mGoods("Ferrero Rocher","¥ 132.59","重量","300g");
        goods_list.add(b6.toMap());
        mGoods b7 = new mGoods("Maltesers","¥ 141.43","重量","118g");
        goods_list.add(b7.toMap());
        mGoods b8 = new mGoods("Lindt","¥ 139.43","重量","249g");
        goods_list.add(b8.toMap());
        mGoods b9 = new mGoods("Borggreve","¥ 28.90","重量","640g");
        goods_list.add(b9.toMap());


        //设置recylerView
        final RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);//获取RC_view
        recycler_view.setLayoutManager(new LinearLayoutManager(this));//设置为线性
        final RCAdapter<Map<String,String>> recycler_adapter = new RCAdapter<Map<String,String>>(this,R.layout.shopping_item_main,goods_list) {
            @Override
            public void convert(RCViewHolder holder, Map<String,String> s)
            {
                //设置商品名称
                TextView name = holder.getView(R.id.name);
                Button shopping_circle = holder.getView(R.id.shopping_circle);
                String tmp = s.get("name");
                name.setText(tmp);
                //设置圆圈中的字母
                if((tmp.charAt(0)>='a'&&tmp.charAt(0)<='z')||(tmp.charAt(0)>='A'&&tmp.charAt(0)<='Z'))
                {
                    shopping_circle.setText(tmp.substring(0,1));
                }
                else shopping_circle.setText("*");


            }
        };
        recycler_adapter.setOnItemClickListener(new RCAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                Intent intent_1 = new Intent(MainActivity.this,goods_detail.class);
                Bundle bundle_1 = new Bundle();
                String tmp[] = {"name","price","type","info"};
                for(int i=0;i<goods_list.get(position).size();i++)
                {
                    bundle_1.putString(tmp[i],goods_list.get(position).get(tmp[i]));
                }
                intent_1.putExtras(bundle_1);
                //startActivity(intent_1);
                startActivityForResult(intent_1,0);
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this,"移除第"+Integer.toString(position)+"个商品",Toast.LENGTH_SHORT).show();
                recycler_adapter.notifyItemRemoved(position);
                goods_list.remove(position);
            }
        });
        recycler_view.setAdapter(recycler_adapter);


        //设置购物车
        selected_goods_list.add(new mGoods("购物车","价格","",""));//添加第0行为标题
        final ListView list_view = (ListView) findViewById(R.id.list_view);
        final MyAdapter mAdapter = new MyAdapter(this,selected_goods_list);
        list_view.setAdapter(mAdapter);
        final AlertDialog.Builder rm_dialog_builder = new AlertDialog.Builder(this);//创建对话框
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                //当选择的item不为标题时候
                if(position > 0)
                {
                    //设置标题,信息,取消以及确认按钮
                    rm_dialog_builder.
                            setTitle("移除商品").
                            setMessage("从购物车移除"+selected_goods_list.get(position).getBookName()+"?").
                            setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();//退出对话框
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除数据源对应位置数据
                            selected_goods_list.remove(position);
                            //删除视图中对应位置item
                            mAdapter.notifyDataSetChanged();
                        }
                    }).create();
                    rm_dialog_builder.show();
                }
                return false;
            }
        });
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>0)
                {
                    Intent intent_1 = new Intent(MainActivity.this,goods_detail.class);
                    Bundle bundle_1 = new Bundle();
                    String tmp[] = {"name","price","type","info"};
                    for(int i=0;i<goods_list.get(position).size();i++)
                    {
                        bundle_1.putString(tmp[i],goods_list.get(position).get(tmp[i]));
                    }
                    intent_1.putExtras(bundle_1);
                    //startActivity(intent_1);
                    startActivityForResult(intent_1,0);
                }
            }
        });

        //设置悬浮按钮
        final FloatingActionButton fa_btn = (FloatingActionButton) findViewById(R.id.fa_btn);
        fa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page == true)
                {
                    page = false;
                    list_view.setVisibility(ListView.VISIBLE);
                    recycler_view.setVisibility(RecyclerView.INVISIBLE);
                    fa_btn.setImageResource(R.mipmap.mainpage);
                }
                else
                {
                    page = true;
                    list_view.setVisibility(ListView.INVISIBLE);
                    recycler_view.setVisibility(RecyclerView.VISIBLE);
                    fa_btn.setImageResource(R.mipmap.shoplist);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(data != null)
        {
            Bundle bundle_tmp = data.getExtras();
            if(bundle_tmp != null) selected_goods_list.add(new mGoods(bundle_tmp.getString("name"),bundle_tmp.getString("price"),bundle_tmp.getString("type"),bundle_tmp.getString("info")));
        }

    }
}
