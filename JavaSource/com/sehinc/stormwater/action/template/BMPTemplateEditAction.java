package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.BMPFieldType;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPTemplateEditAction
    extends BMPTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPTemplateEditAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        BMPTemplateForm
            bmpTemplateForm =
            (BMPTemplateForm) form;
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue = getBMPTemplate(request);
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            == null)
        {
            LOG.error("In bmpAction(): Invalid or missing plan template in session");
            throw new ServletException("Invalid or missing plan template in session");
        }
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        bmpData.load();
        bmpData.setIdentifier(planValue.getPermitType()
                                  .getBMPFormatter()
                                  .formatBMPSubIdentifier(bmpData));
        bmpData.setName(bmpTemplateForm.getName());
        bmpData.setNumber(bmpTemplateForm.getNumber());
        bmpData.setSection(bmpTemplateForm.getSection());
        bmpData.setRequired(bmpTemplateForm.isRequired());
        if (BMPFieldType.getInputTypeById(bmpData.getField_type1())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value1(bmpTemplateForm.getField_value1());
        }
        else
        {
            bmpData.setField_value1(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type2())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value2(bmpTemplateForm.getField_value2());
        }
        else
        {
            bmpData.setField_value2(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type3())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value3(bmpTemplateForm.getField_value3());
        }
        else
        {
            bmpData.setField_value3(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type4())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value4(bmpTemplateForm.getField_value4());
        }
        else
        {
            bmpData.setField_value4(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type5())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value5(bmpTemplateForm.getField_value5());
        }
        else
        {
            bmpData.setField_value5(null);
        }
        bmpData.save(userValue);
        bmpData.load();
        setSessionAttribute(SessionKeys.BMP_TEMPLATE,
                            new BMPValue(bmpData), request);
        return mapping.findForward("continue");
    }
}