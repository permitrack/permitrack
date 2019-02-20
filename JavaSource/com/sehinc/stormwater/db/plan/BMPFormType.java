package com.sehinc.stormwater.db.plan;

import java.util.ArrayList;
import java.util.Collection;

public class BMPFormType
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    BMPFormatter
        formatter;
    private static
    Collection
        allBMPFormTypes;

    private BMPFormType(Integer id, String name, BMPFormatter formatter)
    {
        this.id =
            id;
        this.name =
            name;
        this.formatter =
            formatter;
    }

    public String toString()
    {
        return id
               + ";"
               + name;
    }

    public String getName()
    {
        return this.name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public BMPFormatter getBMPFormatter()
    {
        return this.formatter;
    }

    public static Collection getAll()
    {
        if (allBMPFormTypes
            == null)
        {
            allBMPFormTypes =
                new ArrayList();
            allBMPFormTypes.add(BMP_FORM_DEFAULT);
            allBMPFormTypes.add(BMP_FORM_MPCA_1);
            allBMPFormTypes.add(BMP_FORM_MPCA_2);
        }
        return allBMPFormTypes;
    }

    public static final
    BMPFormType
        BMP_FORM_DEFAULT =
        new BMPFormType(new Integer(0),
                        "NPDES Format",
                        BMPFormatter.DEFAULT);
    public static final
    BMPFormType
        BMP_FORM_MPCA_1 =
        new BMPFormType(new Integer(1),
                        "Minnesota Pollution Control Agency Format #1",
                        BMPFormatter.MPCA);
    public static final
    BMPFormType
        BMP_FORM_MPCA_2 =
        new BMPFormType(new Integer(2),
                        "Minnesota Pollution Control Agency Format #2",
                        BMPFormatter.MPCA);
}
