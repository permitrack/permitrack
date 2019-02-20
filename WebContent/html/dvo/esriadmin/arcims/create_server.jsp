<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.ServerCollection, com.esri.aims.mtier.model.admin.Server, com.esri.aims.mtier.model.admin.Monitor, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="aimsParameters.jsp" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

String aimsServerURL = (String)session.getAttribute("aimsServerURL");
if (aimsServerURL==null) aimsServerURL = "site_config_server.jsp";
String configURL = "site_config_server.jsp";
session.setAttribute("configURL", configURL);
session.setAttribute("aimsServerURL", aimsServerURL);

java.util.Vector monitors = Monitor.getMonitors(admin);

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String origPage = request.getParameter("origPage");
String vsIndex = request.getParameter("vsIndex");

%>


<html>
<head>
	<title><%=textList[110]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">

		function saveConfig() {
			document.location = "saveSite.jsp?page=create_server.jsp";
		}

		function displayList() {
<%
			if (origPage.equalsIgnoreCase("newVS")) {
%>
				document.location = "create_vserver.jsp";
<%
			} else if (origPage.equalsIgnoreCase("newSS")) {
%>
				document.location = "site_config_server.jsp";
<%
			} else if (origPage.equalsIgnoreCase("VSProp")) {
%>
				document.location = "vserverprop.jsp?index=<%=vsIndex%>";
<%
			}
%>
		}

		function addNewServer() {
			var f = document.forms[0];
<%
			if (origPage != null) {
				if (vsIndex != null) {
%>
					f.action="process_create_server.jsp?origPage=<%=origPage%>&vsIndex=<%=vsIndex%>";
<%
				} else {
%>
					f.action="process_create_server.jsp?origPage=<%=origPage%>";
<%
				}
			}
%>
			f.submit();
		}

	</script>



</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="process_create_vserver.jsp" method="post" >
<table width="705" cellspacing="0" cellpadding="0" border=0>
	<tr> <!-- Title Heading -->
		<td align="left">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
				<td align="left">
					<font face="Arial,Helvetica,sans-serif" size="+3">
						<b>&nbsp;&nbsp;<%=textList[0]%></b>
					</font>
					<font size="-1"><br>&nbsp;</font>
				</td>
				<td align="right">
					<font face="Arial,Helvetica,sans-serif" size="-2"><b>
						<%=textList[203]%>&nbsp;&nbsp;<%=(String)session.getAttribute("siteName")%> - <%=(String)session.getAttribute("portNum")%>
					</b></font>
				</td>
			</tr>
			</table>
		</td>
	</tr>

	<tr> <!-- Tabs -->
		<td align="left">
			<table cellspacing="0" cellpadding="0" border="0">
				<%@ include file="aimsTabs.jsp" %>
				<tr>
					<td width="700" colspan="4" height="35" align="right" valign="middle" background="../images/Bar.gif">
						<TABLE>
							<TR>
								<TD>
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>
										<a href="<%=helpUrl%>" target="_blank"><%=textList[33]%></a>
									</b></font>
								</TD>
								<TD  align="center">
									<font face="Arial,Helvetica,sans-serif"  color="#000099">
										&nbsp;&nbsp;|&nbsp;&nbsp;
									</font>
								</TD>
								<TD ALIGN="center">
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>
									<a href="javascript:saveConfig();"><%=textList[34]%></a>
									</b></font>
								</TD>
								<td>
									<img src="../images/pixel.gif" width="20" height="5" hspace="0" vspace="0" border="0" alt="">
								</td>
							</TR>
						</TABLE>
					</td>
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
					<td align="center" valign="middle" width="695" >
						<table width=650" cellspacing="0" cellpadding="1" bgcolor="#FFFFFF" border="0">
							<tr>
								<td height="35" bgcolor="White" align="left">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>&nbsp;&nbsp;<%=textList[110]%></b><br></font>
									<hr color="#A5CEF7">
								</td>
							</tr>
							<tr>
								<td bgcolor="White" align="left">
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										<%=textList[112]%><%=textList[118]%><%=textList[119]%><%=textList[120]%><p>
										<p>
									</font>
								</td>
							</tr>
							<tr>
								<td bgcolor="White" align="center">
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										<%=textList[111]%>&nbsp;
										<select name="monitor">
										<% for (int i=0;i<monitors.size();i++) {
											String sName = (String) monitors.elementAt(i);
										%>
											<option value="<%=sName%>"><%=sName%>
										<% } %>
										</select>&nbsp;
										<input type="button" name="addButton" value="<%=textList[124]%>" style="background-color: #EFEFEF; color: #000099;" onclick="addNewServer()">
										&nbsp;&nbsp;
										<input type="button" name="listButton" value="<%=textList[152]%>" style="background-color: #EFEFEF; color: #000099;" onclick="displayList()">
										<p>
									</font>
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

</form>

</body>
</html>
<% } %>
