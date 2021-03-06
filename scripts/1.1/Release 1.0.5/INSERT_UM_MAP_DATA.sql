-- ENV_PERMIT_DETAIL
INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_PERMIT_DETAIL', 'AVG_PERIOD_UM', 11) -- months

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_PERMIT_DETAIL', 'VOC_LIMIT_UM', 4) -- tons

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_PERMIT_DETAIL', 'HAPS_LIMIT_UM', 6) --lbs/gal

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_PERMIT_DETAIL', 'MMBTU_LIMIT_UM', 8) -- lbs/mmcf


-- ENV_SOURCE_USAGE
INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE_USAGE', 'UNIT_OF_MEASURE_CD', 1) -- gallons

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE_USAGE', 'UNIT_OF_MEASURE_CD', 2) -- US ounces

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE_USAGE', 'UNIT_OF_MEASURE_CD', 3) -- liters


-- ENV_SOURCE_SUBSTANCE
INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE_SUBSTANCE', 'UNIT_OF_MEASURE_CD', 6) --lbs/gal


-- ENV_SOURCE
INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE', 'LBS_VOC_UM', 6) -- lbs/gal

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE', 'DENSITY_UM', 6) -- lbs/gal

INSERT INTO ENV_UNIT_OF_MEASURE_MAP
(CLIENT_ID, TABLE_NAME, COLUMN_NAME, UM_CD)
VALUES
( 0, 'ENV_SOURCE', 'LBS_HAPS_UM', 6) --lbs/gal