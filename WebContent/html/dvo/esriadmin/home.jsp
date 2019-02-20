<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer" %>
<%@ page import="com.esri.aims.mtier.model.admin.SdeServersCollection, com.esri.aims.mtier.model.admin.SdeServer, com.esri.aims.mtier.model.admin.Site" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="textList.jsp" %>
<%

ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {

	admin.setDisplayMessages(true);
    SdeServersCollection sdeservers = new SdeServersCollection(admin,"");
/*
	boolean adminSDE = false;
	boolean adminIMS = true;

    if (sdeservers.size()>0) {
		adminSDE = true;
	}
	*/
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

	int s = -1;
	String vsType = "";
	String vsName = "";
	String aimsServerURL = "site_config_vserver.jsp";
	session.setAttribute("aimsServerURL", aimsServerURL);
	String configURL = "site_config.jsp";
	session.setAttribute("configURL", configURL);


%>


<html>
<head>
	<title><%=textList[10]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		var adminIMS = <%=adminIMS %>	;
		var adminSDE = <%=adminSDE %>;
		function go() {
			var f = document.forms[0];

			if (f.module[0].checked) {
				if (adminIMS) {
					document.location="arcims/services.jsp?sortBy=service";
				} else {
					alert("<%=textList[178] %>");
				}
			} else {
				//alert("SDE Administration not yet implemented.");
				if (adminSDE) {
					document.location="sde/services.jsp";
				} else {
					alert("<%=textList[177] %>");
				}
			}
		}

	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="arcims/services.jsp" method="post">
<table width="700" cellspacing="0" cellpadding="0" border=0>
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
				<tr>
					<TD WIDTH="175" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="images/Tab_selected.gif"><font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b><%=textList[10]%></b></font></TD>
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
			<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>
				<tr> <!-- white box -->
					<td align="center" valign="middle" width="695">	<!-- main body -->
						<TABLE>
							<TR>
								<td align="left" valign="middle">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[11]%>
									</b></font>
								</td>
							</TR>
							<TR>
								<TD ALIGN="left" VALIGN="middle" BACKGROUND="images/cyan_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<img src="images/pixel.gif" width="70" height="5" hspace="0" vspace="0" border="0" alt="">
									</b></font>
								</TD>
							</TR>
							<TR>
								<TD ALIGN="left" VALIGN="middle" >
									<% String sdeColor = "#CCCCCC";
										String aimsColor = "#CCCCCC";
										if (adminSDE) sdeColor = "#000099";
										if (adminIMS) aimsColor = "#000099";
									%>
									<font face="Arial,Helvetica,sans-serif" color="<%=aimsColor%>" size="-1"><b>

										<INPUT TYPE="radio" NAME="module" VALUE="arcims" checked <%if (!adminIMS) {%>disabled<%} %>><%=textList[12]%><br>
									</b></font>
									<font face="Arial,Helvetica,sans-serif" color="<%=sdeColor%>" size="-1"><b>
										<INPUT TYPE="radio" NAME="module" VALUE="sde"<%if (!adminSDE) {%> disabled <%} %>><%=textList[13]%><br>
									<br>
									<!--
									<%
									/*
								    SdeServer sdeserver = null;
							      	for (int i=0; i<sdeservers.size(); i++) {
							      		sdeserver = sdeservers.getSdeServer(i);
							      		out.println(sdeserver.getName() + "<br>");
							      		out.println(sdeserver.getInstanceName() + "<br>");
							      		out.println(sdeserver.getPort() + "<br>");
							      		out.println(sdeserver.getRelease() + "<br>");
							      		out.println(sdeserver.getConnectionsNumber() + "<br>");
							      		out.println(sdeserver.getStatus() + "<br>");
								  		out.println("&nbsp;<br>");
							    	}
									*/
									%>
									 -->
									 <%if ((!adminIMS) && (!adminSDE)) { %>	<p>Both ArcIMS and ArcSDE are currently unavailable.<%} %>
									</b></font>
							</TD>
							</TR>
							<TR>
								<TD ALIGN="left" VALIGN="middle" >
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<img src="images/pixel.gif" width="500" height="100" hspace="0" vspace="0" border="0" alt="">
									</b></font>
								</TD>
							</TR>
							<TR>
								<TD ALIGN="middle" VALIGN="top" height="40">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<INPUT TYPE="button" NAME="selectModule" VALUE="<%=textList[154]%>" onClick="go()" style="background-color: #EFEFEF; color: #000099;"><br>

									</b></font>
								</TD>
							</TR>

						</TABLE>
					</td>
					<td width="5" align="center" valign="middle" background="images/rightedge.gif"> <!-- right edge -->
						<img src="images/pixel.gif" width="5" height="25" hspace="0" vspace="0" border="0" alt="">
					</td>
				</tr>
				<tr> <!-- bottom edge -->
					<td height="5" width="695" bgcolor="White" background="images/bottomleftedge.gif">
						<img src="images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt="">
					</td>
					<td width="5" height="5" align="center" valign="middle" background="images/bottomrightedge.gif">
						<img src="images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt="">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden" name="service" value="">
<input type="hidden" name="serviceMode" value="">
<input type="hidden" name="checkList" value="">

</form>
</body>
</html>

<% } %>
