<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
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
if (aimsServerURL==null) aimsServerURL = "site_config.jsp";
String configURL = (String)session.getAttribute("configURL");
if (configURL==null) configURL ="site_config.jsp";

int s = -1;
String vsType = "";
String vsName = "";

servicePic = "Tab_selected.gif";

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

%>

<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />
<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />
<html>
<head>
	<title><%=textList[28]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">

		function getServiceType(vsname){
			var pos = vsname.indexOf("Server");
			var sType = "Unknown";
			if (pos!=-1) {
				sType = vsname.substring(0,pos);

			}
			sType += " Service";
			if (vsname.indexOf("ArcMap")!=-1) sType += " [ArcMap]";
			return sType;
		}
		var serviceList = new Array();

		<% for (int i=0;i<serviceList.size();i++) { %>
		serviceList[<%=i%>] = "<%=serviceList.getService(i).getName()%>";
		<% } %>

		function saveConfig() {
			document.location = "saveSite.jsp?page=services.jsp";
		}

		function startChecked() {
			var f = document.forms[0];
			f.action = "service_action.jsp";
			f.service.value = "";
			if (getChoices()>0) {
				f.serviceMode.value = "start";
				f.submit();
			} else {
				alert("<%=textList[65]%>");
			}
		}

		function stopChecked() {
			var f = document.forms[0];
			f.action = "service_action.jsp";
			f.service.value = "";
			if (getChoices()>0) {
				f.serviceMode.value = "stop";
				f.submit();
			} else {
				alert("<%=textList[65]%>");
			}
		}

		function removeChecked() {
			var f = document.forms[0];
			f.action = "service_action.jsp";
			f.service.value = "";
			if (getChoices()>0) {
				var msg = "<%=textList[66]%>\n" + nList + "<%=textList[67]%>";
				if (confirm(msg)) {
					f.serviceMode.value = "remove";
					f.submit();
				}
			} else {
				alert("<%=textList[65]%>");
			}
		}

		function getChoices() {
			var f = document.forms[0];
			var str1 = "";
			nList = "";
			var k = 0;
			var c = f.selectService.length;
			if (f.selectService!=null) {
				if (f.selectService.length>1) {
					for (var i=0;i<f.selectService.length;i++) {
						if (f.selectService[i].checked) {
							str1 += "1";
							nList += serviceList[i] + "\n";
							k++;
						} else {
							str1 += "0";
						}
					}
				} else {
					if (f.selectService.checked) {
						str1 = "1";
						nList += serviceList[0] + "\n";
						k++;
					} else {
						str1 = "0";
					}
				}
			} else {
				alert("<%=textList[65]%>");
			}
			//f.checkList.value = str1;
			return k;
		}

	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="premenu.jsp" method="post">
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
					<TD WIDTH="700" colspan="4" HEIGHT="35" ALIGN="right" VALIGN="middle" BACKGROUND="../images/Bar.gif">
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
					<td align="center" valign="middle" width="700">
						<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>

							<tr> <!-- Start/Stop buttons -->
								<td height="35" colspan="4" bgcolor="White">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										&nbsp;
										<% if (serviceList.size()>0) { %>
										<input type="button" name="startButton" value="<%=textList[59]%>" style="background-color: #EFEFEF; color: #000099;" onclick="startChecked()">
										&nbsp;
										<input type="button" name="stopButton" value="<%=textList[58]%>" style="background-color: #EFEFEF; color: #000099;" onclick="stopChecked()">
										&nbsp;
										<input type="button" name="removeButton" value="<%=textList[68]%>" style="background-color: #EFEFEF; color: #000099;" onclick="removeChecked()">
										<% } else { %>
										<%=textList[69]%>
										<% } %>
									</font>
								</td>
							</tr>
							<% if (serviceList.size()>0) { %>
							<tr> <!-- Headings -->
								<td bgcolor="#A5CEF7" >
								<script language="JavaScript1.2" type="text/javascript">
									function setAll() {
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
									<% if (serviceList.size()>1) { %>
									<input type="checkbox" name="selectAll" value="Yes" onclick="setAll()">
									<% } %>
									&nbsp;

								<%=textList[71]%>
								</b></font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[95]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[134]%></b>
									</font>
								</td>
							</tr>
							<%
								}
								for (int i=0;i<serviceList.size();i++) {
							%>
							<tr>
								<td bgcolor="White"  background="../images/gray_line.gif">
									<script language="JavaScript1.2" type="text/javascript">
										function revertSetAll() {
											var f = document.forms[0];
											if (f.selectAll.checked) {
												f.selectAll.checked = false;
											}
										}
									</script>
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<input type="checkbox" name="selectService" value="<%=i%>" <% if (serviceList.size()>1) { %> onclick="revertSetAll()" <%}%>>&nbsp;
										<a href="serviceprop.jsp?index=<%=i%>"><%=serviceList.getService(i).getName()%></a>
									</b></font>
								</td>
								<td align="left" valign="middle" bgcolor="White" background="../images/gray_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%
											vsName = serviceList.getService(i).getVirtualServerName();
											vsType = VirtualServer.getVirtualServer(vsName, admin).getType();
											int pos = vsType.indexOf("Server");
											String vv = vsType.substring(0,pos) + " Service";
													//String vv = "!";
											if (vsType.indexOf("Image")!=-1) {
												if (VirtualServer.getVirtualServer(vsName, admin).getVersion().indexOf("ArcMap")!=-1) {
													vv += " [ArcMap]";
												}
											}
										%>
										<%=vv%>
									</font>
								</td>
								<td><table border="0" cellspacing="0" cellpadding="0"><tr>
										<td width="25" align="left" valign="middle" bgcolor="White" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
												<% String running = "Stopped";
												   String gearURL = "GearStop.gif";
												if (serviceList.getService(i).isRunning()) {
													running = "Started";
													gearURL = "TransGear.gif";
												}
												%>
												<img src="../images/<%=gearURL%>" width="20" height="20" hspace="0" vspace="0" border="0" alt="<%=running%>">

											</font>
										</td>
										<td align="left" valign="middle" bgcolor="White" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
												<%=running%>

											</font>
										</td>
								</tr></table></td>
							</tr>
							<% } %>

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

<input type="hidden" name="service" value="">
<input type="hidden" name="serviceMode" value="">
<input type="hidden" name="checkList" value="">

</form>
</body>
</html>
<% } %>
