// aimsClick.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js,
 *		aimsLayers.js, aimsDHTML.js
 *		aimsNavigation.js
 */
aimsClickPresent =
true;
var onOVArea = false;
// Global vars to save mouse position
var mouseX = 0;
var mouseY = 0;
var x1 = 0;
var y1 = 0;
var x2 = 0;
var y2 = 0;
var zleft = 0;
var zright = 0;
var ztop = 0;
var zbottom = 0;
var totalMeasure = 0;
var currentMeasure = 0;
var lastTotMeasure = 0;
var currentArea = 0;
var currentAcres = 0;
var MeasureType = "line";
// variables for interactive clicks
var clickCount = 0;
var clickPointX = new Array();
var clickPointY = new Array();
var clickMeasure = new Array();
// type - 1=Measure; 2=SelectLine ; 3=SelectPolygon
//var clickType = 3;
var clickType = 1;
var shapeSelectBuffer = false;
var shapeSelectType = 0;
var panning = false;
var zooming = false;
var selectBox = false;
var blankImage = "images/map.gif";
var leftButton = 1;
var rightButton = 2;
if (isNav)
{
    leftButton =
    1;
    rightButton =
    3;
}
/*  *****************************************************
 *	Point click functions
 * 	used by Measure and Select by Line/Polygon
 *	*****************************************************
 */

// put a point at click and add to clickCount
//function clickAddPoint() {
//	var theX = mouseX;
//	var theY = mouseY;
//	getMapXY(theX,theY);
//	clickPointX[clickCount]=mapX;
//	clickPointY[clickCount]=mapY;
//	clickCount += 1;
//	selectCount=0;
//	totalMeasure = totalMeasure + currentMeasure;
//		//var u = Math.pow(10,numDecimals);
//		//if (totalMeasure!=0) totalMeasure = parseInt(totalMeasure*u+0.5)/u;
//
//	clickMeasure[clickCount]=totalMeasure;
//	legendTemp=legendVisible;
//	legendVisible=false;
//	var theString = writeXML();
//	var theNum = 99;
//	sendToServer(imsURL,theString,theNum);
//}

// zero out all clicks in clickCount
function resetClick()
{
    var c1 = clickCount;
    clickCount =
    0;
    clickPointX.length =
    1;
    clickPointY.length =
    1;
    currentMeasure =
    0;
    totalMeasure =
    0;
    lastTotMeasure =
    0;
    clickMeasure.length =
    1;
    selectCount =
    0;
    // reset rubberbanding graphics
    Xpoints.length =
    null;
    Ypoints.length =
    null;
    legendTemp =
    legendVisible;
    legendVisible =
    false;
    var theString = writeXML();
    var theNum = 99;
    //showRetrieveMap();
    sendToServer(imsURL,
                 theString,
                 theNum);
    if (toolMode
        == 20)
    {
        updateMeasureBox();
    }
}
// remove last click from clickCount
//function deleteClick() {
//	var c1 = clickCount;
//	clickCount=clickCount-1;
//	selectCount=0;
//	if (clickCount<0) clickCount=0;
//	if (clickCount>0) {
//		totalMeasure = clickMeasure[clickCount]
//		clickPointX.length=clickCount;
//		clickPointY.length=clickCount;
//		clickMeasure.length=clickCount;
//
//	} else {
//		totalMeasure=0;
//		clickMeasure[0]=0;
//	}
//	currentMeasure=0;
//	if (c1>0) {
//		legendTemp=legendVisible;
//		legendVisible=false;
//		var theString = writeXML();
//		var theNum = 99;
//		sendToServer(imsURL,theString,theNum);
//	}
//
//}


