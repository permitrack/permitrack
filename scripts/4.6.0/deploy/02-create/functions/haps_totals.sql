

/****** Object:  UserDefinedFunction [dbo].[Haps_totals]    Script Date: 02/17/2013 12:05:31 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Haps_totals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Haps_totals]
GO



/****** Object:  UserDefinedFunction [dbo].[Haps_totals]    Script Date: 02/17/2013 12:05:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




CREATE FUNCTION [dbo].[Haps_totals] (@startDate  VARCHAR(30) = NULL, 
                                     @endDate    VARCHAR(30) = NULL, 
                                     @clientId   INT = NULL, 
                                     @facilityId INT = NULL,
                                     @assetId    INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (SELECT GRAND_TOTALS.id        AS SUBST_ID, 
              GRAND_TOTALS.name      AS SUBST_NAME, 
              GRAND_TOTALS.part_num  AS SUBST_NUM, 
              Sum(GRAND_TOTALS.haps) AS HAPS 
       FROM   (SELECT TOTALS.id, 
                      TOTALS.name, 
                      TOTALS.part_num, 
                      CASE TOTALS.um 
                        WHEN 1 THEN ( haps ) 
                        WHEN 2 THEN ( haps * 0.0078125 ) 
                        WHEN 3 THEN ( haps * 0.264172052 ) 
                      END HAPS 
               FROM   Haps(@startDate, @endDate, @clientId, @facilityId, @assetId) AS 
                      TOTALS) 
              AS 
              GRAND_TOTALS 
       GROUP  BY GRAND_TOTALS.id, 
                 GRAND_TOTALS.name, 
                 GRAND_TOTALS.part_num) 



GO


