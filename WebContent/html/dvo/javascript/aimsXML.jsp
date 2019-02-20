<%@ page import="com.sehinc.dataview.DataViewConstants"%>
// aimsXML.js
/*
*  JavaScript template file for ArcIMS HTML Viewer
*		dependent on ArcIMSparam.js, aimsCommon.js, aimsMap.js,
*/

aimsXMLPresent=true;
// client version
var cVersion = "&ClientVersion=4.0";
/*
***************************************************************************************

Functions for sending XML requests and XML reponses

***************************************************************************************
*/

// global variables
    // change these in aimsCustom.js to send XML response to custom function.
    // use numbers >= 1000
var selectXMLMode = 6;
var identifyXMLMode = 7;
var queryXMLMode = 8;
var findXMLMode = 14;
var hyperlinkXMLMode = 15;

// encoding for XML header
var charEncoding = "UTF-8";
var localeEncoding = 'encoding="' + charEncoding + '" ';

// charset for dynamic HTML pages - static pages must be changed manually
var charSet = "ISO-8859-1"

var formColor = "#000000";

// common dynamic variables
var XMLMode = 1;
var okToSend = true;

var xHalf = xDistance/2;
var yHalf = yDistance/2;

// ending position to start parse scan of XML string
var xmlEndPos = 0;

var lastXMLResponse = "";

//var theImageType = "PNG";

var drawOVExtentBox=false;
// force a request for Overview map image with each map request?
var forceNewOVMap = false;
// the base servlet connector URL
var connectorURL = document.location.protocol + "//" + hostName + "<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=redirect";

var requestMethod = "Servlet";
if (parent.connectorType!="") requestMethod = parent.connectorType;

	// index of current active MapService - default is zero - multiples in MultiService sample
var activeMapServiceIndex = 0;
	// array for determining if extent coordinates should have comma instead of point for decimals
var forceCommaInRequest = new Array();
forceCommaInRequest[activeMapServiceIndex] = false;
var forceCommaInOV = false;

var pastStart=false;
if (hasOVMap != true) pastStart = true;

// adjust limit extent to match image proportions?
var adjustLimit=true;

// dynamic flag for setting focus on external attribute display
var focusOnData = false;
var extWin;

/// send in XML request and get XML response -
function sendToServer(URLString,XMLRequest,theType) {

	if (parent.PostFrame.document.forms[0]!=null) {
		if (okToSend) {
			XMLMode = theType;
			if (XMLMode==1) showRetrieveMap();
			if (debugOn>2) alert(msgList[8] + URLString + msgList[9] + XMLRequest);
			okToSend = false;
			if (requestMethod.toUpperCase()=="JSP") {
				// uses JSP Connector - must be installed and class path to arcims_jsp.jar set
				jspSendToServer(URLString,XMLRequest,theType);
			} else {
				// default Servlet Connector
				htmlSendToServer(URLString,XMLRequest,theType);
			}
		} else {
			alert(msgList[10]);
			hideRetrieveMap();
			hideRetrieveData();
		}
	} else {
		alert(msgList[11]);
		hideRetrieveMap();
		hideRetrieveData();
	}

}


function htmlSendToServer(URLString,XMLRequest,theType) {
	// uses default Servlet Connector - requestMethod="Servlet";
	var cVersion = "&ClientVersion=4.0";
	var thePostForm = parent.PostFrame.document.forms[0];
	URLString = URLString + cVersion;
	var requestURL = URLString;
	if (thePostForm.RedirectURL!=null) {
		if (isNotSameHostInURL(URLString, hostName)) {
			requestURL = connectorURL;
			thePostForm.RedirectURL.value = URLString;
		} else {
			thePostForm.RedirectURL.value = "";
		}
	}
	thePostForm.action = requestURL + "&Form=True&Encode=False";
	var xmlHeader = '<?xml version="1.0" ' + localeEncoding + '?>';
	thePostForm.ArcXMLRequest.value = xmlHeader + XMLRequest;
	//thePostForm.JavaScriptFunction.value = "parent.MapFrame.processXML";
	if (thePostForm.FormCharset!=null) thePostForm.FormCharset.value = charSet;
	if (thePostForm.BgColor!=null) thePostForm.BgColor.value = "#000000";

	thePostForm.submit();


}

function jspSendToServer(URLString,XMLRequest,theType) {
	// uses JSP Connector - must be installed
		// class path to arcims_jsp.jar must be set
	var cVersion = "&ClientVersion=4.0";
	var theService = getService(URLString);
	var theHost = getHost(URLString);
	theService += cVersion;
	// get the Post Form and set values, then submit to server
	var thePostForm = parent.PostFrame.document.forms[0];
		// set parameters for the AppServerLink
	var xmlHeader = '<?xml version="1.0" ' + localeEncoding + '?>\n';
	thePostForm.ArcXMLRequest.value=xmlHeader + XMLRequest;
	thePostForm.ServerName.value=theHost;
	thePostForm.ServiceName.value=theService;
	// submit to Application Server
	thePostForm.submit();
}


