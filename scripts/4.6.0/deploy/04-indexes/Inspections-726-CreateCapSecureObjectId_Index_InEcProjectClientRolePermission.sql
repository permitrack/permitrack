
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
CREATE NONCLUSTERED INDEX IX_EC_CLIENT_PROJECT_ROLE_PERMISSION_CAP_SECURE_OBJECT ON dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION
	(
	CAP_SECURE_OBJECT_ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION SET (LOCK_ESCALATION = TABLE)
GO
COMMIT


