package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.hibernate.ReadOnlyData;

import java.util.List;

public class GoalActivityFormData
    extends ReadOnlyData
{
    public final static
    Integer
        DEFAULT =
        0;
    public final static
    Integer
        OUTFALL_INSPECTION =
        1;
    public final static
    Integer
        OUTFALL_INFORMATION =
        2;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        customFormId;
    private
    Integer
        clientId;
    private
    String
        downloadLocation;
    @SuppressWarnings("WeakerAccess")
    public static
    String
        FIND_GOAL_ACTIVITY_FORM_BY_CLIENT_ID_AND_FORM_ID =
        "com.sehinc.common.db.forms.GoalActivityFormData.byClientIdAndFormId";

    public GoalActivityFormData()
    {
    }

    public GoalActivityFormData(Integer id)
    {
        setId(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getCustomFormId()
    {
        return customFormId;
    }

    public void setCustomFormId(Integer integer)
    {
        customFormId =
            integer;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer integer)
    {
        clientId =
            integer;
    }

    public String getDownloadLocation()
    {
        return downloadLocation;
    }

    public void setDownloadLocation(String downloadLocation)
    {
        this.downloadLocation =
            downloadLocation;
    }

    public static List getElementValueByElementId(Integer clientId, Integer formId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "formId",
                    formId}};
        return HibernateUtil.findByNamedQuery(FIND_GOAL_ACTIVITY_FORM_BY_CLIENT_ID_AND_FORM_ID,
                                              parameters);
    }
}
