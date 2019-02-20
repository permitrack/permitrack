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
<html:form styleClass="form-search"
           action="/useraddpageaction"
           method="get">
    <html:text styleClass="input-medium search-query"
               styleId="firstName"
               property="firstName" />
    <button type="submit"
            class="btn">
        Search
    </button>
</html:form>
<html:form styleClass="form-search"
           action="/useraddaction">
    <html:hidden name="userForm"
                 property="id"
                 styleId="id" />
    <table id="userlist"
           class="table table-hover tablesorter action-first action-small">
<%--
        <caption class="label">
            Active Users
        </caption>
--%>
        <thead>
            <tr>
                <th></th>
                <th>
                    Username
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
                        <button class="btn btn-success btn-mini"
                                type="submit"
                                value="Add"
                                onclick="return setId('<bean:write name="a" property="id" />');">
                            Add
                        </button>
                    </td>
                    <td>
                        <bean:write name="a"
                                    property="username" />
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
                        No users found
                    </td>
                </tr>
            </logic:empty>
        </tbody>
    </table>
</html:form>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            function setId(id)
            {
                $("#id").val(id);
            }
            $("#userlist").tablesorter({
                                           headers:{ 0:{ sorter:false}, 7:{ sorter:'isoDate'} }
                                       });
        </script>
    </tiles:put>
</tiles:definition>
