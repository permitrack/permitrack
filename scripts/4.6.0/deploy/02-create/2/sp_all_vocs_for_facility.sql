

/****** Object:  StoredProcedure [dbo].[sp_all_vocs_for_facility]    Script Date: 02/24/2013 12:04:43 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_all_vocs_for_facility]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_all_vocs_for_facility]
GO



/****** Object:  StoredProcedure [dbo].[sp_all_vocs_for_facility]    Script Date: 02/24/2013 12:04:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_all_vocs_for_facility] 
	@yearInt    INT = NULL, 
	@facilityId INT = NULL 
AS 
    DECLARE 
        @yearChar  VARCHAR(4), 
        @StartDate VARCHAR(10), 
        @EndDate   VARCHAR(10) ,
	    @assetTotalGasThroughput decimal(18, 6),
	    @assetTotalGasThroughput2 decimal(18, 6)

    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

	-- This table holds the asset actuals returned
	DECLARE @asset_actuals TABLE (
		ASSET_ID int,
		GAS int,
		PAINT int,
		ASSET_NUMBER varchar(20),
		ASSET_NAME varchar(50),
		ASSET_RATING decimal(18, 6),
		ASSET_PCT_OF_TOTAL decimal(18, 6),
		ASSET_ACTUAL_MMCF decimal(18, 6),
		ASSET_ACTUAL_MMBTU decimal(18, 6),
		ASSET_ACTUAL_PAINT decimal(18, 6)
	)

    DECLARE @clientId INT
    
    SELECT @clientId = CLIENT_ID FROM ENV_FACILITY WHERE ENV_FACILITY.ID = @facilityId

	INSERT INTO @asset_actuals
		SELECT * FROM Throughputs(@yearInt, @facilityId)

        SELECT  asset_id
			   , pn
			   , asset_name
			   , SOURCE_ID
			   , SOURCE_NAME
			   , factor as SOURCE_LBS_VOC
			   , GALLONS
               , LBS_VOC AS EMISSIONS
        
            FROM Tanks_liquids_totals(@startDate, @endDate, @clientId, NULL, NULL)
        
        UNION

		SELECT  bbb.asset_id 
              , bbb.ASSET_NUMBER AS PN
              , bbb.ASSET_NAME
              , e.source_id
              , e.source_name
              , SRC_SUB_EMIS_FCTR as SOURCE_LBS_VOC
              , CASE WHEN EMIS_FCTR_UNITS LIKE '%BTU%' THEN bbb.ASSET_ACTUAL_MMBTU ELSE bbb.ASSET_ACTUAL_MMCF END AS GALLONS
              , (SRC_SUB_EMIS_FCTR 
                * CASE WHEN EMIS_FCTR_UNITS LIKE '%BTU%' THEN bbb.ASSET_ACTUAL_MMBTU ELSE bbb.ASSET_ACTUAL_MMCF END
                ) AS ACTUAL_EMISSIONS
            FROM Emissions_gas(@facilityId, NULL, NULL, 1, 0, 0) as e
        
            INNER JOIN @asset_actuals as bbb on bbb.ASSET_ID = e.ASSET_ID 
        
        UNION

		SELECT  assetSources.asset_id 
              , number AS PN
              , assetSources.asset_name 
              , source_id
              , source_name
              , SOURCE_LBS_VOC
              , MAX(RATING) AS GALLONS
              , MAX(LBS_OF_VOC) AS EMISSIONS
              
           FROM
				(SELECT GALLONS.asset_id 
					      , GALLONS.number 
					      , GALLONS.asset_name 
					      , GALLONS.CONTROL_SUBSTANCE_TYPE_CD
					      , GALLONS.SOURCE_ID
					      , GALLONS.SOURCE_NAME
					      , GALLONS.SOURCE_LBS_VOC
					      , COALESCE(Sum(GALLONS.GALLONS), 0) AS RATING -- usage in gallons 
					      , COALESCE(Sum(GALLONS.GALLONS * MALFUNCTION_PERCENT) , 0) AS MALFUNCTION_RATING -- amount affected by malfunction
					      , COALESCE(Sum(GALLONS.GALLONS * SOURCE_LBS_VOC), 0) AS LBS_OF_VOC
				       FROM Liquids(@StartDate, @EndDate, @facilityId, NULL, NULL, NULL) AS GALLONS 
				       GROUP BY gallons.asset_id 
							     , gallons.asset_name 
							     , number
							     , CONTROL_SUBSTANCE_TYPE_CD
							     , SOURCE_ID
							     , SOURCE_NAME
							     , SOURCE_LBS_VOC
			    ) AS assetSources
			

			
		   GROUP BY assetSources.asset_id 
		          , assetSources.asset_name 
		          , number
                  , source_id
                  , source_name
		          , SOURCE_LBS_VOC
		   ORDER BY 1, 4


GO


