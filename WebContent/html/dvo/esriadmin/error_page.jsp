<%@ page language="java" buffer="8kb"  %>
<%@ page isErrorPage="true" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy" %>

<html>
<head>
	<title>Error - Administrator</title>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<form action="" method="post">
<table width="700" cellspacing="0" cellpadding="0" border=0>
	<tr> <!-- Title Heading -->
		<td align="left">
			
			<font face="Arial,Helvetica,sans-serif" size="+3">
				<b>&nbsp;&nbsp;Administrator</b>
			</font>
			<font size="-1"><br>&nbsp;</font>
		</td>
	</tr>
	
	<tr> <!-- Tabs -->
		<td align="left">
			<table cellspacing="0" cellpadding="0" border="0">
				<tr>
					<TD WIDTH="520" BACKGROUND="../images/NoTab.gif"><img src="../images/pixel.gif" width="520" height="5" hspace="0" vspace="0" border="0" alt=""></TD>
					<TD WIDTH="175" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="../images/Tab_selected.gif"><font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>Error</b></font></TD>
					<td width="5"><img src="../images/pixel.gif" width="5" height="5" hspace="0" vspace="0" border="0" alt=""></td>
				</tr>
				</tr>
					<TD WIDTH="695" HEIGHT="35" ALIGN="center" VALIGN="middle" BACKGROUND="../images/Bar.gif" colspan="2"><font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>&nbsp;</b></font></TD>
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
										An error has occurred.<p>To get back to the previous page, click the Back button on your browser.<p>
										Returned Error Message:<br>
									</b></font>
										<form>											
											<textarea cols="70" rows="10" name="errorStuff" >
<%
	//exception.printStackTrace(new java.io.PrintWriter(out)) ; 
	if (exception instanceof com.esri.aims.mtier.model.axl.ErrorAndWarningException) {
		com.esri.aims.mtier.model.axl.ErrorAndWarningException eaw = (com.esri.aims.mtier.model.axl.ErrorAndWarningException) exception;
		if (eaw.hasErrors()) {
			for (int i = 0; i < eaw.getErrorCount(); i ++) {
				out.println(eaw.getErrorMessageAt(i));
		   	}
		}

                // Check for warning messages not contained in ErrorAndWarningException
                ConnectionProxy admin = (ConnectionProxy)session.getAttribute("adminConnect");
                if (admin != null) {
                    com.esri.aims.mtier.model.axl.admin.Warnings  warnings = admin.getWarnings();
                    if (warnings != null) {
                        for (int i=0; i < warnings.getWarningCount(); i++)
                                out.println("\n" + warnings.getWarningMessageAt(i));     
                    }
                }
    } else {
		exception.printStackTrace(new java.io.PrintWriter(out)) ;
	}
%>
											</textarea>
										</form>
										
									
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

