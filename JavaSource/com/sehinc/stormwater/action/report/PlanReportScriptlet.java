package com.sehinc.stormwater.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.user.UserService;
import com.sehinc.stormwater.db.permitperiod.GoalPermitTimePeriodData;
import com.sehinc.stormwater.db.plan.BMPFormatter;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.server.plan.OutfallInspectionService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import java.io.File;
import java.util.Iterator;

public class PlanReportScriptlet
    extends JRDefaultScriptlet
{
    public void afterGroupInit(String groupName)
        throws JRScriptletException
    {
        if (groupName.equals("MCMNameGroup"))
        {
            this.setVariableValue("BMPNumber",
                                  new Integer(1));
        }
        else if (groupName.equals("BMPNameGroup"))
        {
            this.setVariableValue("GoalNumber",
                                  new Integer(1));
        }
    }

    public String formatBMPIdentifier(Integer planId, Integer mcmNumber, Integer bmpNumber, String bmpSection)
    {
        PlanPermitType
            planPermitType =
            PlanService.getPlanPermitTypeByPlanId(planId);
        if (planPermitType
            != null)
        {
            BMPFormatter
                bmpFormatter =
                planPermitType.getBMPFormatter();
            return bmpFormatter.formatBMPIdentifier(mcmNumber,
                                                    bmpNumber,
                                                    bmpSection);
        }
        return "";
    }

    public String formatGoalIdentifier(Integer planId, Integer mcmNumber, Integer bmpNumber, String bmpSection, Integer goalNumber)
    {
        PlanPermitType
            planPermitType =
            PlanService.getPlanPermitTypeByPlanId(planId);
        if (planPermitType
            != null)
        {
            BMPFormatter
                bmpFormatter =
                planPermitType.getBMPFormatter();
            return bmpFormatter.formatGoalIdentifier(mcmNumber,
                                                     bmpNumber,
                                                     bmpSection,
                                                     goalNumber);
        }
        return "";
    }

    public String getOwnerName(Integer ownerId)
    {
        if (ownerId
            == null)
        {
            return null;
        }
        UserData
            owner =
            UserService.getUserIgnoreStatus(ownerId);
        if (owner
            == null)
        {
            return null;
        }
        return owner.getLastName()
               + ", "
               + owner.getFirstName();
    }

    public String getOwnerContactInfo(Integer ownerId)
    {
        if (ownerId
            == null)
        {
            return null;
        }
        UserData
            owner =
            UserService.getUserIgnoreStatus(ownerId);
        if (owner
            == null)
        {
            return null;
        }
        return owner.getName()
               + "\n"
               + owner.getTitle()
               + "\n"
               + owner.getPrimaryPhone();
    }

    public Integer setPermitTimePeriod(Integer planId, Integer goalId)
        throws JRScriptletException
    {
        StringBuffer
            activeList =
            new StringBuffer();
        StringBuffer
            completeList =
            new StringBuffer();
        Integer
            periods =
            new Integer(0);
        PlanData
            planData =
            PlanService.getPlan(planId);
        if (planData
            == null)
        {
            return new Integer(0);
        }
        Iterator
            permitTimePeriodIterator =
            PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId())
                .getPermitTimePeriods()
                .iterator();
        while (permitTimePeriodIterator.hasNext())
        {
            PermitTimePeriodValue
                permitTimePeriodValue =
                (PermitTimePeriodValue) permitTimePeriodIterator.next();
            GoalPermitTimePeriodData
                goalPermitTimePeriodData =
                PermitPeriodService.getGoalPermitTimePeriod(goalId.intValue(),
                                                            permitTimePeriodValue.getId());
            if (goalPermitTimePeriodData
                != null)
            {
                activeList.append(permitTimePeriodValue.getName()
                                  + " ");
                periods++;
                if (goalPermitTimePeriodData.isComplete())
                {
                    completeList.append(permitTimePeriodValue.getName()
                                        + " ");
                }
            }
        }
        this.setVariableValue("GoalPermitTimePeriodActiveList",
                              activeList.toString());
        this.setVariableValue("GoalPermitTimePeriodCompleteList",
                              completeList.toString());
        return periods;
    }

    public boolean getOutfallInspectionDigitalPhotoFile(Integer digitalPhotoFileLocationId)
        throws JRScriptletException
    {
        File
            digitalPhotoFile =
            OutfallInspectionService.getOutfallInspectionDigitalPhotoFile(digitalPhotoFileLocationId);
        this.setVariableValue("OutfallInspectionDigitalPhotoFile",
                              digitalPhotoFile);
        return digitalPhotoFile.exists();
    }

    public String getCheckedImage()
    {
        return ApplicationProperties.getProperty("base.document.directory")
               + "/img/icons/checkedbox.gif";
    }

    public String getUncheckedImage()
    {
        return ApplicationProperties.getProperty("base.document.directory")
               + "/img/icons/uncheckedbox.gif";
    }
}
