package com.sehinc.stormwater.action.report;

import com.sehinc.common.db.forms.ElementValueData;
import com.sehinc.common.util.MIMEType;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import java.io.File;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class CustomFormReportScriptlet
    extends JRDefaultScriptlet
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(CustomFormReportScriptlet.class);
*/

    public boolean getDigitalPhotoFile(Integer formInstanceId)
        throws JRScriptletException
    {
        List
            elementList =
            ElementValueData.getElementValueByElementType(formInstanceId,
                                                          "file");
        if (elementList
            != null
            && elementList.size()
               > 0)
        {
            ElementValueData
                elementValue =
                (ElementValueData) elementList.get(0);
            File
                digitalPhotoFile =
                new File(elementValue.getElementValue());
            if (digitalPhotoFile.exists()
                && MIMEType.isStandardImage(digitalPhotoFile.getName()))
            {
                this.setVariableValue("vDigitalPhotoFile",
                                      digitalPhotoFile);
                return true;
            }
        }
        return false;
    }

/*
    public boolean getElementValue(Integer formInstanceId, Integer parentElementId)
        throws JRScriptletException
    {
        StringBuffer
            buffer =
            new StringBuffer();
        if (parentElementId
            == null)
        {
            return false;
        }
        try
        {
            List
                parentElementValueList =
                ElementValueData.getElementValueByElementId(formInstanceId,
                                                            parentElementId);
            if (parentElementValueList
                != null
                && parentElementValueList.size()
                   > 0)
            {
                ElementValueData
                    parentElementValue =
                    (ElementValueData) parentElementValueList.get(0);
                ElementData
                    parentElement =
                    new ElementData(parentElementValue.getElementId());
                parentElement.load();
                if (!StringUtil.isEmpty(parentElement.getTitle())
                    && parentElement.getDisplayTitle()
                    .equalsIgnoreCase("Y"))
                {
                    buffer.append(parentElement.getTitle()
                                  + ":  ");
                }
                if (!StringUtil.isEmpty(parentElementValue.getElementValue()))
                {
                    buffer.append(parentElementValue.getElementValue());
                }
                if (parentElement.getChildElementId()
                    != null)
                {
                    List
                        childElementValueList =
                        ElementValueData.getElementValueByElementId(formInstanceId,
                                                                    parentElement.getChildElementId());
                    if (childElementValueList
                        != null
                        && childElementValueList.size()
                           > 0)
                    {
                        buffer.append("  ");
                        ElementValueData
                            childElementValue =
                            (ElementValueData) childElementValueList.get(0);
                        ElementData
                            childElement =
                            new ElementData(childElementValue.getElementId());
                        childElement.load();
                        if (!StringUtil.isEmpty(childElement.getTitle())
                            && childElement.getDisplayTitle()
                            .equalsIgnoreCase("Y"))
                        {
                            buffer.append(childElement.getTitle()
                                          + ":  ");
                        }
                        if (!StringUtil.isEmpty(childElementValue.getElementValue()))
                        {
                            buffer.append(childElementValue.getElementValue());
                        }
                    }
                }
                if (buffer.length()
                    > 0)
                {
                    this.setVariableValue("vElementValue",
                                          buffer.toString());
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            LOG.debug("In scriptlet.getElementValue(): "
                      + e.getMessage());
        }
        return false;
    }
*/
}
