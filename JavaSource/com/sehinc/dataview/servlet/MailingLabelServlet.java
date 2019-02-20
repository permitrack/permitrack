package com.sehinc.dataview.servlet;

import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.util.MIMEType;
import com.sehinc.dataview.MailingLabelBuilder;
import com.sehinc.dataview.report.AveryMailingLabels5160DataSource;
import com.sehinc.dataview.report.AveryMailingLabels5160Report;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailingLabelServlet
    extends HttpServlet
{
    private static
    Logger
        LOG =
        Logger.getLogger(MailingLabelServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        LOG.debug("doGet()");
        doPost(req,
               res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        LOG.debug("doPost()");
        try
        {
            int
                mailingLabelCount =
                Integer.parseInt(req.getParameter("count"));
            List
                labels =
                new ArrayList(mailingLabelCount);
            for (
                int
                    i =
                    0; i
                       < mailingLabelCount; i++)
            {
                List
                    label =
                    new ArrayList();
                for (
                    int
                        j =
                        0; j
                           < 5; j++)
                {
                    String
                        line =
                        req.getParameter("label:"
                                         + i
                                         + ":"
                                         + j);
                    if (line
                        != null)
                    {
                        label.add(line);
                    }
                    else
                    {
                        label.add("");
                    }
                }
                labels.add(label);
            }
            String
                type =
                req.getParameter("type");
            if (type
                != null
                && type.equals("text"))
            {
                downloadTextFile(labels,
                                 res);
            }
            else
            {
                downloadPdfFile(labels,
                                req,
                                res);
            }
        }
        catch (Exception e)
        {
            LOG.error("Error creating PDF mailing labels",
                      e);
        }
    }

    protected void downloadPdfFile(List labels, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        //Create the Avery 5160 Mailing Label Report
        AveryMailingLabels5160Report
            avery5160Report =
            new AveryMailingLabels5160Report();
        //Create a PDF report runner
        PDFReportRunner
            pdfReportRunner =
            new PDFReportRunner(avery5160Report,
                                new AveryMailingLabels5160DataSource(labels));
        //set the response content type
        response.setContentType(MIMEType.getMIMEType("MailingLabels.pdf"));
        //set the response header
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + "MailingLabels.pdf");
        //Get the output stream
        ServletOutputStream
            outputStream =
            response.getOutputStream();
        //Run the report
        pdfReportRunner.runToStream(request,
                                    outputStream);
        //close the output stream
        outputStream.flush();
        outputStream.close();
    }

    protected void downloadPdfFile_old(List labels, HttpServletResponse response)
        throws Exception
    {
        response.setContentType(MIMEType.getMIMEType("MailingLabels.pdf"));
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + "MailingLabels.pdf");
        ServletOutputStream
            outputStream =
            response.getOutputStream();
        MailingLabelBuilder
            builder =
            new MailingLabelBuilder(outputStream);
        builder.buildLabels(labels);
        outputStream.flush();
        outputStream.close();
    }

    protected void downloadTextFile(List labels, HttpServletResponse response)
        throws Exception
    {
        response.setContentType(MIMEType.getMIMEType("MailingLabels.txt"));
        response.setHeader("Content-disposition",
                           "attachment; filename="
                           + "MailingLabels.txt");
        ServletOutputStream
            outputStream =
            response.getOutputStream();
        outputStream.print("\"PID\",\"NAME1\",\"NAME2\",\"ADDRESS1\",\"ADDRESS2\"\r\n");
        for (
            int
                i =
                0; i
                   < labels.size(); i++)
        {
            List
                label =
                (List) labels.get(i);
            for (
                int
                    j =
                    0; j
                       < label.size(); j++)
            {
                if (j
                    > 0)
                {
                    outputStream.print(",");
                }
                outputStream.print("\""
                                   + label.get(j)
                    .toString()
                                   + "\"");
            }
            outputStream.print("\r\n");
        }
        outputStream.flush();
        outputStream.close();
    }
}
