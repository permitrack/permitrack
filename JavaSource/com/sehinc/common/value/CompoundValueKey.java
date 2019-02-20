package com.sehinc.common.value;

import java.io.Serializable;
import java.util.StringTokenizer;

public abstract class CompoundValueKey
    implements Serializable
{
    protected final static
    String
        KEY_SEPERATOR =
        "-";

    protected String[] parseKeyString(String keyString, int parameterSize)
    {
        String[]
            parameters =
            new String[parameterSize];
        int
            count =
            0;
        StringTokenizer
            st =
            new StringTokenizer(keyString,
                                KEY_SEPERATOR);
        while (st.hasMoreTokens())
        {
            if (count
                >= parameterSize)
            {
                throw new java.lang.IllegalArgumentException("key string must contain "
                                                             + parameterSize
                                                             + " parts.");
            }
            parameters[count++] =
                st.nextToken();
        }
        if (count
            < parameterSize)
        {
            throw new java.lang.IllegalArgumentException("key string must contain "
                                                         + parameterSize
                                                         + " parts.");
        }
        return parameters;
    }
}