package com.sehinc.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

public class UrlUtil
{
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

    public
    static
    String
        subNodeString =
        "node_id";

    public static String getSubNodeParameters(String uri)
    {
        return uri.substring(uri.indexOf(subNodeString)
                             + subNodeString.length());
    }

    public static String getSubeNodePrefix(String uri)
    {
        return uri.substring(0,
                             uri.indexOf(subNodeString))
               + subNodeString;
    }

    public static String getUrl(HttpServletRequest req)
    {
        String
            reqUrl =
            req.getRequestURL()
                .toString();
        String
            queryString =
            req.getQueryString();
        if (queryString
            != null)
        {
            reqUrl +=
                "?"
                + queryString;
        }
        return reqUrl;
    }
}
