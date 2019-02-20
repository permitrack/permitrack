-- change float fields to decimal

ALTER TABLE ENV_PERMIT_DETAIL
ALTER COLUMN VOC_LIMIT [decimal](18, 6) NULL;

ALTER TABLE ENV_PERMIT_DETAIL
ALTER COLUMN HAPS_LIMIT [decimal](18, 6) NULL;

ALTER TABLE ENV_PERMIT_DETAIL
ALTER COLUMN MMBTU_LIMIT [decimal](18, 6) NULL;


ALTER TABLE ENV_SOURCE_SUBSTANCE
ALTER COLUMN RATIO [decimal](18, 6) NULL;


ALTER TABLE ENV_SOURCE_USAGE
ALTER COLUMN METER_READING [decimal](18, 6) NULL;


ALTER TABLE ENV_ASSET_ATTR
ALTER COLUMN EP_RATED_MMBTU [decimal](18, 6) NULL;

ALTER TABLE ENV_ASSET_ATTR
ALTER COLUMN EP_CAPACITY_MMBTU [decimal](18, 6) NULL;

ALTER TABLE ENV_ASSET_ATTR
ALTER COLUMN TANK_CAPACITY [decimal](18, 6) NULL;


ALTER TABLE ENV_SOURCE
ALTER COLUMN LBS_VOC [decimal](18, 6) NULL;

ALTER TABLE ENV_SOURCE
ALTER COLUMN DENSITY [decimal](18, 6) NULL;

ALTER TABLE ENV_SOURCE
ALTER COLUMN LBS_HAPS [decimal](18, 6) NULL;

ALTER TABLE ENV_SOURCE
ALTER COLUMN PCT_SOLIDS_VOLUME [decimal](18, 6) NULL;

ALTER TABLE ENV_SOURCE
ALTER COLUMN PCT_SOLIDS_WEIGHT [decimal](18, 6) NULL;