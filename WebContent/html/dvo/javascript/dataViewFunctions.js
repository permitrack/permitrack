// dataViewFunctions.js
// Glen Ausse
// Septmeber 2003
var theMapTitle = "";
var theMapComments = "";
var theMapPageSize = "letter";
var thePrintOrientation = "portrait";
var thePrintoutLegend = "NoLegend";
var theCompleteSearchSet = "";
var objectIDIndex = new Array(); // array arranged by layer index containing the index of the ObjectID field in the original data
var objectIDValue = new Array(); // array arranged by layer index containing the value of the unique ObjectID field in the original data
function clearShape()
{
    parent.MapFrame.deactivateRubberband();
    parent.MapFrame.startNewSelection =
    true;
    parent.MapFrame.resetPolyline();
    parent.MapFrame.resetClick();
}
function zoomToScale(iScaleFactor)
{
    var iScreenDPI = 96;
    var fFeetPerInch = 1
        / 12; // Assumes source data in feet
    var fInchesPerDot = 1
        / iScreenDPI;
    var xNewDistance = (iWidth
                            * fInchesPerDot
        * fFeetPerInch)
        * iScaleFactor;
    var yNewDistance = (iHeight
                            * fInchesPerDot
        * fFeetPerInch)
        * iScaleFactor;
    var xCenter = (eRight
        + eLeft)
        / 2;
    var yCenter = (eTop
        + eBottom)
        / 2;
    saveLastExtent();
    eLeft =
    xCenter
        - (xNewDistance
        / 2);
    eRight =
    xCenter
        + (xNewDistance
        / 2);
    eBottom =
    yCenter
        - (yNewDistance
        / 2);
    eTop =
    yCenter
        + (yNewDistance
        / 2);
    parent.MapFrame.checkFullExtent();
    sendMapXML();
}
function zoomTomySelection(thesearchstring)
{
    parent.MapFrame.updateStatus(parent.MapFrame.modeName);
    sendQueryString(thesearchstring);
}
function TrimStringSpaces(str)
{
    var strlen = str.length;
    if (strlen
        == 0)
    {
        return null;
    } else if (str.charAt(strlen
                              - 1)
        == " ")
    {
        return rtrim(str.substring(0,
                                   strlen
                                       - 1));
    }
    else
    {
        return str;
    }
}
function downloadMap()
{
    var imgOrURL = document.theImage.src;
    if (navigator.appName
        == "Netscape")
    {
        isNav =
        true;
        var vars = "left=0,top=0,screenX=0,screenY=0,height="
                       + mHeight
                       + ",width="
                       + mWidth
            + ",directories=0,fullscreen=0,status=1,menubar=yes,toolbar=0,location=0,resizable=1,scrollbars=yes";
        var Win1 = window.open(imgOrURL,
                               "MapPage",
                               vars);
    }
    else
    {
        var vars = "left=0,top=0,screenX=0,screenY=0,height="
                       + mHeight
                       + ",width="
                       + mWidth
            + ",directories=0,fullscreen=0,status=1,toolbar=0,location=0,resizable=0,scrollbars=no";
        win =
        window.open(imgOrURL,
                    "",
                    vars);
        setTimeout('win.document.execCommand("SaveAs")',
                   3000);
        setTimeout('win.close();',
                   3000);
    }
}
function isPortraitSet()
{
    if (thePrintOrientation
        == "portrait")
    {
        return true;
    }
    else
    {
        return false;
    }
}
function setPrintOrientationValue(theOrientation)
{
    thePrintOrientation =
    theOrientation;
}
function setPageSize(theSize)
{
    theMapPageSize =
    theSize;
}
function setPrintLegendValue(theLegend)
{
    thePrintoutLegend =
    theLegend;
}
function returnPrintLegend()
{
    return thePrintoutLegend;
}
function returnthePIN()
{
    return thePIN;
}
function zoomTomyPIN(thePIN)
{
    parent.MapFrame.updateStatus(parent.MapFrame.modeName);
    thePINsearch =
    thePinField
        + '= "'
        + thePIN
        + '"';
    sendQueryString(thePINsearch);
}
function setActiveLayerByName(name)
{

    //alert('setting active layer for name: ' + name);
    setActiveLayer(2);
    for (i =
         0; i
        < LayerName.length; i++)
    {
        if (LayerName[i]
            == name)
        {
            //alert('setting active layer to ' + i + ' for name: ' + name);
            setActiveLayer(i);
            break;
        }
    }
}
function displayMailingLabelData()
{
    setLayerFields(ActiveLayerIndex);
    var url = appDir
        + "displayMailingLabelData.htm";
    var Win1 = open(url,
                    "MailingLabelWindow",
                    "width=850,height=350,scrollbars=yes,resizable=yes");
}
function displayAdHocWindow()
{
    setLayerFields(ActiveLayerIndex);
    var url = appDir
        + "adhocQueryBuilder.htm";
    var Win1 = open(url,
                    "AdhocQueryWindow",
                    "width=750,height=375,scrollbars=yes,resizable=yes");
}
function searchLandRecordsByOwnerName()
{
    var theOwnerName = parent.MapFrame.theOwnerName.toUpperCase();
    var theOwnerName1Field = parent.MapFrame.theOwnerName1Field;
    var theOwnerName2Field = parent.MapFrame.theOwnerName2Field;
    var namesearch = "";
    var namesearch2 = "";
    theOwnerName =
    theOwnerName.replace("&AMP;",
                         "&");
    if ((theOwnerName
        != "")
        && (theOwnerName1Field
        != ""))
    {
        namesearch =
        "UPPER("
            + theOwnerName1Field
            + ') LIKE "%'
            + theOwnerName
            + '%"';
    }
    if ((theOwnerName
        != "")
        && (theOwnerName2Field
        != ""))
    {
        namesearch2 =
        "UPPER("
            + theOwnerName2Field
            + ') LIKE "%'
            + theOwnerName
            + '%"';
    }
    if (namesearch
        != "")
    {
        namesearch =
        namesearch
            + " OR "
            + namesearch2;
        parent.MapFrame.zoomTomySelection(namesearch);
    }
    else
    {
        parent.MapFrame.zoomTomySelection(namesearch);
    }
}
function html2text(html)
{
    var text = html;
    text =
    HtmlReplace(text,
                "&quot;",
                '"');
    text =
    HtmlReplace(text,
                "&lt;",
                '<');
    text =
    HtmlReplace(text,
                "&amp;",
                '&');
    text =
    HtmlReplace(text,
                "&apos;",
                "'");
    text =
    HtmlReplace(text,
                "&nbsp;",
                "");
    return text;
}
function HtmlReplace(str, original, replacement)
{
    var result;
    result =
    "";
    while (str.indexOf(original)
        != -1)
    {
        if (str.indexOf(original)
            > 0)
        {
            result =
            result
                + str.substring(0,
                                str.indexOf(original))
                + replacement;
        }
        else
        {
            result =
            result
                + replacement;
        }
        str =
        str.substring(str.indexOf(original)
                          + original.length,
                      str.length);
    }
    return result
        + str;
}
// save zoom limits from feature selection
function getAreaZoom(theReply, fCount)
{
    var endpos = xmlEndPos;
    var selectedData = "";
    var inData = "";
    var fudgefactor = 100;
    endpos =
    1;
    for (var i = 0; i
        < fCount; i++)
    { // records
        var theXYs = getEnvelopeXYs(theReply,
                                    endpos)
        parent.MapFrame.selectLeft[i] =
        theXYs[0];
        parent.MapFrame.selectBottom[i] =
        theXYs[1];
        parent.MapFrame.selectRight[i] =
        theXYs[2];
        parent.MapFrame.selectTop[i] =
        theXYs[3];
        endpos =
        xmlEndPos;
    }
    var maxZoomToHeight = parent.MapFrame.selectTop[0];
    var minZoomToHeight = parent.MapFrame.selectBottom[0];
    var minZoomToWidth = parent.MapFrame.selectLeft[0];
    var maxZoomToWidth = parent.MapFrame.selectRight[0];
    for (var i = 1; i
        < fCount; i++)
    {
        if (parent.MapFrame.selectTop[i]
            > maxZoomToHeight)
        {
            maxZoomToHeight =
            parent.MapFrame.selectTop[i];
        }
        if (parent.MapFrame.selectBottom[i]
            < minZoomToHeight)
        {
            minZoomToHeight =
            parent.MapFrame.selectBottom[i];
        }
        if (parent.MapFrame.selectLeft[i]
            < minZoomToWidth)
        {
            minZoomToWidth =
            parent.MapFrame.selectLeft[i];
        }
        if (parent.MapFrame.selectRight[i]
            > maxZoomToWidth)
        {
            maxZoomToWidth =
            parent.MapFrame.selectRight[i];
        }
    }
    parent.MapFrame.saveLastExtent();
    parent.MapFrame.eLeft =
    minZoomToWidth
        - parent.MapFrame.selectPointMargin
        - fudgefactor; //left
    parent.MapFrame.eRight =
    maxZoomToWidth
        + parent.MapFrame.selectPointMargin
        + fudgefactor; //right
    parent.MapFrame.eTop =
    maxZoomToHeight
        + parent.MapFrame.selectPointMargin
        + fudgefactor; //top
    parent.MapFrame.eBottom =
    minZoomToHeight
        - parent.MapFrame.selectPointMargin
        - fudgefactor; //bottom
}



