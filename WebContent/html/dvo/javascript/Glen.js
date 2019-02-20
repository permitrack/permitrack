// Glen.js
// Glen Ausse
// Septmeber 2003
var theMapTitle = "";
var thePrintOrientation = "portrait";
var thePrintoutLegend = "NoLegend";
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



