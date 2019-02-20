<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.ServerCollection, com.esri.aims.mtier.model.admin.Server, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.StringTokenizer" %>
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

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

String selectedTypeIndex = request.getParameter("type");
if (selectedTypeIndex == null) selectedTypeIndex = "1";

ServerCollection servers = ServerCollection.getServers(admin);
%>

<aims:getStartupParameter id="startUpFrequency" connectionId="<%=admin%>" parameter="defaultrecyclingfrequency" />
<aims:getStartupParameter id="startUpHour" connectionId="<%=admin%>" parameter="defaultrecyclingreferencehour" />

<aims:getStartupParameter id="recyclableServers" connectionId="<%=admin%>" parameter="recyclableservers" error="errorRecyc" />
<aims:getStartupParameter id="singleThreadedServers" connectionId="<%=admin%>" parameter="singlethreadedservers" error="errorSingleTh" />

<%
// construct the array of recyclable server
StringTokenizer stok = new StringTokenizer(recyclableServers,",");
Vector vecRecycleServer = new Vector();
while (stok.hasMoreTokens()) {
	String tokString  = stok.nextToken();
	vecRecycleServer.add(tokString);
}
// construct the array of singleThreadedServers
StringTokenizer stok2 = new StringTokenizer(singleThreadedServers,",");
Vector vecSingleThreadServer = new Vector();
while (stok2.hasMoreTokens()) {
	String tokString2  = stok2.nextToken();
	vecSingleThreadServer.add(tokString2);
}
%>

<%
boolean isAM = true;
int hourInt = Integer.parseInt(startUpHour);
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
<aims:getStartupParameter id="startUpMinute" connectionId="<%=admin%>" parameter="defaultrecyclingreferenceminute" />

<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />

<html>
<head>
	<title><%=textList[121]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		var typeList = new Array();
		var versionList = new Array();
		var accessList = new Array();
		var types = new Array();
		var vserverList = new Array();

	<% for (int i=0;i<vServers.size();i++) { %>
		vserverList[<%=i%>] = "<%=vServers.getVirtualServer(i).getName()%>";
		typeList[<%=i%>] = "<%=vServers.getVirtualServer(i).getType()%>";
		versionList[<%=i%>] = "<%=vServers.getVirtualServer(i).getVersion()%>";
		accessList[<%=i%>] = "<%=vServers.getVirtualServer(i).getAccessString()%>";
<% 		}
		int k = 0;
		String[] tList = null;
		Vector currTypeList = new Vector();
		for (int i=0;i<servers.size();i++) {
			tList = servers.getServer(i).getVirtualServerTypesOfAccess(Server.PUBLIC_ACCESS);
			if (tList.length>0) {
				for (int j=0;j<tList.length;j++) {
					if (tList[j].indexOf("ArcMap") != -1) {
						if (!currTypeList.contains("ImageServer [ArcMap]")) {
							tList[j] = "ImageServer [ArcMap]";
%>
							types[<%=k%>] = "<%=tList[j]%>";
<%
							currTypeList.addElement(tList[j]);
							k++;
						}
					} else {
						if (!currTypeList.contains(tList[j])) {
%>
							types[<%=k%>] = "<%=tList[j]%>";
<%
							currTypeList.addElement(tList[j]);
							k++;
						}
					}
				}
			}
		}

		//the currently requested element from the request
                String selectedElement = "";
                if (currTypeList.size() > 0) {
                    selectedElement = (String)currTypeList.elementAt(Integer.parseInt(selectedTypeIndex)); //this is the one without colon
                }
		String selectedElementWithColon = null;
		String selectedVersion = "";
		if (selectedElement.indexOf("ArcMap") != -1) { //if the selected element is ImageServer [ArcMap]
			selectedElement = "ImageServer";
			selectedVersion = "ArcMap";
			selectedElementWithColon = selectedElement + ":ArcMap";
		} else {
			selectedElementWithColon = selectedElement + ":";
		}
		boolean isRecyclable = false;
		if (vecRecycleServer.contains(selectedElementWithColon)){
			isRecyclable = true;
		}
		boolean isSingleThreaded = false;
		if (vecSingleThreadServer.contains(selectedElementWithColon)){
			isSingleThreaded = true;
		}
