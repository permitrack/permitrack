<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL" %>
<%@ include file="textList.jsp"%>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>

<html>
<head><title>Login Check</title></head>

<!-- getting values from esriadmin.properties file located in the classes directory -->
<aims:getSettings id="myHost" value="appserver_host" propFileName="esriadmin.properties" />
<aims:getSettings id="myPort" value="appserver_port" propFileName="esriadmin.properties" />

<%
if (myHost == null) myHost = "localhost";
if (myPort == null) myPort = "5300";
%>

<%
String site = request.getParameter("host");
if (site==null) site = myHost;
String portStr = request.getParameter("port");
if (portStr==null) portStr = myPort;
int	port = Integer.parseInt(portStr);

ConnectionProxy admin = new ConnectionProxy();
    admin.setConnectionType(ConnectionProxy.TCP_ADMIN);
	admin.setHost(site);
	admin.setPort(port);

	int reaction = admin.ping(false);
	if (reaction==ConnectionProxy.PING_FIRST_LOGIN) {
		if (site.equalsIgnoreCase(myHost) && portStr.equalsIgnoreCase(myPort)) {
%>
			<body onload="document.location='firstlogin.jsp?host=<%=myHost%>&port=<%=myPort%>'">
<%
		} else {
%>
			<body onload="document.location='firstlogin.jsp?host=<%=site%>&port=<%=portStr%>'">
<%
		}
	} else if (reaction==ConnectionProxy.PING_FAIL) {
%>
<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
<%=textList[161]%><%=site%>.
</b></font></div>
<%
	} else {
%>
<body onload="document.location='login.jsp?host=<%=site%>&port=<%=port%>'">
<%
	}
%>

</body>
</html>
