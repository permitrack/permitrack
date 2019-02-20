package com.sehinc.environment.value;

import com.sehinc.environment.db.source.EnvSource;

public class SourceValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        displayName;
    private
    String
        displayColorCd;
    private
    Integer
        sourceTypeCd;
    private
    String
        sourceTypeDesc;

    public SourceValue()
    {
    }

    public SourceValue(EnvSource src)
    {
        this.id =
            src.getId();
        this.displayName =
            src.getDisplayName();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName =
            displayName;
    }

    public String getDisplayColorCd()
    {
        if (this.displayColorCd
            != null
            && !this.displayColorCd
            .isEmpty())
        {
            String
                colorHashCode =
                this.displayColorCd;
            int
                length =
                colorHashCode.length();
            colorHashCode =
                colorHashCode.substring(1,
                                        length);
            StringBuffer
                fullPath =
                new StringBuffer();
            fullPath.append("/sehsvc/img/icons/colors/");
            fullPath.append(colorHashCode);
            fullPath.append(".gif");
            return fullPath.toString();
        }
        else
        {
            return "/sehsvc/img/icons/sehblank.gif";
        }
    }

    public void setDisplayColorCd(String displayColorCd)
    {
        this.displayColorCd =
            displayColorCd;
    }

    public Integer getSourceTypeCd()
    {
        return sourceTypeCd;
    }

    public void setSourceTypeCd(Integer sourceTypeCd)
    {
        this.sourceTypeCd =
            sourceTypeCd;
    }

    public String getSourceTypeDesc()
    {
        return sourceTypeDesc;
    }

    public void setSourceTypeDesc(String sourceTypeDesc)
    {
        this.sourceTypeDesc =
            sourceTypeDesc;
    }

    public boolean equals(Object o)
    {
        if (o instanceof SubstanceValue)
        {
            SubstanceValue
                other =
                (SubstanceValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
