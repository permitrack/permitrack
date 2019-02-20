

/****** Object:  StoredProcedure [dbo].[sp_asset_scc_emissions_ghg]    Script Date: 02/21/2013 17:49:29 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_asset_scc_emissions_ghg]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_asset_scc_emissions_ghg]
GO



/****** Object:  StoredProcedure [dbo].[sp_asset_scc_emissions_ghg]    Script Date: 02/21/2013 17:49:29 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_asset_scc_emissions_ghg]
	-- Required Parameters
	@yearInt int = NULL,
	@facilityId int = NULL,
	@sccId int = NULL,
	@assetId int = NULL
AS
DECLARE
	--@yearChar varchar(4),
	--@StartDate varchar(10),
	--@EndDate varchar(10),
	@assetTotalGasThroughput decimal(18, 6),
	@assetTotalGasThroughput2 decimal(18, 6)

------ Create dates for use in query
--SET @yearChar = @yearInt
--SET @StartDate 	= '01-01-' + @yearChar
--SET @EndDate 	= '12-31-' + @yearChar



-- This table holds the asset actuals returned
CREATE TABLE #TEMP_ASSET_TOTALS (
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

INSERT INTO #temp_asset_totals 
	EXEC Sp_assets_for_facility_throughputs 
	  @yearInt=@yearInt, 
	  @facilityId=@facilityId 

			
--- pull meter reading data
SELECT 
	@assetTotalGasThroughput =  ASSET_ACTUAL_MMCF
	, @assetTotalGasThroughput2 = ASSET_ACTUAL_MMBTU
	FROM #TEMP_ASSET_TOTALS
	WHERE ASSET_ID = @assetId 
	  AND GAS = 1

DELETE #TEMP_ASSET_TOTALS
 
SELECT * FROM Emissions(@yearInt, @facilityId, @sccId, @assetId, NULL, @assetTotalGasThroughput2, NULL)




