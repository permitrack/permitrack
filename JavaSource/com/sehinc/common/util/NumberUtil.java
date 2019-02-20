package com.sehinc.common.util;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberUtil
{
    private static final
    String
        DECIMAL_FORMAT_PATTERN =
        "#,##0.##";
    private static final
    String
        CURRENCY_FORMAT_PATTERN =
        "$#,##0.00";
    private static final
    DecimalFormat
        DECIMAL_FORMATTER =
        (DecimalFormat) NumberFormat.getInstance();
    private static final
    DecimalFormat
        CURRENCY_FORMATTER =
        (DecimalFormat) NumberFormat.getCurrencyInstance();
    private static
    Logger
        LOG =
        Logger.getLogger(NumberUtil.class);

    static
    {
        DECIMAL_FORMATTER.applyPattern(DECIMAL_FORMAT_PATTERN);
        CURRENCY_FORMATTER.applyPattern(CURRENCY_FORMAT_PATTERN);
    }

    public static String formatFloatToString(Float myFloat)
    {
        if (myFloat
            == null)
        {
            return "";
        }
        return DECIMAL_FORMATTER.format(myFloat);
    }

    public static BigDecimal numberToBigDecimal(String number)
        throws ParseException
    {
        if (number
            != null
            && !number.equals(""))
        {
            try
            {
                return new BigDecimal((DECIMAL_FORMATTER.parse(number)).doubleValue());
            }
            catch (ParseException pe)
            {
                LOG.warn("NumberUtil.numberToBigDecimal(String): "
                         + pe);
                throw pe;
            }
        }
        return new BigDecimal(0.0d);
    }

    public static float numberToFloat(String number)
        throws ParseException
    {
        if (number
            != null
            && !number.equals(""))
        {
            try
            {
                return (DECIMAL_FORMATTER.parse(number)).floatValue();
            }
            catch (ParseException pe)
            {
                LOG.warn("NumberUtil.numberToFloat(String): "
                         + pe);
                throw pe;
            }
        }
        return 0.0f;
    }

    public static BigDecimal currencyToBigDecimal(String number)
        throws ParseException
    {
        if (number
            != null
            && !number.equals(""))
        {
            try
            {
                return new BigDecimal((CURRENCY_FORMATTER.parse(number)).doubleValue());
            }
            catch (ParseException pe1)
            {
                try
                {
                    return new BigDecimal((DECIMAL_FORMATTER.parse(number)).doubleValue());
                }
                catch (ParseException pe2)
                {
                    LOG.warn("NumberUtil.currencyToBigDecimal(String): "
                             + pe2);
                    throw pe2;
                }
            }
        }
        return new BigDecimal(0.0d);
    }

    public static float currencyToFloat(String number)
        throws ParseException
    {
        if (number
            != null
            && !number.equals(""))
        {
            try
            {
                return (CURRENCY_FORMATTER.parse(number)).floatValue();
            }
            catch (ParseException pe1)
            {
                try
                {
                    return (DECIMAL_FORMATTER.parse(number)).floatValue();
                }
                catch (ParseException pe2)
                {
                    LOG.warn("NumberUtil.currencyToFloat(String): "
                             + pe2);
                    throw pe2;
                }
            }
        }
        return 0.0f;
    }
}
