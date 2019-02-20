package com.sehinc.erosioncontrol.db.event;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import org.apache.log4j.Logger;

import java.util.*;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class EcEvent
    extends StatusData
    implements Comparable
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcEvent.class);
    private
    EcEventType
        eventType;
    private
    Date
        eventDate;
    private
    String
        description;
    private
    String
        notice;
    private
    Date
        complianceDate;
    private
    Integer
        complianceHours;
    private
    ClientData
        client;

    public EcEvent()
    {
    }

    public EcEvent(Integer id)
    {
        setId(id);
    }

    public EcEventType getEventType()
    {
        return this.eventType;
    }

    public void setEventType(EcEventType eventType)
    {
        this.eventType =
            eventType;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getNotice()
    {
        return this.notice;
    }

    public void setNotice(String notice)
    {
        this.notice =
            notice;
    }

    public Date getEventDate()
    {
        return this.eventDate;
    }

    public void setEventDate(Date eventDate)
    {
        this.eventDate =
            eventDate;
    }

    public Date getComplianceDate()
    {
        return this.complianceDate;
    }

    public void setComplianceDate(Date date)
    {
        this.complianceDate =
            date;
    }

    public Integer getComplianceHours()
    {
        return this.complianceHours;
    }

    public void setComplianceHours(Integer hours)
    {
        this.complianceHours =
            hours;
    }

    public ClientData getClient()
    {
        return this.client;
    }

    public void setClient(ClientData client)
    {
        this.client =
            client;
    }

    public static List findByProjectOwner(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            query =
            " select event from EcEvent as event where event.id in "
            + " ( select distinct event.id from EcEvent as event, EcProject as project, EcEventProject as eventProject "
            + "   where event.id = eventProject.eventId "
            + "   and eventProject.projectId = project.id "
            + "   and project.ownerClient.id = ? "
            + " )";
        return HibernateUtil.find(query,
                                  parameters);
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            query =
            " select event from EcEvent as event where event.client.id = ?";
        TreeSet
            treeSet =
            new TreeSet(HibernateUtil.find(query,
                                           parameters));
        Object
            parameters2
            [
            ] =
            {
                clientId,
                clientId,
                clientId,
                clientId};
        query =
            " select distinct event from EcEvent as event, EcEventProject as eventProject, EcProject as project "
            + " where event.id = eventProject.eventId and project.id = eventProject.projectId "
            + "       and (project.ownerClient.id = ? or project.permitAuthorityClient.id = ? or "
            + "            project.permittedClient.id = ? or project.authorizedInspectorClient.id = ?)";
        treeSet.addAll(HibernateUtil.find(query,
                                          parameters2));
        ArrayList
            results =
            new ArrayList(treeSet);
        Collections.sort(results,
                         Collections.reverseOrder(new EventDateComparator()));
        return results;
    }

    public static List findProjectsByEventId(Integer eventId)
    {
        Object
            parameters
            [
            ] =
            {eventId};
        String
            query =
            " select project from EcEventProject as eventProject, EcProject as project "
            + " where eventProject.eventId = ? "
            + "   and eventProject.projectId = project.id ";
        return HibernateUtil.find(query,
                                  parameters);
    }

    public String toString()
    {
        return "EcEvent:[eventType="
               + eventType
               + ",eventDate="
               + eventDate
               + ",description="
               + description
               + ",notice="
               + notice
               + ",complianceDate="
               + complianceDate
               + ",complianceHours="
               + complianceHours
               + "]";
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof EcEvent)
        {
            return getId().compareTo(((EcEvent) obj).getId());
        }
        return 0;
    }
}