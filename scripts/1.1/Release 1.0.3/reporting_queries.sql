
-- "LBS of VOC" field from spreadsheet
-- "Gallons of Product" (usage METER_READING) * "VOC Content" (LBS_VOC)
-- calculated for an asset, grouped by source 
-- ***** NOTE: May want to add a source_type qualifier on here to JUST get the paint totals
SELECT SEPARATE_TOTALS.SOURCE_ID, SEPARATE_TOTALS.display_name, SUM(SEPARATE_TOTALS.LBS_OF_VOC) AS TOTAL_LBS_VOC
FROM (
SELECT	source.id as SOURCE_ID, source.display_name, (usage.METER_READING * source.LBS_VOC ) AS LBS_OF_VOC
	FROM ENV_ASSET as asset
	 INNER JOIN ENV_ASSET_SOURCE as aSo
	  ON aso.ASSET_ID = asset.ID
	 INNER JOIN ENV_SOURCE_USAGE as usage
	  ON usage.ASSET_SOURCE_ID = aSo.ID
	 INNER JOIN ENV_SOURCE as source
	  ON aSo.SOURCE_ID = source.ID
	WHERE asset.id = '16' --given asset_id parameter
	AND asset.status_cd = 1
	AND usage.UNIT_OF_MEASURE_CD = '1' -- gallons
	AND usage.PERIOD_START_TS >= '01-01-2009'
	AND usage.PERIOD_END_TS <= '12-31-2009'
GROUP BY source.id, source.display_name, usage.METER_READING, source.LBS_VOC ) AS SEPARATE_TOTALS
GROUP BY SEPARATE_TOTALS.SOURCE_ID, SEPARATE_TOTALS.display_name



-- Total VOC (LBS)
-- Sum of all LBS_OF_VOC fields for an asset (for all asset's sources)
-- ***** NOTE: May want to add a source_type qualifier on here to JUST get the paint totals
SELECT
TOTALS.ASSET_ID, SUM( TOTALS.LBS_OF_VOC ) AS GRAND_TOTAL
FROM (
	SELECT asset.id as asset_id, (usage.METER_READING * source.LBS_VOC ) AS LBS_OF_VOC
	FROM ENV_ASSET as asset
	 INNER JOIN ENV_ASSET_SOURCE as aSo
	  ON aso.ASSET_ID = asset.ID
	 INNER JOIN ENV_SOURCE_USAGE as usage
	  ON usage.ASSET_SOURCE_ID = aSo.ID
	 INNER JOIN ENV_SOURCE as source
	  ON aSo.SOURCE_ID = source.ID
	WHERE asset.id = '16' --given asset_id parameter
	AND asset.status_cd = 1
	AND usage.UNIT_OF_MEASURE_CD = '1' -- gallons
	AND usage.PERIOD_START_TS >= '01-01-2009'
	AND usage.PERIOD_END_TS <= '01-31-2009'
	GROUP BY asset.id, usage.METER_READING, source.LBS_VOC ) AS TOTALS
GROUP BY TOTALS.ASSET_ID


-- TOTAL HAPS
-- summation of HAPS within an asset's sources
-- across every asset for a given time period
-- grouped by substance
-- SUM ( Usage in gallons * Source.Density * source_substance.ratio )
SELECT TOTALS.id, TOTALS.name, SUM(TOTALS.HAPS) as ITEMIZED_TOTAL_HAPS
FROM (
SELECT substance.id, substance.name, (usage.meter_reading * source.density * sourcesub.ratio) AS HAPS
    FROM ENV_ASSET as asset
	INNER JOIN ENV_ASSET_SOURCE as aSo ON aso.ASSET_ID = asset.ID
	INNER JOIN ENV_SOURCE_USAGE as usage ON usage.ASSET_SOURCE_ID = aSo.ID
	INNER JOIN ENV_SOURCE as source ON aSo.SOURCE_ID = source.ID
	INNER JOIN ENV_SOURCE_SUBSTANCE as sourcesub ON source.ID = sourcesub.SOURCE_ID
	INNER JOIN ENV_SUBSTANCE as substance ON substance.ID = sourcesub.SUBSTANCE_ID
WHERE asset.CLIENT_ID = '682' --clientId parameter 
	AND asset.status_cd = 1
	AND usage.UNIT_OF_MEASURE_CD = '1' -- gallons
	AND usage.PERIOD_START_TS >= '01-01-2009'
	AND usage.PERIOD_END_TS <= '01-31-2009'
	AND source.status_cd = '1' ) AS TOTALS
GROUP BY TOTALS.id, TOTALS.name


-- GRAND TOTAL HAPS FOR GIVEN TIME PERIOD (as above, summed)
SELECT SUM(TOTALS.HAPS) as GRAND_TOTAL_HAPS
FROM (
SELECT substance.id, substance.name, (usage.meter_reading * source.density * sourcesub.ratio) AS HAPS
    FROM ENV_ASSET as asset
	INNER JOIN ENV_ASSET_SOURCE as aSo ON aso.ASSET_ID = asset.ID
	INNER JOIN ENV_SOURCE_USAGE as usage ON usage.ASSET_SOURCE_ID = aSo.ID
	INNER JOIN ENV_SOURCE as source ON aSo.SOURCE_ID = source.ID
	INNER JOIN ENV_SOURCE_SUBSTANCE as sourcesub ON source.ID = sourcesub.SOURCE_ID
	INNER JOIN ENV_SUBSTANCE as substance ON substance.ID = sourcesub.SUBSTANCE_ID
WHERE asset.CLIENT_ID = '682' --clientId parameter 
	AND asset.status_cd = 1
	AND usage.UNIT_OF_MEASURE_CD = '1' -- gallons
	AND usage.PERIOD_START_TS >= '01-01-2009'
	AND usage.PERIOD_END_TS <= '12-31-2009'
	AND source.status_cd = '1' ) AS TOTALS