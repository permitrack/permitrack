

/****** Object:  UserDefinedFunction [dbo].[fn_GetClientContactOrganizationId]    Script Date: 02/14/2013 09:25:20 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_GetClientContactOrganizationId]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[fn_GetClientContactOrganizationId]
GO


