<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
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
<%
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PROCESS_CAN_DELETE);%>
<fieldset><legend>
    <bean:write name="ProcessForm"
                property="processNumber" />
    <bean:write name="ProcessForm"
                property="name" />
</legend></fieldset>
<logic:notEmpty name="ProcessForm"
                property="parentProcessNumber">
    <h5>
        <bean:message key="process.parent.process" />
        <html:link action="/processviewpage.do"
                   paramId="evProcessId"
                   paramName="ProcessForm"
                   paramProperty="parentProcessId">
            <bean:write name="ProcessForm"
                        property="parentProcessNumber" />
            <bean:write name="ProcessForm"
                        property="parentProcessName" />
        </html:link>
    </h5>
</logic:notEmpty>
<h4 class="myAccordian">
    Process Desciption
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="process.number" />
    </dt>
    <dd>
        <bean:write name="ProcessForm"
                    property="processNumber" />
    </dd>
    <dt>
        <bean:message key="process.name" />
    </dt>
    <dd>
        <bean:write name="ProcessForm"
                    property="name" />
    </dd>
    <dt>
        <bean:message key="process.description" />
    </dt>
    <dd>
        <bean:write name="ProcessForm"
                    property="description" />
    </dd>
</dl>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Attached Asset Details
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
                Number
            </th>
            <th>
                Name
            </th>
            <th>
                Active Date
            </th>
            <th>
                Inactive Date
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="ProcessForm"
                     property="assetList">
            <tr>
                <td colspan="5">
                    <bean:message key="process.asset.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="ProcessForm"
                        property="assetList">
            <logic:iterate id="processAssetValue"
                           name="ProcessForm"
                           property="assetList">
                <tr>
                    <td>

                        <%if (blnCanDelete)
                        {%>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/processassetdeleteaction.do"
                                   paramId="evProcessAssetId"
                                   paramName="processAssetValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:write name="processAssetValue"
                                    property="assetNumber" />
                    </td>
                    <td>
                        <bean:write name="processAssetValue"
                                    property="assetName" />
                    </td>
                    <td>
                        <bean:write name="processAssetValue"
                                    property="activeTsString" />
                    </td>
                    <td>
                        <bean:write name="processAssetValue"
                                    property="inactiveTsString" />
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
