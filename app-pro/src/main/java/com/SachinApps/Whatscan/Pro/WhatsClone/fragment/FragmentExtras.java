package com.SachinApps.Whatscan.Pro.WhatsClone.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.EventNotifier;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.EventState;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.IEventListener;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.NotifierFactory;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.FragExtrasBinding;
import com.SachinApps.Whatscan.Pro.WhatsClone.ui.AboutUsActivity;


public class FragmentExtras extends Fragment implements View.OnClickListener, IEventListener {

    FragExtrasBinding binding;

    @Override
    public int eventNotify(int eventType, final Object eventObject) {
        int eventState = EventState.EVENT_IGNORED;
        if (eventType == EventState.EVENT_AD_LOADED_NATIVE) {
            eventState = EventState.EVENT_PROCESSED;
     }

        return eventState;
    }

    private void registerAdsListener() {
        EventNotifier notifier = NotifierFactory.getInstance().getNotifier(NotifierFactory.EVENT_NOTIFIER_AD_STATUS);
        notifier.registerListener(this, 1000);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragExtrasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.tvAboutUs.setOnClickListener(this);
        binding.tvMore.setOnClickListener(this);
        binding.ShareNow.setOnClickListener(this);
        binding.tvRateUs.setOnClickListener(this);


        if(UserHelper.getTheme()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        FragHome.ViewPagerAdapter adapter = new FragHome.ViewPagerAdapter(requireActivity().getSupportFragmentManager());
        binding.vpPosters.setAdapter(adapter);
        binding.arcPi.setViewPager(binding.vpPosters);
        binding.vpPosters.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.switchTheme.setChecked(UserHelper.getTheme());
        binding.switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b) {
                    UserHelper.setTheme(true);
                    binding.switchTheme.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    UserHelper.setTheme(false);
                    binding.switchTheme.setChecked(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        registerAdsListener();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
 }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAboutUs:
                startActivity(new Intent(requireActivity(), AboutUsActivity.class));
                break;
            case R.id.tvMore:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(R.string.more_apps)));
                startActivity(browserIntent);
                break;
            case R.id.ShareNow:
                ShareApp(requireActivity());
                break;
            case R.id.tvRateUs:
                RateApp(requireActivity());
                break;
        }
    }

    public void ShareApp(Context context) {
        final String appLink = "\nhttps://play.google.com/store/apps/details?id=" + context.getPackageName();
        Intent sendInt = new Intent(Intent.ACTION_SEND);
        sendInt.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        sendInt.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_app_message) + appLink);
        sendInt.setType("text/plain");
        context.startActivity(Intent.createChooser(sendInt, "Share"));
    }

    public void RateApp(Context context) {
        final String appName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
        }
    }
}