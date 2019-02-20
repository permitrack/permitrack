/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: AveryMailingLabels5160Report.java,v $
 *
 *
 */

package com.sehinc.dataview.report;

import com.sehinc.common.report.Report;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class AveryMailingLabels5160Report
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(AveryMailingLabels5160Report.class);

    public AveryMailingLabels5160Report()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("DataView Online");
        setReportTitle("Avery 5160 Mailing Labels");
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/dvo/AveryMailingLabels5160.jasper")));
    }
}