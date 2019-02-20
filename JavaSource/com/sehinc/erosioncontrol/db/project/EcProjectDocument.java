package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class EcProjectDocument
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectDocument.class);
    private
    Integer
        projectId;
    private
    String
        name;
    private
    String
        location;
    private
    String
        comment;

    public EcProjectDocument()
    {
    }

    public EcProjectDocument(Integer id)
    {
        setId(id);
    }

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment =
            comment;
    }

    public static List findByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcProjectDocument as data where data.projectId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + getId());
        buffer.append("\nprojectId="
                      + projectId);
        buffer.append("\nname="
                      + name);
        buffer.append("\nlocation="
                      + location);
        buffer.append("\ncomment="
                      + comment);
        buffer.append("\n");
        return buffer.toString();
    }
}