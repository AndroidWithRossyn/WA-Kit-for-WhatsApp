package com.whatscan.whatsweb.whatzweb.whatwebscan.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class UserHelper {
    public static final String MY_PREFERANCE = "Whatscan";
    public static final String Theme = "theme";
    public static final String PERMISSION_GRANT = "permission_grant";
    public static final String USER_IN_SPLASH_INTRO = "USER_IN_SPLASH_INTRO";
    public static int ads_per_session = 0;
    public static String review_count = "review_count";

    public static String Adshow = "Adshow";
    public static String showInterstitial = "showInterstitial";

    public static String bannerAdId = "bannerAdId";
    public static String interstitialAdId = "interstitialAdId";
    public static String nativeAdId = "nativeAdId";
    public static final String IS_PRO = "isPro";

    public static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }



    public static boolean isPermissionGrant() {
        return SharedPrefsHelper.getInstance().getBoolean(PERMISSION_GRANT, false);
    }


    public static void saveBooleanValue(String key, boolean value) {
        SharedPrefsHelper.getInstance().setBoolean(key, value);
    }

    public static boolean getBooleanValue(String key) {
        return SharedPrefsHelper.getInstance().getBoolean(key, false);
    }

    public static void saveStringValue(String key, String value) {
        SharedPrefsHelper.getInstance().setString(key, value);
    }

    public static String getStringValue(String key) {
        return SharedPrefsHelper.getInstance().getString(key, "");
    }


    public static void setPermissionGrant(boolean key) {
        SharedPrefsHelper.getInstance().setBoolean(PERMISSION_GRANT, key);
    }

    public static String rateAppDone = "rateAppDone";

    public static boolean isRateDone() {
        return SharedPrefsHelper.getInstance().getBoolean(rateAppDone, false);
    }

    public static void setRateDone(boolean rate) {
        SharedPrefsHelper.getInstance().setBoolean(rateAppDone, rate);
    }

    public static boolean getTheme() {
        return SharedPrefsHelper.getInstance().getBoolean(Theme, false);
    }

    public static void setTheme(boolean theme) {
        SharedPrefsHelper.getInstance().setBoolean(Theme, theme);
    }



    public static void setReviewCount(int str) {
        SharedPrefsHelper.getInstance().setInt(review_count, str);
    }


    public static boolean getUserInSplashIntro() {
        return SharedPrefsHelper.getInstance().getBoolean(USER_IN_SPLASH_INTRO, false);
    }

    public static void setUserInSplashIntro(boolean rate) {
        SharedPrefsHelper.getInstance().setBoolean(USER_IN_SPLASH_INTRO, rate);
    }

}
