<%@ page import="com.sehinc.dataview.DataViewConstants"%>
// set up basic globals
    // machine name
var hostName = document.location.host;
    // url blurb to Application Server
var esrimapBlurb = "<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=";
var serviceRequest = "";
var theCodePage = "";
// the base servlet connector URL
var connectorURL = document.location.protocol + "//" + hostName + "<%= DataViewConstants.SERVLET_LOCATION%>?ServiceName=redirect";
// Have ArcXML responses URL encoded? Will not work with 2-byte characters
var doURLencode = true;
// returned HTML page charset - not the encoding of the XML request
var charSet = "ISO-8859-1";
// use 3.0 request for catalog?
var catalog30 = false;
// full request URL
var requestURL = "";
// was request redirected?
var wasRedirected = false;
// image width and height from request
var iWidth = "500";
var iHeight = "350";
var lWidth = "170";
var lHeight = "300";
var isLegendRequest = false;



// send request
function sendXML() {
	// get set the form values from this page
	theCodePage = "";
	wasRedirected = false;
	var theForm = document.forms[0];
		// the MapService
	var theService = theForm.Service.value;
	if (theService!="catalog") serviceVar = theService;
	var theHost = theForm.Host.value;
	var cVersion = "&ClientVersion=4.0";
	if ((theService=="catalog") && (catalog30)) cVersion = "";
	var cp = theForm.codePage.value;
	if ((cp!="") && (cp!="")) theCodePage = 'encoding="' + cp + '" ';
	var theURL = document.location.protocol + "//" + theHost + esrimapBlurb + theService + cVersion;
		// send to QueryServer?
	if (theForm.useQuery.checked) theURL = theURL + "&CustomService=Query";
	if (theForm.useGeocode.checked) theURL = theURL + "&CustomService=Geocode";
	if (theForm.useExtract.checked) theURL = theURL + "&CustomService=Extract";
	//doURLencode = theForm.useURLencode.checked;
		// the request
	var theRequest = theForm.request.value;
	// get the Post Form and set values, then submit to server
	var thePostForm = parent.PostFrame.document.forms[0];
			// set parameters for the Servlet Connector
			//alert(theURL);
	requestURL = theURL;
	if (thePostForm.RedirectURL!=null) {
		if (isNotSameHostInURL(theURL, hostName)) {
			//alert("Remote Host:\n" + theURL);
			requestURL = connectorURL;
			thePostForm.RedirectURL.value = theURL;
			wasRedirected = true;
		} else {
			thePostForm.RedirectURL.value = "";

		}

	} else {
		alert("No redirection available.");
	}
	if (thePostForm.FormCharset != null) thePostForm.FormCharset.value = charSet;
	if (doURLencode) {
		thePostForm.action=requestURL + "&Form=True&Encode=True";
	} else {
		thePostForm.action=requestURL + "&Form=True&Encode=False";
	}
	var thexmlHeader = '<?xml version="1.0" ' + theCodePage + '?>\n';
	if (theService=="redirect") thexmlHeader = "";
	thePostForm.ArcXMLRequest.value=thexmlHeader + theRequest;
	requestURL = thePostForm.action;
	// submit to Application Server
	writeURLFrame();
	thePostForm.submit();
	getValuesFromRequest();

}

// handle response
function processXML(inString) {
	if (doURLencode) {
		// swap out pluses for spaces and unescape the string.
		inString = replacePlus(inString);
		var theReply = unescape(inString);
	} else {
		var theReply=inString;
	}
		// get the form on this page and update response box
	var theForm = document.forms[0];
	//alert(theReply);
	theForm.response.value = theReply;
	var f = parent.PostFrame.document.forms[0];
	if (f.RedirectURL==null) {
		parent.PostFrame.document.location= appDir + "jsForm.htm";
	}
	f=null;
}

// clear the response box
function clearResponse() {
		// get the form on this page and clear response box
	var theForm = document.forms[0];
	theForm.response.value = "";

}

// replace +  in string with space to allow parsing of unescaped xml response
// replace +  in string with space to allow parsing of unescaped xml response
function replacePlus(inText) {
     var re = /\+/g;
      inText = inText.replace(re," ");
     return inText;
}

// sample Catalog request
function getCatalog () {
	theForm = document.forms[0];
	theForm.Service.value = "catalog";
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = "<GETCLIENTSERVICES/>";
}

