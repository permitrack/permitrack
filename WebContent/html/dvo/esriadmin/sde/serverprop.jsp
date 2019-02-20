<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer, java.util.StringTokenizer" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="asdeParameters.jsp" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String index = request.getParameter("index");
String sname = request.getParameter("server");
int s = 0;

admin.setDisplayMessages(true);
SdeServersCollection sdeservers = new SdeServersCollection(admin,"");
for (int i=0;i<sdeservers.size();i++) {
	if (sdeservers.getSdeServer(i).getName().equals(sname)) s=i;
}
SdeServer sdeserver = sdeservers.getSdeServer(s);

String pwd = "";
String getPwd = "false";
String sdePwds = (String)session.getAttribute("sdePwds");
if (sdePwds==null) sdePwds = "NoPasswords";
if (sdePwds.equals("NoPasswords")) {
	getPwd = "true";
	//session.setAttribute("sdePwds",sdePwds);
} else {
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
			pwd=p;
		}
	}
	if (pwd.equals("")) getPwd = "true";

}

%>


<html>
<head>
	<title><%=textList[126]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		var pwd = "";
		var getPwd = <%=getPwd%>;
		var serviceList = new Array();

		<% for (int i=0;i<sdeservers.size();i++) { %>
		serviceList[<%=i%>] = "<%=sdeservers.getSdeServer(i).getName()%>";
		<% } %>

		function displayList() {
			document.location = "services.jsp";
		}

		function changeServer() {
			var f = document.forms[0];
			var index = f.sName.selectedIndex;
			var server = f.sName.options[index].value;
			document.location = "serverprop.jsp?server=" + server;
		}

		function startService() {
			document.forms[0].serviceMode.value="start";
			if (getPwd) {
				var Win1 = open("password.jsp","","width=500,height=50");
			} else {
				document.forms[0].pwd.value = "";
				document.forms[0].submit();
			}
		}

		function stopService() {
			if (confirm("<%=textList[160]%>")) {
				document.forms[0].serviceMode.value="stop";
				if (getPwd) {
					var Win1 = open("password.jsp","","width=500,height=50");
				} else {
					document.forms[0].pwd.value = "";
					document.forms[0].submit();
				}
			}
		}

		function pauseService() {
			document.forms[0].serviceMode.value="pause";
			if (getPwd) {
				var Win1 = open("password.jsp","","width=500,height=50");
			} else {
				document.forms[0].pwd.value = "";
				document.forms[0].submit();
			}

		}

		function resumeService() {
			document.forms[0].serviceMode.value="resume";
			if (getPwd) {
				var Win1 = open("password.jsp","","width=500,height=50");
			} else {
				document.forms[0].pwd.value = "";
				document.forms[0].submit();
			}
		}

		function displayList() {
			document.location = "services.jsp";
		}

		function showConnections(n) {
			//alert("Connections Page not available yet.");
			document.location = "connections.jsp?server=" + n;
		}

		function chkPassword2() {
			var ok = false;
			if (getPwd) {
				//var Win1 = open("password.jsp","","width=200,height=75");
				pwd = prompt("Password:","");
				if ((pwd!=null) && (pwd!="")) {
					document.forms[0].pwd.value = pwd;
					ok=true;
				}
			} else {
				ok=true;
				document.forms[0].pwd.value = "";
			}
			return ok;
		}

		function sendAction(pw) {
			//alert(pw);
			document.forms[0].pwd.value = pw;
			document.forms[0].submit();
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
		<td align="center" valign="top">
			<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>
				<tr>
					<td align="center" valign="top" width="695">
						<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>
							<tr> <!-- Start/Stop buttons -->
								<td  bgcolor="White">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										&nbsp;<br>
									</b></font>
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										&nbsp;
										<%=textList[56]%>
										<!-- <%=sdeserver.getName() %>:<%=sdeserver.getPort() %> -->
										<select name="sName" style="color: #000099; font-weight: bold; font-family: sans-serif; " onChange="changeServer()">
											<% for (int i=0;i<sdeservers.size();i++) {
											%>
											<option value="<%=sdeservers.getSdeServer(i).getName()%>"
											<% if (sdeservers.getSdeServer(i).getName().equals(sdeserver.getName())) { %>
											selected
											<% } %>
											><%=sdeservers.getSdeServer(i).getName()%>:<%=sdeservers.getSdeServer(i).getPort()%>
											<% } %>

										</select>
									</font>
								</td>
								<td align="right">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										&nbsp;<br>
									</b></font>
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										&nbsp;<%String statStr2 = sdeserver.getStatus(); %>
										<input type="button" name="startButton" value="<%=textList[59]%>" style="background-color: #EFEFEF; color: #000099;" onclick="startService()" <%if ((statStr2.equals("running")) || (statStr2.equals("paused"))) {%>disabled<%} %>>
										&nbsp;
										<input type="button" name="stopButton" value="<%=textList[58]%>" style="background-color: #EFEFEF; color: #000099;" onclick="stopService()" <%if (statStr2.equals("stopped")) {%>disabled<%} %>>
										&nbsp;
										<input type="button" name="pauseButton" value="<%=textList[127]%>" style="background-color: #EFEFEF; color: #000099;" onclick="pauseService()" <%if ((statStr2.equals("paused")) || (statStr2.equals("stopped"))) {%>disabled<%} %>>
										&nbsp;
										<input type="button" name="resumeButton" value="<%=textList[128]%>" style="background-color: #EFEFEF; color: #000099;" onclick="resumeService()"<%if ((statStr2.equals("running")) || (statStr2.equals("stopped"))) {%>disabled<%} %>>
									</font>
								<!-- <input type="button" name="listButton" value="<%=textList[98]%>" style="background-color: #EFEFEF; color: #000099;" onclick="displayList()"> -->
								</td>
							</tr>
							<!-- data -->
							<tr>

							<td align="center" colspan="2"><table><tr>
								<td bgcolor="White" align="right">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%=textList[137]%>&nbsp;
										</b></font>
									</td>
									<td bgcolor="White">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">

											<%=sdeserver.getName()%>
										</font>
									</td>
								</tr>
								<tr>
									<td bgcolor="White" align="right">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%=textList[138]%>&nbsp;
										</b></font>
									</td>
									<td bgcolor="White" >
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
											<%=sdeserver.getInstanceName()%>
										</font>
									</td>
								</tr>
								<tr>
									<td bgcolor="White" align="right">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%=textList[139]%>&nbsp;
										</b></font>
									</td>
									<td bgcolor="White" >
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
											<%=sdeserver.getPort()%>
										</font>
									</td>
								</tr>
								<tr>
									<td bgcolor="White" align="right">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%=textList[140]%>&nbsp;
										</b></font>
									</td>
									<td bgcolor="White" >
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<% String relString = sdeserver.getRelease();
											if ((relString==null) || (relString.equals("null"))) relString = textList[151]; %>
										<%=relString%>
										</font>
									</td>
								</tr>
								<tr>
									<td bgcolor="White" align="right">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%=textList[57]%>&nbsp;
										</b></font>
									</td>
									<td bgcolor="White" ><table border="0" cellspacing="0" cellpadding="0"><tr>
											<td align="left" valign="middle" bgcolor="White" >
											<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
											<% String statString = "Started";

											if (statStr2.equals("stopped")) statString = "Stopped";
											if (statStr2.equals("paused")) statString = "Paused";%>
											<%=statString%>
										</font>
										</td>
											<td width="25" align="left" valign="middle" bgcolor="White" >

										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
											<% String gearURL = "GearStop.gif";
												if (sdeserver.getStatus().equals("running")) gearURL = "TransGear.gif";
												 %>
											&nbsp;<img src="../images/<%=gearURL%>" width="20" height="20" hspace="0" vspace="0" border="0" alt="<%=sdeserver.getStatus()%>">
												</font>
											</td>
										</tr></table></td>
									</td>
								</tr>
								<tr>
									<td bgcolor="White" align="right">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%=textList[141]%>&nbsp;
										</b></font>
									</td>
									<td bgcolor="White" >
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
								</tr></table></td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="center" height="40" colspan="2">
									<input type="button" name="listButton" value="<%=textList[153]%>" style="background-color: #EFEFEF; color: #000099;" onclick="displayList()">
								</td>

							</tr>
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

<input type="hidden" name="server" value="<%=sname %>">
<input type="hidden" name="serviceMode" value="">
<input type="hidden" name="checkList" value="">
<input type="hidden" name="index" value="<%=s%>">
<input type="hidden" name="pwd" value="">


</form>
</body>
</html>
<% } %>