function getHost(theURL) {
	var thisHost = "";
	var startpos = theURL.indexOf("//");
	if (startpos==-1) {
		startpos = 0;
	} else {
		startpos = startpos + 2;
	}
	var endpos = theURL.indexOf("/",startpos);
	if (endpos==-1) endpos = theURL.length;
	thisHost = theURL.substring(startpos,endpos);
	return thisHost;

}

function getService(theURL) {
	var theService = "";
	var startpos = theURL.indexOf("ServiceName=");
	if (startpos!=-1) {
		startpos +=12;
		theService = theURL.substring(startpos);
	}
	return theService;
}


// send custom XML request. . . set up custom response handler
function sendCustomToServer(XMLRequest, theFunction, theType) {
	var theForm = parent.PostFrame.document.forms[0];
	theForm.JavaScriptFunction.value = theFunction;
	sendToServer(imsQueryURL,XMLRequest,theType)
}

// send the created xml request to map server
function sendMapXML() {

	// ask for the Main map
	//window.onerror=clearError;

	beforeMapRefresh();
	//window.onerror=resetError;
	showRetrieveMap();

	var theText = writeXML();
	if (debugOn==2) alert(msgList[12] + theText);

	sendToServer(imsURL,theText,1);

}

// process the response xml
function processXML(theReplyIn) {

       //if (XMLMode != 40) alert(XMLMode);

	if (doURLencode) {
		theReplyIn = replacePlus(theReplyIn);
		var theReply = unescape(theReplyIn);
	} else {
		var theReply = theReplyIn;
	}
	lastXMLResponse = theReply;
	okToSend = true;
	if (debugOn>2) alert(msgList[13] + theReply);
	var theError = getXMLErrorMessage(theReply);
	//if (theError!="") {
	//	alert(theError);
	//} else {
        //alert(XMLMode);
	switch(XMLMode) {
		case 1:
			//alert ("Received:\n\n" + theReply);
			var theURL = "";
			theURL = getURL(theReply);


			//alert(theURL);
			if (theURL != "") {
				getXYs(theReply);

				document.theImage.src = theURL;

				afterMapRefresh();
				//window.onerror=resetError;
				if (toolMode==3) {
					moveLayer("theMap",hspc,vspc);
					clipLayer2("theMap",0,0,iWidth,iHeight);
					window.setTimeout('showLayer("theMap");',1000);
					if (hasLayer("theMapClicks")) {
						moveLayer("theMapClicks",hspc,vspc);
						clipLayer2("theMapClicks",0,0,iWidth,iHeight);

					}
				}

			}
			else {

				if (debugOn>0) {
					alert(msgList[14] + "\n" + theReply);
				} else {

					alert(msgList[14] + theError);
				}
			}
			if (toolMode==20) {
				updateMeasureBox();
			}
			if (legendVisible) {
				//showLegend();    /************************check
			}
			if (hasOVMap) {
				if (ovIsVisible) {
				// ask for the overview
					if ((!pastStart) || (forceNewOVMap)) {

						theText = writeOVXML();
						//pastStart=true;
						sendToServer(imsOVURL,theText,2);
					} else {
						putExtentOnOVMap();
						hideRetrieveMap();
					}
				} else {
					if ((!pastStart) || (forceNewOVMap)) {
						theText = writeOVXML();
						sendToServer(imsOVURL,theText,2);
					} else {
						hideRetrieveMap();
					}

				}
			} else {
				hideRetrieveMap();
			}


			break;

		case 2:
			// just put up an overview map
			var theURL = "";
			theURL = getURL(theReply);
			if (!pastStart) {
				getOVXYs(theReply);
				pastStart=true;

			}
			//alert(theURL);
			if (theURL != "") {
				ovImageVar.src = theURL;
			}
			else {

				if (debugOn>0) {
					alert(msgList[16] + "\n" + theReply);
				} else {
					alert(msgList[16] + theError);
				}

			}
			if (ovIsVisible) putExtentOnOVMap();
			hideRetrieveMap();

			break;

		case 3:
			//  just get full extent - service info
			//alert("processXML()[3]\n" + theReply);
			if (getLimitExtent) {
				var pos = theReply.indexOf("<PROPERTIES");
				var theXYs =  getEnvelopeXYs(theReply, pos)
				limitLeft = theXYs[0];
				limitBottom = theXYs[1];
				limitRight = theXYs[2];
				limitTop = theXYs[3];
			}

			if (adjustLimit) {
				//adjust limit to proportions of defined image size
				//alert("Limits: " + limitLeft + ", " + limitBottom + ", " + limitRight + ", " + limitTop);
				var imgW2HRatio = iWidth / iHeight;
				var imgH2WRatio = iHeight / iWidth;
				var itWidth = limitRight - limitLeft;
				var itHeight = limitTop - limitBottom;
				var mapRatio = itHeight / itWidth;
				var ix = limitLeft + (itWidth/2);
				var iy = limitBottom + (itHeight/2);
				var iSize = itWidth/2;
				if (iWidth>=iHeight) {
					//alert("Image wider than tall");
					if (imgH2WRatio<mapRatio) {
						//alert("Map ratio greater than Image" );
						iSize = (itHeight * imgW2HRatio) / 2;
						limitRight = ix + iSize;
						limitLeft = ix - iSize;
					} else {
						//alert("Limit wider than tall");
						iSize = (itWidth * imgH2WRatio) / 2;
						limitTop = iy + iSize;
						limitBottom = iy - iSize;
					}
				} else {
					//alert("Image taller than wide");
					if (imgH2WRatio<mapRatio) {
						//alert("Map ratio greater than Image");
						iSize = (itHeight * imgH2WRatio) / 2;
						limitRight = ix + iSize;
						limitLeft = ix - iSize;
					} else {
						//alert("Map ratio smaller than Image");
						iSize = (itWidth * imgH2WRatio) / 2;
						limitTop = iy + iSize;
						limitBottom = iy - iSize;
					}
				}
				//alert("Adjusted Limits: " + limitLeft + ", " + limitBottom + ", " + limitRight + ", " + limitTop);
			}
			imageLimitLeft=limitLeft;
			imageLimitRight=limitRight;
			imageLimitTop=limitTop
			imageLimitBottom=limitBottom;
			fullLeft = limitLeft;
			fullRight = limitRight;
			fullTop = limitTop;
			fullBottom = limitBottom;

			fullWidth = Math.abs(fullRight - fullLeft);
			fullHeight = Math.abs(fullTop - fullBottom);
			fullOVWidth = Math.abs(fullOVRight - fullOVLeft);
			fullOVHeight = Math.abs(fullOVTop - fullOVBottom);
			// get service info - extent, layers
			//alert("processXML()[4]\n" + theReply);
			forceCommaInRequest[activeMapServiceIndex] = false;
			processStartExtent(theReply);

			break;

		case 5:
			// get a list of ImageServices
			//alert("processXML()[5]\n" + theReply);
			processCatalog(theReply);

			break;

		case 6:
			// get select response

			displayAttributeData(theReply);

			var areaCount = justGetFeatureCount(theReply);
			if (areaCount > 0) { getAreaZoom(theReply,areaCount); }

			break;

		case 7:
			// get identify response

			displayAttributeData(theReply);

			var areaCount = justGetFeatureCount(theReply);
			if (areaCount > 0) { getAreaZoom(theReply,areaCount); }

			break;

		case 8:
			// get query response
			//alert("processXML()[8]\nQuery XML Response:\n" + theReply);
			displayAttributeData(theReply);
			var areaCount = justGetFeatureCount(theReply);
			if (areaCount > 0) { getAreaZoom(theReply,areaCount); }
			break;

		case 9:
			// get geocode response
			processGeocode(theReply);

			break;
		case 10:
			// get identifyall response
			doIdentifyAll(theReply);

			break;
		case 11:
			// get buffer response

			//getBufferAttributeData(theReply);
			//displayAttributeData(theReply);

			break;
		/*
		case 12:
			// get proximity response
			processProx(theReply);

			break;

		case 13:
			// get route response
			processRoute(theReply);

			break;
		*/
		case 14:
			// get find response
			//alert(theReply);
			displayAttributeData(theReply);

			break;

		case 15:
			// get hyperlink response
			parseHyperLink(theReply);

			break;

		case 16:
			// get hyperlinkAny response
			parseHyperLinkAny(theReply);

			break;

		case 20:
			// process startup query
			parseStartQuery(theReply);

			break;

		case 25:
			// get geocoding layers
			parseGeocodeLayers(theReply);
			// continue startup
			if ((aimsQueryPresent) && (highlightedOne!="") && (queryZoom)) {
				setStartQuery();
			} else {
				sendMapXML();
			}

			break;

		case 26:
			// get geocoding layers
			if (parseGeocodeParams(theReply,GCLayers[GCActiveLayer])) {
				var theAddressForm = appDir + "addmatch.htm";
				if ((useExternalWindow) || (!useTextFrame)) {
					var Win1 = window.open(theAddressForm,"GeocodeWindow","width=575,height=150,scrollbars=yes,resizable=yes");
				} else {
					parent.TextFrame.document.location= theAddressForm;
				}
			} else {
				if (debugOn>0) {
					alert(msgList[17] + "\n" + theReply);
					if (parent.TextFrame!=null) parent.TextFrame.document.location= appDir + "blank.htm";
				} else {
					alert(msgList[17]);
					if (parent.TextFrame!=null) parent.TextFrame.document.location= appDir + "blank.htm";
				}
			}

			break;

		case 27:
			// get geocoding results
			parseGeocodeResults(theReply);

			break;
		case 40:
			// get list of sample field values
			parseFieldSamples(theReply);
			writeQueryForm();

			break;
		case 55:
			// get layer storedqueries
			//alert("55\n" + theReply);
			parseStoredQueries(theReply);

			break;

		case 70:
			// get layer field for submission to external db
			parseIDFieldData(theReply);

			break;

		case 98:
			// just put up a map with legend
			var theURL = "";
			theURL = getURL(theReply);
			if (theURL != "") {
				document.theImage.src = theURL;
			}
			//else {
			//	alert(theReply + "\nUnable to display Map image");
			//}

			if (legendVisible) {
				showLegend();
				drawLegendOnly=false;
			}
			hideRetrieveMap();

			break;

		case 99:
			// just put up a map
			var theURL = "";
			legendVisible=legendTemp;
			theURL = getURL(theReply);
			if (theURL != "") {
				document.theImage.src = theURL;
			}
			else {
				alert(msgList[14] + theReply);
			}
			hideRetrieveMap();

			break;

		case 101:
			// print - get Map image
			printMapURL = getURL(theReply);
			printLegURL = getLegendURL(theReply);
			if (hasOVMap) {
				getPrintOV();
			} else {
				//writePrintPage();
				getPrintLegend();
			}

			break;

		case 102:
			// print - get OV image
			printOVURL = getURL(theReply);
			legendImage = printLegURL
			getPrintLegend();

			break;

		case 103:
			// print - get Legend image

			hideRetrieveMap();
			writePrintPage();
			break;

		case 900:
			// just make a map
			var theURL = "";
			theURL = getURL(theReply);
			if (theURL != "") {
				getXYs(theReply);
				document.theImage.src = theURL;
			}

			break;


		case 902:
			// just put up an overview map
			var theURL = "";
			theURL = getURL(theReply);
			//alert(theURL);
			if (theURL != "") {
				ovImageVar.src = theURL;
			}
			var tempLeft = fullLeft;
			var tempRight = fullRight;
			var tempTop = fullTop;
			var tempBottom = fullBottom;
			getOVXYs(theReply);
			pastStart = true;
			fullLeft = tempLeft;
			fullRight = tempRight;
			fullTop = tempTop;
			fullBottom = tempBottom;
			var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO renderer="false" extensions="true" ';
			if (aimsLayersPresent) {
				if (LayerID.length>0) {
					theString += 'fields="false" ';
				} else {
					theString += 'fields="true" ';
				}
			} else {
				theString += 'fields="false" ';
			}
			theString += '/>\n</REQUEST>\n</ARCXML>';
			sendToServer(imsURL,theString,3);

			break;

		case 800:
			// get comparable query response
			//alert("processXML()[8]\nQuery XML Response:\n" + theReply);
			displayComparableAttributeData(theReply);

			break;

		case 999:
			printMapURL = getURL(theReply);
			printLegURL = getLegendURL(theReply);
            //alert ("1");
            break;

		default:
			// send any responses to custom requests off to the custom handler
				// XMLMode >= 1000 are reserved for custom requests/responses
			if (XMLMode >= 1000) {
				useCustomFunction(theReply);
			} else {
				alert(theReply + msgList[19]);
			}

		}
	//}


}

