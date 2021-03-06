
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ENV_SCC_INFO_LIBRARY]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ENV_SCC_INFO_LIBRARY]
GO

CREATE TABLE [dbo].[ENV_SCC_INFO_LIBRARY] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SCC_NUMBER] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[SCC_DESCRIPTION] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[MAJ_INDUSTRIAL_GROUP] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[RAW_MATERIAL] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[EMITTING_PROCESS] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL 

) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ENV_SCC_INFO_LIBRARY] WITH NOCHECK ADD 
	CONSTRAINT [PK_ENV_SCC_INFO_LIBRARY] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	) WITH  FILLFACTOR = 90  ON [PRIMARY] 
GO

