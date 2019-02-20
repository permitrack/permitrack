<%@ page import="com.sehinc.common.CommonConstants" %>
<%@ page import="com.sehinc.common.util.StringUtil" %>
<%@ page import="com.sehinc.stormwater.action.base.RequestKeys" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%pageContext.setAttribute("plans",
                           request.getAttribute("plans"));
    pageContext.setAttribute(CommonConstants.MODE,
                             request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM));
    pageContext.setAttribute("type",
                             request.getAttribute(CommonConstants.MODE));
    pageContext.setAttribute("action",
                             "plan"
                             + StringUtil.nullSafeToString(pageContext.getAttribute("type"))
                             + "viewaction");%>
<fieldset><legend>
    <logic:equal name="type"
                 value="template">
        Plan Templates
    </logic:equal>
    <logic:notEqual name="type"
                    value="template">
        Plans
    </logic:notEqual>
</legend></fieldset>
<ul class="nav nav-pills nav-stacked">
    <logic:iterate id="plan"
                   name="plans">
        <li>
            <html:link action='<%= (String) pageContext.getAttribute("action") %>'
                       paramId="id"
                       paramName="plan"
                       paramProperty="id">
                <bean:write name="plan"
                            property="name" />
            </html:link>
        </li>
    </logic:iterate>
</ul>
