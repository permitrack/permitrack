// aimsResource.js
/*
 *  JavaScript resource file for ArcIMS HTML Viewer
 *		arrays containing display text
 * 		This file can be swapped out with appropriate language translation
 */
var msgList = new Array();
var unitList = new Array();
var sUnitList = new Array();
var modeList = new Array();
var titleList = new Array();
var buttonList = new Array();
// ArcIMSparam.js - msgList
msgList[0] =
"Unable to load one of the following Javascript Libraries:\naimsCommon.js\naimsXML.js\naimsMap.js";
// aimsCommon.js - msgList
msgList[1] =
"No request for Limit Extent";
msgList[2] =
"Unable to start. Required HTML Form missing (jsForm.htm).";
msgList[3] =
"Current Extent:\nLeft: ";
msgList[4] =
"\nBottom: ";
msgList[5] =
"\nRight: ";
msgList[6] =
"\nTop: ";
msgList[7] =
"\n\nRatio to Full Extent: ";
// aimsXML.js - msgList
msgList[8] =
"ServiceName: ";
msgList[9] =
"\nArcXML Request:\n ";
msgList[10] =
"Response from previous request(s) not received.";
msgList[11] =
"Form for posting request not found. Unable to communicate with server.";
msgList[12] =
"Sending:\n\n";
msgList[13] =
"ArcXML Response:\n ";
msgList[14] =
"Unable to display MapService\n";
msgList[15] =
"Cannot send request";
msgList[16] =
"Unable to display Overview Map MapService\n";
msgList[17] =
"Unable to set parameters";
msgList[18] =
"Unable to get image for click positions.";
msgList[19] =
"\nUnable to execute response.";
// aimsLayers.js - msgList
msgList[20] =
" is now the Active Layer";
msgList[21] =
"Unable to obtain Field names.\nUnable to query layer.";
msgList[22] =
"Name:";
msgList[23] =
"Id:";
msgList[24] =
"Type:";
msgList[25] =
"Extent:";
msgList[26] =
"Min Scale:";
msgList[27] =
"Max Scale:";
msgList[28] =
"Fields:";
msgList[29] =
"Size:";
msgList[30] =
"Precision:";
msgList[31] =
"Unknown";
msgList[32] =
"String";
msgList[33] =
"Id";
msgList[34] =
"Shape";
msgList[35] =
"Integer";
msgList[36] =
"Double";
msgList[37] =
"Boolean";
msgList[38] =
"Floating";
msgList[39] =
"Small Integer";
msgList[40] =
"BLOB";
msgList[41] =
"Date";
msgList[42] =
"None";
msgList[43] =
"Address Match";
msgList[44] =
"No";
// aimsDHTML.js - msgList
msgList[45] =
"A MapService must be loaded";
// aimsClick.js - msgList
msgList[46] =
"Cannot query MapService\nIdentify, Select, and Query functions are disabled.";
msgList[47] =
"This layer does not have any Hyperlinks.";
msgList[48] =
"No selected features to buffer.";
msgList[49] =
"Buffer Mode disabled.";
msgList[50] =
"Legend Library (aimsLegend.js) not loaded.";
msgList[51] =
"Function not enabled.";
// aimsNavigation.js - msgList
msgList[52] =
"Map: ";
msgList[53] =
"Image: ";
msgList[54] =
"ScaleFactor: ";
// aimsCustom.js - msgList
msgList[55] =
"Unknown Mode:";
msgList[56] =
"\nUnable to execute request.";
// aimsGeocode.js - msgList
msgList[57] =
"Locate Results";
msgList[58] =
"Address";
msgList[59] =
"Score";
msgList[60] =
"Unable to locate address";
// aimsIdentify.js - msgList
msgList[61] =
"Previous ";
msgList[62] =
" Records";
msgList[63] =
"More Records";
msgList[64] =
"No Features found.";
msgList[65] =
"Returned ArcXML Response:";
msgList[66] =
"Server returned:";
msgList[67] =
"Hyperlink to ";
msgList[68] =
"Unable to hyperlink with submitted request.";
msgList[69] =
"Unable to hyperlink.";
// aimsSelect.js - msgList
msgList[70] =
"Measure Totals cleared.";
msgList[71] =
"Selection cleared.";
// aimsQuery.js - msgList
msgList[72] =
"Field";
msgList[73] =
"Operator";
msgList[74] =
"Requesting sample values for the layer ";
msgList[75] =
"The first ";
msgList[76] =
" records are being accessed. There may be duplicate values.";
msgList[77] =
":\nThis layer does not have any StoredQueries.";
msgList[78] =
"Search";
msgList[79] =
"Search is Case-Sensitive";
msgList[80] =
"Unable to build Query String.";
// aimsBuffer.js - msgList
msgList[81] =
"Distance must be at least zero.\\nStart all numbers less than one with a leading zero.";
msgList[82] =
"Highlight features from ";
msgList[83] =
" within a distance of&nbsp;";
msgList[84] =
" around the selected features of ";
msgList[85] =
"Display Attributes";
msgList[86] =
"Rec";
// addmatch.htm - msgList
msgList[87] =
"No Layer";
msgList[88] =
"Layer";
msgList[89] =
"Please enter a string";
msgList[90] =
"Enter String to Find in ";
msgList[91] =
"Cancelled.";
msgList[92] =
"Find String";
// select.htm - msgList
msgList[93] =
"A minimum of two points are required.";
msgList[94] =
"A minimum of three points are required.";
msgList[95] =
"Select with Line or Polygon";
// printForm.htm
msgList[96] =
"Click on \"Create Print Page\" to open a new Browser window with the Map Image,";
msgList[97] =
"Overview Map Image, and Legend displayed. You can then use the File/Print menu item";
msgList[98] =
"to send the display to your printer.";
msgList[99] =
"Please enter a string";
msgList[100] =
"The modules available on this site make extensive use of JavaScript <br> and requires a 4.0 or newer Browser.<br>Internet Explorer is not supported on the Macintosh."; // default.htm
msgList[101] =
" is not visible due to scale and features cannot be selected.\n\nZoom to a scale where the layer is visible.\n\n";
msgList[102] =
" is not visible and features cannot be selected.\n\nCheck Visible for ";
msgList[103] =
"Check Visible for the layer and click Refresh Map.";
msgList[104] =
"The Active Layer ";
msgList[105] =
"At least one point is required";
msgList[106] =
"Buffer Shape";
msgList[107] =
"Buffer Size:";
msgList[108] =
" and click Refresh Map.";
msgList[109] =
"No operator in query";
msgList[110] =
"Query expression is blank.\nNo features have been selected.";
msgList[111] =
"No Layers visible.";
msgList[112] =
"No features found at click location.";
msgList[113] =
"Loading Viewer. . .";
msgList[114] =
"No hyperlinks defined for any of the visible layers.";
msgList[115] =
"No Hyperlinks defined.";
msgList[116] =
"No hyperlink defined for any layer at click location";
msgList[117] =
"Retrieving Data. . .";
msgList[118] =
"Retrieving Map. . .";
msgList[119] =
"You are not authorized to make map requests to this MapService.";
msgList[120] =
"Debug Mode On";
msgList[121] =
"Debug Mode Off";
msgList[122] =
" records are being accessed. Duplicate values will be sorted out, resulting in fewer samples.";
msgList[123] =
"";
msgList[124] =
"";
msgList[125] =
"";
msgList[126] =
"";
msgList[127] =
"";
msgList[128] =
"";
msgList[129] =
"";
msgList[130] =
"";
/*
 msgList[1] = "";
 msgList[2] = "";
 msgList[3] = "";
 msgList[4] = "";
 msgList[5] = "";
 msgList[6] = "";
 msgList[7] = "";
 msgList[8] = "";
 msgList[9] = "";
 msgList[0] = "";
 */
