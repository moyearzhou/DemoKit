package com.moyear.demokit.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.moyear.demokit.R;

import java.util.List;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/21 14:37
 * @Description : 简易RecyclerView适配器
 */
public class SimpleRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SimpleRecyclerViewAdapter(List<String> items) {
        super(R.layout.item_view_simple, items);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String text) {
        baseViewHolder.setText(R.id.txt_item_title, text);
    }
}
