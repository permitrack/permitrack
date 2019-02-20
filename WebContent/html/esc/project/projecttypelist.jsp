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
<%
    Boolean
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PROJECT_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PROJECT_DELETE);
    pageContext.setAttribute("projectTypeList",
                             request.getSession()
                                     .getAttribute(SessionKeys.PROJECT_TYPE_LIST));%>
<fieldset><legend>
    Project Types
</legend></fieldset>
<table class="table table-hover action-first">
<%--
    <colgroup>
        <col style="width: 90px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Description</th>
            <th>End Point Type</th>
            <th>SWMP</th>
            <th>EOLAM</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="projectTypeList">
            <tr>
                <td colspan="6">
                    There are no items in the list.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="projectTypeList">
            <logic:iterate id="projectType"
                           name="projectTypeList">
                <tr>
                    <td>

                        <% if (blnCanEdit)
                        { %>
                        <html:link styleClass='btn btn-mini btn-success'
                                   action="/projecttypeeditpage.do"
                                   paramId="projectTypeId"
                                   paramName="projectType"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehedit.png"
                                      alt="Edit"
                                      title="Edit" />
                        </html:link>
                        <% } %>
                        <% if (blnCanDelete)
                        { %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/projecttypedelete.do"
                                   paramId="id"
                                   paramName="projectType"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete"
                                      title="Delete" />
                        </html:link>
                        <% } %>
                    </td>
                    <td>
                        <html:link action="/projecttypeviewpage.do"
                                   paramId="projectTypeId"
                                   paramName="projectType"
                                   paramProperty="id">
                            <bean:write name="projectType"
                                        property="name" />
                        </html:link>
                    </td>
                    <td>
                        <bean:write name="projectType"
                                    property="description" />
                    </td>
                    <td>
                        <bean:write name="projectType"
                                    property="endPointType.name" />
                    </td>
                    <td>
                        <bean:write name="projectType"
                                    property="swmpYesNoText" />
                    </td>
                    <td>
                        <bean:write name="projectType"
                                    property="extendedOnlineAccessMonths" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
