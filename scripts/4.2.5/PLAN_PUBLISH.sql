if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[PLAN_PUBLISH]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[PLAN_PUBLISH]
GO

CREATE TABLE [dbo].[PLAN_PUBLISH] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[PLAN_ID] [int] NOT NULL ,
	[START_DATE] [datetime] NOT NULL ,
	[END_DATE] [datetime] NOT NULL ,
	[STATUS_CD] [varchar] (4) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NULL ,
	[UPDATE_TS] [datetime] NULL ,
	[UPDATE_USER_ID] [int] NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PLAN_PUBLISH] WITH NOCHECK ADD 
	CONSTRAINT [PK_PLAN_PUBLISH] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO

