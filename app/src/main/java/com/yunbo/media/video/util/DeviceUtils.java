package com.yunbo.media.video.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * create by jeek
 * 2022/5/7
 * des: 设备信息获取工具类
 **/
public class DeviceUtils {

    /**
     * 是否有凹槽 - Vivo
     */
    public static final int NOTCH_IN_SCREEN_VIVO = 0x00000020;

    /**
     * 是否有圆角 - Vivo
     */
    public static final int ROUNDED_IN_SCREEN_VIVO = 0x00000008;

    /**
     * 没有获取设备信息
     */
    public static final int DEVICE_NULL = 0;
    /**
     * 不关心的设备
     */
    public static final int DEVICE_UNKOWN = 1;
    /**
     * 小米
     */
    public static final int DEVICE_XIAOMI = 2;
    /**
     * 三星
     */
    public static final int DEVICE_SAMSUNG = 3;
    /**
     * 华为
     */
    public static final int DEVICE_HUAWEI = 4;
    /**
     * Voio
     */
    public static final int DEVICE_VIVO = 5;
    /**
     * Oppo
     */
    public static final int DEVICE_OPPO = 6;
    /**
     * Meizu
     */
    public static final int DEVICE_MEIZU = 7;

    /**
     * 当前设备厂商
     */
    private static int sDeviceType = DEVICE_NULL;

    /**
     * 刘海屏初始化完成
     */
    private static boolean sHasNotchInScreenInit = false;
    /**
     * 刘海屏
     */
    private static boolean sHasNotchInScreen = false;

