<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Service, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>

<%
String serviceMode = request.getParameter("serviceMode");
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String[] selectList = request.getParameterValues("selectService");

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";

String nextUrl = "services.jsp";

if (alwaysSave.equals("Yes")) {
	nextUrl = "saveSite.jsp?page=services.jsp";
} else if (promptSave.equals("Yes")) {
	nextUrl = "saveDialog.jsp?page=services.jsp";
}
%>

<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />

<%

for (int i = selectList.length-1;i>=0;i--) {
	int index = Integer.parseInt(selectList[i]);
	Service theService = serviceList.getService(index);
%>
<aims:adminService service="<%=theService%>" connectionId="<%=admin%>" action="<%=serviceMode%>" error="adminError"  />
<% } %>
<html>
<head>
	<title>Untitled</title>
	<script language="JavaScript" type="text/javascript">
		function doIt() {
			//alert("<%=nextUrl%>");
			document.location="<%=nextUrl%>";
		}
	</script>
</head>

<body bgcolor="#336699" onload="doIt()">

</body>
</html>
<% } %>
