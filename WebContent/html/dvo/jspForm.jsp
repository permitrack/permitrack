<%@ page language="java"
         buffer="8kb" %>
<%@ page import="com.esri.aims.mtier.io.ConnectionProxy" %>
<html>
    <head>
        <title>JSP Form</title>
        <%
            /**
             * Creates a new instance of The AppServerLink Object.
             */
            ConnectionProxy
                    mcp =
                    new ConnectionProxy();
            String
                    arcxmlRequest =
                    request.getParameter("ArcXMLRequest");   //ArcXML request to be sent
            String
                    jsFunction =
                    request.getParameter("JavaScriptFunction");
            String
                    serverName =
                    request.getParameter("ServerName");
            String
                    serviceName =
                    request.getParameter("ServiceName");
            String
                    connectType =
                    request.getParameter("ConnectType");
            if (connectType
                == null)
            {
                connectType =
                        "tcp";
            }
            String
                    theResponse =
                    "";
            out.println("<script language='JavaScript' type='text/javascript'>");
            out.println("	function passXML() {");
            out.println("	  // ArcXML Response packaged here.");
            if (arcxmlRequest
                != null)
            {
                if (connectType.equals("https"))
                {
                    mcp.setConnectionType(2);
                }
                else if (connectType.equals("http"))
                {
                    //} else {
                    mcp.setConnectionType(1);
                }
                else
                {
                    mcp.setConnectionType(0);
                    mcp.setPort(5300);
                    //	mcp.setUsername("");
                    //	mcp.setPassword("");
                }
                mcp.setService(serviceName);
                mcp.setHost(serverName);
                theResponse =
                        mcp.send(arcxmlRequest);
                //out.println(theResponse);
                out.println("		var f = document.forms[0];");
                out.println("		var xmlResponse = f.ArcXMLResponse.value;");
                out.println("		"
                            + jsFunction
                            + "(xmlResponse);");
                out.println("		return true;");
            }
            out.println("	}");
            out.println("	var connectType='"
                        + connectType
                        + "';");
            out.println("</script>");
        %>
    </HEAD>
    <BODY BGCOLOR="#000000"
          TEXT="#000000"
          onload="passXML()">
        <h3>Response Form</h3>
        <P>
        <FORM ACTION="jspForm.jsp"
              METHOD="POST"
              name="theForm">
            <INPUT TYPE="Hidden"
                   NAME="ArcXMLRequest"
                   VALUE="">
            <INPUT TYPE="Hidden"
                   NAME="JavaScriptFunction"
                   VALUE="parent.MapFrame.processXML">
            <INPUT TYPE="Hidden"
                   NAME="ServerName"
                   VALUE="">
            <INPUT TYPE="Hidden"
                   NAME="ServiceName"
                   VALUE="">
            <INPUT TYPE="Hidden"
                   NAME="ConnectType"
                   VALUE="tcp">
            <P>
                ArcXML Response:
                <BR>
                <textarea name="ArcXMLResponse">
<%
    if (arcxmlRequest
        != null)
    {
        out.println(theResponse);
    }
%>
                </textarea>
        </FORM>
    </BODY>
</HTML>
