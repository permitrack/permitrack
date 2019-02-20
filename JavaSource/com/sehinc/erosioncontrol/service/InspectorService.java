package com.sehinc.erosioncontrol.service;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.erosioncontrol.value.project.ProjectValue;

import java.util.List;

public interface InspectorService
{
    List<CapContact> getAuthorizedInspectors(ProjectValue projectValue, Integer clientId);
}
