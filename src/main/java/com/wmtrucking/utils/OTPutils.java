/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.utils;

/**
 *
 * @author Admin
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Random;

public class OTPutils {

    public String accoutnSid = "AC6b5b7f931a937d47a6a8fe7f315f1b1f";
    public String authToken = "809767780c384c029ce6ffe254731684";
    public String fromPhone = "+18706171419";

    public char[] otp(int len) {
        String numbers = "0123456789";

        Random rndmMethod = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] = numbers.charAt(rndmMethod.nextInt(numbers.length()));
        }
        return otp;
    }

    public boolean sendSMS(String phonenumber, String text) {
        try {
            Twilio.init(accoutnSid, authToken);
            Message message = Message.creator(new PhoneNumber(phonenumber), new PhoneNumber(fromPhone),
                    text)
                    .create();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
