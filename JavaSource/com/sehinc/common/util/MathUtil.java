package com.sehinc.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class MathUtil
    extends Object
{
    public static boolean isInteger(String num)
    {
        boolean
            answer =
            true;
        try
        {
            @SuppressWarnings("UnusedDeclaration")
            Integer
                objNum =
                new Integer(num);
        }
        catch (NumberFormatException e)
        {
            answer =
                false;
        }
        return answer;
    }

    public static boolean isIntegerNumber(String data)
    {
        boolean
            result =
            true;
        char
            nextChar;
        for (
            int
                i =
                0; i
                   < data.length(); i++)
        {
            nextChar =
                data.charAt(i);
            if ((nextChar
                 < '0')
                || (nextChar
                    > '9'))
            {
                result =
                    false;
                break;
            }
        }
        return result;
    }

    public static boolean isPositiveInteger(String num)
    {
        boolean
            answer =
            true;
        try
        {
            BigInteger
                objNum =
                new BigInteger(num);
            if (objNum.signum()
                != 1)
            {
                answer =
                    false;
            }
        }
        catch (NumberFormatException e)
        {
            answer =
                false;
        }
        return answer;
    }

    public static boolean isMultiple(int firstArg, int secondArg)
    {
        boolean
            answer =
            false;
        if (firstArg
            == 0)
        {
            return false;
        }
        try
        {
            int
                result =
                firstArg
                % secondArg;
            if (result
                == 0)
            {
                answer =
                    true;
            }
        }
        catch (ArithmeticException e)
        {
        }
        return answer;
    }

    public static long generateRandom()
    {
        long
            seed =
            new Date().getTime();
        int
            random =
            new Random().nextInt();
        return Math.round(random
                          + seed
                            * (Math.random()));
    }

    public static BigDecimal roundToScale(BigDecimal number, int scale)
    {
        return number.setScale(scale,
                               BigDecimal.ROUND_HALF_UP);
    }
}
