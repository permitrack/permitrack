<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.io.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Service, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<jsp:useBean id="TheBean" scope="page" class="com.esri.aims.mtier.model.util.FileUpload" />
<%@ include file="../textList.jsp"%>

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

TheBean.doUpload(request);
String name = TheBean.getFieldValue("service");
String vserver = TheBean.getFieldValue("vserver");
String outfile = TheBean.getFieldValue("outfile");
if (outfile==null) outfile = "";
String outurl = TheBean.getFieldValue("outurl");
if (outurl==null) outurl = "";
String it = TheBean.getFieldValue("imagetype");
if (it==null) it = "0";
int imagetype = Integer.parseInt(it);
String pixelcount = TheBean.getFieldValue("pixelcount");
if (pixelcount==null) pixelcount = "4";
int pixel = (Integer.parseInt(pixelcount) * 262144);
String cu = TheBean.getFieldValue("cleanup");
if (cu==null) cu = "0";
int cleanup = Integer.parseInt(cu);
String status = TheBean.getFieldValue("status");
if (status==null) status = "Stopped";
String axlfile = TheBean.getFieldValue("axlfile");

Service service = new Service();

String index = TheBean.getFieldValue("index");
int s = Integer.parseInt(index);

String vsType = VirtualServer.getVirtualServer(vserver, admin).getType();
String vsVersion = VirtualServer.getVirtualServer(vserver, admin).getVersion();
if (vsVersion==null) vsVersion = "";

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String nextUrl = "services.jsp";

if (alwaysSave.equals("Yes")) {
	nextUrl = "saveSite.jsp?page=services.jsp";
} else if (promptSave.equals("Yes")) {
	nextUrl = "saveDialog.jsp?page=services.jsp";
}

%>

<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />
<html>
<head>
	<title><%=textList[75]%> - <%=textList[0]%></title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
<%

Service oldService = serviceList.getService(s);
%>
<aims:adminService service="<%=oldService%>" connectionId="<%=admin%>" action="remove" error="adminError"  />
<%
service.setName(name);
service.setVirtualServer(vserver,vsType,vsVersion);
service.setImageType(imagetype);
service.setOutputDir(outfile);
service.setOutputURL(outurl);
service.setPixelCount(pixel);
service.setOutputCleanup(cleanup);
//service.setConfigFile(TheBean.getFilepath() + TheBean.getFilename());
service.setConfigFile(axlfile);
service.setConfigFileLength(TheBean.getFileLength());
boolean doEncode = false;
if ((vsType.equals("ImageServer")) && (vsVersion.equals("ArcMap"))) doEncode = true;
//service.setAccess(0);
service.setConfigContents(TheBean.getUploadedFileAsStream(),doEncode);
//
if (TheBean.getUploadedFile().length()>0) {
	if (service.addService(admin)) {
		if (status.equals("Started")) service.startService(admin);
		%>
		<%=textList[76]%>
	<% } else { %>
		<%=textList[77]%>
	<% }
 } else { %>
		<%=textList[78]%>

<% } %>

</b></font></div>
	<script language="JavaScript" type="text/javascript">
		document.location = "<%=nextUrl%>";
	</script>

</body>
</html>
<% } %>
