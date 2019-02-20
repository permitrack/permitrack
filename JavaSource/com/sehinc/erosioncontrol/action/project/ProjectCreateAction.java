package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.option.DataElementOptionValueService;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.gis.EcGISCoord;
import com.sehinc.erosioncontrol.db.project.*;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.project.ProjectBmpValue;
import com.sehinc.erosioncontrol.value.project.ProjectContactValue;
import com.sehinc.erosioncontrol.value.project.ProjectDocumentValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Hashtable;

public class ProjectCreateAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectCreateAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws Exception
    {
        ProjectForm
            projectForm =
            (ProjectForm) form;
        LOG.info("In ProjectCreateAction");
        ClientData
            clientData =
            loadClient(clientValue);
        String
            action =
            (String) request.getAttribute(RequestKeys.ACTION_PARAMETER);
        if (action
            == null)
        {
            LOG.info("action = "
                     + action);
            return mapping.findForward("project.list.page");
        }
        if (isCancelled(request))
        {
            return processCancelled(mapping,
                                    projectForm);
        }
        if ("createstep1".equals(action))
        {
            return processStepOne(mapping,
                                  request,
                                  projectForm,
                                  userValue,
                                  clientValue);
        }
        if ("createstep2".equals(action))
        {
            return processStepTwo(mapping,
                                  request,
                                  projectForm,
                                  userValue,
                                  clientValue,
                                  clientData,
                                  securityManager);
        }
        if ("createstep3".equals(action))
        {
            return processStepThree(mapping,
                                    request,
                                    projectForm,
                                    userValue);
        }
        return (mapping.findForward("continue"));
    }

    private ActionForward processStepThree(ActionMapping mapping, HttpServletRequest request, ProjectForm projectForm, UserValue userValue)
    {
        LOG.debug("createstep3");
        EcProject
            project =
            new EcProject();
        ProjectValue
            projectValue =
            (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                               request);
        if (projectValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("project.error.project.not.found.in.session"));
            addError(new ActionMessage("project.error.project.not.found.in.session"),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        project.setId(projectValue.getId());
        try
        {
            project.load();
        }
        catch (HibernateException he)
        {
            Object[]
                parameters =
                {projectValue.getId()};
            LOG.error(ApplicationResources.getProperty("project.create.error.could.not.load.project",
                                                       parameters));
            LOG.error(he.getMessage());
            addError(new ActionMessage("project.create.error.could.not.load.project",
                                       parameters),
                     request);
            return (mapping.findForward("project.list.page"));
        }
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
                    }
                    projectBmp.save(userValue);
                }
            }
            projectBmpListIndex++;
        }
        addMessage(new ActionMessage("project.create.success"),
                   request);
        if (project.getStatus()
            .getCode()
            .equals(StatusCodeData.STATUS_CODE_ACTIVE))
        {
            return mapping.findForward("project.email.page");
        }
        return (mapping.findForward("continue"));
    }

    private ActionForward processStepTwo(ActionMapping mapping, HttpServletRequest request, ProjectForm projectForm, UserValue userValue, ClientValue clientValue, ClientData clientData, SecurityManager securityManager)
    {
        LOG.debug("createstep2");
        EcProject
            project =
            new EcProject();
        LOG.debug("ProjectForm.getId()="
                  + projectForm.getId());
        if (projectForm.getId()
            != null
            && projectForm.getId()
               > 0)
        {
            project.setId(projectForm.getId());
            try
            {
                project.load();
            }
            catch (HibernateException he)
            {
                Object[]
                    parameters =
                    {projectForm.getId()};
                LOG.error(ApplicationResources.getProperty("project.create.error.could.not.load.project",
                                                           parameters));
                LOG.error(he.getMessage());
                addError(new ActionMessage("project.create.error.could.not.load.project",
                                           parameters),
                         request);
                return (mapping.findForward("project.list.page"));
            }
        }
        String
            submitType =
            projectForm.getSubmit();
        if (submitType
            != null
            && submitType.equals("Lookup Coordinates"))
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
                    project.setGisX(gisCoord.getGisX());
                    project.setGisY(gisCoord.getGisY());
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
                    project.setGisX("");
                    project.setGisY("");
                }
            }
        }
        else
        {
            project.setGisX(projectForm.getGisX());
            project.setGisY(projectForm.getGisY());
        }
        LOG.debug("PermitAuth="
                  + projectForm.getPermitAuthorityClientId());
        LOG.debug("Permittee="
                  + projectForm.getPermittedClientId());
        project.setOwnerClient(clientData);
        project.setPermitAuthorityClient((ClientData) HibernateUtil.load(ClientData.class,
                                                                         projectForm.getPermitAuthorityClientId()));
        project.setPermittedClient((ClientData) HibernateUtil.load(ClientData.class,
                                                                   projectForm.getPermittedClientId()));
        if (projectForm.getAuthorizedInspectorClientId()
            != null
            && projectForm.getAuthorizedInspectorClientId()
               > 0)
        {
            project.setAuthorizedInspectorClient((ClientData) HibernateUtil.load(ClientData.class,
                                                                                 projectForm.getAuthorizedInspectorClientId()));
        }
        LOG.debug("ProjectType="
                  + projectForm.getProjectTypeId());
        project.setProjectType((EcProjectType) HibernateUtil.load(EcProjectType.class,
                                                                  projectForm.getProjectTypeId()));
        project.setName(projectForm.getName());
        project.setDescription(projectForm.getDescription());
        project.setParcelNumber(projectForm.getParcelNumber());
        project.setPermitNumber(projectForm.getPermitNumber());
        project.setAddress(projectForm.getAddress());
        project.setCity(projectForm.getCity());
        project.setState(projectForm.getState());
        project.setZip(projectForm.getZip());
        project.setTotalSiteArea(projectForm.getTotalSiteArea());
        project.setTotalSiteAreaUnits(projectForm.getTotalSiteAreaUnits());
        project.setDisturbedArea(projectForm.getDisturbedArea());
        project.setDisturbedAreaUnits(projectForm.getDisturbedAreaUnits());
        project.setNewImperviousArea(projectForm.getNewImperviousArea());
        project.setNewImperviousAreaUnits(projectForm.getNewImperviousAreaUnits());
        project.setEffectiveDate(projectForm.getEffectiveDate());
        project.setStartDate(projectForm.getStartDate());
        project.setSeedDate(projectForm.getSeedDate());
        project.setRfaNumber(projectForm.getRfaNumber());
        project.setBlockNumber(projectForm.getBlockNumber());
        project.setLotNumber(projectForm.getLotNumber());
        project.setZone((EcZone) HibernateUtil.load(EcZone.class,
                                                    projectForm.getZoneId()));
        project.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        project.setProjectStatus((ProjectStatusCodeData) SpringServiceLocator.getLookupService()
            .fetchCode(projectForm.getProjectStatusCode(),
                       ProjectStatusCodeData.class));
        try
        {
            project.save(userValue);
            project.load();
        }
        catch (HibernateException he)
        {
            LOG.error(ApplicationResources.getProperty("project.create.error.save.failed"));
            LOG.error(he.getMessage());
            addError(new ActionMessage("project.create.error.save.failed"),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        LOG.debug("ProjectId after save="
                  + project.getId());
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
                            {projectDocumentValue.toString()};
                        LOG.error(ApplicationResources.getProperty("project.create.error.document.save.failed",
                                                                   parameters));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("project.create.error.document.save.failed",
                                                   parameters),
                                 request);
                        return mapping.findForward("project.list.page");
                    }
                }
            }
            projectDocumentListIndex++;
        }
        Hashtable
            projectContactList =
            projectForm.getContacts()
                .getAllContacts();
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
                                    contactOrganization.setRefClientId(projectContactValue.getOrgRefClientId());
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
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            ProjectService.getProjectValue(project,
                                                           clientValue,
                                                           userValue,
                                                           securityManager),
                            request);
        //At this point, if the user clicked "Lookup Coordinates" we should return
        //to the create page.  We must first put the project ID and inspection template ID on the request
        LOG.debug("projectForm.getInspectionTemplateId() = "
                  + projectForm.getInspectionTemplateId());
        if (submitType
            != null
            && submitType.equals("Lookup Coordinates"))
        {
            request.setAttribute(RequestKeys.EC_PROJECT_ID,
                                 project.getId());
            if (projectForm.getInspectionTemplateId()
                != null
                && projectForm.getInspectionTemplateId()
                   > 0)
            {
                request.setAttribute(RequestKeys.EC_INSPECTION_TEMPLATE_ID,
                                     projectForm.getInspectionTemplateId());
            }
            return (mapping.findForward("project.create.page"));
        }
        //If the user clicked "Save" or "Continue", then we should copy
        //the BMP template into the project BMPs
        Integer
            bmpTemplateId =
            projectForm.getInspectionTemplateId();
        if (bmpTemplateId
            != null
            && bmpTemplateId
               > 0)
        {
            for (Object o : InspectionService.getProjectBmpValuesByInspectionTemplateId(bmpTemplateId,
                                                                                        clientValue.getId()))
            {
                ProjectBmpValue
                    projectBmpValue =
                    (ProjectBmpValue) o;
                LOG.debug(projectBmpValue.toString());
                EcProjectBmp
                    projectBmp =
                    new EcProjectBmp();
                projectBmp.setProjectId(project.getId());
                projectBmp.setBmpName(projectBmpValue.getBmpName());
                projectBmp.setCategoryName(projectBmpValue.getBmpCategoryName());
                projectBmp.setIsRequired(projectBmpValue.getIsRequired());
                projectBmp.setDescription(projectBmpValue.getBmpDescription());
                projectBmp.insert(userValue);
            }
        }
        // Clear the authorized project list from the session
        request.getSession()
            .removeAttribute(SessionKeys.EC_AUTHORIZED_PROJECT_LIST);
        // If the user clicked "Continue" then we will forward to the
        // next page (step3) in the create process where they can edit
        // the project BMPs.
        // If the user clicked "Save" then we are done.
        LOG.debug("SubmitType = "
                  + submitType);
        if (submitType
            != null
            && submitType.equals("Continue"))
        {
            setSessionAttribute(SessionKeys.EC_PROJECT_INSPECTION_TEMPLATE_BMP_LIST,
                                ProjectService.getProjectBmpValueList(project.getId()),
                                request);
            setSessionAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT,
                                EcBmp.findByClientId(clientValue.getId()),
                                request);
            return (mapping.findForward("continue"));
        }
        else
        {
            addMessage(new ActionMessage("project.create.success"),
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
            return (mapping.findForward("project.list.page"));
        }
    }

    private ActionForward processStepOne(ActionMapping mapping, HttpServletRequest request, ProjectForm projectForm, UserValue userValue, ClientValue clientValue)
    {
        LOG.debug("createstep1 - creating project.");
        Integer
            projectId =
            setProjectId(request);
        EcProject
            project =
            loadProject(projectId);
        if (project
            != null)
        {
            projectForm.setId(project.getId());
            projectForm.setOwnerClientId(project.getOwnerClient()
                                             .getId());
            projectForm.setPermitAuthorityClientId(project.getPermitAuthorityClient()
                                                       .getId());
            projectForm.setPermittedClientId(project.getPermittedClient()
                                                 .getId());
            if (project.getAuthorizedInspectorClient()
                != null)
            {
                projectForm.setAuthorizedInspectorClientId(project.getAuthorizedInspectorClient()
                                                               .getId());
            }
            projectForm.setProjectTypeId(project.getProjectType()
                                             .getId());
            projectForm.setProjectTypeName(project.getProjectType()
                                               .getName());
            projectForm.setName(project.getName());
            projectForm.setDescription(project.getDescription());
            projectForm.setParcelNumber(project.getParcelNumber());
            projectForm.setPermitNumber(project.getPermitNumber());
            projectForm.setGisX(project.getGisX());
            projectForm.setGisY(project.getGisY());
            projectForm.setAddress(project.getAddress());
            projectForm.setCity(project.getCity());
            projectForm.setState(project.getState());
            projectForm.setZip(project.getZip());
            projectForm.setAreaSize(project.getAreaSize());
            projectForm.setTotalSiteArea(project.getTotalSiteArea());
            projectForm.setTotalSiteAreaUnits(project.getTotalSiteAreaUnits());
            projectForm.setDisturbedArea(project.getDisturbedArea());
            projectForm.setDisturbedAreaUnits(project.getDisturbedAreaUnits());
            projectForm.setNewImperviousArea(project.getNewImperviousArea());
            projectForm.setNewImperviousAreaUnits(project.getNewImperviousAreaUnits());
            projectForm.setEffectiveDate(project.getEffectiveDate());
            projectForm.setStartDate(project.getStartDate());
            projectForm.setSeedDate(project.getSeedDate());
            projectForm.setZoneId(project.getZone()
                                      .getId());
            projectForm.setStatusCode(project.getStatus()
                                          .getCode());
            projectForm.setRfaNumber(project.getRfaNumber());
            projectForm.setBlockNumber(project.getBlockNumber());
            projectForm.setLotNumber(project.getLotNumber());
            if (request.getAttribute(RequestKeys.EC_INSPECTION_TEMPLATE_ID)
                != null)
            {
                projectForm.setInspectionTemplateId((Integer) request.getAttribute(RequestKeys.EC_INSPECTION_TEMPLATE_ID));
            }
            setSessionAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST,
                                ProjectService.getProjectContactValueList(project.getId(),
                                                                          false),
                                request);
            //This attribute holds the project contacts associated with the project roles such as PERMIT_AUTH, PERMITTEE, AUTH_INSPECTOR
            setSessionAttribute(SessionKeys.EC_INTERNAL_PROJECT_CONTACT_LIST,
                                ProjectService.getProjectContactValueList(project.getId(),
                                                                          true),
                                request);
            setSessionAttribute(SessionKeys.EC_PROJECT_DOCUMENT_LIST,
                                ProjectService.getProjectDocumentValueList(project.getId(),
                                                                           clientValue.getId(),
                                                                           userValue.getUsername(),
                                                                           request),
                                request);
        }
        else
        {
            projectForm.reset();
            setSessionAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST,
                                new ArrayList(),
                                request);
            setSessionAttribute(SessionKeys.EC_INTERNAL_PROJECT_CONTACT_LIST,
                                new ArrayList(),
                                request);
            setSessionAttribute(SessionKeys.EC_PROJECT_DOCUMENT_LIST,
                                new ArrayList(),
                                request);
        }
        setSessionAttribute(SessionKeys.EC_PERMIT_AUTHORITY_PROJECT_CONTACT_TYPE_ID,
                            ProjectService.getProjectContactTypeByCode(EcProjectContactType.PERMIT_AUTHORITY)
                                .getId(),
                            request);
        setSessionAttribute(SessionKeys.EC_PERMITTED_PROJECT_CONTACT_TYPE_ID,
                            ProjectService.getProjectContactTypeByCode(EcProjectContactType.PERMITTEE)
                                .getId(),
                            request);
        setSessionAttribute(SessionKeys.EC_AUTHORIZED_INSPECTOR_PROJECT_CONTACT_TYPE_ID,
                            ProjectService.getProjectContactTypeByCode(EcProjectContactType.AUTHORIZED_INSPECTOR)
                                .getId(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_TEMPLATE_LIST,
                            InspectionService.getInspectionTemplateValueList(clientValue),
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_TYPE_LIST,
                            ProjectService.getProjectTypeLabelValueList(clientValue),
                            request);
        setSessionAttribute(SessionKeys.EC_STATE_LIST,
                            UserService.getStateValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_CLIENT_CONTACT_LIST,
                            ClientService.getClientContactValueList(clientValue),
                            request);
        setSessionAttribute(SessionKeys.EC_CONTACT_TYPE_LIST,
                            ProjectService.getProjectContactTypeValueListByClientId(clientValue.getId()),
                            request);
        setSessionAttribute(SessionKeys.EC_AUTHORIZED_CLIENT_LIST,
                            ProjectService.getAuthorizedClientLabelValueList(clientValue),
                            request);
        setSessionAttribute(SessionKeys.EC_PERMIT_AUTHORITY_CLIENT_LIST,
                            ProjectService.getPermitAuthorityClientLabelValueList(clientValue),
                            request);
        setSessionAttribute(SessionKeys.EC_ZONE_LIST,
                            ProjectService.getZonesByClientLabelValueList(clientValue),
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_AREA_UNITS_LIST,
                            DataElementOptionValueService.getECOptionsList(ProjectDataElementOptionValue.AREA_UNITS),
                            request);
        request.setAttribute(SessionKeys.PROJECT_STATUS_CODE_LIST,
                             SpringServiceLocator.getLookupService()
                                 .fetchCodesDisplayable(ProjectStatusCodeData.class));
        int
            authorities =
            ProjectService.getPermitAuthorityClientLabelValueList(clientValue)
                .size();
        int
            types =
            ProjectService.getProjectTypeLabelValueList(clientValue)
                .size();
        if (types
            == 0)
        {
            addError(new ActionMessage("project.error.insufficient.data.project.types"),
                     request);
        }
        if (authorities
            == 0)
        {
            addError(new ActionMessage("project.error.insufficient.data.contacts"),
                     request);
        }
        return (mapping.findForward("continue"));
    }

    private EcProject loadProject(Integer projectId)
    {
        EcProject
            project =
            null;
        if (projectId
            != null)
        {
            project =
                new EcProject();
            project.setId(projectId);
            project.load();
        }
        return project;
    }

    private Integer setProjectId(HttpServletRequest request)
    {
        Integer
            projectId =
            null;
        if (request.getAttribute(RequestKeys.EC_PROJECT_ID)
            != null)
        {
            projectId =
                (Integer) request.getAttribute(RequestKeys.EC_PROJECT_ID);
        }
        return projectId;
    }

    private ActionForward processCancelled(ActionMapping mapping, ProjectForm projectForm)
    {
        LOG.info("Request was CANCELED");
        projectForm.reset();
        return mapping.findForward("project.list.page");
    }

    private ClientData loadClient(ClientValue clientValue)
    {
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientValue.getId());
        clientData.load();
        return clientData;
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PROJECT_CREATE_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_CREATE_MENU_NAME),
                         request);
    }
}