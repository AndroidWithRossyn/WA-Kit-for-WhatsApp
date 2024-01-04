package com.whatscan.whatsweb.whatzweb.whatwebscan.card_caption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.ConstMethod;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActCardCaptionBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityCardCaption extends AppCompatActivity {

    ActCardCaptionBinding binding;
    RepeaterCardCaption cardCaptionAdapter;
    ArrayList<HashMap<String, String>> formList;
    ArrayList<HashMap<String, String>> copyFormList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActCardCaptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Caption");



        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("quotes");
            int i1 = m_jArry.length();
            HashMap<String, String> m_li;
            formList = new ArrayList<>();
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                Log.d("Details-->", jo_inside.getString("quotes"));
                String formula_value = jo_inside.getString("quoteText");
                String url_value = jo_inside.getString("quoteAuthor");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("quoteText", formula_value);
                m_li.put("quoteAuthor", url_value);

                formList.add(m_li);

            }
            copyFormList = formList;
            cardCaptionAdapter = new RepeaterCardCaption(getSupportFragmentManager(), formList);
            binding.viewPager.setAdapter(cardCaptionAdapter);

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

        binding.btnCopy.setOnClickListener(v -> ConstMethod.CopyToClipBoard(ActivityCardCaption.this, copyFormList.get(binding.viewPager.getCurrentItem()).get("quoteText")));

        binding.btnShare.setOnClickListener(v -> {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, copyFormList.get(binding.viewPager.getCurrentItem()).get("quoteText"));
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ActivityCardCaption.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
        });

        binding.btnBack.setOnClickListener(v -> binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()-1));
        binding.btnNext.setOnClickListener(v -> binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1));
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = ActivityCardCaption.this.getAssets().open("quotes.json");
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

    public void backclickCaption(View view) {
        onBackPressed();
    }

//    private class CardCaptionAdapter extends FragmentStatePagerAdapter {
//
//        private ArrayList<HashMap<String, String>> data;
//        FragmentManager fragmentManager;
//
//        public CardCaptionAdapter(@NonNull FragmentManager fragmentManager, ArrayList<HashMap<String, String>> formList) {
//            this.fragmentManager = fragmentManager;
//            this.data = formList;
//        }
//
//        @Override
//        public int getCount() {
//            return data.size();
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup collection, int position) {
//
//            TextView textView;
//            TextView tvAuthor;
//            String quote;
//            String author;
//            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewLayout = inflater.inflate(R.layout.item_captions, collection, false);
//
//            textView = collection.findViewById(R.id.tvCaption);
//            tvAuthor = collection.findViewById(R.id.tvAuthor);
//
//            HashMap<String, String> hashmap = data.get(position);
//            quote = hashmap.get("quoteText");
//            author = hashmap.get("quoteAuthor");
//            try {
////                textView.setText(data.get(position).get("quoteText"));
////                tvAuthor.setText("- " + data.get(position).get("quoteAuthor"));
//                textView.setText(quote);
//                tvAuthor.setText(author);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.e("NullTextView",""+e.getMessage());
//            }
//            return viewLayout;
//        }
//
////        @Override
////        public void destroyItem(View collection, int position, Object view) {
////            ((ViewPager) collection).removeView((View) view);
////        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == ((RelativeLayout) object);
//        }
//
//    }
}