package com.SachinApps.Whatscan.Pro.WhatsClone.reply;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.ConstMethod;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.SharedPrefsHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.EventNotifier;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.EventState;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.IEventListener;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier.NotifierFactory;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActReplyBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReplyActivity extends AppCompatActivity implements IEventListener {
    ActReplyBinding binding;
    ReplyAdapter adapterReply;
    ArrayList<String> parentArrayList = new ArrayList<>();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActReplyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        registerAdsListener();



        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Replay");

        binding.replyRecycler.setLayoutManager(new LinearLayoutManager(ReplyActivity.this));

        notifyItems();

        binding.addFloatingBtn.setOnClickListener(v -> {

            ViewDialog alert = new ViewDialog();
            alert.showDialog(ReplyActivity.this);

        });
 }
    public void notifyItems() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        if (parentArrayList == null) {
            parentArrayList = new ArrayList<>();
        }

        parentArrayList.clear();

        parentArrayList.add("Hello...");
        parentArrayList.add("How are you ?");
        parentArrayList.add("I'm Good, What about you?");
        parentArrayList.add("I Love You, Baby...");
        parentArrayList.add("You are so impressive");
        parentArrayList.add("Today is holiday.");

        String strFromDatabase = SharedPrefsHelper.getInstance().getString("LOCAL_DATA", "[]");
        ArrayList<String> stringArrayList = gson.fromJson(strFromDatabase, type);

        for (int i = 0; i < stringArrayList.size(); i++) {
            parentArrayList.add(stringArrayList.get(i));
        }

        adapterReply = new ReplyAdapter(ReplyActivity.this, parentArrayList, new ReplyListener() {
            @Override
            public void onClickEdit(String s, int layoutrPosition) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(ReplyActivity.this,s,layoutrPosition);
            }

            @Override
            public void onClickCopy(String s) {
                ConstMethod.CopyToClipBoard(ReplyActivity.this,s);
            }

            @Override
            public void onClickShare(String s) {
                if (!TextUtils.isEmpty(s)) {
                    Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setType("text/plain");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT,s);
                    try {
                        startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ReplyActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ReplyActivity.this, "Empty string.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDelete(String s, int layoutrPosition) {
                ViewDialogDelete alert = new ViewDialogDelete();
                alert.showDialog(ReplyActivity.this,s,layoutrPosition);
            }
        });
        binding.replyRecycler.setAdapter(adapterReply);
    }

    public class ViewDialog {

        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.reply_dialogs);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            EditText etTextMsg = dialog.findViewById(R.id.etTextReplyMsg);
            Button dialogButton1 = dialog.findViewById(R.id.btnCancel);
            Button dialogButton2 = dialog.findViewById(R.id.btnSave);
            dialogButton1.setOnClickListener(v -> dialog.dismiss());
            dialogButton2.setOnClickListener(v -> {
                dialog.dismiss();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<String>>() {
                }.getType();

                String strFromDatabase = SharedPrefsHelper.getInstance().getString("LOCAL_DATA", "[]");
                ArrayList<String> stringArrayList = gson.fromJson(strFromDatabase, type);

                if (!TextUtils.isEmpty(etTextMsg.getText().toString())) {
                    stringArrayList.add(etTextMsg.getText().toString());
                    String saveData = gson.toJson(stringArrayList);
                    SharedPrefsHelper.getInstance().setString("LOCAL_DATA", saveData);

                    if (adapterReply != null) {
                        notifyItems();
                    }
                }else {
                    Toast.makeText(activity, "Please enter message..", Toast.LENGTH_SHORT).show();
                    return;
                }
            });

            dialog.show();

        }

        public void showDialog(ReplyActivity replyActivity, String s, int layoutrPosition) {
            final Dialog dialog = new Dialog(replyActivity);
            dialog.setContentView(R.layout.reply_dialogs);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            EditText etTextMsg = dialog.findViewById(R.id.etTextReplyMsg);
            etTextMsg.setText(s);
            Button dialogButton1 = dialog.findViewById(R.id.btnCancel);
            Button dialogButton2 = dialog.findViewById(R.id.btnSave);
            dialogButton1.setOnClickListener(v -> dialog.dismiss());
            dialogButton2.setOnClickListener(v -> {
                dialog.dismiss();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<String>>() {
                }.getType();

                String strFromDatabase = SharedPrefsHelper.getInstance().getString("LOCAL_DATA", "[]");
                ArrayList<String> stringArrayList = gson.fromJson(strFromDatabase, type);


                if (!TextUtils.isEmpty(etTextMsg.getText().toString())) {
                    stringArrayList.remove(layoutrPosition-6);
                    stringArrayList.add(layoutrPosition-6,etTextMsg.getText().toString());
//                        stringArrayList.add(etTextMsg.getText().toString());
                    String saveData = gson.toJson(stringArrayList);
                    SharedPrefsHelper.getInstance().setString("LOCAL_DATA", saveData);

                    if (adapterReply != null) {
                        notifyItems();
                    }
                }else {
                    Toast.makeText(replyActivity, "Please enter message..", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();
        }
    }
    public class ViewDialogDelete {

        public void showDialog(ReplyActivity replyActivity, String s, int layoutrPosition) {
            final Dialog dialog = new Dialog(replyActivity);
            dialog.setContentView(R.layout.delete_dialogs);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);


            Button btnCancel = dialog.findViewById(R.id.btnCancel);
            Button btnYes = dialog.findViewById(R.id.btnYes);
            btnCancel.setOnClickListener(v -> dialog.dismiss());
            btnYes.setOnClickListener(v -> {
                dialog.dismiss();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<String>>() {
                }.getType();

                String strFromDatabase = SharedPrefsHelper.getInstance().getString("LOCAL_DATA", "[]");
                ArrayList<String> stringArrayList = gson.fromJson(strFromDatabase, type);


                if (!TextUtils.isEmpty(s)) {
                    stringArrayList.remove(layoutrPosition-6);
                    String saveData = gson.toJson(stringArrayList);
                    SharedPrefsHelper.getInstance().setString("LOCAL_DATA", saveData);

                    if (adapterReply != null) {
                        notifyItems();
                    }
                }else {
                    Toast.makeText(replyActivity, "Please enter message..", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();
        }
    }

    public void backReply(View view) {
        onBackPressed();
    }
}