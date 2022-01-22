package com.moyear.demokit.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.moyear.demokit.BaseActivity;
import com.moyear.demokit.R;
import com.moyear.demokit.activity.ui.main.PlaceholderFragment;
import com.moyear.demokit.activity.ui.main.SectionsPagerAdapter;
import com.moyear.demokit.databinding.ActivityDemoViewBinding;

public class DemoContainerActivity extends BaseActivity {

    public static final String EVENT_KEY_TARGET_FRAGMENT_CLASS = "key_target_fragment_class";

    private SectionsPagerAdapter sectionsPagerAdapter;

    private ActivityDemoViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDemoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        initFragment(sectionsPagerAdapter);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void initFragment(SectionsPagerAdapter sectionsPagerAdapter) {
        Fragment demoFragment = PlaceholderFragment.newInstance(1);

        String className = getIntent().getStringExtra(EVENT_KEY_TARGET_FRAGMENT_CLASS);
        if (className != null) {
            try {
                Class target = Class.forName(className);
                Fragment parsedFragment = parseFragment(target);

                if (parsedFragment != null) showFragment(parsedFragment);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        sectionsPagerAdapter.addFragment("演示", demoFragment);
        sectionsPagerAdapter.addFragment("说明", PlaceholderFragment.newInstance(2));
    }

    private void showFragment(Fragment demoFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment, demoFragment);
        fragmentTransaction.commit();
    }

    private Fragment parseFragment(Class fragmentClass) {
        Fragment targetFragment = null;

        try {
            targetFragment = (Fragment) fragmentClass.newInstance();

            if (targetFragment == null)
                toast("获取传入的Fragment失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return targetFragment;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_activity_demo, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_show_reference:

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}