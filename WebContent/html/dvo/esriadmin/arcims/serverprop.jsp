<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy, java.net.URL, com.esri.aims.mtier.model.admin.VirtualServer, com.esri.aims.mtier.model.admin.ServerCollection, com.esri.aims.mtier.model.admin.Server, java.util.StringTokenizer" %>
<%@ page import="java.util.Hashtable, java.util.Enumeration, java.util.Vector" %>
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

String index = request.getParameter("index"); // this the value of the user picked option from the container dropdown list

String cName = request.getParameter("containerName"); // this is for request from site_config_vserver.jsp

configPic = "Tab_selected.gif";

ServerCollection servers = ServerCollection.getServers(admin);

%>

<aims:getStartupParameter id="singleThreadedServers" connectionId="<%=admin%>" parameter="singlethreadedservers" error="errorSingleTh" />
<%
// construct the array of singleThreadedServers
StringTokenizer stok2 = new StringTokenizer(singleThreadedServers,",");
Vector vecSingleThreadServer = new Vector();
while (stok2.hasMoreTokens()) {
	String tokString2  = stok2.nextToken();
	vecSingleThreadServer.add(tokString2);
}
%>

<aims:getVirtualServers id="vServers" connectionId="<%=admin%>" error="vsError"  />
<aims:getServices id="serviceList" connectionId="<%=admin%>" error="connectError"  />
<%
// make a type array of all the virtual server instances on this site
int vServerSize = vServers.size();
String[] typeList = new String[vServerSize];
for (int i=0;i<vServers.size();i++) {
	if (vServers.getVirtualServer(i).getVersion().indexOf("ArcMap") != -1) {
		typeList[i] = "ImageServer [ArcMap]";
	} else {
		typeList[i] = vServers.getVirtualServer(i).getType();
	}
}

// compose the hash table for the complete collection of virtual server types
Hashtable hTab = new Hashtable();
for (int j =0;j<typeList.length;j++){
	if (hTab.get(typeList[j]) != null){
	    int tempCount = Integer.parseInt(String.valueOf(hTab.get(typeList[j])));
	    tempCount++;
	    hTab.put(typeList[j],String.valueOf(tempCount));
	} else {
	    hTab.put(typeList[j],"1");
	}
}

//put the keys in an array to populate the initial virtual server type hash table
Enumeration enumm = hTab.keys();
String keyVal = null;
String[] keyValArray = new String[hTab.size()];
int keyValArrayIndex = 0;
while (enumm.hasMoreElements()) {
	keyVal = (String)enumm.nextElement();
	keyValArray[keyValArrayIndex] = keyVal;
	keyValArrayIndex ++;
}
Hashtable hTab2 = new Hashtable();
for (int m = 0; m < keyValArray.length; m++) {
	hTab2.put (keyValArray[m], "0");
}

//spatial server prop for singleThreaded
String[] allowColVal = new String[keyValArray.length]; // array to store the value for the allowed column
String vServerVersion = "";
String vServerType = null;

String selectedContainerName = null;

