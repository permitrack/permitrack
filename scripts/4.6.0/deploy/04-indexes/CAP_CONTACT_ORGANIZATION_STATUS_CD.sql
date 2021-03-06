

/****** Object:  Index [CAP_CONTACT_ORGANIZATION_STATUS_CD]    Script Date: 03/11/2013 12:15:39 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[CAP_CONTACT_ORGANIZATION]') AND name = N'CAP_CONTACT_ORGANIZATION_STATUS_CD')
DROP INDEX [CAP_CONTACT_ORGANIZATION_STATUS_CD] ON [dbo].[CAP_CONTACT_ORGANIZATION] WITH ( ONLINE = OFF )
GO



/****** Object:  Index [CAP_CONTACT_ORGANIZATION_STATUS_CD]    Script Date: 03/11/2013 12:15:39 ******/
CREATE NONCLUSTERED INDEX [CAP_CONTACT_ORGANIZATION_STATUS_CD] ON [dbo].[CAP_CONTACT_ORGANIZATION] 
(
	[STATUS_CD] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO


