<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="asdeParameters.jsp" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

servicePic = "Tab_selected.gif";
String host = (String)session.getAttribute("host");
if (host==null) host = "";

admin.setDisplayMessages(true);
SdeServersCollection sdeservers = new SdeServersCollection(admin,"");

String pwds = (String)session.getAttribute("pwds");
if (pwds==null) {
	for (int i=0;i<sdeservers.size();i++) {
		pwds += sdeservers.getSdeServer(i).getName() + ":";
		if (i<sdeservers.size()-1) pwds += "^";
	}
	session.setAttribute("pwds",pwds);
}
session.setAttribute("sdeservers", sdeservers);

%>


<html>
<head>
	<title><%=textList[126]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		var serviceList = new Array();

		<% for (int i=0;i<sdeservers.size();i++) { %>
		serviceList[<%=i%>] = "<%=sdeservers.getSdeServer(i).getName()%>";
		<% } %>
		function saveConfig() {
			document.location = "saveSite.jsp?page=services.jsp";

		}

		function showDetails(n) {
			//alert("Detail Page not available yet.");
			document.location = "serverprop.jsp?server=" + n;
		}

		function showConnections(n) {
			//alert("Connections Page not available yet.");
			document.location = "connections.jsp?server=" + n;
		}
	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="server_action.jsp" method="post">
<table width="705" cellspacing="0" cellpadding="0" border=0>
	<tr> <!-- Title Heading -->
		<td align="left">

			<font face="Arial,Helvetica,sans-serif" size="+3">
				<b>&nbsp;&nbsp;<%=textList[0]%></b>
			</font>
			<font size="-1"><br>&nbsp;</font>
		</td>
	</tr>

	<tr> <!-- Tabs -->
		<td align="left">
			<table cellspacing="0" cellpadding="0" border="0">
				<%@ include file="asdeTabs.jsp" %>
				<tr>
					<TD WIDTH="700" colspan="3" HEIGHT="35" ALIGN="right" VALIGN="middle" BACKGROUND="../images/Bar.gif">
					<TABLE>
						<TR>
							<TD>
								<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>
									&nbsp;&nbsp;<a href="<%=helpUrl%>" target="_blank"><%=textList[33]%></a>&nbsp;&nbsp;
								</b></font>
							</TD>
							<td>
								<img src="../images/pixel.gif" width="20" height="5" hspace="0" vspace="0" border="0" alt="">
							</td>
						</TR>
					</TABLE>
					</TD>
					<td width="5" height="35" align="center" valign="middle" background="../images/toprightedge.gif"><img src="../images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt=""></td>
				<tr>
			</table>
		</td>
	</tr>

	<tr> <!-- Main Body -->
		<td>
			<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>
				<tr> <!-- white box -->
					<!-- main body -->
					<td align="center" valign="middle" width="695">
						<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>
							<tr> <!-- Start/Stop buttons -->
								<td colspan="4" bgcolor="White">
									<font face="Arial,Helvetica,sans-serif" color="#000099">
										&nbsp;
									</font>
								</td>
							</tr>
							<!-- headings -->
							<tr>
								<td bgcolor="#A5CEF7">
								<script language="JavaScript1.2" type="text/javascript">
									function setAll() {
										//alert("Setting all boxes");
										var f = document.forms[0];
										var t = f.selectAll.checked;
										if (f.selectService.length>1) {
											for (var i=0;i<f.selectService.length;i++) {
												f.selectService[i].checked = t;
											}
										} else {
											f.selectService.checked = t;
										}
									}
								</script>
								<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
									&nbsp;&nbsp;
									<%=textList[179]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[129]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[130]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[134]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[132]%></b>
									</font>
								</td>

							</tr>
							<!-- data -->
							<%
								SdeServer sdeserver = null;
								for (int i=0;i<sdeservers.size();i++) {
									sdeserver = sdeservers.getSdeServer(i);
							%>
							<tr>
								<td bgcolor="White"  background="../images/gray_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										&nbsp;
										<a href="JavaScript:showDetails('<%=sdeserver.getName()%>')"><%=sdeserver.getName()%></a>
									</b></font>
								</td>
								<td bgcolor="White"  background="../images/gray_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%=sdeserver.getInstanceName()%>
									</font>
								</td>
								<td bgcolor="White"  background="../images/gray_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%=sdeserver.getPort()%>
									</font>
								</td>
								<td bgcolor="White"  background="../images/gray_line.gif"><table border="0" cellspacing="0" cellpadding="0"><tr>
										<td width="25" align="left" valign="middle" bgcolor="White" background="../images/gray_line.gif">

									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<% String gearURL = "GearStop.gif";
											String statString = "Started";
											String statStr2 = sdeserver.getStatus();
											if (statStr2.equals("running")) gearURL = "TransGear.gif";
											if (statStr2.equals("stopped")) statString = "Stopped";
											if (statStr2.equals("paused")) statString = "Paused";
											 %>
										<img src="../images/<%=gearURL%>" width="20" height="20" hspace="0" vspace="0" border="0" alt="<%=sdeserver.getStatus()%>">
											</font>
										</td>
										<td align="left" valign="middle" bgcolor="White" background="../images/gray_line.gif">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%=statString%>
									</font>
									</tr></table></td>
								</td>
								<td bgcolor="White"  background="../images/gray_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%
											String sdec = String.valueOf(sdeserver.getConnectionsNumber());
											if ((sdec==null) || (sdec.equals("null")))  sdec = "0";
										%>
										<%if (!sdec.equals("0")){ %><a href="JavaScript:showConnections('<%=sdeserver.getName()%>')"><%} %>
										<%=sdec%>
										<%if (!sdec.equals("0")){ %></a><%} %>

									</font>
								</td>
							</tr>
							<%}%>
						</table>
					</td>
					<!-- right edge -->
					<td width="5" align="center" valign="middle" background="../images/rightedge.gif">
						<img src="../images/pixel.gif" width="5" height="25" hspace="0" vspace="0" border="0" alt="">
					</td>
				</tr>
				<tr> <!-- bottom edge -->
					<td height="5" width="695" bgcolor="White" background="../images/bottomleftedge.gif">
						<img src="../images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt="">
					</td>
					<td width="5" height="5" align="center" valign="middle" background="../images/bottomrightedge.gif">
						<img src="../images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt="">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden" name="server" value="">
<input type="hidden" name="serviceMode" value="">
<input type="hidden" name="checkList" value="">
<input type="hidden" name="index" value="">
<input type="hidden" name="spw" value="">

</form>
</body>
</html>
<% } %>
