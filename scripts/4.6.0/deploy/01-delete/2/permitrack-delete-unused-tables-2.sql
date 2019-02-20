

/****** Object:  Table [dbo].[CAP_CLIENT_CONTACT_TYPE]    Script Date: 03/11/2013 12:08:39 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CAP_CLIENT_CONTACT_TYPE]') AND type in (N'U'))
DROP TABLE [dbo].[CAP_CLIENT_CONTACT_TYPE]
GO


IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_EC_Inspection_Contact_CAP_Contact_Type]') AND parent_object_id = OBJECT_ID(N'[dbo].[EC_INSPECTION_CONTACT]'))
ALTER TABLE [dbo].[EC_INSPECTION_CONTACT] DROP CONSTRAINT [FK_EC_Inspection_Contact_CAP_Contact_Type]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_EC_INSPECTION_CONTACT_EC_INSPECTION_CONTACT_TYPE]') AND parent_object_id = OBJECT_ID(N'[dbo].[EC_INSPECTION_CONTACT]'))
ALTER TABLE [dbo].[EC_INSPECTION_CONTACT] DROP CONSTRAINT [FK_EC_INSPECTION_CONTACT_EC_INSPECTION_CONTACT_TYPE]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ECInspectionContact_CAPContact]') AND parent_object_id = OBJECT_ID(N'[dbo].[EC_INSPECTION_CONTACT]'))
ALTER TABLE [dbo].[EC_INSPECTION_CONTACT] DROP CONSTRAINT [FK_ECInspectionContact_CAPContact]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ECInspectionContact_ECInspection]') AND parent_object_id = OBJECT_ID(N'[dbo].[EC_INSPECTION_CONTACT]'))
ALTER TABLE [dbo].[EC_INSPECTION_CONTACT] DROP CONSTRAINT [FK_ECInspectionContact_ECInspection]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[EC_INSPECTION_CONTACT]') AND type in (N'U'))
DROP TABLE [dbo].[EC_INSPECTION_CONTACT]
GO


IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[EC_INSPECTION_CONTACT_TYPE]') AND type in (N'U'))
DROP TABLE [dbo].[EC_INSPECTION_CONTACT_TYPE]
GO


