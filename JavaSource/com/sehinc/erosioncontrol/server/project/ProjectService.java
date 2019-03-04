package com.sehinc.erosioncontrol.server.project;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.sql.SQLHelper;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.project.ProjectContactValueComparator;
import com.sehinc.erosioncontrol.action.project.ProjectListItem;
import com.sehinc.erosioncontrol.db.code.CodeData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.project.*;
import com.sehinc.erosioncontrol.db.user.EcUserPreferences;
import com.sehinc.erosioncontrol.value.project.*;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class ProjectService
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectService.class);
    public static
    String
        FIND_PROJECTS_BY_OWNER =
        "com.sehinc.erosioncontrol.value.project.ProjectValue.byOwnerClientId";
    public static
    String
        FIND_ALL_AUTHROIZED_PROJECTS =
        "com.sehinc.erosioncontrol.db.project.EcProject.allAuthorizedProjectsByClient";
    public static
    String
        PROJECT_LIST_BY_PROJECT_NAME_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByProjectNameAsc";
    public static
    String
        PROJECT_LIST_BY_PROJECT_NAME_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByProjectNameDesc";
    public static
    String
        PROJECT_LIST_BY_PROJECT_TYPE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByProjectTypeAsc";
    public static
    String
        PROJECT_LIST_BY_PROJECT_TYPE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByProjectTypeDesc";
    public static
    String
        PROJECT_LIST_BY_ZONE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByZoneAsc";
    public static
    String
        PROJECT_LIST_BY_ZONE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByZoneDesc";
    public static
    String
        PROJECT_LIST_BY_PERMIT_NUMBER_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByPermitNumberAsc";
    public static
    String
        PROJECT_LIST_BY_PERMIT_NUMBER_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByPermitNumberDesc";
    public static
    String
        PROJECT_LIST_BY_PERMIT_AUTHORITY_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByPermitAuthorityAsc";
    public static
    String
        PROJECT_LIST_BY_PERMIT_AUTHORITY_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByPermitAuthorityDesc";
    public static
    String
        PROJECT_LIST_BY_PERMITTED_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByPermittedAsc";
    public static
    String
        PROJECT_LIST_BY_PERMITTED_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByPermittedDesc";
    public static
    String
        PROJECT_LIST_BY_AUTHORIZED_INSPECTOR_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByAuthorizedInspectorAsc";
    public static
    String
        PROJECT_LIST_BY_AUTHORIZED_INSPECTOR_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByAuthorizedInspectorDesc";
    public static
    String
        PROJECT_LIST_BY_STATUS_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByStatusAsc";
    public static
    String
        PROJECT_LIST_BY_STATUS_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByStatusDesc";
    public static
    String
        PROJECT_LIST_BY_ADDRESS_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByAddressAsc";
    public static
    String
        PROJECT_LIST_BY_ADDRESS_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByAddressDesc";
    public static
    String
        PROJECT_LIST_BY_LAST_INSPECTION_DATE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByLastInspectionDateAsc";
    public static
    String
        PROJECT_LIST_BY_LAST_INSPECTION_DATE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByLastInspectionDateDesc";
    public static
    String
        PROJECT_LIST_BY_START_DATE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByStartDateAsc";
    public static
    String
        PROJECT_LIST_BY_START_DATE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByStartDateDesc";
    public static
    String
        PROJECT_LIST_BY_EFFECTIVE_DATE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByEffectiveDateAsc";
    public static
    String
        PROJECT_LIST_BY_EFFECTIVE_DATE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByEffectiveDateDesc";
    public static
    String
        PROJECT_LIST_BY_SEED_DATE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListBySeedDateAsc";
    public static
    String
        PROJECT_LIST_BY_SEED_DATE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListBySeedDateDesc";
    public static
    String
        PROJECT_LIST_BY_TOTAL_SITE_AREA_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByTotalSiteAreaAsc";
    public static
    String
        PROJECT_LIST_BY_TOTAL_SITE_AREA_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByTotalSiteAreaDesc";
    public static
    String
        PROJECT_LIST_BY_DISTURBED_AREA_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByDisturbedAreaAsc";
    public static
    String
        PROJECT_LIST_BY_DISTURBED_AREA_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByDisturbedAreaDesc";
    public static
    String
        PROJECT_LIST_BY_NEW_IMPERVIOUS_AREA_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByNewImperviousAreaAsc";
    public static
    String
        PROJECT_LIST_BY_NEW_IMPERVIOUS_AREA_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByNewImperviousAreaDesc";
    public static
    String
        PROJECT_LIST_BY_CITY_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByCityAsc";
    public static
    String
        PROJECT_LIST_BY_CITY_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByCityDesc";
    public static
    String
        PROJECT_LIST_BY_STATE_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByStateAsc";
    public static
    String
        PROJECT_LIST_BY_STATE_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByStateDesc";
    public static
    String
        PROJECT_LIST_BY_ZIP_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByZipAsc";
    public static
    String
        PROJECT_LIST_BY_ZIP_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByZipDesc";
    public static
    String
        PROJECT_LIST_BY_RFA_NUMBER_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByRfaAsc";
    public static
    String
        PROJECT_LIST_BY_RFA_NUMBER_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByRfaDesc";
    public static
    String
        PROJECT_LIST_BY_BLOCK_NUMBER_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByBlockAsc";
    public static
    String
        PROJECT_LIST_BY_BLOCK_NUMBER_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByBlockDesc";
    public static
    String
        PROJECT_LIST_BY_LOT_NUMBER_ASC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByLotAsc";
    public static
    String
        PROJECT_LIST_BY_LOT_NUMBER_DESC =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectListByLotDesc";
    public static
    String
        PROJECT_MAP_LIST_BY_OWNER =
        "com.sehinc.erosioncontrol.db.project.EcProject.projectMapListByOwner";
    public static
    String
        PROJECT_CONTACT_TYPE_BY_CLIENT_ID =
        "com.sehinc.erosioncontrol.db.project.EcProjectContactType.byClientId";
    public static
    String
        PROJECT_CONTACT_TYPE_INTERNAL_BY_CODE =
        "com.sehinc.erosioncontrol.db.project.EcProjectContactType.internalByCode";
    public static
    String
        PROJECT_CONTACT_BY_INTERNAL_PROJECT_CONTACT_TYPE_CODE =
        "com.sehinc.erosioncontrol.db.project.EcProjectContact.byInternalProjectContactTypeCode";
    public static
    String
        PROJECT_CONTACT_TYPE_BY_CODE =
        "com.sehinc.erosioncontrol.db.project.EcProjectContact.byCode";
    public static
    String
        PROJECT_CONTACT_BY_PROJECT_ID =
        "com.sehinc.erosioncontrol.db.project.EcProjectContact.byProjectId";
    public static
    String
        PROJECT_CONTACT_ALL_BY_PROJECT_ID =
        "com.sehinc.erosioncontrol.db.project.EcProjectContact.allByProjectId";

    public ProjectService()
    {
    }

    public static LabelValueBean getProjectLabelValueBean(ProjectValue projectValue)
    {
        String
            label =
            projectValue.getName();
        String
            value =
            projectValue.getId()
                .toString();
        return new LabelValueBean(label,
                                  value);
    }

    public static List getProjectOwnerValuesList(ClientValue clientValue)
    {
        Object[][]
            parameters =
            {
                {
                    "ownerClientId",
                    clientValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_PROJECTS_BY_OWNER,
                                              parameters);
    }

    public static List getProjectOwnerLabelValueList(ClientValue clientValue)
    {
        List
            results =
            new ArrayList();
        Iterator
            values =
            getProjectOwnerValuesList(clientValue).iterator();
        while (values.hasNext())
        {
            ProjectValue
                projectValue =
                (ProjectValue) values.next();
            results.add(getProjectLabelValueBean(projectValue));
        }
        return results;
    }

    public static List getProjectTypeValueList(ClientValue clientValue)
    {
        ArrayList
            projectTypeValueList =
            new ArrayList();
        Iterator
            projectTypeValueIterator =
            EcProjectType.findByClientId(clientValue.getId())
                .iterator();
        while (projectTypeValueIterator.hasNext())
        {
            ProjectTypeValue
                projectTypeValue =
                new ProjectTypeValue((EcProjectType) projectTypeValueIterator.next());
            projectTypeValueList.add(projectTypeValue);
        }
        return projectTypeValueList;
    }

    public static List getProjectTypeLabelValueList(ClientValue clientValue)
    {
        ArrayList
            projectTypeLabelValueList =
            new ArrayList();
        Iterator
            projectTypeValueIterator =
            EcProjectType.findByClientId(clientValue.getId())
                .iterator();
        while (projectTypeValueIterator.hasNext())
        {
            ProjectTypeValue
                projectTypeValue =
                new ProjectTypeValue((EcProjectType) projectTypeValueIterator.next());
            projectTypeLabelValueList.add(new LabelValueBean(projectTypeValue.getName(),
                                                             projectTypeValue.getId()
                                                                 .toString()));
        }
        return projectTypeLabelValueList;
    }

    public static String getProjectTypeName(Integer projectTypeId)
    {
        EcProjectType
            projectType =
            EcProjectType.find(projectTypeId);
        return projectType.getName();
    }

    public static String getProjectTypeLongName(Integer projectTypeId)
    {
        EcProjectType
            projectType =
            EcProjectType.find(projectTypeId);
        return projectType.getName()
               + " - "
               + projectType.getDescription();
    }

    public static List getProjectContactValueList(ProjectValue projectValue, boolean isInternal)
    {
        Object[][]
            parameters =
            {
                {
                    "projectId",
                    projectValue.getId()},
                {
                    "isInternal",
                    new Boolean(isInternal)},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return getProjectContactValueList(parameters,
                                          PROJECT_CONTACT_BY_PROJECT_ID);
    }

    public static List getProjectContactValueList(Integer projectId, boolean isInternal)
    {
        Object[][]
            parameters =
            {
                {
                    "projectId",
                    projectId},
                {
                    "isInternal",
                    new Boolean(isInternal)},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return getProjectContactValueList(parameters,
                                          PROJECT_CONTACT_BY_PROJECT_ID);
    }

    public static List getProjectContactValueListAll(ProjectValue projectValue)
    {
        Object[][]
            parameters =
            {
                {
                    "projectId",
                    projectValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return getProjectContactValueList(parameters,
                                          PROJECT_CONTACT_ALL_BY_PROJECT_ID);
    }

    public static List getProjectContactValueListAll(Integer projectId)
    {
        Object[][]
            parameters =
            {
                {
                    "projectId",
                    projectId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return getProjectContactValueList(parameters,
                                          PROJECT_CONTACT_ALL_BY_PROJECT_ID);
    }

    public static List getProjectContactValueList(Object[][] parameters, String query)
    {
        TreeSet
            projectContactValueList =
            new TreeSet(new ProjectContactValueComparator());
        Iterator
            projectContactValueIterator =
            HibernateUtil.findByNamedQuery(query,
                                           parameters)
                .iterator();
        while (projectContactValueIterator.hasNext())
        {
            LOG.debug("before creating ProjectContactValue");
            ProjectContactValue
                projectContactValue =
                new ProjectContactValue((EcProjectContact) projectContactValueIterator.next());
            if (projectContactValue.getOrgRefClientId()
                != null
                && projectContactValue.getOrgRefClientId()
                       .intValue()
                   > 0)
            {
                CapContactOrganization
                    contactOrg =
                    CapContactOrganization.findByClientId(projectContactValue.getOrgRefClientId());
                if (contactOrg
                    != null)
                {
                    projectContactValue.setOrganizationName(contactOrg.getName());
                }
            }
            projectContactValueList.add(projectContactValue);
        }
        return new ArrayList(projectContactValueList);
    }

    public static synchronized void setDefaultProjectContactTypes(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId}};
        List
            projectContactTypeList =
            HibernateUtil.findByNamedQuery(PROJECT_CONTACT_TYPE_BY_CLIENT_ID,
                                           parameters);
        // If the client does not have any values, then we need to give then a default set of values
        // The default set is all clientId = 0 entries with isInternal set to false.
        if ((projectContactTypeList
             == null
             || projectContactTypeList.size()
                == 0)
            && clientId.intValue()
               != 0)
        {
            parameters[0][1] =
                new Integer(0);
            Iterator
                defaultProjectContactTypeIterator =
                HibernateUtil.findByNamedQuery(PROJECT_CONTACT_TYPE_BY_CLIENT_ID,
                                               parameters)
                    .iterator();
            while (defaultProjectContactTypeIterator.hasNext())
            {
                EcProjectContactType
                    projectContactType =
                    (EcProjectContactType) defaultProjectContactTypeIterator.next();
                EcProjectContactType
                    newProjectContactType =
                    new EcProjectContactType();
                newProjectContactType.setClientId(clientId);
                newProjectContactType.setCode(projectContactType.getCode());
                newProjectContactType.setDescription(projectContactType.getDescription());
                newProjectContactType.setName(projectContactType.getName());
                newProjectContactType.setSequence(projectContactType.getSequence());
                newProjectContactType.setIsInternal(false);
                newProjectContactType.save();
            }
        }
    }

    public static List getProjectContactTypeValueListByClientId(Integer clientId)
    {
        ArrayList
            projectContactTypeValueList =
            new ArrayList();
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId}};
        List
            projectContactTypeList =
            HibernateUtil.findByNamedQuery(PROJECT_CONTACT_TYPE_BY_CLIENT_ID,
                                           parameters);
        // If the client does not have any values, then we need to give then a default set of values
        // The default set is all clientId = 0 entries with isInternal set to false.
        if ((projectContactTypeList
             == null
             || projectContactTypeList.size()
                == 0)
            && clientId.intValue()
               != 0)
        {
            setDefaultProjectContactTypes(clientId);
            projectContactTypeList =
                HibernateUtil.findByNamedQuery(PROJECT_CONTACT_TYPE_BY_CLIENT_ID,
                                               parameters);
        }
        Iterator
            projectContactTypeIterator =
            projectContactTypeList.iterator();
        while (projectContactTypeIterator.hasNext())
        {
            projectContactTypeValueList.add(new ProjectContactTypeValue((EcProjectContactType) projectContactTypeIterator.next()));
        }
        return projectContactTypeValueList;
    }

    public static EcProjectContactType getProjectContactTypeByCode(String code)
    {
        Object[][]
            parameters =
            {
                {
                    "code",
                    code}};
        Iterator
            projectContactTypeIterator =
            HibernateUtil.findByNamedQuery(PROJECT_CONTACT_TYPE_INTERNAL_BY_CODE,
                                           parameters)
                .iterator();
        while (projectContactTypeIterator.hasNext())
        {
            return (EcProjectContactType) projectContactTypeIterator.next();
        }
        return null;
    }

    public static EcProjectContactType getProjectContactTypeByClientCode(Integer clientId, String code)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "code",
                    code}};
        Iterator
            projectContactTypeIterator =
            HibernateUtil.findByNamedQuery(PROJECT_CONTACT_TYPE_BY_CODE,
                                           parameters)
                .iterator();
        while (projectContactTypeIterator.hasNext())
        {
            return (EcProjectContactType) projectContactTypeIterator.next();
        }
        return (EcProjectContactType) HibernateUtil.load(EcProjectContactType.class,
                                                         new Integer(0));
    }

    public static ProjectContactValue getProjectContactValueByProjectContactTypeCode(Integer projectId, String code)
    {
        Object[][]
            parameters =
            {
                {
                    "projectId",
                    projectId},
                {
                    "code",
                    code},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        Iterator
            projectContactIterator =
            HibernateUtil.findByNamedQuery(PROJECT_CONTACT_BY_INTERNAL_PROJECT_CONTACT_TYPE_CODE,
                                           parameters)
                .iterator();
        while (projectContactIterator.hasNext())
        {
            return new ProjectContactValue((EcProjectContact) projectContactIterator.next());
        }
        return null;
    }

    public static List getAuthAndPermitClientLabelValueList(ClientValue clientValue)
    {
        List
            authList =
            getAuthorizedClientLabelValueList(clientValue);
        List
            permitList =
            getPermitAuthorityClientLabelValueList(clientValue);
        Iterator
            pli =
            permitList.iterator();
        while (pli.hasNext())
        {
            authList.add(pli.next());
        }
        return removeDuplicates(authList);
    }

    public static List getOrgLabelValueList(Integer paID, Integer pID, Integer aiID)
    {
        ArrayList
            authList =
            new ArrayList();
        if (paID
            != null)
        {
            ClientData
                paCD =
                new ClientData(paID);
            if (paCD.load())
            {
                authList.add(new LabelValueBean(paCD.getName(),
                                                paCD.getId()
                                                    .toString()));
            }
        }
        if (pID
            != null)
        {
            ClientData
                pCD =
                new ClientData(pID);
            if (pCD.load())
            {
                authList.add(new LabelValueBean(pCD.getName(),
                                                pCD.getId()
                                                    .toString()));
            }
        }
        if (aiID
            != null)
        {
            ClientData
                aiCD =
                new ClientData(aiID);
            if (aiCD.load())
            {
                authList.add(new LabelValueBean(aiCD.getName(),
                                                aiCD.getId()
                                                    .toString()));
            }
        }
        return removeDuplicates(authList);
    }

    private static List removeDuplicates(List list)
    {
        HashSet
            set =
            new HashSet(list);
        ArrayList
            newList =
            new ArrayList(set);
        return newList;
    }

    public static List getAuthorizedClientLabelValueList(ClientValue clientValue)
    {
        ArrayList
            authorizedClientSet =
            getAuthorizedClientValueList(clientValue);
        List
            authorizedClientLabelValueList =
            new ArrayList();
        // Now, populate the authorizedClientLabelValueList
        Iterator
            authorizedClientSetIterator =
            authorizedClientSet.iterator();
        while (authorizedClientSetIterator.hasNext())
        {
            ClientValue
                cValue =
                (ClientValue) authorizedClientSetIterator.next();
            authorizedClientLabelValueList.add(new LabelValueBean(cValue.getName(),
                                                                  cValue.getId()
                                                                      .toString()));
        }
        return authorizedClientLabelValueList;
    }

    public static ArrayList getAuthorizedClientValueList(ClientValue clientValue)
    {
        ArrayList
            authorizedClientSet =
            new ArrayList();
        authorizedClientSet.add(clientValue);
        ClientValueSQLHelper
            clientValueSQLHelper =
            new ClientValueSQLHelper();
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("EXEC sp_authorized_client_list @clientId = "
                      + clientValue.getId()
                      + ", @statusCd = "
                      + StatusCodeData.STATUS_CODE_ACTIVE);
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(buffer.toString());
        Iterator
            iter =
            SQLHelper.retrieveValueList(statement,
                                        clientValueSQLHelper)
                .iterator();
        while (iter.hasNext())
        {
            ClientValue
                clientValue1 =
                (ClientValue) iter.next();
            authorizedClientSet.add(clientValue1);
        }
        return authorizedClientSet;
    }

    public static List getPermitAuthorityClientLabelValueList(ClientValue clientValue)
    {
        ArrayList
            permitAuthClientSet =
            getPermitAuthorityClientValueList(clientValue);
        List
            permitAuthClientLabelValueList =
            new ArrayList();
        Iterator
            permitAuthClientSetIterator =
            permitAuthClientSet.iterator();
        while (permitAuthClientSetIterator.hasNext())
        {
            ClientValue
                cValue =
                (ClientValue) permitAuthClientSetIterator.next();
            permitAuthClientLabelValueList.add(new LabelValueBean(cValue.getName(),
                                                                  cValue.getId()
                                                                      .toString()));
        }
        return permitAuthClientLabelValueList;
    }

    public static ArrayList getPermitAuthorityClientValueList(ClientValue clientValue)
    {
        ArrayList
            permitAuthClientSet =
            new ArrayList();
        ClientValueSQLHelper
            clientValueSQLHelper =
            new ClientValueSQLHelper();
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("EXEC sp_permit_authority_client_list @clientId = "
                      + clientValue.getId()
                      + ", @statusCd = "
                      + StatusCodeData.STATUS_CODE_ACTIVE);
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(buffer.toString());
        Iterator
            iter =
            SQLHelper.retrieveValueList(statement,
                                        clientValueSQLHelper)
                .iterator();
        while (iter.hasNext())
        {
            ClientValue
                clientValue1 =
                (ClientValue) iter.next();
            permitAuthClientSet.add(clientValue1);
        }
        return permitAuthClientSet;
    }

    public static SecurityPermissionValue getProjectSecurityPermissionValue(SecurityManager securityManager, ProjectValue projectValue)
    {
        SecurityPermissionValue
            projectSecurityPermissionValue =
            new SecurityPermissionValue();
        projectSecurityPermissionValue.setIsCreate(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_CREATE,
                                                                                   projectValue));
        projectSecurityPermissionValue.setIsRead(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_READ,
                                                                                 projectValue));
        projectSecurityPermissionValue.setIsUpdate(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_UPDATE,
                                                                                   projectValue));
        projectSecurityPermissionValue.setIsDelete(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_DELETE,
                                                                                   projectValue));
        return projectSecurityPermissionValue;
    }

    public static SecurityPermissionValue getProjectSecurityPermissionValue(SecurityManager securityManager, EcProjectSearchData projectValue)
    {
        SecurityPermissionValue
            projectSecurityPermissionValue =
            new SecurityPermissionValue();
        projectSecurityPermissionValue.setIsCreate(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_CREATE,
                                                                                   projectValue));
        projectSecurityPermissionValue.setIsRead(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_READ,
                                                                                 projectValue));
        projectSecurityPermissionValue.setIsUpdate(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_UPDATE,
                                                                                   projectValue));
        projectSecurityPermissionValue.setIsDelete(securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_DELETE,
                                                                                   projectValue));
        return projectSecurityPermissionValue;
    }

    public static SecurityPermissionValue getInspectionSecurityPermissionValue(SecurityManager securityManager, EcProjectSearchData projectValue)
    {
        SecurityPermissionValue
            inspectionSecurityPermissionValue =
            new SecurityPermissionValue();
        inspectionSecurityPermissionValue.setIsCreate(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_CREATE,
                                                                                      projectValue));
        inspectionSecurityPermissionValue.setIsRead(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_READ,
                                                                                    projectValue));
        inspectionSecurityPermissionValue.setIsUpdate(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_UPDATE,
                                                                                      projectValue));
        inspectionSecurityPermissionValue.setIsDelete(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_DELETE,
                                                                                      projectValue));
        LOG.debug(inspectionSecurityPermissionValue.toString());
        return inspectionSecurityPermissionValue;
    }

    public static SecurityPermissionValue getInspectionSecurityPermissionValue(SecurityManager securityManager, ProjectValue projectValue)
    {
        SecurityPermissionValue
            inspectionSecurityPermissionValue =
            new SecurityPermissionValue();
        inspectionSecurityPermissionValue.setIsCreate(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_CREATE,
                                                                                      projectValue));
        inspectionSecurityPermissionValue.setIsRead(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_READ,
                                                                                    projectValue));
        inspectionSecurityPermissionValue.setIsUpdate(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_UPDATE,
                                                                                      projectValue));
        inspectionSecurityPermissionValue.setIsDelete(securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_DELETE,
                                                                                      projectValue));
        LOG.debug(inspectionSecurityPermissionValue.toString());
        return inspectionSecurityPermissionValue;
    }
    /*
        public static List getProjectList(String query, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        {
            Object[][]
                parameters =
                {
                    {
                        "clientId",
                        clientValue.getId()}};
            List
                authorizedProjectList =
                new ArrayList();
            Iterator
                authorizedProjectIterator =
                HibernateUtil.findByNamedQuery(query,
                                               parameters)
                    .iterator();
            while (authorizedProjectIterator.hasNext())
            {
                EcProject
                    project =
                    (EcProject) authorizedProjectIterator.next();
                ProjectValue
                    projectValue =
                    new ProjectValue(project,
                                     false);
                projectValue.setReportUrl(clientValue,
                                          userValue);
                projectValue.setInspectionFormUrl(clientValue,
                                                  userValue);
                projectValue.setProjectSecurityPermissionValue(getProjectSecurityPermissionValue(securityManager,
                                                                                                 projectValue));
                projectValue.setInspectionSecurityPermissionValue(getInspectionSecurityPermissionValue(securityManager,
                                                                                                       projectValue));
                authorizedProjectList.add(projectValue);
            }
            return authorizedProjectList;
        }
    */

    public static List getZonesByClientLabelValueList(ClientValue clientValue)
    {
        ArrayList
            zoneLabelValueList =
            new ArrayList();
        Iterator
            zoneIterator =
            EcZone.findByClientId(clientValue.getId())
                .iterator();
        while (zoneIterator.hasNext())
        {
            EcZone
                zone =
                (EcZone) zoneIterator.next();
            zoneLabelValueList.add(new LabelValueBean(zone.getName(),
                                                      zone.getId()
                                                          .toString()));
        }
        return zoneLabelValueList;
    }

    public static List getProjectZoneValueList(ClientValue clientValue)
    {
        return getProjectZoneValueList(clientValue,
                                       null);
    }

    public static List getProjectZoneValueList(ClientValue clientValue, Integer excludeZoneId)
    {
        ArrayList
            zoneValueList =
            new ArrayList();
        Iterator
            zoneIterator =
            EcZone.findByClientId(clientValue.getId())
                .iterator();
        while (zoneIterator.hasNext())
        {
            EcZone
                zone =
                (EcZone) zoneIterator.next();
            if ((excludeZoneId
                 == null)
                || (zone.getId()
                        .intValue()
                    != excludeZoneId.intValue()))
            {
                zoneValueList.add(new ProjectZoneValue(zone));
            }
        }
        return zoneValueList;
    }

    public static void replaceZone(Integer clientId, Integer fromZoneId, Integer toZoneId, UserValue userValue)
    {
        EcZone
            toZone =
            new EcZone();
        toZone.setId(toZoneId);
        toZone.load();
        Iterator
            projectIterator =
            EcProject.findByZoneId(fromZoneId)
                .iterator();
        while (projectIterator.hasNext())
        {
            EcProject
                project =
                (EcProject) projectIterator.next();
            project.setZone(toZone);
            project.update(userValue);
        }
    }

    public static List getProjectBmpValueList(Integer projectId)
    {
        ArrayList
            projectBmpValueList =
            new ArrayList();
        List
            projectBmpList =
            EcProjectBmp.findByProjectId(projectId);
        Iterator
            iterator =
            projectBmpList.iterator();
        while (iterator.hasNext())
        {
            EcProjectBmp
                projectBmp =
                (EcProjectBmp) iterator.next();
            ProjectBmpValue
                projectBmpValue =
                new ProjectBmpValue(projectBmp);
            projectBmpValueList.add(projectBmpValue);
        }
        return projectBmpValueList;
    }

    public static List getProjectDocumentValueList(Integer projectId, Integer clientId, String username, HttpServletRequest request)
    {
        ArrayList
            projectDocumentValueList =
            new ArrayList();
        List
            projectDocumentList =
            EcProjectDocument.findByProjectId(projectId);
        Iterator
            iterator =
            projectDocumentList.iterator();
        while (iterator.hasNext())
        {
            EcProjectDocument
                projectDocument =
                (EcProjectDocument) iterator.next();
            ProjectDocumentValue
                projectDocumentValue =
                new ProjectDocumentValue(projectDocument);
            projectDocumentValue.setClientId(clientId);
            projectDocumentValue.setUsername(username);
            projectDocumentValue.setDownloadURL(projectDocumentValue.getDownloadURL(request));
            LOG.debug(projectDocumentValue.toString());
            projectDocumentValueList.add(projectDocumentValue);
        }
        return projectDocumentValueList;
    }

    public static List getPublicProjectDocumentValueList(Integer projectId, HttpServletRequest request)
    {
        ArrayList
            projectDocumentValueList =
            new ArrayList();
        List
            projectDocumentList =
            EcProjectDocument.findByProjectId(projectId);
        Iterator
            iterator =
            projectDocumentList.iterator();
        while (iterator.hasNext())
        {
            EcProjectDocument
                projectDocument =
                (EcProjectDocument) iterator.next();
            ProjectDocumentValue
                projectDocumentValue =
                new ProjectDocumentValue(projectDocument);
            projectDocumentValue.setDownloadURL(projectDocumentValue.getPublicDownloadURL(request));
            LOG.debug(projectDocumentValue.toString());
            projectDocumentValueList.add(projectDocumentValue);
        }
        return projectDocumentValueList;
    }

    public static List getProjectMapValueList(ClientData clientData, HttpServletRequest request)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientData.getId()}};
        String
            detailLevel =
            request.getParameter(RequestKeys.DETAIL_PARAMETER);
        List
            projectMapValueList =
            new ArrayList();
        Iterator
            projectMapValueIterator =
            HibernateUtil.findByNamedQuery(PROJECT_MAP_LIST_BY_OWNER,
                                           parameters)
                .iterator();
        while (projectMapValueIterator.hasNext())
        {
            ProjectMapValue
                projectMapValue =
                new ProjectMapValue((EcProject) projectMapValueIterator.next(),
                                    detailLevel,
                                    clientData);
            if (!StringUtil.isEmpty(projectMapValue.getLatitude())
                && !StringUtil.isEmpty(projectMapValue.getLongitude()))
            {
                projectMapValue.setUrl(clientData.getId(),
                                       request);
                projectMapValue.setCommentUrl(clientData.getId(),
                                              request);
                projectMapValueList.add(projectMapValue);
            }
        }
        return projectMapValueList;
    }

    public static ProjectValue getProjectValue(EcProject ecProject, boolean loadContacts, SecurityManager securityManager)
    {
        ProjectValue
            projectValue =
            new ProjectValue(ecProject,
                             loadContacts);
        projectValue.setProjectSecurityPermissionValue(getProjectSecurityPermissionValue(securityManager,
                                                                                         projectValue));
        projectValue.setInspectionSecurityPermissionValue(getInspectionSecurityPermissionValue(securityManager,
                                                                                               projectValue));
        return projectValue;
    }

    public static ProjectValue getProjectValue(EcProject ecProject, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
    {
        ProjectValue
            projectValue =
            new ProjectValue(ecProject,
                             clientValue,
                             userValue);
        projectValue.setProjectSecurityPermissionValue(getProjectSecurityPermissionValue(securityManager,
                                                                                         projectValue));
        projectValue.setInspectionSecurityPermissionValue(getInspectionSecurityPermissionValue(securityManager,
                                                                                               projectValue));
        return projectValue;
    }

    public static Collection getUserProjectListItems(UserValue userValue)
    {
        TreeSet
            projectListItems =
            new TreeSet();
        EcUserPreferences
            ecUserPreferences =
            EcUserPreferences.findByUserId(userValue.getId());
        if (ecUserPreferences
            == null
            || StringUtil.isEmpty(ecUserPreferences.getProjectListItems()))
        {
            return getDefaultProjectListItems();
        }
        StringTokenizer
            st =
            new StringTokenizer(ecUserPreferences.getProjectListItems(),
                                ",");
        while (st.hasMoreTokens())
        {
            ProjectListItem
                projectListItem =
                ProjectListItem.getProjectListItem(st.nextToken());
            if (projectListItem
                != null)
            {
                projectListItems.add(projectListItem);
            }
        }
        return projectListItems;
    }

    public static Collection getDefaultProjectListItems()
    {
        TreeSet
            projectListItems =
            new TreeSet();
        projectListItems.add(ProjectListItem.PROJECT_NAME);
        projectListItems.add(ProjectListItem.PROJECT_TYPE);
        projectListItems.add(ProjectListItem.PROJECT_ZONE);
        projectListItems.add(ProjectListItem.PERMIT_NUMBER);
        projectListItems.add(ProjectListItem.PERMIT_AUTHORITY);
        projectListItems.add(ProjectListItem.PERMITTED);
        projectListItems.add(ProjectListItem.PROJECT_STATUS);
        return projectListItems;
    }

    public static boolean saveUserProjectListItems(String[] projectListItems, UserValue userValue)
    {
        //Create a string of comma delimited project list item key names and store it in the database
        //Make sure that at least PROJECT_NAME exists
        boolean
            projectNameFound =
            false;
        String
            projectListItemString =
            "";
        for (
            int
                i =
                0; i
                   < projectListItems.length; i++)
        {
            if (projectListItems[i].equals(ProjectListItem.PROJECT_NAME))
            {
                projectNameFound =
                    true;
            }
            projectListItemString +=
                projectListItems[i];
            if ((i
                 + 1)
                < projectListItems.length)
            {
                projectListItemString +=
                    ",";
            }
        }
        //Make sure we have at least PROJECT_NAME
        if (!projectNameFound)
        {
            if (!projectListItemString.equals(""))
            {
                projectListItemString +=
                    ",";
            }
            projectListItemString +=
                ProjectListItem.PROJECT_NAME
                    .getKey();
        }
        EcUserPreferences
            ecUserPreferences =
            EcUserPreferences.findByUserId(userValue.getId());
        if (ecUserPreferences
            == null)
        {
            ecUserPreferences =
                new EcUserPreferences();
            ecUserPreferences.setUserId(userValue.getId());
        }
        ecUserPreferences.setProjectListItems(projectListItemString);
        try
        {
            ecUserPreferences.save();
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to save EcUserPreferences for user ID "
                      + userValue.getId());
            LOG.error(e.getMessage());
        }
        return false;
    }

    public static String generateReportUrl(ClientValue client, UserValue user, EcProjectSearchData data)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        String
            username =
            "";
        if (user
            != null)
        {
            username =
                user.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getProperty("base.url"));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_PROJECT_REPORT_SECURE_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  client.getId()
                                                                      .toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  username),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  data.getProjectId()
                                                                      .toString()),
                                              "UTF-8"));
            return buffer.toString();
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Project Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
        return null;
    }

    public static String generateInspectionFormUrl(ClientValue client, UserValue user, EcProjectSearchData data)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        String
            username =
            "";
        if (user
            != null)
        {
            username =
                user.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getProperty("base.url"));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_INSPECTION_FORM_REPORT_SECURE_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  client.getId()
                                                                      .toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  username),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  data.getProjectId()
                                                                      .toString()),
                                              "UTF-8"));
            return buffer.toString();
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Project Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
        return null;
    }

    public static List<String> getAllRecipients(EcProject project)
    {
        List<String>
            list =
            new ArrayList<String>();
        List
            contactList =
            ProjectService.getProjectContactValueListAll(project.getId()/*, false*/);
        for (Object value : contactList)
        {
            ProjectContactValue
                contact =
                (ProjectContactValue) value;
            list.add(contact.getEmail());
        }
        return list;
    }

    public static void setProjectZonesOnRequest(HttpServletRequest request, List<Integer> clientIds)
    {
        List
                lstZones =
                new ArrayList();
        for (Integer id : clientIds)
        {
            lstZones.addAll(EcZone.findByClientId(id));
        }
        request.setAttribute(SessionKeys.EC_ZONE_LIST,
                lstZones);
    }

    public static void setProjectTypesOnRequest(HttpServletRequest request, List<Integer> clientIds)
    {
        List
                lstTypes =
                new ArrayList();
        for (Integer id : clientIds)
        {
            lstTypes.addAll(EcProjectType.findByClientId(id));
        }
        request.setAttribute(SessionKeys.EC_PROJECT_TYPE_LIST,
                lstTypes);
    }

    public static void setProjectStatusesOnRequest(HttpServletRequest request)
    {
        List<CodeData>
                lstStatuses =
                SpringServiceLocator.getLookupService()
                        .fetchCodes(ProjectStatusCodeData.class);
        request.setAttribute(SessionKeys.PROJECT_STATUS_CODE_LIST,
                lstStatuses);
    }

    public static void setProjectLastInspectionStatusesOnRequest(HttpServletRequest request)
    {
        List<CodeData>
                lstStatuses =
                new ArrayList<CodeData>();

        CodeData pass = new CodeData();
        pass.setCode("PASS");
        pass.setDescription("Pass");
        pass.setDisplayOrder(1);

        CodeData fail = new CodeData();
        fail.setCode("FAIL");
        fail.setDescription("Failed");
        fail.setDisplayOrder(2);

        CodeData warn = new CodeData();
        warn.setCode("WARN");
        warn.setDescription("Warning");
        warn.setDisplayOrder(3);

        lstStatuses.add(pass);
        lstStatuses.add(fail);
        lstStatuses.add(warn);

        request.setAttribute(SessionKeys.PROJECT_LAST_INSPECTION_STATUS_LIST,
                lstStatuses);
    }

    public static void setStateListOnRequest(HttpServletRequest request)
    {
        List
                lstC =
                CapState.findNonArmedForcesStates();
        request.setAttribute(SessionKeys.EC_STATE_LIST,
                lstC);
    }
}