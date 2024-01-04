package com.SachinApps.Whatscan.Pro.WhatsClone.qr_maker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.AdsManager;
import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.UserHelper;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActQrcodeMakerBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCodeMakerActivity extends AppCompatActivity implements View.OnClickListener  {

    ActQrcodeMakerBinding binding;
    private static final String TAG = "QRGenerator";
    private Bitmap qrImg;
    private File qrPath;
    public String qrType;
    protected String qr_value;
    private String[] spinner_item = {"Text", "E-mail", "Phone", "Sms", "Url_Key"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActQrcodeMakerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("QR Generator");

        init();

        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.isNetworkConnected(QRCodeMakerActivity.this) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            AdsManager.loadBannerAd(QRCodeMakerActivity.this, binding.bannerContainer, UserHelper.getStringValue(UserHelper.bannerAdId));
        } else {
            binding.bannerContainer.setVisibility(View.GONE);
        }

   }
    private void init() {
        qrType = Contents.Type.TEXT;
        initSpinner();
    }

    private void initSpinner() {
        binding.qrtypeSpinner.setAdapter(new CustomSpinnerAdapter(spinner_item));
        binding.qrtypeSpinner.setPopupBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        binding.qrtypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    qrType = Contents.Type.TEXT;
                    binding.etValue.setHint("Enter your text");
                    binding.etValue.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (i == 1) {
                    qrType = Contents.Type.EMAIL;
                    binding.etValue.setHint("Enter your e-mail");
                    binding.etValue.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                } else if (i == 2) {
                    qrType = Contents.Type.PHONE;
                    binding.etValue.setHint("Enter your phone");
                    binding.etValue.setInputType(InputType.TYPE_CLASS_PHONE);
                } else if (i == 3) {
                    qrType = Contents.Type.SMS;
                    binding.etValue.setHint("Enter your sms");
                    binding.etValue.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (i == 4) {
                    qrType = Contents.URL_KEY;
                    binding.etValue.setHint("Enter your url_key");
                    binding.etValue.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    qrType = Contents.Type.TEXT;
                    binding.etValue.setHint("Enter your text");
                    binding.etValue.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
        private String[] array;

        @Override
        public long getItemId(int i) {
            return (long) i;
        }

        public CustomSpinnerAdapter(String[] strArr) {
            array = strArr;
        }

        @Override
        public int getCount() {
            return array.length;
        }

        @Override
        public Object getItem(int i) {
            return array[i];
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "inter_medium.ttf");
            TextView textView = new TextView(QRCodeMakerActivity.this);
            textView.setText(array[i]);
            textView.setTextSize(14.0f);
            textView.setTypeface(createFromAsset);
//            textView.setBackground(getResources().getDrawable(R.drawable.round_shape));
//            textView.setBackgroundTintList(new ColorStateList(new int[][]{new int[0]}, new int[]{getResources().getColor(R.color.light_txt_color)}));
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setGravity(17);
            textView.setPadding(43, 15, 43, 15);
            return textView;
        }

        @Override
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "inter_medium.ttf");
            TextView textView = new TextView(QRCodeMakerActivity.this);
            textView.setText(array[i]);
            textView.setTextSize(16.0f);
            textView.setTypeface(createFromAsset);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setGravity(16);
            textView.setPadding(60, 35, 0x00A0, 35);
            return textView;
        }
    }

    @Override
    public void onClick(View view) {
        Uri uri;
        switch (view.getId()) {
            case R.id.iv_generate:
                qr_value = binding.etValue.getText().toString();
                hideSoftKeyboard(view);
                if (qr_value.length() > 0) {
                    Display defaultDisplay = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                    Point point = new Point();
                    defaultDisplay.getSize(point);
                    int x = point.x;
                    int y = point.y;
                    if (x >= y) {
                        x = y;
                    }
                    try {

                        Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
                        intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
                        intent.putExtra(Intents.Encode.TYPE, qrType);
                        intent.putExtra(Intents.Encode.DATA, qr_value);
                        QRCodeEncoders qRCodeEncoder = new QRCodeEncoders(this, intent, (x * 3) / 4, false);
                        Log.e(TAG, "onClick: " + qrType);
                        qrImg = qRCodeEncoder.encodeAsBitmap();
                        binding.ivQrcode.setVisibility(View.VISIBLE);
                        binding.ivQrcode.setImageBitmap(qrImg);
                        binding.ivGenerate.setVisibility(View.GONE);
                        binding.ivRefresh.setVisibility(View.VISIBLE);
                        if(qrImg!=null)saveBitmap();
                        return;
                    } catch (WriterException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    binding.etValue.setError("Required");
                    return;
                }
            case R.id.iv_qrcode:
                try {
                    File file = new File(qrPath.getPath());
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("image/*");
                    if (Build.VERSION.SDK_INT >= 23) {
                        uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                    } else {
                        uri = Uri.fromFile(file);
                    }
                    intent.putExtra("android.intent.extra.STREAM", uri);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(intent, "Share image using"));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case R.id.iv_refresh:
                binding.etValue.setText("");
                binding.ivQrcode.setVisibility(View.GONE);
                binding.ivRefresh.setVisibility(View.GONE);
                binding.ivGenerate.setVisibility(View.VISIBLE);

                return;
            default:
                return;
        }
    }

    private File makeDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + getResources().getString(R.string.app_name) + "/QRCode/");
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        File file2 = new File(file.getPath() + File.separator + "temp.jpg");
        qrPath = file2;
        return file2;
    }

    public void saveBitmap() {
        File makeDir = makeDir();
        if (makeDir != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(makeDir);
                qrImg.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void hideSoftKeyboard(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}