unitList[0] =
"DEGREES";
unitList[1] =
"FEET";
unitList[2] =
"MILES";
unitList[3] =
"METERS";
unitList[4] =
"KILOMETERS";
sUnitList[0] =
"DEGREES";
sUnitList[1] =
"FEET";
sUnitList[2] =
"MILES";
sUnitList[3] =
"METERS";
sUnitList[4] =
"KILOMETERS";
modeList[0] =
"Zoom In";
modeList[1] =
"Zoom Out";
modeList[2] =
"Pan";
modeList[3] =
"Identify";
modeList[4] =
"Select Rectangle";
modeList[5] =
"Select Point";
modeList[6] =
"Select Line";
modeList[7] =
"Select Polygon";
modeList[8] =
"Select Line/Polygon";
modeList[9] =
"Hyperlink";
modeList[10] =
"Select Shape";
modeList[11] =
"Buffer Shape";
modeList[12] =
"Measure";
modeList[13] =
"Create Shape";
modeList[14] =
"Locate Address";
modeList[15] =
"Query";
modeList[16] =
"Search";
modeList[17] =
"Find";
modeList[18] =
"Buffer";
modeList[19] =
"Identify All";
modeList[20] =
"ID Visible Features";
modeList[21] =
"Get Address";
titleList[0] =
"ArcIMS HTML Viewer";
titleList[1] =
"Current Active Layer";	// aimsLayers.js
titleList[2] =
"Layer Information";
titleList[3] =
"Legend";	// aimsLegend.js
titleList[4] =
"ArcIMS HTML Viewer Map"; // aimsPrint.js
titleList[5] =
"Map Output";
titleList[6] =
"Address Match Candidates";	// aimsGeocode.js
titleList[7] =
"Query/Selection Results";	// aimsIdentify.js
titleList[8] =
"Select Results";
titleList[9] =
"Hyperlink Results";
titleList[10] =
"Buffer Results";			// aimsBuffer.js
titleList[11] =
"Address Matching";			// aimsGeocode.js
titleList[12] =
"Toolbar";
titleList[13] =
"Layers";
titleList[14] =
" Visible ";
titleList[15] =
" Active ";
titleList[16] =
"Display Units";			// setUnits.htm
titleList[17] =
"Map Units";
titleList[18] =
"PrintForm";				// printForm.htm
titleList[19] =
"Title to display on Map:<BR>";
titleList[20] =
"ArcIMS HTML Viewer Map";
titleList[21] =
"Print Map";
titleList[22] =
"";
;
titleList[23] =
"";
titleList[24] =
"";
titleList[25] =
"";
titleList[26] =
"";
titleList[27] =
"";
titleList[28] =
"";
titleList[29] =
"";
titleList[30] =
"";
/*
 titleList[0] = "";
 titleList[1] = "";
 titleList[2] = "";;
 titleList[3] = "";
 titleList[4] = "";
 titleList[5] = "";
 titleList[6] = "";
 titleList[7] = "";
 titleList[8] = "";
 titleList[9] = "";
 */
