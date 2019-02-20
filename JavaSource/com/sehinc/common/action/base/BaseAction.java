package com.sehinc.common.action.base;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class BaseAction
    extends BaseActionUnsecure
{
    protected static
    Logger
        LOG =
        Logger.getLogger(BaseAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        if (getUserValue(request)
            == null)
        {
            request.getSession()
                .setAttribute("redirect",
                              UrlUtil.getUrl(request));
            LOG.warn(PortalResources.getProperty("errors.user.not.found.in.session"));
            addError(new ActionMessage("errors.user.not.found.in.session"),
                     request);
            return getLoginRedirect();
        }
        else
        {
            try
            {
                this.getSecurityManager(request);
            }
            catch (Exception e)
            {
                LOG.error("Failed to initialize Security Manager",
                          e);
                addError(new ActionMessage("error.security.manager.init.failed"),
                         request);
                return getErrorForward();
            }
        }
        return super.execute(mapping,
                             form,
                             request,
                             response);
    }

    protected void setRequestAttribute(String name, Object obj, HttpServletRequest request)
    {
        if (request
            != null)
        {
            request.setAttribute(name,
                                 obj);
        }
    }

    public UserValue getUserValue(HttpServletRequest request)
    {
        return SessionService.getUserValue(request);
    }

    protected abstract ClientValue getClientValue(HttpServletRequest request);

    protected ClientValue getClientValueDefault(HttpServletRequest request)
    {
        return getClientValueDefault("",
                                     request);
    }

    protected ClientValue getClientValueDefault(String module, HttpServletRequest request)
    {
        ClientValue
            clientValue =
            null;
        UserValue
            user =
            getUserValue(request);
        if (user
            != null
            && (UserService.getSecurityLevel(user.getGroupId())
                <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL))
        {
            List
                clients;
            if (module.isEmpty())
            {
                clients =
                    ClientService.getClients(user);
            }
            else
            {
                clients =
                    ClientService.getClientsByUserAndModule(user,
                                                            module);
            }
            if (clients.size()
                == 1)
            {
                clientValue =
                    new ClientValue((ClientData) clients.get(0));
            }
        }
        return clientValue;
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }

    protected SecurityManager getSecurityManager(HttpServletRequest request)
        throws Exception
    {
        SecurityManager
            securityManager =
            SecurityManager.getSecurityManager(request);
        securityManager.init(getUserValue(request),
                             getClientValue(request));
        return securityManager;
    }

    protected ActionForward redirect(Integer id, String url, HttpServletRequest request)
    {
        if (request.getParameter("root")
            == null)
        {
            try
            {
                String
                    savedNode =
                    getSavedNode(id,
                                 request);
                if (savedNode
                    != null)
                {
                    return new ActionForward(url
                                             + savedNode,
                                             true);
                }
            }
            catch (Exception e)
            {
            }
        }
        return null;
    }

    protected String getSavedNode(Integer id, HttpServletRequest request)
    {
        return null;
    }
}
