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
            blnCanEdit =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_DETAIL_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_DETAIL_CAN_DELETE);
    Boolean
            blnCanDeleteFacility =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_FACILITY_CAN_DELETE);
    Boolean
            blnCanDeleteSubstance =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.PERMIT_SUBSTANCE_CAN_DELETE);%>
<fieldset><legend>
    <bean:write name="PermitForm"
                property="name" />
</legend></fieldset>
<p class="muted">
    <logic:notEmpty name="PermitForm"
                    property="description">
        <bean:write name="PermitForm"
                    property="description" />
    </logic:notEmpty>
</p>
<dl class="dl-horizontal">
    <dt><bean:message key="permit.active.date" />
    </dt>
    <dd><bean:write name="PermitForm"
                    property="activeTsString" /></dd>
    <dt><bean:message key="permit.inactive.date" />
    </dt>
    <dd><bean:write name="PermitForm"
                    property="inactiveTsString" /></dd>
</dl>
<table class="table table-hover action-first">
    <caption class="label">
        Permit Details
    </caption>
<%--
    <colgroup>
        <col style="width: 90px; white-space: nowrap;" />
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
        <logic:empty name="<%= SessionKeys.EV_PERMIT_DETAIL_ACTIVE_LIST_BY_PERMIT_ID %>"
                     scope="session">
            <tr>
                <td colspan="3">
                    <bean:message key="permit.detail.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_PERMIT_DETAIL_ACTIVE_LIST_BY_PERMIT_ID %>"
                        scope="session">
            <logic:iterate id="permitDetailListValue"
                           name="<%= SessionKeys.EV_PERMIT_DETAIL_ACTIVE_LIST_BY_PERMIT_ID %>"
                           scope="session">
                <tr>
                    <td>

                        <% if (blnCanEdit)
                        { %>
                        <html:link styleClass='btn btn-mini btn-success'
                                   action="/permitdetaileditpage.do"
                                   paramId="evPermitDetailId"
                                   paramName="permitDetailListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehedit.png"
                                      alt="Edit" />
                        </html:link>
                        <% } %>
                        <% if (blnCanDelete)
                        { %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/permitdetaildeleteaction.do"
                                   paramId="id"
                                   paramName="permitDetailListValue"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <% } %>
                    </td>
                    <td>
                        <html:link action="/permitdetailviewpage.do"
                                   paramId="evPermitDetailId"
                                   paramName="permitDetailListValue"
                                   paramProperty="id">
                            <bean:write name="permitDetailListValue"
                                        property="name" />
                        </html:link>
                    </td>
                    <td>
                        <bean:write name="permitDetailListValue"
                                    property="description" />
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Permitted Substances
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
                Chargeable?
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:empty name="<%= SessionKeys.EV_PERMIT_SUBSTANCE_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="3">
                    <bean:message key="permit.substance.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_PERMIT_SUBSTANCE_ACTIVE_LIST %>"
                        scope="session">
            <logic:iterate id="pmtSubListValue"
                           name="<%= SessionKeys.EV_PERMIT_SUBSTANCE_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanDeleteSubstance)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/permitsubstancedeleteaction.do"
                                   paramId="evPermitSubstanceId"
                                   paramName="pmtSubListValue"
                                   paramProperty="permitSubstanceId">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:define id="subType"
                                     name="pmtSubListValue"
                                     property="substanceType" />
                        <bean:write name="subType"
                                    property="description" />
                    </td>
                    <td>
                        <logic:equal value="true"
                                     name="pmtSubListValue"
                                     property="chargeable">
                            <html:img module="/"
                                      page="/img/icons/sehinspect.png" />
                        </logic:equal>
                    </td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
    </tbody>
</table>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Permitted Facilities
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
        <logic:empty name="<%= SessionKeys.EV_PERMIT_FACILITY_ACTIVE_LIST %>"
                     scope="session">
            <tr>
                <td colspan="3">
                    <bean:message key="permit.facility.empty.list" />
                </td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="<%= SessionKeys.EV_PERMIT_FACILITY_ACTIVE_LIST %>"
                        scope="session">
            <logic:iterate id="pmtFacility"
                           name="<%= SessionKeys.EV_PERMIT_FACILITY_ACTIVE_LIST %>"
                           scope="session">
                <tr>
                    <td>

                        <%
                            if (blnCanDeleteFacility)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/permitfacilitydeleteaction.do"
                                   paramId="evPermitFacilityId"
                                   paramName="pmtFacility"
                                   paramProperty="id">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:write name="pmtFacility"
                                    property="name" />
                    </td>
                    <td>
                        <bean:write name="pmtFacility"
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
