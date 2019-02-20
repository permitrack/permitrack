package com.sehinc.common.util;

public class LabelValueBean
{
    public LabelValueBean(String label, String value)
    {
        this.label =
            label;
        this.value =
            value;
    }

    protected
    String
        label =
        null;

    public String getLabel()
    {
        return (this.label);
    }

    protected
    String
        value =
        null;

    public String getValue()
    {
        return (this.value);
    }

    public String toString()
    {
        StringBuffer
            sb =
            new StringBuffer("LabelValueBean[");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.value);
        sb.append("]");
        return (sb.toString());
    }

    public boolean equals(Object o)
    {
        if (o
            == this)
        {
            return true;
        }
        if (!(o instanceof LabelValueBean))
        {
            return false;
        }
        LabelValueBean
            lbvTwo =
            (LabelValueBean) o;
        if (this
            == null
            || lbvTwo
               == null)
        {
            return false;
        }
        if (this.getValue()
            == null
            || lbvTwo.getValue()
               == null)
        {
            return false;
        }
        if (lbvTwo.getValue()
            .equals(this.getValue()))
        {
            return true;
        }
        return false;
    }

    public int hashCode()
    {
        int
            result =
            8;
        result =
            31
            * result
            + (label
               != null
                   ? value.hashCode()
                   : 0);
        return result;
    }
}
