package com.sehinc.environment.action.scc;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.action.scclibrary.SccLibraryForm;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.db.scc.EnvSccInfoLibrary;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SccLibraryImportAction
    extends SccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryImportAction.class);

    public ActionForward sccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SccLibraryImportAction. ";
        String
            strLog =
            strMod
            + "sccAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("scc.import.cancel.action"), request);
            return mapping.findForward("scc.list.page");
        }
        else
        {
            UserValue
                objUser =
                getUserValue(request);
            ClientValue
                clientValue =
                getClientValue(request);
            SccLibraryForm
                sccForm =
                (SccLibraryForm) form;
            Integer[]
                sccLibraryItems =
                sccForm.getSccLibraryItems();
            if (sccLibraryItems
                == null
                || sccLibraryItems.length
                   == 0)
            {
                return mapping.findForward("scc.list.page");
            }
            for (Object o : sccLibraryItems)
            {
                Integer
                    sccLibraryItemId =
                    (Integer) o;
                try
                {
                    EnvSccInfoLibrary
                        sccLib =
                        new EnvSccInfoLibrary(sccLibraryItemId);
                    sccLib.load();
                    EnvSccInfo
                        sccData =
                        new EnvSccInfo();
                    sccData.setClientId(clientValue.getId());
                    sccData.setNumber(sccLib.getNumber());
                    sccData.setDescription(sccLib.getDescription());
                    sccData.setMajIndustrialGroup(sccLib.getMajIndustrialGroup());
                    sccData.setRawMaterial(sccLib.getRawMaterial());
                    sccData.setEmittingProcess(sccLib.getEmittingProcess());
                    sccData.setSccLibraryId(sccLib.getId());
                    Integer
                        intSccId =
                        sccData.save(objUser);
                    LOG.debug(strLog
                              + "New SccInfo created from library with ID = "
                              + intSccId.toString());
                }
                catch (Exception e)
                {
                    LOG.error(ApplicationResources.getProperty("scc.error.import.failed"));
                    LOG.error(e.getMessage());
                    addError(new ActionMessage("scc.error.import.failed"), request);
                    return mapping.findForward("scc.list.page");
                }
            }
            addMessage(new ActionMessage("scc.import.success.action"), request);
            return mapping.findForward("continue");
        }
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SCC_LIST_MENU),
                        request);
    }
}
