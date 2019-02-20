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
    Boolean
            SHOW_EC_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION);
    Boolean
            SHOW_SW_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION);
    Boolean
            SHOW_DV_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION);
    Boolean
            SHOW_EV_ACCESS_OPTION =
            (Boolean) request.getSession()
                    .getAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION);%>
<h4 class="myAccordian">
    User Description
</h4>
<dl class="dl-horizontal">
    <dt>
        First Name
    </dt>
    <dd><bean:write name='userForm'
                    property='firstName' />
    </dd>
    <dt>Last Name
    </dt>
    <dd><bean:write name='userForm'
                    property='lastName' />
    </dd>
    <dt>User Name
    </dt>
    <dd><bean:write name='userForm'
                    property='username' />
    </dd>
    <dt>Title
    </dt>
    <dd><bean:write name='userForm'
                    property='title' />
    </dd>
    <dt>Account Type
    </dt>
    <dd><bean:write name='userForm'
                    property='accountType' />
    </dd>
</dl>
<h4 class="myAccordian">
    Contact Information
</h4>
<dl class="dl-horizontal">
    <dt>
        Email Address
    </dt>
    <dd><bean:write name='userForm'
                    property='emailAddress' />
    </dd>
    <dt>Address Line 1
    </dt>
    <dd><bean:write name='userForm'
                    property='addressLine1' />
    </dd>
    <dt>Address Line 2
    </dt>
    <dd><bean:write name='userForm'
                    property='addressLine2' />
    </dd>
    <dt>City
    </dt>
    <dd><bean:write name='userForm'
                    property='city' />
    </dd>
    <dt>State
    </dt>
    <dd><logic:equal name='userForm'
                     property='state'
                     value='0'>
    </logic:equal>
        <logic:notEqual name='userForm'
                        property='state'
                        value='0'>
            <bean:write name='userForm'
                        property='state' />
        </logic:notEqual>
    </dd>
    <dt>Zip
    </dt>
    <dd><bean:write name='userForm'
                    property='postalCode' />
    </dd>
    <dt>Primary Phone
    </dt>
    <dd><bean:write name='userForm'
                    property='primaryPhone' />
    </dd>
    <dt>Secondary Phone
    </dt>
    <dd><bean:write name='userForm'
                    property='secondaryPhone' />
    </dd>
    <dt>Fax
    </dt>
    <dd><bean:write name='userForm'
                    property='faxPhone' />
    </dd>
</dl>
<h4 class="myAccordian">
    Module Access
</h4>
<dl class="dl-horizontal">
    <% if (SHOW_EC_ACCESS_OPTION)
    { %>
    <dt>
        Erosion Control (ESC)
    </dt>
    <dd>
        <bean:write name="userForm"
                    property="accessECText" />
        <logic:equal name="userForm"
                     property="accessEC"
                     value="true">
            :
            <logic:equal name='userForm'
                         property='accountType'
                         value='Client Administrator'>
                No role assigned. User is a Client Administrator.
            </logic:equal>
            <logic:notEqual name='userForm'
                            property='accountType'
                            value='Client Administrator'>
                <bean:write name="userForm"
                            property="roleNameEC" />
            </logic:notEqual>
        </logic:equal>
    </dd>
    <% } %>
    <% if (SHOW_SW_ACCESS_OPTION)
    { %>
    <dt>
        Storm/Sewer (MS4)
    </dt>
    <dd><bean:write name="userForm"
                    property="accessSWText" />
    </dd>
    <% } %>
    <% if (SHOW_DV_ACCESS_OPTION)
    { %>
    <dt>
        DataView Online
    </dt>
    <dd><bean:write name="userForm"
                    property="accessDVText" />
    </dd>
    <% } %>
    <% if (SHOW_EV_ACCESS_OPTION)
    { %>
    <dt>
        Environment (ENV)
    </dt>
    <dd><bean:write name="userForm"
                    property="accessEVText" />
        <logic:equal name="userForm"
                     property="accessEV"
                     value="true">
            :
            <logic:equal name='userForm'
                         property='accountType'
                         value='Client Administrator'>
                No role assigned. User is a Client Administrator.
            </logic:equal>
            <logic:notEqual name='userForm'
                            property='accountType'
                            value='Client Administrator'>
                <bean:write name="userForm"
                            property="roleNameEV" />
            </logic:notEqual>
        </logic:equal>
    </dd>
    <% } %>
</dl>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
    </tiles:put>
</tiles:definition>
