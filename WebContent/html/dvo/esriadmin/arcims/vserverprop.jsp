<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.ServerCollection, com.esri.aims.mtier.model.admin.Server, java.util.StringTokenizer" %>
<%@ page import="java.util.Vector" %>
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
if (aimsServerURL==null) aimsServerURL = "site_config_vserver.jsp";
String configURL = "site_config_vserver.jsp";
session.setAttribute("configURL", configURL);
session.setAttribute("aimsServerURL", aimsServerURL);
String listServices = request.getParameter("listServices");
if (listServices==null) listServices = "No";
String index = request.getParameter("index");
int s = Integer.parseInt(index);
String virtualServerName = request.getParameter("virtualServerName"); // this is for request from site_config_server.jsp
boolean isAM = true; // when iterate the virtual server later in the code, change the value accordingly.
%>

<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />
<%
VirtualServer theVS = vServers.getVirtualServer(s);
String vsVersion = theVS.getVersion();
String vsName = theVS.getName();
String theVSType = theVS.getType();
%>

<aims:getStartupParameter id="recyclableServers" connectionId="<%=admin%>" parameter="recyclableservers" error="errorRecyc" />
<aims:getStartupParameter id="singleThreadedServers" connectionId="<%=admin%>" parameter="singlethreadedservers" error="errorSingleTh" />

<%
// construct the array of recyclable server
StringTokenizer stok1 = new StringTokenizer(recyclableServers,",");
Vector vecRecycleServer = new Vector();
while (stok1.hasMoreTokens()) {
	String tokString  = stok1.nextToken();
	vecRecycleServer.add(tokString);
}
// construct the array of singleThreadedServers
StringTokenizer stok2 = new StringTokenizer(singleThreadedServers,",");
Vector vecSingleThreadServer = new Vector();
while (stok2.hasMoreTokens()) {
	String tokString2  = stok2.nextToken();
	vecSingleThreadServer.add(tokString2);
}
if (vsVersion.equalsIgnoreCase("ArcMap")) {
	theVSType = "ImageServer:ArcMap";
} else {
	theVSType = theVSType + ":";
}
boolean isRecyclable = false;
if (vecRecycleServer.contains(theVSType)){
	isRecyclable = true;
}
boolean isSingleThreaded = false;
if (vecSingleThreadServer.contains(theVSType)){
	isSingleThreaded = true;
}

%>

<html>
<head>
	<title><%=textList[0]%> <%=textList[86]%></title>
	<script language="JavaScript" type="text/javascript">


		function changeServer() {
			var f = document.forms[0];
			var index = f.sName.selectedIndex;
			document.location = "vserverprop.jsp?index=" + index + "&listServices=<%=listServices%>";
		}

		function chkForm() {
			var f = document.forms[0];
			if (getChoices()>0) {
				f.submit();
			} else {
				alert("No Spatial Servers selected.");
			}
		}

		function getChoices() {
			//alert("getChoices()");
			var f = document.forms[0];
			var k = 0;

			if (f.selectedServer!=null) {
				var c = f.selectedServer.length;
				if (c>1) {
					for (var i=0;i<c;i++) {
						if (f.selectedServer[i].checked) {
							var inst = f.instances[i].value;
							var instNum = 2;
							if (!isNaN(inst)) instNum = parseInt(inst)
							f.selectedServer[i].value += "^" + instNum;
							//alert(f.selectedServer[i].value);
							k++;
						}
					}
				} else {
					if (f.selectedServer.checked) {
						var inst = f.instances.value;
						var instNum = 2;
						if (!isNaN(inst)) instNum = parseInt(inst)
						f.selectedServer.value += "^" + instNum;
						//alert(f.selectedServer.value);
						k++;
					}
				}
			}
			return k;
		}
		function saveConfig() {
			document.location = "saveSite.jsp?page=vserverprop.jsp&index=<%=index%>&listServices=<%=listServices%>";
		}

		function addNewVirtualServer() {
			document.location = "create_vserver.jsp?listServices=<%=listServices%>";
		}

		function displayList() {
			<% if (virtualServerName == null) { %>
					document.location = "site_config_vserver.jsp";
			<% } else {%>
					document.location = "site_config_server.jsp";
			<% } %>
		}

		function createNewContainer() {
			document.location = "create_server.jsp?origPage=VSProp&vsIndex=<%=index%>";
		}

	</script>

