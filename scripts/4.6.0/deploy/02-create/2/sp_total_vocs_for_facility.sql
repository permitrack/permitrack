
/****** Object:  StoredProcedure [dbo].[sp_total_vocs_for_facility]    Script Date: 02/24/2013 13:17:58 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_total_vocs_for_facility]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_total_vocs_for_facility]
GO


/****** Object:  StoredProcedure [dbo].[sp_total_vocs_for_facility]    Script Date: 02/24/2013 13:17:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_total_vocs_for_facility] 
  -- Required Parameters 
  @yearInt      INT = NULL 
  , @facilityId INT = NULL 
AS 
    DECLARE @yearChar  VARCHAR(4), 
            @StartDate VARCHAR(10), 
            @EndDate   VARCHAR(10) 

    -- Create dates for use in query 
    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 



	-- This table holds the asset actuals returned
	CREATE TABLE #VOC_SUBTOTALS (
		ASSET_ID int,

		ASSET_NUMBER varchar(20),
		ASSET_NAME varchar(200),
		SOURCE_ID INT
	   , SOURCE_NAME varchar(200)
		, SRC_SUB_EMIS_FCTR decimal(18, 6)
		, GALLONS decimal(18, 6)
               , LBS_VOC decimal(18, 6)
               
               
               	)

    DECLARE @clientId INT
    
    SELECT @clientId = CLIENT_ID FROM ENV_FACILITY WHERE ENV_FACILITY.ID = @facilityId

	INSERT INTO #VOC_SUBTOTALS 
		EXEC sp_all_vocs_for_facility
		  @yearInt=@yearInt, 
		  @facilityId=@facilityId 
		  
		  

	SELECT SUM(LBS_VOC) AS VOCs, SUM(LBS_VOC) / 2000 AS TONS_VOC
		FROM #voc_subtotals --(@StartDate, @EndDate, @facilityId, NULL, NULL)


GO


