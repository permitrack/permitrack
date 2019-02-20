// aimsLayers.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js,
 *		aimsDHTML.js
 */
aimsLayersPresent =
true;
var LayerName = new Array();
var LayerID = new Array();
var LayerVisible = new Array();
var LayerType = new Array();
var LayerIsFeature = new Array();
var LayerExtent = new Array();
var LayerMinScale = new Array();
var LayerMaxScale = new Array();
var LayerRenderString = new Array();
var LayerShapeField = new Array();
var LayerIDField = new Array();
var LayerFieldList = new Array();
var LayerFieldTypeList = new Array();
var LayerFieldSizeList = new Array();
var LayerFieldPrecisionList = new Array();
var LayerFields = new Array();
var LayerFieldType = new Array();
var LayerFieldCount = 0;
var ActiveLayer = "";
var ActiveLayerType = "";
var layerCount = 0;
var layerLeft = 0;
var layerRight = 0;
var layerTop = 0;
var layerBottom = 0;
var fieldIndex = 0;
var FeatureLayerCount = 0;
// field aliases arrays
var AliasFieldName = new Array();
var AliasFieldAlias = new Array();
var LayerListOpen = false;
var queryOpen = false;
var displayLayerInfoButton = false;
var setLayerVisible = new Array();
// check for query form. . . if present, update field list
function setActiveLayer(i)
{
    fieldIndex =
    0;
    selectCount =
    0;
    //keep the buffer around when user zooms, etc. Until it is cleared.
    //	showBuffer=false;
    ActiveLayer =
    LayerID[i];
    ActiveLayerType =
    LayerType[i];
    ActiveLayerIndex =
    i;
    setLayerFields(i);
    var hyperLinkLayers = parent.MapFrame.hyperLinkLayers;
    parent.ToolFrame.hideLinkButton();
    var isHyperLink = false;
    for (j =
         0; j
        < hyperLinkLayers.length; j++)
    {
        if (LayerName[i]
            == hyperLinkLayers[j])
        {
            parent.ToolFrame.showLinkButton();
            isHyperLink =
            true;
            break;
        }
    }
    if ((!isHyperLink)
        && parent.MapFrame.modeName.toUpperCase()
        == "HYPERLINK")
    {
        parent.MapFrame.clickFunction('selectbox');
        parent.ToolFrame.UpdateIcon('Select Rectangle',
                                    'on');
    }
    // alert('layerName: ' + LayerName[i] + " , layerID: " + i);
    if (useGeocode)
    {
        // if active layer is geocodeable - set current geocode layer to active layer
        if (GCLayerCount
            > 0)
        {
            var j = -1;
            for (var k = 0; k
                < GCLayerId.length; k++)
            {
                if (ActiveLayer
                    == GCLayerId[k])
                {
                    j =
                    k;
                }
            }
            if (j
                != -1)
            {
                GCActiveLayer =
                j;
            }
        }
    }
    if (queryOpen)
    {
        queryForm();
    }
    else
    {
        if ((useExternalWindow)
            || (!useTextFrame))
        {
            Win1 =
            window.open("",
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
            Win1 =
            parent.TextFrame;
            Win1.document.open();
        }
        Win1.document.open();
        Win1.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                  + charSet
                                  + '"><head><title>'
                                  + titleList[1]
                                  + '</title></head>');
        Win1.document.writeln('<body bgcolor="'
                                  + tableBackColor
                                  + '" text="Black" link="Blue" vlink="Gray" LEFTMARGIN=0 onload="window.focus()">');
        Win1.document.writeln('<center><FONT FACE="Arial" SIZE="-1"><b>'
                                  + LayerName[ActiveLayerIndex]
                                  + msgList[20]
                                  + '</b>');
        Win1.document.writeln('</font></center></body></html>');
        Win1.document.close();
    }
}
// write out form for layerList in separate window
function writeLayerListForm()
{
    var Win1 = open(appDir
                        + "toc.htm",
                    "LayerListWindow",
                    "width=190,height=300,scrollbars=yes,resizable=yes");
    Win1.focus();
    Win1 =
    null;
}
// set up the LayerVisible array for map display
function setupLayerVisible()
{
    //alert("setupLayerVisible");
    var toggleCount = LayerVisible.length;
    if (setLayerVisible.length
        < toggleCount)
    {
        toggleCount =
        setLayerVisible.length;
    }
    for (var i = 0; i
        < toggleCount; i++)
    {
        LayerVisible[i] =
        setLayerVisible[i];
    }
}
// get list of layers, id/shape fields, scalefactors, etc.
function getLayers(theReply)
{
    //	alert("LayerInfo:\n" + theReply.length);
    //	alert("LayerInfo:\n" + theReply);
    var theReplyUC = theReply.toUpperCase();
    LayerFields.length =
    1;
    var startpos = 0;
    var endpos = 0;
    var pos = -1;
    var lpos = 1;
    var epos = 1;
    var zpos = 1;
    var zpos2 = 1;
    var tempString = "";
    var visString = "";
    var typeString = "";
    var fieldString = "";
    var testString = "";
    var testString2 = "";
    var minString = "";
    var maxString = "";
    if (LayerID.length
        > 0)
    {
        //layerCount = LayerID.length;
        //alert("Already got layer parameters");
    }
    else
    {
        //alert("Getting layer parameters");
        var fieldCount = 0;
        layerCount =
        0;
        LayerName.length =
        1;
        LayerType.length =
        1;
        LayerVisible.length =
        1;
        LayerExtent.length =
        1;
        LayerIsFeature.length =
        1;
        LayerID.length =
        1;
        LayerIDField.length =
        1;
        LayerShapeField.length =
        1;
        LayerMinScale.length =
        1;
        LayerMaxScale.length =
        1;
        LayerFieldTypeList.length =
        1;
        LayerFieldList.length =
        1;
        LayerRenderString.length =
        1;
        LayerFieldSizeList.length =
        1;
        LayerFieldPrecisionList.length =
        1;
        layerCount =
        0;
        var layerMinX = 0;
        var layerMinY = 0;
        var layerMaxX = 0;
        var layerMaxY = 0;
        //alert("Processing LayerInfo");
        lpos =
        theReplyUC.indexOf("<LAYERINFO",
                           zpos);
        while (lpos
            > -1)
        {
            //alert("<LAYERINFO - pos " + lpos );
            if (lpos
                != -1)
            {
                zpos =
                theReplyUC.indexOf("</LAYERINFO",
                                   lpos);
                //alert("</LAYERINFO - pos " +  zpos);
                if (zpos
                    != -1)
                {
                    pos =
                    theReplyUC.indexOf("NAME=",
                                       lpos);
                    if (pos
                        != -1)
                    {
                        startpos =
                        pos
                            + 6;
                        endpos =
                        theReply.indexOf(dQuote,
                                         startpos);
                        tempString =
                        theReply.substring(startpos,
                                           endpos);
                        tempString =
                        tempString.replace(/&apos;/g,
                                           "'");
                        LayerName[layerCount] =
                        tempString;
                        testString2 =
                        'TYPE="FEATURECLASS"';
                        pos =
                        theReplyUC.indexOf(testString2,
                                           lpos);
                        if ((pos
                            != -1)
                            && (pos
                            < zpos))
                        {
                            LayerIsFeature[layerCount] =
                            true;
                        }
                        else
                        {
                            LayerIsFeature[layerCount] =
                            false;
                        }
                        //startpos = theReplyUC.indexOf("VISIBLE=",endpos);
                        startpos =
                        theReplyUC.indexOf("VISIBLE=",
                                           lpos);
                        if (startpos
                            != -1)
                        {
                            startpos =
                            startpos
                                + 9;
                            endpos =
                            startpos
                                + 4;
                            visString =
                            theReply.substring(startpos,
                                               endpos);
                        }
                        startpos =
                        theReplyUC.indexOf("ID=",
                                           lpos);
                        if ((startpos
                            != -1)
                            && (startpos
                            < zpos))
                        {
                            startpos =
                            startpos
                                + 4;
                            endpos =
                            theReply.indexOf(dQuote,
                                             startpos);
                            tempString =
                            theReply.substring(startpos,
                                               endpos);
                            tempString =
                            tempString.replace(/&apos;/g,
                                               "'");
                            LayerID[layerCount] =
                            tempString;
                        }
                        else
                        {
                            LayerID[layerCount] =
                            LayerName[layerCount];
                        }
                        //alert(LayerID[layerCount]);
                        if (visString
                            == "true")
                        {
                            LayerVisible[layerCount] =
                            1
                        }
                        else
                        {
                            LayerVisible[layerCount] =
                            0
                        }
                        ;
                        //alert(startpos + "/" + LayerName[layerCount] + ":" + visString);
                        startpos =
                        theReplyUC.indexOf("MINSCALE=",
                                           lpos);
                        if ((startpos
                            != -1)
                            && (startpos
                            < zpos))
                        {
                            startpos +=
                            10;
                            endpos =
                            theReply.indexOf(dQuote,
                                             startpos);
                            minString =
                            theReply.substring(startpos,
                                               endpos);
                            minString =
                            setDecimalString(minString);
                            LayerMinScale[layerCount] =
                            parseFloat(minString);
                        }
                        else
                        {
                            LayerMinScale[layerCount] =
                            0;
                        }
                        startpos =
                        theReplyUC.indexOf("MAXSCALE=",
                                           lpos);
                        if ((startpos
                            != -1)
                            && (startpos
                            < zpos))
                        {
                            startpos +=
                            10;
                            endpos =
                            theReply.indexOf(dQuote,
                                             startpos);
                            maxString =
                            theReply.substring(startpos,
                                               endpos);
                            maxString =
                            setDecimalString(maxString);
                            LayerMaxScale[layerCount] =
                            parseFloat(maxString);
                        }
                        else
                        {
                            LayerMaxScale[layerCount] =
                            1.7976931348623157E308;
                        }
                        //alert("MinScale:" + LayerMinScale[layerCount] + "\nMaxScale:" + LayerMaxScale[layerCount]);
                        if (LayerIsFeature[layerCount])
                        {
                            startpos =
                            theReplyUC.indexOf("<FCLASS TYPE=",
                                               lpos);
                            zpos2 =
                            theReplyUC.indexOf("</FCLASS",
                                               lpos);
                            if (startpos
                                != -1)
                            {
                                startpos =
                                startpos
                                    + 14;
                                endpos =
                                theReply.indexOf(dQuote,
                                                 startpos);
                                typeString =
                                theReply.substring(startpos,
                                                   endpos);
                            }
                            else
                            {
                                typeString =
                                "image";
                            }
                        }
                        else
                        {
                            typeString =
                            "image";
                        }
                        LayerType[layerCount] =
                        typeString;
                        var theXYs = getEnvelopeXYs(theReply,
                                                    lpos);
                        tempString =
                        theXYs[0]
                            + "|"
                            + theXYs[1]
                            + "|"
                            + theXYs[2]
                            + "|"
                            + theXYs[3];
                        endpos =
                        xmlEndPos;
                        theXYs =
                        null;
                        LayerExtent[layerCount] =
                        tempString;
                        LayerFieldList[layerCount] =
                        "";
                        LayerFieldTypeList[layerCount] =
                        "";
                        LayerFieldSizeList[layerCount] =
                        "";
                        LayerFieldPrecisionList[layerCount] =
                        "";
                        LayerIDField[layerCount] =
                        "";
                        LayerShapeField[layerCount] =
                        "";
                        if (LayerIsFeature[layerCount])
                        {
                            var jpos = 1;
                            var fldCount = 0;
                            FeatureLayerCount +=
                            1;
                            while ((jpos
                                < zpos)
                                && (jpos
                                != -1))
                            {
                                jpos =
                                theReply.indexOf("<FIELD name=",
                                                 endpos);
                                if (zpos2
                                    > jpos)
                                {
                                    if (jpos
                                        != -1)
                                    {
                                        startpos =
                                        jpos
                                            + 13
                                        endpos =
                                        theReply.indexOf(dQuote,
                                                         startpos);
                                        tempString =
                                        theReply.substring(startpos,
                                                           endpos);
                                        startpos =
                                        theReply.indexOf("type=",
                                                         endpos);
                                        startpos =
                                        startpos
                                            + 6;
                                        endpos =
                                        theReply.indexOf(dQuote,
                                                         startpos);
                                        testString =
                                        theReply.substring(startpos,
                                                           endpos);
                                        if (testString
                                            == "-99")
                                        {
                                            LayerIDField[layerCount] =
                                            tempString;
                                        }
                                        if (testString
                                            == "-98")
                                        {
                                            LayerShapeField[layerCount] =
                                            tempString;
                                        }
                                        if (fldCount
                                            > 0)
                                        {
                                            LayerFieldList[layerCount] =
                                            LayerFieldList[layerCount]
                                                + ",";
                                            LayerFieldTypeList[layerCount] =
                                            LayerFieldTypeList[layerCount]
                                                + ",";
                                            LayerFieldSizeList[layerCount] =
                                            LayerFieldSizeList[layerCount]
                                                + ",";
                                            LayerFieldPrecisionList[layerCount] =
                                            LayerFieldPrecisionList[layerCount]
                                                + ",";
                                        }
                                        LayerFieldList[layerCount] =
                                        LayerFieldList[layerCount]
                                            + tempString;
                                        LayerFieldTypeList[layerCount] =
                                        LayerFieldTypeList[layerCount]
                                            + testString;
                                        startpos =
                                        theReply.indexOf("size=",
                                                         jpos);
                                        startpos =
                                        startpos
                                            + 6;
                                        endpos =
                                        theReply.indexOf(dQuote,
                                                         startpos);
                                        testString =
                                        theReply.substring(startpos,
                                                           endpos);
                                        LayerFieldSizeList[layerCount] =
                                        LayerFieldSizeList[layerCount]
                                            + testString;
                                        startpos =
                                        theReply.indexOf("precision=",
                                                         jpos);
                                        startpos +=
                                        11;
                                        endpos =
                                        theReply.indexOf(dQuote,
                                                         startpos);
                                        testString =
                                        theReply.substring(startpos,
                                                           endpos);
                                        LayerFieldPrecisionList[layerCount] =
                                        LayerFieldPrecisionList[layerCount]
                                            + testString;
                                        fldCount +=
                                        1;
                                    }
                                }
                                else
                                {
                                    // search for next Layer
                                    endpos =
                                    zpos;
                                    jpos =
                                    -1;
                                }
                            }
                            if (ClassRenderLayer.length
                                > 0)
                            {
                                var sr1 = -1
                                for (var u = 0; u
                                    < ClassRenderLayer.length; u++)
                                {
                                    if (ClassRenderLayer[u]
                                        == LayerName[layerCount])
                                    {
                                        sr1 =
                                        u;
                                    }
                                }
                                if (sr1
                                    > -1)
                                {
                                    LayerRenderString[layerCount] =
                                    ClassRenderString[sr1];
                                    //alert(ClassRenderLayer[sr1]);
                                }
                                else
                                {
                                    LayerRenderString[layerCount] =
                                    "";
                                }
                            }
                        }
                        else
                        {
                            LayerRenderString[layerCount] =
                            "";
                        }
                        //alert("Layer " + layerCount + ": " + LayerName[layerCount]);
                        layerCount +=
                        1;
                        endpos =
                        zpos;
                    }
                    lpos =
                    theReplyUC.indexOf("<LAYERINFO",
                                       zpos);
                }
                else
                {
                    lpos =
                    -1;
                }
            }
        }
        //alert("LayerInfo processed");
        LayerName.reverse();
        LayerVisible.reverse();
        LayerType.reverse();
        LayerIDField.reverse();
        LayerShapeField.reverse();
        LayerExtent.reverse();
        LayerIsFeature.reverse();
        LayerMinScale.reverse();
        LayerMaxScale.reverse();
        LayerFieldTypeList.reverse();
        LayerFieldList.reverse();
        LayerFieldSizeList.reverse();
        LayerFieldPrecisionList.reverse();
        LayerID.reverse();
        LayerRenderString.reverse();
    }
    if (ActiveLayerIndex
        >= layerCount)
    {
        ActiveLayerIndex =
        0;
    }
    if (!LayerIsFeature[ActiveLayerIndex])
    {
        var chk = 0;
        for (var i = layerCount
            - 1; i
            >= 0; i--)
        {
            if (LayerIsFeature[i])
            {
                chk =
                i;
            }
        }
        ActiveLayerIndex =
        chk;
    }
    ActiveLayer =
    LayerID[ActiveLayerIndex];
    ActiveLayerType =
    LayerType[ActiveLayerIndex];
    if (FeatureLayerCount
        == 0)
    {
        //alert("No Feature Layers");
        canQuery =
        false;
        useZoomActive =
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
        queryZoom =
        false;
    }
    if (noListLayer.length
        < LayerName.length)
    {
        noListLayer.length =
        LayerName.length;
        for (var i = 0; i
            < LayerName.length; i++)
        {
            if (noListLayer[i]
                == null)
            {
                noListLayer[i] =
                false;
            }
        }
    }
    //alert(layerCount + " layers");
    setActiveLayerByName("parcel");
}
// set the Layer Field array using the current Active Layer's fields
function setLayerFields(layerIndex)
{
    LayerFields.length =
    1;
    LayerFieldType.length =
    1;
    var tempList = new Array();
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[layerIndex];
    }
    if (LayerFieldList[layerIndex]
        != "")
    {
        // /*
        if (useFieldAlias)
        {
            AliasFieldName.length =
            0;
            AliasFieldAlias.length =
            0;
            var msg = LayerName[layerIndex]
                + " - aliases: ";
            if (fieldAliasList[layerIndex]
                != null)
            {
                if (fieldAliasList[layerIndex]
                    != "")
                {
                    if (fieldAliasList[layerIndex].indexOf("|")
                        == -1)
                    {
                        var tempPair = fieldAliasList[layerIndex].split(":");
                        AliasFieldName[0] =
                        tempPair[0];
                        AliasFieldAlias[0] =
                        tempPair[1];
                        msg +=
                        fieldAliasList[layerIndex];
                        tempPair =
                        null;
                    }
                    else
                    {
                        tempList =
                        fieldAliasList[layerIndex].split("|");
                        for (var i = 0; i
                            < tempList.length; i++)
                        {
                            var tempPair = tempList[i].split(":");
                            AliasFieldName[i] =
                            tempPair[0];
                            AliasFieldAlias[i] =
                            tempPair[1];
                            msg +=
                            tempList[i]
                                + " ";
                        }
                    }
                }
                else
                {
                    AliasFieldName[0] =
                    "";
                    AliasFieldAlias[0] =
                    "";
                }
            }
            else
            {
                AliasFieldName[0] =
                "";
                AliasFieldAlias[0] =
                "";
            }
            //alert(msg);
        }
        // */
        var fList = LayerFieldList[layerIndex].split(",");
        var ftList = LayerFieldTypeList[layerIndex].split(",");
        var fieldCount = 0;
        for (var i = 0; i
            < fList.length; i++)
        {
            var useIt = selectFields.toUpperCase().indexOf(fList[i].toUpperCase());
            if ((useIt
                != -1)
                || (selectFields
                == "#ALL#"))
            {
                if (fList[i].toUpperCase()
                    != LayerShapeField[layerIndex].toUpperCase())
                {
                    LayerFields[fieldCount] =
                    fList[i];
                    LayerFieldType[fieldCount] =
                    ftList[i];
                    fieldCount +=
                    1;
                }
            }
        }
        LayerFieldCount =
        fieldCount;
        canQuery =
        true;
        fList =
        null;
        ftList =
        null;
    }
    else
    {
        alert(msgList[21]);
        canQuery =
        false;
    }
}
// get the Field names from the LayerFieldList array
function getLayerFieldNames(layerIndex)
{
    if (LayerFieldList[layerIndex]
        != "")
    {
        var fList = LayerFieldList[layerIndex].split(",");
    }
    else
    {
        var fList = null;
    }
    return fList;
}
// get the Field types from the LayerFieldTypeList array
function getLayerFieldTypes(layerIndex)
{
    if (LayerFieldList[layerIndex]
        != "")
    {
        var ftList = LayerFieldTypeList[layerIndex].split(",");
    }
    else
    {
        var ftList = null;
    }
    return ftList;
}
// get the Field types from the LayerFieldTypeList array
function getLayerFieldSizes(layerIndex)
{
    if (LayerFieldList[layerIndex]
        != "")
    {
        var ftList = LayerFieldSizeList[layerIndex].split(",");
    }
    else
    {
        var ftList = null;
    }
    return ftList;
}
// get the Field types from the LayerFieldTypeList array
function getLayerFieldPrecisions(layerIndex)
{
    if (LayerFieldList[layerIndex]
        != "")
    {
        var ftList = LayerFieldPrecisionList[layerIndex].split(",");
    }
    else
    {
        var ftList = null;
    }
    return ftList;
}
// display layer information
function showLayerInfo(layerIndex)
{
    var url = appDir
                  + "showlayerinfo.htm?"
        + layerIndex;
    if (useTextFrame)
    {
        parent.TextFrame.document.location =
        url;
    }
    else
    {
        Win1 =
        open(url,
             "LayerInfoWindow",
             "width=575,height=250,scrollbars=yes,resizable=yes");
    }
}

