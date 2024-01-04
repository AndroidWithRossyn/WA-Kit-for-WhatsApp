package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActStickerBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.model.StickrMainOption;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.repeater.RepeaterStickrMainOpt;

import java.util.ArrayList;

public class ActivitySticker extends AppCompatActivity {

    ActStickerBinding binding;

    public final String NATIVE_AD = "NativeAd";

    public final static String STICKER_1 = "Baby";
    public final static String STICKER_2 = "Birthday";
    public final static String STICKER_3 = "Emoj";
    public final static String STICKER_4 = "Food";
    public final static String STICKER_5 = "Halloween";
    public final static String STICKER_6 = "Love";
    public final static String STICKER_7 = "Music";
    public final static String STICKER_8 = "Sale";
    public final static String STICKER_9 = "Social";
    public final static String STICKER_10 = "Transport";
    public final static String STICKER_11 = "Travel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActStickerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Sticker");


        binding.rvStickerOptions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        stickerMainAdapter();
    }

    private void stickerMainAdapter() {

        ArrayList<StickrMainOption> stickerParentList = createStickerParentList();
        RepeaterStickrMainOpt stickerCategoryListAdapter = new RepeaterStickrMainOpt(ActivitySticker.this, stickerParentList, (v, pos) -> {

            Intent intent = new Intent(ActivitySticker.this, ActStickerList.class);
            intent.putExtra("curName", stickerParentList.get(pos).getParentText());

                startActivity(intent);

        });

        binding.rvStickerOptions.setAdapter(stickerCategoryListAdapter);
    }


    private ArrayList<StickrMainOption> createStickerParentList() {
        String[] stickerListTitle = new String[0];
        Integer[] stickerListIcon = new Integer[0];

        if (UserHelper.isNetworkConnected(ActivitySticker.this)) {
            stickerListTitle = new String[]{STICKER_1, STICKER_2, STICKER_3, NATIVE_AD, STICKER_4, STICKER_5, STICKER_6, NATIVE_AD, STICKER_7, STICKER_8,
                    STICKER_9, NATIVE_AD, STICKER_10, STICKER_11};
            stickerListIcon = new Integer[]{R.drawable.e_sti_1, R.drawable.e_sti_2, R.drawable.e_sti_3, R.drawable.icon_circle, R.drawable.e_sti_4, R.drawable.e_sti_5, R.drawable.e_sti_6, R.drawable.icon_circle, R.drawable.e_sti_7, R.drawable.e_sti_8, R.drawable.e_sti_9, R.drawable.icon_circle, R.drawable.e_sti_10,
                    R.drawable.e_sti_11};
        } else {
            stickerListTitle = new String[]{STICKER_1, STICKER_2, STICKER_3, STICKER_4, STICKER_5, STICKER_6, STICKER_7, STICKER_8,
                    STICKER_9, STICKER_10, STICKER_11};
            stickerListIcon = new Integer[]{R.drawable.e_sti_1, R.drawable.e_sti_2, R.drawable.e_sti_3, R.drawable.e_sti_4, R.drawable.e_sti_5, R.drawable.e_sti_6, R.drawable.e_sti_7, R.drawable.e_sti_8, R.drawable.e_sti_9, R.drawable.e_sti_10,
                    R.drawable.e_sti_11};
        }

        ArrayList<StickrMainOption> stickerParentList = new ArrayList<>();

        for (int k = 0; k < stickerListIcon.length; k++) {
            StickrMainOption stickerParentMode3 = new StickrMainOption();
            stickerParentMode3.setParentIcon(getResources().getDrawable(stickerListIcon[k]));
            stickerParentMode3.setParentText(stickerListTitle[k]);
            stickerParentList.add(stickerParentMode3);
        }
        return stickerParentList;
    }
}