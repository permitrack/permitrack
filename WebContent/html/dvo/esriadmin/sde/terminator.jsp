<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, java.util.StringTokenizer" %>
<%@ page import="com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer, com.esri.aims.mtier.model.admin.SdeConnections, com.esri.aims.mtier.model.admin.SdeConnection" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {
String[] selectList = request.getParameterValues("selectService");

String password = request.getParameter("pwd");
if (password==null) password = "";
String sname = request.getParameter("server");
int s = 0;

admin.setDisplayMessages(true);
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

%>
<html>
<head>
	<title>Terminating</title>
</head>
	<script language="JavaScript" type="text/javascript">
		function doIt() {
			document.location="connections.jsp?server=<%=sdeserver.getName()%>";
		}
	</script>
<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" onload="doIt()">
<%
SdeConnection seCon= null;

SdeConnections seCons=new SdeConnections(admin,sdeserver);
String errorRtnString = null;

for (int i=0;i<seCons.size();i++) {
	seCon = seCons.getSdeConnection(i);
	String pid = seCon.getPid();
	for (int j=0;j<selectList.length;j++) {
		if (selectList[j].equals(pid)) {
				sdeserver.terminateSdeConnection(admin,pid);


			if (errorRtnString!=null) {
		%>
		<script language="JavaScript" type="text/javascript">
			alert("<%=textList[135]%> <%=textList[159]%> <%=pid%>\n\n<%=errorRtnString%>");
		</script>
		<%}

		}
	}
}

 %>

</body>
</html>

<%
if (savePwds) session.setAttribute("sdePwds",sdePwds);
} %>
