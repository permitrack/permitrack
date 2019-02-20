package com.sehinc.erosioncontrol.server.inspection;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.sql.SQLHelper;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.contact.ContactValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.inspection.InspectorComparator;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.*;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.inspection.*;
import com.sehinc.erosioncontrol.value.project.ProjectBmpValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class InspectionService
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionService.class);
    public static
    String
        INSPECTION_LIST_BY_INSPECTION_DATE_ASC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByInspectionDateAsc";
    public static
    String
        INSPECTION_LIST_BY_INSPECTION_DATE_DESC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByInspectionDateDesc";
    public static
    String
        INSPECTION_LIST_BY_ENTERED_DATE_ASC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByEnteredDateAsc";
    public static
    String
        INSPECTION_LIST_BY_ENTERED_DATE_DESC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByEnteredDateDesc";
    public static
    String
        INSPECTION_LIST_BY_INSPECTOR_NAME_ASC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByInspectorNameAsc";
    public static
    String
        INSPECTION_LIST_BY_INSPECTOR_NAME_DESC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByInspectorNameDesc";
    public static
    String
        INSPECTION_LIST_BY_REASON_ASC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByReasonAsc";
    public static
    String
        INSPECTION_LIST_BY_REASON_DESC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByReasonDesc";
    public static
    String
        INSPECTION_LIST_BY_STATUS_ASC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByStatusAsc";
    public static
    String
        INSPECTION_LIST_BY_STATUS_DESC =
        "com.sehinc.erosioncontrol.db.inspection.EcInspection.inspectionListByStatusDesc";

    public InspectionService()
    {
    }

    public static List getProjectBmpValuesByInspectionTemplateId(Integer inspectionTemplateId, Integer clientId)
    {
        ArrayList
            projectBmpValueList =
            new ArrayList();
        List
            inspectionTemplateBmpList =
            EcInspectionTemplateBmp.findByInspectionTemplateId(inspectionTemplateId,
                                                               clientId);
        Iterator
            iterator =
            inspectionTemplateBmpList.iterator();
        while (iterator.hasNext())
        {
            EcInspectionTemplateBmp
                inspectionTemplateBmp =
                (EcInspectionTemplateBmp) iterator.next();
            ProjectBmpValue
                projectBmpValue =
                new ProjectBmpValue(inspectionTemplateBmp);
            projectBmpValueList.add(projectBmpValue);
        }
        return projectBmpValueList;
    }

    public static List getInspectionTemplateBmpValuesByInspectionTemplateId(Integer inspectionTemplateId, Integer clientId)
    {
        ArrayList
            inspectionTemplateBmpValueList =
            new ArrayList();
        List
            inspectionTemplateBmpList =
            EcInspectionTemplateBmp.findByInspectionTemplateId(inspectionTemplateId,
                                                               clientId);
        Iterator
            iterator =
            inspectionTemplateBmpList.iterator();
        while (iterator.hasNext())
        {
            EcInspectionTemplateBmp
                inspectionTemplateBmp =
                (EcInspectionTemplateBmp) iterator.next();
            InspectionTemplateBmpValue
                inspectionTemplateBmpValue =
                new InspectionTemplateBmpValue(inspectionTemplateBmp);
            inspectionTemplateBmpValueList.add(inspectionTemplateBmpValue);
        }
        return inspectionTemplateBmpValueList;
    }

    public static List getInspectionTemplateValueList(ClientValue clientValue)
    {
        ArrayList
            inspectionTemplateValueList =
            new ArrayList();
        List
            inspectionTemplateList =
            EcInspectionTemplate.findActiveByClientId(clientValue.getId());
        Iterator
            iterator =
            inspectionTemplateList.iterator();
        while (iterator.hasNext())
        {
            EcInspectionTemplate
                inspectionTemplate =
                (EcInspectionTemplate) iterator.next();
            InspectionTemplateValue
                inspectionTemplateValue =
                new InspectionTemplateValue(inspectionTemplate);
            inspectionTemplateValueList.add(inspectionTemplateValue);
        }
        return inspectionTemplateValueList;
    }

    public static List getInspectionActionValueList()
    {
        ArrayList
            inspectionActionValueList =
            new ArrayList();
        Iterator
            inspectionActionIterator =
            EcInspectionAction.findAllSortedByDisplaySequence()
                .iterator();
        while (inspectionActionIterator.hasNext())
        {
            EcInspectionAction
                inspectionAction =
                (EcInspectionAction) inspectionActionIterator.next();
            inspectionActionValueList.add(new InspectionActionValue(inspectionAction));
        }
        return inspectionActionValueList;
    }

    public static List getInspectionReasonValueList()
    {
        ArrayList
            inspectionReasonValueList =
            new ArrayList();
        Iterator
            inspectionReasonIterator =
            EcInspectionReason.findAllSortedById()
                .iterator();
        while (inspectionReasonIterator.hasNext())
        {
            EcInspectionReason
                inspectionReason =
                (EcInspectionReason) inspectionReasonIterator.next();
            LOG.debug("InspectionReason="
                      + inspectionReason.getName());
            inspectionReasonValueList.add(new InspectionReasonValue(inspectionReason));
        }
        return inspectionReasonValueList;
    }

    public static List getInspectionBmpStatusValueList()
    {
        ArrayList
            inspectionBmpStatusValueList =
            new ArrayList();
        Iterator
            inspectionBmpStatusIterator =
            EcInspectionBmpStatus.findAllSortedById()
                .iterator();
        while (inspectionBmpStatusIterator.hasNext())
        {
            EcInspectionBmpStatus
                inspectionBmpStatus =
                (EcInspectionBmpStatus) inspectionBmpStatusIterator.next();
            inspectionBmpStatusValueList.add(new InspectionBmpStatusValue(inspectionBmpStatus));
        }
        return inspectionBmpStatusValueList;
    }

    public static List getInspectionBmpConditionValueList()
    {
        ArrayList
            inspectionBmpConditionValueList =
            new ArrayList();
        Iterator
            inspectionBmpConditionIterator =
            EcInspectionBmpCondition.findAllSortedById()
                .iterator();
        while (inspectionBmpConditionIterator.hasNext())
        {
            EcInspectionBmpCondition
                inspectionBmpCondition =
                (EcInspectionBmpCondition) inspectionBmpConditionIterator.next();
            inspectionBmpConditionValueList.add(new InspectionBmpConditionValue(inspectionBmpCondition));
        }
        return inspectionBmpConditionValueList;
    }

    public static List getInspectionDefectBmpValueList(Integer projectId, Integer inspectionId, Date inspectionDate, Integer clientId, String username, HttpServletRequest request)
    {
        ArrayList
            inspectionDefectBmpValueList =
            new ArrayList();
        Iterator
            inspectionDefectBmpIterator =
            EcInspectionBmp.findMostRecentDefectsByProjectId(projectId,
                                                             inspectionId,
                                                             inspectionDate)
                .iterator();
        while (inspectionDefectBmpIterator.hasNext())
        {
            EcInspectionBmp
                inspectionBmp =
                (EcInspectionBmp) inspectionDefectBmpIterator.next();
            InspectionBmpValue
                inspectionBmpValue =
                new InspectionBmpValue(inspectionBmp);
            EcInspectionBmpDocument
                document =
                EcInspectionBmpDocument.findByInspectionBmpId(inspectionBmp.getId());
            if (document
                != null)
            {
                InspectionBmpDocumentValue
                    inspectionDocumentValue =
                    new InspectionBmpDocumentValue(document);
                inspectionDocumentValue.setClientId(clientId);
                inspectionDocumentValue.setUsername(username);
                inspectionDocumentValue.setDownloadURL(inspectionDocumentValue.getDownloadURL(request));
                inspectionBmpValue.setBmpDocument(inspectionDocumentValue);
            }
            inspectionBmpValue.setInspectionDate(EcInspection.getInspectionDate(inspectionBmp.getInspectionId()));
            inspectionDefectBmpValueList.add(inspectionBmpValue);
        }
        return inspectionDefectBmpValueList;
    }

    public static List getInspectionBmpValuesByInspectionId(Integer inspectionId, Integer clientId, String username, HttpServletRequest request)
    {
        ArrayList
            inspectionBmpValueList =
            new ArrayList();
        List
            inspectionBmpList =
            EcInspectionBmp.findByInspectionId(inspectionId);
        Iterator
            iterator =
            inspectionBmpList.iterator();
        while (iterator.hasNext())
        {
            EcInspectionBmp
                inspectionBmp =
                (EcInspectionBmp) iterator.next();
            InspectionBmpValue
                inspectionBmpValue =
                new InspectionBmpValue(inspectionBmp);
            EcInspectionBmpDocument
                inspectionBmpDocument =
                EcInspectionBmpDocument.findByInspectionBmpId(inspectionBmpValue.getId());
            if (inspectionBmpDocument
                != null)
            {
                InspectionBmpDocumentValue
                    inspectionDocumentValue =
                    new InspectionBmpDocumentValue(inspectionBmpDocument);
                inspectionDocumentValue.setClientId(clientId);
                inspectionDocumentValue.setUsername(username);
                inspectionDocumentValue.setDownloadURL(inspectionDocumentValue.getDownloadURL(request));
                inspectionBmpValue.setBmpDocument(inspectionDocumentValue);
            }
            inspectionBmpValueList.add(inspectionBmpValue);
        }
        return inspectionBmpValueList;
    }

    public static List getInspectionBmpMapValuesByInspectionId(String baseUrl, Integer inspectionId)
    {
        ArrayList
            inspectionBmpValueList =
            new ArrayList();
        List
            inspectionBmpList =
            EcInspectionBmp.findByInspectionId(inspectionId);
        Iterator
            iterator =
            inspectionBmpList.iterator();
        while (iterator.hasNext())
        {
            EcInspectionBmp
                inspectionBmp =
                (EcInspectionBmp) iterator.next();
            InspectionBmpValue
                inspectionBmpValue =
                new InspectionBmpValue(inspectionBmp);
            EcInspectionBmpDocument
                inspectionBmpDocument =
                EcInspectionBmpDocument.findByInspectionBmpId(inspectionBmpValue.getId());
            if (inspectionBmpDocument
                != null)
            {
                InspectionBmpDocumentValue
                    inspectionDocumentValue =
                    new InspectionBmpDocumentValue(inspectionBmpDocument);
                inspectionDocumentValue.setDownloadURL(inspectionDocumentValue.getPublicDownloadURL(baseUrl));
                inspectionBmpValue.setBmpDocument(inspectionDocumentValue);
            }
            inspectionBmpValueList.add(inspectionBmpValue);
        }
        return inspectionBmpValueList;
    }

    public static List getAllInspectionsByProjectId(Integer projectId)
    {
        ArrayList
            inspectionValueList =
            new ArrayList();
        List
            inspectionList =
            EcInspection.findAllByProjectId(projectId);
        Iterator
            iterator =
            inspectionList.iterator();
        while (iterator.hasNext())
        {
            EcInspection
                inspection =
                (EcInspection) iterator.next();
            InspectionValue
                inspectionValue =
                new InspectionValue(inspection);
            CapContact
                inspector =
                inspection.getInspector();
            inspectionValue.setInspector(inspector);
            inspectionValueList.add(inspectionValue);
        }
        return inspectionValueList;
    }

    public static List getAllMapInspectionsByProjectId(Integer clientId, Integer projectId, HttpServletRequest request)
    {
        ArrayList
            inspectionMapValueList =
            new ArrayList();
        List
            inspectionList =
            EcInspection.findActiveHistoryByProjectId(projectId);
        Iterator
            iterator =
            inspectionList.iterator();
        while (iterator.hasNext())
        {
            EcInspection
                inspection =
                (EcInspection) iterator.next();
            InspectionMapValue
                inspectionMapValue =
                new InspectionMapValue(inspection);
            CapContact
                inspector =
                inspection.getInspector();
            inspectionMapValue.setInspector(inspector);
            inspectionMapValue.setUrl(clientId,
                                      request);
            inspectionMapValueList.add(inspectionMapValue);
        }
        return inspectionMapValueList;
    }

    public static InspectionValue getInspectionsByProjectIdInspectionId(Integer projectId, Integer inspectionId, HttpServletRequest request)
    {
        List
            inspectionList =
            EcInspection.findActiveHistoryByProjectId(projectId);
        Iterator
            iterator =
            inspectionList.iterator();
        while (iterator.hasNext())
        {
            EcInspection
                inspection =
                (EcInspection) iterator.next();
            InspectionValue
                inspectionValue =
                new InspectionValue(inspection);
            CapContact
                inspector =
                inspection.getInspector();
            inspectionValue.setInspector(inspector);
            if (inspectionValue.getId()
                    .compareTo(inspectionId)
                == 0)
            {
                return inspectionValue;
            }
        }
        return null;
    }

    public static boolean hasInspectionUpdatePermission(boolean isUpdate, Integer createdByClientId, Integer updatedByClientId, String statusCode, SecurityManager securityManager)
    {
        if (!isUpdate)
        {
            return false;
        }
        //A permit authroity with update permission can update any inspection
        if (securityManager.getIsSystemAdministrator() || securityManager.getIsProjectPermitAuthority())
        {
            return true;
        }
        //if the inspection is DRAFT, the the user can update it if they created it!
        if (statusCode.equals(StatusCodeData.STATUS_CODE_INCOMPLETE))
        {
            if (!createdByClientId.equals(updatedByClientId))
            {
                return false;
            }
            return true;
        }
        //User cannot edit this inspection!
        return false;
    }

    public static List getInspectionList(String query, ProjectValue projectValue, ClientValue clientValue, UserValue userValue, SecurityManager securityManager, HttpServletRequest request)
    {
        Object[][]
            parameters =
            {
                {
                    "projectId",
                    projectValue.getId()}};
        List
            inspectionValueList =
            new ArrayList();
        Iterator
            inspectionListIterator =
            HibernateUtil.findByNamedQuery(query,
                                           parameters)
                .iterator();
        while (inspectionListIterator.hasNext())
        {
            try
            {
                EcInspection
                    inspection =
                    (EcInspection) inspectionListIterator.next();
                InspectionListValue
                    inspectionListValue =
                    new InspectionListValue(inspection);
                inspectionListValue.setReportUrl(clientValue,
                                                 userValue,
                                                 request);
                inspectionListValue.setInspectionFormUrl(clientValue,
                                                         userValue,
                                                         request);
                inspectionListValue.setInspector(new ContactValue(inspection.getInspector()
                                                                      .getId()));
                inspectionListValue.setProjectSecurityPermissionValue(ProjectService.getProjectSecurityPermissionValue(securityManager,
                                                                                                                       projectValue));
                SecurityPermissionValue
                    inspectionSecurityPermissionValue =
                    ProjectService.getInspectionSecurityPermissionValue(securityManager,
                                                                        projectValue);
                // User must have INSPECTION_UPDATE permission, if the project is FINAL they cannot update it unless they are the Permit Authority
                boolean
                    hasUpdatePerm =
                    hasInspectionUpdatePermission(inspectionSecurityPermissionValue.getIsUpdate(),
                                                  inspection.getClientId(),
                                                  clientValue.getId(),
                                                  inspection.getStatus()
                                                      .getCode(),
                                                  securityManager);
                inspectionSecurityPermissionValue.setIsUpdate(hasUpdatePerm);
                // If you can't Update you can't Delete
                inspectionSecurityPermissionValue.setIsDelete(inspectionSecurityPermissionValue.getIsDelete()
                                                              && hasUpdatePerm);
                inspectionListValue.setInspectionSecurityPermissionValue(inspectionSecurityPermissionValue);
                LOG.debug(inspectionListValue.toString());
                inspectionValueList.add(inspectionListValue);
            }
            catch (Exception e)
            {
                LOG.error("Failed to load inspection data: ["
                          + e.getMessage()
                          + "]");
            }
        }
        return inspectionValueList;
    }

    public static boolean getInspectionPermissions(ProjectValue projectValue, SecurityManager securityManager, Integer createClientId, Integer updateClientId, String statusCode)
    {
        SecurityPermissionValue
            inspectionSecurityPermissionValue =
            ProjectService.getInspectionSecurityPermissionValue(securityManager,
                                                                projectValue);
        return hasInspectionUpdatePermission(inspectionSecurityPermissionValue.getIsUpdate(),
                                             createClientId,
                                             updateClientId,
                                             statusCode,
                                             securityManager);
    }

    public static List getPrecipSourceLabelValueList(String insertValue)
    {
        Vector
            precipSourceList =
            new Vector();
        if (StringUtil.hasContent(insertValue)
            &&
            !insertValue.equalsIgnoreCase("Estimate")
            &&
            !insertValue.equalsIgnoreCase("Rain Gauge")
            &&
            !insertValue.equalsIgnoreCase("Media Report")
            &&
            !insertValue.equalsIgnoreCase("Other"))
        {
            precipSourceList.add(new LabelValueBean(insertValue,
                                                    insertValue));
        }
        precipSourceList.add(new LabelValueBean("Estimate",
                                                "Estimate"));
        precipSourceList.add(new LabelValueBean("Rain Gauge",
                                                "Rain Gauge"));
        precipSourceList.add(new LabelValueBean("Media Report",
                                                "Media Report"));
        precipSourceList.add(new LabelValueBean("Other",
                                                "Other"));
        return precipSourceList;
    }

    public static EcInspection getLastInspectionByProjectId(Integer projectId)
    {
        List
            inspectionList =
            EcInspection.findMostRecentByProjectId(projectId);
        Iterator
            iterator =
            inspectionList.iterator();
        while (iterator.hasNext())
        {
            return (EcInspection) iterator.next();
        }
        return null;
    }

    public static Date getLastInspectionDateByProjectId(Integer projectId)
    {
        EcInspection
            inspection =
            getLastInspectionByProjectId(projectId);
        if (inspection
            != null)
        {
            return inspection.getInspectionDate();
        }
        return null;
    }

    private static List removeDuplicates(List list)
    {
        HashSet
            set =
            new HashSet(list);
        ArrayList
            newList =
            new ArrayList(set);
        Collections.sort(newList,
                         new InspectorComparator());
        return newList;
    }

    public static List getInspectorsForProject(ProjectValue projectValue, Integer clientId)
    {
        ArrayList
            authList =
            getProjectClientList(projectValue,
                                 clientId);
        ArrayList
            allowedInspectors =
            getInspectorsFromClients(clientId,
                                     authList);
        return allowedInspectors;
    }

    public static List getContactsForProject(ProjectValue projectValue)
    {
        ArrayList
            authList =
            getProjectClientList(projectValue,
                                 null);
        List
            allowedInspectors =
            getContactsFromClients(authList);
        return removeDuplicates(allowedInspectors);
    }

    private static ArrayList getProjectClientList(ProjectValue projectValue, Integer clientId)
    {
        ArrayList
            authList =
            new ArrayList();
        ClientData
            permitAuth =
            new ClientData(projectValue.getPermitAuthorityClientId());
        permitAuth.load();
        ClientData
            permittee =
            new ClientData(projectValue.getPermittedClientId());
        permittee.load();
        ClientData
            authInsp =
            null;
        if (projectValue.getAuthorizedInspectorClientId()
            != null)
        {
            authInsp =
                new ClientData(projectValue.getAuthorizedInspectorClientId());
            authInsp.load();
        }
        if (clientId
            == null)
        { // Get contacts for all clients on the project
            if (permitAuth
                != null)
            {
                authList.add(new ClientValue(permitAuth));
            }
        }
        else
        {
            // if client id is the parent client, load auth contacts
            if (permitAuth
                != null)
            {
                if (clientId.intValue()
                    == permitAuth.getId()
                    .intValue())
                {
                    authList.add(new ClientValue(permitAuth));
                }
            }
        }
        if (permittee
            != null)
        {
            authList.add(new ClientValue(permittee));
        }
        if (authInsp
            != null)
        {
            authList.add(new ClientValue(authInsp));
        }
        return authList;
    }

    private static ArrayList getInspectorsFromClients(Integer clientId, ArrayList clients)
    {
        ArrayList
            finalInspList =
            new ArrayList();
        for (Object o : clients)
        {
            ClientValue
                refClient =
                (ClientValue) o;
            ContactValueSQLHelper
                contactValueSQLHelper =
                new ContactValueSQLHelper();
            StringBuffer
                buffer =
                new StringBuffer();
            buffer.append("EXEC sp_clients_inspectors @clientId = "
                          + clientId);
            buffer.append(", @refClientId = "
                          + refClient.getId());
            buffer.append(", @statusCd = "
                          + StatusCodeData.STATUS_CODE_ACTIVE);
            SQLHelperPreparedStatement
                statement =
                new SQLHelperPreparedStatement(buffer.toString());
            List
                inspectors =
                SQLHelper.retrieveValueList(statement,
                                            contactValueSQLHelper);
            for (Object oo : inspectors)
            {
                ContactValue
                    inspector =
                    (ContactValue) oo;
                finalInspList.add(inspector.getId());
            }
        }
        // Remove duplicate ids
        HashSet
            set =
            new HashSet(finalInspList);
        ArrayList
            newList =
            new ArrayList(set);
        finalInspList.clear();
        for (Object ooo : newList)
        {   // load contact information into the list
            Integer
                contactId =
                (Integer) ooo;
            CapContact
                contact =
                new CapContact(contactId);
            contact.load();
            finalInspList.add(contact);
        }
        Collections.sort(finalInspList,
                         new InspectorComparator());
        return finalInspList;
    }

    private static ArrayList getContactsFromClients(ArrayList clients)
    {
        ArrayList
            finalContactList =
            new ArrayList();
        List
            tempContactList;
        if (clients.size()
            > 0)
        {
            Iterator
                aci =
                clients.iterator();
            while (aci.hasNext())
            {
                ClientValue
                    allowedClient =
                    (ClientValue) aci.next();
                tempContactList =
                    CapContact.findByClientContactListAndStatus(allowedClient.getId(),
                                                                StatusCodeData.STATUS_CODE_ACTIVE);
                if (tempContactList.size()
                    > 0)
                {
                    Iterator
                        tcli =
                        tempContactList.iterator();
                    while (tcli.hasNext())
                    {
                        CapContact
                            contact =
                            (CapContact) tcli.next();
                        if (!finalContactList.contains(contact))
                        {
                            finalContactList.add(contact);
                        }
                    }
                }
            }
        }
        return finalContactList;
    }

    public static String displayTimeString(Integer hours, Integer minutes, String timePeriod)
    {
        String
            s =
            null;
        if (hours
            != null
            && minutes
               != null
            && timePeriod
               != null)
        {
            if (hours
                < 10)
            {
                s =
                    "0"
                    + hours;
            }
            else
            {
                s =
                    hours.toString();
            }
            if (minutes
                < 10)
            {
                s =
                    s
                    + ":0"
                    + minutes;
            }
            else
            {
                s =
                    s
                    + ":"
                    + minutes;
            }
            s =
                s
                + " "
                + timePeriod;
        }
        return s;
    }
}