<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Service, com.esri.aims.mtier.model.admin.VirtualServer, java.net.URLEncoder, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
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

String index = request.getParameter("index");
int s = Integer.parseInt(index);

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String defaultOutputDir = props.getProperty("OutputDir");
if (defaultOutputDir==null) defaultOutputDir = "C:/ArcIMS/Output";
String defaultOutputURL = props.getProperty("OutputURL");
if (defaultOutputURL==null) defaultOutputURL = "http://localhost/output";
String im = props.getProperty("ImageMemory");
if (im==null) im = "4";
int defaultImageMemory = Integer.parseInt(im);
String cleanup1 = props.getProperty("OutputCleanup");
if (cleanup1==null) cleanup1 = "10";
int defaultCleanup = Integer.parseInt(cleanup1);
String mim = props.getProperty("MaxImageMemory");
if (mim==null) mim = "256";
int maxImageMemory = Integer.parseInt(mim);

String defaultImageType = props.getProperty("ImageType");
if (defaultImageType==null) defaultImageType = "JPG";

String promptSave = request.getParameter("promptSave");
String alwaysSave = request.getParameter("alwaysSave");
if (promptSave==null) session.getAttribute("promptSave");
if (promptSave==null) promptSave = "Yes";
if (alwaysSave==null) session.getAttribute("alwaysSave");
if (alwaysSave==null) alwaysSave = "No";
session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);



String endScript = "";
%>

<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />
<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />
<%
Service theService = serviceList.getService(s);
String vsName = theService.getVirtualServerName();
String vsType = VirtualServer.getVirtualServer(vsName, admin).getType();
%>

<html>
<head>
	<title><%=textList[28]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		var imageType="0";
		var imageMB="4";
		var outdir="<%=URLEncoder.encode(defaultOutputDir)%>";
		var outURL="<%=defaultOutputURL%>";
		var maxImgMemory = <%=maxImageMemory %>;
		var cleanMin="10";
		var vsList = new Array();
		var typeList = new Array();
		var vsCount=0;
		var imgTypeIndex=2;
		var cuIndex=3;
		var pixIndex=4;
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

		function changeService() {
			var f = document.forms[0];
			var index = f.sName.selectedIndex;
			document.location = "serviceprop.jsp?index=" + index;
		}

		function setAMSVS(fnBox,theForm) {
			// BB: check filename for mxd--set VS to ArcMap
			var fName = fnBox.value.toLowerCase();
			var fileExt = fName.substr(fName.length-3);
			var vsName
			if (fileExt=="mxd") {
				 for (var i=0; i<theForm.vserver.length; i++) {
				 		 vsName = theForm.vserver.options[i].text;
					 		 if (vsName.indexOf("ArcMap")>-1) {
						 		theForm.vserver.options.selectedIndex = i;
						 }
				 }
			}
		}

		function chkForm() {
			// check the inputs
			var f = document.forms[0];
			if ((f.filename.value==null) || (f.filename.value=="")) {
				alert("<%=textList[31]%>");
			} else {
				//BB: make sure ArcMap Server for VS only if using mxd
				var fName = f.filename.value.toLowerCase();
				var fileExt = fName.substr(fName.length-3);
				var vsIndex = f.vserver.selectedIndex;
				var isArcMapVS = f.vserver.options[vsIndex].text.indexOf("[ArcMap]") != -1;
				if ((!isArcMapVS && (fileExt !="mxd")) || (isArcMapVS && (fileExt=="mxd")) || (isArcMapVS && (fileExt=="pmf"))) {
					var addIt = true;
					if (!isArcMapVS) {
						if ((fileExt != "axl") && (fileExt != "xml")) {
							addIt = false;
						}
					}
					// if everything seems okay
					if (addIt) {
						f.axlfile.value=f.filename.value.replace(/\\/g, "/");
						f.submit();
					} else {
						alert(f.vserver.value + "<%=textList[205]%>" + fileExt + "'.\n<%=textList[206]%>");
					}
				} else {
					if (isArcMapVS) {
						alert(f.vserver.value + "<%=textList[205]%>" + fileExt + "'.\n<%=textList[207]%>");
					} else {
						alert(f.vserver.value + "<%=textList[205]%>" + fileExt + "'.\n<%=textList[206]%>");
					}
				}
			}
		}

		function saveConfig() {
			document.location = "saveSite.jsp?page=serviceprop.jsp&index=<%=index%>";
		}

		function toggleImageProps() {
			// check the inputs
			var f = document.forms[0];
			var index = f.vserver.selectedIndex;
			var vs = f.vserver.options[index].value;
			var j=0
			for (var i=0;i<vsList.length;i++) {
				if (vsList[i]==vs) j=i;
			}
			if (typeList[j]=="ImageServer") {
				if ((imgTypeIndex!=0) && (f.imagetype.selectedIndex==0)) f.imagetype.selectedIndex = imgTypeIndex; // .jpg
				if ((pixIndex!=0) && (f.pixelcount.selectedIndex==0)) f.pixelcount.selectedIndex = pixIndex; // 4
				if ((f.outfile.value=="- - - -") || (f.outfile.value=="")) f.outfile.value=unescape(outdir);
				if ((f.outurl.value=="- - - -") || (f.outurl.value=="")) f.outurl.value=outURL;
				if ((cuIndex!=0) && (f.cleanup.selectedIndex==0)) f.cleanup.selectedIndex = cuIndex; // 10 min
			} else if (typeList[j]=="MetadataServer") {
				if (f.imagetype.selectedIndex!=0) imgTypeIndex = f.imagetype.selectedIndex;
				f.imagetype.selectedIndex = 0;
				if (f.pixelcount.selectedIndex!=0) pixIndex = f.pixelcount.selectedIndex;
				f.pixelcount.selectedIndex = 0;
				if ((f.outfile.value=="- - - -") || (f.outfile.value=="")) f.outfile.value=unescape(outdir);
				if ((f.outurl.value=="- - - -") || (f.outurl.value=="")) f.outurl.value=outURL;
				if (cuIndex!=0) f.cleanup.selectedIndex = cuIndex; // 10 min
			} else {
				if (f.imagetype.selectedIndex!=0) imgTypeIndex = f.imagetype.selectedIndex;
				f.imagetype.selectedIndex = 0;
				if (f.pixelcount.selectedIndex!=0) pixIndex = f.pixelcount.selectedIndex;
				f.pixelcount.selectedIndex = 0;
				if ((f.outfile.value!="- - - -") && (f.outfile.value!="")) outdir = escape(f.outfile.value);
				f.outfile.value="- - - -";
				if ((f.outurl.value!="- - - -") && (f.outurl.value!="")) outURL = f.outurl.value;
				f.outurl.value="- - - -";
				if (f.cleanup.selectedIndex!=0) cuIndex = f.cleanup.selectedIndex;
				f.cleanup.selectedIndex = 0;
			}
		}

		function getDefaults() {
			var f = document.forms[0];
			cuIndex = f.cleanup.selectedIndex;
			pixIndex = f.pixelcount.selectedIndex;
			toggleImageProps();
		}

		function startService(name,index) {
			document.location = "serviceprop_action.jsp?index=" + index + "&service=" + name + "&serviceMode=start";
		}

		function stopService(name,index) {
			document.location = "serviceprop_action.jsp?index=" + index + "&service=" + name + "&serviceMode=stop";
		}

		function removeService(name,index) {
			document.location = "serviceprop_action.jsp?index=" + index + "&service=" + name + "&serviceMode=remove";
		}

		function displayList() {
			document.location = "services.jsp";
		}

	</script>

