package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplate;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplateBmp;
import com.sehinc.erosioncontrol.value.inspection.InspectionTemplateBmpValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Hashtable;

public class InspectionTemplateCreateAction
    extends InspectionTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateCreateAction.class);

    public ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        InspectionTemplateForm
            inspectionTemplateForm =
            (InspectionTemplateForm) form;
        LOG.info("In InspectionTemplateCreateAction");
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            inspectionTemplateForm.reset();
            return (mapping.findForward("inspection.template.list.page"));
        }
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        EcInspectionTemplate
            inspectionTemplate =
            new EcInspectionTemplate();
        inspectionTemplate.setClientId(clientValue.getId());
        inspectionTemplate.setName(inspectionTemplateForm.getName());
        inspectionTemplate.setDescription(inspectionTemplateForm.getDescription());
        inspectionTemplate.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        inspectionTemplate.insert(userValue);
        inspectionTemplate.load();
        Hashtable
            inspectionTemplateBmpList =
            inspectionTemplateForm.getBmps()
                .getAllBmps();
        int
            inspectionTemplateBmpListIndex =
            0;
        while (inspectionTemplateBmpList.containsKey(new Integer(inspectionTemplateBmpListIndex)))
        {
            InspectionTemplateBmpValue
                inspectionTemplateBmpValue =
                (InspectionTemplateBmpValue) inspectionTemplateBmpList.get(new Integer(inspectionTemplateBmpListIndex));
            LOG.debug(inspectionTemplateBmpValue.toString());
            // Skip this bmp if the required fields were not entered
            if (inspectionTemplateBmpValue.getCategoryId()
                == null
                || inspectionTemplateBmpValue.getCategoryId()
                       .intValue()
                   == 0
                || inspectionTemplateBmpValue.getBmpId()
                   == null
                || inspectionTemplateBmpValue.getBmpId()
                       .intValue()
                   == 0)
            {
                inspectionTemplateBmpListIndex++;
                continue;
            }
            EcInspectionTemplateBmp
                inspectionTemplateBmp =
                new EcInspectionTemplateBmp();
            inspectionTemplateBmp.setInspectionTemplateId(inspectionTemplate.getId());
            inspectionTemplateBmp.setDescription(inspectionTemplateBmpValue.getBmpDescription());
            inspectionTemplateBmp.setBmp((EcBmp) HibernateUtil.load(EcBmp.class,
                                                                    inspectionTemplateBmpValue.getBmpId()));
            inspectionTemplateBmp.setIsRequired(new Boolean(inspectionTemplateBmpValue.getIsRequired()));
            inspectionTemplateBmp.insert();
            inspectionTemplateBmpListIndex++;
        }
        return (mapping.findForward("continue"));
    }
}