
/****** Object:  StoredProcedure [dbo].[sp_all_haps_for_facility]    Script Date: 02/15/2013 14:41:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_all_haps_for_facility] @yearInt    INT = NULL, 
                                                 @facilityId INT = NULL 
AS 
    DECLARE @yearChar  VARCHAR(4), 
            @StartDate VARCHAR(10), 
            @EndDate   VARCHAR(10) 

    SET @yearChar = @yearInt 
    SET @StartDate = '01-01-' + @yearChar 
    SET @EndDate = '12-31-' + @yearChar 

    SELECT DISTINCT s.subst_id, 
                    s.subst_name, 
                    s.subst_num, 
                    s.haps, 
                    dbo.GetPN(s.subst_id, @StartDate, @EndDate) PN 
    FROM   Haps_totals(@startDate, @endDate, NULL, @facilityId, NULL) s 