    /**
     * 获取当前Android版本号
     *
     * @return version
     */
    @SuppressWarnings("deprecation")
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取imei
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        try {
            // check if has the permission
            if (PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String v = tm.getDeviceId();
                if (v == null) {
                    v = "";
                }
                return v;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneNumber(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getLine1Number();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNetworkCountryIso(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getNetworkCountryIso();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNetworkOperator(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getNetworkOperator();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getNetworkOperatorName();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }


    public static int getNetworkType(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getNetworkType();
        } catch (Exception e) {
            return TelephonyManager.NETWORK_TYPE_UNKNOWN;
        }
    }


    public static int getPhoneType(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getPhoneType();
        } catch (Exception e) {
            return TelephonyManager.PHONE_TYPE_NONE;
        }
    }

    public static String getSimCountryIso(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getSimCountryIso();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSimOperator(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getSimOperator();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSimOperatorName(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getSimOperatorName();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSimSerialNumber(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getSimSerialNumber();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    /*
     * @return
     * @see #SIM_STATE_UNKNOWN
     * @see #SIM_STATE_ABSENT
     * @see #SIM_STATE_PIN_REQUIRED
     * @see #SIM_STATE_PUK_REQUIRED
     * @see #SIM_STATE_NETWORK_LOCKED
     * @see #SIM_STATE_READY
     */
    public static int getSimState(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getSimState();
        } catch (Exception e) {
            return TelephonyManager.SIM_STATE_UNKNOWN;
        }
    }

    /**
     * IMSI
     *
     * @return
     */
    public static String getSubscriberId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getSubscriberId();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getVoiceMailNumber(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String v = tm.getVoiceMailNumber();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取mac地址
     *
     * @return
     */
    public static String getMacAddress(Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String v = wifi.getConnectionInfo().getMacAddress();
            if (v == null) {
                v = "";
            }
            return v;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取设备唯一编码 md5(imei+mac)
     *
     * @return
     */
    public static String getUniqueId(Context context) {
//        try {
//            String imei = getIMEI(context);
//            String macAddr = getMacAddress(context);
//            String unique = imei + macAddr;
//            return Md5.MD5(unique);
//        } catch (Exception e) {
        return "";
//        }
    }

    public static int getDeviceType() {
        if (sDeviceType == DEVICE_NULL) {
            String model = Build.MODEL;
            String carrier = Build.MANUFACTURER;
            String brand = Build.BRAND;

            if (model != null) {
                model = model.toLowerCase();
            }

            if (carrier != null) {
                carrier = carrier.toLowerCase();
            }

            if (brand != null) {
                brand = brand.toLowerCase();
            }

            if (TextUtils.isEmpty(model) && TextUtils.isEmpty(carrier) || TextUtils.isEmpty(brand)) {
                sDeviceType = DEVICE_UNKOWN;
            } else if (model.contains("xiaomi") || carrier.contains("xiaomi") || brand.contains("xiaomi")) {
                sDeviceType = DEVICE_XIAOMI;
            } else if (model.contains("samsung") || carrier.contains("samsung") || brand.contains("samsung")) {
                sDeviceType = DEVICE_SAMSUNG;
            } else if (model.contains("huawei") || carrier.contains("huawei") || brand.contains("huawei")) {
                sDeviceType = DEVICE_HUAWEI;
            } else if (model.contains("vivo") || carrier.contains("vivo") || brand.contains("vivo")) {
                sDeviceType = DEVICE_VIVO;
            } else if (model.contains("oppo") || carrier.contains("oppo") || brand.contains("oppo")) {
                sDeviceType = DEVICE_OPPO;
            } else if (model.contains("meizu") || carrier.contains("meizu") || brand.contains("meizu")) {
                sDeviceType = DEVICE_MEIZU;
            } else {
                sDeviceType = DEVICE_UNKOWN;
            }

//            LogUtil.d("model " + model + ", carrier " + carrier + ", brand " + brand + ", type " + sDeviceType);
        }
        return sDeviceType;
    }

    /**
     * 判断手机是否ROOT
     */
    public static boolean isRoot() {
        boolean root = false;
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }

    /**
     * 是否是开发者模式
     *
     * @return
     */
    public static boolean isDeveloperMode(Context context) {
        boolean mode = false;
        int anInt = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
        if (anInt > 0) {
            mode = true;
        }
        return mode;
    }

    /**
     * @param containSys 是否包含系统应用
     * @return
     */
    public static List<String> getAllApkList(Context context, boolean containSys) {
        List<String> apps = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
        StringBuilder stringBuilder = new StringBuilder();
        for (ResolveInfo resolveInfo : resolveInfoList) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (containSys) {
                apps.add(activityInfo.applicationInfo.packageName);
                stringBuilder.append(activityInfo.applicationInfo.packageName);
                stringBuilder.append(",\n");
            } else {
                if (!isSystemPackage(resolveInfo)) {
                    apps.add(activityInfo.applicationInfo.packageName);
                    stringBuilder.append(activityInfo.applicationInfo.packageName);
                    stringBuilder.append(",\n");
                }
            }
        }
//        LogUtil.d("zzx", stringBuilder.toString());
        return apps;
    }

    /**
     * 判断是否是系统应用
     *
     * @param resolveInfo
     * @return
     */
    public static boolean isSystemPackage(ResolveInfo resolveInfo) {
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    /**
     * 获取 fingerprint
     *
     * @return
     */
    public static String getFingerPrint() {
        return Build.FINGERPRINT;
    }

    /**
     * 判断是否是刘海屏－华为
     */
    public static boolean hasNotchInScreenAtHuawei(Context context) {
        boolean ref = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class hwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = hwNotchSizeUtil.getMethod("hasNotchInScreen");
            ref = (boolean) get.invoke(hwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
//            LogUtil.d("warn", "huawei hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
//            LogUtil.d("warn", "huawei hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
//            LogUtil.d("warn", "huawei hasNotchInScreen Exception");
        }
        return ref;
    }

    /**
     * 判断是否是刘海屏
     */
    public static boolean hasNotchInScreen(Context context) {
        if (sHasNotchInScreenInit) {
            return sHasNotchInScreen;
        }
        sHasNotchInScreenInit = true;
        switch (getDeviceType()) {
            case DEVICE_HUAWEI:
                sHasNotchInScreen = hasNotchInScreenAtHuawei(context);
                break;
            case DEVICE_VIVO:
                sHasNotchInScreen = hasNotchInScreenAtVivo(context);
                break;
            case DEVICE_OPPO:
                sHasNotchInScreen = hasNotchInScreenAtOppo(context);
                break;
            case DEVICE_XIAOMI:
                sHasNotchInScreen = hasNotchInScreenAtMIUI(context);
                break;
            default:
                sHasNotchInScreen = false;
                break;
        }
//        LogUtil.d("hasNotchScreen " + sHasNotchInScreen);
        return sHasNotchInScreen;
    }

    /**
     * 判断是否是刘海屏 - Vivo ---p.s. vivo x21a设备上验证报ClassNotFoundException, 需要其它vivo设备验证
     */
    public static boolean hasNotchInScreenAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class ftFeature = cl.loadClass("com.util.FtFeature");
            Method get = ftFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) get.invoke(ftFeature, NOTCH_IN_SCREEN_VIVO);
        } catch (ClassNotFoundException e) {
//            LogUtil.d("warn", "vivo hasNotchInScreen ClassNotFoundException");
            ret = Build.MODEL.toLowerCase().contains("x21a");
        } catch (NoSuchMethodException e) {
//            LogUtil.d("warn", "vivo hasNotchInScreen NoSuchMethodException");
            ret = Build.MODEL.toLowerCase().contains("x21a");
        } catch (Exception e) {
//            LogUtil.d("warn", "vivo hasNotchInScreen Exception");
        }
        return ret;
    }

    /**
     * 判断是否是刘海屏 - oppo ---p.s.待设备上验证
     */
    public static boolean hasNotchInScreenAtOppo(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * 判断是否是刘海屏 - 小米 ---p.s.待设备上验证
     */
    public static boolean hasNotchInScreenAtMIUI(Context context) {
        int result = 0;
        final String key = "ro.miui.notch";
        try {
            ClassLoader classLoader = context.getClassLoader();
            @SuppressWarnings("rawtypes") Class cls = classLoader.loadClass("android.os.SystemProperties");
            // 参数类型
            @SuppressWarnings("rawtypes") Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = cls.getMethod("getInt", paramTypes);
            // 参数
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Integer(0);
            result = (Integer) getInt.invoke(cls, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }

    /**
     * 判断是否是刘海屏 - xiaomi ---p.s.已在红米6pro（刘海屏）和小米6x（非刘海屏）上验证
     */
    public static boolean hasNotchInScreenAtXiaomi(Context context) {
        boolean ret = false;
        String notchFlag = "1";
        try {
            Class<?> sysClass = Class.forName("android.os.SystemProperties");
            Method getStringMethod = sysClass.getDeclaredMethod("get", String.class);
            ret = notchFlag.equals((String) getStringMethod.invoke(sysClass, "ro.miui.notch"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
