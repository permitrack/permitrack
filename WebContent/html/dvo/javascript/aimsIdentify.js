// aimsIdentify.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js, and aimsLayers.js
 *		To be interactive, dependent also on aimsDHTML.js, aimsClick.js, and aimsNavigation.js
 */
aimsIdentifyPresent =
true;
// hyperlink variables
var currentHyperLinkLayer = "";
var currentHyperLinkField = "";
var currentHyperLinkPrefix = "";
var currentHyperLinkSuffix = "";
var hyperlinkWindowWidth = 500;
var hyperlinkWindowHeight = 400;
// variable for search tolerance... dynamically set in app
var searchTolerance = 1
    / 100;
// starting record position for returned records
var queryStartRecord = 1;
if (imsQueryURL
    == "")
{
    imsQueryURL =
    imsURL
        + "&CustomService=Query";
    //alert(imsQueryURL);
}
var canQuery = true;
var newSelectCount = 0;
var selectPoints = new Array();
// can features be selected if invisible or not within layer scale threshholds?
var canSelectInvisible = false;
/*
 ***************************************************************************************

 Identify functions

 ***************************************************************************************
 */

// identify feature
function identify(e)
{
    if (checkIfActiveLayerAvailable())
    {
        highlightedOne =
        "";
        var theX = mouseX;
        var theY = mouseY;
        getMapXY(theX,
                 theY);
        searchTolerance =
        (xDistance
            / iWidth)
            * pixelTolerance;
        var tempWest = mapX
            - searchTolerance;
        var tempNorth = mapY
            + searchTolerance;
        var tempEast = mapX
            + searchTolerance;
        var tempSouth = mapY
            - searchTolerance;
        //queryStartRecord=1;
        //selectEnvelope='maxy="' + forceComma(tempNorth) + '" maxx="' + forceComma(tempEast) + '" miny="' + forceComma(tempSouth) + '" minx="' + forceComma(tempWest) + '"';
        //selectionMode=2;
        var theString = writeGetFeatures(tempWest,
                                         tempSouth,
                                         tempEast,
                                         tempNorth);
        //if (useTextFrame) parent.TextFrame.document.location = "text.htm";
        //alert(theString);
        showRetrieveData();
        sendToServer(imsQueryURL,
                     theString,
                     identifyXMLMode);
    }
}
// hyperlink from feature in active layer
function hyperLink(e)
{
    if (checkIfActiveLayerAvailable())
    {
        if (currentHyperLinkLayer
            != "")
        {
            highlightedOne =
            "";
            var theX = mouseX;
            var theY = mouseY;
            getMapXY(theX,
                     theY);
            searchTolerance =
            (xDistance
                / iWidth)
                * pixelTolerance;
            var tempWest = mapX
                - searchTolerance;
            var tempNorth = mapY
                + searchTolerance;
            var tempEast = mapX
                + searchTolerance;
            var tempSouth = mapY
                - searchTolerance;
            var tempSwap = swapSelectFields;
            swapSelectFields =
            false;
            var tempSelect = selectFields;
            selectFields =
            LayerIDField[ActiveLayerIndex]
                + " "
                + LayerShapeField[ActiveLayerIndex]
                + " "
                + currentHyperLinkField;
            var theString = writeGetFeatures(tempWest,
                                             tempSouth,
                                             tempEast,
                                             tempNorth);
            selectFields =
            tempSelect;
            swapSelectFields =
            tempSwap;
            showRetrieveData();
            //if (useTextFrame) parent.TextFrame.document.location = "text.htm";
            sendToServer(imsQueryURL,
                         theString,
                         hyperlinkXMLMode);
        }
        else
        {
            alert(msgList[47]);
        }
    }
}
// write out XML request to identify features
function writeGetFeatures(west1, south1, east1, north1)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[ActiveLayerIndex];
    }
    var useString = writeIdentifyXML(ActiveLayer,
                                     ActiveLayerType,
                                     selectFields,
                                     west1,
                                     south1,
                                     east1,
                                     north1,
                                     maxFeaturesReturned,
                                     useLimitExtent);
    hightlightedOne =
    "";
    return useString;
}
function writeIdentifyXML(theLayer, theLayerType, theFields, leftX, bottomY, rightX, topY, maxReturned, hasLimit)
{
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" envelope="false" checkesc ="true" geometry="false" featurelimit="'
                        + maxReturned
        + '">\n';
    theString +=
    '<LAYER id="'
        + theLayer
        + '" />';
    theString +=
    '<SPATIALQUERY subfields="'
        + theFields
        + '">';
    theString +=
    '<SPATIALFILTER relation="area_intersection" >';
    theString +=
    '<ENVELOPE maxy="'
        + forceComma(topY)
        + '" maxx="'
        + forceComma(rightX)
        + '" miny="'
        + forceComma(bottomY)
        + '" minx="'
        + forceComma(leftX)
        + '" />';
    theString +=
    '</SPATIALFILTER>';
    theString +=
    '</SPATIALQUERY>';
    if (hasLimit)
    {
        // keep this within the limitExtent
        theString +=
        '<SPATIALFILTER relation="area_intersection">\n';
        theString +=
        '<ENVELOPE maxx="'
            + forceComma(limitRight)
            + '" maxy="'
            + forceComma(limitTop)
            + '" minx="'
            + forceComma(limitLeft)
            + '" miny="'
            + forceComma(limitBottom)
            + '" />\n';
        theString +=
        '</SPATIALFILTER>\n';
    }
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    return theString;
}
// parse XML response for selected features
function displayAttributeData(theReply)
{
    setLayerFields(ActiveLayerIndex);
    if (LayerName[ActiveLayerIndex]
        == "parcel")
    {
        var url = appDir
            + "displayAttributeData.htm";
    }  //displayAttributeData.htm
    else
    {
        var url = appDir
            + "displayNonParcelAttributeData.htm";
    }
    if (useTextFrame)
    {
        parent.TOCFrame.document.location =
        url;
    }
    else
    {
        var Win1 = open(url,
                        "DataWindow",
                        "width=575,height=250,scrollbars=yes,resizable=yes");
        //extWin = Win1;
        //focusOnData = true;
    }
}
function parseHyperLink(theReply)
{
    //alert(theReply);
    var fList = "";
    var lpos1 = 1;
    var lpos2 = 1;
    var pos = 0;
    var startpos = 0;
    var endpos = 0;
    var fString = theReply.substring(startpos,
                                     endpos);
    var featureCount = justGetFeatureCount(theReply);
    var linkString = "width="
                         + hyperlinkWindowWidth
                         + ",height="
                         + hyperlinkWindowHeight
        + ",scrollbars=yes,resizable=yes"
    var selectedData = "";
    var fCount = featureCount;
    //alert("displayAttributeData()[featurecount=" + featureCount + "]");
    selectCount =
    0;
    var tempString = "";
    if (featureCount
        > 0)
    {
        var searchStr = currentHyperLinkField
            + "=";
        newSelectCount +=
        1;
        endpos =
        1;
        pos =
        theReply.indexOf(searchStr,
                         1);
        startpos =
        pos
            + searchStr.length
            + 1;
        endpos =
        theReply.indexOf(dQuote,
                         startpos);
        inData =
        theReply.substring(startpos,
                           endpos);
        var theLinkURL = currentHyperLinkPrefix
                             + inData
            + currentHyperLinkSuffix;
        if (parent.TextFrame
            != null)
        {
            parent.TextFrame.document.open();
            parent.TextFrame.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                                  + charSet
                                                  + '"><head>');
            parent.TextFrame.document.writeln('	<title>'
                                                  + titleList[9]
                                                  + 's</title>');
            parent.TextFrame.document.writeln('</head>');
            parent.TextFrame.document.writeln('<body BGCOLOR="White" TEXT="Black" LEFTMARGIN=0 TOPMARGIN=0>');
            parent.TextFrame.document.writeln('<FONT FACE="Arial"><B>'
                                                  + LayerName[ActiveLayerIndex]
                                                  + '</B></font><FONT FACE="Arial" size="-2">');
            parent.TextFrame.document.writeln('<br>'
                                                  + msgList[67]
                                                  + theLinkURL);
            parent.TextFrame.document.writeln('</FONT>');
            parent.TextFrame.document.writeln('</body></html>');
            parent.TextFrame.document.close();
        }
        var Win1 = open(theLinkURL,
                        "",
                        linkString);
    }
    else
    {

        //alert(tempString);
        if (parent.TextFrame
            != null)
        {
            parent.TextFrame.document.open();
            parent.TextFrame.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                                  + charSet
                                                  + '"><head>');
            parent.TextFrame.document.writeln('	<title>'
                                                  + titleList[9]
                                                  + '</title>');
            parent.TextFrame.document.writeln('</head>');
            parent.TextFrame.document.writeln('<body BGCOLOR="White" TEXT="Black" LEFTMARGIN=0 TOPMARGIN=0>');
            parent.TextFrame.document.writeln('<FONT FACE="Arial"><B>'
                                                  + LayerName[ActiveLayerIndex]
                                                  + '</B></font><FONT FACE="Arial" size="-2">');
            parent.TextFrame.document.writeln('<br>'
                                                  + msgList[68]);
            if (debugOn
                > 0)
            {
                parent.TextFrame.document.writeln('<p>'
                                                      + msgList[65]
                                                      + '<br>');
                parent.TextFrame.document.writeln(untag(theReply));
            }
            parent.TextFrame.document.writeln('</FONT>');
            parent.TextFrame.document.writeln('</body></html>');
            parent.TextFrame.document.close();
        }
        else
        {
            var msg = msgList[69];
            if (debugOn
                > 0)
            {
                msg =
                msg
                    + msgList[9]
                    + theReply;
            }
            alert(msg);
        }
    }
    hideRetrieveData();
}
// are there any selected features?
function checkSelected()
{
    var isOk = false;
    if (selectCount
        > 0)
    {
        isOk =
        true;
    }
    return isOk;
}
function checkHyperLinkLayer(layerIndex)
{
    var canLink = false;
    //alert(hyperLinkLayers.length);
    for (var i = 0; i
        < hyperLinkLayers.length; i++)
    {
        if (LayerName[layerIndex]
            == hyperLinkLayers[i])
        {
            canLink =
            true;
            currentHyperLinkLayer =
            LayerName[layerIndex];
            currentHyperLinkField =
            hyperLinkFields[i];
            currentHyperLinkPrefix =
            hyperLinkPrefix[i];
            currentHyperLinkSuffix =
            hyperLinkSuffix[i];
        }
    }
    return canLink;
}
// check to see if active layer is in scale threshold and visible
// so it can be used for querying
function checkIfActiveLayerAvailable()
{
    // is the override flag set?
    if (canSelectInvisible)
    {
        return true;
    }
    else
    {
        // is the current scale within the scale min and max?
        if ((mapScaleFactor
            >= LayerMinScale[ActiveLayerIndex])
            && (mapScaleFactor
            <= LayerMaxScale[ActiveLayerIndex]))
        {
            // is the active layer visible?
            if (LayerVisible[ActiveLayerIndex]
                == 1)
            {
                return true;
            }
            else
            {
                var msg = msgList[104]
                              + LayerName[ActiveLayerIndex]
                              + msgList[102]
                              + LayerName[ActiveLayerIndex]
                    + msgList[108];
                alert(msg);
                return false;
            }
        }
        else
        {
            var msg = msgList[104]
                          + LayerName[ActiveLayerIndex]
                + msgList[101];
            //if ((!listAllLayers) && (hasTOC)) msg += msgList[103];
            alert(msg);
            return false;
        }
    }
}
/**************************************************************
 *    functions and variables for Identify All
 *    drill down through visible layers
 **************************************************************/

