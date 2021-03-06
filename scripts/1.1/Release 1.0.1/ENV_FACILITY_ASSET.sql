CREATE TABLE [dbo].[ENV_FACILITY_ASSET] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[FACILITY_ID] [int] NOT NULL ,
	[ASSET_ID] [int] NOT NULL ,
	[CREATE_TS] [datetime] NOT NULL ,
	[UPDATE_TS] [datetime] NOT NULL ,
	[UPDATE_USER_ID] [int] NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ENV_FACILITY_ASSET] WITH NOCHECK ADD 
	CONSTRAINT [PK_ENV_FACILITY_ASSET] PRIMARY KEY  CLUSTERED 
	(
		[ID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[ENV_FACILITY_ASSET] ADD 
	CONSTRAINT [FK_ENV_FACILITY_ASSET_ENV_FACILITY] FOREIGN KEY 
	(
		[FACILITY_ID]
	) REFERENCES [dbo].[ENV_FACILITY] (
		[ID]
	),
	CONSTRAINT [FK_ENV_FACILITY_ASSET_ENV_ASSET] FOREIGN KEY 
	(
		[ASSET_ID]
	) REFERENCES [dbo].[ENV_ASSET] (
		[ID]
	)
GO