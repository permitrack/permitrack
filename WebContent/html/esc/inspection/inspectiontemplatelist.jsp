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
                    .getAttribute(SessionKeys.BMP_TEMPLATE_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.BMP_TEMPLATE_CAN_DELETE);%>
<fieldset><legend>
    Inspection Templates
</legend></fieldset>
<table class="table table-hover action-first">
<%--
    <colgroup>
        <col style="width:90px; white-space: nowrap;" />
        <col />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>
                Template Name
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EC_INSPECTION_TEMPLATE_LIST %>">
            <tr>
                <td colspan="2">
                    There are no Templates in the list.
                </td>
            </tr>
        </logic:empty>
        <logic:iterate id="inspectionTemplateValue"
                       name="<%= SessionKeys.EC_INSPECTION_TEMPLATE_LIST %>">
            <tr>
                <td>

                    <%
                        if (blnCanEdit)
                        {
                    %>
                    <html:link styleClass='btn btn-mini btn-success'
                               action="/inspectiontemplateeditpage.do"
                               paramId="ecInspectionTemplateId"
                               paramName="inspectionTemplateValue"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehedit.png"
                                  alt="Edit" />
                    </html:link>
                    <%
                        }
                        if (blnCanDelete)
                        {
                    %>
                    <html:link styleClass='btn btn-mini btn-danger warn-delete'
                               action="/inspectiontemplatedeleteaction.do"
                               paramId="ecInspectionTemplateId"
                               paramName="inspectionTemplateValue"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehdelete.png"
                                  alt="Delete" />
                    </html:link>
                    <%}%>
                </td>
                <td>
                    <bean:write name="inspectionTemplateValue"
                                property="name" />
                </td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
