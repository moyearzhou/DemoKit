package com.moyear.demokit.bean.item;

import android.graphics.Bitmap;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/22 13:17
 * @Description : demo的item
 */
public class ItemDemo {

    private String name;

    private String caption;

    private Bitmap thumbnail;

    public ItemDemo() {}

    public ItemDemo(String name, String caption, String className) {
        this.name = name;
        this.caption = caption;
        this.className = className;
    }

    private String className;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}