package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.AdsManager;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.Common;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.fragment.FragHome;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActMainBinding;

import java.io.File;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    public static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_PERMISSIONS = 1234;
    ActMainBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            UserHelper.setRateDone(true);
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }
    });
    private boolean mIsDoubleClick = false;

    @Override
    public void onResume() {
        super.onResume();
        if (arePermissionDenied()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestPermissionQ();
            } else {
                requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS && grantResults.length > 0) {
            if (arePermissionDenied()) {
                ((ActivityManager) Objects.requireNonNull(this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
                recreate();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragHome()).commit();


        if (UserHelper.isNetworkConnected(MainActivity.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            Log.d("databseConfig", "load Ads");
            AdsManager.loadBannerAd(MainActivity.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));

        } else {
            binding.bannerContainer.setVisibility(View.GONE);
        }

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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void requestPermissionQ() {
        StorageManager sm = (StorageManager) getSystemService(Context.STORAGE_SERVICE);

        Intent intent = sm.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
        String startDir = "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses";

        Uri uri = intent.getParcelableExtra("android.provider.extra.INITIAL_URI");

        String scheme = uri.toString();
        scheme = scheme.replace("/root/", "/document/");
        scheme += "%3A" + startDir;

        uri = Uri.parse(scheme);

        Log.d("URI", uri.toString());

        intent.putExtra("android.provider.extra.INITIAL_URI", uri);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


        activityResultLauncher.launch(intent);
    }

    @Override
    public void onBackPressed() {
        doubleBackToExit();
    }

    private void doubleBackToExit() {
        if (mIsDoubleClick) {
            Intent intent = new Intent(MainActivity.this, ThankYouActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        this.mIsDoubleClick = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

//        new Handler().postDelayed(() -> mIsDoubleClick = false, 2000);
    }
}