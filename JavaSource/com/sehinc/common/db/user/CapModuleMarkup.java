package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;

public class CapModuleMarkup
    extends HibernateData
{
    private
    Integer
        moduleId;
    private
    Integer
        markupId;

    public CapModuleMarkup()
    {
    }

    public CapModuleMarkup(Integer id)
    {
        setId(id);
    }

    public Integer getModuleId()
    {
        return moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public Integer getMarkupId()
    {
        return markupId;
    }

    public void setMarkupId(Integer markupId)
    {
        this.markupId =
            markupId;
    }
}
