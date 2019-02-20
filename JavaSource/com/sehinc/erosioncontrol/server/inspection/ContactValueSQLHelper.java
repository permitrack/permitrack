package com.sehinc.erosioncontrol.server.inspection;

import com.sehinc.common.db.sql.SQLValueHelper;
import com.sehinc.common.value.contact.ContactValue;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactValueSQLHelper
    implements SQLValueHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactValueSQLHelper.class);

    public ContactValueSQLHelper()
    {
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        ContactValue
            contactValue =
            new ContactValue();
        contactValue.setId(rs.getInt(1));
        return contactValue;
    }

    public String getValueSubQuery()
    {
        return null;
    }
}