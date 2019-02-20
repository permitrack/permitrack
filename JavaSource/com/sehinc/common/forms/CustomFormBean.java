package com.sehinc.common.forms;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.forms.*;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.db.plan.GoalActivityFormData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CustomFormBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(CustomFormBean.class);

    private static String[] render(HttpServletRequest request, Integer formInstanceId, String method, Integer formId)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        StringBuffer
            subSectionBuffer =
            new StringBuffer();
        StringBuffer
            javascriptBuffer =
            new StringBuffer();
        StringBuffer
            getDataValueBuffer =
            new StringBuffer();
        StringBuffer
            getDataSourceBuffer =
            new StringBuffer();
        StringBuffer
            getListBuffer =
            new StringBuffer();
        StringBuffer
            validationBuffer =
            new StringBuffer();
        StringBuffer
            validationBuffer2 =
            new StringBuffer();
        int
            counterX =
            0;
        String
            determinedValue =
            "";
        getDataValueBuffer.append("function getDataValue(id){\n");
        getDataSourceBuffer.append("function getDataSource(id){\n");
        getListBuffer.append("function getList(name){\n"
                                  + "var list;\n");
        LOG.debug("In CustomFormBean.render");
        FormData
            formData;
        if (formInstanceId
            == null)
        {
            formData =
                new FormData(formId);
            formData.load();
            LOG.debug("render(): FormId "
                      + formId.toString()
                      + " retrieved.");
        }
        else
        {
            FormInstanceData
                formInstanceData =
                new FormInstanceData(formInstanceId);
            formInstanceData.load();
            formData =
                formInstanceData.getForm();
            formId =
                formData.getId();
        }
        HashMap<String, Integer>
            elementTypeCounters =
            new HashMap<String, Integer>();
        elementTypeCounters.put("file",
                                new Integer(0));
        elementTypeCounters.put("element",
                                new Integer(0));
        Collection
            sectionDataList =
            getSections(formId);
        Integer
            clientId =
            (Integer) request.getAttribute(RequestKeys.CLIENT_ID);
        Collection
            goalActivityFormDataList =
            GoalActivityFormData.getElementValueByElementId(clientId,
                                                            formId);
        GoalActivityFormData
            goalActivityFormData =
            new GoalActivityFormData();
        Iterator
            gafi =
            goalActivityFormDataList.iterator();
        if (gafi.hasNext())
        {
            goalActivityFormData =
                (GoalActivityFormData) gafi.next();
        }
        Iterator
            i =
            sectionDataList.iterator();
        while (i.hasNext())
        {
            SectionData
                sectionData =
                (SectionData) i.next();
            LOG.debug("render(): Rendering section "
                      + sectionData.getId());
            subSectionBuffer.append("<h4 class='myAccordian'>");
            subSectionBuffer.append(sectionData.getTitle());
            subSectionBuffer.append("</h4>");
            subSectionBuffer.append("<div>");
            LOG.debug("render(): Retrieving subsections for section "
                      + sectionData.getId());
            Collection
                subSectionDataList =
                getSubSections(sectionData.getId());
            Iterator
                ssi =
                subSectionDataList.iterator();
            while (ssi.hasNext())
            {
                SubSectionData
                    subSectionData =
                    (SubSectionData) ssi.next();
                LOG.debug("render(): Rendering subsection: "
                          + subSectionData.getId()
                          + " within section "
                          + sectionData.getId());
                String
                    subSectionTitle =
                    subSectionData.getTitle();
                if (subSectionTitle
                    != null)
                {
                    subSectionBuffer.append("<h6>");
                    subSectionBuffer.append(subSectionTitle);
                    subSectionBuffer.append("</h6>");
                }
                LOG.debug("render(): Retrieving elements for subsection "
                          + subSectionData.getId());
                Collection
                    elementDataList =
                    getElementsBySubSection(subSectionData.getId());
                Iterator
                    ei =
                    elementDataList.iterator();
                while (ei.hasNext())
                {
                    ElementData
                        elementData =
                        (ElementData) ei.next();
                    LOG.debug("render(): Rendering element: "
                              + elementData.getId()
                              + " within section "
                              + sectionData.getId());
                    if (elementData.getIsChild()
                        .equals("Y"))
                    {
                        LOG.debug("render(): Element "
                                  + elementData.getId()
                            .toString()
                                  + " is a child. Not displaying in parent loop.");
                    }
                    else
                    {
                        LOG.debug("render(): Retrieving the element_type for use in HTML widget.");
                        ElementTypeData
                            elementTypeData =
                            elementData.getElementType();
                        subSectionBuffer.append("<div class='control-group'>");
                        HashMap
                            elementHash;
                        elementHash =
                            getElementEditField(request,
                                                elementData,
                                                elementTypeData,
                                                formInstanceId,
                                                elementTypeCounters,
                                                null,
                                                counterX);
                        String
                            elementsString =
                            (String) elementHash.get("html");
                        if (determinedValue.isEmpty())
                        {
                            determinedValue =
                                (String) elementHash.get("determinedValue");
                        }
                        String
                            parentJavascript =
                            (String) elementHash.get("javascript");
                        String
                            parentJavascript22 =
                            (String) elementHash.get("javascript22");
                        String
                            parentJavascript33 =
                            (String) elementHash.get("javascript33");
                        String
                            parentJavascript44 =
                            (String) elementHash.get("javascript44");
                        String
                            parentValidation =
                            (String) elementHash.get("validation");
                        String
                            parentValidation555 =
                            (String) elementHash.get("validation555");
                        elementTypeCounters.put("element",
                                                (Integer) elementHash.get("element"));
                        elementTypeCounters.put("file",
                                                (Integer) elementHash.get("file"));
                        javascriptBuffer.append(parentJavascript);
                        validationBuffer.append(parentValidation);
                        validationBuffer2.append(parentValidation555);
                        getDataValueBuffer.append(parentJavascript22);
                        getDataSourceBuffer.append(parentJavascript33);
                        getListBuffer.append(parentJavascript44);
                        subSectionBuffer.append(elementsString);
                        if (!parentValidation555.equals(""))
                        {
                            counterX++;
                        }
                        LOG.debug("render(): Checking for a child element.");
                        Integer
                            childElementId =
                            elementData.getChildElementId();
                        if (childElementId
                            != null)
                        {
                            LOG.debug("render(): Child element "
                                      + childElementId.toString()
                                      + " found.");
                            ElementData
                                childElementData =
                                new ElementData(childElementId);
                            childElementData.load();
                            ElementTypeData
                                childElementTypeData =
                                childElementData.getElementType();
                            HashMap
                                childHash;
                            if (elementData.getAllowOther()
                                .equals("Y"))
                            {
                                LOG.debug("render(): Parent allows other.  Pass parent data to getElementEditField");
                                childHash =
                                    getElementEditField(request,
                                                        childElementData,
                                                        childElementTypeData,
                                                        formInstanceId,
                                                        elementTypeCounters,
                                                        elementData,
                                                        counterX);
                            }
                            else
                            {
                                LOG.debug("render(): Child element is being rendered as normal.");
                                childHash =
                                    getElementEditField(request,
                                                        childElementData,
                                                        childElementTypeData,
                                                        formInstanceId,
                                                        elementTypeCounters,
                                                        null,
                                                        counterX);
                            }
                            String
                                childString =
                                (String) childHash.get("html");
                            String
                                childJavascript =
                                (String) childHash.get("javascript");
                            String
                                childJavascript22 =
                                (String) childHash.get("javascript22");
                            String
                                childJavascript33 =
                                (String) childHash.get("javascript33");
                            String
                                childJavascript44 =
                                (String) childHash.get("javascript44");
                            String
                                childValidation =
                                (String) childHash.get("validation");
                            String
                                childValidation555 =
                                (String) childHash.get("validation555");
                            javascriptBuffer.append(childJavascript);
                            validationBuffer.append(childValidation);
                            validationBuffer2.append(childValidation555);
                            getDataValueBuffer.append(childJavascript22);
                            getDataSourceBuffer.append(childJavascript33);
                            getListBuffer.append(childJavascript44);
                            subSectionBuffer.append(childString);
                            elementTypeCounters.put("element",
                                                    (Integer) childHash.get("element"));
                            elementTypeCounters.put("file",
                                                    (Integer) childHash.get("file"));
                            if (!childValidation555.equals(""))
                            {
                                counterX++;
                            }
                        }
                        subSectionBuffer.append("</div>");
                        subSectionBuffer.append("</div>");
                    }
                }
                LOG.debug("render(): Closing off the subsection.");
            }
            LOG.debug("render(): Closing off the section.");
            subSectionBuffer.append("</div>");
        }
        LOG.debug("render(): Done with all sections.");
        String
            downloadLocation =
            goalActivityFormData.getDownloadLocation();
        LOG.debug("render(): Section collection retrieved. Beginning HTML form and main table.  Number of sections: "
                  + sectionDataList.size());
        buffer.append("<span class='label label-info'>");
        buffer.append(formData.getTitle());
        buffer.append("</span>");
        buffer.append("<fieldset><legend>");
        buffer.append(determinedValue);
        buffer.append("</legend></fieldset>");
        if (downloadLocation
            != null)
        {
            buffer.append("<a class='btn btn-mini pull-right' href='"
                          + downloadLocation
                          + "'>Download Form</a>");
        }
        if (formInstanceId
            != null)
        {
            String
                reportUrl =
                getReportURL(request,
                             formInstanceId);
            if (reportUrl
                != null)
            {
                buffer.append("<a class='btn btn-mini pull-right' href='"
                              + reportUrl
                              + "'>Download Report</a>");
            }
        }
        buffer.append(subSectionBuffer.toString());
        getDataValueBuffer.append("}\n");
        getDataSourceBuffer.append("\nreturn null;}\n");
        getListBuffer.append("\nreturn list;}\n");
        LOG.debug("render(): Appending javascript to the main buffer.");
        StringBuffer
            bufferJS =
            new StringBuffer();
        bufferJS.append("<script type='text/javascript'>\n"
                        + javascriptBuffer.toString());
        bufferJS.append(getDataValueBuffer.toString());
        bufferJS.append(getDataSourceBuffer.toString());
        bufferJS.append(getListBuffer.toString());
        bufferJS.append("\nfunction jcv_handleErrors(){}\n");
        bufferJS.append("\nfunction customForm_custom(){\n");
        bufferJS.append(validationBuffer2.toString());
        bufferJS.append("\n}\n");
        bufferJS.append("\nfunction validateForm() {\n");
        bufferJS.append("var validated = true;\n");
        bufferJS.append(validationBuffer.toString());
        bufferJS.append("return validated;");
        bufferJS.append("\n}\n");
        bufferJS.append("</script>\n");
        LOG.debug("render(): Returning the buffer.");
        return new String[] {
            buffer.toString(),
            bufferJS.toString()};
    }

    private static HashMap getElementEditField(HttpServletRequest request, ElementData elementData, ElementTypeData elementTypeData, Integer formInstanceId, HashMap<String, Integer> elementTypeCounters, ElementData parentData, int counterX)
    {
        StringBuffer
            elementBuffer =
            new StringBuffer();
        StringBuffer
            javascriptBuffer =
            new StringBuffer();
        StringBuffer
            javascriptBuffer22 =
            new StringBuffer();
        StringBuffer
            javascriptBuffer33 =
            new StringBuffer();
        StringBuffer
            javascriptBuffer44 =
            new StringBuffer();
        StringBuffer
            validationBuffer =
            new StringBuffer();
        StringBuffer
            validationBuffer555 =
            new StringBuffer();
        HashMap
            nameAndId =
            null;
        String
            determinedValue =
            "";
        String
            elementValue;
        String
            counterType;
        if (elementData.getDataType()
            .equals("file"))
        {
            counterType =
                "file";
        }
        else
        {
            counterType =
                "element";
        }
        LOG.debug("getElementEditField(): Preparing to get value for elementId "
                  + elementData.getId()
            .toString());
        List
            elementValueList =
            getElementValue(formInstanceId,
                            elementData.getId());
        Iterator
            evi =
            elementValueList.iterator();
        if (evi.hasNext())
        {
            ElementValueData
                elementValueData =
                (ElementValueData) evi.next();
            elementValue =
                elementValueData.getElementValue();
            LOG.debug("getElementEditField(): checking the value: ["
                      + elementValue
                      + "]");
            determinedValue =
                getElementValueToDisplay(elementValueData);
        }
        else
        {
            if (parentData
                != null)
            {
                List
                    parentValueList =
                    getElementValue(formInstanceId,
                                    parentData.getId());
                Iterator
                    pvi =
                    parentValueList.iterator();
                if (pvi.hasNext()
                    && parentData.getAllowOther()
                    .equals("Y"))
                {
                    ElementValueData
                        parentValueData =
                        (ElementValueData) pvi.next();
                    String
                        parentStoredValue =
                        parentValueData.getElementValue();
                    List
                        elementDomainList =
                        getElementDomainValue(parentData.getId());
                    Iterator
                        edi =
                        elementDomainList.iterator();
                    if (edi.hasNext())
                    {
                        ElementDomainData
                            elementDomainData =
                            (ElementDomainData) edi.next();
                        Integer
                            domainId =
                            elementDomainData.getDomainId();
                        DomainData
                            domainData =
                            new DomainData(domainId);
                        domainData.load();
                        Collection
                            domainValueList =
                            getDomainValue(domainId);
                        Iterator
                            dvi =
                            domainValueList.iterator();
                        boolean
                            otherValueIsSelected =
                            true;
                        while (dvi.hasNext())
                        {
                            DomainValueData
                                domainValueOptionData =
                                (DomainValueData) dvi.next();
                            String
                                optionValue =
                                domainValueOptionData.getDomainValue();
                            if (parentStoredValue.equals(optionValue))
                            {
                                otherValueIsSelected =
                                    false;
                            }
                        }
                        if (otherValueIsSelected)
                        {
                            determinedValue =
                                getElementValueToDisplay(parentValueData);
                        }
                    }
                    else
                    {
                        determinedValue =
                            getElementValueToDisplay(parentValueData);
                    }
                }
            }
        }
        if (elementData.getIsChild()
            .equals("N"))
        {
            if (elementData.getRequired()
                .equals("Y"))
            {
                elementBuffer.append("<label class='control-label'>"
                                     + getTitle(elementData)
                                     + "&nbsp;*</label>");
            }
            else
            {
                elementBuffer.append("<label class='control-label'>"
                                     + getTitle(elementData)
                                     + "</label>");
            }
        }
        else
        {
            elementBuffer.append("<span class='help-inline'>"
                                 + getTitle(elementData)
                                 + "</span>");
        }
        if (elementData.getIsChild()
            .equals("N"))
        {
            elementBuffer.append("<div class='controls'>");
        }
        else
        {
            elementBuffer.append("<span class='help-inline'>");
        }
        if (elementTypeData.getHtmlType()
            .equals("text"))
        {
            nameAndId =
                getNameAndId(elementTypeCounters,
                             elementData);
            elementBuffer.append(getTextField(elementData,
                                              nameAndId,
                                              determinedValue));
        }
        else if (elementTypeData.getHtmlType()
            .equals("textarea"))
        {
            nameAndId =
                getNameAndId(elementTypeCounters,
                             elementData);
            elementBuffer.append(getTextArea(elementData,
                                             nameAndId,
                                             determinedValue));
        }
        else if (elementTypeData.getHtmlType()
            .equals("select"))
        {
            nameAndId =
                getNameAndId(elementTypeCounters,
                             elementData);
            HashMap
                selectBuffers =
                getSelectField(elementData,
                               nameAndId,
                               determinedValue);
            elementBuffer.append((String) selectBuffers.get("elementBuffer"));
            javascriptBuffer22.append((String) selectBuffers.get("javascript22Buffer"));
            javascriptBuffer33.append((String) selectBuffers.get("javascript33Buffer"));
            javascriptBuffer44.append((String) selectBuffers.get("javascript44Buffer"));
        }
        else if (elementTypeData.getHtmlType()
            .equals("date"))
        {
            nameAndId =
                getNameAndId(elementTypeCounters,
                             elementData);
            HashMap
                dateBuffers =
                getDateField(elementData,
                             nameAndId,
                             determinedValue);
            elementBuffer.append((String) dateBuffers.get("elementBuffer"));
            javascriptBuffer.append((String) dateBuffers.get("javascriptBuffer"));
        }
        else if (elementTypeData.getHtmlType()
            .equals("file"))
        {
            nameAndId =
                getNameAndId(elementTypeCounters,
                             elementData);
            HashMap
                fileBuffers =
                getFileField(elementData,
                             nameAndId,
                             determinedValue,
                             request);
            elementBuffer.append((String) fileBuffers.get("elementBuffer"));
            javascriptBuffer.append((String) fileBuffers.get("javascriptBuffer"));
        }
        else
        {
            elementBuffer.append("<span class='error'>Couldn't render the HTML element.</span>");
        }
        if (elementData.getIsChild()
            .equals("Y"))
        {
            elementBuffer.append("</span>");
        }
        LOG.debug("getElementEditField(): HTML field constructed, creating validation Javascript.");
        if (determinedValue
            != null
            && !determinedValue.equals("")
            && elementData.getDataType()
            .equals("file"))
        {
            HashMap<String, String>
                h =
                buildJavascript(elementData,
                                (String) nameAndId.get("id"),
                                false,
                                counterX);
            validationBuffer.append(h.get("validation"));
            validationBuffer555.append(h.get("validation555"));
        }
        else
        {
            HashMap<String, String>
                h =
                buildJavascript(elementData,
                                (String) nameAndId.get("id"),
                                true,
                                counterX);
            validationBuffer.append(h.get("validation"));
            validationBuffer555.append(h.get("validation555"));
        }
        HashMap<String, Comparable>
            dataHash =
            new HashMap<String, Comparable>();
        dataHash.put("html",
                     elementBuffer.toString());
        dataHash.put("determinedValue",
                     determinedValue);
        dataHash.put("javascript",
                     javascriptBuffer.toString());
        dataHash.put("javascript22",
                     javascriptBuffer22.toString());
        dataHash.put("javascript33",
                     javascriptBuffer33.toString());
        dataHash.put("javascript44",
                     javascriptBuffer44.toString());
        dataHash.put("validation",
                     validationBuffer.toString());
        dataHash.put("validation555",
                     validationBuffer555.toString());
        Integer
            previous =
            elementTypeCounters.get(counterType);
        elementTypeCounters.put(counterType,
                                new Integer(previous.intValue()
                                            + 1));
        dataHash.put("element",
                     elementTypeCounters.get("element"));
        dataHash.put("file",
                     elementTypeCounters.get("file"));
        LOG.debug("getElementEditField(): Returning the dataHash with element info");
        return dataHash;
    }

    private static String getTextField(ElementData elementData, HashMap nameAndId, String determinedValue)
    {
        LOG.debug("getTextField(): Element_id "
                  + elementData.getId()
            .toString()
                  + " being rendered as a text box.");
        if (elementData.getSize()
            != null)
        {
        }
        else
        {
        }
        if (elementData.getMaxLength()
            != null)
        {
        }
        else
        {
        }
        StringBuffer
            elementBuffer =
            new StringBuffer();
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("indexName")
                             + "' id='"
                             + nameAndId.get("indexId")
                             + "' value='"
                             + elementData.getId()
            .toString()
                             + "' />");
        elementBuffer.append("<a href='#'");
        elementBuffer.append("   id='"
                             + nameAndId.get("id")
                             + "'");
        elementBuffer.append("   data-type='text'");
        elementBuffer.append("   data-original-title='"
                             + elementData.getDisplayTitle()
                             + "'>");
        elementBuffer.append(determinedValue);
        elementBuffer.append("</a>");
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("name")
                             + "'");
        elementBuffer.append("   id='"
                             + nameAndId.get("id")
                             + "'");
        elementBuffer.append("   value='"
                             + determinedValue
                             + "' />");
        return elementBuffer.toString();
    }

    private static String getTextArea(ElementData elementData, HashMap nameAndId, String determinedValue)
    {
        LOG.debug("getTextArea(): Element_id "
                  + elementData.getId()
            .toString()
                  + " being rendered as a text area.");
        StringBuffer
            elementBuffer =
            new StringBuffer();
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("indexName")
                             + "' id='"
                             + nameAndId.get("indexId")
                             + "' value='"
                             + elementData.getId()
            .toString()
                             + "' />");
        elementBuffer.append("<div id='"
                             + nameAndId.get("id")
                             + "' data-type='wysihtml5'>");
        elementBuffer.append(determinedValue
                             + "</div>");
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("name")
                             + "'");
        elementBuffer.append("   id='"
                             + nameAndId.get("id")
                             + "'");
        elementBuffer.append("   value='"
                             + determinedValue
                             + "' />");
        return elementBuffer.toString();
    }

    private static HashMap getDateField(ElementData elementData, HashMap nameAndId, String determinedValue)
    {
        LOG.debug("getDateField(): Element_id "
                  + elementData.getId()
            .toString()
                  + " being rendered as a date calendar.");
        HashMap<String, String>
            buffers =
            new HashMap<String, String>();
        StringBuilder
            elementBuffer =
            new StringBuilder();
        StringBuilder
            javascriptBuffer =
            new StringBuilder();
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("indexName")
                             + "' id='"
                             + nameAndId.get("indexId")
                             + "' value='"
                             + elementData.getId()
            .toString()
                             + "' />");
        elementBuffer.append("<a href='#'");
        elementBuffer.append("   id='"
                             + nameAndId.get("id")
                             + "'");
        elementBuffer.append("   data-type='date'");
        elementBuffer.append("   data-format='mm/dd/yyyy'");
        elementBuffer.append("   data-original-title='"
                             + elementData.getDisplayTitle()
                             + "'>");
        elementBuffer.append(determinedValue);
        elementBuffer.append("</a>");
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("name")
                             + "'");
        elementBuffer.append("   id='"
                             + nameAndId.get("id")
                             + "'");
        elementBuffer.append("   value='"
                             + determinedValue
                             + "' />");
        buffers.put("elementBuffer",
                    elementBuffer.toString());
        buffers.put("javascriptBuffer",
                    javascriptBuffer.toString());
        return buffers;
    }

    private static HashMap getFileField(ElementData elementData, HashMap nameAndId, String determinedValue, HttpServletRequest request)
    {
        LOG.debug("getFileField(): Element_id "
                  + elementData.getId()
            .toString()
                  + " being rendered as a file input with value of ["
                  + determinedValue
                  + "]");
        HashMap<String, String>
            buffers =
            new HashMap<String, String>();
        StringBuffer
            elementBuffer =
            new StringBuffer();
        StringBuffer
            javascriptBuffer =
            new StringBuffer();
        elementBuffer.append("<input type='hidden' name='"
                             + nameAndId.get("indexName")
                             + "' id='"
                             + nameAndId.get("indexId")
                             + "' value='"
                             + elementData.getId()
            .toString()
                             + "' />");


        elementBuffer.append("<div class='fileupload fileupload-new' data-provides='fileupload'>");
        elementBuffer.append("<div class='input-append'>");
        elementBuffer.append("<div class='uneditable-input span3'>");
        elementBuffer.append("<i class='icon-file fileupload-exists'></i>");

        if (determinedValue
            != "")
        {
            UserValue
                user =
                SessionService.getUserValue(request);
            String
                username =
                user.getUsername();
            File
                file =
                new File(determinedValue);
            FileDownloadWrapper
                fileDownload =
                new FileDownloadWrapper();
            fileDownload.setClientId((Integer) request.getAttribute(RequestKeys.CLIENT_ID));
            fileDownload.setUsername(username);
            fileDownload.setFile(file);
            elementBuffer.append("<span class='fileupload-preview'><a href='" + fileDownload.getDownloadURL(request) + "'>" + fileDownload.getName() + "</a></span>");
        }
        else
        {
            elementBuffer.append("<span class='fileupload-preview'></span>");
        }
        elementBuffer.append("</div>");
        elementBuffer.append("<span class='btn btn-file'>");
        elementBuffer.append("<span class='fileupload-new'>Select file</span>");
        elementBuffer.append("<span class='fileupload-exists'>Change</span>");
        elementBuffer.append("<input type='file' name='"
                             + nameAndId.get("name")
                             + "' id='"
                             + nameAndId.get("id")
                             + "' />");
        elementBuffer.append("</span>");
        elementBuffer.append("<a href='#' class='btn fileupload-exists' data-dismiss='fileupload'>Remove</a>");
        elementBuffer.append("</div>");
        elementBuffer.append("</div>");
        buffers.put("elementBuffer",
                    elementBuffer.toString());
        buffers.put("javascriptBuffer",
                    javascriptBuffer.toString());
        return buffers;
    }

    private static HashMap getSelectField(ElementData elementData, HashMap nameAndId, String determinedValue)
    {
        LOG.debug("getSelectField(): Element_id "
                  + elementData.getId()
            .toString()
                  + " being rendered as a select box.");
        HashMap<String, String>
            buffers =
            new HashMap<String, String>();
        StringBuffer
            elementBuffer =
            new StringBuffer();
        StringBuilder
            javascriptBuffer22 =
            new StringBuilder();
        StringBuilder
            javascriptBuffer33 =
            new StringBuilder();
        StringBuilder
            javascriptBuffer44 =
            new StringBuilder();
        boolean
            first =
            true;
        List
            elementDomainList =
            getElementDomainValue(elementData.getId());
        Iterator
            edi =
            elementDomainList.iterator();
        if (edi.hasNext())
        {
            ElementDomainData
                elementDomainData =
                (ElementDomainData) edi.next();
            Integer
                domainId =
                elementDomainData.getDomainId();
            DomainData
                domainData =
                new DomainData(domainId);
            domainData.load();
            javascriptBuffer22.append("\nif (id == '"
                                      + nameAndId.get("id")
                                      + "')"
                                      + "{ return ");
            javascriptBuffer33.append("\nif (id == '"
                                      + nameAndId.get("id")
                                      + "')"
                                      + "{ return getList('"
                                      + nameAndId.get("id")
                                      + "');}\n");
            javascriptBuffer44.append("\nif (name == '"
                                      + nameAndId.get("id")
                                      + "'){list=[\n");
            Collection
                domainValueList =
                getDomainValue(domainId);
            Iterator
                dvi =
                domainValueList.iterator();
            String currentValue = "";

                        if (elementDomainData.getAllowSelect()
                            .equals("Y"))
                        {
                            //elementBuffer.append("<option value='' >Select...</option>");
                            javascriptBuffer44.append((first
                                                           ? ""
                                                           : ",")
                                                      + "\n{\nvalue: '"
                                                      + "', text: '"
                                                      + "'\n}\n");
                            first = false;
                        }

            while (dvi.hasNext())
            {
                DomainValueData
                    domainValueOptionData =
                    (DomainValueData) dvi.next();
                String
                    optionValue =
                    domainValueOptionData.getDomainValue();
                javascriptBuffer44.append((first
                                               ? ""
                                               : ",")
                                          + "\n{\nvalue: '"
                                          + domainValueOptionData.getDomainValue()
                                          + "', text: '"
                                          + optionValue.replaceAll("'",
                                                                   "\\\\'")
                                          + "'\n}\n");
                first =
                    false;
                if (determinedValue.equals(optionValue))
                {
                    LOG.debug("getSelectField(): Marking elementValue ["
                              + optionValue
                              + "] as selected.");
                    currentValue = domainValueOptionData.getDomainValue();
                    javascriptBuffer22.append("'" + domainValueOptionData.getDomainValue() + "'");
                }
            }

            elementBuffer.append("<input type='hidden' name='"
                                 + nameAndId.get("indexName")
                                 + "' id='"
                                 + nameAndId.get("indexId")
                                 + "' value='"
                                 + elementData.getId()
                .toString()
                                 + "' />");
            elementBuffer.append("<a href='#'");
            elementBuffer.append("   id='"
                                 + nameAndId.get("id")
                                 + "'");
            elementBuffer.append("   data-type='select'");
            elementBuffer.append("   data-autotext='always'");
            elementBuffer.append("   data-original-title='"
                                 + elementData.getDisplayTitle()
                                 + "'>");
            if(currentValue.length() < 1 && determinedValue.length() > 0 && elementData.getAllowOther().equals("Y"))
            {
                elementBuffer.append("Other...");
                currentValue = "Other...";
                javascriptBuffer22.append("'" + "Other..." + "'");
            }
            else
            {
                elementBuffer.append(determinedValue);
            }
            elementBuffer.append("</a>");


            elementBuffer.append("<input type='hidden' name='"
                                 + nameAndId.get("name")
                                 + "'");
            elementBuffer.append("   id='"
                                 + nameAndId.get("id")
                                 + "'");
            elementBuffer.append("   value='"
                                 + currentValue
                                 + "' />\n");
        }
        javascriptBuffer22.append("; }\n");


                if (elementData.getAllowOther()
                    .equals("Y"))
                {
                    LOG.debug("getSelectField(): Adding OTHER value to dropdown");
                    //elementBuffer.append("<option value='Other...'");
//                    if (!elementSelected
//                        && (determinedValue.length()
//                            > 0))
//                    {
//                        elementBuffer.append(" SELECTED ");
//                    }
//                    elementBuffer.append(">Other...</option>");
                    javascriptBuffer44.append((first
                                                   ? ""
                                                   : ",")
                                              + "\n{\nvalue: '"
                                              + "Other..."
                                              + "', text: '"
                                              + "Other..."
                                              + "'\n}\n");

                }
//                elementBuffer.append("</select>");


        javascriptBuffer44.append("\n]}\n");
        buffers.put("elementBuffer",
                    elementBuffer.toString());
        buffers.put("javascript22Buffer",
                    javascriptBuffer22.toString());
        buffers.put("javascript33Buffer",
                    javascriptBuffer33.toString());
        buffers.put("javascript44Buffer",
                    javascriptBuffer44.toString());
        return buffers;
    }

    private static String getTitle(ElementData elementData)
    {
        String
            titleToDisplay =
            "";
        if (elementData.getDisplayTitle()
            .equals("Y"))
        {
            titleToDisplay =
                elementData.getTitle()
                + ":";
        }
        return titleToDisplay;
    }

    private static String getElementValueToDisplay(ElementValueData elementValueData)
    {
        LOG.debug("getElementValueToDisplay(): element "
                  + elementValueData.getElementId()
            .toString());
        if ((elementValueData.getElementValue()
             == null)
            || (elementValueData.getElementValue()
                    .equals(""))
            || (elementValueData.getElementValue()
                    .length()
                == 0))
        {
            LOG.debug("getElementValueToDisplay(): No value to display.");
            return "";
        }
        else
        {
            LOG.debug("getElementValueToDisplay(): Displayed the value from ELEMENT_VALUE.");
            String
                val =
                elementValueData.getElementValue();
            return val;
        }
    }

    public static String filterHTML(String content)
    {
        String
            newContent =
            StringUtil.replace(content,
                               "&",
                               "&amp;");
        newContent =
            StringUtil.replace(newContent,
                               "\"",
                               "&quot;");
        newContent =
            StringUtil.replace(newContent,
                               "<",
                               "&lt;");
        newContent =
            StringUtil.replace(newContent,
                               ">",
                               "&gt;");
        newContent =
            StringUtil.replace(newContent,
                               "^",
                               "&circ;");
        newContent =
            StringUtil.replace(newContent,
                               "~",
                               "&tilde;");
        newContent =
            StringUtil.replace(newContent,
                               "‘",
                               "&lsquo;");
        newContent =
            StringUtil.replace(newContent,
                               "’",
                               "&rsquo;");
        return newContent;
    }

    private static HashMap getNameAndId(HashMap elementTypeCounters, ElementData elementData)
    {
        HashMap<String, String>
            nameAndId =
            new HashMap<String, String>();
        StringBuilder
            name =
            new StringBuilder();
        StringBuilder
            id =
            new StringBuilder();
        StringBuilder
            indexName =
            new StringBuilder();
        StringBuilder
            indexId =
            new StringBuilder();
        StringBuilder
            deleteName =
            new StringBuilder();
        StringBuilder
            deleteId =
            new StringBuilder();
        String
            dataType =
            elementData.getDataType();
        if (elementData.getName()
            .equals("STATIC_activity_name"))
        {
            indexName.append("activityNameElementId");
            indexId.append("activityNameElementId");
            name.append("activityName");
            id.append("activityName");
        }
        else
        {
            if (elementData.getName()
                .equals("STATIC_activity_date"))
            {
                indexName.append("activityDateElementId");
                indexId.append("activityDateElementId");
                name.append("activityDate");
                id.append("activityDate");
            }
            else
            {
                if (elementData.getName()
                    .equals("STATIC_activity_desc"))
                {
                    indexName.append("activityDescElementId");
                    indexId.append("activityDescElementId");
                    name.append("activityDesc");
                    id.append("activityDesc");
                }
                else
                {
                    if (dataType.equals("file"))
                    {
                        Integer
                            index =
                            (Integer) elementTypeCounters.get("file");
                        name.append("fileList.file"
                                    + index.toString());
                        id.append("fileList.file"
                                  + index.toString());
                        indexName.append("fileIndexes.int"
                                         + index.toString());
                        indexId.append("fileIndexes.int"
                                       + index.toString());
                        deleteName.append("deleteList.int"
                                          + index.toString());
                        deleteId.append("deleteList.int"
                                        + index.toString());
                    }
                    else
                    {
                        Integer
                            index =
                            (Integer) elementTypeCounters.get("element");
                        name.append("elementList.element"
                                    + index.toString());
                        id.append("elementList.element"
                                  + index.toString());
                        indexName.append("elementIndexes.int"
                                         + index.toString());
                        indexId.append("elementIndexes.int"
                                       + index.toString());
                    }
                }
            }
        }
        nameAndId.put("name",
                      name.toString());
        nameAndId.put("id",
                      id.toString());
        nameAndId.put("indexName",
                      indexName.toString());
        nameAndId.put("indexId",
                      indexId.toString());
        nameAndId.put("deleteName",
                      deleteName.toString());
        nameAndId.put("deleteId",
                      deleteId.toString());
        return nameAndId;
    }

