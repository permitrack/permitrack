<%@ page import="com.sehinc.common.db.client.DvClientInformation" %>
<%@ page import="com.sehinc.dataview.DataViewConstants" %>
<%@ page import="com.sehinc.dataview.FileDownloadHelper" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<META HTTP-EQUIV="Pragma"
      CONTENT="no-cache">
<meta http-equiv="Content-Type"
      content="text/html; charset=ISO-8859-1">
<html>
    <head>
        <title>Attached Documents Page</title>
    </head>
    <body>
        <div class="mainbodytext">
            Attached Documents
        </div>
        <%
            List
                    filenames =
                    FileDownloadHelper.getTopLevelFilenames((DvClientInformation) session.getAttribute(DataViewConstants.DATAVIEW_CLIENT_INFORMATION));
            if (filenames.size()
                > 0)
            {
        %>
        Select one of the documents to download.
        <%
        }
        else
        {
        %>
        There are no documents available for download.
        <%
            }
        %>
        <%
            Iterator
                    it =
                    filenames.iterator();
            while (it.hasNext())
            {
                String
                        filename =
                        (String) it.next();
        %>
        <div>
            <a href="/sehsvc/dvodownload?layer=<%= DataViewConstants.TOP_LEVEL_FILES %>&id=<%= filename%>"><%= filename%></a>
        </div>
        <%
            }
        %>
    </body>
</html>
