
--EXEC sp_asset_scc_emissions @yearInt=2009, @facilityId=1, @sccId=9, @assetId=18

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_asset_scc_emissions]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_asset_scc_emissions]
GO

CREATE PROCEDURE dbo.sp_asset_scc_emissions
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL,
	@sccId int = NULL,
	@assetId int = NULL
AS

DECLARE 
	@yearChar varchar(4),
	@StartDate varchar(10),
	@EndDate varchar(10),
	
	@srcTypeCd int,
	@assetTotalThroughput decimal(18, 6),
	@sourceId int,
	@sourceTotal decimal(18, 6),
	@vocContent decimal(18, 6),
	@density decimal(18, 6),
	@numerator decimal(18, 6),
	@emissionFactor decimal(18, 6),
	@actualEmissions decimal(18, 6),
	@substanceId int,
	@substanceName varchar(50),
	@infoOrigin varchar(50)
	

-- Create dates for use in query
SET @yearChar = @yearInt
SET @StartDate 	= '01-01-' + @yearChar
SET @EndDate 	= '12-31-' + @yearChar

--********************************************--
-- This table holds the substance levels returned
--********************************************--
CREATE TABLE #TEMP_ASSETS_AND_SCCS (
	SUBSTANCE_TYPE varchar(50),
	ACTUAL_THROUGHPUT decimal(18, 6), --in MMCF or gal
	ACT_THRU_UNITS varchar(50),
	SRC_SUB_EMIS_FCTR decimal(18, 6), --in lb/MmCF or lb/gal
	EMIS_FCTR_UNITS varchar(50),
	SRC_INFO_ORIGIN varchar(50),
	ASSET_EFF_FACTOR varchar(50),
	ACTUAL_EMISSIONS decimal(18, 6)
)

-----------------------------------------
-- all sources for this asset/scc pair --
-----------------------------------------	
DECLARE @sources TABLE (
	SOURCE_ID int
)

-----------------------------------------
-- all substances for this asset/scc pair --
-----------------------------------------	
DECLARE @substances TABLE (
	SUBSTANCE_ID int,
	NG_EM_FACTOR decimal(18, 6)
)

-----------------------------------------
-- numerator to be used in paint calcs --
-----------------------------------------	
DECLARE @numerators TABLE (
	NUMERATOR decimal(18, 6)
)	

--********************************************--
-- This table holds the asset actuals returned
--********************************************--
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

INSERT INTO #TEMP_ASSET_TOTALS
EXEC sp_assets_for_facility_throughputs @yearInt=@yearInt, @facilityId=@facilityId

-- get the sources that should be summed in the totals on this page.
INSERT INTO @sources
	SELECT SCC.SOURCE_ID 
	FROM ENV_SOURCE_SCC_INFO AS SCC
		INNER JOIN ENV_ASSET_SOURCE AS ASSO --sources for this scc
		 ON ASSO.SOURCE_ID = SCC.SOURCE_ID
		 AND ASSO.ASSET_ID = @assetId
		 AND ASSO.STATUS_CD = 1 
		LEFT JOIN ENV_ASSET AS ASSET --asset equal to given asset id
		ON ASSET.ID = ASSO.ASSET_ID
	WHERE SCC.SCC_INFO_ID = @sccId
 
-- ************************************************************ --
-- NOTE: 
-- ASSUMPTION: sources under an SCC code should all have the SAME SOURCE TYPE
-- ************************************************************ --

SELECT @srcTypeCd = SRC.SOURCE_TYPE_CD
FROM ENV_SCC_INFO AS SCC
	LEFT JOIN ENV_SOURCE_SCC_INFO AS SSCC ON SSCC.SCC_INFO_ID = SCC.ID
	LEFT JOIN ENV_SOURCE AS SRC ON SRC.ID = SSCC.SOURCE_ID
WHERE SCC.ID = @sccId
	GROUP BY SRC.SOURCE_TYPE_CD,  SCC.ID, SCC_NUMBER
 
SET @assetTotalThroughput = 0
 
