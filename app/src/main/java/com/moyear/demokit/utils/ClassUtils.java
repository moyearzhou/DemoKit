package com.moyear.demokit.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/22 19:20
 * @Description :  class类相关工具类
 */
public class ClassUtils {

    /**
     * 判断一个class类是不是Activity
     * @param clazz
     * @return
     */
    public static boolean isActivity(Class clazz) {
        boolean isActivityClass = false;

        try {
            Object o = clazz.newInstance();

            if (o instanceof Activity) isActivityClass = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return isActivityClass;
    }

    /**
     * 判断一个类是不是Fragment
     * @param clazz
     * @return
     */
    public static boolean isFragment(Class clazz) {
        boolean isFragmentClass = false;

        try {
            Object o = clazz.newInstance();

            if (o instanceof Fragment) isFragmentClass = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return isFragmentClass;
    }
}
