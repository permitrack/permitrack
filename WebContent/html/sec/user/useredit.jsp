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
    Boolean
            ADMIN_LOGGED_IN =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.ADMIN_LOGGED_IN);%>
<html:form styleClass="form-horizontal"
           action="/usereditaction">
    <html:hidden name='userForm'
                 property='username'
                 styleId='username' />
    <html:hidden name="userForm"
                 property="id"
                 styleId="id" />
    <fieldset><legend>
        Edit User
    </legend></fieldset>
    <h4 class="myAccordian">
        User Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Username
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='username' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">First Name *
            </label>
            <div class="controls"><html:text name='userForm'
                                             property='firstName'
                                             styleId='firstName'
                                             size="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Last Name *
            </label>
            <div class="controls"><html:text name='userForm'
                                             property='lastName'
                                             styleId='lastName'
                                             size="26" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Title *
            </label>
            <div class="controls"><html:text name='userForm'
                                             property='title'
                                             size="26"
                                             styleId='title' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Email Address
            </label>
            <div class="controls">
                <a href="mailto:<bean:write name='userForm' property='emailAddress'/>">
                    <bean:write name='userForm'
                                property='emailAddress' />
                </a>
                <html:hidden name='userForm'
                             property='emailAddress'
                             styleId='emailAddress' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                Account Type *
            </label>
            <div class="controls"> <%if (ADMIN_LOGGED_IN)
            {%>
                <html:select name="userForm"
                             property="groupId"
                             styleId="groupId">
                    <html:option value="0">...</html:option>
                    <html:options collection="<%= SessionKeys.USER_GROUP_LIST %>"
                                  property="id"
                                  labelProperty="name" />
                </html:select>
                <%}
                else
                {%>
                <bean:write name="userForm"
                            property="accountType" />
                <html:hidden name="userForm"
                             property="groupId"
                             styleId="groupId" />
                <%}%>
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Contact Information
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Address Line 1
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='addressLine1' />
                <html:hidden name='userForm'
                             property='addressLine1'
                             styleId='addressLine1' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Address Line 2
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='addressLine2' />
                <html:hidden name='userForm'
                             property='addressLine2'
                             styleId='addressLine2' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">City
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='city' />
                <html:hidden name='userForm'
                             property='city'
                             styleId='city' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">State
            </label>
            <div class="controls"><logic:equal name='userForm'
                                               property='state'
                                               value='0'>
            </logic:equal>
                <logic:notEqual name='userForm'
                                property='state'
                                value='0'>
                    <bean:write name='userForm'
                                property='state' />
                </logic:notEqual>
                <html:hidden name='userForm'
                             property='state'
                             styleId='state' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Zip
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='postalCode' />
                <html:hidden name='userForm'
                             property='postalCode'
                             styleId='postalCode' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Primary Phone
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='primaryPhone' />
                <html:hidden name='userForm'
                             property='primaryPhone'
                             styleId='primaryPhone' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Secondary Phone
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='secondaryPhone' />
                <html:hidden name='userForm'
                             property='secondaryPhone'
                             styleId='secondaryPhone' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Fax
            </label>
            <div class="controls"><bean:write name='userForm'
                                              property='faxPhone' />
                <html:hidden name='userForm'
                             property='faxPhone'
                             styleId='faxPhone' />
            </div>
        </div>
    </div>
    <% if (ADMIN_LOGGED_IN)
    { %>
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
                    Select user role
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
                <label class="checkbox accessEV">
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
    <% } %>
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
            function SetRoles()
            {
                var txtEC = document.getElementById("txtRoleIdEC");
                var txtDV = document.getElementById("txtRoleIdDV");
                var txtGrp = document.getElementById("txtGroupId");
                var txtEV = document.getElementById("txtRoleIdEV");
                var cboEC = document.getElementById("roleIdEC");
                var cboDV = document.getElementById("roleIdDV");
                var cboGrp = document.getElementById("groupId");
                var cboEV = document.getElementById("roleIdEV");
                cboEC.value
                        = txtEC.value;
                cboDV.value
                        = txtDV.value;
                cboGrp.value
                        = txtGrp.value;
                cboEV.value
                        = txtEV.value;
            }
            function RequiredFields()
            {
                var blnReturn = false;
                var txtFN = document.getElementById("firstName");
                var txtLN = document.getElementById("lastName");
                var txtTI = document.getElementById("title");
                var txtEM = document.getElementById("emailAddress");
                var cboGrp = document.getElementById("groupId");
                var strMessage = "";
                var txtPP = document.getElementById("primaryPhone");
                var txtSP = document.getElementById("secondaryPhone");
                var txtFX = document.getElementById("faxPhone");
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
                // Format the error message;
                if (strMessage.length
                        > 0)
                { // Information not all provided
                    // Trim the last comma from the message string
                    strMessage
                            = strMessage.substr(0,
                                                strMessage.length
                                                        - 2)
                            + ".";
                    $('#dialog').html("Please provide the required fields. "
                                              + strMessage).dialog('open');
                } // Information not all provided
                else
                { //
                    blnReturn
                            = true;
                } //
                return blnReturn;
            }//  Script End -->
        </script>
    </tiles:put>
</tiles:definition>
