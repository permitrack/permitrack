<%@ page import="com.sehinc.environment.action.navigation.SecondaryMenuBean" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%= new SecondaryMenuBean().render(request,
                                   response) %>