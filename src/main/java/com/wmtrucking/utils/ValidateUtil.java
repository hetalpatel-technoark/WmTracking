package com.wmtrucking.utils;

import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ValidateUtil {

    JsonObject error;

    public void checkNullAndLength(JsonObject jsonObject, HttpServletRequest request, String paramname, int maxLength, int minLength) {

        if (request.getParameter(paramname) == null || request.getParameter(paramname).trim().equals("")) {
            jsonObject.addProperty(paramname, "This field is required");

            return;
        }
        String validateMessage = "";
        boolean isValidted = true;
        if (minLength > 1 && request.getParameter(paramname).length() < minLength) {
            validateMessage = "Minimum of " + minLength + " characters";
            isValidted = false;
        }
        if (maxLength > 1 && request.getParameter(paramname).length() > maxLength) {
            validateMessage += isValidted == false ? "\n Maximum of " + maxLength + " characters" : "Maximum of " + maxLength + " characters";
            isValidted = false;
        }
        if (!isValidted) {
            jsonObject.addProperty(paramname, validateMessage);
        }
    }

    public void checkNull(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        if (request.getParameter(fieldname) == null || request.getParameter(fieldname).trim().equals("")) {
            errors.add(displayfield + " is required.");
        }
    }

    public void checkLength(List<String> errors, HttpServletRequest request, String paramname,String displayfield, int maxLength, int minLength) {

        if (!(request.getParameter(paramname) == null || request.getParameter(paramname).trim().equals(""))) {
            String validateMessage = "";
            boolean isValidted = true;
            if (minLength > 1 && request.getParameter(paramname).length() < minLength) {
                validateMessage = "Minimum of " + minLength + " characters";
                isValidted = false;
            }
            if (maxLength > 1 && request.getParameter(paramname).length() > maxLength) {
                validateMessage += isValidted == false ? "\n Allowed Maximum  " + maxLength + " characters" : " Allowed Maximum  " + maxLength + " characters";
                isValidted = false;
            }
            if (!isValidted) {
              //  jsonObject.addProperty(paramname, validateMessage);
                errors.add(displayfield +" "+ validateMessage);
            }
        }

    }

    public String getStringValue(String value) {
        if (value != null) {
            return value;
        }
        return null;
    }

    public Date getDateValue(String value) {
        if (value != null) {
            DateUtils dateUtils = new DateUtils();
            return dateUtils.stringToDate(value, Constant.DATEFORMAT.toString());
        }
        return null;
    }

    public Long getLongValue(String value) {
        if (!(value == null || value.trim().equals(""))) {
            return Long.parseLong(value);
        }
        return null;
    }
}
