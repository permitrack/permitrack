

/****** Object:  UserDefinedFunction [dbo].[Tanks]    Script Date: 02/18/2013 09:13:28 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tanks]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Tanks]
GO



/****** Object:  UserDefinedFunction [dbo].[Tanks]    Script Date: 02/18/2013 09:13:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Tanks] (@startDate    VARCHAR(30) = NULL 
                               , @endDate    VARCHAR(30) = NULL 
                               , @facilityId INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (SELECT asset.id AS ASSET_ID 
       FROM   env_facility_asset AS fac 
              INNER JOIN env_asset AS asset 
                      ON asset.id = fac.asset_id 
              INNER JOIN env_asset_source AS aSo 
                      ON aso.asset_id = asset.id 
              INNER JOIN env_source_usage AS usage 
                      ON usage.asset_source_id = aSo.id 
              INNER JOIN env_source AS source 
                      ON aSo.source_id = source.id 
              INNER JOIN env_asset_type AS assetType 
                      ON assetType.asset_id = asset.id 
       WHERE  fac.facility_id = @facilityId 
          AND asset.status_cd = 1 
          AND assetType.asset_type_cd = 3 -- bulk liquid   
          AND source.source_type_cd = 3 -- bulk liquid   
          AND usage.period_start_ts >= @StartDate 
          AND usage.period_end_ts <= @EndDate 
       GROUP  BY asset.id) 


GO


