package com.sehinc.environment.action.source;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.db.sourcescc.EnvSourceSccInfo;
import com.sehinc.environment.db.sourcesubstance.EnvSourceSubstance;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SourceSccInfoValue;
import com.sehinc.environment.value.SourceSubstanceValue;
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

public class SourceViewPageAction
    extends SourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceViewPageAction.class);

    public ActionForward sourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In SourceViewPageAction");
        ClientValue
            clientValue =
            getClientValue(request);
        SourceForm
            sourceForm =
            (SourceForm) form;
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View sources",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        Integer
            sourceId;
        if (request.getParameter(RequestKeys.EV_SOURCE_ID)
            != null)
        {
            sourceId =
                new Integer(request.getParameter(RequestKeys.EV_SOURCE_ID));
            LOG.debug("evSourceId="
                      + request.getParameter(RequestKeys.EV_SOURCE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                     request)
                 != null)
        {
            sourceId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_ID,
                                              request);
            LOG.debug("Got sourceId="
                      + sourceId
                      + " from the session.");
            LOG.debug("evSourceId="
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
        ClientData
            client =
            new ClientData(envSource.getClientId());
        try
        {
            client.load();
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("source.error.client.load.failed"));
            addError(new ActionMessage("source.error.client.load.failed"), request);
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
        setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                            envSource.getId(),
                            request);
        ArrayList<SourceSubstanceValue>
            srcSubstanceTypeList =
            new ArrayList<SourceSubstanceValue>();
        List
            ssList =
            EnvSourceSubstance.findBySourceId(envSource.getId());
        Integer
            substanceId;
        for (Object sourceSubstance : ssList)
        {
            EnvSourceSubstance
                ss =
                (EnvSourceSubstance) sourceSubstance;
            try
            {
                SourceSubstanceValue
                    ssVal =
                    new SourceSubstanceValue();
                substanceId =
                    ss.getSubstanceId();
                ssVal.setSourceSubstanceId(ss.getId());
                ssVal.setSourceId(ss.getSourceId());
                ssVal.setSubstanceId(ss.getSubstanceId());
                ssVal.setRatio(ss.getRatioString());
                ssVal.setNatGasEmFactor(ss.getNatGasEmFactorString());
                if (ss.getNatGasEfUnit()
                    != null)
                {
                    ssVal.setNatGasEfUnit(ss.getNatGasEfUnit()
                                              .getCode());
                    ssVal.setNatGasEfUnitDesc(ss.getNatGasEfUnit()
                                                  .getDescription());
                }
                ssVal.setBulkLiqEmFactor(ss.getBulkLiqEmFactorString());
                if (ss.getBulkLiqEfUnit()
                    != null)
                {
                    ssVal.setBulkLiqEfUnit(ss.getBulkLiqEfUnit()
                                               .getCode());
                    ssVal.setBulkLiqEfUnitDesc(ss.getBulkLiqEfUnit()
                                                   .getDescription());
                }
                EnvSubstance
                    substance =
                    new EnvSubstance(ss.getSubstanceId());
                if (substance.load())
                {
                    ssVal.setSubstanceName(substance.getName());
                    ssVal.setSubstancePartNumber(substance.getPartNum());
                }
                EnvSubstance
                    substanceVal =
                    EnvSubstance.findByClientIdAndSubstanceId(clientValue.getId(),
                                                              substanceId);
                ssVal.setSourceTypeCode(envSource.getSourceType()
                                            .getCode());
                ssVal.setSubstanceType(substanceVal.getSubstanceType()
                                           .getDescription());
                srcSubstanceTypeList.add(ssVal);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("source.substance.error.load.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.substance.error.load.failed"), request);
            }
        }
        ArrayList<SourceSccInfoValue>
            sourceSccList =
            new ArrayList<SourceSccInfoValue>();
        List
            sccList =
            EnvSourceSccInfo.findBySourceId(sourceId);
        for (Object o : sccList)
        {
            EnvSourceSccInfo
                sscc =
                (EnvSourceSccInfo) o;
            try
            {
                SourceSccInfoValue
                    asv =
                    new SourceSccInfoValue();
                EnvSccInfo
                    esi =
                    new EnvSccInfo(sscc.getSccInfoId());
                esi.load();
                asv.setSourceSccInfoId(sscc.getId());
                asv.setSccNumber(esi.getNumber());
                String
                    grp =
                    esi.getMajIndustrialGroup();
                if (grp
                    != null)
                {
                    grp =
                        " "
                        + grp;
                }
                else
                {
                    grp =
                        "";
                }
                String
                    raw =
                    esi.getRawMaterial();
                if (raw
                    != null)
                {
                    raw =
                        " "
                        + raw;
                }
                else
                {
                    raw =
                        "";
                }
                String
                    proc =
                    esi.getEmittingProcess();
                if (proc
                    != null)
                {
                    proc =
                        " "
                        + proc;
                }
                else
                {
                    proc =
                        "";
                }
                asv.setSccDescription(esi.getDescription()
                                      + grp
                                      + raw
                                      + proc);
                sourceSccList.add(asv);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("source.scc.error.load.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.scc.error.load.failed"), request);
            }
        }
        setSessionAttribute(SessionKeys.EV_SOURCE_SCC_ACTIVE_LIST,
                            sourceSccList,
                            request);
        setSessionAttribute(SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST,
                            srcSubstanceTypeList,
                            request);
        setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                            null,
                            request);
        setSessionAttribute(SessionKeys.SOURCE_SUBSTANCE_CAN_CREATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_SUBSTANCE_CREATE),
                            request);
        setSessionAttribute(SessionKeys.SOURCE_SUBSTANCE_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_SUBSTANCE_DELETE),
                            request);
        setSessionAttribute(SessionKeys.SOURCE_SCC_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_SCC_DELETE),
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
        getTertiaryMenu(request).setCurrentItem(null);
    }
}