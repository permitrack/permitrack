package com.sehinc.erosioncontrol.service.impl;

import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.code.ContactTypeData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.inspection.InspectionForm;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.service.InspectorService;
import com.sehinc.erosioncontrol.value.project.ProjectValue;

import java.util.HashSet;
import java.util.List;

public class InspectorServiceImpl
    implements InspectorService
{
    public List<CapContact> getAuthorizedInspectors(ProjectValue projectValue, Integer clientId)
    {
        return InspectionService.getInspectorsForProject(projectValue,
                                                         clientId);
    }

    public static CapContact getInspectorFromForm(InspectionForm form, UserValue userValue)
        throws Exception
    {
        CapContact
            inspector =
            new CapContact();
        if (form.getSelectedInspector()
            != null)
        {
            if (form.getSelectedInspector()
                > 0)
            {
                inspector.setId(form.getSelectedInspector());
                inspector.load();
            }
            else if (form.getSelectedInspector()
                     == -1)
            {
                AddressData
                    address =
                    new AddressData();
                address.setName1(form.getAddContactFirstName());
                address.setName2(form.getAddContactLastName());
                address.setLine1(form.getAddContactAddress1());
                address.setLine2(form.getAddContactAddress2());
                address.setCity(form.getAddContactCity());
                address.setState(form.getAddContactState());
                address.setPostalCode(form.getAddContactZip());
                address.insert(userValue);
                CapContactOrganization
                    org =
                    CapContactOrganization.findByClientId(form.getAddClientId());
                inspector.setFirstName(form.getAddContactFirstName());
                inspector.setLastName(form.getAddContactLastName());
                inspector.setEmail(form.getAddContactEmail());
                inspector.setPrimaryPhone(form.getAddContactPhone());
                inspector.setAddress(form.getAddContactAddress1());
                inspector.setAddress2(form.getAddContactAddress2());
                inspector.setCity(form.getAddContactCity());
                inspector.setStateCode(form.getAddContactState());
                inspector.setZip(form.getAddContactZip());
                inspector.setOrganization(org);
                inspector.setOrganizationName(org.getName());
                inspector.setAddressData(address);
                inspector.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                ContactTypeData
                    contactType =
                    (ContactTypeData) SpringServiceLocator.getLookupService()
                        .fetchCode("INSP",
                                   ContactTypeData.class);
                HashSet
                    typeList =
                    new HashSet();
                typeList.add(contactType);
                inspector.setContactTypes(typeList);
                inspector.insert(userValue);
                CapClientContact
                    ccontact =
                    new CapClientContact();
                ccontact.setContact(inspector);
                ccontact.setClient(new ClientData(form.getAddClientId()));
                ccontact.insert();
            }
        }
        else
        {
            throw new Exception("getSelectedInspector() returned 0.");
        }
        return inspector;
    }
}
