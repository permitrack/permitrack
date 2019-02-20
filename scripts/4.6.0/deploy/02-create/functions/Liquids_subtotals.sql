

/****** Object:  UserDefinedFunction [dbo].[Liquids_subtotals]    Script Date: 02/23/2013 16:50:51 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Liquids_subtotals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Liquids_subtotals]
GO



/****** Object:  UserDefinedFunction [dbo].[Liquids_subtotals]    Script Date: 02/23/2013 16:50:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[Liquids_subtotals] (@startDate    VARCHAR(30) = NULL 
                                        , @endDate    VARCHAR(30) = NULL 
                                        , @facilityId INT = NULL 
                                        , @assetId    INT = NULL
                                        , @sourceId    INT = NULL
                                        , @controlUsageId INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (		
		SELECT GALLONS.asset_id 
			  , GALLONS.number 
			  , GALLONS.asset_name 
			  , GALLONS.CONTROL_SUBSTANCE_TYPE_CD
			  , COALESCE(Sum(GALLONS.GALLONS), 0) AS RATING -- usage in gallons 
			  , COALESCE(Sum(GALLONS.GALLONS * MALFUNCTION_PERCENT) , 0) AS MALFUNCTION_RATING -- amount affected by malfunction
			  , COALESCE(Sum(GALLONS.GALLONS * SOURCE_LBS_VOC), 0) AS LBS_OF_VOC
			  , COALESCE(Sum(GALLONS.GALLONS * GALLONS.TRANSFER_RATE), 0) AS LBS_TRANSFERRED
		   FROM   Liquids(@StartDate, @EndDate, @facilityId, @assetId, @sourceId, @controlUsageId) 
			 AS GALLONS    
		   GROUP  BY asset_id 
					 , asset_name 
					 , number
					 , CONTROL_SUBSTANCE_TYPE_CD
		) 

GO


