// aimsCommon.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsMap.js
 */
aimsCommonPresent =
true;
var legendImage = "";
var modeBlurb = modeList[0];
// delimiter to be used between coordinates in strings in ArcXML request
var coordsDelimiter = " ";
// delimiter to be used between pairs of coordinates in strings in ArcXML request
var pairsDelimiter = ";";
var chkUnits = false;
var legendTemp = false;
var ovIsVisible = false;
var showBuffer = false;
var chkGeocodeLayers = false;
var isArcMapService = false;
// character used by browser in decimals - either point or comma
var decimalChar = ((("theChar is"
    + (10
    / 100)).indexOf("."))
    == -1)
    ? ","
    : ".";
//alert("Decimal character: " + decimalChar);

/*
 ***************************************************************************************

 Common functions

 ***************************************************************************************
 */



// when there is a mapservice to load, it proceeds from here
function startUp()
{
    if (imsURL
        != "")
    {
        //alert(imsURL);
        iWidth =
        parseInt(document.theImage.width);
        iHeight =
        parseInt(document.theImage.height);
        if (imsURL
            != imsOVURL)
        {
            toggleOVVisible =
            false;
        }
        getStartExtent();
    }
}
// get the starting extent
function getStartExtent()
{
    if (parent.PostFrame.document.forms[0]
        != null)
    {
        var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO renderer="false" extensions="true" ';
        if (aimsLayersPresent)
        {
            if (LayerID.length
                > 0)
            {
                theString +=
                'fields="false" ';
            }
            else
            {
                theString +=
                'fields="true" ';
            }
        }
        else
        {
            theString +=
            'fields="false" ';
        }
        theString +=
        '/>\n</REQUEST>\n</ARCXML>';
        var theReply = "";
        if (hasOVMap)
        {
            theString =
            '<ARCXML version="1.1">\n<REQUEST>\n<GET_IMAGE><PROPERTIES>\n';
            theString +=
            '<IMAGESIZE height="'
                + i2Height
                + '" width="'
                + i2Width
                + '" />\n';
            if (mapBackColor
                != "")
            {
                theString +=
                '<BACKGROUND color="'
                    + mapBackColor
                    + '" />\n';
            }
            theString +=
            '</PROPERTIES>\n';
            theString +=
            '</GET_IMAGE>\n</REQUEST>\n</ARCXML>';
            sendToServer(imsOVURL,
                         theString,
                         902);
        }
        else
        {
            sendToServer(imsURL,
                         theString,
                         3);
        }
    }
    else
    {
        alert(msgList[2]);
    }
}
// process the start extent and set up layers
function processStartExtent(theReply)
{
    checkForForbiddenTags(theReply);
    // check for separators in serviceinfo
    var endpos = 0;
    var startpos = 0;
    var pos = theReply.indexOf("<SEPARATORS");
    if (pos
        != -1)
    {
        startpos =
        theReply.indexOf("ts=",
                         pos);
        if (startpos
            != -1)
        {
            startpos +=
            4;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            pairsDelimiter =
            theReply.substring(startpos,
                               endpos);
        }
        startpos =
        theReply.indexOf("cs=",
                         pos);
        if (startpos
            != -1)
        {
            startpos +=
            4;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            coordsDelimiter =
            theReply.substring(startpos,
                               endpos);
        }
        //alert("pairsDelimiter="+pairsDelimiter+"\ncoordsDelimiter="+coordsDelimiter);
        checkCoords();
    }
    isArcMapService =
    (theReply.indexOf("<LAYOUTINFO")
        != -1);
    //alert("isArcMapService=" + isArcMapService);
    if (isArcMapService)
    {
        if (autoAdjustForArcMapServer)
        {
            // adjust sizes when displayed by ArcMap Server
            var nasize = parseInt(NorthArrowSize)
                * 2;
            NorthArrowSize =
            new String(nasize);
        }
    }
    //alert("NorthArrowSize=" + NorthArrowSize);
    pos =
    theReply.indexOf("<MAPUNITS");
    if (pos
        != -1)
    {
        startpos =
        theReply.indexOf("units=",
                         pos);
        if (startpos
            != -1)
        {
            startpos +=
            7;
            endpos =
            theReply.indexOf(dQuote,
                             startpos);
            MapUnits =
            theReply.substring(startpos,
                               endpos);
        }
    }
    else
    {
        // if the serviceinfo did not have MAPUNITS tag and units are set to Degrees
        if (MapUnits
            == "DEGREES")
        {
            // if it is too big for the world. . . allow for oversize extent
            // default to Feet
            if ((eRight
                > 250)
                    || (eTop
                > 150)
                    || (eLeft
                < -250)
                || (eBottom
                < -150))
            {
                MapUnits =
                "FEET";
            }
            // alert(MapUnits);
        }
        // if the serviceinfo doesn't have the info, allow the user to set Map Units.
        setMapUnits =
        true;
    }
    MapUnits =
    MapUnits.toUpperCase();
    if (MapUnits
        == "DECIMAL_DEGREES")
    {
        MapUnits =
        "DEGREES";
    }
    if (getStartingExtent)
    {
        pos =
        theReply.indexOf("<PROPERTIES");
        var theXYs = getEnvelopeXYs(theReply,
                                    pos)
        eLeft =
        theXYs[0];
        eBottom =
        theXYs[1];
        eRight =
        theXYs[2];
        eTop =
        theXYs[3];
        startLeft =
        eLeft;
        startRight =
        eRight;
        startTop =
        eTop;
        startBottom =
        eBottom;
    }
    else
    {
        eLeft =
        startLeft;
        eRight =
        startRight;
        eTop =
        startTop;
        eBottom =
        startBottom;
        xDistance =
        Math.abs(eRight
                     - eLeft);
        var sFactor = xDistance
            / iWidth
        mapScaleFactor =
        sFactor;
    }
    if (aimsLayersPresent)
    {
        getLayers(theReply);
        //Begin Initial Layer Setup
        //  if (SC_SetupInitialLayers) InitialLayerStartup();
        //End Initial Layer Setup
        if (setLayerVisible.length
            > 0)
        {
            setupLayerVisible();
        }
    }
    if (aimsQueryPresent)
    {
        if (useStoredQuery)
        {
            checkStoredQueries(theReply);
        }
    }
    else
    {
        useStoredQuery =
        false;
    }
    xDistance =
    Math.abs(eRight
                 - eLeft);
    yDistance =
    Math.abs(eTop
                 - eBottom);
    xHalf =
    xDistance
        / 2;
    yHalf =
    yDistance
        / 2;
    panX =
    xDistance
        * panFactor;
    panY =
    yDistance
        * panFactor;
    mouseX =
    0;
    mouseY =
    0;
    pixelX =
    xDistance
        / iWidth;
    pixelY =
    yDistance
        / iHeight;
    mapX =
    eLeft;
    mayY =
    eTop;
    lastLeft =
    eLeft;
    lastRight =
    eRight;
    lastTop =
    eTop;
    lastBottom =
    eBottom;
    if (hasOVMap
        == false)
    {
        fullLeft =
        limitLeft;
        fullRight =
        limitRight;
        fullTop =
        limitTop;
        fullBottom =
        limitBottom;
        fullWidth =
        Math.abs(fullRight
                     - fullLeft);
        fullHeight =
        Math.abs(fullTop
                     - fullBottom);
    }
    if (aimsLayersPresent)
    {
        if ((hasTOC)
            && (showTOC))
        {
            parent.TOCFrame.document.location =
            appDir
                + "toc.htm";
        }
    }
    if (aimsGeocodePresent)
    {
        if (aimsRoutePresent)
        {
            if ((useRoute)
                || (useReverseGeocode))
            {
                chkGeocodeLayers =
                true;
            }
        }
        else
        {
            if (theReply.indexOf('<EXTENSION type="Geocode"')
                != -1)
            {
                //alert("Has Geocode extension");
                if (useGeocode)
                {
                    chkGeocodeLayers =
                    true;
                    useReverseGeocode =
                    false;
                    useRoute =
                    false;
                }
                else
                {
                    useGeocode =
                    false;
                    useReverseGeocode =
                    false;
                    chkGeocodeLayers =
                    false;
                    useRoute =
                    false;
                }
            }
            else
            {
                //alert("Geocode not available");
                useGeocode =
                false;
                useReverseGeocode =
                false;
                chkGeocodeLayers =
                false;
                useRoute =
                false;
            }
        }
    }
    else
    {
        //alert("Geocode not available");
        useGeocode =
        false;
        useReverseGeocode =
        false;
        chkGeocodeLayers =
        false;
    }
    //alert("useRoute=" + useRoute + "\nuseReverseGeocode=" + useReverseGeocode);
    if (parent.ToolFrame
        != null)
    {
        //alert("Refreshing toolbar");
        parent.ToolFrame.document.location =
        appDir
            + "toolbar.htm";
    }
    hideRetrieveData();
    if ((ovIsVisible)
        && (aimsDHTMLPresent))
    {
        ovIsVisible =
        false;
        toggleOVMap();
    }
    if (chkGeocodeLayers)
    {
        getGeocodeLayers();
    }
    else
    {
        if ((aimsQueryPresent)
                && (highlightedOne
            != "")
            && (queryZoom))
        {
            setStartQuery();
        }
        else
        {
            sendMapXML();
        }
    }
}
// request a list of available Image MapServices
function startMap()
{
    showRetrieveData();
    if (aimsGenericPresent)
    {
        // only if aimsGeneric.js is loaded - for generic sample
        getDefaultParams()
        var theText = "<GETCLIENTSERVICES/>";
        sendToServer(catURL,
                     theText,
                     5);
    }
    else
    {
        startUp();
    }
}
/*  ************************
 *	Extent functions
 *	************************
 */

