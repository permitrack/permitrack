

/****** Object:  UserDefinedFunction [dbo].[Liquids_totals]    Script Date: 02/17/2013 12:04:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Liquids_totals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Liquids_totals]
GO



/****** Object:  UserDefinedFunction [dbo].[Liquids_totals]    Script Date: 02/17/2013 12:04:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE FUNCTION [dbo].[Liquids_totals] (@startDate    VARCHAR(30) = NULL 
                                        , @endDate    VARCHAR(30) = NULL 
                                        , @facilityId INT = NULL 
                                        , @assetId    INT = NULL
                                        , @sourceId    INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (
		SELECT  asset_id 
              , number 
              , asset_name 
              , MAX(RATING) AS RATING
              , MAX(LBS_OF_VOC) AS LBS_OF_VOC
              
           FROM   Liquids_subtotals(@StartDate, @EndDate, @facilityId, @assetId, @sourceId, NULL) 
        
				   
		   GROUP BY asset_id 
					 , asset_name 
					 , number
		
		)

GO


