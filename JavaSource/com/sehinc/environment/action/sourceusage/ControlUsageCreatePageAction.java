package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
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

public class ControlUsageCreatePageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ControlUsageCreatePageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceUsageCreatePageAction. ";
        strLog =
            strMod
            + "sourceUsageAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.usage.create.cancel.action"), request);
            return mapping.findForward("source.usage.list.page");
        }
        else
        {
            SecurityManager
                securityManager =
                getSecurityManager(request);
            if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE))
            {
                LOG.error("User ID "
                          + securityManager.getUserID()
                          + " is not authorized to create a new source usage.");
                addError(new ActionMessage("source.usage.create.unauthorized"), request);
                return mapping.findForward("source.usage.list.page");
            }
            else
            {
                try
                {
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
                }
                catch (Exception e)
                {
                    Object[]
                        parameters =
                        {e.getMessage()};
                    addError(new ActionMessage("source.usage.create.load.failed",
                                               parameters), request);
                    LOG.error(ApplicationResources.getProperty("source.usage.create.load.failed",
                                                               parameters));
                    return mapping.getInputForward();
                }
                return mapping.findForward("continue");
            }
        }
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.CONTROL_USAGE_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.CONTROL_USAGE_NEW_MENU_ITEM);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }
}