package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBEditAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBEditAction.class);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBForm
            bmpDbForm =
            (BMPDBForm) form;
        UserValue
            userValue =
            getUserValue(request);
        BMPDBValue
            bmpDbValue = getBMPDB(request);
        BMPDBData
            bmpDbData =
            new BMPDBData();
        bmpDbData.setId(bmpDbValue.getId());
        if (bmpDbData.load())
        {
            Integer
                bmpDbCategoryId;
            LOG.debug("bmpDbForm.getIsNewCategory()="
                      + bmpDbForm.getIsNewCategory());
            if (bmpDbForm.getIsNewCategory()
                .equals("true"))
            {
                if (bmpDbForm.getBmpDbCategoryName()
                    == null
                    || bmpDbForm.getBmpDbCategoryName()
                           .length()
                       == 0)
                {
                    addError(new ActionMessage("bmpdb.error.no.category.name"), request);
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
                LOG.debug("bmpDbForm.getBmpDbCategoryId()="
                          + bmpDbForm.getBmpDbCategoryId());
                bmpDbCategoryId =
                    bmpDbForm.getBmpDbCategoryId();
            }
            LOG.debug("bmpDbForm.getField_type1()="
                      + bmpDbForm.getField_type1());
            LOG.debug("bmpDbForm.getField_type2()="
                      + bmpDbForm.getField_type2());
            LOG.debug("bmpDbForm.getField_type3()="
                      + bmpDbForm.getField_type3());
            LOG.debug("bmpDbForm.getField_type4()="
                      + bmpDbForm.getField_type4());
            LOG.debug("bmpDbForm.getField_type5()="
                      + bmpDbForm.getField_type5());
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
            bmpDbData.setFieldType5(StringUtil.isEmpty(bmpDbForm.getField_type5())
                                        ? new Integer(0)
                                        : new Integer(bmpDbForm.getField_type5()));
            bmpDbData.setFieldValue5(bmpDbForm.getField_value5());
            bmpDbData.setRequired(bmpDbForm.isRequired());
            bmpDbData.save(userValue);
            bmpDbValue.setName(bmpDbForm.getName());
            return mapping.findForward("continue");
        }
        addError(new ActionMessage("bmpdb.error.loading.bmp"), request);
        return mapping.findForward("bmpdb.list.action");
    }
}