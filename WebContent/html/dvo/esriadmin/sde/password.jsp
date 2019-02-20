<%@ page language="java" buffer="8kb"  %>
<%@ page errorPage="../error_page.jsp" %>
<%@ include file="../textList.jsp" %>

<html>
<head>
	<title><%=textList[6]%></title>
	<script language="JavaScript" type="text/javascript">
		function setPwd() {
			var f = document.forms[0];
			var pwd = f.pwd.value;
			if ((pwd==null) || (pwd=="")) {
				alert("<%=textList[18]%>");
			} else {
				opener.pwd = pwd;
				opener.sendAction(pwd);
				window.close();
			}
		}
	</script>
</head>

<body bgcolor="#336699" text="White" link="#000099" vlink="#000099" alink="#999999" >
<div align="center">
<form action="">
<font face="Arial,Helvetica,sans-serif"><b>
<%=textList[6]%>
</b></font>&nbsp;
<input type="password" name="pwd" id="pwd">&nbsp;
<input type="button" value="<%=textList[154]%>" style="background-color: #EFEFEF; color: #000099;" onclick="setPwd()">
<font face="Arial,Helvetica,sans-serif" size="-1">
<br><%=textList[156]%>
</font>
</form>

</div>
</body>
</html>
