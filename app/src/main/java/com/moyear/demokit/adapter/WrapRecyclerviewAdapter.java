package com.moyear.demokit.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/21 13:58
 * @Description : 可添加头部与底部view的recyclerview适配器
 */
public class WrapRecyclerviewAdapter extends RecyclerView.Adapter {

    private final RecyclerView.Adapter mAdapter;

    private List<View> mHeaderViews;
    private List<View> mFooterViews;

    public WrapRecyclerviewAdapter(@NonNull RecyclerView.Adapter adapter) {
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();

        mAdapter = adapter;

        //为包裹的Adapter注册数据监听，如果不注册的话，则真正的Adapter的数据发生改变的事件无法传递给包裹Adapter
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                notifyItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                notifyItemRangeRemoved(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                notifyItemRangeRemoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //holder的position
        int position = viewType;

        //返回头部ViewHolder
        if (position < getHeaderCount()) {
            return new RecyclerView.ViewHolder(mHeaderViews.get(position)) {};
        }

        //返回真正的列表viewHolder
        int adjPosition = position - getHeaderCount();
        //真正的adapter的item数量
        int adapterItemCount = 0;
        if (mAdapter != null) {
            adapterItemCount = mAdapter.getItemCount();
            if (adjPosition < adapterItemCount) {
                return mAdapter.onCreateViewHolder(parent, mAdapter.getItemViewType(adjPosition));
            }
        }

        //返回底部viewHolder
        return new RecyclerView.ViewHolder(mFooterViews.get(adjPosition - adapterItemCount)) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < getHeaderCount()) return;

        //真正的adapter的item数量
        int adapterItemCount = 0;
        if (mAdapter != null) {
            adapterItemCount = mAdapter.getItemCount();

            int adjPosition = position - getHeaderCount();
            //返回真正的列表viewHolder
            if (adjPosition < adapterItemCount){
                mAdapter.onBindViewHolder(holder, adjPosition);
            }
        }
    }

    /**
     * 用viewHolder的position作为viewType
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mAdapter == null) return 0;
        return getHeaderCount() + getFooterCount() + mAdapter.getItemCount();
    }

    private int getFooterCount() {
        return mFooterViews.size();
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public void addHeaderView(@NonNull View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFooterView(@NonNull View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

}
