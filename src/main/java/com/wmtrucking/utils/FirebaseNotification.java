/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wmtrucking.pojo.NotificationData;
import com.wmtrucking.pojo.NotificationRequestModel;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.commons.lang.StringUtils;

/**
 * This APNPushUtil used for send push notification to Android users. Use thread
 * poll executer to execute code.
 *
 * @author Mukesh
 */
public class FirebaseNotification implements Runnable {

    private static final Logger LOG = Logger.getLogger(FirebaseNotification.class.getName());

    final static String ENDPOINT = "https://fcm.googleapis.com/fcm/send";

    private String deviceToken;
    private String title;
    private String text;
    private String extradata;

    /**
     * Construct your data <br/><b>deviceToken: </b> Comma(,) separated tokens
     * <br/><b>title: </b> Title which is display on device <br/><b>text : </b>
     * Text which is display on notification body <br/><b>extradata : </b>
     * provide extradata if you want other wise provide. JSON Format
     *
     * @param deviceToken
     * @param title
     * @param text
     * @param extradata
     * @param userType
     */
    public FirebaseNotification(Object deviceToken, String title, String text, String extradata) {
        this.deviceToken = null;
        this.title = null;
        this.text = null;
        this.extradata = null;

        this.deviceToken = deviceToken == null ? null : deviceToken.toString();
        this.title = title;
        this.text = text;
        this.extradata = extradata;
    }

    public FirebaseNotification() {
        this.deviceToken = null;
        this.title = null;
        this.text = null;
        this.extradata = null;
    }

    @Override
    public void run() {
        LOG.setLevel(Level.ALL);
        LOG.info("Sending Android pushnotification on " + new Date().toString());
        LOG.info("=====================================");
        LOG.info("deviceToken=> " + this.deviceToken);
        //PropertiesCache propertiesCache = PropertiesCache.getInstance();
        if (this.deviceToken != null && this.deviceToken.length() > 0) {
            System.out.println("deviceToken........"+deviceToken);
            try {

                NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
                NotificationData notificationData = new NotificationData();
                notificationData.setDetail(this.text);
                notificationData.setTitle(this.title);
                notificationData.setExtra(this.extradata);
                notificationRequestModel.setData(notificationData);
                //Removing duplicate values
                HashSet<String> distinctValues = new HashSet<>(Arrays.asList(this.deviceToken.split(",")));
                String[] d = StringUtils.join(distinctValues, ",").split(",");
                notificationRequestModel.setTo(d);
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost postRequest = new HttpPost(
                        "https://fcm.googleapis.com/fcm/send");

                Gson gson = new Gson();
                Type type = new TypeToken<NotificationRequestModel>() {
                }.getType();

                String json = gson.toJson(notificationRequestModel, type);

                StringEntity input = new StringEntity(json);
                input.setContentType("application/json");

                // server key of your firebase project goes here in header field.
                // You can get it from firebase console.
                postRequest.addHeader("Authorization", Constant.FCM_SERVERKEY.toString());

                postRequest.setEntity(input);

                System.out.println("reques: json............>  " + json);
                HttpResponse response = httpClient.execute(postRequest);
                System.out.println("response_code...."+response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() != 200) {
                    LOG.info("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                } else if (response.getStatusLine().getStatusCode() == 200) {

                    LOG.info("response:" + EntityUtils.toString(response.getEntity()));

                }

                LOG.info("End Android pushnotification on " + new Date().toString());
                LOG.info("=====================================");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        FirebaseNotification fn = new FirebaseNotification("", "hi", "test", "");
        fn.run();
    }
}
