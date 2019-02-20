

USE cap2
GO

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
	TOTALS.A_ID AS A_ID,
	COALESCE(SUM( TOTALS.LBS_HAPS ), 0) AS SUM_LBS_HAPS,
	COALESCE(SUM( TOTALS.SOLIDS_VOLUME ), 0) AS SUM_SOLIDS_VOLUME
FROM (
	SELECT asset.id AS A_ID,
		( usage.METER_READING * source.LBS_HAPS ) AS LBS_HAPS,
	    ( usage.METER_READING * source.PCT_SOLIDS_VOLUME ) AS SOLIDS_VOLUME
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
	AND usage.UNIT_OF_MEASURE_CD = 1 -- gal
	AND usage.PERIOD_START_TS >= @startDate
	AND usage.PERIOD_END_TS <= @endDate
	GROUP BY asset.id, usage.METER_READING, source.LBS_HAPS, source.PCT_SOLIDS_VOLUME ) AS TOTALS
GROUP BY TOTALS.A_ID

GO
