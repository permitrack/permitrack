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
            blnCanDeleteAsset =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_ASSET_CAN_DELETE);%>
<fieldset><legend>
    <bean:write name="PermitDetailForm"
                property="name" />
</legend></fieldset>
<h4 class="myAccordian">
    Permit Detail Description
</h4>
<dl class="dl-horizontal">
    <logic:notEmpty name="PermitDetailForm"
                    property="description">
        <dt>
            <bean:message key="permit.desc" />
        </dt>
        <dd>
            <bean:write name="PermitDetailForm"
                        property="description" />
        </dd>
    </logic:notEmpty>
    <dt><bean:message key="permit.detail.avg.period" />
    </dt>
    <dd><bean:write name="PermitDetailForm"
                    property="avgPeriod" />
        <logic:notEmpty name="PermitDetailForm"
                        property="avgPeriod">
            <bean:write name="PermitDetailForm"
                        property="avgPeriodDesc" />
        </logic:notEmpty></dd>
    <dt><bean:message key="permit.detail.voc.limit" />
    </dt>
    <dd><bean:write name="PermitDetailForm"
                    property="vocLimit" />
        <logic:notEmpty name="PermitDetailForm"
                        property="vocLimit">
            <bean:write name="PermitDetailForm"
                        property="vocLimitDesc" />
        </logic:notEmpty></dd>
    <dt><bean:message key="permit.detail.haps.limit" />
    </dt>
    <dd><bean:write name="PermitDetailForm"
                    property="hapsLimit" />
        <logic:notEmpty name="PermitDetailForm"
                        property="hapsLimit">
            <bean:write name="PermitDetailForm"
                        property="hapsLimitDesc" />
        </logic:notEmpty></dd>
    <dt><bean:message key="permit.detail.mmbtu.limit" />
    </dt>
    <dd><bean:write name="PermitDetailForm"
                    property="mmbtuLimit" />
        <logic:notEmpty name="PermitDetailForm"
                        property="mmbtuLimit">
            <bean:write name="PermitDetailForm"
                        property="mmbtuLimitDesc" />
        </logic:notEmpty></dd>
</dl>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Permitted Assets
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
                Name
            </th>
            <th>
                Description
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_PERMIT_ASSET_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="3">
                    <bean:message key="permit.asset.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_PERMIT_ASSET_ACTIVE_LIST %>"
                        scope="session">
            <logic:iterate id="pmtAsset"
                           name="<%= SessionKeys.EV_PERMIT_ASSET_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <% if (blnCanDeleteAsset)
                        { %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/permitassetdeleteaction.do"
                                   paramId="evPermitAssetId"
                                   paramName="pmtAsset"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <% } %>
                    </td>
                    <td>
                        <bean:write name="pmtAsset"
                                    property="numberAndName" />
                    </td>
                    <td>
                        <bean:write name="pmtAsset"
                                    property="description" />
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

