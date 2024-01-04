package com.SachinApps.Whatscan.Pro.WhatsClone.fragment;

import static android.Manifest.permission.CAMERA;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.SachinApps.Whatscan.Pro.WhatsClone.BuildConfig;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.AdsManager;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.EventNotifier;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.EventState;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.IEventListener;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.NotifierFactory;
import com.SachinApps.Whatscan.Pro.WhatsClone.qr_maker.QRCodeMakerActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.qr_reader.QRCodeReaderActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.reply.ReplyActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.FontToEmojiActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.InfoWhatsAppActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.MainActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.WhatsAppWebActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.shayri.ShayriActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.sticker.StickerActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.wa_photos.WhatsappPhotosActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.wa_saved_items.SaveMediaActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.wa_videos.WhatsappVideosActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.ascii_faces.AsciiFaceTextActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.captions.CardCaptionActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.FragHomeBinding;
import com.SachinApps.Whatscan.Pro.WhatsClone.deleted.ui.main.WADeletedActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.SearchProfileActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.TextRepeaterActivity;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.SettingActivity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import games.moisoni.google_iab.BillingConnector;
import games.moisoni.google_iab.BillingEventListener;
import games.moisoni.google_iab.enums.ProductType;
import games.moisoni.google_iab.models.BillingResponse;
import games.moisoni.google_iab.models.ProductInfo;
import games.moisoni.google_iab.models.PurchaseInfo;

public class FragHome extends BaseFragment implements View.OnClickListener {

    private static final String TEMPFILES = "TEMPORARIESFILESALL";
    public long free = 0;
    public long total = 0;
    FragHomeBinding binding;

    private static final String PRODUCT_ID = "remove_ads_pro";

    public boolean isPro = false;

    //    set price according to subscription price
    private int purchase_price = 3;
    private BillingConnector billingConnector;

    public static void takePermission(Activity activity, int request_code) {
        ActivityCompat.requestPermissions(activity,
                new String[]{CAMERA}, request_code);
    }

    public static boolean isPermissionGranted(Activity activity) {
        int camera = ContextCompat.checkSelfPermission(activity, CAMERA);
        return camera == PackageManager.PERMISSION_GRANTED;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        isPro = UserHelper.getBooleanValue(UserHelper.IS_PRO);
        initializeBillingClient();



        binding.imgRemoveAds.setOnClickListener(this);
        binding.cardWhatsappStatusImage.setOnClickListener(this);
        binding.cardWhatsappStatusVideo.setOnClickListener(this);
        binding.cardWebScan.setOnClickListener(this);
        binding.cardSearchProfile.setOnClickListener(this);
        binding.cardRepeater.setOnClickListener(this);
        binding.cardQrGenerator.setOnClickListener(this);
        binding.cardQrCodeReader.setOnClickListener(this);
        binding.ivDots.setOnClickListener(this);
        binding.cardSaveMedia.setOnClickListener(this);
        binding.cardFontEmoji.setOnClickListener(this);
        binding.cardSticker.setOnClickListener(this);
        binding.cardReply.setOnClickListener(this);
        binding.cardCaption.setOnClickListener(this);
        binding.cardShayri.setOnClickListener(this);
        binding.cardAsciiFaceText.setOnClickListener(this);
        binding.cardInfoWhatsapp.setOnClickListener(this);
        binding.cardDeletedMessages.setOnClickListener(this);

        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getBooleanValue(UserHelper.showInterstitial) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            Log.d("databseConfig", "load Inters");
            AdsManager.loadInterAd(requireActivity(), UserHelper.getStringValue(UserHelper.interstitialAdId));

        }

        SharedPreferences sharedPreferences = activity.getSharedPreferences("phar_id", Context.MODE_PRIVATE);
        int memoryRange = sharedPreferences.getInt(TEMPFILES, (int) (Math.random() * 70) + 30);