// prepare the request in xml format for Main Map
function writeXML() {
	var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_IMAGE>\n<PROPERTIES>\n<ENVELOPE minx="' + forceComma(eLeft) + '" miny="' + forceComma(eBottom) + '" maxx="' + forceComma(eRight) + '" maxy="' + forceComma(eTop) + '" />\n';
	theString += '<IMAGESIZE height="' + iHeight + '" width="' + iWidth + '" />\n';
	var visString = "";

	if (aimsLayersPresent) {
		// tell the server which layers are to be visible
		if (toggleVisible) {
			theString += '<LAYERLIST >\n';
			for (var i=0;i<layerCount;i++) {
				if (LayerVisible[i]==1) {
					theString += '<LAYERDEF id="' + LayerID[i] + '" visible="true" ';


                                        //Glen Hide Labels 4/19/06

					if ((showDVOLabels==false) && (LayerName[i]=="parcel"))
                                           {
					   theString += '>\n';
					   theString += NoParcelLabels();
					   theString += '</LAYERDEF>\n';
                                           }
					else if ((showDVOLabels==false) && (LayerName[i]=="Street Centerlines"))
                                           {
					   theString += '>\n';
					   theString += NoStreetLabels();
					   theString += '</LAYERDEF>\n';
                                           }
                                        else { theString += '/>\n'; }

				}
				else {
					theString += '<LAYERDEF id="' + LayerID[i] + '" visible="false" />\n';
				}

			}
			theString += '</LAYERLIST>\n';
		}
	}

	// map background color
	if (mapTransparent) {
		theString += '<BACKGROUND color="255,255,254" transcolor="255,255,254" />\n';
	} else {
		if (mapBackColor!="") {
			theString += '<BACKGROUND color="' + mapBackColor + '" />\n\n';
		}
	}
	if (aimsLegendPresent) {
		// create a legend image
		if (legendVisible) theString += addLegendToMap();
	}

	theString += '</PROPERTIES>\n';

	// buffer
	if (aimsBufferPresent) {
		if (showBuffer) theString += addBufferToMap();

	}

	// select

	if (aimsSelectPresent) {
		theString += addSelectToMap();
	}

	// any custom stuff to fit here
	if (aimsCustomPresent) theString += addCustomToMap1();


	if (aimsClickPresent){
		// clickpoints
		if (clickCount>0) {
			// draw click points and lines between them on map
			var clickColor = selectColor;
			if (clickType==1) clickColor = clickMarkerColor;
			theString += '<LAYER type="acetate" name="allTheClicks">\n';
			if (clickCount>1) {
				theString += '<OBJECT units="database">\n<LINE coords="' + forceComma(clickPointX[0]) + coordsDelimiter + forceComma(clickPointY[0]);
				for (var i=1;i<clickCount;i++) {
					theString += pairsDelimiter  + forceComma(clickPointX[i]) + coordsDelimiter + forceComma(clickPointY[i]);
				}
				theString += '" >\n';
				theString += '<SIMPLELINESYMBOL type="solid" color="' + clickMarkerColor;
				theString += '" width="3" />\n</LINE>\n</OBJECT>\n';
				theString += '<OBJECT units="database">\n<LINE coords="' + forceComma(clickPointX[0]) + coordsDelimiter + forceComma(clickPointY[0]);
				for (var i=1;i<clickCount;i++) {
					theString += pairsDelimiter  + forceComma(clickPointX[i]) + coordsDelimiter + forceComma(clickPointY[i]);
				}
				theString += '" >\n';
				theString += '<SIMPLELINESYMBOL type="solid" color="255,255,255" width="1" />\n</LINE>\n</OBJECT>\n';
	 		}
			for (var i=0;i<clickCount;i++) {
				theString += '<OBJECT units="database">\n<POINT coords="' + forceComma(clickPointX[i]) + coordsDelimiter + forceComma(clickPointY[i]) + '">\n';
				theString += '<SIMPLEMARKERSYMBOL  type="' + clickMarkerType + '"';
				theString += ' color="' + clickMarkerColor + '" width="' + clickMarkerSize + '" />\n</POINT>\n</OBJECT>\n';
			}
			theString += '</LAYER>\n';
		}
	}

	// route display - requires RouteServer extension
	if (aimsRoutePresent) {
		if (geocodeAppMode=="route") theString += addRouteToMapXML("");
	}

	// geocoding or point with label
	if (showGeocode) {
		// draw the point . . . also used to display any point with a label on map
		theString += '<LAYER type="acetate" name="GeoCode1">\n';
		theString += '<OBJECT units="database">\n<POINT coords="' + forceComma(geocodeX) + coordsDelimiter + forceComma(geocodeY) + '">\n';
		theString += '<SIMPLEMARKERSYMBOL  type="circle"  color="' + geocodePointColor + '" width="' + geocodePointSize +'" overlap="false" />\n</POINT></OBJECT>\n';
		if (geocodeLabel!="") {
			theString += '<OBJECT units="database">\n<TEXT coords="' + forceComma(geocodeX) + coordsDelimiter + forceComma(geocodeY) + '" label="' + geocodeLabel + '">\n';
			theString += '<TEXTMARKERSYMBOL fontcolor="' + geocodePointColor + '" fontsize="12" shadow="64,64,64" glowing="255,255,0" halignment="right" valignment="top" overlap="false" /></TEXT></OBJECT>\n';
		}
		theString += '</LAYER>\n';

	}

	// any custom stuff to fit here
	if (aimsCustomPresent) theString += addCustomToMap2();

	if (drawBottomBar) {
		theString += '<LAYER type="acetate" name="theBottomBar">\n';
		theString += '<OBJECT units="pixel">\n<POLYGON ';
		theString += 'coords="0' + coordsDelimiter + '1' + pairsDelimiter;
		theString += '1' + coordsDelimiter + bottomBarHeight + pairsDelimiter;
		theString += (iWidth-1) + coordsDelimiter + bottomBarHeight + pairsDelimiter;
		theString += (iWidth-1) + coordsDelimiter + '1' + pairsDelimiter;
		theString += '0' + coordsDelimiter + '1">\n';
		theString += '<SIMPLEPOLYGONSYMBOL fillcolor="' + bottomBarColor + '" boundary="true" boundarycolor="' + bottomBarOutline + '" overlap="false" />\n';
		theString += '</POLYGON>\n</OBJECT>\n';
		theString += '</LAYER>\n';
	}

	if (drawCopyright) {
		// draw text on the map
		theString += '<LAYER type="acetate" name="theCopyright">\n';
		theString += '<OBJECT units="pixel">\n<TEXT coords="' + CopyrightCoords + '" label="' + CopyrightText + '">\n';
		theString += '<TEXTMARKERSYMBOL fontstyle="' + CopyrightStyle + '" fontsize="' + CopyrightSize + '" ';
		if (CopyrightFont!="") theString += 'font="' + CopyrightFont + '" ';
		theString += 'fontcolor="' + CopyrightColor + '" antialiasing="True" ';
		//if (CopyrightBackground.toUpperCase()=="TRUE") theString += 'background="' + CopyrightBackground + '" backcolor="' + CopyrightBGColor + '" ';
		if (CopyrightBackground.toUpperCase()=="TRUE") theString += 'blockout="' + CopyrightBGColor + '" ';
		if (CopyrightShadow.toUpperCase()=="TRUE") theString += 'shadow="' + CopyrightShadowColor + '" ';
		theString += 'overlap="false" ';
		if (CopyrightGlow.toUpperCase()=="TRUE") theString += ' glowing="' + CopyrightGlowColor + '" ';
		theString += '/>\n</TEXT>\n</OBJECT>\n';
		theString += '</LAYER>\n';
	}
	if (drawNorthArrow) {
		// draw a north arrow
		theString += '<LAYER type="acetate" name="theNorthArrow">\n';
		theString += '<OBJECT units="pixel">\n<NORTHARROW type="' + NorthArrowType + '" size="' + NorthArrowSize + '" coords="' + NorthArrowCoords + '" shadow="32,32,32" ';
		theString += 'angle="' + NorthArrowAngle + '" antialiasing="True" overlap="False" />\n</OBJECT>\n';
		theString += '</LAYER>\n';
	}
	if (drawScaleBar) {
		// draw a scale bar
		//ScaleBarPrecision = numDecimals;
                MapUnits="FEET"; //Hard Fix - Glen 3/9/06 Something was overriding this and messing up the scalebar to DD
		theString += '<LAYER type="acetate" name="theScaleBar">\n';
		xDistance = eRight - eLeft;
		theString += '<OBJECT units="pixel">\n';
		theString += '<SCALEBAR ';

		if (drawScaleBar2) {
			theString += 'screenlength="' + parseInt(iWidth * (1/10)) + '" coords="' + (iWidth-((parseInt(iWidth * (4/10)))+20)) + coordsDelimiter + '3" ';
		} else {
			theString += 'screenlength="' + parseInt(iWidth * (2/10)) + '" coords="' + parseInt(iWidth * (6/10)) + coordsDelimiter + '3" ';
		}
                //alert(MapUnits);
		//if (MapUnits=="DEGREES") theString += 'mode="geodesic" ';
		if (ScaleBarFont!="") theString += 'font="' + ScaleBarFont + '" ';
		theString += 'fontcolor="' + ScaleBarFontColor + '" fontstyle="' + ScaleBarStyle + '" barcolor="' + ScaleBarColor + '" ';
		if (MapUnits!="DEGREES") {
			theString += 'mapunits="' + MapUnits.toLowerCase() + '" ';
		}
		theString += 'scaleunits="' + ScaleBarUnits.toLowerCase() + '" antialiasing="True" ';
		var sDistance = getScaleBarDistance();
		if (MapUnits=="DEGREES") {
			if (xDistance<1/10){
				theString += 'precision="' + ScaleBarPrecision + '" ';
			}
		} else if (sDistance<5) {
			theString += 'precision="' + ScaleBarPrecision + '" ';
		}
		//theString += 'screenlength="' + parseInt(iWidth * (2/10)) + '" ';
		theString += 'fontsize="' + ScaleBarSize + '" barwidth="' + ScaleBarWidth + '" overlap="False"  ';
		//*/
		theString += '/>\n</OBJECT>\n';
		theString += '</LAYER>\n';

	}
	if (drawScaleBar2) {
		// draw a scale bar
		//ScaleBarPrecision = numDecimals;
		theString += '<LAYER type="acetate" name="theScaleBar2">\n';
		xDistance = eRight - eLeft;
		theString += '<OBJECT units="pixel">\n';
		theString += '<SCALEBAR ';

		if (drawScaleBar) {
			theString += 'screenlength="' + parseInt(iWidth * (1/10)) + '" coords="' + (iWidth-((parseInt(iWidth * (2/10)))+10)) + coordsDelimiter + '3" ';
		} else {
			theString += 'screenlength="' + parseInt(iWidth * (2/10)) + '" coords="' + parseInt(iWidth * (6/10)) + coordsDelimiter + '3" ';
		}
		if (MapUnits=="DEGREES") theString += 'mode="geodesic" ';
		if (ScaleBar2Font!="") theString += 'font="' + ScaleBar2Font + '" ';
		theString += 'fontcolor="' + ScaleBar2FontColor + '" fontstyle="' + ScaleBar2Style + '" barcolor="' + ScaleBar2Color + '" ';
		if (MapUnits!="DEGREES") {
			theString += 'mapunits="' + MapUnits.toLowerCase() + '" ';
		}
		theString += 'scaleunits="' + ScaleBar2Units.toLowerCase() + '" antialiasing="True" ';
		var sDistance = getScaleBarDistance();
		if (MapUnits=="DEGREES") {
			if (xDistance<1/10){
				theString += 'precision="' + ScaleBar2Precision + '" ';
			}
		} else if (sDistance<5) {
			theString += 'precision="' + ScaleBar2Precision + '" ';
		}
		//theString += 'screenlength="' + parseInt(iWidth * (2/10)) + '" ';
		theString += 'fontsize="' + ScaleBar2Size + '" barwidth="' + ScaleBar2Width + '" overlap="False"  ';
		//*/
		theString += '/>\n</OBJECT>\n';
		theString += '</LAYER>\n';

	}

	// any custom stuff to fit here
	if (aimsCustomPresent) theString += addCustomToMap3();

	if (drawModeOnMap) {
		// draw the current mode on the map
		theString += '<LAYER type="acetate" name="theMode">\n';
		theString += '<OBJECT units="pixel">\n<TEXT coords="5' + coordsDelimiter + (iHeight-10) + '" label="' + modeBlurb + '">\n';
		theString += '<TEXTMARKERSYMBOL fontstyle="BOLD" fontsize="12" font="ARIAL" fontcolor="' + modeMapColor + '" ';
		theString += 'threed="TRUE" glowing="' + modeMapGlow + '" overlap="false" />\n</TEXT>\n</OBJECT>';
		theString += '</LAYER>\n';

	}

	// any custom stuff to be drawn on top of everything
	if (aimsCustomPresent) theString += addCustomToMap4();

	theString += '</GET_IMAGE>\n</REQUEST>\n</ARCXML>';
    // alert to show arcXmlString
    //alert(theString);
	return theString;
}