</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="process_vserverprop.jsp" method="post" >
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
						<table width="100%" cellspacing="0" cellpadding="1" bgcolor="#FFFFFF" border="0">
							<tr> <!-- Service name -->
								<td >
									<table border=0 width=100%>
										<tr>
											<td align="left" valign="top" bgcolor="#FFFFFF" >
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													&nbsp;<%=textList[56]%>
													<%=vServers.getVirtualServer(s).getName()%>
													<p>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="right" valign="top" >
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													&nbsp;
													&nbsp;
													<input type="button" name="addButton" value="<%=textList[93]%>" style="background-color: #EFEFEF; color: #000099;" onclick="addNewVirtualServer()">
													&nbsp;
												</font>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						<aims:iterateVirtualServers virtualServers="<%=theVS%>" count="serviceCount" >
							<aims:getVirtualServerAttribute id="vsType" attribute="TYPE" />
							<aims:getVirtualServerAttribute id="version" attribute="VERSION" />
							<aims:getVirtualServerAttribute id="descript" attribute="DESCRIPTION" />
							<aims:getVirtualServerAttribute id="containerSet" attribute="CONTAINERSET" />
							<aims:getVirtualServerAttribute id="access" attribute="ACCESS" />

	    					<aims:getVirtualServerAttribute id="frequency" attribute="RECYCLE_FREQUENCY" />
	    					<aims:getVirtualServerAttribute id="hour" attribute="RECYCLE_HOUR" />
<%
							int hourInt = Integer.parseInt(hour);
							if (hourInt > 12) {
								hourInt = hourInt - 12;
								isAM = false;
							} else {
								if (hourInt == 12) {
									isAM = false;
								} else {
									if (hourInt == 0) {
										hourInt = 12;
									}
								}
							}
%>
	    					<aims:getVirtualServerAttribute id="minute" attribute="RECYCLE_MINUTE" />

							<tr>
								<td align="center" width="695">
									<table border=0 width="600">
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[70]%><br>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
														<%=vsType%> <% if (version.equals("ArcMap")) { %>[ArcMap]<% } %>
												</b></font>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[99]%> <br>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=access%>
												</b></font>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[116]%> <br>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<input type="text" name="description" value="<%=descript%>" size="40">
												</b></font>
											</td>
										</tr>
										<%
										//if (vsVersion.equalsIgnoreCase("ArcMap")) {
										if (isRecyclable) {
										%>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[182]%> <br>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
													<select name="frequency" style="font-family: sans-serif; ">
														<option value="1" <%if (frequency.equalsIgnoreCase("1")){%>SELECTED<%}%>> <%=textList[196]%>
														<option value="2" <%if (frequency.equalsIgnoreCase("2")){%>SELECTED<%}%>> <%=textList[197]%>
														<option value="3" <%if (frequency.equalsIgnoreCase("3")){%>SELECTED<%}%>> <%=textList[198]%>
														<option value="4" <%if (frequency.equalsIgnoreCase("4")){%>SELECTED<%}%>> <%=textList[199]%>
														<option value="6" <%if (frequency.equalsIgnoreCase("6")){%>SELECTED<%}%>> <%=textList[200]%>
														<option value="12" <%if (frequency.equalsIgnoreCase("12")){%>SELECTED<%}%>> <%=textList[201]%>
														<option value="24" <%if (frequency.equalsIgnoreCase("24")){%>SELECTED<%}%>> <%=textList[202]%>
													</select>
												</font>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[204]%> <br>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">

<table cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="#FFFFFF" align="left" valign="middle">
			<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
				<select name="hour" style="font-family: sans-serif;">
				<%for (int i = 1; i < 13; i++) { %>
					<option value="<%=i%>" <%if (hourInt == i){%>SELECTED<%}%>> <%if (i < 10) out.println("0" + i); else out.println(i);%>
				<%}%>
				</select>
			</font>
		</td>
		<td bgcolor="#FFFFFF" align="left" valign="middle">
			<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b> : </b></font>
		</td>
		<td bgcolor="#FFFFFF" align="left" valign="middle">
			<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
				<select name="minute" style="font-family: sans-serif; ">
					<option value="0" <%if (minute.equalsIgnoreCase("0")){%>SELECTED<%}%>> 00
					<option value="15" <%if (minute.equalsIgnoreCase("15")){%>SELECTED<%}%>> 15
					<option value="30" <%if (minute.equalsIgnoreCase("30")){%>SELECTED<%}%>> 30
					<option value="45" <%if (minute.equalsIgnoreCase("45")){%>SELECTED<%}%>> 45
				</select>
			</font>
		</td>
		<td bgcolor="#FFFFFF" align="left" valign="middle">
			<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b> &nbsp;&nbsp; </b></font>
		</td>
		<td bgcolor="#FFFFFF" align="left" valign="middle">
				<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
					<select name="ampm" style="font-family: sans-serif; ">
						<option value="AM" <%if (isAM){%>SELECTED<%}%>> <%=textList[194]%>
						<option value="PM" <%if (! isAM){%>SELECTED<%}%>> <%=textList[195]%>
					</select>
				</font>
		</td>
	</tr>
</table>
											</td>
										</tr>
										<% } // end if virtual server is recyclable%>

									</table>
								</td>
							</tr>
							<tr>
								<td align="center" background="../images/cyan_line.gif">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
											&nbsp;<br><%=textList[100]%><p>
									</b></font>

								</td>
							</tr>
							<tr>
								<td align="center">
									<table cellspacing="2" cellpadding="1" width="400" bgcolor="#A5CEF7">
										<tr>
											<td bgcolor="#A5CEF7" width="20">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<b>&nbsp;</b>
												</font>
											</td>
											<td bgcolor="#A5CEF7" width="300">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<b><%=textList[101]%></a></b>
												</font>
											</td>
											<td bgcolor="#A5CEF7" width="80">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<b><%=textList[97]%></a></b>
												</font>
											</td>
										</tr>

