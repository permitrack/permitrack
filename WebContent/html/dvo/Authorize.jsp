<%@ page import="com.sehinc.dataview.DataViewConstants" %>
<META HTTP-EQUIV="Pragma"
      CONTENT="no-cache">
<meta http-equiv="Content-Type"
      content="text/html; charset=ISO-8859-1">
<html>
    <head>
        <title>Authorization Page</title>
        <SCRIPT TYPE="text/javascript"
                LANGUAGE="JavaScript"
                SRC="./javascript/aimsResource.js"></SCRIPT>
        <SCRIPT TYPE="text/javascript"
                LANGUAGE="JavaScript"
                SRC="./ArcIMSparam.jsp"></SCRIPT>
        <SCRIPT LANGUAGE="JavaScript"
                TYPE="text/javascript">
            //var cmdString = document.location.search;// encoding for XML header
            var localeEncoding = 'encoding="UTF-8" ';
            var XMLMode = 1;
            var lastURL = "";// client version
            var cVersion = "&ClientVersion=4.0";// get machine name
            var hostName = document.location.host;// the base servlet connector URL
            var connectorURL = document.location.protocol
                                       + "//"
                                       + hostName
                    + "<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=redirect";// send in XML request and get XML response - uses helper applet
            function sendToServer(URLString, XMLRequest, theType)
            {
                if (parent.PostFrame.document.forms[0]
                        != null)
                {
                    lastURL
                            = URLString;
                    XMLMode
                            = theType;
                    var theForm = parent.PostFrame.document.forms[0];
                    URLString
                            = URLString
                            + cVersion;
                    var requestURL = URLString;
                    if (theForm.RedirectURL
                            != null)
                    {
                        if (isNotSameHostInURL(URLString,
                                               hostName))
                        {
                            requestURL
                                    = connectorURL;
                            theForm.RedirectURL.value
                                    = URLString;
                        }
                        else
                        {
                            theForm.RedirectURL.value
                                    = "";
                        }
                    }
                    if (doURLencode)
                    {
                        theForm.action
                                = requestURL
                                + "&Form=True&Encode=True";
                    }
                    else
                    {
                        theForm.action
                                = requestURL
                                + "&Form=True&Encode=False";
                    }
                    var xmlHeader = '<?xml version="1.0" '
                                            + localeEncoding
                            + '?>';
                    theForm.ArcXMLRequest.value
                            = xmlHeader
                            + XMLRequest;
                    //theForm.JavaScriptFunction.value = theFunction;
                    theForm.submit();
                }
                else
                {
                    alert(msgList[11]);
                }
            }// process the response xml
            function processXML(theReplyIn)
            {
                var theReply;
                if (doURLencode)
                {
                    theReplyIn
                            = replacePlus(theReplyIn);
                    theReply = unescape(theReplyIn);
                }
                else
                {
                    theReply = theReplyIn;
                }
                var theError = getXMLErrorMessage(theReply);
                if (theError
                        != "")
                {
                    writePage(theError,
                              lastURL);
                    //alert(theError + "\n\n" + lastURL);
                }
                else
                {
                    theError
                            = checkForbiddenGetImageTag(theReply);
                    if (theError
                            == "")
                    {
                        switch (XMLMode)
                        {
                            case 1:
                                processAuthorization(theReply,
                                                     1);
                                break;
                            case 2:
                                processAuthorization(theReply,
                                                     2);
                                break;
                            default:
                                alert("default:\n"
                                              + theReply);
                        }
                    }
                    else
                    {
                        writePage(theError,
                                  lastURL);
                    }
                }
            }// replace +  in string with space to allow parsing of unescaped xml response
            function replacePlus(inText)
            {
                var re = /\+/g;
                inText
                        = inText.replace(re,
                                         " ");
                return inText;
            }
            function checkAuthorization(theType)
            {
                var URLString = imsURL;
                if (theType
                        == 2) URLString
                        = imsOVURL;
                var XMLRequest = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO renderer="false" extensions="false" fields="false" />\n</REQUEST>\n</ARCXML>\n';
                sendToServer(URLString,
                             XMLRequest,
                             theType)
            }
            function processAuthorization(theReply, theType)
            {
                var pos = theReply.indexOf("ERROR");
                if (pos
                        != -1)
                {
                    alert(theType
                                  + "\n"
                                  + getXMLErrorMessage(theReply));
                }
                else
                {
                    if ((theType
                            == 1)
                                && (imsOVURL
                            != "")
                            && (imsURL
                            != imsOVURL))
                    {
                        //alert(theType + "- Ok");
                        checkAuthorization(2);
                    }
                    else
                    {
                        //alert(theType + "- Ok");
                        appDir
                                = getPath(document.location.pathname);
                        parent.document.location
                                = appDir
                                          + "viewer.htm"
                                + parent.webParams;
                    }
                }
            }// check if there is an error message in the response
            function getXMLErrorMessage(theString)
            {
                var pos1 = 0;
                var pos2 = 0;
                var pos3 = 0;
                var theError = "";
                pos3
                        = theString.indexOf("<ERROR");
                if (pos3
                        != -1)
                {
                    pos1
                            = theString.indexOf(">",
                                                pos3);
                    pos1
                            += 1;
                    pos2
                            = theString.indexOf("</ERROR");
                    theError
                            = theString.substring(pos1,
                                                  pos2)
                }
                return theError;
            }// get directory path of URL
            function getPath(theFullPath)
            {
                var theSlash = theFullPath.lastIndexOf("/");
                var theDir = theFullPath.substring(0,
                                                   theSlash);
                if (theDir
                        == null) theDir
                        = "";
                theDir
                        = theDir
                        + "/";
                return theDir;
            }
            function writePage(errorString, theURL)
            {
                document.open();
                document.writeln('<html>');
                document.writeln('<body>');
                document.writeln('<div>');
                document.writeln(errorString);

                document.writeln('</div>');
                document.writeln('<div>');
                document.writeln(theURL);

                document.writeln('</div>');
                document.writeln('</body>');
                document.writeln('</html>');
                document.close();
            }// test for forbidden tags for this service
            function checkForbiddenGetImageTag(theString)
            {
                var theError = "";
                var dQuote = '"';
                var startpos = theString.indexOf("CAPABILITIES forbidden=");
                if (startpos
                        != -1)
                {
                    startpos
                            = startpos
                            + 24;
                    endpos
                            = theString.indexOf(dQuote,
                                                startpos);
                    var forbiddenTags = theString.substring(startpos,
                                                            endpos);
                    //alert(forbiddenTags);
                    if (forbiddenTags.indexOf("GET_IMAGE")
                            != -1)
                    {
                        theError
                                = msgList[119];
                        // No image requests!!!! Abort viewer
                    }
                }
                return theError;
            }// check if theURL has different host from theHost
            function isNotSameHostInURL(theURL, theHost)
            {
                var startpos = theURL.indexOf("//");
                if (startpos
                        == -1)
                {
                    startpos
                            = 0;
                }
                else
                {
                    startpos
                            = startpos
                            + 2;
                }
                var endpos = theURL.indexOf("/",
                                            startpos);
                if (endpos
                        == -1) endpos
                        = theURL.length;
                var thisHost = theURL.substring(startpos,
                                                endpos);
                return thisHost
                        != theHost;
            }
        </SCRIPT>
    </head>
    <body>
        <SCRIPT LANGUAGE="JavaScript"
                TYPE="text/javascript">
            document.writeln(msgList[113]);
        </SCRIPT>
    </body>
</html>
