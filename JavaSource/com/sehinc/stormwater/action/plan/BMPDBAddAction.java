package com.sehinc.stormwater.action.plan;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;

public class BMPDBAddAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBAddAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        DynaActionForm
            aForm =
            (DynaActionForm) form;
        if (mapping.getPath()
            .contains("bmpdbaddaction"))
        {
            form.reset(mapping,
                       request);
            return mapping.findForward("continue");
        }
        Integer
            aCategory =
            (Integer) aForm.get("bmpDbCategoryId");
        String
            aName =
            (String) aForm.get("name");
        if (aCategory
            == null)
        {
            addError(new ActionMessage("bmp.error.missing.bmpdb.categoryId"), request);
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue = getBMP(request);
        if (bmpValue
            == null)
        {
            addError(new ActionMessage("bmp.edit.error.NoBMPInSession"), request);
            //return mapping.findForward("mcm.view.action");
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        if (!bmpData.load())
        {
            addError(new ActionMessage("bmp.error.loading.bmp"), request);
//            return mapping.findForward("mcm.view.action");
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        Integer
            bmpDbCategoryId;
        if (aCategory.intValue()
            == 0)
        {
            if (StringUtil.isEmpty(aName))
            {
                addError(new ActionMessage("bmp.error.bmpdb.category.name.required"), request);
                return new ActionForward("/subnodeviewaction.do?"
                                         + UrlUtil.subNodeString
                                         + "="
                                         + "m"
                                         + getMCMValue(request).getId(),
                                         true);
            }
            BMPDBCategoryData
                bmpDbCategoryData =
                new BMPDBCategoryData();
            bmpDbCategoryData.setName(aName);
            bmpDbCategoryId =
                bmpDbCategoryData.insert(userValue);
        }
        else
        {
            bmpDbCategoryId =
                aCategory;
        }
        BMPDBData
            bmpDbData =
            new BMPDBData();
        bmpDbData.setName(bmpData.getName());
        bmpDbData.setBmpDBCategoryId(bmpDbCategoryId);
        bmpDbData.setClientId(CommonConstants.SEH_CLIENT_ID);
        bmpDbData.setFieldType1(bmpData.getField_type1());
        bmpDbData.setFieldValue1(bmpData.getField_value1());
        bmpDbData.setFieldType2(bmpData.getField_type2());
        bmpDbData.setFieldValue2(bmpData.getField_value2());
        bmpDbData.setFieldType3(bmpData.getField_type3());
        bmpDbData.setFieldValue3(bmpData.getField_value3());
        bmpDbData.setFieldType4(bmpData.getField_type4());
        bmpDbData.setFieldValue4(bmpData.getField_value4());
        bmpDbData.setFieldType5(bmpData.getField_type5());
        bmpDbData.setFieldValue5(bmpData.getField_value5());
        bmpDbData.setFormType(bmpData.getFormType());
        bmpDbData.setRequired(bmpData.isRequired());
        try
        {
            Integer
                bmpDbId =
                bmpDbData.insert(userValue);
            ArrayList
                aBMPGoalList =
                (ArrayList) PlanService.getGoalValues(bmpValue);
            Iterator
                iter =
                aBMPGoalList.iterator();
            while (iter.hasNext())
            {
                GoalValue
                    aGoalValue =
                    (GoalValue) iter.next();
                GoalData
                    aGoalData =
                    new GoalData();
                aGoalData.setId(aGoalValue.getId());
                if (aGoalData.load())
                {
                    BMPDBGoalData
                        aBmpDbGoalData =
                        new BMPDBGoalData();
                    aBmpDbGoalData.setBmpDBId(bmpDbId);
                    aBmpDbGoalData.setDescription(aGoalData.getDescription());
                    aBmpDbGoalData.setGoalActivityFormId(aGoalData.getGoalActivityFormId());
                    aBmpDbGoalData.setGoalActivityFrequencyId(aGoalData.getGoalActivityFrequencyId());
                    aBmpDbGoalData.setName(aGoalData.getName());
                    aBmpDbGoalData.setNumber(aGoalData.getNumber());
                    aBmpDbGoalData.insert(userValue);
                }
            }
        }
        catch (HibernateException he)
        {
            LOG.error("In bmpAction(): "
                      + he.getMessage());
            addError(new ActionMessage("bmp.error.bmpdb.insert.failed"), request);
//            return mapping.findForward("bmp.view.action");
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        setSessionAttribute(SessionKeys.BMP_DB,
                            new BMPDBValue(bmpDbData), request);
        return mapping.findForward("continue");
    }
}
