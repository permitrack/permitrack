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
<%@ page import="com.sehinc.security.action.base.SessionKeys" %>
<%@ page import="com.sehinc.security.action.contact.ContactListItem" %>
<%pageContext.setAttribute("lstActive",
                           request.getSession()
                                   .getAttribute(SessionKeys.CONTACT_LIST_ACTIVE));%>
<fieldset><legend>
    Contacts
</legend></fieldset>
<table class="table table-hover action-first action-large">
<%--
    <caption class="label">
        Active contacts
    </caption>
--%>
<%--
    <colgroup>
        <col style="width: 110px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <!-- Contact column headers -->
            <logic:iterate id="contactListItem"
                           name="<%= SessionKeys.CONTACT_LIST_ITEMS %>">
                <logic:equal name="contactListItem"
                             property="key"
                             value="CONTACT_NAME">
                    <th>
                        <bean:write name="contactListItem"
                                    property="name" />
                    </th>
                </logic:equal>
                <logic:notEqual name="contactListItem"
                                property="key"
                                value="CONTACT_NAME">
                    <th>
                        <bean:write name="contactListItem"
                                    property="name" />
                    </th>
                </logic:notEqual>
            </logic:iterate>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="contact"
                       name="lstActive">
            <tr>
                <td>
                    <html:link styleClass='btn btn-mini btn-success'
                               action="/contacteditpageaction.do"
                               paramId="contact_id"
                               paramName="contact"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehedit.png"
                                  alt="Edit" />
                    </html:link>
                    <html:link styleClass='btn btn-mini btn-danger warn-delete'
                               action="/contactdeleteaction.do"
                               paramId="id"
                               paramName="contact"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehdelete.png"
                                  alt="Delete" />
                    </html:link>
                </td>
                <logic:iterate id="contactListItem"
                               name="<%= SessionKeys.CONTACT_LIST_ITEMS %>"
                               scope="session">
                    <logic:equal name="contactListItem"
                                 property="key"
                                 value="<%= ContactListItem.CONTACT_NAME.getKey() %>">
                        <td>
                            <html:link action="/contactviewpageaction.do"
                                       paramId="contact_id"
                                       paramName="contact"
                                       paramProperty="id"
                                       title="View Contact">
                                <bean:write name="contact"
                                            property="lastName" />,
                                <bean:write name="contact"
                                            property="firstName" />
                            </html:link>
                        </td>
                    </logic:equal>
                    <logic:equal name="contactListItem"
                                 property="key"
                                 value="<%= ContactListItem.ORGANIZATION_NAME.getKey() %>">
                        <td>
                            <logic:notEmpty name="contact"
                                            property="organization">
                                <bean:define id="org"
                                             name="contact"
                                             property="organization" />
                                <logic:notEmpty name="org"
                                                property="name">
                                    <bean:write name="org"
                                                property="name" />
                                </logic:notEmpty>
                            </logic:notEmpty>
                        </td>
                    </logic:equal>
                </logic:iterate>
            </tr>
        </logic:iterate>
        <logic:empty name="lstActive">
            <tr>
                <td colspan="3">
                    No active contacts found
                </td>
            </tr>
        </logic:empty>
    </tbody>
</table>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
