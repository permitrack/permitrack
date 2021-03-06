
/****** Object:  StoredProcedure [dbo].[sp_subst_haps_by_asset]    Script Date: 02/16/2013 14:21:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Sp_subst_haps_by_asset] 
  -- Required Parameters 
  @startDate VARCHAR(30) = NULL 
  , @endDate VARCHAR(30) = NULL 
  , @assetId INT = NULL 
AS 
    SELECT TOTALS.source_id     AS SOURCE_ID 
           , TOTALS.source_name AS SOURCE_NAME 
           , TOTALS.name        AS SUBST_NAME 
           , TOTALS.part_num    AS SUBST_NUM 
           , CASE TOTALS.um 
               WHEN 1 THEN ( haps ) -- gallons, no multiplier 
               WHEN 2 THEN ( haps * 0.0078125 ) 
               -- 1 US fluid ounce = 0.0078125 US gallons 
               WHEN 3 THEN ( haps * 0.264172052 ) 
             -- 1 liters = 0.264172052 US gallons 
             END                HAPS 
    FROM   Haps(@startDate, @endDate, NULL, NULL, @assetId) AS TOTALS 