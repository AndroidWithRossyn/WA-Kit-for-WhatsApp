package com.whatscan.whatsweb.whatzweb.whatwebscan.screens;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActWhatsAppWebBinding;


public class ActivityWhatsAppWeb extends AppCompatActivity {
    ActWhatsAppWebBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActWhatsAppWebBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Whatsapp Web");

        binding.wvWhatsappWeb.loadUrl("https://web.whatsapp.com/");
        binding.wvWhatsappWeb.setWebViewClient(new WebViewClient());
        binding.wvWhatsappWeb.getSettings().setJavaScriptEnabled(true);
        binding.wvWhatsappWeb.getSettings().setUseWideViewPort(true);
        binding.wvWhatsappWeb.setWebChromeClient(new WebChromeClient());
        binding.wvWhatsappWeb.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0");
        binding.wvWhatsappWeb.getSettings().setGeolocationEnabled(true);
        binding.wvWhatsappWeb.getSettings().setDomStorageEnabled(true);
        binding.wvWhatsappWeb.getSettings().setDatabaseEnabled(true);
        binding.wvWhatsappWeb.getSettings().setSupportMultipleWindows(true);
//        wvWhatsappWeb.getSettings().setAppCacheEnabled(true);
        binding.wvWhatsappWeb.getSettings().setNeedInitialFocus(true);
        binding.wvWhatsappWeb.getSettings().setLoadWithOverviewMode(true);
        binding.wvWhatsappWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//wvWhatsappWeb.getSettings().setBlockNetworkLoads(true);
        binding.wvWhatsappWeb.getSettings().setBlockNetworkImage(true);
        binding.wvWhatsappWeb.getSettings().setBuiltInZoomControls(true);
        binding.wvWhatsappWeb.setInitialScale(100);
    }

    public void backclickWhatsAppWeb(View view) {
        onBackPressed();
    }
}