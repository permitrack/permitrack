// aimsPrint.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js,
 *		aimsLayers.js, aimsDHTML.js
 *		aimsClick.js, aimsNavigation.js,
 *		aimsLegend.js
 */
aimsPrintPresent =
true;
var printTitle = titleList[4];
var printMapURL = "";
var printOVURL = "";
var printLegURL = "";
var legVis2 = false;
//***************************************************************************************
//Print functions
//***************************************************************************************
function printIt()     // display print form
{
    hideLayer("measureBox");
    if (useTextFrame)
    {
        parent.TextFrame.document.location =
        "printform.htm";
    }
    else
    {
        var Win1 = open("printform.htm",
                        "PrintFormWindow",
                        "width=575,height=150,scrollbars=yes,resizable=yes");
    }
}
function getPrintMap(title)     // Create web page for printing
{                // Step 1 - Get Map
    showRetrieveMap();
    printTitle =
    title;
    var tempWidth = iWidth;
    var tempHeight = iHeight;
    if (thePrintOrientation
        == "portrait")
    {
        if (theMapPageSize
            == "letter")
        {
            iWidth =
            650;
            iHeight =
            670;
        }
        else
        {
            iWidth =
            840;
            iHeight =
            1160;
        }
    }
    else
    {
        if (theMapPageSize
            == "letter")
        {
            iWidth =
            690;
            iHeight =
            600;
        }
        else
        {
            iWidth =
            1130;
            iHeight =
            790;
        }
    }
    legVis2 =
    legendVisible;
    if (aimsLegendPresent)
    {
        legendVisible =
        true;
    }
    var theString = writeXML();
    iWidth =
    tempWidth;
    iHeight =
    tempHeight;
    legendVisible =
    legVis2;
    sendToServer(imsURL,
                 theString,
                 101);
    tempWidth =
    null;
    tempHeight =
    null;
    theString =
    null;
}
function getMapForSize(x, y)
{
    showRetrieveMap();
    var tempWidth = iWidth;
    var tempHeight = iHeight;
    iWidth =
    x;
    iHeight =
    y;
    legVis2 =
    legendVisible;
    if (aimsLegendPresent)
    {
        legendVisible =
        true;
    }
    var theString = writeXML();
    iWidth =
    tempWidth;
    iHeight =
    tempHeight;
    legendVisible =
    legVis2;
    sendToServer(imsURL,
                 theString,
                 999);
    tempWidth =
    null;
    tempHeight =
    null;
    theString =
    null;
    hideRetrieveMap();
}
function getPrintOV()       // Step 2 - Get OVMap
{
    var tempWidth = i2Width;
    var tempHeight = i2Height;
    i2Width =
    100;
    i2Height =
    79;
    var tempDraw = drawOVExtentBox;
    drawOVExtentBox =
    true;
    var theString = writeOVXML();
    drawOVExtentBox =
    tempDraw;
    i2Width =
    tempWidth;
    i2Height =
    tempHeight;
    sendToServer(imsOVURL,
                 theString,
                 102);
    tempWidth =
    null;
    tempHeight =
    null;
    theString =
    null;
}
function getPrintLegend()     // Step 3 - Get Legend
{
    if (printLegURL
        == "")
    {
        printLegURL =
        "images/nolegend.gif";
    }	//  waiting for Legend tags
    writePrintPage();
}
function writePrintPage()     // 4 - Write Web Page
{
    // set window size according to report size
    var x = 900;
    var y = 660;
    if (theMapPageSize
        == "bsize")
    {
        x =
        1390;
        y =
        855;
    }
    if (thePrintOrientation
        == "portrait")
    {
        var tmp = x;
        x =
        y;
        y =
        tmp;
    }
    // <font size=-1 face=verdana>
    var Win1 = window.open("./html/dvo/mapLayout.htm",
                           "PrintPage",
                           "toolbar=yes,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width="
                               + (x
                               + 15)
                               + ",height="
                               + (y
                               + 15));
    var map = Win1.document;
    hideRetrieveMap();
    return;
}
