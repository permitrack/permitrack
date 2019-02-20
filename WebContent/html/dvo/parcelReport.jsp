<%@ page import="com.sehinc.common.db.client.DvClientInformation" %>
<%@ page import="com.sehinc.dataview.DataViewConstants" %>
<%@ taglib prefix="html"
           uri="/WEB-INF/tld/struts-html.tld" %>
<html>

<%
    DvClientInformation
            clientInfo =
            (DvClientInformation) session.getAttribute(DataViewConstants.DATAVIEW_CLIENT_INFORMATION);
    if (clientInfo
        == null)
    {
%>
    <body>
        Your login session has timed out due to inactivity. Please return to the main DataView screen and login again.
            <%
    }
    else
    {
%>
        <head>
            <title>DataViewOnline Parcel Report</title>
            <link rel="stylesheet"
                  type="text/css"
                  href="<html:rewrite module="/" page='/css/dvo.css' />" />
            <script type="text/javascript"
                    language="javascript">
                if (opener)
                {
                    t
                            = opener.parent.MapFrame;
                    t2
                            = "opener.parent.MapFrame";
                }
                else
                {
                    t
                            = parent.MapFrame;
                    t2
                            = "parent.MapFrame";
                }
                var addressTableFields =
                        [
                            [
                                "Parcel ID",
                                "PIN",
                                "&nbsp;"
                            ],
                            [
                                "House Number",
                                "SEH_HSE",
                                "&nbsp;"
                            ],
                            [
                                "Street",
                                "SEH_STR",
                                "&nbsp;"
                            ],
                            [
                                "City",
                                "SEH_CITY",
                                "&nbsp;"
                            ],
                            [
                                "Owner Name 1",
                                "SEH_NAME1",
                                "&nbsp;"
                            ]
                        ];
                var parcelReportFields =
                        [
                            [
                                "Parcel ID",
                                "PIN",
                                "&nbsp;"
                            ],
                            [
                                "House Number",
                                "SEH_HSE",
                                "&nbsp;"
                            ],
                            [
                                "Unit",
                                "SEH_UNIT",
                                "&nbsp;"
                            ],
                            [
                                "Street",
                                "SEH_STR",
                                "&nbsp;"
                            ],
                            [
                                "City",
                                "SEH_CITY",
                                "&nbsp;"
                            ],
                            [
                                "Zip",
                                "SEH_ZIP",
                                "&nbsp;"
                            ],
                            [
                                "Owner Name 1",
                                "SEH_NAME1",
                                "&nbsp;"
                            ],
                            [
                                "Owner Name 2",
                                "SEH_NAME2",
                                "&nbsp;"
                            ],
                            [
                                "Owner Street Address",
                                "SEH_ADDR",
                                "&nbsp;"
                            ],
                            [
                                "Owner City/State/Zip",
                                "SEH_ADDR2",
                                "&nbsp;"
                            ],
                            [
                                "Deeded Acres",
                                "SEH_ACRES",
                                "&nbsp;"
                            ],
                            [
                                "Tax Description",
                                "SEH_LEGAL",
                                "&nbsp;"
                            ],
                            [
                                "Plat Name",
                                "SEH_PLAT",
                                "&nbsp;"
                            ],
                            [
                                "Market Value Land",
                                "SEH_VLAND",
                                "&nbsp;"
                            ],
                            [
                                "Market Value Building",
                                "SEH_VIMP",
                                "&nbsp;"
                            ],
                            [
                                "Market Value Total",
                                "SEH_VALL",
                                "&nbsp;"
                            ],
                            [
                                "Homestead Description",
                                "SEH_HMSTD",
                                "&nbsp;"
                            ],
                            [
                                "Year Built",
                                "SEH_BUILT",
                                "&nbsp;"
                            ]
                        ];
                function loadParcelData(theReply, t)
                {
                    endpos
                            = t.xmlEndPos;
                    var selectedData = t.parseRecordString(theReply,
                                                           endpos);
                    epos
                            = theReply.indexOf("</FEATURE",
                                               endpos);
                    var fName1 = t.getFieldNames(selectedData);
                    var fValue1 = t.getFieldValues(selectedData);
                    var fields = "";
                    for (var f = 0; f
                            < fName1.length; f++)
                    {
                        //Fill In Required Fields
                        fields
                                = fields
                                          + ", "
                                + fName1[f];
                        for (i
                                     = 0; i
                                < parcelReportFields.length; i++)
                        {
                            attribute
                                    = parcelReportFields[i];
                            if (fName1[f]
                                    == attribute[1])
                            {
                                attribute[2]
                                        = fValue1[f];
                            } else if (attribute[1]
                                               == 'SEH_LEGAL'
                                    && fName1[f].indexOf('SEH_LEGAL')
                                    >= 0)
                            {
                                attribute[2]
                                        += '<br>'
                                        + fValue1[f];
                            }
                        }
                        for (i
                                     = 0; i
                                < addressTableFields.length; i++)
                        {
                            attribute
                                    = addressTableFields[i];
                            if (fName1[f]
                                    == attribute[1])
                            {
                                attribute[2]
                                        = fValue1[f];
                            }
                        }
                    }
                    fName1
                            = null;
                    fValue1
                            = null;
                }
                function addReportRow(parent, heading, value)
                {
                    var row = document.createElement('tr');
                    var head = document.createElement('th');
                    head.innerHTML
                            = heading;
                    row.appendChild(head);
                    var cell = document.createElement('td');
                    cell.innerHTML
                            = value;
                    row.appendChild(cell);
                    parent.appendChild(row);
                }
                function init(theReply, t)
                {
                    loadParcelData(theReply,
                                   t);
                    document.getElementById("mainMapImg").src
                            = opener.printMapURL;
                    document.getElementById("logoImage").src
                            = opener.logoURL;
                    var table = document.getElementById('reportFields');
                    for (i
                                 = 0; i
                            < parcelReportFields.length; i++)
                    {
                        addReportRow(table,
                                     parcelReportFields[i][0],
                                     parcelReportFields[i][2]);
                    }
                    table
                            = document.getElementById('addressTable');
                    for (i
                                 = 0; i
                            < addressTableFields.length; i++)
                    {
                        addReportRow(table,
                                     addressTableFields[i][0],
                                     addressTableFields[i][2]);
                    }
                    stripe("table1");
                    stripe("table2");
                }
                /*
                 this function is needed to work around
                 a bug in IE related to element attributes
                 */
                function hasClass(obj)
                {
                    var result = false;
                    if (obj.getAttributeNode("class")
                            != null)
                    {
                        result
                                = obj.getAttributeNode("class").value;
                    }
                    return result;
                }
                function stripe(id)
                {
                    var even = false;
                    var evenColor = arguments[1]
                            ? arguments[1]
                            : "#fff";
                    var oddColor = arguments[2]
                            ? arguments[2]
                            : "#D8E6FE";
                    // obtain a reference to the desired table
                    // if no such table exists, abort
                    var table = document.getElementById(id);
                    if (!table)
                    {
                        return;
                    }
                    var tbodies = table.getElementsByTagName("tbody");
                    for (var h = 0; h
                            < tbodies.length; h++)
                    {
                        var trs = tbodies[h].getElementsByTagName("tr");
                        for (var i = 0; i
                                < trs.length; i++)
                        {
                            // avoid rows that have a class attribute
                            // or backgroundColor style
                            if (!hasClass(trs[i])
                                    && !trs[i].style.backgroundColor)
                            {
                                // get all the cells in this row...
                                var tds = trs[i].getElementsByTagName("td");
                                // and iterate through them...
                                for (j
                                             = 0; j
                                        < tds.length; j++)
                                {
                                    mytd
                                            = tds[j];
                                    if (!hasClass(mytd)
                                            && !mytd.style.backgroundColor)
                                    {
                                        mytd.style.backgroundColor
                                                = even
                                                ? evenColor
                                                : oddColor;
                                    }
                                }
                                tds
                                        = trs[i].getElementsByTagName("th");
                                // and iterate through them...
                                for (j
                                             = 0; j
                                        < tds.length; j++)
                                {
                                    mytd
                                            = tds[j];
                                    if (!hasClass(mytd)
                                            && !mytd.style.backgroundColor)
                                    {
                                        mytd.style.backgroundColor
                                                = even
                                                ? evenColor
                                                : oddColor;
                                    }
                                }
                            }
                            // flip from odd to even, or vice-versa
                            even
                                    = !even;
                        }
                    }
                }
            </script>
            <style type="text/css">
                body
                {
                    font-family: verdana, sans-serif;
                    font-size: 10pt;
                    width: 662px;
                    height: 900px;
                }

                    /*
                                    #mapTitle
                                    {
                                        text-align: center;
                                        font-size: large
                                    }

                                    #mapComments
                                    {
                                        text-align: center;
                                        height: 1.1em;
                                        overflow: hidden
                                    }
                    */

                #mapImage
                {
                    position: absolute;
                    left: 2px;
                    top: 2px;
                    width: 395px;
                }

                #sidebar
                {
                    position: relative;
                    left: 402px;
                    top: 2px;
                    width: 263px;
                    height: 390px;
                    border: solid 2px black;
                    font-size: 12pt;
                }

                #addressTableDiv
                {
                    position: absolute;
                    top: 127px;
                    width: 250px;
                    margin: 2px;
                    font-size: 12pt;
                }

                #addressTable
                {
                    font-size: 12pt;
                }

                #mapDisclaimer
                {
                    clear: both;
                    width: 230px;
                    margin: 2px;
                    font-size: 8pt
                }

                #footer
                {
                    position: absolute;
                    top: 402px;
                    clear: both;
                    margin: 4px;
                    width: 670px;
                }

                #top
                {
                    position: absolute;
                    width: 670px;
                    height: 398px;
                    border: solid 2px black;
                    margin: 2px;
                }

                #logo
                {
                    position: relative;
                    left: 2px;
                    top: 2px;
                    width: 100px;
                }

                #clientName
                {
                    position: absolute;
                    width: 135px;
                    left: 140px;
                    top: 5px;
                    font-size: 12pt;
                    text-align: center;
                    font-weight: bold;
                }

                hr
                {
                    width: 255px;
                    text-align: center;
                }

                th
                {
                    text-align: left;
                }

                    /*
                                    div.NoLegend
                                    {
                                        display: none;
                                    }
                    */
            </style>
        </head>
        <body bgcolor="#ffffff"
              TEXT="Black">
            <!--test-->
            <div id="top">
                <div id="mapImage">
                    <IMG border="2"
                         SRC="./images/map.gif"
                         alt=""
                         id="mainMapImg">
                </div>
                <div id="sidebar">
                    <div id="logo">
                        <IMG SRC="./images/newsehlogo.jpg"
                             border="0"
                             alt=""
                             id="logoImage">
                    </div>
                    <div id="clientName">
                        <script type="text/javascript">document.write(opener.theClientName);</script>
                        <p>Parcel Information Report</p>
                    </div>
                    <div id="addressTableDiv">
                        <hr>
                        <center>
                            <table border="0"
                                   cellspacing="0"
                                   cellpadding="0"
                                   id="table1"
                                   width="250px">
                                <tbody id="addressTable"></tbody>
                            </table>
                        </center>
                        <hr>
                        <div id="mapDisclaimer">
                            Disclaimer: This map is neither a legally recorded map nor a survey and is not intended to be used as one. This map is a compilation of records, information and data from city, county, state and federal offices, and is to be used for reference purposes only.
                        </div>
                    </div>
                </div>
            </div>
            <div id="footer">
                <table width="100%"
                       border="0"
                       cellspacing="0"
                       cellpadding="0"
                       id="table2">
                    <tbody id="reportFields">
                        <tr>
                            <td>
                                <script type="text/javascript">document.write(opener.theClientName);</script>
                            </td>
                            <td onClick="window.close()">
                                <a>Search</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <% } %>
        </body>
</html>