package com.whatscan.whatsweb.whatzweb.whatwebscan.qr_code_reader;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.AdManager;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActQrcodeReaderBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.qr_code_maker.ActivityQRcodeMaker;

import java.util.ArrayList;
import java.util.Iterator;

public class ActivityQRcodeReader extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    ActQrcodeReaderBinding binding;
    public static final String EXTRA_QUERY = "query";
    public static final String TEXT_ENTRY = "text";
    private static final String TAG = "QRReaderActivity";

    public String barcode_result;
    protected int camera_id = -1;
    private ArrayList<Integer> selected_indices;
    public ViewGroup viewGroup;

    public ZXingScannerView zXingScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActQrcodeReaderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("QR Reader");


        init();
        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.isNetworkConnected(ActivityQRcodeReader.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            AdManager.loadBannerAd(ActivityQRcodeReader.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));
        } else {
            binding.bannerContainer.setVisibility(View.GONE);
        }
    }
    private void init() {
        viewGroup = (ViewGroup) findViewById(R.id.fl_camera);
        zXingScannerView = new ZXingScannerView(this);
        viewGroup.addView(zXingScannerView);
    }

    public void setupBarcodeFormats() {
        ArrayList arrayList = new ArrayList();

        if (selected_indices == null || selected_indices.isEmpty()) {
            selected_indices = new ArrayList<>();
            for (int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                selected_indices.add(Integer.valueOf(i));
            }
        }
        Iterator<Integer> it = selected_indices.iterator();
        while (it.hasNext()) {
            arrayList.add(ZXingScannerView.ALL_FORMATS.get(it.next().intValue()));
        }

        if (zXingScannerView != null) {
            zXingScannerView.setFormats(arrayList);
        }
    }

    @Override
    public void handleResult(Result result) {
        barcode_result = result.getText();
        new ToneGenerator(5, 100).startTone(24);
        final Dialog dialog = new Dialog(this, R.style.ThemeWithRoundShape);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_qr_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setLayout(-1, -2);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        TextView tv_search = dialog.findViewById(R.id.tv_search);
        TextView tv_result = dialog.findViewById(R.id.tv_result);

        if (barcode_result.startsWith("tel")) {
            tv_search.setText("Call");
        }
        tv_result.setText(barcode_result);

        ((TextView) dialog.findViewById(R.id.tv_share)).setOnClickListener(view -> {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/*");
            intent.putExtra("android.intent.extra.SUBJECT", "");
            intent.putExtra("android.intent.extra.TEXT", barcode_result);
            startActivity(Intent.createChooser(intent, "Share text using"));
            dialog.dismiss();
        });
        ((TextView) dialog.findViewById(R.id.tv_search)).setOnClickListener(view -> {
            Intent intent;
            if (barcode_result.startsWith("tel")) {
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(barcode_result));
            } else {
                intent = new Intent("android.intent.action.WEB_SEARCH");
                intent.setClassName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.SearchActivity");
                intent.putExtra(EXTRA_QUERY, barcode_result);
            }
            startActivity(intent);
            dialog.dismiss();
        });
        ((ImageView) dialog.findViewById(R.id.iv_close)).setOnClickListener(view -> {
            ((ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText(TEXT_ENTRY, barcode_result));
            Toast.makeText(ActivityQRcodeReader.this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
            if (zXingScannerView == null) {

                zXingScannerView = new ZXingScannerView(ActivityQRcodeReader.this);
                viewGroup.addView(zXingScannerView);
            }
            zXingScannerView.setResultHandler(ActivityQRcodeReader.this);
            zXingScannerView.startCamera(camera_id);
            setupBarcodeFormats();
            dialog.dismiss();
        });
        dialog.show();
    }

    @Override
    public void onResume() {
        if (zXingScannerView == null) {
            zXingScannerView = new ZXingScannerView(this);
            viewGroup.addView(zXingScannerView);
        }
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera(camera_id);
        setupBarcodeFormats();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        zXingScannerView.stopCamera();
        super.onDestroy();
    }
}