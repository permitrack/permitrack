
/****** Object:  StoredProcedure [dbo].[proc_AddUserAsContactAll]    Script Date: 02/13/2013 17:13:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_AddUserAsContactAll]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_AddUserAsContactAll]
GO


