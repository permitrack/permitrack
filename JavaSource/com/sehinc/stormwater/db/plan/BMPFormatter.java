package com.sehinc.stormwater.db.plan;

import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;

public abstract class BMPFormatter
{
    public static
    BMPFormatter
        DEFAULT =
        new BMPFormatterDefault();
    public static
    BMPFormatter
        MPCA =
        new BMPFormatterMPCA();
    private static
    Logger
        LOG =
        Logger.getLogger(BMPFormatter.class);

    public static BMPFormatter getFormatter(Integer id)
    {
        if (id
            == null
            || id.intValue()
               == 1)
        {
            return DEFAULT;
        }
        else
        {
            if (id.intValue()
                == 2)
            {
                return MPCA;
            }
            else
            {
                LOG.debug("Unknown BMPFormatter type requested");
                return DEFAULT;
            }
        }
    }

    abstract public String formatBMPSubIdentifier(BMPData bmp);

    abstract public String formatBMPSubIdentifier(BMPValue bmpValue);

    abstract public String formatBMPSubIdentifier(Integer bmpNumber, String bmpSection);

    abstract public String formatBMPLongIdentifier(PlanData plan, MCMData mcm, BMPData bmp);

    abstract public String formatBMPIdentifier(Integer mcmNumber, Integer bmpNumber, String bmpSection);

    abstract public String formatBMPIdentifier(BMPData bmpData);

    abstract public String formatBMPIdentifier(BMPValue bmpValue);

    abstract public String formatMCMIdentifier(Integer mcmNumber);

    abstract public String formatMCMIdentifier(MCMData mcmData);

    abstract public String formatMCMIdentifier(MCMValue mcmValue);

    abstract public String formatGoalIdentifier(Integer mcmNumber, Integer bmpNumber, String bmpSection, Integer goalNumber);

    abstract public String formatGoalIdentifier(BMPValue bmpValue, GoalValue goalValue);

    abstract public String formatGoalIdentifier(BMPData bmpData, GoalData goalData);

    abstract public boolean useSections();
}
