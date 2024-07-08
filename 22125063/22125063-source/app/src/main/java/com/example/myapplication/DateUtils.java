package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String[] getWeekDates(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        String[] weekDates = new String[7];
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEE", Locale.getDefault());

        for (int i = 0; i < 7; i++) {
            String day = dayFormat.format(calendar.getTime());
            String dayOfWeek = dayOfWeekFormat.format(calendar.getTime()).substring(0, 2).toUpperCase();
            weekDates[i] = day + "\n" + dayOfWeek;
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return weekDates;
    }
    public static String getNextDate(String dateStr, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        Calendar calendar = Calendar.getInstance();

        try {
            Date date = sdf.parse(dateStr);
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, i);
            Date nextDate = calendar.getTime();
            return sdf.format(nextDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

