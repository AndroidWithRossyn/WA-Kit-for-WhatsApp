package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.repeater;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.CallBack_PIP;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.LayoutStickerMainOptionBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.CallBack_PIP;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.model.StickrMainOption;

import java.util.ArrayList;

public class RepeaterStickrMainOpt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final String NATIVE_AD = "NativeAd";
    private final ArrayList<StickrMainOption> appMdlStikrMainOptArrayList;
    private final CallBack_PIP callBackPip;
    Activity mActivity;

    public RepeaterStickrMainOpt(Activity activity, ArrayList<StickrMainOption> appMdlStikrMainOptArrayList, CallBack_PIP callBackPip) {
        super();
        this.mActivity = activity;
        this.appMdlStikrMainOptArrayList = appMdlStikrMainOptArrayList;
        this.callBackPip = callBackPip;
    }

    @Override
    public int getItemViewType(int position) {
        StickrMainOption stickrMainOpt = appMdlStikrMainOptArrayList.get(position);
        if (stickrMainOpt.getParentText().equalsIgnoreCase(NATIVE_AD)) {
            return 2;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == 2) {
            return new AdsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_native_ad, viewGroup, false));
        } else {
            @NonNull LayoutStickerMainOptionBinding itemBinding = LayoutStickerMainOptionBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new ViewHolder(itemBinding);
        }

    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder._tvTitle.setText(appMdlStikrMainOptArrayList.get(i).getParentText());
            holder._ivPhotoThumb.setImageDrawable(appMdlStikrMainOptArrayList.get(i).getParentIcon());
        }
    }


    @Override
    public int getItemCount() {
        return appMdlStikrMainOptArrayList.size();
    }

    public static class AdsHolder extends RecyclerView.ViewHolder {

        FrameLayout fl_adplaceholder;


        AdsHolder(View itemView) {
            super(itemView);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView _ivPhotoThumb;
        public TextView _tvTitle;


        public ViewHolder(final LayoutStickerMainOptionBinding itemView) {
            super(itemView.getRoot());
            _ivPhotoThumb = itemView.ivPhotoThumb;
            _tvTitle = itemView.tvTitle;

            _tvTitle.setVisibility(View.VISIBLE);
            itemView.getRoot().setOnClickListener(v -> callBackPip.onItemClick(itemView.getRoot(), getLayoutPosition()));
        }
    }
}

