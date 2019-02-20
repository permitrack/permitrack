<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.Site" %>
<%@ page import="com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer" %>
<%@ page import="com.esri.aims.mtier.model.admin.Monitor" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="textList.jsp"%>

<%

String site = request.getParameter("site");
session.setAttribute("siteName", site);
String username = request.getParameter("username");
String password = request.getParameter("password");

String portStr = request.getParameter("port");
session.setAttribute("portNum", portStr);
if (portStr==null) portStr = "5300";
int port = Integer.parseInt(portStr);

ConnectionProxy admin = new ConnectionProxy();
	admin.setConnectionType(ConnectionProxy.TCP_ADMIN);
	admin.setHost(site);
	admin.setPort(port);
	admin.setUsername(username);
	admin.setPassword(password);

	int reaction = admin.ping();


String hasArcIMS = "true";
String hasSDE = "true";

boolean loginOk = false;
String failMsg = "";

session.setAttribute("adminConnect", admin);

%>
<aims:siteUser connectionId="<%=admin%>" newUser="<%=username%>" newPassword="<%=password%>" error="error1"/>
<%
    SdeServersCollection sdeservers = new SdeServersCollection(admin,"");
	String sdePwds = "NoPasswords";
	session.setAttribute("sdePwds",sdePwds);
/*
	boolean adminSDE = false;
	boolean adminIMS = true;

    if (sdeservers.size()>0) {
		adminSDE = true;
	}
	*/

        java.util.Vector monitors = Monitor.getMonitors(admin);

        // Check for warning codes not contained in ErrorAndWarningException
        com.esri.aims.mtier.model.axl.admin.Warnings  warnings = admin.getWarnings();
        String warningMessage = null;
        int warningCode = 0;
        if (warnings != null) {
            for (int i=0; i < warnings.getWarningCount(); i++)
            {
                if (i == 0) warningMessage = "";
                else
                   warningMessage += "\\n";

                warningCode = warnings.getWarningCodeAt(i);
                // Is warning unauthorized or unavailable server?
                if ((warningCode == 190)) {
                    warningMessage += warnings.getWarningMessageAt(i);
		    warningMessage += textList[213];
		}
		if ((warningCode == 191)) {
                    warningMessage += warnings.getWarningMessageAt(i);
		}
             }
        }

	boolean adminSDE = false;
	boolean adminIMS = false;

	Site testSite = new Site();
	int whatRunning = testSite.getAdminInfo(admin);
   	if (whatRunning==Site.SITE_CONTAINS_ALL) {
   		adminIMS = true;
		adminSDE = true;
	} else if (whatRunning==Site.SITE_CONTAINS_AIMS) {
		adminSDE = false;
		adminIMS = true;
	} else if (whatRunning==Site.SITE_CONTAINS_SDE) {
   		adminIMS = false;
		adminSDE = true;
	}
	String nextURL = "home.jsp";
	if ((adminIMS) && (!adminSDE)) nextURL = "arcims/services.jsp";
	if ((!adminIMS) && (adminSDE)) nextURL = "sde/services.jsp";
 %>
<html>
<head>
	<title><%=textList[21]%></title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
<%=textList[22]%>
</b></font></div>

<script language="JavaScript" type="text/javascript">
        var warnMsg = "<%=warningMessage%>";
        if (warnMsg != "null")
            alert(warnMsg);
	document.location="<%=nextURL%>?host=<%=site%>";

</script>



</body>
</html>
