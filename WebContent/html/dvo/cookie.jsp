<%@ page import="java.util.Enumeration" %>
<html>
    <body>
        <br>
        Cookies
        <%
            Cookie[]
                    cookies =
                    request.getCookies();
            for (
                    int
                            i =
                            0; i
                               < cookies.length; i++)
            {
                Cookie
                        cookie =
                        cookies[i];
        %>
        <br>
        <%= cookie.getName()%> :: <%= cookie.getValue()%>
        <%
            }
        %>
        <br>
        Attributes
        <%
            Enumeration
                    e =
                    session.getAttributeNames();
            while (e.hasMoreElements())
            {
                Object
                        name =
                        e.nextElement();
        %>
        <br>
        <%= name.toString()%> :: <%= session.getAttribute((String) name)%>
        <%
            }
        %>
        <br>
        Request Type:
        <%= request.getClass()
                .toString() %>
    </body>
</html>
