package com.moyear.demokit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.moyear.moyearutils.annotations.Bind;
import com.moyear.moyearutils.annotations.OnClick;
import com.moyear.moyearutils.annotations.ViewBinder;

public class MainActivity extends AppCompatActivity {

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
}