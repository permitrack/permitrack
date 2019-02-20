<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ page import="com.sehinc.environment.action.navigation.PrimaryMenuBean" %>
<div class="navbar">
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
                 class="nav-collapse collapse">
                <%= new PrimaryMenuBean().render(request,
                                                 response) %>
            </div>
        </div>
    </div>
</div>

