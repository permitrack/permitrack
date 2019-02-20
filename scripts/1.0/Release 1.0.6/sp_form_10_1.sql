

--EXEC sp_form_10_1 @yearInt=2009, @clientId=682, @assetId=40

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_form_10_1]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_form_10_1]
GO

CREATE PROCEDURE dbo.sp_form_10_1
 -- Required Parameters
 @yearInt int = NULL, 
 @assetId int = NULL,
 @clientId int = NULL
AS

DECLARE 
	@yearChar varchar(4),
	@StartDate varchar(10),
	@EndDate varchar(10),
	@tankNum varchar(50),
	@breathingSCC varchar(50),
	@breathingEm decimal(18,6),
	@breathingCap decimal(18,6),
	@breathingTotal decimal(18,6),
	@workingSCC varchar(50),
	@workingEm decimal(18,6), 
	@workingThru decimal(18,6),
	@workingTotal decimal(18,6),
	@loadingSCC varchar(50), 
	@loadingEm decimal(18,6),
	@loadingThru decimal(18,6),
	@loadingTotal decimal(18,6)
	
-- Create dates for use in query
	SET @yearChar = @yearInt
	SET @StartDate 	= '01-01-' + @yearChar
	SET @EndDate 	= '12-31-' + @yearChar

-- get the tank id
SELECT @tankNum = NUMBER 
FROM ENV_ASSET 
WHERE ID = @assetId	
	
-- When given a tank asset id... get the results for each emission scc type

-- BREATHING EMISSIONS --
SELECT DISTINCT @breathingSCC = CORE.SCC_NUMBER, @breathingEm = CORE.BL_EM_FACTOR, 
 @breathingCap = ( CORE.TANK_CAPACITY / 1000 )
FROM (
	SELECT SCC.SCC_NUMBER, ss.BL_EM_FACTOR, aa.TANK_CAPACITY
	FROM ENV_SCC_INFO AS SCC
	INNER JOIN ENV_SUBSTANCE_SCC_INFO AS subSCC 
	 ON subSCC.SCC_INFO_ID = SCC.ID
	INNER JOIN ENV_SOURCE_SUBSTANCE AS ss
	 ON ss.SUBSTANCE_ID = subSCC.SUBSTANCE_ID
	 AND ss.STATUS_CD = 1 AND ss.BL_EF_UNIT = 7
	INNER JOIN ENV_ASSET_SOURCE AS asso
	 ON asso.SOURCE_ID = ss.SOURCE_ID
	 AND asso.ASSET_ID = @assetId
	 AND asso.STATUS_CD = 1 
	INNER JOIN ENV_SOURCE_USAGE as usage
	 ON usage.ASSET_SOURCE_ID = asso.ID
	 AND usage.PERIOD_START_TS >= @startDate
	 AND usage.PERIOD_END_TS <= @endDate
	LEFT JOIN ENV_ASSET_ATTR as aa
	 ON aa.ASSET_ID = @assetId 
	WHERE SCC.SCC_NUMBER in ('4-04-003-01', '4-07-056-03')
	 AND SCC.CLIENT_ID = @clientId
) as CORE


-- WORKING EMISSIONS --
SELECT @workingSCC = CORE.SCC_NUMBER, @workingEm = CORE.BL_EM_FACTOR,
 @workingThru = (SUM(CORE.METER_READING) / 1000) 
FROM (
	SELECT SCC.SCC_NUMBER, ss.BL_EM_FACTOR, usage.METER_READING
	FROM ENV_SCC_INFO AS SCC
	INNER JOIN ENV_SUBSTANCE_SCC_INFO AS subSCC 
	 ON subSCC.SCC_INFO_ID = SCC.ID
	INNER JOIN ENV_SOURCE_SUBSTANCE AS ss
	 ON ss.SUBSTANCE_ID = subSCC.SUBSTANCE_ID
	 AND ss.STATUS_CD = 1 AND ss.BL_EF_UNIT = 7
	INNER JOIN ENV_ASSET_SOURCE AS asso
	 ON asso.SOURCE_ID = ss.SOURCE_ID
	 AND asso.ASSET_ID = @assetId
	 AND asso.STATUS_CD = 1 
	INNER JOIN ENV_SOURCE_USAGE as usage
	 ON usage.ASSET_SOURCE_ID = asso.ID
	 AND usage.PERIOD_START_TS >= @startDate
	 AND usage.PERIOD_END_TS <= @endDate
	LEFT JOIN ENV_ASSET_ATTR as aa
	 ON aa.ASSET_ID = @assetId 
	WHERE SCC.SCC_NUMBER in ('4-04-003-02', '4-07-056-04')
	 AND SCC.CLIENT_ID = @clientId
) as CORE
GROUP BY CORE.SCC_NUMBER, CORE.BL_EM_FACTOR


-- LOADING EMISSIONS --
SELECT @loadingSCC = CORE.SCC_NUMBER, @loadingEm = CORE.BL_EM_FACTOR, 
 @loadingThru = (SUM(CORE.METER_READING) / 1000) 
FROM (
	SELECT SCC.SCC_NUMBER, ss.BL_EM_FACTOR, usage.METER_READING
	FROM ENV_SCC_INFO AS SCC
	INNER JOIN ENV_SUBSTANCE_SCC_INFO AS subSCC 
	 ON subSCC.SCC_INFO_ID = SCC.ID
	INNER JOIN ENV_SOURCE_SUBSTANCE AS ss
	 ON ss.SUBSTANCE_ID = subSCC.SUBSTANCE_ID
	 AND ss.STATUS_CD = 1 AND ss.BL_EF_UNIT = 7
	INNER JOIN ENV_ASSET_SOURCE AS asso
	 ON asso.SOURCE_ID = ss.SOURCE_ID
	 AND asso.ASSET_ID = @assetId
	 AND asso.STATUS_CD = 1 
	INNER JOIN ENV_SOURCE_USAGE as usage
	 ON usage.ASSET_SOURCE_ID = asso.ID
	 AND usage.PERIOD_START_TS >= @startDate
	 AND usage.PERIOD_END_TS <= @endDate
	LEFT JOIN ENV_ASSET_ATTR as aa
	 ON aa.ASSET_ID = @assetId 
	WHERE SCC.SCC_NUMBER in ('4-04-002-50')
	 AND SCC.CLIENT_ID = @clientId
) as CORE
GROUP BY CORE.SCC_NUMBER, CORE.BL_EM_FACTOR

SET @breathingTotal = @breathingEm * @breathingCap
SET @workingTotal = @workingEm * @workingThru
SET @loadingTotal = @loadingEm * @loadingThru

-- return as one row for the asset
SELECT	@assetId AS ASSET_ID, 
		@tankNum as TANK_NUM,
		@breathingSCC AS BREATHING_SCC,
		COALESCE(@breathingEm,0) AS BREATHING_EM,
		COALESCE(@breathingCap,0) AS BREATHING_CAP,
		COALESCE(@breathingTotal,0) AS BREATHING_TOT,
		@workingSCC AS WORKING_SCC,
		COALESCE(@workingEm,0) AS WORKING_EM, 
		COALESCE(@workingThru,0) AS WORKING_THRU,
		COALESCE(@workingTotal,0) AS WORKING_TOT,
		@loadingSCC AS LOADING_SCC, 
		COALESCE(@loadingEm,0) AS LOADING_EM,
		COALESCE(@loadingThru,0) AS LOADING_THRU,
		COALESCE(@loadingTotal,0) AS LOADING_TOT