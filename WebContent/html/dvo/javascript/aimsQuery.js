// aimsQuery.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js, aimsIdentify.js, aimsSelect.js, and aimsLayers.js
 *		To be interactive, dependent also on aimsDHTML.js, aimsClick.js, and aimsNavigation.js
 */
aimsQueryPresent =
true;
var showSampleValues = false;
var storedQueryCount = 0;
var storedQueryName = new Array();
var storedQueryString = new Array();
var storedQueryVariable = new Array();
var storedQueryVarCount = new Array();
var storedQueryFieldList = new Array();
var storedQueryIndex = 0;
/*
 ***************************************************************************************

 Querying functions

 ***************************************************************************************
 */


// query form
function queryForm()
{
    if (checkIfActiveLayerAvailable())
    {
        fieldIndex =
        0;
        showSampleValues =
        false;
        setLayerFields(ActiveLayerIndex);
        if (showSampleValues)
        {
            var theText = writeFieldSample(LayerFields[fieldIndex]);
            sendToServer(imsQueryURL,
                         theText,
                         40);
        }
        else
        {
            writeQueryForm();
        }
    }
}
// find Form
function findForm()
{
    if (checkIfActiveLayerAvailable())
    {
        setLayerFields(ActiveLayerIndex);
        if (useTextFrame)
        {
            parent.TextFrame.document.location =
            appDir
                + "findForm.htm";
        }
        else
        {
            var Win1 = open(appDir
                                + "findForm.htm",
                            "QueryWindow",
                            "width=575,height=150,scrollbars=yes,resizable=yes");
        }
    }
}
// process query
function sendQueryString(newString)
{
    //if (LayerIDField[ActiveLayerIndex]!="#ID#") {
    newString =
    fixSingleQuotes(newString);
    newString =
    swapQuotes(newString);
    //}
    //alert(newString);
    newString =
    makeXMLsafe(newString);
    setQueryString =
    newString;
    selectionMode =
    1;
    selectData.length =
    0;
    LayerFields.length =
    0;
    LayerFieldType.length =
    0;
    LayerFieldCount =
    0;
    highlightedOne =
    "";
    showBuffer =
    false;
    showRetrieveData();
    var theString = writeQueryXML(newString);
    sendToServer(imsQueryURL,
                 theString,
                 queryXMLMode);
}
// write out XML request to query attributes
function writeQueryXML(queryString)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[ActiveLayerIndex];
    }
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" geometry="false" envelope="true" checkesc ="true"';
    theString +=
    ' featurelimit="'
        + maxFeaturesReturned
        + '" beginrecord="'
        + queryStartRecord
        + '">\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" />';
    if (useLimitExtent)
    {
        // keep this within the limitExtent
        theString +=
        '<SPATIALQUERY subfields="'
            + selectFields
            + '" where="'
            + queryString
            + '">';
        theString +=
        '<SPATIALFILTER relation="area_intersection">\n';
        theString +=
        '<ENVELOPE maxx="'
            + forceComma(limitRight)
            + '" maxy="'
            + forceComma(limitTop)
            + '" minx="'
            + forceComma(limitLeft)
            + '" miny="'
            + forceComma(limitBottom)
            + '" />\n';
        theString +=
        '</SPATIALFILTER>\n';
        theString +=
        '</SPATIALQUERY>\n';
    }
    else
    {
        //theString += '<QUERY subfields="' + selectFields + '" where="' + queryString + '" />';
        theString +=
        '<SPATIALQUERY subfields="'
            + selectFields
            + '" where="'
            + queryString
            + '" />';
    }
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    //alert("writeQueryXML()\nQuery XML Request:\n" + theString);
    selectLayer =
    ActiveLayer;
    selectType =
    ActiveLayerType;
    selectCount =
    0;
    hightlightedOne =
    "";
    selectPoints.length =
    1;
    selectLeft.length =
    1;
    selectRight.length =
    1;
    selectTop.length =
    1;
    selectBottom.length =
    1;
    //alert ("Query: " + theString);
    return theString;
}
// write out XML request to get field value samples
function writeFieldSample(theField)
{
    var theFields = theField;
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" geometry="false" envelope="false" checkesc ="true"';
    theString +=
    ' featurelimit="'
        + numberDataSamples
        + '">\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" />';
    //theString += '<QUERY subfields="' + theFields + '" where="' + LayerIDField[ActiveLayerIndex] + ' GT 0" />';
    theString +=
    '<QUERY subfields="'
        + theFields
        + '" />';
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    //alert(theString);
    selectLayer =
    ActiveLayer;
    selectType =
    ActiveLayerType;
    selectCount =
    0;
    hightlightedOne =
    "";
    selectPoints.length =
    1;
    selectLeft.length =
    1;
    selectRight.length =
    1;
    selectTop.length =
    1;
    selectBottom.length =
    1;
    //alert(theString);
    return theString;
}
// send find request
function getFind(theValue)
{
    //alert(theValue);
    alert("Marisa");
    selectCount =
    0;
    showBuffer =
    false;
    highlightedOne =
    "";
    selectPoints.length =
    0;
    selectLeft.length =
    0;
    selectRight.length =
    0;
    selectTop.length =
    0;
    selectBottom.length =
    0;
    drawSelectBoundary =
    false;
    showGeocode =
    false;
    clickCount =
    0;
    totalMeasure =
    0;
    currentMeasure =
    0;
    selectionMode =
    1;
    var theNewQueryString = "";
    var theTempString = "";
    var fieldListString = LayerIDField[ActiveLayerIndex]
                              + " "
        + LayerShapeField[ActiveLayerIndex];
    var elemCount = 0;
    for (var i = 0; i
        < LayerFields.length; i++)
    {
        if (LayerFieldType[i].indexOf("12",
                                      0)
            != -1)
        {
            if (queryCaseInsensitive)
            {
                theTempString =
                "(UPPER("
                    + LayerFields[i]
                    + ") LIKE '%"
                    + theValue.toUpperCase()
                    + "%')";
            }
            else
            {
                theTempString =
                "("
                    + LayerFields[i]
                    + " LIKE '%"
                    + theValue
                    + "%')";
            }
            if (elemCount
                > 0)
            {
                theTempString =
                " OR "
                    + theTempString;
            }
            //if (theNewQueryString.length + theTempString.length < 1024) {
            theNewQueryString =
            theNewQueryString
                + theTempString;
            fieldListString =
            fieldListString
                + " "
                + LayerFields[i];
            elemCount +=
            1;
            //}
        }
    }
    if (theNewQueryString
        != "")
    {
        showRetrieveData();
        showBuffer =
        false;
        theNewQueryString =
        makeXMLsafe(theNewQueryString);
        setQueryString =
        theNewQueryString;
        var theString = writeFindRequest(theNewQueryString,
                                         fieldListString);
        //alert(theString);
        sendToServer(imsQueryURL,
                     theString,
                     findXMLMode);
    }
    else
    {
        alert(msgList[80]);
    }
}
// write out find form
function writeFindRequest(findQuery, fieldList)
{
    var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" geometry="false" envelope="true" checkesc ="true"';
    theString +=
    ' featurelimit="'
        + maxFeaturesReturned
        + '" beginrecord="'
        + queryStartRecord
        + '">\n';
    theString +=
    '<LAYER id="'
        + ActiveLayer
        + '" />\n';
    if (useLimitExtent)
    {
        // keep this within the limitExtent
        theString +=
        '<SPATIALQUERY subfields="'
            + fieldList
            + '" where="'
            + findQuery
            + '" />';
        theString +=
        '<SPATIALFILTER relation="area_intersection">\n';
        theString +=
        '<ENVELOPE maxx="'
            + forceComma(limitRight)
            + '" maxy="'
            + forceComma(limitTop)
            + '" minx="'
            + forceComma(limitLeft)
            + '" miny="'
            + forceComma(limitBottom)
            + '" />\n';
        theString +=
        '</SPATIALFILTER>\n';
        theString +=
        '</SPATIALQUERY>\n';
    }
    else
    {
        theString +=
        '<QUERY subfields="'
            + fieldList
            + '" where="'
            + findQuery
            + '" />';
    }
    theString +=
    '</GET_FEATURES>\n';
    theString +=
    '</REQUEST>\n';
    theString +=
    '</ARCXML>';
    //alert(theString);
    selectLayer =
    ActiveLayer;
    selectType =
    ActiveLayerType;
    selectCount =
    0;
    hightlightedOne =
    "";
    selectPoints.length =
    1;
    selectLeft.length =
    1;
    selectRight.length =
    1;
    selectTop.length =
    1;
    selectBottom.length =
    1;
    return theString;
}
// parse layer field value samples
function parseFieldSamples(theReply)
{
    if (onlyUniqueSamples)
    {
        parseFieldSamplesUnique(theReply);
    }
    else
    {
        //alert("Reply Length: " + theReply.length);
        //alert(theReply);
        var fList = "";
        var lpos1 = 1;
        var lpos2 = 1;
        var epos = 1;
        var spos = 1;
        var morePoints = true;
        var moreFeatures = true;
        var pos = 0;
        var startpos = 0;
        var endpos = 0;
        var featureCount = justGetFeatureCount(theReply);
        var tempString = "";
        if (featureCount
            > 0)
        {
            selectData.length =
            featureCount;
            for (var i = 0; i
                < featureCount; i++)
            {
                pos =
                theReply.indexOf("<FIELDS ",
                                 endpos);
                startpos =
                pos
                    + 8;
                endpos =
                theReply.indexOf(' />',
                                 startpos);
                inData =
                theReply.substring(startpos,
                                   endpos);
                //inData = fixSingleQuotes(inData);
                //selectData[i] = clearLeadingSpace(inData);
                //selectData[i] = escape(inData);
                selectData[i] =
                inData;
            }
        }
        else
        {
            selectData.length =
            0;
        }
    }
}
//replace the original function parseFieldSamples with this function parseFieldSamples
// parse layer field value samples
function parseFieldSamplesUnique(theReply)
{
    //alert("Reply Length: " + theReply.length);
    //alert(theReply);
    var fList = "";
    var lpos1 = 1;
    var lpos2 = 1;
    var epos = 1;
    var spos = 1;
    var morePoints = true;
    var moreFeatures = true;
    var pos = 0;
    var startpos = 0;
    var endpos = 0;
    var featureCount = justGetFeatureCount(theReply);
    //alert(theReply);
    var tempString = "";
    //variables added to original function
    var k = 1;
    var sdLeng = 0;
    var charSort = false;
    if (featureCount
        > 0)
    {

        //Add the first sample value
        for (var i = 0; i
            < 1; i++)
        {
            if (selectData.length
                > 0)
            {
                selectData.length =
                0;
            }
            pos =
            theReply.indexOf("<FIELDS ",
                             endpos);
            startpos =
            pos
                + 8;
            endpos =
            theReply.indexOf(' />',
                             startpos);
            inData =
            theReply.substring(startpos,
                               endpos);
            selectData[i] =
            inData;
        }
        //Add additional sample values
        for (var i = 1; i
            < featureCount; i++)
        {
            pos =
            theReply.indexOf("<FIELDS ",
                             endpos);
            startpos =
            pos
                + 8;
            endpos =
            theReply.indexOf(' />',
                             startpos);
            inData =
            theReply.substring(startpos,
                               endpos);
            sdLeng =
            selectData.length;
            for (var j = 0; j
                < sdLeng; j++)
            {
                if (selectData[j]
                    == inData)
                {
                    addValue =
                    false;
                    break;
                }
                else
                {
                    addValue =
                    true;
                }
            }
            if (addValue)
            {
                selectData[k] =
                inData;
                k =
                k
                    + 1;
            }
        }
    }
    else
    {
        selectData.length =
        0;
    }
    //alert(selectData.length);
    var testSample = selectData[0];
    var testPos = testSample.indexOf('"');
    var testStartPos = testPos
        + 1;
    var testEndPos = testSample.indexOf('"',
                                        testStartPos);
    var testValue = testSample.substring(testStartPos,
                                         testEndPos);
    var testLeng = testValue.length;
    ///*
    //Test for non-numeric characters in the string
    for (l =
         0; l
        < testLeng; l++)
    {
        testChar =
        testValue.substring(l
                                + 1);
        testChar =
        Number(testChar);
        if (isNaN(testChar))
        {
            charSort =
            true;
            break;
        }
    }
    //*/
    if (charSort)
    {
        selectData.sort();
    }
    else
    {
        selectData.sort(compare);
    }
}
//add this below the new function parseFieldSamples
//function to compare numeric values for function parseFieldSamples numeric sort
function compare(a, b)
{
    var returnVal = 0;
    var aPos = a.indexOf('"');
    var aStartPos = aPos
        + 1;
    var aEndPos = a.indexOf('"',
                            aStartPos);
    var aValue = a.substring(aStartPos,
                             aEndPos);
    var aNum = Number(aValue);
    var bPos = b.indexOf('"');
    var bStartPos = bPos
        + 1;
    var bEndPos = b.indexOf('"',
                            bStartPos);
    var bValue = b.substring(bStartPos,
                             bEndPos);
    var bNum = Number(bValue);
    var theVal = aValue
        - bValue;
    if (isNaN(theVal))
    {
        returnVal =
        -1;
    }
    else
    {
        returnVal =
        theVal;
    }
    return returnVal;
}
// write out a query form
function writeQueryForm()
{
    //
    //    var url = appDir + "query.htm";
    //	if (useTextFrame) {
    //		parent.TextFrame.document.location = url;
    //	} else {
    //		var Win1 = open(url,"QueryWindow","width=575,height=150,scrollbars=yes,resizable=yes");
    //	}
}
// temporarily change getSampleValues to true and load queryform
function tempGetSamples(theField)
{
    showSampleValues =
    true;
    //    var Win1 = "";
    //    if (useTextFrame) {
    //		Win1 = parent.TextFrame;
    //
    //	} else {
    //		Win1 = open("./blank.htm","QueryWindow","width=575,height=150,scrollbars=yes,resizable=yes");
    //	}
    //
    ////    alert("Win1: " + Win1 + " is a " + typeof Win1);
    //    Win1.document.close();
    //    Win1.document.open();
    //	Win1.document.writeln('<html><meta http-equiv="Content-Type" content="text/html; charset=' + charSet + '"><HEAD>');
    //	Win1.document.writeln('<body BGCOLOR="' + textFrameBackColor + '" TEXT="Black" size="-1">');
    //	Win1.document.writeln('<div align="center"><font face="Arial"><b>');
    //	Win1.document.writeln(msgList[74] + LayerName[ActiveLayerIndex] + '. . .</b></font><br>');
    //	if (onlyUniqueSamples) {
    //		Win1.document.writeln('<font face="Arial" size="-1">' + msgList[75] + numberDataSamples + msgList[122] + '</font></div></body></html>');
    //	} else {
    //		Win1.document.writeln('<font face="Arial" size="-1">' + msgList[75] + numberDataSamples + msgList[76] + '</font></div></body></html>');
    //	}
    //	Win1.document.close();
    //	Win1=null;
    //
    var theText = writeFieldSample(theField);
    //alert(theText);
    sendToServer(imsQueryURL,
                 theText,
                 40);
}
// get the StoredQueries in the MapService
function getStoredQueries()
{
    if (checkIfActiveLayerAvailable())
    {
        var theString = '<ARCXML version="1.1">\n<REQUEST>\n<GET_SERVICE_INFO renderer="false" extensions="true" fields="false" />\n';
        theString +=
        '</REQUEST>\n</ARCXML>';
        sendToServer(imsQueryURL,
                     theString,
                     55);
    }
}
// get the StoredQueries in the MapService
function parseStoredQueries(theReply)
{
    //alert(theReply);
    storedQueryCount =
    0;
    storedQueryName.length =
    1;
    storedQueryString.length =
    1;
    storedQueryVariable.length =
    1;
    storedQueryVarCount.length =
    1;
    storedQueryFieldList.length =
    1;
    storedQueryIndex =
    0;
    var endpos = 1;
    var pos = -1;
    var blurb = 'id="'
        + ActiveLayer;
    var startpos = theReply.indexOf("<LAYERINFO",
                                    1);
    var thePos = 1;
    while (thePos
        != -1)
    {
        startpos =
        theReply.indexOf("<LAYERINFO",
                         1);
        endpos =
        theReply.indexOf(">",
                         startpos);
        thePos =
        theReply.indexOf(blurb,
                         1);
        if (thePos
            != -1)
        {
            pos =
            thePos;
            thePos =
            -1;
        }
    }
    var sqpos = 0;
    var sqvpos = 0;
    var sqpos2 = 0;
    var vCount = 0;
    if (pos
        != -1)
    {
        var fieldCount = 0;
        epos =
        theReply.indexOf("</LAYERINFO>",
                         pos);
        startpos =
        0;
        endpos =
        pos;
        sqpos =
        theReply.indexOf("<STOREDQUERY name=",
                         endpos);
        sqpos2 =
        theReply.indexOf("</STOREDQUERY>",
                         endpos);
        while ((epos
            > sqpos)
            && (sqpos
            != -1))
        {
            if (sqpos
                != -1)
            {
                startpos =
                sqpos
                    + 19;
                endpos =
                theReply.indexOf(dQuote,
                                 startpos);
                blurb =
                theReply.substring(startpos,
                                   endpos);
                //alert(blurb);
                storedQueryName[storedQueryCount] =
                blurb;
                //startpos = theReply.indexOf("<QUERY where=",endpos);
                var sp2 = theReply.indexOf("<QUERY ",
                                           endpos);
                sp2 +=
                7;
                startpos =
                theReply.indexOf("where=",
                                 sp2);
                startpos +=
                7;
                endpos =
                theReply.indexOf(dQuote,
                                 startpos);
                blurb =
                theReply.substring(startpos,
                                   endpos);
                storedQueryString[storedQueryCount] =
                blurb;
                startpos =
                theReply.indexOf("subfields=",
                                 sp2);
                startpos +=
                11;
                endpos =
                theReply.indexOf(dQuote,
                                 startpos);
                storedQueryFieldList[storedQueryCount] =
                theReply.substring(startpos,
                                   endpos);
                storedQueryVariable[storedQueryCount] =
                "";
                sqvpos =
                theReply.indexOf("<SQVAR",
                                 startpos);
                vCount =
                0;
                while ((sqvpos
                    != -1)
                    && (sqvpos
                    < sqpos2))
                {
                    endpos =
                    sqvpos;
                    startpos =
                    theReply.indexOf("name=",
                                     endpos);
                    startpos +=
                    6;
                    endpos =
                    theReply.indexOf(dQuote,
                                     startpos);
                    blurb =
                    theReply.substring(startpos,
                                       endpos);
                    if (vCount
                        > 0)
                    {
                        blurb =
                        "|"
                            + blurb;
                    }
                    storedQueryVariable[storedQueryCount] =
                    storedQueryVariable[storedQueryCount]
                        + blurb;
                    vCount +=
                    1;
                    sqvpos =
                    theReply.indexOf("<SQVAR",
                                     startpos);
                }
                storedQueryVarCount[storedQueryCount] =
                vCount;
                storedQueryCount +=
                1;
            }
            sqpos =
            theReply.indexOf("<STOREDQUERY name=",
                             endpos);
            sqpos2 =
            theReply.indexOf("</STOREDQUERY>",
                             sqpos);
        }
    }
    blurb =
    null;
    if (storedQueryCount
        == 0)
    {
        alert(LayerName[ActiveLayerIndex]
                  + msgList[77]);
    }
    else
    {
        storedQueryForm();
    }
}
// storedQuery Form
function storedQueryForm()
{
    if (checkIfActiveLayerAvailable())
    {
        writeStoredQueryForm(storedQueryIndex);
    }
}
function writeStoredQueryForm(theIndex)
{
    storedQueryIndex =
    theIndex;
    var url = appDir
        + "storedquery.htm";
    if (storedQueryVarCount
        == 0)
    {
        sendStoredQuery(theIndex,
                        "");
    }
    else
    {
        if (useTextFrame)
        {
            parent.TextFrame.document.location =
            url;
        }
        else
        {
            var Win1 = open(url,
                            "QueryWindow",
                            "width=575,height=150,scrollbars=yes,resizable=yes");
        }
    }
}
// create the querystring for storedQuery and send it
function sendStoredQuery(theIndex, theValue)
{
    showRetrieveData();
    var oldString = storedQueryString[theIndex];
    if (storedQueryVarCount[theIndex]
        > 0)
    {
        var var1 = storedQueryVariable[theIndex].split("|");
        var var2 = theValue.split("|");
        for (var i = 0; i
            < var1.length; i++)
        {
            oldString =
            swapStuff(oldString,
                      var1[i],
                      var2[i]);
        }
    }
    else
    {
        oldString =
        swapStuff(oldString,
                  storedQueryVariable[theIndex],
                  theValue);
    }
    //var newString = parseEntity(oldString);
    var newString = makeXMLsafe(oldString);
    //alert(newString);
    setQueryString =
    newString;
    selectionMode =
    1;
    var theString = writeQueryXML(newString);
    //alert("StoredQuery Request:\n" + theString);
    sendToServer(imsQueryURL,
                 theString,
                 queryXMLMode);
}
// see if there are storedQueries (Search)
function checkStoredQueries(theReply)
{
    var startpos = theReply.indexOf("<STOREDQUERY",
                                    1);
    if (startpos
        == -1)
    {
        //no storedqueries. . . do not display button
        useStoredQuery =
        false;
    }
}
function writeStartQueryXML(queryString, idAndShapeOnly)
{
    if (swapSelectFields)
    {
        selectFields =
        selFieldList[ActiveLayerIndex];
    }
    var theString = '<ARCXML VERSION="1.1">\n<REQUEST>\n<GET_FEATURES outputmode="xml" geometry="false" envelope="true" checkesc ="true"';
    theString +=
    ' compact="true" featurelimit="'
        + maxFeaturesReturned
        + '" beginrecord="0">\n';
    theString +=
    '<LAYER id="'
        + LayerID[ActiveLayerIndex]
        + '" />';
    if (idAndShapeOnly)
    {
        theString +=
        '<QUERY subfields="#ID# #SHAPE#" where="'
            + queryString
            + '" />';
    }
    else
    {
        theString +=
        '<QUERY subfields="'
            + selectFields
            + '" where="'
            + queryString
            + '" />';
    }
    theString +=
    '</GET_FEATURES>';
    theString +=
    '</REQUEST>';
    theString +=
    '</ARCXML>';
    //alert(theString);
    return theString;
}
function setStartQuery()
{
    var theString = writeStartQueryXML(highlightedOne,
                                       true);
    sendToServer(imsQueryURL,
                 theString,
                 20);
}
function parseStartQuery(theReply)
{
    //alert(theReply);
    var theError = getXMLErrorMessage(theReply);
    if (theError
        == "")
    {
        if (queryZoom)
        {
            getXYs(theReply);
            startLeft =
            eLeft;
            startTop =
            eTop;
            startRight =
            eRight;
            startBottom =
            eBottom;
            var fWidth = eRight
                - eLeft;
            var fHeight = eTop
                - eBottom;
            var mWMargin = 0;
            var mHMargin = 0;
            //alert(LayerType[ActiveLayerIndex]);
            if (LayerType[ActiveLayerIndex]
                == "point")
            {
                selectType =
                "point";
                //alert("fullWidth=" + fullWidth + "\nfullHeight=" + fullHeight);
                mWMargin =
                fullWidth
                    * selectPointMargin;
                mHMargin =
                fullHeight
                    * selectPointMargin;
            }
            else
            {
                mWMargin =
                fWidth
                    * selectMargin;
                mHMargin =
                fHeight
                    * selectMargin;
            }
            //alert("mWMargin=" + mWMargin + "\nmHMargin=" + mHMargin);
            eLeft =
            eLeft
                - mWMargin;
            eRight =
            eRight
                + mWMargin;
            eTop =
            eTop
                + mHMargin;
            eBottom =
            eBottom
                - mHMargin;
            startLeft =
            eLeft;
            startTop =
            eTop;
            startRight =
            eRight;
            startBottom =
            eBottom;
        }
        sendMapXML();
    }
    else
    {
        alert(theError);
    }
}



