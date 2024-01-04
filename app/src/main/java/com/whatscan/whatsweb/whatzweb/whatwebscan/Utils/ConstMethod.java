package com.whatscan.whatsweb.whatzweb.whatwebscan.Utils;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.io.File;

public class ConstMethod {

    public static void CopyToClipBoard(Activity activity ,String copyStr){
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(copyStr);
        Toast.makeText(activity, "Copy Succesfully..", Toast.LENGTH_SHORT).show();
    }

    public static String getStickerPath(Activity appCompatActivity) {
        String path = appCompatActivity.getFilesDir().getAbsolutePath();
        StringBuilder builder = new StringBuilder();
        builder.append(path);
        builder.append(File.separator);
        builder.append("Sticker");
        File file = new File(builder.toString());
        if (!file.exists()) {
            file.mkdir();
        }
        builder = new StringBuilder();
        builder.append(file.getAbsolutePath());
        String s = builder.toString();

        return s;
    }
}
