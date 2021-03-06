-----------------------------------------------------------------------------
-- This script will insert the "standard" bmp database for a given client
-- The bmps and categories were taken from Madison's ESC on 11/20/2008
-- xCLIENT_ID must be replaced by the client_id intended to use the BMPs
--
-- Author: espringer
-----------------------------------------------------------------------------


DECLARE @CAT_ID AS INT;

-- Photo Documentation insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Photo Documentation', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Photo1', 'Photo1', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Photo2', 'Photo2', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Perimeter Control insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Perimeter Control', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Silt Fence', 'Standard Silt Fence', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Berm', 'Minimum 2 ft high Perimeter Berm w/ weepers at low points', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Composite Silt Sock', 'Composite Silt Sock', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Velocity Check insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Velocity Check', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Stone Check Dam', 'Min 2 ft high, 3"-6" RIP RAP w/ 1 ft thickness of 2" on upstream side', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Bale Ditch Check', 'Temporary Erosion Bale Ditch Check', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Sediment Trap insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Sediment Trap', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Sediment Basin', 'Temporary or Permenent Basin used for sediment control during construction', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Polymer Application', 'Polymer application', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Inlet Protection insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Inlet Protection', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Inlet Protection', 'Standard Inlet Protection', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Filter Fabric Insert', 'Filter fabric insert', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Filter Fabric Fence', 'Filter fabric fence', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Straw Bale Fence', 'Straw bale fence', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)


-- Construction Entrance insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Construction Entrance', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Rock Construction/Tracking Pads', 'Rock construction or tracking pads', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Sweeping/Cleaning', 'Sweeping or cleaning', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Channel insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Channel', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Straw Bales', 'Straw Bales', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Fiber Logs/Rolls/Fiber Bags', 'Fiber logs, rolls, or filter bags', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Sodding', 'Sodding', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Sod Ditch Liner', 'Sod Ditch Liner', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Stone', 'Stone', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Channel Liner insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Channel Liner', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Sod', 'Sod', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Seed/Mulch', 'Seed or mulch', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Erosion Control Mats', 'Erosion Control Mats', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Stone', 'Stone', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Articulated Concrete Block', 'Articulated concrete block', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Clean Water Diversion insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Clean Water Diversion', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Berm', 'Berm', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Pipe Bypass', 'Pipe Bypass', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)




-- Temporary Slope Stabilization insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Temporary Slope Stabilization', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Polymer Application', 'Polymer Application', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Silt Fence', 'Silt Fence', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Seeding - Temporary', 'Seeding (temporary)', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Erosion Control Mat', 'Erosion Control Mat', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Waterway / Waterbody Protection

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Waterway / Waterbody Protection', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Silt Curtain', 'Install Silt Curtain per the erosion control plan meeting WDNR SOC Standard 1070 for Silt Curtains.', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Pumping insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Pumping', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Excavation Dewatering', 'Dewatering excavation per WDNR SOC Standard 1061.  Note that pumping rates in excess of 70 gallons/minute require a pumping permit from the WDNR.', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Dust Control insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Dust Control', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Haul Road Wetting', 'Water haul road as needed to control dust', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Polymer/Water Application', 'Water & Polymer blend to control dust', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)



-- Ground Cover insertion

INSERT INTO EC_BMP_CATEGORY
(CLIENT_ID, NAME, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, 'Ground Cover', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

SET @CAT_ID = SCOPE_IDENTITY();

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Heavy Mulching', 'Heavy woodchip mulching (4 to 6 inches thick) over areas exposed to construction traffic', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Phased Seeding', 'Phased seeding and/or restoration', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

INSERT INTO EC_BMP
(CLIENT_ID, BMP_CATEGORY_ID, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID, STATUS_CD)
VALUES
(xCLIENT_ID, @CAT_ID, 'Stone Ground Cover', 'Clear stone provided for ground protection in work areas.', '11/20/2008 12:12:12 PM', '11/20/2008 12:12:12 PM', 4, 1)

