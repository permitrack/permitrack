
/****** Object:  StoredProcedure [dbo].[sp_form_10_1]    Script Date: 02/19/2013 11:05:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Sp_form_10_1] 
  -- Required Parameters 
  @yearInt  INT = NULL, 
  @assetId  INT = NULL, 
  @clientId INT = NULL 
AS 
    DECLARE @yearChar       VARCHAR(4), 
            @StartDate      VARCHAR(10), 
            @EndDate        VARCHAR(10), 
            @tankNum        VARCHAR(50), 
            @breathingSCC   VARCHAR(50), 
            @breathingEm    DECIMAL(18, 6), 
            @breathingCap   DECIMAL(18, 6), 
            @breathingTotal DECIMAL(18, 6), 
            @workingSCC     VARCHAR(50), 
            @workingEm      DECIMAL(18, 6), 
            @workingThru    DECIMAL(18, 6), 
            @workingTotal   DECIMAL(18, 6), 
            @loadingSCC     VARCHAR(50), 
            @loadingEm      DECIMAL(18, 6), 
            @loadingThru    DECIMAL(18, 6), 
            @loadingTotal   DECIMAL(18, 6) 

    -- Create dates for use in query 
    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

    -- get the tank id 
    SELECT @tankNum = number 
    FROM   env_asset 
    WHERE  id = @assetId 

    -- BREATHING EMISSIONS --    
    SELECT DISTINCT @breathingSCC = CORE.scc_number, 
                    @breathingEm = CORE.bl_em_factor, 
                    @breathingCap = ( CORE.tank_capacity / 1000 ) 
    FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, 'breathing') 
           AS CORE 

    -- WORKING EMISSIONS --    
    SELECT @workingSCC = GALLONS.scc_number, 
           @workingEm = GALLONS.bl_em_factor, 
           @workingThru = ( Sum(GALLONS.usage) / 1000 ) 
    FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, 
           'working') AS 
           GALLONS 
    GROUP  BY GALLONS.scc_number, 
              GALLONS.bl_em_factor 

    -- LOADING EMISSIONS --    
    SELECT @loadingSCC = GALLONS.scc_number, 
           @loadingEm = GALLONS.bl_em_factor, 
           @loadingThru = ( Sum(GALLONS.usage) / 1000 ) 
    FROM   Tanks_liquids(@StartDate, @EndDate, @clientId, @assetId, 
           'loading') AS 
           GALLONS 
    GROUP  BY GALLONS.scc_number, 
              GALLONS.bl_em_factor 

    SET @breathingTotal = @breathingEm * @breathingCap 
    SET @workingTotal = @workingEm * @workingThru 
    SET @loadingTotal = @loadingEm * @loadingThru 

    -- return as one row for the asset 
    SELECT @assetId                     AS ASSET_ID, 
           @tankNum                     AS TANK_NUM, 
           @breathingSCC                AS BREATHING_SCC, 
           COALESCE(@breathingEm, 0)    AS BREATHING_EM, 
           COALESCE(@breathingCap, 0)   AS BREATHING_CAP, 
           COALESCE(@breathingTotal, 0) AS BREATHING_TOT, 
           @workingSCC                  AS WORKING_SCC, 
           COALESCE(@workingEm, 0)      AS WORKING_EM, 
           COALESCE(@workingThru, 0)    AS WORKING_THRU, 
           COALESCE(@workingTotal, 0)   AS WORKING_TOT, 
           @loadingSCC                  AS LOADING_SCC, 
           COALESCE(@loadingEm, 0)      AS LOADING_EM, 
           COALESCE(@loadingThru, 0)    AS LOADING_THRU, 
           COALESCE(@loadingTotal, 0)   AS LOADING_TOT 