

/****** Object:  Index [IX_ENV_FACILITY_CONTACTS]    Script Date: 03/27/2013 11:39:44 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[ENV_FACILITY_CONTACT]') AND name = N'IX_ENV_FACILITY_CONTACTS')
ALTER TABLE [dbo].[ENV_FACILITY_CONTACT] DROP CONSTRAINT [IX_ENV_FACILITY_CONTACTS]
GO



/****** Object:  Index [IX_ENV_FACILITY_CONTACTS]    Script Date: 03/27/2013 11:39:44 ******/
ALTER TABLE [dbo].[ENV_FACILITY_CONTACT] ADD  CONSTRAINT [IX_ENV_FACILITY_CONTACTS] UNIQUE NONCLUSTERED 
(
	[CAP_CONTACT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 20) ON [PRIMARY]
GO

