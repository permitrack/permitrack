package com.sehinc.erosioncontrol.util;

import com.sehinc.common.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.StringTokenizer;

public class UrlUtil
{
    private static
    Logger
        LOG =
        Logger.getLogger(UrlUtil.class);

    public static String parseName(String name)
    {
        return StringUtil.replace(name,
                                  String.valueOf('\"'),
                                  "\\\"");
    }

    public static String getQueryString(String uri)
    {
        StringTokenizer
            st =
            new StringTokenizer(uri,
                                "?");
        if (st.countTokens()
            < 2)
        {
            return "";
        }
        st.nextToken();
        return st.nextToken();
    }

    public static String getQueryPrefix(String uri)
    {
        StringTokenizer
            st =
            new StringTokenizer(uri,
                                "?");
        if (st.countTokens()
            < 1)
        {
            return "";
        }
        return st.nextToken();
    }
}
