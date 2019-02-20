<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ include file="../textList.jsp" %>

<html>
<head>
	<title><%=textList[23]%> - <%=textList[0]%></title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="" method="post">
<table width="700" cellspacing="0" cellpadding="0" border=0>
	<tr> <!-- Title Heading -->
		<td align="left">
			
			<font face="Arial,Helvetica,sans-serif" size="+3">
				<b>&nbsp;&nbsp;<%=textList[0]%></b>
			</font>
			<font size="-1"><br>&nbsp;</font>
		</td>
	</tr>
	
	<tr> <!-- Tabs -->
		<td align="left">
			<table cellspacing="0" cellpadding="0" border="0">
				<tr>
					<TD WIDTH="520" BACKGROUND="../images/NoTab.gif"><img src="../images/pixel.gif" width="520" height="5" hspace="0" vspace="0" border="0" alt=""></TD>
					<TD WIDTH="175" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="../images/Tab_selected.gif"><font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b><%=textList[24]%></b></font></TD>
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
						<TABLE border="0" cellspacing="0" cellpadding="0" width="695" height="300">
							<TR>
								<TD ALIGN="center" VALIGN="middle">
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										<%=textList[25]%>
										<%=textList[26]%><p>
										<%=textList[27]%><p>
										<form>
											<input type="button" name="goLogin" value="<%=textList[3]%>" onclick="document.location='../default.htm'">
										</form>
										
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

</form>
</body>
</html>

