package com.sehinc.erosioncontrol.server.project;

import com.sehinc.common.db.sql.SQLValueHelper;
import com.sehinc.common.value.client.ClientValue;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientValueSQLHelper
    implements SQLValueHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientValueSQLHelper.class);

    public ClientValueSQLHelper()
    {
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        ClientValue
            clientValue =
            new ClientValue();
        clientValue.setId(rs.getInt(1));
        clientValue.setName(rs.getString(2));
        return clientValue;
    }

    public String getValueSubQuery()
    {
        return null;
    }
}