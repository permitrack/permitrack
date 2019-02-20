<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, java.util.StringTokenizer" %>
<%@ page import="com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer, com.esri.aims.mtier.model.admin.SdeConnections, com.esri.aims.mtier.model.admin.SdeConnection" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="asdeParameters.jsp" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String sname = request.getParameter("server");
int s = 0;

admin.setDisplayMessages(true);
SdeServersCollection sdeservers = new SdeServersCollection(admin,"");
for (int i=0;i<sdeservers.size();i++) {
	if (sdeservers.getSdeServer(i).getName().equals(sname)) s=i;
}
SdeServer sdeserver = sdeservers.getSdeServer(s);

SdeConnection seCon= null;

SdeConnections seCons=new SdeConnections(admin,sdeserver);
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
	<title><%=textList[145]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		var pwd = "";
		var getPwd = <%=getPwd%>;
		var serviceList = new Array();

		<% for (int i=0;i<seCons.size();i++) {
		seCon = seCons.getSdeConnection(i);%>

		serviceList[<%=i%>] = "<%=seCon.getPid()%>";
		<% } %>

		function displayList() {
			document.location = "services.jsp";
		}

		function changeServer() {
			var f = document.forms[0];
			var index = f.sName.selectedIndex;
			var server = f.sName.options[index].value;
			document.location = "connections.jsp?server=" + server;
		}

		function terminate() {
			//alert("<%=textList[136]%>");
			var f = document.forms[0];
			f.action = "terminator.jsp";
			if (getChoices()>0) {
				var msg = "<%=textList[158]%>\n<%=textList[67]%>";
				if (confirm(msg)) {

					if (getPwd) {
						var Win1 = open("password.jsp","","width=500,height=50");
					} else {
						sendAction("");
					}
				}
			} else {
				alert("<%=textList[157]%>");
			}
		}

		function getChoices() {
			var f = document.forms[0];
			var k = 0;

			if (f.selectService!=null) {
				if (f.selectService.length) {
					for (var i=0;i<f.selectService.length;i++) {
						if (f.selectService[i].checked) {
							k++;
						}
					}
				} else {
					if (f.selectService.checked) {
						k++;
					}
				}
			}
			return k;
		}

		function sendAction(pw) {
			//alert(pw);
			document.forms[0].pwd.value = pw;
			document.forms[0].submit();
		}

		function displayList() {
			document.location = "services.jsp";
		}

	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="terminator.jsp" method="post">
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
								<td><table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%><tr>
								<td  bgcolor="White">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										&nbsp;<br>
									</b></font>
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										&nbsp;
										<%=textList[146]%>

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
									<td height="35" align="right" >
										&nbsp;<input type="button" name="KillButton" value="<%=textList[150]%>" style="background-color: #EFEFEF; color: #000099;" onclick="terminate()" <%if (seCons.size()<1) { %> disabled <%} %>>
									</td>
								</tr>
								</table></td>
							</tr>
							<!-- data -->
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<% if (seCons.size()>0) {
										%>
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
											<% if (seCons.size()>1) { %>
											<input type="checkbox" name="selectAll" value="Yes" onclick="setAll()">
											<% }  else {%>
											&nbsp;&nbsp;&nbsp;
											<%} %>
											<%=textList[176]%></b>
											</font>
										</td>
										<td bgcolor="#A5CEF7">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<b><%=textList[175]%></b>
											</font>
										</td>
										<td bgcolor="#A5CEF7">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<b><%=textList[147]%></b>
											</font>
										</td>
										<td bgcolor="#A5CEF7">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<b><%=textList[148]%></b>
											</font>
										</td>
										<td bgcolor="#A5CEF7">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<b><%=textList[149]%></b>
											</font>
										</td>
										</tr>
											<% for (int i=0;i<seCons.size();i++) {
											seCon = seCons.getSdeConnection(i);%>
										<tr>
										<td >
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<input type="checkbox" name="selectService" value="<%=seCon.getPid()%>">&nbsp;
												<%=seCon.getNode() %>
											</font>
										</td>
										<td >
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=seCon.getSysName() %>
											</font>
										</td>
										<td >
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=seCon.getUser() %>
											</font>
										</td>
										<td >
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=seCon.getPid() %>
											</font>
										</td>
										<td >
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=seCon.getServerStartTime()%>
											</font>
										</td>
										</tr>
										<%	}
										} else {
										%>
										<tr>
										<td >
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<b>&nbsp;<br>&nbsp;&nbsp;No current connections<p>&nbsp;</b>
											</font>
										</td>
										</tr>
										<%} %>
										<tr>
											<td bgcolor="#FFFFFF" align="center" height="40" colspan="4">
												<input type="button" name="listButton" value="<%=textList[153]%>" style="background-color: #EFEFEF; color: #000099;" onclick="displayList()">
											</td>

										</tr>
									</table>
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
<input type="hidden" name="index" value="">
<input type="hidden" name="pwd" value="">

</form>
</body>
</html>
<% } %>
