package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BMPViewDeleteAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPViewDeleteAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            bmpDeleteForm =
            (PlanDeleteForm) form;
        bmpDeleteForm.reset();
        LOG.info("In BMPViewDeleteAction");
        BMPValue
            bmpValue =
            getBMP(request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        bmpData.retrieveByPrimaryKeys();
        return mapping.findForward("continue");
    }
}