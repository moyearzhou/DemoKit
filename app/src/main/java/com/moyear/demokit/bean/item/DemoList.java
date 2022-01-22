package com.moyear.demokit.bean.item;

import com.moyear.demokit.activity.AutoBindViewActivity;
import com.moyear.demokit.ui.demo.HeadRecyclerViewFragment;
import com.moyear.demokit.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DemoList {

    List<ItemDemo> mDemoList = new ArrayList<>();

    public static List<ItemDemo> getItems() {
        List<ItemDemo> list = new ArrayList<>();

        list.add(new ItemDemo("XUtils布局绑定", "利用反射注入实现view的绑定", AutoBindViewActivity.class.getName()));
        list.add(new ItemDemo("RecyclerView添加顶部与底部布局", "使用装饰器模式，参考List源码", HeadRecyclerViewFragment.class.getName()));
        return list;
    }

}
