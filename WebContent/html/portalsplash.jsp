<%@ page import="com.sehinc.portal.action.navigation.PortalMenuBean" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<%
    /*
        pageContext.setAttribute("infoVersionId",
                                 request.getSession()
                                         .getAttribute(SessionKeys.INFO_VERSION_ID));
        pageContext.setAttribute("infoEnhancements",
                                 request.getSession()
                                         .getAttribute(SessionKeys.INFO_ENHANCEMENTS_LIST_ACTIVE));
    */
    /*
        pageContext.setAttribute("infoEvents",
                                 request.getSession()
                                         .getAttribute(SessionKeys.INFO_EVENTS_LIST_ACTIVE));
        pageContext.setAttribute("infoTip",
                                 request.getSession()
                                         .getAttribute(SessionKeys.INFO_TIP));
    */
%>
<div class="container">
    <%--
        <div id="bannertext"
             class="noWrap">
            Version
            <bean:write name="infoVersionId"
                        property="versionId" />
        </div>
    --%>
    <%= new PortalMenuBean().render(request,
                                    "image",
                                    response) %>
    <%--
            <div>
                <div id="enhancements_heading">
                    Latest Enhancements
                </div>
            </div>
            <div id="enhancements">
                <ul>
                    <logic:iterate id="e"
                                   name="infoEnhancements">
                        <li>
                            <bean:write property="message"
                                        name="e" />
                        </li>
                    </logic:iterate>
                </ul>
            </div>
    --%>
</div>