for (int c = 0; c < servers.size(); c++) {
	Server aServer = servers.getServer(c);
%>
<aims:iterateContainersForServer server="<%=aServer%>" count="containerCount" >
      <aims:getContainerAttribute id="myContainer" attribute="NAME" connectionId="<%=admin%>" virtualServers="myVirtualServers" />
<%
		selectedContainerName = index;
		if (cName != null) {
			selectedContainerName = cName;
		}
		if (myContainer.equalsIgnoreCase(selectedContainerName)) {
			for (int b=0; b<keyValArray.length; b++) {
				vServerVersion = "";
				if (keyValArray[b].indexOf("ArcMap") != -1) {
					vServerVersion = "ArcMap";
					vServerType = "ImageServer";
				} else {
					vServerType = keyValArray[b];
				}
				String containerAllowedForType = aServer.getContainerAttribute(aServer.COMPONENT_ALLOWED, selectedContainerName, vServerType, vServerVersion);
				if (containerAllowedForType.equalsIgnoreCase("true"))
					allowColVal[b] = textList[171];
				else
					allowColVal[b] = textList[172];
			}
%>
      	<aims:iterateVirtualServers virtualServers="<%=myVirtualServers%>" count="serviceCount2" >
            <aims:getVirtualServerAttribute id="name" attribute="NAME" />
            <aims:getVirtualServerAttribute id="type" attribute="TYPE" />
            <aims:getVirtualServerAttribute id="version" attribute="VERSION" />
            <aims:getVirtualServerAttribute id="containerset" attribute="CONTAINERSET" />
<%
			String ts = "";
			String threadString = "";
			String containerString = "";
			StringTokenizer stok = new StringTokenizer(containerset,",");
			while (stok.hasMoreTokens()) {
				String tokString  = stok.nextToken();
				if (tokString.indexOf(selectedContainerName) != -1){
					StringTokenizer tok = new StringTokenizer(tokString," ");
					containerString = tok.nextToken();
					ts = tok.nextToken();
					if (containerString.equals(selectedContainerName)) {
						threadString = ts;
					}
				}
			}

			int theCount = 0;
			if (hTab2.get(type) != null){
				if (version.indexOf("ArcMap") != -1) {
					theCount = Integer.parseInt((String)hTab2.get("ImageServer [ArcMap]"));
				    theCount += Integer.parseInt(threadString);
					hTab2.put("ImageServer [ArcMap]", String.valueOf(theCount));
				} else {
					theCount = Integer.parseInt((String)hTab2.get(type));
				    theCount += Integer.parseInt(threadString);
					hTab2.put(type, String.valueOf(theCount));
				}
			} else {
			    hTab2.put(type, ts);
			}
%>
      	</aims:iterateVirtualServers>
<%
		} // end of it is currect selected container
%>
</aims:iterateContainersForServer>
<%
} // end the loop for monitor
%>

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

		function switchPage() {
			var f = document.forms[0];
			var index = f.theSType.selectedIndex;
			var url = f.theSType.options[index].value;
			document.location = url;
		}

		function changeContainer() {
			var selectedContainerIndex = document.forms[0].containerName.selectedIndex;
			var selectedContainer = document.forms[0].containerName.options[selectedContainerIndex].value;
			document.location = "serverprop.jsp?index=" + selectedContainer;
		}

		function exitServerProp() {
			<% if (cName == null) { %>
					document.location = "site_config_server.jsp";
			<% } else {%>
					document.location = "site_config_vserver.jsp";
			<% } %>
		}

		function changeContainerTest() {
			var selectIndex = document.forms[0].containerNameTest.selectedIndex;
			var selectVal = document.forms[0].containerNameTest.options[selectIndex].value;
			alert (selectVal);
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
						<table cellspacing="0" cellpadding="1" width="100%">
							<tr>
								<td align="left" >
									<font face="Arial,Helvetica,sans-serif" color="#000099" size="-1"><b>
										&nbsp;<%=textList[56]%>
										<%=selectedContainerName%>
									</b>
								</td>
								<td >&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099">
										<blockquote>&nbsp;<br>
										<%=textList[192]%></blockquote>
									</font>
								</td>
							</tr>
							<tr> <!-- Headings -->
								<td bgcolor="#A5CEF7" align="left">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
										&nbsp;<%=textList[189]%>
									</b></font>
								</td>
								<td bgcolor="#A5CEF7" align="left">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
										<%=textList[190]%>
									</b></font>
								</td>
								<td bgcolor="#A5CEF7" align="left">
									<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
										<%=textList[191]%>
									</b></font>
								</td>
							</tr>

<%
// construct the array for the allowed column
//String[] allowColVal = new String[keyValArray.length]; // array to store the value for the allowed column

String valueForArcMap = (String)hTab2.get("ImageServer [ArcMap]");

int totalThreadCount = 0;
String vstCount = "0"; // string representation of thread count for a particular type
for (int g=0; g<keyValArray.length; g++) {
	vstCount = (String)hTab2.get(keyValArray[g]);
	totalThreadCount += Integer.parseInt(vstCount);
}

// output the final hash table content for the selected container
Enumeration enum2 = hTab2.keys();
for (int h = 0; h < keyValArray.length; h++) {
%>
<tr>
	<td align="left">
	<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
		&nbsp;<%=keyValArray[h]%>
	</b></font>
	</td>
	<td>
	<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
		<%=(String)hTab2.get(keyValArray[h])%>
	</b></font>
	</td>
	<td>
	<font face="Arial,Helvetica,sans-serif" size="-1" color="#000099"><b>
		<%=allowColVal[h]%>
	</b></font>
	</td>
</tr>
<%
}
%>

							<tr>
								<td bgcolor="#FFFFFF" align="center" height="40" colspan="3">
									<input type="button" name="okButton" value="<%=textList[154]%>" width="75" style="background-color: #EFEFEF; color: #000099;" onclick="exitServerProp()">
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
