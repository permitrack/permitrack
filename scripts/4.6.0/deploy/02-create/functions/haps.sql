

/****** Object:  UserDefinedFunction [dbo].[Haps]    Script Date: 02/17/2013 12:05:21 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Haps]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Haps]
GO



/****** Object:  UserDefinedFunction [dbo].[Haps]    Script Date: 02/17/2013 12:05:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[Haps] (
       @startDate    VARCHAR(30) = NULL 
     , @endDate    VARCHAR(30) = NULL 
     , @clientId   INT = NULL 
     , @facilityId INT = NULL 
     , @assetId    INT = NULL) 
returns TABLE 
AS 
    RETURN 
    (
      
      -- TO DO: This should SELECT FROM Liquids()
      
      SELECT asset.id  AS  asset_id 
              , asset.name  AS  asset_name 
              , source.id  AS  source_id 
              , source.display_name  AS source_name 
              , subst.id 
              , subst.name 
              , subst.part_num 
              , ( usage.meter_reading * source.density * sourceSubstance.ratio )  AS HAPS 
              , usage.unit_of_measure_cd AS UM 
       FROM   env_source_usage AS usage 
              INNER JOIN env_asset_source AS assetSource 
                      ON usage.asset_source_id = assetSource.id 
              INNER JOIN env_source AS source 
                      ON assetSource.source_id = source.id 
              INNER JOIN env_source_substance AS sourceSubstance 
                      ON source.id = sourceSubstance.source_id 
              INNER JOIN env_substance AS subst 
                      ON sourceSubstance.substance_id = subst.id 
              INNER JOIN env_asset AS asset 
                      ON asset.id = assetSource.asset_id 
              INNER JOIN env_asset_type AS assetType 
                      ON assetType.asset_id = asset.id 
              LEFT JOIN env_process_asset AS processAsset 
                     ON processAsset.asset_id = asset.id 
						AND processAsset.status_cd = 1 
              INNER JOIN env_process AS process 
                      ON process.id = processAsset.process_id 
              INNER JOIN env_facility_asset AS facilityAsset 
                      ON facilityAsset.asset_id = asset.id 
              INNER JOIN env_facility AS facility 
                      ON facility.id = facilityAsset.facility_id 
       WHERE  facility.client_id = Isnull(@clientId, facility.client_id) 
          AND facility.id = Isnull(@facilityId, facility.id) 
          AND asset.id = Isnull(@assetId, asset.id) 
          AND sourceSubstance.status_cd = 1 
          AND subst.status_cd = 1 
          AND subst.substance_type_cd = 2 -- HAPs -- bug fix
          AND asset.status_cd = 1 
          AND assetType.asset_type_cd = 1 -- PAINT
          AND usage.unit_of_measure_cd IN ( 1, 2, 3 ) -- GALLONS, etc.
          AND usage.period_start_ts >= @startDate 
          AND usage.period_end_ts <= @endDate 
          AND source.density_um = 6
    ) 
    
GO


