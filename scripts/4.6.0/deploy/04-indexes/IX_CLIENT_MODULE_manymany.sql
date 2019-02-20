IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[CLIENT_MODULE]') AND name = N'IX_CLIENT_MODULE_manymany')
DROP INDEX [IX_CLIENT_MODULE_manymany] ON [dbo].[CLIENT_MODULE] WITH ( ONLINE = OFF )
GO

CREATE NONCLUSTERED INDEX [IX_CLIENT_MODULE_manymany] ON [dbo].[CLIENT_MODULE] 
(
	[CLIENT_ID] ASC,
	[MODULE_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO

