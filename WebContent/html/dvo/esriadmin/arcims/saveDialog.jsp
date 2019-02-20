<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="java.lang.*, com.esri.aims.mtier.io.ConnectionProxy, com.esri.aims.mtier.model.admin.Site, java.util.Properties" %>
<%@ taglib uri="http://www.esri.com/arcims/javaconnector" prefix="aims" %>
<%@ include file="../textList.jsp" %>

<%
ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
if (admin==null) {
%>
<jsp:include page="timeout.jsp" flush="true"/>
<% } else {
String serviceMode = request.getParameter("serviceMode");
if (serviceMode==null) serviceMode = "";
String index = request.getParameter("index");
if (index==null) index = "";
String backURL = request.getParameter("page");
if (backURL==null) backURL = "";
String service = request.getParameter("service");
if (service==null) service = "";
String bURL = backURL;
if (index!="") {
	bURL += "?index=" + index;
}
Site site = new Site();
Properties props = site.getSiteProperties(admin);

String promptSave = props.getProperty("PromptSave");
if (promptSave==null) promptSave = "Yes";
String alwaysSave = props.getProperty("AlwaysSave");
if (alwaysSave==null) alwaysSave = "No";

session.setAttribute("promptSave",promptSave);
session.setAttribute("alwaysSave",alwaysSave);

%>
<html>
<head>
	<title><%=textList[34]%> - <%=textList[0]%></title>
	<script language="JavaScript" type="text/javascript">
		//alert("<%=bURL %>");
		function doIt(n) {
			var f = document.forms[0];
			if (f.pS.checked) f.promptSave.value = "No"
				else f.promptSave.value = "Yes";
			if (f.aS.checked) f.alwaysSave.value = "Yes";
				else f.alwaysSave.value = "No";
			if (n==1) {
				f.action = "saveSite.jsp";
				f.submit();
			} else {
				f.action = "notSave.jsp";
				f.submit();
			}

		}

	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="saveSite.jsp" method="post">
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
					<TD WIDTH="520" BACKGROUND="../images/NoTab.gif"><img src="../images/pixel.gif" width="520" height="5" hspace="0" vspace="0" border="0" alt=""></TD>
					<TD WIDTH="175" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="../images/Tab_selected.gif"><font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b><%=textList[34]%></b></font></TD>
					<td width="5"><img src="../images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt=""></td>
				</tr>
				</tr>
					<TD WIDTH="695" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="../images/Bar.gif" colspan="2"><font face="Arial,Helvetica,sans-serif" size="-2" color="#000099"><b>&nbsp;</b></font></TD>
					<td width="5" height="35" align="center" valign="middle" background="../images/toprightedge.gif"><img src="../images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt=""></td>
				<tr>
			</table>
		</td>
	</tr>

	<tr> <!-- Main Body -->
		<td>
			<table border="0" cellspacing="0" cellpadding="0" bgcolor="White" width=100%>
				<tr> <!-- white box -->
					<td align="center" valign="middle" width="695">	<!-- main body -->
						<TABLE border="0" cellspacing="0" cellpadding="0" width="650" height="200">
							<TR>
								<TD ALIGN="center" VALIGN="middle" colspan="4">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[162]%>
									</b></font>
								</TD>
							</TR>
							<TR>
								<TD ALIGN="center" VALIGN="middle" colspan="4">

									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[163]%><br>
										<input type="button" name="yesButton" value="<%=textList[164]%>" style="background-color: #EFEFEF; color: #000099;" onclick="doIt(1)">
										&nbsp;&nbsp;
										<input type="button" name="noButton" value="<%=textList[165]%>" style="background-color: #EFEFEF; color: #000099;" onclick="doIt(0)">
									</b></font>
								</TD>
							</TR>
							<TR>
								<TD ALIGN="right" VALIGN="top" width="10%">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										<input type="checkbox" name="pS" value="Yes" <%if (promptSave.equals("No")) { %>checked<%} %>>
									</b></font>
								</TD>
								<TD ALIGN="left" VALIGN="top" width="30%">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										<%=textList[166]%>
									</b></font>
								</TD>
								<TD ALIGN="right" VALIGN="top" width="10%">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										<input type="checkbox" name="aS" value="Yes" <%if (alwaysSave.equals("Yes")) { %>checked<%} %>>
									</b></font>
								</TD>
								<TD ALIGN="left" VALIGN="top" width="50%">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-2"><b>
										<%=textList[167]%>
									</b></font>
								</TD>
							</TR>

						</TABLE>
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
<input type="hidden" name="index" value="<%=index%>">
<input type="hidden" name="serviceMode" value="<%=serviceMode%>">
<input type="hidden" name="page" value="<%=backURL%>">
<input type="hidden" name="service" value="<%=service%>">
<input type="hidden" name="promptSave" value="<%=promptSave%>">
<input type="hidden" name="alwaysSave" value="<%=alwaysSave%>">


</form>
</body>
</html>
<%} %>