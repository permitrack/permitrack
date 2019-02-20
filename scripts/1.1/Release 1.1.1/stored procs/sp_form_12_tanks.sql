--EXEC sp_form_12_tanks @yearInt=2009, @clientId=682, @facilityId=1

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_form_12_tanks]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_form_12_tanks]
GO

CREATE PROCEDURE dbo.sp_form_12_tanks
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
	@breathingEm decimal(18,6),
	@breathingCap decimal(18,6),
	@workingTotal decimal(18,6),
	@workingEm decimal(18,6),
	@workingThru decimal(18,6),
	@loadingTotal decimal(18,6),
	@loadingEm decimal(18,6),
	@loadingThru decimal(18,6),
	@totalLbsVOC decimal(18,6),
	@totalTonsVOC decimal(18,6),
	@grandTotal decimal(18,6)

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

	SET @breathingTotal = 0
	SET @breathingEm = 0
	SET @breathingCap = 0
	SET @workingTotal = 0
	SET @workingEm = 0
	SET @workingThru = 0
	SET @loadingTotal = 0
	SET @loadingEm = 0
	SET @loadingThru = 0
	SET @totalLbsVOC = 0
	SET @totalTonsVOC = 0
	SET @grandTotal = 0

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

	SET @breathingTotal = 0
	SET @breathingEm = 0
	SET @breathingCap = 0
	SET @workingTotal = 0
	SET @workingEm = 0
	SET @workingThru = 0
	SET @loadingTotal = 0
	SET @loadingEm = 0
	SET @loadingThru = 0

	-- BREATHING EMISSIONS --
	SELECT DISTINCT @breathingEm = CORE.BL_EM_FACTOR,  @breathingCap = ( CORE.TANK_CAPACITY / 1000 )
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
	SELECT @workingEm = GALLONS.BL_EM_FACTOR, @workingThru = (SUM(GALLONS.USAGE) / 1000)
	FROM (
		SELECT TOTALS.SCC_NUMBER, TOTALS.BL_EM_FACTOR,
		CASE TOTALS.UM WHEN 1 THEN ( TOTALS.READING ) -- gallons, no multiplier
			WHEN 2 THEN ( TOTALS.READING * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
			WHEN 3 THEN ( TOTALS.READING * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
			END USAGE
		FROM (
			SELECT SCC.SCC_NUMBER, ss.BL_EM_FACTOR, usage.METER_READING as READING, usage.UNIT_OF_MEASURE_CD as UM
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
			 AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
			LEFT JOIN ENV_ASSET_ATTR as aa
			 ON aa.ASSET_ID = @assetId
			WHERE SCC.SCC_NUMBER in ('4-04-003-02', '4-07-056-04')
			 AND SCC.CLIENT_ID = @clientId
		) AS TOTALS
	) as GALLONS
	GROUP BY GALLONS.SCC_NUMBER, GALLONS.BL_EM_FACTOR


	-- LOADING EMISSIONS --
	SELECT @loadingEm = GALLONS.BL_EM_FACTOR, @loadingThru = (SUM(GALLONS.USAGE) / 1000)
	FROM (
		SELECT TOTALS.SCC_NUMBER, TOTALS.BL_EM_FACTOR,
		CASE TOTALS.UM WHEN 1 THEN ( TOTALS.READING ) -- gallons, no multiplier
			WHEN 2 THEN ( TOTALS.READING * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
			WHEN 3 THEN ( TOTALS.READING * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
			END USAGE
		FROM (
			SELECT SCC.SCC_NUMBER, ss.BL_EM_FACTOR, usage.METER_READING as READING, usage.UNIT_OF_MEASURE_CD as UM
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
			 AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
			LEFT JOIN ENV_ASSET_ATTR as aa
			 ON aa.ASSET_ID = @assetId
			WHERE SCC.SCC_NUMBER in ('4-04-002-50')
			 AND SCC.CLIENT_ID = @clientId
		) AS TOTALS
	) as GALLONS
	GROUP BY GALLONS.SCC_NUMBER, GALLONS.BL_EM_FACTOR

	SET @breathingTotal = @breathingEm * @breathingCap
	SET @workingTotal = @workingEm * @workingThru
	SET @loadingTotal = @loadingEm * @loadingThru

	SET @grandTotal = @grandTotal + @breathingTotal  + @workingTotal + @loadingTotal

	-- loop and move on to the next tank in the result set
	DELETE FROM @tanks WHERE ASSET_ID = @assetId
END

SET @grandTotal = @grandTotal / 2000

SELECT @grandTotal


