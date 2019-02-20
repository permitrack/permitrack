package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.hibernate.AliasToEntityLinkedMapResultTransformer;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.hibernate.SQLQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClientDataExport
    extends ReportBaseAction
{
    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ReportForm
            reportForm =
            (ReportForm) form;
        File
            outputFile;
        //
        // Get the Client value from the session
        //
        ClientValue
            clientValue =
            getClientValue(request);
        if (clientValue
            == null)
        {
            return mapping.findForward("fail");
        }
        //
        // Get the User value from the session
        //
        UserValue
            userValue =
            getUserValue(request);
        if (userValue
            == null)
        {
            return mapping.findForward("fail");
        }
        try
        {
            SXSSFWorkbook
                workbook =
                new SXSSFWorkbook(1000); // keep 1000 rows in memory, exceeding rows will be flushed to disk
            Sheet
                sheet =
                workbook.createSheet();
            List<Map<String, Object>>
                clientDataList =
                getClientData(clientValue.getId());
            /*
                        for (
                            int
                                rownum =
                                0; rownum
                                   < 1000; rownum++)
                        {
                            Row
                                row =
                                sh.createRow(rownum);
                            for (
                                int
                                    cellnum =
                                    0; cellnum
                                       < 10; cellnum++)
                            {
                                Cell
                                    cell =
                                    row.createCell(cellnum);
                                String
                                    address =
                                    new CellReference(cell).formatAsString();
                                cell.setCellValue(address);
                            }
                        }
                        // Rows with rownum < 900 are flushed and not accessible
                        for (
                            int
                                rownum =
                                0; rownum
                                   < 900; rownum++)
                        {
                            Assert.assertNull(sh.getRow(rownum));
                        }
                        // ther last 100 rows are still in memory
                        for (
                            int
                                rownum =
                                900; rownum
                                     < 1000; rownum++)
                        {
                            Assert.assertNotNull(sh.getRow(rownum));
                        }
            */
            int
                rowNumber =
                -1;
            Iterator
                clientDataListIterator =
                clientDataList.iterator();
            while (clientDataListIterator.hasNext())
            {
                rowNumber++;
                Map<String, Object>
                    clientRow =
                    (Map<String, Object>) clientDataListIterator.next();
                if (rowNumber
                    == 0)
                {
                    Row
                        headerRow =
                        sheet.createRow(rowNumber);
                    Object[]
                        headers =
                        clientRow.keySet()
                            .toArray();
                    for (
                        int
                            hcellnum =
                            0; hcellnum
                               < clientRow.size(); hcellnum++)
                    {
                        Cell
                            hcell =
                            headerRow.createCell(hcellnum);
                        Object
                            hval =
                            headers[hcellnum];
                        if (hval
                            != null)
                        {
                            hcell.setCellValue(hval.toString());
                        }
                    }
                    rowNumber++;
                }
                Row
                    row =
                    sheet.createRow(rowNumber);
                Object[]
                    values =
                    clientRow.values()
                        .toArray();
                for (
                    int
                        cellnum =
                        0; cellnum
                           < clientRow.size(); cellnum++)
                {
                    Cell
                        cell =
                        row.createCell(cellnum);
                    /*
                                        String
                                            address =
                                            new CellReference(cell).formatAsString();
                    */
                    Object
                        dval =
                        values[cellnum];
                    if (dval
                        != null
                        && !dval.toString()
                        .equals("null"))
                    {
                        cell.setCellValue(dval.toString());
                    }
                }
            }
            outputFile =
                getOutputFile(clientValue,
                              "xlsx");
            FileOutputStream
                out =
                new FileOutputStream(outputFile);
            workbook.write(out);
            out.close();
            // dispose of temporary files backing this workbook on disk
            workbook.dispose();
            //Set report URL in form
            reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                       clientValue.getId(),
                                                       outputFile,
                                                       request));
            //Set report title in form
            reportForm.setTitle("Client Data Export for "
                                + clientValue.getName());
            setSessionAttribute("reportFormESC",
                                reportForm,
                                request);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("error.report.execution.failed"),
                     request);
            return mapping.findForward("fail");
        }
        return mapping.findForward("continue");
    }

    public static List<Map<String, Object>> getClientData(Integer clientId)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("EXEC sp_client_esc_data_extract_all ");
        buffer.append("@clientId = "
                      + clientId);
        SQLQuery
            query =
            HibernateUtil.getNewSession()
                .createSQLQuery(buffer.toString());
        query.setResultTransformer(new AliasToEntityLinkedMapResultTransformer());
        List<Map<String, Object>>
            list =
            query.list();
        return list;
    }

    private File getOutputFile(ClientValue clientData, String fileExt)
    {
        Date
            nowDate =
            new Date();
        String
            nowTime =
            String.valueOf(nowDate.getTime());
        String
            outputFileName =
            "export_all_"
            + nowTime;
        String
            outputDir =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientData.getId()
            + "/temp/";
        File
            outputDirFile =
            new File(outputDir);
        if (!outputDirFile.exists())
        {
            outputDirFile.mkdirs();
        }
        return new File(outputDir
                        + outputFileName
                        + "."
                        + fileExt.toLowerCase());
    }
}
