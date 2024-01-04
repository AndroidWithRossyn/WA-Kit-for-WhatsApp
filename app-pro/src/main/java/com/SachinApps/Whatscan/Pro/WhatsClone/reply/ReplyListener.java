package com.SachinApps.Whatscan.Pro.WhatsClone.reply;

public interface ReplyListener {
    void onClickEdit(String s,int layoutrPosition);
    void onClickCopy(String s);
    void onClickShare(String s);
    void onDelete(String s,int layoutrPosition);
}
