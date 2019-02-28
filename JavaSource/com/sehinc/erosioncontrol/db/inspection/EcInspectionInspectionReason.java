package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EcInspectionInspectionReason
        extends HibernateData {
    private
    Integer
            inspectionId;
    private
    Integer
            inspectionReasonId;

    public EcInspectionInspectionReason() {
    }

    public EcInspectionInspectionReason(Integer id) {
        setId(id);
    }

    public Integer getInspectionId() {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId) {
        this.inspectionId =
                inspectionId;
    }

    public Integer getInspectionReasonId() {
        return this.inspectionReasonId;
    }

    public void setInspectionReasonId(Integer inspectionReasonId) {
        this.inspectionReasonId =
                inspectionReasonId;
    }

    public static List<EcInspectionInspectionReason> findByInspectionId(Integer inspectionId) {
        Object
                parameters
                [
                ] =
                {inspectionId};
        String
                queryString =
                "select data from EcInspectionInspectionReason as data where data.inspectionId = ? order by data.inspectionReasonId asc";
        return (List<EcInspectionInspectionReason>) HibernateUtil.find(queryString,
                parameters);
    }
}