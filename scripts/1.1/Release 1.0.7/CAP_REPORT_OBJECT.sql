

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_CAP_REPORT_OBJECT_CAP_MODULE]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
alter table [dbo].[CAP_REPORT_OBJECT] DROP CONSTRAINT FK_CAP_REPORT_OBJECT_CAP_MODULE
GO


if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CAP_REPORT_OBJECT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[CAP_REPORT_OBJECT]
GO

CREATE TABLE [dbo].[CAP_REPORT_OBJECT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[MODULE_ID] [int] NOT NULL ,
	[CODE] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[NAME] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[DESCRIPTION] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[CAP_REPORT_OBJECT] WITH NOCHECK ADD 
	CONSTRAINT [PK_CAP_REPORT_OBJECT] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[CAP_REPORT_OBJECT] ADD 
	CONSTRAINT [FK_CAP_REPORT_OBJECT_CAP_MODULE] FOREIGN KEY 
	(
		[Module_ID]
	) REFERENCES [dbo].[CAP_MODULE] (
		[ID]
	)
GO