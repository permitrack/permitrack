
/****** Object:  StoredProcedure [dbo].[sp_greatest_hap]    Script Date: 12/19/2012 12:56:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Sp_greatest_hap] @yearInt      INT = NULL 
                                        , @facilityId INT = NULL 
AS 
    DECLARE @yearChar  VARCHAR(4), 
            @StartDate VARCHAR(10), 
            @EndDate   VARCHAR(10) 

    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

    CREATE TABLE #temp_a 
      ( 
         subst_id     INT 
         , subst_name VARCHAR(50) 
         , subst_num  VARCHAR(50) 
         , haps       DECIMAL(18, 6) 
      ) 

    INSERT INTO #temp_a 
                (s.subst_id 
                 , s.subst_name 
                 , s.subst_num 
                 , s.haps) 
    SELECT * 
    FROM   Haps_totals(@StartDate, @EndDate, NULL, @facilityId, NULL) s 

    SELECT a.subst_id 
           , a.subst_name 
           , a.haps 
    FROM   #temp_a a 
    WHERE  a.haps = (SELECT Max(haps) 
                     FROM   #temp_a); 

    DROP TABLE #temp_a; 

