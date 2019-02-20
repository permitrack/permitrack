package com.sehinc.stormwater.action.plan;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.action.forms.CustomFormReport;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.forms.ElementData;
import com.sehinc.common.db.forms.ElementValueData;
import com.sehinc.common.db.forms.FormData;
import com.sehinc.common.db.forms.FormInstanceData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.forms.CustomForm;
import com.sehinc.common.forms.ElementList;
import com.sehinc.common.forms.FileList;
import com.sehinc.common.forms.IntList;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.BaseAction;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.report.SWReportParameter;
import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.db.plan.GoalActivityFormInstanceData;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

public class FormEditAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FormEditAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        CustomForm
            cf =
            (CustomForm) form;
        GoalActivityData
            goalActivityData;
        Integer
            formId =
            cf.getFormId();
        Integer
            formInstanceId =
            cf.getFormInstanceId();
        ElementList
            elementsList =
            cf.getElementList();
        FileList
            fileElements =
            cf.getFileList();
        IntList
            deleteList =
            cf.getDeleteList();
        IntList
            elementElementIds =
            cf.getElementIndexes();
        IntList
            fileElementIds =
            cf.getFileIndexes();
        Integer
            clientId =
            cf.getClientId();
        Integer
            planId =
            cf.getPlanId();
        Integer
            goalActivityId =
            cf.getGoalActivityId();
        Hashtable
            valuesHash =
            elementsList.getAllElements();
        Hashtable
            indexHash =
            elementElementIds.getAllInts();
        LOG.debug("doAction(): Hashes are initialized. goalActivityId is "
                  + goalActivityId);
        if ((goalActivityId
             != null)
            && (!goalActivityId.equals(new Integer(0))))
        {
            LOG.debug("doAction(): Editing formInstanceId "
                      + formInstanceId.toString());
            LOG.debug("doAction(): Editing goalActivityId "
                      + goalActivityId);
            goalActivityData =
                new GoalActivityData();
            goalActivityData.setId(goalActivityId);
            goalActivityData.load();
            goalActivityData.setName(cf.getActivityName());
            goalActivityData.setDescription(cf.getActivityDesc());
            Date
                activityDate =
                DateUtil.parseDate(cf.getActivityDate());
            goalActivityData.setActivityDate(activityDate);
            UserValue
                userValue =
                getUserValue(request);
            goalActivityData.save(userValue);
        }
        else
        {
            LOG.debug("doAction(): Creating a new formInstance with formId "
                      + formId.toString());
            FormData
                formData =
                new FormData(formId);
            formData.load();
            LOG.debug("doAction(): FormData retrieved.");
            FormInstanceData
                formInstance =
                new FormInstanceData();
            formInstance.setForm(formData);
            formInstance.save();
            formInstanceId =
                formInstance.getId();
            LOG.debug("doAction(): FormInstanceData created. New formInstanceId is: "
                      + formInstanceId);
            goalActivityData =
                new GoalActivityData();
            goalActivityData.setGoalId(cf.getGoalId());
            goalActivityData.setName(cf.getActivityName());
            goalActivityData.setDescription(cf.getActivityDesc());
            Date
                activityDate =
                DateUtil.parseDate(cf.getActivityDate());
            goalActivityData.setActivityDate(activityDate);
            goalActivityData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            goalActivityData.setGoalActivityFormId(cf.getGoalActivityFormId());
            UserValue
                userValue =
                getUserValue(request);
            goalActivityId =
                goalActivityData.save(userValue);
            LOG.debug("doAction(): GoalActivity created.");
            GoalActivityFormInstanceData
                goalActivityFormInstance =
                new GoalActivityFormInstanceData();
            goalActivityFormInstance.setFormInstance(formInstance);
            goalActivityFormInstance.setGoalActivityId(goalActivityData.getId());
            goalActivityFormInstance.save();
            LOG.debug("doAction(): GoalActivityFormInstanceData created.  New Id is "
                      + goalActivityFormInstance.getId());
        }
        setSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                            new GoalActivityValue(goalActivityData), request);
        request.setAttribute(RequestKeys.FORM_INSTANCE_ID,
                             formInstanceId);
        saveActivityInformation(formInstanceId,
                                cf.getActivityNameElementId(),
                                cf.getActivityName());
        saveActivityInformation(formInstanceId,
                                cf.getActivityDateElementId(),
                                cf.getActivityDate());
        saveActivityInformation(formInstanceId,
                                cf.getActivityDescElementId(),
                                cf.getActivityDesc());
        for (
            int
                i =
                0; i
                   < 150; i++)
        {
            if (valuesHash.containsKey(new Integer(i)))
            {
                storeElementValue(new Integer(i),
                                  formInstanceId,
                                  valuesHash,
                                  indexHash);
            }
            else
            {
            }
        }
        Hashtable
            fileValues =
            fileElements.getAllFiles();
        Hashtable
            fileIndexes =
            fileElementIds.getAllInts();
        Hashtable
            deleteValues =
            deleteList.getAllInts();
        FormFile
            fileToStore;
        for (
            int
                i =
                0; i
                   < 25; i++)
        {
            if (fileValues.containsKey(new Integer(i)))
            {
                LOG.debug("doAction(): fileValues contains "
                          + i);
                fileToStore =
                    (FormFile) fileValues.get(new Integer(i));
                Integer
                    elementId =
                    (Integer) fileIndexes.get(new Integer(i));
                LOG.debug("doAction(): File elementId retrieved: "
                          + elementId.toString());
                Collection
                    elementValueCollection =
                    getElementValue(formInstanceId,
                                    elementId);
                Iterator
                    ei =
                    elementValueCollection.iterator();
                String
                    fileLocation =
                    null;
                boolean
                    saveFlag =
                    false;
                LOG.debug("doAction(): File iterator created.");
                if (fileToStore
                    != null
                    && !fileToStore.getFileName()
                    .equals(""))
                {
                    LOG.debug("doAction(): file name is filled in, replace/save.");
                    InputStream
                        stream =
                        fileToStore.getInputStream();
                    String
                        aFileLocation =
                        ApplicationProperties.getProperty("base.document.directory")
                        +
                        "/client"
                        + clientId
                        +
                        "/"
                        + ApplicationProperties.getProperty("application.stormwater")
                        +
                        "/plan"
                        + planId
                        +
                        "/activity"
                        + goalActivityId;
                    File
                        aOutputDir =
                        new File(aFileLocation);
                    aOutputDir.mkdirs();
                    File
                        aNewFile =
                        new File(aFileLocation,
                                 fileToStore.getFileName());
                    OutputStream
                        bos =
                        new FileOutputStream(aNewFile);
                    int
                        bytesRead;
                    byte[]
                        buffer =
                        new byte[8192];
                    while ((
                               bytesRead =
                                   stream.read(buffer,
                                               0,
                                               8192))
                           != -1)
                    {
                        bos.write(buffer,
                                  0,
                                  bytesRead);
                    }
                    bos.close();
                    stream.close();
                    LOG.debug("doAction(): File name given in file input.  Save file.");
                    fileLocation =
                        aNewFile.getParent()
                        + System.getProperty("file.separator")
                        + aNewFile.getName();
                    saveFlag =
                        true;
                }
                else if (fileToStore
                         != null
                         && fileToStore.getFileName()
                    .equals(""))
                {
                    LOG.debug("doAction(): if file name is blank and delete is checked, remove.");
                    Integer
                        deleteFlag =
                        (Integer) deleteValues.get(new Integer(i));
                    LOG.debug("doAction(): Int is: "
                              + deleteFlag);
                    if (deleteFlag
                        != null)
                    {
                        if (deleteFlag.toString()
                            .equals("1"))
                        {
                            LOG.debug("doAction(): File delete checkbox was checked.  Save a blank file name.");
                            saveFlag =
                                true;
                            fileLocation =
                                "";
                        }
                    }
                    else
                    {
                        saveFlag =
                            false;
                    }
                }
                else
                {
                    saveFlag =
                        false;
                }
                if (saveFlag)
                {
                    LOG.debug("doAction(): Saving the file location to the database.");
                    if (ei.hasNext())
                    {
                        ElementValueData
                            elementValueData =
                            (ElementValueData) ei.next();
                        LOG.debug("doAction(): Saving the fileLocation: "
                                  + fileLocation);
                        elementValueData.setElementValue(fileLocation);
                        elementValueData.update();
                    }
                    else
                    {
                        ElementValueData
                            newElementValue =
                            new ElementValueData();
                        LOG.debug("doAction(): Creating new element value.  Value: "
                                  + fileLocation);
                        newElementValue.setElementValue(fileLocation);
                        newElementValue.setElementId(elementId);
                        newElementValue.setFormInstanceId(formInstanceId);
                        newElementValue.save();
                    }
                }
            }
        }
        try
        {
            LOG.debug("doAction(): Calling createCustomFormReport(request, response, "
                      + clientId
                      + ", "
                      + planId
                      + ", "
                      + formInstanceId
                      + ", "
                      + goalActivityId
                      + ")");
            createCustomFormReport(request,
                                   clientId,
                                   planId,
                                   formInstanceId,
                                   goalActivityId);
        }
        catch (Exception e)
        {
            LOG.error("Error creating custom inspection form report",
                      e);
            addError(new ActionMessage("custom.form.create.report.error"), request);
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "a"
                                 + goalActivityData.getId(),
                                 true);
    }

    private static void saveActivityInformation(Integer formInstanceId, Integer elementId, String value)
    {
        Collection
            elementValueCollection =
            getElementValue(formInstanceId,
                            elementId);
        Iterator
            ei =
            elementValueCollection.iterator();
        if (ei.hasNext())
        {
            ElementValueData
                elementValueData =
                (ElementValueData) ei.next();
            elementValueData.setElementValue(value);
            elementValueData.update();
        }
        else
        {
            if (value
                != null
                && !value.equals(""))
            {
                ElementValueData
                    newElementValue =
                    new ElementValueData();
                LOG.debug("storeElementValue(): Creating new element value.");
                newElementValue.setElementValue(value);
                newElementValue.setElementId(elementId);
                newElementValue.setFormInstanceId(formInstanceId);
                newElementValue.save();
            }
            else
            {
            }
        }
    }

    private static void storeElementValue(Integer i, Integer formInstanceId, Hashtable valuesHash, Hashtable indexHash)
    {
        String
            finalizedParentValue;
        String
            parentValueFromJsp =
            (String) valuesHash.get(i);
        LOG.debug("storeElementValue(): Retrieved valueToStore value of: "
                  + parentValueFromJsp);
        Integer
            elementId =
            (Integer) indexHash.get(i);
        ElementData
            elementData =
            new ElementData(elementId);
        elementData.load();
        if (elementData.getIsChild()
            .equals("N"))
        {
            if (elementData.getChildElementId()
                != null)
            {
                if (parentValueFromJsp.equals("Other...")
                    && elementData.getAllowOther()
                    .equals("Y"))
                {
                    LOG.debug("storeElementValue(): Child is an OTHER.");
                    Integer
                        childIndex =
                        (Integer) getByValue(indexHash,
                                             elementData.getChildElementId());
                    if (childIndex
                        != null)
                    {
                        String
                            otherValue =
                            (String) valuesHash.get(childIndex);
                        LOG.debug("storeElementValue(): OTHER finalized parent value of: "
                                  + otherValue);
                        finalizedParentValue =
                            otherValue;
                    }
                    else
                    {
                        finalizedParentValue =
                            "";
                    }
                }
                else if (!parentValueFromJsp.equals("Other...")
                         && elementData.getAllowOther()
                    .equals("Y"))
                {
                    LOG.debug("storeElementValue(): Parent allows OTHER but has not selected it.  Value is: "
                              + parentValueFromJsp);
                    finalizedParentValue =
                        parentValueFromJsp;
                }
                else
                {
                    LOG.debug("storeElementValue(): Storing parent and child seperately.  Parent value: "
                              + parentValueFromJsp);
                    finalizedParentValue =
                        parentValueFromJsp;
                    Collection
                        childValueCollection =
                        getElementValue(formInstanceId,
                                        elementData.getChildElementId());
                    Iterator
                        ci =
                        childValueCollection.iterator();
                    Integer
                        childIndex =
                        (Integer) getByValue(indexHash,
                                             elementData.getChildElementId());
                    String
                        childValue =
                        "";
                    if (childIndex
                        != null)
                    {
                        childValue =
                            (String) valuesHash.get(childIndex);
                    }
                    if (ci.hasNext())
                    {
                        ElementValueData
                            childValueData =
                            (ElementValueData) ci.next();
                        LOG.debug("storeElementValue(): Updating child value as: "
                                  + childValue);
                        childValueData.setElementValue(childValue);
                        childValueData.update();
                    }
                    else
                    {
                        if (childValue
                            != null
                            && !childValue.equals(""))
                        {
                            ElementValueData
                                newChildElementValue =
                                new ElementValueData();
                            LOG.debug("storeElementValue(): Creating new child element value. ");
                            newChildElementValue.setElementValue(childValue);
                            newChildElementValue.setElementId(elementData.getChildElementId());
                            newChildElementValue.setFormInstanceId(formInstanceId);
                            newChildElementValue.save();
                        }
                        else
                        {
                        }
                    }
                }
            }
            else
            {
                finalizedParentValue =
                    parentValueFromJsp;
            }
            saveActivityInformation(formInstanceId,
                                    elementId,
                                    finalizedParentValue);
        }
        else
        {
        }
    }

    private static Object getByValue(Hashtable hash, Integer childIndex)
    {
        LOG.debug("getByValue(): checking for childIndex "
                  + childIndex.toString());
        if (hash.containsValue(childIndex))
        {
            Integer
                possibleChildIndex;
            for (
                int
                    i =
                    0; i
                       < 150; i++)
            {
                if (hash.containsKey(new Integer(i)))
                {
                    possibleChildIndex =
                        (Integer) hash.get(new Integer(i));
                    LOG.debug("getByValue(): Hash does contain the key. Checking index "
                              + i
                              + " value is "
                              + possibleChildIndex.toString());
                    if (possibleChildIndex.toString()
                        .equals(childIndex.toString()))
                    {
                        LOG.debug("getByValue(): Child elementId found as: "
                                  + i);
                        return new Integer(i);
                    }
                }
            }
            LOG.debug("getByValue(): Looped through but did not find the value.");
            return null;
        }
        else
        {
            LOG.debug("getByValue(): Value not found in hash");
            return null;
        }
    }

