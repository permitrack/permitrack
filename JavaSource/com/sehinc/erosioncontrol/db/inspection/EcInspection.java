package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class EcInspection
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspection.class);
    private
    Integer
        projectId;
    private
    Integer
        clientId;
    private
    Date
        inspectionDate;
    private
    Integer
        hours;
    private
    Integer
        minutes;
    private
    String
        timePeriod;
    private
    Date
        enteredDate;
    private
    String
        weatherTrends;
    private
    String
        temperature;
    private
    String
        comment;
    private
    Date
        precipEndDate;
    private
    String
        precipAmount;
    private
    String
        precipSource;
    private
    String
        inspectionActionComment;
    private
    EcInspectionAction
        inspectionAction;
    private
    long
        version;
    private
    CapContact
        inspector;
    private
    Set<EcInspectionOverdueEmailLogEntry>
        overdueLogEntries =
        new HashSet<EcInspectionOverdueEmailLogEntry>();

    public EcInspection()
    {
    }

    public EcInspection(Integer id)
    {
        setId(id);
    }

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Date getInspectionDate()
    {
        return this.inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate)
    {
        this.inspectionDate =
            inspectionDate;
    }

    public Integer getHours()
    {
        return hours;
    }

    public void setHours(Integer hours)
    {
        this.hours =
            hours;
    }

    public Integer getMinutes()
    {
        return minutes;
    }

    public void setMinutes(Integer minutes)
    {
        this.minutes =
            minutes;
    }

    public String getTimePeriod()
    {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod)
    {
        this.timePeriod =
            timePeriod;
    }

    public Date getEnteredDate()
    {
        return this.enteredDate;
    }

    public void setEnteredDate(Date enteredDate)
    {
        this.enteredDate =
            enteredDate;
    }

    public String getWeatherTrends()
    {
        return this.weatherTrends;
    }

    public void setWeatherTrends(String weatherTrends)
    {
        this.weatherTrends =
            weatherTrends;
    }

    public String getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature =
            temperature;
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

    public Date getPrecipEndDate()
    {
        return this.precipEndDate;
    }

    public void setPrecipEndDate(Date precipEndDate)
    {
        this.precipEndDate =
            precipEndDate;
    }

    public String getPrecipAmount()
    {
        return this.precipAmount;
    }

    public void setPrecipAmount(String precipAmount)
    {
        this.precipAmount =
            precipAmount;
    }

    public String getPrecipSource()
    {
        return this.precipSource;
    }

    public void setPrecipSource(String precipSource)
    {
        this.precipSource =
            precipSource;
    }

    public String getInspectionActionComment()
    {
        return this.inspectionActionComment;
    }

    public void setInspectionActionComment(String inspectionActionComment)
    {
        this.inspectionActionComment =
            inspectionActionComment;
    }

    public EcInspectionAction getInspectionAction()
    {
        return this.inspectionAction;
    }

    public void setInspectionAction(EcInspectionAction inspectionAction)
    {
        this.inspectionAction =
            inspectionAction;
    }

    public String getInspectionReason()
    {
        List<String> inspectionReasonStringArray = new ArrayList<String>() {};
        List<EcInspectionInspectionReason>
                inspectionInspectionReasonList =
                EcInspectionInspectionReason.findByInspectionId(getId());
        if (inspectionInspectionReasonList
                != null)
        {
            for (EcInspectionInspectionReason reason : inspectionInspectionReasonList)
            {
                EcInspectionReason inspectionReason = new EcInspectionReason(reason.getInspectionReasonId());
                if(inspectionReason.load())
                {
                    inspectionReasonStringArray.add(inspectionReason.getName());
                }
            }
        }
        return StringUtils.join(inspectionReasonStringArray.toArray(), ", ");
    }

    public static List findAllByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcInspection as data where data.projectId = ? and data.status.code in ("
            + StatusCodeData.STATUS_CODE_ACTIVE
            + ","
            + StatusCodeData.STATUS_CODE_INCOMPLETE
            + ") order by data.inspectionDate desc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findMostRecentByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcInspection as data where data.projectId =? and data.status.code in ("
            + StatusCodeData.STATUS_CODE_ACTIVE
            + ","
            + StatusCodeData.STATUS_CODE_INCOMPLETE
            + ") order by data.inspectionDate desc";
        return HibernateUtil.findTop(queryString,
                                     parameters,
                                     1);
    }

    public static List findMostRecentActiveByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcInspection as data where data.projectId =? and data.status.code = "
            + StatusCodeData.STATUS_CODE_ACTIVE
            + ") order by data.inspectionDate desc";
        return HibernateUtil.findTop(queryString,
                                     parameters,
                                     1);
    }

    public static List findHistoryByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcInspection as data where data.projectId = ? and data.status.code in ("
            + StatusCodeData.STATUS_CODE_ACTIVE
            + ","
            + StatusCodeData.STATUS_CODE_INCOMPLETE
            + ") order by data.inspectionDate desc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findActiveHistoryByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcInspection as data where data.projectId = ? and data.status.code = "
            + StatusCodeData.STATUS_CODE_ACTIVE
            + " order by data.inspectionDate desc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static Date getInspectionDate(Integer inspectionId)
    {
        Object
            parameters
            [
            ] =
            {inspectionId};
        String
            queryString =
            "select data.inspectionDate from EcInspection as data where data.id = ?";
        return (Date) HibernateUtil.findUnique(queryString,
                                               parameters);
    }

    public long getVersion()
    {
        return version;
    }

    public void setVersion(long version)
    {
        this.version =
            version;
    }

    public CapContact getInspector()
    {
        return inspector;
    }

    public void setInspector(CapContact inspector)
    {
        this.inspector =
            inspector;
    }

    public Set<EcInspectionOverdueEmailLogEntry> getOverdueLogEntries()
    {
        return overdueLogEntries;
    }

    public void setOverdueLogEntries(Set<EcInspectionOverdueEmailLogEntry> overdueLogEntries)
    {
        this.overdueLogEntries =
            overdueLogEntries;
    }

    public boolean isFailed()
    {
        List<EcInspectionBmp>
            bmps =
            EcInspectionBmp.findByInspectionId(getId());
        if (bmps
            != null
            && bmps.size()
               > 0)
        {
            for (EcInspectionBmp bmp : bmps)
            {
                if (bmp.getInspectionBmpCondition()
                    != null
                    &&
                    bmp.getIsInspected()
                    != null
                    &&
                    (!bmp.getInspectionBmpStatus()
                        .getName()
                        .equals("Inactive"))
                    &&
                    (!bmp.getInspectionBmpCondition()
                        .getIsPassCondition())
                    && bmp.getIsInspected()
                       == 1)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
