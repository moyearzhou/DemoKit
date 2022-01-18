package com.moyear.demokit;

import android.app.Application;

/**
 * @version V1.0
 * @Author :
 * @Time : 2022/1/7 21:46
 * @Description : 应用application
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化应用崩溃处理
        ExceptionCrashHandler.getInstance().init(this);
    }
}
