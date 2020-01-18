package com.wmtrucking.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtils {

    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    /**
     * 1003 CHECK STRING WHEATHER IT IS A LONG OR NOT.IF YES THEN RETURNS TRUE.
     *
     * @param str
     * @return
     */
    public boolean checkLong(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            try {
                Long num = Long.parseLong(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void removeCookie(HttpServletResponse response, String name) {
        addCookie(response, name, null, 0);
    }

    private SecretKeySpec setKey(String plainkey) {
        SecretKeySpec secretKey = null;
        byte[] keybytes;

        MessageDigest sha = null;
        try {
            keybytes = plainkey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            keybytes = sha.digest(keybytes);
            keybytes = Arrays.copyOf(keybytes, 16);
            secretKey = new SecretKeySpec(keybytes, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    public static String encryptAESURL(String plaintext, String passkey) {
        try {
            byte[] keyBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] cipherText = cipher.doFinal(plaintext.getBytes());
            //Encode Character which are not allowed on URL
            String encodedTxt = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(cipherText);

            return encodedTxt;
        } catch (Exception e) {

        }
        return null;
    }

    public static String decryptAESURL(String text) {
        try {
            byte[] keyBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

            cipher.init(Cipher.DECRYPT_MODE, key);
            String decodeStr = URLDecoder.decode(
                    text,
                    StandardCharsets.UTF_8.toString());
            //Decode URl safe to base 64
            byte[] base64decodedTokenArr = org.apache.commons.codec.binary.Base64.decodeBase64(decodeStr.getBytes());

            byte[] decryptedPassword = cipher.doFinal(base64decodedTokenArr);
            //byte[] decryptedPassword = cipher.doFinal(decodeStr.getBytes());
            String decodeTxt = new String(decryptedPassword);
            return decodeTxt;
        } catch (Exception e) {

        }

        return null;
    }

    public boolean checkDouble(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            try {
                Double num = Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public boolean isBigInteger(String str) {
        try {
            BigInteger num = new BigInteger(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isBigDecimal(String str) {
        if (str == null || str.equals("")) {
            return false;
        } else {
            try {
                BigDecimal num = new BigDecimal(str);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean isLong(String str) {
        try {
            Long num = Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(String str) {
        try {
            Double num = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public BigInteger toBiginteger(String str) {
        try {
            BigInteger num = new BigInteger(str);
            return num;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else if (phoneNo.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkEmail(String stremail) {
        Pattern email = Pattern.compile(
                "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
        return email.matcher(stremail.toLowerCase()).matches();
    }

    public boolean checkProperEmail(String stremail) {
        Pattern email = Pattern.compile(
                "^([a-zA-Z0-9_+\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        return email.matcher(stremail).matches();
    }

    public boolean checkPasswordStrength(String password) {
        Pattern password_reg = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_-]).{7,20})");
        return password_reg.matcher(password).matches();
    }

}
