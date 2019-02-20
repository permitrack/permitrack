<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
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
String configURL = "site_config.jsp";
session.setAttribute("configURL", configURL);


String configMode = request.getParameter("configMode");
if (configMode==null) configMode = (String)session.getAttribute("configMode");
if ((configMode==null) || (configMode=="")) configMode = "prop";
int s = -1;
String vsType = "";
String vsName = "";

Site site = new Site();
Properties props = site.getSiteProperties(admin);

String defaultOutputDir = props.getProperty("OutputDir");
if (defaultOutputDir==null) defaultOutputDir = "C:/ArcIMS/Output";
String defaultOutputURL = props.getProperty("OutputURL");
if (defaultOutputURL==null) defaultOutputURL = "http://localhost/output";
String im = props.getProperty("ImageMemory");
if (im==null) im = "4";
int defaultImageMemory = Integer.parseInt(im);
String cleanup = props.getProperty("OutputCleanup");
if (cleanup==null) cleanup = "10";
String mim = props.getProperty("MaxImageMemory");
if (mim==null) mim = "256";
int maxImageMemory = Integer.parseInt(mim);

String defaultImageType = props.getProperty("ImageType");
if (defaultImageType==null) defaultImageType = "JPG";
String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";

configPic = "Tab_selected.gif";
%>

<html>
	<title><%=textList[0]%> <%=textList[79]%></title>
	<script language="JavaScript" type="text/javascript">
		var maxImgMemory = <%=maxImageMemory %>;
		function saveConfig() {
			document.location = "saveSite.jsp?page=site_config.jsp";
		}
		function sendForm() {
			document.forms[0].submit();
		}
	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="process_properties.jsp" method="post">
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
											<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
												&nbsp;&nbsp;<!-- <a href="site_config.jsp"> --><%=textList[79]%><!-- </a> -->
											</b></font>
										</TD>
										<TD  align="center">
											<font face="Arial,Helvetica,sans-serif"  color="#000099">
												&nbsp;&nbsp;|&nbsp;&nbsp;
											</font>
										</TD>
										<TD align="center">
											<font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>
												<a href="<%=aimsServerURL%>"><%=textList[117]%></a>
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
				</tr>
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
								<td align="center">
									<table cellspacing="2" cellpadding="1" width="100%">
										<tr>
											<td align="left" valign="top" colspan="2">
												<font face="Arial,Helvetica,sans-serif" color="#000099"><b>
													&nbsp;<%=textList[82]%><br>&nbsp;
												</b></font>
											</td>
										</tr>
										<tr>
											<td align="right">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[83]%>&nbsp;
												</b></font>
											</td>
											<td align="left">
													<input type="text" name="OutputDir" value="<%=defaultOutputDir%>" size="40">
											</td>
										</tr>
										<tr>
											<td align="right">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[42]%>&nbsp;
												</b></font>
											</td>
											<td align="left">
													<input type="text" name="OutputURL" value="<%=defaultOutputURL%>" size="40">
											</td>
										</tr>
										<tr>
											<td align="right">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[43]%>&nbsp;
												</b></font>
											</td>
											<td align="left">
												<select name="Cleanup">
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
											<td align="right">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[84]%>&nbsp;
												</b></font>
											</td>
											<td align="left">
												<select name="ImageMemory">
												<script language="JavaScript" type="text/javascript">
													var pixMb = 262144;
													for (var i=1;i < 17;i++) {
														var totpix = pixMb * i;
														var increpix = parseInt(Math.sqrt(totpix));
														document.write("<option value=" + i + " ");
														if (i==<%=defaultImageMemory%>) document.write("selected");
														document.writeln(">" + i + " <%=textList[54]%>" + totpix + " (" + increpix + " x " + increpix + ")");
													}
													<% if (maxImageMemory > 16) { %>
													for (var i=24;i < 65;i=i+8) {
														if (i <= maxImgMemory) {
															var totpix = pixMb * i;
															var increpix = parseInt(Math.sqrt(totpix));
															document.write("<option value=" + i + " ");
															if (i==<%=defaultImageMemory%>) document.write("selected");
															document.writeln(">" + i + " <%=textList[54]%>" + totpix + " (" + increpix + " x " + increpix + ")");
														}
													}
													<% }
													   if (maxImageMemory > 64) {
													 %>
													// coded maximum here is 256.... change if more is allowed
													for (var i=80;i < 257;i=i+16) {
														if (i <= maxImgMemory) {
															var totpix = pixMb * i;
															var increpix = parseInt(Math.sqrt(totpix));
															document.write("<option value=" + i + " ");
															if (i==<%=defaultImageMemory%>) document.write("selected");
															document.writeln(">" + i + " <%=textList[54]%>" + totpix + " (" + increpix + " x " + increpix + ")");
														}
													}
													<% } %>
												</script>
												</select>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" width="195">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[168]%>
												</b></font>
											</td>
											<td bgcolor="#FFFFFF" width="500">
												<select name="ImageType">
													<option value="GIF"
														<% if (defaultImageType.equals("GIF")) { %>
															selected
														<% } %>
														>GIF (.GIF)
													<option value="JPG"
														<% if (defaultImageType.equals("JPG")) { %>
															selected
														<% } %>
													>JPEG (.JPG)
													<option value="PNG8"
														<% if (defaultImageType.equals("PNG8")) { %>
															selected
														<% } %>
													>PNG8 - 8 Bit (.PNG)
													<option value="PNG"
														<% if (defaultImageType.equals("PNG")) { %>
															selected
														<% } %>
													>PNG - 24 Bit (.PNG)
												</select>
											</td>

										</tr>
										<tr>
											<td align="right">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[169]%>&nbsp;
												</b></font>
											</td>
											<td align="left">
													<select name="PromptSave">
														<option value="<%=textList[171]%>" <%if (promptSave.equals("Yes")) { %>SELECTED<%} %>><%=textList[164]%>
														<option value="<%=textList[172]%>" <%if (promptSave.equals("No")) { %>SELECTED<%} %>><%=textList[165]%>
													</select>
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
													&nbsp;<%=textList[173]%>
												</b></font>
											</td>
										</tr>
										<tr>
											<td align="right">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
													<%=textList[170]%>&nbsp;
												</b></font>
											</td>
											<td align="left">
													<select name="AlwaysSave">
														<option value="<%=textList[171]%>" <%if (alwaysSave.equals("Yes")) { %>SELECTED<%} %>><%=textList[164]%>
														<option value="<%=textList[172]%>" <%if (alwaysSave.equals("No")) { %>SELECTED<%} %>><%=textList[165]%>
													</select>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" align="right" >
											<img src="../images/pixel.gif" width="5" height="25" hspace="0" vspace="0" border="0" alt="">
											</td>
											<td bgcolor="#FFFFFF" align="left" height="40">

												<input type="button" name="updateButton" value="<%=textList[64]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="sendForm()">
												<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
													&nbsp;<%=textList[174]%>
												</b></font>
											</td>

										</tr>
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
