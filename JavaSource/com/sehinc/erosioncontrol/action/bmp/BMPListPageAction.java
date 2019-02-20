package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmp.BmpService;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BMPListPageAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPListPageAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In BMPListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.BMP_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"),
                     request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View BMPs",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.BMP_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.BMP_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.BMP_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.BMP_DELETE),
                            request);
        ClientValue
            clientValue =
            getClientValue(request);
        List
            bmpList =
            BmpService.getBMPList(clientValue);
        setSessionAttribute(SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT,
                            bmpList,
                            request);
        setSessionAttribute(SessionKeys.EC_BMP,
                            null,
                            request);
        return mapping.findForward("continue");
    }

}