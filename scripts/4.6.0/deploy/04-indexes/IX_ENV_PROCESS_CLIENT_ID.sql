

/****** Object:  Index [IX_ENV_PROCESS_CLIENT_ID]    Script Date: 03/27/2013 11:48:12 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[ENV_PROCESS]') AND name = N'IX_ENV_PROCESS_CLIENT_ID')
DROP INDEX [IX_ENV_PROCESS_CLIENT_ID] ON [dbo].[ENV_PROCESS] WITH ( ONLINE = OFF )
GO



/****** Object:  Index [IX_ENV_PROCESS_CLIENT_ID]    Script Date: 03/27/2013 11:48:12 ******/
CREATE NONCLUSTERED INDEX [IX_ENV_PROCESS_CLIENT_ID] ON [dbo].[ENV_PROCESS] 
(
	[CLIENT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