var replyArray = new Array();
var fID = 0;
var fIndex = 0;
var idEast;
var idWest;
var idSouth;
var idNorth;
// identify feature
function identifyAll(e)
{
    fID =
    0;
    highlightedOne =
    "";
    var theX = mouseX;
    var theY = mouseY;
    getMapXY(theX,
             theY);
    searchTolerance =
    (xDistance
        / iWidth)
        * pixelTolerance;
    var tempWest = mapX
        - searchTolerance;
    var tempNorth = mapY
        + searchTolerance;
    var tempEast = mapX
        + searchTolerance;
    var tempSouth = mapY
        - searchTolerance;
    idSouth =
    tempSouth;
    idNorth =
    tempNorth;
    idWest =
    tempWest;
    idEast =
    tempEast;
    replyArray.length =
    0;
    var j = -1;
    for (var i = (LayerID.length
        - 1); i
        >= 0; i--)
    {
        replyArray[i] =
        "";
        if ((mapScaleFactor
            >= LayerMinScale[i])
                && (mapScaleFactor
            <= LayerMaxScale[i])
                && (LayerVisible[i])
            && (LayerIsFeature[i]))
        {
            j =
            i;
        }
    }
    if (j
        > -1)
    {
        fID =
        j;
        var theString = writeGetFeaturesDrill(tempWest,
                                              tempSouth,
                                              tempEast,
                                              tempNorth,
                                              fID);
        //if (useTextFrame) parent.TextFrame.document.location = "text.htm";
        //alert(theString);
        showRetrieveData();
        sendToServer(imsQueryURL,
                     theString,
                     10);
    }
    else
    {
        alert(msgList[111]);
    }
}
function doIdentifyAll(theReply)
{
    //drill tool loop sequence
    //alert(theReply);
    replyArray[fID] =
    theReply;
    fID++;
    if (fID
        < (LayerID.length))
    {
        if ((mapScaleFactor
            >= LayerMinScale[fID])
                && (mapScaleFactor
            <= LayerMaxScale[fID])
                && (LayerVisible[fID])
            && (LayerIsFeature[fID]))
        {
            theString =
            writeGetFeaturesDrill(idWest,
                                  idSouth,
                                  idEast,
                                  idNorth,
                                  fID);
            //alert(theString);
            sendToServer(imsQueryURL,
                         theString,
                         10);
        }
        else
        {
            doIdentifyAll("");
        }
    }
    if (fID
        == (LayerID.length))
    {
        //createDrillPage = false;
        //replyArray.reverse();
        displayAttributeDataforDrill(replyArray);
        fID =
        0;
        //var ActiveLayerIndex = 4;
    }
}  //end doDrill()


