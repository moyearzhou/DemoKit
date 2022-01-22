package com.moyear.demokit.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.moyear.demokit.BaseActivity;
import com.moyear.demokit.R;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/22 19:33
 * @Description : Demo演示的基类Activity
 */
public abstract class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            case R.id.menu_show_key:
                showKeyInfo();
                break;
            case R.id.menu_show_code:

                break;
            case R.id.menu_show_reference:
                showReference();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 显示该Demo的关键信息
     */
    abstract void showKeyInfo();

    /**
     * 显示该代码的源码
     */
    abstract void showSourceCode();

    /**
     * 显示Demo的参考资料
     */
    abstract void showReference();

}
