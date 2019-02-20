<%@ page import="com.sehinc.environment.action.base.RequestKeys" %>
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
                    .getAttribute(SessionKeys.FACILITY_CONTACT_CAN_DELETE);%>
<fieldset><legend>
    <bean:write name="FacilityForm"
                property="name" />
</legend></fieldset>
<h4 class="myAccordian">
    Facility Description
</h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="facility.number" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="number" />
    </dd>
    <dt><bean:message key="facility.name" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="name" />
    </dd>
    <dt><bean:message key="facility.desc" />
    </dt>
    <dd>
        <bean:write name="FacilityForm"
                    property="description" />
    </dd>
    <dt><bean:message key="facility.active.date" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="activeTsText" />
    </dd>
    <dt><bean:message key="facility.inactive.date" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="inactiveTsText" />
    </dd>
    <dt><bean:message key="facility.phone" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="phone" />
    </dd>
    <dt><bean:message key="facility.fax" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="fax" />
    </dd>
</dl>
<h4 class="myAccordian">
    Operations
</h4>
<dl class="dl-horizontal">
    <dt><bean:message key="facility.county.name" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="countyName" />
    </dd>
    <dt><bean:message key="facility.sic.code" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="sicCode" />
    </dd>
    <dt><bean:message key="facility.class.desc" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="classDesc" />
    </dd>
    <dt><bean:message key="facility.daily.hrs.op" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="dailyHrsOp" />
    </dd>
    <dt><bean:message key="facility.days.op.week" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="daysOpWeek" />
    </dd>
    <dt><bean:message key="facility.weeks.op.year" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="weeksOpYear" />
    </dd>
    <dt><bean:message key="facility.business.hrs" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="businessHrs" />
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="facility.address.physical" /></h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="facility.address.line1" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="physAddr1" />
    </dd>
    <dt><bean:message key="facility.address.line2" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="physAddr2" />
    </dd>
    <dt><bean:message key="facility.address.line3" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="physAddr3" />
    </dd>
    <dt><bean:message key="facility.address.city" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="physAddrCity" />
    </dd>
    <dt><bean:message key="facility.address.state" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="physAddrState" />
    </dd>
    <dt><bean:message key="facility.address.zip" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="physAddrZip" />
    </dd>
</dl>
<h4 class="myAccordian">
    <bean:message key="facility.address.mailing" /></h4>
<dl class="dl-horizontal">
    <dt>
        <bean:message key="facility.address.line1" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="mailAddr1" />
    </dd>
    <dt><bean:message key="facility.address.line2" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="mailAddr2" />
    </dd>
    <dt><bean:message key="facility.address.city" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="mailAddrCity" />
    </dd>
    <dt><bean:message key="facility.address.state" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="mailAddrState" />
    </dd>
    <dt><bean:message key="facility.address.zip" />
    </dt>
    <dd><bean:write name="FacilityForm"
                    property="mailAddrZip" />
    </dd>
</dl>
<table class="table table-hover action-first action-small">
    <caption class="label">
        Facility Contacts
    </caption>
    <logic:empty name="<%= RequestKeys.EV_FACILITY_CONTACT_LIST %>"
                 scope="request">
        <tr>
            <td colspan="3">
                <bean:message key="facility.contact.empty.list" />
            </td>
        </tr>
    </logic:empty>
    <logic:notEmpty name="<%= RequestKeys.EV_FACILITY_CONTACT_LIST %>"
                    scope="request">
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
                    Role
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:iterate id="contactListValue"
                           name="<%= RequestKeys.EV_FACILITY_CONTACT_LIST %>"
                           scope="request">
                <tr>
                    <td>

                        <%
                            if (blnCanDelete)
                            {
                        %>
                        <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                   action="/facilitycontactdeleteaction.do"
                                   paramId="evFacilityContactId"
                                   paramName="contactListValue"
                                   paramProperty="facilityContactId">
                            <html:img module="/"
                                      page="/img/icons/sehdelete.png"
                                      alt="Delete" />
                        </html:link>
                        <%}%>
                    </td>
                    <td>
                        <bean:write name="contactListValue"
                                    property="firstName" />
                        <bean:write name="contactListValue"
                                    property="lastName" />
                    </td>
                    <td>
                        <bean:write name="contactListValue"
                                    property="roleDesc" />
                    </td>
                </tr>
            </logic:iterate>
        </tbody>
    </logic:notEmpty>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
