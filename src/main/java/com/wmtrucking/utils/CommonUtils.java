package com.wmtrucking.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

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
