package com.sehinc.environment.action.admin;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.environment.action.base.SessionKeys;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminAction
    extends AdminBaseAction
{
    public ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        AdminForm
            adminForm =
            (AdminForm) form;
        adminForm.reset();
        setSessionAttribute(SessionKeys.ADMIN_CLIENT_SELECT_LIST,
                            ClientService.getActiveClientsByModuleAndType(CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE),
                                                                          CommonConstants.PRIMARY_CLIENT),
                            request);
        return mapping.findForward("continue");
    }
}