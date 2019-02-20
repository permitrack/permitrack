package com.sehinc.stormwater.action.report;

import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;
import com.sehinc.stormwater.db.permitperiod.PermitTimePeriodData;
import com.sehinc.stormwater.db.plan.*;
import com.sehinc.stormwater.server.plan.GoalService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BMPReportHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPReportHelper.class);
    public static final
    String
        TEXT_HTML =
        "HTML";
    public static final
    String
        TEXT_PLAIN =
        "PLAIN";

    private BMPReportHelper()
    {
    }

    public static String formatField(Integer bmpDataId, Integer fieldTypeId, String fieldValue, String mcmIdentifier)
    {
        return formatField(bmpDataId,
                           fieldTypeId,
                           fieldValue,
                           mcmIdentifier,
                           TEXT_PLAIN);
    }

    public static String formatField(Integer bmpDataId, Integer fieldTypeId, String fieldValue, Integer mcmIdentifier)
    {
        return formatField(bmpDataId,
                           fieldTypeId,
                           fieldValue,
                           (mcmIdentifier
                            == null)
                               ? ""
                               : mcmIdentifier.toString());
    }

    public static String formatField(Integer bmpDataId, Integer fieldTypeId, String fieldValue, String mcmIdentifier, String textType)
    {
        String
            returnString;
        BMPFieldType
            type =
            BMPFieldType.getTypeById(fieldTypeId);
        if (type.isFieldTypeText())
        {
            returnString =
                fieldValue;
            LOG.debug("*** BMPReportHelper.formatField( )- non-aggrigate field type "
                      + returnString);
        }
        else
        {
            BMPData
                bmpData =
                new BMPData();
            bmpData.setId(bmpDataId);
            if (bmpDataId
                == null
                || !bmpData.load())
            {
                return fieldValue;
            }
            if (type.equals(BMPFieldType.MEASURABLE_GOALS))
            {
                LOG.debug("BMPReportHelper.formatField( ) - MEASURABLE_GOALS field type, fieldTypeId = "
                          + fieldTypeId);
                returnString =
                    formatMeasurableGoals(bmpData,
                                          fieldTypeId,
                                          mcmIdentifier,
                                          textType);
                LOG.debug("*** BMPReportHelper.formatField( MEASURABLE_GOALS ) - "
                          + returnString);
            }
            else if (type.equals(BMPFieldType.TIMELINE))
            {
                LOG.debug("BMPReportHelper.formatField( ) - TIMELINE field type, fieldTypeId = "
                          + fieldTypeId);
                returnString =
                    formatTimeline(bmpData,
                                   fieldTypeId,
                                   mcmIdentifier,
                                   textType);
                LOG.debug("*** BMPReportHelper.formatField( TIMELINE ) - "
                          + returnString);
            }
            else if (type.equals(BMPFieldType.ACTIVITIS_FOR_GOALS))
            {
                LOG.debug("BMPReportHelper.formatField( ) - ACTIVITIS_FOR_GOALS field type, fieldTypeId = "
                          + fieldTypeId);
                returnString =
                    formatActivitiesForGoals(bmpData,
                                             fieldTypeId,
                                             mcmIdentifier,
                                             textType);
                LOG.debug("*** BMPReportHelper.formatField( ACTIVITIS_FOR_GOALS ) - "
                          + returnString);
            }
            else if (type.equals(BMPFieldType.ACTIVITY_IMPLEMENTATION_PLAN))
            {
                LOG.debug("BMPReportHelper.formatField( ) - ACTIVITY_IMPLEMENTATION_PLAN field type, fieldTypeId = "
                          + fieldTypeId);
                returnString =
                    formatActivityImplementationPlan(bmpData,
                                                     fieldTypeId,
                                                     mcmIdentifier,
                                                     textType);
                LOG.debug("*** BMPReportHelper.formatField( ACTIVITY_IMPLEMENTATION_PLAN ) - "
                          + returnString);
            }
            else if (type.equals(BMPFieldType.UNKNOWN))
            {
                LOG.debug("BMPReportHelper.formatField( ) - UNKNOWN field type, fieldTypeId = "
                          + fieldTypeId);
                returnString =
                    "";
                LOG.debug("*** BMPReportHelper.formatField( UNKNOWN ) - "
                          + returnString);
            }
            else
            {
                returnString =
                    "Error: BMP field type not defined??? \nField Id = "
                    + fieldTypeId
                    +
                    "\npassed value = "
                    + fieldValue;
                LOG.info("*** BMPReportHelper.formatField( ) - "
                         + returnString);
            }
        }
        return returnString;
    }

    public static String formatBmpDbField(Integer bmpDbDataId, Integer fieldTypeId, String fieldValue)
    {
        String
            returnString;
        BMPFieldType
            type =
            BMPFieldType.getTypeById(fieldTypeId);
        if (type.isFieldTypeText())
        {
            returnString =
                fieldValue;
            LOG.debug("*** BMPReportHelper.formatBmpDbField( )- non-aggrigate field type "
                      + returnString);
        }
        else
        {
            BMPDBData
                bmpDbData =
                new BMPDBData();
            bmpDbData.setId(bmpDbDataId);
            if (bmpDbDataId
                == null
                || !bmpDbData.load())
            {
                return fieldValue;
            }
            if (type.equals(BMPFieldType.MEASURABLE_GOALS))
            {
                LOG.debug("BMPReportHelper.formatBmpDbField( ) - MEASURABLE_GOALS field type, fieldTypeId = "
                          + fieldTypeId);
                returnString =
                    formatBmpDbMeasurableGoals(bmpDbData,
                                               fieldTypeId,
                                               BMPReportHelper.TEXT_HTML);
                LOG.debug("*** BMPReportHelper.formatBmpDbField( MEASURABLE_GOALS ) - "
                          + returnString);
            }
            else
            {
                returnString =
                    "";
            }
        }
        return returnString;
    }

    public static String formatField(Integer bmpDataId, Integer fieldTypeId, String fieldValue, Integer mcmIdentifier, String textType)
    {
        return formatField(bmpDataId,
                           fieldTypeId,
                           fieldValue,
                           (mcmIdentifier
                            == null)
                               ? ""
                               : mcmIdentifier.toString(),
                           textType);
    }

    private static String formatMeasurableGoals(BMPData bmpData, Integer fieldTypeId, String mcmIdentifier, String textType)
    {
        LOG.debug("1 bmpData.getId = "
                  + bmpData.getId());
        PlanPermitType
            planPermitType =
            PlanService.getPlanPermitType(new BMPValue(bmpData));
        if (planPermitType
            == null)
        {
            LOG.debug("planPermitType is NULL");
            return "";
        }
        BMPFormatter
            bmpFormatter =
            planPermitType.getBMPFormatter();
        StringBuffer
            returnMessage =
            new StringBuffer();
        LOG.debug("2");
        List
            goalList =
            GoalData.findActiveByBmpId(bmpData.getId());
        LOG.debug("3 goalList.size = "
                  + goalList.size());
        if (goalList.size()
            == 0)
        {
            return "No Goals Defined";
        }
        Iterator
            i =
            goalList.iterator();
        while (i.hasNext())
        {
            LOG.debug("4");
            GoalData
                goalData =
                (GoalData) i.next();
            returnMessage.append("Goal "
                                 + bmpFormatter.formatGoalIdentifier(bmpData,
                                                                     goalData)
                                 + "  "
                                 + goalData.getName()
                                 + ((textType.equals(TEXT_HTML))
                                        ? "<br>"
                                        : "\n"));
            returnMessage.append(goalData.getDescription()
                                 + ((textType.equals(TEXT_HTML))
                                        ? "<br><br>"
                                        : "\n\n"));
        }
        LOG.debug("5 Done");
        return returnMessage.toString();
    }

    private static String formatMeasurableGoals(BMPData bmpData, Integer fieldTypeId, Integer mcmIdentifier, String textType)
    {
        return formatMeasurableGoals(bmpData,
                                     fieldTypeId,
                                     (mcmIdentifier
                                      == null)
                                         ? ""
                                         : mcmIdentifier.toString(),
                                     textType);
    }

    private static String formatBmpDbMeasurableGoals(BMPDBData bmpDbData, Integer fieldTypeId, String textType)
    {
        StringBuffer
            returnMessage =
            new StringBuffer();
        LOG.debug("2");
        List
            goalList =
            BMPDBGoalData.findByBmpDBId(bmpDbData.getId());
        LOG.debug("3 goalList.size = "
                  + goalList.size());
        if (goalList.size()
            == 0)
        {
            return "No Goals Defined";
        }
        Iterator
            i =
            goalList.iterator();
        while (i.hasNext())
        {
            LOG.debug("4");
            BMPDBGoalData
                bmpDbGoalData =
                (BMPDBGoalData) i.next();
            returnMessage.append("Goal "
                                 + bmpDbGoalData.getNumber()
                                 + " - "
                                 + bmpDbGoalData.getName()
                                 + ((textType.equals(TEXT_HTML))
                                        ? "<br>"
                                        : "\n"));
            returnMessage.append(bmpDbGoalData.getDescription()
                                 + ((textType.equals(TEXT_HTML))
                                        ? "<br><br>"
                                        : "\n\n"));
        }
        LOG.debug("5 Done");
        return returnMessage.toString();
    }

    private static String formatTimeline(BMPData bmpData, Integer fieldTypeId, String mcmIdentifier, String textType)
    {
        BMPFormatter
            bmpFormatter =
            PlanService.getPlanPermitType(new BMPValue(bmpData))
                .getBMPFormatter();
        StringBuffer
            returnMessage =
            new StringBuffer();
        List
            goalList =
            GoalData.findActiveByBmpId(bmpData.getId());
        if (goalList.size()
            == 0)
        {
            return "No Goals Defined";
        }
        Iterator
            i =
            goalList.iterator();
        while (i.hasNext())
        {
            GoalData
                goalData =
                (GoalData) i.next();
            GoalValue
                goalValue =
                new GoalValue(goalData);
            returnMessage.append("Goal "
                                 + bmpFormatter.formatGoalIdentifier(bmpData,
                                                                     goalData)
                                 + "  "
                                 + goalData.getName()
                                 + ((textType.equals(TEXT_HTML))
                                        ? "<br>"
                                        : "\n"));
            returnMessage.append("Schedule:  "
                                 + goalData.getGoalActivityFrequency()
                                 + ((textType.equals(TEXT_HTML))
                                        ? "<br>"
                                        : "\n"));
            List
                permitTimePeriodData =
                GoalService.getGoalValuesList(goalValue.getId());
            if (permitTimePeriodData.size()
                == 0)
            {
                returnMessage.append("Plan Year(s):  None");
            }
            else
            {
                returnMessage.append("Plan Year(s):  ");
                Iterator
                    i2 =
                    permitTimePeriodData.iterator();
                while (i2.hasNext())
                {
                    PermitTimePeriodData
                        ptpData =
                        (PermitTimePeriodData) i2.next();
                    returnMessage.append(ptpData.getName());
                    if (i2.hasNext())
                    {
                        returnMessage.append(",  ");
                    }
                }
            }
            returnMessage.append(((textType.equals(TEXT_HTML))
                                      ? "<br><br>"
                                      : "\n\n"));
        }
        return returnMessage.toString();
    }

    private static String formatTimeline(BMPData bmpData, Integer fieldTypeId, Integer mcmIdentifier, String textType)
    {
        return formatTimeline(bmpData,
                              fieldTypeId,
                              (mcmIdentifier
                               == null)
                                  ? ""
                                  : mcmIdentifier.toString(),
                              textType);
    }

    private static String formatActivitiesForGoals(BMPData bmpData, Integer fieldTypeId, String mcmIdentifier, String textType)
    {
        return formatMeasurableGoals(bmpData,
                                     fieldTypeId,
                                     mcmIdentifier,
                                     textType);
    }

    private static String formatActivitiesForGoals(BMPData bmpData, Integer fieldTypeId, Integer mcmIdentifier, String textType)
    {
        return formatMeasurableGoals(bmpData,
                                     fieldTypeId,
                                     (mcmIdentifier
                                      == null)
                                         ? ""
                                         : mcmIdentifier.toString(),
                                     textType);
    }

    private static String formatActivityImplementationPlan(BMPData bmpData, Integer fieldTypeId, String mcmIdentifier, String textType)
    {
        return formatTimeline(bmpData,
                              fieldTypeId,
                              mcmIdentifier,
                              textType);
    }

    private static String formatActivityImplementationPlan(BMPData bmpData, Integer fieldTypeId, Integer mcmIdentifier, String textType)
    {
        return formatTimeline(bmpData,
                              fieldTypeId,
                              (mcmIdentifier
                               == null)
                                  ? ""
                                  : mcmIdentifier.toString(),
                              textType);
    }
}
