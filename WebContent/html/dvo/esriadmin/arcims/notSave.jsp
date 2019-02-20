<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>

<%
String serviceMode = request.getParameter("serviceMode");
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String index = request.getParameter("index");


String backURL = request.getParameter("page");

if (index!=null) backURL += "?index=" + index;

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = request.getParameter("promptSave");
String alwaysSave = request.getParameter("alwaysSave");
if (promptSave==null) promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
if (alwaysSave==null) alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";


	site.setSiteProperty("PromptSave",promptSave,admin);
	session.setAttribute("promptSave",promptSave);

	site.setSiteProperty("AlwaysSave",alwaysSave,admin);
	session.setAttribute("alwaysSave",alwaysSave);

%>
<html>
<head>
	<title>Saving Configuration</title>
	<script language="JavaScript" type="text/javascript">
		//alert("didn't saved it");
		//alert("promptSave=<%=promptSave %>\nalwaysSave=<%=alwaysSave %>");
		function doIt() {
			document.location="<%=backURL%>";
		}
	</script>
</head>

<body bgcolor="#336699" text="White" onload="doIt()">
</body>
</html>
<%} %>
