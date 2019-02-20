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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<html:form styleClass="form-horizontal"
           action="/sccimportaction">
    <fieldset><legend>
        Import SCC
    </legend></fieldset>
    <table class="table table-hover action-first action-small">
        <thead>
            <tr>
                <th></th>
                <th>
                    Number
                </th>
                <th>
                    Description
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:empty name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST %>"
                         scope="session">
                <tr>
                    <td colspan="3">
                        <bean:message key="scc.library.empty.list" />
                    </td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST  %>"
                            scope="session">
                <logic:iterate id="sccLibraryListValue"
                               name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST %>"
                               scope="session">
                    <tr>
                        <td>
                            <html:multibox name="SccLibraryForm"
                                           property="sccLibraryItems">
                                <bean:write name="sccLibraryListValue"
                                            property="id" />
                            </html:multibox>
                        </td>
                        <td>
                            <bean:write name="sccLibraryListValue"
                                        property="number" />
                        </td>
                        <td>
                            <bean:write name="sccLibraryListValue"
                                        property="descriptionAndName" />
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
                             value="Cancel" />
            </div>
            <div class="span6">
                <logic:notEmpty name="<%= SessionKeys.EV_ACTIVE_SCC_LIBRARY_LIST  %>"
                                scope="session">
                    <html:submit styleClass="btn btn-success btn-large pull-right"
                                 value="Import" />
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
    </tiles:put>
</tiles:definition>
