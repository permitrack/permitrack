package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpDocument;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import java.io.File;

public class InspectionBmpDocumentValue
    extends FileDownloadWrapper
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionBmpDocumentValue.class);
    private
    Integer
        id;
    private
    Integer
        inspectionBmpId;
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

    public InspectionBmpDocumentValue()
    {
    }

    public InspectionBmpDocumentValue(EcInspectionBmpDocument inspectionBmpDocument)
    {
        this.id =
            inspectionBmpDocument.getId();
        this.inspectionBmpId =
            inspectionBmpDocument.getInspectionBmpId();
        this.name =
            inspectionBmpDocument.getName();
        this.location =
            inspectionBmpDocument.getLocation();
        this.comment =
            inspectionBmpDocument.getComment();
        this.file =
            new File(inspectionBmpDocument.getLocation()
                     + inspectionBmpDocument.getName());
    }

    public InspectionBmpDocumentValue(FormFile formFile)
    {
        this.formFile =
            formFile;
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

    public Integer getInspectionBmpId()
    {
        return this.inspectionBmpId;
    }

    public void setInspectionBmpId(Integer inspectionBmpId)
    {
        this.inspectionBmpId =
            inspectionBmpId;
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
        buffer.append("\ninspectionBmpId="
                      + inspectionBmpId);
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

    public String formFileNiceToString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append(" File="
                      + formFile);
        return buffer.toString();
    }
}