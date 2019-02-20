<%@ page import="com.sehinc.environment.action.navigation.TertiaryMenuBean" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%= new TertiaryMenuBean().render(request,
                                  response) %>