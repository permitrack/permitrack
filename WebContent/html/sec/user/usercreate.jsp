<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ page import="com.sehinc.security.action.base.SessionKeys" %>
<%
    pageContext.setAttribute("stateList",
                             request.getSession()
                                     .getAttribute(SessionKeys.STATE_LIST));
    pageContext.setAttribute("roleListEC",
                             request.getSession()
                                     .getAttribute(SessionKeys.USER_ROLE_LIST_EC));
    pageContext.setAttribute("groupList",
                             request.getSession()
                                     .getAttribute(SessionKeys.USER_GROUP_LIST));
    Boolean
            SHOW_EC_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION);
    Boolean
            SHOW_SW_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION);
    Boolean
            SHOW_DV_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION);
    Boolean
            SHOW_EV_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION);
%>
<html:form styleClass="form-horizontal"
           action="/usercreateaction">
    <fieldset><legend>
        New User
    </legend></fieldset>
    <h4 class="myAccordian">
        User Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="firstName">
                First Name *
            </label>
            <div class="controls">
                <input type="text"
                       name="firstName"
                       id="firstName"
                       size="26"
                       maxlength="100"
                       value="<bean:write name='userForm' property='firstName'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="lastName">Last Name *
            </label>
            <div class="controls">
                <input type="text"
                       name="lastName"
                       id="lastName"
                       size="26"
                       maxlength="100"
                       value="<bean:write name='userForm' property='lastName'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="title">Title *
            </label>
            <div class="controls">
                <input type="text"
                       name="title"
                       id="title"
                       size="24"
                       maxlength="100"
                       value="<bean:write name='userForm' property='title'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="emailAddress">Email Address *
            </label>
            <div class="controls">
                <input type="text"
                       name="emailAddress"
                       id="emailAddress"
                       size="26"
                       maxlength="256"
                       value="<bean:write name='userForm' property='emailAddress'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="groupId">Account Type *
            </label>
            <div class="controls">
                <select name="groupId"
                        id="groupId">
                    <option value="0">...</option>
                    <logic:iterate id="groups"
                                   name="groupList">
                        <option value="<bean:write name='groups' property='id'/>">
                            <bean:write name='groups'
                                        property='name' />
                        </option>
                    </logic:iterate>
                </select>
                <input type="hidden"
                       name="txtGroupId"
                       id="txtGroupId"
                       value="<bean:write name='userForm' property='groupId'/>">
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Contact Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label"
                   for="addressLine1">
                Address Line 1
            </label>
            <div class="controls">
                <input type="text"
                       name="addressLine1"
                       id="addressLine1"
                       size="88"
                       maxlength="50"
                       value="<bean:write name='userForm' property='addressLine1'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="addressLine2">Address Line 2
            </label>
            <div class="controls">
                <input type="text"
                       name="addressLine2"
                       id="addressLine2"
                       size="88"
                       maxlength="50"
                       value="<bean:write name='userForm' property='addressLine2'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="city">City
            </label>
            <div class="controls">
                <input type="text"
                       name="city"
                       id="city"
                       size="26"
                       maxlength="50"
                       value="<bean:write name='userForm' property='city'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="state">State
            </label>
            <div class="controls">
                <select name="state"
                        id="state">
                    <option value="0"
                            selected>...
                    </option>
                    <logic:iterate id="theState"
                                   name="stateList">
                        <option value="<bean:write name='theState' property='code'/>"><bean:write name='theState'
                                                                                                  property='code' /></option>
                    </logic:iterate>
                </select>
                <input type="hidden"
                       name="txtState"
                       id="txtState"
                       value="<bean:write name='userForm' property='state'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="postalCode">Zip
            </label>
            <div class="controls">
                <input type="text"
                       name="postalCode"
                       id="postalCode"
                       size="14"
                       maxlength="10"
                       value="<bean:write name='userForm' property='postalCode'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="primaryPhone">Primary Phone
            </label>
            <div class="controls">
                <input type="text"
                       name="primaryPhone"
                       id="primaryPhone"
                       size="26"
                       maxlength="100"
                       value="<bean:write name='userForm' property='primaryPhone'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="secondaryPhone">Secondary Phone
            </label>
            <div class="controls">
                <input type="text"
                       name="secondaryPhone"
                       id="secondaryPhone"
                       size="26"
                       maxlength="100"
                       value="<bean:write name='userForm' property='secondaryPhone'/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="faxPhone">Fax
            </label>
            <div class="controls">
                <input type="text"
                       name="faxPhone"
                       id="faxPhone"
                       size="26"
                       maxlength="100"
                       value="<bean:write name='userForm' property='faxPhone'/>">
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Permissions
    </h4>
    <div>

    <% if (SHOW_EC_ACCESS_OPTION)
    { %>
        <div class="control-group">
            <label class="control-label">
                Erosion Control (ESC)
            </label>
            <div class="controls controls-row">
                <label class="checkbox"
                       for="accessEC">
                    <html:checkbox name="userForm"
                                   property="accessEC"
                                   styleId="accessEC" />
                    User can access ESC
                </label>
                <logic:notEmpty name="<%= SessionKeys.USER_ROLE_LIST_EC %>"
                                scope="session">
                    Role
                    <html:select name="userForm"
                                 property="roleIdEC"
                                 styleId="roleIdEC">
                        <html:options collection="<%= SessionKeys.USER_ROLE_LIST_EC %>"
                                      property="id"
                                      labelProperty="name" />
                    </html:select>
                </logic:notEmpty>
                <input type="hidden"
                       id="txtRoleIdEC"
                       name="txtRoleIdEC"
                       value="<bean:write name='userForm' property='roleIdEC'/>">
            </div>
        </div>
        <% } %>
        <% if (SHOW_SW_ACCESS_OPTION)
        { %>
        <div class="control-group">
            <label class="control-label">
                Storm/Sewer (MS4)
            </label>
            <div class="controls">
                <label class="checkbox"
                       for="accessSW">
                    <html:checkbox name="userForm"
                                   property="accessSW"
                                   styleId="accessSW" />
                    User can access MS4
                </label>
            </div>
        </div>
        <% } %>
        <% if (SHOW_DV_ACCESS_OPTION)
        { %>
        <div class="control-group">
            <label class="control-label">
                DataView (DVO)
            </label>
            <div class="controls">
                <label class="checkbox"
                       for="accessDV">
                    <html:checkbox name="userForm"
                                   property="accessDV"
                                   styleId="accessDV" />
                    User can access DVO
                </label>
            </div>
        </div>
        <% } %>
        <% if (SHOW_EV_ACCESS_OPTION)
        { %>
        <div class="control-group">
            <label class="control-label">
                Environment (ENV)
            </label>
            <div class="controls controls-row">
                <label class="checkbox"
                       for="accessEV">
                    <html:checkbox name="userForm"
                                   property="accessEV"
                                   styleId="accessEV" />
                    User can access ENV
                </label>
                <logic:notEmpty name="<%= SessionKeys.USER_ROLE_LIST_EV %>"
                                scope="session">
                    Select user role
                    <html:select name="userForm"
                                 property="roleIdEV"
                                 styleId="roleIdEV">
                        <html:options collection="<%= SessionKeys.USER_ROLE_LIST_EV %>"
                                      property="id"
                                      labelProperty="name" />
                    </html:select>
                </logic:notEmpty>
                <input type="hidden"
                       id="txtRoleIdEV"
                       name="txtRoleIdEV"
                       value="<bean:write name='userForm' property='roleIdEV'/>">
            </div>
        </div>
        <% } %>
    </div>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Cancel" />
            </div>
            <div class="span6">
                <html:submit styleClass="btn btn-success btn-large pull-right"
                             property="submit"
                             value="Save"
                             onclick="return RequiredFields();" />
            </div>
        </tiles:put>
    </tiles:insert>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            //<!-- Script Begin
            function SetState()
            { // Set
                var cboS = document.getElementById("state");
                var cboSH = document.getElementById("txtState");
                cboS.value
                        = cboSH.value;
            } // Set
            function RequiredFields()
            {
                var blnReturn = false;
                var txtFN = document.getElementById("firstName");
                var txtLN = document.getElementById("lastName");
                var txtTI = document.getElementById("title");
                var txtEM = document.getElementById("emailAddress");
                var cboGrp = document.getElementById("groupId");
                var txtPP = document.getElementById("primaryPhone");
                var txtSP = document.getElementById("secondaryPhone");
                var txtFX = document.getElementById("faxPhone");
                var strMessage = "";
                var blnPhone = new Object();
                blnPhone.valid
                        = false;
                if (txtPP.value
                        != "")
                {
                    txtPP.value
                            = formatPhone(txtPP.value,
                                          'Primary Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Primary Phone is invalid, ");
                    }
                }
                if (txtSP.value
                        != "")
                {
                    txtSP.value
                            = formatPhone(txtSP.value,
                                          'Secondary Phone',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Secondary Phone is invalid, ");
                    }
                }
                if (txtFX.value
                        != "")
                {
                    txtFX.value
                            = formatPhone(txtFX.value,
                                          'Fax',
                                          blnPhone);
                    if (!blnPhone.valid)
                    {
                        strMessage
                                = strMessage.concat("Fax Phone is invalid, ");
                    }
                }
                // txtFN and txtLN
                var strFullName = txtFN.value;
                strFullName
                        = strFullName.concat(txtLN.value);
                if (strFullName.length
                        <= 3)
                {
                    strMessage
                            = strMessage.concat("Full name must be at least four characters, ");
                }
                else
                {
                    // no spaces allowed
                    if (!NoSpaces(txtFN.value))
                    {
                        strMessage
                                = strMessage.concat("First Name can not contain any spaces, ");
                    }
                    if (!NoSpaces(txtLN.value))
                    {
                        strMessage
                                = strMessage.concat("Last Name can not contain any spaces, ");
                    }
                    // valid characters only
                    if (!ValidChars(txtFN.value))
                    {
                        strMessage
                                = strMessage.concat("First Name contains invalid characters, ");
                    }
                    if (!ValidChars(txtLN.value))
                    {
                        strMessage
                                = strMessage.concat("Last Name contains invalid characters, ");
                    }
                }
                if (txtFN.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("First Name is required, ");
                }
                if (txtLN.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Last Name is required, ");
                }
                if (txtTI.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Title is required, ");
                }
                if (txtEM.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Email is required, ");
                }
                if (cboGrp.value
                        == 0)
                {
                    strMessage
                            = strMessage.concat("Account Type is required, ");
                }
                if (strMessage.length
                        > 0)
                {
                    // Trim the last comma from the message string
                    strMessage
                            = strMessage.substr(0,
                                                strMessage.length
                                                        - 2)
                            + ".";
                    $('#dialog').html("Please provide the required fields. "
                                              + strMessage).dialog('open');
                }
                else
                {
                    blnReturn
                            = true;
                }
                return blnReturn;
            }
            SetState();//  Script End -->
        </script>
    </tiles:put>
</tiles:definition>
