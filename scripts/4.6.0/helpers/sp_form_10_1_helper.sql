USE [cap2]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[sp_form_10_1]
		@yearInt = 2011,
		@assetId = 36,
		@clientId = 682

EXEC	@return_value = [dbo].[sp_form_10_1]
		@yearInt = 2011,
		@assetId = 41,
		@clientId = 682


--36
--38
--39
--40
--41
--42