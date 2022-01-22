package com.moyear.demokit.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/22 19:45
 * @Description : 对话框工具类
 */
public class DialogUtils {

    public static Dialog showMessageDialog(Context context, String title, String msg) {
        return showMessageDialog(context, title, msg, null);
    }

    public static Dialog showMessageDialog(Context context, String title, String msg, String okText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg);
        if (okText != null) builder.setPositiveButton(okText, null);
        return builder.show();
    }

}
