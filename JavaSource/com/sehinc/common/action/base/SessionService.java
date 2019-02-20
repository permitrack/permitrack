package com.sehinc.common.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.server.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SessionService
{
    private static
    Logger
        LOG =
        Logger.getLogger(SessionService.class);

    public static void setSessionAttribute(String name, Object obj, HttpServletRequest request)
    {
        request.getSession()
            .setAttribute(name,
                          obj);
    }

    public static Object getSessionAttribute(String name, HttpServletRequest request)
    {
        return request.getSession()
            .getAttribute(name);
    }

    public static int getSecurityLevel(HttpServletRequest request)
    {
        Integer
            securityLevel =
            (Integer) request.getSession()
                .getAttribute(SessionKeys.SECURITY_LEVEL);
        if (securityLevel
            == null)
        {
            UserValue
                userValue =
                getUserValue(request);
            if (userValue
                == null)
            {
                securityLevel =
                    0;
            }
            else
            {
                securityLevel =
                    UserService.getSecurityLevel(userValue.getGroupId());
            }
            request.getSession()
                .setAttribute(SessionKeys.SECURITY_LEVEL,
                              securityLevel);
        }
        return securityLevel;
    }

    public static UserValue getUserValue(HttpServletRequest request)
    {
        try
        {
            if (request
                != null
                && request.getSession()
                   != null)
            {
                if (request.getSession()
                        .getAttribute(SessionKeys.USER)
                    != null)
                {
                    return (UserValue) request.getSession()
                        .getAttribute(SessionKeys.USER);
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("Could not retrieve session key: "
                      + SessionKeys.USER);
        }
        return null;
    }

    public static ClientValue getClientValue(HttpServletRequest request, String moduleCode)
    {
        if (moduleCode
            != null)
        {
            if (CommonConstants.EROSION_CONTROL_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                if (request.getSession()
                        .getAttribute(SessionKeys.ESC_CLIENT)
                    != null)
                {
                    return (ClientValue) request.getSession()
                        .getAttribute(SessionKeys.ESC_CLIENT);
                }
            }
            else if (CommonConstants.STORM_WATER_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                if (request.getSession()
                        .getAttribute(SessionKeys.MS4_CLIENT)
                    != null)
                {
                    return (ClientValue) request.getSession()
                        .getAttribute(SessionKeys.MS4_CLIENT);
                }
            }
            else if (CommonConstants.SECURITY_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                if (request.getSession()
                        .getAttribute(SessionKeys.SEC_CLIENT)
                    != null)
                {
                    return (ClientValue) request.getSession()
                        .getAttribute(SessionKeys.SEC_CLIENT);
                }
            }
            else if (CommonConstants.DATA_VIEW_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                if (request.getSession()
                        .getAttribute(SessionKeys.DVO_CLIENT)
                    != null)
                {
                    return (ClientValue) request.getSession()
                        .getAttribute(SessionKeys.DVO_CLIENT);
                }
            }
            else if (CommonConstants.ENVIRONMENT_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                if (request.getSession()
                        .getAttribute(SessionKeys.ENV_CLIENT)
                    != null)
                {
                    return (ClientValue) request.getSession()
                        .getAttribute(SessionKeys.ENV_CLIENT);
                }
            }
        }
        return null;
    }

    public static void setClientValue(HttpServletRequest request, String moduleCode, ClientValue clientValue)
    {
        if (moduleCode
            != null)
        {
            if (CommonConstants.EROSION_CONTROL_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                setSessionAttribute(SessionKeys.ESC_CLIENT,
                                    clientValue,
                                    request);
            }
            else if (CommonConstants.STORM_WATER_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                setSessionAttribute(SessionKeys.MS4_CLIENT,
                                    clientValue,
                                    request);
            }
            else if (CommonConstants.SECURITY_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                setSessionAttribute(SessionKeys.SEC_CLIENT,
                                    clientValue,
                                    request);
            }
            else if (CommonConstants.DATA_VIEW_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                setSessionAttribute(SessionKeys.DVO_CLIENT,
                                    clientValue,
                                    request);
            }
            else if (CommonConstants.ENVIRONMENT_MODULE
                .equalsIgnoreCase(moduleCode))
            {
                setSessionAttribute(SessionKeys.ENV_CLIENT,
                                    clientValue,
                                    request);
            }
        }
    }
}


