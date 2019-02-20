<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Site" %>

<%@ include file="../textList.jsp" %>
<%

ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");

if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String aimsServerURL = (String)session.getAttribute("aimsServerURL");
if (aimsServerURL==null) aimsServerURL = "site_config.jsp";
String configURL = (String)session.getAttribute("configURL");
if (configURL==null) configURL ="site_config.jsp";

String backURL = "site_config.jsp";

%>
<html>
<head>
	<title>Saving Configuration</title>
	<script language="JavaScript" type="text/javascript">
		function doIt() {
			document.location="<%=backURL%>";
		}
	</script>
</head>

<body bgcolor="#336699" text="White" onload="doIt()">
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
	<%=textList[85]%>s

</b></font></div>

<%
Site site = new Site();

String defaultOutputDir = request.getParameter("OutputDir");
String defaultOutputURL = request.getParameter("OutputURL");
String defaultImageMemory = request.getParameter("ImageMemory");
String defaultCleanup = request.getParameter("Cleanup");
String defaultImageType = request.getParameter("ImageType");
String promptSave = request.getParameter("PromptSave");
String alwaysSave = request.getParameter("AlwaysSave");

site.setSiteProperty("OutputDir",defaultOutputDir,admin);
site.setSiteProperty("OutputURL",defaultOutputURL,admin);
site.setSiteProperty("OutputCleanup",defaultCleanup,admin);
site.setSiteProperty("ImageMemory",defaultImageMemory,admin);
site.setSiteProperty("ImageType",defaultImageType,admin);
site.setSiteProperty("PromptSave",promptSave,admin);
site.setSiteProperty("AlwaysSave",alwaysSave,admin);

%>

</body>
</html>

<% } %>