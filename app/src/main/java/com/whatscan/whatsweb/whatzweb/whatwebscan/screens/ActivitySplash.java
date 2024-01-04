package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.whatscan.whatsweb.whatzweb.whatwebscan.BuildConfig;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.Utility;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActSplashBinding;

public class ActivitySplash extends BaseActivity {
    ActSplashBinding binding;
    private static final int RC_APP_UPDATE = 11;

    private AppUpdateManager mAppUpdateManager;

    InstallStateUpdatedListener mInstallUpdated = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED) {
                        snackbarUpdateApp();
                    } else if (state.installStatus() == InstallStatus.INSTALLED) {
                        if (mAppUpdateManager != null) {
                            mAppUpdateManager.unregisterListener(mInstallUpdated);
                        }
                    }
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        if (UserHelper.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        binding.ivAppIcon.setImageBitmap(Utility.decodeSampledBitmapFromResource(getResources(), R.drawable.splash_preview, 500, 500));
        initRemoteConfig();
        UserHelper.setUserInSplashIntro(true);
        UserHelper.ads_per_session = 0;
        UserHelper.setReviewCount(0);


        inAppUpdate();
//        UserHelper.saveStringValue(UserHelper.nativeAdId, "ca-app-pub-3940256099942544/2247696110");

        toHome();
    }

    @Override
    public void onDestroy() {
        mAppUpdateManager.unregisterListener(mInstallUpdated);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserHelper.setUserInSplashIntro(true);
        try {
            mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(result -> {
                if (result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    try {
                        mAppUpdateManager.startUpdateFlowForResult(result, IMMEDIATE, ActivitySplash.this, RC_APP_UPDATE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            });
            mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    snackbarUpdateApp();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void snackbarUpdateApp() {
    }

    private void inAppUpdate() {

        mAppUpdateManager = AppUpdateManagerFactory.create(this);

        mAppUpdateManager.registerListener(mInstallUpdated);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)) {

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, IMMEDIATE, ActivitySplash.this, RC_APP_UPDATE);

                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                snackbarUpdateApp();
            }
        });
    }


    private void toHome() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(ActivitySplash.this, ActivityStart.class));
            finish();
        }, 2000);

    }

    public void initRemoteConfig() {
        Log.d("databseConfig", "Task enter");
        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Boolean> task) {
                if (task.isSuccessful()) {
                    boolean updated = task.getResult();
                    Log.d("databseConfig", "Config params updated: " + updated);
                    UserHelper.saveBooleanValue(UserHelper.Adshow, mFirebaseRemoteConfig.getBoolean(UserHelper.Adshow));
                    UserHelper.saveBooleanValue(UserHelper.showInterstitial, mFirebaseRemoteConfig.getBoolean(UserHelper.showInterstitial));
                    UserHelper.saveStringValue(UserHelper.bannerAdId, mFirebaseRemoteConfig.getString(UserHelper.bannerAdId));
                    UserHelper.saveStringValue(UserHelper.interstitialAdId, mFirebaseRemoteConfig.getString(UserHelper.interstitialAdId));
                    UserHelper.saveStringValue(UserHelper.nativeAdId, mFirebaseRemoteConfig.getString(UserHelper.nativeAdId));

                    Log.d("databseConfig", "onCreate: isAdShow " + UserHelper.getBooleanValue(UserHelper.Adshow));
                    Log.d("databseConfig", "onCreate: showInterstitial " + UserHelper.getBooleanValue(UserHelper.showInterstitial));
                    Log.d("databseConfig", "onCreate: banner_key " + UserHelper.getStringValue(UserHelper.bannerAdId));
                    Log.d("databseConfig", "onCreate: interstitial_ad_key " + UserHelper.getStringValue(UserHelper.interstitialAdId));

                } else {
                    Log.d("databseConfig", "onComplete: fetch failed");
                }
            }

        });
        mFirebaseRemoteConfig.fetchAndActivate().addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("databseConfig", "onComplete: fetch failed");
            }
        });
    }


}
