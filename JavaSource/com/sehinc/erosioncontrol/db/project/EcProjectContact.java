package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.List;

public class EcProjectContact
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectContact.class);
    private
    EcProjectContactType
        contactType;
    private
    CapContact
        contact;
    private
    Integer
        projectId;

    public EcProjectContact()
    {
    }

    public EcProjectContact(Integer id)
    {
        setId(id);
    }

    public EcProjectContactType getContactType()
    {
        return this.contactType;
    }

    public void setContactType(EcProjectContactType contactType)
    {
        this.contactType =
            contactType;
    }

    public CapContact getContact()
    {
        return this.contact;
    }

    public void setContact(CapContact contact)
    {
        this.contact =
            contact;
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

    public static List findByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {
                projectId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from EcProjectContact as data where data.projectId =? and data.contact.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}