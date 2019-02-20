<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html>
    <head>
        <title>
            Status | PermiTrack
        </title>
        <meta name="description"
              content="PermiTrack Status Page">
        <meta name="status"
              content="<%= request.getAttribute("headerMessage") %>">
    </head>
    <body>
        <%= request.getAttribute("bodyMessage") %>
    </body>
</html>
