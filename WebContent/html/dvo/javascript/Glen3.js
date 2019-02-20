var printMapURL = "";
function PrintReport()
{
    hideLayer("measureBox");
    var t;
    var t2 = "";
    t =
    parent.MapFrame;
    t2 =
    "parent.MapFrame";
    var Win1 = open("",
                    "PrintPage");
    Win1.document.writeln('<html>');
    Win1.document.writeln('<head>');
    Win1.document.writeln('</head>');
    Win1.document.writeln('<body>');
    var theReply = t.myLastParcelSelected;
    var epos = 1;
    var featureCount = t.justGetFeatureCount(theReply);
    var endpos = t.xmlEndPos;
    var tempCount = 0;
    var selectedData = "";
    var inData = "";
    var fCount = featureCount;
    var theSwitcher = false;
    printMapURL =
    t.theImage.src;
    t.selectCount =
    0;
    endpos =
    1;
    inData =
    t.parseRecordString(theReply,
                        endpos);
    endpos =
    t.xmlEndPos;
    selectedData =
    inData;
    epos =
    theReply.indexOf("</FEATURE",
                     endpos);
    var fName1 = t.getFieldNames(selectedData);
    var fValue1 = t.getFieldValues(selectedData);
    t.selectPoints[t.selectCount] =
    t.getIdValue(fName1,
                 fValue1);
    Win1.document.writeln('<LINK rel="stylesheet" type="text/css" href="gis.css" />');
    Win1.document.writeln('<table border="0" cellspacing="1" width="650" height="800">');
    Win1.document.writeln('  <tr>')
    Win1.document.writeln('    <td colspan="2" align="center" class="Blksubtitle">'
                              + t.theReportTitle
                              + '<br></td>');
    Win1.document.writeln('  </tr>');
    Win1.document.writeln('<td valign="top">');
    Win1.document.writeln('<table border="0" cellspacing="1" width="350">');
    Win1.document.writeln('  <tr bgcolor="#003366">');
    Win1.document.writeln('	   <td colspan="2" align="center" class="whtnavtitle">Property Information</td>');
    Win1.document.writeln('  </tr>');
    t.LoadParcelFields();	//Load Parcel Fields
    for (var z = 0; z
        < t.theParcelFields.length; z++)
    {
        var tFld = "";
        var tFlds = "";
        var tValue = "";
        tFld =
        t.theParcelFields[z].toUpperCase();
        for (var f = 0; f
            < fName1.length; f++)
        {
            tFlds =
            fName1[f].toUpperCase();
            //Set Value
            for (var f = 0; f
                < fName1.length; f++)
            {
                if (fName1[f]
                    == tFld)
                {
                    tValue =
                    fValue1[f];
                }
            }
            tValue =
            t.html2text(tValue);
            if (t.theParcelAlias[z]
                != "")
            {
                if (theSwitcher)
                {
                    Win1.document.writeln('<tr bgcolor="#D8E6FE">');
                    theSwitcher =
                    false;
                }
                else
                {
                    Win1.document.writeln('<tr bgcolor="white">');
                    theSwitcher =
                    true;
                }
                Win1.document.writeln('<td class="table" nowrap><b> '
                                          + t.theParcelAlias[z]
                                          + '</b>');
                Win1.document.writeln(tValue);
                Win1.document.writeln('</td>');
                Win1.document.writeln('</tr>');
            }
        }
    } //end for
    Win1.document.writeln('</td>');
    Win1.document.writeln('</table>');
    //Right Column
    Win1.document.writeln('<td valign="top">');
    Win1.document.writeln('  <table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%">');
    Win1.document.writeln('    <tr>');
    Win1.document.writeln('      <td width="100%" colspan="3">');
    Win1.document.writeln('        <IMG BORDER="1" SRC="'
                              + printMapURL
                              + '" WIDTH=325 HEIGHT=225 HSPACE=0 VSPACE=0 BORDER=0 ALT="">');
    Win1.document.writeln('      </td>');
    Win1.document.writeln('    </tr>');
    Win1.document.writeln('    <tr>');
    Win1.document.writeln('      <td width="100%" align="center" colspan="3" class="pcfineprint">');
    Win1.document.writeln(theDisclaimer);
    Win1.document.writeln('      </td>');
    Win1.document.writeln('    </tr>');
    Win1.document.writeln('    <tr>');
    Win1.document.writeln('      <td width="100%" align="center" colspan="3">');
    Win1.document.writeln('        <IMG BORDER="0" SRC="'
                              + document.location.protocol
                              + '//'
                              + hostName
                              + '/'
                              + theClientPath
                              + '/images/newsehlogo.jpg" WIDTH=184 HEIGHT=41 HSPACE=10 VSPACE=0 BORDER=0 ALT="">');
    Win1.document.writeln('      </td>');
    Win1.document.writeln('    </tr>');
    Win1.document.writeln('  </table>');
    Win1.document.writeln('</td>');
    Win1.document.writeln('</table>');
    win1.document.writeln('</body>');
    Win1.document.writeln('</html>');
    Win1.document.close();
    Win1 =
    null;
    //hideRetrieveMap();
}