package com.sehinc.stormwater.action.template;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPTemplateAction
    extends BMPTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPTemplateAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        BMPTemplateForm
            bmpTemplateForm =
            (BMPTemplateForm) form;
        bmpTemplateForm.reset();
        LOG.info("In BMPTemplateAction");
        Integer
            bmpId =
            null;
        if (request.getParameter(RequestKeys.BMP_ID)
            != null)
        {
            bmpId =
                new Integer(request.getParameter(RequestKeys.BMP_ID));
        }
        else
        {
            BMPValue
                bmpValue = getBMPTemplate(request);
            if (bmpValue
                != null)
            {
                bmpId =
                    bmpValue.getId();
            }
        }
        if (bmpId
            == null)
        {
            addError(new ActionMessage("bmp.error.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        BMPData
            bmpData =
            PlanService.getBMP(bmpId);
        if (bmpData
            != null)
        {
            MCMData
                mcmData =
                PlanService.getMCM((bmpData.getMcmId()));
            if (mcmData
                != null)
            {
                bmpTemplateForm.setMcmNumber(mcmData.getNumber());
            }
            PlanData
                planData =
                getPlanTemplateData(request);
            BMPFormatter
                formatter =
                planData.getPermitType()
                    .getBMPFormatter();
            LOG.debug("setting Identifier to "
                      + formatter.formatBMPLongIdentifier(planData,
                                                          mcmData,
                                                          bmpData));
            bmpTemplateForm.setIdentifier(formatter.formatBMPLongIdentifier(planData,
                                                                            mcmData,
                                                                            bmpData));
            bmpTemplateForm.setId(bmpData.getId());
            bmpTemplateForm.setMCMId(bmpData.getMcmId());
            bmpTemplateForm.setNameLocked(bmpData.isNameLocked());
            bmpTemplateForm.setName(bmpData.getName());
            bmpTemplateForm.setNumber(bmpData.getNumber());
            bmpTemplateForm.setSection(bmpData.getSection());
            bmpTemplateForm.setRequired(bmpData.isRequired());
            if(bmpData.getFormType() != null)
                bmpTemplateForm.setUseSection(formatter.useSections() && !bmpData.getFormType().equals(0));
            else
                bmpTemplateForm.setUseSection(false);
            BMPFieldType
                type =
                BMPFieldType.getTypeById(bmpData.getField_type1());
            bmpTemplateForm.setField_value1(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type1(),
                                                                        bmpData.getField_value1(),
                                                                        bmpTemplateForm.getMcmNumber()
                                                                            .toString(),
                                                                        BMPReportHelper.TEXT_HTML));
            bmpTemplateForm.setField_name1(type.getLabel());
            bmpTemplateForm.setInput_type1(type.getInputTypeName());
            bmpTemplateForm.setExplanation_1(type.getExplanation());
            type =
                BMPFieldType.getTypeById(bmpData.getField_type2());
            bmpTemplateForm.setField_value2(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type2(),
                                                                        bmpData.getField_value2(),
                                                                        bmpTemplateForm.getMcmNumber()
                                                                            .toString(),
                                                                        BMPReportHelper.TEXT_HTML));
            bmpTemplateForm.setField_name2(type.getLabel());
            bmpTemplateForm.setInput_type2(type.getInputTypeName());
            bmpTemplateForm.setExplanation_2(type.getExplanation());
            type =
                BMPFieldType.getTypeById(bmpData.getField_type3());
            bmpTemplateForm.setField_value3(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type3(),
                                                                        bmpData.getField_value3(),
                                                                        bmpTemplateForm.getMcmNumber()
                                                                            .toString(),
                                                                        BMPReportHelper.TEXT_HTML));
            bmpTemplateForm.setField_name3(type.getLabel());
            bmpTemplateForm.setInput_type3(type.getInputTypeName());
            bmpTemplateForm.setExplanation_3(type.getExplanation());
            type =
                BMPFieldType.getTypeById(bmpData.getField_type4());
            bmpTemplateForm.setField_value4(BMPReportHelper.formatField(bmpData.getId(),
                                                                        bmpData.getField_type4(),
                                                                        bmpData.getField_value4(),
                                                                        bmpTemplateForm.getMcmNumber()
                                                                            .toString(),
                                                                        BMPReportHelper.TEXT_HTML));
            bmpTemplateForm.setField_name4(type.getLabel());
            bmpTemplateForm.setInput_type4(type.getInputTypeName());
            bmpTemplateForm.setExplanation_4(type.getExplanation());
            type =
                BMPFieldType.getTypeById(bmpData.getField_type5());
            if (bmpData.getField_type5()
                    .intValue()
                > 0)
            {
                bmpTemplateForm.setField_type5(bmpData.getField_type5()
                                                   .toString());
                bmpTemplateForm.setField_value5(BMPReportHelper.formatField(bmpData.getId(),
                                                                            bmpData.getField_type5(),
                                                                            bmpData.getField_value5(),
                                                                            bmpTemplateForm.getMcmNumber()
                                                                                .toString(),
                                                                            BMPReportHelper.TEXT_HTML));
                bmpTemplateForm.setField_name5(type.getLabel());
                bmpTemplateForm.setInput_type5(type.getInputTypeName());
                bmpTemplateForm.setExplanation_5(type.getExplanation());
            }
        }
        else
        {
            addError(new ActionMessage("bmp.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        setPlanTemplateSessionKeys(SessionKeys.BMP_TEMPLATE,
                                   bmpData.getId(), request);
        return mapping.findForward("continue");
    }
}