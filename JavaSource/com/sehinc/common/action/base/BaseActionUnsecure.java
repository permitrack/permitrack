package com.sehinc.common.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.security.action.base.RequestKeys;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.StringTokenizer;

import static com.sehinc.common.action.base.RequestKeys.REQUEST_URL_KEY;

public abstract class BaseActionUnsecure
    extends Action
{
    private static
    Logger
        LOG =
        Logger.getLogger(BaseActionUnsecure.class);

    public abstract ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    abstract protected void finalizeAction(HttpServletRequest request)
        throws Exception;

    protected void finalizeAction(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        this.finalizeAction(request);
    }

    abstract protected void initializeAction(HttpServletRequest request)
        throws Exception;

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        handleStrutsParameter(mapping,
                              request);
        try
        {
            initActionErrors(request);
            initActionMessages(request);
            initializeAction(request);
            ActionForward
                forward2 =
                doAction(mapping,
                         form,
                         request,
                         response);
            finalizeAction(request,
                           response);
            disableCache(response);
            if (forward2
                == null)
            {
                LOG.warn("ActionForward is null.");
            }
            else if (forward2.getRedirect())
            {
                setSessionAttribute(Globals.MESSAGE_KEY,
                                    getMessages(request),
                                    request);
                setSessionAttribute(Globals.ERROR_KEY,
                                    getErrors(request),
                                    request);
            }
            return forward2;
        }
        catch (Throwable t)
        {
            LOG.error(t.getMessage(),
                      t);
            return handleException(t,
                                   request);
        }
    }

    protected void saveMessages(HttpServletRequest request, ActionMessages messages)
    {
        super.saveMessages(request,
                           messages);
    }

    protected void saveErrors(HttpServletRequest request, ActionMessages errors)
    {
        super.saveErrors(request,
                         errors);
    }

    private void initActionErrors(HttpServletRequest request)
    {
        ActionMessages
            errors =
            (ActionMessages) getSessionAttribute(Globals.ERROR_KEY,
                                                 request);
        if (errors
            != null)
        {
            saveErrors(request,
                       errors);
        }
        removeSessionAttribute(Globals.ERROR_KEY,
                               request);
    }

    private void initActionMessages(HttpServletRequest request)
    {
        ActionMessages
            messages =
            (ActionMessages) getSessionAttribute(Globals.MESSAGE_KEY,
                                                 request);
        if (messages
            != null)
        {
            saveMessages(request,
                         messages);
        }
        removeSessionAttribute(Globals.MESSAGE_KEY,
                               request);
    }

    public void addMessage(ActionMessage message, HttpServletRequest request)
    {
        ActionMessages
            messages =
            getMessages(request);
        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     message);
        saveMessages(request,
                     messages);
    }

    public void addError(ActionMessage message, HttpServletRequest request)
    {
        ActionMessages
            errors =
            getErrors(request);
        errors.add(ActionErrors.GLOBAL_MESSAGE,
                   message);
        saveErrors(request,
                   errors);
    }

    protected ActionForward handleException(Throwable t, HttpServletRequest request)
    {
        LOG.debug("handleException: "
                  + t.toString());
        if (request.getAttribute("org.apache.struts.action.EXCEPTION")
            != null)
        {
            return null;
        }
        request.setAttribute("org.apache.struts.action.EXCEPTION",
                             t);
        try
        {
            request.setAttribute(REQUEST_URL_KEY,
                                 getRequestURLWithParameters(request));
        }
        catch (UnsupportedEncodingException uee)
        {
            request.setAttribute(REQUEST_URL_KEY,
                                 "Unknown");
        }
        return getErrorForward();
    }

    public static String getRequestURLWithParameters(HttpServletRequest request)
        throws UnsupportedEncodingException
    {
        LOG.debug("getRequestURLWithParameters in method");
        StringBuilder
            buffer =
            new StringBuilder();
        buffer.append(request.getServletPath());
        boolean
            first =
            true;
        Enumeration
            myenum =
            request.getParameterNames();
        while (myenum.hasMoreElements())
        {
            String
                parameter =
                (String) myenum.nextElement();
            String[]
                values =
                request.getParameterValues(parameter);
            for (String value : values)
            {
                if (first)
                {
                    buffer.append('?');
                    first =
                        false;
                }
                else
                {
                    buffer.append('&');
                }
                buffer.append(URLEncoder.encode(parameter,
                                                "UTF-8"));
                buffer.append('=');
                buffer.append(URLEncoder.encode(value,
                                                "UTF-8"));
            }
        }
        LOG.debug("getRequestURLWithParameters return "
                  + buffer.toString());
        return buffer.toString();
    }

    private void handleStrutsParameter(ActionMapping mapping, HttpServletRequest request)
    {
        String
            attributes =
            mapping.getParameter();
        String
            name;
        String
            value;
        StringTokenizer
            nvPair;
        StringTokenizer
            attributeList;
        if (attributes
            != null)
        {
            attributeList =
                new StringTokenizer(attributes,
                                    ";",
                                    false);
            while (attributeList.hasMoreTokens())
            {
                nvPair =
                    new StringTokenizer(attributeList.nextToken(),
                                        "=",
                                        false);
                if (nvPair.hasMoreTokens())
                {
                    name =
                        nvPair.nextToken();
                    value =
                        nvPair.nextToken();
                    request.setAttribute(name,
                                         value);
                }
            }
        }
    }

    private void disableCache(HttpServletResponse response)
    {
        response.addHeader("Pragma",
                           "no-cache");
        response.addHeader("Cache-control",
                           "no-cache, no-store");
    }

    public static ActionForward getLoginRedirect()
    {
        return new ActionForward(CommonConstants.FORWARD_LOGIN,
                                 "/login",
                                 true,
                                 "/");
    }

    public static ActionForward getErrorForward()
    {
        return new ActionForward("error",
                                 "/error.do",
                                 false,
                                 "/");
    }

    protected static int getParamInt(String name, HttpServletRequest request)
    {
        String
            idParam =
            request.getParameter(name);
        if (idParam
            == null)
        {
            idParam =
                (String) request.getAttribute(name);
        }
        if (idParam
            != null)
        {
            try
            {
                return Integer.valueOf(idParam);
            }
            catch (Exception e)
            {
                return 0;
            }
        }
        else
        {
            return 0;
        }
    }

    protected void setSessionAttribute(String name, Object obj, HttpServletRequest request)
    {
        request.getSession()
            .setAttribute(name,
                          obj);
    }

    protected Object getSessionAttribute(String name, HttpServletRequest request)
    {
        return request.getSession()
            .getAttribute(name);
    }

    protected void removeSessionAttribute(String name, HttpServletRequest request)
    {
        request.getSession()
            .removeAttribute(name);
    }

    public static String getPublicMapUrl(Integer clientId, HttpServletRequest request)
    {
        StringBuilder
            buffer =
            new StringBuilder();
        try
        {
            buffer.append(ApplicationProperties.getBaseURL(request));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context"));
            buffer.append(ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + com.sehinc.security.action.base.RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(com.sehinc.security.action.base.RequestKeys.EC_PROJECT_MAP_VIEW_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + com.sehinc.security.action.base.RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientId.toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + com.sehinc.security.action.base.RequestKeys.DETAIL_PARAMETER
                          + "="
                          + URLEncoder.encode(com.sehinc.security.action.base.RequestKeys.RESTRICTED_DETAIL,
                                              "UTF-8"));
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating project URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating project URL",
                      uee);
        }
        return buffer.toString();
    }

    public static String getPrivateMapUrl(Integer clientId, Integer projectId, HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        try
        {
            buffer.append(ApplicationProperties.getBaseURL(request));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context"));
            buffer.append(ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + com.sehinc.security.action.base.RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(com.sehinc.security.action.base.RequestKeys.EC_PROJECT_MAP_VIEW_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + com.sehinc.security.action.base.RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientId.toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + com.sehinc.security.action.base.RequestKeys.DETAIL_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.FULL_DETAIL,
                                              "UTF-8"));
            if(projectId != null && projectId > 0)
            {
                buffer.append("&"
                              + com.sehinc.security.action.base.RequestKeys.EC_PROJECT_ID
                              + "="
                              + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                      projectId.toString()),
                                                  "UTF-8"));
            }
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating project URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating project URL",
                      uee);
        }
        return buffer.toString();
    }
}
