package com.sehinc.common.report;

import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public interface ReportRunner
{
    File getOutputFile();

    OutputStream getOutputStream();

    void runToFile(HttpServletRequest request, File outputFile)
        throws SQLException, IOException, JRException, Exception;

    void runToStream(HttpServletRequest request, OutputStream outputStream)
        throws SQLException, IOException, JRException, Exception;
}