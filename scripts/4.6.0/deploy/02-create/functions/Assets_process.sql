

/****** Object:  UserDefinedFunction [dbo].[Assets_process]    Script Date: 02/17/2013 12:03:43 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Assets_process]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Assets_process]
GO



/****** Object:  UserDefinedFunction [dbo].[Assets_process]    Script Date: 02/17/2013 12:03:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Assets_process] (@meterId         INT = NULL) 
RETURNS TABLE 
AS
RETURN 
(
	SELECT 
		BREAKDOWN.ID,
		BREAKDOWN.NUMBER,
		BREAKDOWN.NAME,
		COALESCE ( SUM( BREAKDOWN.SUB_BURNERS + BREAKDOWN.EP_RATED_MMBTU), BREAKDOWN.EP_RATED_MMBTU) AS RATING
	FROM 
		Assets(@meterId, NULL, '1') AS BREAKDOWN 
	GROUP BY BREAKDOWN.ID, BREAKDOWN.NUMBER, BREAKDOWN.NAME, BREAKDOWN.SUB_BURNERS, BREAKDOWN.EP_RATED_MMBTU
		
			
)


GO


