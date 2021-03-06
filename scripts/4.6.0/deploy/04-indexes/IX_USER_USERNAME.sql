IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[USER]') AND name = N'IX_USER_USERNAME')
DROP INDEX [IX_USER_USERNAME] ON [dbo].[USER] WITH ( ONLINE = OFF )
GO

CREATE NONCLUSTERED INDEX [IX_USER_USERNAME] ON [dbo].[USER] 
(
	[USERNAME] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


