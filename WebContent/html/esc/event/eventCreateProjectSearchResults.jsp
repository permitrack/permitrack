<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ include file="eventPageHeader.jsp" %>
<html:form method="POST"
           action="/eventCreateProjectSearchResultsAction">
    <html:hidden name="eventCreateForm"
                 property="page"
                 value="3" />
    <html:hidden styleId="nextPage"
                 name="eventCreateForm"
                 property="nextPage" />
    <table class="table table-hover action-first action-small">
        <thead>
            <tr>
                <th>
                    <input type=checkbox
                           name="checkAll"
                           id="checkAll"
                           onclick="SetAllCheckBoxes();" />
                </th>
                <th>Name</th>
                <th>Permittee</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EC_PROJECT_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="4">
                        There are no Projects found.
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_LIST %>"
                            scope="session">
                <logic:iterate id="item"
                               name="<%= SessionKeys.EC_PROJECT_LIST %>">
                    <tr>
                        <td>
                            <html:multibox property="projectSearchForm.selectedProjects">
                                <bean:write name="item"
                                            property="id" />
                            </html:multibox>
                        </td>
                        <td>
                            <bean:write name="item"
                                        property="name" />
                        </td>
                        <td>
                            <bean:write name="item"
                                        property="permittedClientName" />
                        </td>
                        <td>
                            <bean:write name="item"
                                        property="viewAddress" />
                        </td>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span4">
                <html:submit styleClass="btn btn-large"
                             onclick="return Cancel_OnClick();"
                             value="Cancel" />
            </div>
            <div class="span4">
                <html:submit styleClass="btn btn-danger btn-large pull-right"
                             onclick="return Back_OnClick();"
                             value="Back" />
            </div>
            <div class="span4">
                <logic:notEmpty name="<%= SessionKeys.EC_PROJECT_LIST %>"
                                scope="session">
                    <html:submit styleClass="btn btn-success btn-large pull-right"
                                 onclick="return Next_OnClick();"
                                 value="Finish and Save" />
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
                        < document.eventCreateForm.elements.length; i++)
                {
                    if (document.eventCreateForm.elements[i].name
                            == 'projectSearchForm.selectedProjects')
                    {
                        document.eventCreateForm.elements[i].checked
                                = CheckValue;
                    }
                }
            }
        </script>
    </tiles:put>
</tiles:definition>
