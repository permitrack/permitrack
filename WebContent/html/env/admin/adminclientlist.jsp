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
<%@ page import="com.sehinc.environment.action.base.SessionKeys" %>
<%
    pageContext.setAttribute("clients",
                             request.getSession()
                                     .getAttribute(SessionKeys.ADMIN_CLIENT_SELECT_LIST));
%>
<logic:notEmpty name="clients">
    <tiles:insert page="../../clientlist.jsp">
        <tiles:put name="clients"
                   beanName="clients" />
    </tiles:insert>
</logic:notEmpty>

