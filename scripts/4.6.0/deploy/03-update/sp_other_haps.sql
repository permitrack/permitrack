
/****** Object:  StoredProcedure [dbo].[sp_other_haps]    Script Date: 02/17/2013 14:27:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_other_haps] 
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

    SELECT ( t.haps - ghap.haps ) HAPS 
		FROM   (SELECT Sum(a.haps) HAPS 
				FROM   #temp_a a) t 
			   , (SELECT a.haps 
				  FROM   #temp_a a 
				  WHERE  a.haps = (SELECT Max(haps) 
								   FROM   #temp_a))ghap 

    DROP TABLE #temp_a 