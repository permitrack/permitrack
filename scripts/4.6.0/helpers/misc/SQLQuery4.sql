USE [cap2]
GO

DECLARE	@return_value int

EXEC	[dbo].[sp_project_search]
		@clientId = N'1118', @orderColumns = N'project.name'

--SELECT	'Return Value' = @return_value

GO
