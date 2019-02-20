package com.sehinc.environment.action.permitdetail;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;
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

public class PermitDetailDeleteAction
    extends PermitDetailBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitDetailDeleteAction.class);

    public ActionForward permitDetailAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitDetailDeleteAction. ";
        strLog =
            strMod
            + "permitDetailAction() ";
        LOG.info(strLog
                 + "in action");
        PermitDetailForm
            permitDetail =
            (PermitDetailForm) form;
        UserValue
            objUser =
            getUserValue(request);
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.detail.delete.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            Integer
                permitDetailId;
            LOG.debug("permitDetailId="
                      + permitDetail.getId());
            if (permitDetail.getId()
                != null)
            {
                permitDetailId =
                    permitDetail.getId();
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("permit.detail.error.no.permit.on.request"));
                addError(new ActionMessage("permit.detail.error.no.permit.on.request"), request);
                return mapping.findForward("permit.view.page");
            }
            EnvPermitDetail
                envPermitDetail =
                new EnvPermitDetail(permitDetailId);
            try
            {
                List
                    paList =
                    EnvPermitAsset.findByPermitDetailId(permitDetailId);
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
                LOG.debug("Deleting EnvPermitDetail"
                          + envPermitDetail.getId());
                envPermitDetail.load();
                envPermitDetail.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envPermitDetail.save(objUser);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {permitDetailId};
                LOG.error(ApplicationResources.getProperty("permit.detail.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.detail.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("permit.view.page");
            }
            addMessage(new ActionMessage("permit.detail.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}
