package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InspectionReportValue
    extends InspectionValue
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionReportValue.class);
    private
    String
        url;

    public InspectionReportValue(EcInspection inspection)
    {
        super(inspection);
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url =
            url;
    }

    public void setUrl(Integer clientId, HttpServletRequest request)
    {
        StringBuilder
            buffer =
            new StringBuilder();
        String
            username =
            "";
        UserValue
            userValue =
            SessionService.getUserValue(request);
        if (userValue
            != null)
        {
            username =
                userValue.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getBaseURL(request));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context"))
                .append(ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "=")
                .append(URLEncoder.encode(RequestKeys.EC_INSPECTION_REPORT_SECURE_ACTION,
                                          "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "=")
                .append(URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                              clientId.toString()),
                                          "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "=")
                .append(URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                              username),
                                          "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "=")
                .append(URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                              getProjectId().toString()),
                                          "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_INSPECTION_ID
                          + "=")
                .append(URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                              getId().toString()),
                                          "UTF-8"));
            LOG.debug("Generating inspection report url for inspectionId: "
                      + getId());
            setUrl(buffer.toString());
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Inspection Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
    }
}