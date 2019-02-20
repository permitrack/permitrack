/*
 Copyright (c) 2012. SEH Technology Solutions Inc.
 */

package com.sehinc.dataview;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.DvClientInformation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDownloadHelper
{
    public static List getTopLevelFilenames(DvClientInformation clientInformation)
    {
        if (clientInformation
            == null)
        {
            return new ArrayList();
        }
        String
            basePath =
            ApplicationProperties.getProperty("base.document.directory");
        String
            path =
            basePath
            + DataViewConstants.FILE_LOCATION
            + clientInformation.getDownLoads()
            + "/"
            + DataViewConstants.TOP_LEVEL_FILES
            + "/";
        // Create a File object for the requested file
        File
            requestedFile =
            new File(path);
        File
            files
            [
            ] =
            requestedFile.listFiles();
        List
            filenames =
            new ArrayList();
        if (files
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < files.length; i++)
            {
                filenames.add(files[i].getName());
            }
        }
        return filenames;
    }
}
