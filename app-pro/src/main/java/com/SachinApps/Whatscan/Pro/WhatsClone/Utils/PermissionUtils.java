package com.SachinApps.Whatscan.Pro.WhatsClone.Utils;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissionUtils {

    public static boolean isPermissionGranted(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE);
            return readExternalStoragePermission == PackageManager.PERMISSION_GRANTED;

        } else {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE);
            int writeExternalStoragePermission = ContextCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE);

            return readExternalStoragePermission == PackageManager.PERMISSION_GRANTED &&
                    writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    public static void takePermission(Activity activity, int permissions_request_code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{READ_EXTERNAL_STORAGE}, permissions_request_code);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, permissions_request_code);
        }
    }
}
