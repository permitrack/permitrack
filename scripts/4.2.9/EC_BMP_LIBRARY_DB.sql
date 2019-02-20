if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_BMP_LIBRARY_DB]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_BMP_LIBRARY_DB]
GO

CREATE TABLE [dbo].[EC_BMP_LIBRARY_DB] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[NAME] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[EC_BMP_LIBRARY_DB] WITH NOCHECK ADD 
	CONSTRAINT [PK_EC_BMP_LIBRARY_DB] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO