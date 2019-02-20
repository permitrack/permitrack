package com.sehinc.common.util.crypto;

import java.util.Arrays;

public class BASE64Encoder
{
    final static
    int
        LOWER_CASE_A_VALUE =
        26;
    final static
    int
        ZERO_VALUE =
        52;
    final static
    int
        PLUS_VALUE =
        62;
    final static
    int
        SLASH_VALUE =
        63;
    private final static
    int
        SIX_BIT_MASK =
        63;

    private int convertUnsignedByteToInt(byte b)
    {
        if (b
            >= 0)
        {
            return (int) b;
        }
        return 256
               + b;
    }

    public String encode(byte data[])
    {
        // Base64 encoding yields a String that is 33% longer than the byte array
        int
            charCount =
            ((data.length
              * 4)
             / 3)
            + 4;
        // New lines will also be needed for every 76 charactesr, so allocate a
        // StringBuffer that is long enough to hold the full result without
        // having to expand later
        StringBuffer
            result =
            new StringBuffer((charCount
                              * 77)
                             / 76);
        int
            byteArrayLength =
            data.length;
        int
            byteArrayIndex =
            0;
        int
            byteTriplet;
        while (byteArrayIndex
               < byteArrayLength
                 - 2)
        {
            // Build the 24 bit byte triplet from the input data
            byteTriplet =
                convertUnsignedByteToInt(data[byteArrayIndex++]);
            // Each input byte contributes 8 bits to the triplet
            byteTriplet <<=
                8;
            byteTriplet |=
                convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<=
                8;
            byteTriplet |=
                convertUnsignedByteToInt(data[byteArrayIndex++]);
            // Look at the lowest order six bits and remember them
            byte
                b4 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            // Move the byte triplet to get the next 6 bit value
            byteTriplet >>=
                6;
            byte
                b3 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            byteTriplet >>=
                6;
            byte
                b2 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            byteTriplet >>=
                6;
            byte
                b1 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            // Add the Base64 encoded character to the result String
            result.append(mapByteToChar(b1));
            result.append(mapByteToChar(b2));
            result.append(mapByteToChar(b3));
            result.append(mapByteToChar(b4));
            // There are 57 bytes for every 76 characters, so wrap the line when needed
            if (byteArrayIndex
                % 57
                == 0)
            {
                result.append("\n");
            }
        }
        // Check if we have one byte left over
        if (byteArrayIndex
            == byteArrayLength
               - 1)
        {
            // Convert our one byte to an int
            byteTriplet =
                convertUnsignedByteToInt(data[byteArrayIndex++]);
            // Right pad the second 6 bit value with zeros
            byteTriplet <<=
                4;
            byte
                b2 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            byteTriplet >>=
                6;
            byte
                b1 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            result.append(mapByteToChar(b1));
            result.append(mapByteToChar(b2));
            // Add "==" to the output to make it a multiple of 4 Base64 characters
            result.append("==");
        }
        // Check if we have two byte left over
        if (byteArrayIndex
            == byteArrayLength
               - 2)
        {
            // Convert our two bytes to an int
            byteTriplet =
                convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<=
                8;
            byteTriplet |=
                convertUnsignedByteToInt(data[byteArrayIndex++]);
            // Right pad the third 6 bit value with zeros
            byteTriplet <<=
                2;
            byte
                b3 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            byteTriplet >>=
                6;
            byte
                b2 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            byteTriplet >>=
                6;
            byte
                b1 =
                (byte) (SIX_BIT_MASK
                        & byteTriplet);
            result.append(mapByteToChar(b1));
            result.append(mapByteToChar(b2));
            result.append(mapByteToChar(b3));
            // Add "==" to the output to make it a multiple of 4 Base64 characters
            result.append("=");
        }
        return result.toString();
    }

    private char mapByteToChar(byte b)
    {
        if (b
            < LOWER_CASE_A_VALUE)
        {
            return (char) ('A'
                           + b);
        }
        if (b
            < ZERO_VALUE)
        {
            return (char) ('a'
                           + (b
                              - LOWER_CASE_A_VALUE));
        }
        if (b
            < PLUS_VALUE)
        {
            return (char) ('0'
                           + (b
                              - ZERO_VALUE));
        }
        if (b
            == PLUS_VALUE)
        {
            return '+';
        }
        if (b
            == SLASH_VALUE)
        {
            return '/';
        }
        throw new IllegalArgumentException("Byte "
                                           + new Integer(b)
                                           + " is not a valid Base64 value");
    }

    public static void main(String args[])
        throws Exception
    {
        BASE64Encoder
            encoder =
            new BASE64Encoder();
        BASE64Decoder
            decoder =
            new BASE64Decoder();
        for (
            int
                j =
                0; j
                   < 100; j++)
        {
            byte
                test
                [
                ] =
                new byte[(int) (100000
                                * Math.random())];
            for (
                int
                    i =
                    0; i
                       < test.length; i++)
            {
                test[i] =
                    (byte) (256
                            * Math.random());
            }
            String
                string =
                encoder.encode(test);
            byte
                result
                [
                ] =
                decoder.decodeBuffer(string);
            if (!Arrays.equals(test,
                               result)
                || test.length
                   != result.length)
            {
                System.out
                    .println("ARRAYS DO NOT MATCH!");
            }
        }
    }
}