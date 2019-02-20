package com.sehinc.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil
{
    public final static
    long
        MILLISEC_PER_DAY =
        1000
        * 60
        * 60
        * 24;
    public final static
    long
        MILLISEC_PER_HOUR =
        1000
        * 60
        * 60;
    public static final
    SimpleDateFormat
        YYYY_FORMAT =
        new SimpleDateFormat("yyyy");
    public static final
    SimpleDateFormat
        YMD_FORMAT =
        new SimpleDateFormat("yyyy-MM-dd");
    public static final
    SimpleDateFormat
        MDY_FORMAT =
        new SimpleDateFormat("MM/dd/yyyy");
    public static final
    SimpleDateFormat
        DMY_FORMAT =
        new SimpleDateFormat("dd-MM-yyyy");
    public static final
    SimpleDateFormat
        DMMY_FORMAT =
        new SimpleDateFormat("dd MMMM yyyy");
    public static final
    SimpleDateFormat
        MMDY_FORMAT =
        new SimpleDateFormat("MMMM dd, yyyy");
    public static final
    SimpleDateFormat
        EDMY_FORMAT =
        new SimpleDateFormat("EEEE, MMMM dd, yyyy");
    public static final
    SimpleDateFormat
        MDYT_FORMAT =
        new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
    public static final
    DateFormat
        SHORT_DATE_FORMAT =
        DateFormat.getDateInstance(DateFormat.SHORT);
    public static final
    DateFormat
        SHORT_TIME_FORMAT =
        DateFormat.getTimeInstance(DateFormat.SHORT);
    public static final
    SimpleDateFormat
        PARSE_DATE_FORMAT =
        new SimpleDateFormat("M/d/yy");
    public static final
    SimpleDateFormat
        MDYTT_FORMAT =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final
    SimpleDateFormat
        MMDDHHMM_FORMAT =
        new SimpleDateFormat("MM/dd/yy hh:mm a");
    public static final
    SimpleDateFormat
        EEHA_FORMAT =
        new SimpleDateFormat("EE ha");
    private final static
    Calendar
        TWO_DIGIT_CALENDAR;

    static
    {
        Calendar
            calendar =
            new GregorianCalendar();
        calendar.set(Calendar.YEAR,
                     100);
        TWO_DIGIT_CALENDAR =
            calendar;
    }

    public static boolean isWeekend(Date theDate)
    {
        Calendar
            aCal =
            Calendar.getInstance();
        aCal.setTime(theDate);
        return ((aCal.get(Calendar.DAY_OF_WEEK)
                 == Calendar.SATURDAY)
                || (aCal.get(Calendar.DAY_OF_WEEK)
                    == Calendar.SUNDAY));
    }

    public static boolean isWeekday(Date theDate)
    {
        return !DateUtil.isWeekend(theDate);
    }

    public static String ymdDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(YMD_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String year(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(YYYY_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String dmyDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(DMY_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String mmdyDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(MMDY_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String edmyDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(EDMY_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String dmmyDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(DMMY_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String mdytDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(MDYT_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String mdyDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(MDY_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String shortDate(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(SHORT_DATE_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static String shortTime(Date theDate)
    {
        StringBuffer
            sb =
            new StringBuffer();
        if (theDate
            != null)
        {
            sb.append(SHORT_TIME_FORMAT.format(theDate));
        }
        return sb.toString();
    }

    public static Date addOneDay(Date date)
    {
        return new Date(date.getTime()
                        + MILLISEC_PER_DAY);
    }

    public static Date subtractOneDay(Date date)
    {
        if (date
            != null)
        {
            return new Date(date.getTime()
                            - MILLISEC_PER_DAY);
        }
        else
        {
            return null;
        }
    }

    public static void printDateTime(String message)
    {
        System.out
            .println(DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                                                    DateFormat.MEDIUM)
                         .format(new Date())
                     + "   "
                     + message);
    }

    public static Date parseDate(String date)
    {
        if (date
            == null
            || date.isEmpty())
        {
            return null;
        }
        else
        {
            try
            {
                return PARSE_DATE_FORMAT.parse(date);
            }
            catch (ParseException e)
            {
                return null;
            }
        }
    }

    public static boolean isInDateRange(Date theDate, Date fromDate, Date toDate)
    {
        return (theDate.after(fromDate)
                && theDate.before(toDate));
    }

    public static Date getLastMonthStartDate()
    {
        Calendar
            c =
            Calendar.getInstance();
        c.add(Calendar.MONTH,
              -1);
        c.set(Calendar.DAY_OF_MONTH,
              1);
        return c.getTime();
    }

    public static Date getLastMonthEndDate()
    {
        Calendar
            c =
            Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,
              1);
        c.add(Calendar.HOUR,
              -24);
        return c.getTime();
    }

    public static Date getOneYearAgo()
    {
        Calendar
            calendar =
            Calendar.getInstance();
        calendar.add(Calendar.YEAR,
                     -1);
        return calendar.getTime();
    }
}
