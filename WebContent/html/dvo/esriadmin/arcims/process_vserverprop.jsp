<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.util.StringTokenizer, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
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

String vversion = request.getParameter("version");
String vname = request.getParameter("name");
String description = request.getParameter("description");
if (description==null) description = "";
String[] selectList = request.getParameterValues("selectedServer");

String listServices = request.getParameter("listServices");
if (listServices==null) listServices = "No";

String index = request.getParameter("index");
int s = Integer.parseInt(index);

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String nextUrl = "site_config_vserver.jsp?listServices=" + listServices;

String fromPage = request.getParameter("fromPage");

if (alwaysSave.equals("Yes")) {
	if (fromPage != null) {
		nextUrl = "saveSite.jsp?page=site_config_server.jsp";
	} else {
		nextUrl = "saveSite.jsp?page=site_config_vserver.jsp&listServices=" + listServices;
	}
} else if (promptSave.equals("Yes")) {
	if (fromPage != null) {
		nextUrl = "saveDialog.jsp?page=site_config_server.jsp";
	} else {
		nextUrl = "saveDialog.jsp?page=site_config_vserver.jsp&listServices=" + listServices;
	}
}

String isRecyclable = request.getParameter("isRecyclable");
String isSingleThreaded = request.getParameter("isSingleThreaded");
%>


<html>
<head>
	<title><%=textList[0]%> - <%=textList[103]%></title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
	<%=textList[103]%>

</b></font></div>
<aims:getVirtualServer id="theVS" connectionId="<%=admin%>" name="<%=vname%>" error="vsError" />
<%
theVS.setDescription(description);
//if (vversion.indexOf("ArcMap") != -1) {
if (isRecyclable.equalsIgnoreCase("true")){
	String frequency = request.getParameter("frequency");
	String hour = request.getParameter("hour");
	int intHour = Integer.parseInt(hour);
	String minute = request.getParameter("minute");
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
	theVS.setRecyclingFrequency(frequency);
	theVS.setRecyclingHour(String.valueOf(intHour));
	theVS.setRecyclingMinute(minute);
}
%>
<aims:iterateVirtualServers virtualServers="<%=theVS%>" count="serviceCount" >
	<aims:getVirtualServerAttribute id="containerSet" attribute="CONTAINERSET" />
	<%
	// remove the existing containers
	StringTokenizer stok = new StringTokenizer(containerSet,",");
	String containerString = "";
	String threadString = "";
	while (stok.hasMoreTokens()) {
		String tokString  = stok.nextToken();
		StringTokenizer tok = new StringTokenizer(tokString," ");
		containerString = tok.nextToken();
		threadString = tok.nextToken();
		theVS.removeContainer(containerString);
	}
	// add user defined containers
	for (int i=0;i < selectList.length;i++ ) {
		StringTokenizer stok2 = new StringTokenizer(selectList[i],"^");
		containerString = stok2.nextToken();
		threadString = stok2.nextToken();
		int threads = Integer.parseInt(threadString);
		theVS.addContainer(containerString, threads);
	}
	%>
</aims:iterateVirtualServers>
<%
// update the site
boolean didIt = theVS.changeVirtualServer(admin);
%>
	<script language="JavaScript" type="text/javascript">
		document.location = "<%=nextUrl%>";
	</script>
</body>
</html>
<% } %>
