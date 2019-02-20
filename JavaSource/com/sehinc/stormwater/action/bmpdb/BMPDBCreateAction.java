package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBCreateAction
    extends BMPDBBaseAction
{
    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBForm
            bmpDbForm =
            (BMPDBForm) form;

        UserValue
            userValue =
            getUserValue(request);

        Integer
            bmpDbCategoryId;

        if (bmpDbForm.getIsNewCategory()
            .equals("true"))
        {
            if (bmpDbForm.getBmpDbCategoryName()
                == null
                || bmpDbForm.getBmpDbCategoryName()
                       .length()
                   == 0)
            {
                addError(new ActionMessage("error.no.category.name"), request);
                return mapping.findForward("continue");
            }
            else
            {
                BMPDBCategoryData
                    bmpDbCategoryData =
                    new BMPDBCategoryData();
                bmpDbCategoryData.setName(bmpDbForm.getBmpDbCategoryName());
                bmpDbCategoryId =
                    bmpDbCategoryData.insert(userValue);
            }
        }
        else
        {
            bmpDbCategoryId =
                bmpDbForm.getBmpDbCategoryId();
        }
        BMPDBData
            bmpDbData =
            new BMPDBData();
        bmpDbData.setName(bmpDbForm.getName());
        bmpDbData.setBmpDBCategoryId(bmpDbCategoryId);
        bmpDbData.setClientId(CommonConstants.SEH_CLIENT_ID);
        bmpDbData.setFieldType1(new Integer(bmpDbForm.getField_type1()));
        bmpDbData.setFieldValue1(bmpDbForm.getField_value1());
        bmpDbData.setFieldType2(new Integer(bmpDbForm.getField_type2()));
        bmpDbData.setFieldValue2(bmpDbForm.getField_value2());
        bmpDbData.setFieldType3(new Integer(bmpDbForm.getField_type3()));
        bmpDbData.setFieldValue3(bmpDbForm.getField_value3());
        bmpDbData.setFieldType4(new Integer(bmpDbForm.getField_type4()));
        bmpDbData.setFieldValue4(bmpDbForm.getField_value4());
        bmpDbData.setFieldType5(new Integer(bmpDbForm.getField_type5()));
        bmpDbData.setFieldValue5(bmpDbForm.getField_value5());
        bmpDbData.setFormType(bmpDbForm.getFormType());
        bmpDbData.setRequired(bmpDbForm.isRequired());
        bmpDbData.insert(userValue);
        setSessionAttribute(SessionKeys.BMP_DB,
                            new BMPDBValue(bmpDbData), request);

        return new ActionForward("/bmpdbviewaction.do?"
                                 + RequestKeys.BMP_DB_ID
                                 + "="
                                 + bmpDbData.getId(),
                                 true);

//        return mapping.findForward("continue");
    }
}