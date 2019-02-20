package com.sehinc.erosioncontrol.action.gis;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GISImportAction
    extends GISBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISImportAction.class);

    public ActionForward gisAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        GISForm
            gisForm =
            (GISForm) form;
        LOG.info("In GISImportAction");
        LOG.debug("delimiterText="
                  + gisForm.getDelimiterText());
        LOG.debug("delimiter="
                  + gisForm.getDelimiter()
                  + " OK?");
        LOG.debug("isUpdateText="
                  + gisForm.getIsUpdateText());
        LOG.debug("importFile="
                  + gisForm.getImportFile()
            .getFileName());
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            gisForm.reset();
            return mapping.findForward("project.list.page");
        }
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientValue.getId());
        clientData.load();
        if (gisForm.getImportFile()
            != null
            && gisForm.getImportFile()
                   .getFileName()
                   .trim()
                   .length()
               > 0)
        {
            try
            {
                int
                    recordsProcessed =
                    processImportFile(gisForm.getImportFile(),
                                      clientValue,
                                      userValue,
                                      gisForm.getIsUpdate(),
                                      gisForm.getDelimiter());
                request.setAttribute(RequestKeys.EC_GIS_MESSAGE,
                                     "Successfully imported "
                                     + recordsProcessed
                                     + " records.");
            }
            catch (Exception e)
            {
                LOG.error("Error loading import file.");
                request.setAttribute(RequestKeys.EC_GIS_MESSAGE,
                                     "Import Failed.  "
                                     + e.getMessage());
                return mapping.getInputForward();
            }
        }
        return mapping.findForward("continue");
    }
}