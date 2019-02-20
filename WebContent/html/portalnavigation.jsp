<%@ page import="com.sehinc.portal.action.navigation.PortalMenuBean" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%= new PortalMenuBean().render(request,
                                response) %>