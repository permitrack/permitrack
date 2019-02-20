package com.sehinc.environment.action.permit;

import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.permitfacility.EnvPermitFacility;
import com.sehinc.environment.db.permitsubstance.EnvPermitSubstance;
import com.sehinc.environment.value.PermittedSubstanceValue;

import java.util.ArrayList;
import java.util.List;

public class PermitService
{
    public PermitService()
    {
    }

    public static ArrayList findPermittedSubstances(Integer permitId)
        throws Exception
    {
        ArrayList<PermittedSubstanceValue>
            pmtSubstanceTypeList =
            new ArrayList<PermittedSubstanceValue>();
        List
            psList =
            EnvPermitSubstance.findByPermitId(permitId);
        for (Object pSub : psList)
        {
            EnvPermitSubstance
                ps =
                (EnvPermitSubstance) pSub;
            PermittedSubstanceValue
                psVal =
                new PermittedSubstanceValue();
            psVal.setPermitId(ps.getPermitId());
            psVal.setPermitSubstanceId(ps.getId());
            psVal.setSubstanceType(ps.getSubstanceType());
            psVal.setChargeable(ps.getChargeable());
            pmtSubstanceTypeList.add(psVal);
        }
        return pmtSubstanceTypeList;
    }

    public static ArrayList findPermittedFacilities(Integer permitId)
        throws Exception
    {
        ArrayList<EnvPermitFacility>
            pmtFacilityList =
            new ArrayList<EnvPermitFacility>();
        List
            facilityList =
            EnvPermitFacility.findByPermitId(permitId);
        for (Object a : facilityList)
        {
            EnvPermitFacility
                pFacility =
                (EnvPermitFacility) a;
            EnvFacility
                facility =
                new EnvFacility(pFacility.getFacilityId());
            facility.load();
            if (facility
                != null)
            {
                pFacility.setName(facility.getName());
                pFacility.setDescription(facility.getDescription());
            }
            pmtFacilityList.add(pFacility);
        }
        return pmtFacilityList;
    }
}
