package com.moyear.moyearutils.annotations;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/6 19:00
 * @Description : viewUtils
 */
public class ViewBinder {

    public static void inject(Fragment fragment) {
        bindView(fragment.getClass(), fragment);
    }

    public static void inject(Activity activity) {
        bindView(activity.getClass(), activity);
        bindClickListener(activity.getClass(), activity);
    }

    /**
     * 绑定点击事件监听器
     * @param activityClass
     * @param activity
     */
    private static void bindClickListener(Class activityClass, Activity activity) {
        Method[] methods = activityClass.getDeclaredMethods();

        for (Method method : methods) {
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation == null) continue;

            method.setAccessible(true);

            int[] viewIds = annotation.value();
            for (int viewId : viewIds) {
                if (viewId <= 0) continue;

                View view = findViewById(activity, viewId);
                if (view != null) {

                    view.setOnClickListener(v -> {
                        try {
                            method.invoke(activity, null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }

    }

    private static void bindView(Class activityClass, Activity activity) {
        Field[] declaredFields = activityClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            Bind annotationBind = declaredField.getAnnotation(Bind.class);

            if (annotationBind != null) {
                int viewId = annotationBind.value();
                assignView(activity, declaredField, viewId);
            }
        }
    }

    private static void bindView(Class fragmentClass, Fragment fragment) {


    }

    /**
     * 绑定view
     * @param activity 待绑定的activity
     * @param viewField view成员变量
     * @param viewId view的id
     */
    private static void assignView(Activity activity, Field viewField, int viewId) {
        viewField.setAccessible(true);

        View viewById = findViewById(activity, viewId);
        if (viewById != null) {
            try {
                viewField.set(activity, viewById);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static View findViewById(Activity activity, int viewId) {
        View viewById = activity.findViewById(viewId);
        return viewById;
    }

}
