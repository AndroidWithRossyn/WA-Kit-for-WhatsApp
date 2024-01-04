package com.SachinApps.Whatscan.Pro.WhatsClone.ascii_faces.repeater;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.SachinApps.Whatscan.Pro.WhatsClone.ascii_faces.EmojisListener;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.RowAsciiFaceBinding;

public class RepeaterHappy extends RecyclerView.Adapter<RepeaterHappy.ViewHolder> {
    FragmentActivity requireActivity;
    String[] happyAsciiFace;
    private final EmojisListener nEmojiListener;

    public RepeaterHappy(FragmentActivity requireActivity, String[] happyAsciiFace, EmojisListener emojiListener) {
        this.requireActivity = requireActivity;
        this.happyAsciiFace = happyAsciiFace;
        this.nEmojiListener = emojiListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull RowAsciiFaceBinding itemBinding = RowAsciiFaceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvAscii.setText(happyAsciiFace[position]);
    }

    @Override
    public int getItemCount() {
        return happyAsciiFace.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowAsciiFaceBinding binding;

        public ViewHolder(@NonNull RowAsciiFaceBinding itemBinding) {
            super(itemBinding.getRoot());

            binding = itemBinding;

            itemBinding.ivWpShare.setOnClickListener(v -> {
                if (nEmojiListener != null) {
                    nEmojiListener.onWpShare(itemBinding.tvAscii.getText().toString());
                }
            });
            itemBinding.ivShare.setOnClickListener(v -> {
                if (nEmojiListener != null) {
                    nEmojiListener.onShare(itemBinding.tvAscii.getText().toString());
                }
            });
            itemBinding.ivCopy.setOnClickListener(v -> {
                if (nEmojiListener != null) {
                    nEmojiListener.onCopy(itemBinding.tvAscii.getText().toString());
                }
            });
        }

    }
}
