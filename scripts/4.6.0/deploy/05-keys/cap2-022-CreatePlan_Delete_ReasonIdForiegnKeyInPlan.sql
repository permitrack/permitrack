/*
   Tuesday, January 01, 20132:10:58 PM
   User: 
   Server: OWNER-PC\INSTANCE1
   Database: cap2
   Application: 
*/

/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
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
ALTER TABLE dbo.[PLAN] SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.[PLAN]', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.[PLAN]', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.[PLAN]', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.PLAN_DELETE_REASON ADD CONSTRAINT
	FK_PLAN_DELETE_REASON_PLAN FOREIGN KEY
	(
	PLAN_ID
	) REFERENCES dbo.[PLAN]
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.PLAN_DELETE_REASON SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.PLAN_DELETE_REASON', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.PLAN_DELETE_REASON', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.PLAN_DELETE_REASON', 'Object', 'CONTROL') as Contr_Per 