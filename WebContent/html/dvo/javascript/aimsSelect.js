// aimsSelect.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js, aimsIdentify, and aimsLayers.js
 *		To be interactive, dependent also on aimsDHTML.js, aimsClick.js, and aimsNavigation.js
 */
aimsSelectPresent =
true;
var selectData = new Array();
var selectLeft = new Array();
var selectRight = new Array();
var selectTop = new Array();
var selectBottom = new Array();
var selMaxEnvelope = new Array();
var selectLayer = "";
var selectType = "";
var selectCount = 0;
var selectBlurb = "";
var selectEnvelope = "";
var highlightedOne = "";
//mode - 0=selection; 1=query
var queryMode = 1;
//mode - 1=query; 2=box,point; 3=line,polygon
var selectionMode = 1;
var setQueryString = "";
var shapeBufferDistance = 0;
/*
 ***************************************************************************************

 Selection functions

 ***************************************************************************************
 */

// select feature
function select(e)
{

    //getImageXY(e);
    getMapXY(mouseX,
             mouseY);
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
    queryStartRecord =
    1;
    selectEnvelope =
    'maxy="'
        + forceComma(tempNorth)
        + '" maxx="'
        + forceComma(tempEast)
        + '" miny="'
        + forceComma(tempSouth)
        + '" minx="'
        + forceComma(tempWest)
        + '"';
    selectionMode =
    2;
    showRetrieveData();
    var theString = writeGetFeatures2(tempWest,
                                      tempSouth,
                                      tempEast,
                                      tempNorth);
    sendToServer(imsQueryURL,
                 theString,
                 selectXMLMode);
}
// start select box display
function startSelectBox(e)
{
    if (checkIfActiveLayerAvailable())
    {
        moveLayer("theMap",
                  hspc,
                  vspc);
        getImageXY(e);
        // keep it within the MapImage
        if ((mouseX
            < iWidth)
            && (mouseY
            < iHeight))
        {
            if (selectBox)
            {
                stopSelectBox(e);
            }
            else
            {
                x1 =
                mouseX;
                y1 =
                mouseY
                x2 =
                x1
                    + 1;
                y2 =
                y1
                    + 1;
                zleft =
                x1;
                ztop =
                y1;
                zbottom =
                y1;
                zright =
                x1
                boxIt(x1,
                      y1,
                      x2,
                      y2);
                /*
                 clipLayer("zoomBoxTop",x1,y1,x2,y2);
                 clipLayer("zoomBoxLeft",x1,y1,x2,y2);
                 clipLayer("zoomBoxRight",x1,y1,x2,y2);
                 clipLayer("zoomBoxBottom",x1,y1,x2,y2);
                 */
                selectBox =
                true;
                //if (isNav4) {
                showLayer("zoomBoxTop");
                showLayer("zoomBoxLeft");
                showLayer("zoomBoxRight");
                showLayer("zoomBoxBottom");
                //} else {
                //	showLayer("zoomBox");
                //}
            }
            highlightedOne =
            "";
        }
        return false;
    }
}
// stop select box display..... select
function stopSelectBox(e)
{
    selectBox =
    false;
    /*
     var tempLeft=lastLeft;
     var tempRight=lastRight;
     var tempTop=lastTop;
     var tempBottom=lastBottom;
     */
    //if (isNav4) {
    hideLayer("zoomBoxTop");
    hideLayer("zoomBoxLeft");
    hideLayer("zoomBoxRight");
    hideLayer("zoomBoxBottom");
    //} else {
    //	showLayer("zoomBox");
    //}
    // /*
    if ((zright
        < zleft
        + 2)
        && (zbottom
        < ztop
        + 2))
    {

        //getMapXY(mouseX-pixelTolerance,mouseY-pixelTolerance);
        getMapXY(mouseX,
                 mouseY);
        searchTolerance =
        (xDistance
            / iWidth)
            * pixelTolerance;
        tempLeft =
        mapX
            - searchTolerance;
        tempTop =
        mapY
            - searchTolerance;
        tempRight =
        mapX
            + searchTolerance;
        tempBottom =
        mapY
            + searchTolerance;
        //select(e);
    }
    else
    {
        // */
        pixelX =
        xDistance
            / iWidth;
        var theY = iHeight
            - ztop;
        pixelY =
        yDistance
            / iHeight;
        tempTop =
        pixelY
            * theY
            + eBottom;
        tempRight =
        pixelX
            * zright
            + eLeft;
        tempLeft =
        pixelX
            * zleft
            + eLeft;
        theY =
        iHeight
            - zbottom;
        pixelY =
        yDistance
            / iHeight;
        tempBottom =
        pixelY
            * theY
            + eBottom;
    }
    window.scrollTo(0,
                    0);
    queryStartRecord =
    1;
    var theString = writeGetFeatures2(tempLeft,
                                      tempBottom,
                                      tempRight,
                                      tempTop);
    selectEnvelope =
    'maxy="'
        + forceComma(tempTop)
        + '" maxx="'
        + forceComma(tempRight)
        + '" miny="'
        + forceComma(tempBottom)
        + '" minx="'
        + forceComma(tempLeft)
        + '"';
    drawSelectBoundary =
    true;
    showBuffer =
    false;
    selectionMode =
    2;
    //if (useTextFrame) parent.TextFrame.document.location = "text.htm";
    sendToServer(imsQueryURL,
                 theString,
                 selectXMLMode);
    return true;
}
// write out XML request to select features
function writeGetFeatures2(west1, south1, east1, north1)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[ActiveLayerIndex];
        //alert(selectFields);
    }
    var theEnvelope = 'maxy="'
                          + forceComma(north1)
                          + '" maxx="'
                          + forceComma(east1)
                          + '" miny="'
                          + forceComma(south1)
                          + '" minx="'
                          + forceComma(west1)
        + '"';
    var theString = writeEnvelopeXML(ActiveLayer,
                                     ActiveLayerType,
                                     selectFields,
                                     maxFeaturesReturned,
                                     queryStartRecord,
                                     theEnvelope,
                                     useLimitExtent);
    //alert(theString);
    selectLayer =
    ActiveLayer;
    selectType =
    ActiveLayerType;
    selectCount =
    0;
    hightlightedOne =
    "";
    selectPoints.length =
    1;
    selectLeft.length =
    1;
    selectRight.length =
    1;
    selectTop.length =
    1;
    selectBottom.length =
    1;
    return theString;
}
function writeGetFeatures3()
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[ActiveLayerIndex];
    }
    var theString = writeEnvelopeXML(ActiveLayer,
                                     ActiveLayerType,
                                     selectFields,
                                     maxFeaturesReturned,
                                     queryStartRecord,
                                     selectEnvelope,
                                     useLimitExtent);
    //alert(theString);
    selectLayer =
    ActiveLayer;
    selectType =
    ActiveLayerType;
    selectCount =
    0;
    hightlightedOne =
    "";
    selectPoints.length =
    1;
    selectLeft.length =
    1;
    selectRight.length =
    1;
    selectTop.length =
    1;
    selectBottom.length =
    1;
    return theString;
}
// generic envelope select xml write routine
function writeEnvelopeXML(theLayer, theLayerType, theFields, maxReturned, startRec, theEnvelope, hasLimit)
{
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" envelope="true" geometry="false" checkesc ="true"';
    theString +=
    ' featurelimit="'
        + maxReturned
        + '" beginrecord="'
        + startRec
        + '">\n';
    theString +=
    '<LAYER id="'
        + theLayer
        + '" />';
    theString +=
    '<SPATIALQUERY subfields="'
        + theFields
        + '">';
    //theString += '<SPATIALFILTER relation=envelope_intersection >';
    theString +=
    '<SPATIALFILTER relation="area_intersection" >';
    theString +=
    '<ENVELOPE '
        + theEnvelope
        + ' />';
    theString +=
    '</SPATIALFILTER>';
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
    '</SPATIALQUERY>';
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    return theString;
}
// write out xml request for selection by shape
function writeShapeSelect(theType)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[ActiveLayerIndex];
    }
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" envelope="true" geometry="false" checkesc ="true"';
    theString +=
    ' featurelimit="'
        + maxFeaturesReturned
        + '" beginrecord="'
        + queryStartRecord
        + '">\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" />';
    theString +=
    '<SPATIALQUERY subfields="'
        + selectFields
        + '">';
    theString +=
    '<SPATIALFILTER relation="area_intersection" >';
    if ((theType
        == 3)
        && (shapeBufferDistance
        > 0))
    {
        theString +=
        '<BUFFER distance="'
            + forceComma(shapeBufferDistance)
            + '" ';
        theString +=
        ' bufferunits="'
            + ScaleBarUnits
            + '"';
        theString +=
        ' />\n';
    }
    if (theType
        == 1)
    {
        theString +=
        '<POLYLINE>\n<PATH>\n';
    } else if (theType
        == 2)
    {
        theString +=
        '<POLYGON>\n<RING>\n';
    }
    else
    {
        theString +=
        '<MULTIPOINT>\n';
    }
    if (theType
        == 0)
    {
        theString +=
        '<POINT x="'
            + clickPointX[clickCount
            - 1]
            + '" y="'
            + clickPointY[clickCount
            - 1]
            + '" />\n';
    } else if (theType
        == 3)
    {
        theString +=
        '<POINT x="'
            + circleCenterX
            + '" y="'
            + circleCenterY
            + '" />\n';
    }
    else
    {
        for (var i = 0; i
            < clickCount; i++)
        {
            theString +=
            '<POINT x="'
                + clickPointX[i]
                + '" y="'
                + clickPointY[i]
                + '" />\n';
        }
    }
    if (theType
        == 0)
    {
        theString +=
        '</MULTIPOINT>\n';
    } else if (theType
        == 2)
    {
        theString +=
        '</RING>\n</POLYGON>\n';
    } else if (theType
        == 3)
    {
        theString +=
        '</MULTIPOINT>\n';
    }
    else
    {
        theString +=
        '</PATH>\n</POLYLINE>\n';
    }
    theString +=
    '</SPATIALFILTER>';
    if (useLimitExtent)
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
    '</SPATIALQUERY>';
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    //alert(theString);
    selectLayer =
    ActiveLayer;
    selectType =
    ActiveLayerType;
    selectCount =
    0;
    hightlightedOne =
    "";
    selectPoints.length =
    1;
    selectLeft.length =
    1;
    selectRight.length =
    1;
    selectTop.length =
    1;
    selectBottom.length =
    1;
    //alert(theString);
    return theString;
}
// request a selection using a shape
function sendShapeSelect(theType)
{
    queryStartRecord =
    1;
    selectionMode =
    3;
    showBuffer =
    false;
    if (theType
        == 2)
    {
        if ((clickPointX[0]
            != clickPointX[clickCount
            - 1])
            && (clickPointY[0]
            != clickPointY[clickCount
            - 1]))
        {
            clickPointX[clickCount] =
            clickPointX[0];
            clickPointY[clickCount] =
            clickPointY[0];
            clickCount =
            clickCount
                + 1;
        }
    }
    showRetrieveData();
    var theString = writeShapeSelect(theType);
    //alert(theString);
    //alert(selectXMLMode);
    sendToServer(imsQueryURL,
                 theString,
                 selectXMLMode);
}
// request more records to display
function getMoreData(startRecord)
{
    var theString = "";
    queryStartRecord =
    startRecord;
    if (selectionMode
        == 1)
    {
        //query
        theString =
        writeQueryXML(setQueryString);
    } else if (selectionMode
        == 2)
    {
        theString =
        writeGetFeatures3();
    }
    else
    {
        var theType = clickType
            - 1;
        theString =
        writeShapeSelect(theType);
    }
    showRetrieveData();
    sendToServer(imsQueryURL,
                 theString,
                 selectXMLMode);
}
// refresh map with hightlighted selection
function showHighlight(selNum)
{
    highlightedOne =
    LayerIDField[ActiveLayerIndex]
        + " = "
        + selectPoints[selNum];
    //alert(selectLeft[selNum] + "," + selectRight[selNum]);
    var fWidth = selectRight[selNum]
        - selectLeft[selNum];
    var fHeight = selectTop[selNum]
        - selectBottom[selNum];
    var mWMargin = 0;
    var mHMargin = 0;
    if (selectType
        == "point")
    {
        mWMargin =
        fullWidth
            * selectPointMargin;
        mHMargin =
        fullHeight
            * selectPointMargin;
        if (mWMargin
            > xDistance
            / 2)
        {
            mWMargin =
            xDistance
                / 2;
            mHMargin =
            yDistance
                / 2;
        }
    }
    else
    {
        mWMargin =
        fWidth
            * selectMargin;
        mHMargin =
        fHeight
            * selectMargin;
    }
    saveLastExtent();
    eLeft =
    selectLeft[selNum]
        - mWMargin;
    eRight =
    selectRight[selNum]
        + mWMargin;
    eTop =
    selectTop[selNum]
        + mHMargin;
    eBottom =
    selectBottom[selNum]
        - mHMargin;
    //alert(highlightedOne);
    legendTemp =
    legendVisible;
    legendVisible =
    false;
    sendMapXML();
}
// calculate max envelope for returned records
function calcSelectEnvelope()
{
    //alert(selectCount);
    if (selectCount
        > 0)
    {
        selMaxEnvelope[0] =
        parseFloat(selectLeft[0]);
        selMaxEnvelope[1] =
        parseFloat(selectBottom[0]);
        selMaxEnvelope[2] =
        parseFloat(selectRight[0]);
        selMaxEnvelope[3] =
        parseFloat(selectTop[0]);
        //alert("0 - " + selMaxEnvelope[0] + "," + selMaxEnvelope[1] + "," + selMaxEnvelope[2] + "," + selMaxEnvelope[3]);
        if (selectCount
            > 1)
        {
            for (var i = 1; i
                < selectCount; i++)
            {
                if (parseFloat(selectLeft[i])
                    < selMaxEnvelope[0])
                {
                    selMaxEnvelope[0] =
                    parseFloat(selectLeft[i]);
                }
                if (parseFloat(selectBottom[i])
                    < selMaxEnvelope[1])
                {
                    selMaxEnvelope[1] =
                    parseFloat(selectBottom[i]);
                }
                if (parseFloat(selectRight[i])
                    > selMaxEnvelope[2])
                {
                    selMaxEnvelope[2] =
                    parseFloat(selectRight[i]);
                }
                if (parseFloat(selectTop[i])
                    > selMaxEnvelope[3])
                {
                    selMaxEnvelope[3] =
                    parseFloat(selectTop[i]);
                }
                //alert(i + " - " + selMaxEnvelope[0] + "," + selMaxEnvelope[1] + "," + selMaxEnvelope[2] + "," + selMaxEnvelope[3]);
            }
        }
    }
}
function zoomToReturnedRecords()
{
    calcSelectEnvelope();
    var fWidth = selMaxEnvelope[2]
        - selMaxEnvelope[0];
    var fHeight = selMaxEnvelope[3]
        - selMaxEnvelope[1];
    var mWMargin = 0;
    var mHMargin = 0;
    if ((fWidth
        == 0)
        && (fHeight
        == 0))
    {
        mWMargin =
        fullWidth
            * selectPointMargin;
        mHMargin =
        fullHeight
            * selectPointMargin;
        if (mWMargin
            > xDistance
            / 2)
        {
            mWMargin =
            xDistance
                / 2;
            mHMargin =
            yDistance
                / 2;
        }
    }
    else
    {
        mWMargin =
        fWidth
            * selectMargin;
        mHMargin =
        fHeight
            * selectMargin;
    }
    saveLastExtent();
    eLeft =
    selMaxEnvelope[0]
        - mWMargin;
    eRight =
    selMaxEnvelope[2]
        + mWMargin;
    eTop =
    selMaxEnvelope[3]
        + mHMargin;
    eBottom =
    selMaxEnvelope[1]
        - mHMargin;
    //legendTemp=legendVisible;
    //legendVisible=false;
    sendMapXML();
}
// clear current selection
function clearSelection()
{
    var theCount = selectCount;
    var theHL = highlightedOne;
    selectCount =
    0;
    showBuffer =
    false;
    highlightedOne =
    "";
    selectPoints.length =
    0;
    selectLeft.length =
    0;
    selectRight.length =
    0;
    selectTop.length =
    0;
    selectBottom.length =
    0;
    drawSelectBoundary =
    false;
    showGeocode =
    false;
    clickCount =
    0;
    totalMeasure =
    0;
    currentMeasure =
    0;
    if (useTextFrame)
    {
        parent.TextFrame.document.open();
        parent.TextFrame.document.writeln('<meta http-equiv="Content-Type" content="text/html; charset='
                                              + charSet
                                              + '"><html>');
        parent.TextFrame.document.writeln('<body bgcolor="White" text="Black" link="Blue" vlink="Blue">');
        parent.TextFrame.document.writeln('<FONT FACE="Arial" SIZE="-1"><b>'
                                              + LayerName[ActiveLayerIndex]
                                              + '</b><br>');
        if (toolMode
            == 20)
        {
            parent.TextFrame.document.writeln(msgList[70]);
            updateMeasureBox()
        }
        else
        {
            parent.TextFrame.document.writeln(msgList[71]);
        }
        parent.TextFrame.document.writeln('</body></html>');
        parent.TextFrame.document.close()
    }
    else
    {
        if (toolMode
            == 20)
        {
            showLayer("measureBox");
            updateMeasureBox()
            alert(msgList[70]);
        }
        else
        {
            alert(msgList[71]);
        }
    }
    //if ((theCount>0) || (theHL!="")) {
    //legendTemp=legendVisible;
    //legendVisible=false;
    selectBlurb =
    "";
    //showLayer("LoadMap");
    //var theString = writeXML();
    //sendToServer(imsURL,theString,99);
    sendMapXML();
    //}
}
// add Draw Selected Features to Map XML request
function addSelectToMap()
{
    var selString = "";
    var sFactor = (eRight
        - eLeft)
        / iWidth
    if (((sFactor
        >= LayerMinScale[ActiveLayerIndex])
             && (sFactor
        <= LayerMaxScale[ActiveLayerIndex])
        && (LayerVisible[ActiveLayerIndex]
        == 1))
        || (canSelectInvisible))
    {
        if ((selectCount
            > 0)
            && (showSelectedFeatures))
        {
            selString +=
            '<LAYER type="featureclass" name="Selected Features" visible="true">\n';
            //selString +='<DATASET fromlayer="' + ActiveLayer + '" />\n';
            selString +=
            '<DATASET fromlayer="'
                + LayerID[ActiveLayerIndex]
                + '" />\n';
            if (selectionMode
                == 1)
            {
                selString +=
                '<SPATIALQUERY  where="'
                    + setQueryString
                    + '"';
                if (useLimitExtent)
                {
                    // keep this within the limitExtent
                    selString +=
                    '>\n<SPATIALFILTER relation="area_intersection">\n';
                    selString +=
                    '<ENVELOPE maxx="'
                        + forceComma(limitRight)
                        + '" maxy="'
                        + forceComma(limitTop)
                        + '" minx="'
                        + forceComma(limitLeft)
                        + '" miny="'
                        + forceComma(limitBottom)
                        + '" />\n';
                    selString +=
                    '</SPATIALFILTER>\n';
                    selString +=
                    '</SPATIALQUERY>\n';
                }
                else
                {
                    selString +=
                    '/>\n';
                }
            }
            else
            {
                selString +=
                '<SPATIALQUERY>\n';
                selString +=
                '<SPATIALFILTER relation="area_intersection" >\n';
                if (selectionMode
                    == 2)
                {
                    selString +=
                    '<ENVELOPE '
                        + selectEnvelope
                        + ' />\n';
                }
                else
                {
                    if ((shapeSelectBuffer)
                        && (shapeBufferDistance
                        > 0))
                    {
                        //if (shapeBufferDistance>0) {
                        // do a buffer around the shape before selecting
                        selString +=
                        '<BUFFER distance="'
                            + forceComma(shapeBufferDistance)
                            + '" ';
                        selString +=
                        ' bufferunits="'
                            + ScaleBarUnits
                            + '"';
                        selString +=
                        ' />\n';
                    }
                    if (clickType
                        == 2)
                    {
                        selString +=
                        '<POLYLINE>\n<PATH>\n';
                    } else if (clickType
                        == 3)
                    {
                        selString +=
                        '<POLYGON>\n<RING>\n';
                    }
                    else
                    {
                        selString +=
                        '<MULTIPOINT>\n';
                    }
                    if (clickType
                        == 1)
                    {
                        selString +=
                        '<POINT x="'
                            + clickPointX[clickCount
                            - 1]
                            + '" y="'
                            + clickPointY[clickCount
                            - 1]
                            + '" />\n';
                    }
                    else
                    {
                        for (var i = 0; i
                            < clickCount; i++)
                        {
                            selString +=
                            '<POINT x="'
                                + clickPointX[i]
                                + '" y="'
                                + clickPointY[i]
                                + '" />\n';
                        }
                    }
                    if (clickType
                        == 3)
                    {
                        //selString +='<POINT x="' + clickPointX[0] + '" y="' + clickPointY[0] + '" />\n';
                        selString +=
                        '</RING>\n</POLYGON>\n';
                    } else if (clickType
                        == 2)
                    {
                        selString +=
                        '</PATH>\n</POLYLINE>\n';
                    }
                    else
                    {
                        selString +=
                        '</MULTIPOINT>\n';
                    }
                }
                selString +=
                '</SPATIALFILTER>\n';
                ///*
                if (useLimitExtent)
                {
                    // keep this within the limitExtent
                    selString +=
                    '<SPATIALFILTER relation="area_intersection">\n';
                    selString +=
                    '<ENVELOPE maxx="'
                        + forceComma(limitRight)
                        + '" maxy="'
                        + forceComma(limitTop)
                        + '" minx="'
                        + forceComma(limitLeft)
                        + '" miny="'
                        + forceComma(limitBottom)
                        + '" />\n';
                    selString +=
                    '</SPATIALFILTER>\n';
                }
                //*/
                selString +=
                '</SPATIALQUERY>\n';
            }
            selString +=
            '<SIMPLERENDERER>\n';
            if (selectType
                == "point")
            {
                selString +=
                '<SIMPLEMARKERSYMBOL color="'
                    + selectColor
                    + '" type="Circle" width="10" />\n';
            } else if (selectType
                == "line")
            {
                selString +=
                '<SIMPLELINESYMBOL type="SOLID" color="'
                    + selectColor
                    + '" width="3" />\n';
            }
            else
            {
                //selString +='<SIMPLEPOLYGONSYMBOL FILLCOLOR="' + selectColor + '" FILLTYPE="Gray" ';
                //selString +='<SIMPLEPOLYGONSYMBOL fillcolor="' + selectColor + '" filltype="solid" transparency="' + forceComma(transparentLevel) + '" boundarycolor="255,255,255" />\n';
                //				selString +='<SIMPLEPOLYGONSYMBOL boundarytransparency="0.5" filltransparency="0.3" fillcolor="210,255,200" boundarywidth="3" boundarycaptype="round" boundarycolor="0,0,225" />\n';
                selString +=
                '<SIMPLEPOLYGONSYMBOL boundarytransparency="0.5" filltransparency="0.3" fillcolor="255,0,0" boundarywidth="3" boundarycaptype="round" boundarycolor="255,0,0" />\n';
            }
            selString +=
            '</SIMPLERENDERER>\n';
            selString +=
            '</LAYER>\n';
        }
        if (selectBlurb
            != "")
        {
            // add SelectBlurb to Map XML request
            selString +=
            '<LAYER type="featureclass" name="Selected Features" visible="true">\n';
            //selString +='<DATASET fromlayer="' + ActiveLayer + '" />\n';
            selString +=
            '<DATASET fromlayer="'
                + LayerID[ActiveLayerIndex]
                + '" />\n';
            selString +=
            '<QUERY where="'
                + selectBlurb
                + '" />\n';
            selString +=
            '<SIMPLERENDERER>\n';
            if (selectType
                == "point")
            {
                selString +=
                '<SIMPLEMARKERSYMBOL color="'
                    + selectColor
                    + '" type="Circle" width="10" />\n';
            } else if (selectType
                == "line")
            {
                selString +=
                '<SIMPLELINESYMBOL type="SOLID" color="'
                    + selectColor
                    + '" width="3" />\n';
            }
            else
            {
                selString +=
                '<SIMPLEPOLYGONSYMBOL fillcolor="'
                    + selectColor
                    + '" filltype="solid" transparency="'
                    + forceComma(transparentLevel)
                    + '" boundarycolor="255,255,255" />\n';
            }
            selString +=
            '</SIMPLERENDERER>\n';
            selString +=
            '</LAYER>\n';
        }
        if (highlightedOne
            != "")
        {
            // add Draw Highlighed Feature to Map XML request
            selString +=
            '<LAYER type="featureclass" name="Highlighted Feature" visible="true">\n';
            //selString +='<DATASET fromlayer="' + ActiveLayer + '" />\n';
            selString +=
            '<DATASET fromlayer="'
                + LayerID[ActiveLayerIndex]
                + '" />\n';
            selString +=
            '<QUERY where="'
                + highlightedOne
                + '" />\n';
            selString +=
            '<SIMPLERENDERER>\n';
            if (selectType
                == "point")
            {
                selString +=
                '<SIMPLEMARKERSYMBOL color="'
                    + highlightColor
                    + '" type="Circle" width="10" />\n';
            } else if (selectType
                == "line")
            {
                selString +=
                '<SIMPLELINESYMBOL type="SOLID" color="'
                    + highlightColor
                    + '" width="3" />\n';
            }
            else
            {
                //selString +='<SIMPLEPOLYGONSYMBOL FILLCOLOR="' + highlightColor + '" FILLTYPE="Gray" ';
                selString +=
                '<SIMPLEPOLYGONSYMBOL fillcolor="'
                    + highlightColor
                    + '" filltype="solid" transparency="'
                    + forceComma(transparentLevel)
                    + '" boundarycolor="255,255,255" />\n';
            }
            selString +=
            '</SIMPLERENDERER>\n';
            selString +=
            '</LAYER>\n';
            //alert(selString);
        }
    }
    return selString;
}

