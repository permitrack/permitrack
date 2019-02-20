package com.sehinc.common.value.contact;

import com.sehinc.common.db.contact.CapContactType;

@SuppressWarnings("serial")
public class ContactTypeValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        selected;

    public ContactTypeValue()
    {
    }

    public ContactTypeValue(CapContactType contactType)
    {
        this.id =
            contactType.getId();
        this.name =
            contactType.getName();
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

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getSelected()
    {
        return this.selected;
    }

    public void setSelected(String s)
    {
        this.selected =
            s;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ContactTypeValue)
        {
            ContactTypeValue
                other =
                (ContactTypeValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
