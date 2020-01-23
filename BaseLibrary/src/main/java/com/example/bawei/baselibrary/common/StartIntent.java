package com.example.bawei.baselibrary.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class StartIntent {

    private static volatile StartIntent singleton;

    private StartIntent() {
    }

    public static StartIntent getInstance() {
        if (singleton == null) {
            synchronized (StartIntent.class) {
                if (singleton == null) {
                    singleton = new StartIntent();
                }
            }
        }
        return singleton;
    }
    public void start(Context context, Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }
}