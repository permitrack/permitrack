package com.sehinc.environment.action.asset;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.util.LabelValueBean;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.AssetValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AssetListPageAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetListPageAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In AssetListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Assets",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.ASSET_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.ASSET_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_DELETE),
                            request);
        Integer
            facilityId =
            (Integer) getFacility(request);
        EnvFacility
            facility =
            new EnvFacility(facilityId);
        String
            facilityName;
        try
        {
            facility.load();
            facilityName =
                facility.getName();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("facility.error.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("facility.error.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        List<EnvFacilityAsset>
            facilityAssetList =
            EnvFacilityAsset.findByFacilityId(facilityId);
        List<EnvAsset>
            assetList =
            new ArrayList<EnvAsset>();
        for (EnvFacilityAsset fa : facilityAssetList)
        {
            try
            {
                EnvAsset
                    asset =
                    new EnvAsset(fa.getAssetId());
                asset.load();
                if (asset.getParentAssetId()
                    == null
                    && asset.getStatus()
                    .getCode()
                    .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                {
                    assetList.add(asset);
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {e.getMessage()};
                addError(new ActionMessage("asset.error.load.failed",
                                           parameters), request);
                LOG.error(ApplicationResources.getProperty("asset.error.load.failed",
                                                           parameters));
                return mapping.getInputForward();
            }
        }
        List<AssetValue>
            aValueList =
            new ArrayList<AssetValue>();
        for (Object o : assetList)
        {
            EnvAsset
                parentAsset =
                (EnvAsset) o;
            List
                firstChildren =
                EnvAsset.findAllByParentId(parentAsset.getId());
            List<AssetValue>
                fcValueList =
                new ArrayList<AssetValue>();
            for (Object f : firstChildren)
            {
                EnvAsset
                    firstChild =
                    (EnvAsset) f;
                List
                    secondChildren =
                    EnvAsset.findAllByParentId(firstChild.getId());
                List<AssetValue>
                    scValueList =
                    new ArrayList<AssetValue>();
                for (Object s : secondChildren)
                {
                    EnvAsset
                        secondChild =
                        (EnvAsset) s;
                    AssetValue
                        secondAssetValue =
                        new AssetValue(secondChild);
                    secondAssetValue.setAssetTypes(secondChild.getAssetTypes());
                    secondAssetValue.setMeterNumberName(getMeterNumberName(secondAssetValue.getId()));
                    scValueList.add(secondAssetValue);
                }
                AssetValue
                    firstAssetValue =
                    new AssetValue(firstChild);
                firstAssetValue.setSubAssets(scValueList);
                firstAssetValue.setAssetTypes(firstChild.getAssetTypes());
                firstAssetValue.setMeterNumberName(getMeterNumberName(firstAssetValue.getId()));
                fcValueList.add(firstAssetValue);
            }
            AssetValue
                parentAssetValue =
                new AssetValue(parentAsset);
            parentAssetValue.setSubAssets(fcValueList);
            parentAssetValue.setAssetTypes(parentAsset.getAssetTypes());
            parentAssetValue.setMeterNumberName(getMeterNumberName(parentAssetValue.getId()));
            aValueList.add(parentAssetValue);
        }
        request.getSession()
            .setAttribute(SessionKeys.EV_FACILITY_NAME,
                          facilityName);
        request.getSession()
            .setAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_BY_CLIENT,
                          createAssetDisplayList(aValueList,
                                                 (AssetForm) form,
                                                 request));
        setSessionAttribute(SessionKeys.EV_ASSET_ID,
                            null,
                            request);
        return mapping.findForward("continue");
    }

    private List createAssetDisplayList(List assetList, AssetForm form, HttpServletRequest request)
    {
        Integer
            currentPage =
            determineCurrentPage(assetList,
                                 form,
                                 request);
        Integer
            assetsPerPage =
            determineAssetsPerPage(request,
                                   form);
        int
            startIndex =
            (currentPage
             - 1)
            * (assetsPerPage);
        int
            endIndex =
            (currentPage)
            * (assetsPerPage);
        if (endIndex
            > assetList.size())
        {
            endIndex =
                assetList.size();
        }
        if (indexesOutOfSynch(startIndex,
                              endIndex,
                              assetList.size()))
        {
            currentPage =
                1;
            startIndex =
                0;
            endIndex =
                resetEndIndex(assetList,
                              assetsPerPage);
        }
        request.getSession()
            .setAttribute(SessionKeys.EV_ASSET_LIST_CURRENT_PAGE,
                          currentPage);
        setPageControlList(assetList,
                           assetsPerPage,
                           request,
                           currentPage);
        return assetList.subList(startIndex,
                                 endIndex);
    }

    private void setPageControlList(List assetList, Integer assetsPerPage, HttpServletRequest request, Integer currentPage)
    {
        request.getSession()
            .setAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_PAGE_CONTROL,
                          getAssetControlList(currentPage,
                                              calculateTotalPages(assetList,
                                                                  assetsPerPage)));
    }

    private List<LabelValueBean> getAssetControlList(Integer currentPage, Integer totalPages)
    {
        if (totalPages
            <= 1)
        {
            return null;
        }
        ArrayList<LabelValueBean>
            pageControl =
            new ArrayList<LabelValueBean>();
        pageControl.add(new LabelValueBean("<<",
                                           "F"));
        pageControl.add(new LabelValueBean("<",
                                           "P"));
        for (
            int
                i =
                1; i
                   <= totalPages; i++)
        {
            if (i
                == currentPage)
            {
                pageControl.add(new LabelValueBean(""
                                                   + i,
                                                   "true"));
            }
            else
            {
                pageControl.add(new LabelValueBean(""
                                                   + i,
                                                   ""
                                                   + i));
            }
        }
        pageControl.add(new LabelValueBean(">",
                                           "N"));
        pageControl.add(new LabelValueBean(">>",
                                           "L"));
        return pageControl;
    }

    private boolean indexesOutOfSynch(int startIndex, int endIndex, int i)
    {
        if ((startIndex
             < 0
             || (endIndex
                 > i))
            || startIndex
               > endIndex)
        {
            LOG.debug("startIndex="
                      + startIndex
                      + " endIndex="
                      + endIndex
                      + " assetList.size()="
                      + i);
            return true;
        }
        return false;
    }

    private int resetEndIndex(List assetList, Integer assetsPerPage)
    {
        int
            endIndex;
        if (assetList.size()
            < assetsPerPage)
        {
            endIndex =
                assetList.size();
        }
        else
        {
            endIndex =
                assetsPerPage;
        }
        return endIndex;
    }

    private Integer determineCurrentPage(List assetList, AssetForm form, HttpServletRequest request)
    {
        boolean
            assetsPerPageChanged =
            isAssetsPerPageChanged(form,
                                   request);
        Integer
            assetsPerPage =
            determineAssetsPerPage(request,
                                   form);
        Integer
            currentPage =
            getCurrentPage(assetList,
                           request,
                           assetsPerPage);
        if (currentPage
            == null
            || currentPage
               <= 0
            || assetsPerPageChanged)
        {
            currentPage =
                1;
        }
        return currentPage;
    }

    private Integer getCurrentPage(List assetList, HttpServletRequest request, Integer assetsPerPage)
    {
        Integer
            currentPage;
        if (request.getParameter(RequestKeys.EV_ASSET_LIST_CURRENT_PAGE)
            != null)
        {
            currentPage =
                decodeCurrentPageAttribute(calculateTotalPages(assetList,
                                                               assetsPerPage),
                                           request);
        }
        else
        {
            currentPage =
                (Integer) request.getSession()
                    .getAttribute(SessionKeys.EV_ASSET_LIST_CURRENT_PAGE);
        }
        return currentPage;
    }

    private Integer decodeCurrentPageAttribute(int totalPages, HttpServletRequest request)
    {
        String
            currentPageAttribute =
            request.getParameter(RequestKeys.EV_ASSET_LIST_CURRENT_PAGE);
        Integer
            currentPage =
            (Integer) request.getSession()
                .getAttribute(SessionKeys.EV_ASSET_LIST_CURRENT_PAGE);
        if (currentPage
            == null)
        {
            currentPage =
                1;
        }
        if (currentPageAttribute.equalsIgnoreCase("F"))
        {
            return 1;
        }
        else if (currentPageAttribute.equalsIgnoreCase("P"))
        {
            if (currentPage
                > 1)
            {
                return currentPage
                       - 1;
            }
            else
            {
                return 1;
            }
        }
        else if (currentPageAttribute.equalsIgnoreCase("N"))
        {
            if (currentPage
                < totalPages)
            {
                return currentPage
                       + 1;
            }
            else
            {
                return totalPages;
            }
        }
        else if (currentPageAttribute.equalsIgnoreCase("L"))
        {
            return totalPages;
        }
        else
        {
            try
            {
                Integer
                    thePage =
                    Integer.decode(currentPageAttribute);
                if (thePage
                    > 0
                    && thePage
                       <= totalPages)
                {
                    return thePage;
                }
            }
            catch (NumberFormatException nfe)
            {
                LOG.error(nfe);
                LOG.error("Invalid page attribute: "
                          + currentPageAttribute);
            }
        }
        return null;
    }

    private int calculateTotalPages(List assetList, Integer assetsPerPage)
    {
        int
            totalPages =
            assetList.size()
            / assetsPerPage;
        if ((assetList.size()
             % assetsPerPage)
            > 0)
        {
            totalPages++;
        }
        return totalPages;
    }

    private boolean isAssetsPerPageChanged(AssetForm form, HttpServletRequest request)
    {
        if (form.getAssetsPerPage()
            == null
            || form.getAssetsPerPage()
            .equals(request.getSession()
                        .getAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_ASSETS_PER_PAGE)))
        {
            return false;
        }
        return true;
    }

    private Integer determineAssetsPerPage(HttpServletRequest request, AssetForm form)
    {
        Integer
            assetsPerPage;
        if (form.getAssetsPerPage()
            != null
            && form.getAssetsPerPage()
               > 0)
        {
            assetsPerPage =
                form.getAssetsPerPage();
        }
        else
        {
            assetsPerPage =
                (Integer) request.getSession()
                    .getAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_ASSETS_PER_PAGE);
        }
        if (assetsPerPage
            == null
            || assetsPerPage
               <= 0)
        {
            assetsPerPage =
                new Integer(25);
        }
        request.getSession()
            .setAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_ASSETS_PER_PAGE,
                          assetsPerPage);
        return assetsPerPage;
    }

    private String getMeterNumberName(Integer assetId)
    {
        StringBuffer
            meterNumberName =
            new StringBuffer();
        if (assetId
            != null)
        {
            EnvAsset
                asset =
                new EnvAsset(assetId);
            asset.load();
            Integer
                meterId =
                asset.getBelongsToMeter();
            if (meterId
                != null)
            {
                EnvAsset
                    meterAsset =
                    new EnvAsset(meterId);
                meterAsset.load();
                meterNumberName.append(meterAsset.getNumber());
                meterNumberName.append(" ");
                meterNumberName.append(meterAsset.getName());
            }
        }
        return meterNumberName.toString();
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.ASSET_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.ASSET_LIST_MENU_ITEM);
    }
}