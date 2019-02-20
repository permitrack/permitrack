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
<%
    pageContext.setAttribute("lstActive",
                             request.getSession()
                                     .getAttribute(SessionKeys.USER_LIST_ACTIVE));
%>
<fieldset><legend>
    Users
</legend></fieldset>
<table id="userlist"
       class="table table-hover tablesorter action-first action-large">
<%--
    <caption class="label">
        Active Users
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
            <th>
                User Name
            </th>
            <th>
                Last Name
            </th>
            <th>
                First Name
            </th>
            <th>
                Account Type
            </th>
            <th>
                Phone
            </th>
            <th>
                Email
            </th>
            <th>
                Last Login
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="a"
                       name="lstActive">
            <tr>
                <td>
                    <html:link styleClass='btn btn-mini btn-success'
                               action="/usereditpageaction.do"
                               paramId="user_id"
                               paramName="a"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehedit.png"
                                  alt="Edit" />
                    </html:link>
                    <html:link styleClass='btn btn-mini btn-danger warn-delete'
                               action="/userdeleteaction.do"
                               paramId="user_id"
                               paramName="a"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehdelete.png"
                                  alt="Delete" />
                    </html:link>
                </td>
                <td>
                    <html:link action="/userviewpageaction.do"
                               paramId="user_id"
                               paramName="a"
                               paramProperty="id">
                        <bean:write name="a"
                                    property="username" />
                    </html:link>
                </td>
                <td>
                    <bean:write name="a"
                                property="lastName" />
                </td>
                <td>
                    <bean:write name="a"
                                property="firstName" />
                </td>
                <td>
                    <bean:write name="a"
                                property="accountType" />
                </td>
                <td>
                    <bean:write name="a"
                                property="primaryPhone" />
                </td>
                <td>
                    <a href="mailto:<bean:write name='a' property='emailAddress'/>">
                        <bean:write name="a"
                                    property="emailAddress" />
                    </a>
                </td>
                <td>
                    <bean:write name="a"
                                property="lastLoginDateSimple" />
                </td>
            </tr>
        </logic:iterate>
        <logic:empty name="lstActive">
            <tr>
                <td colspan="10">
                    No active users found
                </td>
            </tr>
        </logic:empty>
    </tbody>
</table>
<label for="emails">
    Emails of Active Users
</label>
<div id="emails"
     class="pre-scrollable">
    <logic:iterate id="a"
                   name="lstActive">
        <logic:notEmpty name="a"
                        property="emailAddress">
            <code>
                <a href="mailto:<bean:write name='a' property='emailAddress'/>">
                    <bean:write name="a"
                                property="emailAddress" />
                </a>;
            </code>
            &nbsp;
        </logic:notEmpty>
    </logic:iterate>
</div>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            $(function ()
              {
                  $("#userlist").tablesorter({
                                                 headers:{ 0:{ sorter:false}, 7:{ sorter:'isoDate'} }
                                             });
              });
        </script>
    </tiles:put>
</tiles:definition>
