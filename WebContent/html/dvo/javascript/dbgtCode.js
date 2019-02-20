//---------------------------------------------------------------

// dbgtCode.js v.1.5
// Copyright (C) 2002,2003 David Bollinger (davebollinger@hotmail.com)

//

// Support code for the "dbGroupToc" modification - A grouped

// table of contents for ArcIMS 3.1+ HTML viewer sites.

//

// Notice: This code may be freely distributed, used and

//         modified provided that this comment remains intact.

//---------------------------------------------------------------


//------------------

// _TOC_ID_GENERATOR

//------------------
function _TOC_ID_GENERATOR()
{
    this.nextID =
    0;
    this.getID =
    function ()
    {
        var id = this.nextID;
        this.nextID =
        this.nextID
            + 1;
        return id;
    }
}
var _idgen = new _TOC_ID_GENERATOR();
//-----------------
// _TOC_IMAGE_CACHE
//-----------------
function _TOC_IMAGE_CACHE()
{
    this.iconPath =
    'dbGroupToc/';                   // path to icon images
    this.swatchPath =
    'dbGroupToc/';                 // path to swatch images
    this.legendPath =
    'dbGroupToc/';                 // path to legend images
    this.strIconSize =
    ' WIDTH="16" HEIGHT="16" ';
    this.loadImage =
    function (path, file)
    {
        if ((file
            || '')
            != '')
        {
            var img = new Image();
            img.src =
            path
                + file;
            return img;
        }
        else
        {
            return null;
        }
    };
    this.loadIcon =
    function (file)
    {
        return this.loadImage(this.iconPath,
                              file);
    };
    this.loadSwatch =
    function (file)
    {
        return this.loadImage(this.swatchPath,
                              file);
    };
    this.loadLegend =
    function (file)
    {
        return this.loadImage(this.legendPath,
                              file);
    };
    this.iconPixel =
    this.loadIcon('icon_pixel.gif');          // single transparent pixel
    this.iconBlank =
    this.loadIcon('icon_blank.gif');
    // all transparent placeholder
    this.iconClosed =
    this.loadIcon('icon_closed.gif');
    // closed group folder
    this.iconOpened =
    this.loadIcon('icon_opened.gif');
    // opened group folder
    this.iconChild =
    this.loadIcon('icon_child.gif');
    // connecting line to child item (continued)
    this.iconChildLast =
    this.loadIcon('icon_childlast.gif');
    // connecting line to child item (last one)
    this.iconSibling =
    this.loadIcon('icon_sibling.gif');
    // vertical connector to sibling item
    this.iconLabelOn =
    this.loadIcon('icon_labelon.gif');
    // labelling enabled
    this.iconLabelOff =
    this.loadIcon('icon_labeloff.gif');
    // labelling disabled
    this.iconLayer =
    this.loadIcon('icon_layer.gif');
    // map layer icon
    this.iconHidden =
    this.loadIcon('icon_hidden.gif');
    // layer not visible, unselected checkbox
    this.iconVisible =
    this.loadIcon('icon_visible.gif');
    // layer visible, selected checkbox
    this.iconVisscale =
    this.loadIcon('icon_visscale.gif');
    // layer visible, but not at this scale
    this.iconTristate =
    this.loadIcon('icon_tristate.gif');
    // tri-state checkbox, some layers visible
    this.iconActive =
    this.loadIcon('icon_active.gif');
    // selected radio
    this.iconInactive =
    this.loadIcon('icon_inactive.gif');
    // unselected radio
    this.iconHelp =
    this.loadIcon('icon_help.gif');
    // not currently used
}
var _cache = new _TOC_IMAGE_CACHE();
//-----------------

// TOC

