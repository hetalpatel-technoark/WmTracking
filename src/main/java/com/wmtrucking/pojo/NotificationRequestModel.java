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
public class NotificationRequestModel {

    @SerializedName("data")
    private NotificationData mData;
    @SerializedName("registration_ids")
    private String[] mTo;

    public NotificationData getData() {
        return mData;
    }

    public void setData(NotificationData data) {
        mData = data;
    }

    public String[] getTo() {
        return mTo;
    }

    public void setTo(String[] to) {
        mTo = to;
    }
}
