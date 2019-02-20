

/****** Object:  Index [IX_CLIENT_ID]    Script Date: 03/11/2013 12:12:45 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[CAP_CONTACT_ORGANIZATION]') AND name = N'IX_CLIENT_ID')
DROP INDEX [IX_CLIENT_ID] ON [dbo].[CAP_CONTACT_ORGANIZATION] WITH ( ONLINE = OFF )
GO



/****** Object:  Index [IX_CLIENT_ID]    Script Date: 03/11/2013 12:12:45 ******/
CREATE NONCLUSTERED INDEX [IX_CLIENT_ID] ON [dbo].[CAP_CONTACT_ORGANIZATION] 
(
	[CLIENT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