// get the Map Image width
function getMapWidth()
{
    var mapFrameWidth = thePageWin.innerWidth;
    if (mapFrameWidth
        == null)
    {
        mapFrameWidth =
        thePageDoc.body.clientWidth;
    }
    return mapFrameWidth;
}
//get the Map Image height
function getMapHeight()
{
    var mapFrameHeight = thePageWin.innerHeight;
    if (mapFrameHeight
        == null)
    {
        mapFrameHeight =
        thePageDoc.body.clientHeight;
    }
    return mapFrameHeight;
}
function checkCurrentExtent()
{
    var msg = msgList[3]
                  + eLeft
                  + msgList[4]
                  + eBottom
                  + msgList[5]
                  + eRight
                  + msgList[6]
        + eTop;
    var ratio1 = xDistance
        / fullWidth;
    msg +=
    msgList[7]
        + ratio1;
    alert(msg);
}
/*  ************************
 *	Mode display functions
 *	************************
 */
// write out ModeFrame page
function writeModeFrame(currentMode)
{
    parent.ModeFrame.document.location =
    appDir
        + "ModeFrame.htm";
}
// write out Mode on dynamic layer
function writeModeLayers(currentMode)
{
    var content = '<font face="'
                      + modeLayerFont
                      + '"color="'
                      + modeLayerShadowColor
                      + '"size='
                      + modeLayerSize
                      + '><b>'
                      + currentMode
        + '</b></font>';
    replaceLayerContent("theMode1",
                        content);
    content =
    '<font face="'
        + modeLayerFont
        + '"color="'
        + modeLayerColor
        + '"size='
        + modeLayerSize
        + '><b>'
        + currentMode
        + '</b></font>';
    replaceLayerContent("theMode2",
                        content);
}
/*  *****************************************************
 *	Various String manipulation Functions
 *	*****************************************************
 */

