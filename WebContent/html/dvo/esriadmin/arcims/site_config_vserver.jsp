<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, java.util.StringTokenizer" %>
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

//set the currect viewing config to session
String currView = "VirtualServer";
session.setAttribute("currentView", currView);

String configMode = request.getParameter("configMode");
if (configMode==null) configMode = (String)session.getAttribute("configMode");
if ((configMode==null) || (configMode=="")) configMode = "vserver";

String listServices = request.getParameter("listServices");
if (listServices==null) listServices = "No";

int s = -1;
//String vsType = "";
//String vsName = "";

configPic = "Tab_selected.gif";
%>

<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />
<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />

<html>
	<title><%=textList[0]%> <%=textList[86]%></title>
	<script language="JavaScript" type="text/javascript">
		var vssList = new Array();
		var noRemoveString = "";
		var nList = "";
		var vsList = new Array();
		<% for (int j=0;j<vServers.size();j++) { %>
		vsList[<%=j%>] = "<%=vServers.getVirtualServer(j).getName()%>";
		<% } %>

		function saveConfig() {
			document.location = "saveSite.jsp?page=site_config_vserver.jsp&configMode=<%=configMode%>&listServices=<%=listServices%>";
		}

		function removeChecked() {
			var f = document.forms[0];
			f.action = "remove_vserver.jsp";

			if (getChoices()>0) {
				if (noRemoveString=="") {
					var msg = "<%=textList[87]%>\n\n" + nList + "\n<%=textList[67]%>";
					if (confirm(msg)) {
						f.submit();
					}
				} else {
					var msg = "<%=textList[88]%>\n<%=textList[89]%>\n\n" + noRemoveString;
					alert(msg);
				}
			} else {
				alert("<%=textList[90]%>");
			}
		}

		function getChoices() {
			var f = document.forms[0];
			var str1 = "";
			nList = "";
			noRemoveString = "";
			var k = 0;

			if (f.selectVS!=null) {
				if (f.selectVS.length) {
					for (var i=0;i<f.selectVS.length;i++) {
						if (f.selectVS[i].checked) {
							str1 += "1";
							var n1 = parseInt(f.selectVS[i].value);
							nList += vsList[n1] + "\n";
							if (vssList[n1]!="") {

								noRemoveString += vsList[n1] + ":\n" + vssList[n1] + "\n";

							}
							//alert(vsList[n1] + "\n\n" + vssList[n1]);
							k++;
						} else {
							str1 += "0";
						}
					}
				} else {
					if (f.selectVS.checked) {
						str1 = "1";
						nList += vsList[parseInt(f.selectVS.value)] + "\n";
						var n1 = parseInt(f.selectVS.value);
						if (vssList[n1]!="") {
							noRemoveString += vsList[n1] + ":\n" + vssList[n1] + "\n";

						}
						//alert(vsList[n1] + "\n\n" + vssList[n1]);
						k++;
					} else {
						str1 = "0";
					}
				}
			}
			//alert(nList);
			return k;
		}

		function addNewVirtualServer() {
			document.location = "create_vserver.jsp?listServices=<%=listServices%>";
		}

		function toggleListServices() {
			<%
			String sw = "Yes";
			if (listServices.equals("Yes")) {
				sw = "No";
			}	%>
			document.location = "site_config_vserver.jsp?configMode=<%=configMode%>&listServices=<%=sw%>";
		}

		function switchPage() {
			var f = document.forms[0];
			var index = f.theSType.selectedIndex;
			var url = f.theSType.options[index].value;
			document.location = url;
		}

		function checkBoxChange(n) {
			alert("<%=textList[211]%>");
			var f = document.forms[0];
			if (f.selectVS.length>1) { // there are more than one virtual server on this site
				f.selectVS[n].checked = false;
			} else {
				f.selectVS.checked = false;
			}
		}

	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="config_vs.jsp" method="post">
