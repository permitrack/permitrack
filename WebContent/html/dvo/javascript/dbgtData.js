//---------------------------------------------------------------
// dbgtData.js v.1.5
// Copyright (C) 2002 David Bollinger (davebollinger@hotmail.com)
//
// Support code for the 'dbGroupToc' modification - A grouped
// table of contents for ArcIMS 3.1+ HTML viewer sites.
//
// Notice:   This code may be freely distributed, used and
//           modified provided that this comment remains intact.
//---------------------------------------------------------------


// THERE MUST EXIST A SINGLE GLOBAL INSTANCE OF THE "TOC" CLASS NAMED 'toc'


// THREE METHODS TO DEFINE THE TOC ARE SHOWN BELOW,
// COMMENT OR UNCOMMENT AND CUSTOMIZE IF NECESSARY


//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// FIRST METHOD, SIMPLEST POSSIBLE, JUST DEFINE THE TOC.
// ALL LAYERS WILL BE AUTOMATICALLY ADDED IN A VERY SIMPLE
// NON-GROUPED FORMAT THAT ROUGHLY MIMICS ESRI'S ORIGINAL TOC
// (this sample for use with any mapservice)
var toc = new TOC('LAYERS',
                  'Base Map',
                  true,
                  'swatch_layers.gif');
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// SECOND METHOD, MEDIUM COMPLEXITY, DEFINE THE TOC AND AN
// ARRAY DESCRIBING THE GROUP OF EACH LAYER.   ALL LAYERS
// WILL BE AUTOMATICALLY ADDED IN A GROUPED FORMAT.  DOES
// NOT SUPPORT NESTED GROUPS OR SWATCHES/LEGENDS/ETC.
// (this sample for use with 'sanfrancisco' sample mapservice)
//

/*
 var toc = new TOC('LAYERS','All Layers',true,'swatch_layers.gif');
 toc.LayersGroups[0] = 'Public Locations';  // Art Galleries
 toc.LayersGroups[1] = 'Public Locations';  // Museums
 toc.LayersGroups[2] = 'Private Locations'; // Theaters
 toc.LayersGroups[3] = 'Private Locations'; // Agencies
 toc.LayersGroups[4] = '';                  // Highways
 toc.LayersGroups[5] = 'Base Map Layers';   // Zipcodes
 toc.LayersGroups[6] = 'Base Map Layers';   // County
 */


//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// THIRD METHOD, MOST COMPLEX, MOST CUSTOMIZABLE, DEFINE THE
// TOC AND *MANUALLY* ADD GROUPS AND LAYERS AS DESIRED.
// NO LAYERS WILL BE ADDED AUTOMATICALLY.  FULL CONTROL OF
// CAPTIONS/SWATCHES/LEGENDS/ETC.
// (this sample for use with 'sanfrancisco' sample mapservice)

/*
 var toc = new TOC('LAYERS','All Layers',true,'swatch_layers.gif');
 var grpLoc = toc.addGroup( new GROUP('Locations',true,'swatch_locations.gif') );
 var grpPub = grpLoc.addGroup( new GROUP('Public',true) );
 grpPub.addLayer( new LAYER('Art Galleries',null,'swatch_artgalleries.gif') );
 grpPub.addLayer( new LAYER('Museums',null,'swatch_museums.gif','','axl_string_to_use_for_label_renderer') );
 var grpPri = grpLoc.addGroup( new GROUP('Private',true,'swatch_theaters_agencies.gif') );
 grpPri.addLayer( new LAYER('Theaters',null,'swatch_theaters.gif') );
 grpPri.addLayer( new LAYER('Agencies',null,'swatch_agencies.gif') );
 toc.addLayer( new LAYER('Highways',null,'swatch_highways.gif','legend_streets.gif') );
 var grpBas = toc.addGroup( new GROUP('Base Map',true) );
 grpBas.addLayer( new LAYER('Zipcodes',null,'swatch_zipcodes.gif') );
 grpBas.addLayer( new LAYER('County',null,'swatch_county.gif') );
 */

