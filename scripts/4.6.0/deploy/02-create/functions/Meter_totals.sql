

/****** Object:  UserDefinedFunction [dbo].[Meter_totals]    Script Date: 02/17/2013 12:04:34 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Meter_totals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Meter_totals]
GO



/****** Object:  UserDefinedFunction [dbo].[Meter_totals]    Script Date: 02/17/2013 12:04:34 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Meter_totals] (@startDate  VARCHAR(30) = NULL, 
                                        @endDate    VARCHAR(30) = NULL,
                                        @meterId    INT         = NULL) 
RETURNS TABLE 
AS
RETURN 
(

		SELECT

			SUM(usage.METER_READING) AS METER_USAGE_READING
		FROM ENV_ASSET as asset
		 INNER JOIN ENV_ASSET_SOURCE as aSo
		  ON aso.ASSET_ID = asset.ID
		 INNER JOIN ENV_SOURCE_USAGE as usage
		  ON usage.ASSET_SOURCE_ID = aSo.ID
		 INNER JOIN ENV_SOURCE as source
		  ON aSo.SOURCE_ID = source.ID
		WHERE asset.id = @meterId -- meter id
		AND usage.UNIT_OF_MEASURE_CD = 5 -- MmCF
		AND usage.PERIOD_START_TS >= @StartDate
		AND usage.PERIOD_END_TS <= @EndDate
	
	
			
)




GO


