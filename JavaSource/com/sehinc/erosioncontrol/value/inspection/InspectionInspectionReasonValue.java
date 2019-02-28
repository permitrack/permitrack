package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionInspectionReason;

public class InspectionInspectionReasonValue
        implements java.io.Serializable {
    private
    Integer
            id;
    private
    Integer
            inspectionId;
    private
    Integer
            inspectionReasonId;
    private
    boolean
            isDeleted =
            false;

    public InspectionInspectionReasonValue() {
    }

    public InspectionInspectionReasonValue(EcInspectionInspectionReason inspectionInspectionReason) {
        this.id =
                inspectionInspectionReason.getId();
        this.inspectionId =
                inspectionInspectionReason.getInspectionId();
        this.inspectionReasonId =
                inspectionInspectionReason.getInspectionReasonId();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id =
                id;
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

    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted =
                isDeleted;
    }

    public String toString() {
        StringBuffer
                buffer =
                new StringBuffer();
        buffer.append("\nid="
                + id);
        buffer.append("\ninspectionId="
                + inspectionId);
        buffer.append("\ninspectionReasonId="
                + inspectionReasonId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}