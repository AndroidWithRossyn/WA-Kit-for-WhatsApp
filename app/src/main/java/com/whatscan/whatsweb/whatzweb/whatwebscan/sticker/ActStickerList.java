package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.ConstMethod;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActStickerListBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.repeater.RepeaterStickr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ActStickerList extends AppCompatActivity {

    ActStickerListBinding binding;
    String currentName;
    int nLastTimeInertedSticker;
    private ArrayList<String> nStickerArrayList = new ArrayList<>();
    RepeaterStickr nStickerRepeater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActStickerListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        currentName = getIntent().getStringExtra("curName");

        binding.tvTitle.setText(currentName);

        nLastTimeInertedSticker = 0;
        String zipName = getZipName(currentName);
        loadStaticSticker(zipName);
        nStickerArrayList.clear();
        nStickerArrayList = loadStickerStorage();
        binding.rvStickerOptions.setLayoutManager(new GridLayoutManager(ActStickerList.this,4, RecyclerView.VERTICAL,false));
        nStickerRepeater = new RepeaterStickr(nStickerArrayList);
        binding.rvStickerOptions.setAdapter(nStickerRepeater);

        nStickerRepeater.setClickListener((path, position) -> {

            nLastTimeInertedSticker = nLastTimeInertedSticker + 1;

            Bitmap decodeFile = BitmapFactory.decodeFile(new File(path).getAbsolutePath());
            Drawable drawable = new BitmapDrawable(getResources(), decodeFile);

            onClickApp("com.whatsapp",decodeFile);
        });

    }

    public void onClickApp(String pack, Bitmap bitmap) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ActStickerList.this.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);


            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("image/*");
            waIntent.setPackage(pack);
            waIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            ActStickerList.this.startActivity(Intent.createChooser(waIntent, "Share with"));
        } catch (Exception e) {
            Log.e("Error on sharing", e + " ");
            Toast.makeText(ActStickerList.this, "App not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getZipName(String pos) {
        String zipName = null;
        if (pos.equalsIgnoreCase(ActivitySticker.STICKER_1)) {
            zipName = "sti__bb.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_2)) {
            zipName = "sti__b_day.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_3)) {
            zipName = "sti__cartton.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_4)) {
            zipName = "sti__eat.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_5)) {
            zipName = "sti__hawolinn.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_6)) {
            zipName = "sti__couple.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_7)) {
            zipName = "sti__sound.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_8)) {
            zipName = "sti__sl.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_9)) {
            zipName = "sti__media.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_10)) {
            zipName = "sticker_transp.zip";
        } else if (pos.equalsIgnoreCase(ActivitySticker.STICKER_11)) {
            zipName = "sticker_travel.zip";
        }
        return zipName;
    }

    private void loadStaticSticker(String zipName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ConstMethod.getStickerPath(this));
        if (new File(stringBuilder.toString()).exists()) {
            for (File file2 : new File(stringBuilder.toString()).listFiles()) {
                if (!file2.getAbsolutePath().contains("thumb")) {
                    file2.delete();
                }
            }
        }
        if (new File(stringBuilder.toString()).exists()) {
            try {
                InputStream open = getAssets().open(zipName);
                stringBuilder = new StringBuilder();
                stringBuilder.append(ConstMethod.getStickerPath(this));
                boolean b = unZipFile(open, stringBuilder.toString());
                Log.e("FileZipOperation", String.valueOf(b));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean unZipFile(InputStream inputStream, String str) {
        byte[] bArr = new byte[2048];
        try {
            ZipInputStream nZipInputStream = new ZipInputStream(inputStream);
            while (true) {
                ZipEntry nextEntry = nZipInputStream.getNextEntry();
                if (nextEntry != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unzipping ");
                    stringBuilder.append(nextEntry.getName());
                    Log.e("FileZipOperation", stringBuilder.toString());
                    if (nextEntry.isDirectory()) {
                        Log.e("FileZipOperation", str + " " + nextEntry.getName());
                    } else {
                        File file = new File(str, nextEntry.getName());
                        if (!file.exists()) {
                            if (file.createNewFile()) {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                while (true) {
                                    int read = nZipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                nZipInputStream.closeEntry();
                                fileOutputStream.close();
                            } else {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Failed to create file ");
                                stringBuilder.append(file.getName());
                                Log.e("FileZipOperation", stringBuilder.toString());
                            }
                        }
                    }
                } else {
                    nZipInputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> loadStickerStorage() {
        ArrayList<String> nStickerArrayList = new ArrayList<>();
        nStickerArrayList.clear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ConstMethod.getStickerPath(this));
        File file = new File(stringBuilder.toString());
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.getAbsolutePath().contains("thumb")) {
                    nStickerArrayList.add(file2.getAbsolutePath());
                }
            }
        }
        return nStickerArrayList;
    }

    public void backclickStickerList(View view) {
        onBackPressed();
    }
}