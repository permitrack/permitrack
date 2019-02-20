package com.sehinc.erosioncontrol.action.gis;

import au.com.bytecode.opencsv.CSVReader;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.db.gis.EcGISCoord;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class GISBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISBaseAction.class);

    public abstract ActionForward gisAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        return gisAction(mapping,
                         form,
                         request,
                         response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.OPTIONS_LIST_MENU_ITEM);
    }

    public int processImportFile(FormFile importFile, ClientValue clientValue, UserValue userValue, boolean isUpdate, char delimiter)
        throws Exception
    {
        ArrayList<EcGISCoord>
            importTable =
            new ArrayList<EcGISCoord>();
        HashMap<String, EcGISCoord>
            gisCoordTable =
            new HashMap<String, EcGISCoord>();
        InputStream
            stream =
            importFile.getInputStream();
        // Create the temp import file on the server
        String
            outputFilePath =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientValue.getId()
            + "/temp/";
        File
            outputFile =
            new File(outputFilePath
                     + importFile.getFileName());
        // Make directories if needed
        outputFile.mkdirs();
        // Delete the file if it already exists
        if (outputFile.exists())
        {
            outputFile.delete();
        }
        //write the stream to the file specified
        OutputStream
            bos =
            new FileOutputStream(outputFile);
        int
            bytesRead;
        byte[]
            buffer =
            new byte[8192];
        while ((
                   bytesRead =
                       stream.read(buffer,
                                   0,
                                   8192))
               != -1)
        {
            bos.write(buffer,
                      0,
                      bytesRead);
        }
        bos.close();
        stream.close();
        CSVReader
            csvReader =
            new CSVReader(new InputStreamReader(new FileInputStream(outputFile)),
                          delimiter);
        //Load the import file
        String[]
            nextLine;
        int
            recordCount =
            0;
        while ((
                   nextLine =
                       csvReader.readNext())
               != null)
        {
            //We must make sure that the record has 3 valid columns in each record
            try
            {
                EcGISCoord
                    gisCoord =
                    new EcGISCoord();
                gisCoord.setClientId(clientValue.getId());
                gisCoord.setParcelNumber(StringUtil.trimCharLeft(nextLine[0].trim(),
                                                                 '0'));
                gisCoord.setGisY(StringUtil.trimCharLeft(nextLine[1].trim(),
                                                         '0'));
                gisCoord.setGisX(StringUtil.trimCharLeft(nextLine[2].trim(),
                                                         '0'));
                importTable.add(gisCoord);
                recordCount++;
            }
            catch (Exception e)
            {
                throw new Exception("The import file is invalid.  It is not in the required format.");
            }
        }
        // If isUpdate is false, then delete all of the records first
        if (!isUpdate)
        {
            EcGISCoord.deleteAllByClient(clientValue);
        }
        // Read all of the records for the given client
        List
            clientGisCoordList =
            EcGISCoord.findAllByClient(clientValue);
        //Load the existing table records into a hashmap
        Iterator
            gisCoordListIter =
            clientGisCoordList.iterator();
        while (gisCoordListIter.hasNext())
        {
            EcGISCoord
                gisCoord =
                (EcGISCoord) gisCoordListIter.next();
            gisCoordTable.put(gisCoord.getParcelNumber(),
                              gisCoord);
        }
        //Get a Hibernate session and transaction for the insert/update process
        //		Session session = HibernateUtil.getNewSession();
        //		Transaction tx = session.beginTransaction();
        //Iterate through the import table
        Iterator
            importTableIter =
            importTable.iterator();
        while (importTableIter.hasNext())
        {
            try
            {
                EcGISCoord
                    importGisCoord =
                    (EcGISCoord) importTableIter.next();
                if (gisCoordTable.get(importGisCoord.getParcelNumber())
                    != null)
                {
                    EcGISCoord
                        existingGisCoord =
                        gisCoordTable.get(importGisCoord.getParcelNumber());
                    existingGisCoord.setGisX(importGisCoord.getGisX());
                    existingGisCoord.setGisY(importGisCoord.getGisY());
                    existingGisCoord.update();
                }
                else
                {
                    importGisCoord.insert();
                }
            }
            catch (HibernateException he)
            {
                LOG.warn("processImportFile():"
                         + he.getMessage());
            }
        }
        return recordCount;
    }
}