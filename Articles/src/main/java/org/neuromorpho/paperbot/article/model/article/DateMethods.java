package org.neuromorpho.paperbot.article.model.article;

import java.util.Calendar;
import java.util.Date;

public final class DateMethods {

    public static Date getStartYearDate(String year) {
        Calendar date = Calendar.getInstance();   //current date
        date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
        date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
        date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
        date.set(Calendar.MONTH, date.getActualMinimum(Calendar.MONTH));
        date.set(Calendar.YEAR, Integer.parseInt(year));
        return date.getTime();
    }

    public static Date getEndYearDate(String year) {
        Calendar date = Calendar.getInstance();   //current date
        date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
        date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
        date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.set(Calendar.MONTH, date.getActualMaximum(Calendar.MONTH));
        date.set(Calendar.YEAR, Integer.parseInt(year));
        return date.getTime();
    }

}
