package com.sehinc.erosioncontrol.value.event;

import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.db.event.EcEvent;
import org.apache.struts.upload.FormFile;

import java.util.Date;

public class EventValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    EventTypeValue
        eventType;
    private
    String
        description;
    private
    String
        notice;
    private
    Date
        eventDate;
    private
    Date
        complianceDate;
    private
    Integer
        complianceHours;
    private
    String
        eventDateString;
    private
    String
        complianceDateString;
    private
    Integer
        clientId;
    private
    String
        clientName;
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

    public EventValue()
    {
    }

    public EventValue(EcEvent event)
    {
        this.id =
            event.getId();
        this.eventType =
            new EventTypeValue(event.getEventType());
        this.description =
            event.getDescription();
        this.notice =
            event.getNotice();
        this.eventDate =
            event.getEventDate();
        this.complianceDate =
            event.getComplianceDate();
        this.complianceHours =
            event.getComplianceHours();
        this.eventDateString =
            DateUtil.mdyDate(eventDate);
        this.complianceDateString =
            DateUtil.mdyDate(complianceDate);
        this.clientId =
            event.getClient()
                .getId();
        this.clientName =
            event.getClient()
                .getName();
    }

    public EventValue(FormFile formFile)
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

    public EventTypeValue getEventType()
    {
        return this.eventType;
    }

    public void setEventType(EventTypeValue type)
    {
        this.eventType =
            type;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getNotice()
    {
        return this.notice;
    }

    public void setNotice(String notice)
    {
        this.notice =
            notice;
    }

    public Date getEventDate()
    {
        return this.eventDate;
    }

    public void setEventDate(Date eventDate)
    {
        this.eventDate =
            eventDate;
    }

    public Date getComplianceDate()
    {
        return this.complianceDate;
    }

    public void setComplianceDate(Date date)
    {
        this.complianceDate =
            date;
    }

    public Integer getComplianceHours()
    {
        return this.complianceHours;
    }

    public void setComplianceHours(Integer hours)
    {
        this.complianceHours =
            hours;
    }

    public String getEventDateString()
    {
        return this.eventDateString;
    }

    public void setEventDateString(String eventDateString)
    {
        this.eventDateString =
            eventDateString;
    }

    public String getComplianceDateString()
    {
        return this.complianceDateString;
    }

    public void setComplianceDateString(String complianceDateString)
    {
        this.complianceDateString =
            complianceDateString;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getClientName()
    {
        return this.clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName =
            clientName;
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

    public FormFile getFormFile()
    {
        return formFile;
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
        buffer.append("\nEventValue:[id=")
            .append(getId());
        buffer.append("\ntype=")
            .append(eventType);
        buffer.append("\nnotice=")
            .append(notice);
        buffer.append("\neventDate=")
            .append(eventDate);
        buffer.append("\ncomplianceDate=")
            .append(complianceDate);
        buffer.append("\ncomplianceHours=")
            .append(complianceHours);
        buffer.append("\n\n");
        return buffer.toString();
    }
}