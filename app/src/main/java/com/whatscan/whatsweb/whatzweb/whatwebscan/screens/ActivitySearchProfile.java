package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.AdManager;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.EventNotifier;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.EventState;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.IEventListener;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.NotifierFactory;
import com.whatscan.whatsweb.whatzweb.whatwebscan.ascii_face.ActivityAsciiFaceText;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActSearchProfileBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.qr_code_reader.ActivityQRcodeReader;


public class ActivitySearchProfile extends AppCompatActivity {
    ActSearchProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSearchProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Search Profile");


        binding.btnSearchProfile.setOnClickListener(v -> {
            if (isValidMobile(binding.etMobileNumber.getText().toString())) {
                Uri uri = Uri.parse("smsto:" + binding.etMobileNumber.getText().toString());
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            } else {
                Toast.makeText(ActivitySearchProfile.this, "Enter valid Mobile Number..", Toast.LENGTH_SHORT).show();
            }

        });

        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.isNetworkConnected(ActivitySearchProfile.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            AdManager.loadBannerAd(ActivitySearchProfile.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));
            AdManager.loadNativeAds(ActivitySearchProfile.this, binding.flAdplaceholder, UserHelper.getStringValue(UserHelper.nativeAdId));

        } else {
            binding.bannerContainer.setVisibility(View.GONE);
        }
   }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public void backclickProfile(View view) {
        onBackPressed();
    }
}