package com.moyear.demokit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @version V1.0
 * @Author :
 * @Time : 2022/1/21 13:37
 * @Description : 基本Activity
 */
public class BaseActivity extends AppCompatActivity {


    protected void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
