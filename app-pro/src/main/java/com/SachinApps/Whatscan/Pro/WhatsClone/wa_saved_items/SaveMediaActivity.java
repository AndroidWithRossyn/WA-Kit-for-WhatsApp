package com.SachinApps.Whatscan.Pro.WhatsClone.wa_saved_items;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActSaveMediaBinding;

public class SaveMediaActivity extends AppCompatActivity {

    ActSaveMediaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSaveMediaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Saved Media");

        FragmentSaveMedia newFragment = new FragmentSaveMedia();

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}