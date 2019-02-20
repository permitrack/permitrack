<%@ page import="com.sehinc.dataview.DataViewConstants"%>
// aimsMap.js
/*
*  JavaScript template file for ArcIMS HTML Viewer
*		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js
*/

aimsMapPresent=true;

// global variables
    // show xml responses
var debugOn = 0;
  // can debug be toggled?
var setDebug = true;

var useLimitExtent=false;
var getStartingExtent=true;
var getLimitExtent=true;
var enforceFullExtent=false;

// map extents. . . dynamically updated
var eLeft = -180.0;
var eRight = 180.0;
var eTop = 90.0;
var eBottom = -90.0;
var fullLeft = eLeft;
var fullRight = eRight;
var fullTop = eTop;
var fullBottom = eBottom;

// map size . . . dynamically updated
var iWidth = 630;
var iHeight = 512;
// location map size . . . dynamically updated
var i2Width = 150;
var i2Height = 120;

var imageLimitLeft = limitLeft;
var imageLimitTop = limitTop;
var imageLimitRight = limitRight;
var imageLimitBottom = limitBottom;

var appDir = "";

var xDistance = Math.abs(eRight-eLeft);
var yDistance = Math.abs(eTop-eBottom);
var panX = xDistance * panFactor;
var panY = yDistance * panFactor;
var pixelX = xDistance/iWidth;
var pixelY = yDistance/iHeight;
var mapX = eLeft;
var mapY = eTop;
var lastLeft = eLeft;
var lastRight = eRight;
var lastTop = eTop;
var lastBottom = eBottom;
var fullOVLeft = eLeft;
var fullOVRight = eRight;
var fullOVTop = eTop;
var fullOVBottom = eBottom;
var theCursor = "crosshair";
var canLoad=false;
//if (imsURL!="") canLoad=false;

var fullWidth = Math.abs(fullRight - fullLeft);
var fullHeight = Math.abs(fullTop - fullBottom);
var fullOVWidth = Math.abs(fullOVRight - fullOVLeft);
var fullOVHeight = Math.abs(fullOVTop - fullOVBottom);
var mapScaleFactor = xDistance / iWidth;

var geocodeX = 0;
var geocodeY = 0;
var showGeocode = false;
var geocodeLabel = "";

var queryZoom=false;

var sQuote = "'";
var dQuote = '"';

var toolMode = 1;
var legendVisible=false;
// set legend visible at service load... showTOC=false & hasTOC=true
if ((hasTOC) && (!showTOC)) legendVisible=true;

MapUnits = MapUnits.toUpperCase();
ScaleBarUnits = ScaleBarUnits.toUpperCase();
if (drawScaleBar2) {
    switch (ScaleBarUnits) {
        case "MILES":
            ScaleBar2Units = "KILOMETERS";
            break;
        case "KILOMETERS":
            ScaleBar2Units = "MILES";
            break;
        case "FEET":
            ScaleBar2Units = "METERS";
            break;
        case "METERS":
            ScaleBar2Units = "FEET";
            break;

    }
}


// save the extent
function saveLastExtent() {
    lastLeft = eLeft;
    lastRight = eRight;
    lastTop = eTop;
    lastBottom = eBottom;
}

// zoom out to full extent
function fullExtent() {
    if (aimsDHTMLPresent) moveLayer("theMap",hspc,vspc);
    window.scrollTo(0,0);
    saveLastExtent();
    eLeft = fullLeft;
    eRight = fullRight;
    eTop = fullTop;
    eBottom = fullBottom;
    //var theString = writeXML();
    sendMapXML();
}

function startExtent() {
    if (aimsDHTMLPresent) moveLayer("theMap",hspc,vspc);
    window.scrollTo(0,0);
    saveLastExtent();
    eLeft = startLeft;
    eRight = startRight;
    eTop = startTop;
    eBottom = startBottom;
    //var theString = writeXML();
    sendMapXML();
}

// zoom back to last extent
function zoomBack() {
    if (aimsDHTMLPresent) moveLayer("theMap",hspc,vspc);
    var left1 = eLeft;
    var right1 = eRight;
    var top1 = eTop;
    var bottom1 = eBottom;
    eLeft = lastLeft;
    eRight = lastRight;
    eTop = lastTop;
    eBottom = lastBottom;
    lastLeft = left1;
    lastRight = right1;
    lastTop = top1;
    lastBottom = bottom1;
    //var theString = writeXML();
    sendMapXML();
}

