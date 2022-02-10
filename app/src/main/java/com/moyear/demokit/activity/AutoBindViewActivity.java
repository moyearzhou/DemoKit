package com.moyear.demokit.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.moyear.demokit.R;
import com.moyear.libs.Bind;
import com.moyear.libs.OnClick;
import com.moyear.libs.ViewBinder;

/**
 * 演示Activity：利用反射自动绑定View
 */
public class AutoBindViewActivity extends DemoActivity {

    @Bind(R.id.tvContent)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewBinder.inject(this);
        tvContent.setText("反射注入");
    }

    @OnClick(R.id.tvContent)
    private void textClick() {
        Toast.makeText(getApplicationContext(), "点击", Toast.LENGTH_LONG).show();
    }

    @Override
    void showKeyInfo() {

    }

    @Override
    void showSourceCode() {

    }

    @Override
    void showReference() {

    }
}