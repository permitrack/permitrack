// aimsDHTML.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js,
 *		aimsLayers.js
 */
aimsDHTMLPresent =
true;
/*
 ***************************************************************************************

 DHTML layer functions

 ***************************************************************************************
 */

// Create a DHTML layer
function createLayer(name, inleft, intop, width, height, visible, content)
{
    var layer;
    if (isNav4)
    {
        document.writeln('<layer name="'
                             + name
                             + '" left='
                             + inleft
                             + ' top='
                             + intop
                             + ' width='
                             + width
                             + ' height='
                             + height
                             + ' visibility='
                             + (visible
            ? '"show"'
            : '"hide"')
                             + '>');
        document.writeln(content);
        document.writeln('</layer>');
    }
    else
    {
        document.writeln('<div id="'
                             + name
                             + '" style="position:absolute; overflow:hidden; left:'
                             + inleft
                             + 'px; top:'
                             + intop
                             + 'px; width:'
                             + width
                             + 'px; height:'
                             + height
                             + 'px;'
                             + '; z-index:1; visibility:'
                             + (visible
            ? 'visible;'
            : 'hidden;')
                             + '">');
        document.writeln(content);
        document.writeln('</div>');
    }
}
// get the layer object called "name"
function getLayer(name)
{
    if (isNav4)
    {
        return(document.layers[name]);
    } else if (isIE4)
    {
        layer =
        eval('document.all.'
                 + name
                 + '.style');
        return(layer);
    } else if (is5up)
    {
        var theObj = document.getElementById(name);
        return theObj.style
    }
    else
    {
        return(null);
    }
}
// get the layer object called "name" if in OverviewFrame
function getOVLayer(name)
{
    if (parent.OverviewFrame
        != null)
    {
        if (isNav4)
        {
            return(parent.OverviewFrame.document.layers[name]);
        } else if (isIE4)
        {
            layer =
            eval('parent.OverviewFrame.document.all.'
                     + name
                     + '.style');
            return(layer);
        } else if (is5up)
        {
            var theObj = parent.OverviewFrame.document.getElementById(name);
            return theObj.style
        }
        else
        {
            return(null);
        }
    }
    else
    {
        return getLayer(name);
    }
}
function isVisible(name)
{
    var layer = getLayer(name);
    if (isNav
        && layer.visibility
        == "show")
    {
        return(true);
    }
    if (isIE
        && layer.visibility
        == "visible")
    {
        return(true);
    }
    return(false);
}
// move layer to x,y
function moveLayer(name, x, y)
{
    var layer = getLayer(name);
    if (isNav4)
    {
        layer.moveTo(x,
                     y);
    }
    //if (document.all) {
    else
    {
        layer.left =
        x
            + "px";
        layer.top =
        y
            + "px";
    }
}
// set layer background color
function setLayerBackgroundColor(name, color)
{
    var layer = getLayer(name);
    if (isNav4)
    {
        layer.bgColor =
        color;
    }
    //else if (document.all)
    else
    {
        layer.backgroundColor =
        color;
    }
}
// toggle layer to invisible
function hideLayer(name)
{
    var layer = getLayer(name);
    if (isNav4)
    {
        layer.visibility =
        "hide";
    }
    //if (document.all)
    else
    {
        layer.visibility =
        "hidden";
    }
    //layer.display="none";
}
// toggle layer to visible
function showLayer(name)
{
    var layer = getLayer(name);
    if (isNav4)
    {
        layer.visibility =
        "show";
    }
    //if (document.all)
    else
    {
        layer.visibility =
        "visible";
    }
    //layer.display="block";
}
// toggle Overview layer to visible - if in OverviewFrame
function showOVLayer(name)
{
    var layer = getOVLayer(name);
    if (isNav4)
    {
        layer.visibility =
        "show";
    }
    //if (document.all)
    else
    {
        layer.visibility =
        "visible";
    }
    //layer.display="block";
}
// toggle Overview layer to invisible - if in OverviewFrame
function hideOVLayer(name)
{
    var layer = getOVLayer(name);
    if (isNav4)
    {
        layer.visibility =
        "hide";
    }
    //if (document.all)
    else
    {
        layer.visibility =
        "hidden";
    }
    //layer.display="block";
}
// clip layer display to clipleft, cliptip, clipright, clipbottom
// Not working with Mozilla Milestone 12 (Nav5)
function clipLayer2(name, clipleft, cliptop, clipright, clipbottom)
{
    var layer = getLayer(name);
    if (isNav4)
    {
        layer.clip.left =
        clipleft;
        layer.clip.top =
        cliptop;
        layer.clip.right =
        clipright;
        layer.clip.bottom =
        clipbottom;
    }
    //if (document.all)
    else if (isIE)
    {
        layer.clip =
        'rect('
            + cliptop
            + ' '
            + clipright
            + ' '
            + clipbottom
            + ' '
            + clipleft
            + ')';
    }
}
function clipLayer(name, clipleft, cliptop, clipright, clipbottom)
{
    var layer = getLayer(name);
    if (isNav4)
    {
        layer.clip.left =
        clipleft;
        layer.clip.top =
        cliptop;
        layer.clip.right =
        clipright;
        layer.clip.bottom =
        clipbottom;
    }
    else
    {
        //layer.clip = 'rect(' + cliptop + ' ' +  clipright + ' ' + clipbottom + ' ' + clipleft +')';
        var newWidth = clipright
            - clipleft;
        var newHeight = clipbottom
            - cliptop;
        layer.height =
        newHeight;
        layer.width =
        newWidth;
        //var theTop = parseInt((parseFloat(cliptop) * 10 + 0.5)/10);
        //var theLeft = parseInt((parseFloat(clipleft) * 10 + 0.5)/10);
        //alert(cliptop + " " + clipleft);
        layer.top =
        cliptop
            + "px";
        layer.left =
        clipleft
            + "px";
        //layer.display= "none";
    }
}
// clip layer in OverviewFrame
function clipOVLayer(name, clipleft, cliptop, clipright, clipbottom)
{
    var layer = getLayer(name);
    if (parent.OverviewFrame
        != null)
    {
        layer =
        getOVLayer(name);
    }
    if (isNav4)
    {
        layer.clip.left =
        clipleft;
        layer.clip.top =
        cliptop;
        layer.clip.right =
        clipright;
        layer.clip.bottom =
        clipbottom;
    }
    else
    {
        //layer.clip = 'rect(' + cliptop + ' ' +  clipright + ' ' + clipbottom + ' ' + clipleft +')';
        if ((clipright.isNaN)
                || (clipleft.isNaN)
                || (cliptop.isNaN)
            || (clipbottom.isNaN))
        {
            layer.visibility =
            "hidden";
        }
        else
        {
            var newWidth = clipright
                - clipleft;
            var newHeight = clipbottom
                - cliptop;
            if ((newWidth
                <= 0)
                || (newHeight
                <= 0))
            {
                layer.visibility =
                "hidden";
            }
            else
            {
                //if (newWidth.isNaN) newWidth = parseInt(i2Width);
                //if (newHeight.isNaN) newHeight = parseInt(i2Height);
                //alert(newWidth + "," + newHeight);
                layer.height =
                newHeight;
                layer.width =
                newWidth;
                //var theTop = parseInt((parseFloat(cliptop) * 10 + 0.5)/10);
                //var theLeft = parseInt((parseFloat(clipleft) * 10 + 0.5)/10);
                //alert(cliptop + " " + clipleft);
                layer.top =
                cliptop
                    + "px";
                layer.left =
                clipleft
                    + "px";
                //layer.display= "none";
                layer.visibility =
                "visible";
            }
        }
    }
}
// replace layer's content with new content
// not working with Mozilla Milestone 12 (Nav5)
function replaceLayerContent(name, content)
{
    if (isNav4)
    {
        var layer = getLayer(name);
        layer.document.open();
        layer.document.writeln(content);
        layer.document.close();
    } else if (isIE)
    {
        var str = "document.all."
                      + name
                      + ".innerHTML = '"
                      + content
            + "'";
        eval(str);
    }
}
/*
 // get window width
 function getWinWidth() {
 if (isNav)
 return(window.innerWidth);
 else if (isIE)
 return(document.body.clientWidth);
 else
 return(null);
 }

 // get window height
 function getWinHeight() {
 if (isNav)
 return(window.innerHeight);
 else if (isIE)
 return(document.body.clientHeight);
 else
 return(null);
 }
 */

