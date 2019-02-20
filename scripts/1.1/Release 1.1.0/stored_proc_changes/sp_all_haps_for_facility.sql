--exec sp_all_haps_for_facility @yearInt=2009, @facilityId=1

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_all_haps_for_facility]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_all_haps_for_facility]
GO



CREATE PROCEDURE dbo.sp_all_haps_for_facility
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL
AS

DECLARE 
	@yearChar varchar(4),
	@StartDate varchar(10),
	@EndDate varchar(10)
	
-- Create dates for use in query
SET @yearChar = @yearInt
SET @StartDate 	= '01-01-' + @yearChar
SET @EndDate 	= '12-31-' + @yearChar



CREATE TABLE #temp_a (
SUBST_ID int,
SUBST_NAME varchar(50),
SUBST_NUM varchar(50),
HAPS decimal(18,6)
)

CREATE TABLE #temp_b (
SUBST_ID int,
PN varchar(200)
)

CREATE TABLE #temp_c (
SUBST_ID int,
SUBST_NAME varchar(50),
SUBST_NUM varchar(50),
HAPS decimal(18,6),
PN varchar(200)
)
-----------------------------------------------------------------------------
INSERT INTO #temp_a(s.SUBST_ID, s.SUBST_NAME, s.SUBST_NUM, s.HAPS)
select distinct s.SUBST_ID,
	s.SUBST_NAME,
	s.SUBST_NUM,
	s.HAPS
	from (
		SELECT
			GRAND_TOTALS.ID AS SUBST_ID,
			GRAND_TOTALS.NAME AS SUBST_NAME,
			GRAND_TOTALS.PART_NUM AS SUBST_NUM,
			SUM(GRAND_TOTALS.HAPS) AS HAPS
		FROM ( 
			 SELECT
				TOTALS.ID,
				TOTALS.NAME, 
				TOTALS.PART_NUM,
			  CASE  TOTALS.UM WHEN 1 THEN ( HAPS ) -- gallons, no multiplier
					WHEN 2 THEN ( HAPS * 0.0078125 ) -- 1 US fluid ounce = 0.0078125 US gallons
					WHEN 3 THEN ( HAPS * 0.264172052 ) -- 1 liters = 0.264172052 US gallons
				END HAPS
			 FROM (
				select 	distinct
					subst.ID, subst.NAME, subst.PART_NUM,
					( usage.METER_READING * source.DENSITY * sosub.RATIO) AS HAPS,
					usage.UNIT_OF_MEASURE_CD AS UM
				FROM ENV_FACILITY as facility
				 INNER JOIN ENV_PROCESS as process
					  ON process.facility_id = facility.id
			     INNER JOIN ENV_PROCESS_ASSET as prcass
				      ON prcass.process_id = process.id
				 INNER JOIN ENV_ASSET as asset
				   ON asset.ID = prcass.ASSET_ID
				 INNER JOIN ENV_ASSET_SOURCE as aSo
				  ON aso.ASSET_ID = asset.id
				 INNER JOIN ENV_SOURCE_USAGE as usage
				  ON usage.ASSET_SOURCE_ID = aSo.ID
				 INNER JOIN ENV_SOURCE as source
				  ON aSo.SOURCE_ID = source.ID
					 INNER JOIN ENV_SOURCE_SUBSTANCE as sosub
					  ON source.ID = sosub.SOURCE_ID
					  AND sosub.STATUS_CD = 1
					 INNER JOIN ENV_SUBSTANCE as subst
					  ON sosub.SUBSTANCE_ID = subst.ID
					  AND subst.STATUS_CD = 1
				 INNER JOIN ENV_ASSET_TYPE as assetType 
				  ON assetType.ASSET_ID = asset.id
				WHERE facility.id = @facilityId
				AND assetType.ASSET_TYPE_CD = 1
				AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
				AND usage.PERIOD_START_TS >= @startDate
				AND usage.PERIOD_END_TS <= @endDate
				AND source.DENSITY_UM = 6
			 ) AS TOTALS 
		) AS GRAND_TOTALS GROUP BY GRAND_TOTALS.ID, GRAND_TOTALS.NAME, GRAND_TOTALS.PART_NUM 
    ) s
    GROUP BY s.SUBST_ID, s.SUBST_NAME, s.SUBST_NUM, s.HAPS

----------------------------------------------------------------------------------------------------------

