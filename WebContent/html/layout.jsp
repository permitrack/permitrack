<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"
           prefix="logic" %>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <tiles:insert page="header.jsp">
        <tiles:put name="title">
            <tiles:getAsString name="title" />
        </tiles:put>
    </tiles:insert>
    <body>
        <tiles:insert attribute="portal-bar" />
        <div id="wrap">
            <tiles:insert attribute="layout" />
            <div id="push"></div>
        </div>
        <tiles:insert attribute="footer"
                      ignore="true" />
        <div id="dialog"
             title="Alert">
        </div>
        <tiles:insert page="scripts.jsp" />
        <tiles:insert beanName="scripts" beanScope="request" template="scripts1.jsp" ignore="true" />
    </body>
    <tiles:insert page="googleanalytics.jsp" />
</html>
