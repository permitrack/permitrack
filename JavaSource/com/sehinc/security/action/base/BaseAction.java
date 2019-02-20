package com.sehinc.security.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.contact.ContactShortValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public abstract class BaseAction
    extends com.sehinc.common.action.base.BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BaseAction.class);

    protected void Throw(String strLog, String strError, Exception e)
        throws Exception
    {
        String
            strMessage;
        strMessage =
            strLog
            + strError
            + "<br>Message:<br> "
            + e.getMessage();
        LOG.debug(strMessage);
        throw new Exception(strMessage);
    }

    protected void Throw(String strLog, String strError)
        throws Exception
    {
        throw new Exception(strLog
                            + strError);
    }

    protected void Throw(String strError)
        throws Exception
    {
        throw new Exception(strError);
    }

    // TODO rename
    protected Integer getClientIdFromRequestOrSession(HttpServletRequest request)
    {
        if (request.getParameter(RequestKeys.CLIENT_ID)
            != null)
        {
            Integer
                client_id =
                new Integer(request.getParameter(RequestKeys.CLIENT_ID));
            if (client_id
                > 0)
            {
                ClientData
                    clientData =
                    new ClientData(client_id);
                if (clientData.load())
                {
                    setSessionAttribute(SessionKeys.SEC_CLIENT,
                                        new ClientValue(clientData),
                                        request);
                }
                else
                {
                    LOG.error("Failed to load client ID "
                              + request.getParameter(RequestKeys.CLIENT_ID));
                }
            }
            return client_id;
        }
        if (getClientValue(request)
            != null)
        {
            LOG.debug("Client Id in session = "
                      + getClientValue(request).getId());
            return getClientValue(request).getId();
        }
        return null;
    }

    protected void setClientInSession(Integer intClientId, HttpServletRequest request)
        throws Exception
    {
        try
        {
            ClientData
                clientData =
                new ClientData(intClientId);
            if (!clientData.load()
                || !clientData.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("Could not load Client ID = "
                                    + intClientId.toString());
            }
            setSessionAttribute(SessionKeys.SEC_CLIENT,
                                new ClientValue(clientData),
                                request);
        }
        catch (Exception e)
        {
            LOG.error("Unable to load client Id = "
                      + intClientId.toString());
            throw e;
        }
    }

    protected void setContactIdInSession(Integer intContactId, HttpServletRequest request)
        throws Exception
    {
        try
        {
            setSessionAttribute(SessionKeys.CONTACT_ID,
                                intContactId ,
                                request);
            setSessionAttribute(SessionKeys.CONTACT_SHORT_VALUE,
                                new ContactShortValue(intContactId),
                                request);
        }
        catch (Exception e)
        {
            LOG.error("Unable to save the contact id and contact short value into the session.");
            throw e;
        }
    }

    protected void setUserIdInSession(Integer intUserId, HttpServletRequest request)
        throws Exception
    {
        try
        {
            UserData
                userData =
                new UserData(intUserId);
            if (!userData.load()
                || !userData.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("Failed to load user ID = "
                                    + intUserId.toString());
            }
            setSessionAttribute(SessionKeys.EDIT_USER,
                                new UserValue(userData),
                                request);
        }
        catch (Exception e)
        {
            LOG.error("Failed to load user ID = "
                      + intUserId.toString());
            throw e;
        }
    }

    protected void setRoleIdInSession(Integer intRoleId, HttpServletRequest request)
    {
        setSessionAttribute(SessionKeys.ROLE_ID,
                            intRoleId,
                            request);
    }

    protected Integer getUserId(HttpServletRequest request)
    {
        String
            strLog =
            "getUserId() ";
        UserValue
            userValue =
            null;
        Integer
            userId;
        if (request.getParameter(RequestKeys.USER_ID)
            != null)
        {
            userId =
                new Integer(request.getParameter(RequestKeys.USER_ID));
        }
        else if (getSessionAttribute(SessionKeys.EDIT_USER, request)
                 != null
                 && getSessionAttribute(SessionKeys.EDIT_USER, request) instanceof UserValue)
        {
            userValue =
                (UserValue) getSessionAttribute(SessionKeys.EDIT_USER, request);
            userId =
                userValue.getId();
        }
        else
        {
            userValue = getUserValue(request);
                //getUser();
            userId =
                userValue.getId();
        }
        if (userValue
            != null)
        {
            LOG.debug(strLog
                      + "Unable to get edit user value from request or the session.");
        }
        else
        {
            LOG.debug(strLog
                      + "User Id = "
                      + userId.toString());
        }
        return userId;
    }

    protected Integer getContactId(HttpServletRequest request)
    {
        String
            strLog =
            "getContactId() ";
        ContactShortValue
            contactShortValue;
        Integer
            contactId =
            0;
        LOG.debug(strLog
                  + "in method");
        if (request.getParameter(RequestKeys.CONTACT_ID)
            != null)
        {
            LOG.debug("request contact id begin");
            contactId =
                new Integer(request.getParameter(RequestKeys.CONTACT_ID));
            LOG.debug("request contact id end");
        }
        else if (getSessionAttribute(SessionKeys.CONTACT_ID, request)
                 != null)
        {
            LOG.debug("session contact id begin");
            contactId =
                (Integer) getSessionAttribute(SessionKeys.CONTACT_ID, request);
            LOG.debug("session contact id end");
        }
        else if (getSessionAttribute(SessionKeys.CONTACT_SHORT_VALUE, request)
                 != null)
        {
            LOG.debug("one");
            if (getSessionAttribute(SessionKeys.CONTACT_SHORT_VALUE, request) instanceof ContactShortValue)
            {
                LOG.debug("two");
                contactShortValue =
                    (ContactShortValue) getSessionAttribute(SessionKeys.CONTACT_SHORT_VALUE, request);
                LOG.debug("three");
                contactId =
                    contactShortValue.getId();
                LOG.debug("four");
            }
            else
            {
                LOG.debug(strLog
                          + "Session contact short value is not of type Contact Short Value.");
            }
        }
        else
        {
            LOG.debug(strLog
                      + "Unable to get contact short value from request or the session.");
        }
        LOG.debug(strLog
                  + "Contact Id = "
                  + contactId.toString());
        return contactId;
    }

    protected Integer getRoleIdFromRequestOrSession(HttpServletRequest request)
    {
        String
            strLog =
            "getRoleIdFromRequestOrSession() ";
        Integer
            i =
            0;
        LOG.debug(strLog
                  + "in method");
        if (request.getParameter(RequestKeys.ROLE_ID)
            != null)
        {
            i =
                new Integer(request.getParameter(RequestKeys.ROLE_ID));
        }
        else if (getSessionAttribute(SessionKeys.ROLE_ID, request)
                 != null)
        {
            if (getSessionAttribute(SessionKeys.ROLE_ID, request) instanceof Integer)
            {
                i =
                    (Integer) getSessionAttribute(SessionKeys.ROLE_ID, request);
            }
            else
            {
                LOG.debug(strLog
                          + "Session Role ID is not of type Integer.");
            }
        }
        else
        {
            LOG.debug(strLog
                      + "Unable to get Role ID value from request or the session.");
        }
        LOG.debug(strLog
                  + "Role Id = "
                  + i.toString());
        return i;
    }

    protected String getModuleIdFromRequest(HttpServletRequest request)
    {
        String
            i =
            "";
        if (request.getParameter(RequestKeys.MODULE_ID)
            != null)
        {
            i =
                request.getParameter(RequestKeys.MODULE_ID);
        }
        return i;
    }

    protected void setPrimaryMenu(String strPrimaryMenuName, HttpServletRequest request)
    {
        if (strPrimaryMenuName.equals(PrimaryMenu.SECURITY_MENU_NAME))
        {
            setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                PrimaryMenu.getInstance(getClientValue(request)
                                                        != null
                                                            ? getClientValue(request).getName()
                                                            : ""),
                                request);
        }
        else if (strPrimaryMenuName.equals(PrimaryMenu.SECURITY_MENU_NONE_NAME))
        {
            setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                PrimaryMenu.getInstanceNone(),
                                request);
        }
        else
        {
            removeSessionAttribute(SessionKeys.PRIMARY_MENU,
                                request);
        }
    }

    protected void setMessage(String strMessage, HttpServletRequest request)
    {
        if (strMessage
            == null)
        {
            strMessage =
                "";
        }
        setSessionAttribute(SessionKeys.SECURITY_MESSAGE,
                            strMessage,
                            request);
    }

    public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return super.execute(mapping,
                             form,
                             request,
                             response);
    }

    public PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        this.setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
        return (PrimaryMenu) request.getSession()
            .getAttribute(SessionKeys.PRIMARY_MENU);
    }

