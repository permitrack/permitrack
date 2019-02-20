

/****** Object:  UserDefinedFunction [dbo].[Liquids]    Script Date: 02/17/2013 12:04:03 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Liquids]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Liquids]
GO


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[Liquids] (@startDate    VARCHAR(30) = NULL 
                             , @endDate    VARCHAR(30) = NULL 
                             , @facilityId INT = NULL
                             , @assetId    INT = NULL
                             , @sourceId    INT = NULL
                             , @controlUsageId INT = NULL) 
RETURNS TABLE 
AS
RETURN 
(
	SELECT asset.id as ASSET_ID, 
			asset.name as ASSET_NAME,
			asset.number as NUMBER,
			source.ID AS SOURCE_ID,
			source.DISPLAY_NAME AS SOURCE_NAME,
			source.LBS_VOC AS SOURCE_LBS_VOC,
			--usage.METER_READING as READING,
			
			CASE usage.UNIT_OF_MEASURE_CD
				WHEN 1 THEN ( usage.METER_READING ) 
				-- gallons, no multiplier 
				WHEN 2 THEN ( usage.METER_READING * 0.0078125 ) 
				-- 1 US fluid ounce = 0.0078125 US gallons 
				WHEN 3 THEN ( usage.METER_READING * 0.264172052 ) 
	            -- 1 liters = 0.264172052 US gallons 
            END GALLONS,
			
			usage.UNIT_OF_MEASURE_CD as UM,
			usage.PERIOD_START_TS as USAGE_START,
			usage.PERIOD_END_TS as USAGE_END,
			1 - COALESCE(COALESCE(usage.TRANSFER_RATE, assetAttributes.TRANSFER_RATE), 0.55) AS TRANSFER_RATE,
			control1.SUBSTANCE_TYPE_CD as CONTROL_SUBSTANCE_TYPE_CD,
			COALESCE(
				SUM(
					CAST(DATEDIFF(s, 
							 CASE 
   								WHEN controlUsage.PERIOD_START_TS <= usage.PERIOD_START_TS THEN usage.PERIOD_START_TS 
								WHEN controlUsage.PERIOD_START_TS > usage.PERIOD_START_TS THEN controlUsage.PERIOD_START_TS
							END, 
							CASE 
								WHEN controlUsage.PERIOD_END_TS >= usage.PERIOD_END_TS THEN usage.PERIOD_END_TS
								WHEN controlUsage.PERIOD_END_TS < usage.PERIOD_END_TS THEN controlUsage.PERIOD_END_TS
							END)  AS FLOAT)
							)
					/ CAST(DATEDIFF(s, usage.PERIOD_START_TS, usage.PERIOD_END_TS) AS FLOAT)
				, 0)
			as MALFUNCTION_PERCENT  

		FROM env_source_usage AS usage 
		  INNER JOIN env_asset_source AS assetSource 
				  ON usage.asset_source_id = assetSource.id 
		  INNER JOIN env_source AS source 
				  ON assetSource.source_id = source.id 
		  INNER JOIN env_asset AS asset 
				  ON asset.id = assetSource.asset_id 
		  INNER JOIN env_asset_type AS assetType 
				  ON assetType.asset_id = asset.id 
		  LEFT JOIN env_asset_attr AS assetAttributes
				  ON assetAttributes.asset_id = asset.id 
		  LEFT JOIN env_process_asset AS processAsset 
				 ON processAsset.asset_id = asset.id 
		  INNER JOIN env_process AS process 
				  ON process.id = processAsset.process_id 
		  INNER JOIN env_facility_asset AS facilityAsset 
				  ON facilityAsset.asset_id = asset.id 
		  INNER JOIN env_facility AS facility 
				  ON facility.id = facilityAsset.facility_id 
		  LEFT JOIN ENV_ASSET_EMITTED_SUBSTANCE_TYPE control1 
				on control1.ASSET_ID = asset.ID
		  LEFT JOIN ENV_CONTROL_USAGE controlUsage 			     
					on controlUsage.ASSET_EMITTED_SUBSTANCE_TYPE_ID = control1.ID
					AND ((    controlUsage.PERIOD_START_TS >= usage.PERIOD_START_TS
					      AND controlUsage.PERIOD_START_TS <= usage.PERIOD_END_TS)
					  OR (    controlUsage.PERIOD_END_TS >= usage.PERIOD_START_TS
					      AND controlUsage.PERIOD_END_TS <= usage.PERIOD_END_TS))
			WHERE facility.id = Isnull(@facilityId, facility.id)
				AND asset.id = Isnull(@assetId, asset.id)
				AND source.id = Isnull(@sourceId, source.id)
				AND isnull(@controlUsageId, 0) = CASE WHEN @controlUsageId IS NULL THEN 0 ELSE controlUsage.ID END
				AND assetType.ASSET_TYPE_CD = 1
				AND source.source_type_cd = 1 -- paint sources
				AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
				AND usage.PERIOD_START_TS >= @StartDate
				AND usage.PERIOD_END_TS <= @EndDate
			GROUP BY
				asset.id , 
				asset.name ,
				asset.number ,
				source.id,
				source.DISPLAY_NAME,
				source.LBS_VOC,
				usage.ID,
				usage.METER_READING ,
				usage.UNIT_OF_MEASURE_CD ,
				usage.PERIOD_START_TS ,
				usage.PERIOD_END_TS, 
				COALESCE(COALESCE(usage.TRANSFER_RATE, assetAttributes.TRANSFER_RATE), 0.55), 
				control1.SUBSTANCE_TYPE_CD
)

GO


