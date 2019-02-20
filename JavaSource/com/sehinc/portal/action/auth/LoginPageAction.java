package com.sehinc.portal.action.auth;

import com.sehinc.common.action.base.BaseActionUnsecure;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageAction
    extends BaseActionUnsecure
{
    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        if (request.getSession()
            != null
            && request.getSession()
                   .getAttribute("redirect")
               != null)
        {
            LoginForm
                loginForm =
                (LoginForm) form;
            loginForm.setRedirect((String) request.getSession()
                .getAttribute("redirect"));
        }
        request.getSession()
            .invalidate();
        /* TODO
                    List
                        ivList =
                        InfoVersion.findActive();
                    Iterator
                        ivi =
                        ivList.iterator();
                    if (ivi.hasNext())
                    {
                        InfoVersion
                            infoVersion =
                            (InfoVersion) ivi.next();
                        LOG.debug("Setting application version as "
                                  + infoVersion.getVersionId());
                        List
                            ieList =
                            InfoEnhancements.findActiveByVersionId(infoVersion.getId()
                                                                       .toString());
                        request.getSession()
                            .setAttribute(SessionKeys.INFO_VERSION_ID,
                                          infoVersion);
                        request.getSession()
                            .setAttribute(SessionKeys.INFO_ENHANCEMENTS_LIST_ACTIVE,
                                          ieList);
                    }
        */
        /*
                    LOG.debug("Getting active Tips and Events for login page.");
                    InfoTips
                        tip =
                        InfoTips.findRandomActive();
                    List
                        ievList =
                        InfoEvents.findActive();
                    request.getSession()
                        .setAttribute(SessionKeys.INFO_EVENTS_LIST_ACTIVE,
                                      ievList);
                    request.getSession()
                        .setAttribute(SessionKeys.INFO_TIP,
                                      tip);
        */
        /*
                }
                catch (Exception e)
                {
                    String
                        msg =
                        "LoginPageAction() method failed. Exception: "
                        + e.getCause()
                        + " "
                        + e.getLocalizedMessage();
                    LOG.error(msg);
                    throw new Exception(msg);
                }
        */
        return mapping.findForward("html");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }
}