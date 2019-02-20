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
<%@ page import="com.sehinc.common.security.SecurityManager,
                 com.sehinc.erosioncontrol.action.base.RequestKeys,
                 com.sehinc.erosioncontrol.action.base.SessionKeys,
                 com.sehinc.security.SecureObjectPermissionData" %>
<fieldset><legend>
    Partners
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
            <th>
                Partner Name
            </th>
            <th>
                Type
            </th>
            <th>
                Contact
            </th>
            <th>
                Phone
            </th>
            <th>
                Email
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EC_CLIENT_PARTNER_LIST %>">
            <tr>
                <td colspan="6">
                    There are no Partners in the list.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_CLIENT_PARTNER_LIST %>">
            <%
                SecurityManager
                        securityManager =
                        SecurityManager.getSecurityManager(request); %>
            <logic:iterate id="partnerListValue"
                           name="<%= SessionKeys.EC_CLIENT_PARTNER_LIST %>">
                <tr>
                    <td>

                        <% if (securityManager.HasECPermission(SecureObjectPermissionData.PARTNER_UPDATE))
                        { %>
                        <html:link styleClass='btn btn-mini btn-success'
                                   action="/partnereditpage.do"
                                   paramId="<%= RequestKeys.EC_PARTNER_ID %>"
                                   paramName="partnerListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehedit.png"
                                      alt="Edit" />
                        </html:link>
                        <% }
                            if (securityManager.HasECPermission(SecureObjectPermissionData.PARTNER_DELETE))
                            { %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/partnerdeleteaction.do"
                                   paramId="id"
                                   paramName="partnerListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <% } %>
                    </td>
                    <td>
                        <% if (securityManager.HasECPermission(SecureObjectPermissionData.PARTNER_UPDATE))
                        { %>
                        <html:link action="/partnereditpage.do"
                                   paramId="<%= RequestKeys.EC_PARTNER_ID %>"
                                   paramName="partnerListValue"
                                   paramProperty="id">
                            <bean:write name="partnerListValue"
                                        property="name" />
                        </html:link>
                        <% }
                        else
                        { %>
                        <bean:write name="partnerListValue"
                                    property="name" />
                        <% } %>
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="clientRelationshipTypeName" />
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="contactFirstName" />
                        <bean:write name="partnerListValue"
                                    property="contactLastName" />
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="contactPhone" />
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="contactEmail" />
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
