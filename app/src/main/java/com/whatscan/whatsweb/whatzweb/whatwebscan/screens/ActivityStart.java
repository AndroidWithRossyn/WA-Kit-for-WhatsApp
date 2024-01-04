package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.Common;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.Utility;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActStartBinding;

import java.io.File;

public class ActivityStart extends BaseActivity  {
    ActStartBinding binding;
    public static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActStartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.ivHrzBanner.setImageBitmap(Utility.decodeSampledBitmapFromResource(getResources(), R.drawable.ic_frame, 500, 500));

        UserHelper.setUserInSplashIntro(false);


        binding.linStart.setOnClickListener(view1 -> {
            if (UserHelper.isPermissionGrant()){
                toMain();
            }else {
                permissonDialog();
            }
        });


   }

    private void permissonDialog() {
        if (arePermissionDenied()) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.dialog_permission);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);

            TextView tvAllow = dialog.findViewById(R.id.tvAllow);
            TextView tvCancel = dialog.findViewById(R.id.tvCancel);


            tvAllow.setOnClickListener(view -> {
                toMain();
            });
            tvCancel.setOnClickListener(view -> {
                if (dialog.isShowing() && !isFinishing()) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        } else {
            toMain();
        }

    }

    private void toMain() {
        Intent mIntent = new Intent(ActivityStart.this, ActivityMain.class);
        startActivity(mIntent);
        finish();
    }

    private boolean arePermissionDenied() {
        Common.APP_DIR = Environment.getExternalStorageDirectory().getPath() +
                File.separator + "StatusDownloader";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getContentResolver().getPersistedUriPermissions().size() <= 0;
        }

        for (String permissions : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), permissions) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }

        return false;
    }
}