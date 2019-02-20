IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CONTACT_CLIENT]') AND parent_object_id = OBJECT_ID(N'[dbo].[CONTACT]'))
ALTER TABLE [dbo].[CONTACT] DROP CONSTRAINT [FK_CONTACT_CLIENT]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CONTACT]') AND type in (N'U'))
DROP TABLE [dbo].[CONTACT]
GO