        setProgressInAsync(binding.memoryPercantage, binding.memoryProgress, memoryRange, false);
        getMemorySize();
        parse();
        return view;
    }

    private void parse() {
        binding.freeRam.setText(calSize((double) this.free) + "");
        binding.ramTotal.setText(" / " + calSize((double) this.total));
    }

    private void getMemorySize() {
        Pattern compile = Pattern.compile("([a-zA-Z]+):\\s*(\\d+)");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
            while (true) {
                CharSequence readLine = randomAccessFile.readLine();
                if (readLine != null) {
                    Matcher matcher = compile.matcher(readLine);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        if (group.equalsIgnoreCase("MemTotal")) {
                            this.total = Long.parseLong(group2);
                        } else if (group.equalsIgnoreCase("MemFree") || group.equalsIgnoreCase("SwapFree")) {
                            this.free = Long.parseLong(group2);
                        }
                    }
                } else {
                    randomAccessFile.close();
                    this.total *= 1 << 10;
                    this.free *= 1 << 10;
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String calSize(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double d2 = d / 1048576.0d;
        double d3 = d / 1.073741824E9d;
        double d4 = d / 1.099511627776E12d;
        if (d4 > 1.0d) {
            return decimalFormat.format(d4).concat(" TB");
        }
        if (d3 > 1.0d) {
            return decimalFormat.format(d3).concat(" GB");
        }
        if (d2 > 1.0d) {
            return decimalFormat.format(d2).concat(" MB");
        }
        return decimalFormat.format(d).concat(" KB");
    }

    public void setProgressInAsync(final TextView percantage, final ProgressBar progressBar, final int progress, final boolean justNow) {
        new Thread(() -> {
            if (justNow) {
                activity.runOnUiThread(() -> {
                    percantage.setText(progress + "MB");
                    progressBar.setProgress(progress);
                });
            } else {
                int currentRange = 0;

                while (currentRange < progress) {
                    currentRange++;

                    final int finalCurrentMomoryRange = currentRange;

                    activity.runOnUiThread(() -> {
                        percantage.setText(finalCurrentMomoryRange + "MB");
                        progressBar.setProgress(finalCurrentMomoryRange);
                    });

                    try {
                        Thread.sleep(65);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgRemoveAds:
                initPurchaseDialog();
                break;
            case R.id.cardWhatsappStatusImage:
                Intent mIntent = new Intent(activity, WhatsappPhotosActivity.class);
                startActivityes(mIntent);
                break;
            case R.id.ivDots:
                Intent intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.cardSaveMedia:
                Intent intentSave = new Intent(activity, SaveMediaActivity.class);
                startActivityes(intentSave);
                break;
            case R.id.cardWhatsappStatusVideo:
                Intent intent0 = new Intent(activity, WhatsappVideosActivity.class);
                startActivityes(intent0);
                break;
            case R.id.cardSearchProfile:
                Intent intent4 = new Intent(activity, SearchProfileActivity.class);

                startActivityes(intent4);

                break;

            case R.id.cardRepeater:
                Intent intentText = new Intent(activity, TextRepeaterActivity.class);

                startActivityes(intentText);

                break;

            case R.id.cardWebScan:
                Intent intent21 = new Intent(requireActivity(), WhatsAppWebActivity.class);

                startActivityes(intent21);

                break;


            case R.id.cardQrGenerator:
                Intent intent2 = new Intent(activity, QRCodeMakerActivity.class);

                startActivityes(intent2);

                break;
            case R.id.cardQrCodeReader:
                Intent intent3 = new Intent(activity, QRCodeReaderActivity.class);
                if (!isPermissionGranted(requireActivity())) {
                    takePermission(activity, 111);
                } else {

                    startActivityes(intent3);

                }
                break;
            case R.id.cardFontEmoji:
                Intent intent000 = new Intent(requireActivity(), FontToEmojiActivity.class);

                startActivityes(intent000);

                break;
            case R.id.cardSticker:
                Intent intent222 = new Intent(requireActivity(), StickerActivity.class);

                startActivity(intent222);

                break;
            case R.id.cardReply:
                Intent intent333 = new Intent(requireActivity(), ReplyActivity.class);

                startActivity(intent333);

                break;
            case R.id.cardCaption:
                Intent intent444 = new Intent(requireActivity(), CardCaptionActivity.class);

                startActivity(intent444);

                break;
            case R.id.cardShayri:
                Intent intent5 = new Intent(requireActivity(), ShayriActivity.class);

                startActivity(intent5);

                break;
            case R.id.cardAsciiFaceText:
                Intent intent1 = new Intent(requireActivity(), AsciiFaceTextActivity.class);

                startActivityes(intent1);

                break;
            case R.id.cardDeletedMessages:
                Intent intent1sas = new Intent(requireActivity(), WADeletedActivity.class);

                startActivityes(intent1sas);

                break;
            case R.id.cardInfoWhatsapp:
                Intent intent6 = new Intent(requireActivity(), InfoWhatsAppActivity.class);

                startActivity(intent6);

                break;
        }
    }

    public void startActivityes(Intent intent) {
        AdsManager.adCounter++;
        AdsManager.showInterAd(requireActivity(), UserHelper.getStringValue(UserHelper.interstitialAdId), intent, 0);
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.fragmentManager = fragmentManager;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            FragmentPostersView fragment = new FragmentPostersView();
            Bundle bundle = new Bundle();
            bundle.putSerializable("CurrentPosition", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public void initPurchaseDialog() {
        final Dialog dialog = new Dialog(requireActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.app_purchase_dialog);
        dialog.getWindow()
                .setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.show();
        TextView txtPrice = dialog.findViewById(R.id.txtPrice);
        txtPrice.setText("" + purchase_price + "$");
        TextView txtPurchase = dialog.findViewById(R.id.txtPurchase);
        txtPurchase.setOnClickListener(view -> {
            removeAds();
            dialog.dismiss();
        });
        ImageView imageViewClose = dialog.findViewById(R.id.imageViewClose);
        imageViewClose.setOnClickListener(view -> dialog.dismiss());
    }

    private void removeAds() {
        billingConnector.purchase(requireActivity(), PRODUCT_ID);
    }

    private void initializeBillingClient() {
        List<String> nonConsumableIds = new ArrayList<>();
        nonConsumableIds.add(PRODUCT_ID);

        billingConnector = new BillingConnector(requireActivity(), BuildConfig.LICENSE_KEY)
                .setNonConsumableIds(nonConsumableIds)
                .autoAcknowledge()
                .enableLogging()
                .connect();

        billingConnector.setBillingEventListener(new BillingEventListener() {
            @Override
            public void onProductsFetched(@NonNull List<ProductInfo> productDetails) {

            }

            //this IS the listener in which we can restore previous purchases
            @Override
            public void onPurchasedProductsFetched(@NonNull ProductType productType, @NonNull List<PurchaseInfo> purchases) {
                String purchasedProduct;
                boolean isAcknowledged;

                for (PurchaseInfo purchaseInfo : purchases) {
                    purchasedProduct = purchaseInfo.getProduct();
                    isAcknowledged = purchaseInfo.isAcknowledged();

                    if (!isPro) {
                        if (purchasedProduct.equalsIgnoreCase(PRODUCT_ID)) {
                            if (isAcknowledged) {
                                isPro = true;
                                //here we are saving the purchase status into our "userPrefersAdFree" variable
                                UserHelper.saveBooleanValue(UserHelper.IS_PRO, true);
                                binding.imgRemoveAds.setVisibility(View.GONE);
//                                Toast.makeText(mActivity, "The previous purchase was successfully restored.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

            //this IS NOT the listener in which we'll give user entitlement for purchases (see ReadMe.md why)
            @Override
            public void onProductsPurchased(@NonNull List<PurchaseInfo> purchases) {

            }

            //this IS the listener in which we'll give user entitlement for purchases (the ReadMe.md explains why)
            @Override
            public void onPurchaseAcknowledged(@NonNull PurchaseInfo purchase) {
                String acknowledgedProduct = purchase.getProduct();

                if (acknowledgedProduct.equalsIgnoreCase(PRODUCT_ID)) {
                    isPro = true;
                    //here we are saving the purchase status into our "userPrefersAdFree" variable
                    UserHelper.saveBooleanValue(UserHelper.IS_PRO, true);
                    binding.imgRemoveAds.setVisibility(View.GONE);
                    reloadScreen();
                    Toast.makeText(requireActivity(), "The purchase was successfully made.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPurchaseConsumed(@NonNull PurchaseInfo purchase) {

            }

            @Override
            public void onBillingError(@NonNull BillingConnector billingConnector, @NonNull BillingResponse response) {
                switch (response.getErrorType()) {
                    case ACKNOWLEDGE_WARNING:
                        //this response will be triggered when the purchase is still PENDING
                        Toast.makeText(requireActivity(), "The transaction is still pending. Please come back later to receive the purchase!", Toast.LENGTH_SHORT).show();
                        break;
                    case BILLING_UNAVAILABLE:
                    case SERVICE_UNAVAILABLE:
                        Toast.makeText(requireActivity(), "Billing is unavailable at the moment. Check your internet connection!", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        Toast.makeText(requireActivity(), "Something happened, the transaction was canceled!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void reloadScreen() {
        //Reload the screen to activate the removeAd and remove the actual Ad off the screen.
        requireActivity().overridePendingTransition(0, 0);
        requireActivity().overridePendingTransition(0, 0);
        startActivity(new Intent(requireActivity(), MainActivity.class));
        requireActivity().finish();
    }
}