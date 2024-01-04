package com.whatscan.whatsweb.whatzweb.whatwebscan.shayri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.ConstMethod;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActShayriBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityShayri extends AppCompatActivity {

    ActShayriBinding binding;
    RepeaterShayri ShayriAdapter;
    ArrayList<String> formList;
    ArrayList<String> copyFormList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActShayriBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Shayari");


        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("shayris");
            int i1 = m_jArry.length();
            HashMap<String, String> m_li;
            formList = new ArrayList<>();
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String formula_value = jo_inside.getString("shayriText");

                formList.add(formula_value);

            }
            copyFormList = formList;
            ShayriAdapter = new RepeaterShayri(getSupportFragmentManager(), formList);
            binding.viewPager.setAdapter(ShayriAdapter);

            binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            binding.viewPager.setOffscreenPageLimit(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.btnCopy.setOnClickListener(v -> ConstMethod.CopyToClipBoard(ActivityShayri.this, copyFormList.get(binding.viewPager.getCurrentItem())));
        binding.btnShare.setOnClickListener(v -> {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, copyFormList.get(binding.viewPager.getCurrentItem()));
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ActivityShayri.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
        });

        binding.btnBack.setOnClickListener(v -> binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()-1));
        binding.btnNext.setOnClickListener(v -> binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1));
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = ActivityShayri.this.getAssets().open("shayri.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void backclickShayri(View view) {
        onBackPressed();
    }
}