if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EC_EVENT_DOCUMENT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EC_EVENT_DOCUMENT]
GO

CREATE TABLE [dbo].[EC_EVENT_DOCUMENT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[EVENT_ID] [int] NOT NULL ,
	[NAME] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[LOCATION] [varchar] (500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
) ON [PRIMARY]
GO

