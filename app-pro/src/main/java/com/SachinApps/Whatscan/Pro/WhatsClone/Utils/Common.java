package com.SachinApps.Whatscan.Pro.WhatsClone.Utils;

import static com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UtilityMmTyp.getMimeType;
import static com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UtilityPhotoDecod.getRealPathFromURI;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.SachinApps.Whatscan.Pro.WhatsClone.model.StatusModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Common {

    public static final int GRID_COUNT = 2;
    public static final File STATUS_DIRECTORY = new File(Environment.getExternalStorageDirectory() +
            File.separator + "WhatsApp/Media/.Statuses");

    public static final String FOLDER_WHATSAPP_STATUS = ".Statuses";
    public static final File statusFolder = new File(Environment.getExternalStorageDirectory(), "WhatsApp/Media/" + FOLDER_WHATSAPP_STATUS);
    public static final File statusFolder11 = new File(Environment.getExternalStorageDirectory(), "Android/media/com.whatsapp/Whatsapp/Media/" + FOLDER_WHATSAPP_STATUS);

    public static final String KEY_PNG = ".png";
    public static String APP_DIR;
    static Bitmap bitmap = null;
    static String oldSavedFileName;
    static String savedImageUri;
    static Context mContext;

    public static void copyFile(StatusModel status, Context mContext, RelativeLayout container) {

        File file = new File(Common.APP_DIR);
        if (!file.exists()) {
            if (!file.mkdirs()) {
//                Snackbar.make(container, "Something went wrong", Snackbar.LENGTH_SHORT).show();
            }
        }

        String fileName;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());

        if (status.isVideo()) {

            fileName = "VID_" + currentDateTime + ".mp4";
        } else {
            fileName = "IMG_" + currentDateTime + ".jpg";
        }

        File destFile = new File(file + File.separator + fileName);

        try {

            if (status.isApi30()) {
                if (status.isVideo()) {
                    Context context = mContext;
                    String videoFileName = "video_" + System.currentTimeMillis() + ".mp4";

                    ContentValues valuesvideos;
                    valuesvideos = new ContentValues();
                    valuesvideos.put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM + File.separator + "Status Video");
//            valuesvideos.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/");
                    valuesvideos.put(MediaStore.Video.Media.TITLE, videoFileName);
                    valuesvideos.put(MediaStore.Video.Media.DISPLAY_NAME, videoFileName);
                    valuesvideos.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
                    valuesvideos.put(MediaStore.Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
                    valuesvideos.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
                    valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 1);
                    ContentResolver resolver = context.getContentResolver();
                    Uri collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //all video files on primary external storage
                    Uri uriSavedVideo = resolver.insert(collection, valuesvideos);

                    ParcelFileDescriptor pfd;

                    try {
                        pfd = context.getContentResolver().openFileDescriptor(uriSavedVideo, "w");

                        assert pfd != null;
                        FileOutputStream out = new FileOutputStream(pfd.getFileDescriptor());

                        // Get the already saved video as fileinputstream from here
                        InputStream in = mContext.getContentResolver().openInputStream(status.getDocumentFile().getUri());


                        byte[] buf = new byte[8192];

                        int len;
                        int progress = 0;
                        while ((len = in.read(buf)) > 0) {
                            progress = progress + len;

                            out.write(buf, 0, len);
                        }
                        out.close();
                        in.close();
                        pfd.close();
                        valuesvideos.clear();
                        valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 0);
                        valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 0); //only your app can see the files until pending is turned into 0

                        context.getContentResolver().update(uriSavedVideo, valuesvideos, null, null);
                        File file1 = new File(Environment.getExternalStorageDirectory() + File.separator + "/Movies/" + videoFileName);

                        Toast.makeText(mContext, "save successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    if (status.getDocumentFile().getUri() != null) {
                        bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), status.getDocumentFile().getUri());
                    }
                    new saveImageTask(mContext).execute();
                }
//                File fff = new File(status.getDocumentFile().getUri().getPath());
//                System.out.println("LOL: " + fff.getPath());
//                System.out.println("LOL 2: " + destFile.getPath());
//                List<UriPermission> list = context.getContentResolver().getPersistedUriPermissions();
//                copyFile(context, status.getDocumentFile().getUri().getPath(), Objects.requireNonNull(status.getDocumentFile().getName()),
//                        list.get(0).getUri());
            } else {
                org.apache.commons.io.FileUtils.copyFile(status.getFile(), destFile);
            }

            destFile.setLastModified(System.currentTimeMillis());
            new SingleMediaScanner(mContext, file);

//            showNotification(context, container, destFile, status);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String MoveStatusImage(File result, Context mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = mContext.getContentResolver();
            ContentValues valuesvideos = new ContentValues();
            valuesvideos.put(MediaStore.MediaColumns.DISPLAY_NAME, result.getName());
            String fileMimeType = getMimeType(result.getAbsolutePath());
            valuesvideos.put(MediaStore.MediaColumns.MIME_TYPE, fileMimeType);
            valuesvideos.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Status Photos");
            final Uri uriSavedVideo = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, valuesvideos);

            try {
                OutputStream out = resolver.openOutputStream(uriSavedVideo);
                File fileCaseWatchBD = result;
                FileInputStream in = new FileInputStream(fileCaseWatchBD);
                byte[] buf = new byte[50192];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
                fileCaseWatchBD.delete();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }

            if (uriSavedVideo != null) {
                String mSelectedImagePath = UtilityPhotoDecod.getRealPathFromURI(mContext, uriSavedVideo);
                return mSelectedImagePath;
            } else {
                Toast.makeText(mContext, "Please try again", Toast.LENGTH_SHORT).show();
                return "";
            }
        }
        return "";
    }

    public static class saveImageTask extends AsyncTask<String, String, Exception> {

        public saveImageTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Exception doInBackground(String... strings) {
            //get Filter applied image
            if (bitmap != null) {
                String OUTPUT_STATUS_FOLDER = new UtilityFile(mContext).getFile("WAStatus").getAbsolutePath();
                String fileName = getCurrentDateTime().replaceAll(":", "-").concat(KEY_PNG);
                File editForlder = new File(OUTPUT_STATUS_FOLDER);
                if (!editForlder.exists()) {
                    editForlder.mkdirs();
                }
                File file = new File(editForlder, fileName);
                oldSavedFileName = fileName;
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    savedImageUri = MoveStatusImage(file, mContext);

//                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                    intent.setData(Uri.parse(savedImageUri));
//                    sendBroadcast(intent);
                    return null;
                } catch (Exception e) {
                    return e;
                }
            } else {
                return new Exception("Image not Detacted");
            }
        }

        public String getCurrentDateTime() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }

        @Override
        protected void onPostExecute(Exception e) {
            super.onPostExecute(e);
//            if (mContext.isFinishing() || mContext.isDestroyed()) {
            // or call isFinishing() if min sdk version < 17
//                return;
//            }
//            if (progressDialog != null && progressDialog.isShowing())
//                progressDialog.dismiss();

            if (e == null) {
                Toast.makeText(mContext, "save successfully...", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
