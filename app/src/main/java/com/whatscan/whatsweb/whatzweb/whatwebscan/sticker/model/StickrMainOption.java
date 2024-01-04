package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.model;

import android.graphics.drawable.Drawable;

import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.CallBackParentMain;
import com.whatscan.whatsweb.whatzweb.whatwebscan.sticker.CallBackParentMain;

import java.io.Serializable;
import java.util.List;

public class StickrMainOption implements CallBackParentMain<StickrMain>, Serializable {

    Drawable parentIcon;
    private List<StickrMain> mChildItemList;
    private String mParentText;
    private Boolean isVisibleProgress = false;
    private int visibility = 0;

    public Boolean getVisibleProgress() {
        return isVisibleProgress;
    }

    public void setVisibleProgress(Boolean visibleProgress) {
        isVisibleProgress = visibleProgress;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    @Override
    public List<StickrMain> getChildList() {
        return mChildItemList;
    }

    public void setChildItemList(List<StickrMain> childItemList) {
        mChildItemList = childItemList;
    }

    public String getParentText() {
        return mParentText;
    }

    public void setParentText(String parentText) {
        mParentText = parentText;
    }

    public Drawable getParentIcon() {
        return parentIcon;
    }

    public void setParentIcon(Drawable parentIcon) {
        this.parentIcon = parentIcon;
    }

}
