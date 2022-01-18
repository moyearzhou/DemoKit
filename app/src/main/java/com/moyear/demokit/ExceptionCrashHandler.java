package com.moyear.demokit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/7 21:52
 * @Description : 应用异常崩溃处理
 */
public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;

    private static final String TAG = "ExceptionCrashHandler";

    public static ExceptionCrashHandler mExceptionCrashHandler;

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private ExceptionCrashHandler() {}

    public static ExceptionCrashHandler getInstance() {
        if (mExceptionCrashHandler == null) {
            synchronized (ExceptionCrashHandler.class) {
                if (mExceptionCrashHandler == null)
                    mExceptionCrashHandler = new ExceptionCrashHandler();
            }
        }
        return mExceptionCrashHandler;
    }

    public void init(Context context) {
        Thread.currentThread().setUncaughtExceptionHandler(this);
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        this.mContext = context;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable throwable) {
        Log.d(TAG, "uncaughtException: catch a crash exception!!!");

        String strDeviceInfo = getBasicInfo();
        String strException = obtainExceptionInfo(throwable);

        String crashLogText = strDeviceInfo + "\n=====================\n" + strException;
        saveCrashLogToSdcard(crashLogText);
    }

    private void saveCrashLogToSdcard(String crashLogText) {

    }

    private String getBasicInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> map : obtainBasicInfoMap().entrySet()) {

            stringBuffer.append(map.getKey() + ":" + map.getValue());
            stringBuffer.append("\n");

        }
        return stringBuffer.toString();
    }

    private HashMap<String, String> obtainBasicInfoMap() {
        HashMap<String, String> maps = new HashMap<>();
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        maps.put("versionName", packageInfo.versionName);
        maps.put("versionCode", packageInfo.versionCode + "");
        maps.put("buildModel", Build.MODEL);
        maps.put("SDK", Build.VERSION.SDK_INT + "");
        maps.put("DeviceInfo", getDeviceInfo());
        return maps;
    }

    private static String getDeviceInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        Field[] declaredFields = Build.class.getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);

                String name = field.getName();
                String value = field.get(null).toString();

                stringBuffer.append(name + "=" + value);
                stringBuffer.append("\n");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 捕获异常信息
     * @param throwable
     * @return
     */
    private String obtainExceptionInfo(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        throwable.printStackTrace(printWriter);
        printWriter.close();//关闭
        return stringWriter.toString();
    }

}
