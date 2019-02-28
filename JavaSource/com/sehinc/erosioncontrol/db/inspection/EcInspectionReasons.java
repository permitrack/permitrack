package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;

@SuppressWarnings("unused")
public class EcInspectionReasons
        extends HibernateData {
    private
    Integer
            inspectionId;
    private
    String
            name;

    public EcInspectionReasons() {
    }

    public EcInspectionReasons(Integer id)
    {
        setId(id);
    }

    public Integer getInspectionId() {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId) {
        this.inspectionId =
                inspectionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name =
                name;
    }
}