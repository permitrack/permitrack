package com.sehinc.erosioncontrol.value.event;

import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.erosioncontrol.db.event.EcEventDocument;
import org.apache.struts.upload.FormFile;

import java.io.File;

public class EventDocumentValue
    extends FileDownloadWrapper
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        eventId;
    private
    String
        docName;
    private
    String
        location;
    private
    boolean
        isDeleted =
        false;
    private
    FormFile
        formFile;

    public EventDocumentValue()
    {
    }

    public EventDocumentValue(EcEventDocument eventDocument)
    {
        if (eventDocument
            != null)
        {
            this.id =
                eventDocument.getId();
            this.eventId =
                eventDocument.getEventId();
            this.docName =
                eventDocument.getName();
            this.location =
                eventDocument.getLocation();
            this.file =
                new File(eventDocument.getLocation()
                         + eventDocument.getName());
        }
    }

    public EventDocumentValue(FormFile formFile)
    {
        this.formFile =
            formFile;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getEventId()
    {
        return eventId;
    }

    public void setEventId(Integer eventId)
    {
        this.eventId =
            eventId;
    }

    public String getDocName()
    {
        return docName;
    }

    public void setDocName(String docName)
    {
        this.docName =
            docName;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public boolean isDeleted()
    {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted)
    {
        this.isDeleted =
            isDeleted;
    }

    public FormFile getFormFile()
    {
        return formFile;
    }

    public void setFormFile(FormFile formFile)
    {
        this.formFile =
            formFile;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\neventId=")
            .append(eventId);
        buffer.append("\ndocName=")
            .append(docName);
        buffer.append("\nlocation=")
            .append(location);
        buffer.append("\nisDeleted=")
            .append(isDeleted);
        buffer.append("\n\n");
        return buffer.toString();
    }
}