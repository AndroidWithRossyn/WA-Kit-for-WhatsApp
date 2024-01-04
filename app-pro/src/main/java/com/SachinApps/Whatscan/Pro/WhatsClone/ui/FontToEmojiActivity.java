package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import static com.SachinApps.Whatscan.Pro.WhatsClone.ui.TextRepeaterActivity.hideSoftKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.AdsManager;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.ConstMethod;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.ascii_faces.AsciiFaceTextActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActFontToEmojiBinding;

import java.io.IOException;
import java.io.InputStream;

public class FontToEmojiActivity extends AppCompatActivity {
    ActFontToEmojiBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActFontToEmojiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Font to Emoji");


        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getBooleanValue(UserHelper.showInterstitial) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            Log.d("databseConfig", "load Inters");
            AdsManager.loadNativeAds(FontToEmojiActivity.this, binding.flAdplaceholder, UserHelper.getStringValue(UserHelper.nativeAdId));
        }

        binding.btnTransform.setOnClickListener(v -> {
            hideSoftKeyboard(FontToEmojiActivity.this);
            if (!TextUtils.isEmpty(binding.etText.getText().toString()) && !TextUtils.isEmpty(binding.etTextEmoji.getText().toString())) {
                char[] charArray = binding.etText.getText().toString().toCharArray();
                binding.etTextwithEmoji.setText(".\n");
                for (char c2 : charArray) {
                    if (c2 == '?') {
                        try {
                            InputStream open = getBaseContext().getAssets().open("ques.txt");
                            byte[] bArr = new byte[open.available()];
                            open.read(bArr);
                            open.close();
                            binding.etTextwithEmoji.append(new String(bArr).replaceAll("[*]", binding.etTextEmoji.getText().toString()) + "\n\n");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } else if (c2 == ((char) (c2 & '_')) || Character.isDigit(c2)) {
                        try {
                            InputStream open2 = getBaseContext().getAssets().open(c2 + ".txt");
                            byte[] bArr2 = new byte[open2.available()];
                            open2.read(bArr2);
                            open2.close();
                            binding.etTextwithEmoji.append(new String(bArr2).replaceAll("[*]", binding.etTextEmoji.getText().toString()) + "\n\n");
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    } else {
                        try {
                            InputStream open3 = getBaseContext().getAssets().open("low" + c2 + ".txt");
                            byte[] bArr3 = new byte[open3.available()];
                            open3.read(bArr3);
                            open3.close();
                            binding.etTextwithEmoji.append(new String(bArr3).replaceAll("[*]", binding.etTextEmoji.getText().toString()) + "\n\n");
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
        });

        binding.btnCopy.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.etTextwithEmoji.getText().toString())) {
                ConstMethod.CopyToClipBoard(FontToEmojiActivity.this, binding.etTextwithEmoji.getText().toString());
            } else {
                Toast.makeText(FontToEmojiActivity.this, "Empty string.", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnClear.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.etTextwithEmoji.getText().toString())) {
                binding.etTextwithEmoji.getText().clear();
            } else {
                Toast.makeText(FontToEmojiActivity.this, "Empty string.", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnShare.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.etTextwithEmoji.getText().toString())) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, binding.etTextwithEmoji.getText().toString());
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(FontToEmojiActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(FontToEmojiActivity.this, "Empty string.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}