/*
    private static String reformatFileName(ElementData elementData, String displayValue, HttpServletRequest request)
    {
        if (elementData.getDataType()
            .equals("file"))
        {
            UserValue
                user =
                SessionService.getUserValue(request);
            String
                username =
                user.getUsername();
            File
                file =
                new File(displayValue);
            FileDownloadWrapper
                fileDownload =
                new FileDownloadWrapper();
            fileDownload.setClientId((Integer) request.getAttribute(RequestKeys.CLIENT_ID));
            fileDownload.setUsername(username);
            fileDownload.setFile(file);
            StringBuffer
                fileLink =
                new StringBuffer();
            fileLink.append("<a href='"
                            + fileDownload.getDownloadURL(request)
                            + "'>"
                            + fileDownload.getName()
                            + "</a>");
            return fileLink.toString();
        }
        else
        {
            return displayValue;
        }
    }
*/

    private static HashMap buildJavascript(ElementData elementData, String id, boolean checkReq, int counterX)
    {
        HashMap<String, String>
            hash =
            new HashMap<String, String>();
        StringBuffer
            validationBuffer =
            new StringBuffer();
        StringBuffer
            validationBuffer555 =
            new StringBuffer();
        String
            title =
            elementData.getTitle();
        String
            elementId =
            elementData.getId()
                .toString();
        String
            dataType =
            elementData.getDataType();
        String
            required =
            elementData.getRequired();
        if ((checkReq
             && required.equals("Y"))
            || dataType.equals("int")
            || dataType.equals("float")
            || dataType.equals("date"))
        {
            validationBuffer.append("var var"
                                    + elementId
                                    + " = $('input#"
                                    + id
                                    + "').val();");
            if (checkReq
                && required.equals("Y"))
            {
                validationBuffer555.append("this.a"
                                           + counterX
                                           + " = new Array('"
                                           + id
                                           + "','"
                                           + title.replaceAll("'",
                                                              "\\\\'")
                                           + " is required."
                                           + "', null);\n");
                validationBuffer.append("if ( var"
                                        + elementId
                                        + " == '') {\n");
                validationBuffer.append("jcv_handleErrors('"
                                        + title
                                        + " is required.', ''); validated= false;");
                validationBuffer.append("\n}\n");
            }
            if (dataType.equals("int"))
            {
                validationBuffer.append("if ( !validateInteger('"
                                        + id
                                        + "')) {\n");
                validationBuffer.append("jcv_handleErrors('"
                                        + title
                                        + " must be an integer value.', ''); validated= false;");
                validationBuffer.append("\n}\n");
            }
            else if (dataType.equals("float"))
            {
                validationBuffer.append("if ( !validateFloat('"
                                        + id
                                        + "')) { \n");
                validationBuffer.append("jcv_handleErrors('"
                                        + title
                                        + " must be a decimal or integer number.', ''); validated= false;");
                validationBuffer.append("\n}\n");
            }
            else if (dataType.equals("date"))
            {
                validationBuffer.append("\nif (!validateDate('"
                                        + id
                                        + "')) {\n");
                validationBuffer.append("jcv_handleErrors('"
                                        + title
                                        + " is not a valid date. (mm/dd/yyyy).', ''); validated= false;");
                validationBuffer.append("\n}\n");
            }
        }
        hash.put("validation",
                 validationBuffer.toString());
        hash.put("validation555",
                 validationBuffer555.toString());
        return hash;
    }

    public static String[] renderView(HttpServletRequest request, Integer formInstanceId)
    {
        LOG.debug("In CustomFormBean.renderView");
        return render(request,
                      formInstanceId,
                      "view",
                      null);
    }

    public static String[] renderEdit(HttpServletRequest request, Integer formInstanceId)
    {
        LOG.debug("In CustomFormBean.renderEdit");
        return render(request,
                      (Integer) request.getAttribute(RequestKeys.FORM_INSTANCE_ID),
                      "edit",
                      (Integer) request.getAttribute(RequestKeys.FORM_ID));
    }

    public static String[] renderCreate(HttpServletRequest request, Integer formId)
    {
        LOG.debug("In CustomFormBean.renderCreate");
        return render(request,
                      null,
                      "create",
                      formId);
    }

    public CustomFormBean()
    {
    }

    private static Collection getSections(Integer formId)
    {
        Object[][]
            parameters =
            {
                {
                    "formId",
                    formId}};
        String
            FIND_SECTIONS_BY_FORM_ID =
            "com.sehinc.common.db.forms.SectionData.byFormId";
        return HibernateUtil.findByNamedQuery(FIND_SECTIONS_BY_FORM_ID,
                                              parameters);
    }

    private static Collection getElementsBySubSection(Integer subSectionId)
    {
        Object[][]
            parameters =
            {
                {
                    "subSectionId",
                    subSectionId}};
        String
            FIND_ELEMENTS_BY_SUB_SECTION_ID =
            "com.sehinc.common.db.forms.ElementData.bySubSectionId";
        return HibernateUtil.findByNamedQuery(FIND_ELEMENTS_BY_SUB_SECTION_ID,
                                              parameters);
    }

    private static List getElementValue(Integer formInstanceId, Integer elementId)
    {
        Object[][]
            parameters =
            {
                {
                    "formInstanceId",
                    formInstanceId},
                {
                    "elementId",
                    elementId}};
        return HibernateUtil.findByNamedQuery(ElementValueData.FIND_ELEMENT_VALUE_BY_FORM_INSTANCE_ID_AND_ELEMENT_ID,
                                              parameters);
    }

    private static Collection getDomainValue(Integer domainId)
    {
        Object[][]
            parameters =
            {
                {
                    "domainId",
                    domainId}};
        String
            FIND_DOMAIN_VALUES_BY_DOMAIN_ID =
            "com.sehinc.common.db.forms.DomainValueData.byDomainId";
        return HibernateUtil.findByNamedQuery(FIND_DOMAIN_VALUES_BY_DOMAIN_ID,
                                              parameters);
    }

    private static List getElementDomainValue(Integer elementId)
    {
        Object[][]
            parameters =
            {
                {
                    "elementId",
                    elementId}};
        String
            FIND_ELEMENT_DOMAIN_BY_ELEMENT_ID =
            "com.sehinc.common.db.forms.ElementDomainData.byElementId";
        return HibernateUtil.findByNamedQuery(FIND_ELEMENT_DOMAIN_BY_ELEMENT_ID,
                                              parameters);
    }

    private static Collection getSubSections(Integer sectionId)
    {
        Object[][]
            parameters =
            {
                {
                    "sectionId",
                    sectionId}};
        String
            FIND_SUB_SECTIONS_BY_SECTION_ID =
            "com.sehinc.common.db.forms.SubSectionData.bySectionId";
        return HibernateUtil.findByNamedQuery(FIND_SUB_SECTIONS_BY_SECTION_ID,
                                              parameters);
    }

    public static String getCustomFormReportLocation(Integer goalActivityId)
    {
        GoalActivityFileLocationData
            aLocation =
            GoalActivityFileLocationData.findUniqueByGoalActivityIdAndName(goalActivityId,
                                                                           CommonConstants.CUSTOM_FORM_INSPECTION_PDF);
        if (aLocation
            != null)
        {
            return aLocation.getLocation()
                   + aLocation.getName();
        }
        return null;
    }

    private static String getReportURL(HttpServletRequest request, Integer formInstanceId)
    {
        UserValue
            userValue =
            SessionService.getUserValue(request);
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE);
        GoalActivityData
            goalActivity =
            GoalActivityData.findActiveByFormInstanceId(formInstanceId);
        FileDownloadWrapper
            goalActivityReport =
            new FileDownloadWrapper();
        goalActivityReport.setClientId(clientValue.getId());
        goalActivityReport.setUsername(userValue.getUsername());
        goalActivityReport.setFile(new File(getCustomFormReportLocation(goalActivity.getId())));
        return goalActivityReport.getDownloadURL(request);
    }
}
