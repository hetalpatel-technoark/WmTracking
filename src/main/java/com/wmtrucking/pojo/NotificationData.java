/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author administrator
 */
public class NotificationData {

    @SerializedName("detail")
    private String mDetail;

    @SerializedName("extra")
    private String extra;

    @SerializedName("title")
    private String mTitle;

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
