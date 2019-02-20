package com.sehinc.stormwater.db.plan;

import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.MCMValue;

public class BMPFormatterDefault
    extends BMPFormatter
{
    public boolean useSections()
    {
        return false;
    }

    public String formatBMPSubIdentifier(Integer bmpNumber, String bmpSection)
    {
        return ((bmpNumber
                 != null)
                    ? bmpNumber.toString()
                    : "0");
    }

    public String formatBMPSubIdentifier(BMPValue bmpValue)
    {
        return formatBMPSubIdentifier(bmpValue.getNumber(),
                                      null);
    }

    public String formatBMPSubIdentifier(BMPData bmpData)
    {
        return formatBMPSubIdentifier(new BMPValue(bmpData));
    }

    public String formatBMPIdentifier(Integer mcmNumber, Integer bmpNumber, String bmpSection)
    {
        return formatMCMIdentifier(mcmNumber)
               + "."
               + formatBMPSubIdentifier(bmpNumber,
                                        null);
    }

    public String formatBMPLongIdentifier(PlanData plan, MCMData mcm, BMPData bmp)
    {
        return formatMCMIdentifier(mcm)
               + "."
               + formatBMPSubIdentifier(bmp);
    }

    public String formatBMPIdentifier(BMPValue bmpValue)
    {
        MCMData
            mcmData =
            PlanService.getMCM(bmpValue.getMcmId());
        return formatBMPIdentifier(((mcmData
                                     != null)
                                        ? mcmData.getNumber()
                                        : new Integer(0)),
                                   bmpValue.getNumber(),
                                   null);
    }

    public String formatBMPIdentifier(BMPData bmpData)
    {
        return formatBMPIdentifier(new BMPValue(bmpData));
    }

    public String formatMCMIdentifier(Integer mcmNumber)
    {
        return ((mcmNumber
                 != null)
                    ? mcmNumber.toString()
                    : "0");
    }

    public String formatMCMIdentifier(MCMData mcmData)
    {
        return formatMCMIdentifier(new MCMValue(mcmData));
    }

    public String formatMCMIdentifier(MCMValue mcmValue)
    {
        return formatMCMIdentifier(mcmValue.getNumber());
    }

    public String formatGoalIdentifier(Integer mcmNumber, Integer bmpNumber, String bmpSection, Integer goalNumber)
    {
        return formatBMPIdentifier(mcmNumber,
                                   bmpNumber,
                                   null)
               + "."
               + ((goalNumber
                   != null)
                      ? goalNumber.toString()
                      : "0");
    }

    public String formatGoalIdentifier(BMPValue bmpValue, GoalValue goalValue)
    {
        MCMData
            mcmData =
            PlanService.getMCM(bmpValue.getMcmId());
        return formatGoalIdentifier(((mcmData
                                      != null)
                                         ? mcmData.getNumber()
                                         : new Integer(0)),
                                    bmpValue.getNumber(),
                                    null,
                                    goalValue.getNumber());
    }

    public String formatGoalIdentifier(BMPData bmpData, GoalData goalData)
    {
        return formatGoalIdentifier(new BMPValue(bmpData),
                                    new GoalValue(goalData));
    }
}
