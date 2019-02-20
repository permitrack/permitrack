package com.sehinc.erosioncontrol.server.report;

import com.sehinc.common.util.MIMEType;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmp;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpDocument;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue;
import com.sehinc.service.servlet.util.FileDownloadServlet;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReportService
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportService.class);

    public ReportService()
    {
    }

    public static InspectionBmpValue getInspectionBmpValue(Integer inspectionBmpId)
    {
        EcInspectionBmp
            inspectionBmp =
            new EcInspectionBmp();
        inspectionBmp.setId(inspectionBmpId);
        if (inspectionBmp.load())
        {
            InspectionBmpValue
                inspectionBmpValue =
                new InspectionBmpValue(inspectionBmp);
            EcInspectionBmpDocument
                inspectionBmpDocument =
                EcInspectionBmpDocument.findByInspectionBmpId(inspectionBmpValue.getId());
            if (inspectionBmpDocument
                != null)
            {
                InspectionBmpDocumentValue
                    inspectionDocumentValue =
                    new InspectionBmpDocumentValue(inspectionBmpDocument);
                inspectionBmpValue.setBmpDocument(inspectionDocumentValue);
            }
            return inspectionBmpValue;
        }
        LOG.debug("returning NULL!!");
        return null;
    }

    public static List getInspectionBmpValueList(Integer inspectionId)
    {
        ArrayList
            inspectionBmpValueList =
            new ArrayList();
        List
            inspectionBmpList =
            EcInspectionBmp.findByInspectionId(inspectionId);
        Iterator
            iterator =
            inspectionBmpList.iterator();
        while (iterator.hasNext())
        {
            EcInspectionBmp
                inspectionBmp =
                (EcInspectionBmp) iterator.next();
            InspectionBmpValue
                inspectionBmpValue =
                new InspectionBmpValue(inspectionBmp);
            EcInspectionBmpDocument
                inspectionBmpDocument =
                EcInspectionBmpDocument.findByInspectionBmpId(inspectionBmpValue.getId());
            if (inspectionBmpDocument
                != null)
            {
                InspectionBmpDocumentValue
                    inspectionDocumentValue =
                    new InspectionBmpDocumentValue(inspectionBmpDocument);
                inspectionBmpValue.setBmpDocument(inspectionDocumentValue);
            }
            inspectionBmpValueList.add(inspectionBmpValue);
        }
        return inspectionBmpValueList;
    }

    public static File getInspectionBmpDocument(Integer inspectionBmpDocumentId)
        throws IOException
    {
        EcInspectionBmpDocument
            inspectionBmpDocument =
            new EcInspectionBmpDocument();
        inspectionBmpDocument.setId(inspectionBmpDocumentId);
        if (inspectionBmpDocument.load())
        {
            if (MIMEType.isStandardImage(inspectionBmpDocument.getName()))
            {
                String folder = FileDownloadServlet.getRealPath(inspectionBmpDocument.getLocation());

                String filePath = FileDownloadServlet.resize(folder.concat(inspectionBmpDocument.getName()), 400);

                File
                    documentFile =
                    new File(filePath);
                return documentFile;
            }
        }
        return null;
    }
}