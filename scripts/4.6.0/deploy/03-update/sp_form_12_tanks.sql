
/****** Object:  StoredProcedure [dbo].[sp_form_12_tanks]    Script Date: 02/19/2013 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Sp_form_12_tanks] 
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
            @breathingEm    DECIMAL(18, 6), 
            @breathingCap   DECIMAL(18, 6), 
            @workingTotal   DECIMAL(18, 6), 
            @workingEm      DECIMAL(18, 6), 
            @workingThru    DECIMAL(18, 6), 
            @loadingTotal   DECIMAL(18, 6), 
            @loadingEm      DECIMAL(18, 6), 
            @loadingThru    DECIMAL(18, 6), 
            @totalLbsVOC    DECIMAL(18, 6), 
            @totalTonsVOC   DECIMAL(18, 6), 
            @grandTotal     DECIMAL(18, 6) 

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
    SELECT asset_id 
    FROM   Tanks(@StartDate, @EndDate, @facilityId) 

    -- for each tank asset, get the totals and add to the grand total   
    WHILE (SELECT Count(*) 
           FROM   @tanks) > 0 
      BEGIN 
          SELECT TOP 1 @assetId = asset_id 
          FROM   @tanks 

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
          SELECT DISTINCT @breathingEm = CORE.bl_em_factor, 
                          @breathingCap = ( CORE.tank_capacity / 1000 ) 
          FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, 
                 'breathing') 
                 AS CORE 

          -- WORKING EMISSIONS --   
          SELECT @workingEm = GALLONS.bl_em_factor, 
                 @workingThru = ( Sum(GALLONS.usage) / 1000 ) 
          FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, 
                 'working') AS 
                 GALLONS 
          GROUP  BY GALLONS.scc_number, 
                    GALLONS.bl_em_factor 

          -- LOADING EMISSIONS --   
          SELECT @loadingEm = GALLONS.bl_em_factor, 
                 @loadingThru = ( Sum(GALLONS.usage) / 1000 ) 
          FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, 
                 'loading') AS 
                 GALLONS 
          GROUP  BY GALLONS.scc_number, 
                    GALLONS.bl_em_factor 

          SET @breathingTotal = @breathingEm * @breathingCap 
          SET @workingTotal = @workingEm * @workingThru 
          SET @loadingTotal = @loadingEm * @loadingThru 
          
          SET @grandTotal = @grandTotal + @breathingTotal + @workingTotal 
                            + @loadingTotal 

          -- loop and move on to the next tank in the result set   
          DELETE FROM @tanks 
          WHERE  asset_id = @assetId 
      END 

    SET @grandTotal = @grandTotal / 2000 

    SELECT @grandTotal 