// swap out double quotes for single
function swapQuotes2(inText)
{
    var doubleQuote = dQuote;
    var singleQuote = "'";
    var preTemp = "";
    var posTemp = "";
    var nextPos = 0;
    var ePos = inText.length;
    var pos = 9;
    while (pos
        != -1)
    {
        pos =
        inText.indexOf(dQuote);
        if (pos
            != -1)
        {
            nextPos =
            pos
                + 1;
            preTemp =
            inText.substring(0,
                             pos);
            posTemp =
            inText.substring(nextPos,
                             ePos);
            inText =
            preTemp
                + sQuote
                + posTemp;
        }
    }
    return inText;
}
function swapQuotes(inText)
{
    inText =
    inText.replace(/"/g,
                   "'");
    return inText;
}
// convert hexidecimal rgb number to delimited decimal rgb
function convertHexToDec(hexColor)
{
    var pos = hexColor.indexOf(",");
    var decString = hexColor;
    if (pos
        == -1)
    {
        pos =
        hexColor.indexOf("#");
        if (pos
            != -1)
        {
            hexColor =
            hexColor.substring((pos
                + 1),
                               (pos
                                   + 7));
        }
        //alert(hexColor);
        var redHex = hexColor.substring(0,
                                        2);
        var greenHex = hexColor.substring(2,
                                          4);
        var blueHex = hexColor.substring(4,
                                         6);
        decString =
        parseInt(redHex,
                 16)
            + ","
            + parseInt(greenHex,
                       16)
            + ","
            + parseInt(blueHex,
                       16);
    }
    //alert(decString);
    return decString;
}
// swap out one interior string with another
function swapStuff(oldString, oldStuff, newStuff)
{
    var pos = 0;
    var rpos = 0;
    var epos = 0;
    var leftString = "";
    var rightString = "";
    pos =
    oldString.indexOf(oldStuff);
    while (pos
        != -1)
    {
        epos =
        oldString.length;
        rpos =
        pos
            + oldStuff.length;
        leftString =
        oldString.substring(0,
                            pos);
        rightString =
        oldString.substring(rpos,
                            epos);
        oldString =
        leftString
            + newStuff
            + rightString;
        pos =
        oldString.indexOf(oldStuff);
    }
    leftString =
    null;
    rightString =
    null;
    return oldString;
}
/*  *****************************************************
 *	Various utility Functions
 *	*****************************************************
 */

