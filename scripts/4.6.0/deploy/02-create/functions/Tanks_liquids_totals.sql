

/****** Object:  UserDefinedFunction [dbo].[Tanks_liquids_totals]    Script Date: 02/27/2013 16:51:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tanks_liquids_totals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Tanks_liquids_totals]
GO



/****** Object:  UserDefinedFunction [dbo].[Tanks_liquids_totals]    Script Date: 02/27/2013 16:51:35 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[Tanks_liquids_totals] 
(
       @startDate   VARCHAR(30) = NULL 
     , @endDate    VARCHAR(30) = NULL 
     , @clientId   INT = NULL 
     , @assetId    INT = NULL
     , @type       VARCHAR(30)
) 
returns TABLE 
AS 
	
    RETURN 
      (SELECT 	--scc_number 
				   --, 
				   asset_id
				   , pn
				   , asset_name
				   , SOURCE_ID
				   , SOURCE_NAME
				   --, READING 
				   --, UM 
				   --, tank_capacity 
				   , sum(bl_em_factor ) as factor
				    
                   , SUM(USAGE) / 1000 as gallons
                   , 
                       Sum(CASE WHEN breathing = 1 THEN capacity_total ELSE bl_em_factor * (USAGE) / 1000  END) 
                       
                       as LBS_VOC
                         
            FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, @type)
                  
                  GROUP  BY 
                   --scc_number, 
                    --bl_em_factor 
				   --, 
				   asset_id
				   , pn
				   , asset_name
                    , source_id
                    , source_name
                    --, bl_em_factor 
                    --, capacity_total
                    
                         )
GO


