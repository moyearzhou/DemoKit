package com.moyear.demokit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.moyear.demokit.BaseFragment;
import com.moyear.demokit.activity.DemoContainerActivity;
import com.moyear.demokit.adapter.DemoAdapter;
import com.moyear.demokit.bean.item.DemoList;
import com.moyear.demokit.bean.item.ItemDemo;
import com.moyear.demokit.databinding.FragmentHomeBinding;
import com.moyear.demokit.utils.ClassUtils;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DemoAdapter adapter = new DemoAdapter(DemoList.getItems());
        adapter.setOnItemClickListener((adapter1, view, position) -> {

            List<ItemDemo> data = (List<ItemDemo>) adapter1.getData();
            ItemDemo itemDemo = data.get(position);

            viewDemo(itemDemo.getClassName());
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        return root;
    }

    private void viewDemo(String targetClassName) {
        try {
            Class<?> aClass;
            aClass = Class.forName(targetClassName);
            if (ClassUtils.isActivity(aClass)) {
                Intent intent = new Intent(getActivity(), aClass);
                startActivity(intent);
                return;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getActivity(), DemoContainerActivity.class);
        intent.putExtra(DemoContainerActivity.EVENT_KEY_TARGET_FRAGMENT_CLASS, targetClassName);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}