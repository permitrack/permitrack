package com.sehinc.portal.db.info;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class InfoVersion
    extends StatusData
{
    public static
    String
        INFO_VERSION_BY_STATUS_CODE =
        "com.sehinc.portal.db.info.InfoVersion.byStatusCode";
    private
    String
        versionId;
    private
    String
        releaseDate;

    public InfoVersion()
    {
    }

    public InfoVersion(Integer id)
    {
        setId(id);
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate =
            releaseDate;
    }

    public String getVersionId()
    {
        return versionId;
    }

    public void setVersionId(String versionId)
    {
        this.versionId =
            versionId;
    }

    public static List findActive()
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
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(INFO_VERSION_BY_STATUS_CODE,
                                              parameters);
    }
}