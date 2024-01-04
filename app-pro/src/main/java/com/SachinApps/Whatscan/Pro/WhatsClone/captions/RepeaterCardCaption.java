package com.SachinApps.Whatscan.Pro.WhatsClone.captions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class RepeaterCardCaption extends FragmentStatePagerAdapter {
    private ArrayList<HashMap<String, String>> data;
    FragmentManager fragmentManager;
    public RepeaterCardCaption(FragmentManager fragmentManager, ArrayList<HashMap<String, String>> formList) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.data = formList;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentQuotView fragment = new FragmentQuotView();
        Bundle bundle = new Bundle();
        bundle.putInt("CurrentPosition", position);
        bundle.putSerializable("CurrentQuots", data.get(position).get("quoteText"));
        bundle.putSerializable("CurrentAuthor", data.get(position).get("quoteAuthor"));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
