
/****** Object:  UserDefinedFunction [dbo].[Assets]    Script Date: 02/17/2013 12:03:14 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Assets]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Assets]
GO


/****** Object:  UserDefinedFunction [dbo].[Assets]    Script Date: 02/17/2013 12:03:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[Assets] (@meterId         INT = NULL,
                               @buildingHeat    VARCHAR(1) = NULL
                              , @process         VARCHAR(1) = NULL
                              ) 
RETURNS TABLE 
AS
RETURN 
(


		SELECT  A.ID, 
			A.NUMBER,
			A.NAME,
			AA.EP_RATED_MMBTU, 
			SUM(SUB_AA.EP_RATED_MMBTU) AS SUB_BURNERS
		 FROM ENV_ASSET AS A
		 LEFT JOIN ENV_ASSET_TYPE AS ATYPE ON ATYPE.ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET_ATTR AS AA ON AA.ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET AS SUB_A ON SUB_A.PARENT_ASSET_ID = A.ID
		 LEFT JOIN ENV_ASSET_ATTR AS SUB_AA ON SUB_AA.ASSET_ID = SUB_A.ID
		 WHERE 
			A.BELONGS_TO_METER = Isnull(@meterId, a.BELONGS_TO_METER) 
		    AND ATYPE.ASSET_TYPE_CD = 2 --natural gas assets
			AND A.STATUS_CD = 1 
			AND (A.BUILDING_HEAT = @buildingHeat OR A.PROCESS = @process) 
		 GROUP BY A.ID, A.NUMBER, A.NAME, AA.EP_RATED_MMBTU
	
	
	
	
	
	
	
			
)



GO