//-----------------
function TOC(title, caption, autoRefreshMap, swatch)
{

    // PROPERTIES
    this.title =
    title
        || 'LAYERS';
    this.caption =
    caption
        || title
        || 'LAYERS';
    this.root =
    new GROUP(caption,
              true,
              swatch);
    this.isInited =
    false;
    this.divToc =
    null;
    this.divTocHelp =
    null;
    this.autoRefreshMap =
    autoRefreshMap
        || false;
    this.LayersGroups =
    new Array();
}
_TOC =
TOC.prototype;
// EVENTS
_TOC.onIconClick =
function (tocid)
{
    var item = this.root.findItemByTocID(tocid);
    item.onIconClick();
    this.refresh();
    return false;
}
_TOC.onVisibleClick =
function (tocid, value)
{
    var item = this.root.findItemByTocID(tocid);
    item.onVisibleClick(value);
    this.refresh();
    this.refreshMap();
    return false;
}
_TOC.onActiveClick =
function (tocid)
{
    var item = this.root.findItemByTocID(tocid);
    item.onActiveClick();
    this.refresh();
    parent.MapFrame.updateStatus(parent.MapFrame.modeName);
    return false;
}
_TOC.onSwatchClick =
function (tocid)
{
    var item = this.root.findItemByTocID(tocid);
    item.onSwatchClick();
    this.refresh();
    return false;
}
_TOC.onCaptionClick =
function (tocid)
{
    var item = this.root.findItemByTocID(tocid);
    item.onCaptionClick();
    parent.MapFrame.updateStatus(parent.MapFrame.modeName);
    this.refresh();
}
_TOC.onLabelClick =
function (tocid)
{
    var item = this.root.findItemByTocID(tocid);
    item.onLabelClick();
    this.refresh();
    parent.MapFrame.updateStatus(parent.MapFrame.modeName);
    return false;
}
// METHODS
_TOC.init =
function ()
{
    if (LayerName.length
        == 0)
    {
        return;
    }
    if (this.root.items.length
        == 0)
    {
        var addTo;
        for (var i = 0, n = LayerName.length; i
            < n; i++)
        {
            addTo =
            this;
            if (this.LayersGroups)
            {
                if ((this.LayersGroups.length
                    > i)
                    && (this.LayersGroups[i]
                    != ''))
                {
                    addTo =
                    this.root.findItemByAxlID(this.LayersGroups[i]);
                    if (addTo
                        == null)
                    {
                        addTo =
                        this.root.addGroup(new GROUP(this.LayersGroups[i],
                                                     true,
                                                     null));
                    }
                }
            }
            addTo.addLayer(new LAYER(LayerID[i],
                                     LayerName[i],
                                     null,
                                     null,
                                     null));
        }
    }
    this.root.init();
    this.isInited =
    true;
    parent.MapFrame.updateStatus(parent.MapFrame.modeName);
}
_TOC.setOutput =
function (divToc, divTocHelp)
{
    this.divToc =
    divToc;
    this.divTocHelp =
    divTocHelp;
}
_TOC.addItem =
function (item)
{
    return this.root.addItem(item);
}
_TOC.addGroup =
_TOC.addItem;
_TOC.addLayer =
_TOC.addItem;
_TOC.refresh =
function ()
{
    if (this.divToc
        != null)
    {
        this.divToc.innerHTML =
        this.writeHTML();
    }
    if (this.divTocHelp
        != null)
    {
        this.divTocHelp.innerHTML =
        this.writeHelpHTML();
    }
}
_TOC.refreshMap =
function ()
{
    if ((this.autoRefreshMap)
        & (okToSend))
    {
        sendMapXML();
    }
}
_TOC.writeHTML =
function ()
{
    var s = "";
    if (!this.isInited)
    {
        this.init();
    }
    if (!this.isInited)
    {
        return '';
    }
    //------
    // TITLE
    //------
    s +=
    '<CENTER><P CLASS="LayerListTitle">'
        + this.title
        + '<BR>';
    //-------------------
    // START OF TOC TABLE
    //-------------------
    s +=
    '<TABLE WIDTH="100%"	BORDER="0" CELLSPACING="0" CELLPADDING="0" NOWRAP>';
    //--------
    // CASCADE
    //--------
    s +=
    this.root.writeHTML('',
                        '');
    //-----------------
    // END OF TOC TABLE
    //-----------------
    s +=
    '</TABLE><BR></CENTER>\n';
    return s;
}
_TOC.writeHelpHTML =
function ()
{
    var s = '';
    s +=
    '<BR><BR><LEFT><Strong>Layer Help:</Strong><BR><BR><DIV	CLASS="LayerListHelp" ALIGN="left" NOWRAP>';
    s +=
    '<IMG SRC="'
        + _cache.iconClosed.src
        + '"'
        + _cache.strIconSize
        + '> A closed group, click to open.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconOpened.src
        + '"'
        + _cache.strIconSize
        + '> An open group, click to close.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconLayer.src
        + '"'
        + _cache.strIconSize
        + '> A map layer.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconHidden.src
        + '"'
        + _cache.strIconSize
        + '> A hidden group/layer, click to make visible.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconVisible.src
        + '"'
        + _cache.strIconSize
        + '> A visible group/layer, click to hide.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconVisscale.src
        + '"'
        + _cache.strIconSize
        + '> A visible layer, but not at this scale.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconTristate.src
        + '"'
        + _cache.strIconSize
        + '> A partially visible group, click to make visible.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconInactive.src
        + '"'
        + _cache.strIconSize
        + '> An inactive layer, click to make active.<BR>';
    s +=
    '<IMG SRC="'
        + _cache.iconActive.src
        + '"'
        + _cache.strIconSize
        + '> The active layer.<BR>';
    s +=
    '</DIV></CENTER>\n';
    return s;
}
//-----------------
// GROUP
//-----------------
function GROUP(caption, opened, swatch)
{
    // PROPERTIES
    this.parent =
    null;
    this.tocid =
    _idgen.getID();
    this.caption =
    caption
        || '';
    this.axlid =
    this.caption;
    this.opened =
    opened
        || false;
    this.items =
    new Array();
    this.iconOpened =
    _cache.iconOpened;
    this.iconClosed =
    _cache.iconClosed;
    this.swatch =
    _cache.loadSwatch(swatch);
}
_GROUP =
GROUP.prototype;
// EVENTS
_GROUP.onIconClick =
function ()
{
    this.toggleOpened();
}
_GROUP.onVisibleClick =
function (value)
{
    this.setVisible(value);
}
_GROUP.onActiveClick =
function ()
{
    // nop
}
_GROUP.onSwatchClick =
function ()
{
    // nop
}
_GROUP.onCaptionClick =
function ()
{
    this.toggleOpened();
}
_GROUP.onLabelClick =
function ()
{
    // nop
}
// METHODS
_GROUP.init =
function ()
{
    for (var i = 0, n = this.items.length; i
        < n; i++)
    {
        this.items[i].init();
    }
}
_GROUP.findItemByAxlID =
function (axlid)
{
    if (this.axlid
        == axlid)
    {
        return this;
    }
    for (var i = 0, n = this.items.length; i
        < n; i++)
    {
        var item = this.items[i].findItemByAxlID(axlid);
        if (item)
        {
            return item;
        }
    }
    return null;
}
_GROUP.findItemByTocID =
function (tocid)
{
    if (this.tocid
        == tocid)
    {
        return this;
    }
    for (var i = 0, n = this.items.length; i
        < n; i++)
    {
        var item = this.items[i].findItemByTocID(tocid);
        if (item)
        {
            return item;
        }
    }
    return null;
}
_GROUP.addItem =
function (item)
{
    item.parent =
    this;
    this.items[this.items.length] =
    item;
    return item;
}
_GROUP.addLayer =
_GROUP.addItem;
_GROUP.addGroup =
_GROUP.addItem;
_GROUP.getItem =
function (index)
{
    return this.items[index];
}
_GROUP.toggleOpened =
function ()
{
    this.opened =
    !this.opened;
}
_GROUP.setActive =
function ()
{
    // nop
    // though might be useful as part of a modified identify-all tool
}
_GROUP.toggleLabel =
function ()
{
    for (var i = 0, n = this.items.length; i
        < n; i++)
    {
        this.items[i].toggleLabel();
    }
}
_GROUP.getVisible =
function ()
{
    for (var i = 0, n = this.items.length, v = 0; i
        < n; i++)
    {
        v +=
        this.items[i].getVisible();
    }
    return (v
        == 0)
        ? 0
        : (v
        == n)
        ? 1
        : 99999; // 99999=tristate
}
_GROUP.setVisible =
function (value)
{
    for (var i = 0, n = this.items.length; i
        < n; i++)
    {
        this.items[i].setVisible(value);
    }
}
_GROUP.toggleVisible =
function ()
{
    for (var i = 0, n = this.items.length; i
        < n; i++)
    {
        this.items[i].toggleVisible();
    }
}
_GROUP.writeHTML =
function (child_shim, sibling_shim)
{
    var s = "";
    //-------------------
    // START OF GROUP ROW
    //-------------------
    s +=
    '<TR><TD NOWRAP VALIGN="TOP">';
    //------------
    // FOLDER ICON
    //------------
    s +=
    child_shim;
    if (this.opened)
    {
        s +=
        '<IMG SRC="'
            + this.iconOpened.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onIconClick('
            + this.tocid
            + ');" alt="Click to close">';
    }
    else
    {
        s +=
        '<IMG SRC="'
            + this.iconClosed.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onIconClick('
            + this.tocid
            + ');" alt="Click to open">';
    }
    //-----------------
    // VISIBLE CHECKBOX
    //-----------------
    var vis = this.getVisible();
    if (vis
        == 0)
    {
        s +=
        '<IMG SRC="'
            + _cache.iconHidden.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onVisibleClick('
            + this.tocid
            + ',1);" alt="Click to show">';
    } else if (vis
        == 1)
    {
        s +=
        '<IMG SRC="'
            + _cache.iconVisible.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onVisibleClick('
            + this.tocid
            + ',0);" alt="Click to hide">';
    }
    else
    {
        s +=
        '<IMG SRC="'
            + _cache.iconTristate.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onVisibleClick('
            + this.tocid
            + ',1);" alt="Click to show">';
    }
    //-------------
    // SWATCH IMAGE
    //-------------
    if (this.swatch
        != null)
    {
        s +=
        '<IMG SRC="'
            + this.swatch.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onSwatchClick('
            + this.tocid
            + ');" alt="">';
    }
    //--------------
    // GROUP CAPTION
    //--------------
    s +=
    '<A HREF="javascript:t.toc.onCaptionClick('
        + this.tocid
        + ')">'
        + this.caption
        + '</A>';
    //-----------------
    // END OF GROUP ROW
    //-----------------
    s +=
    '</TD></TR>';
    //--------
    // CASCADE
    //--------
    if (this.opened)
    {
        for (var i = 0, n = this.items.length; i
            < n; i++)
        {
            var new_child_shim = sibling_shim
                                     + '<img src="'
                                     + ((i
                == n
                - 1)
                ? _cache.iconChildLast.src
                : _cache.iconChild.src)
                                     + '"'
                                     + _cache.strIconSize
                + '>';
            var new_sibling_shim = sibling_shim
                                       + '<img src="'
                                       + ((i
                == n
                - 1)
                ? _cache.iconBlank.src
                : _cache.iconSibling.src)
                                       + '"'
                                       + _cache.strIconSize
                + '>';
            s +=
            this.items[i].writeHTML(new_child_shim,
                                    new_sibling_shim);
        }
    }
    return s;
}
//-----------------
// LAYER
//-----------------
function LAYER(name, caption, swatch, legend, labelField)
{
    // PROPERTIES
    this.parent =
    null;
    this.tocid =
    _idgen.getID();
    this.name =
    name
        || '';
    this.axlid =
    this.name;
    this.caption =
    caption
        || name
        || '';
    this.index =
    -1;
    this.icon =
    _cache.iconLayer;
    this.swatch =
    _cache.loadSwatch(swatch);
    this.legend =
    _cache.loadLegend(legend);
    this.legendVisible =
    false;
    this.labelField =
    labelField
        || '';
    this.labelled =
    false;
}
;
_LAYER =
LAYER.prototype;
// EVENTS
_LAYER.onIconClick =
function ()
{
    this.setActive();
}
_LAYER.onVisibleClick =
function (value)
{
    this.toggleVisible();
}
_LAYER.onActiveClick =
function ()
{
    this.setActive();
}
_LAYER.onSwatchClick =
function ()
{
    this.legendVisible =
    !this.legendVisible;
}
_LAYER.onCaptionClick =
function ()
{
    this.setActive();
}
_LAYER.onLabelClick =
function ()
{
    this.toggleLabel();
}
// METHODS
_LAYER.init =
function ()
{
    for (var i = 0, n = LayerID.length; i
        < n; i++)
    {
        if (LayerID[i]
            == this.name)
        {
            this.index =
            i;
            return;
        }
    }
    for (var i = 0, n = LayerName.length; i
        < n; i++)
    {
        if (LayerName[i]
            == this.name)
        {
            this.index =
            i;
            return;
        }
    }
    if (debugOn
        > 0)
    {
        alert('Possible error in TOC definition.\nUnable to get layer index for "'
                  + this.name
                  + '".\nCheck TOC definition in dbgtData.js.');
    }
}
_LAYER.findItemByAxlID =
function (axlid)
{
    if (this.axlid
        == axlid)
    {
        return this;
    }
    return null;
}
_LAYER.findItemByTocID =
function (tocid)
{
    if (this.tocid
        == tocid)
    {
        return this;
    }
    return null;
}
_LAYER.setActive =
function ()
{
    setActiveLayer(this.index);
    var isOk = checkHyperLinkLayer(this.index);
    if (toolMode
        == 15)
    {
        if (!isOk)
        {
            currentHyperLinkLayer =
            '';
            currentHyperLinkField =
            '';
            currentHyperLinkPrefix =
            '';
            currentHyperLinkSuffix =
            '';
            alert('This layer does not have any Hyperlinks.');
        }
    }
}
_LAYER.toggleLabel =
function ()
{
    this.labelled =
    !this.labelled;
}
_LAYER.getVisible =
function ()
{
    return LayerVisible[this.index];
}
_LAYER.setVisible =
function (value)
{
    LayerVisible[this.index] =
    value;
}
_LAYER.toggleVisible =
function ()
{
    LayerVisible[this.index] =
    1
        - LayerVisible[this.index];
}
_LAYER.writeHTML =
function (child_shim, sibling_shim)
{
    var s = '';
    //-------------------
    // START OF LAYER ROW
    //-------------------
    var highlight = (ActiveLayerIndex
        == this.index)
        ? ' CLASS="Highlight"'
        : '';
    s +=
    '<TR'
        + highlight
        + '><TD NOWRAP>';
    //-----------------
    // CONNECTING LINES
    //-----------------
    s +=
    child_shim;
    s +=
    '<IMG SRC="'
        + this.icon.src
        + '" WIDTH="16" HEIGHT="16" onmousedown="javascript:t.toc.onIconClick('
        + this.tocid
        + ');" alt="" >';
    //--------------------
    // VISIBILITY CHECKBOX
    //--------------------
    if (LayerVisible[this.index])
    {
        if ((mapScaleFactor
            >= LayerMinScale[this.index])
            && (mapScaleFactor
            <= LayerMaxScale[this.index]))
        {
            s +=
            '<IMG SRC="'
                + _cache.iconVisible.src
                + '" onmousedown="t.toc.onVisibleClick('
                + this.tocid
                + ');" alt="Click to hide">';
        }
        else
        {
            s +=
            '<IMG SRC="'
                + _cache.iconVisscale.src
                + '"'
                + _cache.strIconSize
                + ' onmousedown="t.toc.onVisibleClick('
                + this.tocid
                + ');" alt="Click to hide">';
        }
    }
    else
    {
        s +=
        '<IMG SRC="'
            + _cache.iconHidden.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onVisibleClick('
            + this.tocid
            + ');" alt="Click to hide">';
    }
    //----------------
    // ACTIVE RADIOBOX
    //----------------
    if (LayerIsFeature[this.index])
    {
        if (ActiveLayerIndex
            == this.index)
        {
            s +=
            '<IMG SRC="'
                + _cache.iconActive.src
                + '">';
        }
        else
        {
            s +=
            '<IMG SRC="'
                + _cache.iconInactive.src
                + '"'
                + _cache.strIconSize
                + ' onmousedown="t.toc.onActiveClick('
                + this.tocid
                + ');" alt="Click to make active">';
        }
    }
    //-------------
    // SWATCH IMAGE
    //-------------
    if (this.swatch
        != null)
    {
        s +=
        '<IMG SRC="'
            + this.swatch.src
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onSwatchClick('
            + this.tocid
            + ');" alt="">';
    }
    //--------------
    // LAYER CAPTION
    //--------------
    s +=
    '<A HREF="javascript:t.toc.onCaptionClick('
        + this.tocid
        + ');">'
        + this.caption
        + '</A>';
    //-------------
    // LABEL TOGGLE
    //-------------
    if (this.labelField
        != "")
    {
        var labelicon = (this.labelled)
            ? _cache.iconLabelOn.src
            : _cache.iconLabelOff.src;
        s +=
        '<IMG SRC="'
            + labelicon
            + '"'
            + _cache.strIconSize
            + ' onmousedown="t.toc.onLabelClick('
            + this.tocid
            + ');" alt="Toggle Labels">';
    }
    //-----------------
    // END OF LAYER ROW
    //-----------------
    s +=
    '</TD></TR>';
    //--------------------
    // OPTIONAL LEGEND ROW
    //--------------------
    if ((this.legend
        != null)
        && (this.legendVisible))
    {
        s +=
        '<TR><TD NOWRAP>'
            + sibling_shim; // connecting lines
        s +=
        '<IMG SRC="'
            + _cache.iconBlank.src
            + '">'; // layer icon
        s +=
        '<IMG SRC="'
            + _cache.iconBlank.src
            + '">'; // visible checkbox
        s +=
        '<IMG SRC="'
            + _cache.iconBlank.src
            + '">'; // active radio
        s +=
        '<IMG SRC="'
            + this.legend.src
            + '" WIDTH="'
            + this.legend.width
            + '" HEIGHT="'
            + this.legend.height
            + '">'; // line up legend with swatch
        s +=
        '</TD></TR>';
    }
    return s;
}
//-----------------
// eof
//-----------------



