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
<%pageContext.setAttribute("allSOPList",
                           request.getSession()
                                   .getAttribute(SessionKeys.EC_SECURE_OBJECT_PERMISSION_LIST));
    pageContext.setAttribute("headersList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_SECURE_OBJECT_HEADERS_LIST));%>
<html:form styleClass="form-horizontal"
           action="/roleeccreateaction">
    <fieldset><legend>
        New Role
    </legend></fieldset>
    <h4 class="myAccordian">
        Role Description
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Name
            </label>
            <div class="controls"><html:text name="RoleECForm"
                                             property="name"
                                             styleId="name"
                                             size="50" />
                <html:hidden name="RoleECForm"
                             property="clientId"
                             styleId="clientId" />
                <html:hidden name="RoleECForm"
                             property="code"
                             styleId="code" />
                <input type="hidden"
                       id="txtRoleList"
                       name="txtRoleList"
                       value="<%= SessionKeys.ROLE_NAME_LIST %>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Description
            </label>
            <div class="controls"><html:text name="RoleECForm"
                                             property="description"
                                             size="68"
                                             maxlength="200"
                                             styleId="description" />
            </div>
        </div>
    </div>
    <h4 class="myAccordian">
        Permissions
    </h4>
    <div>
        <div class="control-group">
            <label class="control-label">
                Select permissions
            </label>
            <div class="controls">
                <table class="table table-hover table-condensed table-bordered">
                    <thead>
                        <tr>
                            <th>
                                Entity/Permission
                            </th>
                            <logic:iterate id="header"
                                           name="headersList">
                                <th>
                                    <bean:write name="header" />
                                </th>
                            </logic:iterate>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate id="currentSO"
                                       name="allSOPList"
                                       indexId="myIndex">
                            <tr>
                                <logic:iterate id="roleSOPVal"
                                               name="currentSO"
                                               indexId="myIndex">
                                    <logic:equal name="myIndex"
                                                 value="0">
                                        <td>
                                            <bean:write name="roleSOPVal"
                                                        property="soName" />
                                        </td>
                                    </logic:equal>
                                    <td>
                                        <label for="selectedSOP"
                                               style="display: none;"></label>
                                        <input type="checkbox"
                                               name="selectedSOP"
                                               id="selectedSOP"
                                               value="<bean:write name='roleSOPVal' property='sopId'/>" />
                                    </td>
                                </logic:iterate>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            </div>
        </div>
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
            function RequiredFields()
            {
                var blnRequiredFields = false;
                var txtNM = document.getElementById("name");
                var txtDE = document.getElementById("description");
                var strMessage = "";
                txtNM.value
                        = Trim(txtNM.value);
                txtDE.value
                        = Trim(txtDE.value);
                if (txtNM.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Erosion Control (ESC) Role Name is required, ");
                }
                if (txtDE.value
                        == "")
                {
                    strMessage
                            = strMessage.concat("Erosion Control (ESC) Role Description is required, ");
                }
                if (RoleExists(txtNM.value))
                {
                    strMessage
                            = strMessage.concat("Erosion Control (ESC) Role Name already exists.  Enter a different name and try again., ");
                }
                if (InValidChars(txtNM.value))
                {
                    strMessage
                            = strMessage.concat("Erosion Control (ESC) Role Name contains invalid characters, ");
                }
                if (strMessage.length
                        > 0)
                {
                    // Trim the last comma from the message string
                    $('#dialog').html("Please provide the required fields: "
                                              + strMessage.substr(0,
                                                                  strMessage.length
                                                                          - 2)
                                              + ".").dialog('open');
                }
                else
                {
                    blnRequiredFields
                            = true;
                }
                return blnRequiredFields;
            }
            function RoleExists(sString)
            {
                var blnReturn = false;
                var txtU = document.getElementById("txtRoleList");
                if (Trim(txtU.value)
                        != "")
                {
                    sString
                            = sString.concat(",");
                    blnReturn
                            = (txtU.value.indexOf(sString)
                            >= 0);
                }
                return blnReturn;
            }
            function InValidChars(sString)
            {
                var check = "<";
                var inValid = false;
                var ch = "";
                sString
                        = Trim(sString);
                if (sString.length
                        >= 1)
                {
                    for (i
                                 = 0; i
                            < sString.length; i++)
                    {
                        if (!inValid)
                        {
                            ch
                                    = sString.charAt(i);
                            inValid
                                    = (check.indexOf(ch)
                                    >= 0);
                        }
                    }
                }
                return inValid;
            }//  Script End -->
        </script>
    </tiles:put>
</tiles:definition>
