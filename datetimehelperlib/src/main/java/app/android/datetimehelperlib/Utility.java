package app.android.datetimehelperlib;

import android.text.format.DateFormat;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
    public static String changeDateFormat(String inputFormat, String outputFormat, String date) {
        String formattedDate = "";
        if (null == date || null == outputFormat || null == inputFormat) {
            return formattedDate;
        }
        try {
            Date dt = new SimpleDateFormat(inputFormat).parse(date);
            formattedDate = new SimpleDateFormat(outputFormat).format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static long getCurrentUnixTimestamp() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getYesterdayTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -24);
        return calendar.getTimeInMillis() / 1000;
    }

    public static CharSequence getRemainingTime(Long timeInSeconds) {
        Calendar calTime = Calendar.getInstance();
        calTime.setTimeInMillis(timeInSeconds * 1000L);
        calTime.add(Calendar.HOUR, 24);

        CharSequence result = DateUtils.getRelativeTimeSpanString(calTime.getTimeInMillis(),
                Utility.getCurrentUnixTimestamp() * 1000L, 0);
        return result;
    }

    public static long getTimestampForDate(String inputFormat, String date) {
        long timeStamp = 0;
        try {
            Date dt = new SimpleDateFormat(inputFormat).parse(date);
            timeStamp = dt.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp / 1000;
    }

    public static String getDateForTimeStamp(String outputFormat, long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        String date = DateFormat.format(outputFormat, cal).toString();
        return date;
    }

    public static String getCurrentDateTime(String requiredDateFormat, String requiredTimeZone) {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df;
        try {
            df = new SimpleDateFormat(requiredDateFormat);
            df.setTimeZone(TimeZone.getTimeZone(requiredTimeZone));
        } catch (Exception ex) {
            df = new SimpleDateFormat("dd-MM-yy");
            df.setTimeZone(TimeZone.getTimeZone(requiredTimeZone));
        }
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
}
