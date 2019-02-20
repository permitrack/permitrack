package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.BMPFormType;
import com.sehinc.stormwater.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBNewAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBNewAction.class);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBForm
            bmpDbForm =
            (BMPDBForm) form;
        LOG.info("In BMPDBNewAction");
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            bmpDbForm.reset();
            return (mapping.findForward("bmpdb.list.action"));
        }
        String
            action =
            (String) request.getAttribute(RequestKeys.ACTION_PARAMETER);
        if (action
            == null)
        {
            LOG.info("action = "
                     + action);
            addError(new ActionMessage("bmpdb.error.missingActionParameter"), request);
            return (mapping.findForward("bmpdb.list.action"));
        }
        if ("newstep1".equals(action))
        {
            LOG.debug("newstep1");
            bmpDbForm.reset();
/*
            request.getSession()
                .removeAttribute(SessionKeys.BMP_DB);
*/
            request.setAttribute(RequestKeys.BMP_DB_FORM_TYPE_LIST,
                                 BMPFormType.getAll());
            return mapping.findForward("continue");
        }
        if ("newstep2".equals(action))
        {
            if (bmpDbForm.getFormType()
                == null)
            {
                LOG.info("bmpDbForm.getFormType() returns null");
                addError(new ActionMessage("bmpdb.error.missingFormType"), request);
                return mapping.findForward("bmpdb.list.action");
            }
            else if (bmpDbForm.getFormType()
                .equals(BMPFormType.BMP_FORM_DEFAULT
                            .getId()))
            {
                bmpDbForm.initializeNonMPCABMP();
            }
            else if (bmpDbForm.getFormType()
                .equals(BMPFormType.BMP_FORM_MPCA_1
                            .getId()))
            {
                bmpDbForm.initializeMPCAForm1BMP();
            }
            else if (bmpDbForm.getFormType()
                .equals(BMPFormType.BMP_FORM_MPCA_2
                            .getId()))
            {
                bmpDbForm.initializeMPCAForm2BMP();
            }
            bmpDbForm.setFormType(bmpDbForm.getFormType());
            request.setAttribute(RequestKeys.BMP_DB_FORM_TYPE_ID,
                                 bmpDbForm.getFormType());
            return mapping.findForward("continue");
        }
        LOG.error(ApplicationResources.getProperty("bmpdb.error.unknownActionParameter"));
        addError(new ActionMessage("bmpdb.error.unknownActionParameter"), request);
        return mapping.findForward("bmpdb.list.action");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU,
                                                 0),
                        request);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.BMPDB_NEW_MENU_ITEM_NAME);
    }
}