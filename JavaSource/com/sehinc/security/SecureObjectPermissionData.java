package com.sehinc.security;

import com.sehinc.common.db.security.CapPermission;
import com.sehinc.common.db.security.CapSecureObject;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;

public class SecureObjectPermissionData
    implements Comparable, Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(SecureObjectPermissionData.class);
    private
    Integer
        mintSecureObjectID =
        0;
    private
    Integer
        mintPermissionID =
        0;
    public final static
    SecureObjectPermissionData
        USER_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_USER,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        USER_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_USER,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        USER_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_USER,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        USER_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_USER,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        USER_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_USER,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        CLIENT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_CLIENT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        CLIENT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_CLIENT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        CLIENT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_CLIENT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        CLIENT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_CLIENT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        CLIENT_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_CLIENT,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        CONTACT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_CONTACT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        CONTACT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_CONTACT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        CONTACT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_CONTACT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        CONTACT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_CONTACT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        CONTACT_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_CONTACT,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        ROLE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_ROLE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        ROLE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_ROLE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        ROLE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_ROLE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        ROLE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_ROLE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        ROLE_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_ROLE,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        USER_ROLE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_USER_ROLE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        USER_ROLE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_USER_ROLE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        USER_ROLE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_USER_ROLE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        USER_ROLE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_USER_ROLE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        USER_ROLE_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_USER_ROLE,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        BMP_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        BMP_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        BMP_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        BMP_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        BMP_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        BMP_TEMPLATE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_TEMPLATE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        BMP_TEMPLATE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_TEMPLATE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        BMP_TEMPLATE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_TEMPLATE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        BMP_TEMPLATE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_TEMPLATE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        BMP_TEMPLATE_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_TEMPLATE,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        EVENT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EVENT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EVENT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EVENT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EVENT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EVENT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EVENT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EVENT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EVENT_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_EVENT,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        ZONE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_ZONE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        ZONE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_ZONE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        ZONE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_ZONE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        ZONE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_ZONE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        ZONE_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_ZONE,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        PROJECT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_PROJECT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        PROJECT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_PROJECT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        PROJECT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_PROJECT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        PROJECT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_PROJECT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        PROJECT_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_PROJECT,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        INSPECTION_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_INSPECTION,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        INSPECTION_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_INSPECTION,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        INSPECTION_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_INSPECTION,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        INSPECTION_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_INSPECTION,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        INSPECTION_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_INSPECTION,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        INSPECTION_ACTION =
        new SecureObjectPermissionData(SecurityConstants.SO_INSPECTION,
                                       SecurityConstants.PERMISSION_ACTION);
    public final static
    SecureObjectPermissionData
        PARTNER_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_PARTNER,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        PARTNER_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_PARTNER,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        PARTNER_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_PARTNER,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        PARTNER_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_PARTNER,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        PARTNER_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_PARTNER,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        PARTNER_ACTION =
        new SecureObjectPermissionData(SecurityConstants.SO_PARTNER,
                                       SecurityConstants.PERMISSION_ACTION);
    public final static
    SecureObjectPermissionData
        REPORT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_REPORT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        REPORT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_REPORT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        REPORT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_REPORT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        REPORT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_REPORT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        REPORT_STATUS =
        new SecureObjectPermissionData(SecurityConstants.SO_REPORT,
                                       SecurityConstants.PERMISSION_STATUS);
    public final static
    SecureObjectPermissionData
        REPORT_ACTION =
        new SecureObjectPermissionData(SecurityConstants.SO_REPORT,
                                       SecurityConstants.PERMISSION_ACTION);
    public final static
    SecureObjectPermissionData
        SW_PERIOD_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PERIOD,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_PERIOD_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PERIOD,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_PERIOD_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PERIOD,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_PERIOD_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PERIOD,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_PLAN_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PLAN,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_PLAN_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PLAN,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_PLAN_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PLAN,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_PLAN_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_PLAN,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_MCM_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_MCM,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_MCM_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_MCM,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_MCM_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_MCM,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_MCM_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_MCM,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_BMP_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_BMP,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_BMP_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_BMP,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_BMP_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_BMP,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_BMP_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_BMP,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_GOAL_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_GOAL_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_GOAL_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_GOAL_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_GOAL_ACTIVITY_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL_ACTIVITY,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_GOAL_ACTIVITY_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL_ACTIVITY,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_GOAL_ACTIVITY_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL_ACTIVITY,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_GOAL_ACTIVITY_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_GOAL_ACTIVITY,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_REPORT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_REPORT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_REPORT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_REPORT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_REPORT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_REPORT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_REPORT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_REPORT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_ROLE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_ROLE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_ROLE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_ROLE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_ROLE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_ROLE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_ROLE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_ROLE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        SW_USER_ROLE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_USER_ROLE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        SW_USER_ROLE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_USER_ROLE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        SW_USER_ROLE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_USER_ROLE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        SW_USER_ROLE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_SW_USER_ROLE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_DETAIL_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_DETAIL,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_DETAIL_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_DETAIL,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_DETAIL_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_DETAIL,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_DETAIL_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_DETAIL,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_SUBSTANCE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_SUBSTANCE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_SUBSTANCE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_SUBSTANCE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_SUBSTANCE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_SUBSTANCE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_SUBSTANCE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_SUBSTANCE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_ASSET_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SUBSTANCE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SUBSTANCE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SUBSTANCE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SUBSTANCE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SUBSTANCE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SUBSTANCE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SUBSTANCE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SUBSTANCE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SOURCE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SOURCE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SOURCE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SOURCE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SOURCE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SOURCE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SOURCE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SOURCE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_ASSET_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_ASSET,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_ASSET_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_ASSET,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_ASSET_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_ASSET,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_ASSET_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_ASSET,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_CONTACT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_CONTACT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_CONTACT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_CONTACT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_CONTACT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_CONTACT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_CONTACT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_CONTACT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_USAGE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_USAGE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_USAGE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_USAGE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_USAGE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_USAGE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_USAGE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_USAGE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SUBSTANCE_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SUBSTANCE,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SUBSTANCE_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SUBSTANCE,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SUBSTANCE_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SUBSTANCE,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_ASSET_SUBSTANCE_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_ASSET_SUBSTANCE,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_FACILITY_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_FACILITY,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_FACILITY_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_FACILITY,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_FACILITY_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_FACILITY,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PERMIT_FACILITY_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PERMIT_FACILITY,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_ASSET_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_ASSET,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_ASSET_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_ASSET,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_ASSET_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_ASSET,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_FACILITY_ASSET_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_FACILITY_ASSET,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SCC_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SCC_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SCC_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SCC_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SCC_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SCC,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SCC_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SCC,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SCC_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SCC,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SOURCE_SCC_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SOURCE_SCC,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_SCC_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE_SCC,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_SCC_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE_SCC,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_SCC_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE_SCC,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SUBSTANCE_SCC_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SUBSTANCE_SCC,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_REPORT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_REPORT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_REPORT_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_REPORT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_REPORT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_REPORT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_REPORT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_REPORT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        BMP_LIBRARY_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_LIBRARY,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        BMP_LIBRARY_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_LIBRARY,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        BMP_LIBRARY_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_LIBRARY,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        BMP_LIBRARY_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_BMP_LIBRARY,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        RO_REPORT_CREATE =
        new SecureObjectPermissionData(SecurityConstants.RO_REPORT,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        RO_REPORT_READ =
        new SecureObjectPermissionData(SecurityConstants.RO_REPORT,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        RO_REPORT_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.RO_REPORT,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        RO_REPORT_DELETE =
        new SecureObjectPermissionData(SecurityConstants.RO_REPORT,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_SCC_LIBRARY_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC_LIBRARY,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_SCC_LIBRARY_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC_LIBRARY,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_SCC_LIBRARY_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC_LIBRARY,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_SCC_LIBRARY_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_SCC_LIBRARY,
                                       SecurityConstants.PERMISSION_DELETE);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_ASSET_CREATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS_ASSET,
                                       SecurityConstants.PERMISSION_CREATE);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_ASSET_READ =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS_ASSET,
                                       SecurityConstants.PERMISSION_READ);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_ASSET_UPDATE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS_ASSET,
                                       SecurityConstants.PERMISSION_UPDATE);
    public final static
    SecureObjectPermissionData
        EV_PROCESS_ASSET_DELETE =
        new SecureObjectPermissionData(SecurityConstants.SO_EV_PROCESS_ASSET,
                                       SecurityConstants.PERMISSION_DELETE);
    private static final
    Map<Integer, SecureObjectPermissionData>
        INSTANCES =
        new HashMap<Integer, SecureObjectPermissionData>();

    static
    {
        INSTANCES.put(10,
                      PROJECT_CREATE);
        INSTANCES.put(11,
                      PROJECT_READ);
        INSTANCES.put(12,
                      PROJECT_UPDATE);
        INSTANCES.put(13,
                      PROJECT_DELETE);
        INSTANCES.put(14,
                      PROJECT_STATUS);
        INSTANCES.put(20,
                      INSPECTION_CREATE);
        INSTANCES.put(21,
                      INSPECTION_READ);
        INSTANCES.put(22,
                      INSPECTION_UPDATE);
        INSTANCES.put(23,
                      INSPECTION_DELETE);
        INSTANCES.put(24,
                      INSPECTION_STATUS);
        INSTANCES.put(25,
                      INSPECTION_ACTION);
        INSTANCES.put(30,
                      BMP_CREATE);
        INSTANCES.put(31,
                      BMP_READ);
        INSTANCES.put(32,
                      BMP_UPDATE);
        INSTANCES.put(33,
                      BMP_DELETE);
        INSTANCES.put(34,
                      BMP_STATUS);
        INSTANCES.put(40,
                      BMP_TEMPLATE_CREATE);
        INSTANCES.put(41,
                      BMP_TEMPLATE_READ);
        INSTANCES.put(42,
                      BMP_TEMPLATE_UPDATE);
        INSTANCES.put(43,
                      BMP_TEMPLATE_DELETE);
        INSTANCES.put(44,
                      BMP_TEMPLATE_STATUS);
        INSTANCES.put(50,
                      CONTACT_CREATE);
        INSTANCES.put(51,
                      CONTACT_READ);
        INSTANCES.put(52,
                      CONTACT_UPDATE);
        INSTANCES.put(53,
                      CONTACT_DELETE);
        INSTANCES.put(54,
                      CONTACT_STATUS);
        INSTANCES.put(60,
                      ROLE_CREATE);
        INSTANCES.put(61,
                      ROLE_READ);
        INSTANCES.put(62,
                      ROLE_UPDATE);
        INSTANCES.put(63,
                      ROLE_DELETE);
        INSTANCES.put(64,
                      ROLE_STATUS);
        INSTANCES.put(70,
                      EVENT_CREATE);
        INSTANCES.put(71,
                      EVENT_READ);
        INSTANCES.put(72,
                      EVENT_UPDATE);
        INSTANCES.put(73,
                      EVENT_DELETE);
        INSTANCES.put(74,
                      EVENT_STATUS);
        INSTANCES.put(80,
                      USER_CREATE);
        INSTANCES.put(81,
                      USER_READ);
        INSTANCES.put(82,
                      USER_UPDATE);
        INSTANCES.put(83,
                      USER_DELETE);
        INSTANCES.put(84,
                      USER_STATUS);
        INSTANCES.put(90,
                      CLIENT_CREATE);
        INSTANCES.put(91,
                      CLIENT_READ);
        INSTANCES.put(92,
                      CLIENT_UPDATE);
        INSTANCES.put(93,
                      CLIENT_DELETE);
        INSTANCES.put(94,
                      CLIENT_STATUS);
        INSTANCES.put(100,
                      USER_ROLE_CREATE);
        INSTANCES.put(101,
                      USER_ROLE_READ);
        INSTANCES.put(102,
                      USER_ROLE_UPDATE);
        INSTANCES.put(103,
                      USER_ROLE_DELETE);
        INSTANCES.put(104,
                      USER_ROLE_STATUS);
        INSTANCES.put(110,
                      ZONE_CREATE);
        INSTANCES.put(111,
                      ZONE_READ);
        INSTANCES.put(112,
                      ZONE_UPDATE);
        INSTANCES.put(113,
                      ZONE_DELETE);
        INSTANCES.put(114,
                      ZONE_STATUS);
        INSTANCES.put(115,
                      PARTNER_CREATE);
        INSTANCES.put(116,
                      PARTNER_READ);
        INSTANCES.put(117,
                      PARTNER_UPDATE);
        INSTANCES.put(118,
                      PARTNER_DELETE);
        INSTANCES.put(119,
                      PARTNER_STATUS);
        INSTANCES.put(120,
                      PARTNER_ACTION);
        INSTANCES.put(121,
                      REPORT_CREATE);
        INSTANCES.put(122,
                      REPORT_READ);
        INSTANCES.put(123,
                      REPORT_UPDATE);
        INSTANCES.put(124,
                      REPORT_DELETE);
        INSTANCES.put(125,
                      REPORT_STATUS);
        INSTANCES.put(126,
                      REPORT_ACTION);
        INSTANCES.put(200,
                      SW_PERIOD_CREATE);
        INSTANCES.put(201,
                      SW_PERIOD_READ);
        INSTANCES.put(202,
                      SW_PERIOD_UPDATE);
        INSTANCES.put(203,
                      SW_PERIOD_DELETE);
        INSTANCES.put(210,
                      SW_PLAN_CREATE);
        INSTANCES.put(211,
                      SW_PLAN_READ);
        INSTANCES.put(212,
                      SW_PLAN_UPDATE);
        INSTANCES.put(213,
                      SW_PLAN_DELETE);
        INSTANCES.put(220,
                      SW_MCM_CREATE);
        INSTANCES.put(221,
                      SW_MCM_READ);
        INSTANCES.put(222,
                      SW_MCM_UPDATE);
        INSTANCES.put(223,
                      SW_MCM_DELETE);
        INSTANCES.put(224,
                      SW_BMP_CREATE);
        INSTANCES.put(225,
                      SW_BMP_READ);
        INSTANCES.put(226,
                      SW_BMP_UPDATE);
        INSTANCES.put(227,
                      SW_BMP_DELETE);
        INSTANCES.put(230,
                      SW_GOAL_CREATE);
        INSTANCES.put(231,
                      SW_GOAL_READ);
        INSTANCES.put(232,
                      SW_GOAL_UPDATE);
        INSTANCES.put(233,
                      SW_GOAL_DELETE);
        INSTANCES.put(240,
                      SW_GOAL_ACTIVITY_CREATE);
        INSTANCES.put(241,
                      SW_GOAL_ACTIVITY_READ);
        INSTANCES.put(242,
                      SW_GOAL_ACTIVITY_UPDATE);
        INSTANCES.put(243,
                      SW_GOAL_ACTIVITY_DELETE);
        INSTANCES.put(250,
                      SW_REPORT_CREATE);
        INSTANCES.put(251,
                      SW_REPORT_READ);
        INSTANCES.put(252,
                      SW_REPORT_UPDATE);
        INSTANCES.put(253,
                      SW_REPORT_DELETE);
        INSTANCES.put(260,
                      SW_ROLE_CREATE);
        INSTANCES.put(261,
                      SW_ROLE_READ);
        INSTANCES.put(262,
                      SW_ROLE_UPDATE);
        INSTANCES.put(263,
                      SW_ROLE_DELETE);
        INSTANCES.put(260,
                      SW_USER_ROLE_CREATE);
        INSTANCES.put(261,
                      SW_USER_ROLE_READ);
        INSTANCES.put(262,
                      SW_USER_ROLE_UPDATE);
        INSTANCES.put(263,
                      SW_USER_ROLE_DELETE);
        INSTANCES.put(300,
                      EV_PERMIT_CREATE);
        INSTANCES.put(301,
                      EV_PERMIT_READ);
        INSTANCES.put(302,
                      EV_PERMIT_UPDATE);
        INSTANCES.put(303,
                      EV_PERMIT_DELETE);
        INSTANCES.put(310,
                      EV_PERMIT_DETAIL_CREATE);
        INSTANCES.put(311,
                      EV_PERMIT_DETAIL_READ);
        INSTANCES.put(312,
                      EV_PERMIT_DETAIL_UPDATE);
        INSTANCES.put(313,
                      EV_PERMIT_DETAIL_DELETE);
        INSTANCES.put(320,
                      EV_SUBSTANCE_CREATE);
        INSTANCES.put(321,
                      EV_SUBSTANCE_READ);
        INSTANCES.put(322,
                      EV_SUBSTANCE_UPDATE);
        INSTANCES.put(323,
                      EV_SUBSTANCE_DELETE);
        INSTANCES.put(330,
                      EV_PERMIT_SUBSTANCE_CREATE);
        INSTANCES.put(331,
                      EV_PERMIT_SUBSTANCE_READ);
        INSTANCES.put(332,
                      EV_PERMIT_SUBSTANCE_UPDATE);
        INSTANCES.put(333,
                      EV_PERMIT_SUBSTANCE_DELETE);
        INSTANCES.put(340,
                      EV_SOURCE_CREATE);
        INSTANCES.put(341,
                      EV_SOURCE_READ);
        INSTANCES.put(342,
                      EV_SOURCE_UPDATE);
        INSTANCES.put(343,
                      EV_SOURCE_DELETE);
        INSTANCES.put(350,
                      EV_PROCESS_CREATE);
        INSTANCES.put(351,
                      EV_PROCESS_READ);
        INSTANCES.put(352,
                      EV_PROCESS_UPDATE);
        INSTANCES.put(353,
                      EV_PROCESS_DELETE);
        INSTANCES.put(360,
                      EV_ASSET_CREATE);
        INSTANCES.put(361,
                      EV_ASSET_READ);
        INSTANCES.put(362,
                      EV_ASSET_UPDATE);
        INSTANCES.put(363,
                      EV_ASSET_DELETE);
        INSTANCES.put(370,
                      EV_SOURCE_SUBSTANCE_CREATE);
        INSTANCES.put(371,
                      EV_SOURCE_SUBSTANCE_READ);
        INSTANCES.put(372,
                      EV_SOURCE_SUBSTANCE_UPDATE);
        INSTANCES.put(373,
                      EV_SOURCE_SUBSTANCE_DELETE);
        INSTANCES.put(381,
                      EV_ASSET_SOURCE_CREATE);
        INSTANCES.put(382,
                      EV_ASSET_SOURCE_READ);
        INSTANCES.put(383,
                      EV_ASSET_SOURCE_UPDATE);
        INSTANCES.put(384,
                      EV_ASSET_SOURCE_DELETE);
        INSTANCES.put(390,
                      EV_FACILITY_CREATE);
        INSTANCES.put(391,
                      EV_FACILITY_READ);
        INSTANCES.put(392,
                      EV_FACILITY_UPDATE);
        INSTANCES.put(393,
                      EV_FACILITY_DELETE);
        INSTANCES.put(400,
                      EV_PERMIT_ASSET_CREATE);
        INSTANCES.put(401,
                      EV_PERMIT_ASSET_READ);
        INSTANCES.put(402,
                      EV_PERMIT_ASSET_UPDATE);
        INSTANCES.put(403,
                      EV_PERMIT_ASSET_DELETE);
        INSTANCES.put(410,
                      EV_FACILITY_CONTACT_CREATE);
        INSTANCES.put(411,
                      EV_FACILITY_CONTACT_READ);
        INSTANCES.put(412,
                      EV_FACILITY_CONTACT_UPDATE);
        INSTANCES.put(413,
                      EV_FACILITY_CONTACT_DELETE);
        INSTANCES.put(420,
                      EV_SOURCE_USAGE_CREATE);
        INSTANCES.put(421,
                      EV_SOURCE_USAGE_READ);
        INSTANCES.put(422,
                      EV_SOURCE_USAGE_UPDATE);
        INSTANCES.put(423,
                      EV_SOURCE_USAGE_DELETE);
        INSTANCES.put(430,
                      EV_ASSET_SUBSTANCE_CREATE);
        INSTANCES.put(431,
                      EV_ASSET_SUBSTANCE_READ);
        INSTANCES.put(432,
                      EV_ASSET_SUBSTANCE_UPDATE);
        INSTANCES.put(433,
                      EV_ASSET_SUBSTANCE_DELETE);
        INSTANCES.put(440,
                      EV_PERMIT_FACILITY_CREATE);
        INSTANCES.put(441,
                      EV_PERMIT_FACILITY_READ);
        INSTANCES.put(442,
                      EV_PERMIT_FACILITY_UPDATE);
        INSTANCES.put(443,
                      EV_PERMIT_FACILITY_DELETE);
        INSTANCES.put(450,
                      EV_FACILITY_ASSET_CREATE);
        INSTANCES.put(451,
                      EV_FACILITY_ASSET_READ);
        INSTANCES.put(452,
                      EV_FACILITY_ASSET_UPDATE);
        INSTANCES.put(453,
                      EV_FACILITY_ASSET_DELETE);
        INSTANCES.put(460,
                      EV_SCC_CREATE);
        INSTANCES.put(461,
                      EV_SCC_READ);
        INSTANCES.put(462,
                      EV_SCC_UPDATE);
        INSTANCES.put(463,
                      EV_SCC_DELETE);
        INSTANCES.put(480,
                      EV_SOURCE_SCC_CREATE);
        INSTANCES.put(481,
                      EV_SOURCE_SCC_READ);
        INSTANCES.put(482,
                      EV_SOURCE_SCC_UPDATE);
        INSTANCES.put(483,
                      EV_SOURCE_SCC_DELETE);
        INSTANCES.put(490,
                      EV_SUBSTANCE_SCC_CREATE);
        INSTANCES.put(491,
                      EV_SUBSTANCE_SCC_READ);
        INSTANCES.put(492,
                      EV_SUBSTANCE_SCC_UPDATE);
        INSTANCES.put(493,
                      EV_SUBSTANCE_SCC_DELETE);
        INSTANCES.put(500,
                      EV_REPORT_CREATE);
        INSTANCES.put(501,
                      EV_REPORT_READ);
        INSTANCES.put(502,
                      EV_REPORT_UPDATE);
        INSTANCES.put(503,
                      EV_REPORT_DELETE);
        INSTANCES.put(510,
                      BMP_LIBRARY_CREATE);
        INSTANCES.put(511,
                      BMP_LIBRARY_READ);
        INSTANCES.put(512,
                      BMP_LIBRARY_UPDATE);
        INSTANCES.put(513,
                      BMP_LIBRARY_DELETE);
        INSTANCES.put(520,
                      RO_REPORT_CREATE);
        INSTANCES.put(521,
                      RO_REPORT_READ);
        INSTANCES.put(523,
                      RO_REPORT_UPDATE);
        INSTANCES.put(524,
                      RO_REPORT_DELETE);
        INSTANCES.put(530,
                      EV_SCC_LIBRARY_CREATE);
        INSTANCES.put(531,
                      EV_SCC_LIBRARY_READ);
        INSTANCES.put(532,
                      EV_SCC_LIBRARY_UPDATE);
        INSTANCES.put(533,
                      EV_SCC_LIBRARY_DELETE);
        INSTANCES.put(534,
                      EV_PROCESS_ASSET_CREATE);
        INSTANCES.put(535,
                      EV_PROCESS_ASSET_READ);
        INSTANCES.put(536,
                      EV_PROCESS_ASSET_UPDATE);
        INSTANCES.put(537,
                      EV_PROCESS_ASSET_DELETE);
    }

    public SecureObjectPermissionData()
    {
    }

    private SecureObjectPermissionData(Integer SecureObjectID, Integer PermissionID)
    {
        mintSecureObjectID =
            SecureObjectID;
        mintPermissionID =
            PermissionID;
    }

    public Integer getSecureObjectID()
    {
        return mintSecureObjectID;
    }

    public Integer getPermissionID()
    {
        return mintPermissionID;
    }

    public int compareTo(Object obj)
    {
        if (obj
            != null
            && obj instanceof SecureObjectPermissionData)
        {
            SecureObjectPermissionData
                secureObjectPermissionData =
                (SecureObjectPermissionData) obj;
            return getPermissionID().compareTo(secureObjectPermissionData.getPermissionID())
                   + getSecureObjectID().compareTo(secureObjectPermissionData.getSecureObjectID());
        }
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj
            != null
            && obj instanceof SecureObjectPermissionData)
        {
            SecureObjectPermissionData
                secureObjectPermissionData =
                (SecureObjectPermissionData) obj;
            if (getSecureObjectID().equals(secureObjectPermissionData.getSecureObjectID())
                && getPermissionID().equals(secureObjectPermissionData.getPermissionID()))
            {
                return true;
            }
        }
        return false;
    }

    public Integer getIndex()
    {
        Iterator
            keyIter =
            INSTANCES.keySet()
                .iterator();
        while (keyIter.hasNext())
        {
            Integer
                key =
                (Integer) keyIter.next();
            if (INSTANCES.get(key)
                .equals(this))
            {
                return key;
            }
        }
        return null;
    }

    public static SecureObjectPermissionData getInstance(Integer id)
    {
        return INSTANCES.get(id);
    }

    public static Collection getCRUDByModule(Integer moduleId)
    {
        List
            moduleSOPList =
            new ArrayList();
        Iterator
            capSOPIter =
            CapSecureObject.findByModuleId(moduleId)
                .iterator();
        while (capSOPIter.hasNext())
        {
            CapSecureObject
                capSOP =
                (CapSecureObject) capSOPIter.next();
            Iterator
                sopIter =
                INSTANCES.values()
                    .iterator();
            while (sopIter.hasNext())
            {
                SecureObjectPermissionData
                    sop =
                    (SecureObjectPermissionData) sopIter.next();
                if (capSOP.getId()
                    .equals(sop.getSecureObjectID()))
                {
                    if (sop.getPermissionID()
                        <= 4)
                    {
                        moduleSOPList.add(sop);
                    }
                }
            }
        }
        return moduleSOPList;
    }

    public static Collection getCRUDBySO(Integer secureObjectId)
    {
        List
            moduleSOPList =
            new ArrayList();
        List
            permissionList =
            CapPermission.findCRUD();
        Iterator
            capSOPIter =
            CapSecureObject.findBySecureObjectId(secureObjectId)
                .iterator();
        while (capSOPIter.hasNext())
        {
            CapSecureObject
                capSO =
                (CapSecureObject) capSOPIter.next();
            Iterator
                permIter =
                permissionList.iterator();
            while (permIter.hasNext())
            {
                CapPermission
                    permission =
                    (CapPermission) permIter.next();
                SecureObjectPermissionData
                    sopData =
                    getSecureObjectPermissionData(capSO.getId(),
                                                  permission.getId());
                if (sopData
                    != null)
                {
                    moduleSOPList.add(sopData);
                }
            }
        }
        return moduleSOPList;
    }

    public static SecureObjectPermissionData getSecureObjectPermissionData(Integer SecureObjectID, Integer PermissionID)
    {
        String
            strMod =
            "com.sehinc.security.SecureObjectPermissionData.";
        String
            strLog =
            strMod
            + "getSecureObjectPermissionData() ";
        SecureObjectPermissionData
            objSOP;
        SecureObjectPermissionData
            objFoundSOP =
            null;
        boolean
            blnFound =
            false;
        LOG.debug(strLog
                  + "SecureObjectID: "
                  + SecureObjectID.toString()
                  + " PermissionID: "
                  + PermissionID.toString());
        Iterator
            it =
            INSTANCES.values()
                .iterator();
        while (it.hasNext()
               && !blnFound)
        {
            objSOP =
                (SecureObjectPermissionData) it.next();
            if ((objSOP.getSecureObjectID()
                     .compareTo(SecureObjectID)
                 == 0)
                && (objSOP.getPermissionID()
                        .compareTo(PermissionID)
                    == 0))
            {
                blnFound =
                    true;
                LOG.debug(strLog
                          + "Found Permission SO="
                          + objSOP.getSecureObjectID()
                    .toString()
                          + " P="
                          + objSOP.getPermissionID()
                    .toString());
                objFoundSOP =
                    objSOP;
            }
        }
        return objFoundSOP;
    }
}
