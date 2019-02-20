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
                    .getAttribute(SessionKeys.FACILITY_CAN_UPDATE);
    Boolean
            blnCanDelete =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.FACILITY_CAN_DELETE);%>
<html:form styleClass="form-horizontal"
           action="/facilitylistpage">
    <logic:empty name="<%= SessionKeys.EV_FACILITY_ACTIVE_LIST_BY_CLIENT %>"
                 scope="session">
        <p class="text-info">
            <bean:message key="facility.empty.list" />
        </p>
    </logic:empty>
    <logic:notEmpty name="<%= SessionKeys.EV_FACILITY_ACTIVE_LIST_BY_CLIENT  %>"
                    scope="session">
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
                        Number
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Description
                    </th>
                </tr>
            </thead>
            <tbody>
                <logic:iterate id="facilityListValue"
                               name="<%= SessionKeys.EV_FACILITY_ACTIVE_LIST_BY_CLIENT  %>"
                               scope="session">
                    <tr>
                        <td>

                            <%if (blnCanEdit)
                            {%>
                            <html:link styleClass='btn btn-mini btn-success'
                                       action="/facilityeditpage.do"
                                       paramId="evFacilityId"
                                       paramName="facilityListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehedit.png"
                                          alt="Edit" />
                            </html:link>
                            <%}%>
                            <%if (blnCanDelete)
                            {%>
                            <html:link styleClass='btn btn-mini btn-danger warn-delete'
                                       action="/facilitydeleteaction.do"
                                       paramId="id"
                                       paramName="facilityListValue"
                                       paramProperty="id">
                                <html:img module="/"
                                          page="/img/icons/sehdelete.png"
                                          alt="Delete" />
                            </html:link>
                            <%}%>
                        </td>
                        <td>
                            <bean:write name="facilityListValue"
                                        property="number" />
                        </td>
                        <td>
                            <html:link action="/facilityviewpage.do"
                                       paramId="evFacilityId"
                                       paramName="facilityListValue"
                                       paramProperty="id">
                                <bean:write name="facilityListValue"
                                            property="name" />
                            </html:link>
                        </td>
                        <td>
                            <bean:write name="facilityListValue"
                                        property="description" />
                        </td>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </logic:notEmpty>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
