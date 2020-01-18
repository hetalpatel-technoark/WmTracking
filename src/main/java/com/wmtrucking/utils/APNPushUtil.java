/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.utils;

import com.notnoop.apns.APNS;
import java.io.InputStream;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This APNPushUtil used for send push notification to IOS users. Use thread
 * poll executer to execute code.
 *
 * @author Mukesh
 */
public class APNPushUtil implements Runnable {

    private static final Logger LOG = Logger.getLogger(APNPushUtil.class.getName());

    private String deviceToken;
    private String title;
    private String text;
    private Map<String, ?> extradata;
  

    public APNPushUtil() {
        this.deviceToken = null;
        this.title = null;

    }

    public APNPushUtil(Object deviceToken, String title) {
        this.deviceToken = null;
        this.title = null;

        this.deviceToken = deviceToken == null ? null : deviceToken.toString();
        this.title = title;

    }

    @Override
    public void run() {
        LOG.setLevel(Level.ALL);
        LOG.info("Sending ios pushnotification on " + new Date().toString());
        LOG.info("=====================================");
        LOG.info("deviceToken=> " + this.deviceToken);
      
        try {

            if (!(this.deviceToken == null || this.deviceToken.equals(""))) {
                String filename = "", password = "";

                filename = Constant.PUSH_CERTIFICATE.toString();
                password = Constant.PUSH_CERTIFICATE_PASSWORD.toString();

                InputStream certStream = this.getClass().getClassLoader().getResourceAsStream(filename);
                ApnsService service = null;
                if (Constant.MODE.toString().equals("Local")) {
                    service = APNS.newService()
                            .withCert(certStream, password)
                            .withSandboxDestination()
                            .build();
                } else {
                    service = APNS.newService()
                            .withCert(certStream, password)
                            .withProductionDestination()
                            .build();
                }
                service.start();
                PayloadBuilder builder = APNS.newPayload();
                builder.alertBody(this.text).alertTitle(this.title).sound("default");
                if (this.extradata != null) {
                    builder.customFields(this.extradata);
                }

                //Removing duplicate values
                HashSet<String> distinctValues = new HashSet<>(Arrays.asList(this.deviceToken.split(",")));
//                String[] tokens = this.deviceToken.split(",");
                for (String token : distinctValues) {
                    String payload = builder.build();
                    service.push(token, payload);
                   // System.out.println(service.push(token, payload));
                }

                LOG.info("InactiveDevices=> " + service.getInactiveDevices());
                service.stop();
            }
        } catch (Exception e) {
        }

        LOG.info("End ios pushnotification on " + new Date().toString());
        LOG.info("=====================================");

    }

    public static void main(String[] args) {
        APNPushUtil app = new APNPushUtil("", "test");
        app.run();
    }

//    public ApnsNotification sendNotification(String deviceToken, String title, String text, Map<String, ?> extradata) {
//       
//            if (deviceToken == null || deviceToken.equals("")) {
//                return null;
//            }
//
//            if (deviceToken.length() > 64) {
//                return null;
//            }
//
//            InputStream certStream = this.getClass().getClassLoader().getResourceAsStream(Constant.PUSH_CERTIFICATE.toString());
//            ApnsService service = null;
//            if (Constant.MODE.toString().toString().equals("Local")) {
//                service = APNS.newService()
//                        .withCert(certStream, Constant.PUSH_CERTIFICATE_PASSWORD.toString())
//                        .withSandboxDestination()
//                        .build();
//            } 
//            else {
//                service = APNS.newService()
//                        .withCert(certStream, Constant.PUSH_CERTIFICATE_PASSWORD.toString())
//                        .withProductionDestination()
//                        .build();
//            }
//            service.start();
//            PayloadBuilder builder = APNS.newPayload();
//            builder.alertBody(text).alertTitle(title).sound("default");
//            if (extradata != null) {
//                builder.customFields(extradata);
//            }
//            String payload = builder.build();
//
//            ApnsNotification apnsNotification = service.push(deviceToken, payload);
//
//            service.stop();
//            return apnsNotification;       
//       
//    }
}
