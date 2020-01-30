package com.example.bawei.baselibrary.utils;

import android.content.pm.PackageManager;


public class PermissionUtils {

    private PermissionUtils() {

    }

    public static boolean checkPermission(String permission) {
        return AppUtils.getPackageManager().checkPermission(permission, AppUtils.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }
}
