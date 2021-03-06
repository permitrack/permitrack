IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[EC_CLIENT_INFORMATION]') AND name = N'IX_EC_CLIENT_INFORMATION_CLIENT_ID')
DROP INDEX [IX_EC_CLIENT_INFORMATION_CLIENT_ID] ON [dbo].[EC_CLIENT_INFORMATION] WITH ( ONLINE = OFF )
GO

CREATE NONCLUSTERED INDEX [IX_EC_CLIENT_INFORMATION_CLIENT_ID] ON [dbo].[EC_CLIENT_INFORMATION] 
(
	[CLIENT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


