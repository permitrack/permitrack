package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SourceUsageReadingsPageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageReadingsPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_READ,
                           request))
        {
            mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.SOURCE_USAGE_CAN_UPDATE,
                            hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_UPDATE,
                                          request),
                            request);
        setSessionAttribute(SessionKeys.SOURCE_USAGE_CAN_DELETE,
                            hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_DELETE,
                                          request),
                            request);
        try
        {
            ClientValue
                clientValue =
                getClientValue(request);
            if (request.getAttribute("type")
                == null
                || request.getAttribute("type")
                .equals("source"))
            {
                List
                    bhList =
                    findNonMeterOtherAssets(clientValue.getId());
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_OTHER_LIST_BY_CLIENT,
                                     bhList);
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_OTHER_COUNT,
                                     bhList.size());
                List
                    pList =
                    findNonMeterProcessAssets(clientValue.getId());
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_PROCESS_LIST_BY_CLIENT,
                                     pList);
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_PROCESS_COUNT,
                                     pList.size());
                List
                    meters =
                    findMeters(clientValue.getId());
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_METER_LIST_BY_CLIENT,
                                     meters);
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_METER_COUNT,
                                     meters.size());
            }
            else if (request.getAttribute("type")
                .equals("control"))
            {
                List
                    bhList =
                    EnvAsset.findAssetsWithControlsByClient(clientValue.getId());
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_METER_LIST_BY_CLIENT,
                                     bhList);
                request.setAttribute(SessionKeys.EV_ASSET_ACTIVE_METER_COUNT,
                                     bhList.size());
            }
            setSessionAttribute(SessionKeys.EV_ASSET_ID,
                                null,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getCause()};
            addError(new ActionMessage("source.usage.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("source.usage.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }

    private List findNonMeterProcessAssets(Integer clientId)
    {
        return getFinalList(EnvAsset.findProcessAssetsByClient(clientId));
    }

    private List findNonMeterOtherAssets(Integer clientId)
    {
        return getFinalList(EnvAsset.findOtherAssetsByClient(clientId));
    }

    private List findMeters(Integer clientId)
    {
        return getFinalList(EnvAsset.findMetersByClientIdSortByNumber(clientId));
    }
}
