package com.sehinc.erosioncontrol.util;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.action.project.ProjectForm;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectContactType;
import com.sehinc.erosioncontrol.db.project.EcProjectType;
import com.sehinc.erosioncontrol.db.project.EcZone;
import com.sehinc.erosioncontrol.server.project.ProjectService;

public class ProjectUtil
{
    public static ProjectForm loadProjectIntoForm(EcProject project, ProjectForm projectForm)
    {
        projectForm.setId(project.getId());
        projectForm.setOwnerClientId(project.getOwnerClient()
                                         .getId());
        projectForm.setOwnerClientName(project.getOwnerClient()
                                           .getName());
        projectForm.setPermitAuthorityClientId(project.getPermitAuthorityClient()
                                                   .getId());
        projectForm.setPermitAuthorityClientName(project.getPermitAuthorityClient()
                                                     .getName());
        projectForm.setPermitAuthorityClientContact(ProjectService.getProjectContactValueByProjectContactTypeCode(project.getId(),
                                                                                                                  EcProjectContactType.PERMIT_AUTHORITY));
        projectForm.setPermittedClientId(project.getPermittedClient()
                                             .getId());
        projectForm.setPermittedClientName(project.getPermittedClient()
                                               .getName());
        projectForm.setPermittedClientContact(ProjectService.getProjectContactValueByProjectContactTypeCode(project.getId(),
                                                                                                            EcProjectContactType.PERMITTEE));
        if (project.getAuthorizedInspectorClient()
            != null)
        {
            projectForm.setAuthorizedInspectorClientId(project.getAuthorizedInspectorClient()
                                                           .getId());
            projectForm.setAuthorizedInspectorClientName(project.getAuthorizedInspectorClient()
                                                             .getName());
            projectForm.setAuthorizedInspectorClientContact(ProjectService.getProjectContactValueByProjectContactTypeCode(project.getId(),
                                                                                                                          EcProjectContactType.AUTHORIZED_INSPECTOR));
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
        projectForm.setZoneName(project.getZone()
                                    .getName());
        projectForm.setStatusCode(project.getStatus()
                                      .getCode());
        projectForm.setProjectStatusCode(project.getProjectStatus()
                                             .getCode());
        projectForm.setProjectStatusCodeDesc(project.getProjectStatus()
                                                 .getDescription());
        projectForm.setCompletedDate(DateUtil.mdyDate(project.getCompletedDate()));
        projectForm.setRfaNumber(project.getRfaNumber());
        projectForm.setBlockNumber(project.getBlockNumber());
        projectForm.setLotNumber(project.getLotNumber());
        projectForm.setLotNumber(project.getLotNumber());
        projectForm.setInspectionFrequency(project.getInspectionFrequency());
        projectForm.setInspectionOverdueNotificationEnabled(project.getInspectionOverdueNotificationEnabled());
        projectForm.setInspectionOverdueInitialThreshold(project.getInspectionOverdueInitialThreshold());
        projectForm.setInspectionOverdueInitialMessage(project.getInspectionOverdueInitialMessage());
        projectForm.setSecondInspectionOverdueNotificationEnabled(project.getSecondInspectionOverdueNotificationEnabled());
        projectForm.setInspectionOverdueSecondThreshold(project.getInspectionOverdueSecondThreshold());
        projectForm.setInspectionOverdueSecondMessage(project.getInspectionOverdueSecondMessage());
        return projectForm;
    }

    public static void loadFormIntoProject(ProjectForm projectForm, EcProject project)
    {
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
        project.setZone((EcZone) HibernateUtil.load(EcZone.class,
                                                    projectForm.getZoneId()));
        project.setStatusCode(projectForm.getStatusCode());
        project.setProjectStatus(projectForm.getProjectStatusCode());
        project.setGisX(projectForm.getGisX());
        project.setGisY(projectForm.getGisY());
        project.setCompletedDate(DateUtil.parseDate(projectForm.getCompletedDate()));
        project.setRfaNumber(projectForm.getRfaNumber());
        project.setBlockNumber(projectForm.getBlockNumber());
        project.setLotNumber(projectForm.getLotNumber());
        project.setInspectionFrequency(projectForm.getInspectionFrequency());
        project.setInspectionOverdueNotificationEnabled(projectForm.isInspectionOverdueNotificationEnabled());
        project.setInspectionOverdueInitialThreshold(projectForm.getInspectionOverdueInitialThreshold());
        project.setInspectionOverdueInitialMessage(projectForm.getInspectionOverdueInitialMessage());
        project.setSecondInspectionOverdueNotificationEnabled(projectForm.isSecondInspectionOverdueNotificationEnabled());
        project.setInspectionOverdueSecondThreshold(projectForm.getInspectionOverdueSecondThreshold());
        project.setInspectionOverdueSecondMessage(projectForm.getInspectionOverdueSecondMessage());
    }
}
