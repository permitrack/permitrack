

/****** Object:  Index [IX_CAPUserSEHModule_UserID_SEHModuleID]    Script Date: 03/26/2013 09:19:07 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[CAP_USER_MODULE]') AND name = N'IX_CAPUserSEHModule_UserID_SEHModuleID')
ALTER TABLE [dbo].[CAP_USER_MODULE] DROP CONSTRAINT [IX_CAPUserSEHModule_UserID_SEHModuleID]
GO


