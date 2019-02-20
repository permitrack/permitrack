package com.sehinc.environment.action.facility;

import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.facility.EnvFacilityContact;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.db.lookup.EnvFacilityContactTypeData;
import com.sehinc.environment.value.FacilityContactValue;

import java.util.ArrayList;
import java.util.List;

public class FacilityService
{
    public FacilityService()
    {
    }

    public static FacilityForm populateFacilityForm(FacilityForm facilityForm, EnvFacility envFacility)
        throws Exception
    {
        facilityForm.setId(envFacility.getId());
        facilityForm.setName(envFacility.getName());
        facilityForm.setNumber(envFacility.getNumber());
        facilityForm.setDescription(envFacility.getDescription());
        facilityForm.setActiveTsText(envFacility.getActiveTsText());
        facilityForm.setInactiveTsText(envFacility.getInactiveTsText());
        facilityForm.setPhone(envFacility.getPhone());
        facilityForm.setFax(envFacility.getFax());
        facilityForm.setCountyName(envFacility.getCountyName());
        facilityForm.setSicCode(envFacility.getSicCode());
        facilityForm.setClassDesc(envFacility.getClassDesc());
        facilityForm.setDailyHrsOp(envFacility.getDailyHrsOp());
        facilityForm.setDaysOpWeek(envFacility.getDaysOpWeek());
        facilityForm.setWeeksOpYear(envFacility.getWeeksOpYear());
        facilityForm.setBusinessHrs(envFacility.getBusinessHrs());
        if (envFacility.getPhysicalAddressId()
            != null
            && envFacility.getPhysicalAddressId()
               != 0)
        {
            AddressData
                physAddr =
                new AddressData(envFacility.getPhysicalAddressId());
            physAddr.load();
            facilityForm.setPhysAddr1(physAddr.getLine1());
            facilityForm.setPhysAddr2(physAddr.getLine2());
            facilityForm.setPhysAddr3(physAddr.getLine3());
            facilityForm.setPhysAddrCity(physAddr.getCity());
            facilityForm.setPhysAddrState(physAddr.getState());
            facilityForm.setPhysAddrZip(physAddr.getPostalCode());
        }
        if (envFacility.getMailingAddressId()
            != null
            && envFacility.getMailingAddressId()
               != 0)
        {
            AddressData
                mailAddr =
                new AddressData(envFacility.getMailingAddressId());
            mailAddr.load();
            facilityForm.setMailAddr1(mailAddr.getLine1());
            facilityForm.setMailAddr2(mailAddr.getLine2());
            facilityForm.setMailAddrCity(mailAddr.getCity());
            facilityForm.setMailAddrState(mailAddr.getState());
            facilityForm.setMailAddrZip(mailAddr.getPostalCode());
        }
        return facilityForm;
    }

    public static Integer saveFacilityInformation(FacilityForm form, UserValue objUser, ClientValue objClient)
        throws Exception
    {
        return saveFacilityInformation(form,
                                       null,
                                       objUser,
                                       objClient);
    }

    public static Integer saveFacilityInformation(FacilityForm facilityForm, EnvFacility facility, UserValue objUser, ClientValue objClient)
        throws Exception
    {
        if (facility
            == null)
        {
            facility =
                new EnvFacility();
        }
        facility.setClientId(objClient.getId());
        facility.setName(facilityForm.getName());
        facility.setNumber(facilityForm.getNumber());
        facility.setDescription(facilityForm.getDescription());
        facility.setActiveTs(DateUtil.parseDate(facilityForm.getActiveTsText()));
        facility.setInactiveTs(DateUtil.parseDate(facilityForm.getInactiveTsText()));
        facility.setPhone(facilityForm.getPhone());
        facility.setFax(facilityForm.getFax());
        facility.setCountyName(facilityForm.getCountyName());
        facility.setSicCode(facilityForm.getSicCode());
        facility.setClassDesc(facilityForm.getClassDesc());
        facility.setDailyHrsOp(facilityForm.getDailyHrsOp());
        facility.setDaysOpWeek(facilityForm.getDaysOpWeek());
        facility.setWeeksOpYear(facilityForm.getWeeksOpYear());
        facility.setBusinessHrs(facilityForm.getBusinessHrs());
        facility.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
        AddressData
            physAddr =
            new AddressData();
        if (facility.getPhysicalAddressId()
            != null)
        {
            physAddr.setId(facility.getPhysicalAddressId());
            physAddr.load();
        }
        physAddr.setLine1(facilityForm.getPhysAddr1());
        physAddr.setLine2(facilityForm.getPhysAddr2());
        physAddr.setLine3(facilityForm.getPhysAddr3());
        physAddr.setCity(facilityForm.getPhysAddrCity());
        physAddr.setState(facilityForm.getPhysAddrState());
        physAddr.setPostalCode(facilityForm.getPhysAddrZip());
        Integer
            physAddrId =
            physAddr.save(objUser);
        AddressData
            mailAddr =
            new AddressData();
        if (facility.getMailingAddressId()
            != null)
        {
            mailAddr.setId(facility.getMailingAddressId());
            mailAddr.load();
        }
        mailAddr.setLine1(facilityForm.getMailAddr1());
        mailAddr.setLine2(facilityForm.getMailAddr2());
        mailAddr.setCity(facilityForm.getMailAddrCity());
        mailAddr.setState(facilityForm.getMailAddrState());
        mailAddr.setPostalCode(facilityForm.getMailAddrZip());
        Integer
            mailAddrId =
            mailAddr.save(objUser);
        facility.setPhysicalAddressId(physAddrId);
        facility.setMailingAddressId(mailAddrId);
        return facility.save(objUser);
    }

    public static List getFacilityContacts(Integer facilityId)
        throws Exception
    {
        ArrayList<FacilityContactValue>
            contactList =
            new ArrayList<FacilityContactValue>();
        List
            fcList =
            EnvFacilityContact.findAllByFacilityId(facilityId);
        for (Object fc : fcList)
        {
            EnvFacilityContact
                fContact =
                (EnvFacilityContact) fc;
            FacilityContactValue
                contactValue =
                new FacilityContactValue(fContact);
            if (fContact.getRoleCd()
                != null
                && fContact.getRoleCd()
                   != 0)
            {
                EnvFacilityContactTypeData
                    type =
                    EnvFacilityContactTypeData.findByTypeCode(fContact.getRoleCd());
                contactValue.setRoleDesc(type.getDescription());
            }
            contactList.add(contactValue);
        }
        return contactList;
    }

    public static ArrayList getFacilityAssets(Integer facilityId)
        throws Exception
    {
        ArrayList<EnvFacilityAsset>
            assetList =
            new ArrayList<EnvFacilityAsset>();
        List
            fcList =
            EnvFacilityAsset.findByFacilityId(facilityId);
        for (Object a : fcList)
        {
            EnvFacilityAsset
                fAsset =
                (EnvFacilityAsset) a;
            EnvAsset
                asset =
                new EnvAsset(fAsset.getAssetId());
            asset.load();
            if (asset
                != null)
            {
                fAsset.setName(asset.getName());
                fAsset.setDescription(asset.getDescription());
                fAsset.setNumber(asset.getNumber());
            }
            assetList.add(fAsset);
        }
        return assetList;
    }
}