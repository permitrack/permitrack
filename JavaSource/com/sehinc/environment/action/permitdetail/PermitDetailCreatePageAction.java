package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
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

public class PermitDetailCreatePageAction
    extends PermitDetailBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDetailCreatePageAction.class);

    public ActionForward permitDetailAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitDetailCreatePageAction. ";
        strLog =
            strMod
            + "permitDetailAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_DETAIL_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new permit.");
            addError(new ActionMessage("permit.detail.create.unauthorized"), request);
            return mapping.findForward("permit.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new permit detail page.");
        try
        {
            PermitDetailForm
                detailForm =
                (PermitDetailForm) form;
            detailForm.reset();
            Integer
                permitId =
                (Integer) getSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                              request);
            detailForm.setPermitId(permitId);
            ClientValue
                clientValue =
                getClientValue(request);
            String
                tableName =
                EnvUnitOfMeasureMap.PERMIT_DETAIL_TABLE;
            List
                avgPeriod_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.PERM_DTL_AVG_PERIOD_COL);
            setSessionAttribute(SessionKeys.EV_AVG_PERIOD_LIST,
                                avgPeriod_units,
                                request);
            List
                vocLimit_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.PERM_DTL_VOC_LIMIT_COL);
            setSessionAttribute(SessionKeys.EV_VOC_LIMIT_LIST,
                                vocLimit_units,
                                request);
            List
                hapsLimit_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.PERM_DTL_HAPS_LIMIT_COL);
            setSessionAttribute(SessionKeys.EV_HAPS_LIMIT_LIST,
                                hapsLimit_units,
                                request);
            List
                mmbtuLimit_units =
                EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                         tableName,
                                                         EnvUnitOfMeasureMap.PERM_DTL_MMBTU_LIMIT_COL);
            setSessionAttribute(SessionKeys.EV_MMBTU_LIMIT_LIST,
                                mmbtuLimit_units,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("permit.detail.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("permit.detail.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_DETAIL_MENU_ITEM);
    }
}
