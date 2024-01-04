package com.SachinApps.Whatscan.Pro.WhatsClone.ascii_faces;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.ConstMethod;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.ascii_faces.repeater.RepeaterHappy;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActSubAsciiFaceTextBinding;

import java.util.ArrayList;

public class SubAsciiFaceTextActivity extends AppCompatActivity {
    ActSubAsciiFaceTextBinding binding;
    RepeaterHappy adapter;
    ArrayList<String> happyAsciiFace;
    String[] AsciiFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSubAsciiFaceTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Ascii Face");

        AsciiFace = getIntent().getStringArrayExtra("AsciiFace");

        happyAsciiFace = getEmojis(SubAsciiFaceTextActivity.this);

        binding.firdtRecycler.setLayoutManager(new LinearLayoutManager(SubAsciiFaceTextActivity.this, RecyclerView.VERTICAL, false));

        adapter = new RepeaterHappy(SubAsciiFaceTextActivity.this, AsciiFace, new EmojisListener() {
            @Override
            public void onWpShare(String emojiUnicode) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, emojiUnicode);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SubAsciiFaceTextActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(SubAsciiFaceTextActivity.this, "direct wp share", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShare(String emojiUnicode) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
                intent.putExtra(Intent.EXTRA_TEXT, emojiUnicode);
                startActivity(Intent.createChooser(intent, getString(R.string.share_using)));
                Toast.makeText(SubAsciiFaceTextActivity.this, "share", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCopy(String emojiUnicode) {
                ConstMethod.CopyToClipBoard(SubAsciiFaceTextActivity.this,emojiUnicode);
            }
        });
        binding.firdtRecycler.setAdapter(adapter);
    }

    public static ArrayList<String> getEmojis(Context context) {
        ArrayList<String> nConvertedEmojiList = new ArrayList<>();
        String[] nEmojiList = context.getResources().getStringArray(R.array.emoji);
        for (String emojiUnicode : nEmojiList) {
            nConvertedEmojiList.add(convertEmoji(emojiUnicode));
        }
        return nConvertedEmojiList;
    }
    private static String convertEmoji(String emoji) {
        String nReturnedEmoji;
        try {
            int convertEmojiToInt = Integer.parseInt(emoji.substring(2), 16);
            nReturnedEmoji = new String(Character.toChars(convertEmojiToInt));
        } catch (NumberFormatException e) {
            nReturnedEmoji = "";
        }
        return nReturnedEmoji;
    }

    public void backSubAscii(View view) {
        onBackPressed();
    }
}