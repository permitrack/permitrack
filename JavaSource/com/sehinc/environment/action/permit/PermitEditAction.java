package com.sehinc.environment.action.permit;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermit;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitEditAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitEditAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitEditPageAction. ";
        strLog =
            strMod
            + "permitAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.edit.cancel.action"), request);
            return mapping.findForward("permit.list.page");
        }
        else
        {
            PermitForm
                permitForm =
                (PermitForm) form;
            UserValue
                userValue =
                getUserValue(request);
            ClientValue
                objClient =
                getClientValue(request);
            Integer
                permitId =
                permitForm.getId();
            EnvPermit
                envPermit =
                new EnvPermit(permitId);
            try
            {
                envPermit.load();
                if (!envPermit.getStatus()
                    .getCode()
                    .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                {
                    throw new Exception("The requested Permit ID = "
                                        + permitId
                                        + " does not exist.");
                }
                envPermit.setName(permitForm.getName());
                envPermit.setDescription(permitForm.getDescription());
                envPermit.setClientId(permitForm.getClientId());
                envPermit.setActiveTs(permitForm.getActiveTs());
                envPermit.setInactiveTs(permitForm.getInactiveTs());
                envPermit.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                envPermit.setClientId(objClient.getId());
                envPermit.save(userValue);
                setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                    permitId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {permitId};
                LOG.error(ApplicationResources.getProperty("permit.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("permit.list.page");
            }
        }
        addMessage(new ActionMessage("permit.edit.success"), request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_EDIT_MENU_ITEM);
    }
}
