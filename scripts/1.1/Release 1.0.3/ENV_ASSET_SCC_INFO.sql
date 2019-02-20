if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ENV_ASSET_SCC_INFO]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ENV_ASSET_SCC_INFO]
GO

CREATE TABLE [dbo].[ENV_ASSET_SCC_INFO] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ASSET_ID] [int] NOT NULL ,
	[SCC_INFO_ID] [int] NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ENV_ASSET_SCC_INFO] ADD 
	CONSTRAINT [PK_ENV_ASSET_SCC_INFO] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[ENV_ASSET_SCC_INFO] ADD 
	CONSTRAINT [FK_ENV_ASSET_SCC_INFO_ENV_ASSET] FOREIGN KEY 
	(
		[ASSET_ID]
	) REFERENCES [dbo].[ENV_ASSET] (
		[ID]
	),
	CONSTRAINT [FK_ENV_ASSET_SCC_INFO_ENV_SCC_INFO] FOREIGN KEY 
	(
		[SCC_INFO_ID]
	) REFERENCES [dbo].[ENV_SCC_INFO] (
		[ID]
	)
GO