INSERT INTO #temp_b (s.SUBST_ID, pn)
select distinct s.SUBST_ID,
		dbo.GetPN(s.ASSET_ID) pn
	from (
                SELECT
			GRAND_TOTALS.ASSET_ID,
			GRAND_TOTALS.ID AS SUBST_ID,
			GRAND_TOTALS.NAME AS SUBST_NAME,
			GRAND_TOTALS.PART_NUM AS SUBST_NUM
		FROM ( 
			 SELECT
				TOTALS.asset_id,
				TOTALS.ID,
					TOTALS.NAME, 
				TOTALS.PART_NUM
			 FROM (
				select 	distinct
					asset.id as asset_id,
					subst.ID, subst.NAME, subst.PART_NUM,
					usage.UNIT_OF_MEASURE_CD AS UM
				FROM ENV_FACILITY as facility
				 INNER JOIN ENV_PROCESS as process
					  ON process.facility_id = facility.id
				 INNER JOIN ENV_PROCESS_ASSET as prcass
				      ON prcass.process_id = process.id
				 INNER JOIN ENV_ASSET as asset
				   ON asset.ID = prcass.ASSET_ID
				 INNER JOIN ENV_ASSET_SOURCE as aSo
				  ON aso.ASSET_ID = asset.id
				 INNER JOIN ENV_SOURCE_USAGE as usage
				  ON usage.ASSET_SOURCE_ID = aSo.ID
				 INNER JOIN ENV_SOURCE as source
				  ON aSo.SOURCE_ID = source.ID
					 INNER JOIN ENV_SOURCE_SUBSTANCE as sosub
					  ON source.ID = sosub.SOURCE_ID
					  AND sosub.STATUS_CD = 1
					 INNER JOIN ENV_SUBSTANCE as subst
					  ON sosub.SUBSTANCE_ID = subst.ID
					  AND subst.STATUS_CD = 1
				 INNER JOIN ENV_ASSET_TYPE as assetType 
				  ON assetType.ASSET_ID = asset.id
				WHERE facility.id = @facilityId
				AND assetType.ASSET_TYPE_CD = 1
				AND usage.UNIT_OF_MEASURE_CD in (1,2,3) -- gallons, ounces, liters
				AND usage.PERIOD_START_TS >= @startDate
				AND usage.PERIOD_END_TS <= @endDate
				AND source.DENSITY_UM = 6
			 ) AS TOTALS 
		) AS GRAND_TOTALS GROUP BY GRAND_TOTALS.ID, GRAND_TOTALS.NAME, GRAND_TOTALS.PART_NUM, GRAND_TOTALS.ASSET_ID 
          ) s
    GROUP BY s.SUBST_ID, s.SUBST_NAME, s.SUBST_NUM, s.ASSET_ID 
	
---------------------------------------------------------

DECLARE
	@curA_SUBST_ID int,
	@curA_SUBST_NAME varchar(50),
	@curA_SUBST_NUM varchar(50),
	@curA_HAPS decimal(18,6),
	@curB_SUBST_ID int,
	@curB_PN varchar(50),
	@new_pn varchar(200)

SET  @new_pn = ''

DECLARE
	cursor_a CURSOR FOR 
	select * from #temp_a

DECLARE
	cursor_b CURSOR FOR
	select * from #temp_b

OPEN cursor_a
FETCH NEXT FROM cursor_a INTO @curA_SUBST_ID, @curA_SUBST_NAME, @curA_SUBST_NUM, @curA_HAPS;

WHILE @@FETCH_STATUS = 0
   BEGIN 
	PRINT 'Cursor A: ' + + cast(@curA_SUBST_ID as varchar)
	
		OPEN cursor_b
		FETCH NEXT FROM cursor_b INTO @curB_SUBST_ID, @curB_PN;
		
		WHILE @@FETCH_STATUS = 0
		BEGIN
			PRINT 'Cursor B: ' + cast(@curB_SUBST_ID as varchar)
			IF (@curA_SUBST_ID = @curB_SUBST_ID)
			  BEGIN
				SET @new_pn = @new_pn + @curB_PN + ', ';
				PRINT '----Cursor A subst id == cursor B subst id -- new PN: ' + cast(@new_pn as varchar)
				FETCH NEXT FROM cursor_b INTO @curB_SUBST_ID, @curB_PN;
			  END
			ELSE
			  BEGIN
				INSERT INTO #temp_c (SUBST_ID, SUBST_NAME, SUBST_NUM , HAPS, PN)
				VALUES (@curA_SUBST_ID, @curA_SUBST_NAME, @curA_SUBST_NUM, @curA_HAPS, @new_pn)
				SET @new_pn = ''
				FETCH NEXT FROM cursor_a INTO @curA_SUBST_ID, @curA_SUBST_NAME, @curA_SUBST_NUM, @curA_HAPS;
			  END		
		END
	
		INSERT INTO #temp_c (SUBST_ID, SUBST_NAME, SUBST_NUM , HAPS, PN)
				VALUES (@curA_SUBST_ID, @curA_SUBST_NAME, @curA_SUBST_NUM, @curA_HAPS, @new_pn)

		CLOSE cursor_b
		DEALLOCATE cursor_b
	
	FETCH NEXT FROM cursor_a INTO @curA_SUBST_ID, @curA_SUBST_NAME, @curA_SUBST_NUM, @curA_HAPS;
   END



CLOSE cursor_a
DEALLOCATE cursor_a
---------------------------------------------------------------

drop table #temp_a;
drop table #temp_b;

select * from #temp_c;
drop table #temp_c;

GO