// prepare the request in xml format for overview map
function writeOVXML() {
	var extentRatio = xDistance/fullWidth;
	var midX = eLeft + xHalf;
	var midY = eBottom + yHalf;
	var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_IMAGE><PROPERTIES>\n';
	theString += '<ENVELOPE minx="' + forceOVComma(fullOVLeft) + '" miny="' + forceOVComma(fullOVBottom) + '" maxx="' + forceOVComma(fullOVRight) + '" maxy="' + forceOVComma(fullOVTop) + '" />\n';
	theString += '<IMAGESIZE height="' + i2Height + '" width="' + i2Width + '" />\n';
	if ((toggleOVVisible) && (imsURL==imsOVURL)) {
		theString += '<LAYERLIST >\n';
		for (var i=0;i<layerCount;i++) {
			if (LayerVisible[i]==1) {
				visString = "true";
			}
			else {
				visString = "false";
			}
			theString += '<LAYERDEF name="' + LayerName[i] + '" visible="' + visString + '" />\n';

		}
		theString += '</LAYERLIST>\n';
	}
	if (mapBackColor!="") {
		theString += '<BACKGROUND color="' + mapBackColor + '" />\n';
	}

	theString += '</PROPERTIES>\n';
	if (drawOVExtentBox) {
		theString += '<LAYER type="acetate" name="ACETATE">\n';
		if (extentRatio<(1/100)) {
			theString += '<OBJECT units="database">\n<POINT coord="' + forceOVComma(midX) + coordsDelimiter + forceOVComma(midY) + '">\n';
			theString += '<SIMPLEMARKERSYMBOL  type="Square" color="' + ovBoxColor + '" width="4" />\n</POINT></OBJECT>\n';
		} else {
			theString += '<OBJECT units="database">\n<LINE coords="' + forceOVComma(eLeft) + coordsDelimiter + forceOVComma(eBottom) + pairsDelimiter + forceOVComma(eLeft) + coordsDelimiter + forceOVComma(eTop) + pairsDelimiter;
			theString += forceOVComma(eRight) + coordsDelimiter + forceOVComma(eTop) + pairsDelimiter + forceOVComma(eRight) + coordsDelimiter + forceOVComma(eBottom) + pairsDelimiter + forceOVComma(eLeft) + coordsDelimiter + forceOVComma(eBottom) + '">\n';
			theString += '<SIMPLELINESYMBOL type="solid" color="' + ovBoxColor + '" width="3" />\n</LINE></OBJECT>\n';
		}
		theString += '</LAYER>\n';
	}
	theString += '</GET_IMAGE>\n</REQUEST>\n</ARCXML>';
	//alert(theString);
	return theString;
}

