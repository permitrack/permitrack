
/****** Object:  StoredProcedure [dbo].[sp_form_10_1_total]    Script Date: 02/19/2013 11:46:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Sp_form_10_1_total] 
  -- Required Parameters 
  @yearInt    INT = NULL, 
  @clientId   INT = NULL, 
  @facilityId INT = NULL 
AS 
    DECLARE @assetId        INT, 
            @yearChar       VARCHAR(4), 
            @StartDate      VARCHAR(10), 
            @EndDate        VARCHAR(10), 
            @breathingTotal DECIMAL(18, 6), 
            @workingTotal   DECIMAL(18, 6), 
            @loadingTotal   DECIMAL(18, 6), 
            @totalLbsVOC    DECIMAL(18, 6), 
            @totalTonsVOC   DECIMAL(18, 6) 

    -- Create dates for use in query 
    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

    --------------------------------- 
    -- all tanks for the facility  -- 
    ---------------------------------   
    DECLARE @tanks TABLE 
      ( 
         asset_id INT 
      ) 

    --------------------------------- 
    -- totals for each asset / scc -- 
    ---------------------------------   
    CREATE TABLE #totals 
      ( 
         asset_id      INT, 
         tank_num      VARCHAR(50), 
         breathing_scc VARCHAR(50), 
         breathing_em  DECIMAL(18, 6), 
         breathing_cap DECIMAL(18, 6), 
         breathing_tot DECIMAL(18, 6), 
         working_scc   VARCHAR(50), 
         working_em    DECIMAL(18, 6), 
         working_thru  DECIMAL(18, 6), 
         working_tot   DECIMAL(18, 6), 
         loading_scc   VARCHAR(50), 
         loading_em    DECIMAL(18, 6), 
         loading_thru  DECIMAL(18, 6), 
         loading_tot   DECIMAL(18, 6) 
      ) 

    -- Get all TANK assets -- 
    INSERT INTO @tanks 
    SELECT asset_id 
    FROM   Tanks(@StartDate, @EndDate, @facilityId) 

    -- for each tank asset, get the totals and add to the grand total 
    WHILE (SELECT Count(*) 
           FROM   @tanks) > 0 
      BEGIN 
          SELECT TOP 1 @assetId = asset_id 
          FROM   @tanks 

          INSERT INTO #totals 
          EXEC Sp_form_10_1 
            @yearInt=@yearInt, 
            @clientId=@clientId, 
            @assetId=@assetId 

          -- loop and move on to the next tank in the result set 
          DELETE FROM @tanks 
          WHERE  asset_id = @assetId 
      END 

    -- Sum the results and return 
    SET @totalLbsVOC = 0 
    SET @totalTonsVOC = 0 

    WHILE (SELECT Count(*) 
           FROM   #totals) > 0 
      BEGIN 
          SELECT TOP 1 @assetId = asset_id, 
                       @breathingTotal = breathing_tot, 
                       @workingTotal = working_tot, 
                       @loadingTotal = loading_tot 
          FROM   #totals 

          -- increment the total 
          SET @totalLbsVOC = @totalLbsVOC + @breathingTotal + @workingTotal 
                             + @loadingTotal 

          -- loop and move on to the next tank in the result set 
          DELETE FROM #totals 
          WHERE  asset_id = @assetId 
      END 

    SET @totalTonsVOC = @totalLbsVOC / 2000 

    SELECT @totalLbsVOC  AS TOTAL_LBS_VOC, 
           @totalTonsVOC AS TOTAL_TONS_VOC 

    DROP TABLE #totals 