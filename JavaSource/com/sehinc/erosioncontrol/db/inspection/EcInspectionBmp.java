package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class EcInspectionBmp
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionBmp.class);
    private
    Integer
        inspectionId;
    private
    Integer
        projectBmpId;
    private
    String
        bmpName;
    private
    String
        bmpCategoryName;
    private
    Integer
        isInspected;
    private
    EcInspectionBmpStatus
        inspectionBmpStatus;
    private
    Boolean
        isRequired;
    private
    EcInspectionBmpCondition
        inspectionBmpCondition;
    private
    String
        description;
    private
    String
        comment;
    private
    String
        location;

    public EcInspectionBmp()
    {
    }

    public EcInspectionBmp(Integer id)
    {
        setId(id);
    }

    public Integer getInspectionId()
    {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId)
    {
        this.inspectionId =
            inspectionId;
    }

    public Integer getProjectBmpId()
    {
        return this.projectBmpId;
    }

    public void setProjectBmpId(Integer projectBmpId)
    {
        this.projectBmpId =
            projectBmpId;
    }

    public String getBmpName()
    {
        return this.bmpName;
    }

    public void setBmpName(String bmpName)
    {
        this.bmpName =
            bmpName;
    }

    public String getBmpCategoryName()
    {
        return this.bmpCategoryName;
    }

    public void setBmpCategoryName(String bmpCategoryName)
    {
        this.bmpCategoryName =
            bmpCategoryName;
    }

    public Integer getIsInspected()
    {
        return this.isInspected;
    }

    public void setIsInspected(Integer isInspected)
    {
        this.isInspected =
            isInspected;
    }

    public EcInspectionBmpStatus getInspectionBmpStatus()
    {
        return this.inspectionBmpStatus;
    }

    public void setInspectionBmpStatus(EcInspectionBmpStatus inspectionBmpStatus)
    {
        this.inspectionBmpStatus =
            inspectionBmpStatus;
    }

    public Boolean getIsRequired()
    {
        return this.isRequired;
    }

    public void setIsRequired(Boolean isRequired)
    {
        this.isRequired =
            isRequired;
    }

    public EcInspectionBmpCondition getInspectionBmpCondition()
    {
        return this.inspectionBmpCondition;
    }

    public void setInspectionBmpCondition(EcInspectionBmpCondition inspectionBmpCondition)
    {
        this.inspectionBmpCondition =
            inspectionBmpCondition;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment =
            comment;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public static List findByInspectionId(Integer inspectionId)
    {
        Object
            q1parameters
            [
            ] =
            {inspectionId};
        String
            queryString =
            "select data from com.sehinc.erosioncontrol.db.inspection.EcInspectionBmp as data where data.inspectionId = ? order by data.id asc";
        return HibernateUtil.find(queryString,
                                  q1parameters);
    }

    public static List findMostRecentDefectsByProjectId(Integer projectId, Integer inspectionId, Date inspectionDate)
    {
        List
            inspectionList;
        String
            queryString;
        if (inspectionDate
            == null)
        {
            inspectionDate =
                new Date();
        }
        if (inspectionId
            == null)
        {
            Object
                q1parameters
                [
                ] =
                {projectId};
            queryString =
                "select data from com.sehinc.erosioncontrol.db.inspection.EcInspection as data where data.projectId =? and data.status.code = "
                + StatusCodeData.STATUS_CODE_ACTIVE
                + " order by data.inspectionDate desc, data.id desc";
            inspectionList =
                HibernateUtil.findTop(queryString,
                                      q1parameters,
                                      1);
        }
        else
        {
            Object
                q2parameters
                [
                ] =
                {
                    projectId,
                    inspectionId};
            queryString =
                "select data from com.sehinc.erosioncontrol.db.inspection.EcInspection as data where data.projectId = ? and data.id <> ? and data.inspectionDate < '"
                + DateUtil.mdyDate(inspectionDate)
                + "' and data.status.code = "
                + StatusCodeData.STATUS_CODE_ACTIVE
                + " order by data.inspectionDate desc, data.id desc";
            inspectionList =
                HibernateUtil.findTop(queryString,
                                      q2parameters,
                                      1);
        }
        if (inspectionList
            != null
            && inspectionList.size()
               > 0)
        {
            EcInspection
                inspection =
                (EcInspection) inspectionList.get(0);
            Object
                q3parameters
                [
                ] =
                {inspection.getId()};
            queryString =
                "select data from com.sehinc.erosioncontrol.db.inspection.EcInspectionBmp as data where data.inspectionId = ? and data.inspectionBmpStatus.name <> 'Inactive' and data.inspectionBmpCondition.isPassCondition = 0 and data.isInspected = 1 order by data.id asc";
            return HibernateUtil.find(queryString,
                                      q3parameters);
        }
        return new ArrayList();
    }
}