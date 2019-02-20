package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.client.EcClientService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class PartnerAddPageAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerAddPageAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        PartnerAddForm
            partnerAddForm =
            (PartnerAddForm) form;
        LOG.info("In PartnerAddPageAction");
        Integer
            partnerId;
        LOG.debug("ecPartnerId="
                  + request.getParameter(RequestKeys.EC_PARTNER_ID));
        if (request.getParameter(RequestKeys.EC_PARTNER_ID)
            != null)
        {
            LOG.debug("ecPartnerId="
                      + request.getParameter(RequestKeys.EC_PARTNER_ID));
            partnerId =
                new Integer(request.getParameter(RequestKeys.EC_PARTNER_ID));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("partner.error.no.partner.on.request"));
            addError(new ActionMessage("partner.error.no.partner.on.request"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        /*
                UserValue
                    userValue =
                    getUserValue(request);
                ClientValue
                    clientValue =
                    getClientValue(request);
        */
        ClientData
            partnerClientData =
            ClientService.getActiveClientById(partnerId);
        if (partnerClientData
            == null)
        {
            Object[]
                parameters =
                {partnerId};
            LOG.error(ApplicationResources.getProperty("partner.error.could.not.load.partner",
                                                       parameters));
            addError(new ActionMessage("partner.error.could.not.load.partner",
                                       parameters),
                     request);
            return mapping.findForward("partner.list.page");
        }
        partnerAddForm.setClientInfo(partnerClientData);
        List
            partnerUserList;
        try
        {
            partnerUserList =
                UserService.findUserValuesByClient(new ClientValue(partnerClientData));
            setSessionAttribute(RequestKeys.EC_PARTNER_USER_LIST,
                                partnerUserList,
                                request);
            LOG.debug("partnerUserList\n"
                      + partnerUserList.toString());
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {
                    partnerClientData.getName(),
                    partnerClientData.getId()};
            LOG.error(ApplicationResources.getProperty("partner.error.could.not.load.users",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.could.not.load.users",
                                       parameters),
                     request);
            return mapping.findForward("partner.list.page");
        }
        setSessionAttribute(SessionKeys.EC_CLIENT_RELATIONSHIP_TYPE_LIST,
                            EcClientService.getClientRelationshipTypeValueList(),
                            request);
        return mapping.findForward("continue");
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PARTNER_EDIT_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PARTNER_EDIT_MENU_NAME),
                         request);
    }
}