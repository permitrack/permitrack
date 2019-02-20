package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.client.EcClientRelationship;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PartnerDeleteAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerDeleteAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        PartnerForm
            partnerForm =
            (PartnerForm) form;
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.PARTNER_DELETE))
        {
            LOG.error(ApplicationResources.getProperty("partner.error.delete.not.authorized"));
            addError(new ActionMessage("partner.error.delete.not.authorized"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        Integer
            partnerClientId =
            null;
        if (partnerForm.getId()
            == null
            || partnerForm.getId()
                   .intValue()
               == 0)
        {
            if (request.getParameter(RequestKeys.EC_PARTNER_ID)
                != null)
            {
                partnerClientId =
                    new Integer(request.getParameter(RequestKeys.EC_PARTNER_ID));
            }
        }
        else
        {
            partnerClientId =
                partnerForm.getId();
        }
        if (partnerClientId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("partner.error.missing.partner.id"));
            addError(new ActionMessage("partner.error.missing.partner.id"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        EcClientRelationship
            clientRelationship =
            EcClientRelationship.findByClientAndRelatedClient(clientValue.getId(),
                                                              partnerClientId);
        LOG.debug("client="
                  + clientValue.getId()
                  + " partner="
                  + partnerClientId);
        if (clientRelationship
            != null)
        {
            try
            {
                LOG.debug("found clientRelationship");
                clientRelationship.delete();
                LOG.debug("Delete clientRelationship successful");
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("partner.error.delete.client.relationship"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("partner.error.delete.client.relationship"),
                         request);
                return mapping.findForward("partner.list.page");
            }
        }
        addMessage(new ActionMessage("partner.delete.success"),
                   request);
        return mapping.findForward("continue");
    }
}