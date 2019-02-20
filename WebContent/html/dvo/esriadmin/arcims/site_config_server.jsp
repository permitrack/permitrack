<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.ServerCollection, com.esri.aims.mtier.model.admin.Server, java.util.StringTokenizer" %>
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

String configMode = request.getParameter("configMode");
if (configMode==null) configMode = (String)session.getAttribute("configMode");
if ((configMode==null) || (configMode=="")) configMode = "server";
int s = -1;

configPic = "Tab_selected.gif";

ServerCollection servers = ServerCollection.getServers(admin);

%>
<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />
<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />
<%
int vServerCount = vServers.size();
String[] vServerName = new String[vServerCount];
String[] vServerType = new String[vServerCount];
String[] vServerVersion = new String[vServerCount];
String[] vServerContainerSet = new String[vServerCount];
int[] vServerServiceCount = new int[vServerCount];

String[] serviceName = new String[serviceList.size()];
String[] serviceVS = new String[serviceList.size()];

for (int i=0;i<serviceList.size();i++) {
	serviceVS[i] = serviceList.getService(i).getVirtualServerName();
	serviceName[i] = serviceList.getService(i).getName();
}
int incre = 0;
int ssCount = 0;
%>

<aims:iterateVirtualServers virtualServers="<%=vServers%>" count="serviceCount" >
	<aims:getVirtualServerAttribute id="vsName" attribute="NAME" />
	<aims:getVirtualServerAttribute id="vsType" attribute="TYPE" />
	<aims:getVirtualServerAttribute id="version" attribute="VERSION" />
	<aims:getVirtualServerAttribute id="containerSet" attribute="CONTAINERSET" />
