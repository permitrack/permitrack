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
    pageContext.setAttribute("allSOPList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_SECURE_OBJECT_PERMISSION_LIST));
    pageContext.setAttribute("headersList",
                             request.getSession()
                                     .getAttribute(SessionKeys.EC_SECURE_OBJECT_HEADERS_LIST));
%>
<h4 class="myAccordian">
    Role Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Name
    </dt>
    <dd><bean:write name="RoleECForm"
                    property="name" />
        <html:hidden name="RoleECForm"
                     property="name"
                     styleId="name" />
        <html:hidden name="RoleECForm"
                     property="id"
                     styleId="id" />
        <html:hidden name="RoleECForm"
                     property="clientId"
                     styleId="clientId" />
        <html:hidden name="RoleECForm"
                     property="code"
                     styleId="code" />
    </dd>
    <dt>Description
    </dt>
    <dd><bean:write name="RoleECForm"
                    property="description" />
        <html:hidden name="RoleECForm"
                     property="description"
                     styleId="description" />
    </dd>
    <dt>Status Code
    </dt>
    <dd><bean:write name="RoleECForm"
                    property="statusCodeText" />
        <html:hidden name="RoleECForm"
                     property="statusCode"
                     styleId="statusCode" />
    </dd>
</dl>
<h4 class="myAccordian">
    Role Permissions
</h4>
<dl class="dl-horizontal">
    <dt>
        Role has these permissions
    </dt>
    <dd>
        <table class="table table-condensed table-hover table-condensed">
            <thead>
                <tr>
                    <th>
                        Entity/Permission
                    </th>
                    <logic:iterate id="header"
                                   name="headersList">
                        <th>
                            <bean:write name="header" />
                        </th>
                    </logic:iterate>
                </tr>
            </thead>
            <tbody>
                <logic:iterate id="currentSO"
                               name="allSOPList"
                               indexId="myIndex">
                    <tr>
                        <logic:iterate id="roleSOPVal"
                                       name="currentSO"
                                       indexId="myIndex">
                            <logic:equal name="myIndex"
                                         value="0">
                                <td>
                                    <bean:write name="roleSOPVal"
                                                property="soName" />
                                </td>
                            </logic:equal>
                            <logic:equal name="roleSOPVal"
                                         property="selected"
                                         value="true">
                                <td>
                                    X
                                </td>
                            </logic:equal>
                            <logic:notEqual name="roleSOPVal"
                                            property="selected"
                                            value="true">
                                <td></td>
                            </logic:notEqual>
                        </logic:iterate>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </dd>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
