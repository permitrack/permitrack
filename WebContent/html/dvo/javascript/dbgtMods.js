//---------------------------------------------------------------
// dbgtMods.js v.1.5
// Copyright (C) 2002,2003 David Bollinger (davebollinger@hotmail.com)
//
// Support code for the "dbGroupToc" modification - A grouped
// table of contents for ArcIMS 3.1+ HTML viewer sites.
//
// Notice: This code may be freely distributed, used and
//         modified provided that this comment remains intact.
//---------------------------------------------------------------


// this module is optional.
//
// this module is intended to present a method for simulating
// inheritence in javascript whereby you can customize the
// TOC, GROUP and LAYER objects used by the TOC in order to achieve
// custom behaviour without having to modify dbgtCode.js itself.
//
// (note that this inheritence trick doesn't really work for
// primitive data types - you either know what that means or you
// don't, sorry, not sure how to further document that!)
//
// several "common" variants are provided below as a starting point.
// presented roughly in order of increasing complexity.
//
// the "sample usage" shown below are designed to work on the
// sample 'sanfrancisco' map toc definition, simply replace the
// corresponding code with the sample usage code to try out the
// variation.
//


//---------------------------------------------------------------------------
// TOCNR
//   a toc with no "root" node, if you visually prefer to eliminate the
//   root node and the first column of branch connecting lines
// sample usage:
//   var toc = new TOCNR('LAYERS','unused root caption',true,'swatch_layers.gif');
//---------------------------------------------------------------------------
function TOCNR(title, caption, autoRefreshMap, swatch)
{
    var toc = new TOC(title,
                      caption,
                      autoRefreshMap,
                      swatch);
    inherit(this,
            toc);
    // override GROUP writeHTML for toc.root
    this.root.writeHTML =
    function (child_shim, sibling_shim)
    {
        var s = "";
        for (var i = 0, n = this.items.length; i
            < n; i++)
        {
            s +=
            this.items[i].writeHTML('',
                                    '');
        }
        return s;
    };
    return this;
}
//---------------------------------------------------------------------------
// LAYERSI
//   a layer with a "swatch" as it's icon.  in other words, the swatch
//   image is drawn where the layer icon would otherwise be drawn, perhaps
//   to save horizontal space in the toc.
//
// usage:
//   toc.addLayer( new LAYERSI('Highways',null,'swatch_highways.gif','legend_streets.gif') );
//---------------------------------------------------------------------------
function LAYERSI(name, caption, swatch, legend, labelField)
{
    var lyr = new LAYER(name,
                        caption,
                        swatch,
                        legend,
                        labelField);
    inherit(this,
            lyr);
    // override LAYER init
    this.init =
    function ()
    {
        this.zuper.init();
        this.index =
        this.zuper.index; // primitive hack
        this.icon =
        this.swatch;
        this.swatch =
        null;
        this.onIconClick =
        this.onSwatchClick;
    }
    return this;
}
//---------------------------------------------------------------------------
// LAYERAL
//   a layer whose icon image indicates its active layer status.  when the
//   layer is active a custom icon is used.  makes the most sense if all
//   feature layers share this style (not just one).  you may then wish to
//   remove the highlight of the active layer line.
//
// usage:
//   toc.addLayer( new LAYERAL('Highways',null,'swatch_highways.gif','legend_streets.gif') );
//---------------------------------------------------------------------------
function LAYERAL(name, caption, swatch, legend, labelField)
{
    var lyr = new LAYER(name,
                        caption,
                        swatch,
                        legend,
                        labelField);
    inherit(this,
            lyr);
    // override LAYER init
    this.init =
    function ()
    {
        this.zuper.init();
        this.index =
        this.zuper.index; // primitive hack
        this.iconInactive =
        this.icon;
        this.iconActive =
        _cache.loadIcon("icon_layer_active.gif");
    }
    this.writeHTML =
    function (child_shim, sibling_shim)
    {
        this.zuper.icon =
        ((this.index
            == ActiveLayerIndex)
            ? this.iconActive
            : this.iconInactive);
        return this.zuper.writeHTML(child_shim,
                                    sibling_shim);
    }
    return this;
}
//---------------------------------------------------------------------------
// GROUPVL
//   a group that acts like a virtual layer - it can't be opened
//   and all layer visiblity is synchronized, either all
//   visible or all invisible.  no active layer control provided.
//   most useful with imagery since active layer control is absent
//   (or with feature layers that aren't intended to ever be
//   selected as the active layer).  for instance, individual tiles
//   of aerial photographs to be treated as a single aggregate
//   layer, a set of feature layers containing only label renderers
//   grouped in a single "annotation" virtual layer, etc.  assumes
//   that all items in the group are LAYERS, doesn't support nested
//   groups.  does NOT enforce initial state of all-or-none visible,
//   that's controled by your mapservice.
// sample usage:
//     var grpPri = grpLoc.addGroup( new GROUPVL('Private',true,'swatch_theaters_agencies.gif') );
//---------------------------------------------------------------------------
function GROUPVL(caption, opened, swatch)
{
    var group = new GROUP(caption,
                          opened,
                          swatch);
    inherit(this,
            group);
    this.opened =
    false;
    // override GROUP toggleOpened
    //
    this.toggleOpened =
    function ()
    {
        this.opened =
        false;
    };
    // use a custom icon (since the folder icon would be
    // confusing if you can't actually open it)
    //
    this.iconOpened =
    _cache.loadIcon("icon_groupvl.gif");
    this.iconClosed =
    this.iconOpened;
    return this;
}
//---------------------------------------------------------------------------
// GROUP1
//   a group that only allows (and forces) exactly one layer to be visible.
//   for instance, within a thematic group of layers that the user cannot
//   turn off but are only logically viewed one at a time, either because
//   they obscure each other or cause other map interpretation problems.
//   such as historical aerial photography layers, fire districts vs school
//   districts vs police districts all drawn as filled polygons and
//   completely covering aoi, etc.  assumes that all items in the group
//   are LAYERS, doesn't support nested groups.  does NOT enforce initial
//   state of all-or-none visible, that's controled by your mapservice.
// sample usage:
// 	   var grpBas = toc.addGroup( new GROUP1('Base Map',true) );
//---------------------------------------------------------------------------
function GROUP1(caption, opened, swatch)
{
    var group = new GROUP(caption,
                          opened,
                          swatch);
    inherit(this,
            group);
    // override GROUP getVisible
    //
    this.getVisible =
    function ()
    {
        return 1;  // always indicate visible
    };
    // override GROUP setVisible
    //
    this.setVisible =
    function (value)
    {
        // nop
    };
    // override GROUP init
    //
    this.init =
    function ()
    {
        this.zuper.init();
        // override LAYER toggleVisible
        //
        var ltv = function ()
        {
            this.parent.zuper.setVisible(0); // hide all layers in this group
            this.setVisible(1); // show just the one clicked, thus exclusive
        }
        for (var i = 0, n = this.items.length; i
            < n; i++)
        {
            this.items[i].toggleVisible =
            ltv;
        }
    }
    return this;
}
//---------------------------------------------------------------------------
// GROUP01
//   a group that only allows zero or one layers to be visible.
//   similar to GROUP1 as describe above, except that it supports the
//   all-layers-are-hidden state.  a bit trickier than GROUP1 due to
//   special handling necessary to overcome tristate conditions.
// sample usage:
// 	   var grpBas = toc.addGroup( new GROUP01('Base Map',true) );
//---------------------------------------------------------------------------
function GROUP01(caption, opened, swatch)
{
    var group = new GROUP(caption,
                          opened,
                          swatch);
    inherit(this,
            group);
    // override GROUP getVisible
    //
    this.getVisible =
    function ()
    {
        var gvr = this.zuper.getVisible();
        return (gvr
            == 0)
            ? 0
            : 1;  // never return tri-state condition
    };
    // override GROUP setVisible
    //
    this.setVisible =
    function (value)
    {
        if (value
            == 0)
        { // hide, simple case
            this.zuper.setVisible(0);
        }
        else
        { // show, tricky case
            var gvr = this.zuper.getVisible();
            if (gvr
                == 0)
            { // currently all layers hidden, so show only first one
                this.getItem(0).toggleVisible();
            } // else some number of layers already visible (hopefully only one), so do nothing
        }
    };
    // override GROUP init
    //
    this.init =
    function ()
    {
        this.zuper.init();
        // override LAYER toggleVisible
        //
        var ltv = function ()
        {
            var wasVisible = (this.getVisible()
                == 1); // current visible state of layer
            this.parent.setVisible(0); // hide all layers in this group
            if (!wasVisible)
            {
                this.setVisible(1);
            } // show this layer *IFF* it wasn't visible before
        }
        for (var i = 0, n = this.items.length; i
            < n; i++)
        {
            this.items[i].toggleVisible =
            ltv;
        }
    }
    return this;
}
//---------------------------------------------------------------------------
// UTILITY
//---------------------------------------------------------------------------
function inherit(sub, zuper)
{
    sub.zuper =
    zuper;
    for (var i in zuper)
    {
        sub[i] =
        zuper[i];
    }
    return sub;
}
//-----------------
// eof
//-----------------