// sample get Redirectable Hosts request
function getHosts () {
	theForm = document.forms[0];
	theForm.Service.value = "redirect";
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = "RedirectableHosts";
}
// sample Image request template
function getImage() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = '<ARCXML version="1.1">\n<REQUEST>\n<GET_IMAGE>\n<PROPERTIES>\n<ENVELOPE minx="' + minx + '" miny="' + miny + '" maxx="' + maxx + '" maxy="' + maxy + '" />\n<IMAGESIZE width="' + iWidth + '" height="' + iHeight + '" />\n</PROPERTIES>\n</GET_IMAGE>\n</REQUEST>\n</ARCXML>';
}

// sample Legend Image request template
function getLegend() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	var legString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_IMAGE>\n<PROPERTIES>\n';
	legString += '<ENVELOPE minx="' + minx + '" miny="' + miny + '" maxx="' + maxx + '" maxy="' + maxy + '" />\n<IMAGESIZE width="' + iWidth + '" height="' + iHeight + '" />\n';
	legString += '<LEGEND title="Legend" font="Arial" width="' + lWidth + '" height="' + lHeight + '" ';
	legString += 'autoextend="true" backgroundcolor="255,255,255" ';
	legString += 'titlefontsize="12" swatchwidth="14" swatchheight="18" ';
	legString += 'antialiasing="true" ';
	legString += 'cellspacing="2" columns="1" reverseorder="false" ';
	legString += 'cansplit="false" splittext="(cont)" ';
	legString += 'layerfontsize="10" valuefontsize="8" ';
	legString += 'transcolor="254,254,254" ';
	legString += '/>\n';
	legString += '<DRAW map="false" />\n';
	legString += '</PROPERTIES>\n</GET_IMAGE>\n</REQUEST>\n</ARCXML>';
	theForm.request.value = legString;
}

// sample query request template
function getQuery() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = true;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES featurelimit="25" beginrecord="0" outputmode="xml" geometry="false" envelope="true" >\n<LAYER id="' + layerId + '" />\n<SPATIALQUERY subfields="' + subfields + '" where="' + defaultWhere + '" >\n</SPATIALQUERY>\n</GET_FEATURES>\n</REQUEST>\n</ARCXML>';
}

// sample Extract request template
function getExtract() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = true;
	theForm.request.value = '<ARCXML version="1.1">\n<REQUEST>\n<GET_EXTRACT>\n<PROPERTIES>\n<ENVELOPE minx="' + minx + '" miny="' + miny + '" maxx="' + maxx + '" maxy="' + maxy + '" />\n<IMAGESIZE width="' + iWidth + '" height="' + iHeight + '" />\n</PROPERTIES>\n</GET_EXTRACT>\n</REQUEST>\n</ARCXML>';
}

// sample Geocode request template
function getGeocode() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = true;
	theForm.useExtract.checked = false;
	theForm.request.value = writeGeocodeXML();
}

// write out the geocode XML request
function writeGeocodeXML() {
	var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_GEOCODE maxcandidates="10" minscore="35">\n<LAYER id="' + layerId + '" /><ADDRESS>\n';
	theString += '<!-- Style: USAddress - other styles require other GCTAGS -->\n';
	theString += '<GCTAG id="STREET" value="101 Main St"/>\n';
	theString += '<GCTAG id="CROSSSTREET" value=""/>\n';
	theString += '</ADDRESS>\n</GET_GEOCODE>\n</REQUEST>\n</ARCXML>\n';
	//alert(theString);
	return theString;

}
// sample ServiceInfo request
function getServiceInfo() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO renderer="false" extensions="false" fields="false" envelope="false" />\n</REQUEST>\n</ARCXML>';

}

// sample ArcMap ServiceInfo request
function getArcMapServiceInfo() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = '<ARCMAPREQUEST>\n<GET_SERVICE_INFO />\n</ARCMAPREQUEST>';

}


// sample ArcMap Image request template
function getArcMapImage() {
	theForm = document.forms[0];
	theForm.Service.value = serviceVar;
	theForm.useQuery.checked = false;
	theForm.useGeocode.checked = false;
	theForm.useExtract.checked = false;
	theForm.request.value = '<ARCMAPREQUEST>\n<GET_MAP dataframe="DataFrameName" >\n<MAPSIZE width="' + iWidth + '" height="' + iHeight + '" />\n<ENVELOPE minx="' + minx + '" miny="' + miny + '" maxx="' + maxx + '" maxy="' + maxy + '" />\n</GET_MAP>\n</ARCMAPREQUEST>';
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
	//alert(thisHost);
	if (thisHost==theHost) {
		return false;
	} else {
		return true;
	}
}

function getPath(theFullPath) {
	var theSlash = theFullPath.lastIndexOf("/");
	var theDir = theFullPath.substring(0,theSlash);
	if (theDir==null) theDir="";
	theDir = theDir + "/";
	return theDir;

}

