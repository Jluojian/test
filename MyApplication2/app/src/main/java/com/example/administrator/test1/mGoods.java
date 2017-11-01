package com.example.administrator.test1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/22.
 */
public class mGoods
{
    private Map<String,String> m = new HashMap<String,String>();

    public mGoods(String name,String price,String type,String info)
    {
        setGoods(name,price,type,info);
    }


    public void setGoods(String name,String price,String type,String info)
    {
        m.put("name",name);
        m.put("price",price);
        m.put("type",type);
        m.put("info",info);

    }

    final public String getBookName()
    {
        return m.get("name");
    }

    final public String getBookPrice()
    {
        return m.get("price");
    }

    final public String getBookType()
    {
        return m.get("type");
    }

    final public String getBookInfo()
    {
        return m.get("info");
    }

    public Map<String,String> toMap()
    {
        return m;
    }
};
