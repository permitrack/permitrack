package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PlanTemplateNewMCMAction
    extends PlanTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanTemplateNewMCMAction.class);

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        MCMTemplateForm
            mcmTemplateForm =
            (MCMTemplateForm) form;
        mcmTemplateForm.reset();
        LOG.info("In PlanTemplateNewMCMAction");
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU,
                                                 0),
                        request);
    }
}