package com.wmtrucking.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    public Date stringToDate(String value, String format) {
        try {
            if (value != null) {
                Date date1 = new SimpleDateFormat(format).parse(value);
                return date1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String dateWithFormat(Date d, String format) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(d);
        }
        return "";
    }

    public String dateWithFormat(String d, String format) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(stringToDate(d, format));
        }
        return "";
    }

    public Date dateFormat(Date d, String format) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return stringToDate(sdf.format(d), format);
        }
        return null;
    }

    public int getYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    public Date getGregorianCalendar(String timezone) {

        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(timezone));

        int hour12 = cal.get(Calendar.YEAR);
        int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
        int minutes = cal.get(Calendar.MINUTE); // 0..59
        int seconds = cal.get(Calendar.SECOND); // 0..59
        boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;
        Calendar cal1 = new GregorianCalendar();
        cal1.set(hour12, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hour24, minutes, seconds);

        return dateFormat(cal1.getTime(), "dd-MM-yyy HH:mm");
    }

    public String getDiff(Date startDate, Date endDate) {

        long diff = endDate.getTime() - startDate.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) diff / (1000 * 60 * 60 * 24);

        System.out.println(diffInDays + "  days");
        System.out.println(diffHours + "  Hour");
        System.out.println(diffMinutes + "  min");
        System.out.println(diffSeconds + "  sec");
        if (diffInDays > 0) {
            return diffInDays + " days ago";
        } else if (diffHours == 0 && diffMinutes == 0) {
            return "Just now";
        } else if (diffHours == 0 && diffMinutes > 0) {
            return diffMinutes + " Minutes ago";
        }
        return diffHours + " Hours " + diffMinutes + " Minutes ago";
    }

    public Date IncreaseDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        return cal.getTime();
    }

    public Date DecreaseDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days); 
        return cal.getTime();
    }
}
