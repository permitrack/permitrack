<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String aimsServerURL = (String)session.getAttribute("aimsServerURL");
if (aimsServerURL==null) aimsServerURL = "site_config_vserver.jsp";
String configURL = (String)session.getAttribute("configURL");
if (configURL==null) configURL ="site_config_vserver.jsp";

String[] selectList = request.getParameterValues("selectVS");

String listServices = request.getParameter("listServices");
if (listServices==null) listServices = "No";

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String nextUrl = "site_config_vserver.jsp";

if (alwaysSave.equals("Yes")) {
	nextUrl = "saveSite.jsp?page=site_config_vserver.jsp";
} else if (promptSave.equals("Yes")) {
	nextUrl = "saveDialog.jsp?page=site_config_vserver.jsp";
}

%>

<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />

<%

for (int i = selectList.length-1;i>=0;i--) {
	int index = Integer.parseInt(selectList[i]);
	VirtualServer theVS = vServers.getVirtualServer(index);
	theVS.removeVirtualServer(admin);
}
%>

<html>
<head>
	<title>Untitled</title>
	<script language="JavaScript" type="text/javascript">
		function doIt() {
			document.location = "<%=nextUrl%>";
		}
	</script>
</head>

<body bgcolor="#336699" onload="doIt()">

</body>
</html>
<% } %>
