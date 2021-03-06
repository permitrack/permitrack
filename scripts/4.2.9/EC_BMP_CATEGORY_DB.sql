if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ECBMP_CATEGORY_DB_ECBMP_LIBRARY_DB]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[EC_BMP_CATEGORY_DB] DROP CONSTRAINT FK_ECBMP_CATEGORY_DB_ECBMP_LIBRARY_DB
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_BMP_CATEGORY_DB]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_BMP_CATEGORY_DB]
GO

CREATE TABLE [dbo].[EC_BMP_CATEGORY_DB] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[NAME] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[BMP_LIBRARY_DB_ID] [int] NOT NULL,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[EC_BMP_CATEGORY_DB] WITH NOCHECK ADD 
	CONSTRAINT [PK_EC_BMP_CATEGORY_DB] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO


ALTER TABLE [dbo].[EC_BMP_CATEGORY_DB] ADD 
	CONSTRAINT [FK_ECBMP_CATEGORY_DB_ECBMP_LIBRARY_DB] FOREIGN KEY 
	(
		[BMP_LIBRARY_DB_ID]
	) REFERENCES [dbo].[EC_BMP_LIBRARY_DB] (
		[ID]
	)
GO