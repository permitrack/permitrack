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
<%@ page import="com.sehinc.erosioncontrol.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/projectemailaction">
    <table class="table table-hover action-first action-small">
        <caption class="label">
            Project Contacts
        </caption>
        <thead>
            <tr>
                <th>
                    <input type=checkbox
                           name="checkAll"
                           id="checkAll"
                           onclick="SetAllCheckBoxes();" />
                </th>
                <th>
                    Name
                </th>
                <th>
                    Organization
                </th>
                <th>
                    Contact Type
                </th>
                <th>
                    Email
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="5">
                        There are no Contacts associated with this project.
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>"
                            scope="session">
                <logic:iterate id="contact"
                               name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>">
                    <tr>
                        <td width="20">
                            <html:multibox name="projectEmailForm"
                                           property="projectContacts">
                                <bean:write name="contact"
                                            property="id" />
                            </html:multibox>
                        </td>
                        <td>
                            <bean:write name="contact"
                                        property="firstName" />
                            <bean:write name="contact"
                                        property="lastName" />
                        </td>
                        <td>
                            <bean:write name="contact"
                                        property="organizationName" />
                        </td>
                        <td>
                            <bean:write name="contact"
                                        property="contactTypeName" />
                        </td>
                        <td>
                            <bean:write name="contact"
                                        property="email" />
                        </td>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:cancel styleClass="btn btn-large"
                             value="Skip" />
            </div>
            <div class="span6">
                <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_CONTACT_LIST %>"
                                scope="session">
                    <html:submit styleClass="btn btn-success btn-large pull-right"
                                 value="Send" />
                </logic:notEmpty>
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
            function SetAllCheckBoxes()
            {
                var selectAllBox = document.getElementById('checkAll');
                var CheckValue = selectAllBox.checked;
                for (var i = 0; i
                        < document.projectEmailForm.elements.length; i++)
                {
                    if (document.projectEmailForm.elements[i].name
                            == 'projectContacts')
                    {
                        document.projectEmailForm.elements[i].checked
                                = CheckValue;
                        //                $.uniform.update(document.projectEmailForm.elements[i]);
                    }
                }
            }
        </script>
    </tiles:put>
</tiles:definition>
