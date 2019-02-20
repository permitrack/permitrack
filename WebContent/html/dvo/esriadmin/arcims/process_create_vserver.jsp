<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, java.util.StringTokenizer, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
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
if (configURL==null) configURL ="site_config_vserver";

String version = "";
String frequency = null;
String hour = null;
int intHour = 0;
String minute = null;
String vname = request.getParameter("name");
String vtype = request.getParameter("type");
String isRecyclable = request.getParameter("isRecyclable");
String isSingleThreaded = request.getParameter("isSingleThreaded");

if (vtype.equals("ImageServer [ArcMap]")) {
	vtype = "ImageServer";
	version = "ArcMap";
}
if (isRecyclable.equalsIgnoreCase("true")){
	frequency = request.getParameter("frequency");
	hour = request.getParameter("hour");
	intHour = Integer.parseInt(hour);
	minute = request.getParameter("minute");
	String timeOfDay = request.getParameter("ampm");
	if (timeOfDay.equalsIgnoreCase("PM")) {
		if (intHour != 12) {
			intHour = intHour + 12;
		}
	} else {
		if (intHour == 12) {
			intHour = 0;
		}
	}
}

String description = request.getParameter("description");
if (description==null) description = "";
String[] selectList = request.getParameterValues("selectedServer");

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

String nextUrl = "site_config_vserver.jsp?listServices=" + listServices;

if (alwaysSave.equals("Yes")) {
	nextUrl = "saveSite.jsp?page=site_config_vserver.jsp&listServices=" + listServices;
} else if (promptSave.equals("Yes")) {
	nextUrl = "saveDialog.jsp?page=site_config_vserver.jsp&listServices=" + listServices;
}

%>


<html>
<head>
	<title><%=textList[0]%> - <%=textList[102]%></title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
	<%=textList[102]%>

</b></font></div>
<%
VirtualServer theVS = new VirtualServer();
theVS.setName(vname);
theVS.setDescription(description);
theVS.setType(vtype);
theVS.setVersion(version);
//if (version.equalsIgnoreCase("ArcMap")) {
if (isRecyclable.equalsIgnoreCase("true")) {
	theVS.setRecyclingFrequency(frequency);
	theVS.setRecyclingHour(String.valueOf(intHour));
	theVS.setRecyclingMinute(minute);
}

for (int i=0;i<selectList.length;i++ ) {
	StringTokenizer stok = new StringTokenizer(selectList[i],"^");
	String containerString = stok.nextToken();
	String threadString = stok.nextToken();
	int threads = Integer.parseInt(threadString);
	theVS.addContainer(containerString, threads);
}
theVS.addVirtualServer(admin);
%>
	<script language="JavaScript" type="text/javascript">
		document.location = "<%=nextUrl%>";
	</script>
</body>
</html>

<% } %>
