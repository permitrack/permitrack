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
    Partner Search Results
</legend></fieldset>
<table class="table table-hover action-first action-small">
<%--
    <colgroup>
        <col style="width: 50px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>Organization</th>
            <th>City</th>
            <th>State</th>
            <th>Zip</th>
            <th>Contact</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EC_FIND_RESULTS_PARTNER_LIST %>"
                     scope="session">
            <tr>
                <td colspan="8">
                    Your search returned no Partners. Please revise your search criteria and try again.
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EC_FIND_RESULTS_PARTNER_LIST %>"
                        scope="session">
            <logic:iterate id="partnerListValue"
                           name="<%= SessionKeys.EC_FIND_RESULTS_PARTNER_LIST %>">
                <tr>
                    <td>
                        <%if (SecurityManager.getSecurityManager(request)
                                .HasECPermission(SecureObjectPermissionData.PARTNER_UPDATE))
                        {%>
                        <html:link styleClass="btn btn-mini btn-success"
                                   action="/partneraddpage.do"
                                   paramId="<%= RequestKeys.EC_PARTNER_ID %>"
                                   paramName="partnerListValue"
                                   paramProperty="id">
                            <bean:message key="partner.list.add" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="name" />
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="city" />
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="state" />
                    </td>
                    <td>
                        <bean:write name="partnerListValue"
                                    property="postalCode" />
                    </td>
                    <td>
                        <logic:notEmpty name="partnerListValue"
                                        property="contactLastName">
                            <bean:write name="partnerListValue"
                                        property="contactFirstName" />
                            <bean:write name="partnerListValue"
                                        property="contactLastName" />
                        </logic:notEmpty>
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
<logic:empty name="<%= SessionKeys.EC_FIND_RESULTS_PARTNER_LIST %>">
    <tiles:insert page="../../toolbar.jsp">
        <tiles:put name="controls"
                   direct="true">
            <div class="span6">
                <html:link action="partnerlistpage"
                           styleClass="btn btn-large"
                           title="Cancel" />
            </div>
            <div class="span6">
                <html:link action="partnerfindpage"
                           styleClass="btn btn-success btn-large pull-right"
                           titleKey="partner.search.again" />
            </div>
        </tiles:put>
    </tiles:insert>
</logic:empty>
