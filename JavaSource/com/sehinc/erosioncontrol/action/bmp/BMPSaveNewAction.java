package com.sehinc.erosioncontrol.action.bmp;

import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmp.BmpService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPSaveNewAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPSaveNewAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            bmpForm.reset();
            return mapping.findForward("BMP.list.page");
        }
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        //First determine if the user intends to create a new category:
        //if isNewCategory is true, then we should create a new category, but only if
        //it is a unique category name.  If it is a duplicate, return an error message
        LOG.debug("isNewCategory="
                  + bmpForm.getIsNewCategory());
        LOG.debug("BMPCategoryId="
                  + bmpForm.getBMPCategoryId());
        if (bmpForm.getIsNewCategory())
        {
            if (StringUtil.isEmpty(bmpForm.getCategoryName()))
            {
                addError(new ActionMessage("bmp.new.category.name.is.empty"),
                         request);
                LOG.info(ApplicationResources.getProperty("bmp.new.category.name.is.empty"));
                return mapping.getInputForward();
            }
            EcBmpCategory
                bmpCategory =
                BmpService.getBmpCategoryByName(bmpForm.getCategoryName(),
                                                clientValue);
            if (bmpCategory
                != null)
            {
                addError(new ActionMessage("bmp.new.category.already.exists"),
                         request);
                LOG.info(ApplicationResources.getProperty("bmp.new.category.already.exists"));
                return mapping.getInputForward();
            }
        }
        else
        {
            if (bmpForm.getBMPCategoryId()
                == null
                || bmpForm.getBMPCategoryId()
                       .intValue()
                   == 0)
            {
                addError(new ActionMessage("bmp.category.not.selected"),
                         request);
                LOG.info(ApplicationResources.getProperty("bmp.category.not.selected"));
                return mapping.getInputForward();
            }
        }
        if (!bmpForm.getIsNewCategory())
        {
            EcBmp
                bmp =
                BmpService.getBmpByCategoryIdAndName(bmpForm.getBMPCategoryId(),
                                                     bmpForm.getName(),
                                                     clientValue);
            if (bmp
                != null)
            {
                addError(new ActionMessage("bmp.already.exists.in.category"),
                         request);
                LOG.info(ApplicationResources.getProperty("bmp.already.exists.in.category"));
                return mapping.getInputForward();
            }
        }
        EcBmpCategory
            newBmpCategory =
            new EcBmpCategory();
        try
        {
            if (!bmpForm.getIsNewCategory())
            {
                newBmpCategory.setId(bmpForm.getBMPCategoryId());
                newBmpCategory.load();
            }
            else
            {
                newBmpCategory.setClientId(clientValue.getId());
                newBmpCategory.setName(bmpForm.getCategoryName());
                newBmpCategory.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                newBmpCategory.save(userValue);
                newBmpCategory.load();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("bmp.category.create.or.load.failed",
                                       parameters),
                     request);
            LOG.error(ApplicationResources.getProperty("bmp.category.create.or.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        EcBmp
            newBmp =
            new EcBmp();
        try
        {
            newBmp.setClientId(clientValue.getId());
            newBmp.setCategory(newBmpCategory);
            newBmp.setName(bmpForm.getName());
            newBmp.setDescription(bmpForm.getDescription());
            newBmp.setWeblink(bmpForm.getWeblink());
            newBmp.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            newBmp.save(userValue);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("bmp.create.failed",
                                       parameters),
                     request);
            LOG.error(ApplicationResources.getProperty("bmp.create.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        addMessage(new ActionMessage("bmp.create.success"),
                   request);
        return mapping.findForward("continue");
    }
}
