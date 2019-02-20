
--EXEC sp_fuel_combustion @yearInt=2009, @facilityId=1, @assetId=1

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_fuel_combustion]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_fuel_combustion]
GO

CREATE PROCEDURE dbo.sp_fuel_combustion
	-- Required Parameters
	@yearInt int = NULL, 
	@facilityId int = NULL,
	@assetId int = NULL
AS


DECLARE 
	@assetNum varchar(50),
	@assetName varchar(50),
	@yearInstalled int,
	@yearInstDate datetime,
	@capacity decimal(18,6),
	@assetThru decimal(18,6),
	@actThruUnits varchar(20),
	@sccDesc varchar(250),
	@sccCode varchar(50),
	@heatContent int

-- Get name, number, year, and capacity
SELECT @assetName=A.NAME, @assetNum=A.NUMBER, @yearInstDate=A.ACTIVE_TS, @capacity=AA.EP_CAPACITY_MMBTU
FROM ENV_ASSET AS A, ENV_ASSET_ATTR AS AA
WHERE A.ID = @assetId
AND AA.ASSET_ID = A.ID

SET @yearInstalled = YEAR(@yearInstDate)

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

-- Get this asset's throughput and rating 
SELECT @assetThru = ASSET_ACTUAL_MMCF 
FROM #TEMP_ASSET_TOTALS
WHERE ASSET_ID = @assetId

SET @actThruUnits = 'MmCF/yr'

DROP TABLE #TEMP_ASSET_TOTALS


-- GET SCC CODE FOR NATURAL GAS SOURCES BURNED IN THIS YEAR FOR THIS ASSET
 -- @sccDesc 
 -- @sccCode 
 -- @heatContent 

SELECT @heatContent=SO.HC_FUEL, @sccCode=SCC.SCC_NUMBER, @sccDesc=SCC.SCC_DESCRIPTION + SCC.MAJ_INDUSTRIAL_GROUP
	FROM ENV_ASSET AS ASS 
	INNER JOIN ENV_ASSET_SOURCE AS aSo ON aSo.ASSET_ID = ASS.ID
	INNER JOIN ENV_SOURCE AS SO ON SO.ID = aSo.SOURCE_ID
	   AND SO.SOURCE_TYPE_CD = 2
	INNER JOIN ENV_SOURCE_SCC_INFO AS SSCC ON SSCC.SOURCE_ID = SO.ID
	INNER JOIN ENV_SCC_INFO AS SCC ON SCC.ID = SSCC.SCC_INFO_ID
	WHERE ASS.ID = @assetId 
	GROUP BY SCC.ID, SCC.SCC_NUMBER, SCC.SCC_DESCRIPTION, SCC.MAJ_INDUSTRIAL_GROUP,
		 ASS.ID, ASS.NUMBER, ASS.NAME, SO.ID, SO.DESCRIPTION, SO.HC_FUEL
	ORDER BY SCC_NUMBER
	
-- return all info
SELECT @assetNum AS ASSET_NUM,
	@assetName AS ASSET_NAME,
	@yearInstalled AS YEAR_INST,
	@capacity AS CAPACITY,
	@assetThru AS ASSET_THRU,
	@actThruUnits AS ACT_THRU_UNITS,
	@sccDesc AS SCC_DESC,
	@sccCode AS SCC_CODE,
	@heatContent AS HC_CONTENT

GO