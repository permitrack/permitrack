package com.sehinc.environment.action.permit;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermit;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;
import com.sehinc.environment.db.permitfacility.EnvPermitFacility;
import com.sehinc.environment.db.permitsubstance.EnvPermitSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class PermitDeleteAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDeleteAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitDeleteAction. ";
        strLog =
            strMod
            + "permitAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.delete.cancel.action"), request);
            return mapping.findForward("permit.list.page");
        }
        else
        {
            PermitForm
                permitForm =
                (PermitForm) form;
            UserValue
                userValue =
                getUserValue(request);
            Integer
                permitId =
                permitForm.getId();
            List<EnvPermitDetail>
                detailList =
                EnvPermitDetail.findByPermitId(permitId);
            if (!detailList.isEmpty())
            {
                Iterator<EnvPermitDetail>
                    detailIterator =
                    detailList.iterator();
                while (detailIterator.hasNext())
                {
                    EnvPermitDetail
                        pDetail =
                        detailIterator.next();
                    List
                        paList =
                        EnvPermitAsset.findByPermitDetailId(pDetail.getId());
                    if (!paList.isEmpty())
                    {
                        Iterator<EnvPermitAsset>
                            pai =
                            paList.iterator();
                        while (pai.hasNext())
                        {
                            EnvPermitAsset
                                pAss =
                                pai.next();
                            LOG.debug("Deleting EnvPermitAsset id="
                                      + pAss.getId());
                            pAss.delete();
                        }
                    }
                    LOG.debug("Deleting EnvPermitDetail id="
                              + pDetail.getId());
                    pDetail.load();
                    pDetail.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                    pDetail.save(userValue);
                }
            }
            List
                psList =
                EnvPermitSubstance.findByPermitId(permitId);
            for (Object ps : psList)
            {
                EnvPermitSubstance
                    pSub =
                    (EnvPermitSubstance) ps;
                LOG.debug("Deleting EnvPermitSubstance id="
                          + pSub.getId());
                pSub.delete();
            }
            List
                pfList =
                EnvPermitFacility.findByPermitId(permitId);
            for (Object pf : pfList)
            {
                EnvPermitFacility
                    pFac =
                    (EnvPermitFacility) pf;
                LOG.debug("Deleting EnvPermitFacility id="
                          + pFac.getId());
                pFac.delete();
            }
            EnvPermit
                envPermit =
                new EnvPermit(permitId);
            try
            {
                envPermit.load();
                if (!envPermit.getStatus()
                    .getCode()
                    .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                {
                    throw new Exception("The requested Permit ID = "
                                        + permitId
                                        + " does not exist.");
                }
                envPermit.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envPermit.save(userValue);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {permitId};
                LOG.error(ApplicationResources.getProperty("permit.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("permit.list.page");
            }
            addMessage(new ActionMessage("permit.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}