// get the map extents from xml reply
function getXYs(theString) {
	var tempStr = "";
	var smallStr = "";
	var startpos = 0;
	var endpos = 0;
	var theReply = theString
	var theXYs =  getEnvelopeXYs(theString, 0)
	eLeft = theXYs[0];
	eBottom = theXYs[1];
	eRight = theXYs[2];
	eTop = theXYs[3];
	xDistance = Math.abs(eRight-eLeft);
	yDistance = Math.abs(eTop-eBottom);
	xHalf = xDistance / 2;
	yHalf = yDistance / 2;
	panX = xDistance * panFactor;
	panY = yDistance * panFactor;
	var sFactor = xDistance / iWidth
	if (aimsLayersPresent) {
		if (sFactor!=mapScaleFactor) {
			mapScaleFactor = sFactor;
			//if ((hasTOC) && (!legendVisible)) parent.TOCFrame.document.location = appDir + "toc.htm";
			if (aimsLayersPresent) {
				if (LayerListOpen) writeLayerListForm();
			}

		}
	}
}


// get the map extents from xml reply
function getOVXYs(theString) {
	var tempStr = "";
	var smallStr = "";
	var startpos = 0;
	var endpos = 0;
	var tempComma = forceCommaInRequest[activeMapServiceIndex];
	var theXYs =  getEnvelopeXYs(theString, 0)
	forceCommaInOV = forceCommaInRequest[activeMapServiceIndex];
	forceCommaInRequest[activeMapServiceIndex] = tempComma;
	fullOVLeft = theXYs[0];
	fullOVBottom = theXYs[1];
	fullOVRight = theXYs[2];
	fullOVTop = theXYs[3];
	fullOVWidth = Math.abs(fullOVRight - fullOVLeft);
	fullOVHeight = Math.abs(fullOVTop - fullOVBottom);
	//alert("Left:" + fullLeft + "\nTop:" + fullTop + "\nRight:" + fullRight + "\nBottom:" + fullBottom);
}

