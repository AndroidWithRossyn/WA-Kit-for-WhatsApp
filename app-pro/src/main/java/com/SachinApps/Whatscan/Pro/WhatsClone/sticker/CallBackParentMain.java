package com.SachinApps.Whatscan.Pro.WhatsClone.sticker;

import java.util.List;


public interface CallBackParentMain<C> {

    List<C> getChildList();
    boolean isInitiallyExpanded();
}