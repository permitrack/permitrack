package com.sehinc.stormwater.action.plan;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.report.BMPReportHelper;
import com.sehinc.stormwater.db.plan.*;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        bmpForm.reset();
        Integer
            bmpId =
            0;
        if (request.getParameter(RequestKeys.BMP_ID)
            != null)
        {
            bmpId =
                new Integer(request.getParameter(RequestKeys.BMP_ID));
        }
        else
        {
            BMPValue
                bmpValue =
                getBMP(request);
            if (bmpValue
                != null)
            {
                bmpId =
                    bmpValue.getId();
            }
        }
        if (bmpId
            > 0)
        {
            BMPData
                bmpData;
            bmpData =
                new BMPData();
            bmpData.setId(bmpId);
            if (bmpData.load())
            {
                if (setPlanSessionKeys(SessionKeys.BMP,
                                       bmpId, request))
                {
                    UserData
                        userData =
                        new UserData();
                    userData.setId(bmpData.getOwnerId());
                    if (bmpData.getOwnerId()
                        != null
                        && bmpData.getOwnerId()
                               .intValue()
                           > 0
                        && userData.retrieveByPrimaryKeysAlternate())
                    {
                        bmpForm.setOwner(userData.getName());
                        bmpForm.setOwnerId(bmpData.getOwnerId());
                    }
                    MCMData
                        mcmData =
                        PlanService.getMCM((bmpData.getMcmId()));
                    if (mcmData
                        != null)
                    {
                        bmpForm.setMcmNumber(mcmData.getNumber());
                    }
                    PlanData
                        planData =
                        getPlanData(request);
                    BMPFormatter
                        formatter =
                        planData.getPermitType()
                            .getBMPFormatter();
                    LOG.debug("setting Identifier to "
                              + formatter.formatBMPLongIdentifier(planData,
                                                                  mcmData,
                                                                  bmpData));
                    bmpForm.setIdentifier(formatter.formatBMPLongIdentifier(planData,
                                                                            mcmData,
                                                                            bmpData));
                    bmpForm.setId(bmpData.getId());
                    bmpForm.setMCMId(bmpData.getMcmId());
                    bmpForm.setNameLocked(bmpData.isNameLocked());
                    bmpForm.setName(bmpData.getName());
                    bmpForm.setNumber(bmpData.getNumber());
                    bmpForm.setSection(bmpData.getSection());
                    bmpForm.setRequired(bmpData.isRequired());
                    if(bmpData.getFormType() != null)
                        bmpForm.setUseSection(formatter.useSections() && !bmpData.getFormType().equals(0));
                    else
                        bmpForm.setUseSection(false);
                    BMPFieldType
                        type =
                        BMPFieldType.getTypeById(bmpData.getField_type1());
                    bmpForm.setField_value1(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type1(),
                                                                        bmpData.getField_value1(),
                                                                        bmpForm.getMcmNumber()
                                                                            .toString(),
                                                                        /*
                                                                                                                                                mcmData.getNumber()
                                                                                                                                                    .toString(),
                                                                        */
                                                                        BMPReportHelper.TEXT_HTML));
                    bmpForm.setField_name1(type.getLabel());
                    bmpForm.setInput_type1(type.getInputTypeName());
                    bmpForm.setExplanation_1(type.getExplanation());
                    type =
                        BMPFieldType.getTypeById(bmpData.getField_type2());
                    bmpForm.setField_value2(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type2(),
                                                                        bmpData.getField_value2(),
                                                                        bmpForm.getMcmNumber()
                                                                            .toString(),
                                                                        /*
                                                                                                                                                mcmData.getNumber()
                                                                                                                                                    .toString(),
                                                                        */
                                                                        BMPReportHelper.TEXT_HTML));
                    bmpForm.setField_name2(type.getLabel());
                    bmpForm.setInput_type2(type.getInputTypeName());
                    bmpForm.setExplanation_2(type.getExplanation());
                    type =
                        BMPFieldType.getTypeById(bmpData.getField_type3());
                    bmpForm.setField_value3(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type3(),
                                                                        bmpData.getField_value3(),
                                                                        bmpForm.getMcmNumber()
                                                                            .toString(),
                                                                        /*
                                                                                                                                                mcmData.getNumber()
                                                                                                                                                    .toString(),
                                                                        */
                                                                        BMPReportHelper.TEXT_HTML));
                    bmpForm.setField_name3(type.getLabel());
                    bmpForm.setInput_type3(type.getInputTypeName());
                    bmpForm.setExplanation_3(type.getExplanation());
                    type =
                        BMPFieldType.getTypeById(bmpData.getField_type4());
                    bmpForm.setField_value4(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type4(),
                                                                        bmpData.getField_value4(),
                                                                        bmpForm.getMcmNumber()
                                                                            .toString(),
                                                                        /*
                                                                                                                                                mcmData.getNumber()
                                                                                                                                                    .toString(),
                                                                        */
                                                                        BMPReportHelper.TEXT_HTML));
                    bmpForm.setField_name4(type.getLabel());
                    bmpForm.setInput_type4(type.getInputTypeName());
                    bmpForm.setExplanation_4(type.getExplanation());
                    type =
                        BMPFieldType.getTypeById(bmpData.getField_type5());
                    if (bmpData.getField_type5()
                        != null)
                    {
                        if (bmpData.getField_type5()
                                .intValue()
                            > 0)
                        {
                            bmpForm.setField_type5(bmpData.getField_type5()
                                                       .toString());
                            bmpForm.setField_value5(BMPReportHelper.formatField(bmpData.getId(),
                                                                                bmpData.getField_type5(),
                                                                                bmpData.getField_value5(),
                                                                                bmpForm.getMcmNumber()
                                                                                    .toString(),
                                                                                /*
                                                                                                                                                                mcmData.getNumber()
                                                                                                                                                                    .toString(),
                                                                                */
                                                                                BMPReportHelper.TEXT_HTML));
                            bmpForm.setField_name5(type.getLabel());
                            bmpForm.setInput_type5(type.getInputTypeName());
                            bmpForm.setExplanation_5(type.getExplanation());
                        }
                    }
                    bmpForm.setDisplayButtonAddBMPtoDB(planData.getPermitType()
                                                           .getAddBMPtoDB());
                    return mapping.findForward("continue");
                }
            }
        }
        addError(new ActionMessage("bmp.error.load.failed",
                                   bmpId), request);
        if(getMCMValue(request) == null)
        {
            return mapping.findForward("plan.list.action");
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "m"
                                 + getMCMValue(request).getId(),
                                 true);
    }
}