%>
		//types.sort();

		function chkForm() {
			var f = document.forms[0];
			var theName = f.name.value;
			if ((theName==null) || (theName=="")) {
				alert("Please enter a name for this virtual server.");
			} else {
				var isNewName = true;
				for (var i=0;i<vserverList.length;i++) {
					if (vserverList[i]==theName) isNewName = false;
				}
				if (isNewName) {
					if ((hasSpace(theName)) || (hasSlash(theName)) || (hasBadChar(theName))) {
						alert("Service names cannot have a space or any special characters.");
					} else {

						if (getChoices()>0) {
							// if everything seems okay
							f.submit();
							//alert("Not implemented yet.");

						} else {
							alert("No Spatial Servers selected.");
						}
					}
				} else {
					alert("Name already used by an existing Virtual Server.");
				}
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
							if (!isNaN(inst)) instNum = parseInt(inst);
							f.selectedServer[i].value += "^" + instNum;
							k++;
						}
					}
				} else {
					if (f.selectedServer.checked) {
						var inst = f.instances.value;
						var instNum = 2;
						if (!isNaN(inst)) instNum = parseInt(inst)
						f.selectedServer.value += "^" + instNum;
						k++;
					}
				}
			}
			return k;
		}

		function saveConfig() {
			document.location = "saveSite.jsp?page=create_vserver.jsp&listServices=<%=listServices%>";
		}

		function displayList() {
			document.location = "site_config_vserver.jsp?listServices=<%=listServices%>";
		}

		function hasSpace(theString) {
			var k = -1
			var j = -1
			j = theString.indexOf(" ");
			if (j != -1) k=1;

			if (k == 1) {
				return true;
			} else {
				return false;
			}
		}

		function hasBadChar(theString) {
			var k = -1
			var j = -1
			j = theString.indexOf("'");
			if (j != -1) k=1;
			j = theString.indexOf('"');
			if (j != -1) k=1;
			j = theString.indexOf("&");
			if (j != -1) k=1;
			j = theString.indexOf("%");
			if (j != -1) k=1;
			j = theString.indexOf("^");
			if (j != -1) k=1;
			j = theString.indexOf("#");
			if (j != -1) k=1;
			j = theString.indexOf("!");
			if (j != -1) k=1;
			j = theString.indexOf("@");
			if (j != -1) k=1;
			j = theString.indexOf("*");
			if (j != -1) k=1;
			j = theString.indexOf("(");
			if (j != -1) k=1;
			j = theString.indexOf(")");
			if (j != -1) k=1;
			j = theString.indexOf("=");
			if (j != -1) k=1;
			j = theString.indexOf(";");
			if (j != -1) k=1;
			j = theString.indexOf("<");
			if (j != -1) k=1;
			j = theString.indexOf(">");
			if (j != -1) k=1;
			j = theString.indexOf(",");
			if (j != -1) k=1;
			j = theString.indexOf("?");
			if (j != -1) k=1;
			j = theString.indexOf("{");
			if (j != -1) k=1;
			j = theString.indexOf("}");
			if (j != -1) k=1;
			j = theString.indexOf("[");
			if (j != -1) k=1;
			j = theString.indexOf("]");
			if (j != -1) k=1;
			j = theString.indexOf("~");
			if (j != -1) k=1;
			j = theString.indexOf("`");
			if (j != -1) k=1;
			j = theString.indexOf("+");
			if (j != -1) k=1;
			j = theString.indexOf("|");
			if (j != -1) k=1;
			if (k == 1) {
				return true;
			} else {
				return false;
			}
		}

		function hasSlash(theString) {
			var k = -1
			var j = -1
			j = theString.indexOf("/");
			if (j != -1) k=1;
			j = theString.indexOf("\\");
			if (j != -1) k=1;
			j = theString.indexOf(":");
			if (j != -1) k=1;
			if (k == 1) {
				return true;
			} else {
				return false;
			}
		}

		function changeType() {
			var f = document.forms[0];
			var index = f.type.selectedIndex;
			document.location = "create_vserver.jsp?type=" + index;
		}

		function createNewContainer() {
			document.location = "create_server.jsp?origPage=newVS";
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
						<table width="100%" cellspacing="0" cellpadding="1" bgcolor="#FFFFFF" border="0">
							<tr> <!-- Service name -->
								<td >
									<table border=0 width=100%>
										<tr>
											<td align="left" valign="top" bgcolor="#FFFFFF" >
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													&nbsp;<%=textList[122]%>
													<p>
												</b></font>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" width="695">
									<table border=0 >
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[70]%>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
												<select name="type" onChange="changeType()">
												<script language="JavaScript" type="text/javascript">
												<!--
													var sel = <%=(selectedTypeIndex)%>;
													for (var i=0;i<types.length;i++) {
													  if (sel == i)
														document.writeln('<option selected value="' + types[i] + '">' + types[i] + '</option>');
													  else
														document.writeln('<option value="' + types[i] + '">' + types[i] + '</option>');
													}
												//-->
												</script>
												</select>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[36]%>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
													<input type="text" name="name" size="40">
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[116]%>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
													<input type="text" name="description" value="" size="40">
											</td>
										</tr>

<%
										// check if there is any free containers for this site, considering all the monitors
										boolean hasFreeContainer = false;
										for (int p=0;p<servers.size();p++) {
											Server theServer = servers.getServer(p);
											String[] containers = theServer.getContainerIDs();
											for (int q=0;q<containers.length;q++) {
												String hasFreeContainerString = theServer.getContainerAttribute(theServer.COMPONENT_ALLOWED, containers[q], selectedElement, selectedVersion);
												if (hasFreeContainerString.equalsIgnoreCase("true")) {
													hasFreeContainer = true;
													break;
												}
											}
										}
										if (isRecyclable) {
%>
										<tr>
											<td bgcolor="#FFFFFF" align="right" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[182]%> <br>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="left" valign="middle">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<select name="frequency" style="font-family: sans-serif; ">
														<option value="1" <% if (startUpFrequency.equalsIgnoreCase("1")) {%> SELECTED <%}%>> <%=textList[196]%>
														<option value="2" <% if (startUpFrequency.equalsIgnoreCase("2")) {%> SELECTED <%}%>> <%=textList[197]%>
														<option value="3" <% if (startUpFrequency.equalsIgnoreCase("3")) {%> SELECTED <%}%>> <%=textList[198]%>
														<option value="4" <% if (startUpFrequency.equalsIgnoreCase("4")) {%> SELECTED <%}%>> <%=textList[199]%>
														<option value="6" <% if (startUpFrequency.equalsIgnoreCase("6")) {%> SELECTED <%}%>> <%=textList[200]%>
														<option value="12" <% if (startUpFrequency.equalsIgnoreCase("12")) {%> SELECTED <%}%>> <%=textList[201]%>
														<option value="24" <% if (startUpFrequency.equalsIgnoreCase("24")) {%> SELECTED <%}%>> <%=textList[202]%>
													</select>
												</b></font>
											</td>
										</tr>
<tr>
	<td bgcolor="#FFFFFF" align="right" valign="middle">
		<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
			<%=textList[204] %> <br>
		</b></font>
	</td>
	<td>
		<table  cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="#FFFFFF" align="left" valign="middle">
				<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
				<select name="hour" style="font-family: sans-serif;">
				<%for (int i = 1; i < 13; i++) { %>
					<option value="<%=i%>" <%if (hourInt == i){%>SELECTED<%}%>> <%if (i < 10) out.println("0" + i); else out.println(i);%>
				<%}%>
				</select>
				</b></font>
				</td>
				<td bgcolor="#FFFFFF" align="left" valign="middle">
					<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b> : </b></font>
				</td>
				<td bgcolor="#FFFFFF" align="left" valign="middle">
					<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
						<select name="minute" style="font-family: sans-serif; ">
							<option value="0" <%if (startUpMinute.equalsIgnoreCase("0")){%>SELECTED<%}%>> 00
							<option value="15" <%if (startUpMinute.equalsIgnoreCase("15")){%>SELECTED<%}%>> 15
							<option value="30" <%if (startUpMinute.equalsIgnoreCase("30")){%>SELECTED<%}%>> 30
							<option value="45" <%if (startUpMinute.equalsIgnoreCase("45")){%>SELECTED<%}%>> 45
						</select>
					</b></font>
				</td>
				<td bgcolor="#FFFFFF" align="left" valign="middle">
					<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b> &nbsp;&nbsp; </b></font>
				</td>
				<td bgcolor="#FFFFFF" align="left" valign="middle">
					<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
						<select name="ampm" style="font-family: sans-serif; ">
							<option value="AM" <%if (isAM){%>SELECTED<%}%>> <%=textList[194]%>
							<option value="PM" <%if (! isAM){%>SELECTED<%}%>> <%=textList[195]%>
						</select>
					</b></font>
				</td>
			</tr>
		</table>
	</td>
</tr>
<%
										} // end of if selected new virtual server is recyclable or is ArcMap
%>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" >
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
											&nbsp;<br><%=textList[123]%><p>
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
													<b><%=textList[108]%></a></b>
												</font>
											</td>
											<td bgcolor="#A5CEF7" width="80">
												<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
													<b><%=textList[97]%></a></b>
												</font>
											</td>
										</tr>
<%
//if (!hasFreeContainer && selectedElement.indexOf("ArcMap") != -1) {
if (!hasFreeContainer) {
%>
	<tr>
		<td background="../images/cyan_line.gif">
		&nbsp;
		</td>
		<td background="../images/cyan_line.gif">
			<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
			<%=textList[209]%>
			</font>
		</td>
		<td background="../images/cyan_line.gif">
		&nbsp;
		</td>
	</tr>
<%
} else {
	for (int i=0;i<servers.size();i++) {
		Server theServer = servers.getServer(i);
		String[] containers = theServer.getContainerIDs();
		for (int j=0;j<containers.length;j++) {
			String serverAllowedForSelectedType = theServer.getContainerAttribute(theServer.COMPONENT_ALLOWED, containers[j], selectedElement, selectedVersion);
			if (serverAllowedForSelectedType.equalsIgnoreCase("true")) {
%>
				<tr>
					<td background="../images/cyan_line.gif">
						<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
							<input type="checkbox" name="selectedServer" value="<%=containers[j]%>" >
						</font>
					</td>
					<td background="../images/cyan_line.gif">
						<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
							<%=containers[j]%>
						</font>
					</td>
<%
				if (isSingleThreaded) {
%>
					<td background="../images/cyan_line.gif">
						<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
							<input type="text" name="instances" size="3" value="1" disabled>
						</font>
					</td>
<%
				} else {
%>
					<td background="../images/cyan_line.gif">
						<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
							<input type="text" name="instances" size="3" value="2">
						</font>
					</td>
<%
				}
			}
%>
				</tr>
<%
		} //end of container loop
	} //end of monitor loop
}
%>
								</table>
								</td>
							</tr>

							<tr >
								<td bgcolor="#FFFFFF" align="center">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<input type="button" name="createContainer" value="<%=textList[187]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="javascript:createNewContainer();">
									</font>
								</td>
							</tr>
							<tr>
							<td>&nbsp;</td>
							</tr>

							<tr>
								<td bgcolor="#FFFFFF" align="center" height="40">
									<input type="button" name="updateButton" value="<%=textList[124]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="chkForm()">
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
<input type="hidden" name="isRecyclable" value="<%=isRecyclable%>">
<input type="hidden" name="isSingleThreaded" value="<%=isSingleThreaded%>">

</form>

</body>
</html>
<% } %>
