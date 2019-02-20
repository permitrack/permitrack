package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionDocument;
import org.apache.struts.upload.FormFile;

import java.io.File;

@SuppressWarnings("serial")
public class InspectionDocumentValue
    extends FileDownloadWrapper
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        inspectionId;
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

    public InspectionDocumentValue()
    {
    }

    public InspectionDocumentValue(EcInspectionDocument inspectionDocument)
    {
        this.id =
            inspectionDocument.getId();
        this.inspectionId =
            inspectionDocument.getInspectionId();
        this.name =
            inspectionDocument.getName();
        this.location =
            inspectionDocument.getLocation();
        this.comment =
            inspectionDocument.getComment();
        this.file =
            new File(inspectionDocument.getLocation()
                     + inspectionDocument.getName());
    }

    public InspectionDocumentValue(FormFile formFile)
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

    public Integer getInspectionId()
    {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId)
    {
        this.inspectionId =
            inspectionId;
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
        this.isDeleted =
            isDeleted
            != null
            && !isDeleted.equals("")
            && isDeleted.equalsIgnoreCase("true");
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\ninspectionId=");
        buffer.append(inspectionId);
        buffer.append("\nid=");
        buffer.append(id);
        buffer.append("\nname=");
        buffer.append(name);
        buffer.append("\nlocation=");
        buffer.append(location);
        buffer.append("\ncomment=");
        buffer.append(comment);
        buffer.append("\nformFile=");
        buffer.append(formFile);
        buffer.append("\nisDeleted=");
        buffer.append(isDeleted);
        buffer.append("\nisDeletedText=");
        buffer.append(getIsDeletedText());
        buffer.append("\n\n");
        return buffer.toString();
    }
}