//keep track of currently selected tool, and display it to user
// set the imsMap cursor tool
function clickFunction(toolName)
{
    if (!activate_rb)
    { // Delete the js_graphics
        deactivateRubberband();
        resetPolyline();
    }
    if (hasLayer("measureBox"))
    {
        hideLayer("measureBox");
    }
    switch (toolName)
    {
        // Zooming functions
        case "zoomin":
            toolMode =
            1;
            panning =
            false;
            selectBox =
            false;
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "crosshair";
                document.body.style.cursor =
                "crosshair";
                theCursor =
                document.all.theTop.style.cursor;
            }
            modeBlurb =
            modeList[0];
            break;
        case "zoomout":
            toolMode =
            2;
            panning =
            false;
            selectBox =
            false;
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "crosshair";
                document.body.style.cursor =
                "crosshair";
                theCursor =
                document.all.theTop.style.cursor;
            }
            modeBlurb =
            modeList[1];
            break;
        case "zoomlast":
            zoomBack();
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            break;
        case "zoomactive":
            var q = LayerExtent[ActiveLayerIndex].split("|");
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            var l = parseFloat(setDecimalString(q[0]));
            var b = parseFloat(setDecimalString(q[1]));
            var r = parseFloat(setDecimalString(q[2]));
            var t = parseFloat(setDecimalString(q[3]));
            var w = r
                - l;
            var h = t
                - b;
            // add a bit of a margin around the layer
            var wm = w
                * (5
                / 100);
            var hm = h
                * (5
                / 100);
            l =
            l
                - wm;
            r =
            r
                + wm;
            b =
            b
                - hm;
            t =
            t
                + hm;
            zoomToEnvelope(l,
                           b,
                           r,
                           t);
            break;
        case "fullextent":
            fullExtent();
            break;
        case "pan":
            toolMode =
            3;
            zooming =
            false;
            selectBox =
            false;
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "move";
                document.body.style.cursor =
                "move";
                theCursor =
                document.all.theTop.style.cursor;
            }
            modeBlurb =
            modeList[2];
            //if (useTextFrame) parent.TextFrame.document.location= appDir + "text.htm";
            break;


        // Identify-Hyperlink functions
        case "identify":
            // identify mode - layer attributes - requires aimsIdentify.js
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            if (canQuery)
            {
                toolMode =
                4;
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "crosshair";
                    document.body.style.cursor =
                    "crosshair";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                modeBlurb =
                modeList[3];
            }
            else
            {
                alert(msgList[46]);
            }
            showGeocode =
            false;
            //if (useTextFrame) parent.TextFrame.document.location= appDir + "text.htm";
            hideLayer("measureBox");
            break;
        case "identifyall":
            // identify drill mode
            //alert("In IdentifyAll");
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            toolMode =
            5;
            if (canQuery)
            {
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "crosshair";
                    document.body.style.cursor =
                    "crosshair";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                //modeBlurb = modeList[19]; // identify all
                modeBlurb =
                modeList[20]; // identify visible features
                //modeBlurb = modeList[3]; // identify
            }
            else
            {
                alert(msgList[46]);
            }
            //alert("Function Not Implemented");
            showGeocode =
            false;
            drawSelectBoundary =
            false;
            if (useTextFrame)
            {
                parent.TextFrame.document.location =
                appDir
                    + "text.htm";
            }
            hideLayer("measureBox");
            break;
        case "hyperlink":
            // hyperlink mode - requires aimsIdentify.js
            var isOk = false;
            var j = -1;
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            toolMode =
            15;
            modeBlurb =
            modeList[9];
            showGeocode =
            false;
            if (useTextFrame)
            {
                parent.TextFrame.document.location =
                appDir
                    + "text.htm";
            }
            hideLayer("measureBox");
            var isOk = checkHyperLinkLayer(ActiveLayerIndex)
            if (isOk)
            {
                if (canQuery)
                {
                    if (isIE)
                    {
                        document.all.theTop.style.cursor =
                        "crosshair";
                        theCursor =
                        document.all.theTop.style.cursor;
                    }
                }
                else
                {
                    alert(msgList[46]);
                }
                //alert("Function Not Implemented");
            }
            else
            {
                currentHyperLinkLayer =
                "";
                currentHyperLinkField =
                "";
                alert(msgList[47]);
            }
            break;

        // Measure-Unit function
        case "measure":
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            MeasureType =
            "line";
            if (clickCount
                > 0)
            {
                if (totalMeasure
                    == 0)
                {
                    resetClick();
                }
            }
            toolMode =
            20;
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "crosshair";
                document.body.style.cursor =
                "crosshair";
                theCursor =
                document.all.theTop.style.cursor;
            }
            //modeBlurb = modeList[12];
            modeName =
            "Measure";
            if (clickType
                == 1)
            {
                //showLayer("measureBox");
                updateMeasureBox();
            }
            showGeocode =
            false;
            break;
        case "setunits":
            parent.TOCFrame.location =
            "setunits.htm";
            break;
        case "toc":
            parent.TOCFrame.location =
            "toc.htm";
            break;

        // Graphic Selection functions
        case "shape":
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            toolMode =
            21;
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "crosshair";
                document.body.style.cursor =
                "crosshair";
                theCursor =
                document.all.theTop.style.cursor;
            }
            modeBlurb =
            modeList[13];
            showGeocode =
            false;
            hideLayer("measureBox");
            break;

        //	case "selectbox":
        //		panning=false;
        //		zooming=false;
        // select mode - requires aimsSelect.js
        //selectXMLMode=906;
        //		if (canQuery) {
        //			toolMode = 10;
        //                        //XMLMode = 6;
        //			clickCount=0;
        //			showBuffer=false;
        //			if (isIE)	{
        //				document.all.theTop.style.cursor = "crosshair";
        //                               document.body.style.cursor = "crosshair";
        //				theCursor = document.all.theTop.style.cursor;
        //			}
        //			modeBlurb = modeList[4];
        //		} else {
        //			alert(msgList[46]);
        //		}
        //		showGeocode=false;
        //		if (useTextFrame) parent.TextFrame.document.location= appDir + "text.htm";
        //		hideLayer("measureBox");
        //		break;
        //
        case "selectpoint":

            //Glen - Will have to change to allow info on other layers
            //parent.MapFrame.setActiveLayerByName(parent.MapFrame.theParcelLayerName);
            panning =
            false;
            zooming =
            false;
            shapeSelectBuffer =
            false;
            // select mode - requires aimsSelect.js
            if (canQuery)
            {
                toolMode =
                11;
                clickCount =
                0;
                //resetClick();
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "hand";
                    document.body.style.cursor =
                    "hand";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                modeBlurb =
                modeList[5];
            }
            else
            {
                alert(msgList[46]);
            }
            showGeocode =
            false;
            showBuffer =
            false;
            hideLayer("measureBox");
            break;
        case "selectbox":

            //Glen - Will have to change to allow info on other layers
            //parent.MapFrame.setActiveLayerByName(parent.MapFrame.theParcelLayerName);
            panning =
            false;
            zooming =
            false;
            shapeSelectBuffer =
            false;
            // select mode - requires aimsSelect.js
            if (canQuery)
            {
                if (parent.MapFrame.theSelectionMode
                    == 1)
                {
                    selectXMLMode =
                    6
                    XMLMode =
                    6;
                    toolMode =
                    10;
                    queryTool =
                    0;
                    clickCount =
                    0;
                    showBuffer =
                    false;
                    highlightedOne =
                    "";
                    if (isIE)
                    {
                        document.all.theTop.style.cursor =
                        "hand";
                        document.body.style.cursor =
                        "hand";
                        theCursor =
                        document.all.theTop.style.cursor;
                    }
                    //modeBlurb = "select by box";
                    modeBlurb =
                    "Select Rectangle";
                } //box
                if (parent.MapFrame.theSelectionMode
                    == 2)
                {
                    toolMode =
                    11;
                    shapeSelectType =
                    0;
                    if (isIE)
                    {
                        document.all.theTop.style.cursor =
                        "hand";
                        theCursor =
                        document.all.theTop.style.cursor;
                    }
                    //modeBlurb = "select by point";
                    modeBlurb =
                    "Select Rectangle";
                } //point
                if (parent.MapFrame.theSelectionMode
                    == 3)
                {
                    shapeSelectType =
                    1;
                    toolMode =
                    12;
                    if (isIE)
                    {
                        document.all.theTop.style.cursor =
                        "crosshair";
                        document.body.style.cursor =
                        "crosshair";
                        theCursor =
                        document.all.theTop.style.cursor;
                    }
                    //modeBlurb = "select by line";
                    modeBlurb =
                    "Select Rectangle";
                } //line
                if (parent.MapFrame.theSelectionMode
                    == 4)
                {
                    shapeSelectType =
                    2;
                    toolMode =
                    13;
                    if (isIE)
                    {
                        document.all.theTop.style.cursor =
                        "crosshair";
                        document.body.style.cursor =
                        "crosshair";
                        theCursor =
                        document.all.theTop.style.cursor;
                    }
                    //modeBlurb = "select by polygon";
                    modeBlurb =
                    "Select Rectangle";
                } //poly
                if (parent.MapFrame.theSelectionMode
                    == 5)
                {
                    shapeSelectType =
                    3;
                    toolMode =
                    22;
                    if (isIE)
                    {
                        document.all.theTop.style.cursor =
                        "crosshair";
                        document.body.style.cursor =
                        "crosshair";
                        theCursor =
                        document.all.theTop.style.cursor;
                    }
                    //modeBlurb = "select by circle";
                    modeBlurb =
                    "Select Rectangle";
                } //circle
                clickCount =
                0;
            }
            else
            {
                alert(msgList[46]);
            }
            showGeocode =
            false;
            showBuffer =
            false;
            //if (useTextFrame) parent.TextFrame.document.location= appDir + "text.htm";
            hideLayer("measureBox");
            break;
        case "selectline":
            panning =
            false;
            zooming =
            false;
            shapeSelectBuffer =
            false;
            // select mode - requires aimsSelect.js
            if (canQuery)
            {
                toolMode =
                12;
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "crosshair";
                    document.body.style.cursor =
                    "crosshair";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                hideLayer("measureBox");
                if (useTextFrame)
                {
                    parent.TextFrame.document.location =
                    appDir
                        + "text.htm";
                }
                modeBlurb =
                modeList[6];
            }
            else
            {
                alert(msgList[46]);
            }
            //alert("Function Not Implemented");
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        case "selectpoly":
            panning =
            false;
            zooming =
            false;
            shapeSelectBuffer =
            false;
            shapeSelectType =
            2;                         //gsa
            // select mode - requires aimsSelect.js
            if (canQuery)
            {
                toolMode =
                13;
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "crosshair";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                //hideLayer("measureBox");
                modeBlurb =
                "select by polygon";
            }
            else
            {
                alert("can't do this");
            }
            //alert("Function Not Implemented");
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        case "selectshape":
            panning =
            false;
            zooming =
            false;
            shapeSelectBuffer =
            false;
            // select mode - requires aimsSelect.js
            if (canQuery)
            {
                toolMode =
                16;
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "crosshair";
                    document.body.style.cursor =
                    "crosshair";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                hideLayer("measureBox");
                if (useTextFrame)
                {
                    parent.TextFrame.document.location =
                    appDir
                        + "select.htm";
                }
                else
                {
                    Win1 =
                    open("select.htm",
                         "QueryWindow",
                         "width=575,height=150,scrollbars=yes,resizable=yes");
                }
                modeBlurb =
                modeList[8];
            }
            else
            {
                alert(msgList[46]);
            }
            //alert("Function Not Implemented");
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        ///*
        case "buffershape":
            panning =
            false;
            zooming =
            false;
            // interactive shape buffer - not implemented
            if (canQuery)
            {
                toolMode =
                17;
                //toolMode = 16;
                shapeSelectBuffer =
                true;
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "crosshair";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                hideLayer("measureBox");
                if (useTextFrame)
                {


                    //	  break;
                    parent.TOCFrame.document.location =
                    appDir
                        + "../common/html/buffer.htm";
                    //parent.TOCFrame.document.location= appDir + "shapebuffer.htm";
                }
                else
                {
                    Win1 =
                    open("shapeBuffer.htm",
                         "QueryWindow",
                         "width=575,height=150,scrollbars=yes,resizable=yes");
                }
                modeBlurb =
                modeList[11];
            }
            else
            {
                alert(msgList[46]);
            }
            //alert("Function Not Implemented");
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        //*/
        // Geocode Function
        case "geocode":
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            // geocode mode - requires aimsGeocode.js
            hideLayer("measureBox");
            modeBlurb =
            modeList[14];
            geocodeAppMode =
            "locate";
            setupGeocode();
            //parent.TextFrame.document.location= appDir + "addmatch.htm";
            break;

        // Query - Search - Find functions
        //if ((toolName=="attributesel") || (toolName=="query")) {
        case "query":
            // query mode - requires aimsQuery.js
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            queryStartRecord =
            1;
            //toolMode=
            if (canQuery)
            {
                LayerFields.length =
                1;
                LayerFieldType.length =
                1;
                LayerFieldCount =
                0;
                toolMode =
                8;
                modeBlurb =
                modeList[15];
                fieldIndex =
                0;
                setQueryString =
                "";
                hideLayer("measureBox");
                queryForm();
            }
            else
            {
                alert(msgList[46]);
            }
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        case "storedquery":
            // storedquery mode - requires aimsQuery.js
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            queryStartRecord =
            1;
            toolMode =
            51;
            modeBlurb =
            "Search";
            if (canQuery)
            {
                toolMode =
                51;
                modeBlurb =
                modeList[16];
                fieldIndex =
                0;
                setQueryString =
                "";
                hideLayer("measureBox");
                getStoredQueries();
            }
            else
            {
                alert(msgList[46]);
            }
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        case "find":
            //find
            toolMode =
            9;
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            queryStartRecord =
            1;
            if (canQuery)
            {
                LayerFields.length =
                1;
                LayerFieldType.length =
                1;
                LayerFieldCount =
                0;
                fieldIndex =
                0;
                setQueryString =
                "";
                hideLayer("measureBox");
                modeBlurb =
                modeList[17];
                findForm();
            }
            else
            {
                alert(msgList[46]);
            }
            showGeocode =
            false;
            showBuffer =
            false;
            break;
        case "clearsel":
            clearSelection();
            break;

        // Buffer function
        case "buffer":
            //buffer - requires aimsBuffer.js
            if (useBuffer)
            {
                if (checkSelected())
                {
                    toolMode =
                    25;
                    shapeSelectBuffer =
                    false;
                    modeBlurb =
                    modeList[18];
                    writeBufferForm();
                }
                else
                {
                    showBuffer =
                    false;
                    alert(msgList[48]);
                }
            }
            else
            {
                alert(msgList[49]);
            }
            break;
        case "options":
            writeOptionForm();
            break;

        // Print function
        case "print":
            printIt();
            break;

        // custom modes
        case "dbidentify":
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            // identify mode - requires custom db query - not in basic
            if (canQuery)
            {
                toolMode =
                40;
                if (isIE)
                {
                    document.all.theTop.style.cursor =
                    "hand";
                    theCursor =
                    document.all.theTop.style.cursor;
                }
                modeBlurb =
                modeList[3];
            }
            else
            {
                alert(msgList[46]);
            }
            //alert("Function Not Implemented");
            showGeocode =
            false;
            if (useTextFrame)
            {
                parent.TextFrame.document.location =
                appDir
                    + "text.htm";
            }
            hideLayer("measureBox");
            break;
        case "extract":
            extractIt();
            break;
        case "legend_original":
            if (aimsLegendPresent)
            {
                if (imsURL
                    != "")
                {
                    if (hasTOC)
                    {
                        if (legendVisible)
                        {
                            legendVisible =
                            false;
                            //writeLayerList();
                            parent.TOCFrame.document.location =
                            appDir
                                + "toc.htm";
                        }
                        else
                        {
                            legendVisible =
                            true;
                            getLegend();
                        }
                    }
                    else
                    {
                        legendVisible =
                        true;
                        getLegend();
                    }
                }
                else
                {
                    alert(msgList[45]);
                }
            }
            else
            {
                alert(msgList[50]);
            }
            break;
        case "legend":
            getLegend();
            break;
        case "layerlist":
            parent.TOCFrame.document.location =
            appDir
                + "toc.htm";
            break;
        case "address":
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            // geocode mode - requires aimsGeocode.js
            hideLayer("measureBox");
            modeBlurb =
            modeList[21];
            geocodeAppMode =
            "address";
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "hand";
                theCursor =
                document.all.theTop.style.cursor;
            }
            toolMode =
            7;
            //parent.TextFrame.document.location= appDir + "addmatch.htm";
            break;
        case "route":
            // route requires aimsRoute.js and RouteServer extension
            panning =
            false;
            zooming =
            false;
            selectBox =
            false;
            shapeSelectBuffer =
            false;
            // geocode mode - requires aimsGeocode.js
            hideLayer("measureBox");
            modeBlurb =
            modeList[21];
            geocodeAppMode =
            "route";
            if (isIE)
            {
                document.all.theTop.style.cursor =
                "hand";
                theCursor =
                document.all.theTop.style.cursor;
            }
            toolMode =
            6;
            setupGeocode();
            break;
        case "addlayer" :
            aimsCustomPresent =
            true;
            sendMapXML(addLotsToMap());
            break;
        default:
            alert(msgList[51]);
            break;
    }
    modeName =
    modeBlurb;
    if (useModeFrame)
    {
        writeModeFrame(modeBlurb);
    } else if ((drawFloatingMode)
        && (modeLayerOn))
    {
        writeModeLayers(modeBlurb);
    } else if ((modeRefreshMap)
        && (drawModeOnMap))
    {
        //var theString = writeXML();
        sendMapXML();
    }
}
// check for mouseup
function chkMouseUp(e)
{
    if ((toolMode
        == 1)
        && (zooming))
    {
        stopZoomBox(e);
    }
    if ((toolMode
        == 2)
        && (zooming))
    {
        stopZoomOutBox(e);
    }
    if ((toolMode
        == 3)
        && (panning))
    {
        stopPan(e);
    }
    if ((toolMode
        == 10)
        && (selectBox))
    {
        stopSelectBox(e);
    }
    if ((toolMode
        == 101)
        && (selectBox))
    {
        stopSelectBox(e);
    }
    if ((toolMode
        == 102)
        && (selectBox))
    {
        stopSelectBox(e);
    }
    return false;
}
// perform appropriate action with mapTool
function mapTool(e)
{
    var theButton = 0;
    // get the button pushed... if right, ignore... let browser do the popup... it will anyway
    if (isNav)
    {
        theButton =
        e.which;
    }
    else
    {
        theButton =
        window.event.button;
    }
    if (theButton
        == leftButton)
    {
        getImageXY(e);
        if ((mouseX
            >= 0)
                && (mouseX
            < iWidth)
                && (mouseY
            >= 0)
            && (mouseY
            < iHeight))
        {
            //if ((!isNav) || (!is5up)) {
            if ((hasOVMap)
                    && (ovIsVisible)
                    && (mouseX
                < i2Width
                + ovBoxSize)
                    && (mouseY
                < i2Height)
                && (ovMapIsLayer))
            {
                //alert(mouseX + ", " + mouseY);
                ovMapClick(mouseX,
                           mouseY);
                window.status =
                "On OV Map Area";
                //}
            }
            else
            {
                //alert(mouseX + "," + mouseY);
                switch (toolMode)
                {
                    case 1:
                        startZoomBox(e);
                        return false;
                        break;
                    case 2:
                        startZoomOutBox(e);
                        return false;
                        break;
                    case 3:
                        startPan(e);
                        return false;
                        break;
                    case 4:
                        identify(e);
                        break;
                    case 5:
                        // identify all
                        identifyAll(e);
                        break;

                    // custom modes
                    ///*
                    case 6:
                        // route - requires custom route routine - not in default
                        // requires aimsRoute.js, aimsGeocode.js
                        //routeClick = routeClick + 1;
                        //if (routeClick > 2) routeClick = 2;
                        //setRouteXY()
                        //writeRoutePage();
                        address(e);
                        break;
                    //*/
                    case 7:
                        // address - requires custom reverseGeocode routine - not in default
                        // requires aimsRoute.js, aimsGeocode.js
                        address(e);
                        break;
                    //*/
                    case 10:
                        //select(e);
                        startSelectBox(e);
                        return false;
                        //select(e);
                        break;
                    case 11:
                        //select point
                        //alert("In Case 11");
                        if (checkIfActiveLayerAvailable())
                        {
                            select(e);
                        }
                        break;
                    case 12:
                        //select line
                        clickType =
                        2;
                        activateRubberband(e);
                        break;
                    case 13:


                        //select polygon
                        clickType =
                        3;
                        activateRubberband(e);
                        break;

                    //select polygon
                    //if (checkIfActiveLayerAvailable()) {
                    //clickType=3;
                    //clickAddPoint();
                    //if (useTextFrame) {
                    //	if (parent.TextFrame.document.title!==modeList[7]) {
                    //		parent.TextFrame.document.location= appDir + "selectpoly.htm";
                    //	}
                    //}
                    //}
                    //break;
                    case 15:
                        // hyperlink
                        hyperLink(e);
                        break;
                    case 16:
                        //select shape
                        if (checkIfActiveLayerAvailable())
                        {
                            clickType =
                            2;
                            clickAddPoint();
                            if (useTextFrame)
                            {
                                if (parent.TextFrame.document.title
                                    !== modeList[8])
                                {
                                    parent.TextFrame.document.location =
                                    appDir
                                        + "select.htm";
                                }
                            }
                        }
                        break;
                    ///*
                    case 17:
                        //buffer shape -
                        if (checkIfActiveLayerAvailable())
                        {
                            clickType =
                            2;
                            clickAddPoint();
                            if (useTextFrame)
                            {
                                if (parent.TextFrame.document.title
                                    != modeList[11])
                                {
                                    parent.TextFrame.document.location =
                                    appDir
                                        + "shapeBuffer.htm";
                                }
                            }
                        }
                        break;
                    //*/
                    case 20:
                        // measure
                        clickType =
                        1;
                        activateRubberband(e);
                        //clickAddPoint();
                        break;
                    case 21:
                        // shape
                        clickType =
                        4;
                        clickAddPoint();
                        break;
                    case 22:
                        //select circle
                        clickType =
                        1;
                        activateRubberband(e);
                        break;
                    case 30:
                        // hyperlink
                        hyperLinkAny(e);
                        break;
                    case 40:
                        // db identify - requires custom db query - not in default
                        if (aimsDBPresent)
                        {
                            matchDBLinkLayer(dbLinkLayer);
                            dbIdentify(e);
                        }
                        break;
                    default:
                        if (toolMode
                            >= 1000)
                        {
                            customMapTool(e);
                        }
                }
            }
        }
    }
    if (theButton
        == rightButton)
    {
        if (activate_rb)
        {
            if (toolMode
                == 22)
            {
                circleCentreSaved =
                false;
            }
            deactivateRubberband();
            closePolyline(e);
            resetClick();
        }
    }
}
// update measureBox layer
function updateMeasureBox()
{
    if (isNav4)
    {
        var theForm = document.layers["measureBox"].document.forms[0];
    }
    else
    {
        //var theForm = document.measureBox.forms[0];
        var theForm = document.forms[0];
    }
    var j = 1;
    for (var i = 0; i
        < sUnitList.length; i++)
    {
        if (ScaleBarUnits
            == sUnitList[i])
        {
            j =
            i;
        }
    }
    var u = Math.pow(10,
                     numDecimals);
    var tMeas = 0;
    if (totalMeasure
        != 0)
    {
        tMeas =
        parseInt(totalMeasure
                     * u
                     + 0.5)
            / u;
    }
    theForm.theMeasTotal.value =
    tMeas
        + " "
        + unitList[j];
    theForm.theMeasSegment.value =
    currentMeasure
        + " "
        + unitList[j];
    showLayer("measureBox");
}
// If we're using the jg_graphics.
// Finish and perform appropriate action with mapTool on map DOUBLECLICK.
function finishPolyline(e)
{
    if ((clickCount
        > 1)
        || (toolMode
        == 22))
    {
        deactivateRubberband(); // Hide rubberband
        closePolyline(e); // Close the polyline to a polygon-look-a-like
        circleCentreSaved =
        false;
        switch (toolMode)
        {
            case 13:  // polygon
                sendShapeSelect(2);
                break;
            case 12:  // line
                sendShapeSelect(1);
                break;
            case 20:  // measure
                resetClick();
                break;
            case 22: // circle
                var dx = parseFloat(clickPointX[0]
                                        - circleCenterX);
                var dy = parseFloat(clickPointY[0]
                                        - circleCenterY);
                var buffDist = Math.round(Math.sqrt((Math.pow(dx,
                                                              2))
                                                        + (Math.pow(dy,
                                                                    2)))
                                              * 100)
                    / 100;
                shapeBufferDistance =
                buffDist; // Buffer radius
                sendShapeSelect(3);
                break;
            default:
                return false;
        }
    }
    else
    {
        if (activate_rb)
        {
            if (toolMode
                == 13)
            {
                alert("At least two or more points \nneeded to create a selection area.");
            }
        }
    }
}
function noContext()
{
    return false;
}
