package com.whatscan.whatsweb.whatzweb.whatwebscan.whatsapp_photos;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.RowStatusBinding;


public class ItemViewHolder extends RecyclerView.ViewHolder{

    public ImageButton save, share;
    public ImageView imageView;
    public ImageView ivVideo;

    public ItemViewHolder(@NonNull RowStatusBinding itemView) {
        super(itemView.getRoot());

        imageView = itemView.ivThumbnail;
        ivVideo = itemView.ivVideo;
        save = itemView.save;
        share = itemView.share;
    }
}