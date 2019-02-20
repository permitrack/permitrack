package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
import com.sehinc.environment.db.controlusage.EnvControlUsage;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.AssetSubstanceValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ControlUsageEditPageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ControlUsageEditPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        SourceUsageForm
            controlUsageForm =
            (SourceUsageForm) form;
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_UPDATE,
                           request))
        {
            return mapping.findForward("page.permission.denied");
        }
        Integer
            controlUsageId;
        if (request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID)
            != null)
        {
            controlUsageId =
                new Integer(request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID));
            LOG.debug("controlUsageId="
                      + request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                     request)
                 != null)
        {
            controlUsageId =
                (Integer) getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                              request);
            LOG.debug("Got controlUsageId="
                      + controlUsageId
                      + " from the session.");
            LOG.debug("controlUsageId="
                      + getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                            request));
        }
        else
        {
            return mapping.findForward("control.usage.list.page");
        }
        EnvControlUsage
            envControlUsage =
            new EnvControlUsage();
        try
        {
            envControlUsage.setId(controlUsageId);
            envControlUsage.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {controlUsageId};
            LOG.error(ApplicationResources.getProperty("control.usage.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("control.usage.error.load.failed",
                                       parameters), request);
            return mapping.findForward("control.usage.list.page");
        }
        Integer
            assetId =
            (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                          request);
        ArrayList<AssetSubstanceValue>
            assetSubstanceList =
            new ArrayList<AssetSubstanceValue>();
        List
            efList =
            EnvAssetSubstance.findByAssetId(assetId);
        for (Object o : efList)
        {
            EnvAssetSubstance
                asub =
                (EnvAssetSubstance) o;
            try
            {
                AssetSubstanceValue
                    eff =
                    new AssetSubstanceValue();
                EnvSubstanceTypeData
                    sub =
                    EnvSubstanceTypeData.findByTypeCode(asub.getSubstanceTypeCd());
                if (sub
                    != null)
                {
                    eff.setSubstanceTypeName(sub.getDescription()
                                             + " - "
                                             + asub.getEfficiencyFactor()
                                             + "% - "
                                             + asub.getAdditionalInfo());
                }
                eff.setAssetSubstanceId(asub.getId());
                eff.setEfficiencyFactor(asub.getEfficiencyFactor());
                eff.setAdditionalInfo(asub.getAdditionalInfo());
                assetSubstanceList.add(eff);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("asset.substance.error.load.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.substance.error.load.failed"), request);
            }
        }
        setSessionAttribute(SessionKeys.EV_ASSET_SUBSTANCE_LIST,
                            assetSubstanceList,
                            request);
        controlUsageForm.setSourceUsageId(envControlUsage.getId());
        controlUsageForm.setAssetSourceId(envControlUsage.getAssetControlId());
        controlUsageForm.setPeriodStartTs(envControlUsage.getPeriodStartTs());
        controlUsageForm.setPeriodEndTs(envControlUsage.getPeriodEndTs());
        controlUsageForm.setDescription(envControlUsage.getDescription());
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.CONTROL_USAGE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.CONTROL_USAGE_EDIT_MENU_ITEM);
    }
}
