package com.whatscan.whatsweb.whatzweb.whatwebscan.reply;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.RowReplyMsgBinding;

import java.util.ArrayList;

public class RepeaterReply extends RecyclerView.Adapter<RepeaterReply.ViewHolder> {

    private final Activity activity;
    private ReplyListener replyListener;
    private ArrayList<String> parentArrayList = new ArrayList<>();

    public RepeaterReply(Activity activity, ArrayList<String> parentArrayList, ReplyListener replyListener) {
        this.activity = activity;
        this.parentArrayList = parentArrayList;
        this.replyListener = replyListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull RowReplyMsgBinding itemBinding = RowReplyMsgBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position > 5) {
            holder.ivEdit.setVisibility(View.VISIBLE);
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder.ivEdit.setVisibility(View.GONE);
            holder.ivDelete.setVisibility(View.GONE);
        }
        holder.tvAscii.setText(parentArrayList.get(position));


    }

    @Override
    public int getItemCount() {
        return parentArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAscii;
        ImageView ivWpShare;
        ImageView ivEdit;
        ImageView ivCopy;
        ImageView ivDelete;

        public ViewHolder(@NonNull RowReplyMsgBinding itemView) {
            super(itemView.getRoot());

            tvAscii = itemView.tvAscii;
            ivWpShare = itemView.ivWpShare;
            ivEdit = itemView.ivEdit;
            ivDelete = itemView.ivDelete;
            ivCopy = itemView.ivCopy;

            ivEdit.setOnClickListener(v -> {
                if (replyListener != null) {
                    replyListener.onClickEdit(tvAscii.getText().toString(), getLayoutPosition());
                }
            });
            ivCopy.setOnClickListener(v -> {
                if (replyListener != null) {
                    replyListener.onClickCopy(tvAscii.getText().toString());
                }
            });
            ivWpShare.setOnClickListener(v -> {
                 if (replyListener != null) {
                    replyListener.onClickShare(tvAscii.getText().toString());
                }
            });
            ivDelete.setOnClickListener(v -> {
                 if (replyListener != null) {
                    replyListener.onDelete(tvAscii.getText().toString(),getLayoutPosition());
                }
            });
        }
    }
}
