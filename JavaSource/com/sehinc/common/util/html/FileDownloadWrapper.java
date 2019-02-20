package com.sehinc.common.util.html;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.crypto.CryptoUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;

public class FileDownloadWrapper
{
    private static
    Logger
        LOG =
        Logger.getLogger(FileDownloadWrapper.class);
    private
    String
        username;
    private
    Integer
        clientId;
    protected
    File
        file;
    private
    String
        downloadURL;

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setUsername(String username)
    {
        this.username =
            username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setFile(File file)
    {
        this.file =
            file;
    }

    public File getFile()
    {
        return file;
    }

    public String getName()
    {
        return file.getName();
    }

    public String getDownloadURL(HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        try
        {
            if (file
                != null)
            {
                buffer.append(ApplicationProperties.getBaseURL(request));
                buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                              + ApplicationProperties.getProperty("file.download.servlet"));
                buffer.append("?"
                              + ApplicationProperties.getProperty("param.username")
                              + "="
                              + URLEncoder.encode(getUsername(),
                                                  "UTF-8"));
                buffer.append("&"
                              + ApplicationProperties.getProperty("param.clientId")
                              + "="
                              + URLEncoder.encode(String.valueOf(getClientId()),
                                                  "UTF-8"));
                buffer.append("&"
                              + ApplicationProperties.getProperty("param.document.location")
                              + "="
                              + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                      file.getAbsolutePath()),
                                                  "UTF-8"));
                LOG.debug("Generating download url for file located at: "
                          + file.getAbsolutePath());
                return buffer.toString();
            }
        }
        catch (Exception e)
        {
            LOG.error("Error generating downloadURL: ",
                      e);
        }
        return null;
    }

    public String getPublicDownloadURL(HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        try
        {
            if (file
                != null)
            {
                buffer.append(ApplicationProperties.getBaseURL(request));
                buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                              + ApplicationProperties.getProperty("public.file.download.servlet"));
                buffer.append("?"
                              + ApplicationProperties.getProperty("param.document.location")
                              + "="
                              + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                      file.getAbsolutePath()),
                                                  "UTF-8"));
                LOG.debug("Generating public download url for file located at: "
                          + file.getAbsolutePath());
                return buffer.toString();
            }
        }
        catch (Exception e)
        {
            LOG.error("Error generating publicDownloadUR: ",
                      e);
        }
        return null;
    }

    public String getPublicDownloadImageURL(HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        try
        {
            if (file
                != null)
            {
                buffer.append(ApplicationProperties.getBaseURL(request));
                buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                              + ApplicationProperties.getProperty("image.servlet")
                              + "?"
                              + ApplicationProperties.getProperty("param.document.location")
                              + "="
                              +
                              URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                    file.getAbsolutePath()),
                                                "UTF-8"));
                LOG.debug("Generating public download url for file located at: "
                          + file.getAbsolutePath());
                return buffer.toString();
            }
        }
        catch (Exception e)
        {
            LOG.error("Error generating publicDownloadImageURL: ",
                      e);
        }
        return null;
    }

    public String getPublicDownloadURL(String baseUrl)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        try
        {
            if (file
                != null)
            {
                buffer.append(baseUrl);
                buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                              + ApplicationProperties.getProperty("public.file.download.servlet"));
                buffer.append("?"
                              + ApplicationProperties.getProperty("param.document.location")
                              + "="
                              + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                      file.getAbsolutePath()),
                                                  "UTF-8"));
                LOG.debug("Generating public download url for file located at: "
                          + file.getAbsolutePath());
                return buffer.toString();
            }
        }
        catch (Exception e)
        {
            LOG.error("Error generating publicDownloadURL: ",
                      e);
        }
        return null;
    }

    public String getDownloadURL()
    {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL)
    {
        this.downloadURL =
            downloadURL;
    }
}
