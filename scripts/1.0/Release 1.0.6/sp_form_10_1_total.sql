

--EXEC sp_form_10_1_total @yearInt=2009, @clientId=682, @facilityId=1

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_form_10_1_total]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_form_10_1_total]
GO

CREATE PROCEDURE dbo.sp_form_10_1_total
 -- Required Parameters
 @yearInt int = NULL, 
 @clientId int = NULL,
 @facilityId int = NULL
AS

DECLARE 
	@assetId int,
	@yearChar varchar(4),
	@StartDate varchar(10),
	@EndDate varchar(10),
	@breathingTotal decimal(18,6),
	@workingTotal decimal(18,6),
	@loadingTotal decimal(18,6),
	@totalLbsVOC decimal(18,6),
	@totalTonsVOC decimal(18,6)

-- Create dates for use in query
	SET @yearChar = @yearInt
	SET @StartDate 	= '01-01-' + @yearChar
	SET @EndDate 	= '12-31-' + @yearChar

---------------------------------
-- all tanks for the facility  --
---------------------------------	
DECLARE @tanks TABLE (
	ASSET_ID int
)
	
---------------------------------
-- totals for each asset / scc --
---------------------------------	
CREATE TABLE #totals (
	ASSET_ID int, 
	TANK_NUM varchar(50),
	BREATHING_SCC varchar(50),
	BREATHING_EM decimal(18,6),
	BREATHING_CAP decimal(18,6),
	BREATHING_TOT decimal(18,6),
	WORKING_SCC varchar(50),
	WORKING_EM decimal(18,6), 
	WORKING_THRU decimal(18,6),
	WORKING_TOT decimal(18,6),
	LOADING_SCC varchar(50), 
	LOADING_EM decimal(18,6),
	LOADING_THRU decimal(18,6),
	LOADING_TOT decimal(18,6)
)
	
	
-- Get all TANK assets --
INSERT INTO @tanks
	SELECT asset.id as ASSET_ID
	FROM ENV_FACILITY_ASSET as fac
		 INNER JOIN ENV_ASSET as asset
		  ON asset.ID = fac.asset_id
		  AND asset.STATUS_CD = 1
		 INNER JOIN ENV_ASSET_SOURCE as aSo
		  ON aso.ASSET_ID = asset.ID
		 INNER JOIN ENV_SOURCE_USAGE as usage
		  ON usage.ASSET_SOURCE_ID = aSo.ID
		  AND usage.PERIOD_START_TS >= @StartDate
		  AND usage.PERIOD_END_TS <= @EndDate
		 INNER JOIN ENV_SOURCE as source
		  ON aSo.SOURCE_ID = source.ID
		  AND source.source_type_cd = 3 -- bulk liquid
		INNER JOIN ENV_ASSET_TYPE as assetType
		  ON assetType.ASSET_ID = asset.id
		  AND assetType.ASSET_TYPE_CD = 3 -- bulk liquid
	WHERE fac.FACILITY_ID = @facilityId
	GROUP BY asset.ID
	
	
-- for each tank asset, get the totals and add to the grand total
WHILE (SELECT COUNT(*) FROM @tanks) > 0
  BEGIN
    SELECT TOP 1 @assetId = ASSET_ID FROM @tanks	
	
	INSERT INTO #totals 
	  EXEC sp_form_10_1 @yearInt=@yearInt, @clientId=@clientId, @assetId=@assetId
	
	-- loop and move on to the next tank in the result set
	DELETE FROM @tanks WHERE ASSET_ID = @assetId
END

-- Sum the results and return
SET @totalLbsVOC = 0
SET @totalTonsVOC = 0

WHILE (SELECT COUNT(*) FROM #totals) > 0
  BEGIN
    SELECT TOP 1 @assetId = ASSET_ID, @breathingTotal = BREATHING_TOT, 
				 @workingTotal = WORKING_TOT, @loadingTotal = LOADING_TOT
	 FROM #totals	

	 -- increment the total
	SET @totalLbsVOC = @totalLbsVOC + @breathingTotal + @workingTotal + @loadingTotal
	
	-- loop and move on to the next tank in the result set
	DELETE FROM #totals WHERE ASSET_ID = @assetId
END

SET @totalTonsVOC = @totalLbsVOC / 2000

SELECT @totalLbsVOC AS TOTAL_LBS_VOC, @totalTonsVOC as TOTAL_TONS_VOC

DROP TABLE #totals
	
	