if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ENV_SCC_INFO]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ENV_SCC_INFO]
GO

CREATE TABLE [dbo].[ENV_SCC_INFO] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SCC_NUMBER] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[SCC_DESCRIPTION] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CLIENT_ID] [int] NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ENV_SCC_INFO] WITH NOCHECK ADD 
	CONSTRAINT [PK_ENV_SCC_INFO] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO