package com.moyear.demokit.ui.demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moyear.demokit.BaseFragment;
import com.moyear.demokit.R;
import com.moyear.demokit.adapter.SimpleRecyclerViewAdapter;
import com.moyear.demokit.adapter.WrapRecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeadRecyclerViewFragment extends BaseFragment {

    public HeadRecyclerViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_head_recycyler_view, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);

        SimpleRecyclerViewAdapter simpleRecyclerViewAdapter = new SimpleRecyclerViewAdapter(getItems());
        WrapRecyclerviewAdapter wrapRecyclerviewAdapter = new WrapRecyclerviewAdapter(simpleRecyclerViewAdapter);

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_head_view, container, false);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.item_footer_view, container, false);
        wrapRecyclerviewAdapter.addHeaderView(headView);
        wrapRecyclerviewAdapter.addFooterView(footerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(wrapRecyclerviewAdapter);
        return rootView;
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add("这是第" + i + " 个items");
        }
        return items;
    }
}