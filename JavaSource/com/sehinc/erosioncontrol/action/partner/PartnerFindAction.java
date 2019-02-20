package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.client.EcClientService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartnerFindAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerFindAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        PartnerForm
            partnerForm =
            (PartnerForm) form;
        if (isCancelled(request))
        {
            partnerForm.reset();
            return (mapping.findForward("partner.list.page"));
        }
        try
        {
            setSessionAttribute(SessionKeys.EC_FIND_RESULTS_PARTNER_LIST,
                                EcClientService.findPartners(getClientValue(request),
                                                             partnerForm.getName(),
                                                             partnerForm.getAddressLine1(),
                                                             partnerForm.getCity(),
                                                             partnerForm.getState(),
                                                             partnerForm.getPostalCode()),
                                request);
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("partner.error.partner.search.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.partner.search.failed"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        return mapping.findForward("continue");
    }
}