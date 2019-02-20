var theClientName = "Chetek,Wisconsin";
var theClientPath = "Website/DataViewOnline";
var myLastParcelSelected = "";
var theReportTitle = "Chetek Parcel Report";
var theDisclaimer = "This drawing is neither a legally recorded map nor a survey and is not intended to be used as one.  It is to be used for reference purposes only.  Short Elliott Hendrickson, Inc. is not responsible for any inaccuracies herein contained.";
var theLegend = "legend.jpg"
// Required Fields
var theStreetNameField = "Address";
var theStreetNumField = "Hse";
var theOwnerName1Field = "Name";
var theOwnerName2Field = "co_owner";
var thePinField = "Parcelno";
var thePAddressField = "Address";
var thePAddressField2 = "";
var theOAddressField = "Address";
var theOAddressField2 = "";
var theParcelLayerIndex = 2;
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
// Load Parcel Fields
function LoadParcelFields()
{
    theParcelFields[0] =
    "Parcelno";
    theParcelAlias[0] =
    "Pin:";
    theParcelFormat[0] =
    "1";
    theParcelFields[1] =
    "Name";
    theParcelAlias[1] =
    "Owner Name:";
    theParcelFormat[1] =
    "3b";
    theParcelFields[2] =
    "Co_owner";
    theParcelAlias[2] =
    "";
    theParcelFormat[2] =
    "3e";
    theParcelFields[3] =
    "Address";
    theParcelAlias[3] =
    "Street Address:";
    theParcelFormat[3] =
    "3b";
    theParcelFields[4] =
    "Address2";
    theParcelAlias[4] =
    "";
    theParcelFormat[4] =
    "3e";
    theParcelFields[5] =
    "Address";
    theParcelAlias[5] =
    "Owner Address:";
    theParcelFormat[5] =
    "3b";
    theParcelFields[6] =
    "Address2";
    theParcelAlias[6] =
    "";
    theParcelFormat[6] =
    "3e";
    theParcelFields[7] =
    "Acres";
    theParcelAlias[7] =
    "Acres:";
    theParcelFormat[7] =
    "1";
    theParcelFields[8] =
    "Desc1";
    theParcelAlias[8] =
    "Legal Description:";
    theParcelFormat[8] =
    "3b";
    theParcelFields[9] =
    "Desc2";
    theParcelAlias[9] =
    "";
    theParcelFormat[9] =
    "3m";
    theParcelFields[10] =
    "Desc3";
    theParcelAlias[10] =
    "";
    theParcelFormat[10] =
    "3m";
    theParcelFields[11] =
    "Desc4";
    theParcelAlias[11] =
    "";
    theParcelFormat[11] =
    "3m";
    theParcelFields[12] =
    "Desc5";
    theParcelAlias[12] =
    "";
    theParcelFormat[12] =
    "3m";
    theParcelFields[13] =
    "Desc6";
    theParcelAlias[13] =
    "";
    theParcelFormat[13] =
    "3m";
    theParcelFields[14] =
    "Desc7";
    theParcelAlias[14] =
    "";
    theParcelFormat[14] =
    "3m";
    theParcelFields[15] =
    "Desc8";
    theParcelAlias[15] =
    "";
    theParcelFormat[15] =
    "3m";
    theParcelFields[16] =
    "Desc9";
    theParcelAlias[16] =
    "";
    theParcelFormat[16] =
    "3m";
    theParcelFields[17] =
    "Desc10";
    theParcelAlias[17] =
    "";
    theParcelFormat[17] =
    "3m";
    theParcelFields[18] =
    "Desc11";
    theParcelAlias[18] =
    "";
    theParcelFormat[18] =
    "3m";
    theParcelFields[19] =
    "Desc12";
    theParcelAlias[19] =
    "";
    theParcelFormat[19] =
    "3m";
    theParcelFields[20] =
    "Desc13";
    theParcelAlias[20] =
    "";
    theParcelFormat[20] =
    "3m";
    theParcelFields[21] =
    "Desc14";
    theParcelAlias[21] =
    "";
    theParcelFormat[21] =
    "3m";
    theParcelFields[22] =
    "Desc15";
    theParcelAlias[22] =
    "";
    theParcelFormat[22] =
    "3m";
    theParcelFields[23] =
    "Desc16";
    theParcelAlias[23] =
    "";
    theParcelFormat[23] =
    "3m";
    theParcelFields[24] =
    "Desc17";
    theParcelAlias[24] =
    "";
    theParcelFormat[24] =
    "3m";
    theParcelFields[25] =
    "Desc18";
    theParcelAlias[25] =
    "";
    theParcelFormat[25] =
    "3m";
    theParcelFields[26] =
    "Desc19";
    theParcelAlias[26] =
    "";
    theParcelFormat[26] =
    "3m";
    theParcelFields[27] =
    "Desc20";
    theParcelAlias[27] =
    "";
    theParcelFormat[27] =
    "3m";
    theParcelFields[28] =
    "Desc21";
    theParcelAlias[28] =
    "";
    theParcelFormat[28] =
    "3m";
    theParcelFields[29] =
    "Desc22";
    theParcelAlias[29] =
    "";
    theParcelFormat[29] =
    "3m";
    theParcelFields[30] =
    "Desc23";
    theParcelAlias[30] =
    "";
    theParcelFormat[30] =
    "3e";
    theParcelFields[31] =
    "Platname";
    theParcelAlias[31] =
    "Plat Name:";
    theParcelFormat[31] =
    "2";
    theParcelFields[32] =
    "Lot";
    theParcelAlias[32] =
    "Lot:";
    theParcelFormat[32] =
    "1";
    theParcelFields[33] =
    "Block";
    theParcelAlias[33] =
    "Block:";
    theParcelFormat[33] =
    "1";
}