buttonList[0] =
"Hide Legend"; // aimsLegend.js
buttonList[1] =
"Locate Another Address"; // aimsGeocode.js
buttonList[2] =
"Value";	// aimsQuery.js
buttonList[3] =
"Sample Values";
buttonList[4] =
"Get Samples";
buttonList[5] =
"Add to Query String";
buttonList[6] =
"Execute";
buttonList[7] =
"Undo";
buttonList[8] =
"Clear";
buttonList[9] =
"Create Buffer";	// aimsBuffer.js
buttonList[10] =
"Yes";
buttonList[11] =
"No";
buttonList[12] =
"Select Layer";	// addmatch.htm
buttonList[13] =
"Locate";
buttonList[14] =
"Toggle between Legend and LayerList";		// toolbar.htm
buttonList[15] =
"Toggle Overview Map";
buttonList[16] =
"Zoom In";
buttonList[17] =
"Zoom Out";
buttonList[18] =
"Zoom to Full Extent";
buttonList[19] =
"Zoom to Active Layer";
buttonList[20] =
"Back to Last Extent";
buttonList[21] =
"Pan";
buttonList[22] =
"Pan to North";
buttonList[23] =
"Pan to South";
buttonList[24] =
"Pan to West";
buttonList[25] =
"Pan to East";
buttonList[26] =
"HyperLink";
buttonList[27] =
"Identify";
buttonList[28] =
"Query";
buttonList[29] =
"Search";
buttonList[30] =
"Find";
buttonList[31] =
"Measure";
buttonList[32] =
"Set Units";
buttonList[33] =
"Buffer";
buttonList[34] =
"Select by Rectangle";
buttonList[35] =
"Select by Line/Polygon";
buttonList[36] =
"Clear Selection";
buttonList[37] =
"Locate Address";
buttonList[38] =
"Print";
buttonList[39] =
"Extract";
buttonList[40] =
"Options";
buttonList[41] =
"Load MapService";
buttonList[42] =
"Hide LayerList";	// toc.htm
buttonList[43] =
"Info";
buttonList[44] =
"Refresh Map";
buttonList[45] =
"    Restart    "; // select.htm
buttonList[46] =
" Delete Last Point ";
buttonList[47] =
"  Complete Line & Select  ";
buttonList[48] =
"Complete Polygon & Select";
buttonList[49] =
"Cancel"; // setUnits.htm
buttonList[50] =
"Create Print Page";  // printForm.htm
buttonList[51] =
"Buffer Point & Select";
buttonList[52] =
"Buffer Line & Select";
buttonList[53] =
"Complete Polygon, Buffer, Select";
buttonList[54] =
"Use Point to Select";
buttonList[55] =
"Get Address @ Click";
buttonList[56] =
"Find Route";
buttonList[57] =
"";
buttonList[58] =
"";
buttonList[59] =
"";
buttonList[60] =
"";
/*
 buttonList[1] = "";
 buttonList[2] = "";
 buttonList[3] = "";
 buttonList[4] = "";
 buttonList[5] = "";
 buttonList[6] = "";
 buttonList[7] = "";
 buttonList[8] = "";
 buttonList[9] = "";
 buttonList[0] = "";
 */
