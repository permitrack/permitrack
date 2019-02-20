<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Service, com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer, java.util.StringTokenizer" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String serviceMode = request.getParameter("serviceMode");
String password = request.getParameter("pwd");
if (password==null) password = "";
String sname = request.getParameter("server");
int s = 0;

SdeServersCollection sdeservers = new SdeServersCollection(admin,"");
for (int i=0;i<sdeservers.size();i++) {
	if (sdeservers.getSdeServer(i).getName().equals(sname)) s=i;
}
SdeServer sdeserver = sdeservers.getSdeServer(s);

String sdePwds = (String)session.getAttribute("sdePwds");
boolean savePwds = false;
if (sdePwds==null) sdePwds = "NoPasswords";
if (sdePwds.equals("NoPasswords")) {
	sdePwds = sdeserver.getName() + ":" + password;
	if (!password.equals("")) savePwds = true;
} else {
	if (password.equals("")) {

		StringTokenizer stok = new StringTokenizer(sdePwds,"^");
		String theName = "";
		String thePwd = "";
		String p = "";
		while (stok.hasMoreTokens()) {
			String tokString  = stok.nextToken();
			StringTokenizer tok = new StringTokenizer(tokString,":");
			theName = tok.nextToken();
			p = tok.nextToken();
			if (theName.equals(sdeserver.getName())) {
				password=p;
			}
		}
	} else {
		sdePwds += "^" + sdeserver.getName() + ":" + password;
		savePwds = true;
	}
}

sdeserver.setPassword(password);

String pwd = "";
%>
<html>
<head>
	<title>Untitled</title>
	<script language="JavaScript" type="text/javascript">
		function doIt() {
			document.location="serverprop.jsp?server=<%=sname%>";
		}
	</script>
</head>

<body bgcolor="#336699" onload="doIt()">
<%
String errorRtnString = null;
try{
		if (serviceMode.equals("start")) {
			sdeserver.startService(admin);
		}
		if (serviceMode.equals("stop")) {
			sdeserver.shutdownService(admin);
		}
		if (serviceMode.equals("pause")) {
			sdeserver.pauseService(admin);
		}
		if (serviceMode.equals("resume")) {
			sdeserver.resumeService(admin);
		}
    }

catch (com.esri.aims.mtier.model.axl.ErrorAndWarningException ewe) {
  	if (ewe.hasErrors())
    	errorRtnString = ewe.getErrorMessageAt(0);
	else
   		errorRtnString = "Error has ocurred";
}
catch (Exception ex) {
  	errorRtnString = "Error Exception ex";
}



	if (errorRtnString!=null) {
%>
<script language="JavaScript" type="text/javascript">
	alert("<%=textList[135]%> <%=serviceMode %> <%=sname %>\n\n<%=errorRtnString%>");
</script>
<%}
%>
</body>
</html>

<%
if (savePwds) session.setAttribute("sdePwds",sdePwds);
} %>
