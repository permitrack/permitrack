package com.sehinc.environment.value;

import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.db.substance.EnvSubstance;

public class SubstanceValue
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
        partNum;
    private
    EnvSubstanceTypeData
        typeData;
    private
    Integer
        typeCd;
    private
    String
        typeDesc;

    public SubstanceValue()
    {
    }

    public SubstanceValue(EnvSubstance sub)
    {
        this.id =
            sub.getId();
        this.name =
            sub.getName();
        this.partNum =
            sub.getPartNum();
        this.typeData =
            sub.getSubstanceType();
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getPartNum()
    {
        return partNum;
    }

    public void setPartNum(String partNum)
    {
        this.partNum =
            partNum;
    }

    public void setTypeData(EnvSubstanceTypeData typeData)
    {
        this.typeData =
            typeData;
    }

    public EnvSubstanceTypeData getTypeData()
    {
        return typeData;
    }

    public void setTypeCd(Integer typeCd)
    {
        this.typeCd =
            typeCd;
    }

    public Integer getTypeCd()
    {
        return typeCd;
    }

    public void setTypeDesc(String typeDesc)
    {
        this.typeDesc =
            typeDesc;
    }

    public String getTypeDesc()
    {
        return typeDesc;
    }

    public String getNameAndPartNum()
    {
        return name
               + (partNum
                  != null
                      ? " - "
                        + partNum
                      : "");
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