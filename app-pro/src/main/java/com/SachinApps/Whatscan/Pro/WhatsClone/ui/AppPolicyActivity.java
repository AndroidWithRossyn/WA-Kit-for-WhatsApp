package com.SachinApps.Whatscan.Pro.WhatsClone.ui;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActAppPolicyBinding;

public class AppPolicyActivity extends AppCompatActivity {
    ActAppPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActAppPolicyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.ivBack.setOnClickListener(view1 -> onBackPressed());

        String url = "https://pages.flycricket.io/whatsapp-tools-5/privacy.html";

        binding.wvPrivacyPolicy.getSettings().setJavaScriptEnabled(true);
        binding.wvPrivacyPolicy.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
//        wvPrivacyPolicy.getSettings().setAppCacheEnabled(true);
        binding.wvPrivacyPolicy.getSettings().setDatabaseEnabled(true);
        binding.wvPrivacyPolicy.getSettings().setDomStorageEnabled(true);
        binding.wvPrivacyPolicy.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        binding.wvPrivacyPolicy.setWebViewClient(new WebViewClient() {

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                String e = error.toString();
                System.out.println("ERROR " + e);
                handler.cancel();
            }
        });
        binding.wvPrivacyPolicy.loadUrl(url);
    }
}