</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" onload="getDefaults()">
<form action="process_serviceprop.jsp" method="post" enctype="MULTIPART/FORM-DATA">
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
					<td align="center" valign="middle" width="695" >
						<table width="100%" cellspacing="0" cellpadding="1" bgcolor="#FFFFFF" border="0">
							<tr> <!-- Service name -->
								<td colspan="2">
									<table border=0 width=100%>
										<tr>
											<td align="left" valign="top" bgcolor="#FFFFFF" >
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													&nbsp;<%=textList[56]%>
													<select name="sName" style="color: #000099; font-weight: bold; font-family: sans-serif; " onChange="changeService()">
														<% for (int i=0;i<serviceList.size();i++) {
														%>
														<option value="<%=i%>"
														<% if (i==s) { %>
														selected
														<% } %>
														><%=serviceList.getService(i).getName()%>
														<% } %>

													</select>
													<p>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" align="right" valign="top" >
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
													<%=textList[57]%>
												<%
												if (theService.isRunning()) {
												%>
												<img src="../images/TransGear.gif" width="20" align="middle" height="20" hspace="0" vspace="0" border="0" alt="<%=textList[60]%>">
												<%=textList[60]%> &nbsp;<input type="button" name="stopButton" value="<%=textList[58]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="stopService('<%=theService.getName()%>',<%=index%>)">
												<%
												} else  {
												%>
												<img src="../images/GearStop.gif" width="20" align="middle" height="20" hspace="0" vspace="0" border="0" alt="<%=textList[61]%>">
												<%=textList[61]%> &nbsp;<input type="button" name="startButton" value="<%=textList[59]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="startService('<%=theService.getName()%>',<%=index%>)">
												<% } %>
												&nbsp;
												</b></font>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[37]%>
									</b></font>
								</td>
								<td bgcolor="#FFFFFF" width="500">
									<select name="vserver" onchange="toggleImageProps()">
										<%
											int pvsCount = 0;
											for (int i=0;i<vServers.size();i++) {
												String vs = vServers.getVirtualServer(i).getName();
												String vt = VirtualServer.getVirtualServer(vs, admin).getType();
												String acc = VirtualServer.getVirtualServer(vs, admin).getAccessString();
												if (acc.equals("PUBLIC")) {
													int pos = vt.indexOf("Server");
													String vv = vt.substring(0,pos);
													//String vv = "!";
													if (vt.indexOf("Image")!=-1) {
														if (VirtualServer.getVirtualServer(vs, admin).getVersion().indexOf("ArcMap")!=-1) {
															vv += " [ArcMap]";
														}
													}
													endScript += "vsList[" + pvsCount + "] = '" + vs + "';\n";
													endScript += "typeList[" + pvsCount + "] = '" + vt + "';\n";
													pvsCount++;
										%>
										<option value="<%=vs%>"
										<% if (vs.equals(vsName)) {%> selected <%}%>
											> <%=vs%> - <%=vv%>
										<%		}
											}
										%>

									</select>
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[62]%>
								</td>
								<td bgcolor="#FFFFFF" width="500">
										<input type="file" name="filename" size="40" onchange="setAMSVS(this,this.form)"><br>
								</td>

							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="center" colspan="2">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										<%=textList[63]%>&nbsp;<%=theService.getConfigFile()%>
									</b></font>
								</td>

							</tr>

							<%
							//if (vsType.indexOf("Image")!=-1) { %>
							<!-- Image services only -->
							<tr>
								<td colspan="2" align="center">
									<hr width="80%" color="#000099">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										<%=textList[40]%>
									</b></font>
								</td>

							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[41]%>
									</b></font>
								</td>
								<td bgcolor="#FFFFFF" width="500">
										<% 	String outDir = "";
											//if (!vsType.equals("MetadataServer"))
												outDir = theService.getOutputDir();
											if (outDir==null) outDir = "";
										%>
										<input type="text" name="outfile" value="<%=outDir%>" size="40">
								</td>

							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[42]%>
									</b></font>
								</td>
								<td bgcolor="#FFFFFF" width="500">
										<% 	String outURL = "";
											//if (!vsType.equals("MetadataServer"))
												outURL = theService.getOutputURL();
											if (outURL==null) outURL = "";
										%>
										<input type="text" name="outurl" value="<%=outURL%>" size="40">
								</td>

							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[43]%>
									</b></font>
								</td>
								<td bgcolor="#FFFFFF" width="500">
										<% String cleanup = "";
											if ((vsType.indexOf("Image")!=-1) || (vsType.indexOf("Metadata")!=-1)) cleanup = theService.getOutputCleanupString();
											if (cleanup==null) cleanup = "";
										%>
										<select name="cleanup">
											<option value="0" <% if (cleanup.equals("0")) {%>selected<%}%>><%=textList[44]%>
											<option value="5" <% if (cleanup.equals("5")) {%>selected<%}%>><%=textList[45]%> 5 <%=textList[46]%>
											<option value="10" <% if (cleanup.equals("10")) {%>selected<%}%>><%=textList[45]%> 10 <%=textList[46]%>
											<option value="15" <% if (cleanup.equals("15")) {%>selected<%}%>><%=textList[45]%> 15 <%=textList[46]%>
											<option value="20" <% if (cleanup.equals("20")) {%>selected<%}%>><%=textList[45]%> 20 <%=textList[46]%>
											<option value="25" <% if (cleanup.equals("25")) {%>selected<%}%>><%=textList[45]%> 25 <%=textList[46]%>
											<option value="30" <% if (cleanup.equals("30")) {%>selected<%}%>><%=textList[45]%> 30 <%=textList[46]%>
											<option value="45" <% if (cleanup.equals("45")) {%>selected<%}%>><%=textList[45]%> 45 <%=textList[46]%>
											<option value="60" <% if (cleanup.equals("60")) {%>selected<%}%>><%=textList[47]%>
											<option value="120" <% if (cleanup.equals("120")) {%>selected<%}%>><%=textList[45]%> 2 <%=textList[48]%>
											<option value="180" <% if (cleanup.equals("180")) {%>selected<%}%>><%=textList[45]%> 3 <%=textList[48]%>
											<option value="240" <% if (cleanup.equals("240")) {%>selected<%}%>><%=textList[45]%> 4 <%=textList[48]%>
											<option value="360" <% if (cleanup.equals("360")) {%>selected<%}%>><%=textList[45]%> 6 <%=textList[48]%>
											<option value="480" <% if (cleanup.equals("480")) {%>selected<%}%>><%=textList[45]%> 8 <%=textList[48]%>
											<option value="720" <% if (cleanup.equals("720")) {%>selected<%}%>><%=textList[49]%>
											<option value="1440" <% if (cleanup.equals("1440")) {%>selected<%}%>><%=textList[50]%>
										</select>
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[51]%>
									</b></font>
								</td>
								<td bgcolor="#FFFFFF" width="500">
									<% String iType = "";
										//if (!vsType.equals("MetadataServer"))
											iType = theService.getImageTypeString();
										if (iType==null) iType = "";
									%>

									<select name="imagetype">
										<option value="0">- - - -
										<option value="1"
											<% if (iType.equals("GIF")) { %>
												selected
											<% } %>
											>GIF (.GIF)
										<option value="0"
											<% if (iType.equals("JPG")) { %>
												selected
											<% } %>
										>JPEG (.JPG)
										<option value="3"
											<% if (iType.equals("PNG8")) { %>
												selected
											<% } %>
										>PNG8 - 8 Bit (.PNG)
										<option value="2"
											<% if (iType.equals("PNG")) { %>
												selected
											<% } %>
										>PNG - 24 Bit (.PNG)
									</select><font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b> <%=textList[52]%>
									</b></font>
								</td>

							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="right" width="195">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[53]%>
									</b></font>
								</td>
								<td bgcolor="#FFFFFF" width="500">

										<% 	//String pix = theService.getPixelCountString();
											int pix = 0;
											//if (vsType.indexOf("Image")!=-1)
												pix = (theService.getPixelCount()/262144);

										%>
										<select name="pixelcount">
											<option value="0">- - - -
											<%

											int pixMb = 262144;
											for (int i=1;i < 17;i++) {
												int totpix = pixMb * i;
												double totpix2 = pixMb * i;
												Double incred = new Double(Math.sqrt(totpix2));
												int increpix = incred.intValue();
												%>
												<option value="<%=i%>"<% if (i==pix) {%> selected<%}%>><%=i%> <%=textList[54]%><%=totpix %> (<%=increpix%> x <%=increpix%>)
											<%}
											if (maxImageMemory > 16) {
												for (int i=24;i < 65;i=i+8) {
													if (i <= maxImageMemory) {
														int totpix = pixMb * i;
														double totpix2 = pixMb * i;
														Double incred = new Double(Math.sqrt(totpix2));
														int increpix = incred.intValue();
														%>
														<option value="<%=i%>"<% if (i==pix) {%> selected<%}%>><%=i%> <%=textList[54]%><%=totpix %> (<%=increpix%> x <%=increpix%>)
													<%}
												}
											}
											if (maxImageMemory > 64) {

												// coded maximum here is 256.... change if more is allowed
												for (int i=80;i < 257;i=i+16) {
													if (i <= maxImageMemory) {
														int totpix = pixMb * i;
														double totpix2 = pixMb * i;
														Double incred = new Double(Math.sqrt(totpix2));
														int increpix = incred.intValue();
														%>
														<option value="<%=i%>"<% if (i==pix) {%> selected<%}%>><%=i%> <%=textList[54]%><%=totpix %> (<%=increpix%> x <%=increpix%>)
													<%}
												}
											}
											%>
										</select><font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b> <%=textList[52]%>
									</b></font>
								</td>

							</tr>
							<!-- end Image service settings -->
							<%// } %>

							<tr>
								<td bgcolor="#FFFFFF" align="left" width="195">
									<img src="../images/pixel.gif" width="5" height="25" hspace="0" vspace="0" border="0" alt="">
								</td>
								<td bgcolor="#FFFFFF" align="left" height="40">

									<input type="button" name="updateButton" value="<%=textList[64]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="chkForm()">
									&nbsp;&nbsp;&nbsp;<input type="button" name="listButton" value="<%=textList[152]%>" style="background-color: #EFEFEF; color: #000099;" onclick="displayList()">
									<!-- &nbsp;&nbsp;
									<input type="button" name="removeButton" value="Remove" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="removeService('<%=theService.getName()%>',<%=index%>)">
									-->&nbsp;
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

<input type="hidden" name="service" value="<%=theService.getName()%>">
<input type="hidden" name="index" value="<%=index%>">
<input type="hidden" name="serviceMode" value="">
<input type="hidden" name="checkList" value="">

<% String status = "Stopped";
	if (theService.isRunning()) status = "Started";
%>
<input type="hidden" name="status" value="<%=status%>">
<input type="hidden" name="axlfile" value="">
</form>
<script language="JavaScript" type="text/javascript">
	<%=endScript%>
	vsCount=vsList.length;
</script>
</body>
</html>
<% } %>
