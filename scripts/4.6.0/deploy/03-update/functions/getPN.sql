
/****** Object:  UserDefinedFunction [dbo].[GetPN]    Script Date: 02/16/2013 14:43:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER FUNCTION [dbo].[getPN] (@substance_id INT 
                              , @startDate  VARCHAR(30) = '01/01/1900' 
                              , @endDate    VARCHAR(30) = '12/31/2099') 
returns VARCHAR(2000) 
AS 
  BEGIN 
      DECLARE @result AS VARCHAR(2000) 

      SET @result = (SELECT DISTINCT process.process_number + ', ' 
                     FROM   dbo.env_process_asset AS prcass 
                            INNER JOIN dbo.env_process AS process 
                                    ON process.id = prcass.process_id 
                            INNER JOIN dbo.env_asset_source assetSource 
                                    ON assetSource.asset_id = prcass.asset_id 
                            INNER JOIN dbo.env_source_substance sourceSubstance 
                                    ON sourceSubstance.source_id = 
                                       assetSource.source_id 
                            INNER JOIN env_source_usage AS usage 
                                    ON usage.asset_source_id = assetSource.id 
                            INNER JOIN env_source AS source 
                                    ON assetSource.source_id = source.id 
                     WHERE  sourceSubstance.substance_id = @substance_id 
                        AND prcass.status_cd = '1' 
                        AND assetSource.status_cd = '1' 
                        AND sourceSubstance.status_cd = '1' 
                        AND usage.unit_of_measure_cd IN ( 1, 2, 3 ) 
                        AND usage.period_start_ts >= @startDate 
                        AND usage.period_end_ts <= @endDate 
                        AND source.density_um = 6 
                     FOR xml path('')) 
      SET @result = Substring(@result, 0, Len(@result)) 

      RETURN @result 
  END 