// show the URL in response, if any
function showURL() {
	var theForm = document.forms[0];
	var theResponse = theForm.response.value;
	var theURL = "";
	//if (theResponse.length<=1) {
	//	alert("No URL to display");
	//} else {
		var startpos = theResponse.indexOf("url=");
		if (startpos!=-1) {
			startpos = startpos + 5;
			endpos = theResponse.indexOf('"',startpos);
			theURL = theResponse.substring(startpos,endpos);
		}
	//}
	if (theURL!="") {
		var theWin = open("","");
		theWin.document.writeln('<html>');
		theWin.document.writeln('<body>');
		theWin.document.writeln('<div align="center">');
		theWin.document.writeln('<img src="' + theURL + '">');
		theWin.document.writeln('</div>');
		theWin.document.writeln('</body>');
		theWin.document.writeln('</html>');
	} else {
		alert("No URL to display");
	}

}

function writeURLFrame() {
	var t = parent.URLFrame;
	var thePostForm = parent.PostFrame.document.forms[0];
	t.document.open();
	t.document.writeln('<HTML><BODY BGCOLOR="#000040" TEXT="White" BGPROPERTIES="FIXED" BACKGROUND="images/Background.png" TOPMARGIN=0><DIV ALIGN="center"><FONT FACE="Arial,serif" SIZE="-2">');
	var replyHeader = requestURL;
	if (wasRedirected) {
		replyHeader = thePostForm.RedirectURL.value + " redirected through " + connectorURL;
	}
	t.document.writeln(replyHeader);
	t.document.writeln('</FONT></DIV></BODY></HTML>');
}

function getValuesFromRequest() {
	var theForm = document.forms[0];
	var theRequest = theForm.request.value;
	var dQuote = '"';
	var startpos = 0;
	var endpos = 0;
	var pos = 0;
	startpos = theRequest.indexOf(" id=");
	if (startpos!=-1) {
		startpos += 5;
		endpos = theRequest.indexOf(dQuote,startpos);
		layerId = theRequest.substring(startpos,endpos);
	}
	startpos = theRequest.indexOf(" minx=");
	if (startpos!=-1) {
		startpos += 7;
		endpos = theRequest.indexOf(dQuote,startpos);
		minx = theRequest.substring(startpos,endpos);
	}
	startpos = theRequest.indexOf(" miny=");
	if (startpos!=-1) {
		startpos += 7;
		endpos = theRequest.indexOf(dQuote,startpos);
		miny = theRequest.substring(startpos,endpos);
	}
	startpos = theRequest.indexOf(" maxx=");
	if (startpos!=-1) {
		startpos += 7;
		endpos = theRequest.indexOf(dQuote,startpos);
		maxx = theRequest.substring(startpos,endpos);
	}
	startpos = theRequest.indexOf(" maxy=");
	if (startpos!=-1) {
		startpos += 7;
		endpos = theRequest.indexOf(dQuote,startpos);
		maxy = theRequest.substring(startpos,endpos);
	}
	startpos = theRequest.indexOf(" where=");
	if (startpos!=-1) {
		startpos += 8;
		endpos = theRequest.indexOf(dQuote,startpos);
		defaultWhere = theRequest.substring(startpos,endpos);
	}
	startpos = theRequest.indexOf(" subfields=");
	if (startpos!=-1) {
		startpos += 11;
		endpos = theRequest.indexOf(dQuote,startpos);
		subfields = theRequest.substring(startpos,endpos);
	}
	pos = theRequest.indexOf("<IMAGESIZE ");
	if (pos!=-1) {
		startpos = theRequest.indexOf(" width=",pos);
		if (startpos!=-1) {
			startpos += 8;
			endpos = theRequest.indexOf(dQuote,startpos);
			iWidth = theRequest.substring(startpos,endpos);
		}
		startpos = theRequest.indexOf(" height=",pos);
		if (startpos!=-1) {
			startpos += 9;
			endpos = theRequest.indexOf(dQuote,startpos);
			iHeight = theRequest.substring(startpos,endpos);
		}
	}
	pos = theRequest.indexOf("<LEGEND ");
	if (pos!=-1) {
		startpos = theRequest.indexOf(" width=",pos);
		if (startpos!=-1) {
			startpos += 8;
			endpos = theRequest.indexOf(dQuote,startpos);
			lWidth = theRequest.substring(startpos,endpos);
		}
		startpos = theRequest.indexOf(" height=",pos);
		if (startpos!=-1) {
			startpos += 9;
			endpos = theRequest.indexOf(dQuote,startpos);
			lHeight = theRequest.substring(startpos,endpos);
		}
	}

}

var appDir = getPath(document.location.pathname);

