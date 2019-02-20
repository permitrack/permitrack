package com.sehinc.erosioncontrol.value.project;

import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.erosioncontrol.db.project.EcProjectDocument;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import java.io.File;

public class ProjectDocumentValue
    extends FileDownloadWrapper
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectDocumentValue.class);
    private
    Integer
        id;
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
    private
    boolean
        isDeleted =
        false;
    private
    FormFile
        formFile;

    public ProjectDocumentValue()
    {
    }

    public ProjectDocumentValue(EcProjectDocument projectDocument)
    {
        this.id =
            projectDocument.getId();
        this.projectId =
            projectDocument.getProjectId();
        this.name =
            projectDocument.getName();
        this.location =
            projectDocument.getLocation();
        this.comment =
            projectDocument.getComment();
        this.file =
            new File(projectDocument.getLocation(),
                     projectDocument.getName());
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public FormFile getFormFile()
    {
        return this.formFile;
    }

    public void setFormFile(FormFile formFile)
    {
        this.formFile =
            formFile;
    }

    public boolean getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted =
            isDeleted;
    }

    public String getIsDeletedText()
    {
        if (isDeleted)
        {
            return "true";
        }
        return "false";
    }

    public void setIsDeletedText(String isDeleted)
    {
        if (isDeleted
            != null
            && isDeleted
               != ""
            && isDeleted.equalsIgnoreCase("true"))
        {
            this.isDeleted =
                true;
        }
        else
        {
            this.isDeleted =
                false;
        }
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("projectId="
                      + projectId);
        buffer.append("\nid="
                      + id);
        buffer.append("\nname="
                      + name);
        buffer.append("\nlocation="
                      + location);
        buffer.append("\ncomment="
                      + comment);
        buffer.append("\nformFile="
                      + formFile);
        buffer.append("\nisDeleted="
                      + isDeleted);
        buffer.append("\nisDeletedText="
                      + getIsDeletedText());
        buffer.append("\n\n");
        return buffer.toString();
    }
}