package com.github.share.utils;

import android.content.pm.PackageManager;

/**
 * Created by zlove on 2018/2/1.
 */

public class AppUtils {

    public static boolean isAppInstalled(String packageName) {
        PackageManager pm = ShareApplication.getInstance().getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
