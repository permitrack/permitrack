

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CAPContactPhone_CAPContact]') AND parent_object_id = OBJECT_ID(N'[dbo].[CAP_CONTACT_PHONE]'))
ALTER TABLE [dbo].[CAP_CONTACT_PHONE] DROP CONSTRAINT [FK_CAPContactPhone_CAPContact]
GO



/****** Object:  Table [dbo].[CAP_CONTACT_PHONE]    Script Date: 03/22/2013 14:24:16 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CAP_CONTACT_PHONE]') AND type in (N'U'))
DROP TABLE [dbo].[CAP_CONTACT_PHONE]
GO


