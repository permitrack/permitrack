package com.sehinc.stormwater.action.report;

import net.sf.jasperreports.engine.JRScriptletException;

public class MCMReportScriptlet
    extends PlanReportScriptlet
{
    public void afterGroupInit(String groupName)
        throws JRScriptletException
    {
        if (groupName.equals("BMPNameGroup"))
        {
            this.setVariableValue("GoalNumber",
                                  new Integer(1));
        }
    }
}