// write XML to identify features for drill ID
function writeGetFeaturesDrill(west1, south1, east1, north1, thefID)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[thefID];
    }
    var useString = writeIdentifyXML(LayerID[thefID],
                                     LayerType[thefID],
                                     selectFields,
                                     west1,
                                     south1,
                                     east1,
                                     north1,
                                     maxFeaturesReturned,
                                     useLimitExtent);
    hightlightedOne =
    "";
    return useString;
}
// parse XML response for selected features
function displayAttributeDataforDrill(theReplyArray)
{
    showRetrieveData();
    var url = appDir
        + "displayAttributeDataAll.htm";
    if (useTextFrame)
    {
        parent.TextFrame.document.location =
        url;
    }
    else
    {
        var Win1 = open(url,
                        "DataWindow",
                        "width=575,height=250,scrollbars=yes,resizable=yes");
    }
}
// checks if any layer has hyperlink at location and links to first one matching
function hyperLinkAny(e)
{
    if (hyperLinkLayers.length
        > 0)
    {
        var j = -1;
        fIndex =
        0;
        for (var i = layerCount
            - 1; i
            > -1; i--)
        {
            if ((mapScaleFactor
                >= LayerMinScale[i])
                    && (mapScaleFactor
                <= LayerMaxScale[i])
                && (LayerVisible[i]
                == 1))
            {
                fIindex =
                -1;
                for (var k = hyperLinkLayers.length
                    - 1; k
                    > -1; k--)
                {
                    if (hyperLinkLayers[k]
                        == LayerName[i])
                    {
                        fIndex =
                        k;
                        j =
                        i;
                    }
                }
            }
        }
        if (j
            > -1)
        {
            fID =
            j;
            highlightedOne =
            "";
            var theX = mouseX;
            var theY = mouseY;
            getMapXY(theX,
                     theY);
            searchTolerance =
            (xDistance
                / iWidth)
                * pixelTolerance;
            var west1 = mapX
                - searchTolerance;
            var north1 = mapY
                + searchTolerance;
            var east1 = mapX
                + searchTolerance;
            var south1 = mapY
                - searchTolerance;
            idSouth =
            south1;
            idNorth =
            north1;
            idWest =
            west1;
            idEast =
            east1;
            var tempSwap = swapSelectFields;
            swapSelectFields =
            false;
            var tempSelect = selectFields;
            selectFields =
            LayerIDField[fID]
                + " "
                + LayerShapeField[fID]
                + " "
                + hyperLinkFields[fIndex];
            var theString = writeIdentifyXML(LayerID[fID],
                                             LayerType[fID],
                                             selectFields,
                                             west1,
                                             south1,
                                             east1,
                                             north1,
                                             maxFeaturesReturned,
                                             useLimitExtent);
            selectFields =
            tempSelect;
            swapSelectFields =
            tempSwap;
            showRetrieveData();
            //if (useTextFrame) parent.TextFrame.document.location = "text.htm";
            sendToServer(imsQueryURL,
                         theString,
                         16);
        }
        else
        {
            alert(msgList[114]);
        }
    }
    else
    {
        alert(msgList[115]);
    }
}
function parseHyperLinkAny(theReply)
{
    //alert(theReply);
    var fList = "";
    var lpos1 = 1;
    var lpos2 = 1;
    var pos = 0;
    var startpos = 0;
    var endpos = 0;
    var fString = theReply.substring(startpos,
                                     endpos);
    var featureCount = justGetFeatureCount(theReply);
    var linkString = "width="
                         + hyperlinkWindowWidth
                         + ",height="
                         + hyperlinkWindowHeight
        + ",scrollbars=yes,resizable=yes"
    var selectedData = "";
    var fCount = featureCount;
    //alert("displayAttributeData()[featurecount=" + featureCount + "]");
    selectCount =
    0;
    var tempString = "";
    if (featureCount
        > 0)
    {
        var searchStr = hyperLinkFields[fIndex]
            + "=";
        newSelectCount +=
        1;
        endpos =
        1;
        pos =
        theReply.indexOf(searchStr,
                         1);
        startpos =
        pos
            + searchStr.length
            + 1;
        endpos =
        theReply.indexOf(dQuote,
                         startpos);
        inData =
        theReply.substring(startpos,
                           endpos);
        var theLinkURL = hyperLinkPrefix[fIndex]
                             + inData
            + hyperLinkSuffix[fIndex];
        if (parent.TextFrame
            != null)
        {
            parent.TextFrame.document.open();
            parent.TextFrame.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                                  + charSet
                                                  + '"><head>');
            parent.TextFrame.document.writeln('	<title>'
                                                  + titleList[9]
                                                  + 's</title>');
            parent.TextFrame.document.writeln('</head>');
            parent.TextFrame.document.writeln('<body BGCOLOR="White" TEXT="Black" LEFTMARGIN=0 TOPMARGIN=0>');
            parent.TextFrame.document.writeln('<FONT FACE="Arial"><B>'
                                                  + LayerName[fID]
                                                  + '</B></font><FONT FACE="Arial" size="-2">');
            parent.TextFrame.document.writeln('<br>'
                                                  + msgList[67]
                                                  + theLinkURL);
            parent.TextFrame.document.writeln('</FONT>');
            parent.TextFrame.document.writeln('</body></html>');
            parent.TextFrame.document.close();
        }
        var Win1 = open(theLinkURL,
                        "",
                        linkString);
    } else if (fID
        < layerCount)
    {
        fID++;
        var j = -1;
        if ((mapScaleFactor
            >= LayerMinScale[fID])
                && (mapScaleFactor
            <= LayerMaxScale[fID])
            && (LayerVisible[fID]
            == 1))
        {
            fIindex =
            -1;
            for (var k = hyperLinkLayers.length
                - 1; k
                > -1; k--)
            {
                if (hyperLinkLayers[k]
                    == LayerName[fID])
                {
                    fIndex =
                    k;
                    j =
                    fID;
                }
            }
        }
        if (j
            > -1)
        {
            var tempSwap = swapSelectFields;
            swapSelectFields =
            false;
            var tempSelect = selectFields;
            selectFields =
            LayerIDField[fID]
                + " "
                + LayerShapeField[fID]
                + " "
                + hyperLinkFields[fIndex];
            var theString = writeIdentifyXML(LayerID[fID],
                                             LayerType[fID],
                                             selectFields,
                                             idWest,
                                             idSouth,
                                             idEast,
                                             idNorth,
                                             maxFeaturesReturned,
                                             useLimitExtent);
            selectFields =
            tempSelect;
            swapSelectFields =
            tempSwap;
            showRetrieveData();
            //if (useTextFrame) parent.TextFrame.document.location = "text.htm";
            sendToServer(imsQueryURL,
                         theString,
                         16);
        }
        else
        {
            parseHyperLinkAny("");
        }
    }
    else
    {

        //alert(tempString);
        if (parent.TextFrame
            != null)
        {
            parent.TextFrame.document.open();
            parent.TextFrame.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                                  + charSet
                                                  + '"><head>');
            parent.TextFrame.document.writeln('	<title>'
                                                  + titleList[9]
                                                  + '</title>');
            parent.TextFrame.document.writeln('</head>');
            parent.TextFrame.document.writeln('<body BGCOLOR="White" TEXT="Black" LEFTMARGIN=0 TOPMARGIN=0>');
            parent.TextFrame.document.writeln('<FONT FACE="Arial" size="-1">');
            //parent.TextFrame.document.writeln('<B>' + LayerName[ActiveLayerIndex] +'</B></font><FONT FACE="Arial" size="-2">');
            parent.TextFrame.document.writeln('<br>'
                                                  + msgList[116]);
            /*
             if (debugOn>0) {
             parent.TextFrame.document.writeln('<p>' + msgList[65] + '<br>');
             parent.TextFrame.document.writeln(untag(theReply));
             }
             */
            parent.TextFrame.document.writeln('</FONT>');
            parent.TextFrame.document.writeln('</body></html>');
            parent.TextFrame.document.close();
        }
        else
        {
            var msg = msgList[69];
            if (debugOn
                > 0)
            {
                msg =
                msg
                    + msgList[9]
                    + theReply;
            }
            alert(msg);
        }
    }
    hideRetrieveData();
}

