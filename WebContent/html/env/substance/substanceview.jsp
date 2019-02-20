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
<%
    Boolean
            blnCanDeleteScc =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SUBSTANCE_SCC_CAN_DELETE);%>
<fieldset><legend>
    <bean:write name="SubstanceForm"
                property="name" />
</legend></fieldset>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="substance.partNum" />
    </dt>
    <dd><bean:write name="SubstanceForm"
                    property="partNum" /></dd>
    <dt><bean:message key="substance.substanceType" />
    </dt>
    <dd><bean:write name="SubstanceForm"
                    property="substanceTypeDesc" /></dd>
</dl>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Substance SCC Info List
    </caption>
<%--
    <colgroup>
        <col style="width: 50px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>
                SCC Info Number
            </th>
            <th>
                Description
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_SUBSTANCE_SCC_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="10">
                    <bean:message key="substance.scc.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_SUBSTANCE_SCC_ACTIVE_LIST %>"
                        scope="session">
            <logic:iterate id="sccListVal"
                           name="<%= SessionKeys.EV_SUBSTANCE_SCC_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanDeleteScc)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/substancesccdeleteaction.do"
                                   paramId="evSubstanceSccId"
                                   paramName="sccListVal"
                                   paramProperty="substanceSccInfoId">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:write name="sccListVal"
                                    property="sccNumber" />
                    </td>
                    <td>
                        <bean:write name="sccListVal"
                                    property="sccDescription" />
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
