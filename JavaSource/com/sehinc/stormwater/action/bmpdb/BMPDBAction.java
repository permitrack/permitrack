package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.util.MathUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.report.BMPReportHelper;
import com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.db.plan.BMPFieldType;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBAction.class);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBForm
            bmpDbForm =
            (BMPDBForm) form;
        bmpDbForm.reset();
        BMPDBValue
            bmpDbValue =
            getBMPDB(request);
        if (bmpDbValue
            != null)
        {
            ActionForward
                f =
                redirect(bmpDbValue.getId(),
                         "/", request);
            if (f
                != null)
            {
                return f;
            }
            BMPDBData
                bmpDbData;
            bmpDbData =
                new BMPDBData();
            bmpDbData.setId(bmpDbValue.getId());
            if (bmpDbData.load())
            {
                bmpDbForm.setId(bmpDbData.getId());
                bmpDbForm.setClientId(bmpDbData.getClientId());
                bmpDbForm.setName(bmpDbData.getName());
                bmpDbForm.setRequired(bmpDbData.isRequired());
                bmpDbForm.setFormType(bmpDbData.getFormType());
                BMPFieldType
                    type =
                    BMPFieldType.getTypeById(bmpDbData.getFieldType1());
                bmpDbForm.setField_value1(BMPReportHelper.formatBmpDbField(bmpDbData.getId(),
                                                                           bmpDbData.getFieldType1(),
                                                                           bmpDbData.getFieldValue1()));
                bmpDbForm.setField_name1(type.getLabel());
                bmpDbForm.setInput_type1(type.getInputTypeName());
                bmpDbForm.setExplanation_1(type.getExplanation());
                bmpDbForm.setField_type1(type.getId()
                                             .toString());
                type =
                    BMPFieldType.getTypeById(bmpDbData.getFieldType2());
                bmpDbForm.setField_value2(BMPReportHelper.formatBmpDbField(bmpDbData.getId(),
                                                                           bmpDbData.getFieldType2(),
                                                                           bmpDbData.getFieldValue2()));
                bmpDbForm.setField_name2(type.getLabel());
                bmpDbForm.setInput_type2(type.getInputTypeName());
                bmpDbForm.setExplanation_2(type.getExplanation());
                bmpDbForm.setField_type2(type.getId()
                                             .toString());
                type =
                    BMPFieldType.getTypeById(bmpDbData.getFieldType3());
                bmpDbForm.setField_value3(BMPReportHelper.formatBmpDbField(bmpDbData.getId(),
                                                                           bmpDbData.getFieldType3(),
                                                                           bmpDbData.getFieldValue3()));
                bmpDbForm.setField_name3(type.getLabel());
                bmpDbForm.setInput_type3(type.getInputTypeName());
                bmpDbForm.setExplanation_3(type.getExplanation());
                bmpDbForm.setField_type3(type.getId()
                                             .toString());
                type =
                    BMPFieldType.getTypeById(bmpDbData.getFieldType4());
                bmpDbForm.setField_value4(BMPReportHelper.formatBmpDbField(bmpDbData.getId(),
                                                                           bmpDbData.getFieldType4(),
                                                                           bmpDbData.getFieldValue4()));
                bmpDbForm.setField_name4(type.getLabel());
                bmpDbForm.setInput_type4(type.getInputTypeName());
                bmpDbForm.setExplanation_4(type.getExplanation());
                bmpDbForm.setField_type4(type.getId()
                                             .toString());
                type =
                    BMPFieldType.getTypeById(bmpDbData.getFieldType5());
                if (type
                    != null
                    && type.getId()
                           .intValue()
                       > 0)
                {
                    bmpDbForm.setField_type5(type.getId()
                                                 .toString());
                    bmpDbForm.setField_value5(BMPReportHelper.formatBmpDbField(bmpDbData.getId(),
                                                                               type.getId(),
                                                                               bmpDbData.getFieldValue5()));
                    bmpDbForm.setField_name5(type.getLabel());
                    bmpDbForm.setInput_type5(type.getInputTypeName());
                    bmpDbForm.setExplanation_5(type.getExplanation());
                }
                if (bmpDbData.getBmpDBCategoryId()
                    != null)
                {
                    BMPDBCategoryData
                        bmpDbCategoryData =
                        new BMPDBCategoryData();
                    bmpDbCategoryData.setId(bmpDbData.getBmpDBCategoryId());
                    if (bmpDbCategoryData.retrieveByPrimaryKeysAlternate())
                    {
                        bmpDbForm.setBmpDbCategoryId(bmpDbCategoryData.getId());
                        bmpDbForm.setBmpDbCategoryName(bmpDbCategoryData.getName());
                    }
                }
                return mapping.findForward("continue");
            }
            LOG.warn("Could not load BMPDB "
                     + bmpDbValue.getId());
        }
        addError(new ActionMessage("bmpdb.error.loading.bmp"), request);
        return mapping.findForward("bmpdb.list.action");
    }

    @Override
    protected String getSavedNode(Integer id, HttpServletRequest request)
    {
        String
            savedNode =
            null;
        Integer
            bmp =
            0;
        Cookie[]
            cookies =
            request.getCookies();
        for (Cookie cookie : cookies)
        {
            if (cookie.getName()
                    .equals("jstree_select")
                && cookie.getValue()
                   != null)
            {
                String
                    value =
                    cookie.getValue()
                        .replaceAll("%23",
                                    "");
                if (value.contains("bmpDbGoal")
                    && MathUtil.isInteger(value.substring("bmpDbGoal".length())))
                {
                    savedNode =
                        "goallibraryviewaction.do?"
                        + RequestKeys.BMP_DB_GOAL_ID
                        + "="
                        + value.substring("bmpDbGoal".length());
                }
            }
            else if (cookie.getName()
                         .equals("jstree_open")
                     && cookie.getValue()
                        != null)
            {
                String
                    value =
                    cookie.getValue()
                        .split("%2C")[0].replaceAll("%23",
                                                    "");
                if (value.contains("bmpDb")
                    && MathUtil.isInteger(value.substring("bmpDb".length())))
                {
                    bmp =
                        Integer.parseInt(value.substring("bmpDb".length()));
                }
            }
        }
        if (bmp.equals(id)
            && savedNode
               != null)
        {
            return savedNode;
        }
        return null;
    }
}