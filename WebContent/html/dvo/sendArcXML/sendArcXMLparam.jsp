<%@ page import="com.sehinc.dataview.DataViewConstants"%>
/*****************************************************
 *	 sendArcXMLparam.js
 *
 *	parameter file for sendArcXML utility
 *
 *****************************************************/

<jsp:useBean id="dataViewClientInformation"  scope="session" type="com.sehinc.common.db.client.DvClientInformation"/>
// default MapService name
var serviceVar = "<jsp:getProperty  name="dataViewClientInformation" property="clientName"/>";

// default extent used in Envelopes
var minx="<jsp:getProperty  name="dataViewClientInformation" property="startLeft"/>";
var miny="<jsp:getProperty  name="dataViewClientInformation" property="startBottom"/>";
var maxx="<jsp:getProperty  name="dataViewClientInformation" property="startRight"/>";
var maxy="<jsp:getProperty  name="dataViewClientInformation" property="startTop"/>";

//initial map extent

var startLeft = <jsp:getProperty  name="dataViewClientInformation" property="startLeft"/>;

var startRight = <jsp:getProperty  name="dataViewClientInformation" property="startRight"/>;

var startTop = <jsp:getProperty  name="dataViewClientInformation" property="startTop"/>;

var startBottom =<jsp:getProperty  name="dataViewClientInformation" property="startBottom"/>;
//maximum map extent

var limitLeft = <jsp:getProperty  name="dataViewClientInformation" property="limitLeft"/>;

var limitRight = <jsp:getProperty  name="dataViewClientInformation" property="limitRight"/>;

var limitTop = <jsp:getProperty  name="dataViewClientInformation" property="limitTop"/>;

var limitBottom = <jsp:getProperty  name="dataViewClientInformation" property="limitBottom"/>;

// default layer id used in queries
var layerId = "parcels";
// default where used in queries
var defaultWhere = "Address like '5th'";
// default subfields value in queries
var subfields = "#ALL#";

// escape the response?
var doURLencode = false;


