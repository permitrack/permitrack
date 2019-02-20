if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_EC_BMP_DB_STAT_CD_LKP_STAT_CD]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[EC_BMP_DB] DROP CONSTRAINT FK_EC_BMP_DB_STAT_CD_LKP_STAT_CD
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ECBMP_ECBMPDBCategory]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[EC_BMP_DB] DROP CONSTRAINT FK_ECBMP_ECBMPDBCategory
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_BMP_DB]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_BMP_DB]
GO

CREATE TABLE [dbo].[EC_BMP_DB] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[BMP_CATEGORY_DB_ID] [int] NOT NULL ,
	[NAME] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[DESCRIPTION] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[WEBLINK] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[EC_BMP_DB] WITH NOCHECK ADD 
	CONSTRAINT [PK_EC_BMP_DB] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[EC_BMP_DB] ADD 
	CONSTRAINT [FK_EC_BMP_DB_STAT_CD_LKP_STAT_CD] FOREIGN KEY 
	(
		[STATUS_CD]
	) REFERENCES [dbo].[LOOKUP_STATUS_CODE] (
		[CODE]
	) ON UPDATE CASCADE ,
	CONSTRAINT [FK_ECBMP_ECBMPDBCategory] FOREIGN KEY 
	(
		[BMP_CATEGORY_DB_ID]
	) REFERENCES [dbo].[EC_BMP_CATEGORY_DB] (
		[ID]
	)
GO