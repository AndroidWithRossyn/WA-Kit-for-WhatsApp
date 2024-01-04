package com.whatscan.whatsweb.whatzweb.whatwebscan.Utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.whatscan.whatsweb.whatzweb.whatwebscan.MyApplication;


public class SharedPrefsHelper {

    private static SharedPrefsHelper mInstance;
    private final SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor mEditor;

    private SharedPrefsHelper() {
        mSharedPreferences = MyApplication.Companion.getInstance().getSharedPreferences(UserHelper.MY_PREFERANCE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


    public static SharedPrefsHelper getInstance() {
        if (mInstance == null)
            mInstance = new SharedPrefsHelper();
        return mInstance;
    }

    public static SharedPrefsHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new SharedPrefsHelper();
        return mInstance;
    }

    public String getString(String key, String defValue) {

        return mSharedPreferences.getString(key, defValue);
    }

    public SharedPrefsHelper setString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
        return this;
    }

    public int getInt(String key, int defValue) {

        return mSharedPreferences.getInt(key, defValue);
    }

    public SharedPrefsHelper setInt(String key, int value) {

        mEditor.putInt(key, value);
        mEditor.commit();

        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {

        return mSharedPreferences.getBoolean(key, defValue);
    }

    public void setBoolean(String key, boolean value) {

        mEditor.putBoolean(key, value);
        mEditor.commit();

    }

    public long getLong(String key, long defValue) {

        return mSharedPreferences.getLong(key, defValue);
    }

}