function zoomToPoint(xIn, yIn, drawIt,theLabel) {
    //alert(xIn + "," + yIn);
    var mWMargin = 0;
    var mHMargin = 0;
    mWMargin = Math.abs(limitRight-limitLeft) * selectPointMargin;
    mHMargin = Math.abs(limitTop-limitBottom) * selectPointMargin;
    saveLastExtent();
    eLeft = xIn - mWMargin;
    eRight = xIn + mWMargin;
    eTop = yIn + mHMargin;
    eBottom = yIn - mHMargin;
    if (drawIt) {
        showGeocode=true;
        geocodeX=xIn;
        geocodeY=yIn;
        geocodeLabel=theLabel;
    }
    sendMapXML();
}

function zoomToEnvelope(minXin,minYin,maxXin,maxYin) {
    saveLastExtent();
    eLeft=minXin;
    eBottom=minYin;
    eRight=maxXin;
    eTop=maxYin;
    checkFullExtent();
    sendMapXML()

}

// zoom to center of fullextent at set scale
function zoomScale(inScale) {
    // inScale is scale factor where 1.0 = 100% of fullWidth and fullHeight
    var halfWidth = fullWidth / 2;
    var halfHeight = fullHeight / 2
    var midX = eRight - (xDistance / 2);
    var midY = eTop - (yDistance / 2);
    var newWidth = halfWidth * inScale;
    var newHeight = halfHeight * inScale;
    saveLastExtent();
    eLeft = midX - newWidth;
    eRight = midX + newWidth;
    eTop = midY + newHeight;
    eBottom = midY - newHeight;
    sendMapXML();

}

