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
if (aimsServerURL==null) aimsServerURL = "site_config_server.jsp";
String configURL = (String)session.getAttribute("configURL");
if (configURL==null) configURL ="site_config_server.jsp";

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String nextUrl = "site_config_server.jsp";

if (alwaysSave.equals("Yes")) {
	nextUrl = "saveSite.jsp?page=site_config_server.jsp";
} else if (promptSave.equals("Yes")) {
	nextUrl = "saveDialog.jsp?page=site_config_server.jsp";
}

%>

<html>
<head>
	<title><%=textList[113]%></title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
	<%=textList[113]%>

</b></font></div>
<%


String sName = "";
String[] selectList = request.getParameterValues("selectServer");

String listServices = request.getParameter("listServices");
if (listServices==null) listServices = "No";

for (int i = selectList.length-1;i>=0;i--) {
	int index = selectList[i].indexOf("_");
	sName = selectList[i].substring(0,index);
%>

<aims:adminServer name="<%=sName%>" connectionId="<%=admin%>" action="removeContainer" removeId="<%=selectList[i]%>" error="aError" />

<%
}
%>
	<script language="JavaScript" type="text/javascript">
		document.location = "<%=nextUrl%>";
	</script>

</body>
</html>
<% } %>
