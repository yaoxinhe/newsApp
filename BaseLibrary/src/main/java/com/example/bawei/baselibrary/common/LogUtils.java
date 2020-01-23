package com.example.bawei.baselibrary.common;

import android.util.Log;
/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/15 17:00
 * @Email 1151403054@qq.com
 */
public class LogUtils {
    private final static String TAG="LogUtils";
    public static void i(String log){
        Log.i(TAG,log);
    }
    public static void d(String log){
        Log.d(TAG,log);
    }
    public static void w(String log){
        Log.w(TAG,log);
    }
    public static void e(String log){
        Log.e(TAG,log);
    }
}
