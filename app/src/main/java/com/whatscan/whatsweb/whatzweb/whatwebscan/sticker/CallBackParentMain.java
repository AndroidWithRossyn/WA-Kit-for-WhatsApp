package com.whatscan.whatsweb.whatzweb.whatwebscan.sticker;

import java.util.List;


public interface CallBackParentMain<C> {

    List<C> getChildList();
    boolean isInitiallyExpanded();
}