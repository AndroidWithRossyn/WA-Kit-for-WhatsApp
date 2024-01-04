package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.repeater;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.LayoutStickersBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.views.ViewSQRPics;

import java.io.File;
import java.util.List;

public class RepeaterStickr extends RecyclerView.Adapter<RepeaterStickr.ViewHolder> {

    private final List<String> appMdlBbblTxtsLisy;
    private ItemClickListener listener;

    public RepeaterStickr(List<String> appMdlBbblTxtsLisy) {
        this.appMdlBbblTxtsLisy = appMdlBbblTxtsLisy;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull LayoutStickersBinding itemBinding = LayoutStickersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int pos) {
        viewHolder._ivSticker.setImageBitmap(BitmapFactory.decodeFile(new File(this.appMdlBbblTxtsLisy.get(pos)).getAbsolutePath()));
        viewHolder.itemView.setTag(this.appMdlBbblTxtsLisy.get(pos));
    }


    @Override
    public int getItemCount() {
        return appMdlBbblTxtsLisy.size();
    }


    public void setClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(String path, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewSQRPics _ivSticker;

        public ViewHolder(LayoutStickersBinding itemView) {
            super(itemView.getRoot());
            _ivSticker = itemView.ivSticker;
            itemView.getRoot().setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(appMdlBbblTxtsLisy.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }
}