package com.sehinc.erosioncontrol.server.correctiveaction;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.correctiveaction.EcCorrectiveAction;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CorrectiveActionService
{
    private static
    Logger
        LOG =
        Logger.getLogger(CorrectiveActionService.class);
    public static
    String
        CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_ASC =
        "CAbyProjNameAsc";
    public static
    String
        CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_DESC =
        "CAbyProjNameDesc";
    public static
    String
        CORRECTIVE_ACTION_LIST_BY_BMP_NAME_ASC =
        "CAbyBMPNameAsc";
    public static
    String
        CORRECTIVE_ACTION_LIST_BY_BMP_NAME_DESC =
        "CAbyBMPNameDesc";
    public static
    String
        CORRECTIVE_ACTION_LIST_BY_LAST_INSPECTION_DATE_ASC =
        "CAbyInspDateAsc";
    public static
    String
        CORRECTIVE_ACTION_LIST_BY_LAST_INSPECTION_DATE_DESC =
        "CAbyInspDateDesc";

    public CorrectiveActionService()
    {
    }

    public static List getCorrectiveActionList(SecurityManager securityManager, ClientValue clientValue, UserValue userValue, String query)
    {
        List
            correctiveActionList =
            new ArrayList();
        List
            caList =
            getCorrectiveActions(query,
                                 clientValue.getId());
        Iterator
            cai =
            caList.iterator();
        while (cai.hasNext())
        {
            try
            {
                EcCorrectiveAction
                    cAction =
                    (EcCorrectiveAction) cai.next();
                LOG.debug(cAction.toString());
                EcProject
                    project =
                    EcProject.findById(cAction.getProjectId());
                ProjectValue
                    projectValue =
                    ProjectService.getProjectValue(project,
                                                   clientValue,
                                                   userValue,
                                                   securityManager);
                cAction.setProjectValue(projectValue);
                correctiveActionList.add(cAction);
            }
            catch (Exception e)
            {
                LOG.error("Failed to load corrective action data: ["
                          + e.getMessage()
                          + "]");
            }
        }
        return correctiveActionList;
    }

    private static List getCorrectiveActions(String query, Integer clientId)
    {
        StringBuffer
            fullQuery =
            new StringBuffer();
        fullQuery.append("select b.id, i.inspection_date, i.status_cd, i.bmp_status, ");
        fullQuery.append("       p.id as projectId, p.name, b.bmp_category_name, b.bmp_name, b.comment ");
        fullQuery.append("from last_final_inspections as i ");
        fullQuery.append("inner join ec_project as p ");
        fullQuery.append("on p.id = i.project_id ");
        fullQuery.append("inner join ec_inspection_bmp as b ");
        fullQuery.append("on i.id = b.inspection_id ");
        fullQuery.append("where bmp_status = 'FAIL' ");
        fullQuery.append("and p.project_status_cd = 1 ");
        fullQuery.append("and ( p.owner_client_id =:clientId ");
        fullQuery.append("or p.permit_authority_client_id =:clientId ");
        fullQuery.append("or p.permitted_client_id =:clientId ");
        fullQuery.append("or p.authorized_inspector_client_id =:clientId ) ");
        fullQuery.append("and b.inspection_bmp_condition_id <> 2 and b.inspection_bmp_condition_id <> 1 ");
        fullQuery.append("and b.is_inspected = 1 ");
        if (query.equals(CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_ASC))
        {
            fullQuery.append("order by p.name asc");
        }
        else if (query.equals(CORRECTIVE_ACTION_LIST_BY_PROJECT_NAME_DESC))
        {
            fullQuery.append("order by p.name desc");
        }
        else if (query.equals(CORRECTIVE_ACTION_LIST_BY_BMP_NAME_ASC))
        {
            fullQuery.append("order by b.bmp_name asc");
        }
        else if (query.equals(CORRECTIVE_ACTION_LIST_BY_BMP_NAME_DESC))
        {
            fullQuery.append("order by b.bmp_name desc");
        }
        else if (query.equals(CORRECTIVE_ACTION_LIST_BY_LAST_INSPECTION_DATE_ASC))
        {
            fullQuery.append("order by i.inspection_date asc");
        }
        else if (query.equals(CORRECTIVE_ACTION_LIST_BY_LAST_INSPECTION_DATE_DESC))
        {
            fullQuery.append("order by i.inspection_date desc");
        }
        SQLQuery
            queryE =
            HibernateUtil.getNewSession()
                .createSQLQuery(fullQuery.toString());
        queryE.addEntity(EcCorrectiveAction.class);
        queryE.setInteger("clientId",
                          clientId);
        return queryE.list();
    }
}