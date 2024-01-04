package com.SachinApps.Whatscan.Pro.WhatsClone.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.Locale;

public class AdsManager {
    public static int adCounter = 3;
    public static int adDisplayCounter = 5;
    static AdView gadView;
    static InterstitialAd mInterstitialAd;
    static boolean NativeVideo = true;

    public static void loadBannerAd(Context context, LinearLayout adContainer, String AdId) {
        gadView = new AdView(context);
        gadView.setAdUnitId(AdId);
        adContainer.addView(gadView);
        loadBanner(context);
    }

    static void loadBanner(Context context) {
        AdRequest adRequest =
                new AdRequest.Builder().build();

        AdSize adSize = getAdSize((Activity) context);
        gadView.setAdSize(adSize);
        gadView.loadAd(adRequest);
    }

    static AdSize getAdSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    public static void adptiveBannerAd(Context context, LinearLayout adContainer, String AdId) {
        AdView adView = new AdView(context);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdSize(AdSize.LARGE_BANNER);
        adView.setAdUnitId(AdId);
        adView.loadAd(adRequest);
        adContainer.addView(adView);
    }


    public static void loadInterAd(Context context, String AdId) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, AdId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                Log.d("databseConfig", "an ad is loaded");
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });
    }

    public static void showInterAd(final Activity context, String AdId, final Intent intent, final int requstCode) {
        if (adCounter == adDisplayCounter && mInterstitialAd != null) {
            adCounter = 1;
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
//                    Log.d("TAG", "The ad was dismissed.");
                    loadInterAd(context, AdId);
                    startActivity(context, intent, requstCode);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
//                     Called when fullscreen content failed to show.
                    Log.d("databseConfig", "The ad failed to show.");
                }


                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                    Log.d("databseConfig", "The ad was shown.");
                }
            });
            mInterstitialAd.show((Activity) context);
        } else {
            if (adCounter == adDisplayCounter) {
                adCounter = 1;
            }
            startActivity(context, intent, requstCode);
        }
    }

    public static void showInterAd(final Fragment context, String AdId, final Intent intent, final int requstCode) {
        if (adCounter == adDisplayCounter && mInterstitialAd != null) {
            adCounter = 1;
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    Log.d("databseConfig", "The ad was dismissed.");
                    loadInterAd(context.getActivity(), AdId);
                    startActivity(context, intent, requstCode);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
//                     Called when fullscreen content failed to show.
                    Log.d("databseConfig", "The ad failed to show.");
                }


                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
//                    Log.d("TAG", "The ad was shown.");
                }
            });
            mInterstitialAd.show(context.getActivity());
        } else {
            if (adCounter == adDisplayCounter) {
                adCounter = 1;
            }
            startActivity(context, intent, requstCode);
        }
    }

    static void startActivity(Activity context, Intent intent, int requestCode) {
        if (intent != null) {
            Log.d("databseConfig", "ads number " + adCounter);
            context.startActivityForResult(intent, requestCode);
        }
    }


    static void startActivity(Fragment context, Intent intent, int requestCode) {
        if (intent != null) {
            context.startActivityForResult(intent, requestCode);
        }
    }
    public static void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Set the media view.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every NativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every NativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getMediaContent().getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (nativeAd.getMediaContent() != null && nativeAd.getMediaContent().hasVideoContent()) {

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
//                    refresh.setEnabled(true);
                    Log.d("databseConfig", "Video status: Ad contain a video asset.");
                    super.onVideoEnd();
                }
            });
        } else {
            Log.d("databseConfig", "Video status: Ad does not contain a video asset.");

        }
    }

    public static void loadNativeAds(Activity activity, FrameLayout frameLayout, final String AdId) {

        AdLoader.Builder builder = new AdLoader.Builder((Context) activity, AdId);

        // OnLoadedListener implementation.
        builder.forNativeAd(
                nativeAd -> {
                    // If this callback occurs after the activity is destroyed, you must call
                    // destroy and return or you may get a memory leak.
                    boolean isDestroyed = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        Log.d("databseConfig", "Native status: Build.VERSION_CODES.JELLY_BEAN_MR1");
                        isDestroyed = activity.isDestroyed();
                    }
                    if (isDestroyed || activity.isFinishing() || activity.isChangingConfigurations()) {
                        Log.d("databseConfig", "Native status: isDestroyed or isFinishing() or isChangingConfigurations()");
                        nativeAd.destroy();
                        return;
                    }
                    // You must call destroy on old ads when you are done with them,
                    // otherwise you will have a memory leak.
//                    if (nativeAd != null) {
//                        nativeAd.destroy();
//                    }


                    frameLayout.removeAllViews();
//                        MainActivity.this.nativeAd = nativeAd;
                    NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.ads_native_full, frameLayout, false);
                    populateNativeAdView(nativeAd, adView);

                    Log.d("databseConfig", "Native status: Ad loaded.");
                    frameLayout.addView(adView);
                });

        VideoOptions videoOptions =
                new VideoOptions.Builder().setStartMuted(NativeVideo).build();

        NativeAdOptions adOptions =
                new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(
                        new AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                String error = String.format(Locale.getDefault(), "domain: %s, code: %d, message: %s", loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                                Toast.makeText((Context) activity, "Failed to load native ad with error " + error, Toast.LENGTH_SHORT).show();
                                Log.d("databseConfig", "Failed to load native ad with error " + error);
                            }
                        })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());


    }

}
