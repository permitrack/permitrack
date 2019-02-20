package com.sehinc.erosioncontrol.server.client;

import com.sehinc.common.db.client.CapClientTypeInfo;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.client.EcClientRelationship;
import com.sehinc.erosioncontrol.db.client.EcClientRelationshipType;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.value.client.ClientRelationshipTypeValue;
import com.sehinc.erosioncontrol.value.client.PartnerValue;
import com.sehinc.erosioncontrol.value.client.PartnerValueNameComparator;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import java.util.*;

public class EcClientService
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcClientService.class);

    public EcClientService()
    {
    }

    public static List getClientRelationshipTypeValueList()
    {
        List
            results =
            new ArrayList();
        Object
            parameters
            [
            ]
            [
            ] =
            {};
        String
            CLIENT_RELATIONSHIP_TYPE_BY_ID_DESC =
            "com.sehinc.erosioncontrol.db.client.EcClientRelationshipType.findAllSortedByIdDesc";
        Iterator
            i =
            HibernateUtil.findByNamedQuery(CLIENT_RELATIONSHIP_TYPE_BY_ID_DESC,
                                           parameters)
                .iterator();
        while (i.hasNext())
        {
            EcClientRelationshipType
                ecClientRelationshipType =
                (EcClientRelationshipType) i.next();
            results.add(new ClientRelationshipTypeValue(ecClientRelationshipType));
        }
        return results;
    }

    public static List getSubordinatePartnerValueList(ClientValue clientValue)
    {
        TreeSet
            results =
            new TreeSet(new PartnerValueNameComparator());
        Object
            parameters
            [
            ] =
            {
                clientValue.getId(),
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select relationship from com.sehinc.common.db.client.ClientData as client, com.sehinc.common.db.client.ClientData as relatedClient, com.sehinc.erosioncontrol.db.client.EcClientRelationship as relationship where relationship.clientId = client.id and relationship.relatedClientId = relatedClient.id and client.id = ? and relatedClient.status.code = ?";
        List
            relationships =
            HibernateUtil.find(queryString,
                               parameters);
        Iterator
            relationshipIterator =
            relationships.iterator();
        while (relationshipIterator.hasNext())
        {
            PartnerValue
                partnerValue =
                new PartnerValue();
            EcClientRelationship
                clientRelationship =
                (EcClientRelationship) relationshipIterator.next();
            partnerValue.setClientRelationshipInfo(clientRelationship);
            ClientData
                clientData =
                ClientService.getActiveClientById(clientRelationship.getRelatedClientId());
            partnerValue.setClientInfo(clientData);
            UserData
                userData =
                UserService.getUser(clientRelationship.getRelatedClientUserId());
            partnerValue.setUserInfo(userData);
            results.add(partnerValue);
        }
        return new ArrayList(results);
    }

    public static String getNewClientShortName(String clientName)
    {
        int
            i =
            1;
        boolean
            found =
            false;
        String
            theName =
            StringUtil.stripNonAlphaNumericCharactersExceptSpaces(clientName);
        while (!found)
        {
            Object
                parameters
                [
                ] =
                {theName};
            ClientData
                clientData =
                (ClientData) HibernateUtil.findUnique("select data from com.sehinc.common.db.client.ClientData as data where data.shortName = ?",
                                                      parameters);
            if (clientData
                == null)
            {
                found =
                    true;
            }
            else
            {
                if (theName.length()
                    == 50
                    && i
                       > 9)
                {
                    theName =
                        StringUtil.replaceAt(theName,
                                             theName.length()
                                             - 2,
                                             new Integer(i).toString());
                }
                else
                {
                    theName =
                        StringUtil.replaceAt(theName,
                                             theName.length()
                                             - 1,
                                             new Integer(i).toString());
                }
            }
            i++;
        }
        return theName;
    }

    public static CapClientTypeInfo getClientType(UserValue userValue)
    {
        Object
            parameters
            [
            ] =
            {userValue.getId()};
        String
            queryString =
            "select clientType from com.sehinc.common.db.client.CapClientTypeInfo as clientTypeInfo, "
            +
            "com.sehinc.common.db.client.CapClientType as clientType "
            +
            "where clientType.clientId = ? and clientType.clientType.id = clientTypeInfo.id";
        return (CapClientTypeInfo) HibernateUtil.findUnique(queryString,
                                                            parameters);
    }

    public static boolean isClientTypePrimary()
    {
        String
            name =
            "select clientTypeInfo.name from com.sehinc.common.db.client.CapClientTypeInfo as clientTypeInfo, "
            +
            "com.sehinc.common.db.client.CapClientType as clientType "
            +
            "where clientType.clientId = ? and clientType.clientTypeId = clientTypeInfo.id";
        if (name.equalsIgnoreCase("primary"))
        {
            return true;
        }
        return false;
    }

    public static boolean isClientRelationshipPreferred(ClientValue clientValue)
    {
        List
            results;
        Object
            parameters
            [
            ] =
            {clientValue.getId()};
        String
            queryString =
            "select clientRelationship from com.sehinc.erosioncontrol.db.client.EcClientRelationship as clientRelationship, com.sehinc.erosioncontrol.db.client.EcClientRelationshipType as clientRelationshipType where clientRelationship.relatedClientId = ? and clientRelationship.clientRelationshipType.id = clientRelationshipType.id and clientRelationshipType.code = 'PREFERRED'";
        results =
            HibernateUtil.find(queryString,
                               parameters);
        if (results.size()
            > 0)
        {
            return true;
        }
        return false;
    }

    public static List findPartners(ClientValue clientValue, String name, String address, String city, String state, String postalCode)
        throws Exception
    {
        /*
                LOG.debug("clientValue="
                          + clientValue.getName()
                          + " name="
                          + name
                          + " address="
                          + address
                          + " city="
                          + city
                          + " state="
                          + state
                          + " postalCode="
                          + postalCode);
        */
        StringBuffer
            whereClause =
            new StringBuffer();
        TreeSet
            results =
            new TreeSet(new PartnerValueNameComparator());
        if (name
            == null
            && address
               == null
            && city
               == null
            && state
               == null
            && postalCode
               == null)
        {
            return new ArrayList();
        }
        if (name
            != null
            && name.trim()
                   .length()
               > 0)
        {
            whereClause.append(" AND C.NAME LIKE '%"
                               + name
                               + "%' ");
        }
        if (address
            != null
            && address.trim()
                   .length()
               > 0)
        {
            whereClause.append(" AND (A.LINE1 LIKE '%"
                               + address
                               + "%' OR A.LINE2 LIKE '%"
                               + address
                               + "%') ");
        }
        if (city
            != null
            && city.trim()
                   .length()
               > 0)
        {
            whereClause.append(" AND A.CITY LIKE '%"
                               + city
                               + "%' ");
        }
        if (state
            != null
            && state.trim()
                   .length()
               > 0)
        {
            whereClause.append(" AND A.STATE LIKE '%"
                               + state
                               + "%' ");
        }
        if (postalCode
            != null
            && postalCode.trim()
                   .length()
               > 0)
        {
            whereClause.append(" AND A.POSTAL_CODE LIKE '%"
                               + postalCode
                               + "%' ");
        }
        Object
            parameters
            [
            ] =
            {
                clientValue.getId(),
                StatusCodeData.STATUS_CODE_ACTIVE,
                clientValue.getId()};
        String
            queryString =
            "SELECT C.ID "
            +
            "FROM [CLIENT] C INNER JOIN CLIENT_MODULE CM ON C.ID = CM.CLIENT_ID "
            +
            "INNER JOIN CAP_MODULE M ON CM.MODULE_ID = M.ID "
            +
            "LEFT OUTER JOIN ADDRESS A ON C.ADDRESS_ID = A.ID "
            +
            "WHERE C.ID <> ? AND "
            +
            "      C.STATUS_CD = ? AND "
            +
            "      M.CODE = 'EC' "
            +
            whereClause.toString()
            + " "
            +
            "AND C.ID NOT IN "
            +
            "(SELECT CR.RELATED_CLIENT_ID "
            +
            " FROM EC_CLIENT_RELATIONSHIP CR "
            +
            " WHERE CR.CLIENT_ID = ?)";
        Hashtable
            scalars =
            new Hashtable();
        scalars.put("ID",
                    Hibernate.INTEGER);
        List
            partnerList =
            HibernateUtil.findSQL(queryString,
                                  scalars,
                                  parameters);
        LOG.debug("found "
                  + partnerList.size()
                  + " partners.");
        Iterator
            partnerIterator =
            partnerList.iterator();
        CapContactType
            mainContactType =
            CapContactType.getMainContactType();
        while (partnerIterator.hasNext())
        {
            PartnerValue
                partnerValue =
                new PartnerValue();
            Integer
                partnerClientId =
                (Integer) partnerIterator.next();
            ClientData
                partnerClientData =
                new ClientData(partnerClientId);
            try
            {
                partnerClientData.load();
                partnerValue.setClientInfo(partnerClientData);
                partnerValue.setUserInfo(UserService.getUserByContactType(partnerClientData.getId(),
                                                                          mainContactType));
            }
            catch (Exception e)
            {
                LOG.error("findPartners(): \n"
                          + e.getMessage());
            }
            results.add(partnerValue);
        }
        return new ArrayList(results);
    }

    public static List<Integer> findPrimaryClients(ClientValue value)
    {
        String
            sql =
            "SELECT C.ID "
            +
            "FROM [CLIENT] C "
            +
            "WHERE C.ID IN "
            +
            "(SELECT distinct CR.CLIENT_ID "
            +
            " FROM EC_CLIENT_RELATIONSHIP CR "
            +
            " WHERE CR.RELATED_CLIENT_ID = ?)";
        Object
            parameters
            [
            ] =
            {value.getId()};
        Hashtable
            scalars =
            new Hashtable();
        scalars.put("ID",
                    Hibernate.INTEGER);
        return HibernateUtil.findSQL(sql,
                                     scalars,
                                     parameters);
    }
}