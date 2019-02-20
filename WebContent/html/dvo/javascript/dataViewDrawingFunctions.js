var rb = new jsGraphics("rubberband"); //rubberbandlayer
var pl = new jsGraphics("polyline"); // polylinelayer

// ==============================
// USER SETTINGS
// ==============================
var linecolor = "#800000";
var fillcolor = "#c0c0c0";
var linewidth = 3;
var handlesHeight = 4;
var handlesWidth = 4;
var showLength = false; // Show length information..or not.
var showRubberbandLength = false; // Show the length of the rubberband
// ==============================
// ==============================
var Xpoints = new Array();
var Ypoints = new Array();
var startX = 0;
var startY = 0;
var currentX = 0;
var currentY = 0;
var circleCenterX = 0;
var circleCenterY = 0;
var activate_rb = false;
var close_pl = false;
var startNewSelection = true;
var circleCentreSaved = false;
rb.setStroke(linewidth);
rb.setColor(linecolor);
pl.setColor(linecolor);
pl.setStroke(linewidth);
// ================================================
// Start up the jsgraphics
// ================================================
function activateRubberband(e)
{
    if (startNewSelection)
    {
        clickCount =
        0;
        startNewSelection =
        false;
    }
    currentX =
    mouseX;
    currentY =
    mouseY;
    getMapXY(currentX,
             currentY);
    clickPointX[clickCount] =
    mapX;
    clickPointY[clickCount] =
    mapY;
    clickCount +=
    1;
    totalMeasure =
    totalMeasure
        + currentMeasure;
    clickMeasure[clickCount] =
    totalMeasure;
    if ((toolMode
        == 20)
            && (MeasureType
        == "polygon")
        && (clickCount
        > 2))
    {
        calculateMeasureArea();
    }
    if ((toolMode
        == 22)
        && (!circleCentreSaved))
    {// Save circle centre points
        circleCenterX =
        mapX;
        circleCenterY =
        mapY;
        circleCentreSaved =
        true;
    }
    activate_rb =
    true;
    // Get hold of the starting coordinates.
    if (clickCount
        == 1)
    {
        resetPolyline(); // Delete polygon.
        pl.setColor(linecolor);
        startX =
        currentX;
        startY =
        currentY;
        drawHandles();
        if (toolMode
            == 22)
        {
            //calcDistance(mapX,mapY);
            clickCount--; // If we're using circle selection, just use rubberband.
        }
    }
    // Start drawing the polyline.
    if (clickCount
        > 1)
    {
        drawPolyline();
    }
}
// ================================================
// Reset the rubberband line.
// ================================================
function deactivateRubberband()
{
    rb.clear();
    activate_rb =
    false;
}
// ================================================
// Clear out the polygon layer.
// ================================================
function resetPolyline()
{
    pl.clear();
}
// ================================================
// Draw the rubberband in the div element.
// ================================================
function drawRubberband(e)
{
    var x1 = mouseX;
    var y1 = mouseY;
    rb.clear();
    rb.drawLine(currentX,
                currentY,
                x1,
                y1); // coordinates related to the document.
    // Show rubberband length when measure and circleselection
    if ((showRubberbandLength)
        && ((toolMode
        == 20)
        || (toolMode
        == 22)))
    {
        displayRubberbandLength();
    }
    if (toolMode
        == 22)
    {
        drawDynamicCircle(e);
    } // Draw the circle when moving mouse.
    if (clickCount
        > 1)
    {
        // Draw double rubberband if we're using polygon selection (toolMode=13) or area measure
        if ((toolMode
            == 13)
            || ((MeasureType
            == "polygon")
            && (toolMode
            == 20)))
        {
            rb.drawLine(x1,
                        y1,
                        startX,
                        startY);
        }
    }
    rb.paint();
}
// ================================================
// Draw the polyline.
// ================================================
function drawPolyline(e)
{
    //alert("draw");
    addElement();
    //if ((showLength)&&(toolMode==20)) {
    //	displayLength();
    //}
    drawHandles();
    pl.drawPolyline(Xpoints,
                    Ypoints);
    pl.paint();
}
// ================================================
// Draw the 'moving' circle...
// ================================================
function drawDynamicCircle(e)
{
    var x1 = mouseX;
    var y1 = mouseY;
    var dx = parseInt(x1
                          - currentX);
    var dy = parseInt(y1
                          - currentY);
    var ch = Math.round(2
                            * (Math.sqrt((Math.pow(dx,
                                                   2))
                                             + (Math.pow(dy,
                                                         2)))))
        / 1;
    var cx = parseInt(currentX
                          - (ch
        / 2));
    var cy = parseInt(currentY
                          - (ch
        / 2));
    rb.drawEllipse(cx,
                   cy,
                   ch,
                   ch);
}
// ================================================
// Makes the handles (breakpoints)
// ================================================
function drawHandles()
{
    var eCen = parseInt((handlesHeight
        / 2));
    if (clickCount
        > 1)
    {
        for (i =
             0; i
            < Xpoints.length; i++)
        {
            pl.drawRect((Xpoints[i]
                - eCen),
                        (Ypoints[i]
                            - eCen),
                        handlesWidth,
                        handlesHeight);
        }
    }
    else
    {
        pl.drawRect((startX
            - eCen),
                    (startY
                        - eCen),
                    handlesWidth,
                    handlesHeight);
    }
    pl.paint();
}
// ================================================
// Close the polyline and make it look like a filled polygon.
// ================================================
function closePolyline(e)
{
    close_pl =
    true;
    // Draw the last line
    drawPolyline(e);
    close_pl =
    false;
    // Reset mapclicks and x,y array.
    Xpoints.length =
    null;
    Ypoints.length =
    null;
    // Starover to draw the polyline
    startNewSelection =
    true;
    resetPolyline();
}
// ================================================
// Adds element (x,y points) into the polyline array.
// ================================================
function addElement()
{
    var xsize = Xpoints.length;
    var ysize = Ypoints.length;
    if ((xsize
        > 0)
        && (ysize
        > 0))
    { // Add more points...
        // Keep on adding more coordinates.
        if (!close_pl)
        {
            Xpoints[xsize] =
            currentX;
            Ypoints[ysize] =
            currentY;
        }
        else
        { // Stop adding coordinates and close the polyline into a polygon-look-a-like.
            Xpoints[xsize] =
            currentX;
            Ypoints[ysize] =
            currentY;
            Xpoints[xsize
                + 1] =
            startX;
            Ypoints[ysize
                + 1] =
            startY;
        }
    }
    else
    {// Add the FIRST coordinates.
        Xpoints[0] =
        startX;
        Xpoints[1] =
        currentX;
        Ypoints[0] =
        startY;
        Ypoints[1] =
        currentY;
    }
}
// ================================================
// Display the length on every polyline segment.
// calculates the label position.
// ================================================
function displayLength()
{
    var xsize = Xpoints.length;
    var ysize = Ypoints.length;
    var textX, textY;
    var dx = parseInt(Xpoints[xsize
        - 1])
        - (Xpoints[xsize
        - 2]);
    var dy = parseInt(Ypoints[ysize
        - 1])
        - (Ypoints[ysize
        - 2]);
    //var l = Math.round(Math.sqrt((Math.pow(dx,2))+(Math.pow(dy,2))))/1;
    // Fix text X placement
    if (dx
        < 0)
    {
        textX =
        parseInt(Xpoints[xsize
            - 2]
                     + (dx
            / 2));
    }
    else
    {
        textX =
        parseInt((Xpoints[xsize
            - 2]
            + (dx
            / 2)));
    }
    // Fix text Y placement
    if (dy
        < 0)
    {
        textY =
        parseInt(Ypoints[ysize
            - 2]
                     + (dy
            / 2));
    }
    else
    {
        textY =
        parseInt(Ypoints[ysize
            - 2]
                     + (dy
            / 2));
    }
    pl.setFont("verdana",
               "20px",
               Font.BOLD);
    pl.drawString(currentMeasure,
                  textX,
                  textY);
}
// ================================================
// Display the current length of the rubberband
// when moving mouse on map.
// Calculates the label position.
// ================================================
function displayRubberbandLength()
{
    var textX, textY;
    var x1 = mouseX;
    var y1 = mouseY;
    var dx = parseInt(x1
                          - currentX);
    var dy = parseInt(y1
                          - currentY);
    // Fix text X placement
    if (dx
        < 0)
    {
        textX =
        parseInt(currentX
                     + (dx
            / 2));
    }
    else
    {
        textX =
        parseInt((currentX
            + (dx
            / 2)));
    }
    // Fix text Y placement
    if (dy
        < 0)
    {
        textY =
        parseInt(currentY
                     + (dy
            / 2));
    }
    else
    {
        textY =
        parseInt(currentY
                     + (dy
            / 2));
    }
    rb.setFont("verdana",
               "20px",
               Font.BOLD);
    rb.drawString(currentMeasure,
                  textX,
                  textY);
}