// toggle Overview Map display
function toggleOVMap()
{
    if (imsURL
        != "")
    {
        ovIsVisible =
        !ovIsVisible;
        if (ovIsVisible)
        {
            //showRetrieveMap();
            //var theString = writeOVXML();
            putExtentOnOVMap();
            showLayer("ovLayer");
            showLayer("ovShadow");
        }
        else
        {
            hideLayer("ovLayer");
            hideLayer("ovShadow");
            hideLayer("zoomOVBoxTop");
            hideLayer("zoomOVBoxLeft");
            hideLayer("zoomOVBoxRight");
            hideLayer("zoomOVBoxBottom");
            //if (!isNav4) {
            //	hideLayer("ovBox");
            //}
        }
        if (isIE)
        {
            document.all.theTop.style.cursor =
            theCursor;
        }
    }
    else
    {
        alert(msgList[45]);
    }
}
// plot extent box on overview map - only for Overview if in OverviewFrame
function putExtentOnOVMap()
{
    var ovXincre = fullOVWidth
        / i2Width;
    var ovYincre = fullOVHeight
        / i2Height;
    var vleft = (eLeft
        - fullOVLeft)
                    / ovXincre
        + ovBorderWidth;
    var vright = (eRight
        - fullOVLeft)
                     / ovXincre
        + ovBorderWidth;
    var vtop = (fullOVTop
        - eTop)
                   / ovYincre
        + ovBorderWidth;
    var vbottom = (fullOVTop
        - eBottom)
                      / ovYincre
        + ovBorderWidth;
    //if (vleft<ovHspc) vleft = ovHspc;
    //if (vtop<ovVspc) vtop = ovVspc;
    if (vright
        > i2Width
        + ovBorderWidth)
    {
        vright =
        i2Width;
    }
    if (vbottom
        > i2Height
        + ovBorderWidth)
    {
        vbottom =
        i2Height;
    }
    if (!isNav4)
    {
        vleft =
        vleft
            + ovHspc;
        vright =
        vright
            + ovHspc;
        vtop =
        vtop
            + ovVspc;
        vbottom =
        vbottom
            + ovVspc;
    }
    if (eTop
        > fullOVBottom)
    {
        clipOVLayer("zoomOVBoxTop",
                    vleft,
                    vtop
                        - ovExtentBoxSize,
                    vright
                        + 1,
                    vtop);
        showOVLayer("zoomOVBoxTop");
    }
    else
    {
        hideOVLayer("zoomOVBoxTop");
    }
    if (eLeft
        < fullOVRight)
    {
        clipOVLayer("zoomOVBoxLeft",
                    vleft
                        - ovExtentBoxSize,
                    vtop
                        - ovExtentBoxSize,
                    vleft,
                    vbottom);
        showOVLayer("zoomOVBoxLeft");
    }
    else
    {
        hideOVLayer("zoomOVBoxLeft");
    }
    if (eRight
        > fullOVLeft)
    {
        clipOVLayer("zoomOVBoxRight",
                    vright,
                    vtop
                        - ovExtentBoxSize,
                    vright
                        + ovExtentBoxSize,
                    vbottom);
        showOVLayer("zoomOVBoxRight");
    }
    else
    {
        hideOVLayer("zoomOVBoxRight");
    }
    if (eBottom
        < fullOVTop)
    {
        clipOVLayer("zoomOVBoxBottom",
                    vleft,
                    vbottom
                        - ovExtentBoxSize,
                    vright
                        + 1,
                    vbottom);
        showOVLayer("zoomOVBoxBottom");
    }
    else
    {
        hideOVLayer("zoomOVBoxBottom");
    }
}
function boxIt(theLeft, theTop, theRight, theBottom)
{
    if (!isNav4)
    {
        theTop =
        theTop
            + vspc;
        theBottom =
        theBottom
            + vspc;
        theLeft =
        theLeft
            + hspc;
        theRight =
        theRight
            + hspc;
    }
    clipLayer("zoomBoxTop",
              theLeft,
              theTop,
              theRight,
              theTop
                  + ovBoxSize);
    clipLayer("zoomBoxLeft",
              theLeft,
              theTop,
              theLeft
                  + ovBoxSize,
              theBottom);
    clipLayer("zoomBoxRight",
              theRight
                  - ovBoxSize,
              theTop,
              theRight,
              theBottom);
    clipLayer("zoomBoxBottom",
              theLeft,
              theBottom
                  - ovBoxSize,
              theRight,
              theBottom);
    showLayer("zoomBoxTop");
    showLayer("zoomBoxLeft");
    showLayer("zoomBoxRight");
    showLayer("zoomBoxBottom");
}

