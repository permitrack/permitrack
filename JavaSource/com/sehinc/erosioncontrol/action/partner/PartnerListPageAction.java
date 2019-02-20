package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
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
import java.util.List;

public class PartnerListPageAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerListPageAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        PartnerForm
            partnerForm =
            (PartnerForm) form;
        partnerForm.reset();
        LOG.info("In PartnerListPageAction");
        ClientValue
            clientValue =
            getClientValue(request);
        List
            partnerList =
            EcClientService.getSubordinatePartnerValueList(clientValue);
        request.getSession()
            .setAttribute(SessionKeys.EC_CLIENT_PARTNER_LIST,
                          partnerList);
        return mapping.findForward("continue");
    }

    @Override
    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.CLIENT_PARTNER_MENU_ITEM_NAME);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PARTNER_LIST_MENU_NAME),
                         request);
    }
}