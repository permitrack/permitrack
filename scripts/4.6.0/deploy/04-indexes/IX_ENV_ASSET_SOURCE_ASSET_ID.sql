

/****** Object:  Index [IX_ENV_ASSET_SOURCE_ASSET_ID]    Script Date: 03/27/2013 11:33:22 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[ENV_ASSET_SOURCE]') AND name = N'IX_ENV_ASSET_SOURCE_ASSET_ID')
DROP INDEX [IX_ENV_ASSET_SOURCE_ASSET_ID] ON [dbo].[ENV_ASSET_SOURCE] WITH ( ONLINE = OFF )
GO


/****** Object:  Index [IX_ENV_ASSET_SOURCE_ASSET_ID]    Script Date: 03/27/2013 11:33:22 ******/
CREATE NONCLUSTERED INDEX [IX_ENV_ASSET_SOURCE_ASSET_ID] ON [dbo].[ENV_ASSET_SOURCE] 
(
	[ASSET_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


