<%@ page language="java" buffer="8kb"  %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy" %>
<%@ page errorPage="error_page.jsp" %>
<%@ include file="textList.jsp"%>
<% 

//add in scriptlet to set up site names in form
String site = request.getParameter("host");
if (site==null) site = "localhost";

String portStr = request.getParameter("port");
if (portStr==null) portStr = "5300";
int	port = Integer.parseInt(portStr);

String username = request.getParameter("username");
// change the default value to an empty string if you don't want a default
if (username==null) username = "";
String password = request.getParameter("password");
// change the default value to an empty string when you really want to use a password
if (password==null) password = "";

String defaultOutputDir = "c:\\ArcIMS\\output";
String defaultOutputURL = site + "/output";

session.setAttribute("defaultOutputDir", defaultOutputDir);
session.setAttribute("defaultOutputURL", defaultOutputURL);

ConnectionProxy admin = new ConnectionProxy();
    admin.setConnectionType(ConnectionProxy.TCP_ADMIN);
	admin.setHost(site);
	admin.setPort(port);

	int reaction = admin.ping(false);
	if (reaction==ConnectionProxy.PING_FIRST_LOGIN) {
%>
		<html>
		<body onload="document.location='firstlogin.jsp?host=<%=site%>&port=<%=port%>'"></body>
		</html>
<%
	} else if (reaction==ConnectionProxy.PING_FAIL) {
%>
		<html>
		<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
		<div align="center"><font face="Arial,Helvetica,sans-serif"><b>
		<%=textList[161]%><%=site%>.
		</b></font></div>
		</body>
		</html>
<%
	} else {
%>
<html>
<head>
	<title><%=textList[0]%> <%=textList[3]%></title>
	<script language="JavaScript" type="text/javascript">
		function login() {
			var f = document.forms[0];
			var site = f.site.value;
			var username = f.username.value;
			var password = f.password.value;
			if ((username==null) || (password==null) || (username=="") || (password=="")) {
				alert("<%=textList[1]%>");
			} else {
				f.submit();
			}
		}		
	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="premenu.jsp" method="post">
<table width="695" cellspacing="0" cellpadding="0" border=0>
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
				<tr>
					<TD WIDTH="175" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="images/Tab_selected.gif"><font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b><%=textList[3]%></b></font></TD>
					<TD WIDTH="520" BACKGROUND="images/NoTab.gif"><img src="images/pixel.gif" width="70" height="5" hspace="0" vspace="0" border="0" alt=""></TD>
					<td width="5"><img src="images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt=""></td>
				</tr>
				</tr>
					<TD WIDTH="695" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="images/Bar.gif" colspan="2"><font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>&nbsp;</b></font></TD>
					<td width="5" height="35" align="center" valign="middle" background="images/toprightedge.gif"><img src="images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt=""></td>
				<tr>
			</table>
		</td>
	</tr>
	
	<tr> <!-- Main Body -->
		<td>
			<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width="695">
				<tr> <!-- login body -->
					<td align="center" valign="middle">		
						<table cellspacing="5" cellpadding="5" width=340 
 							<tr> 
								<td align="left" valign="middle" colspan="2">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[4]%> 
									</b></font>
								</td>
							</tr>
 							<tr> 
								<td align="right" valign="middle" >
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[5]%> 
									</b></font>
								</td>
								<td align="left" valign="middle" >
										<input type="text" name="username" value="<%=username%>">
									
								</td>
							</tr>
							<tr> 
								<td align="right" valign="middle" >
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[6]%> 									
									</b></font>
								</td>
								<td align="left" valign="middle" >
										<input type="password" name="password" value="<%=password%>"><br>									
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle" colspan=2 >
									<input type="button" name="loginButton" value="<%=textList[7]%>" onClick="login()" style="background-color: #EFEFEF; color: #000099;">
								</td>
							</tr>
						
						</table>
					</td>
					<td width="5" height="300" align="center" valign="middle" background="images/rightedge.gif">&nbsp;</td>
				</tr>
				<tr> <!-- bottom edge -->
					<td width="695" height="5" bgcolor="White" background="images/bottomleftedge.gif">					
						&nbsp;
					</td>
					<td width="5" height="5" align="center" valign="middle" background="images/bottomrightedge.gif">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden" name="site" value="<%=site%>">
<input type="hidden" name="port" value="<%=portStr%>">
</form>
</body>
</html>

<%
	}
%>