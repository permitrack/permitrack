

/****** Object:  UserDefinedFunction [dbo].[Vocs_totals]    Script Date: 02/20/2013 13:39:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Vocs_totals]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Vocs_totals]
GO



/****** Object:  UserDefinedFunction [dbo].[Vocs_totals]    Script Date: 02/20/2013 13:39:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




CREATE FUNCTION [dbo].[Vocs_totals] (@startDate  VARCHAR(30) = NULL, 
                                     @endDate    VARCHAR(30) = NULL,
                                     @assetId    INT = NULL) 
returns TABLE 
AS 
RETURN 
    (
	SELECT 
		COALESCE(SUM( GALLONS.LBS_OF_VOC ),0) AS SUM_LBS_VOC
		FROM (
			SELECT
				LBS_OF_VOC
			FROM Vocs(@startDate, @endDate, @assetId) AS TOTALS
		) 
		AS GALLONS
	) 



GO


