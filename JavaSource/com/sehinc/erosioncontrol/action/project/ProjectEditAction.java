package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.gis.EcGISCoord;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectBmp;
import com.sehinc.erosioncontrol.db.project.EcProjectContact;
import com.sehinc.erosioncontrol.db.project.EcProjectContactType;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.util.ProjectUtil;
import com.sehinc.erosioncontrol.value.project.ProjectBmpValue;
import com.sehinc.erosioncontrol.value.project.ProjectContactValue;
import com.sehinc.erosioncontrol.value.project.ProjectDocumentValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

public class ProjectEditAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectEditAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws Exception
    {
        ProjectForm
            projectForm =
            (ProjectForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            projectForm.reset();
            return mapping.findForward("project.view.page");
        }
        ClientData
            clientData =
            loadClientData(clientValue);
        EcProject
            project =
            loadProject(projectForm);
        if (isDeleted(projectForm))
        {
            return processDelete(mapping,
                                 request,
                                 projectForm,
                                 userValue,
                                 project);
        }
        if (isLookupCoordinates(projectForm))
        {
            lookupCooridinatesAndSetOnForm(projectForm,
                                           clientValue,
                                           request);
        }
        ProjectUtil.loadFormIntoProject(projectForm,
                                        project);
        project.save(userValue);
        project.load();
        processDocuments(projectForm,
                         project,
                         request);
        processContacts(projectForm,
                        userValue,
                        clientValue,
                        clientData,
                        project);
        processBmps(projectForm,
                    userValue,
                    project);
        setProjectInSession(request,
                            userValue,
                            clientValue,
                            securityManager,
                            project);
        clearProjectListFromSession(request);
        if (isLookupCoordinates(projectForm))
        {
            return (mapping.findForward("project.edit.page"));
        }
        addMessage(new ActionMessage("project.save.success"),
                   request);
        if (project.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE)
            || project.getStatus()
            .getCode()
            .equals(StatusCodeData.STATUS_CODE_INACTIVE)
            || project.getStatus()
            .getCode()
            .equals(StatusCodeData.STATUS_CODE_AUTO_ACTIVATE))
        {
            return mapping.findForward("project.email.page");
        }
        return mapping.findForward("continue");
    }

    private void setProjectInSession(HttpServletRequest request, UserValue userValue, ClientValue clientValue, SecurityManager securityManager, EcProject project)
    {
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            ProjectService.getProjectValue(project,
                                                           clientValue,
                                                           userValue,
                                                           securityManager),
                            request);
    }

    private void clearProjectListFromSession(HttpServletRequest request)
    {
        request.getSession()
            .removeAttribute(SessionKeys.EC_AUTHORIZED_PROJECT_LIST);
    }

    private void processBmps(ProjectForm projectForm, UserValue userValue, EcProject project)
    {
        Hashtable
            projectBmpList =
            projectForm.getBmps()
                .getAllBmps();
        int
            projectBmpListIndex =
            0;
        while (projectBmpList.containsKey(new Integer(projectBmpListIndex)))
        {
            ProjectBmpValue
                projectBmpValue =
                (ProjectBmpValue) projectBmpList.get(new Integer(projectBmpListIndex));
            LOG.debug(projectBmpValue.toString());
            if (projectBmpValue.getIsDeleted())
            {
                if (projectBmpValue.getProjectBmpId()
                    != null
                    && projectBmpValue.getProjectBmpId()
                       > 0)
                {
                    EcProjectBmp
                        projectBmp =
                        new EcProjectBmp();
                    projectBmp.setId(projectBmpValue.getProjectBmpId());
                    projectBmp.load();
                    projectBmp.delete();
                }
            }
            else
            {
                if (!StringUtil.isEmpty(projectBmpValue.getBmpCategoryName())
                    && !StringUtil.isEmpty(projectBmpValue.getBmpName()))
                {
                    EcProjectBmp
                        projectBmp =
                        new EcProjectBmp();
                    projectBmp.setProjectId(project.getId());
                    projectBmp.setBmpName(projectBmpValue.getBmpName());
                    projectBmp.setCategoryName(projectBmpValue.getBmpCategoryName());
                    projectBmp.setIsRequired(projectBmpValue.getIsRequired());
                    projectBmp.setDescription(projectBmpValue.getBmpDescription());
                    if (projectBmpValue.getProjectBmpId()
                        != null
                        && projectBmpValue.getProjectBmpId()
                           > 0)
                    {
                        projectBmp.setId(projectBmpValue.getProjectBmpId());
                        projectBmp.update(userValue);
                    }
                    else
                    {
                        projectBmp.insert(userValue);
                    }
                }
            }
            projectBmpListIndex++;
        }
    }

    private void processContacts(ProjectForm projectForm, UserValue userValue, ClientValue clientValue, ClientData clientData, EcProject project)
    {
        Hashtable
            projectContactList =
            projectForm.getContacts()
                .getAllContacts();
        LOG.debug(projectContactList.toString());
        for (
            int
                projectContactListIndex =
                0; projectContactListIndex
                   < 25; projectContactListIndex++)
        {
            if (projectContactList.containsKey(new Integer(projectContactListIndex)))
            {
                ProjectContactValue
                    projectContactValue =
                    (ProjectContactValue) projectContactList.get(new Integer(projectContactListIndex));
                LOG.debug(projectContactValue.toString());
                if (projectContactValue.getIsDeleted())
                {
                    if (projectContactValue.getId()
                        > 0)
                    {
                        EcProjectContact
                            projectContact =
                            new EcProjectContact();
                        projectContact.setId(projectContactValue.getId());
                        projectContact.load();
                        projectContact.delete();
                    }
                }
                else
                {
                    if (projectContactValue.getOrganizationId()
                        != null
                        && projectContactValue.getOrganizationId()
                           != 0
                        && projectContactValue.getContactId()
                           != null
                        && projectContactValue.getContactId()
                           != 0)
                    {
                        EcProjectContactType
                            contactType;
                        if (projectContactValue.getContactTypeId()
                            == null
                            || projectContactValue.getContactTypeId()
                               == 0)
                        {
                            contactType =
                                ProjectService.getProjectContactTypeByClientCode(clientValue.getId(),
                                                                                 EcProjectContactType.GENERAL);
                            projectContactValue.setContactTypeId(contactType.getId());
                        }
                        else
                        {
                            contactType =
                                new EcProjectContactType(projectContactValue.getContactTypeId());
                            contactType.load();
                        }
                        LOG.debug("done loading existing contactType ID="
                                  + contactType.getId());
                        CapContactOrganization
                            contactOrganization =
                            null;
                        if (projectContactValue.getOrganizationId()
                            != null)
                        {
                            if (projectContactValue.getOrganizationId()
                                == -1)
                            {
                                if (projectContactValue.getOrgRefClientId()
                                    != null
                                    && projectContactValue.getOrgRefClientId()
                                       > 0)
                                {
                                    contactOrganization =
                                        CapContactOrganization.findByRefClientId(clientValue.getId(),
                                                                                 projectContactValue.getOrgRefClientId());
                                }
                                if (contactOrganization
                                    == null)
                                {
                                    contactOrganization =
                                        new CapContactOrganization();
                                    AddressData
                                        organizationAddress =
                                        new AddressData();
                                    organizationAddress.setLine1(projectContactValue.getAddress());
                                    organizationAddress.setCity(projectContactValue.getCity());
                                    organizationAddress.setState(projectContactValue.getStateCode());
                                    organizationAddress.setPostalCode(projectContactValue.getZip());
                                    organizationAddress.save(userValue);
                                    contactOrganization.setName(projectContactValue.getOrganizationName());
                                    contactOrganization.setClientId(clientValue.getId());
                                    contactOrganization.setAddress(organizationAddress);
                                    contactOrganization.setIsClient(false);
                                    contactOrganization.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                                    contactOrganization.save(userValue);
                                }
                            }
                            else
                            {
                                contactOrganization =
                                    new CapContactOrganization();
                                contactOrganization.setId(projectContactValue.getOrganizationId());
                                contactOrganization.load();
                            }
                        }
                        CapContact
                            contact =
                            new CapContact();
                        AddressData
                            contactAddress =
                            new AddressData();
                        if (projectContactValue.getContactId()
                            != null)
                        {
                            if (projectContactValue.getContactId()
                                > 0)
                            {
                                contact.setId(projectContactValue.getContactId());
                                contact.load();
                                contactAddress =
                                    contact.getAddressData();
                                contactOrganization =
                                    contact.getOrganization();
                                LOG.debug("done loading existing contact ID="
                                          + contact.getId());
                            }
                            contactAddress.setLine1(projectContactValue.getAddress());
                            contactAddress.setCity(projectContactValue.getCity());
                            contactAddress.setState(projectContactValue.getStateCode());
                            contactAddress.setPostalCode(projectContactValue.getZip());
                            contactAddress.save(userValue);
                            if (contactOrganization
                                != null)
                            {
                                contact.setOrganization(contactOrganization);
                                contact.setOrganizationName(contactOrganization.getName());
                            }
                            contact.setFirstName(projectContactValue.getFirstName());
                            contact.setLastName(projectContactValue.getLastName());
                            contact.setAddressData(contactAddress);
                            contact.setAddress(projectContactValue.getAddress());
                            contact.setCity(projectContactValue.getCity());
                            contact.setZip(projectContactValue.getZip());
                            contact.setPrimaryPhone(projectContactValue.getPrimaryPhone());
                            contact.setEmail(projectContactValue.getEmail());
                            contact.setStateCode(projectContactValue.getStateCode());
                            contact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                            contact.save(userValue);
                            LOG.debug("done saving contact ID="
                                      + contact.getId());
                        }
                        CapClientContact
                            clientContact =
                            CapClientContact.findByClientIdAndContactId(clientValue.getId(),
                                                                        contact.getId());
                        if (clientContact
                            == null)
                        {
                            clientContact =
                                new CapClientContact();
                            clientContact.setClient(clientData);
                            clientContact.setContact(contact);
                            clientContact.save();
                        }
                        EcProjectContact
                            projectContact =
                            new EcProjectContact();
                        if (projectContactValue.getId()
                            != null
                            && projectContactValue.getId()
                               > 0)
                        {
                            projectContact.setId(projectContactValue.getId());
                            projectContact.load();
                        }
                        projectContact.setProjectId(project.getId());
                        projectContact.setContact(contact);
                        projectContact.setContactType(contactType);
                        projectContact.save();
                        LOG.debug("done saving projectContact ID="
                                  + projectContact.getId());
                    }
                }
            }
        }
    }

    private void processDocuments(ProjectForm projectForm, EcProject project, HttpServletRequest request)
    {
        Hashtable
            projectDocumentList =
            projectForm.getDocuments()
                .getAllDocuments();
        int
            projectDocumentListIndex =
            0;
        while (projectDocumentList.containsKey(new Integer(projectDocumentListIndex)))
        {
            ProjectDocumentValue
                projectDocumentValue =
                (ProjectDocumentValue) projectDocumentList.get(new Integer(projectDocumentListIndex));
            LOG.debug(projectDocumentValue.toString());
            if (projectDocumentValue.getId()
                > 0)
            {
                if (projectDocumentValue.getIsDeleted())
                {
                    deleteProjectDocument(projectDocumentValue);
                }
            }
            else
            {
                if (projectDocumentValue.getFormFile()
                    != null
                    && projectDocumentValue.getFormFile()
                           .getFileName()
                           .trim()
                           .length()
                       > 0)
                {
                    projectDocumentValue.setProjectId(project.getId());
                    try
                    {
                        processProjectDocument(projectDocumentValue,
                                               project.getOwnerClient()
                                                   .getId());
                    }
                    catch (Exception e)
                    {
                        Object[]
                            parameters =
                            {
                                projectDocumentValue.getFormFile()
                                    .getFileName()};
                        LOG.error(ApplicationResources.getProperty("project.error.project.document.save.failed",
                                                                   parameters));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("project.error.project.document.save.failed",
                                                   parameters),
                                 request);
                    }
                }
            }
            projectDocumentListIndex++;
        }
    }

    private void lookupCooridinatesAndSetOnForm(ProjectForm projectForm, ClientValue clientValue, HttpServletRequest request)
    {
        LOG.info("Lookup Coordinates requested.");
        if (projectForm.getParcelNumber()
            != null
            && projectForm.getParcelNumber()
                   .trim()
                   .length()
               > 0)
        {
            EcGISCoord
                gisCoord =
                EcGISCoord.findUniqueByParcelNumber(clientValue,
                                                    StringUtil.trimCharLeft(projectForm.getParcelNumber(),
                                                                            '0'));
            if (gisCoord
                != null)
            {
                LOG.info("Found coordinates for parcel number = "
                         + projectForm.getParcelNumber());
                projectForm.setGisX(gisCoord.getGisX());
                projectForm.setGisY(gisCoord.getGisY());
            }
            else
            {
                LOG.info("Count not find coordinates for parcel number = "
                         + projectForm.getParcelNumber());
                Object[]
                    parameters =
                    {projectForm.getParcelNumber()};
                addError(new ActionMessage("project.coordinate.lookup.not.found",
                                           parameters),
                         request);
                projectForm.setGisX("");
                projectForm.setGisY("");
            }
        }
    }

    private boolean isLookupCoordinates(ProjectForm projectForm)
    {
        return projectForm.getSubmit()
               != null
               && projectForm.getSubmit()
            .equals("Lookup Coordinates");
    }

    private ActionForward processDelete(ActionMapping mapping, HttpServletRequest request, ProjectForm projectForm, UserValue userValue, EcProject project)
    {
        LOG.info("Project Delete Request");
        projectForm.reset();
        project.setProjectStatus(StatusCodeData.STATUS_CODE_DELETED);
        project.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        project.save(userValue);
        clearProjectListFromSession(request);
        addMessage(new ActionMessage("project.delete.success"),
                   request);
        return mapping.findForward("project.list.page");
    }

    private boolean isDeleted(ProjectForm projectForm)
    {
        return projectForm.getSubmit()
               != null
               && projectForm.getSubmit()
            .equalsIgnoreCase("Delete");
    }

    private EcProject loadProject(ProjectForm projectForm)
    {
        EcProject
            project =
            new EcProject();
        LOG.debug("hidden project ID on form ="
                  + projectForm.getId());
        project.setId(projectForm.getId());
        project.retrieveByPrimaryKeys();
        return project;
    }

    private ClientData loadClientData(ClientValue clientValue)
    {
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientValue.getId());
        clientData.load();
        return clientData;
    }
}