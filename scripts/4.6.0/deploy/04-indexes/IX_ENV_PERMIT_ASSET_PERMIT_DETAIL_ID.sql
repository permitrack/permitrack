

/****** Object:  Index [IX_ENV_PERMIT_ASSET_PERMIT_DETAIL_ID]    Script Date: 03/27/2013 11:42:37 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[ENV_PERMIT_ASSET]') AND name = N'IX_ENV_PERMIT_ASSET_PERMIT_DETAIL_ID')
DROP INDEX [IX_ENV_PERMIT_ASSET_PERMIT_DETAIL_ID] ON [dbo].[ENV_PERMIT_ASSET] WITH ( ONLINE = OFF )
GO



/****** Object:  Index [IX_ENV_PERMIT_ASSET_PERMIT_DETAIL_ID]    Script Date: 03/27/2013 11:42:37 ******/
CREATE NONCLUSTERED INDEX [IX_ENV_PERMIT_ASSET_PERMIT_DETAIL_ID] ON [dbo].[ENV_PERMIT_ASSET] 
(
	[PERMIT_DETAIL_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


