// aimsGeocode.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js,
 *		aimsLayers.js, aimsDHTML.js
 *		aimsClick.js, aimsNavigation.js
 */
aimsGeocodePresent =
true;
var GCLayers = new Array();
var GCLayerId = new Array();
var GCLayerStyle = new Array();
var GCActiveLayer = 0;
var GCidCount = 0;
var GCid = new Array();
var GClabel = new Array();
var GCdesc = new Array();
var GCvalue = new Array();
var GCpointX = new Array();
var GCpointY = new Array();
var GCpointCount = 0;
var GCscore = new Array();
var GCaddress = new Array();
var GCLayerCount = 0;
var SdcGeocodeStyle = "sdcgeocode";
var geocodeAppMode = "locate"; // default mode - other modes (route, address) require RouteServer Extension
if (imsGeocodeURL
    == "")
{
    imsGeocodeURL =
    imsURL
        + "&CustomService=Geocode";
}
/*
 ***************************************************************************************

 Geocoding functions

 ***************************************************************************************
 */

// set up geocode
function setupGeocode()
{
    getGeocodeParams();
}
// get a list of geocoding layers
function getGeocodeLayers()
{
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO />\n';
    theString +=
    '</REQUEST>\n</ARCXML>';
    sendToServer(imsGeocodeURL,
                 theString,
                 25);
}
// get the parameters for geocoding the layer
function getGeocodeParams()
{
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO />\n';
    theString +=
    '</REQUEST>\n</ARCXML>';
    var theReply = "";
    sendToServer(imsGeocodeURL,
                 theString,
                 26);
}
// write out the geocode XML request
function writeGeocodeXML()
{
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_GEOCODE maxcandidates="'
                        + maxGeocodeCandidates
                        + '" minscore="'
                        + minGeocodeScore
        + '">\n';
    theString +=
    '<LAYER id="'
        + GCLayerId[GCActiveLayer]
        + '" />';
    theString +=
    '<ADDRESS>\n';
    for (var i = 0; i
        < GCidCount; i++)
    {
        theString +=
        '<GCTAG id="'
            + GCid[i]
            + '" value="'
            + GCvalue[i]
            + '"/>\n';
    }
    theString +=
    '</ADDRESS>\n</GET_GEOCODE>\n</REQUEST>\n</ARCXML>\n';
    //alert(theString);
    return theString;
}
// parse out geocoding layers
function parseGeocodeLayers(theReply)
{
    //alert(theReply);
    var pos = 0;
    var startpos = 0;
    var endpos = 0;
    var lpos = 0;
    var rCount = 0;
    GCLayers.length =
    1;
    GCLayerCount =
    0;
    pos =
    theReply.indexOf("<LAYERINFO",
                     endpos);
    while (pos
        != -1)
    {
        lpos =
        theReply.indexOf('name=',
                         pos);
        if (lpos
            != -1)
        {
            startpos =
            lpos
                + 6;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            GCLayers[GCLayerCount] =
            theReply.substring(startpos,
                               endpos);
            startpos =
            theReply.indexOf('id=',
                             endpos);
            if (startpos
                != -1)
            {
                startpos +=
                4;
                endpos =
                theReply.indexOf(dQuote,
                                 startpos);
                GCLayerId[GCLayerCount] =
                theReply.substring(startpos,
                                   endpos);
            }
            startpos =
            theReply.indexOf("<GCSTYLE name=",
                             endpos);
            if (startpos
                != -1)
            {
                startpos +=
                15;
                endpos =
                theReply.indexOf(dQuote,
                                 startpos);
                GCLayerStyle[GCLayerCount] =
                theReply.substring(startpos,
                                   endpos);
                if (GCLayerStyle[GCLayerCount]
                    == SdcGeocodeStyle)
                {
                    rCount++;
                }
                //alert(GCLayerStyle[GCLayerCount]);
            }
            GCLayerCount =
            GCLayerCount
                + 1;
            pos =
            theReply.indexOf("<LAYERINFO",
                             endpos);
        }
        else
        {
            // bypass this for now
            //alert(theReply);
            pos =
            -1;
        }
    }
    if (rCount
        == 0)
    {
        //no route extension parameters
        useRoute =
        false;
        useReverseGeocode =
        false;
    }
    if (GCLayerCount
        == 0)
    {
        useGeocode =
        false;
        useRoute =
        false;
        useReverseGeocode =
        false;
    }
    if (parent.ToolFrame
        != null)
    {
        //alert("Refreshing toolbar");
        parent.ToolFrame.document.location =
        appDir
            + "toolbar.htm";
    }
    //alert("useRoute=" + useRoute + "\nuseReverseGeocode=" + useReverseGeocode);
}
// parse out the bacic geocode parameters for the layer
function parseGeocodeParams(theReply, theLayer)
{
    //alert(theLayer + "\n" + theReply);
    var pos = 0;
    var startpos = 0;
    var endpos = 0;
    var lpos = 0;
    GCid.length =
    0;
    GClabel.length =
    0;
    GCdesc.length =
    0;
    GCidCount =
    0;
    var theName = 'name="'
                      + theLayer
        + '"'
    pos =
    theReply.indexOf(theName,
                     endpos);
    if (pos
        != -1)
    {
        //alert(theName + " at " + pos);
        startpos =
        pos
            + 17;
        lpos =
        theReply.indexOf("</LAYERINFO>",
                         startpos);
        startpos =
        theReply.indexOf("<GCINPUT id=",
                         startpos);
        while ((startpos
            < lpos)
            && (startpos
            != -1))
        {

            //if ((startpos<lpos) && (startpos!=-1)){
            //alert(startpos);
            startpos +=
            13;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            GCid[GCidCount] =
            theReply.substring(startpos,
                               endpos);
            startpos =
            theReply.indexOf("label=",
                             endpos);
            startpos =
            startpos
                + 7;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            GClabel[GCidCount] =
            theReply.substring(startpos,
                               endpos);
            startpos =
            theReply.indexOf("description=",
                             endpos);
            startpos +=
            13;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            GCdesc[GCidCount] =
            theReply.substring(startpos,
                               endpos);
            GCidCount =
            GCidCount
                + 1;
            //}
            startpos =
            theReply.indexOf("<GCINPUT id=",
                             endpos);
        }
        return true;
    }
    else
    {
        return false;
    }
}
// parse out geocode response and display results in table
function parseGeocodeResults(theReply)
{
    //alert(theReply);
    GCscore.length =
    1;
    var pos = theReply.indexOf("<GCCOUNT count=");
    var lpos = 0;
    var startpos = pos
        + 16;
    var startpos2 = 0;
    var endpos = theReply.indexOf(dQuote,
                                  startpos);
    var fString = theReply.substring(startpos,
                                     endpos);
    GCpointCount =
    parseInt(fString);
    var gcCount = 0;
    //alert(GCpointCount);
    if ((pos
        > 0)
        && (GCpointCount
        > 0))
    {
        pos =
        theReply.indexOf("<FEATURE");
        if (pos
            != -1)
        {
            while (pos
                != -1)
            {
                lpos =
                theReply.indexOf("<FIELD",
                                 pos);
                if (lpos
                    != -1)
                {
                    //while (lpos!=-1) {
                    startpos2 =
                    theReply.indexOf('name="SCORE"',
                                     lpos);
                    startpos =
                    theReply.indexOf("FIELDVALUE valuestring=",
                                     startpos2);
                    startpos =
                    startpos
                        + 24;
                    endpos =
                    theReply.indexOf(dQuote,
                                     startpos);
                    GCscore[gcCount] =
                    theReply.substring(startpos,
                                       endpos);
                    startpos2 =
                    theReply.indexOf('name="ADDRESSFOUND"',
                                     lpos);
                    startpos =
                    theReply.indexOf("FIELDVALUE valuestring=",
                                     startpos2);
                    startpos =
                    startpos
                        + 24;
                    endpos =
                    theReply.indexOf(dQuote,
                                     startpos);
                    GCaddress[gcCount] =
                    theReply.substring(startpos,
                                       endpos);
                    startpos2 =
                    theReply.indexOf('name="SHAPEFIELD"',
                                     lpos);
                    startpos =
                    theReply.indexOf("<POINT x=",
                                     startpos2);
                    startpos +=
                    10;
                    endpos =
                    theReply.indexOf(dQuote,
                                     startpos);
                    GCpointX[gcCount] =
                    theReply.substring(startpos,
                                       endpos);
                    startpos =
                    theReply.indexOf("y=",
                                     endpos);
                    startpos =
                    startpos
                        + 3;
                    endpos =
                    theReply.indexOf(dQuote,
                                     startpos);
                    GCpointY[gcCount] =
                    theReply.substring(startpos,
                                       endpos);
                    gcCount++;
                    //	lpos = theReply.indexOf("<FIELD",endpos);
                    //}
                }
                pos =
                theReply.indexOf("<FEATURE",
                                 endpos);
            }
        }
        if (geocodeAppMode
            == "locate")
        {
            showGeocode =
            true;
            geocodeX =
            GCpointX[0];
            geocodeY =
            GCpointY[0];
            geocodeLabel =
            GCaddress[0];
            hideRetrieveData();
            //} else {
            var Win1;
            var theFrame = "parent.MapFrame";
            if ((useExternalWindow)
                || (!useTextFrame))
            {
                Win1 =
                window.open("",
                            "GeocodeWindow",
                            "width=575,height=120,scrollbars=yes,resizable=yes");
                theFrame =
                "opener";
                if (parent.MapFrame
                    != null)
                {
                    theFrame =
                    "opener.parent.MapFrame";
                }
            }
            else
            {
                Win1 =
                parent.TextFrame;
                Win1.document.open();
            }
            Win1.document.open();
            Win1.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                      + charSet
                                      + '"><head><title>'
                                      + titleList[6]
                                      + '</title></head>');
            Win1.document.writeln('<body bgcolor="'
                                      + textFrameBackColor
                                      + '" text="Black" link="Blue" vlink="Blue" LEFTMARGIN=0 onload="window.focus()">');
            Win1.document.writeln('<FONT FACE="Arial" SIZE="-1"><b>'
                                      + msgList[57]
                                      + '</b>');
            Win1.document.writeln('<table border="1" cellspacing="0" cellpadding="2" nowrap bgcolor="'
                                      + tableBackColor
                                      + '">');
            Win1.document.writeln('<tr><th><FONT FACE="Arial" SIZE="-1">#</font></th><th><FONT FACE="Arial" SIZE="-1">'
                                      + msgList[58]
                                      + '</font></th><th><FONT FACE="Arial" SIZE="-1">'
                                      + msgList[59]
                                      + '</th></tr>');
            for (var i = 0; i
                < GCpointCount; i++)
            {
                Win1.document.writeln('<tr>');
                Win1.document.writeln('<td><FONT FACE="Arial" SIZE="-1"><a href="javascript:'
                                          + theFrame
                                          + '.zoomToPoint('
                                          + GCpointX[i]
                                          + ','
                                          + GCpointY[i]
                                          + ',true,\''
                                          + GCaddress[i]
                                          + '\')">'
                                          + (i
                    + 1)
                                          + '</a></font></td>');
                Win1.document.writeln('<td><FONT FACE="Arial" SIZE="-1">'
                                          + GCaddress[i]
                                          + '</font></td>');
                Win1.document.writeln('<td><FONT FACE="Arial" SIZE="-1">'
                                          + GCscore[i]
                                          + '</font></td>');
                Win1.document.writeln('</tr>');
            }
            Win1.document.writeln('</table><form><input type="button" onclick="document.location=\''
                                      + appDir
                                      + 'addmatch.htm\';" value="'
                                      + buttonList[1]
                                      + '"></form>');
            Win1.document.close();
            //}
            Win1 =
            null;
            //toolMode = 1;
            //if (parent.ToolFrame!=null) parent.ToolFrame.document.location = appDir + "toolbar.htm";
            if (GCpointCount
                == 1)
            {
                sendMapXML();
            }
            //if (GCpointCount==1) zoomToPoint(GCpointX[0], GCpointY[0], true, geocodeLabel);
        } else if (geocodeAppMode
            == "route")
        {
            // requires RouteServer extension
            hideRetrieveData();
            if (GCpointCount
                == 1)
            {
                addStop(parseFloat(GCpointX[0]),
                        parseFloat(GCpointY[0]),
                        GCaddress[0],
                        GCaddress[0]);
            }
            else
            {
                var url = appDir
                    + "candidates.htm";
                if ((useExternalWindow)
                    || (!useTextFrame))
                {
                    Win1 =
                    window.open(url,
                                "GeocodeWindow",
                                "width=575,height=120,scrollbars=yes,resizable=yes");
                }
                else
                {
                    parent.TextFrame.document.location =
                    url;
                }
            }
        } else if (geocodeAppMode
            == "address")
        {
            // requires RouteServer extension
            if (zoomToGCPoint)
            {
                zoomToGCPoint =
                false;
                hideRetrieveData();
                zoomToRouteEnvelope(parseFloat(GCpointX[0]),
                                    parseFloat(GCpointY[0]),
                                    parseFloat(GCpointX[0]),
                                    parseFloat(GCpointY[0]));
            }
            else
            {
                hideRetrieveData();
                //alert(GCaddress[0]);
                writeRGCresultPage(GCaddress[0]);
            }
        }
    }
    else
    {
        //no match
        alert(msgList[60]);
        var url = appDir
            + "addmatch.htm";
        if ((useExternalWindow)
            || (!useTextFrame))
        {
            Win1 =
            window.open(url,
                        "GeocodeWindow",
                        "width=575,height=120,scrollbars=yes,resizable=yes");
        }
        else
        {
            parent.TextFrame.document.location =
            url;
        }
    }
}

