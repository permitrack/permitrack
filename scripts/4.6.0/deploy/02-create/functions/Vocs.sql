

/****** Object:  UserDefinedFunction [dbo].[Vocs]    Script Date: 02/20/2013 13:36:49 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Vocs]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Vocs]
GO



/****** Object:  UserDefinedFunction [dbo].[Vocs]    Script Date: 02/20/2013 13:36:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Vocs] (@startDate    VARCHAR(30) = NULL 
                             , @endDate    VARCHAR(30) = NULL
                             , @assetId    INT = NULL) 
returns TABLE 
AS 
    RETURN 
      (
      
		  SELECT
			LBS_OF_VOC

		FROM 
			Liquids_totals(@startDate, @endDate, NULL, @assetId, NULL)
		
	) 


GO


