<%@ page import="com.sehinc.common.action.base.SessionKeys" %>
<%@ page import="com.sehinc.common.action.base.SessionService" %>
<%@ page import="com.sehinc.security.action.base.RequestKeys" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib prefix="logic"
           uri="http://struts.apache.org/tags-logic" %>
<logic:present name="<%= SessionKeys.USER %>"
               scope="session">
    <bean:define id="userValue"
                 name="<%= SessionKeys.USER %>"
                 scope="session" />
</logic:present>
<bean:define id="LoggedInLabel">
    <%try
    {
        if (SessionService.getUserValue(request)
            != null
            && SessionService.getUserValue(request)
                       .getUsername()
               != null)
        {%>
    <%=
    SessionService.getUserValue(request)
            .getFirstName()
    + " "
    + SessionService.getUserValue(request)
            .getLastName()
    %>
    <%}
    else
    {%>
    Log in
    <%}
    }
    catch (Exception e)
    {%>
    Log in
    <%}%>
</bean:define>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <%--
                        <a class="btn btn-navbar"
                           data-toggle="collapse"
                           data-target="#menuSecurity">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
            --%>
            <logic:equal name="LoggedInLabel"
                         value="Log in">
                <html:link module="/"
                           styleClass="brand pull-left"
                           href="/sehsvc">
                    <html:img alt="PermiTrack"
                              title="PermiTrack"
                              module="/"
                              page="/img/icons/Logo-PermiTrack-White-T.png"
                              style="max-height: 30px;" />
                </html:link>
            </logic:equal>
            <logic:notEqual name="LoggedInLabel"
                            value="Log in">
                <html:link module="/"
                           styleClass="brand pull-left hidden-phone"
                           href="/sehsvc/pt">
                    <html:img alt="PermiTrack"
                              title="PermiTrack"
                              module="/"
                              page="/img/icons/Logo-PermiTrack-White-T.png"
                              style="max-height: 30px;" />
                </html:link>
            </logic:notEqual>
            <a class="btn btn-navbar"
               data-toggle="collapse"
               data-target="#menuApps"
               style="float: left;">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div id="menuSecurity"
                 class="pull-right"> <%--nav-collapse collapse --%>
                <ul class="nav nav-pills">
                    <logic:equal name="LoggedInLabel"
                                 value="Log in">
                        <li>
                            <html:link module="/"
                                       action="/loginPageAction">
                                Sign in
                            </html:link>
                        </li>
                    </logic:equal>
                    <logic:notEqual name="LoggedInLabel"
                                    value="Log in">
                        <li class="dropdown">
                            <a href="#"
                               class="dropdown-toggle noWrap"
                               data-toggle="dropdown"
                               title="Role: <%=SessionService.getUserValue(request).getAccountType()%>">
                                <bean:write name="LoggedInLabel" />
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <html:link module="/html/sec/client"
                                               action="/clientviewpageaction">
                                        Settings
                                    </html:link>
                                </li>
                                <li>
                                    <html:link module='/'
                                               action='/help'>
                                        Help &amp; Support
                                    </html:link>
                                </li>
                                <li>
                                    <html:link module="/html/sec/user"
                                               action="/userchangepasswordpageaction"
                                               paramId="<%=RequestKeys.USER_ID%>"
                                               paramName="userValue"
                                               paramProperty="id">
                                        Change Password
                                    </html:link>
                                </li>
                                <li>
                                        <%--TODO--%>
                                    <html:link module="/"
                                               action="/help">
                                        Open Ticket
                                    </html:link>
                                </li>
                                <li>
                                    <html:link module="/"
                                               action="/logoutAction">
                                        Sign out
                                    </html:link>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div style="padding: 0 20px;">
                                        <div>
                                            <span class="label">
                                                Username
                                            </span>
                                        </div>
                                        <div>
                                            <bean:write name="userValue"
                                                        property="username"
                                                        ignore="true" />
                                        </div>
                                        <div style="margin-top: 5px;">
                                            <span class="label">
                                                Email
                                            </span>
                                        </div>
                                        <div>
                                            <bean:write name="userValue"
                                                        property="emailAddress"
                                                        ignore="true" />
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </logic:notEqual>
                </ul>
            </div>
            <div id="menuApps"
                 class="nav-collapse collapse">
                <tiles:insert attribute="portalMenu"
                              flush="false"
                              ignore="true" />
            </div>
        </div>
    </div>
</div>
