package com.sehinc.common.util.crypto;

public class BASE64Decoder
{
    private static final
    int
        EIGHT_BIT_MASK =
        0xFF;

    public byte[] decodeBuffer(String data)
    {
        // Create a wrapper around the input to screen out non-Base64 characters
        StringWrapper
            wrapper =
            new StringWrapper(data);
        // A Base64 byte array is 75% the size of its String representation
        int
            byteArrayLength =
            wrapper.getUsefulLength()
            * 3
            / 4;
        byte
            result
            [
            ] =
            new byte[byteArrayLength];
        int
            byteTriplet;
        int
            byteIndex =
            0;
        // Continue until we have less than 4 full characters left to
        // decode in the input.
        while (byteIndex
               + 2
               < byteArrayLength)
        {
            // Package a set of four characters into a byte triplet
            // Each character contributes 6 bits of useful information
            byteTriplet =
                mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<=
                6;
            byteTriplet |=
                mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<=
                6;
            byteTriplet |=
                mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<=
                6;
            byteTriplet |=
                mapCharToInt(wrapper.getNextUsefulChar());
            // Grab a normal byte (eight bits) out of the byte triplet
            // and put it in the byte array
            result[byteIndex
                   + 2] =
                (byte) (byteTriplet
                        & EIGHT_BIT_MASK);
            byteTriplet >>=
                8;
            result[byteIndex
                   + 1] =
                (byte) (byteTriplet
                        & EIGHT_BIT_MASK);
            byteTriplet >>=
                8;
            result[byteIndex] =
                (byte) (byteTriplet
                        & EIGHT_BIT_MASK);
            byteIndex +=
                3;
        }
        // Check if we have one byte left to decode
        if (byteIndex
            == byteArrayLength
               - 1)
        {
            // Take out the last two characters from the String
            byteTriplet =
                mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<=
                6;
            byteTriplet |=
                mapCharToInt(wrapper.getNextUsefulChar());
            // Remove the padded zeros
            byteTriplet >>=
                4;
            result[byteIndex] =
                (byte) (byteTriplet
                        & EIGHT_BIT_MASK);
        }
        // Check if we have two bytes left to decode
        if (byteIndex
            == byteArrayLength
               - 2)
        {
            // Take out the last three characters from the String
            byteTriplet =
                mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<=
                6;
            byteTriplet |=
                mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<=
                6;
            byteTriplet |=
                mapCharToInt(wrapper.getNextUsefulChar());
            // Remove the padded zeros
            byteTriplet >>=
                2;
            result[byteIndex
                   + 1] =
                (byte) (byteTriplet
                        & EIGHT_BIT_MASK);
            byteTriplet >>=
                8;
            result[byteIndex] =
                (byte) (byteTriplet
                        & EIGHT_BIT_MASK);
        }
        return result;
    }

    private int mapCharToInt(char c)
    {
        if (c
            >= 'A'
            && c
               <= 'Z')
        {
            return c
                   - 'A';
        }
        if (c
            >= 'a'
            && c
               <= 'z')
        {
            return (c
                    - 'a')
                   + BASE64Encoder.LOWER_CASE_A_VALUE;
        }
        if (c
            >= '0'
            && c
               <= '9')
        {
            return (c
                    - '0')
                   + BASE64Encoder.ZERO_VALUE;
        }
        if (c
            == '+')
        {
            return BASE64Encoder.PLUS_VALUE;
        }
        if (c
            == '/')
        {
            return BASE64Encoder.SLASH_VALUE;
        }
        throw new IllegalArgumentException(c
                                           + " is not a valid Base64 character.");
    }

    private class StringWrapper
    {
        private
        String
            mString;
        private
        int
            mIndex =
            0;
        private
        int
            mUsefulLength;

        private boolean isUsefulChar(char c)
        {
            return (c
                    >= 'A'
                    && c
                       <= 'Z')
                   ||
                   (c
                    >= 'a'
                    && c
                       <= 'z')
                   ||
                   (c
                    >= '0'
                    && c
                       <= '9')
                   ||
                   (c
                    == '+')
                   ||
                   (c
                    == '/');
        }

        public StringWrapper(String s)
        {
            mString =
                s;
            mUsefulLength =
                0;
            int
                length =
                mString.length();
            for (
                int
                    i =
                    0; i
                       < length; i++)
            {
                if (isUsefulChar(mString.charAt(i)))
                {
                    mUsefulLength++;
                }
            }
        }

        public int getUsefulLength()
        {
            return mUsefulLength;
        }

        public char getNextUsefulChar()
        {
            char
                result =
                '_';  // Start with a non-Base64 character
            while (!isUsefulChar(result))
            {
                result =
                    mString.charAt(mIndex++);
            }
            return result;
        }
    }
}