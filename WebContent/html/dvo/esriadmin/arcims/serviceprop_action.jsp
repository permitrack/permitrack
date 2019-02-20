<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Service, java.util.Enumeration, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String service = request.getParameter("service");
String serviceMode = request.getParameter("serviceMode");
String checkList = request.getParameter("checkList");
//String checkList = request.getParameter("checkList");

String index = request.getParameter("index");
if (index==null) index = "0";
int s = Integer.parseInt(index);

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
	Service theService = serviceList.getService(s);
%>
<% if (serviceMode.equals("update")) {
	// update
} else {
%>
	<aims:adminService service="<%=theService%>" connectionId="<%=admin%>" action="<%=serviceMode%>" error="adminError"  />
<%
}
%>

<html>
<head>
	<title>Service Action</title>
	<script language="JavaScript" type="text/javascript">
		function doIt() {
			document.location = "<%=nextUrl%>";
		}
	</script>

</head>

<body bgcolor="#336699" onload="doIt()">
<form action="">
<input type="hidden" name="service" value="<%=service%>">
<input type="hidden" name="index" value="<%=index%>">
<input type="hidden" name="serviceMode" value="<%=serviceMode%>">
<input type="hidden" name="checkList" value="">
</form>

</body>
</html>
<% } %>
