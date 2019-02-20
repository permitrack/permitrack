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
<fieldset><legend>
    Saved Search Filters
    <html:link styleClass="btn btn-mini btn-success"
               action="usersearchcreatepageaction">
        New
    </html:link>
</legend></fieldset>
<table class="table table-hover action-first action-large">
<%--
    <colgroup>
        <col style="width: 110px; white-space: nowrap;" />
    </colgroup>
--%>
    <thead>
        <tr>
            <th></th>
            <th>Name</th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="ecUserSearch"
                       name="<%= SessionKeys.USER_SEARCH_LIST %>">
            <tr>
                <td>
                    <html:link styleClass="btn btn-mini btn-success"
                               action="/usersearcheditpageaction.do"
                               paramId="searchId"
                               paramName="ecUserSearch"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehedit.png"
                                  alt="Edit" />
                    </html:link>
                    <html:link styleClass="btn btn-mini btn-danger warn-delete"
                               action="/usersearchdeleteaction.do"
                               paramId="searchId"
                               paramName="ecUserSearch"
                               paramProperty="id">
                        <html:img module="/"
                                  page="/img/icons/sehdelete.png"
                                  alt="Delete" />
                    </html:link>
                </td>
                <td>
                    <html:link action="/usersearcheditpageaction.do"
                               paramId="searchId"
                               paramName="ecUserSearch"
                               paramProperty="id">
                        <bean:write name="ecUserSearch"
                                    property="ecSearch.name" />
                    </html:link>
                </td>
            </tr>
        </logic:iterate>
        <logic:empty name="<%= SessionKeys.USER_SEARCH_LIST %>">
            <tr>
                <td colspan="2">
                    There are no Searches saved for this User.
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
