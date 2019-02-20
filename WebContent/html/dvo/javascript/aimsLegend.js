// aimsLegend.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js
 */
aimsLegendPresent =
true;
var drawLegendOnly = false;
var defaultLegTitle = legTitle;
/*
 ***************************************************************************************

 Legend functions

 ***************************************************************************************
 */



// send request to create graphic legend
function getLegend()
{
    legendVisible =
    true;
    drawLegendOnly =
    true;
    var theString = writeXML();
    showRetrieveMap();
    sendToServer(imsURL,
                 theString,
                 98);
}
// write out the legend display
function showLegend()
{
    parent.TOCFrame.document.open();
    parent.TOCFrame.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset='
                                         + charSet
                                         + '"><head><title>'
                                         + titleList[3]
                                         + '</title>');
    parent.TOCFrame.document.writeln('<style type="text/css">a {text-decoration:none;}</style>');
    parent.TOCFrame.document.writeln('</head>');
    parent.TOCFrame.document.writeln('<body BGCOLOR="White" text="Black" leftmargin=0 topmargin=0 rightmargin=0 link="Black" vlink="Black" alink="Black">');
    parent.TOCFrame.document.writeln('<center>');
    parent.TOCFrame.document.writeln('<IMG SRC="'
                                         + legendImage
                                         + '" HSPACE=0 VSPACE=0 BORDER=0 ALT="'
                                         + titleList[3]
                                         + '"></center>');
    parent.TOCFrame.document.writeln('</body></html>');
    parent.TOCFrame.document.close();
}
// add Legend to XML request
function addLegendToMap()
{
    var legString = '<LEGEND title="'
                        + legTitle
                        + '" font="'
                        + legFont
                        + '" width="'
                        + legWidth
                        + '" height="'
                        + legHeight
        + '" ';
    legString +=
    'autoextend="true" backgroundcolor="255,255,255"';
    if (hideLayersFromList)
    {
        legString +=
        '>\n<LAYERS>\n';
        for (var legvar = 0; legvar
            < noListLayer.length; legvar++)
        {
            if (noListLayer[legvar])
            {
                legString +=
                '<LAYER id="'
                    + LayerID[legvar]
                    + '" />\n';
            }
        }
        legString +=
        '</LAYERS>\n';
        legString +=
        '</legend></fieldset>\n';
    }
    else
    {
        legString +=
        ' />\n';
    }
    if (drawLegendOnly)
    {
        legString =
        legString
            + '<DRAW map="false" />\n';
    }
    return legString;
}
