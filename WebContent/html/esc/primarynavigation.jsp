<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ page import="com.sehinc.erosioncontrol.action.navigation.PrimaryMenuBean" %>
<%--<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar"
               data-toggle="collapse"
               data-target="#menuPrimary">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div id="menuPrimary"
                 class="nav-collapse collapse">--%>
                <%= new PrimaryMenuBean().render(request,
                                                 response) %>
<%-- TODO link to new help file
                <html:link styleClass="float-right" action="/manualescuser">
                    Help
                </html:link>
--%>
<%--
            </div>
        </div>
    </div>
</div>
--%>
