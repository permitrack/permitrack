package com.sehinc.portal.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CookieFilter
    implements Filter
{
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest
            request =
            (HttpServletRequest) req;
        HttpServletResponse
            response =
            (HttpServletResponse) res;
        @SuppressWarnings("UnusedAssignment")
        Object
            o =
            request.getAttribute("rememberme");
        if (request.getSession()
            != null
            && request.getSession()
            .isNew())
        {
            String
                id =
                request.getSession()
                    .getId();
            long
                expireTimestamp =
                System.currentTimeMillis()
                + (24
                   * 60
                   * 60
                   * 1000); // 24 hours ahead
            String
                expireDate =
                new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z").format(new Date(expireTimestamp));
            response.setHeader("Set-Cookie",
                               String.format("JSESSIONID=%s;Expires=%s;Path=/sehsvc",
                                             id,
                                             expireDate));
        }
        chain.doFilter(req,
                       res);
    }

    public void init(FilterConfig config)
        throws ServletException
    {
    }

    public void destroy()
    {
        //add code to release any resource
    }
}
