

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_total_voc_month_for_asset]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_total_voc_month_for_asset]
GO

CREATE PROCEDURE dbo.sp_total_voc_month_for_asset
	-- Required Parameters
	@startDate varchar(30) = NULL,
	@endDate varchar(30) = NULL,
	@assetId int = NULL
AS

DECLARE @result float(8)

SELECT
    @result = COALESCE(SUM( GALLONS.LBS_OF_VOC ),0)
FROM (
SELECT
 CASE TOTALS.UM WHEN 1 THEN ( LBS_OF_VOC ) -- gallons, no multiplier
		WHEN 2 THEN ( LBS_OF_VOC * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
		WHEN 3 THEN ( LBS_OF_VOC * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
	END LBS_OF_VOC
FROM (
	SELECT ( usage.METER_READING * source.LBS_VOC ) AS LBS_OF_VOC,
		usage.UNIT_OF_MEASURE_CD AS UM
	FROM ENV_ASSET as asset
	 INNER JOIN ENV_ASSET_SOURCE as aSo
	  ON aso.ASSET_ID = asset.ID
	 INNER JOIN ENV_SOURCE_USAGE as usage
	  ON usage.ASSET_SOURCE_ID = aSo.ID
	 INNER JOIN ENV_SOURCE as source
	  ON aSo.SOURCE_ID = source.ID
	 INNER JOIN ENV_ASSET_TYPE as assetType 
	  ON assetType.ASSET_ID = asset.id
	WHERE asset.id = @assetId
	AND asset.status_cd = 1
	AND assetType.ASSET_TYPE_CD = 1
	AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
	AND usage.PERIOD_START_TS >= @startDate
	AND usage.PERIOD_END_TS <= @endDate
	GROUP BY usage.UNIT_OF_MEASURE_CD, usage.METER_READING, source.LBS_VOC ) AS TOTALS
) AS GALLONS

RETURN @result

GO