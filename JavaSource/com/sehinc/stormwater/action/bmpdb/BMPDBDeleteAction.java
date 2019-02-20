package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.bmpdb.BMPDBCategoryData;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class BMPDBDeleteAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBDeleteAction.class);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {

        BMPDBValue
            bmpDbValue = getBMPDB(request);
        Iterator
            goalList =
            BMPDBGoalData.findByBmpDBId(bmpDbValue.getId())
                .iterator();
        while (goalList.hasNext())
        {
            BMPDBGoalData
                bmpDbGoalDataItem =
                (BMPDBGoalData) goalList.next();
            try
            {
                bmpDbGoalDataItem.delete();
            }
            catch (HibernateException he)
            {
                LOG.warn("In bmpdbAction(): Failed to delete BMPDBGoalData.id = "
                         + bmpDbGoalDataItem.getId());
            }
        }
        BMPDBData
            bmpDbData =
            new BMPDBData();
        bmpDbData.setId(bmpDbValue.getId());
        Integer
            bmpDbCategoryId =
            null;
        if (bmpDbData.load())
        {
            try
            {
                bmpDbCategoryId =
                    bmpDbData.getBmpDBCategoryId();
                bmpDbData.delete();
            }
            catch (HibernateException he)
            {
                LOG.warn("In bmpdbAction(): Failed to delete BMPDBData.id = "
                         + bmpDbData.getId());
            }
        }
        if (bmpDbCategoryId
            != null)
        {
            Long
                bmpCount =
                BMPDBCategoryData.countBMPById(bmpDbCategoryId);
            if (bmpCount
                == null
                || bmpCount.intValue()
                   == 0)
            {
                BMPDBCategoryData
                    bmpDbCategoryData =
                    new BMPDBCategoryData();
                bmpDbCategoryData.setId(bmpDbCategoryId);
                if (bmpDbCategoryData.load())
                {
                    bmpDbCategoryData.delete();
                }
                else
                {
                    LOG.warn("In bmpdbAction(): Failed to delete BMPDBCategoryData.id = "
                             + bmpDbCategoryId);
                }
            }
        }
        removeSessionAttribute(SessionKeys.BMP_DB,
                            request);
        return mapping.findForward("continue");
    }
}