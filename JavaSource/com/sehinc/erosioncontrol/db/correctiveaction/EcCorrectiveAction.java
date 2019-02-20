package com.sehinc.erosioncontrol.db.correctiveaction;

import com.sehinc.common.db.user.StatusData;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.value.project.ProjectValue;

import java.util.Date;

public class EcCorrectiveAction
    extends StatusData
{
    private
    Integer
        id;
    private
    Date
        inspectionDate;
    private
    Integer
        projectId;
    private
    String
        projectName;
    private
    String
        bmpCategoryName;
    private
    String
        bmpName;
    private
    String
        bmpComment;
    private
    ProjectValue
        projectValue;
    public static final
    String
        BMP_NAME =
        "bmp_name";
    public static final
    String
        PROJECT_NAME =
        "project_name";
    public static final
    String
        LAST_INSPECTION_DATE =
        "last_inspection_date";

    public EcCorrectiveAction()
    {
    }

    public EcCorrectiveAction(Integer id)
    {
        setId(id);
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

    public String getBmpCategoryName()
    {
        return bmpCategoryName;
    }

    public void setBmpCategoryName(String bmpCategoryName)
    {
        this.bmpCategoryName =
            bmpCategoryName;
    }

    public String getBmpComment()
    {
        return bmpComment;
    }

    public void setBmpComment(String bmpComment)
    {
        this.bmpComment =
            bmpComment;
    }

    public String getBmpName()
    {
        return bmpName;
    }

    public void setBmpName(String bmpName)
    {
        this.bmpName =
            bmpName;
    }

    public Date getInspectionDate()
    {
        return inspectionDate;
    }

    public String getInspectionDateAsString()
    {
        return DateUtil.mdyDate(inspectionDate);
    }

    public void setInspectionDate(Date inspectionDate)
    {
        this.inspectionDate =
            inspectionDate;
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName =
            projectName;
    }

    public ProjectValue getProjectValue()
    {
        return projectValue;
    }

    public void setProjectValue(ProjectValue projectValue)
    {
        this.projectValue =
            projectValue;
    }

    public String toString()
    {
        StringBuilder
            str =
            new StringBuilder();
        str.append("projectId: ")
            .append(this.getProjectId());
        str.append(" projName: ")
            .append(this.getProjectName());
        str.append(" bmpCategoryName: ")
            .append(this.getBmpCategoryName());
        str.append(" bmpName: ")
            .append(this.getBmpName());
        str.append(" bmpComment: ")
            .append(this.getBmpComment());
        str.append(" inspectionDate: ")
            .append(this.getInspectionDate());
        return str.toString();
    }
}