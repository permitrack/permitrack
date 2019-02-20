package com.sehinc.environment.action.sourcesubstance;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.db.substance.EnvSubstance;
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

public class SourceSubstanceCreatePageAction
    extends SourceSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSubstanceCreatePageAction.class);

    public ActionForward sourceSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceSubstanceCreatePageAction. ";
        strLog =
            strMod
            + "sourceSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_SUBSTANCE_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new source substance.");
            addError(new ActionMessage("source.substance.create.unauthorized"), request);
            return mapping.findForward("source.view.page");
        }
        else
        {
            LOG.debug(strLog
                      + "preparing to create new source substance page.");
            try
            {
                SourceSubstanceForm
                    ssForm =
                    (SourceSubstanceForm) form;
                ssForm.reset();
                Integer
                    sourceId =
                    (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                                  request);
                ssForm.setSourceId(sourceId);
                ClientValue
                    clientValue =
                    getClientValue(request);
                EnvSource
                    source =
                    EnvSource.findSourceByClientIdAndSourceId(clientValue.getId(),
                                                              sourceId);
                ssForm.setSourceDisplayName(source.getDisplayName());
                Integer
                    srcTypeCode =
                    source.getSourceType()
                        .getCode();
                String
                    tableName =
                    EnvUnitOfMeasureMap.SOURCE_SUBSTANCE_TABLE;
                switch (srcTypeCode)
                {
                    case 1:
                        break;
                    case 2:
                        List
                            ngFactorUnits =
                            EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                                     tableName,
                                                                     EnvUnitOfMeasureMap.SOURCE_SUB_NG_COL);
                        setSessionAttribute(SessionKeys.EV_NAT_GAS_EMISSION_LIST,
                                            ngFactorUnits,
                                            request);
                        break;
                    case 3:
                        List
                            blFactorUnits =
                            EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                                     tableName,
                                                                     EnvUnitOfMeasureMap.SOURCE_SUB_BL_COL);
                        setSessionAttribute(SessionKeys.EV_BULK_LIQ_EMISSION_LIST,
                                            blFactorUnits,
                                            request);
                        break;
                    default:
                }
                setSessionAttribute(SessionKeys.EV_SRC_SUB_SRC_TYPE,
                                    srcTypeCode,
                                    request);
                List
                    substances =
                    EnvSubstance.findByClientId(clientValue.getId());
                setSessionAttribute(SessionKeys.EV_SUBSTANCE_ACTIVE_LIST_BY_CLIENT,
                                    substances,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {e.getMessage()};
                addError(new ActionMessage("source.substance.create.load.failed",
                                           parameters), request);
                LOG.error(ApplicationResources.getProperty("source.substance.create.load.failed",
                                                           parameters));
                return mapping.getInputForward();
            }
            return mapping.findForward("continue");
        }
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_SUBSTANCE_MENU_ITEM);
    }
}