// disables error checking
function clearError()
{
    return true;
}
// reset error checking to default
function resetError()
{
    return false;
}
function reloadApp()
{
    if (isNav)
    {
        document.location =
        "default.htm";
    }
}
// clear out leading spaces in field value list
function clearLeadingSpace(inText)
{
    var pos = 9;
    while (pos
        != -1)
    {
        pos =
        inText.indexOf('=" ');
        if (pos
            != -1)
        {
            var lastpos = inText.length;
            var midend = pos
                + 2;
            var midstart = pos
                + 3;
            var leftSide = inText.substring(0,
                                            midend);
            var rightSide = inText.substring(midstart,
                                             lastpos);
            inText =
            leftSide
                + rightSide;
        }
    }
    return inText;
}
// replace < and > in string with [ and ] to allow display in html page
function untag(inputString)
{
    var outString = inputString.replace(/</g,
                                        "[");
    outString =
    outString.replace(/>/g,
                      "]");
    return outString;
}
// replace single quotes with double single quotes
//	set up interior single qoutes and apostrophes for queries
function fixSingleQuotes(inputString)
{
    var outString = inputString.replace(/'/g,
                                        "''");
    return outString;
}
// parse out record data from XML stream
function parseRecordString(theReply, startpos)
{
    var inData = "";
    var pos = theReply.indexOf("<FIELDS ",
                               startpos);
    if (pos
        != -1)
    {
        startpos =
        pos
            + 8;
        xmlEndPos =
        theReply.indexOf('" />',
                         startpos);
        inData =
        theReply.substring(startpos,
                           xmlEndPos);
    }
    return inData;
}
// get a list of field names from the returned record
function getFieldNames(recordString)
{
    var theStuff = new String(recordString);
    var theList = theStuff.split('" ');
    var fName1 = new Array();
    for (var f = 0; f
        < theList.length; f++)
    {
        var v = theList[f].split('="');
        fName1[f] =
        v[0];
    }
    return fName1;
}
// get a list field values from the returned record
function getFieldValues(recordString)
{
    var theStuff = new String(recordString);
    var theList = theStuff.split('" ');
    var fValue1 = new Array();
    for (var f = 0; f
        < theList.length; f++)
    {
        var v = theList[f].split('="');
        if ((v[1]
            == "")
            || (v[1]
            == null))
        {
            v[1] =
            "&nbsp;";
        }
        if (v[0]
            == LayerShapeField[ActiveLayerIndex])
        {
            v[1] =
            "["
                + ActiveLayerType
                + "]";
        }
        fValue1[f] =
        v[1];
    }
    return fValue1;
}
// just get the field value from the lists of fieldnames and fieldvalues
function getIdValue(fieldNameArray, fieldValueArray)
{
    var theValue = 0;
    for (var f = 0; f
        < fieldNameArray.length; f++)
    {
        if (fieldNameArray[f]
            == LayerIDField[ActiveLayerIndex])
        {
            theValue =
            fieldValueArray[f];
        }
    }
    return theValue;
}
// just get the interior string from the theReply between preString and postString
//		starting from startpos
function justGetValue(theReply, preString, postString, startpos)
{
    var theValue = "";
    var pos = theReply.indexOf(preString,
                               startpos);
    if (pos
        != -1)
    {
        pos =
        pos
            + preString.length;
        var endpos = theReply.indexOf(postString,
                                      (pos));
        if (endpos
            != -1)
        {
            theValue =
            theReply.substring(pos,
                               endpos);
            xmlEndPos =
            endpos;
        }
    }
    return theValue;
}
// get one field value from theReply starting from startpos
function justGetFieldValue(theReply, theField, startpos)
{
    var preString = theField
        + '="';
    var returnString = justGetValue(theReply,
                                    preString,
                                    dQuote,
                                    startpos);
    return returnString;
}
// get the number of features returned in xml response
function justGetFeatureCount(theReply)
{
    var theCount = 0;
    var pos = theReply.indexOf("<FEATURECOUNT");
    if (pos
        != -1)
    {
        var theValue = justGetValue(theReply,
                                    'count="',
                                    dQuote,
                                    pos);
        //alert(theValue);
        theCount =
        parseInt(theValue);
    }
    return theCount;
}
// get all the field values and return a list
function getAllFieldValues(theReply, theField, recCount)
{
    var vList = new Array();
    xmlEndPos =
    0;
    for (var i = 0; i
        < recCount; i++)
    {
        vList[i] =
        parseFloat(justGetFieldValue(theReply,
                                     theField,
                                     xmlEndPos));
    }
    return vList;
}
// reset order to numeric
function numberorder(a, b)
{
    return a
        - b;
}
// replace common HTML entitys with the characters they represent
function parseEntity(oldString)
{
    //alert(oldString);
    oldString =
    oldString.replace(/&apos;/g,
                      "'");
    oldString =
    oldString.replace(/&gt;/g,
                      ">");
    oldString =
    oldString.replace(/&lt;/g,
                      "<");
    oldString =
    oldString.replace(/&quot;/g,
                      '"');
    oldString =
    oldString.replace(/&amp;/g,
                      "&");
    //alert(oldString);
    /*
     oldString = swapStuff(oldString,"&apos;","'");
     oldString = swapStuff(oldString,"&divide;","/");
     oldString = swapStuff(oldString,"&ge;",">=");
     oldString = swapStuff(oldString,"&gt;",">");
     oldString = swapStuff(oldString,"&le;","<=");
     oldString = swapStuff(oldString,"&lt;","<");
     oldString = swapStuff(oldString,"&ne;","<>");
     oldString = swapStuff(oldString,"&quot;",'"');
     oldString = swapStuff(oldString,"&amp;","&");
     */
    return oldString;
}
function hideQuotes(oldString)
{
}
// replace the five problem characters for the server's XML parser
function makeXMLsafe(oldString)
{
    //alert(oldString);
    oldString =
    oldString.replace(/&/g,
                      "&amp;");
    oldString =
    oldString.replace(/'/g,
                      "&apos;");
    oldString =
    oldString.replace(/>/g,
                      "&gt;");
    oldString =
    oldString.replace(/</g,
                      "&lt;");
    oldString =
    oldString.replace(/"/g,
                      "&quot;");
    /*
     oldString = swapStuff(oldString,"'","&apos;");
     oldString = swapStuff(oldString,">","&gt;");
     oldString = swapStuff(oldString,"<","&lt;");
     oldString = swapStuff(oldString,'"',"&quot;");
     */
    //alert(oldString);
    return oldString;
}
// replace +  in string with space to allow parsing of unescaped xml response
function replacePlus(inText)
{
    var re = /\+/g;
    inText =
    inText.replace(re,
                   " ");
    return inText;
}
// replaces comas or spaces in these variables to coordsDelimiter value
// the variables checked are used for image coords and should be integer
function checkCoords()
{
    var re = /,|\s|\|/g;
    NorthArrowCoords =
    NorthArrowCoords.replace(re,
                             coordsDelimiter);
    CopyrightCoords =
    CopyrightCoords.replace(re,
                            coordsDelimiter);
}
// get the substring between beforeString and afterString, starting at startpos
// 		must be found before limitpos (0 for no limit)
// 		caseSensitive = true or false
function getInsideString(inString, beforeString, afterString, startpos, limitpos, caseSensitive)
{
    var returnString = "";
    var ucInString = inString;
    var ucBefore = beforeString;
    var ucAfter = afterString;
    if (limitpos
        == 0)
    {
        limitpos =
        inString.length;
    }
    if (!caseSensitive)
    {
        ucInString =
        inString.toUpperCase();
        ucBefore =
        beforeString.toUpperCase();
        ;
        ucAfter =
        afterString.toUpperCase();
        ;
    }
    pos =
    ucInString.indexOf(ucBefore,
                       startpos);
    //alert(startpos);
    if ((pos
        != -1)
        && (pos
        < limitpos))
    {
        pos =
        pos
            + ucBefore.length;
        var endpos = ucInString.indexOf(ucAfter,
                                        pos);
        returnString =
        inString.substring(pos,
                           endpos);
    }
    return returnString;
}
// formats date string to "{ts 'yyyy-mm-dd hh:mm:ss'}"
function formatDate(theDateString)
{
    //if (theDateString.toUpperCase().indexOf("UTC")==-1) theDateString + " UTC";
    var v = new Date(theDateString);
    //alert(v);
    var dateString = "";
    if (!isNaN(v.valueOf()))
    {
        var y = v.getFullYear();
        var mo = v.getMonth()
            + 1;
        if (mo
            < 10)
        {
            mo =
            "0"
                + mo;
        }
        var d = v.getDate();
        if (d
            < 10)
        {
            d =
            "0"
                + d;
        }
        var h = v.getHours();
        if (h
            < 10)
        {
            h =
            "0"
                + h;
        }
        var mi = v.getMinutes();
        if (mi
            < 10)
        {
            mi =
            "0"
                + mi;
        }
        var s = v.getSeconds();
        if (s
            < 10)
        {
            s =
            "0"
                + s;
        }
        dateString =
        "{ts \""
            + y
            + "-"
            + mo
            + "-"
            + d;
        if (theDateString.indexOf(":")
            != -1)
        {
            if (v.getHours()
                    + v.getMinutes()
                    + v.getSeconds()
                > 0)
            {
                dateString +=
                " "
                    + h
                    + ":"
                    + mi
                    + ":"
                    + s;
            }
        }
        dateString +=
        "\"}";
    }
    // note: this sets up the formatted date with double quotes,
    //		which will be changed to single by the swapQuotes function called in sendQueryString()
    return dateString;
}
// format decimal numerics from comma to point
// 	SQL format requires English notation
function convertDecimal(theNumString)
{
    var replacer = "."
    var re = /,/g;
    var newString = theNumString.replace(re,
                                         replacer);
    return newString;
}
// test for forbidden tags for this service
function checkForForbiddenTags(theReply)
{
    var startpos = theReply.indexOf("CAPABILITIES forbidden=");
    if (startpos
        != -1)
    {
        startpos =
        startpos
            + 24;
        endpos =
        theReply.indexOf(dQuote,
                         startpos);
        var forbiddenTags = theReply.substring(startpos,
                                               endpos);
        //alert(forbiddenTags);
        if (forbiddenTags.indexOf("GET_IMAGE")
            != -1)
        {
            // No image requests!!!! Abort viewer
            parent.document.location =
            "Abort.htm";
        }
        if (forbiddenTags.indexOf("GET_FEATURES")
            != -1)
        {
            // No id/query requests!!!! Kill buttons
            aimsSelectPresent =
            false;
            aimsQueryPresent =
            false;
            aimsBufferPresent =
            false;
            aimsIdentifyPresent =
            false;
            canQuery =
            false;
            useIdentify =
            false;
            useSelect =
            false;
            useQuery =
            false;
            useFind =
            false;
            useBuffer =
            false;
            useStoredQuery =
            false;
            useHyperLink =
            false;
            useHyperLinkAny =
            false;
            useIdentifyAll =
            false;
            useBufferShape =
            false;
        }
        if (forbiddenTags.indexOf("GET_GEOCODE")
            != -1)
        {
            // No geocode requests!!!! Kill buttons
            aimsGeocodePresent =
            false;
            useGeocode =
            false;
            useReverseGeocode =
            false;
        }
        if (forbiddenTags.indexOf("GET_EXTRACT")
            != -1)
        {
            // No geocode requests!!!! Kill buttons
            useExtract =
            false;
        }
    }
}


