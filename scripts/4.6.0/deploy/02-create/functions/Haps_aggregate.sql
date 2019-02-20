

/****** Object:  UserDefinedFunction [dbo].[Haps_aggregate]    Script Date: 02/20/2013 12:36:42 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Haps_aggregate]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Haps_aggregate]
GO



/****** Object:  UserDefinedFunction [dbo].[Haps_aggregate]    Script Date: 02/20/2013 12:36:42 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Haps_aggregate] (@startDate    VARCHAR(30) = NULL 
                             , @endDate    VARCHAR(30) = NULL 
                             , @assetId    INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (
		SELECT  usage.ID as usage_id,
				asset.id as A_ID,
				usage.METER_READING as READING,
				usage.UNIT_OF_MEASURE_CD as UM,
				CASE source.LBS_HAPS_UM WHEN 6 THEN
				source.LBS_HAPS ELSE 0 END as LBS_HAPS,
				source.PCT_SOLIDS_VOLUME as PCT_SOLIDS_VOLUME
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
			--AND source.LBS_HAPS_UM = 6 -- lbs/gal
			AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
			AND usage.PERIOD_START_TS >= @startDate
			AND usage.PERIOD_END_TS <= @endDate
			AND source.DISPLAY_NAME COLLATE Latin1_General_CS_AS NOT LIKE 'AER %'
			--GROUP BY asset.id, usage.METER_READING, usage.UNIT_OF_MEASURE_CD, source.LBS_HAPS
		) 


GO


