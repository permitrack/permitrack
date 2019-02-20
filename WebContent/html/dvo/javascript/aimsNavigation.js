// aimsNavigation.js
/*
 *  JavaScript template file for ArcIMS HTML Viewer
 *		dependent on aimsXML.js, ArcIMSparam.js, aimsCommon.js, aimsMap.js,
 *		aimsLayers.js, aimsDHTML.js
 *		aimsClick.js
 */
aimsNavigationPresent =
true;
/*
 ***************************************************************************************

 Map Navigation functions - Zoom , Pan, etc.

 ***************************************************************************************
 */

// convert mouse click xy's into map coordinates
function getMapXY(xIn, yIn)
{
    mouseX =
    xIn;
    pixelX =
    xDistance
        / iWidth;
    mapX =
    pixelX
        * mouseX
        + eLeft;
    mouseY =
    iHeight
        - yIn;
    pixelY =
    yDistance
        / iHeight;
    mapY =
    pixelY
        * mouseY
        + eBottom;
}
function getImageXY(e)
{
    if (isNav)
    {
        mouseX =
        e.pageX;
        mouseY =
        e.pageY;
    }
    else
    {
        mouseX =
        event.clientX
            + document.body.scrollLeft;
        mouseY =
        event.clientY
            + document.body.scrollTop;
    }
    // subtract offsets from page left and top
    mouseX =
    mouseX
        - hspc;
    mouseY =
    mouseY
        - vspc;
}
function getOVImageXY(e)
{
    if (isNav)
    {
        mouseX =
        e.pageX;
        mouseY =
        e.pageY;
    }
    else
    {
        mouseX =
        event.clientX
            + document.body.scrollLeft;
        mouseY =
        event.clientY
            + document.body.scrollTop;
    }
    // subtract offsets from page left and top
    mouseX =
    mouseX
        - ovHspc;
    mouseY =
    mouseY
        - ovVspc;
}
// get coordinates on ov map and reset display
function ovMapClick(x, y)
{
    var ovWidth = i2Width;
    var ovHeight = i2Height;
    var ovXincre = fullOVWidth
        / ovWidth;
    var ovYincre = fullOVHeight
        / ovHeight;
    var ovX = x;
    var ovY = ovHeight
        - y;
    var ovmapX = ovX
                     * ovXincre
        + fullOVLeft;
    var ovmapY = ovY
                     * ovYincre
        + fullOVBottom;
    saveLastExtent();
    eLeft =
    ovmapX
        - xHalf;
    eRight =
    ovmapX
        + xHalf;
    eTop =
    ovmapY
        + yHalf;
    eBottom =
    ovmapY
        - yHalf;
    //alert(ovmapX + "," +  ovmapY + "\nLeft:" + fullLeft + "\nTop:" + fullTop + "\nRight:" + fullRight + "\nBottom:" + fullBottom);
    //var theString = writeXML();
    sendMapXML();
}
// get click on OVmap and move display there
function ovMap2Click(e)
{
    getOVImageXY(e);
    mouseY =
    mouseY
        - ovBorderWidth;
    zooming =
    false;
    panning =
    false;
    selectBox =
    false;
    ovMapClick(mouseX,
               mouseY);
}
// zoom in around mouse click
function zoomin(e)
{
    getMapXY(mouseX,
             mouseY);
    var tempLeft = lastLeft;
    var tempRight = lastRight;
    var tempTop = lastTop;
    var tempBottom = lastBottom;
    saveLastExtent();
    eLeft =
    mapX
        - (xHalf
        / zoomFactor);
    eRight =
    mapX
        + (xHalf
        / zoomFactor);
    eTop =
    mapY
        + (yHalf
        / zoomFactor);
    eBottom =
    mapY
        - (yHalf
        / zoomFactor);
    sendMapXML();
}
// zoom out from mouse click
function zoomout(e)
{
    getMapXY(mouseX,
             mouseY);
    var tempLeft = lastLeft;
    var tempRight = lastRight;
    var tempTop = lastTop;
    var tempBottom = lastBottom;
    saveLastExtent();
    eLeft =
    mapX
        - (xDistance
               * zoomFactor
        / 2);
    eRight =
    mapX
        + (xDistance
               * zoomFactor
        / 2);
    eTop =
    mapY
        + (yDistance
               * zoomFactor
        / 2);
    eBottom =
    mapY
        - (yDistance
               * zoomFactor
        / 2);
    if (enforceFullExtent)
    {
        if ((eRight
            - eLeft)
            > fullWidth)
        {
            eLeft =
            fullLeft;
            eRight =
            fullRight;
            eTop =
            fullTop;
            eBottom =
            fullBottom;
            lastLeft =
            tempLeft;
            lastRight =
            tempRight;
            lastTop =
            tempTop;
            lastBottom =
            tempBottom;
        }
    }
    sendMapXML();
}
// get the coords at mouse position
function getMouse(e)
{
    window.status =
    "";
    getImageXY(e);
    if (isIE)
    {
        if ((hasOVMap)
                && (ovIsVisible)
            && (ovMapIsLayer))
        {
            if ((mouseX
                < i2Width
                + 2)
                && (mouseY
                < i2Height))
            {
                document.all.theTop.style.cursor =
                "default";
            }
            else
            {
                document.all.theTop.style.cursor =
                theCursor;
            }
        }
    }
    //if ((mouseX < (i2Width + ovBoxSize)) && (mouseY < (i2Height + ovBoxSize))) {onOVArea=true} else {onOVArea=false}
    if ((mouseX
        > iWidth)
            || (mouseY
        > iHeight)
            || (mouseX
        <= 0)
            || (mouseY
        <= 0)
        || ((hasOVMap)
                && (ovIsVisible)
                && (mouseX
        < i2Width
        + ovBoxSize)
                && (mouseY
        < i2Height
        + ovBoxSize)
        && (ovMapIsLayer)))
    {
        chkMouseUp(e);
    }
    else
    {
        mouseStuff();
    }
    if (activate_rb)
    {
        drawRubberband(e);
    }
    //if ((panning) || (zooming) || (selectBox))
    return false;
    //else
    //	return true;
}
function LatLon()
{
    if ((zooming)
        || (selectBox))
    {
        x2 =
        mouseX;
        y2 =
        mouseY;
        setClip();
    } else if (panning)
    {
        x2 =
        mouseX;
        y2 =
        mouseY;
        panMouse();
    }
    pixelX =
    xDistance
        / iWidth;
    mapX =
    pixelX
        * mouseX
        + eLeft;
    var theY = iHeight
        - mouseY;
    pixelY =
    yDistance
        / iHeight;
    mapY =
    pixelY
        * theY
        + eBottom;
    if (toolMode
        == 20)
    {
        calcDistance(mapX,
                     mapY);
    } else if (showXYs)
    {
        var u = Math.pow(10,
                         numDecimals);
        var uX = parseInt(mapX
                              * u
                              + (5
            / 10))
            / u
        var uY = parseInt(mapY
                              * u
                              + (5
            / 10))
            / u
        var mouseString = msgList[52]
                              + uX
                              + " , "
                              + uY
                              + " -- "
                              + msgList[53]
                              + mouseX
                              + " , "
            + mouseY;
        if (showScalePercent)
        {
            mouseString =
            mouseString
                + " -- "
                + msgList[54]
                + mapScaleFactor;
        }
        //if (onOVArea) {mouseString += " -- On OV Map Area [" + i2Width + "x" + i2Height + "]"} else {mouseString += " -- Off OV Map Area [" + i2Width + "x" + i2Height + "]"}
        //window.status = mouseString;
        //parent.ScaleFrame.document.forms['mapcoords'].elements[0].value = uX;
        //parent.ScaleFrame.document.forms['mapcoords'].elements[1].value = uY;
        alert(uX
                  + " - "
                  + uY);
    }
}
function mouseStuff()
{
    if ((zooming)
        || (selectBox))
    {
        x2 =
        mouseX;
        y2 =
        mouseY;
        setClip();
    } else if (panning)
    {
        x2 =
        mouseX;
        y2 =
        mouseY;
        panMouse();
    }
    pixelX =
    xDistance
        / iWidth;
    mapX =
    pixelX
        * mouseX
        + eLeft;
    var theY = iHeight
        - mouseY;
    pixelY =
    yDistance
        / iHeight;
    mapY =
    pixelY
        * theY
        + eBottom;
    if (toolMode
        == 20)
    {
        calcDistance(mapX,
                     mapY);
    } else if (showXYs)
    {
        var u = Math.pow(10,
                         numDecimals);
        var uX = parseInt(mapX
                              * u
                              + (5
            / 10))
            / u
        var uY = parseInt(mapY
                              * u
                              + (5
            / 10))
            / u
        var mouseString = msgList[52]
                              + uX
                              + " , "
                              + uY
                              + " -- "
                              + msgList[53]
                              + mouseX
                              + " , "
            + mouseY;
        if (showScalePercent)
        {
            mouseString =
            mouseString
                + " -- "
                + msgList[54]
                + mapScaleFactor;
        }
        //if (onOVArea) {mouseString += " -- On OV Map Area [" + i2Width + "x" + i2Height + "]"} else {mouseString += " -- Off OV Map Area [" + i2Width + "x" + i2Height + "]"}
        //window.status = mouseString;
        parent.ScaleFrame.document.forms['mapcoords'].elements[0].value =
        uX;
        parent.ScaleFrame.document.forms['mapcoords'].elements[1].value =
        uY;
    }
}
// start zoom in.... box displayed
function startZoomBox(e)
{
    moveLayer("theMap",
              hspc,
              vspc);
    getImageXY(e);
    // keep it within the MapImage
    if ((mouseX
        < iWidth)
        && (mouseY
        < iHeight))
    {
        if (zooming)
        {
            stopZoomBox(e);
        }
        else
        {
            x1 =
            mouseX;
            y1 =
            mouseY
            x2 =
            x1
                + 1;
            y2 =
            y1
                + 1;
            zleft =
            x1;
            ztop =
            y1;
            zbottom =
            y1;
            zright =
            x1
            boxIt(x1,
                  y1,
                  x2,
                  y2);
            zooming =
            true;
            /*
             if (isNav4) {
             showLayer("zoomBoxTop");
             showLayer("zoomBoxLeft");
             showLayer("zoomBoxRight");
             showLayer("zoomBoxBottom");
             } else {
             showLayer("zoomBox");
             }
             */
        }
    }
    return false;
}
// stop zoom box display... zoom in
function stopZoomBox(e)
{
    zooming =
    false;
    //if (isNav4) {
    hideLayer("zoomBoxTop");
    hideLayer("zoomBoxLeft");
    hideLayer("zoomBoxRight");
    hideLayer("zoomBoxBottom");
    //} else {
    //	hideLayer("zoomBox");
    //}
    if ((zright
        < zleft
        + 2)
        && (zbottom
        < ztop
        + 2))
    {
        zoomin(e);
    }
    else
    {
        var tempLeft = lastLeft;
        var tempRight = lastRight;
        var tempTop = lastTop;
        var tempBottom = lastBottom;
        saveLastExtent();
        pixelX =
        xDistance
            / iWidth;
        var theY = iHeight
            - ztop;
        pixelY =
        yDistance
            / iHeight;
        eTop =
        pixelY
            * theY
            + eBottom;
        eRight =
        pixelX
            * zright
            + eLeft;
        eLeft =
        pixelX
            * zleft
            + eLeft;
        theY =
        iHeight
            - zbottom;
        pixelY =
        yDistance
            / iHeight;
        eBottom =
        pixelY
            * theY
            + eBottom;
        window.scrollTo(0,
                        0);
        //var theString = writeXML();
        sendMapXML();
    }
    return true;
}
// start zoom out... box displayed
function startZoomOutBox(e)
{
    moveLayer("theMap",
              hspc,
              vspc);
    getImageXY(e);
    // keep it within the MapImage
    if ((mouseX
        < iWidth)
        && (mouseY
        < iHeight))
    {
        if (zooming)
        {
            stopZoomOutBox(e);
        }
        else
        {
            x1 =
            mouseX;
            y1 =
            mouseY
            x2 =
            x1
                + 1;
            y2 =
            y1
                + 1;
            zleft =
            x1;
            ztop =
            y1;
            zbottom =
            y1;
            zright =
            x1
            boxIt(x1,
                  y1,
                  x2,
                  y2);
            zooming =
            true;
            /*
             if (isNav4) {
             showLayer("zoomBoxTop");
             showLayer("zoomBoxLeft");
             showLayer("zoomBoxRight");
             showLayer("zoomBoxBottom");
             } else {
             showLayer("zoomBox");
             }
             */
        }
        //} else {
        //	if (zooming) stopZoomOutBox(e);
    }
    return false;
}
// stop zoom out box. . . zoom out
function stopZoomOutBox(e)
{
    zooming =
    false;
    //if (isNav4) {
    hideLayer("zoomBoxTop");
    hideLayer("zoomBoxLeft");
    hideLayer("zoomBoxRight");
    hideLayer("zoomBoxBottom");
    //} else {
    //	hideLayer("zoomBox");
    //}
    if ((zright
        < zleft
        + 2)
        && (zbottom
        < ztop
        + 2))
    {
        zoomout(e);
    }
    else
    {
        var tempLeft = eLeft;
        var tempRight = eRight;
        var tempTop = eTop;
        var tempBottom = eBottom;
        saveLastExtent();
        var zWidth = Math.abs(zright
                                  - zleft);
        var zHeight = Math.abs(ztop
                                   - zbottom);
        var xRatio = iWidth
            / zWidth;
        var yRatio = iHeight
            / zHeight;
        var xAdd = xRatio
                       * xDistance
            / 2;
        var yAdd = yRatio
                       * yDistance
            / 2;
        eLeft =
        eLeft
            - xAdd;
        eRight =
        eRight
            + xAdd;
        eTop =
        eTop
            + yAdd;
        eBottom =
        eBottom
            - yAdd;
        window.scrollTo(0,
                        0);
        if (enforceFullExtent)
        {
            if ((eRight
                - eLeft)
                > fullWidth)
            {
                eLeft =
                fullLeft;
                eRight =
                fullRight;
                eTop =
                fullTop;
                eBottom =
                fullBottom;
                lastLeft =
                tempLeft;
                lastRight =
                tempRight;
                lastTop =
                tempTop;
                lastBottom =
                tempBottom;
            }
        }
        //var theString = writeXML();
        sendMapXML();
    }
    return true;
}
// clip zoom box layer to mouse coords
function setClip()
{
    var tempX = x1;
    var tempY = y1;
    if (x1
        > x2)
    {
        zright =
        x1;
        zleft =
        x2;
    }
    else
    {
        zleft =
        x1;
        zright =
        x2;
    }
    if (y1
        > y2)
    {
        zbottom =
        y1;
        ztop =
        y2;
    }
    else
    {
        ztop =
        y1;
        zbottom =
        y2;
    }
    if ((x1
        != x2)
        && (y1
        != y2))
    {
        //clipLayer("zoomBox",zleft,ztop,zright,zbottom);
        boxIt(zleft,
              ztop,
              zright,
              zbottom);
        /*
         clipLayer("zoomBoxTop",zleft,ztop,zright,ztop+ovBoxSize);
         clipLayer("zoomBoxLeft",zleft,ztop,zleft+ovBoxSize,zbottom);
         clipLayer("zoomBoxRight",zright-ovBoxSize,ztop,zright,zbottom);
         clipLayer("zoomBoxBottom",zleft,zbottom-ovBoxSize,zright,zbottom);
         */
    }
    //return false;
}
// start pan.... image will move
function startPan(e)
{
    moveLayer("theMap",
              hspc,
              vspc);
    getImageXY(e);
    // keep it within the MapImage
    if ((mouseX
        < iWidth)
        && (mouseY
        < iHeight))
    {
        if (panning)
        {
            stopPan(e);
        }
        else
        {
            x1 =
            mouseX;
            y1 =
            mouseY
            x2 =
            x1
                + 1;
            y2 =
            y1
                + 1;
            panning =
            true;
        }
    }
    return false;
}
// stop moving image.... pan
function stopPan(e)
{
    window.scrollTo(0,
                    0);
    panning =
    false;
    var tempLeft = eLeft;
    var tempRight = eRight;
    var tempTop = eTop;
    var tempBottom = eBottom;
    //saveLastExtent();
    var ixOffset = x2
        - x1;
    var iyOffset = y1
        - y2;
    pixelX =
    xDistance
        / iWidth;
    var theY = iHeight
        - ztop;
    pixelY =
    yDistance
        / iHeight;
    var xOffset = pixelX
        * ixOffset;
    var yOffset = pixelY
        * iyOffset;
    eTop =
    eTop
        - yOffset;
    eRight =
    eRight
        - xOffset;
    eLeft =
    eLeft
        - xOffset;
    eBottom =
    eBottom
        - yOffset;
    if (enforceFullExtent)
    {
        if (eLeft
            < limitLeft)
        {
            eLeft =
            limitLeft;
            eRight =
            eLeft
                + xDistance;
        }
        if (eTop
            > limitTop)
        {
            eTop =
            limitTop;
            eBottom =
            eTop
                - yDistance;
        }
        if (eRight
            > limitRight)
        {
            eRight =
            limitRight;
            eLeft =
            eRight
                - xDistance;
        }
        if (eBottom
            < limitBottom)
        {
            eBottom =
            limitBottom;
            eTop =
            eBottom
                + yDistance;
        }
    }
    lastLeft =
    tempLeft;
    lastRight =
    tempRight;
    lastTop =
    tempTop;
    lastBottom =
    tempBottom;
    //hideLayer("theMap");
    if (hasLayer("theMapClicks"))
    {
        document.theClickImage.src =
        blankImage;
    }
    //var theString = writeXML();
    sendMapXML();
    //window.setTimeout('moveLayer("theMap",hspc,vspc);',1000);
    //window.setTimeout('showLayer("theMap");',1500);
    //window.setTimeout('clipLayer("theMap",0,0,iWidth,iHeight);',1500);
    return true;
}
// move map image with mouse
function panMouse()
{
    var xMove = x2
        - x1;
    var yMove = y2
        - y1;
    var cLeft = -xMove;
    var cTop = -yMove;
    var cRight = iWidth;
    var cBottom = iHeight;
    if (xMove
        > 0)
    {
        cLeft =
        0;
        cRight =
        iWidth
            - xMove;
    }
    if (yMove
        > 0)
    {
        cTop =
        0;
        cBottom =
        iHeight
            - yMove;
    }
    clipLayer2("theMap",
               cLeft,
               cTop,
               cRight,
               cBottom);
    moveLayer("theMap",
              xMove
                  + hspc,
              yMove
                  + vspc);
    if (hasLayer("theMapClicks"))
    {
        clipLayer2("theMapClicks",
                   cLeft,
                   cTop,
                   cRight,
                   cBottom);
        moveLayer("theMapClicks",
                  xMove
                      + hspc,
                  yMove
                      + vspc);
    }
    //return false;
}
// pan to mouse click
function pan(e)
{
    getMapXY(mouseX,
             mouseY);
    var tempLeft = lastLeft;
    var tempRight = lastRight;
    var tempTop = lastTop;
    var tempBottom = lastBottom;
    saveLastExtent();
    eLeft =
    mapX
        - xHalf;
    eRight =
    mapX
        + xHalf;
    eTop =
    mapY
        + yHalf;
    eBottom =
    mapY
        - yHalf;
    //var theString = writeXML();
    sendMapXML();
}
function setZoomColor()
{
    //if (isNav4) {
    setLayerBackgroundColor("zoomBoxTop",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomBoxLeft",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomBoxRight",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomBoxBottom",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomOVBoxTop",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomOVBoxLeft",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomOVBoxRight",
                            zoomBoxColor);
    setLayerBackgroundColor("zoomOVBoxBottom",
                            zoomBoxColor);
    //} else {
    //	var theDiv = getLayer("zoomBox");
    //	theDiv.borderColor = zoomBoxColor;
    //setLayerBackgroundColor("ovBox", zoomBoxColor);
    //}
}