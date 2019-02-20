package com.sehinc.erosioncontrol.action.gis;

import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.server.gis.GISService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GISSearchAction
    extends GISBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISSearchAction.class);

    public ActionForward gisAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        GISSearchForm
            gisSearchForm =
            (GISSearchForm) form;
        LOG.info("In GISSearchAction");
        LOG.info("submit="
                 + request.getParameter("submit"));
        ClientValue
            clientValue =
            getClientValue(request);
        if (gisSearchForm.getSearchParcelNumber()
            == null
            || gisSearchForm.getSearchParcelNumber()
                   .trim()
                   .length()
               == 0)
        {
            LOG.warn("No Search Criteria Entered");
            request.setAttribute(RequestKeys.EC_GIS_MESSAGE,
                                 "Please enter a parcel number for your search criteria.");
            return mapping.getInputForward();
        }
        List
            searchResults =
            GISService.getGISCoordValueList(clientValue,
                                            StringUtil.trimCharLeft(gisSearchForm.getSearchParcelNumber(),
                                                                    '0'));
        request.setAttribute(RequestKeys.GIS_SEARCH_RESULTS,
                             searchResults);
        LOG.info("Found "
                 + searchResults.size()
                 + " search results for parcel number "
                 + gisSearchForm.getSearchParcelNumber());
        request.setAttribute(RequestKeys.EC_GIS_MESSAGE,
                             "Found "
                             + searchResults.size()
                             + " search results for parcel number "
                             + gisSearchForm.getSearchParcelNumber());
        return mapping.findForward("continue");
    }
}