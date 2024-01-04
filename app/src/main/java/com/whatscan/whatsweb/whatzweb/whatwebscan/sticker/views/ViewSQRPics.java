package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class ViewSQRPics extends AppCompatImageView {

    public ViewSQRPics(Context context) {
        super(context);
    }

    public ViewSQRPics(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewSQRPics(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}