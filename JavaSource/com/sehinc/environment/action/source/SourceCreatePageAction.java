package com.sehinc.environment.action.source;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.db.lookup.EnvDisplayColorData;
import com.sehinc.environment.db.lookup.EnvSourceTypeData;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
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
import java.util.List;

public class SourceCreatePageAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceCreatePageAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceCreatePageAction. ";
        strLog =
            strMod
            + "sourceAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new source.");
            addError(new ActionMessage("source.create.unauthorized"), request);
            return mapping.findForward("source.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new source page.");
        try
        {
            SourceForm
                sourceForm =
                (SourceForm) form;
            sourceForm.reset();
            ClientValue
                clientValue =
                getClientValue(request);
            sourceForm.setClientName(clientValue.getName());
            List
                sTypes =
                EnvSourceTypeData.findAllByClientId(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_SOURCE_TYPE_LIST,
                                sTypes,
                                request);
            Integer
                sourceId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                              request);
            sourceForm.setId(sourceId);
            String
                tableName =
                EnvUnitOfMeasureMap.SOURCE_TABLE;
            List
                lbsVOC_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.SOURCE_LBS_VOC_COL);
            setSessionAttribute(SessionKeys.EV_VOC_UM_LIST,
                                lbsVOC_units,
                                request);
            List
                density_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.SOURCE_DENSITY_COL);
            setSessionAttribute(SessionKeys.EV_DENSITY_UM_LIST,
                                density_units,
                                request);
            List
                lbsHAPS_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.SOURCE_LBS_HAPS_COL);
            setSessionAttribute(SessionKeys.EV_HAPS_UM_LIST,
                                lbsHAPS_units,
                                request);
            List
                colors =
                EnvDisplayColorData.findAllInOrder();
            setSessionAttribute(SessionKeys.SOURCE_DISPLAY_COLOR_LIST,
                                colors,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("source.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("source.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.SOURCE_CREATE_MENU_ITEM);
    }
}
