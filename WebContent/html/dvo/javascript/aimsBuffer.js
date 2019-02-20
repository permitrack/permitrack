// aimsBuffer.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js, aimsIdentify.js,
 *			aimsSelect.js, aimsQuery.js, and aimsLayers.js
 *		To be interactive, dependent also on aimsDHTML.js, aimsClick.js, and aimsNavigation.js
 */
aimsBufferPresent =
true;
var drawTargetLayer = true;
var bufferTargetLayer = "";
var bufferTargetLayerIndex = 0;
var bufferDistance = 350;
var bufferSmoothEdges = 1
    / 100;
var getBufferedData = false;
/*
 ***************************************************************************************

 Buffer functions

 ***************************************************************************************
 */
function writeBufferForm()
{
    var url = appDir
        + "buffer.htm";
    parent.TOCFrame.location =
    url;
}
// buffer around selected features
function bufferIt()
{
    hideLayer("measureBox");
    //alert("Function not yet enabled.");
    showBuffer =
    true;
    //	sendMapXML();
    var buffString1 = writeGetBufferedData();
    if (buffString1
        != "")
    {
        sendToServer(imsQueryURL,
                     buffString1,
                     1001);
    }
    else
    {
        alert(msgList[15]);
    }
}
// add buffer stuff to Map XML request
function addBufferToMap()
{
    var buffString = "";
    //bufferSmoothEdges = bufferDistance /100;
    if (selectionMode
        == 1)
    {
        if (drawTargetLayer)
        {
            buffString +=
            '<LAYER type="featureclass" name="theBufferTarget" visible="true">\n';
            buffString +=
            '<DATASET fromlayer="'
                + LayerID[ActiveLayerIndex]
                + '" />\n';
            buffString +=
            '<SPATIALQUERY where="'
                + setQueryString
                + '" >\n';
            buffString +=
            '<BUFFER distance="'
                + forceComma(bufferDistance)
                + '" ';
            buffString +=
            ' bufferunits="'
                + ScaleBarUnits.toLowerCase()
                + '"';
            buffString +=
            '>\n';
            if (useLimitExtent)
            {
                // keep this within the limitExtent
                buffString +=
                '<SPATIALQUERY>\n';
                buffString +=
                '<SPATIALFILTER relation="area_intersection">\n';
                buffString +=
                '<ENVELOPE maxx="'
                    + forceComma(limitRight)
                    + '" maxy="'
                    + forceComma(limitTop)
                    + '" minx="'
                    + forceComma(limitLeft)
                    + '" miny="'
                    + forceComma(limitBottom)
                    + '" />\n';
                buffString +=
                '</SPATIALFILTER>\n';
                buffString +=
                '</SPATIALQUERY>\n';
            }
            buffString +=
            '<TARGETLAYER id="'
                + LayerID[bufferTargetLayerIndex]
                + '" />\n';
            buffString +=
            '</BUFFER>\n';
            buffString +=
            '</SPATIALQUERY>\n';
            buffString +=
            '<SIMPLERENDERER>\n';
            var tlType = LayerType[bufferTargetLayerIndex];
            if (tlType
                == "point")
            {
                buffString +=
                '<SIMPLEMARKERSYMBOL color="'
                    + highlightColor
                    + '" type="Circle" width="10" />\n';
            } else if (tlType
                == "line")
            {
                buffString +=
                '<SIMPLELINESYMBOL color="'
                    + highlightColor
                    + '" width="2" />\n';
            }
            else
            {
                buffString +=
                '<SIMPLEPOLYGONSYMBOL fillcolor="'
                    + highlightColor
                    + '" filltype="solid" transparency="'
                    + forceComma(5
                                     / 10)
                    + '" boundarycolor="255,255,255" />\n';
            }
            buffString +=
            '</SIMPLERENDERER>\n';
            buffString +=
            '</LAYER>\n';
        }
        buffString +=
        '<LAYER type="featureclass" name="theBufferPolygons" visible="true">\n';
        buffString +=
        '<DATASET fromlayer="'
            + LayerID[ActiveLayerIndex]
            + '" />\n';
        buffString +=
        '<SPATIALQUERY where="'
            + setQueryString
            + '" >\n';
        buffString +=
        '<BUFFER distance="'
            + forceComma(bufferDistance)
            + '" bufferunits="'
            + ScaleBarUnits.toLowerCase()
            + '" />\n';
        buffString +=
        '</SPATIALQUERY>\n';
        buffString +=
        '<SIMPLERENDERER>\n';
        buffString +=
        '<SIMPLEPOLYGONSYMBOL fillcolor="100,100,100" filltype="solid" filltransparency="0" boundarywidth="2" boundarycolor="255,0,0" />\n';
        buffString +=
        '</SIMPLERENDERER>\n';
        buffString +=
        '</LAYER>\n';
    } else if ((selectionMode
        == 2)
        || (selectionMode
        == 3))
    {
        if (drawTargetLayer)
        {
            buffString +=
            '<LAYER type="featureclass" name="theBufferTarget" visible="true">\n';
            buffString +=
            '<DATASET fromlayer="'
                + LayerID[ActiveLayerIndex]
                + '" />\n';
            buffString +=
            '<SPATIALQUERY>\n';
            buffString +=
            '<BUFFER distance="'
                + forceComma(bufferDistance)
                + '" bufferunits="'
                + ScaleBarUnits.toLowerCase()
                + '">\n';
            buffString +=
            '<TARGETLAYER id="'
                + LayerID[bufferTargetLayerIndex]
                + '" />\n';
            if (useLimitExtent)
            {
                // keep this within the limitExtent
                buffString +=
                '<SPATIALQUERY>\n';
                buffString +=
                '<SPATIALFILTER relation="area_intersection">\n';
                buffString +=
                '<ENVELOPE maxx="'
                    + forceComma(limitRight)
                    + '" maxy="'
                    + forceComma(limitTop)
                    + '" minx="'
                    + forceComma(limitLeft)
                    + '" miny="'
                    + forceComma(limitBottom)
                    + '" />\n';
                buffString +=
                '</SPATIALFILTER>\n';
                buffString +=
                '</SPATIALQUERY>\n';
            }
            buffString +=
            '</BUFFER>\n';
            // select rectangle or shape
            buffString +=
            '<SPATIALFILTER relation="area_intersection">\n';
            if (selectionMode
                == 2)
            {
                buffString +=
                '<ENVELOPE '
                    + selectEnvelope
                    + ' />\n';
            }
            else
            {
                if (clickType
                    == 2)
                {
                    buffString +=
                    '<POLYLINE>\n<PATH>\n';
                } else if (clickType
                    == 3)
                {
                    buffString +=
                    '<POLYGON>\n<RING>\n';
                }
                else
                {
                    buffString +=
                    '<MULTIPOINT>\n';
                }
                for (var i = 0; i
                    < clickCount; i++)
                {
                    buffString +=
                    '<POINT x="'
                        + clickPointX[i]
                        + '" y="'
                        + clickPointY[i]
                        + '" />\n';
                }
                if (clickType
                    == 3)
                {
                    //buffString += '<POINT x="' + clickPointX[0] + '" y="' + clickPointY[0] + '" />\n';
                    buffString +=
                    '</RING>\n</POLYGON>\n';
                } else if (clickType
                    == 2)
                {
                    buffString +=
                    '</PATH>\n</POLYLINE>\n';
                }
                else
                {
                    buffString +=
                    '</MULTIPOINT>\n';
                }
            }
            buffString +=
            '</SPATIALFILTER>\n';
            buffString +=
            '</SPATIALQUERY>\n';
            buffString +=
            '<SIMPLERENDERER>\n';
            var tlType = LayerType[bufferTargetLayerIndex];
            if (tlType
                == "point")
            {
                buffString +=
                '<SIMPLEMARKERSYMBOL color="'
                    + highlightColor
                    + '" type="Circle" width="10" />\n';
            } else if (tlType
                == "line")
            {
                buffString +=
                '<SIMPLELINESYMBOL color="'
                    + highlightColor
                    + '" width="2" />\n';
            }
            else
            {
                buffString +=
                '<SIMPLEPOLYGONSYMBOL fillcolor="'
                    + highlightColor
                    + '" filltype="solid" transparency="'
                    + forceComma(35
                                     / 100)
                    + '" boundarycolor="255,255,255" />\n';
            }
            buffString +=
            '</SIMPLERENDERER>\n';
            buffString +=
            '</LAYER>\n';
        }
        buffString +=
        '<LAYER type="featureclass" name="theBuffer" visible="true">\n';
        buffString +=
        '<DATASET fromlayer="'
            + LayerID[ActiveLayerIndex]
            + '" />\n';
        buffString +=
        '<SPATIALQUERY>\n';
        buffString +=
        '<BUFFER distance="'
            + forceComma(bufferDistance)
            + '" bufferunits="'
            + ScaleBarUnits.toLowerCase()
            + '" />\n';
        buffString +=
        '<SPATIALFILTER relation="area_intersection">\n';
        if (selectionMode
            == 2)
        {
            buffString +=
            '<ENVELOPE '
                + selectEnvelope
                + ' />\n';
        }
        else
        {
            if (clickType
                == 2)
            {
                buffString +=
                '<POLYLINE>\n<PATH>\n';
            } else if (clickType
                == 3)
            {
                buffString +=
                '<POLYGON>\n<RING>\n';
            }
            else
            {
                buffString +=
                '<MULTIPOINT>\n';
            }
            for (var i = 0; i
                < clickCount; i++)
            {
                buffString +=
                '<POINT x="'
                    + clickPointX[i]
                    + '" y="'
                    + clickPointY[i]
                    + '" />\n';
            }
            if (clickType
                == 3)
            {
                //buffString += '<POINT x="' + clickPointX[0] + '" y="' + clickPointY[0] + '" />\n';
                buffString +=
                '</RING>\n</POLYGON>\n';
            } else if (clickType
                == 2)
            {
                buffString +=
                '</PATH>\n</POLYLINE>\n';
            }
            else
            {
                buffString +=
                '</MULTIPOINT>\n';
            }
        }
        buffString +=
        '</SPATIALFILTER>\n';
        if (useLimitExtent)
        {
            // keep this within the limitExtent
            buffString +=
            '<SPATIALFILTER relation="area_intersection">\n';
            buffString +=
            '<ENVELOPE maxx="'
                + forceComma(limitRight)
                + '" maxy="'
                + forceComma(limitTop)
                + '" minx="'
                + forceComma(limitLeft)
                + '" miny="'
                + forceComma(limitBottom)
                + '" />\n';
            buffString +=
            '</SPATIALFILTER>\n';
        }
        buffString +=
        '</SPATIALQUERY>\n';
        buffString +=
        '<SIMPLERENDERER>\n';
        //buffString += '<SIMPLEPOLYGONSYMBOL fillcolor="255,0,0" filltype="solid" transparency="' + forceComma(5/10) + '" boundarycolor="255,0,0" />\n';
        buffString +=
        '<SIMPLEPOLYGONSYMBOL fillcolor="100,100,100" filltype="solid" filltransparency="0" boundarywidth="2" boundarycolor="255,0,0" />\n';
        buffString +=
        '</SIMPLERENDERER>\n';
        buffString +=
        '</LAYER>\n';
    }
    else
    {
        // buffer a user shape
        // not implemented
    }
    return buffString;
}
function writeGetBufferedData()
{
    var buffString = "";
    //bufferSmoothEdges = bufferDistance /1001;
    //alert(selectionMode);
    queryStartRecord =
    0;
    showRetrieveData();
    switch (selectionMode)
    {
        case 1:
            buffString =
            writeQueryBufferXML();
            break;
        case 2:
            buffString =
            writeEnvelopeBufferXML();
            break;
        case 3:
            buffString =
            writeShapeBufferXML(clickType
                                    - 1);
            break;
        //case 4:
        // buffer a user shape - not implemented
        //	bufferTargetLayerIndex=ActiveLayerIndex;
        //	buffString = writeUserShapeBufferXML(clickType);
        //	break;
    }
    //alert(buffString);
    return buffString;
}
function writeQueryBufferXML()
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[bufferTargetLayerIndex];
    }
    var targetLayerType = LayerType[bufferTargetLayerIndex];
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES globalenvelope="true" outputmode="xml" geometry="false" envelope="false" checkesc ="true" ';
    //theString += 'compact="true"';
    //theString += ' featurelimit="' + maxFeaturesReturned + '" beginrecord="' + queryStartRecord + '"';
    theString +=
    '>\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" ftype="'
        + ActiveLayerType
        + '" />\n';
    theString +=
    '<SPATIALQUERY where="'
        + setQueryString
        + '">\n';
    theString +=
    '<BUFFER distance="'
        + forceComma(bufferDistance)
        + '" bufferunits="'
        + ScaleBarUnits.toLowerCase()
        + '" >\n';
    theString +=
    '<TARGETLAYER id="'
        + LayerID[bufferTargetLayerIndex]
        + '"/>\n';
    theString +=
    '<SPATIALQUERY subfields="'
        + selectFields
        + '" />\n';
    //theString += '</TARGETLAYER>\n';
    theString +=
    '</BUFFER>\n';
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
    '</SPATIALQUERY>\n';
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    //alert("writeQueryXML()\nQuery XML Request:\n" + theString);
    return theString;
}
// write out xml request for selection by shape
function writeShapeBufferXML(theType)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[bufferTargetLayerIndex];
    }
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES globalenvelope="true" outputmode="xml" envelope="false" geometry="false" checkesc ="true" ';
    //theString += 'compact="true"';
    //theString += ' featurelimit="' + maxFeaturesReturned + '" beginrecord="' + queryStartRecord + '"';
    theString +=
    '>\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" ftype="'
        + ActiveLayerType
        + '" />';
    theString +=
    '<SPATIALQUERY>';
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
    '<SPATIALFILTER relation="area_intersection" >';
    if (theType
        == 1)
    {
        theString +=
        '<POLYLINE>\n<PATH>\n';
    }
    else
    {
        theString +=
        '<POLYGON>\n<RING>\n';
    }
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
    if (theType
        == 2)
    {
        theString +=
        '</RING>\n</POLYGON>\n';
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
    '<BUFFER distance="'
        + forceComma(bufferDistance)
        + '" bufferunits="'
        + ScaleBarUnits.toLowerCase()
        + '" >\n';
    theString +=
    '<TARGETLAYER id="'
        + LayerID[bufferTargetLayerIndex]
        + '">\n';
    theString +=
    '<SPATIALQUERY subfields="'
        + selectFields
        + '" />\n';
    theString +=
    '</TARGETLAYER>\n';
    theString +=
    '</BUFFER>\n';
    theString +=
    '</SPATIALQUERY>';
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    //alert(theString);
    return theString;
}
// generic envelope select xml write routine
function writeEnvelopeBufferXML()
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[bufferTargetLayerIndex];
    }
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES globalenvelope="true" outputmode="xml" envelope="false" geometry="false" checkesc ="true"';
    //theString += ' compact="true"';
    //theString += ' featurelimit="' + maxReturned + '" beginrecord="' + startRec + '"';
    theString +=
    '>\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" ftype="'
        + ActiveLayerType
        + '" />\n';
    theString +=
    '<SPATIALQUERY>';
    theString +=
    '<SPATIALFILTER relation="area_intersection" >\n';
    theString +=
    '<ENVELOPE '
        + selectEnvelope
        + ' />';
    theString +=
    '</SPATIALFILTER>\n';
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
    '<BUFFER distance="'
        + forceComma(bufferDistance)
        + '" bufferunits="'
        + ScaleBarUnits.toLowerCase()
        + '" >\n';
    theString +=
    '<TARGETLAYER id="'
        + LayerID[bufferTargetLayerIndex]
        + '"/>\n';
    theString +=
    '<SPATIALQUERY subfields="'
        + selectFields
        + '" />\n';
    //theString += '</TARGETLAYER>\n';
    theString +=
    '</BUFFER>\n';
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
function getBufferAttributeData(theReply)
{
    //alert(theReply);
    setLayerFields(bufferTargetLayerIndex);
    var theError = getXMLErrorMessage(theReply);
    var fList = "";
    var lpos1 = 1;
    var lpos2 = 1;
    var epos = 1;
    var spos = 1;
    var morePoints = true;
    var moreFeatures = true;
    var featureCount = justGetFeatureCount(theReply);
    //alert("Features Returned: " + featureCount);
    var pos = 0;
    var startpos = 0;
    var endpos = xmlEndPos;
    var stillMore = false
    pos =
    theReply.indexOf('hasmore="true"',
                     endpos);
    if (pos
        != -1)
    {
        stillMore =
        true;
    }
    pos =
    0;
    var tempCount = 0;
    var selectedData = "";
    var inData = "";
    var xStr = "";
    var yStr = "";
    var eNorth = "";
    var eSouth = "";
    var eWest = "";
    var eEast = "";
    var fCount = featureCount;
    //alert("displayAttributeData()[featurecount=" + featureCount + "]");
    var selectCount2 = 0;
    var tempString = "";
    var Win1 = parent.TextFrame;
    var theFrame = "parent.MapFrame";
    if (featureCount
        > 0)
    {
        newSelectCount +=
        1;
        //if (showSelectedData) {
        //alert("displayAttributeData()[opening html page]");
        if ((useExternalWindow)
            || (!useTextFrame))
        {
            Win1 =
            window.open(appDir
                            + "text.htm",
                        "QueryWindow",
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
            //parent.TextFrame.document.location= appDir + "text.htm";
            Win1 =
            parent.TextFrame;
            Win1.document.open();
        }
        Win1.document.open();
        Win1.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                  + charSet
                                  + '"><head><title>'
                                  + titleList[10]
                                  + '</title></head>');
        Win1.document.writeln('<body bgcolor="'
                                  + textFrameBackColor
                                  + '" text="Black" link="Blue" vlink="Gray" LEFTMARGIN=0 onload="window.focus()">');
        Win1.document.writeln('<center><FONT FACE="Arial" SIZE="-1"><b>'
                                  + LayerName[bufferTargetLayerIndex]
                                  + '</b>');
        Win1.document.writeln('<table border="1" cellspacing="0" cellpadding="2" nowrap bgcolor="'
                                  + tableBackColor
                                  + '">');
        //}
        endpos =
        1;
        for (var i = 0; i
            < fCount; i++)
        {
            //alert(endpos);
            inData =
            parseRecordString(theReply,
                              endpos);
            endpos =
            xmlEndPos;
            //selectedData = clearLeadingSpace(inData);
            selectedData =
            inData;
            epos =
            theReply.indexOf("</FEATURE",
                             endpos);
            if (showSelectedData)
            {
                //alert("2\n" + selectedData);
                var showHyper = false;
                if (hyperLinkLayers
                    != null)
                {
                    for (var s1 = 0; s1
                        < hyperLinkLayers.length; s1++)
                    {
                        if (hyperLinkLayers[s1]
                            == LayerName[bufferTargetLayerIndex])
                        {
                            showHyper =
                            true;
                        }
                    }
                }
                var tempActiveLayer = ActiveLayer;
                var tempActiveLayerIndex = ActiveLayerIndex;
                var tempActiveLayerType = ActiveLayerType;
                ActiveLayer =
                bufferTargetLayer;
                ActiveLayerIndex =
                bufferTargetLayerIndex;
                ActiveLayerType =
                LayerType[bufferTargetLayerIndex];
                var fName1 = getFieldNames(selectedData);
                var fValue1 = getFieldValues(selectedData);
                var idFieldNum = -1;
                var shapeFieldNum = -1;
                if (hideIDFieldData)
                {
                    // hide ID column header
                    for (var f = 0; f
                        < fName1.length; f++)
                    {
                        if (fName1[f]
                            == LayerIDField[ActiveLayerIndex])
                        {
                            idFieldNum =
                            f;
                        }
                    }
                }
                if (hideShapeFieldData)
                {
                    // hide Shape column header
                    for (var f = 0; f
                        < fName1.length; f++)
                    {
                        if (fName1[f]
                            == LayerShapeField[ActiveLayerIndex])
                        {
                            shapeFieldNum =
                            f;
                        }
                    }
                }
                ActiveLayer =
                tempActiveLayer;
                ActiveLayerIndex =
                tempActiveLayerIndex;
                ActiveLayerType =
                tempActiveLayerType;
                //selectPoints[selectCount] = getIdValue(fName1, fValue1);
                if (selectCount2
                    == 0)
                {
                    Win1.document.write('<tr><th><FONT FACE="Arial" SIZE="-2">'
                                            + msgList[86]
                                            + '</FONT></a></th>');
                    for (var f = 0; f
                        < fName1.length; f++)
                    {
                        if ((f
                            != idFieldNum)
                            && (f
                            != shapeFieldNum))
                        {
                            var f2 = -1;
                            if (useFieldAlias)
                            {
                                for (var f3 = 0; f3
                                    < AliasFieldName.length; f3++)
                                {
                                    if (AliasFieldName[f3]
                                        == fName1[f])
                                    {
                                        f2 =
                                        f3;
                                    }
                                }
                            }
                            if (f2
                                != -1)
                            {
                                Win1.document.write('<th><FONT FACE="Arial" SIZE="-2">'
                                                        + AliasFieldAlias[f2]
                                                        + '</FONT></a></th>');
                            }
                            else
                            {
                                Win1.document.write('<th><FONT FACE="Arial" SIZE="-2">'
                                                        + fName1[f]
                                                        + '</FONT></a></th>');
                            }
                        }
                    }
                    Win1.document.writeln('</tr>');
                }
                Win1.document.write('<tr><td>');
                Win1.document.write('<FONT FACE="Arial" SIZE="-2">'
                                        + (selectCount2
                                               + queryStartRecord
                    + 1)
                                        + '</FONT>');
                Win1.document.writeln('</td>');
                for (var f = 0; f
                    < fName1.length; f++)
                {
                    if ((f
                        != idFieldNum)
                        && (f
                        != shapeFieldNum))
                    {
                        Win1.document.write('<TD>');
                        var isHyper = false;
                        if (showHyper)
                        {
                            for (var s1 = 0; s1
                                < hyperLinkFields.length; s1++)
                            {
                                if (hyperLinkFields[s1]
                                    == fName1[f])
                                {
                                    var theLinkURL = currentHyperLinkPrefix
                                                         + fValue1[f]
                                        + currentHyperLinkSuffix;
                                    Win1.document.write('<a href="'
                                                            + theLinkURL
                                                            + '" target="_blank">');
                                    isHyper =
                                    true;
                                }
                            }
                        }
                        var s2 = -1;
                        for (var s1 = 0; s1
                            < LayerFields.length; s1++)
                        {
                            if (fName1[f]
                                == LayerFields[s1])
                            {
                                s2 =
                                s1;
                            }
                        }
                        //if (s2!=-1) {
                        if (LayerFieldType[s2]
                            == "91")
                        {
                            //alert(fName1[f]);
                            if (isNaN(fValue1[f]))
                            {
                                var theDate = fValue1[f];
                            }
                            else
                            {
                                var theDate = new Date(parseInt(fValue1[f]));
                            }
                            Win1.document.write('<FONT FACE="Arial" SIZE="-2">'
                                                    + theDate
                                                    + '</FONT>');
                            theDate =
                            null;
                        }
                        else
                        {
                            Win1.document.write('<FONT FACE="Arial" SIZE="-2">'
                                                    + fValue1[f]
                                                    + '</FONT>');
                        }
                        //}
                        if (isHyper)
                        {
                            Win1.document.write('</a>');
                        }
                        Win1.document.writeln('</td>');
                    }
                }
                Win1.document.writeln('</tr>');
                fName1 =
                null;
                fValue1 =
                null;
            }
            selectCount2 +=
            1;
        }
        Win1.document.writeln('</table>');
        Win1.document.writeln('</font></center></body></html>');
        Win1.document.close();
    }
    else
    {
        //alert(tempString);
        if ((useExternalWindow)
            || (!useTextFrame))
        {
            Win1 =
            window.open(appDir
                            + "text.htm",
                        "QueryWindow",
                        "width=575,height=120,scrollbars=yes,resizable=yes");
        }
        else
        {
            parent.TextFrame.document.location =
            appDir
                + "text.htm";
            Win1 =
            parent.TextFrame;
            Win1.document.open();
        }
        Win1.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                  + charSet
                                  + '"><head>');
        Win1.document.writeln('	<title>'
                                  + titleList[10]
                                  + '</title>');
        Win1.document.writeln('</head>');
        Win1.document.writeln('<body BGCOLOR="White" TEXT="Black" LEFTMARGIN=0 TOPMARGIN=0>');
        Win1.document.writeln('<FONT FACE="Arial"><B>'
                                  + LayerName[bufferTargetLayerIndex]
                                  + '</B></font><FONT FACE="Arial" size="-2">');
        Win1.document.writeln('<br>'
                                  + msgList[64]);
        if (debugOn
            > 0)
        {
            Win1.document.writeln('<p>'
                                      + msgList[65]
                                      + '<br>');
            Win1.document.writeln(untag(theReply));
        }
        else
        {
            if (theError
                != "")
            {
                Win1.document.writeln('<p>'
                                          + msgList[66]
                                          + '<br>');
                Win1.document.writeln(theError);
            }
        }
        Win1.document.writeln('</FONT>');
        Win1.document.writeln('</body></html>');
        Win1.document.close();
    }
    //if ((toolMode==4) || (toolMode==9))  selectCount=0;
    Win1 =
    null;
    setLayerFields(ActiveLayerIndex);
    hideRetrieveData();
}

