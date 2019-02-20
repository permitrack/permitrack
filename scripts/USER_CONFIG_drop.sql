IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ECUserConfig_User]') AND parent_object_id = OBJECT_ID(N'[dbo].[EC_USER_CONFIG]'))
ALTER TABLE [dbo].[EC_USER_CONFIG] DROP CONSTRAINT [FK_ECUserConfig_User]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[EC_USER_CONFIG]') AND type in (N'U'))
DROP TABLE [dbo].[EC_USER_CONFIG]
GO


