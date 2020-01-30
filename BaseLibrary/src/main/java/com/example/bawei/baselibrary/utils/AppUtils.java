package com.example.bawei.baselibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.StringRes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Objects;



public class AppUtils {

    private static Context mCcontext;


    private AppUtils() {

    }

    /***
     * Please use in Application
     * @param context Application
     */
    public static void init(Context context) {
        mCcontext = context;
    }

    /***
     *
     * @return Context
     */
    public static Context getContext() {
        if (mCcontext == null) {
            throw new RuntimeException("init must be used in the application");
        }
        return mCcontext;
    }

    /***
     *
     * @return ApplicationContext
     */
    public static Context getApplicationContext() {
        return getContext().getApplicationContext();
    }

    /***
     * 根据 id 获取String
     * @param id String id
     * @return 字符串
     */
    public static String getResString(@StringRes int id){
        return getContext().getString(id);
    }

    /***
     *
     * @return PackageInfo
     */
    public static PackageInfo getPackageInfo() {
        try {
            return getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     *
     * @return PackageManager
     */
    public static PackageManager getPackageManager() {
        return getApplicationContext().getPackageManager();
    }

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /***
     *
     * @return App name
     */
    public static String getAppName() {
        int labelRes = getApplicationContext().getApplicationInfo().labelRes;
        return getApplicationContext().getString(labelRes);
    }

    /***
     *
     * @return Package name
     */
    public static String getPackageName() {
        return getApplicationContext().getPackageName();
    }

    /***
     *
     * @return Version code
     */
    public static String getVersionCode() {
        return Objects.requireNonNull(getPackageInfo()).versionCode+"";
    }

    /***
     *
     * @return Version name
     */
    public static String getVersionName() {
        return Objects.requireNonNull(getPackageInfo()).versionName ;
    }

    public static String getDeviceId() {
        if (!PermissionUtils.checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            return "";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return "";
        }
        TelephonyManager tm = getTelephonyManager();
        String deviceId = tm.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String imei = tm.getImei();
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            String meid = tm.getMeid();
            return TextUtils.isEmpty(meid) ? "" : meid;
        }
        return "";
    }

    /***
     *
     * @return serial
     */
    public static String getSerial() {
        if (PermissionUtils.checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
        }
        return "";
    }

    /***
     *
     * @param flag true Imei, false Meid
     * @return Imei or Meid
     */
    public static String getImeiorMeid(boolean flag) {

        if (!PermissionUtils.checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            return "";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return "";
        }

        TelephonyManager telephonyManager = getTelephonyManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (flag) {
                return getMinOne(telephonyManager.getImei(0), telephonyManager.getImei(1));
            } else {
                return getMinOne(telephonyManager.getMeid(0), telephonyManager.getMeid(1));
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String ids = getSystemPropertyByReflect(flag ? "ril.gsm.imei" : "ril.cdma.meid");
            if (!TextUtils.isEmpty(ids)) {
                String[] idArr = ids.split(",");
                if (idArr.length == 2) {
                    return getMinOne(idArr[0], idArr[1]);
                } else {
                    return idArr[0];
                }
            }

            String id0 = telephonyManager.getDeviceId();
            String id1 = "";
            try {
                Method method = telephonyManager.getClass().getMethod("getDeviceId", int.class);
                id1 = (String) method.invoke(telephonyManager,
                        flag ? TelephonyManager.PHONE_TYPE_GSM
                                : TelephonyManager.PHONE_TYPE_CDMA);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if (flag) {
                if (id0 != null && id0.length() < 15) {
                    id0 = "";
                }
                if (id1 != null && id1.length() < 15) {
                    id1 = "";
                }
            } else {
                if (id0 != null && id0.length() == 14) {
                    id0 = "";
                }
                if (id1 != null && id1.length() == 14) {
                    id1 = "";
                }
            }
            return getMinOne(id0, id1);
        } else {
            String deviceId = telephonyManager.getDeviceId();
            if (flag) {
                if (deviceId != null && deviceId.length() >= 15) {
                    return deviceId;
                }
            } else {
                if (deviceId != null && deviceId.length() == 14) {
                    return deviceId;
                }
            }
        }

        return "";
    }


    private static String getMinOne(String s0, String s1) {
        boolean empty0 = TextUtils.isEmpty(s0);
        boolean empty1 = TextUtils.isEmpty(s1);
        if (empty0 && empty1) {
            return "";
        }
        if (!empty0 && !empty1) {
            if (s0.compareTo(s1) <= 0) {
                return s0;
            } else {
                return s1;
            }
        }
        if (!empty0) {
            return s0;
        }
        return s1;
    }


    private static String getSystemPropertyByReflect(String key) {
        try {
            @SuppressLint("PrivateApi")
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method getMethod = clz.getMethod("get", String.class, String.class);
            return (String) getMethod.invoke(clz, key, "");
        } catch (Exception e) {/**/}
        return "";
    }


    @SuppressLint("HardwareIds")
    public static String getIMSI() {
        if (!PermissionUtils.checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            return "";
        }
        return getTelephonyManager().getSubscriberId();
    }

    /***
     *
     * @return Phone type
     */
    public static String getPhoneType() {
        return getTelephonyManager().getPhoneType() + "";
    }

    /***
     *
     * @return Current system language
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /***
     *
     * @return System version
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /***
     *
     * @return System model
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /***
     *
     * @return Decide brand
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }
}
