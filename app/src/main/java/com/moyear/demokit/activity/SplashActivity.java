package com.moyear.demokit.activity;

import android.content.Intent;
import android.os.Bundle;

import com.moyear.demokit.BaseActivity;
import com.moyear.demokit.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}