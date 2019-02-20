package com.sehinc.environment.db.code;

public class CodeData
{
    private
    String
        code;
    private
    String
        description;
    private
    int
        displayOrder;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public int getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder)
    {
        this.displayOrder =
            displayOrder;
    }

    public boolean equals(Object o)
    {
        if (this
            == o)
        {
            return true;
        }
        if (o
            == null
            || getClass()
               != o.getClass())
        {
            return false;
        }
        CodeData
            codeData =
            (CodeData) o;
        return !(code
                 != null
                     ? !code.equals(codeData.code)
                     : codeData.code
                       != null);
    }

    public int hashCode()
    {
        int
            result;
        result =
            (code
             != null
                 ? code.hashCode()
                 : 0);
        result =
            31
            * result
            + (description
               != null
                   ? description.hashCode()
                   : 0);
        result =
            31
            * result
            + displayOrder;
        return result;
    }
}