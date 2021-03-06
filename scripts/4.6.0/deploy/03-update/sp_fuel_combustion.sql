
/****** Object:  StoredProcedure [dbo].[sp_fuel_combustion]    Script Date: 02/17/2013 14:14:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_fuel_combustion] 
  -- Required Parameters 
  @yearInt      INT = NULL 
  , @facilityId INT = NULL 
  , @assetId    INT = NULL 
AS 
	DECLARE @assetNum      VARCHAR(50), 
			@assetName     VARCHAR(50), 
			@yearInstalled INT, 
			@yearInstDate  DATETIME, 
			@capacity      DECIMAL(18, 6), 
			@assetThru     DECIMAL(18, 6), 
			@actThruUnits  VARCHAR(20), 
			@sccDesc       VARCHAR(250), 
			@sccCode       VARCHAR(50), 
			@heatContent   INT 

	-- Get name, number, year, and capacity 
	SELECT @assetName = A.name 
		   , @assetNum = A.number 
		   , @yearInstDate = A.active_ts 
		   , @capacity = AA.ep_capacity_mmbtu 
	FROM   env_asset AS A 
		   , env_asset_attr AS AA 
	WHERE  A.id = @assetId 
	   AND AA.asset_id = A.id 

	SET @yearInstalled = Year(@yearInstDate) 

	--********************************************-- 
	-- This table holds the asset actuals returned 
	--********************************************-- 
	CREATE TABLE #temp_asset_totals 
	  ( 
		 asset_id             INT 
		 , gas                INT 
		 , paint              INT 
		 , asset_number       VARCHAR(20) 
		 , asset_name         VARCHAR(50) 
		 , asset_rating       DECIMAL(18, 6) 
		 , asset_pct_of_total DECIMAL(18, 6) 
		 , asset_actual_mmcf  DECIMAL(18, 6) 
		 , asset_actual_mmbtu DECIMAL(18, 6) 
		 , asset_actual_paint DECIMAL(18, 6) 
	  ) 

	INSERT INTO #temp_asset_totals 
	EXEC Sp_assets_for_facility_throughputs 
	  @yearInt=@yearInt, 
	  @facilityId=@facilityId 

	-- Get this asset's throughput and rating  
	SELECT @assetThru = asset_actual_mmcf 
	FROM   #temp_asset_totals 
	WHERE  asset_id = @assetId 

	SET @actThruUnits = 'MmCF/yr' 

	DROP TABLE #temp_asset_totals 

	-- GET SCC CODE FOR NATURAL GAS SOURCES BURNED IN THIS YEAR FOR THIS ASSET 
	SELECT @heatContent = SO.hc_fuel 
		   , @sccCode = SCC.scc_number 
		   , @sccDesc = SCC.scc_description 
						+ SCC.maj_industrial_group 
	FROM   env_asset AS ASS 
		   INNER JOIN env_asset_source AS aSo 
				   ON aSo.asset_id = ASS.id 
		   INNER JOIN env_source AS SO 
				   ON SO.id = aSo.source_id 
		   INNER JOIN env_source_scc_info AS SSCC 
				   ON SSCC.source_id = SO.id 
		   INNER JOIN env_scc_info AS SCC 
				   ON SCC.id = SSCC.scc_info_id 
	WHERE  ASS.id = @assetId 
	   AND SO.source_type_cd = 2 
	GROUP  BY SCC.id 
			  , SCC.scc_number 
			  , SCC.scc_description 
			  , SCC.maj_industrial_group 
			  , ASS.id 
			  , ASS.number 
			  , ASS.name 
			  , SO.id 
			  , SO.description 
			  , SO.hc_fuel 
	ORDER  BY scc_number 

	-- return all info 
	SELECT @assetNum        AS ASSET_NUM 
		   , @assetName     AS ASSET_NAME 
		   , @yearInstalled AS YEAR_INST 
		   , @capacity      AS CAPACITY 
		   , @assetThru     AS ASSET_THRU 
		   , @actThruUnits  AS ACT_THRU_UNITS 
		   , @sccDesc       AS SCC_DESC 
		   , @sccCode       AS SCC_CODE 
		   , @heatContent   AS HC_CONTENT 
