package com.sehinc.common.security;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.CapClientTypeInfo;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.CapPermission;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.security.CapRoleSecureObjectPermission;
import com.sehinc.common.db.security.CapSecureObject;
import com.sehinc.common.db.user.CapUserModule;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.util.MathUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.security.CapRoleValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.ECConstants;
import com.sehinc.erosioncontrol.db.client.EcClientProjectRole;
import com.sehinc.erosioncontrol.db.client.EcClientProjectRolePermission;
import com.sehinc.erosioncontrol.db.client.EcClientRelationPermission;
import com.sehinc.erosioncontrol.db.client.EcClientRelationshipType;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectSearchData;
import com.sehinc.erosioncontrol.value.client.ClientProjectRolePermissionValue;
import com.sehinc.erosioncontrol.value.client.ClientProjectRoleValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SecurityManager
{
    public static final
    int
        SYSTEM_ADMINISTRATOR_SECURITY_LEVEL =
        100;
    public static final
    int
        CLIENT_ADMINISTRATOR_SECURITY_LEVEL =
        90;
    public static final
    int
        USER_SECURITY_LEVEL =
        80;
    public static final
    int
        ZERO_SECURITY_LEVEL =
        0;
    public static final
    int
        SYSTEM_ADMINISTRATOR_SECURITY_LEVEL_ID =
        1;
    public static final
    int
        CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID =
        2;
    public static final
    int
        USER_SECURITY_LEVEL_ID =
        3;
    public static final
    int
        ZERO_SECURITY_LEVEL_ID =
        0;
    public static final
    String
        SYSTEM_ADMINISTRATOR_SECURITY_LEVEL_NAME =
        "SYSTEM_ADMINISTRATOR_SECURITY_LEVEL";
    public static final
    String
        CLIENT_ADMINISTRATOR_SECURITY_LEVEL_NAME =
        "CLIENT_ADMINISTRATOR_SECURITY_LEVEL";
    public static final
    String
        USER_SECURITY_LEVEL_NAME =
        "USER_SECURITY_LEVEL";
    public static final
    String
        ZERO_SECURITY_LEVEL_NAME =
        "ZERO_SECURITY_LEVEL";
    public static final
    int
        PASSWORD_EXPIRATION_PERIOD =
        90;
    public static final
    int
        MIN_PASSWORD_LENGTH =
        6;
    public static final
    int
        MAX_USERNAME_LENGTH =
        10;
    public static final
    int
        MIN_USERNAME_LENGTH =
        4;
    public static final
    String
        PORTAL_ADMIN =
        ApplicationProperties.getProperty("portal.admin");
    private
    Integer
        _clientId;
    private
    Integer
        _projectId;
    private
    Integer
        _userId;
    private
    String
        _userName;
    private
    boolean
        _isProjectOwner =
        false;
    private
    boolean
        _isProjectPermitAuthority =
        false;
    private
    boolean
        _isProjectPermitted =
        false;
    private
    boolean
        _isProjectAuthorizedInspector =
        false;
    private
    List
        _secureObjectPermissionList;
    private
    boolean
        _isClientAdministrator =
        false;
    private
    boolean
        _isSystemAdministrator =
        false;
    private
    boolean
        _rolesAndPermsNotLoaded =
        true;
    private
    Integer
        _groupId;
    private
    Integer
        _securityLevelId;
    private
    ArrayList
        _clientModuleAccess;
    private
    ArrayList
        _userModuleAccess;
    private
    ArrayList
        _projectRolePermissionValueList;
    private
    ArrayList
        _clientProjectRoleValueList;
    private
    ArrayList
        _clientRelationshipSecureObjectPermissionList;
    private
    Integer
        _clientRelationshipTypeId;
    private
    Integer
        _clientTypeId;
    private
    Integer
        _projectOwnerClientID;
    private
    Integer
        _projectPermitAuthorityClientID;
    private
    Integer
        _projectPermittedClientID;
    private
    Integer
        _projectAuthorizedInspectorClientID;
    private static
    Logger
        LOG =
        Logger.getLogger(SecurityManager.class);

    public static String getUniqueUsername(String firstName, String lastName, int maxlength)
    {
        String
            username =
            StringUtil.stripNonAlphaNumericCharacters(firstName.toLowerCase()
                                                          .substring(0,
                                                                     1)
                                                      + lastName.toLowerCase());
        if (username.length()
            > maxlength)
        {
            username =
                username.substring(0,
                                   maxlength
                                   - 1);
        }
        boolean
            done =
            false;
        String
            tempUsername =
            username;
        int
            i =
            1;
        while (!done)
        {
            LOG.debug("trying username: "
                      + tempUsername);
            Integer
                index =
                i;
            UserData
                userData =
                UserData.findByUsername(tempUsername);
            if (userData
                == null)
            {
                done =
                    true;
            }
            else
            {
                if (username.length()
                    + index.toString()
                    .length()
                    > maxlength)
                {
                    tempUsername =
                        StringUtil.replaceAt(username,
                                             (maxlength
                                              - ((username.length()
                                                  + index.toString()
                                                 .length())
                                                 - maxlength))
                                             - 1,
                                             index.toString());
                }
                else
                {
                    tempUsername =
                        username
                        + index.toString();
                }
            }
            i++;
        }
        return tempUsername;
    }

    /**
     * Generates random password.<BR>
     * Uses digits 1-9 and upper case letters A-Z.<BR>
     * O(oh) and 0(zero) are not used to avoid confusion.<BR>
     * The algorithm adds the time in milliseconds multiplyed by a Math.random
     * with a Random.nextInt and generates a <I>0 < number < 33</I>.<BR>
     * This is done through BigInteger.mod(BigInteger) method.<BR>
     * The number generated represents the index of a character in pool:<BR>
     * "123456789ABCDEFGHJKLMNPQRSTUVWXYZ"<BR>
     * The character picked will be attached to a buffer of the length passed as argument.<BR>
     * To avoid repetition (even random!) the character has to be different then the last added char.<BR>
     *
     * @@exception Exception &nbsp;
     */
    public static String generatePassword(int length)
    {
        BigInteger
            modulator =
            new BigInteger("33");
        String
            pool =
            "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        char
            lastChar =
            '0';
        if ((length
             < 1)
            || (length
                > 100))
        {
            throw new RuntimeException("Invalid length choice: "
                                       + length
                                       + ". Please select 1 through 100");
        }
        StringBuilder
            buffer =
            new StringBuilder();
        for (
            int
                i =
                0; i
                   < length; i++)
        {
            long
                randomNumber =
                MathUtil.generateRandom();
            //get an index 0-33 by modulating hocusPocus
            //use it as index of next char from pool
            BigInteger
                number =
                new BigInteger(String.valueOf(randomNumber));
            int
                index =
                number.mod(modulator)
                    .intValue();
            char
                nextChar =
                pool.charAt(index);
            //add char to password - avoid repetitions
            if (lastChar
                != nextChar)
            {
                buffer.append(nextChar);
                lastChar =
                    nextChar;
            }
            else
            {
                i--;
            }
        }
        return buffer.toString();
    }

    public boolean validChars(String sString)
    {
        boolean
            blnValidChars =
            true;
        String
            checkOK =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char
            ch;
        int
            i;
        sString =
            sString.trim();
        if (sString.length()
            >= 1)
        {
            for (
                i =
                    0; i
                       < sString.length(); i++)
            {
                if (blnValidChars)
                {
                    ch =
                        sString.charAt(i);
                    blnValidChars =
                        (checkOK.indexOf(ch)
                         >= 0);
                }
            }
        }
        else
        {
            blnValidChars =
                false;
        }
        return blnValidChars;
    }

    public boolean validateUserName(String strUserName, Integer userId)
        throws Exception
    {
        boolean
            blnUserNameValid =
            false;
        String
            strOriginalUserName =
            "";
        if (userId
            != null)
        {
            UserData
                origUser =
                UserData.findActiveById(userId);
            if (origUser
                != null)
            {
                strOriginalUserName =
                    origUser.getUsername();
            }
        }
        if (strUserName.length()
            >= MIN_USERNAME_LENGTH)
        { // Name is not too short
            //Check that the user name does not contain a space
            if (strUserName.indexOf(" ")
                < 0)
            {
                if (validChars(strUserName))
                {
                    if (!strUserName.equals(strOriginalUserName))
                    { // User name has not changed
                        UserData
                            user =
                            UserData.findByUsername(strUserName);
                        if (user
                            != null)
                        { // User name is a duplicate
                            throw new Exception("Please enter a valid user name.");
                        }
                    }
                    else
                    {
                        blnUserNameValid =
                            true;
                    }
                }
                else
                {
                    throw new Exception("User Name is invalid.  Only alphanumeric characters are allowed.");
                }
            }
            else
            {
                throw new Exception("User Name is invalid.  User Name cannot contain spaces.");
            }
        }
        else
        {
            throw new Exception("User Name must be at least 4 characters.");
        }
        return blnUserNameValid;
    }

    public static SecurityManager getSecurityManager(HttpServletRequest request)
        throws Exception
    {
        if (request.getAttribute(SessionKeys.SECURITY_MANAGER)
            == null)
        {
            SecurityManager
                securityManager =
                new SecurityManager();
            request.setAttribute(SessionKeys.SECURITY_MANAGER,
                                 securityManager);
            securityManager.init(SessionService.getUserValue(request),
                                 null);
        }
        return (SecurityManager) request.getAttribute(SessionKeys.SECURITY_MANAGER);
    }

    private void setSecurityLevel()
    {
        LOG.debug("setSecurityLevel in method");
        setIsClientAdministrator(false);
        setIsSystemAdministrator(false);
        UserData
            userData;
        try
        {
            userData =
                new UserData(getUserID());
            if (userData.load())
            {
                setUserName(userData.getUsername());
                GroupData
                    groupData =
                    new GroupData(userData.getGroupId());
                if (groupData.load())
                {
                    setGroupId(groupData.getId());
                    setSecurityLevelId(groupData.getSecurityLevelId());
                }
                else
                {
                    LOG.error("Failed to load group ID "
                              + userData.getGroupId());
                    setGroupId(GroupData.CLIENT_USER_GROUP_ID);
                    setSecurityLevelId(USER_SECURITY_LEVEL_ID);
                }
            }
            else
            {
                LOG.error("Failed to load user ID "
                          + _userId);
                setGroupId(GroupData.CLIENT_USER_GROUP_ID);
                setSecurityLevelId(USER_SECURITY_LEVEL_ID);
            }
        }
        catch (Exception e)
        {
            LOG.error("SecurityManager.setSecurityLevel(): <br>Message:<br>"
                      + e.getMessage());
            throw new RuntimeException("SecurityManager.setSecurityLevel(): <br>Message:<br>"
                                       + e.getMessage());
        }
        setIsClientAdministrator(getSecurityLevelId()
                                 <= 2);
        setIsSystemAdministrator(getSecurityLevelId()
                                 == 1);
    }

    private void setModulePermissions()
    {
        if (_clientModuleAccess
            == null)
        {
            _clientModuleAccess =
                new ArrayList();
        }
        else
        {
            _clientModuleAccess.clear();
        }
        if (_userModuleAccess
            == null)
        {
            _userModuleAccess =
                new ArrayList();
        }
        else
        {
            _userModuleAccess.clear();
        }
        if (getClientID().equals(CommonConstants.SEH_CLIENT_ID))
        {
            _clientModuleAccess.add(CommonConstants.EROSION_CONTROL_MODULE);
            _clientModuleAccess.add(CommonConstants.STORM_WATER_MODULE);
            _clientModuleAccess.add(CommonConstants.DATA_VIEW_MODULE);
            _clientModuleAccess.add(CommonConstants.SECURITY_MODULE);
            _clientModuleAccess.add(CommonConstants.ENVIRONMENT_MODULE);
            _userModuleAccess.add(CommonConstants.EROSION_CONTROL_MODULE);
            _userModuleAccess.add(CommonConstants.STORM_WATER_MODULE);
            _userModuleAccess.add(CommonConstants.DATA_VIEW_MODULE);
            _userModuleAccess.add(CommonConstants.SECURITY_MODULE);
            _userModuleAccess.add(CommonConstants.ENVIRONMENT_MODULE);
        }
        else
        {
            try
            {
                _clientModuleAccess.add(CommonConstants.SECURITY_MODULE);
                _userModuleAccess.add(CommonConstants.SECURITY_MODULE);
                if (ClientModule.findClientModule(getClientID(),
                                                  CommonConstants.EROSION_CONTROL_MODULE)
                    != null)
                {
                    _clientModuleAccess.add(CommonConstants.EROSION_CONTROL_MODULE);
                }
                if (CapUserModule.findByUserIdAndModuleCode(getUserID(),
                                                            CommonConstants.EROSION_CONTROL_MODULE,
                                                            getClientID())
                    != null)
                {
                    _userModuleAccess.add(CommonConstants.EROSION_CONTROL_MODULE);
                }
                if (ClientModule.findClientModule(getClientID(),
                                                  CommonConstants.STORM_WATER_MODULE)
                    != null)
                {
                    _clientModuleAccess.add(CommonConstants.STORM_WATER_MODULE);
                }
                if (CapUserModule.findByUserIdAndModuleCode(getUserID(),
                                                            CommonConstants.STORM_WATER_MODULE,
                                                            getClientID())
                    != null)
                {
                    _userModuleAccess.add(CommonConstants.STORM_WATER_MODULE);
                }
                if (ClientModule.findClientModule(getClientID(),
                                                  CommonConstants.DATA_VIEW_MODULE)
                    != null)
                {
                    _clientModuleAccess.add(CommonConstants.DATA_VIEW_MODULE);
                }
                if (CapUserModule.findByUserIdAndModuleCode(getUserID(),
                                                            CommonConstants.DATA_VIEW_MODULE,
                                                            getClientID())
                    != null)
                {
                    _userModuleAccess.add(CommonConstants.DATA_VIEW_MODULE);
                }
                if (ClientModule.findClientModule(getClientID(),
                                                  CommonConstants.ENVIRONMENT_MODULE)
                    != null)
                {
                    _clientModuleAccess.add(CommonConstants.ENVIRONMENT_MODULE);
                }
                if (CapUserModule.findByUserIdAndModuleCode(getUserID(),
                                                            CommonConstants.ENVIRONMENT_MODULE,
                                                            getClientID())
                    != null)
                {
                    _userModuleAccess.add(CommonConstants.ENVIRONMENT_MODULE);
                }
            }
            catch (Exception e)
            {
                LOG.error("Error setting client module permissions.");
                LOG.error(e);
                throw new RuntimeException("Error setting client module permissions.");
            }
        }
    }

    private void ProjectClientRoles()
    {
        EcProject
            project =
            new EcProject(getProjectID());
        if (!project.load())
        {
            LOG.error("Failed to load Project ID "
                      + getProjectID());
            throw new RuntimeException("Failed to load Project ID: "
                                       + getProjectID());
        }
        ProjectValue
            projectValue =
            new ProjectValue(project,
                             false);
        ProjectClientRoles(projectValue);
    }

    private void ProjectClientRoles(ProjectValue projectValue)
    {
        try
        {
            if (projectValue
                != null)
            {
                if (projectValue.getOwnerClientId()
                    != null)
                {
                    setProjectOwnerClientId(projectValue.getOwnerClientId());
                }
                else
                {
                    setProjectOwnerClientId(null);
                }
                if (projectValue.getPermitAuthorityClientId()
                    != null)
                {
                    setProjectPermitAuthorityClientId(projectValue.getPermitAuthorityClientId());
                }
                else
                {
                    setProjectPermitAuthorityClientId(null);
                }
                if (projectValue.getPermittedClientId()
                    != null)
                {
                    setProjectPermittedClientId(projectValue.getPermittedClientId());
                }
                else
                {
                    setProjectPermittedClientId(null);
                }
                if (projectValue.getAuthorizedInspectorClientId()
                    != null)
                {
                    setProjectAuthorizedInspectorClientId(projectValue.getAuthorizedInspectorClientId());
                }
                else
                {
                    setProjectAuthorizedInspectorClientId(null);
                }
                setIsProjectOwner(getClientID().equals(getProjectOwnerClientId()));
                setIsProjectPermitAuthority(getClientID().equals(getProjectPermitAuthorityClientId()));
                setIsProjectPermitted(getClientID().equals(getProjectPermittedClientId()));
                setIsProjectAuthorizedInspector(getClientID().equals(getProjectAuthorizedInspectorClientId()));
            }
            else
            {
                throw new RuntimeException("SecurityManager.ProjectClientRoles(ProjectValue): ProjectValue is null");
            }
        }
        catch (Exception e)
        {
            LOG.error("Error in SecurityManager.ProjectClientRoles(ProjectValue)");
            LOG.error(e);
            throw new RuntimeException("SecurityManager.ProjectClientRoles(ProjectValue) Error: <br>Message:<br>"
                                       + e.getMessage());
        }
    }

    private List GetUserRoles()
    {
        List
            lstRoles =
            new ArrayList();
        try
        {
            for (Object o : CapRole.findByUserId(getUserID(),
                                                 StatusCodeData.STATUS_CODE_ACTIVE))
            {
                lstRoles.add(new CapRoleValue((CapRole) o));
            }
        }
        catch (Exception e)
        {
            LOG.error("GetUserRoles()");
            LOG.error(e);
            throw new RuntimeException("GetUserRoles()",
                                       e);
        }
        return lstRoles;
    }

    private void LoadUserRolePermissions()
    {
        if (_secureObjectPermissionList
            == null)
        {
            _secureObjectPermissionList =
                new ArrayList();
        }
        else
        {
            _secureObjectPermissionList.clear();
        }
        if (getIsClientAdministrator()
            || getIsSystemAdministrator())
        {
            return;
        }
        try
        {
            for (Object o : GetUserRoles())
            {
                CapRoleValue
                    roleValue =
                    (CapRoleValue) o;
                for (Object o1 : CapRoleSecureObjectPermission.findByRoleId(roleValue.getId()))
                {
                    CapRoleSecureObjectPermission
                        roleSOP =
                        (CapRoleSecureObjectPermission) o1;
                    SecureObjectPermissionData
                        sop =
                        SecureObjectPermissionData.getSecureObjectPermissionData(roleSOP.getSecureObjectId(),
                                                                                 roleSOP.getPermissionId());
                    if (sop
                        != null)
                    {
                        if (!_secureObjectPermissionList.contains(sop))
                        {
                            _secureObjectPermissionList.add(sop);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("Error loading secure object permissions");
            throw new RuntimeException("Error loading secure object permissions",
                                       e);
        }
    }

    public void deleteAllSecureObjectPermissionsFromRole(Integer roleId)
        throws Exception
    {
        try
        {
            for (Object o : CapRoleSecureObjectPermission.findByRoleId(roleId))
            {
                CapRoleSecureObjectPermission
                    sop =
                    (CapRoleSecureObjectPermission) o;
                try
                {
                    sop.delete();
                }
                catch (Exception e2)
                {
                    LOG.warn("Failed to delete CapRoleSecureObjectPermission ID "
                             + sop.getId());
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("Failed to load CapRoleSecureObjectPermission list for role ID "
                      + roleId);
            throw new Exception("Failed to load CapRoleSecureObjectPermission list for role ID "
                                + roleId,
                                e);
        }
    }

    public void init()
        throws Exception
    {
        if (_clientProjectRoleValueList
            == null
            || _projectRolePermissionValueList
               == null)
        {
            loadClientProjectRoles();
            loadProjectPermissions();
            _rolesAndPermsNotLoaded =
                false;
        }
    }

    public void init(Integer userID, Integer clientID)
        throws Exception
    {
        LOG.debug("In init(userID="
                  + userID
                  + " clientID="
                  + clientID
                  + ")");
        setClientID(clientID);
        setUserID(userID);
        if (_rolesAndPermsNotLoaded)
        {
            this.init();
        }
    }

    public void init(UserValue userValue, ClientValue clientValue)
        throws Exception
    {
        init(userValue
             != null
                 ? userValue.getId()
                 : 0,
             clientValue
             != null
                 ? clientValue.getId()
                 : 0);
    }

    private void loadClientProjectRoles()
    {
        if (_clientProjectRoleValueList
            == null)
        {
            _clientProjectRoleValueList =
                new ArrayList();
        }
        else
        {
            _clientProjectRoleValueList.clear();
        }
        try
        {
            List
                cprList =
                EcClientProjectRole.findAll();
            for (Object aCprList : cprList)
            {
                EcClientProjectRole
                    clientProjectRole =
                    (EcClientProjectRole) aCprList;
                ClientProjectRoleValue
                    clientProjectRoleValue =
                    new ClientProjectRoleValue(clientProjectRole);
                _clientProjectRoleValueList.add(clientProjectRoleValue);
            }
        }
        catch (Exception e)
        {
            LOG.error("loadClientProjectRoles(): Failed to load EcClientProjectRole list");
        }
    }

    private void loadClientRelationshipType()
    {
        //first check to see if the client is PRIMARY, if so they are PREFERRED
        try
        {
            LOG.debug("loadClientRelationshipType: clientTypeId = "
                      + getClientTypeId());
            if (getClientTypeId()
                != null
                && getClientTypeId().equals(CommonConstants.PRIMARY_CLIENT))
            {
                LOG.debug("loadClientRelationshipType: setting clientRelationshipType to PREFERRED");
                setClientRelationshipTypeId(ECConstants.CLIENT_RELATIONSHIP_TYPE_PREFERRED);
            }
            else
            {
                //If the client is not PRIMARY, then get the hightest relationship level (PREFERRED > STANDARD) that the client has
                Integer
                    minClientRelationshipTypeId =
                    EcClientRelationshipType.minRelationshipTypeIdByRelatedClientId(getClientID());
                LOG.debug("loadClientRelationshipType: minClientRelationshipTypeId="
                          + minClientRelationshipTypeId);
                if (minClientRelationshipTypeId
                    == null
                    || minClientRelationshipTypeId
                       == 0)
                {
                    //Default the type to STANDARD
                    LOG.debug("loadClientRelationshipType: not ClientRelationshipType found, default to STANDARD");
                    setClientRelationshipTypeId(ECConstants.CLIENT_RELATIONSHIP_TYPE_STANDARD);
                }
                else
                {
                    LOG.debug("loadClientRelationshipType: setting clientRelationshipTypeId = "
                              + minClientRelationshipTypeId);
                    setClientRelationshipTypeId(minClientRelationshipTypeId);
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("loadClientRelationshipType(): Failed to load client relationship for client ID "
                      + getClientID());
            LOG.error(e);
        }
    }

    private void loadClientType()
    {
        try
        {
            CapClientTypeInfo
                capClientTypeInfo =
                CapClientTypeInfo.findByClientId(getClientID());
            if (capClientTypeInfo
                != null)
            {
                setClientTypeId(capClientTypeInfo.getId());
            }
        }
        catch (Exception e)
        {
            LOG.error("loadClientType(): Failed to load client type for client ID "
                      + getClientID());
            LOG.error(e);
        }
    }

    private void loadClientPermissions()
    {
        if (_clientRelationshipSecureObjectPermissionList
            == null)
        {
            LOG.debug("loadClientPermissions: creating new ArrayList()");
            _clientRelationshipSecureObjectPermissionList =
                new ArrayList();
        }
        else
        {
            LOG.debug("loadClientPermissions: ArrayList.clear()");
            _clientRelationshipSecureObjectPermissionList.clear();
        }
        LOG.debug("loadClientPermissions: ArrayList.size() after clear = "
                  + _clientRelationshipSecureObjectPermissionList.size());
        try
        {
            LOG.debug("loadClientPermissions: getClientTypeId() = "
                      + getClientTypeId());
            LOG.debug("loadClientPermissions: getClientRelationshipTypeId() = "
                      + getClientRelationshipTypeId());
            for (Object o : EcClientRelationPermission.findByClientTypeAndRelationshipType(getClientTypeId(),
                                                                                           getClientRelationshipTypeId()))
            {
                EcClientRelationPermission
                    relationshipPermission =
                    (EcClientRelationPermission) o;
                CapSecureObject
                    secureObject =
                    relationshipPermission.getSecureObject();
                CapPermission
                    permission =
                    relationshipPermission.getPermission();
                SecureObjectPermissionData
                    sop =
                    SecureObjectPermissionData.getSecureObjectPermissionData(secureObject.getId(),
                                                                             permission.getId());
                if (sop
                    != null)
                {
                    if (!_clientRelationshipSecureObjectPermissionList.contains(sop))
                    {
                        LOG.debug("loadClientPermissions: adding SecureObjectPermissionData Index="
                                  + sop.getIndex());
                        _clientRelationshipSecureObjectPermissionList.add(sop);
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("Error loading client relationship secure object permissions");
            throw new RuntimeException("Error loading client relationship secure object permissions",
                                       e);
        }
    }

    private void loadProjectPermissions()
    {
        if (_projectRolePermissionValueList
            == null)
        {
            _projectRolePermissionValueList =
                new ArrayList();
        }
        else
        {
            _projectRolePermissionValueList.clear();
        }
        try
        {
            for (Object o : HibernateUtil.findAll(EcClientProjectRolePermission.class))
            {
                _projectRolePermissionValueList.add(new ClientProjectRolePermissionValue((EcClientProjectRolePermission) o));
            }
        }
        catch (Exception e)
        {
            LOG.error("Failed to load project role permissions.  Message: "
                      + e.getMessage());
        }
    }

    public boolean HasPermission(SecureObjectPermissionData secureObjectPermissionData)
    {
        boolean
            blnHasPermission;
        LOG.debug("HasPermission("
                  + secureObjectPermissionData.getSecureObjectID()
                  + ","
                  + secureObjectPermissionData.getPermissionID()
                  + ").getIsClientAdministrator()= "
                  + getIsClientAdministrator());
        if (getIsClientAdministrator())
        {
            blnHasPermission =
                true;
        }
        else
        {
            blnHasPermission =
                getUserSecureObjectPermission().contains(secureObjectPermissionData);
            LOG.debug("HasPermission("
                      + secureObjectPermissionData.getSecureObjectID()
                      + ","
                      + secureObjectPermissionData.getPermissionID()
                      + ").getUserSecureObjectPermission()= "
                      + blnHasPermission);
        }
        LOG.debug("HasPermission("
                  + secureObjectPermissionData.getSecureObjectID()
                  + ","
                  + secureObjectPermissionData.getPermissionID()
                  + ") = "
                  + blnHasPermission);
        return blnHasPermission;
    }

    public boolean HasECPermission(SecureObjectPermissionData secureObjectPermissionData)
    {
        boolean
            blnHasPermission;
        boolean
            blnHasClientPermission;
        if (getIsSystemAdministrator())
        {
            blnHasPermission =
                true;
            blnHasClientPermission =
                true;
        }
        else
        {
            if (getIsClientAdministrator())
            {
                blnHasPermission =
                    true;
            }
            else
            {
                blnHasPermission =
                    HasPermission(secureObjectPermissionData);
            }
            blnHasClientPermission =
                hasClientRelationshipPermission(secureObjectPermissionData);
        }
        return blnHasPermission
               && blnHasClientPermission;
    }

    public boolean HasECPermission(SecureObjectPermissionData secureObjectPermissionData, ProjectValue projectValue)
    {
        boolean
            blnHasPermission;
        boolean
            blnProjectPermission =
            true;
        if (projectValue
            == null
            || projectValue.getId()
               == null)
        {
            return false;
        }
        blnHasPermission =
            HasECPermission(secureObjectPermissionData);
        //Check the clients permssions on the project
        //First check if the project id has changed.
        if (getProjectID()
            == null
            || !getProjectID().equals(projectValue.getId()))
        {
            this.setProjectID(projectValue);
        }
        //Check the project permission for non-system admins
        if (blnHasPermission
            && !getIsSystemAdministrator())
        {
            blnProjectPermission =
                HasECProjectPermission(secureObjectPermissionData);
        }
        return blnHasPermission
               && blnProjectPermission; //Permission granted if client and user both have permission.
    }

    public boolean HasECPermission(SecureObjectPermissionData secureObjectPermissionData, EcProjectSearchData searchData)
    {
        boolean
            blnHasPermission;
        boolean
            blnProjectPermission =
            true;
        if (searchData
            == null
            || searchData.getProjectId()
               == null)
        {
            return false;
        }
        try
        {
            Integer.parseInt(searchData.getProjectId());
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        blnHasPermission =
            HasECPermission(secureObjectPermissionData);
        if (getProjectID()
            == null
            || !getProjectID().toString()
            .equals(searchData.getProjectId()))
        {
            this.setProjectID(Integer.parseInt(searchData.getProjectId()));
        }
        //Check the project permission for non-system admins
        if (blnHasPermission
            && !getIsSystemAdministrator())
        {
            blnProjectPermission =
                HasECProjectPermission(secureObjectPermissionData);
        }
        return blnHasPermission
               && blnProjectPermission; //Permission granted if client and user both have permission.
    }

    public boolean HasECPermission(SecureObjectPermissionData secureObjectPermissionData, Integer projectId)
    {
        if (projectId
            == null)
        {
            return false;
        }
        EcProject
            project =
            new EcProject(projectId);
        if (project.load()
            && project.getStatus()
            .getCode()
            .equals(StatusCodeData.STATUS_CODE_ACTIVE))
        {
            ProjectValue
                projectValue =
                new ProjectValue(project,
                                 false);
            return HasECPermission(secureObjectPermissionData,
                                   projectValue);
        }
        return false;
    }

    private boolean clientRoleHasPermission(ClientProjectRolePermissionValue clientProjectRolePermissionValue)
    {
        return getProjectRolePermissionValueList().contains(clientProjectRolePermissionValue);
    }

    protected boolean HasECProjectPermission(SecureObjectPermissionData secureObjectPermissionData)
    {
        boolean
            blnHasProjectPermission =
            false;
        //Check to see if any of the client's project roles allows the permission
        if (getIsProjectOwner())
        {
            ClientProjectRoleValue
                clientProjectRoleValue =
                getClientProjectRole(ECConstants.CLIENT_PROJECT_ROLE_OWNER);
            if (clientProjectRoleValue
                != null)
            {
                ClientProjectRolePermissionValue
                    clientProjectRolePermissionValue =
                    new ClientProjectRolePermissionValue();
                clientProjectRolePermissionValue.setProjectRoleId(clientProjectRoleValue.getId());
                clientProjectRolePermissionValue.setSecureObjectId(secureObjectPermissionData.getSecureObjectID());
                clientProjectRolePermissionValue.setPermissionId(secureObjectPermissionData.getPermissionID());
                blnHasProjectPermission =
                    clientRoleHasPermission(clientProjectRolePermissionValue);
                LOG.debug("HasProjectPermission.getIsProjectOwner = "
                          + blnHasProjectPermission);
            }
        }
        if (!blnHasProjectPermission
            && getIsProjectPermitAuthority())
        {
            ClientProjectRoleValue
                clientProjectRoleValue =
                getClientProjectRole(ECConstants.CLIENT_PROJECT_ROLE_PA);
            if (clientProjectRoleValue
                != null)
            {
                ClientProjectRolePermissionValue
                    clientProjectRolePermissionValue =
                    new ClientProjectRolePermissionValue();
                clientProjectRolePermissionValue.setProjectRoleId(clientProjectRoleValue.getId());
                clientProjectRolePermissionValue.setSecureObjectId(secureObjectPermissionData.getSecureObjectID());
                clientProjectRolePermissionValue.setPermissionId(secureObjectPermissionData.getPermissionID());
                blnHasProjectPermission =
                    clientRoleHasPermission(clientProjectRolePermissionValue);
                LOG.debug("HasProjectPermission.getIsProjectPermitAuthority = "
                          + blnHasProjectPermission);
            }
        }
        if (!blnHasProjectPermission
            && getIsProjectPermitted())
        {
            ClientProjectRoleValue
                clientProjectRoleValue =
                getClientProjectRole(ECConstants.CLIENT_PROJECT_ROLE_PE);
            if (clientProjectRoleValue
                != null)
            {
                ClientProjectRolePermissionValue
                    clientProjectRolePermissionValue =
                    new ClientProjectRolePermissionValue();
                clientProjectRolePermissionValue.setProjectRoleId(clientProjectRoleValue.getId());
                clientProjectRolePermissionValue.setSecureObjectId(secureObjectPermissionData.getSecureObjectID());
                clientProjectRolePermissionValue.setPermissionId(secureObjectPermissionData.getPermissionID());
                blnHasProjectPermission =
                    clientRoleHasPermission(clientProjectRolePermissionValue);
                LOG.debug("HasProjectPermission.getIsProjectPermitted = "
                          + blnHasProjectPermission);
            }
        }
        if (!blnHasProjectPermission
            && getIsProjectAuthorizedInspector())
        {
            ClientProjectRoleValue
                clientProjectRoleValue =
                getClientProjectRole(ECConstants.CLIENT_PROJECT_ROLE_AI);
            if (clientProjectRoleValue
                != null)
            {
                ClientProjectRolePermissionValue
                    clientProjectRolePermissionValue =
                    new ClientProjectRolePermissionValue();
                clientProjectRolePermissionValue.setProjectRoleId(clientProjectRoleValue.getId());
                clientProjectRolePermissionValue.setSecureObjectId(secureObjectPermissionData.getSecureObjectID());
                clientProjectRolePermissionValue.setPermissionId(secureObjectPermissionData.getPermissionID());
                blnHasProjectPermission =
                    clientRoleHasPermission(clientProjectRolePermissionValue);
                LOG.debug("HasProjectPermission.getIsProjectAuthorizedInspector = "
                          + blnHasProjectPermission);
            }
        }
        return blnHasProjectPermission;
    }

    public void setProjectID(int projectId)
    {
        setProjectID(new Integer(projectId));
    }

    public void setProjectID(Integer projectId)
    {
        _projectId =
            projectId;
        ProjectClientRoles();
    }

    public void setProjectID(ProjectValue projectValue)
    {
        if (projectValue
            != null)
        {
            _projectId =
                projectValue.getId();
            ProjectClientRoles(projectValue);
        }
    }

    public Integer getProjectID()
    {
        return _projectId;
    }

    public Integer getUserID()
    {
        return _userId;
    }

    public void setUserID(Integer userId)
    {
        if (_userId
            == null
            || !_userId.equals(userId))
        {
            _userId =
                userId;
            setSecurityLevel();
            LoadUserRolePermissions();
        }
    }

    public String getUserName()
    {
        return _userName;
    }

    private void setUserName(String userName)
    {
        _userName =
            userName;
    }

    public void setClientID(Integer clientId, boolean force)
    {
        if (_clientId
            == null
            || !_clientId.equals(clientId)
            || force)
        {
            _clientId =
                clientId;
            setModulePermissions();
            loadClientType();
            if (getClientCanAccessEC())
            {
                loadClientRelationshipType();
                loadClientPermissions();
            }
        }
    }

    public void setClientID(Integer clientId)
    {
        setClientID(clientId,
                    false);
    }

    public Integer getClientID()
    {
        return _clientId;
    }

    public boolean getIsProjectOwner()
    {
        return this._isProjectOwner;
    }

    public void setIsProjectOwner(boolean isProjectOwner)
    {
        this._isProjectOwner =
            isProjectOwner;
    }

    public boolean getIsProjectOwner(Integer projectId)
    {
        if (_projectId
            == null
            || !_projectId.equals(projectId))
        {
            setProjectID(projectId);
        }
        return getIsProjectOwner();
    }

    public Integer getProjectOwnerClientId()
    {
        return this._projectOwnerClientID;
    }

    public void setProjectOwnerClientId(Integer projectOwnerClientID)
    {
        this._projectOwnerClientID =
            projectOwnerClientID;
    }

    public boolean getIsProjectPermitAuthority()
    {
        return this._isProjectPermitAuthority;
    }

    public void setIsProjectPermitAuthority(boolean isProjectPermitAuthority)
    {
        this._isProjectPermitAuthority =
            isProjectPermitAuthority;
    }

    public boolean getIsProjectPermitAuthority(Integer projectId)
    {
        if (_projectId
            == null
            || !_projectId.equals(projectId))
        {
            setProjectID(projectId);
        }
        return getIsProjectPermitAuthority();
    }

    public Integer getProjectPermitAuthorityClientId()
    {
        return this._projectPermitAuthorityClientID;
    }

    public void setProjectPermitAuthorityClientId(Integer projectPermitAuthorityClientID)
    {
        this._projectPermitAuthorityClientID =
            projectPermitAuthorityClientID;
    }

    public boolean getIsProjectPermitted()
    {
        return _isProjectPermitted;
    }

    public void setIsProjectPermitted(boolean isProjectPermitted)
    {
        this._isProjectPermitted =
            isProjectPermitted;
    }

    public boolean getIsProjectPermitted(Integer projectId)
    {
        if (_projectId
            == null
            || !_projectId.equals(projectId))
        {
            setProjectID(projectId);
        }
        return getIsProjectPermitted();
    }

    public Integer getProjectPermittedClientId()
    {
        return this._projectPermittedClientID;
    }

    public void setProjectPermittedClientId(Integer projectPermittedClientID)
    {
        this._projectPermittedClientID =
            projectPermittedClientID;
    }

    public boolean getIsProjectAuthorizedInspector()
    {
        return this._isProjectAuthorizedInspector;
    }

    public void setIsProjectAuthorizedInspector(boolean isProjectAuthorizedInspector)
    {
        this._isProjectAuthorizedInspector =
            isProjectAuthorizedInspector;
    }

    public boolean getIsProjectAuthorizedInspector(Integer projectId)
    {
        if (_projectId
            == null
            || !_projectId.equals(projectId))
        {
            setProjectID(projectId);
        }
        return getIsProjectAuthorizedInspector();
    }

    public Integer getProjectAuthorizedInspectorClientId()
    {
        return this._projectAuthorizedInspectorClientID;
    }

    public void setProjectAuthorizedInspectorClientId(Integer projectAuthorizedInspectorClientID)
    {
        this._projectAuthorizedInspectorClientID =
            projectAuthorizedInspectorClientID;
    }

    public boolean getIsClientAdministrator()
    {
        return _isClientAdministrator;
    }

    private void setIsClientAdministrator(boolean isClientAdministrator)
    {
        this._isClientAdministrator =
            isClientAdministrator;
    }

    public boolean getIsSystemAdministrator()
    {
        return _isSystemAdministrator;
    }

    private void setIsSystemAdministrator(boolean isSystemAdministrator)
    {
        this._isSystemAdministrator =
            isSystemAdministrator;
    }

    public List getUserSecureObjectPermission()
    {
        if (_secureObjectPermissionList
            == null)
        {
            _secureObjectPermissionList =
                new ArrayList();
        }
        return _secureObjectPermissionList;
    }

    public Integer getSecurityLevelId()
    {
        return _securityLevelId;
    }

    private void setSecurityLevelId(Integer securityLevelId)
    {
        _securityLevelId =
            securityLevelId;
    }

    public boolean getClientCanAccessEC()
    {
        return _clientModuleAccess.contains(CommonConstants.EROSION_CONTROL_MODULE);
    }

    public boolean getClientCanAccessSW()
    {
        return _clientModuleAccess.contains(CommonConstants.STORM_WATER_MODULE);
    }

    public boolean getClientCanAccessDV()
    {
        return _clientModuleAccess.contains(CommonConstants.DATA_VIEW_MODULE);
    }

    public boolean getClientCanAccessEV()
    {
        return _clientModuleAccess.contains(CommonConstants.ENVIRONMENT_MODULE);
    }

    public boolean getClientCanAccessModule(String moduleCode)
    {
        return _clientModuleAccess.contains(moduleCode);
    }

    public boolean getUserCanAccessEC()
    {
        return _userModuleAccess.contains(CommonConstants.EROSION_CONTROL_MODULE);
    }

    public boolean getUserCanAccessSW()
    {
        return _userModuleAccess.contains(CommonConstants.STORM_WATER_MODULE);
    }

    public boolean getUserCanAccessDV()
    {
        return _userModuleAccess.contains(CommonConstants.DATA_VIEW_MODULE);
    }

    public boolean getUserCanAccessEV()
    {
        return _userModuleAccess.contains(CommonConstants.ENVIRONMENT_MODULE);
    }

    public boolean getUserCanAccessModule(String moduleCode)
    {
        return _userModuleAccess.contains(moduleCode);
    }

    private void setClientRelationshipTypeId(Integer clientRelationshipTypeId)
    {
        _clientRelationshipTypeId =
            clientRelationshipTypeId;
    }

    private Integer getClientRelationshipTypeId()
    {
        return _clientRelationshipTypeId;
    }

    private void setClientTypeId(Integer clientTypeId)
    {
        _clientTypeId =
            clientTypeId;
    }

    private Integer getClientTypeId()
    {
        return _clientTypeId;
    }

    private ClientProjectRoleValue getClientProjectRole(String projectRoleCode)
    {
        for (Object o : getClientProjectRoleValueList())
        {
            ClientProjectRoleValue
                clientProjectRoleValue =
                (ClientProjectRoleValue) o;
            if (clientProjectRoleValue
                == null)
            {
                LOG.error("ClientProjectRoleValue is NULL");
            }
            else
            {
                LOG.debug("ClientProjectRoleValue.getCode()="
                          + clientProjectRoleValue.getCode());
            }
            if (clientProjectRoleValue
                != null)
            {
                if (clientProjectRoleValue.getCode()
                    .equalsIgnoreCase(projectRoleCode))
                {
                    return clientProjectRoleValue;
                }
            }
        }
        return null;
    }

    private List getClientRelationshipPermission()
    {
        if (_clientRelationshipSecureObjectPermissionList
            == null)
        {
            _clientRelationshipSecureObjectPermissionList =
                new ArrayList();
        }
        return _clientRelationshipSecureObjectPermissionList;
    }

    private boolean hasClientRelationshipPermission(SecureObjectPermissionData secureObjectPermissionData)
    {
        return getClientTypeId()
               == null
               || getClientTypeId().equals(CommonConstants.PRIMARY_CLIENT)
               || getClientRelationshipPermission().contains(secureObjectPermissionData);
    }

    public Integer getGroupId()
    {
        return _groupId;
    }

    private void setGroupId(Integer groupId)
    {
        _groupId =
            groupId;
    }

    private List getClientProjectRoleValueList()
    {
        if (_clientProjectRoleValueList
            == null)
        {
            loadClientProjectRoles();
        }
        return _clientProjectRoleValueList;
    }

    private List getProjectRolePermissionValueList()
    {
        if (_projectRolePermissionValueList
            == null)
        {
            loadProjectPermissions();
        }
        return _projectRolePermissionValueList;
    }
}
