package com.example.bawei.baselibrary.MyInter;

import android.app.Activity;

import java.lang.reflect.Field;
/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/15 17:00
 * @Email 1151403054@qq.com
 */
public class YxhFindViewByIdManager {

    private static volatile YxhFindViewByIdManager instance;

    private YxhFindViewByIdManager() {
    }

    public static YxhFindViewByIdManager getInstance() {
        if (instance == null) {
            synchronized (YxhFindViewByIdManager.class) {
                if (instance == null) {
                    instance = new YxhFindViewByIdManager();
                }
            }
        }
        return instance;
    }

    public void InjectAllView(Activity activity) {
        Class<?> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(YxhInjectView.class)) {
                YxhInjectView injectView = field.getAnnotation(YxhInjectView.class);
                assert injectView != null;
                int id = injectView.id();
                if (id > 0) {
                    field.setAccessible(true);
                    try {
                        field.set(activity, activity.findViewById(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}