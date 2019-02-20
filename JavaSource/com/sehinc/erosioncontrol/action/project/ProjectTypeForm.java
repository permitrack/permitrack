package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.project.EcEndPointType;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class ProjectTypeForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectTypeForm.class);
    // Fields
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        description;
    private
    EcEndPointType
        endPointType;
    private
    Integer
        endPointTypeId;
    private
    Boolean
        swmp;
    private
    Integer
        extendedOnlineAccessMonths;
    private
    Integer
        monthsFromStart;
    private
    Integer
        i =
        new Integer(0);
    private
    String
        s =
        new String("");

    public ProjectTypeForm()
    {
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getEndPointTypeId()
    {
        return this.endPointTypeId;
    }

    public void setEndPointTypeId(Integer v)
    {
        this.endPointTypeId =
            v;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer v)
    {
        this.clientId =
            v;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String v)
    {
        this.name =
            lt(v);
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String v)
    {
        this.description =
            lt(v);
    }

    public EcEndPointType getEndPointType()
    {
        return this.endPointType;
    }

    public void setEndPointType(EcEndPointType v)
    {
        this.endPointType =
            v;
    }

    public Boolean getSwmp()
    {
        return this.swmp;
    }

    public void setSwmp(Boolean v)
    {
        this.swmp =
            v;
    }

    public Integer getExtendedOnlineAccessMonths()
    {
        return this.extendedOnlineAccessMonths;
    }

    public void setExtendedOnlineAccessMonths(Integer v)
    {
        this.extendedOnlineAccessMonths =
            v;
    }

    public Integer getMonthsFromStart()
    {
        return this.monthsFromStart;
    }

    public void setMonthsFromStart(Integer v)
    {
        this.monthsFromStart =
            v;
    }

    public void reset()
    {
        this.clientId =
            i;
        this.description =
            s;
        this.endPointType =
            new EcEndPointType();
        this.endPointTypeId =
            i;
        this.extendedOnlineAccessMonths =
            i;
        this.monthsFromStart =
            i;
        this.id =
            i;
        this.name =
            s;
        this.swmp =
            false;
    }

    public void checkForHTML()
    {
        description =
            StringUtil.html(description);
        name =
            StringUtil.html(name);
    }

    public void validateForm(ActionErrors errors)
    {
        if (this.name
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "Project Type Name"));
        }
    }
}