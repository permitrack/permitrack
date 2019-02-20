package com.sehinc.common.util;

import java.io.File;

public class FileUtil
{
    static public boolean deleteDirectory(File path)
    {
        if (path.exists())
        {
            File[]
                files =
                path.listFiles();
            for (
                int
                    i =
                    0; i
                       < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                    deleteDirectory(files[i]);
                }
                else
                {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    static public String getFileExtension(String fileName)
    {
        int
            startIndex =
            fileName.lastIndexOf('.');
        if ((startIndex
             >= 0)
            && ((startIndex
                 + 1)
                < fileName.length()))
        {
            return fileName.substring(startIndex
                                      + 1);
        }
        return "";
    }

    static public String getFileExtension(File file)
    {
        return getFileExtension(file.getName());
    }
}