<table width="705" cellspacing="0" cellpadding="0" border="0">
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
					<td><!-- page contents -->
						<table cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<td align="left" valign="top" colspan="3">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										&nbsp;<br>
										&nbsp;<%=textList[188]%>
										</font>
										<select name="theSType" style="color: #000099; font-family: sans-serif; font-weight: bold;" onChange="switchPage()">
											<option value="site_config_vserver.jsp" SELECTED><%=textList[80]%>
											<option value="site_config_server.jsp"><%=textList[81]%>
										</select>
										</b>
								</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr> <!-- Start/Stop buttons -->
								<td height="35" colspan="3" bgcolor="White">
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										&nbsp;
										<% if (vServers.size()>0) { %>
										&nbsp;
										<input type="button" name="removeButton" value="<%=textList[68]%>" style="background-color: #EFEFEF; color: #000099;" onclick="removeChecked()">
										&nbsp;<%=textList[91]%>
										<% } else { %>
										<%=textList[92]%><br>
										<% } %>
								</td>
								<td align="right" colspan="3">
									<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099">
										<input type="button" name="addButton" value="<%=textList[93]%>" style="background-color: #EFEFEF; color: #000099;" onclick="addNewVirtualServer()">
										&nbsp;
									</font>
								</td>
							</tr>
							<% if (vServers.size()>0) { %>


							<tr> <!-- Headings -->
								<td bgcolor="#A5CEF7" width="20" valign="top" align="center">
									&nbsp;
								</td>
								<td bgcolor="#A5CEF7" >
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[94]%>
								</b></font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[95]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><a href="javascript:toggleListServices()"><%=textList[96]%></a></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[108]%></b>
									</font>
								</td>
								<td bgcolor="#A5CEF7" width="65">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<b><%=textList[97]%></b>
									</font>
								</td>
							</tr>
							<%
							int i=0;
							%>

								<aims:iterateVirtualServers virtualServers="<%=vServers%>" count="serviceCount" >
								<tr>
									<aims:getVirtualServerAttribute id="vsName" attribute="NAME" />
									<aims:getVirtualServerAttribute id="vsType" attribute="TYPE" />
									<aims:getVirtualServerAttribute id="version" attribute="VERSION" />
									<aims:getVirtualServerAttribute id="containerSet" attribute="CONTAINERSET" />
									<td background="../images/gray_line.gif" width="20" valign="top" align="center">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<%
												if ((vsName.equals("ImageServer1")) || (vsName.equals("FeatureServer1")) || (vsName.equals("QueryServer1")) || (vsName.equals("GeocodeServer1")) || (vsName.equals("ExtractServer1")) || (vsName.equals("MetadataServer1")) || (vsName.equals("ImageServerArcMap1")) || (vsName.equals("RouteServer1"))) {
											%>
													<!--- <IMG SRC="../images/noselectbox.gif" WIDTH=16 HEIGHT=17 HSPACE=0 VSPACE=0 BORDER=0 ALT=""> --->
													<input type="checkbox" disabled name="selectVS" value="<%=i%>" onClick="checkBoxChange(<%=i%>)">
											 <% } else { %>
													<input type="checkbox" name="selectVS" value="<%=i%>">
											 <% } %>
										</b></font>
									</td>
									<td background="../images/gray_line.gif" valign="top">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
											<a href="vserverprop.jsp?index=<%=i%>&listServices=<%=listServices%>"><%=vsName%></a>
										</b></font>
									</td>
									<td background="../images/gray_line.gif" valign="top">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
											<%=vsType%>
											<% if (!version.equals("")) { %>
											 [<%=version%>]
											<% } %>

										</font>
									</td>
									<td background="../images/gray_line.gif" valign="top">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
											<%  int scount = 0;
												String sl = "";
												String sl2 = "";
												for (int j=0;j<serviceList.size();j++) {
													if (serviceList.getService(j).getVirtualServerName().equals(vsName)) {
														scount++;
														sl += serviceList.getService(j).getName() + "<br>";
														sl2 += serviceList.getService(j).getName() + "\\n";
													}
												}
												%>
												<script language="JavaScript" type="text/javascript">
													vssList[<%=i%>] = "<%=sl2%>";
												</script>
												<%
												i++;
												if (listServices.equals("Yes")) {
													if (scount>0) {%>
													<%=sl%>
													<% 	} else { %>
													- - - - -
													<% }
												} else { %>
													<%=scount%>
											<% 	}
											%>
										</font>
									</td>
									<%
										StringTokenizer stok = new StringTokenizer(containerSet,",");
										String containerString = "";
										String threadString = "";
										String strA[] = new String[stok.countTokens()]; // array for container name in containerset for the currect virtual server
										String strB[] = new String[stok.countTokens()]; // array for thread count for the corresponding container in strA.
										int k = 0;
										String theTok = "";
										while(stok.hasMoreTokens()){
											theTok = stok.nextToken();
											strA[k] = theTok.substring(0,theTok.indexOf(" "));
											strB[k++] = theTok.substring(theTok.indexOf(" "));
										}
									%>
									<td background="../images/gray_line.gif" valign="top">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%for (int m=0; m<strA.length; m++) {
											containerString = strA[m];
										%>
											<!--- introduce containerName request parameter to indicate to serverprop.jsp that
											the request is from virtual server page --->
											<a href="serverprop.jsp?containerName=<%=containerString%>"><%=containerString%></a><br>
										<%
										}
										%>
										</font>
									</td>
									<td background="../images/gray_line.gif" valign="top">
										<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1">
										<%for (int n=0; n<strB.length; n++) {
											threadString = strB[n];
										%>
											<%=threadString%><br>
										<%
										}
										%>
										</font>
									</td>
									</tr>
							</aims:iterateVirtualServers>

							<% } %>

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
