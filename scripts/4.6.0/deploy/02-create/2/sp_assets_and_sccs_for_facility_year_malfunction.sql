

/****** Object:  StoredProcedure [dbo].[sp_assets_and_sccs_for_facility_year_malfunction]    Script Date: 02/22/2013 12:17:54 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_assets_and_sccs_for_facility_year_malfunction]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_assets_and_sccs_for_facility_year_malfunction]
GO



/****** Object:  StoredProcedure [dbo].[sp_assets_and_sccs_for_facility_year_malfunction]    Script Date: 02/22/2013 12:17:54 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE PROCEDURE [dbo].[sp_assets_and_sccs_for_facility_year_malfunction] 
  @yearInt      INT = NULL 
  , @facilityId INT = NULL 
AS 
    DECLARE @yearChar  VARCHAR(4), 
            @assetId   INT, 
            @StartDate VARCHAR(10), 
            @EndDate   VARCHAR(10), 
            @prcNum    VARCHAR(50) 

    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

    SELECT asset.id                 AS ASSET_ID 
           , asset.number           AS ASSET_NUMBER 
           , asset.name             AS ASSET_NAME 
           , PRC.process_number   AS PROC_NUM 
           , control1.ADDL_INFO AS CONTROL_NAME
           , controlUsage.ID AS CONTROL_USAGE_ID
           , controlUsage.PERIOD_START_TS AS MALFUNCTION_START
           , controlUsage.PERIOD_END_TS AS MALFUNCTION_END
    FROM   
		env_asset AS asset

		INNER JOIN env_asset_source AS assetSource 
				ON assetSource.asset_id = asset.id 

		INNER JOIN env_source_scc_info AS SSCC 
				ON SSCC.source_id = assetSource.source_id 
		INNER JOIN env_scc_info AS SCC 
			    ON SCC.id = SSCC.scc_info_id 
		
		INNER JOIN env_asset_type AS ATYPE 
			   ON ATYPE.asset_id = asset.id
		
		LEFT JOIN env_process_asset AS PRASS 
            ON asset.id = PRASS.asset_id 
                AND PRASS.status_cd = 1 
	    LEFT JOIN env_process AS PRC 
			   ON PRASS.process_id = PRC.id 

		INNER JOIN  
		    ENV_FACILITY_ASSET AS FAC_ASS
				ON asset.ID = FAC_ASS.ASSET_ID 

		INNER JOIN  
		    env_facility AS FAC 
				ON FAC_ASS.FACILITY_ID = FAC.id 
     
		INNER JOIN ENV_ASSET_EMITTED_SUBSTANCE_TYPE control1 
			on control1.ASSET_ID = asset.ID
		
		INNER JOIN ENV_CONTROL_USAGE controlUsage 
				on controlUsage.ASSET_EMITTED_SUBSTANCE_TYPE_ID = control1.ID
					AND ((controlUsage.PERIOD_START_TS >= @StartDate
					AND controlUsage.PERIOD_START_TS <= @EndDate)
						OR (controlUsage.PERIOD_END_TS >= @StartDate
					AND controlUsage.PERIOD_END_TS <= @EndDate))				
    WHERE  FAC.id = @facilityId
           AND asset.status_cd = 1 
           AND asset.process = 1 -- PROCESS assets
           AND ATYPE.asset_type_cd IN ( 1, 2 )-- GAS and PAINT
    GROUP  BY   asset.id 
              , asset.number 
              , asset.name 
              , PRC.process_number 
			  , control1.ADDL_INFO
			  , controlUsage.ID 
			  , controlUsage.PERIOD_START_TS 
			  , controlUsage.PERIOD_END_TS 			   
    ORDER  BY PRC.process_number 
              , asset.id 
		      , control1.ADDL_INFO 
			  , controlUsage.PERIOD_START_TS 
			  , controlUsage.PERIOD_END_TS 

GO


