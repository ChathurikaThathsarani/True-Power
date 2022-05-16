package com.example.truepower;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NextCheckupDate {
    public static int nDays_Between_Dates(String date1, String date2) {
        int diffDays = 0;
        try {
            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = dates.parse(date1);
            Date endDate = dates.parse(date2);
            long diff = endDate.getTime() - startDate.getTime();
            diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs(diffDays);
    }
}
