package com.sehinc.portal.action.base;

import com.sehinc.common.action.base.BaseActionUnsecure;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class ErrorAction
    extends BaseActionUnsecure
{
    private static
    Logger
        LOG =
        Logger.getLogger(ErrorAction.class);
    private final static
    String
        NEWLINE =
        ApplicationProperties.getNewline();
    private final static
    int
        ALIGNMENT_SPACING =
        14;
    public final static
    String
        REQUEST_URL_KEY =
        "error.request.url";
    static
    int
        trackCount =
        0;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        try
        {
            String
                tracking =
                (++trackCount)
                + "-"
                + (System.currentTimeMillis()
                   % 10000000);
            Throwable
                t =
                (Exception) request.getAttribute(PageContext.EXCEPTION);
            if (t
                == null)
            {
                t =
                    (Exception) request.getAttribute("org.apache.struts.action.EXCEPTION");
            }
            writeLogInfo(request,
                         tracking,
                         t);
            request.setAttribute("tracking",
                                 tracking);
        }
        catch (Throwable t)
        {
            LOG.error("Throwable caught in error handling code.",
                      t);
        }
        return mapping.findForward(PortalUtils.getUserMarkup(request));
    }

    private void writeLogInfo(HttpServletRequest request, String tracking, Throwable t)
    {
        String
            username;
        UserValue
            userValue =
            SessionService.getUserValue(request);
        if (userValue
            != null)
        {
            username =
                userValue.getUsername();
        }
        else
        {
            username =
                "not logged in";
        }
        StringBuffer
            buffer =
            new StringBuffer();
        appendNameValuePair(buffer,
                            "tracking:",
                            tracking);
        appendNameValuePair(buffer,
                            "version:",
                            ApplicationProperties.getProperty("version"));
        appendNameValuePair(buffer,
                            "username:",
                            username);
        appendNameValuePair(buffer,
                            "browser:",
                            request.getHeader("User-Agent"));
        appendNameValuePair(buffer,
                            "request URL:",
                            (String) request.getAttribute(REQUEST_URL_KEY));
        appendNameValuePair(buffer,
                            "referer:",
                            request.getHeader("Referer"));
        if (t
            != null)
        {
            LOG.error("Exception encountered"
                      + NEWLINE
                      + "--------------------"
                      + buffer.toString()
                      + ""
                      + NEWLINE
                      + "--------------------",
                      t);
        }
        else
        {
            LOG.error("Error, exception not found"
                      + NEWLINE
                      + "--------------------"
                      + buffer.toString()
                      + ""
                      + NEWLINE
                      + "--------------------");
        }
    }

    private void appendNameValuePair(StringBuffer buffer, String name, String value)
    {
        buffer.append(NEWLINE);
        buffer.append(StringUtil.padRight(name,
                                          " ",
                                          ALIGNMENT_SPACING));
        buffer.append(value);
    }

    protected void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }

    protected void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }
}