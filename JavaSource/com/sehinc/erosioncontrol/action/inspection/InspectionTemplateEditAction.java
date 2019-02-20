package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplate;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplateBmp;
import com.sehinc.erosioncontrol.value.inspection.InspectionTemplateBmpValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Hashtable;

public class InspectionTemplateEditAction
    extends InspectionTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateEditAction.class);

    public ActionForward inspectionTemplateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        InspectionTemplateForm
            inspectionTemplateForm =
            (InspectionTemplateForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            inspectionTemplateForm.reset();
            return (mapping.findForward("inspection.template.list.page"));
        }
        UserValue
            userValue =
            getUserValue(request);
        EcInspectionTemplate
            inspectionTemplate =
            new EcInspectionTemplate();
        if (inspectionTemplateForm.getId()
            != null
            && inspectionTemplateForm.getId()
                   .intValue()
               > 0)
        {
            inspectionTemplate.setId(inspectionTemplateForm.getId());
            inspectionTemplate.load();
        }
        else
        {
            addError(new ActionMessage("inspection.template.edit.failed"),
                     request);
            return mapping.findForward("inspection.template.list.page");
        }
        inspectionTemplate.setName(inspectionTemplateForm.getName());
        inspectionTemplate.setDescription(inspectionTemplateForm.getDescription());
        if (inspectionTemplate.getId()
            == null)
        {
            inspectionTemplate.insert(userValue);
        }
        else
        {
            inspectionTemplate.update(userValue);
        }
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
            if (inspectionTemplateBmpValue.getIsDeleted())
            {
                if (inspectionTemplateBmpValue.getId()
                    != null
                    || inspectionTemplateBmpValue.getId()
                           .intValue()
                       > 0)
                {
                    EcInspectionTemplateBmp
                        inspectionTemplateBmp =
                        new EcInspectionTemplateBmp();
                    inspectionTemplateBmp.setId(inspectionTemplateBmpValue.getId());
                    if (inspectionTemplateBmp.load())
                    {
                        inspectionTemplateBmp.delete();
                    }
                }
            }
            else
            {
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
                if (inspectionTemplateBmpValue.getId()
                    != null
                    && inspectionTemplateBmpValue.getId()
                           .intValue()
                       > 0)
                {
                    inspectionTemplateBmp.setId(inspectionTemplateBmpValue.getId());
                    inspectionTemplateBmp.load();
                }
                inspectionTemplateBmp.setBmp((EcBmp) HibernateUtil.load(EcBmp.class,
                                                                        inspectionTemplateBmpValue.getBmpId()));
                inspectionTemplateBmp.setDescription(inspectionTemplateBmpValue.getBmpDescription());
                inspectionTemplateBmp.setInspectionTemplateId(inspectionTemplate.getId());
                inspectionTemplateBmp.setIsRequired(new Boolean(inspectionTemplateBmpValue.getIsRequired()));
                inspectionTemplateBmp.save();
            }
            inspectionTemplateBmpListIndex++;
        }
        return (mapping.findForward("continue"));
    }
}