<%
											ServerCollection servers = ServerCollection.getServers(admin);
											for (int i=0;i<servers.size();i++) {
												Server theServer = servers.getServer(i);
												String[] containers = theServer.getContainerIDs();
												for (int j=0;j<containers.length;j++) {
													String serverAllowedForSelectedType = theServer.getContainerAttribute(theServer.COMPONENT_ALLOWED, containers[j], vsType, version);
													String containerString = "";
													String threadString = "";
													String ts = "";
													boolean itMatches = false;
													StringTokenizer stok = new StringTokenizer(containerSet,",");
													while (stok.hasMoreTokens()) {
														String tokString  = stok.nextToken();
														StringTokenizer tok = new StringTokenizer(tokString," ");
														containerString = tok.nextToken();
														ts = tok.nextToken();
														if (containerString.equals(containers[j])) {
															itMatches=true;
															threadString = ts;
														}
													}
													if (itMatches) {
														//if it is in the containerset list, output the server name and thread count
%>
										<tr>
											<td background="../images/cyan_line.gif">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<input type="checkbox" name="selectedServer" value="<%=containers[j]%>" checked>
												</font>
											</td>
											<td background="../images/cyan_line.gif">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<%=containers[j]%>
												</font>
											</td>
											<td background="../images/cyan_line.gif">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<input type="text" name="instances" size="3" value="<%=threadString%>" <%if (isSingleThreaded) {%> disabled <%}%>>
												</font>
											</td>
										</tr>
<%
													} else if (serverAllowedForSelectedType.equalsIgnoreCase("true")){
														//output the empty container with appropriate thread count
%>
										<tr>
											<td background="../images/cyan_line.gif">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<input type="checkbox" name="selectedServer" value="<%=containers[j]%>">
												</font>
											</td>
											<td background="../images/cyan_line.gif">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<%=containers[j]%>
												</font>
											</td>
											<td background="../images/cyan_line.gif">
<%
											if (isSingleThreaded) {
%>
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<input type="text" name="instances" size="3" value="1" disabled>
												</font>
<%
											} else {
%>
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<input type="text" name="instances" size="3" value="2">
												</font>
<%
											}
%>
											</td>
										</tr>
<%
													}
										 		} // end of containers loop
											} // end of the monitor loop
										%>
									</table>
								</td>
							</tr>
						</aims:iterateVirtualServers>

							<tr >
								<td bgcolor="#FFFFFF" align="center">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<input type="button" name="createContainer" value="<%=textList[187]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="javascript:createNewContainer();">
									</font>
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="center" height="40">
									<input type="button" name="updateButton" value="<%=textList[64]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="chkForm()">
									&nbsp;&nbsp;&nbsp;<input type="button" name="listButton" value="<%=textList[152]%>" style="background-color: #EFEFEF; color: #000099;" onclick="displayList()">

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

<input type="hidden" name="listServices" value="<%=listServices%>">
<input type="hidden" name="index" value="<%=index%>">
<input type="hidden" name="name" value="<%=vsName%>">
<input type="hidden" name="version" value="<%=vsVersion%>">

<%
if (virtualServerName != null) {
%>
	<input type="hidden" name="fromPage" value="<%=virtualServerName%>">
<%
}
%>

<input type="hidden" name="isRecyclable" value="<%=isRecyclable%>">
<input type="hidden" name="isSingleThreaded" value="<%=isSingleThreaded%>">

</form>

</body>
</html>

<% } %>
