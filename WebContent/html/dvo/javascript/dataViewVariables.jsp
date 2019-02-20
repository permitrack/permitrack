<%@ page import="com.sehinc.dataview.DataViewConstants"%>
<jsp:useBean id="dataViewClientInformation"  scope="session" type="com.sehinc.common.db.client.DvClientInformation"/>
var theClientName = "<jsp:getProperty  name="dataViewClientInformation" property="clientFullName"/>";
var theClientPath = "dataView";

var myLastParcelSelected = "";
var theReportTitle = "<jsp:getProperty  name="dataViewClientInformation" property="clientFullName"/> Parcel Report";
var theDisclaimer = "This drawing is neither a legally recorded map nor a survey and is not intended to be used as one.  It is to be used for reference purposes only.  Short Elliott Hendrickson, Inc. is not responsible for any inaccuracies herein contained.";
var theLegend = "legend.jpg"
var logoURL = "/sehsvc/dvodownload?layer=logo&id=logo.gif";

var theSelectionMode = 2;
var showSelectedAttributesImmediately = true;

var showDVOLabels = true;

// Required Fields
var theStreetNameField = "SEH_STR";
var theStreetNumField = "SEH_HSE";
var theOwnerName1Field = "SEH_OWNA1";
var theOwnerName2Field = "SEH_OWNA2"; 
var thePinField = "PIN";
var thePAddressField = "SEH_OCCA3";
var thePAddressField2 = "SEH_OCCA4";
var theOAddressField = "SEH_OWNA3";
var theOAddressField2 = "SEH_OWNA4";

var theParcelLayerIndex=2;
var theParcelLayerName="parcel";

// Parcel Arrays
var theParcelFields = new Array(30);
var theParcelAlias = new Array(30);
var theParcelFormat = new Array(30);

// Parcel variables
var thePIN = "";
var theOwnerAdd = "";
var theOwnerAdd2 = "";
var theOwnerAdd3 = "";

var thePropertyAddress = "";
var thePropertyAddress2 = "";

// mailing labels
var displayOccupantLabels = true;
var displayOwnerLabels = true;

// Load Parcel Fields

function LoadParcelFields() 
{
  theParcelFields[0] = "PIN";
  theParcelAlias[0] = "Pin:";
  theParcelFormat[0] = "1";
  theParcelFields[1] = "SEH_OWNA1";
  theParcelAlias[1] = "Owner Name:";
  theParcelFormat[1] = "3b";
  theParcelFields[2] = "SEH_OWNA2";
  theParcelAlias[2] = "";
  theParcelFormat[2] = "3e";
  theParcelFields[3] = "SEH_OCCA3";
  theParcelAlias[3] = "Street Address:";
  theParcelFormat[3] = "3b";
  theParcelFields[4] = "SEH_OCCA4";
  theParcelAlias[4] = "";
  theParcelFormat[4] = "3e";
  theParcelFields[5] = "SEH_OWNA3";
  theParcelAlias[5] = "Owner Address:";
  theParcelFormat[5] = "3b";
  theParcelFields[6] = "SEH_OWNA4";
  theParcelAlias[6] = "";
  theParcelFormat[6] = "3e";
  theParcelFields[7] = "SEH_ACRES";
  theParcelAlias[7] = "Acres:";
  theParcelFormat[7] = "1";
  theParcelFields[8] = "SEH_LEGAL";
  theParcelAlias[8] = "Legal Description:";
  theParcelFormat[8] = "3b";
  theParcelFields[9] = "SEH_LEGAL2";
  theParcelAlias[9] = "";
  theParcelFormat[9] = "3m";
  theParcelFields[10] = "SEH_LEGAL3";
  theParcelAlias[10] = "";
  theParcelFormat[10] = "3m";
  theParcelFields[11] = "SEH_LEGAL4";
  theParcelAlias[11] = "";
  theParcelFormat[11] = "3m";
  theParcelFields[12] = "Desc5";
  theParcelAlias[12] = "";
  theParcelFormat[12] = "3m";
  theParcelFields[13] = "Desc6";
  theParcelAlias[13] = "";
  theParcelFormat[13] = "3m";
  theParcelFields[14] = "Desc7";
  theParcelAlias[14] = "";
  theParcelFormat[14] = "3m";
  theParcelFields[15] = "Desc8";
  theParcelAlias[15] = "";
  theParcelFormat[15] = "3m";
  theParcelFields[16] = "Desc9";
  theParcelAlias[16] = "";
  theParcelFormat[16] = "3m";
  theParcelFields[17] = "Desc10";
  theParcelAlias[17] = "";
  theParcelFormat[17] = "3m";
  theParcelFields[18] = "Desc11";
  theParcelAlias[18] = "";
  theParcelFormat[18] = "3m";
  theParcelFields[19] = "Desc12";
  theParcelAlias[19] = "";
  theParcelFormat[19] = "3m";
  theParcelFields[20] = "Desc13";
  theParcelAlias[20] = "";
  theParcelFormat[20] = "3m";
  theParcelFields[21] = "Desc14";
  theParcelAlias[21] = "";
  theParcelFormat[21] = "3m";
  theParcelFields[22] = "Desc15";
  theParcelAlias[22] = "";
  theParcelFormat[22] = "3m";
  theParcelFields[23] = "Desc16";
  theParcelAlias[23] = "";
  theParcelFormat[23] = "3m";
  theParcelFields[24] = "Desc17";
  theParcelAlias[24] = "";
  theParcelFormat[24] = "3m";
  theParcelFields[25] = "Desc18";
  theParcelAlias[25] = "";
  theParcelFormat[25] = "3m";
  theParcelFields[26] = "Desc19";
  theParcelAlias[26] = "";
  theParcelFormat[26] = "3m";
  theParcelFields[27] = "Desc20";
  theParcelAlias[27] = "";
  theParcelFormat[27] = "3m";
  theParcelFields[28] = "Desc21";
  theParcelAlias[28] = "";
  theParcelFormat[28] = "3m";
  theParcelFields[29] = "Desc22";
  theParcelAlias[29] = "";
  theParcelFormat[29] = "3m";
  theParcelFields[30] = "Desc23";
  theParcelAlias[30] = "";
  theParcelFormat[30] = "3e";

  theParcelFields[31] = "SEH_PLAT";
  theParcelAlias[31] = "Plat Name:";
  theParcelFormat[31] = "2";
  theParcelFields[32] = "Lot";
  theParcelAlias[32] = "Lot:";
  theParcelFormat[32] = "1";
  theParcelFields[33] = "Block";
  theParcelAlias[33] = "Block:";
  theParcelFormat[33] = "1";

}

