

/****** Object:  Table [dbo].[ACL_ENTRY_remove]    Script Date: 01/16/2013 14:51:01 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ACL_ENTRY_remove]') AND type in (N'U'))
DROP TABLE [dbo].[ACL_ENTRY_remove]
GO


/****** Object:  Table [dbo].[ACL_remove]    Script Date: 01/16/2013 14:51:20 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ACL_remove]') AND type in (N'U'))
DROP TABLE [dbo].[ACL_remove]
GO

/****** Object:  Table [dbo].[BMP_032907]    Script Date: 01/16/2013 14:52:49 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BMP_032907]') AND type in (N'U'))
DROP TABLE [dbo].[BMP_032907]
GO


IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CAPContactPhone_CAPContact]') AND parent_object_id = OBJECT_ID(N'[dbo].[CAP_CONTACT_PHONE]'))
ALTER TABLE [dbo].[CAP_CONTACT_PHONE] DROP CONSTRAINT [FK_CAPContactPhone_CAPContact]
GO

/****** Object:  Table [dbo].[CAP_CONTACT_PHONE]    Script Date: 01/16/2013 14:56:08 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CAP_CONTACT_PHONE]') AND type in (N'U'))
DROP TABLE [dbo].[CAP_CONTACT_PHONE]
GO


/****** Object:  Table [dbo].[MCM_032907]    Script Date: 01/16/2013 15:27:07 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MCM_032907]') AND type in (N'U'))
DROP TABLE [dbo].[MCM_032907]
GO


/****** Object:  Table [dbo].[MCM2_remove]    Script Date: 01/16/2013 15:28:00 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MCM2_remove]') AND type in (N'U'))
DROP TABLE [dbo].[MCM2_remove]
GO


/****** Object:  Table [dbo].[MESSAGE]    Script Date: 01/16/2013 15:29:47 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MESSAGE]') AND type in (N'U'))
DROP TABLE [dbo].[MESSAGE]
GO


IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_OUTFALL_INFORMATION_GOAL_ACTIVITY]') AND parent_object_id = OBJECT_ID(N'[dbo].[OUTFALL_INFORMATION]'))
ALTER TABLE [dbo].[OUTFALL_INFORMATION] DROP CONSTRAINT [FK_OUTFALL_INFORMATION_GOAL_ACTIVITY]
GO


/****** Object:  Table [dbo].[OUTFALL_INFORMATION]    Script Date: 01/16/2013 15:33:28 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OUTFALL_INFORMATION]') AND type in (N'U'))
DROP TABLE [dbo].[OUTFALL_INFORMATION]
GO


IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_OUTFALL_INSPECTION_GOAL_ACTIVITY]') AND parent_object_id = OBJECT_ID(N'[dbo].[OUTFALL_INSPECTION]'))
ALTER TABLE [dbo].[OUTFALL_INSPECTION] DROP CONSTRAINT [FK_OUTFALL_INSPECTION_GOAL_ACTIVITY]
GO

IF  EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF_OUTFALL_INSPECTION_DIGITAL_PHOTO]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[OUTFALL_INSPECTION] DROP CONSTRAINT [DF_OUTFALL_INSPECTION_DIGITAL_PHOTO]
END

GO

IF  EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF_OUTFALL_INSPECTION_SAMPLE_COLLECTED]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[OUTFALL_INSPECTION] DROP CONSTRAINT [DF_OUTFALL_INSPECTION_SAMPLE_COLLECTED]
END

GO


/****** Object:  Table [dbo].[OUTFALL_INSPECTION]    Script Date: 01/16/2013 15:34:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OUTFALL_INSPECTION]') AND type in (N'U'))
DROP TABLE [dbo].[OUTFALL_INSPECTION]
GO







/****** Object:  Table [dbo].[PRPTY_remove]    Script Date: 01/16/2013 16:14:16 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PRPTY_remove]') AND type in (N'U'))
DROP TABLE [dbo].[PRPTY_remove]
GO



/****** Object:  Table [dbo].[ROLE_ACL_remove]    Script Date: 01/16/2013 16:14:32 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ROLE_ACL_remove]') AND type in (N'U'))
DROP TABLE [dbo].[ROLE_ACL_remove]
GO


/****** Object:  Table [dbo].[ROLE_remove]    Script Date: 01/16/2013 16:14:47 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ROLE_remove]') AND type in (N'U'))
DROP TABLE [dbo].[ROLE_remove]
GO


/****** Object:  Table [dbo].[USR_remove]    Script Date: 01/16/2013 16:15:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USR_remove]') AND type in (N'U'))
DROP TABLE [dbo].[USR_remove]
GO


