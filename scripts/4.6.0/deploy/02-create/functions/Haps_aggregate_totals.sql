

/****** Object:  UserDefinedFunction [dbo].[Haps_aggregate_totals]    Script Date: 02/20/2013 12:40:46 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Haps_aggregate_totals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Haps_aggregate_totals]
GO



/****** Object:  UserDefinedFunction [dbo].[Haps_aggregate_totals]    Script Date: 02/20/2013 12:40:46 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




CREATE FUNCTION [dbo].[Haps_aggregate_totals] (@startDate  VARCHAR(30) = NULL, 
                                     @endDate    VARCHAR(30) = NULL, 
                                     @assetId    INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (
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
				FROM (SELECT * FROM
					Haps_aggregate(@startDate, @endDate, @assetId)
				) AS TOTALS GROUP BY TOTALS.A_ID, TOTALS.READING, TOTALS.LBS_HAPS, TOTALS.PCT_SOLIDS_VOLUME, TOTALS.UM
			) AS GALLONS GROUP BY GALLONS.A_ID
		) 




GO


