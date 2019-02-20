package com.sehinc.stormwater.db.plan;

import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;

public class BMPFormatterMPCA
    extends BMPFormatter
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPFormatterMPCA.class);

    public boolean useSections()
    {
        return true;
    }

    public String formatBMPSubIdentifier(Integer bmpNumber, String bmpSection)
    {
        LOG.debug("in formatBMPsubIdentifier = "
                  + ((bmpSection
                      != null)
                         ? bmpSection
                           + "-"
                         : "")
                  + ((bmpNumber
                      != null)
                         ? bmpNumber.toString()
                         : "0"));
        return ((bmpSection
                 != null)
                    ? bmpSection
                      + "-"
                    : "")
               + ((bmpNumber
                   != null)
                      ? bmpNumber.toString()
                      : "0");
    }

    public String formatBMPSubIdentifier(BMPValue bmpValue)
    {
        return formatBMPSubIdentifier(bmpValue.getNumber(),
                                      bmpValue.getSection());
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
                                        bmpSection);
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
                                   bmpValue.getSection());
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
                                   bmpSection)
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
                                    bmpValue.getSection(),
                                    goalValue.getNumber());
    }

    public String formatGoalIdentifier(BMPData bmpData, GoalData goalData)
    {
        return formatGoalIdentifier(new BMPValue(bmpData),
                                    new GoalValue(goalData));
    }
}
