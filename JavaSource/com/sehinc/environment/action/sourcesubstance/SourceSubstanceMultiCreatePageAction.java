package com.sehinc.environment.action.sourcesubstance;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.db.sourcesubstance.EnvSourceTypeSubTypeMap;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SourceSubstanceValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SourceSubstanceMultiCreatePageAction
    extends SourceSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSubstanceMultiCreatePageAction.class);

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
        LOG.debug(strLog
                  + "preparing to create new source substance page.");
        try
        {
            ClientValue
                clientValue =
                getClientValue(request);
            Integer
                sourceId;
            LOG.debug("sourceId="
                      + getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                            request));
            if (getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                    request)
                != null)
            {
                sourceId =
                    (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                                  request);
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("source.error.no.source.on.request"));
                addError(new ActionMessage("source.error.no.source.on.request"), request);
                return mapping.findForward("source.list.page");
            }
            EnvSource
                source =
                new EnvSource(sourceId);
            source.load();
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
                substanceTypeList =
                EnvSourceTypeSubTypeMap.findSubstanceTypesBySourceTypeCd(source.getSourceType()
                                                                             .getCode());
            ArrayList<SourceSubstanceValue>
                substanceList =
                new ArrayList<SourceSubstanceValue>();
            for (Object o : substanceTypeList)
            {
                EnvSourceTypeSubTypeMap
                    map =
                    (EnvSourceTypeSubTypeMap) o;
                List
                    substances =
                    EnvSubstance.findBySubstanceTypeAndClientId(map.getSubstanceTypeCd(),
                                                                clientValue.getId());
                for (Object s : substances)
                {
                    EnvSubstance
                        substance =
                        (EnvSubstance) s;
                    SourceSubstanceValue
                        sourceSubstance =
                        new SourceSubstanceValue(substance);
                    substanceList.add(sourceSubstance);
                }
            }
            setSessionAttribute(SessionKeys.EV_SUBSTANCE_ACTIVE_LIST_BY_CLIENT,
                                substanceList,
                                request);
            SourceSubstanceMultiForm
                ssMultiForm =
                (SourceSubstanceMultiForm) form;
            ssMultiForm.setSourceId(sourceId);
            ssMultiForm.setSourceDisplayName(source.getDisplayName());
            ssMultiForm.setSourceSubstances(substanceList);
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