<%
vServerName[incre] = vsName;
vServerType[incre] = vsType;
vServerVersion[incre] = version;
vServerContainerSet[incre] = containerSet;
vServerServiceCount[incre] = 0;
for (int i=0;i<serviceList.size();i++) {
	if (serviceList.getService(i).getVirtualServerName().equals(vsName)) {
		vServerServiceCount[incre]++;
	}
}
incre++;
%>
</aims:iterateVirtualServers>
<!-- <%=incre %> -->
<html>
	<title><%=textList[0]%> <%=textList[86]%></title>
	<script language="JavaScript" type="text/javascript">
		var nList = "";
		var noRemoveString = "";
		var containerName = new Array();
		var containerVS = new Array();
		var ssCount = 0;
		function saveConfig() {
			document.location = "saveSite.jsp?page=site_config_server.jsp";
		}

		function addNewServer() {
			document.location = "create_server.jsp?origPage=newSS";
		}

		function removeChecked() {
			var f = document.forms[0];
			f.action = "remove_server.jsp";
			if (getChoices()>0) {
				if (noRemoveString=="") {
					var msg = "<%=textList[104]%>\n\n" + nList + "\n<%=textList[67]%>";
					if (confirm(msg)) {
						f.submit();
					}
				} else {
					var msg = "<%=textList[193]%>\n\n" + noRemoveString;
					alert(msg);
				}
			} else {
				alert("<%=textList[105]%>");
			}
		}

		function getChoices() {
			nList = "";
			var f = document.forms[0];
			var k = 0;
			noRemoveString = "";
			if (f.selectServer!=null) { // there is/are spatial server(s) checked
				if (f.selectServer.length>1) { // there are more than one spatial server on this site
					for (var i=0;i<f.selectServer.length;i++) {
						if (f.selectServer[i].checked) {
							if (containerVS[i]!="")  {
								noRemoveString = containerName[i] + ":\n" + containerVS[i] + "\n";
							} else {
								nList += f.selectServer[i].value + "\n";
							}
							k++;
						}
					}
				} else { // there is only one spatial server on this site
					if (f.selectServer.checked) {
						if (containerVS[0]!="")  {
							noRemoveString = containerName[0] + ":\n" + containerVS[0] + "\n";
						} else {
							nList += f.selectServer.value + "\n";
						}
						k++;
					}
				}
			}
			return k;
		}

		function switchPage() {
			var f = document.forms[0];
			var index = f.theSType.selectedIndex;
			var url = f.theSType.options[index].value;
			document.location = url;
		}

		function checkBoxChange(n) {
			alert("<%=textList[210]%>");
			var f = document.forms[0];
			if (f.selectServer.length>1) { // there are more than one spatial server on this site
				f.selectServer[n].checked = false;
			} else {
				f.selectServer.checked = false;
			}
		}

	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="process_server.jsp" method="post">
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
					<table width="695" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<td align="left">
								<table border=0>
									<tr>
										<TD  align="center">
											<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>
												&nbsp;&nbsp;<a href="site_config.jsp"><%=textList[79]%></a>
											</b></font>
										</TD>
										<TD  align="center">
											<font face="Arial,Helvetica,sans-serif"  color="#000099">
												&nbsp;&nbsp;|&nbsp;&nbsp;
											</font>
										</TD>
										<TD align="center">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<!-- <a href="<%=aimsServerURL%>"> --><%=textList[117]%><!-- </a> -->
											</b></font>
										</TD>
										<TD  align="center">
											<font face="Arial,Helvetica,sans-serif" color="#000099">
												&nbsp;&nbsp;|&nbsp;&nbsp;
											</font>
										</TD>
									</tr>
								</table>
							</td>
							<td align="right">
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
					<td ><!-- page contents -->
						<table cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<td align="left" >
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										&nbsp;<br>
										&nbsp;<%=textList[188]%>
										</font>
										<select name="theSType" style="color: #000099; font-family: sans-serif; font-weight: bold;" onChange="switchPage()">
											<option value="site_config_vserver.jsp"><%=textList[80]%>
											<option value="site_config_server.jsp" SELECTED><%=textList[81]%>
										</select>
										</b>
								</td>
								<td >&nbsp;</td>
							</tr>
							<tr> <!-- remove/add buttons -->
								<td height="35" bgcolor="White" >
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										&nbsp;
										<% if (servers.size()>0) { %>
										&nbsp;
										<input type="button" name="removeButton" value="<%=textList[68]%>" style="background-color: #EFEFEF; color: #000099;" onclick="removeChecked()">
										&nbsp;<%=textList[106]%>
										<% } else { %>
										<%=textList[107]%><br>
										<% } %>
									</font>
								</td>
								<td align="right" >
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										<input type="button" name="addButton" value="<%=textList[93]%>" style="background-color: #EFEFEF; color: #000099;" onclick="addNewServer()">
										&nbsp;&nbsp;
									</font>
								</td>
							</tr>
							<tr>
							<tr> <!-- Headings -->
								<td colspan="2">
									<table cellspacing="0" cellpadding="1" width="100%">
									<tr>
										<td bgcolor="#A5CEF7" width="20" valign="top" align="center">
											&nbsp;
										</td>
										<td bgcolor="#A5CEF7" align="left">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<%=textList[108]%>
											</b></font>
										</td>
										<td bgcolor="#A5CEF7" align="left">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<%=textList[94]%>
											</b></font>
										</td>
										<td bgcolor="#A5CEF7" align="left">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<%=textList[95]%>
											</b></font>
										</td>
										<td bgcolor="#A5CEF7" align="left">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<%=textList[96]%>
											</b></font>
										</td>
										<td bgcolor="#A5CEF7" align="left">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<%=textList[97]%>
											</b></font>
										</td>
									</tr>
									<%
									if (servers.size()>0) {
										for (int i=0;i<servers.size();i++) {
											Server theServer = servers.getServer(i);
											String[] containers = theServer.getContainerIDs();
											for (int j=0;j<containers.length;j++) {
									%>
									<tr>
										<td background="../images/gray_line.gif" width="20" valign="top" align="center">
											<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
												<%
													boolean isDefaultContainer = false;
													int pos = containers[j].indexOf("_1");
													if (pos == (containers[j].length()-2)) {
														isDefaultContainer = true;
													%>
														<!--- <IMG SRC="../images/noselectbox.gif" WIDTH=16 HEIGHT=17 HSPACE=0 VSPACE=0 BORDER=0 ALT=""> --->
														<input type="checkbox" disabled name="selectServer" value="<%=containers[j]%>" onClick="checkBoxChange(<%=ssCount %>)">
													<% } else { %>
														<input type="checkbox" name="selectServer" value="<%=containers[j]%>">
													<% } %>
											</b></font>
										</td>
										<td align="left" valign="top" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												<a href="serverprop.jsp?index=<%=containers[j]%>&serverIndex=<%=i%>"><%=containers[j]%></a>
											</b></font>
										</td>
										<td align="left" valign="top" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
<%
												String cList = "";
												String ctList = "";
												String cList2 = "";
												String iList = "";
												String sList = "";
												for (int k=0;k<vServerContainerSet.length;k++) {
													String ckStr = containers[j] + " ";
													int scount = 0;
													if (vServerContainerSet[k].indexOf(ckStr)!=-1) {
														//Following line added the parameter "virtualServerName" to indicate to the vserverprop.jsp where it is from
														cList += "<a href='vserverprop.jsp?index=" + k + "&virtualServerName=" + vServerName[k] + "'>" + vServerName[k] + "</a> <br>" ;
														cList2 += vServerName[k] + " ";
														ctList += vServerType[k] ;
														if ((vServerType[k].indexOf("ImageServer")!=1) && (vServerVersion[k].indexOf("ArcMap")!=-1)) ctList += "[ArcMap]";
														ctList += "<br>";
														VirtualServer vs = VirtualServer.getVirtualServer(vServerName[k], admin);
														iList += String.valueOf(vs.getThreadsForContainer(containers[j])) + "<br>";

														for (int m=0;m<serviceList.size();m++) {
															if (serviceList.getService(m).getVirtualServerName().equals(vServerName[k])) {
																scount++;
															}
														}
														sList += String.valueOf(scount) + "<br>";
													}
												}
%>
												<%=cList%>&nbsp
											</font>

										</td>
										<td align="left" valign="top" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=ctList%>
											</font>
										</td>
										<td align="left" valign="top" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=sList%>
											</font>
										</td>
										<td align="left" valign="top" background="../images/gray_line.gif">
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
												<%=iList%>
											</font>
										</td>
										<script language="JavaScript" type="text/javascript">
											containerName[ssCount] = "<%=containers[j]%>";
											containerVS[ssCount] = "<%=cList2%>";
											ssCount++;
										</script>
									</tr>
<%
												ssCount++;
									 		} // end for loop for containers for a monitor
										} // end for loop for the monitors
									} // end if monitor.size() > 0
%>
								 	</table>
								</td>
							</tr>
						</table>
					</td>
					<td width="5" align="center" valign="middle" background="../images/rightedge.gif"> <!-- right edge -->
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
