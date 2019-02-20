package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmp.BmpService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BMPCreateAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPCreateAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.BMP_CREATE))
        {
            Object[]
                parameters =
                {
                    SessionService.getUserValue(request)
                        .getUsername(),
                    "create"};
            LOG.info(ApplicationResources.getProperty("bmp.permission.denied",
                                                      parameters));
            addError(new ActionMessage("bmp.permission.denied",
                                       parameters),
                     request);
            return mapping.findForward("BMP.list.page");
        }
        List
            bmpCatList =
            BmpService.getBMPCategoryList(clientValue);
        request.getSession()
            .setAttribute(SessionKeys.EC_BMP_CATEGORY_LIST_BY_CLIENT,
                          bmpCatList);
        return mapping.findForward("continue");
    }
    /*
        public void finalizeAction(HttpServletRequest request)
            throws Exception
        {
            super.finalizeAction(request);
            setPrimaryMenuItem(request);
            setSecondaryMenu(request);
            setSecondaryMenuItem(request);
        }
    */

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.BMP_CREATE_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.BMP_LIST_MENU_NAME),
                         request);
    }
}
