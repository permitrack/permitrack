package com.sehinc.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayUtil
{
    public static final
    SimpleDateFormat
        DATE_IO_FORMAT =
        new SimpleDateFormat("MM/dd/yyyy");
    public static final
    String
        NEWLINE =
        System.getProperty("line.separator");
    public final static
    NumberFormat
        US_CURRENCY_FORMAT =
        NumberFormat.getNumberInstance(Locale.ENGLISH);

    static
    {
        US_CURRENCY_FORMAT.setMinimumFractionDigits(2);
    }

    public static String formatSSN(String str)
    {
        if (str
            == null
            || str.length()
               == 0
            || str.equals("0"))
        {
            return "";
        }
        else if (str.length()
                 == 9)
        {
            return str.substring(0,
                                 3)
                   + "-"
                   + str.substring(3,
                                   5)
                   + "-"
                   + str.substring(5,
                                   9);
        }
        else
        {
            return str;
        }
    }

    public static String formatPhone(String str)
    {
        if (str
            == null
            || str.length()
               == 0)
        {
            return "";
        }
        else if (str.length()
                 == 10)
        {
            return str.substring(0,
                                 3)
                   + "."
                   + str.substring(3,
                                   6)
                   + "."
                   + str.substring(6,
                                   10);
        }
        else if (str.length()
                 == 7)
        {
            return str.substring(0,
                                 3)
                   + "."
                   + str.substring(3,
                                   7);
        }
        else
        {
            return "invalid";
        }
    }

    public static String formatDate(Date date)
    {
        if (date
            != null)
        {
            return DateUtil.shortDate(date);
        }
        else
        {
            return "";
        }
    }

    public static String formatDateTime(Date date)
    {
        if (date
            != null)
        {
            return DateUtil.shortDate(date)
                   + " "
                   + DateUtil.shortTime(date);
        }
        else
        {
            return "";
        }
    }

    public static String displayAsHTML(String str)
    {
        return StringUtil.replace(str,
                                  "\n",
                                  "<br>\n");
    }

    public static String formatMoney(BigDecimal money, String defaultValue)
    {
        if (money
            == null)
        {
            return defaultValue;
        }
        else
        {
            return US_CURRENCY_FORMAT.format(money.doubleValue());
        }
    }

    public static String formatMoney(BigDecimal money)
    {
        return formatMoney(money,
                           "");
    }

    public static String formatMoney(int money)
    {
        if (money
            != 0)
        {
            return formatMoney(new BigDecimal(money));
        }
        else
        {
            return "";
        }
    }

    public static String formatMoney(float money)
    {
        if (money
            != 0.0f)
        {
            return formatMoney(new BigDecimal(money));
        }
        else
        {
            return "";
        }
    }

    public static String formatZIP(String zip)
    {
        zip =
            zip.trim();
        if (zip.length()
            > 5)
        {
            if (zip.length()
                == 9)
            {
                zip =
                    zip.substring(0,
                                  5)
                    + "-"
                    + zip.substring(5,
                                    9);
            }
            else
            {
                zip =
                    zip.substring(0,
                                  5);
            }
        }
        return zip;
    }

    public static Date inputDate(String date)
        throws ParseException
    {
        return DATE_IO_FORMAT.parse(date);
    }

    public static String trimLeadingZeros(String value)
    {
        for (
            int
                i =
                0; i
                   < value.length(); i++)
        {
            if (value.charAt(i)
                != '0')
            {
                return value.substring(i);
            }
        }
        return "";
    }
}
