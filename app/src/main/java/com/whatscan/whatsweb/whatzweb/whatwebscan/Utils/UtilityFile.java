package com.whatscan.whatsweb.whatzweb.whatwebscan.Utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.Vector;

public class UtilityFile {

    public static final String FOLDER_SAVE_DB_FILE = "tuppledb";
    public static final String FOLDER_TO_PRIVATEVAULT = "/.PrivateVault/";
    public static final String FOLDER_TO_THEME = "/.PrivateVault/theme/";
    public static final String FOLDER_TO_SNAP = "/PrivateVault/snap/";
    public static final String FOLDER_TO_SUPPORT = "/.PrivateVault/support/";
    public static final String FOLDER_TO_PRIVATE_VAULT = "/.PrivateVault/privatevault/";
    public static final String FOLDER_TO_PRIVATE_VAULT_THUMBNAIL = "/.PrivateVault/privatevault/thumbnail/";
    private File dirName;
    private final File dirShareFiles;

    public UtilityFile(Context context) {
        // Find the dir at SDCARD to save cached images
        try {
            dirName = context.getExternalFilesDir(null);

            if (dirName != null && !dirName.exists()) {
                // create cache dir in your application context
                dirName.mkdirs();
            }
        } catch (NullPointerException e) {
        }
        dirShareFiles = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "Gallery Pro");
        if (!dirShareFiles.exists()) {
            // create cache dir in your application context
            dirShareFiles.mkdirs();
        }
    }

    public static Bitmap decodeFile(String filePath) {
        // Decode image size

        Bitmap bmp;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 400;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int inSampleSize = 1;
        if (width_tmp > REQUIRED_SIZE || height_tmp > REQUIRED_SIZE) {

            final int halfHeight = height_tmp / 2;
            final int halfWidth = width_tmp / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= REQUIRED_SIZE
                    && (halfWidth / inSampleSize) >= REQUIRED_SIZE) {
                inSampleSize *= 2;
            }
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = inSampleSize;
        bmp = BitmapFactory.decodeFile(filePath, o2);

        bmp = rotatedBitmap(bmp, filePath);


        return bmp;

    }

    public static Bitmap rotatedBitmap(Bitmap bmp, String filePath) {
        try {
            ExifInterface ei = new ExifInterface(filePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    bmp = rotateImage(bmp, 270);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bmp = rotateImage(bmp, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bmp = rotateImage(bmp, 180);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                    bmp = rotateImage(bmp, 0);
                    // etc.
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bmp;
    }

    public static Bitmap rotateImage(Bitmap bmp, int mRotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(mRotation);
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
                matrix, true);
    }

    public static String getPath(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null,
                null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public static Collection<File> listFiles(File directory,
                                             FilenameFilter[] filter, int recurse) {

        Vector<File> files = new Vector<File>();

        File[] entries = directory.listFiles();

        if (entries != null) {
            for (File entry : entries) {
                for (FilenameFilter filefilter : filter) {
                    if (filefilter.accept(directory, entry.getName())) {
                        files.add(entry);
                    }
                }
                if ((recurse <= -1) || (recurse > 0 && entry.isDirectory())) {
                    recurse--;
                    files.addAll(listFiles(entry, filter, recurse));
                    recurse++;
                }
            }
        }
        return files;
    }

    public File getFile(String name) {
        // Identify images by hashcode or encode by URLEncoder.encode.
        File file = new File(dirName, name);
        file.mkdirs();
        return file;
    }

    public File getFile1(String name) {
        // Identify images by hashcode or encode by URLEncoder.encode.
        File file = new File(dirShareFiles, name);
        file.mkdirs();
        return file;
    }

    public String getFilePath(String name) {
        // Identify images by hashcode or encode by URLEncoder.encode.
        File file = new File(dirName, name);
        file.mkdirs();
        return file.getAbsolutePath();
    }

    public String getDirPath() {
        return dirName.getAbsolutePath();
    }

    public File getDir() {
        return dirName;
    }

}