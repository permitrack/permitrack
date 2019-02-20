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
<link href='<html:rewrite module="/" page="/css/bootstrap-signin.css"/>'
      rel="styleSheet"
      type="text/css"
      media="screen" />
<div class="container">
    <div id="cookieError"
         class="alert"
         style="display: none;">
        It appears that cookies are disabled in your browser. You will not be able to properly use this site until you enable cookies.
    </div>
    <tiles:insert page="messages.jsp"
                  flush="false" />
    <%--<div class="row">--%>
        <%--<div class="span12">--%>
            <%--<div class="alert alert-block alert-success">--%>
                <%--
                <h3>
                    Please Note
                </h3>
                --%>
                <%--MyPermiTrack.com is currently experience issues with our SSL Certificate. We are urgently working on these issues and we sincerely apologize for any inconvenience. It is completely SAFE to proceed past the SSL Certificate Warning by clicking "Continue" or equivalent if your browser allows it.--%>
                <%--We have added new features to Erosion Control (ESC)! <html:link action="/inspection" anchor="features" target="blank">Click here to view the list of new features.</html:link>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="row">
        <div class="span4"> <%-- TODO
            <html:form styleClass="form-signin"
                       action="/htmlLoginAction.do">
                <p>
                    <strong>New to PermiTrack?</strong> Sign up
                    <a class="muted pull-right"
                       href="http://www.mypermitrack.com/Pages/contactus.aspx">
                        More info
                    </a>
                </p>
                <input type="text"
                       class="input-block-level"
                       placeholder="Full name"
                       name="fullname"
                       maxlength="30">
                <input type="text"
                       class="input-block-level"
                       placeholder="Email"
                       name="email">
                <input type="password"
                       class="input-block-level"
                       placeholder="Password"
                       name="password">
                <button class="btn btn-large btn-primary"
                        type="submit">
                    Sign up for PermiTrack
                </button>
            </html:form>
--%> </div>
        <div class="span4">
            <html:form styleClass="form-signin"
                       action="/htmlLoginAction.do">
                <h3 class="form-signin-heading">
                    Sign in
                </h3>
                <%--
                TODO : Give option to user? Default to "Yes, redirect"?
                <bean:write name="loginForm" property="redirect" filter="false" />
                --%>
                <input type="hidden"
                       name="redirect"
                       value='<bean:write name="loginForm" property="redirect" filter="false" />' />
                <input type="text"
                       class="input-block-level"
                       placeholder="Email or username"
                       name="userid">
                <input type="password"
                       class="input-block-level"
                       placeholder="Password"
                       name="password">
                <label class="checkbox">
                    <input type="checkbox"
                           name="rememberme"
                           checked="checked">
                    Remember me
                </label>
                <button class="btn btn-large btn-success"
                        type="submit"
                        style="margin-bottom: 5px; margin-right: 10px;">
                    Sign in
                </button>
                <span>
                    <a href='<html:rewrite module="/" page="/password" />'
                       class="muted noWrap">
                        I forgot my password
                    </a>
                </span>
            </html:form>
            <!--Open IDs login-->
            <form class="form-signin"
                  action="/sehsvc/id?identifier=https://www.google.com/accounts/o8/id"
                  method="post">
                <div class="noWrap"
                     style="margin-bottom: 18px;">
                    <button class="btn btn-large btn-success"
                            type="submit"
                            style="margin-bottom: 5px; margin-right: 10px;">
                        Sign in with OpenID
                    </button>
                    <html:img page="/img/icons/login/gprofile_button-64.png" />
                </div>
                <div class="alert alert-success">
                    In order to use this feature, your PermiTrack email address must match your Google email address.
                </div>
            </form>
        </div>
        <div class="span4"></div>
    </div>
</div>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script type="text/javascript">
            //<!--
            if (!Modernizr.cookies)
            {
                $("#cookieError").css("display",
                                      "block");
            }// -->
        </script>
    </tiles:put>
</tiles:definition>