// get URL
function getURL(theReply) {
	var theURL = "";
	var startpos = 0;
	var endpos = 0;

	var pos = theReply.indexOf("OUTPUT");
	if (pos != -1) {
		theURL = getInsideString(theReply,'url="',dQuote,pos,0,false);
	}
	legendImage = getLegendURL(theReply);
	if (theURL.substr(0,1) == " ") {
		NewUrl = theURL.replace (" ", "");
		return NewUrl;
	} else {
		return theURL;
	}

	//return theURL;

}


// getLegendURL
function getLegendURL(theReply) {
	var theURL = "";
	var startpos = 0;
	var endpos = 0;
	var pos = theReply.indexOf("LEGEND");
	//alert(pos);
	if (pos != -1) {
		theURL = getInsideString(theReply,'url="',dQuote,pos,0,false);
	}
	//alert("Legend:" + theURL);
	return theURL;

}

// just make a map with URL and  extent
function justGetMap(theURL, extentLeft, extentTop, extentRight, extentBottom, getOVMap) {
	var mode = 900;
	if (getOVMap) mode = 1;
	if (extentLeft!="") eLeft = extentLeft;
	if (extentTop!="") eTop = extentTop
	if (extentRight!="") eRight = extentRight;
	if (extentBottom!="") eBottom = extentBottom;
	var theText = writeXML();
	if (debugOn==2) alert(msgList[12] + theText);
	sendToServer(theURL,theText,mode);

}

