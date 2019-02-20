package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ReportOptionsAction
    extends ReportBaseAction
{
    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        ReportForm
            reportForm =
            (ReportForm) form;
        reportForm.reset();
        reportForm.setStartDate(DateUtil.getLastMonthStartDate());
        reportForm.setEndDate(DateUtil.getLastMonthEndDate());
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.REPORT_MENU_NAME),
                         request);
    }
}