package com.example.administrator.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class goods_detail extends AppCompatActivity {

    protected boolean star_flag = false;
    protected String operations[];
    protected Intent intent_1 = new Intent();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            setResult(RESULT_OK,intent_1);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        //获取来自主页面的数据
        final Bundle bundle_1 = getIntent().getExtras();

        //对name进行处理,将空格之后的字符串删除,将'替换为_
        String goods_name = bundle_1.getString("name");
        for(int i=0;i<goods_name.length();i++)
        {
            if(goods_name.charAt(i) == ' ')
            {
                goods_name = goods_name.substring(0,i);
                break;
            }
        }
        goods_name = goods_name.toLowerCase();
        goods_name = goods_name.replace('\'','_');

        //导出图片
        ImageView goods_picture = (ImageView) findViewById(R.id.goods_picture);
        int img_id = getResources().getIdentifier(goods_name,"mipmap","com.example.administrator.test1");
        goods_picture.setImageResource(img_id);

        //设置商品价格
        TextView goods_price_view = (TextView) findViewById(R.id.goods_price);
        goods_price_view.setText(bundle_1.getString("price"));

        //设置商品名称
        TextView goods_name_view = (TextView) findViewById(R.id.goods_name);
        goods_name_view.setText(bundle_1.getString("name"));

        //设置商品信息
        TextView goods_type_with_info_view = (TextView) findViewById(R.id.goods_type_with_info);
        goods_type_with_info_view.setText(bundle_1.getString("type")+" "+bundle_1.getString("info"));

        //设置返回按钮
        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK,intent_1);
                finish();
            }
        });

        //设置星号按钮
        final ImageView star_btn = (ImageButton) findViewById(R.id.star_btn);
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(star_flag == false)
                {
                    star_btn.setImageResource(R.mipmap.full_star);
                    star_flag = true;
                }
                else
                {
                    star_btn.setImageResource(R.mipmap.empty_star);
                    star_flag = false;
                }
            }
        };
        star_btn.setOnClickListener(listener);

        //设置List_view列表
        ListView operationList = (ListView) findViewById(R.id.list_view_1);
        operations = new String[]{"一键下单","分享商品","不感兴趣","查看更多商品促销信息",""};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.operation_list_item,operations);
        operationList.setAdapter(arrayAdapter);

        //设置购物车按钮
        ImageButton shopcar_btn = (ImageButton) findViewById(R.id.shopcar_btn);
        shopcar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(goods_detail.this,"您添加了"+bundle_1.getString("name")+"进购物车",Toast.LENGTH_SHORT).show();
//                Intent intent_1 = new Intent();
//                intent_1.putExtras(bundle_1);
//                setResult(RESULT_OK,intent_1);
//                finish();
                intent_1.putExtras(bundle_1);

            }
        });
    }
}