// get min and max x,y's from xml stream . . . return an array with values
function getEnvelopeXYs(theString, startpos) {
	var theEnvelope = new Array();
	//forceCommaInRequest[activeMapServiceIndex] = false;
	theString = theString.toUpperCase();
	var tempString = "";
	var pos = theString.indexOf("ENVELOPE",startpos);
	if (pos!=-1) {
		pos = pos + 8;
		startpos = theString.indexOf("MINX=",pos);
		startpos += 6;
		var endpos = theString.indexOf(dQuote,startpos);
		tempString = theString.substring(startpos,endpos);
		theEnvelope[0] = parseFloat(setDecimalString(tempString));
		startpos = theString.indexOf("MINY=",pos);
		startpos += 6;
		endpos = theString.indexOf(dQuote,startpos);
		tempString = theString.substring(startpos,endpos);
		theEnvelope[1] = parseFloat(setDecimalString(tempString));
		startpos = theString.indexOf("MAXX=",pos);
		startpos += 6;
		endpos = theString.indexOf(dQuote,startpos);
		tempString = theString.substring(startpos,endpos);
		theEnvelope[2] = parseFloat(setDecimalString(tempString));
		startpos = theString.indexOf("MAXY=",pos);
		startpos += 6;
		endpos = theString.indexOf(dQuote,startpos);
		tempString = theString.substring(startpos,endpos);
		theEnvelope[3] = parseFloat(setDecimalString(tempString));
		xmlEndPos = endpos;
	}
	return theEnvelope;

}