/*
    public UserValue getUser()
    {
        return getUserValue(request);
    }
*/

    public void setSecondaryMenu(SecondaryMenu secondaryMenu, HttpServletRequest request)
    {
        LOG.debug("setSecondaryMenu in method "
                  + secondaryMenu.getName());
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }

    public boolean isDeleted(HttpServletRequest request)
    {
        String
            submitString =
            (String) request.getAttribute("submit");
        LOG.debug("submitString = "
                  + submitString);
        return submitString
               != null
               && submitString.equalsIgnoreCase("Delete");
    }

    protected boolean checkActionMessages(ActionMessages objAE, HttpServletRequest request)
    {
        boolean
            blnErrorsFound =
            false;
        ActionMessage
            objE;
        Iterator
            i;
        String
            strMessage =
            "";
        Object[]
            objValues;
        int
            b;
        if (!objAE.isEmpty())
        {
            blnErrorsFound =
                true;
            i =
                objAE.get();
            while (i.hasNext())
            {
                objE =
                    (ActionMessage) i.next();
                objValues =
                    objE.getValues();
                for (
                    b =
                        0; b
                           < objE.getValues().length; b++)
                {
                    strMessage =
                        strMessage
                        + objValues[b]
                        + ", ";
                }
            }
            if (strMessage.length()
                > 2)
            {
                strMessage =
                    strMessage.substring(0,
                                         strMessage.length()
                                         - 2);
            }
        }
        setSessionAttribute(SessionKeys.SECURITY_MESSAGE,
                            strMessage,
                            request);
        setSessionAttribute(SessionKeys.ACTION_ERRORS,
                            objAE,
                            request);
        return blnErrorsFound;
    }

/*
    protected void setSessionAttribute(String name, Object obj, HttpServletRequest request)
    {
        request.getSession()
            .setAttribute(name,
                          obj);
    }

    protected Object getSessionAttribute(String name)
    {
        return request.getSession()
            .getAttribute(name);
    }

    protected void removeSessionAttribute(String name)
    {
        request.getSession()
            .removeAttribute(name);
    }
*/

/*
    public UserValue getUserValue(request)
    {
        return SessionService.getUserValue(request);
    }
*/

    public ClientValue getClientValue(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.SECURITY_MODULE);
        if (clientValue
            == null)
        {
            clientValue =
                getClientValueDefault(request);
            if (clientValue
                != null)
            {
                setSessionAttribute(SessionKeys.SEC_CLIENT,
                                    clientValue,
                                    request);
            }
        }
        return clientValue;
    }

/*
    protected SecurityManager getSecurityManager()
        throws Exception
    {
        return this.getSecurityManager(request);
    }
*/

    protected void setPortalMenu(HttpServletRequest request)
    {
        PortalMenu
            portalMenu =
            (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU, request);
        portalMenu.setCurrentItemByCode(CommonConstants.SECURITY_MODULE);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPortalMenu(request);
    }
}
