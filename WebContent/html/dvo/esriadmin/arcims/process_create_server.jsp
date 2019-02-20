<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.Server, java.util.StringTokenizer, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String monitor = request.getParameter("monitor");

String listVS = request.getParameter("listVS");
if (listVS==null) listVS = "No";

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String vsIndex = request.getParameter("vsIndex"); // origianl page is virtual server property

String origPage = request.getParameter("origPage");
if (origPage.equalsIgnoreCase("newVS")) {
	origPage = "create_vserver.jsp";
} else if (origPage.equalsIgnoreCase("newSS")) {
	origPage = "site_config_server.jsp";
} else if (origPage.equalsIgnoreCase("VSProp")) {
	origPage = "vserverprop.jsp&index=" + vsIndex;
}

String nextUrl = origPage;

if (alwaysSave.equals("Yes")) {
	if (origPage != null) {
		nextUrl = "saveSite.jsp?page=" + origPage;
	}
} else if (promptSave.equals("Yes")) {
	if (origPage != null) {
		nextUrl = "saveDialog.jsp?page=" + origPage;
	}
}
%>


<html>
<head>
	<title>Creating Server</title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
	Creating Server

</b></font></div>

<aims:adminServer name="<%=monitor%>" action="addContainer" addedId="myId" connectionId="<%=admin%>" error="aError" />
	<script language="JavaScript" type="text/javascript">
		document.location = "<%=nextUrl%>";
	</script>

</body>
</html>
<% } %>
