package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.BMPFieldType;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMTemplateCreateBMPAction
    extends MCMTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMTemplateCreateBMPAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPTemplateForm
            bmpTemplateForm =
            (BMPTemplateForm) form;
        LOG.info("In MCMTemplateCreateBMPAction");
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            mcmValue =
            getMCMTemplate(request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setMcmId(mcmValue.getId());
        bmpData.setName(bmpTemplateForm.getName());
        bmpData.setNumber(bmpTemplateForm.getNumber());
        bmpData.setSection(bmpTemplateForm.getSection());
        bmpData.setFormType(bmpTemplateForm.getFormType());
        bmpData.setRequired(bmpTemplateForm.isRequired());
        bmpData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        bmpData.setField_type1(new Integer(bmpTemplateForm.getField_type1()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type1())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value1(bmpTemplateForm.getField_value1());
        }
        bmpData.setField_type2(new Integer(bmpTemplateForm.getField_type2()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type2())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value2(bmpTemplateForm.getField_value2());
        }
        bmpData.setField_type3(new Integer(bmpTemplateForm.getField_type3()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type3())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value3(bmpTemplateForm.getField_value3());
        }
        bmpData.setField_type4(new Integer(bmpTemplateForm.getField_type4()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type4())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value4(bmpTemplateForm.getField_value4());
        }
        bmpData.setField_type5(new Integer(bmpTemplateForm.getField_type5()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type5())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value5(bmpTemplateForm.getField_value5());
        }
        bmpData.save(userValue);
        return mapping.findForward("continue");
    }
}