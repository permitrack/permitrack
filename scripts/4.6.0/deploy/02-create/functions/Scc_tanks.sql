

/****** Object:  UserDefinedFunction [dbo].[Scc_tanks]    Script Date: 02/18/2013 09:10:12 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Scc_tanks]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Scc_tanks]
GO



/****** Object:  UserDefinedFunction [dbo].[Scc_tanks]    Script Date: 02/18/2013 09:10:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Scc_tanks] 
(
	-- Add the parameters for the function here
	@type VARCHAR(30) = 'working'
)
RETURNS 
@Sccs TABLE 
(
	-- Add the column definitions for the TABLE variable here
	ID INT
)
AS
BEGIN
	-- Fill the table variable with the rows for your result set
	IF (@type IS NULL OR @type = 'breathing')
		BEGIN
			INSERT INTO @Sccs (ID)
				(SELECT ID FROM ENV_SCC_INFO WHERE SCC_NUMBER IN ( 
											 '4-04-003-01', '4-07-056-03' ))
		END

	IF (@type IS NULL OR @type = 'working')
		BEGIN
			INSERT INTO @Sccs (ID)
				(SELECT ID FROM ENV_SCC_INFO WHERE SCC_NUMBER IN ( 
											 '4-04-003-02', '4-07-056-04' ))
		END

	IF (@type IS NULL OR @type = 'loading')
		BEGIN
			INSERT INTO @Sccs (ID)
				(SELECT ID FROM ENV_SCC_INFO WHERE SCC_NUMBER IN ( 
											 '4-04-002-50' ))
		END
	
	IF (@type = 'ghg')
		BEGIN
			INSERT INTO @Sccs (ID)
				(SELECT ID FROM ENV_SCC_INFO WHERE SCC_NUMBER IN ( 
											 '1-02-006-02', '1-02-006-03' ))
		END

	RETURN 

END

GO