/*
    protected void finalizeAction(HttpServletRequest arg0)
        throws Exception
    {
    }
*/

    private static Collection getElementValue(Integer formInstanceId, Integer elementId)
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

    private static void createCustomFormReport(HttpServletRequest request, Integer clientId, Integer planId, Integer formInstanceId, Integer goalActivityId)
        throws Exception
    {
        UserValue
            userValue =
            SessionService.getUserValue(request);
        File
            outputFile =
            new File(ApplicationProperties.getProperty("base.document.directory")
                     + "/client"
                     + clientId
                     + "/"
                     + ApplicationProperties.getProperty("application.stormwater")
                     + "/plan"
                     + planId
                     + "/activity"
                     + goalActivityId
                     + "/"
                     + CommonConstants.CUSTOM_FORM_INSPECTION_PDF);
        CustomFormReport
            customFormReport =
            new CustomFormReport();
        customFormReport.setReportParameter(SWReportParameter.FORM_INSTANCE_ID,
                                            formInstanceId);
        customFormReport.setReportParameter(SWReportParameter.CLIENT_ID,
                                            clientId);
        PDFReportRunner
            pdfReportRunner =
            new PDFReportRunner(customFormReport);
        pdfReportRunner.runToFile(request,
                                  outputFile);
        GoalActivityFileLocationData
            fileLocation =
            GoalActivityFileLocationData.findUniqueByGoalActivityIdAndName(goalActivityId,
                                                                           outputFile.getName());
        if (fileLocation
            == null)
        {
            fileLocation =
                new GoalActivityFileLocationData();
            fileLocation.setGoalActivityId(goalActivityId);
            fileLocation.setName(outputFile.getName());
            fileLocation.setLocation(outputFile.getParent()
                                     + System.getProperty("file.separator"));
            fileLocation.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            fileLocation.insert(userValue);
        }
    }
}
