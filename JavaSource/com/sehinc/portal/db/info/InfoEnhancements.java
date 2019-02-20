package com.sehinc.portal.db.info;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class InfoEnhancements
    extends StatusData
    implements java.io.Serializable
{
    public static
    String
        INFO_ENHANCEMENTS_BY_VERSION_ID =
        "com.sehinc.portal.db.info.InfoEnhancements.byVersionId";
    private
    String
        infoVersionId;
    private
    String
        message;

    public InfoEnhancements()
    {
    }

    public InfoEnhancements(Integer id)
    {
        setId(id);
    }

    public String getInfoVersionId()
    {
        return infoVersionId;
    }

    public void setInfoVersionId(String infoVersionId)
    {
        this.infoVersionId =
            infoVersionId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message =
            message;
    }

    public static List findActiveByVersionId(String versionId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "versionId",
                    versionId}};
        return HibernateUtil.findByNamedQuery(INFO_ENHANCEMENTS_BY_VERSION_ID,
                                              parameters);
    }

    public boolean equals(Object o)
    {
        if (o instanceof InfoEnhancements)
        {
            InfoEnhancements
                other =
                (InfoEnhancements) o;
            return other.getId()
                .equals(this.getId());
        }
        return false;
    }
}