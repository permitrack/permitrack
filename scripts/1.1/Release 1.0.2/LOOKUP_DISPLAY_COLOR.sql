if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[LOOKUP_ENV_DISPLAY_COLOR]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[LOOKUP_ENV_DISPLAY_COLOR]
GO

CREATE TABLE [dbo].[LOOKUP_ENV_DISPLAY_COLOR] (
	[CODE] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[DESCRIPTION] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ORDER_NUM] [int] NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[LOOKUP_ENV_DISPLAY_COLOR] WITH NOCHECK ADD 
	CONSTRAINT [PK_LOOKUP_ENV_DISPLAY_COLOR] PRIMARY KEY  CLUSTERED 
	(
		[CODE]
	)  ON [PRIMARY] 
GO

