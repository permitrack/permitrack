package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
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

public class MCMCreateBMPAction
    extends MCMBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMCreateBMPAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        LOG.info("In MCMCreateBMPAction");
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            mcmValue = getMCMValue(request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setMcmId(mcmValue.getId());
        bmpData.setName(bmpForm.getName());
        bmpData.setNumber(bmpForm.getNumber());
        bmpData.setSection(bmpForm.getSection());
        bmpData.setOwnerId(bmpForm.getOwnerId());
        bmpData.setRequired(bmpForm.isRequired());
        bmpData.setFormType(bmpForm.getFormType());
        bmpData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        bmpData.setField_type1(new Integer(bmpForm.getField_type1()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type1())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value1(bmpForm.getField_value1());
        }
        bmpData.setField_type2(new Integer(bmpForm.getField_type2()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type2())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value2(bmpForm.getField_value2());
        }
        bmpData.setField_type3(new Integer(bmpForm.getField_type3()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type3())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value3(bmpForm.getField_value3());
        }
        bmpData.setField_type4(new Integer(bmpForm.getField_type4()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type4())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value4(bmpForm.getField_value4());
        }
        bmpData.setField_type5(new Integer(bmpForm.getField_type5()));
        if (BMPFieldType.getInputTypeById(bmpData.getField_type5())
            .equals(BMPFieldType.TEXT))
        {
            bmpData.setField_value5(bmpForm.getField_value5());
        }
        bmpData.save(userValue);
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "b"
                                 + bmpData.getId(),
                                 true);
//        return mapping.findForward("continue");
    }
}