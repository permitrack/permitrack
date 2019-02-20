package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
import com.sehinc.environment.db.controlusage.EnvControlUsage;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlUsageViewPageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ControlUsageViewPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_READ,
                           request))
        {
            mapping.findForward("page.permission.denied");
        }
        SourceUsageForm
            srcUsageForm =
            (SourceUsageForm) form;
        Integer
            usageId =
            getControlUsageId(request);
        if (usageId
            < 1)
        {
            mapping.findForward("error");
        }
        EnvControlUsage
            envControlUsage =
            new EnvControlUsage();
        EnvAssetSubstance
            assetSubstance =
            new EnvAssetSubstance();
        try
        {
            envControlUsage.setId(usageId);
            envControlUsage.load();
            assetSubstance.setId(envControlUsage.getAssetControlId());
            assetSubstance.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {usageId};
            LOG.error(ApplicationResources.getProperty("source.usage.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("source.usage.error.load.failed",
                                       parameters), request);
            return mapping.findForward("source.usage.list.page");
        }
        setSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                            usageId,
                            request);
        srcUsageForm.setSourceUsageId(envControlUsage.getId());
        srcUsageForm.setSourceDisplayName(assetSubstance.getAdditionalInfo());
        srcUsageForm.setPeriodStartTs(envControlUsage.getPeriodStartTs());
        srcUsageForm.setPeriodEndTs(envControlUsage.getPeriodEndTs());
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.CONTROL_USAGE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.CONTROL_USAGE_VIEW_MENU_ITEM);
    }

    private Integer getControlUsageId(HttpServletRequest request)
    {
        Integer
            id;
        if (request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID)
            != null)
        {
            id =
                new Integer(request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                     request)
                 != null)
        {
            id =
                (Integer) getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                              request);
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("source.usage.error.no.source.usage.on.request"));
            addError(new ActionMessage("source.usage.error.no.source.usage.on.request"), request);
            return 0;
        }
        return id;
    }
}
