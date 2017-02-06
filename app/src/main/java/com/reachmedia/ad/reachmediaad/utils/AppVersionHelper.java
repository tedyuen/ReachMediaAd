package com.reachmedia.ad.reachmediaad.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by tedyuen on 2017/2/6.
 */

public class AppVersionHelper {
    /**
     * 获取软件版本名称
     *
     * @param mContext 上下文
     * @return 返回当前版本名
     * @throws Exception
     */
    public static String getVersionName(Context mContext) throws Exception {
        // 获取packageManager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        String version = packInfo.versionName;
        Log.i("version", version);
        return version;
    }


    /**
     * 获取软件版本号
     *
     * @param context 上下文
     * @return 返回当前版本号
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
