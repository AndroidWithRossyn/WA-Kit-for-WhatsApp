package com.whatscan.whatsweb.whatzweb.whatwebscan.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.whatscan.whatsweb.whatzweb.whatwebscan.BuildConfig;


public class Utility {

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        Log.i("decodeSampled Method", "Done.....");
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int mHeight = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (mHeight > reqHeight || width > reqWidth) {

            final int mHalfHeight = mHeight / 2;
            final int mHalfWidth = width / 2;

            while ((mHalfHeight / inSampleSize) >= reqHeight
                    && (mHalfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        Log.i("Calculate Size Method", "Done........");
        return inSampleSize;
    }

    public static void printLog(String key, String value){
        if(BuildConfig.DEBUG ){
            Log.e(key,""+value);
        }
    }

}
