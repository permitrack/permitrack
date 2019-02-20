package com.sehinc.erosioncontrol.action.gis;

import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.gis.EcGISCoord;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class GISEditAction
    extends GISBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISEditAction.class);

    public ActionForward gisAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        GISSearchForm
            gisSearchForm =
            (GISSearchForm) form;
/*
        LOG.info("submit="
                 + request.getParameter("submit"));
        String
            action =
            request.getParameter("submit");
        if (action
            != null
            && action.equals("New Search"))
        {
            LOG.info("New Search Requested.");
            gisSearchForm.reset();
            return (mapping.findForward("continue"));
        }
        UserValue
            userValue =
            getUserValue(request);
*/
        ClientValue
            clientValue =
            getClientValue(request);
        int
            deleteCount =
            0;
        int
            updateCount =
            0;
        String[]
            parcelNumbers =
            gisSearchForm.getParcelNumber();
        String[]
            gisX =
            gisSearchForm.getGisX();
        String[]
            gisY =
            gisSearchForm.getGisY();
        ArrayList
            isDeleted =
            new ArrayList();
        if (gisSearchForm.getIsDeleted()
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < gisSearchForm.getIsDeleted().length; i++)
            {
                isDeleted.add(gisSearchForm.getIsDeleted()[i]);
            }
        }
        if (parcelNumbers
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < parcelNumbers.length; i++)
            {
                EcGISCoord
                    gisCoord =
                    EcGISCoord.findUniqueByParcelNumber(clientValue,
                                                        StringUtil.trimCharLeft(parcelNumbers[i].trim(),
                                                                                '0'));
                if (isDeleted.contains(new Integer(i)))
                {
                    LOG.debug("Deleting Parcel Number = "
                              + gisCoord.getParcelNumber());
                    gisCoord.delete();
                    deleteCount++;
                }
                else
                {
                    gisCoord.setGisX(gisX[i]);
                    gisCoord.setGisY(gisY[i]);
                    gisCoord.update();
                    updateCount++;
                }
            }
            LOG.info("Deleted "
                     + deleteCount
                     + " records.  Updated "
                     + updateCount
                     + " records.");
            request.setAttribute(RequestKeys.EC_GIS_MESSAGE,
                                 "The records were successfully updated.");
        }
        else
        {
            request.setAttribute(RequestKeys.EC_GIS_MESSAGE,
                                 "There are no records to process.");
        }
        return mapping.findForward("continue");
    }
}