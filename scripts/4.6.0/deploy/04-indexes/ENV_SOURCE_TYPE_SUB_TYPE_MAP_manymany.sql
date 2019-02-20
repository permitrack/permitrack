

/****** Object:  Index [ENV_SOURCE_TYPE_SUB_TYPE_MAP_manymany]    Script Date: 03/27/2013 11:56:06 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[ENV_SOURCE_TYPE_SUB_TYPE_MAP]') AND name = N'ENV_SOURCE_TYPE_SUB_TYPE_MAP_manymany')
DROP INDEX [ENV_SOURCE_TYPE_SUB_TYPE_MAP_manymany] ON [dbo].[ENV_SOURCE_TYPE_SUB_TYPE_MAP] WITH ( ONLINE = OFF )
GO



/****** Object:  Index [ENV_SOURCE_TYPE_SUB_TYPE_MAP_manymany]    Script Date: 03/27/2013 11:56:06 ******/
CREATE NONCLUSTERED INDEX [ENV_SOURCE_TYPE_SUB_TYPE_MAP_manymany] ON [dbo].[ENV_SOURCE_TYPE_SUB_TYPE_MAP] 
(
	[SOURCE_TYPE_CD] ASC,
	[SUBSTANCE_TYPE_CD] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO

