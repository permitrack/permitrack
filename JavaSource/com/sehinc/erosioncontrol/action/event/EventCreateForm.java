package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.action.project.ProjectSearchForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

import java.util.Date;

public class EventCreateForm
    extends BaseValidatorForm
{
    private final static
    Logger
        LOG =
        Logger.getLogger(EventCreateForm.class);
    private
    boolean
        immutable =
        true;

    public boolean getImmutable()
    {
        return immutable;
    }

    public void setImmutable(boolean immutable)
    {
        this.immutable =
            immutable;
    }

    private
    Integer
        id;
    private
    Integer
        eventType;
    private
    Date
        eventDate;
    private
    String
        description;
    private
    String
        notice;
    private
    Boolean
        isComplianceByDate =
        new Boolean(true);
    private
    Date
        complianceDate;
    private
    Integer
        complianceHours;
    private
    String
        nextPage;
    private
    FormFile
        formFile;
    private
    ProjectSearchForm
        projectSearchForm =
        new ProjectSearchForm();

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getEventType()
    {
        return eventType;
    }

    public void setEventType(Integer type)
    {
        this.eventType =
            type;
    }

    public Date getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(Date date)
    {
        this.eventDate =
            date;
    }

    public String getEventDateString()
    {
        return DateUtil.mdyDate(eventDate);
    }

    public void setEventDateString(String date)
    {
        this.eventDate =
            DateUtil.parseDate(date);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String a)
    {
        this.description =
            a;
    }

    public String getNotice()
    {
        return notice;
    }

    public void setNotice(String a)
    {
        this.notice =
            a;
    }

    public Boolean getIsComplianceByDate()
    {
        return isComplianceByDate;
    }

    public void setIsComplianceByDate(Boolean b)
    {
        this.isComplianceByDate =
            b;
    }

    public Date getComplianceDate()
    {
        return complianceDate;
    }

    public void setComplianceDate(Date date)
    {
        this.complianceDate =
            date;
    }

    public String getComplianceDateString()
    {
        return DateUtil.mdyDate(complianceDate);
    }

    public void setComplianceDateString(String date)
    {
        this.complianceDate =
            DateUtil.parseDate(date);
    }

    public Integer getComplianceHours()
    {
        return complianceHours;
    }

    public void setComplianceHours(Integer hours)
    {
        this.complianceHours =
            hours;
    }

    public String getNextPage()
    {
        return this.nextPage;
    }

    public void setNextPage(String nextPage)
    {
        this.nextPage =
            nextPage;
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

    public ProjectSearchForm getProjectSearchForm()
    {
        return projectSearchForm;
    }

    public void setProjectSearchForm(ProjectSearchForm psf)
    {
        this.projectSearchForm =
            psf;
    }

    public void reset()
    {
        if (getImmutable())
        {
            return;
        }
        LOG.debug("reset the EventCreateForm");
        this.id =
            null;
        this.eventType =
            null;
        this.eventDate =
            null;
        this.description =
            null;
        this.notice =
            null;
        this.isComplianceByDate =
            true;
        this.complianceDate =
            null;
        this.complianceHours =
            null;
        this.nextPage =
            null;
        this.formFile =
            null;
        this.projectSearchForm
            .reset();
    }

    public void validateForm(ActionErrors errors)
    {
        LOG.debug("EventCreateForm.validateForm(errors:"
                  + errors
                  + ")");
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\neventCreateForm:[immutable=")
            .append(immutable);
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\neventType=")
            .append(eventType);
        buffer.append("\neventDate=")
            .append(eventDate);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\nnotice=")
            .append(notice);
        buffer.append("\ncomplianceDate=")
            .append(complianceDate);
        buffer.append("\ncomplianceHours=")
            .append(complianceHours);
        buffer.append("\nprojectSearchForm=")
            .append(projectSearchForm);
        buffer.append("\nisComplianceByDate=")
            .append(isComplianceByDate);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
