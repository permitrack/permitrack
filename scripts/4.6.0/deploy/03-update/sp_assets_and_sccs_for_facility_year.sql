
/****** Object:  StoredProcedure [dbo].[sp_assets_and_sccs_for_facility_year]    Script Date: 02/16/2013 14:18:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_assets_and_sccs_for_facility_year] 
  -- Required Parameters 
  @yearInt      INT = NULL 
  , @facilityId INT = NULL 
AS 
    DECLARE 
    --@yearChar  VARCHAR(4), 
            @assetId   INT, 
            --@StartDate VARCHAR(10), 
            --@EndDate   VARCHAR(10), 
            @prcNum    VARCHAR(50) 

    ---- Create dates for use in query 
    --SET @yearChar = @yearInt 
    --SET @StartDate = '01-01-' + @yearChar 
    --SET @EndDate = '12-31-' + @yearChar 

    SELECT ASS.id                 AS ASSET_ID 
           , ASS.number           AS ASSET_NUMBER 
           , ASS.name             AS ASSET_NAME 
           , PRC.process_number   AS PROC_NUM 
           , SCC.id               AS SCC_ID 
           , SCC.scc_number       AS SCC_NUMBER 
           , SCC.scc_description  AS SCC_DESCRIPTION 
           , SCC.raw_material     AS SCC_RAW_MATERIAL 
           , SCC.emitting_process AS SCC_EMITTING_PROCESS 
    FROM env_asset AS ASS 
        INNER JOIN env_asset_type AS ATYPE 
               ON ATYPE.asset_id = ASS.id 
        INNER JOIN env_asset_source AS aSo 
                ON aSo.asset_id = ASS.id 
        INNER JOIN env_source_scc_info AS SSCC 
                ON SSCC.source_id = aSo.source_id 
        INNER JOIN env_scc_info AS SCC 
               ON SCC.id = SSCC.scc_info_id 

        INNER JOIN ENV_FACILITY_ASSET AS FAC_ASS 
               ON FAC_ass.ASSET_ID = ass.id

        INNER JOIN env_facility AS FAC 
               ON FAC.ID = fac_ass.FACILITY_ID

        LEFT JOIN env_process_asset AS PRASS 
                ON ASS.id = PRASS.asset_id 
                  AND PRASS.status_cd = 1 
        LEFT JOIN env_process AS PRC 
               ON PRC.id = prass.PROCESS_ID 
                  
    WHERE  FAC.id = @facilityId 
           AND ASS.status_cd = 1 
           AND aSo.status_cd = 1 
           AND ASS.process = 1 --active process assets  
           AND ATYPE.asset_type_cd IN ( 1, 2 )--natural gas assets and paint  
    GROUP  BY SCC.id 
              , SCC.scc_number 
              , SCC.scc_description 
              , SCC.raw_material 
              , SCC.emitting_process 
              , ASS.id 
              , ASS.number 
              , ASS.name 
              , PRC.process_number 
    ORDER  BY PRC.process_number 
              , ASS.id 
              , scc_number 