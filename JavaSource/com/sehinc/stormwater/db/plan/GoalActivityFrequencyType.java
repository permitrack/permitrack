package com.sehinc.stormwater.db.plan;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GoalActivityFrequencyType
    implements UserType
{
    private static final
    int[]
        SQL_TYPES =
        {Types.INTEGER};

    public int[] sqlTypes()
    {
        return SQL_TYPES;
    }

    public Class returnedClass()
    {
        return GoalActivityFrequencyData.class;
    }

    public boolean equals(Object x, Object y)
    {
        return x
               == y;
    }

    public Object deepCopy(Object value)
    {
        return value;
    }

    public boolean isMutable()
    {
        return false;
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
        throws HibernateException, SQLException
    {
        Integer
            id =
            new Integer(resultSet.getInt(names[0]));
        return resultSet.wasNull()
            ? null
            : GoalActivityFrequencyData.getInstance(id);
    }

    public Serializable disassemble(Object o)
    {
        return (Serializable) o;
    }

    public Object assemble(Serializable o, Object owner)
    {
        return GoalActivityFrequencyData.getInstance(((GoalActivityFrequencyData) o).getId());
    }

    public Object replace(Object original, Object target, Object owner)
    {
        return GoalActivityFrequencyData.getInstance(((GoalActivityFrequencyData) original).getId());
    }

    public int hashCode(Object o)
    {
        return o.hashCode();
    }

    public void nullSafeSet(PreparedStatement statement, Object value, int index)
        throws HibernateException, SQLException
    {
        if (value
            == null)
        {
            statement.setNull(index,
                              Types.VARCHAR);
        }
        else
        {
            statement.setString(index,
                                value.toString());
        }
    }
}
