
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_all_haps_for_client]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_all_haps_for_client]
GO

CREATE PROCEDURE dbo.sp_all_haps_for_client
	-- Required Parameters
	@startDate varchar(30) = NULL,
	@endDate varchar(30) = NULL,
	@clientId int = NULL
AS

-- substance levels by client's assets and sources
-- inputs: client_id and date range
SELECT
	GRAND_TOTALS.ID AS SUBST_ID,
	GRAND_TOTALS.NAME AS SUBST_NAME,
	GRAND_TOTALS.PART_NUM AS SUBST_NUM,
	SUM(GRAND_TOTALS.HAPS) AS HAPS
FROM (
 SELECT
	TOTALS.ID,
        TOTALS.NAME, 
	TOTALS.PART_NUM,
  CASE  TOTALS.UM WHEN 1 THEN ( HAPS ) -- gallons, no multiplier
		WHEN 2 THEN ( HAPS * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
		WHEN 3 THEN ( HAPS * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
	END HAPS
 FROM (
	select 	
		subst.ID,
                subst.NAME, subst.PART_NUM,
		( usage.METER_READING * source.DENSITY * sosub.RATIO) AS HAPS,
		usage.UNIT_OF_MEASURE_CD AS UM
	FROM ENV_ASSET as asset
	 INNER JOIN ENV_ASSET_SOURCE as aSo
	  ON aso.ASSET_ID = asset.ID
	 INNER JOIN ENV_SOURCE_USAGE as usage
	  ON usage.ASSET_SOURCE_ID = aSo.ID
	 INNER JOIN ENV_SOURCE as source
	  ON aSo.SOURCE_ID = source.ID
         INNER JOIN ENV_SOURCE_SUBSTANCE as sosub
          ON source.ID = sosub.SOURCE_ID
          AND sosub.STATUS_CD = 1
         INNER JOIN ENV_SUBSTANCE as subst
          ON sosub.SUBSTANCE_ID = subst.ID
          AND subst.STATUS_CD = 1
	 INNER JOIN ENV_ASSET_TYPE as assetType 
	  ON assetType.ASSET_ID = asset.id
	WHERE asset.client_id = @clientId -- clientId
	AND asset.status_cd = 1
	AND assetType.ASSET_TYPE_CD = 1
	AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
	AND usage.PERIOD_START_TS >= @startDate
	AND usage.PERIOD_END_TS <= @endDate
	AND source.DENSITY_UM = 6
 ) AS TOTALS 
) AS GRAND_TOTALS GROUP BY GRAND_TOTALS.ID, GRAND_TOTALS.NAME, GRAND_TOTALS.PART_NUM

GO