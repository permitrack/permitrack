package com.sehinc.environment.action.source;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvDisplayColorData;
import com.sehinc.environment.db.lookup.EnvSourceTypeData;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SourceEditPageAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceEditPageAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceEditPageAction. ";
        strLog =
            strMod
            + "sourceAction() ";
        LOG.info(strLog
                 + "in action");
        SourceForm
            sourceForm =
            (SourceForm) form;
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_SOURCE_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("source.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("source.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("source.list.page");
        }
        Integer
            sourceId;
        if (request.getParameter(RequestKeys.EV_SOURCE_ID)
            != null)
        {
            sourceId =
                new Integer(request.getParameter(RequestKeys.EV_SOURCE_ID));
            LOG.debug("sourceId="
                      + request.getParameter(RequestKeys.EV_SOURCE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                     request)
                 != null)
        {
            sourceId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                              request);
            LOG.debug("sourceId="
                      + getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("source.error.no.source.on.request"));
            addError(new ActionMessage("source.error.no.source.on.request"), request);
            return mapping.findForward("source.list.page");
        }
        EnvSource
            envSource =
            new EnvSource(sourceId);
        try
        {
            envSource.load();
            if (!envSource.getStatus()
                .getCode()
                .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("The requested Source ID = "
                                    + sourceId
                                    + " does not exist.");
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {sourceId};
            LOG.error(ApplicationResources.getProperty("source.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("source.error.load.failed",
                                       parameters), request);
            return mapping.findForward("source.list.page");
        }
        sourceForm.setId(envSource.getId());
        sourceForm.setClientId(envSource.getClientId());
        sourceForm.setPartNumber(envSource.getPartNumber());
        sourceForm.setBatchNumber(envSource.getBatchNumber());
        sourceForm.setDisplayName(envSource.getDisplayName());
        sourceForm.setDescription(envSource.getDescription());
        sourceForm.setLbsVoc(envSource.getLbsVocString());
        sourceForm.setLbsHaps(envSource.getLbsHapsString());
        sourceForm.setDensity(envSource.getDensityString());
        sourceForm.setPctSolidsWeight(envSource.getPctSolidsWeightString());
        sourceForm.setPctSolidsVolume(envSource.getPctSolidsVolumeString());
        sourceForm.setInfoOrigin(envSource.getInfoOrigin());
        sourceForm.setStatusCode(envSource.getStatus()
                                     .getCode());
        sourceForm.setSourceTypeCd(envSource.getSourceType()
                                       .getCode());
        sourceForm.setSourceTypeDesc(envSource.getSourceType()
                                         .getDescription());
        if (envSource.getDisplayColor()
            != null)
        {
            sourceForm.setDisplayColorCd(envSource.getDisplayColor()
                                             .getCode());
        }
        sourceForm.setHcFuel(envSource.getHcFuel());
        if (envSource.getLbsVocUm()
            != null)
        {
            sourceForm.setLbsVocUm(envSource.getLbsVocUm()
                                       .getCode());
            sourceForm.setLbsVocDesc(envSource.getLbsVocUm()
                                         .getDescription());
        }
        if (envSource.getDensityUm()
            != null)
        {
            sourceForm.setDensityUm(envSource.getDensityUm()
                                        .getCode());
            sourceForm.setDensityDesc(envSource.getDensityUm()
                                          .getDescription());
        }
        if (envSource.getLbsHapsUm()
            != null)
        {
            sourceForm.setLbsHapsUm(envSource.getLbsHapsUm()
                                        .getCode());
            sourceForm.setLbsHapsDesc(envSource.getLbsHapsUm()
                                          .getDescription());
        }
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
        List
            subTypes =
            EnvSourceTypeData.findAllByClientId(clientValue.getId());
        setSessionAttribute(SessionKeys.EV_SOURCE_TYPE_LIST,
                            subTypes,
                            request);
        setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                            envSource.getId(),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.SOURCE_VIEW_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.SOURCE_VIEW_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_EDIT_MENU_ITEM);
    }
}
