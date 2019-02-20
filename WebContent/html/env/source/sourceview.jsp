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
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SOURCE_SUBSTANCE_CAN_DELETE);
    Boolean
            blnCanDeleteScc =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SOURCE_SCC_CAN_DELETE);%>
<fieldset><legend>
    <bean:write name="SourceForm"
                property="displayName" />
</legend></fieldset>
<h4 class="myAccordian">
    Source Description
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="source.sourceType" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="sourceTypeDesc" /></dd>
    <dt><bean:message key="source.desc" />
    </dt>
    <dd>
        <bean:write name="SourceForm"
                    property="description" />
    </dd>
    <dt><bean:message key="source.partNumber" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="partNumber" /></dd>
    <dt><bean:message key="source.batchNumber" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="batchNumber" /></dd>
    <dt><bean:message key="source.info.origin" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="infoOrigin" /></dd>
    <dt><bean:message key="source.info.color" />
    </dt>
    <dd>
        <logic:notEmpty name="SourceForm"
                        property="displayColorCd">
            <div style="width: 10px; height: 10px; background-color: <bean:write name="SourceForm" property="displayColorCd"/>"></div>
        </logic:notEmpty>
    </dd>
</dl>
<h4 class="myAccordian">
    Physical Characteristics
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="source.lbs.voc" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="lbsVoc" />
        <bean:write name="SourceForm"
                    property="lbsVocDesc" /></dd>
    <dt><bean:message key="source.density" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="density" />
        <bean:write name="SourceForm"
                    property="densityDesc" /></dd>
    <dt><bean:message key="source.lbs.haps" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="lbsHaps" />
        <bean:write name="SourceForm"
                    property="lbsHapsDesc" /></dd>
    <dt><bean:message key="source.pct.solids.weight" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="pctSolidsWeight" /></dd>
    <dt><bean:message key="source.pct.solids.volume" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="pctSolidsVolume" /></dd>
    <dt><bean:message key="source.hc.fuel" />
    </dt>
    <dd><bean:write name="SourceForm"
                    property="hcFuel" />
        <logic:notEmpty name="SourceForm"
                        property="hcFuel">
            BTU/cf
        </logic:notEmpty></dd>
</dl>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Source Substances
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
                CAS/Part Number
            </th>
            <th>
                Name
            </th>
            <th>
                % Formula (by Weight)/Emission Factor
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="4">
                    <bean:message key="source.substance.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                        scope="session">
            <logic:iterate id="subListValue"
                           name="<%= SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanDelete)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/sourcesubstancedeleteaction.do"
                                   paramId="evSourceSubstanceId"
                                   paramName="subListValue"
                                   paramProperty="sourceSubstanceId">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:write name="subListValue"
                                    property="substancePartNumber" />
                    </td>
                    <td>
                        <bean:write name="subListValue"
                                    property="substanceName" />
                        <bean:write name="subListValue"
                                    property="substanceType" />
                    </td>
                    <logic:equal name="subListValue"
                                 property="sourceTypeCode"
                                 value="1">
                        <td>
                            <bean:write name="subListValue"
                                        property="ratio" />
                        </td>
                    </logic:equal>
                    <logic:equal name="subListValue"
                                 property="sourceTypeCode"
                                 value="2">
                        <td>
                            <bean:write name="subListValue"
                                        property="natGasEmFactor" />
                            <bean:write name="subListValue"
                                        property="natGasEfUnitDesc" />
                        </td>
                    </logic:equal>
                    <logic:equal name="subListValue"
                                 property="sourceTypeCode"
                                 value="3">
                        <td>
                            <bean:write name="subListValue"
                                        property="bulkLiqEmFactor" />
                            <bean:write name="subListValue"
                                        property="bulkLiqEfUnitDesc" />
                        </td>
                    </logic:equal>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Source SCC Info List
    </caption>
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
        <logic:empty name="<%= SessionKeys.EV_SOURCE_SCC_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="3">
                    <bean:message key="source.scc.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_SOURCE_SCC_ACTIVE_LIST %>"
                        scope="session">
            <logic:iterate id="sccListVal"
                           name="<%= SessionKeys.EV_SOURCE_SCC_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanDeleteScc)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/sourcesccdeleteaction.do"
                                   paramId="evSourceSccId"
                                   paramName="sccListVal"
                                   paramProperty="sourceSccInfoId">
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
