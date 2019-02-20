

/****** Object:  UserDefinedFunction [dbo].[Tanks_liquids]    Script Date: 02/18/2013 09:13:12 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tanks_liquids]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Tanks_liquids]
GO



/****** Object:  UserDefinedFunction [dbo].[Tanks_liquids]    Script Date: 02/18/2013 09:13:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[Tanks_liquids] (@startDate   VARCHAR(30) = NULL 
									 , @endDate    VARCHAR(30) = NULL 
									 , @clientId   INT = NULL 
									 , @assetId    INT = NULL
									 , @type       VARCHAR(30)) 
returns TABLE 
AS 
	
    RETURN 
      (
      
		  SELECT SCC.scc_number 
				   , ss.bl_em_factor 
				   , asset.ID as asset_id
				   , asset.NUMBER   AS PN
				   , asset.NAME AS ASSET_NAME
				   , source.ID                  AS SOURCE_ID
				   , source.DISPLAY_NAME         as SOURCE_NAME
				   , usage.meter_reading        AS READING 
				   , usage.unit_of_measure_cd   AS UM 
				   , aa.tank_capacity 
				   , CASE usage.unit_of_measure_cd 
                         WHEN 1 THEN ( usage.meter_reading ) -- gallons, no multiplier  
                         WHEN 2 THEN ( usage.meter_reading * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons  
                         WHEN 3 THEN ( usage.meter_reading * 0.264172052 ) -- 1 liters = 0.264172052 US gallons  
                     END AS USAGE 
                   , bl_em_factor * ( aa.tank_capacity / 1000 ) AS CAPACITY_TOTAL
                   , CASE WHEN SCC.id IN (SELECT id FROM Scc_tanks('breathing')) THEN 1 ELSE 0 END AS breathing
                   , CASE WHEN SCC.id IN (SELECT id FROM Scc_tanks('working')) THEN 1 ELSE 0 END AS working
                   , CASE WHEN SCC.id IN (SELECT id FROM Scc_tanks('loading')) THEN 1 ELSE 0 END AS loading
			FROM   env_scc_info AS SCC 
				   INNER JOIN env_substance_scc_info AS subSCC -- Not sure what this is doing. Same results with RIGHT join or leaving out altogether.
						   ON subSCC.scc_info_id = SCC.id 
				   INNER JOIN env_source_substance AS ss 
						   ON ss.substance_id = subSCC.substance_id 
				   INNER JOIN env_asset_source AS asso 
						   ON asso.source_id = ss.source_id 
				   INNER JOIN env_source_usage AS usage 
						   ON usage.asset_source_id = asso.id 
				   INNER JOIN env_source AS source
				           ON source.ID = asso.SOURCE_ID
				   INNER JOIN env_asset AS asset
				           ON asset.ID = asso.ASSET_ID
				   LEFT JOIN env_asset_attr AS aa 
						  ON aa.asset_id = asso.asset_id 
			WHERE  SCC.id IN (SELECT id 
							  FROM   Scc_tanks(@type)) 
			   AND SCC.client_id = @clientId 
			   AND ss.status_cd = 1 
			   AND ss.bl_ef_unit = 7 
			   AND asso.asset_id = isnull(@assetId, asso.asset_id)
			   AND asso.status_cd = 1 
			   AND usage.period_start_ts >= @startDate 
			   AND usage.period_end_ts <= @endDate 
			   AND usage.unit_of_measure_cd IN ( 1, 2, 3 )  -- gallons, ounces, liters
                             
        )
GO


