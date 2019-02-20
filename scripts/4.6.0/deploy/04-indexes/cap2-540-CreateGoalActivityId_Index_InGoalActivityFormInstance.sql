
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE NONCLUSTERED INDEX IX_GOAL_ACTIVITY_FORM_INSTANCE_GOAL_ACTIVITY_ID ON dbo.GOAL_ACTIVITY_FORM_INSTANCE
	(
	GOAL_ACTIVITY_ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE dbo.GOAL_ACTIVITY_FORM_INSTANCE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT


