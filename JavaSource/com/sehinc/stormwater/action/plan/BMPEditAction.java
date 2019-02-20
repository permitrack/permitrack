package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.BMPFieldType;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPEditAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPEditAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue =
            getBMP(request);
        if (bmpValue
            == null)
        {
            LOG.error("In bmpAction(): BMP not found in session");
//            addError(new ActionMessage("bmp.edit.error.NoBMPInSession"));
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
/*
        PlanData
            planData =
            getPlanData();
        if (planData
            == null)
        {
            LOG.error("In bmpAction(): Invalid or missing plan in session");
            throw new ServletException("Invalid or missing plan in session");
        }
*/
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        bmpData.retrieveByPrimaryKeys();
        bmpData.setSection(bmpForm.getSection());
        bmpData.setName(bmpForm.getName());
        bmpData.setNumber(bmpForm.getNumber());
        bmpData.setIdentifier(getPermitType(request)  //planData.getPermitType()
                                  .getBMPFormatter()
                                  .formatBMPSubIdentifier(bmpData));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type1())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value1(bmpForm.getField_value1());
        }
        else
        {
            bmpData.setField_value1(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type2())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value2(bmpForm.getField_value2());
        }
        else
        {
            bmpData.setField_value2(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type3())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value3(bmpForm.getField_value3());
        }
        else
        {
            bmpData.setField_value3(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type4())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value4(bmpForm.getField_value4());
        }
        else
        {
            bmpData.setField_value4(null);
        }
        if (BMPFieldType.getInputTypeById(bmpData.getField_type5())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value5(bmpForm.getField_value5());
        }
        else
        {
            bmpData.setField_value5(null);
        }
        bmpData.setOwnerId(bmpForm.getOwnerId());
        bmpData.setRequired(bmpForm.isRequired());
        bmpData.save(userValue);
        bmpValue.setName(bmpData.getName());
        bmpValue.setNumber(bmpData.getNumber());
        bmpValue.setSection(bmpData.getSection());

        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "b"
                                 + bmpData.getId(),
                                 true);

//        return mapping.findForward("continue");
    }
}