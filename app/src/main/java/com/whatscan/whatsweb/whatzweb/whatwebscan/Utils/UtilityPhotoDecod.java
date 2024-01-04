package com.whatscan.whatsweb.whatzweb.whatwebscan.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class UtilityPhotoDecod {
    public static int SAMPLER_SIZE = 256;


    public static String getRealPathFromURI(Context activity, Uri contentURI) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null,
                null, null, null);
        // Source is Dropbox or other similar local file path
        if (cursor == null)
            return contentURI.getPath();
        else {
            cursor.moveToFirst();
            int idx = cursor
                    .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
