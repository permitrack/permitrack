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
<%pageContext.setAttribute("lstEcActive",
                           request.getSession()
                                   .getAttribute(SessionKeys.ROLE_LIST_EC_ACTIVE));
    pageContext.setAttribute("lstEvActive",
                             request.getSession()
                                     .getAttribute(SessionKeys.ROLE_LIST_EV_ACTIVE));
    pageContext.setAttribute("group",
                             request.getSession()
                                     .getAttribute(SessionKeys.SECURITY_GROUP_ID));%>
<fieldset><legend>
    Roles
</legend></fieldset>
<table class="table table-hover action-first action-large">
<%--
    <colgroup>
        <col style="width: 110px; white-space: nowrap;" />
    </colgroup>
--%>
    <caption class="label">
        Erosion Control (ESC) Roles
    </caption>
    <thead>
        <tr>
            <th></th>
            <th>
                Role
            </th>
            <th>
                Description
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:notEmpty name="lstEcActive">
            <logic:iterate id="roles"
                           name="lstEcActive">
                <tr>
                    <td>
                        <logic:notEqual name="group"
                                        value="3">
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/roleeceditpageaction.do"
                                       paramId="role_id"
                                       paramName="roles"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehedit.png"
                                          alt="Edit" />
                            </html:link>
                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                       action="/roleecdeleteaction.do"
                                       paramId="role_id"
                                       paramName="roles"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                        </logic:notEqual>
                    </td>
                    <td>
                        <html:link action="/roleecviewpageaction.do"
                                   paramId="role_id"
                                   paramName="roles"
                                   paramProperty="id">
                            <bean:write name="roles"
                                        property="name" />
                        </html:link>
                    </td>
                    <td>
                        <bean:write name="roles"
                                    property="description" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="lstEcActive">
            <tr>
                <td colspan="3"
                    class="text-warning">
                    No active roles found
                </td>
            </tr>
        </logic:empty>
    </tbody>
</table>
<table class="table table-hover action-first action-large">
<%--
    <colgroup>
        <col style="width: 110px; white-space: nowrap;" />
    </colgroup>
--%>
    <caption class="label">
        Environment (ENV) Roles
    </caption>
    <thead>
        <tr>
            <th></th>
            <th>
                Role
            </th>
            <th>
                Description
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:notEmpty name="lstEvActive">
            <logic:iterate id="evroles"
                           name="lstEvActive">
                <tr>
                    <td>
                        <logic:notEqual name="group"
                                        value="3">
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/roleeveditpageaction.do"
                                       paramId="role_id"
                                       paramName="evroles"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehedit.png"
                                          alt="Edit" />
                            </html:link>
                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                       action="/roleevdeleteaction.do"
                                       paramId="role_id"
                                       paramName="evroles"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                        </logic:notEqual>
                    </td>
                    <td>
                        <html:link action="/roleevviewpageaction.do"
                                   paramId="role_id"
                                   paramName="evroles"
                                   paramProperty="id">
                            <bean:write name="evroles"
                                        property="name" />
                        </html:link>
                    </td>
                    <td>
                        <bean:write name="evroles"
                                    property="description" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="lstEvActive">
            <tr>
                <td colspan="3">
                    No active roles found
                </td>
            </tr>
        </logic:empty>
    </tbody>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
