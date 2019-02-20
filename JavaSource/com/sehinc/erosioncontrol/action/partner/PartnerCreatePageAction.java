package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.service.user.UserService;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.client.EcClientService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PartnerCreatePageAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerCreatePageAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        PartnerForm
            partnerForm =
            (PartnerForm) form;
        partnerForm.reset();
        LOG.info("In PartnerCreatePageAction");
        request.setAttribute(RequestKeys.EC_PARTNER_FORM,
                             partnerForm);
        setSessionAttribute(SessionKeys.EC_CLIENT_RELATIONSHIP_TYPE_LIST,
                            EcClientService.getClientRelationshipTypeValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_STATE_LIST,
                            UserService.getStateValueList(),
                            request);
        return mapping.findForward("continue");
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PARTNER_CREATE_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PARTNER_LIST_MENU_NAME),
                         request);
    }
}