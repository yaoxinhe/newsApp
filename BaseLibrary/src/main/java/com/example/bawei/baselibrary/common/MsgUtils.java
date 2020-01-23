package com.example.bawei.baselibrary.common;

import android.content.Context;
import android.widget.Toast;
/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/15 17:00
 * @Email 1151403054@qq.com
 */
public class MsgUtils {
    /**
     * 显示消息
     * @param context
     * @param msg
     */
    public static void showMsg(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示消息
     * @param msg
     */
    public static void showMsg(String msg){
        LogUtils.d(msg);
    }
}
