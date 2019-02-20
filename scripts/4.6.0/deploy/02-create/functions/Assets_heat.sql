

/****** Object:  UserDefinedFunction [dbo].[Assets_heat]    Script Date: 02/17/2013 12:03:30 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Assets_heat]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Assets_heat]
GO



/****** Object:  UserDefinedFunction [dbo].[Assets_heat]    Script Date: 02/17/2013 12:03:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[Assets_heat] (@meterId INT = NULL) 
RETURNS TABLE 
AS
RETURN 
(


SELECT 
		BREAKDOWN.ID,
		BREAKDOWN.NUMBER,
		BREAKDOWN.NAME,
		COALESCE ( SUM( BREAKDOWN.SUB_BURNERS + BREAKDOWN.EP_RATED_MMBTU), BREAKDOWN.EP_RATED_MMBTU) AS RATING
	FROM --(
	Assets(@meterId, '1', NULL)
	--	SELECT  A.ID, 
	--		A.NUMBER,
	--		A.NAME,
	--		AA.EP_RATED_MMBTU, 
	--		SUM(SUB_AA.EP_RATED_MMBTU) AS SUB_BURNERS
	--	 FROM ENV_ASSET AS A
	--	 LEFT JOIN ENV_ASSET_TYPE AS ATYPE ON ATYPE.ASSET_ID = A.ID
	--	  AND ATYPE.ASSET_TYPE_CD = 2 --natural gas assets
	--	 LEFT JOIN ENV_ASSET_ATTR AS AA ON AA.ASSET_ID = A.ID
	--	 LEFT JOIN ENV_ASSET AS SUB_A ON SUB_A.PARENT_ASSET_ID = A.ID
	--	 LEFT JOIN ENV_ASSET_ATTR AS SUB_AA ON SUB_AA.ASSET_ID = SUB_A.ID
	--	 WHERE A.BELONGS_TO_METER = Isnull(@meterId, a.BELONGS_TO_METER) 
	--	 AND A.STATUS_CD = 1 AND A.BUILDING_HEAT = 1 --active building heat assets
	--	   GROUP BY A.ID, A.NUMBER, A.NAME, AA.EP_RATED_MMBTU
	--) 
	AS BREAKDOWN 
	GROUP BY BREAKDOWN.ID, BREAKDOWN.NUMBER, BREAKDOWN.NAME, BREAKDOWN.SUB_BURNERS, BREAKDOWN.EP_RATED_MMBTU
	
	
	
			
)



GO


