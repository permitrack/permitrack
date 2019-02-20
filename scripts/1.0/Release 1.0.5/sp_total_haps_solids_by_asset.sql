

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_total_haps_solids_by_asset]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_total_haps_solids_by_asset]
GO

CREATE PROCEDURE dbo.sp_total_haps_solids_by_asset
	-- Required Parameters
	@startDate varchar(30) = NULL,
	@endDate varchar(30) = NULL,
	@assetId int = NULL
AS

SELECT
	GALLONS.A_ID AS A_ID,
	COALESCE(SUM( GALLONS.LBS_HAPS ), 0) AS SUM_LBS_HAPS,
	COALESCE(SUM( GALLONS.SOLIDS_VOLUME ), 0) AS SUM_SOLIDS_VOLUME
	FROM (
		SELECT
			TOTALS.A_ID,
			CASE TOTALS.UM WHEN 1 THEN ( TOTALS.READING * TOTALS.LBS_HAPS ) -- gallons, no multiplier
				WHEN 2 THEN ( TOTALS.READING * 0.0078125 * TOTALS.LBS_HAPS ) -- 1 US fluid ounce = 0.0078125 US gallons
				WHEN 3 THEN ( TOTALS.READING * 0.264172052 * TOTALS.LBS_HAPS ) -- 1 liters = 0.264172052 US gallons
			END LBS_HAPS,
			CASE TOTALS.UM WHEN 1 THEN ( TOTALS.READING * TOTALS.PCT_SOLIDS_VOLUME ) -- gallons, no multiplier
				WHEN 2 THEN ( TOTALS.READING * 0.0078125 * TOTALS.PCT_SOLIDS_VOLUME ) -- 1 US fluid ounce = 0.0078125 US gallons
				WHEN 3 THEN ( TOTALS.READING * 0.264172052 * TOTALS.PCT_SOLIDS_VOLUME ) -- 1 liters = 0.264172052 US gallons
			END SOLIDS_VOLUME
		FROM (
			SELECT
				asset.id as A_ID,
				usage.METER_READING as READING,
				usage.UNIT_OF_MEASURE_CD as UM,
				source.LBS_HAPS as LBS_HAPS,
				source.PCT_SOLIDS_VOLUME as PCT_SOLIDS_VOLUME
			FROM ENV_ASSET as asset
			 INNER JOIN ENV_ASSET_SOURCE as aSo
			  ON aso.ASSET_ID = asset.ID
			 INNER JOIN ENV_SOURCE_USAGE as usage
			  ON usage.ASSET_SOURCE_ID = aSo.ID
			 INNER JOIN ENV_SOURCE as source
			  ON aSo.SOURCE_ID = source.ID
			INNER JOIN ENV_ASSET_TYPE as assetType ON assetType.ASSET_ID = asset.id
			WHERE asset.id = @assetId
			AND asset.status_cd = 1
			AND assetType.ASSET_TYPE_CD = 1
			AND source.LBS_HAPS_UM = 6 -- lbs/gal
			AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
			AND usage.PERIOD_START_TS >= @startDate
			AND usage.PERIOD_END_TS <= @endDate
			GROUP BY asset.id, usage.METER_READING, usage.UNIT_OF_MEASURE_CD, source.LBS_HAPS, source.PCT_SOLIDS_VOLUME 
		) AS TOTALS GROUP BY TOTALS.A_ID, TOTALS.READING, TOTALS.LBS_HAPS, TOTALS.UM, TOTALS.PCT_SOLIDS_VOLUME
	) AS GALLONS GROUP BY GALLONS.A_ID

GO
