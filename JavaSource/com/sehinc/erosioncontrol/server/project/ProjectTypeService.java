package com.sehinc.erosioncontrol.server.project;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.project.ProjectTypeForm;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.project.EcEndPointType;
import com.sehinc.erosioncontrol.db.project.EcProjectType;
import com.sehinc.erosioncontrol.value.project.ProjectTypeValue;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

public class ProjectTypeService
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectTypeService.class);

    public ProjectTypeService()
    {
    }

    public static Integer getProjectTypeIdFromSessionAndRequest(HttpServletRequest request)
    {
        Integer
            intProjectTypeId =
            new Integer(0);
        ProjectTypeValue
            projectTypeValue;
        if (request.getParameter(RequestKeys.PROJECT_TYPE_ID)
            != null)
        {
            intProjectTypeId =
                new Integer(request.getParameter(RequestKeys.PROJECT_TYPE_ID));
            LOG.info("ProjectTypeService: getProjectTypeIdFromSessionAndRequest: Request project type Id = "
                     + intProjectTypeId);
        }
        else if (request.getSession()
                     .getAttribute(SessionKeys.PROJECT_TYPE_ID)
                 != null)
        {
            intProjectTypeId =
                new Integer(request.getSession()
                                .getAttribute(SessionKeys.PROJECT_TYPE_ID)
                                .toString());
            LOG.info("ProjectTypeService: getProjectTypeIdFromSessionAndRequest: Session project type Id = "
                     + intProjectTypeId);
        }
        else if (request.getSession()
                     .getAttribute(SessionKeys.PROJECT_TYPE)
                 != null)
        {
            projectTypeValue =
                (ProjectTypeValue) request.getSession()
                    .getAttribute(SessionKeys.PROJECT_TYPE);
            intProjectTypeId =
                projectTypeValue.getId();
            LOG.info("ProjectTypeService: getProjectTypeIdFromSessionAndRequest: Session project typeValue.projecttypeId = "
                     + intProjectTypeId);
        }
        else
        {
            LOG.info("ProjectTypeService: getProjectTypeIdFromSessionAndRequest: Project Type Id not found");
        }
        return intProjectTypeId;
    }

    public static void setProjectTypeInSessionAndRequest(Integer projectTypeId, HttpServletRequest request)
        throws Exception
    {
        LOG.info("ProjectTypeService: setProjectTypeInSessionAndRequest: Request Project Type Id = "
                 + projectTypeId);
        if (projectTypeId
            != null)
        {
            request.getSession()
                .setAttribute(SessionKeys.PROJECT_TYPE,
                              getProjectTypeById(projectTypeId));
        }
        else
        {
            request.getSession()
                .setAttribute(SessionKeys.PROJECT_TYPE,
                              null);
        }
        request.getSession()
            .setAttribute(SessionKeys.PROJECT_TYPE_ID,
                          projectTypeId);
        request.setAttribute(RequestKeys.PROJECT_TYPE_ID,
                             projectTypeId);
    }

    public static ProjectTypeValue getProjectTypeById(Integer projectTypeId)
        throws Exception
    {
        ProjectTypeValue
            projectTypeValue =
            new ProjectTypeValue();
        List
            lstProjectType;
        Iterator
            itProjectType;
        EcProjectType
            ecProjectType;
        LOG.debug("ProjectTypeService.getProjectTypeById("
                  + projectTypeId.toString()
                  + "): in method.");
        projectTypeValue.resetProjectTypeValue();
        lstProjectType =
            HibernateUtil.find("select data from EcProjectType as data where data.id = "
                               + projectTypeId.toString()
                               + " and data.status.code = "
                               + StatusCodeData.STATUS_CODE_ACTIVE
                .toString(),
                               new Object[0]);
        if (lstProjectType
            != null)
        {
            if (!lstProjectType.isEmpty())
            {
                itProjectType =
                    lstProjectType.iterator();
                if (itProjectType.hasNext())
                {
                    ecProjectType =
                        (EcProjectType) itProjectType.next();
                    if (ecProjectType
                        != null)
                    {
                        projectTypeValue =
                            new ProjectTypeValue(ecProjectType);
                    }
                    else
                    {
                        LOG.debug("ProjectTypeService.getProjectTypeById: ecproject type from project type iterator is NULL");
                        throw new Exception("ProjectTypeService.getProjectTypeById: ecproject type from project type iterator is NULL");
                    }
                }
                else
                {
                    LOG.debug("ProjectTypeService.getProjectTypeById: project type Iterator does not contain data.");
                    throw new Exception("ProjectTypeService.getProjectTypeById: project type Iterator does not contain data.");
                }
            }
            else
            {
                LOG.debug("ProjectTypeService.getProjectTypeById: lstproject type is empty.");
                throw new Exception("ProjectTypeService.getProjectTypeById: lstproject type is empty.");
            }
        }
        else
        {
            LOG.debug("ProjectTypeService.getProjectTypeById: lstproject type is NULL.");
            throw new Exception("ProjectTypeService.getProjectTypeById: lstproject type is NULL.");
        }
        return projectTypeValue;
    }

    public static List getProjectTypeList(Integer clientId)
        throws Exception
    {
        List
            lstProjectTypeList;
        String
            strError =
            new String("ProjectTypeService.getProjectTypeList: ");
        LOG.debug(strError
                  + "client Id = "
                  + clientId.toString());
        if (clientId.intValue()
            > 0)
        {
            lstProjectTypeList =
                HibernateUtil.find("select data from EcProjectType as data where data.clientId = "
                                   + clientId.toString()
                                   + " and data.status.code = "
                                   + StatusCodeData.STATUS_CODE_ACTIVE
                    .toString()
                                   + " order by data.name",
                                   new Object[0]);
            if (lstProjectTypeList
                == null)
            {
                strError =
                    strError
                    + "Project Type List returned from Hibernate is null";
                LOG.debug(strError);
                throw new Exception(strError);
            }
        }
        else
        {
            strError =
                strError
                + "client Id passed in is zero (0)";
            LOG.debug(strError);
            throw new Exception(strError);
        }
        LOG.debug(strError
                  + "Project Type Count = "
                  + lstProjectTypeList.size());
        return lstProjectTypeList;
    }

    public static List getEndPointTypeList()
        throws Exception
    {
        List
            lstEndPointTypeList;
        String
            strError =
            new String("ProjectTypeService.getEndPointTypeList: ");
        LOG.debug(strError
                  + "In method");
        lstEndPointTypeList =
            HibernateUtil.find("select data from EcEndPointType as data order by data.id",
                               new Object[0]);
        if (lstEndPointTypeList
            == null)
        {
            strError =
                strError
                + "End Point Type List returned from Hibernate is null";
            LOG.debug(strError);
            throw new Exception(strError);
        }
        LOG.debug(strError
                  + "End Point Type Count = "
                  + lstEndPointTypeList.size());
        return lstEndPointTypeList;
    }

    public static Integer saveProjectType(ProjectTypeForm projectTypeForm, Integer clientId, UserValue userValue)
        throws Exception
    {
        Integer
            intProjectTypeId;
        EcProjectType
            ecProjectType =
            new EcProjectType();
        EcEndPointType
            ecEndPointType =
            new EcEndPointType();
        String
            strDebug =
            new String("ProjectTypeService.saveProjectType: ");
        String
            strError;
        if (projectTypeForm
            != null)
        {
            LOG.debug(strDebug
                      + "("
                      + projectTypeForm.getName()
                      + ","
                      + clientId.toString()
                      + ")");
            ecEndPointType.setId(projectTypeForm.getEndPointTypeId());
            ecEndPointType.load();
            if (projectTypeForm.getId()
                != null
                && projectTypeForm.getId()
                       .intValue()
                   > 0)
            {
                ecProjectType.setId(projectTypeForm.getId());
                ecProjectType.load();
            }
            else
            {
                ecProjectType.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            }
            ecProjectType.setClientId(clientId);
            ecProjectType.setName(projectTypeForm.getName());
            ecProjectType.setDescription(projectTypeForm.getDescription());
            ecProjectType.setEndPointType(ecEndPointType);
            ecProjectType.setSwmp(projectTypeForm.getSwmp());
            ecProjectType.setExtendedOnlineAccessMonths(projectTypeForm.getExtendedOnlineAccessMonths());
            ecProjectType.setMonthsFromStart(projectTypeForm.getMonthsFromStart());
            try
            {
                intProjectTypeId =
                    ecProjectType.save(userValue);
                LOG.debug(strDebug
                          + "project type saved.");
                if (intProjectTypeId
                    == null)
                {
                    strError =
                        strDebug
                        + "ecProjectType.save() returned a null value.  "
                        + projectTypeForm.getName();
                    LOG.debug(strError);
                    throw new Exception(strError);
                }
                else if (intProjectTypeId.intValue()
                         == 0)
                {
                    strError =
                        strDebug
                        + "ecProjectType.save() returned a zero for project type id.  "
                        + projectTypeForm.getName();
                    LOG.debug(strError);
                    throw new Exception(strError);
                }
            }
            catch (Exception e)
            {
                strError =
                    strDebug
                    + "Unable to save the project type "
                    + projectTypeForm.getName();
                LOG.debug(strError);
                throw new Exception(strError
                                    + " Error Message: "
                                    + e.getMessage());
            }
        }
        else
        {
            strError =
                strDebug
                + "Project Type Form is null.  Unable to save the project type.";
            LOG.debug(strError);
            throw new Exception(strError);
        }
        return intProjectTypeId;
    }

    private static String q2(String s)
    {
        String
            x =
            new String("");
        int
            a;
        char
            q =
            new String("'").charAt(0);
        if (s.indexOf("'")
            >= 0)
        {
            for (
                a =
                    0; a
                       < s.length(); a++)
            {
                if (s.charAt(a)
                    == q)
                {
                    x =
                        x
                        + "''";
                }
                else
                {
                    x =
                        x
                        + s.charAt(a);
                }
            }
        }
        else
        {
            x =
                s;
        }
        return x;
    }

    public static ProjectTypeValue getProjectTypeByName(String projectTypeName, Integer clientId)
        throws Exception
    {
        ProjectTypeValue
            projectTypeValue =
            new ProjectTypeValue();
        List
            lstProjectType;
        Iterator
            itProjectType;
        EcProjectType
            ecProjectType;
        String
            strDebug =
            new String("ProjectTypeService.getProjectTypeByname: ");
        String
            strError;
        LOG.debug(strDebug
                  + "("
                  + projectTypeName
                  + ","
                  + clientId.toString()
                  + "): in method.");
        projectTypeValue.resetProjectTypeValue();
        if ((projectTypeName
             != null))
        {
            try
            {
                lstProjectType =
                    HibernateUtil.find("select data from EcProjectType as data where data.clientId = "
                                       + clientId.toString()
                                       + " and data.name = '"
                                       + q2(projectTypeName)
                                       + "' and data.status.code = "
                                       + StatusCodeData.STATUS_CODE_ACTIVE
                        .toString(),
                                       new Object[0]);
            }
            catch (Exception e)
            {
                strError =
                    strDebug
                    + "Error retrieving project type by name. Error Message: "
                    + e.getMessage();
                LOG.debug(strError);
                throw new Exception(strError);
            }
            if (lstProjectType
                != null)
            {
                if (!lstProjectType.isEmpty())
                {
                    itProjectType =
                        lstProjectType.iterator();
                    if (itProjectType.hasNext())
                    {
                        ecProjectType =
                            (EcProjectType) itProjectType.next();
                        if (ecProjectType
                            != null)
                        {
                            projectTypeValue =
                                new ProjectTypeValue(ecProjectType);
                        }
                        else
                        {
                            strError =
                                strDebug
                                + "ecProjectType from iterator is NULL";
                            LOG.debug(strError);
                            throw new Exception(strError);
                        }
                    }
                    else
                    {
                        strError =
                            strDebug
                            + "ecProjectType contains no data. itProjectType.hasNext() returned false";
                        LOG.debug(strError);
                        projectTypeValue.resetProjectTypeValue();
                    }
                }
                else
                {
                    strError =
                        strDebug
                        + "ecProjectType contains no data.  lstProjectType.isEmpty() returned true";
                    LOG.debug(strError);
                    projectTypeValue.resetProjectTypeValue();
                }
            }
            else
            {
                strError =
                    strDebug
                    + "lstProjectType == null";
                LOG.debug(strError);
                throw new Exception(strError);
            }
        }
        return projectTypeValue;
    }

    public static Integer getProjectCount(Integer projectTypeId)
        throws Exception
    {
        Long
            lngProjectCount;
        String
            strDebug =
            new String("ProjectTypeService.getProjectCount ");
        String
            strError;
        if (projectTypeId
            != null)
        {
            LOG.debug(strDebug
                      + "("
                      + projectTypeId.toString()
                      + ") in method.");
            try
            {
                lngProjectCount =
                    (Long) HibernateUtil.findUnique("select count(distinct data.id) from EcProject as data "
                                                    +
                                                    "where data.projectType.id = "
                                                    + projectTypeId.toString()
                                                    +
                                                    " and data.status.code != "
                                                    + StatusCodeData.STATUS_CODE_DELETED
                        .toString(),
                                                    new Object[0]);
                if (lngProjectCount
                    == null)
                {
                    strError =
                        strDebug
                        + "intProjectCount is null!!!";
                    LOG.debug(strError);
                    throw new Exception(strError);
                }
            }
            catch (Exception e)
            {
                strError =
                    strDebug
                    + "Error getting project count ["
                    + e.getMessage()
                    + "]";
                LOG.debug(strError);
                throw new Exception(strError);
            }
        }
        else
        {
            strError =
                strDebug
                + "project Type Id is null.  Unable to get project count";
            LOG.debug(strError);
            throw new Exception(strError);
        }
        LOG.debug(strDebug
                  + "Project count for project type id "
                  + projectTypeId.toString()
                  + " is "
                  + lngProjectCount.toString());
        return new Integer(lngProjectCount.intValue());
    }

    public static void deleteProjectType(Integer projectTypeId, UserValue userValue)
        throws Exception
    {
        EcProjectType
            ecProjectType =
            new EcProjectType();
        String
            strDebug =
            new String("ProjectTypeService.deleteProjectType: ");
        String
            strError;
        if (projectTypeId
            != null)
        {
            LOG.debug(strDebug
                      + "("
                      + projectTypeId.toString()
                      + ")");
            ecProjectType.setId(projectTypeId);
            ecProjectType.load();
            try
            {
                ecProjectType.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
                ecProjectType.save(userValue);
                LOG.debug(strDebug
                          + "project type deleted.");
            }
            catch (Exception e)
            {
                strError =
                    strDebug
                    + "Unable to delete the project type "
                    + projectTypeId.toString();
                LOG.debug(strError);
                throw new Exception(strError
                                    + " Error Message: "
                                    + e.getMessage());
            }
        }
        else
        {
            strError =
                strDebug
                + "Project Type Id is null.  Unable to delete the project type.";
            LOG.debug(strError);
            throw new Exception(strError);
        }
    }
}
