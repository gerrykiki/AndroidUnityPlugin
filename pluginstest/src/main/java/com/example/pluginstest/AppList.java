package com.example.pluginstest;

import android.graphics.drawable.Drawable;

/**
 * Created by gerrylin on 2018/3/29.
 */

public class AppList {
    private String name;
    Drawable icon;

    public AppList(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }
}