// get URLs and extents from URL
function getCommandLineParams(cmdString) {
    // Parse out from URL querystring parameters
    // to pass to the viewer.
    // Syntax:
    // Host=Hostmachine					name of Host, if not default
    // Service=MapService				name of MapService, if not default
    // OVMap=OvMapService				name of Overview MapService, if not default
    // Box=minX:minY:maxX:maxY			extent to be displayed
    // Layers=0101101					visible layers,starting from topmost: 0=not visible;1=visible
    // ActiveLayer=layerIndex			index of layer to be active, if not default
    // Query=queryExpression			query expression to be send on load - expression must be escaped in URL
    // QueryZoom=Yes					zoom to above query?
    // StartLeft,StartTop,
    // StartRight,StartBottom			starting coords - alternative to Box
    // LimitLeft,LimitTop,
    // LimitRight,LimitBottom			limit coords
    // DebugOn=Yes						Show all requests, responses
    //
    setLayerVisible.length=0;
    var cmdString2 = cmdString.toUpperCase();
    var startpos = 0;
    var endpos = 0;
    var pos = cmdString2.indexOf("HOST=");
    if (pos!=-1) {
        startpos = pos + 5;
        endpos = cmdString.indexOf("&",startpos);
        if (endpos==-1) endpos = cmdString.length;
        hostName = cmdString.substring(startpos,endpos);
        serverURL  = document.location.protocol + "//" + hostName + "<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=";
	}
	pos = cmdString2.indexOf("SERVICE="); // formally was MAPSERVICE=
	if (pos!=-1) {
		startpos = pos + 8;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		imsURL = serverURL + cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("OVMAP="); // formally was OVMAPSERVICE=
	if (pos!=-1) {
		startpos = pos + 6;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		imsOVURL = serverURL + cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("BOX=");
	if (pos!=-1) {
		startpos = pos + 4;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		var boxString = cmdString.substring(startpos,endpos);
		//alert(boxString);
		var xyBox = boxString.split(":");
		if (xyBox.length==4) {
			startLeft = parseFloat(xyBox[0]);
			startBottom = parseFloat(xyBox[1]);
			startRight = parseFloat(xyBox[2]);
			startTop = parseFloat(xyBox[3]);
			eLeft=startLeft;
			eBottom=startBottom;
			eRight=startRight;
			eTop = startTop;
		}
		//xyBox=null;
	}
	pos = cmdString2.indexOf("MAXRECT=");
	if (pos!=-1) {
		startpos = pos + 8;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		var boxString = cmdString.substring(startpos,endpos);
		//alert(boxString);
		var xyBox = boxString.split(":");
		if (xyBox.length==4) {
			limitLeft = xyBox[0];
			limitBottom = xyBox[1];
			limitRight = xyBox[2];
			limitTop = xyBox[3];
		}
		//xyBox=null;
	}
	if (aimsLayersPresent) {
		// you need to have aimsLayers.js loaded
		pos = cmdString2.indexOf("LAYERS=");
		if (pos!=-1) {
			startpos = pos + 7;
			endpos = cmdString.indexOf("&",startpos);
			if (endpos==-1) endpos = cmdString.length;
			var layers = cmdString.substring(startpos,endpos);
			//"0" means the layer should be turned off, and "1" means
			//the layer should be visible.  For example, "1001" means there
			//are 4 layers.  The first and last layer are visible, and the
			//middle two layers are turned off. The first number represents
			//the top-most layer.

			//Parse the layers string
			var numLayers=layers.length;
			icount=0;
			//alert(layers);
			while (icount<=numLayers-1) {
			  onoff=layers.substring(icount,icount+1);
			  if (onoff=='0') {
			  	setLayerVisible[icount]=false;
			  }
			  else if (onoff=='1') {
			    setLayerVisible[icount]=true;
			  }
			  icount=icount+1;
			}
			layers="";
		}

		pos = cmdString2.indexOf("ACTIVELAYER=");
		if (pos!=-1) {
			startpos = pos + 12;
			endpos = cmdString.indexOf("&",startpos);
			if (endpos==-1) endpos = cmdString.length;
			var actlyr = cmdString.substring(startpos,endpos);
			//alert(actlyr);
			ActiveLayerIndex = parseInt(actlyr);
			selectType = LayerType[ActiveLayerIndex];
			actlyr = "";
		}
		if (aimsQueryPresent) {
			// you need to have aimsQuery.js loaded
			pos = cmdString2.indexOf("QUERY=");
			if (pos!=-1) {
				startpos = pos + 6;
				endpos = cmdString.indexOf("&",startpos);
				if (endpos==-1) endpos = cmdString.length;
				var escQuery = cmdString.substring(startpos,endpos);
				escQuery = replacePlus(escQuery);
				escQuery = unescape(escQuery);
				highlightedOne = makeXMLsafe(escQuery);
				escQuery="";
			}
			if (highlightedOne!="") {
				pos = cmdString2.indexOf("QUERYZOOM=YES");
				if (pos!=-1) queryZoom = true;
			}
		}
	}

	pos = cmdString2.indexOf("STARTLEFT=");
	if (pos!=-1) {
		startpos = pos + 10;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		startLeft = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("STARTTOP=");
	if (pos!=-1) {
		startpos = pos + 9;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		startTop = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("STARTRIGHT=");
	if (pos!=-1) {
		startpos = pos + 11;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		startRight = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("STARTBOTTOM=");
	if (pos!=-1) {
		startpos = pos + 12;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		startBottom = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("LIMITLEFT=");
	if (pos!=-1) {
		startpos = pos + 10;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		limitLeft = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("LIMITTOP=");
	if (pos!=-1) {
		startpos = pos + 9;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		limitTop = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("LIMITRIGHT=");
	if (pos!=-1) {
		startpos = pos + 11;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		limitRight = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("LIMITBOTTOM=");
	if (pos!=-1) {
		startpos = pos + 12;
		endpos = cmdString.indexOf("&",startpos);
		if (endpos==-1) endpos = cmdString.length;
		limitBottom = cmdString.substring(startpos,endpos);
	}
	pos = cmdString2.indexOf("EXTENT=AUTO");
	if (pos!=-1) {
		startLeft=0;
		startRight=0;
		startTop=0;
		startBottom=0;
		limitLeft=0;
		limitRight=0;
		limitTop=0;
		limitBottom=0;
		getStartingExtent=true;
		getLimitExtent=true;
	}
	pos = cmdString2.indexOf("DEBUG=YES");
	if (pos==-1) pos = cmdString2.indexOf("DEBUG=TRUE");
	if (pos!=-1) {
		debugOn=3;
	}

	//alert("imsURL=" + imsURL + "\nimsOVURL=" + imsOVURL);
	if (imsURL!="") {
		imsQueryURL= imsURL + "&CustomService=Query";
		imsGeocodeURL = imsURL + "&CustomService=Geocode";
		//canLoad=false;
	}
	// if starting extents zero'd then flag to get start from mapservice
	if ((startLeft!=0) && (startRight!=0)) getStartingExtent=false;
	// if limit extents zero'd then flag to get max from mapservice
	if ((limitLeft!=0) && (limitRight!=0)) {
		getLimitExtent=false;
		enforceFullExtent=true;
	}
}

// get directory path of URL
function getPath(theFullPath) {
	var theSlash = theFullPath.lastIndexOf("/");
	var theDir = theFullPath.substring(0,theSlash);
	if (theDir==null) theDir="";
	theDir = theDir + "/";
	return theDir;

}

// check for existance of layer
function hasLayer(name) {
	var result = false;
	if (isNav4) {
		if (document.layers[name]!=null) result=true;
	}  else if (isIE) {
		if (eval('document.all.' + name)!=null) result=true;
	} else if (isNav) {
		var theElements = document.getElementsByTagName("DIV");
		var theObj;
		var j = -1;
		for (i=0;i<theElements.length;i++) {
			if (theElements[i].id==name) result=true;
		}
    }
	return result;
}

// put up the "RetriveData" image
function showRetrieveData() {
	if (hasLayer("LoadData")) {
		showLayer("LoadData");
                //alert("loading data?");
	}
}

// hide the "RetriveData" image
function hideRetrieveData() {
	if (hasLayer("LoadData")) {
		hideLayer("LoadData");
	}
}

// put up the "RetriveMap" image
function showRetrieveMap() {
	if (hasLayer("LoadMap")) {
		showLayer("LoadMap");
	}
}

// hide the "RetriveMap" image
function hideRetrieveMap() {
	if (hasLayer("LoadMap")) {
		hideLayer("LoadMap");
	}
}

/*  *****************************************************
*	Various Distance Conversion Functions
*	*****************************************************
*/

// get scale bar distance
function getScaleBarDistance() {
	// get distance from left and right values in map units
	// convert to ScaleBar units then clip to ScaleBar size

	// Note: decimal are not hard coded to allow use with locales using commas instead of points.
	var mUnits = MapUnits;
	var mDistance = eRight - eLeft;
	var sbDistance = 0
	if (MapUnits=="DEGREES") {
		// if DEGREES then convert to feet
			// first get mid y point
		var midY = eBottom + (eTop-eBottom)/2;
		var tempL = eLeft;
		var tempR = eRight;
		if (tempL<-180) tempL = (-1799999 /10000);
		if (tempR>180) tempL = (1799999/10000);
		var Lon1 = tempL * Math.PI / 180;
		var Lon2 = tempR * Math.PI / 180;
		var Lat1 = midY * Math.PI / 180;
		var Lat2 = midY * Math.PI / 180;
		var LonDist = Math.abs(Lon2-Lon1);
		var LatDist = Math.abs(Lat1-Lat2);
		var A = Math.pow(Math.sin(LatDist / 2),2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(LonDist /2),2);
		//var A = Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(LonDist /2),2);
		var C = 2 * Math.asin(Math.min(1, Math.sqrt(A)));
		var D = (3963 - 13 * Math.sin((Lat1 + Lat2) / 2)) * C

		mDistance = D * 5280;
		mUnits = "FEET";
	}
	if (mUnits != ScaleBarUnits) {
		theDist = mDistance
		mDistance = convertUnits(theDist,mUnits,ScaleBarUnits);
	}

	sbDistance = mDistance/5;
	// /*
	var num1 = 0;
	var num2 = sbDistance;
	if (sbDistance>10000000) {
		num1 = parseInt(sbDistance/5000000);
		num2 = num1 * 5000000;
	} else if (sbDistance>1000000) {
		num1 = parseInt(sbDistance/500000);
		num2 = num1 * 500000;
	} else if (sbDistance>100000) {
		num1 = parseInt(sbDistance/50000);
		num2 = num1 * 50000;
	} else if (sbDistance>10000) {
		num1 = parseInt(sbDistance/5000);
		num2 = num1 * 5000;
	} else if (sbDistance>1000) {
		num1 = parseInt(sbDistance/500);
		num2 = num1 * 500;
	} else if (sbDistance>100) {
		num1 = parseInt(sbDistance/50);
		num2 = num1 * 50;
	} else if (sbDistance>10) {
		num1 = parseInt(sbDistance/5);
		num2 = num1 * 5;
	} else if (sbDistance>1) {
		num1 = parseInt(sbDistance/(25/100));
		num2 = num1 * (25/100);
	} else if (sbDistance>0.1) {
		num1 = parseInt(sbDistance/(25/1000));
		num2 = num1 * (25/1000);
	}
	sbDistance = num2;
	// */
	if (sbDistance > 2) {
		ScaleBarPrecision = "0";
	} else if (sbDistance > 1) {
		ScaleBarPrecision = "1";
	} else if (sbDistance > 1/10) {
		ScaleBarPrecision = "2";
	} else if (sbDistance > 1/100) {
		ScaleBarPrecision = "3";
	} else {
		ScaleBarPrecision = "4";
	}

	//alert(mDistance);
	return sbDistance;
}

// calculate distance to current scalebarunits
function calcDistance(mX,mY) {
	// Note: decimal are not hard coded to allow use with locales using commas instead of points.
	if (clickCount>0) {
		var mUnits = MapUnits;
		var mDistance = 0;
		var p = clickCount-1;
		var Lon1 = clickPointX[p] * Math.PI / 180;
		var Lon2 = mX * Math.PI / 180;
		var Lat1 = clickPointY[p] * Math.PI / 180;
		var Lat2 = mY * Math.PI / 180;
		var LonDist = Lon1-Lon2;
		var LatDist = Lat1-Lat2;
		if (MapUnits=="DEGREES") {
			var A = Math.pow(Math.sin(LatDist / 2),2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(LonDist /2),2);
			//var A = Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(LonDist /2),2);
			var C = 2 * Math.asin(Math.min(1, Math.sqrt(A)));
			var D = (3963 - 13 * Math.sin((Lat1 + Lat2) / 2)) * C
			mDistance = D * 5280;
			mUnits = "FEET";
		} else {
			var xD = Math.abs(mX - clickPointX[p]);
			var yD = Math.abs(mY - clickPointY[p]);
			mDistance = Math.sqrt(Math.pow(xD,2) + Math.pow(yD,2));
		}

                //Math Fix - Glen 3/9/06 AN error in one of the math functions?
                mDistance = ((Math.abs(mX - clickPointX[p]) * Math.abs(mX - clickPointX[p])) + (Math.abs(mY - clickPointY[p]) * Math.abs(mY - clickPointY[p])));
                mDistance = Math.sqrt(mDistance);

		var theDist = convertUnits(mDistance,mUnits,ScaleBarUnits);
		var u = Math.pow(10,numDecimals);
//OLD		currentMeasure = parseInt(theDist*u+(5/10))/u;
		currentMeasure = mDistance;
		updateMeasureBox();

	}
}

// convert the amounts to new units
function convertUnits(theDist1,mUnits,sUnits) {
	// Note: decimal are not hard coded to allow use with locales using commas instead of points.
	var theDist = parseFloat(theDist1);
	var mDistance = theDist;
	//alert(theDist);
	if (mUnits == "FEET") {
		if (sUnits=="MILES") {
			mDistance = theDist / 5280;
		} else if (sUnits == "METERS") {
			mDistance = theDist * (3048/10000);
		} else if (sUnits == "KILOMETERS") {
			mDistance = theDist * (3048/10000000);
		}
	} else {
		if (sUnits=="MILES") {
			mDistance = theDist * (6213711922/10000000000000);
		} else if (sUnits == "FEET") {
			mDistance = theDist * (3280839895/1000000000);
		} else if (sUnits == "KILOMETERS") {
			mDistance = theDist / 1000;
		}
	}
	var u = Math.pow(10,numDecimals);
	//alert(u);
	if (!isNav) mDistance = parseInt(mDistance * u + (5/10)) / u
	//alert(mDistance);
	return mDistance;
}

// set new map extent
function setExtent(newLeft, newTop, newRight, newBottom) {
	eLeft = newLeft;
	eTop = newTop;
	eRight = newRight;
	eBottom = newBottom;
}

//  set new full extent
function setFullExtent(maxLeft, maxTop, maxRight, maxBottom) {
	fullLeft = maxLeft;
	fullTop = maxTop;
	fullRight = maxRight;
	fullBottom = maxBottom;
	fullWidth = Math.abs(fullRight-fullLeft);
	fullHeight = Math.abs(fullTop-fullBottom);
}

function beforeMapRefresh() {
 // add any code for checking parameters before map update
 // called by sendXML() before writing XML request
 //alert("Yup. It works.[1]");
}

function afterMapRefresh() {
 // add any code for checking parameters after map update
 // called by processXML() if XMMmode=1 after image and extent is updated
 //alert("Yup. It works.[1]");
}

// zoom using button.
//		zoomType: 1=in; 2=out
function zoomButton(zoomType) {
	saveLastExtent();
	if (zoomType == 1) {
		// zoom in
		eLeft = eLeft + (xHalf/2);
		eRight = eRight - (xHalf/2);
		eTop = eTop - (yHalf/2);
		eBottom = eBottom + (yHalf/2);
	}
	else {
		// zoom out
		eLeft = eLeft - xHalf;
		eRight = eRight + xHalf;
		eTop = eTop + yHalf;
		eBottom = eBottom - yHalf;
	}
	checkFullExtent();
	//var theString = writeXML();
	sendMapXML();
}

// pan using arrow buttons
function panButton(panType) {
	//alert("Left:" + left + "\nTop:" + top + "\nRight:" + right + "\nBottom:" + bottom + "\nWidth:" + xDistance + "\nHeight:" + yDistance + "\nPanX:" + panX + "\nPanY:" + panY);
	saveLastExtent();
	xDistance = Math.abs(eRight-eLeft);
	yDistance = Math.abs(eTop-eBottom);
	panX = xDistance * panFactor;
	panY = yDistance * panFactor;
	switch(panType) {
	//if (panType == 1) {
	case 1:
		//west
		eLeft = eLeft - panX;
		eRight = eLeft + xDistance;
		break
	case 2:
		// north
		eTop = eTop + panY;
		eBottom = eTop - yDistance;
		break
	case 3:
		// east
		eRight = eRight + panX;
		eLeft = eRight - xDistance;
		break
	case 4:
		// south
		eBottom = eBottom - panY;
		eTop = eBottom + yDistance;
		break
	case 5:
		// southwest
		eTop = eTop - panY;
		eLeft = eLeft - panX;
		eBottom = eTop - yDistance;
		eRight = eLeft + xDistance;
		break
	case 6:
		// northwest
		eTop = eTop + panY;
		eLeft = eLeft - panX;
		eBottom = eTop - yDistance;
		eRight = eLeft + xDistance;
		break
	case 7:
		// northeast
		eTop = eTop + panY;
		eLeft = eLeft + panX;
		eBottom = eTop - yDistance;
		eRight = eLeft + xDistance;
		break
	case 8:
		// southeast
		eTop = eTop - panY;
		eLeft = eLeft + panX;
		eBottom = eTop - yDistance;
		eRight = eLeft + xDistance;
	}
	checkFullExtent();
	//alert("Left:" + left + "\nTop:" + top + "\nRight:" + right + "\nBottom:" + bottom + "\nWidth:" + xDistance + "\nHeight:" + yDistance + "\nPanX:" + panX + "\nPanY:" + panY);
	//var theString = writeXML();
	sendMapXML();

}

function checkFullExtent() {
	if (enforceFullExtent) {
		var xDistance1 = eRight - eLeft;
		var yDistance1 = eTop - eBottom;

		if (xDistance1>fullWidth) xDistance1 = fullWidth;
		if (yDistance1>fullHeight) yDistance1 = fullHeight;

		//alert("Adjusting extent to image proportions");
		if ((eLeft < limitLeft) && (eTop > limitTop)) {
			eLeft = limitLeft;
			eRight = limitRight;
		} else {
			if (eLeft < limitLeft) {
				eLeft = limitLeft;
				eRight = eLeft + xDistance1;
			} else if (eRight > limitRight) {
				eRight = limitRight;
				eLeft = eRight - xDistance1;
			}
		}
		if ((eLeft < limitLeft) && (eTop > limitTop)) {
			eTop = limitTop;
			eBottom = limitBottom;
		} else {
			if (eTop > limitTop) {
				eTop = limitTop;
				eBottom = eTop - yDistance1;
			} else if (eBottom < limitBottom) {
				eBottom = limitBottom;
				eTop = eBottom + yDistance1;
			}
		}
	}

}
