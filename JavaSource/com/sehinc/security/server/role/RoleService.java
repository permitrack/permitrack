package com.sehinc.security.server.role;

import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.security.CapRoleSecureObjectPermission;
import com.sehinc.common.db.security.CapSecureObject;
import com.sehinc.common.value.role.RoleSecureObjectPermissionValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class RoleService
{
    private static
    Logger
        LOG =
        Logger.getLogger(RoleService.class);

    public RoleService()
    {
    }

    public static ArrayList getOrderedPermissionData(Integer moduleId)
        throws Exception
    {
        return getOrderedPermissionsData(null,
                                         moduleId);
    }

    public static ArrayList getOrderedPermissionsData(CapRole role, Integer moduleId)
        throws Exception
    {
        HashMap
            enabledSOPHash =
            new HashMap();
        if (role
            != null)
        {
            Iterator
                roleSOPIter =
                CapRoleSecureObjectPermission.findByRoleId(role.getId())
                    .iterator();
            while (roleSOPIter.hasNext())
            {
                CapRoleSecureObjectPermission
                    roleSOP =
                    (CapRoleSecureObjectPermission) roleSOPIter.next();
                SecureObjectPermissionData
                    sop =
                    SecureObjectPermissionData.getSecureObjectPermissionData(roleSOP.getSecureObjectId(),
                                                                             roleSOP.getPermissionId());
                if (sop
                    != null)
                {
                    enabledSOPHash.put(sop.getIndex(),
                                       true);
                }
            }
        }
        Collection
            allExistingSOPs =
            SecureObjectPermissionData.getCRUDByModule(moduleId);
        Iterator
            allExistingIterator =
            allExistingSOPs.iterator();
        ArrayList
            all_rows =
            new ArrayList();
        HashMap
            theUsed =
            new HashMap();
        while (allExistingIterator.hasNext())
        {
            SecureObjectPermissionData
                possibleSOP =
                (SecureObjectPermissionData) allExistingIterator.next();
            Integer
                secureObjId =
                possibleSOP.getSecureObjectID();
            String
                used =
                (String) theUsed.get(secureObjId);
            if (used
                == null)
            {
                Collection
                    rowOfSOPs =
                    SecureObjectPermissionData.getCRUDBySO(secureObjId);
                Iterator
                    rowIterator =
                    rowOfSOPs.iterator();
                ArrayList
                    my_columns =
                    new ArrayList();
                while (rowIterator.hasNext())
                {
                    SecureObjectPermissionData
                        rowSOP =
                        (SecureObjectPermissionData) rowIterator.next();
                    Integer
                        rowIndex =
                        rowSOP.getIndex();
                    Integer
                        rowPID =
                        possibleSOP.getPermissionID();
                    Integer
                        rowSOID =
                        possibleSOP.getSecureObjectID();
                    CapSecureObject
                        secureObjectData =
                        new CapSecureObject(rowSOID);
                    if (!secureObjectData.load())
                    {
                        throw new Exception("Could not load CapSecureObject with ID "
                                            + rowSOID);
                    }
                    String
                        soName =
                        secureObjectData.getName();
                    RoleSecureObjectPermissionValue
                        roleSOPVal =
                        new RoleSecureObjectPermissionValue();
                    roleSOPVal.setPId(rowPID);
                    roleSOPVal.setSoId(rowSOID);
                    roleSOPVal.setSoName(soName);
                    roleSOPVal.setSopId(rowIndex);
                    // check if possible id exists in enabledHash, to see if it is selected
                    Boolean
                        exists =
                        (Boolean) enabledSOPHash.get(rowIndex);
                    if (exists
                        != null)
                    {
                        if (exists)
                        { // exists is set to "true" which means that this permission is enabled
                            roleSOPVal.setSelected(true);
                        }
                    }
                    // Put completed, filled roleSOPVal into the all_rows list
                    my_columns.add(roleSOPVal);
                }
                all_rows.add(my_columns);
                // Mark this SOID as "used", meaning we put all the permissions
                // for that object into the data structure already.
                theUsed.put(secureObjId,
                            "used");
            }
        }
        return all_rows;
    }
}