-- WHILE: ANY SOURCES IN THIS RESULT...
-- -- get the annual throughput of these sources from the ENV_SOURCE_USAGE table
-- -- according to the rules of their particular source type.
IF (@srcTypeCd = 1) -- PAINT
BEGIN
	WHILE (SELECT COUNT(*) FROM @sources) > 0
	  BEGIN
		SELECT TOP 1 @sourceId = SOURCE_ID FROM @sources
	 
		SET @sourceTotal = 0
		
		--- pull straight usages and sum
		SELECT @sourceTotal = COALESCE(SUM( GALLONS.USAGE ), 0) -- usage in gallons
		FROM (
			SELECT
				CASE TOTALS.UM WHEN 1 THEN ( TOTALS.READING ) -- gallons, no multiplier
				WHEN 2 THEN ( TOTALS.READING * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
				WHEN 3 THEN ( TOTALS.READING * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
				END USAGE
			FROM (
				SELECT
					usage.METER_READING as READING,
					usage.UNIT_OF_MEASURE_CD as UM
				FROM ENV_ASSET as asset
					INNER JOIN ENV_ASSET_SOURCE as aSo
					ON aso.ASSET_ID = asset.ID
					AND aso.SOURCE_ID = @sourceId
					INNER JOIN ENV_SOURCE_USAGE as usage
					ON usage.ASSET_SOURCE_ID = aSo.ID
					INNER JOIN ENV_ASSET_TYPE as assetType ON assetType.ASSET_ID = asset.id
				WHERE asset.id = @assetId
					AND asset.status_cd = 1
					AND assetType.ASSET_TYPE_CD = 1
					AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
					AND usage.PERIOD_START_TS >= @StartDate
					AND usage.PERIOD_END_TS <= @EndDate
			) AS TOTALS 
		) AS GALLONS 
			
		-- sum the entire total
		SET @assetTotalThroughput = @assetTotalThroughput + @sourceTotal

		-- output all VOCs according to pam's calculation -- no other substance types
		SELECT @vocContent = LBS_VOC, @density = DENSITY FROM ENV_SOURCE WHERE ID = @sourceId
		-- calculate the numerator (needed for final emission factor calculation)
		SET @numerator = @vocContent * @density * @sourceTotal
		-- these entries will be summed later
		INSERT INTO @numerators (NUMERATOR) VALUES (@numerator)
		
		-- loop and move on to the next id in the result set
		DELETE FROM @sources WHERE SOURCE_ID = @sourceId
	END -- end sources loop

	-- Get the emission factor
	SELECT @emissionFactor = SUM(NUMERATOR) FROM @numerators

	-- if the asset had 0 throughput, then avoid a divide by zero error!
	IF (@assetTotalThroughput != 0)
	BEGIN
		SET @emissionFactor = @emissionFactor / @assetTotalThroughput
	END
	
	SET @actualEmissions = @emissionFactor * @assetTotalThroughput

	SET @actualEmissions = @actualEmissions / 2000 -- TONS
	
	INSERT INTO #TEMP_ASSETS_AND_SCCS
	(SUBSTANCE_TYPE, ACTUAL_THROUGHPUT, ACT_THRU_UNITS, 
		SRC_SUB_EMIS_FCTR, EMIS_FCTR_UNITS,
		SRC_INFO_ORIGIN, ASSET_EFF_FACTOR, ACTUAL_EMISSIONS)
	VALUES
	('VOC', @assetTotalThroughput, 'gal', 
		@emissionFactor, 'lb/gal', 
		'Vendor Information', 'NA', @actualEmissions)	

	
END -- end paint if
	

IF (@srcTypeCd = 2) -- NATURAL GAS
BEGIN
WHILE (SELECT COUNT(*) FROM @sources) > 0
	  BEGIN
		SELECT TOP 1 @sourceId = SOURCE_ID FROM @sources
	 
		--- pull meter reading data
		SELECT @assetTotalThroughput = ASSET_ACTUAL_MMCF 
		FROM #TEMP_ASSET_TOTALS
		WHERE ASSET_ID = @assetId
	
		SELECT @infoOrigin = INFO_ORIGIN
		FROM ENV_SOURCE WHERE ID = @sourceId
		
		INSERT INTO @substances			
			SELECT SUBSTANCE_ID, NG_EM_FACTOR 
			FROM ENV_SOURCE_SUBSTANCE 
			WHERE SOURCE_ID = @sourceId 
			AND STATUS_CD = 1
			AND NG_EF_UNIT = 8
			
		-- loop per substance
		-- output the emission factor for each substance on the single nat gas source
		WHILE (SELECT COUNT(*) FROM @substances) > 0
		  BEGIN
			SELECT TOP 1 @substanceId = SUBSTANCE_ID, @emissionFactor = NG_EM_FACTOR FROM @substances	
						
			SELECT @substanceName = [NAME]
			FROM ENV_SUBSTANCE 
			WHERE ID = @substanceId
			
			SET @actualEmissions = @emissionFactor * @assetTotalThroughput

			SET @actualEmissions = @actualEmissions / 2000 -- TONS

			-- insert substance totals into table
			INSERT INTO #TEMP_ASSETS_AND_SCCS
			(SUBSTANCE_TYPE, ACTUAL_THROUGHPUT, ACT_THRU_UNITS, 
				SRC_SUB_EMIS_FCTR, EMIS_FCTR_UNITS,
				SRC_INFO_ORIGIN, ASSET_EFF_FACTOR, ACTUAL_EMISSIONS)
			VALUES
 			(@substanceName, @assetTotalThroughput, 'MmCF', 
				@emissionFactor, 'lb/MmCF', 
				@infoOrigin, 'NA', @actualEmissions)	
			
			-- loop and move on to the next meter id in the result set
			DELETE FROM @substances WHERE SUBSTANCE_ID = @substanceId
		END -- end substance loop
			
		-- loop and move on to the next meter id in the result set
		DELETE FROM @sources WHERE SOURCE_ID = @sourceId
	END -- end sources loop
		
END -- end gas if
	
	
 SELECT * FROM #TEMP_ASSETS_AND_SCCS
 
 DROP TABLE #TEMP_ASSET_TOTALS
 DROP TABLE #TEMP_ASSETS_AND_SCCS
 
 