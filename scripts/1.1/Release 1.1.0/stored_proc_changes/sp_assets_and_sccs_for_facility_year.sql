

--EXEC sp_assets_and_sccs_for_facility_year @yearInt=2009, @facilityId=1

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_assets_and_sccs_for_facility_year]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_assets_and_sccs_for_facility_year]
GO

CREATE PROCEDURE dbo.sp_assets_and_sccs_for_facility_year
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL
AS

DECLARE 
	@yearChar varchar(4),
	@assetId int,
	@StartDate varchar(10),
	@EndDate varchar(10),
	@prcNum varchar(50)

-- Create dates for use in query
SET @yearChar = @yearInt
SET @StartDate 	= '01-01-' + @yearChar
SET @EndDate 	= '12-31-' + @yearChar

--********************************************--
-- This table holds the asset actuals 
--********************************************--
DECLARE @TEMP_ASSETS_AND_SCCS TABLE (
	ASSET_ID int,
	ASSET_NUMBER varchar(20), 
	ASSET_NAME varchar(50),
	PROC_NUM varchar(50),
	SCC_ID int, 
	SCC_NUMBER varchar(200),
	SCC_DESCRIPTION varchar(50), 
	SCC_RAW_MATERIAL varchar(50), 
	SCC_EMITTING_PROCESS varchar(50)	
)

--********************************************--
-- table to hold the assets of the facility
--********************************************--
DECLARE @TEMP_FACILITY_ASSETS TABLE(
	A_ID int,
	PROCESS_NUMBER varchar(20)
)

-- Get all of this facility's process assets, that had usages during this year.
INSERT INTO @TEMP_FACILITY_ASSETS
 SELECT ASS.ID, PRC.PROCESS_NUMBER
 FROM ENV_FACILITY AS FAC
	LEFT JOIN ENV_PROCESS AS PRC ON PRC.FACILITY_ID = FAC.ID
	LEFT JOIN ENV_PROCESS_ASSET AS PRASS ON PRASS.PROCESS_ID = PRC.ID
	INNER JOIN ENV_ASSET AS ASS ON ASS.ID = PRASS.ASSET_ID
	 AND ASS.STATUS_CD = 1 AND ASS.PROCESS = 1 --active process assets
	LEFT JOIN ENV_ASSET_TYPE AS ATYPE ON ATYPE.ASSET_ID = ASS.ID
	 AND ATYPE.ASSET_TYPE_CD in ( 1,2 )--natural gas assets and paint
	INNER JOIN ENV_ASSET_SOURCE AS aSo ON aSo.ASSET_ID = ASS.ID
 WHERE FAC.ID = @facilityId 
  GROUP BY ASS.ID, ASS.NUMBER, ASS.NAME, PRC.PROCESS_NUMBER
  ORDER BY PRC.PROCESS_NUMBER


WHILE (SELECT COUNT(*) FROM @TEMP_FACILITY_ASSETS) > 0
  BEGIN
    SELECT TOP 1 @assetId = A_ID, @prcNum = PROCESS_NUMBER FROM @TEMP_FACILITY_ASSETS

	-- get SCC codes for each asset, and store a row in final table with:
	-- asset id 1, scc code 1
	-- asset id 1, scc code 2
	-- etc
	
	-- GET ALL SCC CODES FOR THIS ASSET
	INSERT INTO @TEMP_ASSETS_AND_SCCS
	SELECT ASS.ID, ASS.NUMBER, ASS.NAME, '5.13', 
		SCC.ID, SCC.SCC_NUMBER, SCC.SCC_DESCRIPTION, SCC.RAW_MATERIAL, SCC.EMITTING_PROCESS
	FROM ENV_ASSET_SOURCE AS aSo 
	LEFT JOIN ENV_ASSET AS ASS ON ASS.ID = aSo.ASSET_ID 
	INNER JOIN ENV_SOURCE_SCC_INFO AS SSCC ON SSCC.SOURCE_ID = aSo.SOURCE_ID
	LEFT JOIN ENV_SCC_INFO AS SCC ON SCC.ID = SSCC.SCC_INFO_ID
	WHERE aSo.asset_id = 31 and aSo.status_cd = 1
	GROUP BY SCC.ID, SCC.SCC_NUMBER, SCC.SCC_DESCRIPTION, 
		 SCC.RAW_MATERIAL, SCC.EMITTING_PROCESS,
		 ASS.ID, ASS.NUMBER, ASS.NAME
	ORDER BY SCC_NUMBER
	
	--SELECT ASS.ID, ASS.NUMBER, ASS.NAME, @prcNum, 
	--	SCC.ID, SCC.SCC_NUMBER, SCC.SCC_DESCRIPTION, SCC.RAW_MATERIAL, SCC.EMITTING_PROCESS
	--FROM ENV_ASSET AS ASS 
	--INNER JOIN ENV_ASSET_SOURCE AS aSo ON aSo.ASSET_ID = ASS.ID
	--INNER JOIN ENV_SOURCE_SCC_INFO AS SSCC ON SSCC.SOURCE_ID = aSo.SOURCE_ID
	--LEFT JOIN ENV_SCC_INFO AS SCC ON SCC.ID = SSCC.SCC_INFO_ID
	--WHERE ASS.ID = @assetId 
	--GROUP BY SCC.ID, SCC.SCC_NUMBER, SCC.SCC_DESCRIPTION, 
	--	 SCC.RAW_MATERIAL, SCC.EMITTING_PROCESS,
	--	 ASS.ID, ASS.NUMBER, ASS.NAME
	--ORDER BY SCC_NUMBER
	
	
	-- loop and move on to the next meter id in the result set
    DELETE FROM @TEMP_FACILITY_ASSETS WHERE A_ID = @assetId

  END
  

-- values used in the report
SELECT * FROM @TEMP_ASSETS_AND_SCCS
	
GO