// set number string to have decimal character to match browser language type - point or comma
function setDecimalString(numberString) {
	if (numberString.indexOf(",")!=-1) forceCommaInRequest[activeMapServiceIndex] = true;
	if (decimalChar==".") {
		numberString = numberString.replace(/,/g, ".");
	} else {
		numberString = numberString.replace(/./g, ",");
	}
	return numberString;
}

function forceComma(theNumber) {
	var comma = ",";
	var dot = ".";
	var charOut = comma;
	var charIn = dot;
	var numberString = new String(theNumber);
	if (forceCommaInRequest[activeMapServiceIndex]) {
		charOut = dot;
		charIn = comma;
	}
	var pos = numberString.indexOf(charOut);
	if (pos!=-1) {
		var begin = numberString.substring(0,pos);
		var ending = numberString.substring(pos+1, numberString.length);
		numberString = begin + charIn + ending;
	}
	return numberString;
}
function forceOVComma(theNumber) {
	var comma = ",";
	var dot = ".";
	var charOut = comma;
	var charIn = dot;
	var numberString = new String(theNumber);
	if (forceCommaInOV) {
		charOut = dot;
		charIn = comma;
	}
	var pos = numberString.indexOf(charOut);
	if (pos!=-1) {
		var begin = numberString.substring(0,pos);
		var ending = numberString.substring(pos+1, numberString.length);
		numberString = begin + charIn + ending;
	}
	return numberString;
}

// check if there is an error message in the response
function getXMLErrorMessage(theString) {
	var pos1 = 0;
	var pos2 = 0;
	var pos3 = 0;
	var theError = "";
	pos3 = theString.indexOf("<ERROR");
	if (pos3!=-1) {
		pos1 = theString.indexOf(">",pos3);
		pos1 += 1;
		pos2 = theString.indexOf("</ERROR");
		theError = theString.substring(pos1,pos2)
	}
	return theError;

}

// check if theURL has different host from theHost
function isNotSameHostInURL(theURL, theHost) {
	var startpos = theURL.indexOf("//");
	if (startpos==-1) {
		startpos = 0;
	} else {
		startpos = startpos + 2;
	}
	var endpos = theURL.indexOf("/",startpos);
	if (endpos==-1) endpos = theURL.length;
	var thisHost = theURL.substring(startpos,endpos);
	if (thisHost==theHost) {
		return false;
	} else {
		return true;
	}
}


