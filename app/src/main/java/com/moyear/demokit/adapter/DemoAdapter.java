package com.moyear.demokit.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.moyear.demokit.R;
import com.moyear.demokit.bean.item.ItemDemo;

import java.util.List;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/22 12:49
 * @Description : 代码案例列表适配器
 */
public class DemoAdapter extends BaseQuickAdapter<ItemDemo, BaseViewHolder> {

    public DemoAdapter(List<ItemDemo> list) {
        super(R.layout.item_view_simple, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ItemDemo itemDemo) {
        baseViewHolder.setText(R.id.txt_item_title, itemDemo.getName());
        baseViewHolder.setText(R.id.txt_item_caption, itemDemo.getCaption());
    }
}
